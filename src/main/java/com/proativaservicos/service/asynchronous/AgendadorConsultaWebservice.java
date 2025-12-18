package com.proativaservicos.service.asynchronous;

import com.proativaservicos.model.Importacao;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.ImportacaoService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.TipoConsultaEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Singleton
@Startup
public class AgendadorConsultaWebservice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CampanhaService campanhaService;

    @Inject
    private ImportacaoService importacaoService;

    @Inject
    private ConsultaAssincronaRefinBmg consultaRefin;

    @Inject
    private ConsultaAssincronaCartaoBeneficio consultaBeneficio;

    @Inject
    private ConsultaAssincronaMasterSaqueExecutor consultaSaqueMaster;

    @Inject
    private ConsultaAssincronaSaqueBmg consultaSaque;

    @Inject
    private ConsultaAssincronaApiPanSimulacao consultaPan;

    @Inject
    private ConsultaAssincronaSeguro consultaSeguro;

    @Inject
    private UsuarioService serviceUsuarService;

    @Inject
    private ImportacaoService serviceImportacao;

    @Inject
    private CampanhaService serviceCampanha;

    @Resource
    TimerService timerService;

    @PostConstruct
    public void init() {

        System.out.println("INICIANDO O TEMPORIZADOR.... ");
        setTemporizadorInit();
    }

    public void agendamentoConsultaRefin(Importacao importacao) {

        System.out.println("ADICIONANDO AGENDAMENTO CONSULTA ... " + importacao.getAgendamentoConsulta());
        System.out.println("TIPO: " + importacao.getCampanha().getIntegrarWs().getTipoIntegracao());

        if (importacao.getId() != null && importacao.getCampanha() != null) {

            TimerConfig tr = new TimerConfig();
            tr.setInfo(importacao);
            if (importacao.getAgendamentoConsulta() != null)
                this.timerService.createSingleActionTimer(importacao.getAgendamentoConsulta(), tr);
            else {
                System.out.println("DATA NÃO SELECIONADA.");
            }
        }
    }

    public void setTemporizadorInit() {

        System.out.println("INICIANDO AGENDADOR CONSULTA....");

        List<Long> listCampanhas = this.campanhaService.pesquisarIdsCampanhasConsulta();

        if (CollectionUtils.isNotEmpty(listCampanhas)) {

            List<Importacao> listImportacao = this.serviceImportacao.pesquisarImportacaoPorCampanhas(listCampanhas);

            if (CollectionUtils.isNotEmpty(listImportacao)) {

                for (Importacao importacao : listImportacao) {

                    importacao.setCampanha(this.serviceCampanha.pesquisarCampanha(importacao.getCampanha().getId(), false));
                    TimerConfig tr = new TimerConfig();
                    tr.setPersistent(true);

                    tr.setInfo(importacao);

                    importacao.setAgendamentoConsulta(retornarDataConsulta(importacao.getAgendamentoConsulta()));

                    if (importacao.getAgendamentoConsulta() != null) {
                        this.timerService.createSingleActionTimer(importacao.getAgendamentoConsulta(), tr);
                        System.out.println("ARQUIVO: " + importacao.getNomeArquivo() + " | DATA AGENDAMENTO: "
                                + DateUtil.builder(importacao.getAgendamentoConsulta())
                                .formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto());
                    }
                }

            }
        }

    }

    private static Date retornarDataConsulta(Date data) {

        if (data == null)
            return null;

        if (data.before(new Date())) {

            return DateUtil.builder(new Date()).adicionarTempoData(DataEnum.MINUTO, 10).getData();

        } else {

            return data;
        }

    }

    @Timeout
    public void executaTarefa(jakarta.ejb.Timer timer) {

        Usuario usuario = this.serviceUsuarService.pesquisarUsuario(1L);

        if (timer != null && timer.getInfo() != null && timer.getInfo() instanceof Importacao) {

            Importacao importacao = (Importacao) timer.getInfo();
            System.out.println("Iniciando consulta agendada { " + importacao.getNomeArquivo() + " : { " + DateUtil.builder(importacao.getAgendamentoConsulta()).formatarStringParaData("dd/MM/yyyy HH:mm:ss").getDataTexto());
            this.campanhaService.atualizarCampanhaAgendamento(importacao.getCampanha());
            this.importacaoService.atualizarImportacaoConsulta(importacao.getId(), true);

            if (importacao.getCampanha().getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.REFIN)) {

                System.out.println("EXECUTANDO TAREFA: CONSULTA REFIN");
                this.consultaRefin.consultarProdutoRefin(importacao, importacao.getCampanha(), usuario,
                        importacao.getCampanha().getEmpresa());

            } else if (importacao.getCampanha().getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.CARTAO_BENEFICIO)) {

                System.out.println("EXECUTANDO TAREFA: CONSULTA VALIDANDO BENEFICIO");
                this.consultaBeneficio.consultarRetornoSeTemCartao(importacao, importacao.getCampanha(), usuario, importacao.getCampanha().getEmpresa());


            } else if (importacao.getCampanha().getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.SAQUE_MASTER)) {

                System.out.println("EXECUTANDO TAREFA: CONSULTA SAQUE BMG MASTER");
                this.consultaSaqueMaster.consultarSaque(importacao, importacao.getCampanha(), usuario, importacao.getCampanha().getEmpresa());


            } else if (importacao.getCampanha().getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.SAQUE)) {

                System.out.println("EXECUTANDO TAREFA: CONSULTA SAQUE: [" + importacao.getCampanha().getIntegrarWs().getTipoIntegracao() + " ]");

                if (importacao.getCampanha().getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.PAN_SIMULACAO_SAQUE))
                    this.consultaPan.consultarSimulacaoSaque(importacao, importacao.getCampanha(), usuario, importacao.getCampanha().getEmpresa());
                else
                    this.consultaSaque.consultarSaqueComplementar(importacao, importacao.getCampanha(), usuario, importacao.getCampanha().getEmpresa());

            } else if (importacao.getCampanha().getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().name().startsWith("SEGURO")) {

                System.out.println("EXECUTANDO TAREFA: CONSULTA SEGURO");
                this.consultaSeguro.consultarProdutoSeguro(importacao, importacao.getCampanha(), usuario, importacao.getEmpresa());

            }

        }

    }

}

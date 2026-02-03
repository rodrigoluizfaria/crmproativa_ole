package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.HistoricoAtendimento;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.HistoricoAtendimentoService;
import com.proativaservicos.service.StatusAtendimentoService;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import java.util.*;

@Named
@ViewScoped
public class TratativaBackofficeBean extends GenericBean {

    @Inject
    private AtendimentoService atendimentoService;

    @Inject
    private HistoricoAtendimentoService historicoAtendimentoService;

    @Inject
    private StatusAtendimentoService statusAtendimentoService;

    private Long idAtendimento;

    private Atendimento atendimento;

    private List<StatusAtendimento> listStatusAtendimento;
    private List<HistoricoAtendimento> listHistoricoAtendimento;

    private String statusFinal;

    private boolean enviarEmail;

    private boolean enviarSms;

    private String repostaN2Aux;


    public void inicializar() {

        this.atendimento = this.atendimentoService.pesquisarAtendimentoSacPorCodigo(idAtendimento);
        this.repostaN2Aux = null;

        if (StringUtils.isNotBlank(atendimento.getRespostaN2())) {
            this.repostaN2Aux = atendimento.getRespostaN2();
            this.atendimento.setRespostaN2("");
        }


        inicializarVariaveis();
    }


    public void salvar() {

        try {
            System.out.println("Salvando atendimento: " + atendimento.getProtocolo());
            validarAtendimento();

            this.atendimento.setDataFechamentoDemanda(new Date());
            this.atendimento.setResponsavelN2(retornarUsuarioSessao());
            this.atendimento.setUsuarioAlteracao(retornarUsuarioSessao());
            this.atendimento.setDataAlteracao(new Date());
            // this.atendimento.setDemandaEncerrada(Boolean.TRUE);

            aplicarRegraDevolucao(this.atendimento);

            alterar(this.atendimento);
            criarHistoricoAtendimento();
            Faces.redirect("/crmproativa/pages/backoffice/backoffice_fila.jsf");
            Messages.addFlashGlobalInfo("Atendimento salvo com sucesso!");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inesperado.");
        }
    }


    private void aplicarRegraDevolucao(Atendimento atendimento) {

        if (atendimento.getStatus().getAcao().equals(AcaoStatusAtendimentoEnum.DEVOLVER) || atendimento.getStatus().getAcao().equals(AcaoStatusAtendimentoEnum.EM_ANALISE)) {
            atendimento.setDemandaEncerrada(Boolean.FALSE);
            atendimento.setAtendimentoFinalizado(Boolean.FALSE);

        }
    }


    private void validarAtendimento() throws ProativaException {

        List<String> erros = new ArrayList<>();

        if (this.atendimento.getStatus() == null) {
            erros.add("Informe o status de atendimento final");
        }
        if (StringUtils.isBlank(this.atendimento.getRespostaN2())) {
            erros.add("Informe a resolução do atendimento");
        }

        if (!erros.isEmpty()) {
            throw new ProativaException(String.join("; ", erros));
        }

        if (this.atendimento.getStatus().getAcao().equals(AcaoStatusAtendimentoEnum.CONCLUIR)) {
            this.atendimento.setDemandaEncerrada(Boolean.TRUE);
            this.atendimento.setAtendimentoFinalizado(Boolean.TRUE);

        }


    }

    private void inicializarVariaveis() {

        this.listStatusAtendimento = this.statusAtendimentoService.pesquisarStatusAtendimentoPorAcao(Arrays.asList(AcaoStatusAtendimentoEnum.CONCLUIR, AcaoStatusAtendimentoEnum.DEVOLVER, AcaoStatusAtendimentoEnum.EM_ANALISE), retornarEmpresaUsuarioSessao().getId());

        if (this.atendimento != null && this.atendimento.getId() != null)
            this.listHistoricoAtendimento = this.historicoAtendimentoService.pesquisarHistoricoSacPorAtendimento(this.atendimento.getId());

        if (CollectionUtils.isNotEmpty(this.listHistoricoAtendimento)) {

            // aplica filtro se necessário
            Optional<HistoricoAtendimento> primeiroAtnOp =
                    this.listHistoricoAtendimento.stream()
                            .filter(h -> h.getDataCadastro() != null).min(Comparator.comparing(HistoricoAtendimento::getDataCadastro));

            primeiroAtnOp.ifPresent(historicoAtendimento -> this.atendimento.setObservacaoOriginal(historicoAtendimento.getObservacao()));


        }
    }

    private void criarHistoricoAtendimento() throws ProativaException {

        HistoricoAtendimento historicoAtendimento = HistoricoAtendimento.fromAtendimento(this.atendimento);
        inserir(historicoAtendimento);

    }

    public void buscarHistorico() {

    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<StatusAtendimento> getListStatusAtendimento() {
        return listStatusAtendimento;
    }

    public void setListStatusAtendimento(List<StatusAtendimento> listStatusAtendimento) {
        this.listStatusAtendimento = listStatusAtendimento;
    }

    public String getStatusFinal() {
        return statusFinal;
    }

    public void setStatusFinal(String statusFinal) {
        this.statusFinal = statusFinal;
    }

    public boolean isEnviarEmail() {
        return enviarEmail;
    }

    public void setEnviarEmail(boolean enviarEmail) {
        this.enviarEmail = enviarEmail;
    }

    public boolean isEnviarSms() {
        return enviarSms;
    }

    public void setEnviarSms(boolean enviarSms) {
        this.enviarSms = enviarSms;
    }

    public List<HistoricoAtendimento> getListHistoricoAtendimento() {
        return listHistoricoAtendimento;
    }

    public String getRepostaN2Aux() {
        return repostaN2Aux;
    }

    public void setRepostaN2Aux(String repostaN2Aux) {
        this.repostaN2Aux = repostaN2Aux;
    }
}

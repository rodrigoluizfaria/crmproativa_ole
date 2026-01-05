package com.proativaservicos.bean;


import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.dto.HistoricoAtividadesDto;
import com.proativaservicos.model.dto.ProtocoloDTO;
import com.proativaservicos.service.*;
import com.proativaservicos.util.CorreiosUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Named
@ViewScoped
public class FichaAtendimentoSacBean extends GenericBean {


    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private AtendimentoAudiosService atendimentoAudiosService;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private AtendimentoAtivoService serviceAtendimentoAtivo;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private MotivoService motivoService;

    @Inject
    private SubMotivoService subMotivoService;

    @Inject
    private FormaPagamentoService serviceFormaPagamento;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private DepartamentoService serviceDepartamento;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private PabxService servicePabx;

    @Inject
    private PontoAtendimentoService servicePontoAtendimento;

    @Inject
    private PausaService servicePausa;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private CampanhaService campanhaService;

    @Inject
    private ClienteService clienteService;

    @Inject
    private StatusAtendimentoService statusAtendimentoService;

    @Inject
    private HistoricoAtividadeService serviceHistoricoAtividade;

    @Inject
    private RegistroSistemaDiarioService serviceRegistro;

    private List<ProtocoloDTO> listHistoricoProtocolosDTO;

    private List<HistoricoAtividadesDto> listHistoricoAtividadesDto;

    private List<PontoAtendimento> listPontoAtendimento;

    private List<Pausa> listPausas;

    private List<Motivo> listMotivo;

    private List<SubMotivo> listSubMotivo;

    private List<Atendimento> listProtocolosAtendimento;

    private List<HistoricoAtividade> listHistoricoAtividades;

    private List<Departamento> listDepartamentos;

    private Usuario usuario;

    private Pausa pausa;

    private Campanha campanha;

    private Atendimento atendimento;

    private Atendimento atendimentoVisualizar;

    private Cliente cliente;

    private Empresa empresa;

    private Endereco endereco;

    private HistoricoAtendimento historicoAtendimento;

    private CartaoCredito cartaoCreditoSelecionado;

    private Boolean novoCliente;

    private String cpf;

    private Long motivoSelecionadoId;

    private MotivoSolicitacaoSegundaViaCartaoEnum motivoSolicitacaoSegundaViaCartao;

    // 0=Lista, 1=SubLista, 2=Formulario
    private int stepClassificacao = 0;
    private int stepHistorico = 0;

    //CARTAO, SENHA, 2VIA, NOTAS,LIMITE -> MOCADO TROCAR PARA O ENUM....
    private String abaAtiva = "CARTAO";

    private Motivo motivoSelecionado;
    private SubMotivo subMotivoSelecionado;

    private boolean atendimentoIniciado = false;

    private boolean temAtendimentoFinalizado = false;

    private Double novoLimiteSolicitado;
    private String motivoSolicitacaoLimite;

    private Date dataInicioClassificacaoHistorico;

    private Long InicioAtendimentoMillis;

    private Long quantidadeAtendimento;

    private String protocoloPai;

    private Atendimento atendimentoPai;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        this.empresa = retornarEmpresaUsuarioSessao();
        String cpfTmp = (String) Faces.getSession().getAttribute("cpf_atn");
        Long codigoAtendimento = (Long) Faces.getSession().getAttribute("atendimento_iniciado");
        this.protocoloPai = (String) Faces.getSession().getAttribute("protocolo_pai");

        if (codigoAtendimento != null && this.atendimento == null) {

            System.out.println("Atendimento iniciado F5; atn" + codigoAtendimento);
            this.atendimento = this.serviceAtendimento.pesquisarAtendimentoPorId(codigoAtendimento);
            System.out.println("Atendimento iniciado F6: " + this.atendimento.getProtocolo());
            System.out.println("CODIGO PAI: " + this.atendimento.getId());
            System.out.println("Protocolo pai: " + this.protocoloPai);
        }

        inicializarAtendimento(cpfTmp);


        //   System.out.println("Ramal: " + this.usuario.getPontoAtendimento().getRamal());

        if (StringUtils.isBlank(cpfTmp)) {
            aguardandoAtendimentoLog();
        }


    }

    private void aguardandoAtendimentoLog() {

        try {

            String tipo = null;

            Object logSistem = this.serviceRegistro.pesquisarUltimoEvento(this.usuario.getId(), null);

            if (logSistem != null)
                tipo = (String) logSistem;


            if (StringUtils.isNotBlank(tipo) && !tipo.equals(TipoEventoEnum.AGUARDANDO_ATENDIMENTO.name()))
                this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.AGUARDANDO_ATENDIMENTO, "aguardando atendimento", usuario.getIp());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void inicializarListas() {

        this.listMotivo = this.motivoService.pesquisarMotivosPorEmpresa(retornarEmpresaUsuarioSessao().getId());
    }

    //INICIA UM NOVO ATENDIMENTO MESMO PROTOCOLO
    public void classificarAtendimento() {

        System.out.println("Iniciando atendimento...");
        System.out.println(atendimento.getMotivo().getDescricao());
        System.out.println(atendimento.getSubMotivo().getDescricao());
        this.dataInicioClassificacaoHistorico = new Date();
        ///SALVAR
        this.atendimentoIniciado = true;
        String detalhes = retornarDetalhesAtendimento();
        String descicao = (StringUtils.isBlank(this.atendimento.getObservacaoAdicional()) ? "" : " | " + this.atendimento.getObservacaoAdicional());
        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO, detalhes, descicao, this.usuario, "pi pi-user", new Date(), this.protocoloPai);
        Messages.addGlobalInfo("Sua classificação registrada com sucesso!");
        this.historicoAtendimento = new HistoricoAtendimento();


    }


    public void salvarSolicitacaoAtendimento() {

        try {

            validarSalvarAtendimento();
            System.out.println("Salnvando solicitação de atendimento");

            System.out.println(this.cliente.getNome() + " - " + cliente.getCpf());
            System.out.println("Motivo: " + this.atendimento.getMotivo().getDescricao());
            System.out.println("Sub Motico: " + this.atendimento.getSubMotivo().getDescricao());
            System.out.println("AObs: " + this.atendimento.getObservacao());
            System.out.println("Obs Adicioal: " + this.atendimento.getObservacaoAdicional());
            System.out.println("ENVIAR N2: " + this.atendimento.getEnviarN2());

            System.out.println("SALVAR HISTORICO DE ATENDIMENTO.......");
            PrimeFaces.current().executeScript("PF('dlgFinalizar').hide();");
            Messages.addGlobalInfo("Atendimento salvo com sucesso");
            String descricao = StringUtils.defaultString(this.atendimento.getObservacao(), "Sem observação");

            inserirAtividadesAtendimentos(determinarStatusAtividade(), retornarDetalhesAtendimento(), descricao, this.usuario, "pi pi-user", new Date(), this.protocoloPai);
            System.out.println("FILHA> " + atendimento.getId());
            gerarNovoAtendimento();
            resetarAtendimento();
            temAtendimentoFinalizado = true;

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    public void derivarAtendimentoN2() {

        try {

            validarSalvarAtendimento();
            System.out.println("Derivar: " + this.atendimento.getObservacao());
            this.atendimento.setEnviarN2(Boolean.TRUE);
            String descricao = StringUtils.defaultString(this.atendimento.getObservacao(), "Sem observação");
            inserirAtividadesAtendimentos(determinarStatusAtividade(), retornarDetalhesAtendimento(), descricao, this.usuario, "pi pi-user", new Date(), this.protocoloPai);
            gerarNovoAtendimento();
            resetarAtendimento();
            temAtendimentoFinalizado = true;
            PrimeFaces.current().executeScript("PF('dlgDerivarN2').hide();");
            Messages.addGlobalInfo("Atendimento derivado com sucesso");

        } catch (ProativaException e) {
            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());
        }
    }

    private TipoStatusAtividadesEnum determinarStatusAtividade() {

        Boolean enviarN2 = this.atendimento.getEnviarN2();
        System.out.println(enviarN2 + " ENVIAR N2");

        return (enviarN2 != null && enviarN2)
                ? TipoStatusAtividadesEnum.SOLICITACAO_ABERTA
                : TipoStatusAtividadesEnum.ATENDIMENTO_FINALIZADO;
    }

    private void validarSalvarAtendimento() throws ProativaException {

        if (StringUtils.isBlank(this.atendimento.getObservacao()))
            throw new ProativaException("Informe o manifesto do atendimento");
    }

    private void validarEncerrarAtendimento() throws ProativaException {

        System.out.println("ATENDIMENTO INICIADO: " + this.atendimentoIniciado);

        if (this.atendimentoIniciado)
            throw new ProativaException("Não é possível prosseguir: há um atendimento iniciado que deve ser encerrado.");

        if (!temAtendimentoFinalizado)
            throw new ProativaException("Não é possível prosseguir: finalize pelo menos um atendimento antes de continuar.");

        if (StringUtils.isBlank(this.cliente.getNome()))
            throw new ProativaException("É necessário informar o nome do cliente para continuar.");

        this.serviceAtendimento.deletarAtendimentoSemClassificacao(this.cliente.getId(), this.protocoloPai);

        this.serviceAtendimento.atualizarProtocoloDataFimAtendimento(this.cliente.getId(), this.protocoloPai, new Date());


    }


    public void encerrarAtendimento() {

        try {

            ///SALVAR ATENDIMENTO???? GERAR LOG....
            validarEncerrarAtendimento();
            System.out.println("Encerrando atendimento");

            resetarAtendimento();

            this.atendimento = null;
            this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.ENCERROU_ATENDIMENTO, "Atendimento encerrado: Protocolo principal: " + protocoloPai);

            Faces.getSession().removeAttribute("protocolo_pai");
            Faces.getSession().removeAttribute("cpf_atn");
            Faces.getSession().removeAttribute("atendimento_iniciado");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Não foi possível concluir a operação.");

        }
    }


    private void gerarNovoAtendimento() {


        // this.atendimento.setId(null);
        this.atendimento.setUsuarioCadastro(this.usuario);
        this.atendimento.setDataCadastro(new Date());
        this.atendimento.setDataInicioAtendimento(this.dataInicioClassificacaoHistorico);
        this.atendimento.setDataFimAtendimento(new Date());
        this.atendimento.setDataAberturaDemanda(new Date());
        this.atendimento.setEmpresa(retornarEmpresaUsuarioSessao());

        if (this.atendimento.getEnviarN2() != null && this.atendimento.getEnviarN2()) {

            this.atendimento.setDataFechamentoDemanda(null);
            this.atendimento.setDataAberturaDemanda(new Date());
            this.atendimento.setDemandaEncerrada(Boolean.FALSE);

            if (this.atendimento.getSubMotivo() != null && this.atendimento.getSubMotivo().getPrazoDemanda() != null && this.atendimento.getSubMotivo().getPrazoDemanda() > 0)
                this.atendimento.setPrazoPrazoDemanda(DateUtil.builder(new Date()).adicionarTempoData(DataEnum.DIA, this.atendimento.getSubMotivo().getPrazoDemanda()).getData());

            this.atendimento.setAtendimentoFinalizado(Boolean.FALSE);
            this.atendimento.setStatus(retornarStatusDerivadorAtendimentoN2());

        } else {

            this.atendimento.setDataFechamentoDemanda(null);
            this.atendimento.setDataAberturaDemanda(null);
            this.atendimento.setDemandaEncerrada(null);
            this.atendimento.setAtendimentoFinalizado(Boolean.TRUE);
            this.atendimento.setStatus(retornarStatusFinalizadorAtendimentoN1());

        }
        //INSERIR HISTORICO
        try {
            this.atendimento.setUsuarioOcupado(null);

            if (this.atendimento.getId() == null)
                inserir(this.atendimento);
            else {
                alterar(this.atendimento);
            }

            gerarHistoricoAtendimento();

            if (CollectionUtils.isEmpty(this.listProtocolosAtendimento))
                this.listProtocolosAtendimento = new ArrayList<>();

            this.listProtocolosAtendimento.add(this.atendimento);
            carregarHistoricosAtendimentosMocado();
            //novo atendimento..... GERAR...

            iniciarObjetoAtendimento();

            String protocolo = gerarProtocolo();
            // cliente.setProtocolo(protocolo);
            //cliente.setUltimoProtocolo(protocolo);
            //this.atendimento.setProtocolo(protocolo);
            this.atendimento.setProtocoloPai(this.protocoloPai);
            this.atendimento.setAtendimentoPai(Boolean.FALSE);
            this.atendimento.setProtocolo(protocolo);
            this.cliente.setUltimoProtocolo(protocolo);
            this.atendimento.setUsuarioOcupado(this.usuario);
            inserir(this.atendimento);

            Faces.getSession().setAttribute("atendimento_iniciado", this.atendimento.getId());


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    private StatusAtendimento retornarStatusFinalizadorAtendimentoN1() {

        try {


            StatusAtendimento statusAtendimento = this.statusAtendimentoService.pesquisarStatusAtendimentoPorAcao(AcaoStatusAtendimentoEnum.CONCLUIR_N1, retornarEmpresaUsuarioSessao().getId());

            if (statusAtendimento == null) {
                statusAtendimento = new StatusAtendimento();
                statusAtendimento.setAcao(AcaoStatusAtendimentoEnum.CONCLUIR_N1);
                statusAtendimento.setDescricao("Finalizado N1");
                statusAtendimento.setUsuarioCadastro(retornarUsuarioSessao());
                statusAtendimento.setDataCadastro(new Date());
                statusAtendimento.setUsuarioAlteracao(retornarUsuarioSessao());
                statusAtendimento.setAtivo(TipoAcessoEnum.ATIVO);
                inserir(statusAtendimento);
                return statusAtendimento;
            }

            return statusAtendimento;

        } catch (ProativaException e) {
            e.printStackTrace();
            return null;
        }

    }

    private StatusAtendimento retornarStatusDerivadorAtendimentoN2() {

        try {


            StatusAtendimento statusAtendimento = this.statusAtendimentoService.pesquisarStatusAtendimentoPorAcao(AcaoStatusAtendimentoEnum.DERIVAR, retornarEmpresaUsuarioSessao().getId());

            if (statusAtendimento == null) {
                statusAtendimento = new StatusAtendimento();
                statusAtendimento.setAcao(AcaoStatusAtendimentoEnum.DERIVAR);
                statusAtendimento.setDescricao("Escalonado N2");
                statusAtendimento.setUsuarioCadastro(retornarUsuarioSessao());
                statusAtendimento.setDataCadastro(new Date());
                statusAtendimento.setUsuarioAlteracao(retornarUsuarioSessao());
                statusAtendimento.setAtivo(TipoAcessoEnum.ATIVO);
                inserir(statusAtendimento);
                return statusAtendimento;
            }

            return statusAtendimento;

        } catch (ProativaException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void entrarEmPausaAtendimento() {
        System.out.println("Pausando atendimento");
    }


    private void gerarHistoricoAtendimento() {


        HistoricoAtendimento historicoAtendimento = new HistoricoAtendimento();

        historicoAtendimento.setId(null);
        historicoAtendimento.setAtendimento(this.atendimento);
        historicoAtendimento.setMotivo(this.atendimento.getMotivo());
        historicoAtendimento.setSubMotivo(this.atendimento.getSubMotivo());
        historicoAtendimento.setStatusAtendimento(this.atendimento.getStatus());
        historicoAtendimento.setUsuario(retornarUsuarioSessao());
        historicoAtendimento.setDataCadastro(new Date());

        historicoAtendimento.setProtocolo(this.atendimento.getProtocolo());
        historicoAtendimento.setObservacao(this.atendimento.getObservacao());
        historicoAtendimento.setObservacaoAdicional(this.atendimento.getObservacaoAdicional());
        historicoAtendimento.setObservacaoN2(this.atendimento.getObservacaoN2());
        historicoAtendimento.setAnotacao(this.atendimento.getAnotacao());

        historicoAtendimento.setEnviarN2(this.atendimento.getEnviarN2());

        historicoAtendimento.setDataInicioAtendimento(this.dataInicioClassificacaoHistorico);

        historicoAtendimento.setDataFimAtendimento(new Date());

        historicoAtendimento.setDataAberturaDemanda(new Date());


        if (this.atendimento.getEnviarN2() != null && this.atendimento.getEnviarN2()) {

            historicoAtendimento.setDataFechamentoDemanda(null);
            historicoAtendimento.setDataAberturaDemanda(new Date());
            historicoAtendimento.setDemandaEncerrada(Boolean.FALSE);
            historicoAtendimento.setAtendimentoFinalizado(Boolean.FALSE);

        } else {

            historicoAtendimento.setDataFechamentoDemanda(null);
            historicoAtendimento.setDataAberturaDemanda(null);
            historicoAtendimento.setDemandaEncerrada(null);
            historicoAtendimento.setAtendimentoFinalizado(Boolean.TRUE);

        }
        //INSERIR HISTORICO
        try {

            System.out.println("Salvando historico....");
            inserir(historicoAtendimento);
            System.out.println(historicoAtendimento);


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    private String retornarDetalhesAtendimento() {

        if (this.atendimento == null || this.atendimento.getMotivo() == null || this.atendimento.getSubMotivo() == null)
            return "";
        return this.atendimento.getMotivo().getDescricao() + " -> " + this.atendimento.getSubMotivo().getDescricao();
    }

    private void resetarAtendimento() {

        resetClassificacao();
        atendimentoIniciado = false;
        atendimento.setObservacao(null);
        atendimento.setObservacaoAdicional(null);
        atendimento.setObservacaoN2(null);
        atendimento.setAnotacao(null);
        atendimento.setEnviarN2(null);
        atendimento.setResponsavelN2(null);

        this.historicoAtendimento = null;

    }


    public void onCliente() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String cpf = params.get("cpf");
        String audio = params.get("audioId");
        String numero = params.get("numero");

        System.out.printf("CPF=%s, Audio=%s, Numero=%s ", cpf, audio, numero);


        if (StringUtils.isNotBlank(cpf)) {

            inicializarAtendimento(cpf);

            Faces.getSession().setAttribute("cpf_atn", cpf);

            System.out.println("CLIENTE: " + this.cpf);
        }

        if (StringUtils.isNotBlank(audio) && this.usuario.getPontoAtendimento() != null) {

            String audioId = audio.replaceAll(".WAV", "").replaceAll(".wav", "");

            this.atendimentoAudiosService.salvarAtendimentoAudio(audioId,audioId, this.atendimento.getId(),
                    this.usuario.getPontoAtendimento().getPabx(), this.usuario.getPontoAtendimento().getRamal(),
                    new Date(), "ura", numero);

        }


        this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.INICIOU_ATENDIMENTO, "Atendimento iniciado: Protocolo principal: " + protocoloPai);

    }

    public void inicializarCliente(String cpf) throws ProativaException {

        if (StringUtils.isNotBlank(cpf)) {

            this.cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorCpf(cpf, true);

            if (this.cliente == null) {
                System.out.println("Inserindo cliente");
                criarNovoCliente(cpf);
            }
        }
    }

    public void inicializarAtendimento(String cpf) {

        try {

            if (StringUtils.isNotBlank(cpf)) {

                if (this.atendimento == null || this.atendimento.getId() == null) {

                    inicializarCliente(cpf);
                    //this.cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorCpf(cpf);
                    criarNovoAtendimento(this.cliente);

                    //  carregarAtividadesAtendimentosMocado();
                    System.out.println("Inserindo Atendimento...");
                    inserir(this.atendimento);

                    Faces.getSession().setAttribute("atendimento_iniciado", this.atendimento.getId());
                    Faces.getSession().setAttribute("protocolo_pai", this.atendimento.getProtocoloPai());
                    this.protocoloPai = this.atendimento.getProtocoloPai();


                } else {

                    System.out.println("Existe atn");
                    this.campanha = this.atendimento.getCampanha();
                    this.cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorId(this.atendimento.getCliente().getId(), true);
                    this.cliente.setProtocolo(this.protocoloPai);
                    this.cliente.setUltimoProtocolo(this.atendimento.getProtocolo());
                    //   this.listProtocolosAtendimento = this.serviceAtendimento.pesquisarAtendimentosSacPorCliente(cliente.getId());
                    this.listHistoricoAtividades = this.serviceHistoricoAtividade.pesquisarHistoricoAtividadePorProtocolo(this.atendimento.getProtocoloPai());

                    if (this.atendimento.getAtendimentoPai() != null && this.atendimento.getAtendimentoPai()) {

                        this.atendimentoPai = atendimento;

                    } else {
                        this.atendimentoPai = this.serviceAtendimento.pesquisarAtendimentoSacPorPrococolo(this.protocoloPai);
                    }

                }

                cliente.adicionarTelefone(new Telefone(Short.valueOf("31"), "999631311"));
                this.listProtocolosAtendimento = this.cliente.getListAtendimentos();
                this.quantidadeAtendimento = this.serviceAtendimento.buscarQuantidadeClientesAtendidosDiario(this.usuario.getId(), cliente.getProtocolo());
                inicializarListas();
                inicializarHistoricoAtividade();
                carregarHistoricosAtendimentosMocado();

            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inseperado");
        }

    }

    private void criarNovoCliente(String cpf) throws ProativaException {

        if (StringUtils.isNotBlank(cpf)) {

            this.cliente = new Cliente();
            this.cliente.setCpf(cpf);
            this.cliente.setUsuarioCadastro(retornarUsuarioSessao());
            this.cliente.setEmpresa(retornarEmpresaUsuarioSessao());
            this.cliente.setTipoClienteEnum(TipoClienteEnum.CLIENTE_NOVO);
            this.endereco = new Endereco();
            System.out.println("Inserindo cliente...");
            inserir(this.cliente);
            System.out.println("- ID CLIENTE: " + this.cliente.getId());
            System.out.println("- CPF: " + this.cliente.getCpf());
        }

    }

    private void criarNovoAtendimento(Cliente cliente) {

        iniciarObjetoAtendimento();
        String protocolo = gerarProtocolo();
        cliente.setProtocolo(protocolo);
        cliente.setUltimoProtocolo(protocolo);
        this.protocoloPai = protocolo;
        this.atendimento.setProtocolo(protocolo);
        this.atendimento.setProtocoloPai(protocolo);
        this.atendimento.setAtendimentoPai(Boolean.TRUE);

        this.atendimentoPai = atendimento;

        if (this.campanha == null) {
            System.out.println("Campanha não encontrada;");
        } else {
            System.out.println(this.campanha.getDescricao());
            this.atendimento.setCampanha(this.campanha);
        }

    }

    private void iniciarObjetoAtendimento() {

        this.atendimento = new Atendimento();
        this.atendimento.setDataCadastro(new Date());
        this.atendimento.setCpf(cliente.getCpf());
        this.atendimento.setCliente(cliente);
        this.atendimento.setTipoClienteEnum(TipoClienteEnum.CLIENTE_NOVO);
        this.atendimento.setUsuarioOcupado(this.usuario);
        this.endereco = new Endereco();
        this.campanha = this.campanhaService.pesquisarCampanhaPorTipo(retornarEmpresaUsuarioSessao().getId(), TipoCampanhaEnum.SAC);
        this.atendimento.setEmpresa(retornarEmpresaUsuarioSessao());
        this.atendimento.setCampanha(campanha);
    }

    private String gerarProtocolo() {

        if (StringUtils.isNotBlank(this.atendimento.getProtocolo()))
            return this.atendimento.getProtocolo();

        String formatoData = DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmm").getDataTexto();
        return formatoData + this.cliente.getId() + Utils.getNumeroRandomico();
    }

    private void carregarHistoricosAtendimentosMocado() {

        ///PESQUISAR NO BAMCP;;;
        //MOCADO...

        if (CollectionUtils.isEmpty(this.listProtocolosAtendimento)) {

            this.listHistoricoProtocolosDTO = new ArrayList<>();
            System.out.println("Inserir Mocado...");
       /*     this.listHistoricoProtocolosDTO.add(new ProtocoloDTO(
                    "SAC12345678",
                    "Encerrado",
                    "Reclamação",
                    "Reclamação sobre cobrança indevida na fatura",
                    "14/10/2024",
                    "21/10/2024"
            ));

            this.listHistoricoProtocolosDTO.add(new ProtocoloDTO(
                    "SAC23456789",
                    "Encerrado",
                    "Contestação",
                    "Contestação de compra não reconhecida",
                    "09/09/2024",
                    "16/09/2024"
            ));
*/
        } else {

            //ALTERAR DEPOS;;;;
            this.listHistoricoProtocolosDTO = new ArrayList<>();

            for (Atendimento atn : this.listProtocolosAtendimento) { // GenericAtendimento ou sua classe concreta

                // Pula o atendimento atual da lista (não mostra ele mesmo no histórico)
                if (!atn.getProtocolo().equals(this.atendimento.getProtocolo())) {


                    ProtocoloDTO protocoloDTO = new ProtocoloDTO();
                    protocoloDTO.setIdAtendimento(atn.getId());
                    protocoloDTO.setNumeroProtocolo(atn.getProtocolo());
                    if (atn.getUsuarioAlteracao() != null)
                        protocoloDTO.setUsuarioN1(atn.getUsuarioAlteracao().getNome());

                    // --- 1. DEFINE DATAS E DESCRIÇÕES BÁSICAS ---
                    if (atn.getMotivo() != null) {
                        protocoloDTO.setTipo(atn.getMotivo().getDescricao());
                    } else {
                        protocoloDTO.setTipo("Sem motivo");
                    }

                    if (atn.getSubMotivo() != null) {
                        protocoloDTO.setDescricao(atn.getSubMotivo().getDescricao());
                    } else {
                        protocoloDTO.setDescricao("Sem submotivo");
                    }

                    // --- 2. LÓGICA DE STATUS BASEADA NAS AÇÕES ---

                    // Status padrão inicial
                    StatusProtocoloEnum statusCalculado = StatusProtocoloEnum.ABERTO;

                    // CENÁRIO A: É uma demanda de Backoffice (N2)?
                    if (Boolean.TRUE.equals(atn.getEnviarN2())) {

                        // Define datas específicas de N2
                        protocoloDTO.setDataAbertura(atn.getDataAberturaDemanda());
                        protocoloDTO.setDataPrazo(atn.getPrazoPrazoDemanda());

                        if (Boolean.TRUE.equals(atn.getDemandaEncerrada())) {
                            statusCalculado = StatusProtocoloEnum.CONCLUIDO;
                        } else {
                            // Verifica se o prazo estourou (usando aquele método Transient que criamos)
                            if (atn.isPrazoEstourado()) {
                                statusCalculado = StatusProtocoloEnum.ATRASADO;
                            } else {
                                statusCalculado = StatusProtocoloEnum.EM_ANDAMENTO;
                            }
                        }

                    }
                    // CENÁRIO B: É um atendimento de N1 (Front)?
                    else {

                        // Define data de atendimento normal
                        protocoloDTO.setDataAbertura(atn.getDataInicioAtendimento());

                        // Analisa a Ação do Status (Se houver status selecionado)
                        if (atn.getStatus() != null && atn.getStatus().getAcao() != null) {

                            switch (atn.getStatus().getAcao()) {
                                case CONCLUIR:
                                case PROPOSTA_EFETIVADA:
                                case FIM_FILA:
                                case BLOQUEAR_CPF:
                                case SEM_ACAO:
                                case CONCLUIR_N1:
                                    statusCalculado = StatusProtocoloEnum.CONCLUIDO;
                                    break;

                                case AGENDAR:
                                case AGENDAR_GLOBAL:
                                case AGENDAR_DUAS_HORAS:
                                case AGENDAR_QUATRO_HORAS:
                                case AGENDAR_SEIS_HORAS:
                                case AGENDAR_VINTE_QUATRO_HORAS:

                                case CONTATO: // Contato realizado mas não fechou
                                    statusCalculado = StatusProtocoloEnum.EM_ANDAMENTO;
                                    break;

                                case DERIVAR:
                                case EM_ANALISE:
                                    statusCalculado = StatusProtocoloEnum.EM_ANDAMENTO;
                                    break;

                                case DEVOLVER:
                                    statusCalculado = StatusProtocoloEnum.ABERTO; // Devolveu pra fila
                                    break;

                                default:
                                    statusCalculado = StatusProtocoloEnum.ABERTO;
                                    break;
                            }
                        } else {
                            // Se não tem status, considera Aberto ou que a ligação caiu (Cancelado/Aberto)
                            statusCalculado = StatusProtocoloEnum.ABERTO;
                        }
                    }

                    protocoloDTO.setStatus(statusCalculado); // Seu DTO deve esperar o Enum StatusProtocoloEnum

                    // Adiciona na lista
                    listHistoricoProtocolosDTO.add(protocoloDTO);
                }
            }

        }
    }

    private void inicializarHistoricoAtividade() {

        if (CollectionUtils.isEmpty(this.listHistoricoAtividades)) {

            criarHistoricoAtividadeInicial();

        } else {

            System.out.println("POPULANDO LIST HISTORICO");

            for (HistoricoAtividade h : this.listHistoricoAtividades) {

                System.out.println(h.getDetalhes() + "- " + h.getDescricao());
                inserirAtividadesAtendimentos(h.getTipoStatusAtividade(), h.getDetalhes(), h.getDescricao(), h.getUsuario(), h.getTipoIcone(), h.getData(), h.getProtocolo(), true);
                if (h.getTipoStatusAtividade().isAtendimentoFinalizador())
                    this.temAtendimentoFinalizado = true;
            }

        }

    }

    private void criarHistoricoAtividadeInicial() {

        System.out.println("Criando inicio Atividade de Atendimento");
        String canal = (this.campanha == null || StringUtils.isBlank(this.campanha.getDescricao())) ? "Canal não informado" : this.campanha.getDescricao();
        String descricao = "Cliente conectado via " + canal;
        String detalhes = "Protocolo gerado automaticamente";

        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.INICIO_ATENDIMENTO, descricao, detalhes, null, "pi-phone", new Date(), this.protocoloPai, false);
    }

    private void carregarAtividadesAtendimentosMocado() {

        // Mocado === TEM QUE PEGAR DE OUTRA MANEIRA....

        this.listHistoricoAtividadesDto = new ArrayList<>();

      /*  listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.SOLICITACAO_AUMENTO_LINITE,
                "De R$ 15.000 para R$ 22.222", "Motivo: Viagem internacional", new Date(), "voce", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Consulta Disponível", "Consulta realizada com sucesso", new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Solicitação Aumento", "Cliente questionou taxas", new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Solicitação Aumento", "Simulação realizada", new Date(), "Você", "pi-user"));*/

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.INICIO_ATENDIMENTO, "Cliente conectado via URA", "Protocolo gerado automaticamente", new Date(), "Sistema", "pi-phone"));

    }

    private void inserirAtividadesAtendimentos(TipoStatusAtividadesEnum tipoAtendimento, String detalhes, String descicao, Usuario autor, String icon, Date data, String protocolo) {

        inserirAtividadesAtendimentos(tipoAtendimento, detalhes, descicao, autor, icon, data, protocolo, false);

    }

    private void inserirAtividadesAtendimentos(TipoStatusAtividadesEnum tipoAtendimento, String detalhes, String descicao, Usuario autor, String icon, Date data, String protocolo, boolean naoInserirHistoricoAtividade) {

        if (CollectionUtils.isEmpty(this.listHistoricoAtividadesDto))
            this.listHistoricoAtividadesDto = new ArrayList<>();


        this.listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(tipoAtendimento, descicao, detalhes, data, retornarAutor(autor), icon));

        //MUDAR PARA ID
        this.listHistoricoAtividadesDto.sort(Comparator.comparing(HistoricoAtividadesDto::getData).reversed());


        try {

            if (!naoInserirHistoricoAtividade) {
                //INSERIR NO BANCO...
                HistoricoAtividade historicoAtividade = new HistoricoAtividade();
                historicoAtividade.setTipoStatusAtividade(tipoAtendimento);
                historicoAtividade.setDescricao(descicao);
                historicoAtividade.setDetalhes(detalhes);
                historicoAtividade.setUsuario(autor);
                historicoAtividade.setCliente(this.cliente);
                historicoAtividade.setData(new Date());
                historicoAtividade.setTipoIcone(icon);
                historicoAtividade.setProtocolo(protocolo);
                inserir(historicoAtividade);

                String evento = "Protocolo: " + protocolo + " |" + detalhes + " | " + descicao;

                this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.retornarTipoEvento(tipoAtendimento.name()), evento);

            }

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inesperado");
        }


    }

    private String retornarAutor(Usuario usuario) {

        if (usuario == null || usuario.getId() == null)
            return "Sistema";
        if (usuario.getId().equals(this.usuario.getId()))
            return "Você";

        return usuario.getNomeSobrenome();

    }

    //AlTERAR PARA PABX.....
    public void iniciarPausaAtendimento() {

        try {

            if (pausa == null || this.pausa.getId() == null) {
                throw new ProativaException("Informe o motivo da pausa,");
            }


            if (pausa != null && pausa.getMaximoPausa() != null && pausa.getMaximoPausa() > 0) {

                Integer quantidadeUsuariosPausa = this.serviceControlePausa.pesquisarQuantidadeUsuarioEmPausa(pausa.getId(), this.empresa.getId());

                if (quantidadeUsuariosPausa >= pausa.getMaximoPausa()) {

                    throw new ProativaException("Quantidade de usuarios em pausa foi atingido: " + quantidadeUsuariosPausa + " Pausa: " + pausa.getDescricao());
                }
            }

            validarEncerrarAtendimento();

            String builder = "ATENDIMENTO FINALIZADO: " + this.atendimento.getNome()
                    + " | Protocolo: " + this.atendimento.getProtocolo();

            this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.FINALIZOU_ATENDIMENTO, builder);

            Date dataPausa = new Date();

            encerrarAtendimento();

            criarControlePausa(pausa, dataPausa);

            if (pausa.getCodigoInterno() != null && this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VONIX)) {

                //    realizarPausaVonix(String.valueOf(pausa.getCodigoInterno()));

            } /*else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null
                    && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS)
                    && StringUtils.isNotBlank(this.usuario.getPontoAtendimento().getApiToken()) && this.intervalo3c != null) {

                System.out.println("REALIZANDO PAUSA 3C");
                //this.tresCPlusServiceUtil.entrarEmPausa(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.intervalo3c, this.usuario.getPontoAtendimento().getApiToken());
               // qualificarChamada3c();

                // this.tresCPlusServiceUtil.qualificarChamada(this.usuario.getPontoAtendimento().getPabx().getUrl(),this.callId,this.qualification3c,this.usuario.getPontoAtendimento().getApiToken());

            }*/

            this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.PAUSA, pausa.getDescricao(), new Date());
            this.pausa = null;
            Faces.redirect("/crmproativa/pages/home/inicial.jsf");

        } catch (ProativaException e) {
            this.pausa = null;

            if (e.getMessage().contains("Por favor, responda")) {

                Messages.addGlobalWarn(e.getMessage());

            } else {
                Messages.addGlobalError(e.getMessage());
            }

            PrimeFaces.current().executeScript("PF('dlgPausa').hide();");

        } catch (Exception e) {
            this.pausa = null;
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
            PrimeFaces.current().executeScript("PF('dlgPausa').hide();");
        }

    }

    private void criarControlePausa(Pausa pausa, Date dataPausa) {

        try {

            ControlePausa controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario,
                    new Date(System.currentTimeMillis()));

            if (controlePausa != null)
                throw new ProativaException("Você já se encontra em Pausa");

            ControlePausa controleAtual = new ControlePausa(dataPausa, pausa, this.usuario, this.empresa);
            inserirGenerico((Serializable) controleAtual);

            // PAUSA DISCADORA....
           /* if (this.carregarJavaScriptVonix()) {

            } else {

                this.discadorUtil.iniciarPausaAtendimento(retornarServicoDiscador(), this.usuario, pausa, true);
            }*/

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void onChangeMotivo(Motivo mo) {
        this.motivoSelecionado = mo;
        this.atendimento.setMotivo(mo);
        this.listSubMotivo = this.subMotivoService.pesquisarSubMotivosPorMotivo(mo.getId(), TipoAcessoEnum.ATIVO);
        this.stepClassificacao = 1;
    }

    public void onSelecionarSubMotivo(SubMotivo sub) {

        this.atendimento.setSubMotivo(sub);
        this.subMotivoSelecionado = sub;
        this.stepClassificacao = 2;

        if (CollectionUtils.isNotEmpty(sub.getListTipoMetodosMotivos())) {
            this.abaAtiva = sub.getListTipoMetodosMotivos().get(0).name();
        } else {
            this.abaAtiva = null;
        }

    }

    public void setStepClassificacao(int step) {

        this.stepClassificacao = step;

        if (step == 0) {

            this.atendimento.setMotivo(null);
            this.atendimento.setSubMotivo(null);
            this.motivoSelecionado = null;
            this.subMotivoSelecionado = null;

        } else if (step == 1) {
            this.subMotivoSelecionado = null;
            this.atendimento.setSubMotivo(null);
        }
    }

    public void onSetStepModalHistorico(int step) {
        this.stepHistorico = step;
    }


    public void resetClassificacao() {
        setStepClassificacao(0);
    }

    public void onListarPontosAtendimentos() {

        this.listPontoAtendimento = this.servicePontoAtendimento.pesquisarPontoAtendimentosPorEmpresa(retornarEmpresaUsuarioSessao().getId());

    }

    //METODOS -> SO SUBMOTIVO... API....

    public void registrarSolicitacaoLimite() {

        // CHAMAR A API;;;


        Messages.addGlobalInfo("Solicitação de aumento registrada para análise.");

        // Limpar campos
        this.novoLimiteSolicitado = null;
        this.motivoSolicitacaoLimite = null;
    }

    public void trocarAba(String aba) {
        System.out.println("ABA: " + aba);
        this.abaAtiva = aba;
    }

    public void onAssociarRamal() {

        System.out.println("onAssociarRamal");

        if (this.usuario.getPontoAtendimento() != null) {

            System.out.println(this.usuario.getPontoAtendimento().getRamal());
            this.serviceUsuario.atualizarPontoAtendimento(this.usuario.getPontoAtendimento().getId(), this.usuario.getId());


        }
    }

    public void visualizarAtendimentoProtocolo(Long id) {
        this.atendimentoVisualizar = this.serviceAtendimento.pesquisarAtendimentoSacPorCodigo(id);
    }

    public void editarDadosCadastrais() {
        this.endereco = new Endereco();
    }

    public void onBuscarEndereco() {

        try {

            System.out.println("Buscando endereco CEP: " + this.endereco.getCep());

            if (StringUtils.isBlank(this.endereco.getCep()))
                throw new ProativaException("Informe o endereço");

            CorreiosUtil correiosUtil = new CorreiosUtil();

            Endereco end = correiosUtil.consultarEnderecoPorCep(endereco.getCep(), 1L);

            if (end != null) {
                this.endereco = end;
            } else {
                System.out.println("Endereco não localizado...");
            }


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void salvarDadosCadastrais() {
        System.out.println("SALVANDO DADOS CADASTRAIS");

        this.endereco.setCliente(this.cliente);
        System.out.println(this.endereco.getLogradouro());

        inserirAtividadesAtendimentos(
                TipoStatusAtividadesEnum.ATUALIZACAO_DADOS_CADASTRAIS,
                "Dados atualizados",
                "Atualização dos dados cadastrais",
                this.usuario,
                "pi pi-user",
                new Date(), this.protocoloPai
        );

        if (StringUtils.isNotBlank(this.cliente.getNome())) {
            this.clienteService.atualizarNomeCliente(this.cliente.getNome(), this.cliente.getId());
        }

        try {
            this.endereco.setId(null);
            inserir(this.endereco);
            this.cliente.adicionarEndereco(this.endereco);
            this.endereco = new Endereco();
            Messages.addGlobalInfo("Dados cadastrais atualizados com sucesso!");
        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void onBuscarDepartamentos() {
        this.listDepartamentos = this.serviceDepartamento.listarDepartamentosAtivos(TipoAcessoEnum.ATIVO);
    }

    public void limparClassificacao() {
        resetarAtendimento();
    }

    public void onListarPausa() {

        this.pausa = new Pausa();
        this.listPausas = this.servicePausa.pesquisarPausaPorEmpresa(retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<PontoAtendimento> getListPontoAtendimento() {
        return listPontoAtendimento;
    }

    public List<Motivo> getListMotivo() {
        return listMotivo;
    }

    public List<SubMotivo> getListSubMotivo() {
        return listSubMotivo;
    }

    public void setMotivoSelecionadoId(Long motivoSelecionadoId) {
        this.motivoSelecionadoId = motivoSelecionadoId;
    }

    public Long getMotivoSelecionadoId() {
        return motivoSelecionadoId;
    }

    public Motivo getMotivoSelecionado() {
        return motivoSelecionado;
    }

    public void setMotivoSelecionado(Motivo motivoSelecionado) {
        this.motivoSelecionado = motivoSelecionado;
    }

    public SubMotivo getSubMotivoSelecionado() {
        return subMotivoSelecionado;
    }

    public void setSubMotivoSelecionado(SubMotivo subMotivoSelecionado) {
        this.subMotivoSelecionado = subMotivoSelecionado;
    }

    public int getStepClassificacao() {
        return stepClassificacao;
    }

    public Boolean getNovoCliente() {
        return novoCliente;
    }

    public void setNovoCliente(Boolean novoCliente) {
        this.novoCliente = novoCliente;
    }

    public List<ProtocoloDTO> getListHistoricoProtocolosDTO() {


        return listHistoricoProtocolosDTO;
    }

    public boolean isAtendimentoIniciado() {
        return atendimentoIniciado;
    }

    // Getter e Setter
    public String getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(String abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

    public MotivoSolicitacaoSegundaViaCartaoEnum[] getListMotivoSolicitacaoSegundaViaCartao() {
        return MotivoSolicitacaoSegundaViaCartaoEnum.values();
    }

    public MotivoSolicitacaoSegundaViaCartaoEnum getMotivoSolicitacaoSegundaViaCartao() {
        return motivoSolicitacaoSegundaViaCartao;
    }

    public void setMotivoSolicitacaoSegundaViaCartao(MotivoSolicitacaoSegundaViaCartaoEnum motivoSolicitacaoSegundaViaCartao) {
        this.motivoSolicitacaoSegundaViaCartao = motivoSolicitacaoSegundaViaCartao;
    }

    public String getMotivoSolicitacaoLimite() {
        return motivoSolicitacaoLimite;
    }

    public void setMotivoSolicitacaoLimite(String motivoSolicitacaoLimite) {
        this.motivoSolicitacaoLimite = motivoSolicitacaoLimite;
    }

    public Double getNovoLimiteSolicitado() {
        return novoLimiteSolicitado;
    }

    public void setNovoLimiteSolicitado(Double novoLimiteSolicitado) {
        this.novoLimiteSolicitado = novoLimiteSolicitado;
    }

    public int getStepHistorico() {
        return stepHistorico;
    }

    public void setStepHistorico(int stepHistorico) {
        this.stepHistorico = stepHistorico;
    }

    public List<HistoricoAtividadesDto> getListHistoricoAtividadesDto() {

     /*   if (CollectionUtils.isNotEmpty(listHistoricoAtividadesDto)) {
            return listHistoricoAtividadesDto.stream()
                    .sorted(Comparator.comparing(HistoricoAtividadesDto::getData).reversed())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();*/

        return this.listHistoricoAtividadesDto;
    }


    public CartaoCredito getCartaoCreditoSelecionado() {
        return cartaoCreditoSelecionado;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pausa> getListPausas() {
        return listPausas;
    }

    public void setListPausas(List<Pausa> listPausas) {
        this.listPausas = listPausas;
    }

    public Pausa getPausa() {
        return pausa;
    }

    public void setPausa(Pausa pausa) {
        this.pausa = pausa;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    private void gerarClienteMocado(String cpf) {

        System.out.println("Cliente novo....");
        cliente = new Cliente();
        cliente.setTipoClienteEnum(TipoClienteEnum.CLIENTE_NOVO);
        atendimento = new Atendimento();


        inicializarListas();
        this.cpf = cpf;

        this.cliente.setCpf(cpf);
        this.cliente.setNome("JOSE ANTONIO");
        this.atendimento.setLimite(new BigDecimal("10258.52"));
        this.atendimento.setLimiteDisponivel(new BigDecimal("7443.09"));
        this.atendimento.setValorLiberado(new BigDecimal("3085.66"));
        this.atendimento.setValorMaxOperacao(new BigDecimal("7199.88"));

        String protoColo = gerarProtocolo();
        this.atendimento.setProtocolo(protoColo);
        cliente.setProtocolo(protoColo);
        cliente.setUltimoProtocolo(protoColo);

        this.cliente.setClienteVip(Boolean.TRUE);


        this.cartaoCreditoSelecionado = new CartaoCredito();
        cartaoCreditoSelecionado.setNumeroCartao("5117233260721226");
        cartaoCreditoSelecionado.setValidade("09/2027");
        cartaoCreditoSelecionado.setCodigoSeguranca(616);
        cartaoCreditoSelecionado.setTipo("Múltiplo");
        cartaoCreditoSelecionado.setCartaoAdicional(Boolean.FALSE);
        cartaoCreditoSelecionado.setBandeira("Visa");
        cartaoCreditoSelecionado.setStatus("BLOQUEADO");

        cartaoCreditoSelecionado.setLimiteTotal(new BigDecimal("15000.00"));
        cartaoCreditoSelecionado.setLimiteDisponivel(new BigDecimal("8500.00"));
        cartaoCreditoSelecionado.setLimiteEmergencial(new BigDecimal("2000.00"));

        Telefone telefone = new Telefone();
        telefone.setDdd(Short.valueOf("31"));
        telefone.setNumero("999631311");
        cliente.adicionarTelefone(telefone);

        BigDecimal limiteTotal = cartaoCreditoSelecionado.getLimiteTotal();
        BigDecimal limiteDisponivel = cartaoCreditoSelecionado.getLimiteDisponivel();


        BigDecimal usado = limiteTotal.subtract(limiteDisponivel);

        BigDecimal percentualUsado = usado
                .multiply(BigDecimal.valueOf(100))
                .divide(limiteTotal, RoundingMode.HALF_UP);

        System.out.println("Percentual usado: " + percentualUsado + "%");

        this.cliente.addCartao(cartaoCreditoSelecionado);
        carregarHistoricosAtendimentosMocado();

        this.quantidadeAtendimento = 3L;
    }

    public void setInicioAtendimentoMillis(Long inicioAtendimentoMillis) {
        InicioAtendimentoMillis = inicioAtendimentoMillis;
    }

    public long getInicioAtendimentoMillis() {


        if (this.atendimento != null && this.atendimento.getDataCadastro() != null) {
            return this.atendimento.getDataCadastro().getTime();
        }
        return System.currentTimeMillis(); // Ou System.currentTimeMillis() se quiser começar agora
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getQuantidadeAtendimento() {
        return quantidadeAtendimento;
    }

    public Atendimento getAtendimentoVisualizar() {
        return atendimentoVisualizar;
    }

    public void setAtendimentoVisualizar(Atendimento atendimentoVisualizar) {
        this.atendimentoVisualizar = atendimentoVisualizar;
    }

    public List<Departamento> getListDepartamentos() {
        return listDepartamentos;
    }
}

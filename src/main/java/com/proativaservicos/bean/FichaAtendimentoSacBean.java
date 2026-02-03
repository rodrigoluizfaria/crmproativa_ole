package com.proativaservicos.bean;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.dto.HistoricoAtividadesDto;
import com.proativaservicos.model.dto.ProtocoloDTO;
import com.proativaservicos.service.*;
import com.proativaservicos.util.*;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Inject
    private TelefoneService telefoneService;

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
    private Atendimento atendimentoAnterior;

    private Atendimento atendimentoVisualizar;

    private Cliente cliente;

    private Empresa empresa;

    private Endereco endereco;

    private HistoricoAtendimento historicoAtendimento;
    private HistoricoAtendimento historicoAtendimentoVisualizar;

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

    private String numeroCLiente;

    private String opcao;

    private boolean anonimo;

    private boolean precisaResponder;

    private List<Atendimento> listaDevolutivasPendentes;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        this.empresa = retornarEmpresaUsuarioSessao();
        String cpfTmp = (String) Faces.getSession().getAttribute("cpf_atn");
        Long codigoAtendimento = (Long) Faces.getSession().getAttribute("atendimento_iniciado");
        this.protocoloPai = (String) Faces.getSession().getAttribute("protocolo_pai");
        this.anonimo = Boolean.TRUE.equals(Faces.getSession().getAttribute("anonimo"));
        this.opcao = (String) Faces.getSession().getAttribute("opcao");
        this.numeroCLiente = (String) Faces.getSession().getAttribute("numeroCLiente");

        if (codigoAtendimento != null && this.atendimento == null) {

            //    System.out.println("Atendimento iniciado F5; atn" + codigoAtendimento);
            this.atendimento = this.serviceAtendimento.pesquisarAtendimentoPorId(codigoAtendimento);
            //   System.out.println("Atendimento iniciado F6: " + this.atendimento.getProtocolo());
            // System.out.println("CODIGO PAI: " + this.atendimento.getId());
            // System.out.println("Protocolo pai: " + this.protocoloPai);
        }

        inicializarAtendimento(cpfTmp);

        //   System.out.println("Ramal: " + this.usuario.getPontoAtendimento().getRamal());

        if (StringUtils.isBlank(cpfTmp) && !this.anonimo) {

            aguardandoAtendimentoLog();

            try {
                logarRamal();
            } catch (ProativaException e) {
                Messages.addGlobalError(e.getMessage());
            }

        }

    }

    public void logarRamal() throws ProativaException {

        if (this.usuario.getPontoAtendimento() == null || this.usuario.getPontoAtendimento().getPabx() == null || StringUtils.isBlank(this.usuario.getPontoAtendimento().getRamal()))
            throw new ProativaException("Nenhum ramal está associado.");

        if (this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.PST_PHONE)) {
            System.out.println("Logando... " + this.usuario.getPontoAtendimento().getRamal());
            String retorno = PabxUtil.logarPstPhone(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getRamal());

            if (!JsonUtil.isJSON(retorno))
                throw new ProativaException(retorno);

            try {

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(retorno);

                String status = node.get("status").asText();
                String mensagem = node.get("mensagem").asText();
                System.out.println(mensagem);

                if ("erro".equals(status)) {
                    throw new ProativaException(mensagem);
                }

                System.out.println("Logado:: " + mensagem);
                Messages.addGlobalInfo("Logado com sucesso!");

            } catch (Exception e) {
                e.printStackTrace();
                throw new ProativaException("Erro ao logar Ramal: ");
            }

        } else {
            throw new ProativaException("O pabx: " + this.usuario.getPontoAtendimento().getPabx().getDescricao() + ", não existe integração no momento.");
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

        //  System.out.println("Iniciando atendimento...");
        //  System.out.println(atendimento.getMotivo().getDescricao());
        //  System.out.println(atendimento.getSubMotivo().getDescricao());
        this.dataInicioClassificacaoHistorico = new Date();
        ///SALVAR
        this.atendimentoIniciado = true;
        String detalhes = retornarDetalhesAtendimento();
        String descicao = (StringUtils.isBlank(this.atendimento.getObservacaoAdicional()) ? "" : " | " + this.atendimento.getObservacaoAdicional());
        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO, detalhes, descicao, this.usuario, "pi pi-user", new Date(), this.protocoloPai);
        Messages.addGlobalInfo("Sua classificação registrada com sucesso!");
        this.historicoAtendimento = new HistoricoAtendimento();
        this.historicoAtendimento.setDataInicioAtendimento(new Date());
        verificarReincidencia();


    }


    public void salvarSolicitacaoAtendimento() {

        try {

            validarSalvarAtendimento();
            //  System.out.println("Salnvando solicitação de atendimento");

            //  System.out.println(this.cliente.getNome() + " - " + cliente.getCpf());
            //  System.out.println("Motivo: " + this.atendimento.getMotivo().getDescricao());
            //  System.out.println("Sub Motico: " + this.atendimento.getSubMotivo().getDescricao());
            //  System.out.println("AObs: " + this.atendimento.getObservacao());
            //  System.out.println("Obs Adicioal: " + this.atendimento.getObservacaoAdicional());
            //  System.out.println("ENVIAR N2: " + this.atendimento.getEnviarN2());
            //  System.out.println("SALVAR HISTORICO DE ATENDIMENTO.......");
            PrimeFaces.current().executeScript("PF('dlgFinalizar').hide();");
            Messages.addGlobalInfo("Atendimento salvo com sucesso");
            String descricao = StringUtils.defaultString(this.atendimento.getObservacao(), "Sem observação");

            inserirAtividadesAtendimentos(determinarStatusAtividade(), retornarDetalhesAtendimento(), descricao, this.usuario, "pi pi-user", new Date(), this.protocoloPai);
            // System.out.println("FILHA> " + atendimento.getId());
            verificarReincidenciaEAtualizarFCR(this.atendimento);
            gerarNovoAtendimento();
            resetarAtendimento();
            temAtendimentoFinalizado = true;

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }

    }

    public void derivarAtendimentoN2() {

        try {

            if (this.anonimo) {
                throw new ProativaException("Para derivar o atendimento é necessário informar o cliente.");
            }

            validarSalvarAtendimento();
            //  System.out.println("Derivar: " + this.atendimento.getObservacao());
            this.atendimento.setEnviarN2(Boolean.TRUE);
            this.atendimento.setFcr(Boolean.FALSE);
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
        //   System.out.println(enviarN2 + " ENVIAR N2");

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

        if (!this.anonimo && StringUtils.isBlank(this.cliente.getNome()))
            throw new ProativaException("É necessário informar o nome do cliente para continuar.");


        if (this.anonimo) {

            this.serviceAtendimento.deletarAtendimentoSemClassificacaoCodAtendimento(this.atendimento.getId(), this.protocoloPai);
            this.serviceAtendimento.atualizarProtocoloDataFimAtendimentoPorProtocolo(this.protocoloPai, new Date());

        } else {
            this.serviceAtendimento.deletarAtendimentoSemClassificacao(this.cliente.getId(), this.protocoloPai);
            this.serviceAtendimento.atualizarProtocoloDataFimAtendimento(this.cliente.getId(), this.protocoloPai, new Date());

        }

    }


    public void encerrarAtendimento() {

        try {


            validarEncerrarAtendimento();
            //gerarHistoricoAtendimentoFinal(this.atendimento);
            resetarAtendimento();

            this.atendimento = null;
            this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.ENCERROU_ATENDIMENTO, "Atendimento encerrado: Protocolo principal: " + protocoloPai);

            Faces.getSession().removeAttribute("protocolo_pai");
            Faces.getSession().removeAttribute("cpf_atn");
            Faces.getSession().removeAttribute("atendimento_iniciado");
            Faces.getSession().removeAttribute("anonimo");
            Faces.getSession().removeAttribute("opcao");
            Faces.getSession().removeAttribute("telefone");
            this.listProtocolosAtendimento = null;
            this.listHistoricoProtocolosDTO = null;
            this.listHistoricoAtividades = null;
            this.listHistoricoAtividadesDto = null;

            logarRamal();
            PrimeFaces.current().executeScript("location.reload();");
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
            if (this.anonimo)
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

        historicoAtendimento.setDataFimAtendimento(this.atendimento.getDataFimAtendimento());

        historicoAtendimento.setDataAberturaDemanda(new Date());
        historicoAtendimento.setAtendimentoAnonimo(this.atendimento.getAtendimentoAnonimo());


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

            //     System.out.println("Salvando historico....");
            inserir(historicoAtendimento);
            //  System.out.println(historicoAtendimento);


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
        String opcaoUra = params.get("opcao");
        this.numeroCLiente = params.get("numero");
        this.opcao = (opcaoUra != null) ? retornarOpcaoUra(opcaoUra) : "Indefinido";
        System.out.printf("Iniciando Atendimento: CPF=%s, Audio=%s, Numero=%s, OpcaoRaw=%s, OpcaoDesc=%s%n",
                cpf, audio, this.numeroCLiente, opcaoUra, this.opcao);

        anonimo = StringUtils.isBlank(cpf);

        if (StringUtils.isNotBlank(cpf)) {

            inicializarAtendimento(cpf);
            Faces.getSession().setAttribute("cpf_atn", cpf);
            //   System.out.println("CLIENTE: " + this.cpf);

        } else {

            inicializarAtendimentoAnonimo();

        }

        if (StringUtils.isNotBlank(audio) && this.usuario.getPontoAtendimento() != null) {

            String audioId = audio.replaceAll(".WAV", "").replaceAll(".wav", "");

            this.atendimentoAudiosService.salvarAtendimentoAudio(audioId, audioId, this.atendimento.getId(),
                    this.usuario.getPontoAtendimento().getPabx(), this.usuario.getPontoAtendimento().getRamal(),
                    new Date(), "ura", this.numeroCLiente);

        }

        this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.INICIOU_ATENDIMENTO,
                "Atendimento iniciado: Protocolo principal: "
                        + protocoloPai + (this.anonimo ? " |Cliente não informou CPF." : ""));

        Faces.getSession().setAttribute("opcao", opcao);
        Faces.getSession().setAttribute("numeroCLiente", this.numeroCLiente);
    }

    // Sugestão de método auxiliar caso você não tenha
    private void inicializarAtendimentoAnonimo() {

        try {

            criarNovoAtendimento(null);
            this.atendimento.setAtendimentoAnonimo(Boolean.TRUE);
            this.atendimento.setTipoClienteEnum(TipoClienteEnum.CLIENTE_ANONIMO);
            this.cliente = new Cliente();
            this.cliente.setNome(TipoClienteEnum.CLIENTE_ANONIMO.getDescricao());

            if (Utils.isTelefoneValido(this.numeroCLiente)) {

                Short ddd = Short.parseShort(this.numeroCLiente.substring(0, 2));
                String numero = this.numeroCLiente.substring(2);
                Telefone telefone = new Telefone();
                telefone.setNumero(numero);
                telefone.setDdd(ddd);
                this.cliente.adicionarTelefone(telefone);
            }

            inserir(this.atendimento);
            gerarHistoricoAtendimentoAnonimo(this.atendimento);
            inicializarAtendimento(null);

            Faces.getSession().setAttribute("anonimo", Boolean.TRUE);
            Faces.getSession().setAttribute("atendimento_iniciado", this.atendimento.getId());
            Faces.getSession().setAttribute("protocolo_pai", this.atendimento.getProtocoloPai());

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    private void gerarHistoricoAtendimentoAnonimo(Atendimento atendimento) {

        if (atendimento != null && atendimento.getId() != null) {
            try {
                HistoricoAtendimento his = new HistoricoAtendimento();
                his.setAtendimento(atendimento);
                his.setAtendimentoAnonimo(Boolean.TRUE);
                his.setObservacao("Cliente não informou CPF.");
                his.setDataInicioAtendimento(new Date());
                his.setDataFimAtendimento(new Date());
                his.setDataFimAtendimento(new Date());
                his.setDataCadastro(new Date());
                his.setProtocolo(atendimento.getProtocolo());
                inserir(his);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void gerarHistoricoAtendimentoFinal(Atendimento atendimento) {

        if (atendimento != null && atendimento.getId() != null) {
            try {
                HistoricoAtendimento his = new HistoricoAtendimento();

                his.setAtendimentoAnonimo(Boolean.TRUE);
                his.setObservacao("Atendimento finalizado, protocolo principal: " + atendimento.getProtocoloPai());
                his.setDataInicioAtendimento(new Date());
                his.setDataFimAtendimento(new Date());
                his.setDataFimAtendimento(new Date());
                his.setDataCadastro(new Date());
                his.setProtocolo(atendimento.getProtocoloPai());
                if (atendimento.getAtendimentoPai() != null && atendimento.getAtendimentoPai())
                    his.setAtendimento(atendimento);
                else
                    his.setAtendimento(this.serviceAtendimento.pesquisarAtendimentoSacPorPrococolo(atendimento.getProtocoloPai()));

                inserir(his);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private String retornarOpcaoUra(String opcao) {

        if (StringUtils.isBlank(opcao)) {
            return "Nenhuma opção informada";
        }

        switch (opcao) {
            case "1":
                return "Opção 1 – Consulta de limite";
            case "2":
                return "Opção 2 – Perda ou Roubo de cartão";
            case "3":
                return "Opção 3 – Segunda via da fatura";
            case "4":
                return "Opção 4 – Desbloqueio de cartão";
            case "5":
                return "Opção 5 – Redefinição de senha";
            case "6":
                return "Opção 6 – Cancelamento de cartão";
            case "7":
                return "Opção 7 – Rastreio do cartão";
            case "9":
                return "Opção 9 – Falar com atendente";
            default:
                return "Nenhuma opção informada";
        }
    }


    public void inicializarCliente(String cpf) throws ProativaException {

        if (StringUtils.isNotBlank(cpf)) {

            this.cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorCpf(cpf, true);

            if (this.cliente == null) {

                criarNovoCliente(cpf);

            } else {

                this.cliente.setTipoClienteEnum(TipoClienteEnum.CLIENTE);
            }

            adicionarTelefone();
        }
    }

    public void inicializarAtendimento(String cpf) {

        try {

            if (StringUtils.isNotBlank(cpf) || this.anonimo) {


                if (this.atendimento == null || this.atendimento.getId() == null) {

                    inicializarCliente(cpf);

                    criarNovoAtendimento(this.cliente);
                    inserir(this.atendimento);

                    Faces.getSession().setAttribute("atendimento_iniciado", this.atendimento.getId());
                    Faces.getSession().setAttribute("protocolo_pai", this.atendimento.getProtocoloPai());
                    this.protocoloPai = this.atendimento.getProtocoloPai();


                } else {


                    this.campanha = this.atendimento.getCampanha();
                    if (!this.anonimo && this.atendimento.getCliente() != null) {

                        this.cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorId(this.atendimento.getCliente().getId(), true);
                        this.cliente.setProtocolo(this.protocoloPai);
                        this.cliente.setUltimoProtocolo(this.atendimento.getProtocolo());
                        adicionarTelefone();

                    } else {

                        this.cliente = new Cliente();
                        this.cliente.setTipoClienteEnum(TipoClienteEnum.CLIENTE_ANONIMO);
                        this.cliente.setNome(TipoClienteEnum.CLIENTE_ANONIMO.getDescricao());
                    }

                    this.listHistoricoAtividades = this.serviceHistoricoAtividade.pesquisarHistoricoAtividadePorProtocolo(this.atendimento.getProtocoloPai());

                    if (this.atendimento.getAtendimentoPai() != null && this.atendimento.getAtendimentoPai()) {

                        this.atendimentoPai = atendimento;

                    } else {
                        this.atendimentoPai = this.serviceAtendimento.pesquisarAtendimentoSacPorPrococolo(this.protocoloPai);
                    }

                }

                this.listProtocolosAtendimento = this.cliente.getListAtendimentos();

                inicializarListas();
                inicializarHistoricoAtividade();
                carregarHistoricosAtendimentosMocado();
                verificarReincidencia();

                verificarSeClienteEhReincidente();


            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inseperado");
        }

    }

    private void verificarSeClienteEhReincidente() {

        if (this.cliente != null && this.cliente.getId() != null && StringUtils.isNotBlank(protocoloPai)) {

            this.quantidadeAtendimento = this.serviceAtendimento.buscarQuantidadeAtendimentosDoClienteUltimos7Dias(this.cliente.getId(), protocoloPai);
            //   boolean isReincidente = this.serviceAtendimento.verificarSeClienteEhReincidente(this.cliente.getId(), protocoloPai);

            if (quantidadeAtendimento != null && quantidadeAtendimento > 0) {

                this.atendimento.setReincidencia(true);

                this.atendimento.setFcr(false);

                Messages.addGlobalWarn("Atenção: Cliente reincidente (Contato recente identificado).");
            } else {

                this.atendimento.setReincidencia(false);
                this.atendimento.setFcr(true);
            }
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
            inserir(this.cliente);

        }

    }

    private void adicionarTelefone() {
        try {

            // Validações iniciais
            if (StringUtils.isBlank(this.numeroCLiente) || !Utils.isTelefoneValido(this.numeroCLiente) || this.cliente == null) {
                return; // Sai se inválido
            }

            // Prepara os dados
            String apenasNumeros = this.numeroCLiente.replaceAll("\\D", "");
            Short ddd = Short.valueOf(apenasNumeros.substring(0, 2));
            String numero = apenasNumeros.substring(2);
            Telefone telefone = new Telefone(ddd, numero);

            // Verifica se o telefone já existe na lista em memória para não duplicar
            boolean telefoneJaExiste = false;
            if (!CollectionUtils.isEmpty(this.cliente.getListTelefones())) {
                telefoneJaExiste = this.cliente.getListTelefones().stream()
                        .anyMatch(t -> t.getDdd().equals(ddd) && t.getNumero().equals(numero));
            }

            if (!telefoneJaExiste) {
                // 1. Adiciona na lista em memória (para a tela atualizar e para o Cascade do JPA funcionar)
                this.cliente.adicionarTelefone(telefone);

                // 2. Só tenta inserir direto no banco SE o cliente já existir (ID != null)
                if (this.cliente.getId() != null) {
                    // System.out.println("Cliente já existe, inserindo telefone direto no banco...");

                    // NOTA: Certifique-se que inserirTelefoneCliente recebe (Long, Short, String, Long)
                    this.telefoneService.inserirTelefoneCliente(
                            this.cliente.getId(),
                            ddd,
                            numero,
                            this.usuario.getId()
                    );
                }
                // Se o ID for null, não chamamos o service. O telefone será salvo
                // automaticamente quando o usuário clicar em "Salvar" no formulário do Cliente.
            }

        } catch (Exception e) {
            // Log de erro melhorado para facilitar o debug
            System.out.println("Erro ao inserir telefone. Cliente ID: "
                    + (this.cliente != null ? this.cliente.getId() : "null")
                    + ". Erro: " + e.getMessage());
            e.printStackTrace(); // Importante para ver a linha exata no log do servidor
        }
    }

    private void criarNovoAtendimento(Cliente cliente) {

        iniciarObjetoAtendimento();
        String protocolo = gerarProtocolo();
        //   cliente.setProtocolo(protocolo);F
        //   cliente.setUltimoProtocolo(protocolo);
        this.protocoloPai = protocolo;
        this.atendimento.setProtocolo(protocolo);
        this.atendimento.setProtocoloPai(protocolo);
        this.atendimento.setAtendimentoPai(Boolean.TRUE);

        this.atendimentoPai = atendimento;

        if (this.campanha == null) {

            System.out.println("Campanha não encontrada;");

        } else {
            //    System.out.println(this.campanha.getDescricao());
            this.atendimento.setCampanha(this.campanha);
        }

    }

    private void iniciarObjetoAtendimento() {

        this.atendimento = new Atendimento();
        this.atendimento.setDataCadastro(new Date());

        if (!this.anonimo) {
            this.atendimento.setCpf(cliente.getCpf());
            this.atendimento.setCliente(cliente);
            this.atendimento.setTipoClienteEnum(cliente.getTipoClienteEnum());
        }

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
        String formatoData = "";

        if (this.cliente == null) {

            return DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + Utils.getNumeroRandomico();

        } else {

            formatoData = DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmm").getDataTexto();
            return formatoData + this.cliente.getId() + Utils.getNumeroRandomico();
        }


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
            this.listaDevolutivasPendentes = new ArrayList<>();

            this.listProtocolosAtendimento.sort(
                    Comparator.comparing(Atendimento::getDataAlteracao).reversed()
            );


            for (Atendimento atn : this.listProtocolosAtendimento) {

                // Pula o atendimento atual da lista (não mostra ele mesmo no histórico)
                if (!atn.getProtocolo().equals(this.atendimento.getProtocolo())) {


                    ProtocoloDTO protocoloDTO = new ProtocoloDTO();
                    protocoloDTO.setIdAtendimento(atn.getId());
                    protocoloDTO.setNumeroProtocolo(atn.getProtocolo());

                    if (atn.getUsuarioAlteracao() != null)
                        protocoloDTO.setUsuarioN1(atn.getUsuarioAlteracao().getNome());


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
                    StatusProtocoloEnum statusCalculado;

                    // CENÁRIO A: É uma demanda de Backoffice (N2)?
                    if (Boolean.TRUE.equals(atn.getEnviarN2())) {


                        protocoloDTO.setDataAbertura(atn.getDataAberturaDemanda());
                        protocoloDTO.setDataPrazo(atn.getPrazoPrazoDemanda());

                        if (Boolean.TRUE.equals(atn.getDemandaEncerrada())) {
                            statusCalculado = StatusProtocoloEnum.CONCLUIDO;

                        } else {

                            if (atn.getStatus().getAcao().equals(AcaoStatusAtendimentoEnum.DEVOLVER)) {

                                statusCalculado = StatusProtocoloEnum.DEVOLVIDO_N1;
                                this.precisaResponder = true;
                                this.listaDevolutivasPendentes.add(atn);

                            } else {
                                statusCalculado = StatusProtocoloEnum.EM_ANDAMENTO;
                            }


                        }

                    } else {

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

            //  System.out.println("POPULANDO LIST HISTORICO");

            for (HistoricoAtividade h : this.listHistoricoAtividades) {

                //  System.out.println(h.getDetalhes() + "- " + h.getDescricao());
                inserirAtividadesAtendimentos(h.getTipoStatusAtividade(), h.getDetalhes(), h.getDescricao(), h.getUsuario(), h.getTipoIcone(), h.getData(), h.getProtocolo(), true);
                if (h.getTipoStatusAtividade().isAtendimentoFinalizador())
                    this.temAtendimentoFinalizado = true;
            }

        }

    }

    private void criarHistoricoAtividadeInicial() {


        String canal = (this.campanha == null || StringUtils.isBlank(this.campanha.getDescricao())) ? "Canal não informado" : this.campanha.getDescricao();
        String descricao;

        if (this.cliente == null || this.cliente.getId() == null) {
            descricao = "Cliente conectado via " + canal + "(CPF não informado)";
        } else {
            descricao = "Cliente conectado via " + canal;
        }

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
                if (cliente != null && cliente.getId() != null)
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

            String builder = "ATENDIMENTO FINALIZADO: " + this.atendimento.getNome() + " | Protocolo: " + this.atendimento.getProtocolo();

            this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.FINALIZOU_ATENDIMENTO, builder);

            Date dataPausa = new Date();

            encerrarAtendimento();

            criarControlePausa(pausa, dataPausa);

            if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.PST_PHONE)) {

                //  System.out.println("Entrando em pausa.");
                Integer codPausa = pausa.getCodigoInterno() != null ? pausa.getCodigoInterno() : 1;
                String retorno = PabxUtil.entarEmPausaPstPhone(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getRamal(), this.pausa.getDescricao(), codPausa);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(retorno);
                String status = node.get("status").asText();
                String mensagem = node.get("mensagem").asText();
                //   System.out.println("Status: " + status + " | message: " + mensagem);

            }

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
        //   System.out.println("ABA: " + aba);
        this.abaAtiva = aba;
    }

    public void onAssociarRamal() {

        // System.out.println("onAssociarRamal");
        try {


            if (this.usuario.getPontoAtendimento() != null) {

                //   System.out.println(this.usuario.getPontoAtendimento().getRamal());
                this.serviceUsuario.atualizarPontoAtendimento(this.usuario.getPontoAtendimento().getId(), this.usuario.getId());
                logarRamal();


            }
        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void visualizarAtendimentoProtocolo(Long id) {
        this.atendimentoVisualizar = this.serviceAtendimento.pesquisarAtendimentoSacPorCodigo(id);
    }

    public void editarDadosCadastrais() {

        this.endereco = new Endereco();

        if (this.anonimo && this.cliente.getId() == null)
            this.cliente.setNome("");
    }

    public void onBuscarEndereco() {

        try {

            //  System.out.println("Buscando endereco CEP: " + this.endereco.getCep());

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

        try {

            if (this.anonimo && StringUtils.isBlank(this.cliente.getCpf())) {
                throw new ProativaException("Informe o CPF do cliente para prosseguir com a atualização");
            }

            String cpfLimpo = this.cliente.getCpf().replaceAll("\\D", "");

            if (!Utils.validaCPF(cpfLimpo)) {
                throw new ProativaException("O CPF informado é inválido");
            }
            this.cliente.setCpf(cpfLimpo);

            //buscarPorCpf(false);

            if (this.anonimo && this.cliente.getId() == null) {

                Cliente cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorCpf(cpfLimpo, true);

                if (cliente != null && cliente.getId() != null) {

                    this.cliente.setListAtendimentos(cliente.getListAtendimentos());
                    this.listProtocolosAtendimento = this.cliente.getListAtendimentos();
                    carregarHistoricosAtendimentosMocado();

                } else {

                    this.cliente.setUsuarioCadastro(retornarUsuarioSessao());
                    this.cliente.setEmpresa(retornarEmpresaUsuarioSessao());
                    this.cliente.setTipoClienteEnum(TipoClienteEnum.CLIENTE_NOVO);
                    this.endereco = new Endereco();
                    inserir(this.cliente);
                }

                this.atendimento.setCliente(this.cliente);
                this.atendimento.setNome(this.cliente.getNome());
                this.atendimento.setCpf(this.cliente.getCpf());
                this.atendimento.setAtendimentoAnonimo(Boolean.TRUE);
                adicionarTelefone();
                this.anonimo = false;
                Faces.getSession().removeAttribute("anonimo");
                Faces.getSession().setAttribute("cpf_atn", this.cliente.getCpf());
                Faces.getSession().setAttribute("atendimento_iniciado", atendimento.getId());
            }


            inserirAtividadesAtendimentos(
                    TipoStatusAtividadesEnum.ATUALIZACAO_DADOS_CADASTRAIS,
                    "Dados atualizados",
                    "Atualização dos dados cadastrais",
                    this.usuario,
                    "pi pi-user",
                    new Date(), this.protocoloPai
            );

            System.out.println("CRIANDO CLIENTE:::: " + this.cliente.getNome());

            //alterar(this.cliente);
            this.clienteService.atualizarNomeCliente(this.cliente.getNome(), cliente.getNomeMae(), cliente.getNomePai(), cliente.getDataNascimento(), this.cliente.getId());
            //ATUALIZAR TIPO CLIENTE....
            this.serviceAtendimento.atualizarAtendimentoAnonimo(false, this.cliente.getId(), this.atendimento.getId());
            this.atendimento.setAtendimentoAnonimo(Boolean.FALSE);


            if (StringUtils.isNotBlank(this.endereco.getCidade()) && StringUtils.isNotBlank(this.endereco.getLogradouro()) && StringUtils.isNotBlank(this.endereco.getBairro())) {

                this.endereco.setId(null);
                this.endereco.setCliente(this.cliente);
                inserir(this.endereco);
                this.cliente.adicionarEndereco(this.endereco);
                this.endereco = new Endereco();

            }

            Messages.addGlobalInfo("Dados cadastrais atualizados com sucesso!");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }


    }

    public void buscarPorCpf() {
        buscarPorCpf(true);
    }

    public void buscarPorCpf(boolean exibirError) {

        try {

            if (StringUtils.isNotBlank(this.cliente.getCpf())) {

                String cpf = this.cliente.getCpf().replaceAll("\\D", "");
                Cliente cliente = this.clienteService.pesquisarClienteComAtendimentosSacPorCpf(cpf, true);

                if (cliente != null) {

                    this.cliente = cliente;
                    Faces.getSession().removeAttribute("anonimo");
                    this.anonimo = false;
                    this.atendimento.setCliente(cliente);
                    this.atendimento.setTipoClienteEnum(cliente.getTipoClienteEnum());
                    this.atendimento.setDataNascimento(cliente.getDataNascimento());
                    this.atendimento.setNome(cliente.getNome());
                    this.atendimento.setCpf(cliente.getCpf());
                    this.atendimento.setAtendimentoAnonimo(Boolean.FALSE);
                    alterar(this.atendimento);
                    this.listProtocolosAtendimento = this.cliente.getListAtendimentos();
                    carregarHistoricosAtendimentosMocado();
                    // inicializarListas();
                    Faces.getSession().removeAttribute("anonimo");
                    Faces.getSession().setAttribute("cpf_atn", cliente.getCpf());
                    Faces.getSession().setAttribute("atendimento_iniciado", atendimento.getId());
                    this.anonimo = false;

                } else {

                    Messages.addGlobalWarn("Cliente não localizado.");
                }

            } else {
                Messages.addGlobalError("Informe o CPF do cliente");
            }
        } catch (ProativaException e) {
            if (exibirError)
                Messages.addGlobalError(e.getMessage());
        }


    }

    public void tratarCliqueDevolutiva() {

        if (CollectionUtils.isNotEmpty(this.listaDevolutivasPendentes)) {

            if (listaDevolutivasPendentes.size() == 1) {

                this.atendimentoVisualizar = listaDevolutivasPendentes.get(0);
                this.atendimentoVisualizar.setObservacao("");
                iniciarObjetoHistoricoAtendimentoVisualizar(this.atendimentoVisualizar);
                PrimeFaces.current().ajax().update("formResponderDevolutiva"); // Atualiza o modal de resposta
                PrimeFaces.current().executeScript("PF('dlgResponderDevolutiva').show();");

            } else {

                PrimeFaces.current().ajax().update("formListaDevolutiva");
                PrimeFaces.current().executeScript("PF('dlgListaDevolutivas').show();");
            }
        }
    }

    private void iniciarObjetoHistoricoAtendimentoVisualizar(Atendimento atendimento) {

        this.historicoAtendimentoVisualizar = new HistoricoAtendimento();
        this.historicoAtendimentoVisualizar.setDataInicioAtendimento(new Date());
        this.historicoAtendimentoVisualizar.setAtendimento(this.atendimentoVisualizar);
        this.historicoAtendimentoVisualizar.setProtocolo(this.atendimento.getProtocolo());
        this.historicoAtendimentoVisualizar.setEnviarN2(this.atendimentoVisualizar.getEnviarN2());
        this.historicoAtendimentoVisualizar.setMotivo(this.atendimentoVisualizar.getMotivo());
        this.historicoAtendimentoVisualizar.setSubMotivo(this.atendimentoVisualizar.getSubMotivo());

        this.historicoAtendimentoVisualizar.setDataCadastro(new Date());
        this.historicoAtendimentoVisualizar.setUsuario(this.usuario);
    }

    public void selecionarDevolutivaParaResponder(Atendimento dto) {

        this.atendimentoVisualizar = dto;
        iniciarObjetoHistoricoAtendimentoVisualizar(this.atendimentoVisualizar);
        this.atendimentoVisualizar.setObservacao("");

        PrimeFaces.current().executeScript("PF('dlgListaDevolutivas').hide(); PF('dlgResponderDevolutiva').show();");
    }

    public void salvarRespostaDevolucao() {

        try {

            if (StringUtils.isBlank(this.atendimentoVisualizar.getObservacao()))
                throw new ProativaException("É necessário informar uma resposta para o N2.");


            this.historicoAtendimentoVisualizar.setDataFimAtendimento(new Date());
            StatusAtendimento status = this.serviceStatusAtendimento.pesquisarStatusAtendimentoPorAcao(AcaoStatusAtendimentoEnum.RETORNO_N2, retornarEmpresaUsuarioSessao().getId());

            if (status == null) {

                status = new StatusAtendimento();
                status.setDescricao("Retorno N2");
                status.setDataCadastro(new Date());
                status.setDataAlteracao(new Date());
                status.setUsuarioCadastro(retornarUsuarioSessao());
                status.setAcao(AcaoStatusAtendimentoEnum.RETORNO_N2);
                status.setAtivo(TipoAcessoEnum.ATIVO);
                inserir(status, true);

            }

            this.historicoAtendimentoVisualizar.setDataFimAtendimento(new Date());
            this.historicoAtendimentoVisualizar.setObservacao(this.atendimentoVisualizar.getObservacao());
            this.historicoAtendimentoVisualizar.setObservacaoAdicional("Resposta de devolução enviada ao N2");
            this.atendimentoVisualizar.setStatus(status);

            Messages.addGlobalInfo("Resposta enviada com sucesso! O protocolo retornou para análise do N2.");
            PrimeFaces.current().executeScript("PF('dlgResponderDevolutiva').hide();");
            PrimeFaces.current().ajax().update("formConteudo");

            this.historicoAtendimentoVisualizar.setStatusAtendimento(status);
            inserir(this.historicoAtendimentoVisualizar, true);
            this.atendimentoVisualizar.setStatus(status);
            this.serviceAtendimento.atualizarAtendimentoSac(this.atendimentoVisualizar.getId(), status.getId(), this.atendimentoVisualizar.getObservacao(), new Date(), this.usuario.getId());

            inicializarAtendimento(this.atendimentoVisualizar.getCpf());

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inesperado.");
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

    public void verificarReincidenciaEAtualizarFCR(Atendimento atendimentoAtual) {

        if (atendimentoAtual != null && atendimentoAtual.getId() != null && atendimentoAtual.getCliente() != null && atendimentoAtual.getCliente().getId() != null) {

            int diasJanelaReincidencia = 7;

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, -diasJanelaReincidencia);
            Date dataLimite = cal.getTime();


            Atendimento atendimentoAnterior = serviceAtendimento.buscarUltimoAtendimentoDoCliente(
                    atendimentoAtual.getCliente().getId(),
                    atendimentoAtual.getMotivo().getId(),
                    atendimentoAtual.getSubMotivo().getId(),
                    dataLimite,
                    atendimentoAtual.getId()
            );

            if (atendimentoAnterior != null) {

                atendimentoAtual.setReincidencia(true);
                atendimentoAtual.setFcr(true);

                if (Boolean.TRUE.equals(atendimentoAnterior.getFcr())) {

                    atendimentoAnterior.setFcr(false);
                    this.serviceAtendimento.alterarFrc(atendimentoAnterior.getId(), Boolean.FALSE);
                    System.out.println("FCR do atendimento " + atendimentoAnterior.getId() + " foi invalidado por reincidência.");
                }


            } else {

                atendimentoAtual.setReincidencia(false);
                atendimentoAtual.setFcr(true);
            }
        }
    }

    public void verificarReincidencia() {

        if (this.atendimento.getCliente() != null
                && this.atendimento.getMotivo() != null
                && this.atendimento.getSubMotivo() != null) {

            this.atendimentoAnterior = serviceAtendimento.buscarUltimaInteracao(
                    this.atendimento.getCliente().getId(),
                    this.atendimento.getMotivo().getId(),
                    this.atendimento.getSubMotivo().getId(),
                    this.atendimento.getId()
            );

            // Se achou (atendimentoAnterior != null), o JSF vai renderizar o painel laranja
        }
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

    public boolean isPrecisaResponder() {
        return precisaResponder;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    private void gerarClienteMocado(String cpf) {

        //  System.out.println("Cliente novo....");
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

    /**
     * Define a cor base do tema (Verde para sucesso, Laranja para atenção)
     */
    public String getFcrTema() {
        // Boolean.TRUE.equals retorna false se for NULL ou FALSE
        return Boolean.TRUE.equals(atendimento.getFcr()) ? "green" : "orange";
    }

    /**
     * Define o ícone: Joinha (Sucesso) ou Alerta (Atenção)
     */
    public String getFcrIcone() {
        return Boolean.TRUE.equals(atendimento.getFcr()) ? "pi-check-circle" : "pi-exclamation-triangle";
    }

    /**
     * Define o texto do Badge: "Elegível" ou "Perdido"
     */
    public String getFcrStatusTexto() {
        //   return Boolean.TRUE.equals(atendimento.getFcr()) ? "Elegível" : "Perdido";
        return Boolean.TRUE.equals(atendimento.getFcr()) ? "SIM" : "NÃO";
    }

    /**
     * Define a descrição do motivo da perda
     */
    public String getFcrMotivoPerda() {


        if (Boolean.TRUE.equals(atendimento.getFcr())) {
            return null;
        }


        if (Boolean.TRUE.equals(atendimento.getReincidencia())) {
            return "Reincidência (Cliente retornou < 7 dias)";
        }

        // Se não é FCR e não é Reincidência, assumimos que foi Derivação ou erro inicial
        return "Motivo: Derivação / Análise";
    }


    public long getInicioAtendimentoMillis() {

        if (this.atendimento != null && this.atendimento.getDataCadastro() != null) {
            return this.atendimento.getDataCadastro().getTime();
        }
        return System.currentTimeMillis();
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

    public String getOpcao() {
        return opcao;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public Atendimento getAtendimentoAnterior() {
        return atendimentoAnterior;
    }

    public List<Atendimento> getListaDevolutivasPendentes() {
        return listaDevolutivasPendentes;
    }
}

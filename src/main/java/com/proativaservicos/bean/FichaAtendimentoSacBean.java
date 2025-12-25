package com.proativaservicos.bean;


import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.dto.HistoricoAtividadesDto;
import com.proativaservicos.model.dto.ProtocoloDTO;
import com.proativaservicos.service.*;
import com.proativaservicos.util.CorreiosUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FichaAtendimentoSacBean extends GenericBean {


    @Inject
    private AtendimentoService serviceAtendimento;

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
    private HistoricoAtividadeService serviceHistoricoAtividade;

    private List<ProtocoloDTO> listHistoricoProtocolosDTO;

    private List<HistoricoAtividadesDto> listHistoricoAtividadesDto;

    private List<PontoAtendimento> listPontoAtendimento;

    private List<Pausa> listPausas;

    private List<Motivo> listMotivo;

    private List<SubMotivo> listSubMotivo;

    private List<Atendimento> listProtocolosAtendimento;
    private List<HistoricoAtividade> listHistoricoAtividades;

    private Usuario usuario;

    private Pausa pausa;

    private Campanha campanha;

    private Atendimento atendimento;

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

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        String cpfTmp = (String) Faces.getSession().getAttribute("cpf_atn");
        Object objAtn = Faces.getSession().getAttribute("atendimento_iniciado");

        if (objAtn != null) {

            System.out.println("Atendimento iniciado F5");
            this.atendimento = (Atendimento) objAtn;
            System.out.println("Atendimento iniciado F6: " + this.atendimento.getProtocolo());
        }

        inicializarAtendimento(cpfTmp);

        this.usuario.setPontoAtendimento(this.servicePontoAtendimento.pesquisarPontoAtendimentosPorUsuario(this.usuario.getId()));


    }

    public void inicializarListas() {

        this.listMotivo = this.motivoService.pesquisarMotivosPorEmpresa(retornarEmpresaUsuarioSessao().getId());
    }

    public void iniciarAtendimento() {

        System.out.println("Iniciando atendimento...");
        System.out.println(atendimento.getMotivo().getDescricao());
        System.out.println(atendimento.getSubMotivo().getDescricao());
        this.dataInicioClassificacaoHistorico = new Date();
        ///SALVAR
        this.atendimentoIniciado = true;
        String detalhes = retornarDetalhesAtendimento();
        String descicao = (StringUtils.isBlank(this.atendimento.getObservacaoAdicional()) ? "" : " | " + this.atendimento.getObservacaoAdicional());
        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO, detalhes, descicao, this.usuario, "pi pi-user");
        Messages.addGlobalInfo("Sua classificação registrada com sucesso!");
        this.historicoAtendimento = new HistoricoAtendimento();


    }


    public void salvarSolicitacaoAtendimento() {


        System.out.println("Salnvando atendimento");


        System.out.println(this.atendimento.getNome() + " - " + atendimento.getCpf());
        System.out.println("Motivo: " + this.atendimento.getMotivo().getDescricao());
        System.out.println("Sub Motico: " + this.atendimento.getSubMotivo().getDescricao());
        System.out.println("AObs: " + this.atendimento.getObservacao());
        System.out.println("Obs Adicioal: " + this.atendimento.getObservacaoAdicional());
        System.out.println("ENVIAR N2: " + this.atendimento.getEnviarN2());

        System.out.println("SALVAR HISTORICO DE ATENDIMENTO.......");
        PrimeFaces.current().executeScript("PF('dlgFinalizar').hide();");
        Messages.addGlobalInfo("Histórido de atendimento salvo com sucesso");

        gerarHistoricoAtendimento();

        String descricao = StringUtils.defaultString(this.atendimento.getObservacao(), "Sem observação");
        inserirAtividadesAtendimentos(determinarStatusAtividade(), retornarDetalhesAtendimento(), descricao, this.usuario, "pi pi-user");

        resetarAtendimento();
        temAtendimentoFinalizado = true;


    }

    private TipoStatusAtividadesEnum determinarStatusAtividade() {

        Boolean enviarN2 = this.atendimento.getEnviarN2();
        System.out.println(enviarN2 + " ENVIAR N2");

        return (enviarN2 != null && enviarN2)
                ? TipoStatusAtividadesEnum.SOLICITACAO_ABERTA
                : TipoStatusAtividadesEnum.ATENDIMENTO_FINALIZADO;
    }


    private void validarEncerrarAtendimento() throws ProativaException {

        System.out.println("ATENDIMENTO INICIADO: " + this.atendimentoIniciado);

        if (this.atendimentoIniciado)
            throw new ProativaException("Não é possível prosseguir: há um atendimento iniciado que deve ser encerrado.");

        if (!temAtendimentoFinalizado)
            throw new ProativaException("Não é possível prosseguir: finalize pelo menos um atendimento antes de continuar.");

        if (StringUtils.isBlank(this.atendimento.getNome()))
            throw new ProativaException("É necessário informar o nome do cliente para continuar.");


    }

    public void derivarAtendimentoN2() {

        System.out.println("Derivar: " + this.atendimento.getObservacaoN2());
        this.atendimento.setEnviarN2(Boolean.TRUE);
        gerarHistoricoAtendimento();

        String descricao = StringUtils.defaultString(this.atendimento.getObservacaoN2(), "Sem observação");
        inserirAtividadesAtendimentos(determinarStatusAtividade(), retornarDetalhesAtendimento(), descricao, this.usuario, "pi pi-user");

        resetarAtendimento();
        temAtendimentoFinalizado = true;


        PrimeFaces.current().executeScript("PF('dlgDerivarN2').hide();");
    }

    public void encerrarAtendimento() {

        try {

            ///SALVAR ATENDIMENTO???? GERAR LOG....
            validarEncerrarAtendimento();
            System.out.println("Encerrando atendimento");

            resetarAtendimento();
            this.atendimento = null;
            Faces.getSession().removeAttribute("cpf_atn");


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Não foi possível concluir a operação.");

        }
    }


    private void gerarHistoricoAtendimento() {

        if (historicoAtendimento == null)
            historicoAtendimento = new HistoricoAtendimento();

        historicoAtendimento.setId(null);
        historicoAtendimento.setAtendimento(this.atendimento);
        historicoAtendimento.setMotivo(this.atendimento.getMotivo());
        historicoAtendimento.setSubMotivomotivo(this.atendimento.getSubMotivo());
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

            historicoAtendimento.setDataFechamentoDemanda(new Date());
            historicoAtendimento.setDataAberturaDemanda(new Date());
            historicoAtendimento.setDemandaEncerrada(Boolean.TRUE);
            historicoAtendimento.setAtendimentoFinalizado(Boolean.TRUE);

        }
        //INSERIR HISTORICO
        System.out.println(historicoAtendimento);

    }

    public void entrarEmPausaAtendimento() {
        System.out.println("Pausando atendimento");
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
        this.historicoAtendimento = null;

    }


    public void onCliente() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String cpf = params.get("cpf");
        System.out.println("O cliente foi encontrado: " + cpf);


        if (StringUtils.isNotBlank(cpf)) {

            inicializarAtendimento(cpf);

            Faces.getSession().setAttribute("cpf_atn", cpf);

            System.out.println("CLIENTE: " + this.cpf);
        }
    }

    public void inicializarAtendimento(String cpf) {

        try {


            if (StringUtils.isNotBlank(cpf)) {

                if (this.atendimento == null || this.atendimento.getId() == null)
                    this.atendimento = (Atendimento) this.serviceAtendimento.pesquisarAtendimentoPorCpf(cpf, retornarEmpresaUsuarioSessao().getId(), null);

                if (atendimento == null) {

                    //     gerarClienteMocado(cpf);
                    inicializarListas();
                    criarNovoAtendimento(cpf);
                    carregarHistoricosAtendimentos();
                    //  carregarAtividadesAtendimentosMocado();


                    System.out.println("Inserindo Cliente...");
                    inserir(this.atendimento);
                    inicializarHistoricoAtividade();
                    Faces.getSession().setAttribute("atendimento_iniciado", this.atendimento);


                } else {

                    System.out.println("Existe atn");
                    this.campanha = this.atendimento.getCampanha();
                    this.listProtocolosAtendimento = this.serviceAtendimento.pesquisarAtendimentosSacPorCpf(cpf);
                    this.listHistoricoAtividades = this.serviceHistoricoAtividade.pesquisarHistoricoAtividadePorId(this.atendimento.getId());
                    inicializarListas();
                    inicializarHistoricoAtividade();
                    carregarHistoricosAtendimentos();


                }

            }
        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inseperado");
        }

    }

    private void criarNovoAtendimento(String cpf) {

        this.atendimento = new Atendimento();
        this.atendimento.setDataCadastro(new Date());
        this.atendimento.setCpf(cpf);
        this.atendimento.setTipoClienteEnum(TipoClienteEnum.CLIENTE_NOVO);
        this.endereco = new Endereco();
        this.campanha = this.campanhaService.pesquisarCampanhaPorTipo(retornarEmpresaUsuarioSessao().getId(), TipoCampanhaEnum.SAC);
        this.atendimento.setProtocolo(gerarProtocolo());

        if (this.campanha == null) {
            System.out.println("Campanha não encontrada;");
        } else {
            System.out.println(this.campanha.getDescricao());
            this.atendimento.setCampanha(this.campanha);
        }

    }

    private String gerarProtocolo() {

        if (StringUtils.isNotBlank(this.atendimento.getProtocolo()))
            return this.atendimento.getProtocolo();

        String formatoData = DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmm").getDataTexto();
        SecureRandom random = new SecureRandom();

        int numero = 10000000 + random.nextInt(90000000);

        return formatoData + numero;

    }

    private void carregarHistoricosAtendimentos() {

        ///PESQUISAR NO BAMCP;;;
        //MOCADO...

        if (CollectionUtils.isEmpty(this.listProtocolosAtendimento)) {

            this.listHistoricoProtocolosDTO = new ArrayList<>();

            this.listHistoricoProtocolosDTO.add(new ProtocoloDTO(
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

        } else {

            this.listHistoricoProtocolosDTO = new ArrayList<>();

            for (Atendimento atnAux : this.listProtocolosAtendimento) {

                ProtocoloDTO protocoloDTO = new ProtocoloDTO();
                protocoloDTO.setNumeroProtocolo(atnAux.getProtocolo());
                protocoloDTO.setTipo(atnAux.getMotivo().getDescricao());
                protocoloDTO.setDescricao(atnAux.getSubMotivo().getDescricao());

                protocoloDTO.setDataAbertura(atnAux.getDataAberturaDemanda());
                protocoloDTO.setDataPrazo(atnAux.getPrazoPrazoDemanda());
                listHistoricoProtocolosDTO.add(protocoloDTO);

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
                inserirAtividadesAtendimentos(h.getTipoStatusAtividade(), h.getDetalhes(), h.getDescricao(), h.getUsuario(), h.getTipoIcone(), true);
            }

        }

    }

    private void criarHistoricoAtividadeInicial() {

        System.out.println("Criando inicio Atividade de Atendimento");
        String canal = (this.campanha == null || StringUtils.isBlank(this.campanha.getDescricao())) ? "Canal não informado" : this.campanha.getDescricao();
        String descricao = "Cliente conectado via " + canal;
        String detalhes = "Protocolo gerado automaticamente";

        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.INICIO_ATENDIMENTO, descricao, detalhes, null, "pi-phone", false);
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

    private void inserirAtividadesAtendimentos(TipoStatusAtividadesEnum tipoAtendimento, String detalhes, String descicao, Usuario autor, String icon) {

        inserirAtividadesAtendimentos(tipoAtendimento, detalhes, descicao, autor, icon, false);

    }

    private void inserirAtividadesAtendimentos(TipoStatusAtividadesEnum tipoAtendimento, String detalhes, String descicao, Usuario autor, String icon, boolean naoInserirHistoricoAtividade) {

        if (CollectionUtils.isEmpty(this.listHistoricoAtividadesDto))
            this.listHistoricoAtividadesDto = new ArrayList<>();


        this.listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(tipoAtendimento, descicao, detalhes, new Date(), retornarAutor(autor), icon));

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
                historicoAtividade.setAtendimento(this.atendimento);
                historicoAtividade.setData(new Date());
                historicoAtividade.setTipoIcone(icon);
                inserir(historicoAtividade);
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

        this.atendimento.adicionarEndereco(this.endereco);
        inserirAtividadesAtendimentos(
                TipoStatusAtividadesEnum.ATUALIZACAO_DADOS_CADASTRAIS,
                "Dados atualizados",
                "Atualização dos dados cadastrais",
                this.usuario,
                "pi pi-user"
        );

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
        atendimento = new Atendimento();
        atendimento.setTipoClienteEnum(TipoClienteEnum.CLIENTE_NOVO);


        inicializarListas();
        this.cpf = cpf;

        this.atendimento.setCpf(cpf);
        this.atendimento.setNome("JOSE ANTONIO");
        this.atendimento.setLimite(new BigDecimal("10258.52"));
        this.atendimento.setLimiteDisponivel(new BigDecimal("7443.09"));
        this.atendimento.setValorLiberado(new BigDecimal("3085.66"));
        this.atendimento.setValorMaxOperacao(new BigDecimal("7199.88"));
        this.atendimento.setProtocolo(gerarProtocolo());
        this.atendimento.setClienteVip(Boolean.TRUE);


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
        atendimento.adicionarTelefone(telefone);

        BigDecimal limiteTotal = cartaoCreditoSelecionado.getLimiteTotal();
        BigDecimal limiteDisponivel = cartaoCreditoSelecionado.getLimiteDisponivel();


        BigDecimal usado = limiteTotal.subtract(limiteDisponivel);

        BigDecimal percentualUsado = usado
                .multiply(BigDecimal.valueOf(100))
                .divide(limiteTotal, RoundingMode.HALF_UP);

        System.out.println("Percentual usado: " + percentualUsado + "%");

        this.atendimento.addCartao(cartaoCreditoSelecionado);
        carregarHistoricosAtendimentos();
        carregarAtividadesAtendimentosMocado();
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
}

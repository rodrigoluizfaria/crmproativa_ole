package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.argus.RetornoGetSkillsIten;
import com.proativaservicos.model.argus.SkillsResponse;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.model.trescplus.ResponseCampanha;
import com.proativaservicos.model.trescplus.ResponseListCampanha;
import com.proativaservicos.service.*;
import com.proativaservicos.service.asynchronous.*;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.ListDataModel;
import jakarta.faces.model.SelectItem;
import jakarta.faces.model.SelectItemGroup;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Components;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableBase;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


@Named
@ViewScoped
public class CampanhaBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private PausaService servicePausa;

    @Inject
    private FormaPagamentoService serviceFormaPagamento;

    @Inject
    private FilaService serviceFila;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private ImportacaoArquivoUtil importacaoUtil;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private QuestionarioService serviceQuestionatio;

    @Inject
    private LogImportacaoDiscadorService serviceLogImportacaoDiscadorService;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @Inject
    private ArgusService argusService;

    private List<LogImportacaoDiscador> listLogImportacoesDiscador;

    private Campanha campanha;

    private Campanha campanhaRetrabalhar;

    private Long idCampanhaRetrabalhar;

    private DualListModel<StatusCampanha> dualListStatusAtendimentos;

    private Usuario usuario;

    private Fila fila;


    private UploadedFile file;

    private boolean consultarCartaoBMG;

    private boolean retrabalharFimFila;

    private int indice;

    private List<StatusCampanha> listStatusCampanha;

    private List<?> listCampanha;

    private List<Empresa> listEmpresas;

    private List<Equipe> listEquipes;
    private List<Equipe> listEquipesAssociados;

    private List<StatusAtendimento> listStatusAtendimentos;
    private List<StatusAtendimento> listStatusAtendimentosAssociados;

    private List<FormaPagamento> listFormaPagamento;
    private List<FormaPagamento> listFormaPagamentoAssociados;

    private List<Pausa> listPausa;
    private List<Pausa> listPausaAssociados;

    private List<Produto> listProdutos;
    private List<Produto> listProdutosAssociados;

    private List<Usuario> listUsuarios;
    private List<Usuario> listUsuariosAssociados;

    private List<StatusTelefone> listStatusTelefone;
    private List<StatusTelefone> listStatusTelefoneAssociados;

    private List<Fila> listFilas;
    private List<SelectItem> listaSelectItens;

    private List<Questionario> listFormularios;

    private List<Questionario> listFormulariosAssociados;


    private List<String> listCabecalho;


    private TipoPaginaEnum tipoPagina;

    private DualListModel<Equipe> dualListEquipes;
    private DualListModel<StatusAtendimento> dualListStatusAtendimento;
    private DualListModel<Produto> dualListProduto;
    private DualListModel<FormaPagamento> dualListFormaPagamento;
    private DualListModel<Pausa> dualListPausa;
    private DualListModel<StatusTelefone> dualListStatusTelefone;

    private DualListModel<Questionario> dualListQuestionario;


    private DataTableBase dataTablePersonalizada;
    private ListDataModel<String[]> dataModel;
    private Map<Integer, DadosBaseImportacaoEnum> mapImportacaoIndiceCabecalho;
    private DadosBaseImportacaoEnum dadosDabseImportacao;

    private InputStream inpuStream;

    private Date dataInicio;
    private Date dataFim;

    private String strNomeArquivo;
    private String strNomeCampanha;

    private Long tamanhoArquivo;

    private List<TipoConsultaEnum> listaTipoConsultasDisponiveis;

    private TipoConsultaEnum tipoConsultaEscolhido;

    private List<IntegracaoWs> listConsultaSaque;

    private List<IntegracaoWs> listConsultaSeguroPapCard;

    private List<IntegracaoWs> listConsultaRefin;

    private List<IntegracaoWs> listConsultaBeneficio;

    private List<IntegracaoWs> listConsultaMaster;

    private List<Long> listImportacao;

    @Inject
    private ProcessarCampanhaCsv csv;

    @Inject
    private ImportacaoService serviceImportacao;

    @Inject
    private VirtualDiscadorPoweDial servicePwd;

    @Inject
    private AtendimentoAtivoService serviceAtendimentoAtivo;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private AtendimentoSingletonService serviceAtendimentoSingleton;

    @Inject
    private ConsultaAssincronaSeguro consultaSeguro;

    @EJB
    private ConsultaAssincronaMasterSaqueExecutor consultaMaster;


    @Inject
    private ConsultaAssincronaApiPanSimulacao consultaSimulacaoPan;

    @Inject
    private ConsultaAssincronaSaqueBmg consultaSaque;

    private StreamedContent arquivoExtrator;

    private StreamedContent arquivoMailing3c;

    private byte[] fileByte;

    @Inject
    private DiscadorUtil discadorUtil;

    private List<ProdutoReturn> listProdutosRefin;

    @Inject
    private WsdlRefinBmgUtil refinUtil;

    private Integer codTabelaRefin;

    private int indiceMapDadosBase;

    private StatusTelefone statusTelefonePreditivo;

    private List<com.proativaservicos.model.trescplus.Campanha> campanhas3c;

    private List<RetornoGetSkillsIten> retornoGetSkillsItens;

    private Integer codCampanha3c;

    private String nomeArquivo3c;

    @PostConstruct
    public void init() {


        try {

            inicializarVariaveis(true);
            this.campanha = new Campanha();
            this.campanha.setEmpresa(this.usuario.getEmpresa());
            this.tipoPagina = TipoPaginaEnum.PESQUISA;
            this.listFilas = this.serviceFila.pesquisarFilasPorEmpresa(this.usuario.getEmpresa().getId());
            pesquisar();

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }
    }

    public void atualizarComboImportacaoPersonalizada() {

        Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.mapImportacaoIndiceCabecalho.put(Integer.valueOf(requestParamMap.get("indice")), DadosBaseImportacaoEnum.getBaseImportacaoEnum(requestParamMap.get("tidoEnum")));

    }

    private Column criarColuna(String cabecalho, DadosBaseImportacaoEnum tipoDadosImportacao, int indice) {

        try {

            Column coluna = new Column();
            coluna.setHeaderText(cabecalho);
            coluna.setStyle("width:200px;");
            coluna.setRendered(true);
            coluna.getChildren().add(criarSelectOneMenu(tipoDadosImportacao, indice));
            return coluna;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void criarDataModel() {

        List<String[]> data = new ArrayList<>();
        data.add(new String[0]);
        this.dataModel = new ListDataModel<>(data);
    }

    private void criarSelectItens() {

        this.listaSelectItens = new ArrayList<>();

        SelectItem selectItem = new SelectItem();
        selectItem.setValue(null);
        selectItem.setLabel("Selecione");


        this.listaSelectItens.add(selectItem);

        Map<String, List<SelectItem>> listaSelectItemAgrupados = new TreeMap<>();

        for (DadosBaseImportacaoEnum tipoDadosImportacao : DadosBaseImportacaoEnum.values()) {

            selectItem = new SelectItem();
            selectItem.setValue(tipoDadosImportacao);
            selectItem.setLabel(tipoDadosImportacao.getDescricao());

            if (listaSelectItemAgrupados.containsKey(tipoDadosImportacao.getGrupo())) {

                listaSelectItemAgrupados.get(tipoDadosImportacao.getGrupo()).add(selectItem);

            } else {

                List<SelectItem> selectItens = new ArrayList<>();
                selectItens.add(selectItem);

                listaSelectItemAgrupados.put(tipoDadosImportacao.getGrupo(), selectItens);
            }
        }

        for (String grupo : listaSelectItemAgrupados.keySet()) {

            SelectItemGroup selectItemGroup = new SelectItemGroup(grupo);

            selectItemGroup.setSelectItems((SelectItem[]) (listaSelectItemAgrupados.get(grupo)).toArray((Object[]) new SelectItem[(listaSelectItemAgrupados.get(grupo)).size()]));

            this.listaSelectItens.add(selectItemGroup);

        }
    }

    private SelectOneMenu criarSelectOneMenu(DadosBaseImportacaoEnum tipoDadosImportacao, int indice) {

        SelectOneMenu selectOneMenu = new SelectOneMenu();
        selectOneMenu.setId("selectImportacao" + indice);
        selectOneMenu.setDisabled(false);
        selectOneMenu.setRendered(true);
        selectOneMenu.setRequired(false);
        selectOneMenu.setAutoWidth("true");
        selectOneMenu.setFilter(true);
        selectOneMenu.setFilterMatchMode("contains");
        selectOneMenu.setOnchange("atualizarCombo(" + indice + ");");
        selectOneMenu.setStyle("height:100% !important;width:80%");
        UISelectItems selectItens = new UISelectItems();
        selectItens.setValue(this.listaSelectItens);
        selectOneMenu.getChildren().add(selectItens);
        selectOneMenu.setValue(tipoDadosImportacao);
        selectOneMenu.setValueExpression("value", Components.createValueExpression("#{campanhaBean.dadosDabseImportacao}", DadosBaseImportacaoEnum.class));
        return selectOneMenu;

    }

    private void criarTabela() {

        this.dataTablePersonalizada = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formTable:tableImportacao");
        this.dataTablePersonalizada.getChildren().clear();
        this.dataTablePersonalizada.getColumns().clear();
        this.dataTablePersonalizada.setVar("item");
        // this.dataTablePersonalizada.setValue(this.dataModel);

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {

        this.file = event.getFile();
        this.strNomeArquivo = file.getFileName();
        this.tamanhoArquivo = this.file.getSize();
        this.inpuStream = this.file.getInputStream();
        this.fileByte = IOUtils.toByteArray(this.inpuStream);

    }

    public void preImportar() {

        try {

            if (this.campanha.getIntegrarWs() != null && (this.campanha.getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.BMG) || this.campanha.getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.PAN)) && this.campanha.getConsultaSaque().equals(Boolean.TRUE) && this.campanha.getValorSaque() == null) {

                throw new ProativaException("Valor de Saque Mínimo de ser informado.");
            }

            if (this.campanha.getIntegrarWs() != null && this.campanha.getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.BMG) && this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.BMG_SEGURO) && this.campanha.getConsultaSeguro().equals(Boolean.TRUE) && this.campanha.getValorSaque() == null) {

                throw new ProativaException("Valor da Parcela Mínima de ser informada.");
            }

            if (this.campanha.getIntegrarWs() != null && this.campanha.getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.BMG) && TipoIntegracaoEnum.BMG_GRAVACAO.equals(this.campanha.getIntegrarWs().getTipoIntegracao())) {

                this.campanha.setFormaDeEnvio(TipoFormaEnvioEnum.GRAVACAO);

            } else {

                this.campanha.setFormaDeEnvio(null);
            }

            if (this.inpuStream != null && this.fileByte.length > 0L) {

                lerCabecalhoImportacao();
                // criandoTableTeste();

            } else {
                // SALVAR CAMPANHA SEM IMPORTACAO.....

                salvar();

            }

        } catch (ProativaException e) {
            // TODO: handle exception
            Messages.addGlobalError(e.getMessage());
        }

    }

    private void lerCabecalhoImportacao() {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.fileByte), StandardCharsets.ISO_8859_1));

            this.listCabecalho = Arrays.asList(reader.readLine().split("[;]"));

            this.mapImportacaoIndiceCabecalho = new HashMap<>();

            if (this.listCabecalho.size() > 100) {

                throw new ProativaException(" Número Máximo Colunas Permitido foi ultrapassado...");
            }

            criarDataModel();
            criarTabela();
            criarSelectItens();

            for (int i = 0; i < this.listCabecalho.size(); i++) {

                DadosBaseImportacaoEnum tiposDadosImportacao = DadosBaseImportacaoEnum.getBaseImportacaoEnum(listCabecalho.get(i).trim());
                this.mapImportacaoIndiceCabecalho.put(i, tiposDadosImportacao);
                this.dataTablePersonalizada.getChildren().add(criarColuna(listCabecalho.get(i), tiposDadosImportacao, i));
                //criandoTableTeste(listCabecalho.get(i), tiposDadosImportacao, i);
            }
            indiceMapDadosBase = 0;
            PrimeFaces.current().executeScript("PF('dlgLayout').show();");


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        } finally {

            try {

                if (reader != null)
                    reader.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void inicializarVariaveis(boolean pesquisar) {

        this.usuario = retornarUsuarioSessao();
        this.listStatusCampanha = serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

        this.inpuStream = null;
        this.tamanhoArquivo = 0L;

        if (!pesquisar) {

            this.listEquipesAssociados = new ArrayList<>();

            this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listStatusAtendimentosAssociados = new ArrayList<>();

            this.listProdutos = this.serviceProduto.pesquisarProdutosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

            this.listProdutosAssociados = new ArrayList<>();

            this.listFormaPagamento = this.serviceFormaPagamento.pesquisarformaPagamentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listFormaPagamentoAssociados = new ArrayList<>();

            this.listPausa = this.servicePausa.pesquisarPausaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listPausaAssociados = new ArrayList<>();

            this.listStatusTelefone = this.serviceStatusTelefone.pesquisarStatusTelefonesPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listStatusTelefoneAssociados = new ArrayList<>();

            this.listFormularios = this.serviceQuestionatio.pesquisarQuestionatiosPorEmpresa(retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);


            this.dualListEquipes = new DualListModel<>();
            this.dualListStatusAtendimento = new DualListModel<>();
            this.dualListProduto = new DualListModel<>();
            this.dualListFormaPagamento = new DualListModel<>();
            this.dualListPausa = new DualListModel<>();
            this.dualListStatusTelefone = new DualListModel<>();
            this.dataTablePersonalizada = new DataTable();
            this.fila = new Fila();
            this.indice = 0;
            this.mapImportacaoIndiceCabecalho = new TreeMap<>();
            this.campanhas3c = new ArrayList<>();

        }
    }

    public int contarLinhas() {

        int cont = 0;
        try {

            Scanner scan;
            ByteArrayInputStream arquivo = new ByteArrayInputStream(this.fileByte);
            scan = new Scanner(arquivo);

            scan.nextLine();

            while (scan.hasNextLine()) {
                scan.nextLine();
                cont++;
            }

            scan.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cont;

    }

    private void preencherPickList() {

        if (this.campanha.getEmpresa() == null || this.campanha.getEmpresa().getId() == null) {

            this.listEquipes = new ArrayList<>();

            this.listFilas = this.serviceFila.pesquisarFilasPorEmpresa(retornarEmpresaUsuarioSessao().getId());

            this.dualListEquipes = new DualListModel<>((List<Equipe>) CollectionUtils.subtract(this.listEquipes, this.listEquipesAssociados), this.listEquipesAssociados);

            this.listFilas = new ArrayList<>();


        } else {

            preencherListas(this.campanha.getEmpresa());

        }

    }

    // EQUIPE POR EMPRESA NÂO BUSCAR PELA MATRIZ
    private void preencherListas(Empresa empresa) {

        // CASO TIVER INTEGRACAO DE CONSULTA;.....

        this.listEquipes = this.serviceEquipe.pesquisarEquipes(empresa.getId(), TipoAcessoEnum.ATIVO);

        this.dualListEquipes = new DualListModel<>((List<Equipe>) CollectionUtils.subtract(this.listEquipes, this.listEquipesAssociados), this.listEquipesAssociados);

        //	this.dualListStatusAtendimento = new DualListModel<StatusAtendimento>((List<StatusAtendimento>) CollectionUtils.subtract(this.listStatusAtendimentos, this.listStatusAtendimentosAssociados),this.listStatusAtendimentosAssociados);

        //	this.dualListStatusTelefone = new DualListModel<StatusTelefone>((List<StatusTelefone>) CollectionUtils.subtract(this.listStatusTelefone, this.listStatusTelefoneAssociados),this.listStatusTelefoneAssociados);

        //	this.dualListFormaPagamento = new DualListModel<FormaPagamento>((List<FormaPagamento>) CollectionUtils.subtract(this.listFormaPagamento, this.listFormaPagamentoAssociados),	this.listFormaPagamentoAssociados);


        this.listFilas = this.serviceFila.pesquisarFilasPorEmpresa(empresa.getId());

    }


    public void salvar() {

        try {

            // VALIDAR CAMPANHA MAIS ANTIGA

            vincularDualListSalvar();
            limparDadosCampoOrientacao(this.campanha);
            boolean isSalvar = false;

            if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA) || this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) || this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_ARGUS) ) {

                this.statusTelefonePreditivo = this.serviceStatusTelefone.pesquisarStatusTelefone(retornarEmpresaMatrizUsuarioSessao().getId(), "Entregue Pelo Discador");

                if (this.statusTelefonePreditivo == null) {

                    this.statusTelefonePreditivo = new StatusTelefone("Entregue Pelo Discador");
                    this.statusTelefonePreditivo.setParametro(AcaoStatusTelefoneEnum.CONTATO_CLIENTE);
                    this.statusTelefonePreditivo.setAtivo(TipoAcessoEnum.ATIVO);
                    this.statusTelefonePreditivo.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                    atribuirUsuarioDataEntidade(this.statusTelefonePreditivo, true);
                    this.serviceStatusTelefone.inserir(this.statusTelefonePreditivo);

                }

                if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) && this.campanha.getCampanhaData3c() != null)
                    this.campanha.setCampanha3c(this.campanha.getCampanhaData3c().getId());
                else if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_ARGUS) && this.campanha.getSkillIten() != null)
                    this.campanha.setSkill(this.campanha.getSkillIten().getHashEndpointSkill());

            }

            if (this.campanha.getId() == null) {

                isSalvar = true;
                this.campanha.setUsuarioCadastro(retornarUsuarioSessao());
                this.campanha.setDataCadastro(new Date(System.currentTimeMillis()));

                inserir(this.campanha, false);
                inicializarDualListsVinculacoes(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

                if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

                    boolean naoAssociarStatusTel = this.campanha.getListStatusTelefone().stream().noneMatch(s -> (s.getId().longValue() == this.statusTelefonePreditivo.getId().longValue()));

                    if (naoAssociarStatusTel)
                        this.campanha.getListStatusTelefone().add(statusTelefonePreditivo);

                    this.campanha.setCodImportacaoPwd(this.servicePwd.inserirCampanhaPwd(this.campanha, Faces.getRequestBaseURL()));

                    if (this.campanha.getCodImportacaoPwd() != null)
                        this.serviceCampanha.atualizarCampanhaImportacaoPowerDialer(this.campanha);

                }

                // ALTARAR...
            } else {

                alterar(this.campanha, false);

                this.campanha.setCodImportacaoPwd(this.servicePwd.inserirCampanhaPwd(this.campanha, Faces.getRequestBaseURL()));

                if (this.campanha.getCodImportacaoPwd() != null)
                    this.serviceCampanha.atualizarCampanhaImportacaoPowerDialer(this.campanha);

            }

            if ((this.campanha.getFila() == null || this.campanha.getFila().getId() == null || StringUtils.isBlank(this.campanha.getFila().getNome())) && this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

                Messages.addGlobalWarn("Campanha salva com sucesso. Contudo não foi possivel enviar para o discador. fila não informada.", new Object());

            } else {

                Messages.addGlobalInfo("Campanha salva com sucesso.", new Object());
            }

            if (this.inpuStream != null && this.tamanhoArquivo != null && this.tamanhoArquivo > 0L) {

                iniciarImportacao(this.campanha);
            }

            if (isSalvar) {

                this.campanha = new Campanha();

            }

            this.strNomeArquivo = "";

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }

    private void iniciarImportacao(Campanha campanha) throws Exception {
        // TODO Auto-generated method stub

        Importacao importacao;
        importacao = new Importacao();
        importacao.setCampanha(campanha);
        importacao.setEmpresa(campanha.getEmpresa());
        importacao.setUsuario(this.usuario);
        importacao.setNomeArquivo(this.strNomeArquivo);
        importacao.setDataInicio(new Date());
        importacao.setStatusImportacao(StatusImportacaoEnum.NA_FILA);
        importacao.setQtidadeLinhas(contarLinhas());
        inserir(importacao);
        campanha.getListImportacao().add(0, importacao);
        importacao.setAgendamentoConsulta(campanha.getAgendamento());
        importacao.setConsultaRealizada((campanha.getAgendarConsulta() != null && campanha.getAgendarConsulta()) ? Boolean.FALSE : null);

        if (codTabelaRefin != null)
            importacao.setCodTabelaRefin(codTabelaRefin);

        this.importacaoUtil.sendImportacao(campanha.getId(), importacao, this.mapImportacaoIndiceCabecalho, this.fileByte);

    }

    private void vincularDualListSalvar() {

        this.campanha.setListEquipe(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListEquipes.getTarget())) {

            this.campanha.setListEquipe(this.dualListEquipes.getTarget());
        }

        this.campanha.setListStatusAtendimentos(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListStatusAtendimento.getTarget())) {

            this.campanha.setListStatusAtendimentos(this.dualListStatusAtendimento.getTarget());
        }

        this.campanha.setListStatusTelefone(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListStatusTelefone.getTarget())) {
            this.campanha.setListStatusTelefone(this.dualListStatusTelefone.getTarget());
        }

        this.campanha.setListProdutos(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListProduto.getTarget())) {

            this.campanha.setListProdutos(this.serviceProduto.pesquisarProdutos(retornarListIds(this.dualListProduto.getTarget())));

        }

        this.campanha.setListFormaPagamento(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListFormaPagamento.getTarget())) {

            this.campanha.setListFormaPagamento(this.dualListFormaPagamento.getTarget());

        }

        this.campanha.setListPausa(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListPausa.getTarget())) {

            this.campanha.setListPausa(this.dualListPausa.getTarget());

        }

        this.campanha.setListQuestionatios(new ArrayList<>());

        if (verificarListaNaoVazia(this.dualListQuestionario.getTarget())) {

            this.campanha.setListQuestionatios(this.serviceQuestionatio.pesquisarQuestionariosPorIds(retornarListIds(this.dualListQuestionario.getTarget())));


        }

    }

    private List<Long> retornarListIds(List<?> target) {

        return target.stream().map(e -> ((Generic) e).getId()).collect(Collectors.toList());


    }

    private boolean verificarListaNaoVazia(List<?> lista) {
        return CollectionUtils.isNotEmpty(lista);
    }

    public void voltar() {

        inicializarVariaveis(true);

        this.campanha = new Campanha();
        this.campanha.setEmpresa(this.usuario.getEmpresa());

        pesquisar();

        this.tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    private void pesquisar() {

        this.listCampanha = new ArrayList<>();

        if (this.dataFim != null) {

            Calendar calendar = zerarHora(this.dataFim);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            this.dataFim.setTime(calendar.getTimeInMillis());

            if (this.dataInicio != null) {

                if (this.dataInicio.compareTo(this.dataFim) < 0) {

                    this.listCampanha = this.serviceCampanha.pesquisarCampanhas(this.campanha, this.dataInicio, this.dataFim);

                } else {

                    Messages.addGlobalError(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);
                }

                // CASO DATA INICIO SEJA NULL - DATA INICIO 1 DIA A MAIS
            } else {

                this.listCampanha = this.serviceCampanha.pesquisarCampanhas(this.campanha, this.dataInicio, this.dataFim);
            }

            // RETORNA A DATA NORMAL...
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
            this.dataFim.setTime(calendar.getTimeInMillis());

        } else {

            this.listCampanha = this.serviceCampanha.pesquisarCampanhas(this.campanha, this.dataInicio, this.dataFim);

        }

    }

    public void pesquisarCampanha() {

        pesquisar();

    }

    public Calendar zerarHora(Date data) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dataFim);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;

    }

    public void novo() {

        inicializarVariaveis(false);

        this.tipoConsultaEscolhido = null;

        List<FormaPagamento> listFormaPagamentoAssociadas = new ArrayList<FormaPagamento>();
        List<StatusTelefone> listStatusTelefoneAssociadas = new ArrayList<StatusTelefone>();
        List<StatusAtendimento> listStatusAtendimentoAssociadas = new ArrayList<StatusAtendimento>();
        List<Pausa> listPausasAssociadas = new ArrayList<Pausa>();
        List<Produto> listProdutosAssociadas = new ArrayList<Produto>();
        List<Questionario> listQuestionatio = new ArrayList<Questionario>();

        this.campanha = new Campanha();

        if (retornarEmpresaMatrizUsuarioSessao().isPossuiFiliais()) {

            preencherPickList();

        } else {


            preencherListas(retornarEmpresaUsuarioSessao());
        }

        inicializarDualListsVinculacoes(this.listEquipesAssociados, listStatusAtendimentoAssociadas, listPausasAssociadas, listStatusTelefoneAssociadas, listProdutosAssociadas, listFormaPagamentoAssociadas, listQuestionatio);

        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(Long id) {

        Campanha campanha = this.serviceCampanha.pesquisarCampanha(id, true);

        this.tipoConsultaEscolhido = null;

        if (campanha != null && campanha.getId() != null) {

            this.campanha = campanha;

            inicializarVariaveis(false);

            List<Equipe> listEquipesAssociadas = new ArrayList<Equipe>();
            List<FormaPagamento> listFormaPagamentoAssociadas = new ArrayList<FormaPagamento>();
            List<StatusTelefone> listStatusTelefoneAssociadas = new ArrayList<StatusTelefone>();
            List<StatusAtendimento> listStatusAtendimentoAssociadas = new ArrayList<StatusAtendimento>();
            List<Pausa> listPausasAssociadas = new ArrayList<Pausa>();
            List<Produto> listProdutosAssociadas = new ArrayList<Produto>();
            List<Questionario> listQuestionarioAssociado = new ArrayList<Questionario>();

            if (this.campanha.getIntegrarWs() != null)
                this.tipoConsultaEscolhido = this.campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum();


            for (Produto produto : this.campanha.getListProdutos()) {

                listProdutosAssociadas.add(new Produto(produto.getId(), produto.getDescricao()));

            }

            for (Equipe equipe : this.campanha.getListEquipe()) {

                listEquipesAssociadas.add(new Equipe(equipe.getId(), equipe.getNome(), equipe.getAtivo()));

            }

            for (FormaPagamento formaPagamento : this.campanha.getListFormaPagamento()) {

                listFormaPagamentoAssociadas.add(new FormaPagamento(formaPagamento.getId(), formaPagamento.getDescricao(), formaPagamento.getParametro(), formaPagamento.getAtivo()));

            }

            for (StatusTelefone statusTelefone : this.campanha.getListStatusTelefone()) {

                listStatusTelefoneAssociadas.add(new StatusTelefone(statusTelefone.getId(), statusTelefone.getDescricao()));

            }

            for (StatusAtendimento statusAtendimento : this.campanha.getListStatusAtendimentos()) {

                listStatusAtendimentoAssociadas.add(new StatusAtendimento(statusAtendimento.getId(), statusAtendimento.getDescricao(), statusAtendimento.getAcao(), statusAtendimento.getAtivo()));
            }

            for (Pausa pausa : campanha.getListPausa()) {

                listPausasAssociadas.add(pausa);
            }

            for (Questionario questionario : this.campanha.getListQuestionatios()) {

                listQuestionarioAssociado.add(new Questionario(questionario.getId(), questionario.getDescricao()));
            }

            if (retornarUsuarioSessao().getEmpresa().isPossuiFiliais()) {

                preencherPickList();

            } else {

                preencherListas(retornarEmpresaUsuarioSessao());
            }

            gerarTotalEnvioDiscador();

            inicializarDualListsVinculacoes(listEquipesAssociadas, listStatusAtendimentoAssociadas, listPausasAssociadas, listStatusTelefoneAssociadas, listProdutosAssociadas, listFormaPagamentoAssociadas, listQuestionarioAssociado);

            if (this.campanha.getIntegrarWs() != null) {

                this.tipoConsultaEscolhido = this.campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum();

            }

            pesquisarConsultaSaque();
            pesquisarIntegracaoDiscador();
            tipoPagina = TipoPaginaEnum.CADASTRO;

        }

    }

    public void pesquisarIntegracaoDiscador() {

        if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) && this.campanha.getCampanha3c() != null) {

            try {

                IntegracaoWs integracaoWs = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

                if (integracaoWs != null) {

                    onBuscarIntegracaoDiscador();

                    if (CollectionUtils.isNotEmpty(this.campanhas3c)) {

                        Optional<com.proativaservicos.model.trescplus.Campanha> campanhaOptional = this.campanhas3c.stream().filter(c -> c.getId() == this.campanha.getCampanha3c()).findFirst();
                        campanhaOptional.ifPresent(value -> this.campanha.setCampanhaData3c(value));
                    }

                    if (this.campanha.getCampanhaData3c() == null) {

                        ResponseCampanha campanhaResponse = this.tresCPlusServiceUtil.pesquisarCampanha(integracaoWs, this.campanha.getCampanha3c());

                        if (campanhaResponse.getStatus() != 200)
                            throw new ProativaException(campanhaResponse.getTitle() + " - " + campanhaResponse.getDetail());

                        this.campanha.setCampanhaData3c(campanhaResponse.getData());
                    }

                }

            } catch (ProativaException e) {
                e.printStackTrace();
                Messages.addGlobalError(e.getMessage());
            }

        }else if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_ARGUS) && this.campanha.getSkillIten() == null ) {



            IntegracaoWs integracaoWs = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.ARGUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (integracaoWs != null) {

                onBuscarIntegracaoDiscador();

                if (CollectionUtils.isNotEmpty(this.retornoGetSkillsItens)) {

                    Optional<RetornoGetSkillsIten> campanhaOptional = this.retornoGetSkillsItens.stream().filter(c -> c.getHashEndpointSkill().equals(this.campanha.getSkill())).findFirst();

                    campanhaOptional.ifPresent(value -> this.campanha.setSkillIten(value));
                }


            }

        }
    }


    private void gerarTotalEnvioDiscador() {

        try {


            if (this.campanha.getListImportacao() != null && !this.campanha.getListImportacao().isEmpty()) {

                for (Importacao importacao : this.campanha.getListImportacao()) {

                    Integer totalEnviado = 0;
                    Integer totalNaoEnviado = 0;

                    Integer totalEnviadoRetrabalhado = 0;
                    Integer totalNaoEnviadoRetrabalhado = 0;

                    importacao.setListLogImportacaoDiscador(this.serviceLogImportacaoDiscadorService.pesquisarLogImportacaoDiscadorPorImportacao(importacao.getId()));

                    if (CollectionUtils.isNotEmpty(importacao.getListLogImportacaoDiscador())) {

                        for (LogImportacaoDiscador log : importacao.getListLogImportacaoDiscador()) {

                            if (StringUtils.isNotBlank(log.getDadoRetorno())) {

                                if (log.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX) && Utils.isJSON(log.getDadoRetorno())) {

                                    JSONObject jsonObjRetorno = new JSONObject(log.getDadoRetorno());

                                    if (log.getRetrabalhar() != null && log.getRetrabalhar().equals(Boolean.TRUE)) {

                                        totalEnviadoRetrabalhado = totalEnviadoRetrabalhado + (jsonObjRetorno.isNull("imported_count") ? 0 : Integer.valueOf(jsonObjRetorno.getInt("imported_count")));

                                        totalNaoEnviadoRetrabalhado = totalNaoEnviadoRetrabalhado + (jsonObjRetorno.isNull("not_imported_count") ? 0 : Integer.valueOf(jsonObjRetorno.getInt("not_imported_count")));


                                    } else {

                                        totalEnviado = totalEnviado + (jsonObjRetorno.isNull("imported_count") ? 0 : Integer.valueOf(jsonObjRetorno.getInt("imported_count")));

                                        totalNaoEnviado = totalNaoEnviado + (jsonObjRetorno.isNull("not_imported_count") ? 0 : Integer.valueOf(jsonObjRetorno.getInt("not_imported_count")));
                                        log.setRetrabalhar(Boolean.FALSE);
                                    }

                                } else if (log.getTipoIntegracao().equals(TipoIntegracaoEnum.VIRTUAL_POWER_DIALER)) {

                                    totalEnviado = log.getQuantidadeEnviado() != null ? Integer.parseInt(String.valueOf(log.getQuantidadeEnviado())) : 0;

                                }

                            }

                            importacao.setQuantidadeImportadoDiscador(totalEnviado == 0 ? null : totalEnviado);

                            importacao.setQuantidadeNaoImportadoDiscador(totalNaoEnviado == 0 ? null : totalNaoEnviado);

                            importacao.setQuantidadeImportadoDiscadorRetrabalhado(totalEnviadoRetrabalhado == 0 ? null : totalEnviadoRetrabalhado);

                            importacao.setQuantidadeNaoImportadoDiscadorRetrabalhado(totalNaoEnviadoRetrabalhado == 0 ? null : totalNaoEnviadoRetrabalhado);


                        }
                    }
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void onPesquisarLogImportacao(Importacao idImportacao) {

        try {

            this.listLogImportacoesDiscador = null;

            if (idImportacao != null && this.campanha != null && this.campanha.getId() != null) {

                this.listLogImportacoesDiscador = idImportacao.getListLogImportacaoDiscador();

                if (this.listLogImportacoesDiscador != null && !this.listLogImportacoesDiscador.isEmpty()) {

                    Integer totalEnviado = 0;
                    Integer totalNaoEnviado = 0;

                    for (LogImportacaoDiscador log : this.listLogImportacoesDiscador) {

                        if (StringUtils.isNotBlank(log.getDadoRetorno())) {

                            if (log.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX)) {

                                if (Utils.isJSON(log.getDadoRetorno())) {

                                    JSONObject jsonObjRetorno = new JSONObject(log.getDadoRetorno());

                                    totalEnviado = totalEnviado + (jsonObjRetorno.isNull("imported_count") ? 0 : Integer.valueOf(jsonObjRetorno.getInt("imported_count")));

                                    totalNaoEnviado = totalNaoEnviado + (jsonObjRetorno.isNull("not_imported_count") ? 0 : Integer.valueOf(jsonObjRetorno.getInt("not_imported_count")));

                                    if (log.getRetrabalhar() == null) {
                                        log.setRetrabalhar(Boolean.FALSE);
                                    }

                                }
                            }

                        }

                    }

                }

            }

            PrimeFaces.current().executeScript("PF('dlgLayoutLog').show();");
            PrimeFaces.current().ajax().update(":formTableLog:dlgLayoutLog");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    public void trocarEmpresa() {

        this.listEquipesAssociados = new ArrayList<Equipe>();

        this.listEquipes = new ArrayList<>();

        this.campanha.setListEquipe(new ArrayList<Equipe>());

        this.campanha.setListQuestionatios(new ArrayList<Questionario>());


        if (this.campanha.getId() != null) {

            this.serviceCampanha.excluirEquipesCampanha(this.campanha.getId());
        }

        preencherPickList();

    }

    public void salvarFila() {

        try {

            if (!retornarEmpresaUsuarioSessao().isPossuiFiliais()) {

                this.fila.setEmpresa(this.retornarEmpresaUsuarioSessao());

            } else {

                this.fila.setEmpresa(this.campanha.getEmpresa());
            }

            if (this.fila.getEmpresa() == null || this.fila.getEmpresa().getId() == null) {

                throw new ProativaException("A empresa não foi Informada");
            }

            if (this.fila.getId() == null) {

                atribuirUsuarioDataEntidade((Serializable) this.fila, true);

                inserirGenerico(this.fila);

            } else {

                atribuirUsuarioDataEntidade((Serializable) this.fila, false);
                alterarGenerico(this.fila);
            }

            this.listFilas = this.serviceFila.pesquisarFilasPorEmpresa(this.fila.getEmpresa().getId());

            this.fila = new Fila();

            Messages.addGlobalInfo("Fila salva com sucesso!", new Object[0]);

        } catch (ProativaException e) {

            e.printStackTrace();

            Messages.addGlobalError(e.getMessage(), new Object[0]);
        }

    }

    public void limparDadosCampoOrientacao(Campanha campanha) {

        if (campanha != null && campanha.getOrientacao() != null) {

            String orientacao = campanha.getOrientacao().replaceAll("(?s)<form[^>]*>.*?</form>", "");
            campanha.setOrientacao(orientacao);
        }
    }

    private void inicializarDualListsVinculacoes(List<Equipe> listaEquipeAssociados,
                                                 List<StatusAtendimento> listaStatusAtendimentoAssociados, List<Pausa> listaMotivoPausaAssociados,
                                                 List<StatusTelefone> listaStatusTelefoneAssociados, List<Produto> listaProdutoAssociados,
                                                 List<FormaPagamento> listaFormaPagamentoAssociados, List<Questionario> listQuestionario) {

        this.dualListEquipes = new DualListModel<Equipe>(
                (List<Equipe>) CollectionUtils.subtract(this.listEquipes, listaEquipeAssociados),
                listaEquipeAssociados);

        this.dualListStatusAtendimento = new DualListModel<StatusAtendimento>((List<StatusAtendimento>) CollectionUtils
                .subtract(this.listStatusAtendimentos, listaStatusAtendimentoAssociados),
                listaStatusAtendimentoAssociados);

        this.dualListPausa = new DualListModel<Pausa>(
                (List<Pausa>) CollectionUtils.subtract(this.listPausa, listaMotivoPausaAssociados),
                listaMotivoPausaAssociados);

        this.dualListProduto = new DualListModel<Produto>((List<Produto>) CollectionUtils.subtract(this.listProdutos, listaProdutoAssociados), listaProdutoAssociados);

        this.dualListFormaPagamento = new DualListModel<FormaPagamento>(
                (List<FormaPagamento>) CollectionUtils.subtract(this.listFormaPagamento, listaFormaPagamentoAssociados),
                listaFormaPagamentoAssociados);

        this.dualListStatusTelefone = new DualListModel<StatusTelefone>(
                (List<StatusTelefone>) CollectionUtils.subtract(listStatusTelefone, listaStatusTelefoneAssociados),
                listaStatusTelefoneAssociados);

        this.listaTipoConsultasDisponiveis = new ArrayList<TipoConsultaEnum>();

        this.listaTipoConsultasDisponiveis.addAll(Arrays.asList(new TipoConsultaEnum[]{TipoConsultaEnum.SAQUE, TipoConsultaEnum.SEGURO, TipoConsultaEnum.SEGURO_PAPCARD_PARCELADO, TipoConsultaEnum.SEGURO_BMG_MED, TipoConsultaEnum.REFIN, TipoConsultaEnum.CARTAO_BENEFICIO,TipoConsultaEnum.SAQUE_MASTER}));

        this.dualListQuestionario = new DualListModel<Questionario>((List<Questionario>) CollectionUtils.subtract(this.listFormularios, listQuestionario), listQuestionario);


    }

    public void pesquisarConsultaSaque() {

        List<TipoIntegracaoEnum> integracaoPossiveis = new ArrayList<>();

        this.listConsultaSaque = new ArrayList<IntegracaoWs>();

        this.listConsultaSeguroPapCard = new ArrayList<IntegracaoWs>();

        this.listConsultaRefin = new ArrayList<IntegracaoWs>();

        this.listConsultaBeneficio = new ArrayList<IntegracaoWs>();

        this.listConsultaMaster = new ArrayList<>();

        if (this.tipoConsultaEscolhido != null) {

            integracaoPossiveis = Arrays.stream(TipoIntegracaoEnum.values()).filter(a -> this.tipoConsultaEscolhido.equals(a.getTipoConsultaEnum())).collect(Collectors.toList());

        } else {

            this.campanha.setIntegrarWs(null);
        }

        if (!integracaoPossiveis.isEmpty() && this.campanha.getEmpresa() != null) {

            if (TipoConsultaEnum.SAQUE.equals(this.tipoConsultaEscolhido)) {

                this.listConsultaSaque = this.serviceIntegracao.pesquisarIntegracoes(integracaoPossiveis, this.campanha.getEmpresa().getId(), TipoAcessoEnum.ATIVO);


            } else if (this.tipoConsultaEscolhido.name().startsWith("SEGURO")) {

                this.listConsultaSeguroPapCard = this.serviceIntegracao.pesquisarIntegracoes(integracaoPossiveis, this.campanha.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                this.campanha.setConsultaSeguro(true);

            } else if (TipoConsultaEnum.REFIN.equals(this.tipoConsultaEscolhido)) {

                this.listConsultaRefin = this.serviceIntegracao.pesquisarIntegracoes(integracaoPossiveis, this.campanha.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                onChangComboRefin();


            } else if (TipoConsultaEnum.CARTAO_BENEFICIO.equals(this.tipoConsultaEscolhido)) {

                this.listConsultaBeneficio = this.serviceIntegracao.pesquisarIntegracoes(integracaoPossiveis, this.campanha.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            }else if (TipoConsultaEnum.SAQUE_MASTER.equals(this.tipoConsultaEscolhido)) {

                this.listConsultaMaster = this.serviceIntegracao.pesquisarIntegracoes(integracaoPossiveis, this.campanha.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            }


        } else {

            this.listConsultaSaque = new ArrayList<>();
        }
    }

    public void exportarCampanhaCSV() {

        try {

            byte[] arquivo = this.csv.executar(this.campanha.getId());
            this.arquivoExtrator = DefaultStreamedContent.builder().name(this.campanha.getDescricao() + ".csv").contentType("text/csv").stream(() -> new ByteArrayInputStream(arquivo)).build();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }


    public void exportarCampanha3cCSV(String fileArquivo) {

        try {

            System.out.println("NOME ARQUIVO: " + fileArquivo);

            if (StringUtils.isNotBlank(fileArquivo)) {

                File diretorio = criarDiretorios("campanhas_3c");
                File arquivo = new File(diretorio, fileArquivo);

                System.out.println(arquivo.getAbsolutePath());

                if (arquivo.exists()) {
                    byte[] arquivoStream = Files.readAllBytes(arquivo.toPath());
                    System.out.println("Existe...");
                    this.arquivoMailing3c = DefaultStreamedContent.builder().name(fileArquivo).contentType("text/csv").stream(() -> new ByteArrayInputStream(arquivoStream)).build();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    public void onDialogRetrabalharCampanha(Long idCampanha) {

        this.idCampanhaRetrabalhar = idCampanha;
        this.retrabalharFimFila = false;

        if (this.idCampanhaRetrabalhar != null) {

            this.campanhaRetrabalhar = this.serviceCampanha.pesquisarCampanha(idCampanhaRetrabalhar, false);

            if (this.campanhaRetrabalhar != null && this.campanhaRetrabalhar.getId() != null) {

                this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosFimFilaPorCampanhaEmAtendimentos(this.idCampanhaRetrabalhar);
                this.dualListStatusAtendimento = new DualListModel<StatusAtendimento>(this.listStatusAtendimentos, new ArrayList<StatusAtendimento>());
                this.campanhaRetrabalhar.setListImportacao(this.serviceImportacao.pesquisarImportacaoPorCampanha(idCampanha));

                if (this.campanhaRetrabalhar.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) && this.campanhaRetrabalhar.getCampanha3c() != null) {

                    buscarCampanhas3c();
                    this.campanhaRetrabalhar.setCampanhaData3c(this.campanhas3c.stream().filter(p -> p.getId() == this.campanhaRetrabalhar.getCampanha3c()).findFirst().orElse(null));
                }


                PrimeFaces.current().executeScript("PF('dlgCampanhaRetrabalhar').show();");
                PrimeFaces.current().ajax().update("dlgCampanhaRetrabalhar");
            }

        }

    }

    public void retrabalharCampanha() {

        try {

            if (!retrabalharFimFila && (this.dualListStatusAtendimento.getTarget() == null || this.dualListStatusAtendimento.getTarget().isEmpty()))
                throw new ProativaException("Dever ser escolhido pelo menos um status de atendimento a ser retrabalhado.");

            //   ConvertUtils.register((Converter) new DateConverter(null), Date.class);

            if (this.campanhaRetrabalhar != null && this.campanhaRetrabalhar.getId() != null) {

                List<Long> listStatusAtendimentosIds = new ArrayList<Long>();

                for (StatusAtendimento status : dualListStatusAtendimento.getTarget()) {

                    listStatusAtendimentosIds.add(status.getId());
                }


                if (this.campanhaRetrabalhar.getTipoCampanha().equals(TipoCampanhaEnum.ATIVA)) {

                    this.serviceAtendimentoAtivo.retrabalharAtendimentosAtivo(this.campanhaRetrabalhar.getId(), listStatusAtendimentosIds, retrabalharFimFila);

                } else if (this.campanhaRetrabalhar.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA) || this.campanhaRetrabalhar.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) || this.campanhaRetrabalhar.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_ARGUS)) {

                    this.campanhaRetrabalhar.setRetrabalharCampanha(Boolean.TRUE);
                    System.out.println("Retrabalhar...");

                    if (CollectionUtils.isNotEmpty(this.listImportacao))
                        this.serviceCampanha.retrabalharCampanhaFimFila(this.campanhaRetrabalhar, this.usuario, listStatusAtendimentosIds, this.listImportacao, retrabalharFimFila);
                    else
                        this.serviceCampanha.retrabalharCampanhaFimFila(this.campanhaRetrabalhar, this.usuario, listStatusAtendimentosIds, retrabalharFimFila);


                }

                Messages.addGlobalInfo("Campanha retrabalhada com sucesso,");
                retrabalharFimFila = false;
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void onFinalizarCampanha() {

        try {

            if (this.idCampanhaRetrabalhar == null)
                throw new ProativaException("Campanha não informada.");

            if (CollectionUtils.isEmpty(this.listStatusCampanha))
                throw new ProativaException("Status de Campanha não está cadastrada");

            if (this.listStatusCampanha.stream().noneMatch(s -> AcaoCampanhaEnum.FINALIZAR.equals(s.getAcao())))
                throw new ProativaException("Nenhuma acão de status finalizadora de campanha cadastrada.");

            Campanha campanha = this.serviceCampanha.pesquisarCampanha(idCampanhaRetrabalhar, false);

            if (campanha.getStatus().getAcao().equals(AcaoCampanhaEnum.FINALIZAR))
                throw new ProativaException("Campanha já está finalizada.");


            campanha.setStatus(this.listStatusCampanha.stream().filter(s -> AcaoCampanhaEnum.FINALIZAR.equals(s.getAcao())).findFirst().get());

            atribuirUsuarioDataEntidade((Serializable) campanha, false);
            this.serviceCampanha.alterar(campanha);

            this.serviceAtendimentoSingleton.removerCampanha(campanha.getId());

            //REMOVER DO DISCADOR....
            if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {
                System.out.println("FINALIZANDO...");
                this.servicePwd.inserirCampanhaPwd(campanha, Faces.getRequestBaseURL());
                this.serviceCampanha.finalizarCampanhaDiscador(campanha, this.usuario);

            }

            pesquisar();
            Messages.addGlobalInfo("Campanha finalizada com sucesso.", new Object[0]);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    public void onRemoverContatosDiscador() {


    }

    public void onChangComboRefin() {


        if (this.campanha != null && this.campanha.getIntegrarWs() != null && this.campanha.getIntegrarWs().getUsr() != null && this.campanha.getIntegrarWs().getPsw() != null && this.campanha.getIntegrarWs().getUrl() != null) {

            try {

                this.listProdutosRefin = this.refinUtil.retornarProdutosRefin(this.campanha.getIntegrarWs().getUrl(), this.campanha.getIntegrarWs().getUsr(), this.campanha.getIntegrarWs().getPsw(), this.usuario, false);

            } catch (ProativaException e) {

                Messages.addGlobalError(e.getMessage() + " - [ " + this.campanha.getIntegrarWs().getDescricao() + " - Loja: " + this.campanha.getIntegrarWs().getCodLojaEmpresa() + " ]", new Object[0]);

            } catch (Exception e) {

                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante + " - [ " + this.campanha.getIntegrarWs().getUsr() + " ]", new Object[0]);

            } finally {

                if (CollectionUtils.isEmpty(this.listProdutosRefin)) {

                    this.listProdutosRefin = new ArrayList<>();
                    ProdutoReturn produto = new ProdutoReturn();
                    produto.setCodigo(9083);
                    produto.setDescricao("9083 - INSS REF NORMAL DIGITAL TOKEN");
                    this.listProdutosRefin.add(produto);

                    ProdutoReturn produto2 = new ProdutoReturn();
                    produto2.setCodigo(9085);
                    produto2.setDescricao("9085 - INSS REF FLEX 2 DIGITAL TOKEN");
                    this.listProdutosRefin.add(produto2);

                }

            }

        } else {

            //Messages.addGlobalError("Serviço de Integração Invalida. ["+ ((this.campanha ==null ||this.campanha.getIntegrarWs()==null) ? "Nenhum informado": this.campanha.getIntegrarWs().getDescricao())+" ]", new Object[0]);

        }

    }


    public void limparContatosDiscador() {

        try {

            System.out.println("Limpando contatos da fila: " + this.campanhaRetrabalhar.getFila().getNome());

            if (this.campanhaRetrabalhar != null && this.campanhaRetrabalhar.getFila() != null) {

                if (StringUtils.isNotBlank(this.campanhaRetrabalhar.getFila().getNome())) {

                    if (!this.discadorUtil.limparContatosFila(this.usuario, this.campanhaRetrabalhar.getFila().getNome())) {

                        Messages.addGlobalError("Acorreu um erro ao limpar a fila.", new Object[0]);

                    } else {

                        Messages.addGlobalInfo("Contatos removidos da fila com sucesso.", new Object[0]);
                    }

                }

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void consultarContatosDiscador() {

        try {

            if (this.campanhaRetrabalhar != null) {

                String retorno = this.discadorUtil.consultarContatos(this.usuario, this.campanhaRetrabalhar);

                this.campanhaRetrabalhar.setTotalDiscado(StringUtils.isNotBlank(retorno) ? retorno : "Nenhum dado retornado.");

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void reconsultarWebservice(Importacao importacao) {

        try {

            if (this.campanha != null && this.campanha.getIntegrarWs() == null)
                throw new ProativaException("Favor informar a integração.");

            if (this.campanha != null && this.campanha.getIntegrarWs() != null && (this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.BMG_SEGURO) || this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.BMG_GRAVACAO) || this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.PAN_SIMULACAO_SAQUE) || this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.API_BANCO_MASTER))) {


                if (importacao != null && this.campanha != null) {

                    System.out.println("REMOVENDO OBSERVACAO ATENDIMENTO DA CAMPANHA: " + this.campanha.getDescricao() + " | IMPORTACAO: " + importacao + " | USUARIO: " + this.usuario.getNome());

                    this.serviceAtendimento.removerObservacaoValorMaximo(importacao.getId(), this.campanha.getId());

                    System.out.println("INICIANDO RECONSULTA....");

                    if (this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.BMG_SEGURO)) {

                        this.consultaSeguro.consultarProdutoSeguro(importacao, this.campanha, this.usuario, getEmpresa());

                    } else if (this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.PAN_SIMULACAO_SAQUE)) {
                        System.out.println("CONSULTA PAN....");
                        this.consultaSimulacaoPan.consultarSimulacaoSaque(importacao, this.campanha, this.usuario, getEmpresa());

                    } else if(this.campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.API_BANCO_MASTER)){
                        this.consultaMaster.consultarSaque(importacao,this.campanha,this.usuario,getEmpresa());

                    }else

                        this.consultaSaque.consultarSaqueComplementar(importacao, this.campanha, this.usuario, getEmpresa());

                    Messages.addGlobalInfo("A reconsulta foi inicializada com sucesso.");

                }

            }  else
                throw new ProativaException("Está ação está disponível para esta integração.");


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);


        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public boolean isConsultarCartaoBMG() {
        return consultarCartaoBMG;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public DadosBaseImportacaoEnum getDadosDabseImportacao() {

        return mapImportacaoIndiceCabecalho.get(indiceMapDadosBase++);

        //  return dadosDabseImportacao;
    }

    public ListDataModel<String[]> getDataModel() {
        return dataModel;
    }

    public DataTableBase getDataTablePersonalizada() {

        return dataTablePersonalizada;
    }

    public void onBuscarIntegracaoDiscador() {


        if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C)) {
            buscarCampanhas3c();

        } else if (this.campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_ARGUS)) {

            buscarSkillArqus();
        }
    }

    private void buscarSkillArqus() {

        try {

            IntegracaoWs integracaoWs = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.ARGUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (integracaoWs == null)
                throw new ProativaException("Nenhum serviço de integração encontrado.");

            SkillsResponse skillsResponse = this.argusService.buscarSkills(integracaoWs);

            if (skillsResponse != null && skillsResponse.getCodStatus() < 1)
                throw new ProativaException(StringUtils.isNotBlank(skillsResponse.getDescStatus()) ? skillsResponse.getDescStatus() : "Nenhuma informação foi recebida da API.");

            if (skillsResponse == null || CollectionUtils.isEmpty(skillsResponse.getRetornoGetSkillsItens()))
                throw new ProativaException("Nenhum skill encontrada");

            this.retornoGetSkillsItens = skillsResponse.getRetornoGetSkillsItens();


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    private void buscarCampanhas3c() {

        try {

            IntegracaoWs integracaoWs = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (integracaoWs == null)
                throw new ProativaException("Nenhum serviço de integração encontrado.");

            ResponseListCampanha campanhaResponse = tresCPlusServiceUtil.listarCampanhas(integracaoWs);

            if (campanhaResponse != null && campanhaResponse.getStatus() != 200)
                throw new ProativaException(campanhaResponse.getTitle() + " - " + campanhaResponse.getDetail());


            if (campanhaResponse == null || CollectionUtils.isEmpty(campanhaResponse.getData()))
                throw new ProativaException("Nenhuma campanha 3C+ encontrada.");

            this.campanhas3c = campanhaResponse.getData();

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void onBuscarLogImportacao(Long id) {
        List<LogImportacaoDiscador> listLog = this.serviceLogImportacaoDiscadorService.pesquisarLogImportacaoDiscadorPorImportacao(id);
        this.campanha.getListImportacao().stream().filter(i -> i.getId().equals(id)).findFirst().get().setListLogImportacaoDiscador(listLog);

    }

    public DualListModel<Equipe> getDualListEquipes() {
        return dualListEquipes;
    }

    public DualListModel<Pausa> getDualListPausa() {
        return dualListPausa;
    }

    public DualListModel<Produto> getDualListProduto() {
        return dualListProduto;
    }

    public DualListModel<StatusAtendimento> getDualListStatusAtendimento() {
        return dualListStatusAtendimento;
    }

    public Fila getFila() {
        return fila;
    }

    public UploadedFile getFile() {
        return file;
    }

    public int getIndice() {
        return indice;
    }

    public InstituicaoFinanceiraEnum[] getInstituicao() {
        return InstituicaoFinanceiraEnum.values();
    }

    public InstituicaoFinanceiraEnum[] getInstituicaoFinanceira() {

        return InstituicaoFinanceiraEnum.values();
    }

    public List<SelectItem> getListaSelectItens() {
        return listaSelectItens;
    }

    public List<String> getListCabecalho() {
        return listCabecalho;
    }

    public List<?> getListCampanha() {
        return listCampanha;
    }

    public List<Empresa> getListEmpresas() {
        return listEmpresas;
    }

    public List<Fila> getListFilas() {
        return listFilas;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public List<Usuario> getListUsuariosAssociados() {
        return listUsuariosAssociados;
    }

    public Map<Integer, DadosBaseImportacaoEnum> getMapImportacaoIndiceCabecalho() {
        return mapImportacaoIndiceCabecalho;
    }

    public SegmentoEnum[] getSegmento() {
        return SegmentoEnum.values();
    }

    public TipoAcessoEnum[] getTipoAcesso() {
        return TipoAcessoEnum.values();
    }

    public TipoCampanhaEnum[] getTipoCampanha() {
        return TipoCampanhaEnum.values();
    }

    public TipoDiscagemEnum[] getTipoDiscagem() {
        return TipoDiscagemEnum.values();
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public void setConsultarCartaoBMG(boolean consultarCartaoBMG) {
        this.consultarCartaoBMG = consultarCartaoBMG;
    }

    public void setDadosDabseImportacao(DadosBaseImportacaoEnum dadosDabseImportacao) {
        this.dadosDabseImportacao = dadosDabseImportacao;
    }

    public void setDataModel(ListDataModel<String[]> dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataTablePersonalizada(DataTable dataTablePersonalizada) {
        this.dataTablePersonalizada = dataTablePersonalizada;
    }

    public void setDualListEquipes(DualListModel<Equipe> dualListEquipes) {
        this.dualListEquipes = dualListEquipes;
    }

    public List<StatusTelefone> getListStatusTelefoneAssociados() {
        return listStatusTelefoneAssociados;
    }

    public void setListStatusTelefoneAssociados(List<StatusTelefone> listStatusTelefoneAssociados) {
        this.listStatusTelefoneAssociados = listStatusTelefoneAssociados;
    }

    public DualListModel<FormaPagamento> getDualListFormaPagamento() {
        return dualListFormaPagamento;
    }

    public void setDualListFormaPagamento(DualListModel<FormaPagamento> dualListFormaPagamento) {
        this.dualListFormaPagamento = dualListFormaPagamento;
    }

    public void setDualListPausa(DualListModel<Pausa> dualListPausa) {
        this.dualListPausa = dualListPausa;
    }

    public void setDualListProduto(DualListModel<Produto> dualListProduto) {
        this.dualListProduto = dualListProduto;
    }

    public void setDualListStatusAtendimento(DualListModel<StatusAtendimento> dualListStatusAtendimento) {
        this.dualListStatusAtendimento = dualListStatusAtendimento;
    }

    public void setFila(Fila fila) {
        this.fila = fila;
    }

    public void setFile(UploadedFile file) {
        this.strNomeArquivo = this.file.getFileName();
        this.file = file;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setListaSelectItens(List<SelectItem> listaSelectItens) {
        this.listaSelectItens = listaSelectItens;
    }

    public void setListCabecalho(List<String> listCabecalho) {
        this.listCabecalho = listCabecalho;
    }

    public void setListCampanha(List<Campanha> listCampanha) {
        this.listCampanha = listCampanha;
    }

    public void setListEmpresas(List<Empresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public void setListFilas(List<Fila> listFilas) {
        this.listFilas = listFilas;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public void setListUsuariosAssociados(List<Usuario> listUsuariosAssociados) {
        this.listUsuariosAssociados = listUsuariosAssociados;
    }

    public void setMapImportacaoIndiceCabecalho(Map<Integer, DadosBaseImportacaoEnum> mapImportacaoIndiceCabecalho) {
        this.mapImportacaoIndiceCabecalho = mapImportacaoIndiceCabecalho;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Equipe> getListEquipesAssociados() {
        return listEquipesAssociados;
    }

    public void setListEquipesAssociados(List<Equipe> listEquipesAssociados) {
        this.listEquipesAssociados = listEquipesAssociados;
    }

    public List<StatusAtendimento> getListStatusAtendimentos() {
        return listStatusAtendimentos;
    }

    public void setListStatusAtendimentos(List<StatusAtendimento> listStatusAtendimentos) {
        this.listStatusAtendimentos = listStatusAtendimentos;
    }

    public List<StatusAtendimento> getListStatusAtendimentosAssociados() {
        return listStatusAtendimentosAssociados;
    }

    public void setListStatusAtendimentosAssociados(List<StatusAtendimento> listStatusAtendimentosAssociados) {
        this.listStatusAtendimentosAssociados = listStatusAtendimentosAssociados;
    }

    public List<FormaPagamento> getListFormaPagamento() {
        return listFormaPagamento;
    }

    public void setListFormaPagamento(List<FormaPagamento> listFormaPagamento) {
        this.listFormaPagamento = listFormaPagamento;
    }

    public List<FormaPagamento> getListFormaPagamentoAssociados() {
        return listFormaPagamentoAssociados;
    }

    public void setListFormaPagamentoAssociados(List<FormaPagamento> listFormaPagamentoAssociados) {
        this.listFormaPagamentoAssociados = listFormaPagamentoAssociados;
    }

    public List<Pausa> getListPausa() {
        return listPausa;
    }

    public void setListPausa(List<Pausa> listPausa) {
        this.listPausa = listPausa;
    }

    public List<Pausa> getListPausaAssociados() {
        return listPausaAssociados;
    }

    public void setListPausaAssociados(List<Pausa> listPausaAssociados) {
        this.listPausaAssociados = listPausaAssociados;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public List<Produto> getListProdutosAssociados() {
        return listProdutosAssociados;
    }

    public void setListProdutosAssociados(List<Produto> listProdutosAssociados) {
        this.listProdutosAssociados = listProdutosAssociados;
    }

    public DualListModel<StatusTelefone> getDualListStatusTelefone() {
        return dualListStatusTelefone;
    }

    public void setDualListStatusTelefone(DualListModel<StatusTelefone> dualListStatusTelefone) {
        this.dualListStatusTelefone = dualListStatusTelefone;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getStrNomeArquivo() {
        return strNomeArquivo;
    }

    public void setStrNomeArquivo(String strNomeArquivo) {
        this.strNomeArquivo = strNomeArquivo;
    }

    public void onInstituicaoFinanceiraChange() {

        this.campanha.setValidarSaque(InstituicaoFinanceiraEnum.BMG.equals(this.campanha.getInstituicaoFinanceira()));
    }

    public List<TipoConsultaEnum> getListaTipoConsultasDisponiveis() {
        return listaTipoConsultasDisponiveis;
    }

    public void setListaTipoConsultasDisponiveis(List<TipoConsultaEnum> listaTipoConsultasDisponiveis) {
        this.listaTipoConsultasDisponiveis = listaTipoConsultasDisponiveis;
    }

    public TipoConsultaEnum getTipoConsultaEscolhido() {
        return tipoConsultaEscolhido;
    }

    public void setTipoConsultaEscolhido(TipoConsultaEnum tipoConsultaEscolhido) {
        this.tipoConsultaEscolhido = tipoConsultaEscolhido;
    }

    public List<IntegracaoWs> getListConsultaSaque() {
        return listConsultaSaque;
    }

    public void setListConsultaSaque(List<IntegracaoWs> listConsultaSaque) {
        this.listConsultaSaque = listConsultaSaque;
    }

    public List<StatusTelefone> getListStatusTelefone() {
        return this.listStatusTelefone;
    }

    public void setListStatusTelefone(List<StatusTelefone> listStatusTelefone) {
        this.listStatusTelefone = listStatusTelefone;
    }

    public VariaveisSctiptOrientacao[] getVariaveisSctiptOrientacao() {
        return VariaveisSctiptOrientacao.values();

    }


    public StreamedContent getArquivoExtrator() {

        exportarCampanhaCSV();

        return arquivoExtrator;
    }

    public void setArquivoExtrator(StreamedContent arquivoExtrator) {
        this.arquivoExtrator = arquivoExtrator;
    }

    public List<IntegracaoWs> getListConsultaSeguroPapCard() {
        return listConsultaSeguroPapCard;
    }

    public void setListConsultaSeguroPapCard(List<IntegracaoWs> listConsultaSeguroPapCard) {
        this.listConsultaSeguroPapCard = listConsultaSeguroPapCard;
    }

    public List<LogImportacaoDiscador> getListLogImportacoesDiscador() {
        return listLogImportacoesDiscador;
    }

    public void setListLogImportacoesDiscador(List<LogImportacaoDiscador> listLogImportacoesDiscador) {
        this.listLogImportacoesDiscador = listLogImportacoesDiscador;
    }

    public Campanha getCampanhaRetrabalhar() {
        return campanhaRetrabalhar;
    }

    public void setCampanhaRetrabalhar(Campanha campanhaRetrabalhar) {
        this.campanhaRetrabalhar = campanhaRetrabalhar;
    }

    public Long getIdCampanhaRetrabalhar() {
        return idCampanhaRetrabalhar;
    }

    public void setIdCampanhaRetrabalhar(Long idCampanhaRetrabalhar) {
        this.idCampanhaRetrabalhar = idCampanhaRetrabalhar;
    }

    public DualListModel<StatusCampanha> getDualListStatusAtendimentos() {
        return dualListStatusAtendimentos;
    }

    public void setDualListStatusAtendimentos(DualListModel<StatusCampanha> dualListStatusAtendimentos) {
        this.dualListStatusAtendimentos = dualListStatusAtendimentos;
    }


    public boolean isRetrabalharFimFila() {
        return retrabalharFimFila;
    }

    public void setRetrabalharFimFila(boolean retrabalharFimFila) {
        this.retrabalharFimFila = retrabalharFimFila;
    }

    public String getStrNomeCampanha() {
        return strNomeCampanha;
    }

    public void setStrNomeCampanha(String strNomeCampanha) {
        this.strNomeCampanha = strNomeCampanha;
    }

    public List<IntegracaoWs> getListConsultaRefin() {
        return listConsultaRefin;
    }

    public void setListConsultaRefin(List<IntegracaoWs> listConsultaRefin) {
        this.listConsultaRefin = listConsultaRefin;
    }


    public List<ProdutoReturn> getListProdutosRefin() {
        return listProdutosRefin;
    }

    public void setListProdutosRefin(List<ProdutoReturn> listProdutosRefin) {
        this.listProdutosRefin = listProdutosRefin;
    }

    public Integer getCodTabelaRefin() {
        return codTabelaRefin;
    }

    public void setCodTabelaRefin(Integer codTabelaRefin) {
        this.codTabelaRefin = codTabelaRefin;
    }

    /**
     * @return the listFormularios
     */
    public List<Questionario> getListFormularios() {
        return listFormularios;
    }

    /**
     * @param listFormularios the listFormularios to set
     */
    public void setListFormularios(List<Questionario> listFormularios) {
        this.listFormularios = listFormularios;
    }

    /**
     * @return the listFormulariosAssociados
     */
    public List<Questionario> getListFormulariosAssociados() {
        return listFormulariosAssociados;
    }

    /**
     * @param listFormulariosAssociados the listFormulariosAssociados to set
     */
    public void setListFormulariosAssociados(List<Questionario> listFormulariosAssociados) {
        this.listFormulariosAssociados = listFormulariosAssociados;
    }

    /**
     * @return the dualListQuestionario
     */
    public DualListModel<Questionario> getDualListQuestionario() {
        return dualListQuestionario;
    }

    /**
     * @param dualListQuestionario the dualListQuestionario to set
     */
    public void setDualListQuestionario(DualListModel<Questionario> dualListQuestionario) {
        this.dualListQuestionario = dualListQuestionario;
    }

    public List<Long> getListImportacao() {
        return listImportacao;
    }

    public void setListImportacao(List<Long> listImportacao) {
        this.listImportacao = listImportacao;
    }

    public List<IntegracaoWs> getListConsultaBeneficio() {
        return listConsultaBeneficio;
    }

    public void setListConsultaBeneficio(List<IntegracaoWs> listConsultaBeneficio) {
        this.listConsultaBeneficio = listConsultaBeneficio;
    }

    public List<com.proativaservicos.model.trescplus.Campanha> getCampanhas3c() {
        return campanhas3c;
    }

    public Integer getCodCampanha3c() {
        return codCampanha3c;
    }

    public void setCodCampanha3c(Integer codCampanha3c) {
        this.codCampanha3c = codCampanha3c;
    }

    public String getNomeArquivo3c() {
        return nomeArquivo3c;
    }

    public void setNomeArquivo3c(String nomeArquivo3c) {
        this.nomeArquivo3c = nomeArquivo3c;
    }

    public StreamedContent getArquivoMailing3c() {
        System.out.println("BAIXANDO ARQUIVO....");

        return arquivoMailing3c;
    }

    public void setArquivoMailing3c(StreamedContent arquivoMailing3c) {
        this.arquivoMailing3c = arquivoMailing3c;
    }

    public List<RetornoGetSkillsIten> getRetornoGetSkillsItens() {
        return retornoGetSkillsItens;
    }

    public void setRetornoGetSkillsItens(List<RetornoGetSkillsIten> retornoGetSkillsItens) {
        this.retornoGetSkillsItens = retornoGetSkillsItens;
    }

    public void setListConsultaMaster(List<IntegracaoWs> listConsultaMaster) {
        this.listConsultaMaster = listConsultaMaster;
    }

    public List<IntegracaoWs> getListConsultaMaster() {
        return listConsultaMaster;
    }
}

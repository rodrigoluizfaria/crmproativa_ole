package com.proativaservicos.bean;

import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ExtratorImportacao;
import com.proativaservicos.service.ExtracaoImportacaoService;
import com.proativaservicos.service.asynchronous.AtendimentosExtrator;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.ImportacaoArquivoUtil;
import com.proativaservicos.util.constantes.DadosBaseImportacaoExtratorEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.StatusImportacaoExtratorEnum;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Components;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Named
@ViewScoped
public class ExtratorBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private UploadedFile file;

    private StreamedContent arquivoExtrator;

    private List<String> listCabecalho;

    private TreeMap<Integer, DadosBaseImportacaoExtratorEnum> mapImportacaoIndiceCabecalho;

    private TreeMap<DadosBaseImportacaoExtratorEnum, SelectOneMenu> mapsImportacao;

    private int totalAdesao;

    private List<ExtratorImportacao> listExtracaoCompleta;

    private List<ExtratorImportacao> listExtracaoImportacaoIncompleta;

    private String nome;

    private ExtratorImportacao importacao;

    private InputStream inpuStream;

    private List<String> listLinhasFile;

    @Inject
    private AtendimentosExtrator extratorUtil;

    @Inject
    private ExtracaoImportacaoService serviceExtracao;

    private DataTable dataTablePersonalizada;

    private ListDataModel<String[]> dataModel;

    private List<SelectItem> listaSelectItens;

    private DadosBaseImportacaoExtratorEnum dadosDabseImportacaoEnum;

    private int indice;

    private byte[] fileByte;

    private String logImportacao;

    private String nomeColunaStatus;

    private Integer colunaIndiceCancelamento;

    private int qtidadeLinhas;

    private int qtidadeColunas;

    private List<String> listaCabecalhoExtrator = DadosBaseImportacaoExtratorEnum.getColunasExtrator();

    private boolean validarStatus;

    private String logIndiceStatus;

    private  int indiceMapDadosBase;


    @PostConstruct
    public void init() {

        inicializarVariaveis();
        preencherList();

    }

    private void inicializarVariaveis() {

        this.listExtracaoCompleta = new ArrayList<ExtratorImportacao>();
        this.listExtracaoImportacaoIncompleta = new ArrayList<ExtratorImportacao>();
        this.indice = 0;
        this.importacao = new ExtratorImportacao();
        this.listLinhasFile = new ArrayList<String>();
        this.mapImportacaoIndiceCabecalho = new TreeMap<Integer, DadosBaseImportacaoExtratorEnum>();
        this.mapsImportacao = new TreeMap<DadosBaseImportacaoExtratorEnum, SelectOneMenu>();
        this.nomeColunaStatus = "";
        this.colunaIndiceCancelamento = null;

    }

    private void preencherList() {

        pesquisar();
    }


    public void onCheckColunaCabecalho() {

        for (int i = 0; this.listCabecalho.size() < i; i++) {

            if (this.listCabecalho.get(i).trim().equalsIgnoreCase(this.nomeColunaStatus.trim())) {

                this.colunaIndiceCancelamento = Integer.valueOf(i);
                break;
            }

        }

    }


    public void pesquisar() {

        this.listExtracaoCompleta = serviceExtracao.pesquisarExtracaoImportacao(1L, StatusImportacaoExtratorEnum.FINALIZADA);

        this.listExtracaoImportacaoIncompleta = serviceExtracao.pesquisarExtracaoImportacaoIncompleta(1L);

        this.listExtracaoCompleta.addAll(0, listExtracaoImportacaoIncompleta);

    }

    public void handleFileUpload(FileUploadEvent event) {

        Scanner scanner = null;


        try {

            this.file = event.getFile();
            this.inpuStream = this.file.getInputStream();
            this.fileByte = IOUtils.toByteArray(this.inpuStream);
            this.nomeColunaStatus = "";
            this.file = event.getFile();
            this.qtidadeLinhas = 0;
            this.qtidadeColunas = 0;
            this.inpuStream = this.file.getInputStream();
            this.importacao = new ExtratorImportacao();
            this.colunaIndiceCancelamento = null;

            String descricao = DateUtil.builder().formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + "_" + event.getFile().getFileName().replaceAll(".csv", "").replaceAll(".CSV", "");

            importacao.setDescricao(descricao);

            scanner = new Scanner(file.getInputStream(), "ISO-8859-1");

            scanner.useDelimiter(";");

            this.listCabecalho = Arrays.asList(scanner.nextLine().split(";"));

            TreeMap<Integer, DadosBaseImportacaoExtratorEnum> mapTemp = new TreeMap<Integer, DadosBaseImportacaoExtratorEnum>();

            for (int i = 0; i < this.listCabecalho.size(); i++) {

                DadosBaseImportacaoExtratorEnum tmp = DadosBaseImportacaoExtratorEnum.getBaseImportacaoEnum(this.listCabecalho.get(i));

                if (this.listCabecalho.get(i).equalsIgnoreCase("status") || this.listCabecalho.get(i).trim().equalsIgnoreCase("status"))
                    this.nomeColunaStatus = listCabecalho.get(i);

                if (tmp != null)
                    mapTemp.put(Integer.valueOf(i), tmp);


            }

            if (CollectionUtils.isNotEmpty(this.listCabecalho)) {

                this.qtidadeColunas = this.listCabecalho.size();
                this.importacao.setHeader(new Gson().toJson(mapTemp));

            }

            while (scanner.hasNext()) {

                String linha = scanner.nextLine();

                if (linha != null && !linha.trim().isEmpty()) {

                    this.qtidadeLinhas++;
                    this.listLinhasFile.add(linha.replaceAll("\"", ""));

                }

            }

            this.importacao.setNomeArquivo(file.getFileName());
            this.importacao.setArquivo(file.getContent());

            if (StringUtils.isBlank(this.nomeColunaStatus)) {
                this.logImportacao = "Não foi identificada a coluna \"Status\".";
            }


        } catch (IOException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object());

        } finally {
            scanner.close();

        }

    }

    public void processarArquivo() {

        try {

            if (!this.validarStatus && !validarCabecalho()) {

                Messages.addFlashGlobalWarn("\"Adesão\" e ou \"Resp. Corban\" não foi informada. verifique o arquivo.", new Object());


            } else if (this.inpuStream != null) {

                JSONObject json = new JSONObject(this.mapImportacaoIndiceCabecalho);

                this.importacao.setQtidadeLinhas(this.qtidadeLinhas);
                this.importacao.setHeader(json.toString());

                this.importacao.setQtidadeLinhas(this.qtidadeLinhas);
                this.importacao.setNomeArquivo(file.getFileName());
                this.importacao.setDataInicio(new Date());
                this.importacao.setUsuario(retornarUsuarioSessao());
                this.importacao.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                this.importacao.setStatusImportacao(StatusImportacaoExtratorEnum.IMPORTANDO);
                this.importacao.setMapCabecalhoImportacao(this.mapImportacaoIndiceCabecalho);
                this.importacao.setIndiceColunaCancelamento(this.colunaIndiceCancelamento);

                this.inserir((Serializable) this.importacao);

                this.extratorUtil.importarArquivoExtrator(this.importacao);

                Messages.addGlobalInfo("Importação iniciada com sucesso.", new Object());
                this.importacao = new ExtratorImportacao();
                this.nome = "";
                this.totalAdesao = 0;
                this.qtidadeLinhas = 0;
                this.qtidadeColunas = 0;

            } else {
                Messages.addFlashGlobalWarn("Nenhum arquivo informado.", new Object());

            }


        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object());
        }

    }

    public void iniciarImportacao() {


        if (this.inpuStream != null && !this.listCabecalho.isEmpty()) {

            //criarDataModel();
            //criarTabela();
            criarSelectItens();

            this.mapsImportacao = new TreeMap<DadosBaseImportacaoExtratorEnum, SelectOneMenu>();

            int j = 0;

            for (DadosBaseImportacaoExtratorEnum dadosImportacao : DadosBaseImportacaoExtratorEnum.values()) {

                DadosBaseImportacaoExtratorEnum tiposDadosImportacao = DadosBaseImportacaoExtratorEnum.INFORMACOES_COMPLEMENTARES;

                for (int i = 0; i < this.listCabecalho.size(); i++) {

                    tiposDadosImportacao = DadosBaseImportacaoExtratorEnum.getBaseImportacaoEnum(this.listCabecalho.get(i));

                    if (tiposDadosImportacao != null && tiposDadosImportacao.equals(dadosImportacao)) {

                        this.mapImportacaoIndiceCabecalho.put(Integer.valueOf(i), tiposDadosImportacao);
                    }

                }

                if (tiposDadosImportacao == null)
                    tiposDadosImportacao = DadosBaseImportacaoExtratorEnum.INFORMACOES_COMPLEMENTARES;

                this.mapsImportacao.put(tiposDadosImportacao, criarSelectOneMenu(tiposDadosImportacao, j));

                //this.dataTablePersonalizada.getColumns().add(criarColuna(this.listCabecalho.get(i), tiposDadosImportacao, i));

            }

            validarItemStatuExtrator();
            PrimeFaces.current().executeScript("PF('dlgLayout').show();");

        } else {

            Messages.addFlashGlobalWarn("Nenhum arquivo foi informado.", new Object());

        }

    }

    public boolean validarCabecalho() {

        boolean ade = false;
        boolean corban = false;

        for (Integer key : this.mapImportacaoIndiceCabecalho.keySet()) {

            if (mapImportacaoIndiceCabecalho.get(key) != null && mapImportacaoIndiceCabecalho.get(key).equals(DadosBaseImportacaoExtratorEnum.ADE)) {
                ade = true;
                continue;
            }

            if ((mapImportacaoIndiceCabecalho.get(key) != null && mapImportacaoIndiceCabecalho.get(key).equals(DadosBaseImportacaoExtratorEnum.RESP_CORBAN)) || (mapImportacaoIndiceCabecalho.get(key) != null && mapImportacaoIndiceCabecalho.get(key).equals(DadosBaseImportacaoExtratorEnum.COD_CONSISTENCIA))) {
                corban = true;
                continue;
            }

            if (corban && ade) {
                return true;
            }

        }

        if (validarStatus && ade) {
            return true;
        }


        if (!this.validarStatus && corban && ade) {
            return true;
        }


        /*
         * if(this.listCabecalho.stream().anyMatch(f ->
         * f.equalsIgnoreCase("Resp. Corban")) && this.listCabecalho.stream().anyMatch(f
         * -> f.equalsIgnoreCase("ADE") || f.equalsIgnoreCase("ADESÂO"))){ return true;
         * }
         */

        return false;


    }

    public void preSalvar() {


        if (this.inpuStream != null && this.fileByte.length > 0L) {

            lerImportacao();

        } else {


        }

    }


    private void lerImportacao() {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.fileByte), StandardCharsets.ISO_8859_1));

            this.listCabecalho = Arrays.asList(reader.readLine().split("[;]"));

            this.mapImportacaoIndiceCabecalho = new TreeMap<>();

            if (this.listCabecalho.size() > 100) {

                throw new ProativaException(" Número Máximo Colunas Permitido foi ultrapassado...");
            }

            criarDataModel();
            criarTabela();
            criarSelectItens();

            for (int i = 0; i < this.listCabecalho.size(); i++) {

                DadosBaseImportacaoExtratorEnum tiposDadosImportacao = DadosBaseImportacaoExtratorEnum.getBaseImportacaoEnum(listCabecalho.get(i).trim());

                mapImportacaoIndiceCabecalho.put(Integer.valueOf(i), tiposDadosImportacao);

                this.dataTablePersonalizada.getChildren().add(criarColuna(listCabecalho.get(i), tiposDadosImportacao, i));

            }
            indiceMapDadosBase =0;

            validarItemStatuExtrator();
            PrimeFaces.current().executeScript("PF('dlgLayout').show();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        } finally {

            try {

                if (reader != null)
                    reader.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public void salvarImportacao() {


        if ((this.importacao.getAtualizarPropostasExistente() || this.importacao.getIntegrarPropostas()) && !validarCabecalho()) {

            Messages.addFlashGlobalWarn("Adesão não foi informada. Não é possivel atualizar ou integrar proposta", new Object());

        } else if (mapImportacaoIndiceCabecalho != null && !mapImportacaoIndiceCabecalho.isEmpty()   && !listLinhasFile.isEmpty()) {

            JSONObject json = new JSONObject(this.mapImportacaoIndiceCabecalho);

            this.importacao.setHeader(json.toString());
            this.importacao.setDataInicio(new Date());

            this.importacao.setQtidadeLinhas(this.qtidadeLinhas);
            this.importacao.setNomeArquivo(file.getFileName());
            this.importacao.setDataInicio(new Date());
            this.importacao.setUsuario(retornarUsuarioSessao());
            this.importacao.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
            this.importacao.setStatusImportacao(StatusImportacaoExtratorEnum.IMPORTANDO);
            this.importacao.setMapCabecalhoImportacao(this.mapImportacaoIndiceCabecalho);

            this.serviceExtracao.salvar(this.importacao);
            //this.importacaoUtil.sendImportacao(null, null, mapImportacaoIndiceCabecalho, null);

        } else {

            Messages.addFlashGlobalWarn("Nenhuma coluna foi selecionada", new Object());
        }

    }

    private Column criarColuna(String cabecalho, DadosBaseImportacaoExtratorEnum tipoDadosImportacao, int indice) {

        Column coluna = new Column();
        coluna.setHeaderText(cabecalho);
        coluna.setStyle("width:200px;");
        coluna.getChildren().add(criarSelectOneMenu(tipoDadosImportacao, indice));

        return coluna;
    }

    private void criarDataModel() {

        List<String[]> data = new ArrayList<>();
        data.add(new String[0]);
        this.dataModel = new ListDataModel<String[]>(data);
    }

    private void criarSelectItens() {

        this.listaSelectItens = new ArrayList<>();

        SelectItem selectItem = new SelectItem();
        selectItem.setValue(null);
        selectItem.setLabel("Selecione");
        this.listaSelectItens.add(selectItem);

        Map<String, List<SelectItem>> listaSelectItemAgrupados = new TreeMap<>();

        for (DadosBaseImportacaoExtratorEnum tipoDadosImportacao : DadosBaseImportacaoExtratorEnum.values()) {

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

            selectItemGroup.setSelectItems((SelectItem[]) ((List<?>) listaSelectItemAgrupados.get(grupo)).toArray((Object[]) new SelectItem[((List<?>) listaSelectItemAgrupados.get(grupo)).size()]));

            this.listaSelectItens.add(selectItemGroup);

        }

    }


    private SelectOneMenu criarSelectOneMenu(DadosBaseImportacaoExtratorEnum tipoDadosImportacao, int indice) {

        SelectOneMenu selectOneMenu = new SelectOneMenu();

        selectOneMenu.setId("selectImportacao" + indice);
        selectOneMenu.setDisabled(false);
        selectOneMenu.setRendered(true);
        selectOneMenu.setRequired(false);
        // selectOneMenu.setAutoWidth(false);
        selectOneMenu.setValue(tipoDadosImportacao);

        selectOneMenu.setValueExpression("value", Components.createValueExpression("#{extratorBean.dadosDabseImportacaoEnum}", DadosBaseImportacaoExtratorEnum.class));

        selectOneMenu.setOnchange("atualizarCombo(" + indice + ");");

        selectOneMenu.setStyle("height:100% !important;width: 100% !important;");

        UISelectItems selectItens = new UISelectItems();

        selectItens.setValue(this.listaSelectItens);

        selectOneMenu.getChildren().add(selectItens);

        return selectOneMenu;

    }

    private void criarTabela() {

        this.dataTablePersonalizada = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formTable:tableImportacao");

        this.dataTablePersonalizada.getChildren().clear();
        this.dataTablePersonalizada.setVar("item");
    }

    public void atualizarComboImportacaoPersonalizada() {

        Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int valor = Integer.valueOf(requestParamMap.get("indice"));

        this.mapImportacaoIndiceCabecalho.put(valor, DadosBaseImportacaoExtratorEnum.getBaseImportacaoEnum(requestParamMap.get("tidoEnum")));

        validarItemStatuExtrator();


    }

    private void validarItemStatuExtrator() {

        if (this.mapImportacaoIndiceCabecalho != null) {
            this.logIndiceStatus = this.mapImportacaoIndiceCabecalho.containsValue(DadosBaseImportacaoExtratorEnum.STATUS) ? "" : "A coluna status não foi informada.";

        }

    }

    @SuppressWarnings("deprecation")
    public void onUploadArquivo(ExtratorImportacao extrator) {

        try {

            File file = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + "extrator" + File.separator + extrator.getNomeArquivo());

            if (!file.exists() && !file.isFile())
                throw new ProativaException("Arquivo não encontrado.");

            try {

                new DefaultStreamedContent();

                this.arquivoExtrator = DefaultStreamedContent.builder().name(extrator.getNomeArquivo() + ".csv").stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(file.getAbsolutePath())).build();

            } catch (Exception e) {

                e.printStackTrace();
                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

            }

        } catch (ProativaException e) {
            Messages.addGlobalWarn(e.getMessage());
        }

    }

    /*
     * @SuppressWarnings("deprecation") public void
     * onUploadArquivo(ExtratorImportacao extrator) {
     *
     * try { if(extrator.getArquivo()==null) throw new
     * ProativaException("Nenhum arquivo encontrado.");
     *
     * byte[] arquivo = null; ByteArrayInputStream byteArrayInputStream = null;
     *
     * try {
     *
     * arquivo = extrator.getArquivo();
     *
     * byteArrayInputStream = new ByteArrayInputStream(arquivo);
     *
     * this.arquivoExtrator = (StreamedContent) new
     * DefaultStreamedContent(byteArrayInputStream,
     * "text/csv",extrator.getNomeArquivo() + ".csv"); // } catch (Exception e) {
     *
     * e.printStackTrace();
     * Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new
     * Object[0]);
     *
     * } finally {
     *
     * arquivo = null;
     *
     * if (byteArrayInputStream != null) {
     *
     * try {
     *
     * byteArrayInputStream.close(); byteArrayInputStream = null;
     *
     * } catch (IOException e) {
     *
     * e.printStackTrace();
     *
     * } } } }catch (ProativaException e) { Messages.addGlobalWarn(e.getMessage(),
     * new Object[0]); }
     *
     * }
     *
     */
    public String retornarStatusCss(StatusImportacaoExtratorEnum status) {


        switch (status) {

            case FINALIZADA:

                return "status-instock";

            case FINALIZADA_ERRO:
                return "status-outofstock";

            case IMPORTANDO:
                return "status-lowstock";

            case PROCESSANDO:
                return "status-lowstock";

            default:
                return "";

        }

    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Map<Integer, DadosBaseImportacaoExtratorEnum> getMapImportacaoIndiceCabecalho() {
        return mapImportacaoIndiceCabecalho;
    }

    public void setMapImportacaoIndiceCabecalho(TreeMap<Integer, DadosBaseImportacaoExtratorEnum> mapImportacaoIndiceCabecalho) {
        this.mapImportacaoIndiceCabecalho = mapImportacaoIndiceCabecalho;
    }

    public int getTotalAdesao() {
        return totalAdesao;
    }

    public void setTotalAdesao(int totalAdesao) {
        this.totalAdesao = totalAdesao;
    }

    public List<ExtratorImportacao> getListExtracaoCompleta() {
        return listExtracaoCompleta;
    }

    public void setListExtracaoCompleta(List<ExtratorImportacao> listExtracaoCompleta) {
        this.listExtracaoCompleta = listExtracaoCompleta;
    }

    public List<ExtratorImportacao> getListExtracaoImportacaoIncompleta() {
        return listExtracaoImportacaoIncompleta;
    }

    public void setListExtracaoImportacaoIncompleta(List<ExtratorImportacao> listExtracaoImportacaoIncompleta) {
        this.listExtracaoImportacaoIncompleta = listExtracaoImportacaoIncompleta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIndice() {
        return indice;
    }

    public DadosBaseImportacaoExtratorEnum getDadosDabseImportacaoEnum() {

        return mapImportacaoIndiceCabecalho.get(this.indiceMapDadosBase++);

       // return dadosDabseImportacaoEnum;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setDadosDabseImportacaoEnum(DadosBaseImportacaoExtratorEnum dadosDabseImportacaoEnum) {
        this.dadosDabseImportacaoEnum = dadosDabseImportacaoEnum;
    }

    public DataTable getDataTablePersonalizada() {
        return dataTablePersonalizada;
    }

    public void setDataTablePersonalizada(DataTable dataTablePersonalizada) {
        this.dataTablePersonalizada = dataTablePersonalizada;
    }

    public ListDataModel<String[]> getDataModel() {
        return dataModel;
    }

    public void setDataModel(ListDataModel<String[]> dataModel) {
        this.dataModel = dataModel;
    }

    public int getQtidadeLinhas() {
        return qtidadeLinhas;
    }

    public void setQtidadeLinhas(int qtidadeLinhas) {
        this.qtidadeLinhas = qtidadeLinhas;
    }

    public ExtratorImportacao getImportacao() {
        return importacao;
    }

    public void setImportacao(ExtratorImportacao importacao) {
        this.importacao = importacao;
    }

    public List<String> getListCabecalho() {
        return listCabecalho;
    }

    public void setListCabecalho(List<String> listCabecalho) {
        this.listCabecalho = listCabecalho;
    }

    public int getQtidadeColunas() {
        return qtidadeColunas;
    }

    public TreeMap<DadosBaseImportacaoExtratorEnum, SelectOneMenu> getMapsImportacao() {
        return mapsImportacao;
    }

    public List<String> getListaCabecalhoExtrator() {
        return listaCabecalhoExtrator;
    }

    public StreamedContent getArquivoExtrator() {
        return arquivoExtrator;
    }

    public void setArquivoExtrator(StreamedContent arquivoExtrator) {
        this.arquivoExtrator = arquivoExtrator;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    /**
     * @return the logImportacao
     */
    public String getLogImportacao() {
        return logImportacao;
    }

    /**
     * @param logImportacao the logImportacao to set
     */
    public void setLogImportacao(String logImportacao) {
        this.logImportacao = logImportacao;
    }

    /**
     * @return the nomeColunaStatus
     */
    public String getNomeColunaStatus() {
        return nomeColunaStatus;
    }

    /**
     * @param nomeColunaStatus the nomeColunaStatus to set
     */
    public void setNomeColunaStatus(String nomeColunaStatus) {
        this.nomeColunaStatus = nomeColunaStatus;
    }

    /**
     * @param qtidadeColunas the qtidadeColunas to set
     */
    public void setQtidadeColunas(int qtidadeColunas) {
        this.qtidadeColunas = qtidadeColunas;
    }

    public boolean isValidarStatus() {
        return validarStatus;
    }

    public void setValidarStatus(boolean validarStatus) {
        this.validarStatus = validarStatus;
    }

    public String getLogIndiceStatus() {
        return logIndiceStatus;
    }

    public void setLogIndiceStatus(String logIndiceStatus) {
        this.logIndiceStatus = logIndiceStatus;
    }
}

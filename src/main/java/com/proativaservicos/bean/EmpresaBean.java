package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.service.asynchronous.InsercaoMassaAsyncrona;
import com.proativaservicos.service.asynchronous.WebSocket3c;
import com.proativaservicos.util.CriptografiaUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.model.SelectItemGroup;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.*;
import java.util.*;

@Named
@ViewScoped
public class EmpresaBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Empresa empresa;

    @Inject
    private Loja loja;

    @Inject
    private ConciliarSftp conciliar;

    @Inject
    private EmpresaService serviceEmpresa;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private InsercaoMassaAsyncrona insercaoMassAsycrona;

    @Inject
    private BlackListTelefoneService serviceBlackListTelefones;

    @Inject
    private BlackListService serviceBlackListCpf;

    private TipoPaginaEnum tipoPagina;

    private InstituicaoFinanceiraEnum instituicao;

    private List<Empresa> listEmpresa;

    private List<SelectItem> listLayouts;

    private Integer indiceLoja;

    private Integer indiceSftp;

    private LayoutEnum layout;

    private String telefone;

    private String cpf;

    private Set<String> listBlackListTelefones;

    private Set<String> listBlackListCpfs;

    private StreamedContent file;


    /**
     * BEAN EMPRESA pagina empresa.jsf
     */

    @PostConstruct
    public void init() {

        this.empresa = retornarEmpresaUsuarioSessao();
        this.empresa.setNome("");
        this.empresa.setCnpj("");
        buscarEmpresa();
        tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void inicializarVariaveis() {

        this.conciliar = new ConciliarSftp();
        this.loja = new Loja();
        this.indiceLoja = null;
        this.indiceSftp = null;

        if (empresa.getListLoja() == null) {
            empresa.setListLoja(new ArrayList<>());
        }

        if (empresa.getSftps() == null) {
            empresa.setSftps(new ArrayList<>());

        }

        SelectItemGroup groupEspecial = new SelectItemGroup("Especial");
        SelectItemGroup groupCor = new SelectItemGroup("Cor");

        groupCor.setSelectItems(LayoutEnum.retornarSelectItensl("cor"));
        groupEspecial.setSelectItems(LayoutEnum.retornarSelectItensl("especial"));

        this.listLayouts = new ArrayList<SelectItem>();
        listLayouts.add(groupEspecial);
        listLayouts.add(groupCor);

    }

    public void salvar() {

        try {

            String msg = "";

            if (empresa.getId() == null) {

                this.empresa.setLicencas(CriptografiaUtil.criptografarSimples("0"));
                this.empresa.setMatriz(retornarEmpresaMatrizUsuarioSessao());

                inserirGenerico(empresa);

                msg = "Empresa salva com sucesso!";

            } else {

                alterar(empresa);
                msg = "Empresa alterada com sucesso!";

            }

            Messages.addGlobalInfo(msg, new Object[0]);

            inicializarVariaveis();

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            e.printStackTrace();
        }

    }

    public void buscarEmpresa() {

        try {

            listEmpresa = serviceEmpresa.persquisarCriteria(empresa);

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addFatal(null, "Erro Ao buscar", "");
        }

    }

    public void validateNomeEmpresa(FacesContext context, UIComponent component, Object value) throws ValidatorException {

    }

    public void limpar() {

        empresa = new Empresa();

    }

    public void novaEmpresa() {
        this.empresa = new Empresa();
        this.empresa.setParametroAdesaoCampoObrigatorio(Boolean.TRUE);

        this.empresa.setLicencas(CriptografiaUtil.criptografarSimples("0"));
        // this.empresa.setMatriz(retornarEmpresaUsuarioSessao());
        inicializarVariaveis();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(Empresa emp) {

        emp = this.empresa;

        this.empresa.setListLoja(serviceLoja.pesquisarLojas(empresa.getId()));


        inicializarVariaveis();

        tipoPagina = TipoPaginaEnum.CADASTRO;
    }

    public void voltar() {

        init();
    }

    public void adicionarFtp() {

        try {

            if (this.indiceSftp == null) {

                atribuirUsuarioDataEntidade(this.conciliar, true);
                this.conciliar.setAtivo(TipoAcessoEnum.ATIVO);
                this.empresa.getSftps().add(this.conciliar);
                Messages.addGlobalInfo(MessagesEnum.SFTP_ADICIONADO_COM_SUCESSO.constante, new Object[0]);

            } else {

                atribuirUsuarioDataEntidade(this.conciliar, false);

                this.empresa.getSftps().set(this.indiceSftp, this.conciliar);
                Messages.addGlobalInfo(MessagesEnum.SFTP_ATLERADO_COM_SUCESSO.constante, new Object[0]);
            }

            this.conciliar = new ConciliarSftp();
            this.indiceSftp = null;

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void removerFtp(int index) {

        this.empresa.getSftps().remove(index);
        this.indiceSftp = null;

    }


    public void editarFtp(ConciliarSftp conciliar, Integer indice) {

        this.conciliar = conciliar;
        this.indiceSftp = indice;

    }

    public void onAdiocionderSftp() {

        if (this.conciliar != null && this.conciliar.getTipoLoginSftp() == null)
            this.conciliar.setTipoLoginSftp(TipoLoginSftpEnum.NORMAL);

    }

    public void adicionarLoja() {

        if (this.indiceLoja == null) {

            this.empresa.getListLoja().add(this.loja);

            Messages.addGlobalInfo(MessagesEnum.LOJA_ADICIONADA_COM_SUCESSO.constante, new Object[0]);

        } else {

            this.empresa.getListLoja().set(this.indiceLoja.intValue(), this.loja);

            Messages.addGlobalInfo(MessagesEnum.LOJA_ATUALIZADA_COM_SUCESSO.constante, new Object[0]);

        }

        this.loja = new Loja();
        this.indiceLoja = null;

    }

    public void removerLoja(int index) {

        this.empresa.getListLoja().remove(index);
        this.indiceLoja = null;

    }

    public void editarLoja(Loja loja, Integer index) {

        this.loja = loja;
        this.indiceLoja = index;

    }

    public String retornarQtidadeLicenca(String strLicencao) {

        if (strLicencao == null || strLicencao.trim().isEmpty()) {

            return MessagesEnum.LICENCA_FULL.constante;

        }

        return CriptografiaUtil.descriptografarSimples(strLicencao);

    }

    @SuppressWarnings("unused")
    private void inicializarMetas() {

        if (this.empresa.getMeta() == null) {

            this.empresa.setMeta(new Meta());

        }

        this.empresa.getMeta().setEmpresaDiariaRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEmpresaDiariaConcluidaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEmpresaSemanalRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEmpresaSemanalConcluidaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEmpresaMensalRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEmpresaMensalConcluidaValor(Double.valueOf(0.0D));

        this.empresa.getMeta().setEmpresaDiariaRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEmpresaDiariaConcluidaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEmpresaSemanalRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEmpresaSemanalConcluidaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEmpresaMensalRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEmpresaMensalConcluidaQuantidade(Integer.valueOf(0));

        this.empresa.getMeta().setOperadorDiariaRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setOperadorDiariaConcluidaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setOperadorSemanalRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setOperadorSemanalConcluidaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setOperadorMensalRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setOperadorMensalConcluidaValor(Double.valueOf(0.0D));

        this.empresa.getMeta().setOperadorDiariaRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setOperadorDiariaConcluidaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setOperadorSemanalRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setOperadorSemanalConcluidaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setOperadorMensalRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setOperadorMensalConcluidaQuantidade(Integer.valueOf(0));

        this.empresa.getMeta().setEquipeDiariaRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEquipeDiariaConcluidaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEquipeSemanalRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEquipeSemanalConcluidaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEquipeMensalRealizadaValor(Double.valueOf(0.0D));
        this.empresa.getMeta().setEquipeMensalConcluidaValor(Double.valueOf(0.0D));

        this.empresa.getMeta().setEquipeDiariaRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEquipeDiariaConcluidaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEquipeSemanalRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEquipeSemanalConcluidaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEquipeMensalRealizadaQuantidade(Integer.valueOf(0));
        this.empresa.getMeta().setEquipeMensalConcluidaQuantidade(Integer.valueOf(0));

    }

    public void enviarListaNegraCpf(FileUploadEvent event) {

        try {


            File arquivo = new File(System.getProperty("jboss.server.temp.dir") + File.separator + event.getFile().getFileName());
            FileUtils.writeByteArrayToFile(arquivo, event.getFile().getContent());

            Set<String> cpfs = new TreeSet<String>();
            List<String> listCpfs = FileUtils.readLines(arquivo, "UTF-8");

            for (String string : listCpfs) {

                if (StringUtils.isBlank(string) || !StringUtils.isNumeric(string.replaceAll("\\D+", "").trim())) {

                    throw new ProativaException(string + " - Não é um CPF valido");
                }

                cpfs.add(string.replaceAll("\\+D", "").trim());


            }

            this.insercaoMassAsycrona.inserirCpfsListaNegra(new ArrayList<String>(cpfs), retornarEmpresaMatrizUsuarioSessao(), retornarUsuarioSessao());

            Messages.addGlobalInfo("CPF's inseridos com sucesso.", new Object[0]);

        } catch (ProativaException e1) {

            Messages.addGlobalError(e1.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }


    }

    public void enviarListaNegraTelefones(FileUploadEvent event) {

        try {


            File arquivo = new File(System.getProperty("jboss.server.temp.dir") + File.separator + event.getFile().getFileName());
            FileUtils.writeByteArrayToFile(arquivo, event.getFile().getContent());

            Set<String> telefones = new TreeSet<String>();
            List<String> listTelefones = FileUtils.readLines(arquivo, "UTF-8");

            for (String string : listTelefones) {

                if (StringUtils.isBlank(string) || !StringUtils.isNumeric(string.replaceAll("\\D+", "").trim())) {

                    throw new ProativaException(string + " - Não é um telefone valido");
                }

                telefones.add(string.replaceAll("\\+D", "").trim());


            }

            this.insercaoMassAsycrona.inserirTelefonesListaNegra(new ArrayList<String>(telefones), retornarEmpresaMatrizUsuarioSessao());

            Messages.addGlobalInfo("telefones inseridos com sucesso.", new Object[0]);

        } catch (ProativaException e1) {

            Messages.addGlobalError(e1.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public void enviarArquivoChave(FileUploadEvent event) {

        try {

            File dir = criarDiretorios("chave_sftp");

            File arquivo = new File(dir, event.getFile().getFileName());

            FileUtils.writeByteArrayToFile(arquivo, event.getFile().getContent());

            if (!arquivo.exists())
                throw new ProativaException("Não foi possivel salvar o arquivo.");

            conciliar.setArquivoChave(arquivo.getAbsolutePath());

            Messages.addGlobalInfo("Arquivo salvo com sucesso.");

        } catch (ProativaException e1) {

            Messages.addGlobalError(e1.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }

    public void inserirCpfBlackList() {
        try {

            if (this.listBlackListCpfs != null && StringUtils.isNotBlank(this.cpf) && !this.listBlackListCpfs.contains(this.cpf)) {

                this.listBlackListCpfs.add(this.cpf);

                this.serviceBlackListCpf.inserir(new BlackListCpf(StringUtils.leftPad(this.cpf.trim(), 11, "0"),
                        retornarEmpresaMatrizUsuarioSessao(), this.retornarUsuarioSessao(), new Date()));

            }
            this.cpf = null;

        } catch (ProativaException e1) {

            Messages.addGlobalError(e1.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void inserirTelefoneBlackList() {

        try {

            if (StringUtils.isNotBlank(this.telefone) && this.listBlackListTelefones.add(telefone)) {

                this.serviceBlackListTelefones
                        .inserir(new TelefoneBlackList(this.telefone.trim(), retornarEmpresaMatrizUsuarioSessao()));

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void removerTelefoneBlackList(int index) {

        try {

            if (this.telefone != null) {

                this.serviceBlackListTelefones.excluirTelefoneBlackList(this.telefone,
                        retornarEmpresaMatrizUsuarioSessao().getId());
                this.listBlackListTelefones.remove(this.telefone);

            }

            this.telefone = null;

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void removerCpfBlackList(int index) {
        try {

            if (this.cpf != null) {

                BlackListCpf blackListCpf = this.serviceBlackListCpf.pesquisarListaNegra(this.cpf, retornarEmpresaMatrizUsuarioSessao().getId());

                if (blackListCpf != null) {
                    this.serviceBlackListCpf.excluir(BlackListCpf.class, blackListCpf.getId());
                    this.listBlackListCpfs.remove(this.cpf);
                }
            }
            this.cpf = null;
        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public void onChangeTab(TabChangeEvent event) {

        if (event.getTab().getId().equals("tabBloqueiTelefone") && CollectionUtils.isEmpty(this.listBlackListTelefones)) {

            this.listBlackListTelefones = this.serviceBlackListTelefones.pesquisarTelefonesBlackListManual(retornarEmpresaMatrizUsuarioSessao().getId());

        } else if (event.getTab().getId().equals("tabBloqueiCpf") && CollectionUtils.isEmpty(this.getListBlackListCpfs())) {

            this.listBlackListCpfs = this.serviceBlackListCpf.pesquisarListaNegra(retornarEmpresaMatrizUsuarioSessao().getId());

        }

    }

    public void onDownloadArquivoChave(String arquivoChave) {

        try {

            if (StringUtils.isNotBlank(arquivoChave)) {

                File arquivo = new File(arquivoChave);

                if (arquivo.exists()) {

                    InputStream stream = new FileInputStream(arquivo);

                    file = DefaultStreamedContent.builder()
                            .name(arquivo.getName())
                            .contentType("application/octet-stream")
                            .stream(() -> stream)
                            .build();

                } else {
                    Messages.addGlobalError("Arquivo não encontrado.");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }
    }

    public Empresa getEmpresa() {

        return empresa;

    }

    public void setEmpresa(Empresa empresaBean) {

        this.empresa = empresaBean;

    }

    public List<Empresa> getListEmpresa() {

        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {

        this.listEmpresa = listEmpresa;
    }

    @SuppressWarnings("static-access")
    public InstituicaoFinanceiraEnum[] getInstituicao() {
        return instituicao.values();
    }

    public void setInstituicao(InstituicaoFinanceiraEnum instituicao) {
        this.instituicao = instituicao;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public ConciliarSftp getConciliar() {
        return conciliar;
    }

    public void setConciliar(ConciliarSftp conciliar) {
        this.conciliar = conciliar;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public LayoutEnum getLayout() {
        return layout;
    }

    public void setLayout(LayoutEnum layout) {
        this.layout = layout;
    }

    public TemaEnum[] getTema() {
        return TemaEnum.values();
    }

    public MenuEstiloEnum[] getMenuEstiloEnum() {
        return MenuEstiloEnum.values();
    }

    public ColorSchemaEnum[] getColorSchemaEnum() {
        return ColorSchemaEnum.values();
    }

    public ComponentThemeEnum[] getComponentThemeEnum() {
        return ComponentThemeEnum.values();
    }

    public MenuThemeEnum[] getMenuThemeEnum() {
        return MenuThemeEnum.values();
    }

    public ComponentColorEnum[] getComponentColorEnum() {
        return ComponentColorEnum.values();
    }

    public List<SelectItem> getListLayouts() {
        return listLayouts;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<String> getListBlackListTelefones() {
        return listBlackListTelefones;
    }

    public void setListBlackListTelefones(Set<String> listBlackListTelefones) {
        this.listBlackListTelefones = listBlackListTelefones;
    }

    public void setListLayouts(List<SelectItem> listLayouts) {
        this.listLayouts = listLayouts;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<String> getListBlackListCpfs() {
        return listBlackListCpfs;
    }

    public void setListBlackListCpfs(Set<String> listBlackListCpfs) {
        this.listBlackListCpfs = listBlackListCpfs;
    }

    public TipoLoginSftpEnum[] getTipoLoginSftp() {
        return TipoLoginSftpEnum.values();
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
}

package com.proativaservicos.bean;

import com.proativaservicos.service.BotProService;
import com.proativaservicos.service.asynchronous.ConsultaResultsVonxArquivo;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Faces;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class MisterBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = -7553017778422993143L;

    private String testeStr = "";

    private boolean testBool = false;

    private Integer quantidadeArquivos;

    private Date data;

    @Inject
    private ConsultaResultsVonxArquivo buscaResults;

    @Inject
    private BotProService serviceBot;

    private Long id;

    @PostConstruct
    public void init() {

        Long idT = (Long) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id");

        Optional<Long> op = Optional.ofNullable((Long) Faces.getSession().getAttribute("id_preditivo"));

        System.out.println("INICIANDO::. .PRESENT  " + (op.isPresent() ? op.get() : "Não"));


        System.out.println(FacesContext.getCurrentInstance().isPostback());

        this.id = 12L;

        // buscarQuantidadeArquivosXml();

        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", this.id);
        Faces.getSession().setAttribute("id_preditivo", 12L);
    }

    @PreDestroy
    public void destroy() {

        System.out.println("REMOVENDO...");
        FacesUtil.removendoAtributos("id_preditivo");


    }

    public void initView() {

        if (FacesContext.getCurrentInstance().isPostback()) {
            System.out.println("INICIANDO IS POSTBACK " + this.id);
            return;

        } else {
            System.out.println("INICIANDO NÂO POSTBACK");
            init();
        }
    }

    public void enviarMsg() {

        System.out.println("Enviando....");
        // this.serviceBot.bot(retornarUsuarioSessao().getId());

    }

    public void buscarQuantidadeArquivosXml() {
        File fileServer = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator
                + "retorno_xml" + File.separator + "results_vonix_"
                + DateUtil.builder().formatarDataParaString("yyyyMMdd").getDataTexto());

        File fileServerMove = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator
                + "retorno_xml_move" + File.separator + "results_vonix_"
                + DateUtil.builder().formatarDataParaString("yyyyMMdd").getDataTexto());

        if (!fileServerMove.exists())
            fileServerMove.mkdirs();

        if (!fileServer.exists())
            fileServer.mkdirs();

        List<File> listFiles = retornarArquivosXml(
                System.getProperty("user.home") + File.separator + "proativa" + File.separator + "retorno_xml");
        this.quantidadeArquivos = CollectionUtils.isEmpty(listFiles) ? Integer.valueOf(0)
                : Integer.valueOf(listFiles.size());
    }

    private List<File> retornarArquivosXml(String dir) {

        try {

            List<File> files = Files.walk(Paths.get(dir)).map(Path::toFile).filter(File::isFile)
                    .filter(path -> path.getName().toString().endsWith(".xml")).collect(Collectors.toList());
            return files;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;

    }

    public void iniciarResultsVonix() {

        System.out.println("INICIO RESUTS MANUAL ARQUIVO");
        // this.buscaResults.getFiles();

    }

    public String getTesteStr() {
        return testeStr;
    }

    public void setTesteStr(String testeStr) {
        this.testeStr = testeStr;
    }

    public boolean isTestBool() {
        return testBool;
    }

    public void setTestBool(boolean testBool) {
        this.testBool = testBool;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void testeBean() {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        if (testeStr != null && !testeStr.equals("") && data != null) {

            System.out.println(testeStr + " | " + testBool);
            System.out.println(formato.format(data));
            testBool = true;

        } else {

            System.out.println("NULLLL");
        }

    }

    public void testBean(String nome) {
        System.out.println(nome);

    }

    public void salvar() {

        System.out.println("METODO SALVAR: " + testeStr);
    }

    public void abrePanel() {

    }

    public Integer getQuantidadeArquivos() {
        return quantidadeArquivos;
    }

}

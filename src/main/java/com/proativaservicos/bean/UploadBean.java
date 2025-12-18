package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Upload;
import com.proativaservicos.service.UploadService;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class UploadBean extends GenericBean {

    private static final long serialVersionUID = 1L;


    @Inject
    private UploadService serviceUpload;

    private Date dataInicio;
    private Date dataFim;

    private Upload arquivo;

    private StreamedContent file;

    private List<Upload> listUploads;

    @PostConstruct
    public void init() {

        this.dataInicio = new Date();
        this.dataFim = new Date();
        this.arquivo = new Upload();
        pesquisar();


    }

    public void pesquisar() {

        this.listUploads = this.serviceUpload.pesquisar(this.arquivo, retornarUsuarioSessao(), this.dataInicio, this.dataFim);

    }

    public void salvar() {

        try {

            if (arquivo != null && arquivo.getStream() != null && this.arquivo.getDescricao() != null && (StringUtils.isNotEmpty(this.arquivo.getNomeArquivo()) || StringUtils.isNotEmpty(this.arquivo.getNomeArquivoOriginal()))) {


                this.arquivo.setDataCriacao(new Date());
                this.arquivo.setUsuarioCadastro(retornarUsuarioSessao());
                this.arquivo.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                String diretorio = salvarArquivoMailing(this.arquivo.getStream(), this.arquivo.getNomeArquivo(), "mailing");
                this.arquivo.setDiretorio(diretorio);
                this.serviceUpload.inserir(this.arquivo);

                this.arquivo = new Upload();
                Messages.addGlobalInfo("Arquivo salvo com sucesso.", new Object[0]);


            } else {

                throw new ProativaException("Não foi possivel salvar arquivo, verifique o nome.");
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);
        }

    }

    public void salvar(Upload arquivo) {

        try {

            if (arquivo != null && arquivo.getStream() != null && (StringUtils.isNotEmpty(arquivo.getNomeArquivo()) || StringUtils.isNotEmpty(arquivo.getNomeArquivoOriginal()))) {


                arquivo.setDataCriacao(new Date());
                arquivo.setUsuarioCadastro(retornarUsuarioSessao());
                arquivo.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                String diretorio = salvarArquivoMailing(arquivo.getStream(), arquivo.getNomeArquivo(), "mailing");
                arquivo.setDiretorio(diretorio);
                this.serviceUpload.inserir(arquivo);

                this.arquivo = new Upload();
                Messages.addGlobalInfo("Arquivo salvo com sucesso.", new Object[0]);
                PrimeFaces.current().executeScript("PF('dlgUpload').hide();");
                init();

            } else {

                throw new ProativaException("Não foi possivel salvar arquivo, verifique o nome.");
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);
        }

    }

    public void delete(Long id) {

        try {

            if (id != null) {

                this.serviceUpload.pesquisar(Upload.class, id);
                this.serviceUpload.deletar(id);
                pesquisar();

                Messages.addGlobalInfo("Arquivo removido com sucesso.", new Object[0]);
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        }

    }

    public void handleFileUpload(FileUploadEvent event) {

        try {


            Upload arquivo = new Upload();
            arquivo.setNomeArquivo(event.getFile().getFileName());
            arquivo.setNomeArquivoOriginal(event.getFile().getFileName());
            arquivo.setDescricao(event.getFile().getFileName());
            arquivo.setStream(event.getFile().getInputStream());
            arquivo.setDescricao(event.getFile().getFileName());
            arquivo.setTamanho(String.valueOf(event.getFile().getSize() <= 0 ? "" : event.getFile().getSize() / 1000));
            salvar(arquivo);

        } catch (IOException e) {

            e.printStackTrace();
            Messages.addGlobalError("Erro ao importar arquivo.", new Object[0]);
        }

    }

    /*
     * public void handleFileUpload(FileUploadEvent event) {
     *
     * try {
     *
     * System.out.println(event.getFile().getFileName() + " - " +
     * event.getFile().getContentType() + " - " + event.getFile().getSize());
     * this.arquivo.setNomeArquivo(event.getFile().getFileName());
     * this.arquivo.setNomeArquivoOriginal(this.arquivo.getNomeArquivo());
     * this.arquivo.setStream(event.getFile().getInputStream());
     *
     * this.arquivo.setTamanho(String.valueOf(event.getFile().getSize()
     * <=0?"":event.getFile().getSize()/1000 ) );
     *
     * } catch (IOException e) {
     *
     * e.printStackTrace(); Messages.addGlobalError("Erro ao importar arquivo.", new
     * Object[0]); } }
     */
    public void baixarArquivo(Upload arquivo) {

        if (arquivo != null) {

            try {

                File file = new File(arquivo.getDiretorio() + File.separator + arquivo.getNomeArquivo());

                if (!file.exists())
                    throw new ProativaException("Arquivo não encontrado.");

                byte[] array = Files.readAllBytes(Paths.get(arquivo.getDiretorio() + File.separator + arquivo.getNomeArquivo()));

                this.file = DefaultStreamedContent.builder().name(arquivo.getNomeArquivo()).contentType("text/csv").stream(() -> new ByteArrayInputStream(array)).build();

            } catch (ProativaException e) {

                e.printStackTrace();
                Messages.addGlobalError(e.getMessage(), new Object[0]);

            } catch (Exception e) {

                e.printStackTrace();
                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            }
        }
    }


    /**
     * @return the file
     */
    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the arquivo
     */
    public Upload getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(Upload arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * @return the listUploads
     */
    public List<Upload> getListUploads() {
        return listUploads;
    }

    /**
     * @param listUploads the listUploads to set
     */
    public void setListUploads(List<Upload> listUploads) {
        this.listUploads = listUploads;
    }


}

package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ConciliarAudio;
import com.proativaservicos.model.ConciliarSftp;
import com.proativaservicos.model.Loja;
import com.proativaservicos.service.ConciliarAudioService;
import com.proativaservicos.service.ConcilicarSftpService;
import com.proativaservicos.service.LojaService;
import com.proativaservicos.util.constantes.CronEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.List;


@Named
@ViewScoped
public class CadastroConciliarAudioBean extends GenericBean {


    private static final long serialVersionUID = 1L;

    @Inject
    private ConciliarAudioService serviceConciliarAudio;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private ConcilicarSftpService serviceSftp;

    private TipoPaginaEnum pagina;

    private ConciliarAudio conciliar;

    private List<ConciliarAudio> listConciliarAudio;

    private List<Loja> listLojas;

    private List<ConciliarSftp> listSftps;

    private Long idLoja;

    private Long idSftp;

    private Long idEmpresa;

    @PostConstruct
    public void init() {


        this.pagina = TipoPaginaEnum.PESQUISA;

        this.conciliar = new ConciliarAudio();
        this.conciliar.setEmpresa(retornarEmpresaUsuarioSessao());

        trocarEmpresa();

        this.listConciliarAudio = this.serviceConciliarAudio.pesquisarConciliarAudioAutomatico(retornarEmpresaUsuarioSessao().getId(), this.idLoja, this.idSftp, null);

    }


    public void pesquisar() {

        this.listConciliarAudio = this.serviceConciliarAudio.pesquisarConciliarAudioAutomatico(this.conciliar.getEmpresa().getId(), this.idLoja, this.idSftp, this.conciliar.getDescricao());

    }

    public void trocarEmpresa() {

        if (this.conciliar != null && this.conciliar.getEmpresa() != null) {

            this.listLojas = this.serviceLoja.pesquisarLojas(this.conciliar.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listSftps = this.serviceSftp.pesquisarListConciliarPorEmpresa(this.conciliar.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }


    }

    public void novo() {

        this.conciliar = new ConciliarAudio();
        this.pagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(ConciliarAudio conciliar) {

        this.conciliar = conciliar;
        this.pagina = TipoPaginaEnum.CADASTRO;
        trocarEmpresa();

    }

    public void voltar() {

        init();

    }

    public void salvar() {

        try {

            if (this.conciliar != null && this.conciliar.getId() != null) {

                alterar(this.conciliar, true);


            } else {

                inserir(this.conciliar, true);
                this.conciliar = new ConciliarAudio();

            }

            Messages.addGlobalInfo("Salvo com sucesso.", new Object());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object());
        }

    }


    /**
     * @return the pagina
     */
    public TipoPaginaEnum getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(TipoPaginaEnum pagina) {
        this.pagina = pagina;
    }

    /**
     * @return the loja
     */

    /**
     * @return the listConciliarAudio
     */
    public List<ConciliarAudio> getListConciliarAudio() {
        return listConciliarAudio;
    }

    /**
     * @param listConciliarAudio the listConciliarAudio to set
     */
    public void setListConciliarAudio(List<ConciliarAudio> listConciliarAudio) {
        this.listConciliarAudio = listConciliarAudio;
    }

    /**
     * @return the listLojas
     */
    public List<Loja> getListLojas() {
        return listLojas;
    }

    /**
     * @param listLojas the listLojas to set
     */
    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;
    }

    /**
     * @return the listSftps
     */
    public List<ConciliarSftp> getListSftps() {
        return listSftps;
    }

    /**
     * @param listSftps the listSftps to set
     */
    public void setListSftps(List<ConciliarSftp> listSftps) {
        this.listSftps = listSftps;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public TipoPaginaEnum[] getTipoPagina() {
        return TipoPaginaEnum.values();
    }

    public ConciliarAudio getConciliar() {
        return conciliar;
    }

    public void setConciliar(ConciliarAudio conciliar) {
        this.conciliar = conciliar;
    }


    /**
     * @return the idLoja
     */
    public Long getIdLoja() {
        return idLoja;
    }

    public CronEnum[] getCronEnum() {
        return CronEnum.values();
    }


    /**
     * @param idLoja the idLoja to set
     */
    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }


    /**
     * @return the idSftp
     */
    public Long getIdSftp() {
        return idSftp;
    }


    /**
     * @param idSftp the idSftp to set
     */
    public void setIdSftp(Long idSftp) {
        this.idSftp = idSftp;
    }


    /**
     * @return the idEmpresa
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }


    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


}

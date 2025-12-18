package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ConciliarSftp;
import com.proativaservicos.model.LogConciliarAudio;
import com.proativaservicos.model.Loja;
import com.proativaservicos.service.ConcilicarSftpService;
import com.proativaservicos.service.LogConciliarAudioService;
import com.proativaservicos.service.LojaService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.StatusConciliarSftp;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoConciliarEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.util.LangUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Named
@ViewScoped
public class LogConciliarAudioBean extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Inject
    private LogConciliarAudioService logConciliarService;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private ConcilicarSftpService serviceSftp;

    private LogConciliarAudio logConciliar;

    private Date dataInicio;

    private Date dataFim;

    private Long idSftp;

    private Long idLoja;

    private List<Loja> listLojas;

    private List<ConciliarSftp> listSftps;

    private List<LogConciliarAudio> listLogConciliar;

    private List<String> listStringAudio;
    private List<String> listStringAudioFilter;
    @PostConstruct
    public void init() {

        this.dataInicio = new Date();
        this.dataFim = new Date();
        this.logConciliar = new LogConciliarAudio();
        this.logConciliar.setEmpresa(retornarEmpresaUsuarioSessao());
        this.listStringAudio = null;
        inicializarEmpresa();
        trocarEmpresa();

        pesquisar();

    }

    public void pesquisar() {

        try {
            if (this.dataInicio != null && this.dataFim != null && this.dataInicio.after(this.dataFim))
                throw new ProativaException("A data inicial deve ser maior que a data final.");

            this.listLogConciliar = this.logConciliarService.pesquisarLogConciliar(
                    this.logConciliar.getEmpresa().getId(), this.idSftp, this.idLoja, this.logConciliar.getStatus(),
                    this.logConciliar.getTipoConciliar(), this.dataInicio, this.dataFim);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }


    public void trocarEmpresa() {

        if (this.logConciliar != null && this.logConciliar.getEmpresa() != null) {

            this.listLojas = this.serviceLoja.pesquisarLojas(this.logConciliar.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listSftps = this.serviceSftp.pesquisarListConciliarPorEmpresa(this.logConciliar.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }


    }

    public void onExibirStringAudios(LogConciliarAudio log) {

        log.converterJsonArquivos();

        this.listStringAudio = log.getListArquivos();


    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();

        if (StringUtils.isBlank((filterText))) {
            return true;
        }


        String obj = (String) value;

        return String.valueOf(obj).toLowerCase().contains(filterText) || String.valueOf(obj).toLowerCase().contains(filterText);

    }

    public StatusConciliarSftp[] getStatusConciliarSftp() {

        return StatusConciliarSftp.values();
    }

    public TipoConciliarEnum[] getTipoConciliarEnum() {
        return TipoConciliarEnum.values();
    }

    public LogConciliarAudio getLogConciliar() {
        return logConciliar;
    }

    public void setLogConciliar(LogConciliarAudio logConciliar) {
        this.logConciliar = logConciliar;
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
     * @return the logConciliarService
     */
    public LogConciliarAudioService getLogConciliarService() {
        return logConciliarService;
    }

    /**
     * @param logConciliarService the logConciliarService to set
     */
    public void setLogConciliarService(LogConciliarAudioService logConciliarService) {
        this.logConciliarService = logConciliarService;
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
     * @return the idLoja
     */
    public Long getIdLoja() {
        return idLoja;
    }

    /**
     * @param idLoja the idLoja to set
     */
    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
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
     * @return the listLogConciliar
     */
    public List<LogConciliarAudio> getListLogConciliar() {
        return listLogConciliar;
    }

    /**
     * @param listLogConciliar the listLogConciliar to set
     */
    public void setListLogConciliar(List<LogConciliarAudio> listLogConciliar) {
        this.listLogConciliar = listLogConciliar;
    }

    public List<String> getListStringAudio() {
        return listStringAudio;
    }


    public List<String> getListStringAudioFilter() {
        return listStringAudioFilter;
    }

    public void setListStringAudio(List<String> listStringAudio) {
        this.listStringAudio = listStringAudio;
    }

    public void setListStringAudioFilter(List<String> listStringAudioFilter) {
        this.listStringAudioFilter = listStringAudioFilter;
    }

}

package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.argus.PausaItem;
import com.proativaservicos.model.trescplus.Intervalo;
import com.proativaservicos.model.trescplus.ResponseIntervalo;
import com.proativaservicos.service.ConsistenciaService;
import com.proativaservicos.service.ControlePausaService;
import com.proativaservicos.service.EntidadesServices;
import com.proativaservicos.service.PausaService;
import com.proativaservicos.util.ArgusService;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.TresCPlusServiceUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class SistemaBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pausa pausa;

    private ControlePausa controlePausa;

    private List<Pausa> listPausa;

    @Inject
    private PausaService servicePausa;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private DiscadorUtil discadorUtil;

    @Inject
    private RegistroSistemaUtil registroSistema;

    @Inject
    private ConsistenciaService servicesConsistencia;

    @Inject
    private EntidadesServices serviceEntidade;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @Inject
    private ArgusService argusService;


    private boolean isScriptVonix = false;

    private Usuario usuario;

    private Empresa empresa;

    private boolean usuarioEmPausa;

    private List<Consistencia> listConsistencias;

    private List<Entidades> listEntidades;

    private List<EntidadesVetadas> listEntidadesVetadas;

    private List<DescontosJuros> listDescontosJuros;

    private List<Intervalo> intervaloList;

    private List<PausaItem> intervaloListArgus;

    private Integer intervalo3c;

    private Integer intervaloArgus;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        this.empresa = retornarEmpresaUsuarioSessao();

        this.controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario, new Date(System.currentTimeMillis()));

        if (this.usuario != null && this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VONIX)) {
            setScriptVonix(true);

            if (this.controlePausa != null && this.controlePausa.getPausa().getCodigoInterno() != null)
                this.usuario.setPausaVonix(this.controlePausa.getPausa().getCodigoInterno());
        }

        this.usuarioEmPausa = false;
    }

    public void abrirDlgPausa() {

        this.pausa = new Pausa();
        this.listPausa = this.servicePausa.pesquisarPausaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);


        if (CollectionUtils.isNotEmpty(this.usuario.getIntervalos3c())) {

            this.intervaloList = this.usuario.getIntervalos3c();

        } else if(CollectionUtils.isNotEmpty(this.usuario.getListaPausaArgus())){

            this.intervaloListArgus = this.usuario.getListaPausaArgus();

        }  else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

            try {

                ResponseIntervalo responseIntervalo = this.tresCPlusServiceUtil.pesquisarIntervalos(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getApiToken());

                if (CollectionUtils.isNotEmpty(responseIntervalo.getData())) {

                    this.usuario.setIntervalos3c(responseIntervalo.getData());
                    this.intervaloList = responseIntervalo.getData();
                }

            } catch (ProativaException e) {
                System.err.println(e.getMessage());
            }

        } else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.ARGUS)) {

            try {

                List<PausaItem> listPausaItems = this.argusService.listarPausas(new IntegracaoWs(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getPabx().getApiToken(), TipoIntegracaoEnum.ARGUS), this.usuario.getPontoAtendimento().getRamal());

                if (CollectionUtils.isNotEmpty(listPausaItems)) {

                    this.usuario.setListaPausaArgus(listPausaItems);
                    this.intervaloListArgus = listPausaItems;

                }

            } catch (ProativaException e) {
                Messages.addGlobalError(e.getMessage());
            }

        }

        PrimeFaces.current().ajax().update("dlgSistemaPausa");
        PrimeFaces.current().executeScript("PF('dlgSistemaPausa').show();");

    }

    public void desassociarFilas() {

        try {

            Usuario usuario = retornarUsuarioSessao();

            if (usuario != null && usuario.getPontoAtendimento() != null && StringUtils.isNotBlank(usuario.getPontoAtendimento().getRamal())) {

                Map<String, String> retorno = this.discadorUtil.desassociarFilas(usuario, false);

                if (retorno != null && retorno.get("retorno") != null && StringUtils.isNotBlank(retorno.get("retorno"))) {

                    JSONObject obj = new JSONObject(retorno.get("retorno"));

                    if (!obj.isEmpty() && !obj.isNull("sucesso") && !obj.getBoolean("sucesso")) {

                        Messages.addGlobalWarn(obj.isNull("mensagem") ? "Removido sem sucesso." : "Retorno Discador: " + obj.getString("mensagem"));

                    } else {

                        Messages.addGlobalInfo("Retorno Discador: " + obj.getString("mensagem"));
                    }

                }

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }

    }

    public String iniciarHome() {

        desassociarFilas();
        return "/pages/home/inicial.xhtml?faces-redirect=true";

    }

    public void iniciarPausa() {

        try {

            ControlePausa controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario, new Date(System.currentTimeMillis()));

            if (controlePausa != null)
                throw new ProativaException(MessagesEnum.VOCE_JA_ESTA_EM_PAUSA.constante);

            if (this.pausa != null && this.pausa.getMaximoPausa() != null && this.pausa.getMaximoPausa() > 0) {

                Integer usuariosPausados = this.serviceControlePausa.pesquisarQuantidadeUsuarioEmPausa(this.pausa.getId(), this.empresa.getId());

                if (usuariosPausados >= this.pausa.getMaximoPausa())
                    throw new ProativaException("A quantidade máxima de usuários em pausa foi atingido: " + this.pausa.getDescricao() + " - " + this.pausa.getMaximoPausa());

            }

            entrarPausaDiscador();
            Date dataPausa = new Date();
            this.registroSistema.registrarLog(this.usuario.getId(), TipoEventoEnum.PAUSA, this.pausa.getDescricao(), dataPausa);

            controlePausa = new ControlePausa();
            controlePausa.setDataCadastro(dataPausa);
            controlePausa.setPausa(this.pausa);
            controlePausa.setUsuarioPausa(this.usuario);
            controlePausa.setEmpresa(this.empresa);

            inserirGenerico(controlePausa);

            if (this.isScriptVonix && this.pausa != null && this.pausa.getCodigoInterno() != null) {

                this.usuario.setPausaVonix(this.pausa.getCodigoInterno());

            } else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VSPHONE)) {

                this.discadorUtil.entrarEmPausa(usuario, pausa, true);

            }
            System.out.println("PAUSANDO....");
            Faces.redirect(Faces.getRequestContextPath() + "/pages/home/inicial.jsf");

        } catch (ProativaException e) {
            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    private void entrarPausaDiscador() throws ProativaException {


        if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS) && this.intervalo3c != null) {

            this.tresCPlusServiceUtil.entrarEmPausa(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.intervalo3c, this.usuario.getPontoAtendimento().getApiToken());

        } else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.ARGUS) && this.intervaloArgus != null) {

            String texto = this.argusService.pausar(new IntegracaoWs(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getPabx().getApiToken(), TipoIntegracaoEnum.ARGUS), this.usuario.getPontoAtendimento().getRamal(), String.valueOf(this.intervaloArgus));
            Messages.addFlashGlobalInfo(texto);

        } else {
            this.discadorUtil.entrarEmPausa(this.usuario, this.pausa, true);
        }
    }

    public void iniciarPausaVonix() {


        if (this.isScriptVonix && this.controlePausa != null) {

            Integer codInterno = this.controlePausa.getPausa().getCodigoInterno();

            if (codInterno != null) {
                PrimeFaces.current().executeScript("vonix.doPause('" + codInterno + "');");

            } else {

                PrimeFaces.current().executeScript("vonix.doPause('9');");
            }

        }
    }

    public void removerPausa() {

        if (this.usuario != null && this.controlePausa != null) {

            try {

                this.controlePausa.setDataRetorno(new Date());
                alterarGenerico((Serializable) this.controlePausa);

                this.controlePausa = null;
                Messages.addGlobalInfo("Pausa removida com sucess.");


            } catch (Exception e) {

                e.printStackTrace();
                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
            }

        }

    }

    public void onListarConsistencias() {

        if (CollectionUtils.isEmpty(this.listConsistencias)) {

            this.listConsistencias = this.servicesConsistencia.pesquisarConsistenciaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

        }

    }

    public void onEntidades() {

        if (CollectionUtils.isEmpty(this.listEntidades)) {

            this.listEntidades = (List<Entidades>) this.serviceEntidade.pesquisarTodos(Entidades.class);
        }
    }

    public void onEntidadesVetadas() {

        if (CollectionUtils.isEmpty(this.listEntidadesVetadas)) {

            this.listEntidadesVetadas = (List<EntidadesVetadas>) this.serviceEntidade.pesquisarTodos(EntidadesVetadas.class);
        }
    }

    public void onDescontosJuros() {

        if (CollectionUtils.isEmpty(this.listDescontosJuros)) {

            this.listDescontosJuros = (List<DescontosJuros>) this.serviceEntidade.pesquisarTodos(DescontosJuros.class);
        }
    }


    public InstituicaoFinanceiraEnum[] getBancos() {
        return InstituicaoFinanceiraEnum.values();
    }

    public Pausa getPausa() {
        return pausa;
    }

    public void setPausa(Pausa pausa) {
        this.pausa = pausa;
    }

    public List<Pausa> getListPausa() {
        return listPausa;
    }

    public void setListPausa(List<Pausa> listPausa) {
        this.listPausa = listPausa;
    }

    public boolean isScriptVonix() {
        return isScriptVonix;
    }

    public void setScriptVonix(boolean isScriptVonix) {
        this.isScriptVonix = isScriptVonix;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isUsuarioEmPausa() {
        return usuarioEmPausa;
    }

    public void setUsuarioEmPausa(boolean usuarioEmPausa) {
        this.usuarioEmPausa = usuarioEmPausa;
    }

    public List<Consistencia> getListConsistencias() {
        return listConsistencias;
    }

    public List<Entidades> getListEntidades() {
        return listEntidades;
    }

    public void setListEntidades(List<Entidades> listEntidades) {
        this.listEntidades = listEntidades;
    }

    public List<EntidadesVetadas> getListEntidadesVetadas() {
        return listEntidadesVetadas;
    }

    public void setListEntidadesVetadas(List<EntidadesVetadas> listEntidadesVetadas) {
        this.listEntidadesVetadas = listEntidadesVetadas;
    }

    public List<DescontosJuros> getListDescontosJuros() {
        return listDescontosJuros;
    }

    public void setListDescontosJuros(List<DescontosJuros> listDescontosJuros) {
        this.listDescontosJuros = listDescontosJuros;
    }

    public void setListConsistencias(List<Consistencia> listConsistencias) {
        this.listConsistencias = listConsistencias;
    }

    public List<Intervalo> getIntervaloList() {
        return intervaloList;
    }

    public void setIntervaloList(List<Intervalo> intervaloList) {
        this.intervaloList = intervaloList;
    }

    public Integer getIntervalo3c() {
        return intervalo3c;
    }

    public void setIntervalo3c(Integer intervalo3c) {
        this.intervalo3c = intervalo3c;
    }

    public Integer getIntervaloArgus() {
        return intervaloArgus;
    }

    public void setIntervaloArgus(Integer intervaloArgus) {
        this.intervaloArgus = intervaloArgus;
    }

    public List<PausaItem> getIntervaloListArgus() {
        return intervaloListArgus;
    }

    public void setIntervaloListArgus(List<PausaItem> intervaloListArgus) {
        this.intervaloListArgus = intervaloListArgus;
    }
}

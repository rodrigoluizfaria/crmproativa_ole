package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ControlePausa;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Pausa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ControlePausaService;
import com.proativaservicos.service.HistoricoAtendimentoService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.TresCPlusServiceUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class inicialBean extends GenericBean implements Serializable {


    private static final long serialVersionUID = 1L;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;
    private ControlePausa controlePausa;

    private Pausa pausa;

    private Integer totalAgendamentos;

    private List<Pausa> listPausas;

    private Usuario usuario;

    private Empresa empresa;

    private boolean usuarioEmPausa;

    private Integer proximosDias;

    private String tempoPausa;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();

        if(!this.usuario.getPerfil().equals(PerfilUsuarioEnum.USUARIO_CONSULTA)) {

            this.empresa = retornarEmpresaUsuarioSessao();
            this.tempoPausa = "";

            this.controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario, new Date(System.currentTimeMillis()));

            onRefreshPausa();

            if (usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR)) {

                this.usuario.setQuantidadePendentes(this.serviceAtendimento.pesquisarQuantidadePendentes(usuario.getId(), Arrays.asList(TipoCampanhaEnum.ATIVA.name(), TipoCampanhaEnum.RECEPTIVA.name(), TipoCampanhaEnum.PREDITIVA.name())));

                this.usuario.setQuantidadeAgendamentos(this.serviceAtendimento.pesquisarQuantidadeAtendimentosAlerta(usuario.getId()));

                Date proximosDias = DateUtil.builder().adicionarTempoData(DataEnum.DIA, 15).getData();

                this.totalAgendamentos = this.serviceHistorico.pesquisarTotalAgendamentos(this.usuario.getId(), new Date(),
                        new Date(), this.usuario, this.usuario.getEmpresa().getId(), false);

                this.proximosDias = this.serviceHistorico.pesquisarTotalAgendamentos(this.usuario.getId(),
                        DateUtil.builder().adicionarTempoData(DataEnum.DIA, 1).getData(), proximosDias, this.usuario,
                        this.usuario.getEmpresa().getId(), false);

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
                this.usuario.setPausaVonix(null);

                if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS) && StringUtils.isNotBlank(this.usuario.getPontoAtendimento().getApiToken()))
                    this.tresCPlusServiceUtil.sairPausa(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getApiToken());

            } catch (ProativaException e){

                System.err.println(e.getMessage());

            } catch (Exception e) {

                e.printStackTrace();
                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
            }

        }

    }

    public void onRefreshPausa() {

        if (this.usuario != null && this.controlePausa != null) {

            this.tempoPausa = "";

            DateUtil.builder(this.controlePausa.getDataCadastro()).calcularDiferencaDatas(DataEnum.SEGUNDO);

            this.tempoPausa = DateUtil.builder(this.controlePausa.getDataCadastro(), new Date()).retornarDiferencaEntreDatasScala().getDataTexto();


        }
    }

    public ControlePausa getControlePausa() {
        return controlePausa;
    }

    public void setControlePausa(ControlePausa controlePausa) {
        this.controlePausa = controlePausa;
    }

    public Pausa getPausa() {
        return pausa;
    }

    public void setPausa(Pausa pausa) {
        this.pausa = pausa;
    }

    public List<Pausa> getListPausas() {
        return listPausas;
    }

    public void setListPausas(List<Pausa> listPausas) {
        this.listPausas = listPausas;
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

    public Integer getTotalAgendamentos() {
        return totalAgendamentos;
    }

    public Integer getProximosDias() {
        return proximosDias;
    }

    public static void main(String[] args) {

        DateUtil.builder(new Date()).adicionarTempoData(DataEnum.HORA, 1);

        System.out.println(DateUtil.builder(new Date(), DateUtil.builder(new Date()).adicionarTempoData(DataEnum.HORA, 1).adicionarTempoData(DataEnum.MINUTO, 35).getData()).retornarDiferencaEntreDatasScala().getDataTexto());
    }

    public String getTempoPausa() {
        return tempoPausa;
    }
}

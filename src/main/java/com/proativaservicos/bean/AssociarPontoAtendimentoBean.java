package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Pabx;
import com.proativaservicos.model.PontoAtendimento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.PabxService;
import com.proativaservicos.service.PontoAtendimentoService;
import com.proativaservicos.service.UsuarioLogadoService;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.PabxUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import com.proativaservicos.util.constantes.TipoPabxEnum;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AssociarPontoAtendimentoBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private List<PontoAtendimento> listPontoAtendimentos;

    private Usuario usuario;
    private Empresa empresa;
    private PontoAtendimento pontoAtendimento;

    @Inject
    private PontoAtendimentoService servicePontoAtendimento;

    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;

    @Inject
    private PabxService pabxService;

    @Inject
    private DiscadorUtil discador;

    @Inject
    private RegistroSistemaUtil registroSistema;

    private Pabx pabx;

    private boolean exibirBtnVerificar;

    private boolean exibirPainelConfirm;

    private boolean exibirBtnAssociar;

    private String ramalConfirm;

    @Inject
    public void init() {

        inicializarVariaveis();

    }

    private void inicializarVariaveis() {

        this.usuario = retornarUsuarioSessao();

        this.empresa = retornarEmpresaUsuarioSessao();


        try {


            if (this.usuario.getPontoAtendimento() == null || this.usuario.getPontoAtendimento().getRamal() == null) {

                this.usuario.setPontoAtendimento(new PontoAtendimento());

            }

            this.listPontoAtendimentos = this.servicePontoAtendimento.pesquisarPontoAtendimentosPorEmpresa(this.empresa.getId());

            for (PontoAtendimento pontoAtendimento : listPontoAtendimentos) {

                if (this.usuario.getPontoAtendimento().getRamal() != null && this.usuario.getPontoAtendimento().getId() == pontoAtendimento.getId()) {

                    //this.pontoAtendimento = pontoAtendimento;

                }

            }

            if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getId() != null && StringUtils.isNotEmpty(this.usuario.getPontoAtendimento().getRamal())) {

                String usuarioEmUso = validarRamalEmUso();

                if (StringUtils.isNotBlank(usuarioEmUso)) {

                    this.pontoAtendimento = null;

                    String msg = "Seu ramal [ " + this.usuario.getPontoAtendimento().getRamal() + " ] está em uso com o agent: " + usuarioEmUso;

                    if (usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VONIX)) {

                        this.usuario.setPontoAtendimento(null);
                        alterarGenerico((Serializable) this.usuario);
                        msg = msg + ", associação do seu ponto de atendimento será removida. para evitar conflito.";
                    }


                    Messages.addGlobalWarn(msg, new Object[0]);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

        this.pabx = null;
        this.ramalConfirm = null;
        this.exibirBtnVerificar = false;
        this.exibirPainelConfirm = false;
        this.exibirBtnAssociar = false;

    }

    public String validarRamalEmUso() {

        String usuarioRamal = this.serviceUsuarioLogado.pesquisarSessaoUsuarioRamal(this.usuario.getPontoAtendimento().getRamal(), this.usuario.getId());

        if (StringUtils.isNotBlank(usuarioRamal)) {

            return usuarioRamal;

        }

        return null;
    }

    public void associarPontoAtendimento() {

        try {

            if (this.usuario != null && this.pontoAtendimento != null && this.pontoAtendimento.getId() != null) {

                if (StringUtils.isEmpty(this.pontoAtendimento.getRamal())) {

                    throw new ProativaException("Ramal inválido. ");
                }

                if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getId() != null && StringUtils.isNotEmpty(this.usuario.getPontoAtendimento().getRamal())) {

                    String usuarioRamal = validarRamalEmUso();

                    if (StringUtils.isNotBlank(usuarioRamal)) {

                        throw new ProativaException("Usuário " + usuarioRamal + " já está associado neste ramal - " + this.pontoAtendimento.getRamal());

                    }
                }

                if (this.pontoAtendimento.getPabx().getTipo().equals(TipoPabxEnum.VONIX)) {

                    this.usuario.setPontoAtendimento(this.pontoAtendimento);

                    alterarGenerico((Serializable) this.usuario);

                } else if (this.pontoAtendimento.getPabx().getTipo().equals(TipoPabxEnum.VSPHONE)) {

                    if (StringUtils.isBlank(this.pontoAtendimento.getRamal()) || StringUtils.isBlank(this.ramalConfirm.trim()) || !this.ramalConfirm.trim().contains(this.pontoAtendimento.getRamal())) {
                        throw new ProativaException("Não foi possivel associar o ponto atendimento. numero informado foi inválido.");
                    }

                    this.usuario.setPontoAtendimento(this.pontoAtendimento);

                    this.discador.deslogar(this.usuario);

                    Map<String, String> retorno = this.discador.logar(this.usuario);


                    if (retorno != null && StringUtils.isNotBlank(retorno.get("retorno"))) {

                        JSONObject json = new JSONObject(retorno.get("retorno"));

                        if (!json.isEmpty() && !json.isNull("sucess") && !json.getBoolean("sucess") && !json.isNull("message")) {

                            if (json.getString("message").contains("está logado com o")) {

                                this.discador.deslogar(this.usuario);
                                this.usuario.setPontoAtendimento(null);
                            }

                            throw new ProativaException("Retorno Power Dialer: " + json.getString("message"));

                        }

                    }

                } else if (this.pontoAtendimento.getPabx().getTipo().equals(TipoPabxEnum.PST_PHONE)) {

                    if (StringUtils.isBlank(this.pontoAtendimento.getRamal())) {
                        throw new ProativaException("Não foi possivel associar o ponto atendimento. numero informado foi inválido.");
                    }

                    this.usuario.setPontoAtendimento(this.pontoAtendimento);

                    alterarGenerico((Serializable) this.usuario);


                }else if(this.pontoAtendimento.getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

                    this.usuario.setPontoAtendimento(this.pontoAtendimento);

                    alterarGenerico((Serializable) this.usuario);

                }else if(this.pontoAtendimento.getPabx().getTipo().equals(TipoPabxEnum.ARGUS)) {

                    this.usuario.setPontoAtendimento(this.pontoAtendimento);

                    alterarGenerico((Serializable) this.usuario);
                }


                Messages.addGlobalInfo("Ponto de Atendimento associado com sucesso!", new Object[0]);

                this.registroSistema.registrarLog(this.usuario.getId(), TipoEventoEnum.ASSOCIOU_RAMAL, "PONTO DE ATENDIMENTO ASSOCIADO: " + this.pontoAtendimento.getDescricao() + " | PABX: " + this.pontoAtendimento.getPabx().getDescricao());

            } else {

                throw new ProativaException("Erro ao associar o Ramal");
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }
    }

    public void onChangePontoAtendimento() {

        try {

            if (this.pontoAtendimento == null) {

                this.exibirBtnVerificar = false;
                this.exibirPainelConfirm = false;
                this.exibirBtnAssociar = false;

            } else {

                this.pabx = this.pabxService.pesquisarPorPontoAtendimento(this.pontoAtendimento.getId());

                if (this.pabx != null) {

                    boolean isVonix = this.pabx.getTipo().equals(TipoPabxEnum.VONIX );

                    if (isVonix || this.pabx.getTipo().equals(TipoPabxEnum.TRES_CPLUS ) || this.pabx.getTipo().equals(TipoPabxEnum.ARGUS )  || this.pabx.getTipo().equals(TipoPabxEnum.PST_PHONE )) {

                        this.exibirBtnVerificar = false;
                        this.exibirPainelConfirm = false;
                        this.exibirBtnAssociar = true;
                        return;
                    }

                    this.exibirBtnVerificar = true;

                } else {
                    throw new ProativaException("Nenhum servidor disponivel.");
                }

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }

    public void doVerificarPontoAtendimento() {

        try {

            this.pabx = this.pabxService.pesquisarPorPontoAtendimento(this.pontoAtendimento.getId());
            this.exibirBtnVerificar = false;

            if (this.pabx != null) {

                boolean isVonix = this.pabx.getTipo().equals(TipoPabxEnum.VONIX);

                if (!isVonix) {

                    String str = PabxUtil.discarChamada(this.pabx.getTipo(), this.pabx.getUrl(), this.pabx.getUsuario(), this.pabx.getSenha(), this.pontoAtendimento.getRamal(), this.pabx.getTipo().getCodigoVerificaRamal(), "Associando Ramal.", "", "", "", "");

                    if (str != null) {

                        this.exibirBtnVerificar = true;
                        this.exibirPainelConfirm = true;
                        this.exibirBtnAssociar = true;

                    }

                }


            } else {

                throw new ProativaException("O ponto atendimento: " + this.pontoAtendimento.getRamal() + ", não tem nenhum servidor associado.");
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public List<PontoAtendimento> getListPontoAtendimentos() {
        return listPontoAtendimentos;
    }

    public void setListPontoAtendimentos(List<PontoAtendimento> listPontoAtendimentos) {
        this.listPontoAtendimentos = listPontoAtendimentos;
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

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }

    public boolean isExibirBtnVerificar() {
        return exibirBtnVerificar;
    }

    public void setExibirBtnVerificar(boolean exibirBtnVerificar) {
        this.exibirBtnVerificar = exibirBtnVerificar;
    }

    public boolean isExibirPainelConfirm() {
        return exibirPainelConfirm;
    }

    public void setExibirBtnAssociar(boolean exibirBtnAssociar) {
        this.exibirBtnAssociar = exibirBtnAssociar;
    }

    public boolean isExibirBtnAssociar() {
        return exibirBtnAssociar;
    }

    public String getRamalConfirm() {
        return ramalConfirm;
    }

    public void setRamalConfirm(String ramalConfirm) {
        this.ramalConfirm = ramalConfirm;
    }

}

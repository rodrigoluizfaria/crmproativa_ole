package com.proativaservicos.bean;


import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.UsuarioLogadoService;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import com.proativaservicos.util.constantes.TipoPabxEnum;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.primefaces.PrimeFaces;

/**
 * Classe para efetuar o logout
 *
 * @author rodrigo
 */
@Named
@RequestScoped
public class LogoutBean extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioLogadoBean usuario;

    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private DiscadorUtil discadorUtil;

    private Usuario usuarioSessao;

    @PostConstruct
    public void init() {
        deslogar();

    }

    public String deslogar() {

        if (retornarUsuarioSessao() != null && retornarUsuarioSessao().getId() != null) {

            this.usuarioSessao = retornarUsuarioSessao();

            this.serviceUsuarioLogado.excluirUsuarioLogado(this.usuarioSessao.getId());

            this.registro.registrarLog(retornarUsuarioSessao().getId(), TipoEventoEnum.LOGOUT, MessagesEnum.LOGOUT.constante);

            deslogarDiscador();

            logout();
            Faces.redirect("index.xhtml");
            return "failure";

        }

        return null;

    }

    private void logout() {

        try {
            Faces.logout();
            Faces.invalidateSession();
        } catch (ServletException e) {

            e.printStackTrace();
        }
    }


    public void deslogarDiscador() {

        if (this.usuarioSessao != null && this.usuarioSessao.getPontoAtendimento() != null && StringUtils.isNotBlank(this.usuarioSessao.getPontoAtendimento().getRamal())) {

            try {

                this.discadorUtil.deslogar(this.usuarioSessao);

                if (this.usuarioSessao.getPontoAtendimento().getPabx() != null) {

                    if (this.usuarioSessao.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VONIX)) {
                        System.out.println("Deslogando agent " + this.usuarioSessao.getPontoAtendimento().getRamal() + " da vonix.");
                        PrimeFaces.current().executeScript("deslogarVonix(" + this.usuarioSessao.getPontoAtendimento().getPabx().getUrl() + "," + this.usuarioSessao.getPontoAtendimento().getRamal() + ");");
                    }


                }

            } catch (ProativaException e) {

                System.out.println("Erro ao deslogar no discador: " + e.getMessage());
            }
        }

    }


    public UsuarioLogadoBean getUsuario() {

        return usuario;

    }

    public void setUsuario(UsuarioLogadoBean usuario) {
        this.usuario = usuario;
    }

}

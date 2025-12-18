package com.proativaservicos.listener;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.UsuarioLogadoService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ControlUserSession implements HttpSessionListener, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Map<String, HttpSession> mapSessions = new HashMap<>();

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;

    @Inject
    private RegistroSistemaUtil registro;

    public void invalidarSessao(String sessionId) {

        HttpSession session = mapSessions.get(sessionId);


        if (session != null) {

            session.setAttribute("REMOVER_USR_SESSAO", Boolean.valueOf(true));
            removerUsuario(sessionId, Faces.getServletContext());
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {

        event.getSession().setMaxInactiveInterval(1800);
        mapSessions.put(event.getSession().getId(), event.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        removerUsuario(se.getSession().getId(), se.getSession().getServletContext());
    }

    private void removerUsuario(String sessionId, ServletContext servletContext) {

        if (sessionId != null) {

            Usuario usuario = this.serviceUsuario.pesquisarUsuarioPorSessao(sessionId);

            if (usuario != null && usuario.getId() != null) {


                this.serviceUsuarioLogado.removerUsuarioLogado(sessionId);
                this.registro.registrarLog(usuario.getId(), TipoEventoEnum.LOGOUT, MessagesEnum.LOGOUT.constante);

                // DISCADORA

            }

            mapSessions.remove(sessionId);
        }
    }

    public void removerUsuarioExpediente(Usuario usuario) {

        try {


            if (usuario != null && usuario.getLogin() != null) {

                String idSession = this.serviceUsuarioLogado.pesquisarSessaoUsuario(usuario.getLogin());

                if (!StringUtils.isBlank(idSession)) {

                    HttpSession session = mapSessions.get(idSession);
                    session.setAttribute("REMOVER_USR_SESSAO_EXPEDIENTE", true);
                    this.serviceUsuarioLogado.removerUsuarioLogado(idSession);
                    this.registro.registrarLog(usuario.getId(), TipoEventoEnum.LOGOUT, MessagesEnum.LOGOUT.constante);


                    mapSessions.remove(idSession);
                    Faces.redirect(Faces.getRequestContextPath() + "/index.jsf", new Object[0]);
                    //DISCADORA

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}

package com.proativaservicos.security;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.SubMenuEnum;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpSession;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/*"})
public class Filter implements PhaseListener, jakarta.servlet.Filter {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
/*
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		request.setCharacterEncoding("UTF-8");
		chain.doFilter((ServletRequest) request, (ServletResponse) response);*/
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {

        try {

            HttpSession sessao = Faces.getSession(false);

            boolean isUsrSession = false;

            String page = Faces.getRequestURI();

            if (page.contains("consultaNumero.jsf") || page.contains("envioSms.jsf"))
                return;

            if (sessao != null)
                isUsrSession = sessao.getAttribute("usuario") != null;

            if (isUsrSession || page.contains("index.jsf")) {

                if (isUsrSession && (page.equals("/crmproativa/pages/home/index.jsf") || page.equals("/crmproativa/")))
                    Faces.redirect(Faces.getRequestContextPath() + "/pages/home/inicial.jsf");


                if (sessao != null && sessao.getAttribute("REMOVER_USR_SESSAO") != null) {

                    Faces.logout();
                    sessao.invalidate();
                    Messages.addFlashGlobalError("Sua sessão expirou, favor logar novamente.");
                    Faces.redirect(Faces.getRequestContextPath() + "/pages/home/index.jsf");

                } else if (sessao != null && sessao.getAttribute("REMOVER_USR_SESSAO_EXPEDIENTE") != null) {

                    Faces.logout();
                    sessao.invalidate();
                    Messages.addFlashGlobalError("Sua sessão expirou, fora do horário de expediente.");
                    Faces.redirect(Faces.getRequestContextPath() + "/pages/home/index.jsf");

                } else if (validarPermissaoUsuario() && !page.equals("/crmproativa/") && !page.equals("/crmproativa/index.jsf")) {

                    Faces.redirect(Faces.getRequestContextPath() + "/pages/erro/access.jsf");
                }


            } else {

                if (page.contains("/pages/"))
                    Messages.addFlashGlobalError("Sessão inválida. por favor efetue login. ");

                Faces.redirect(Faces.getRequestContextPath() + "/pages/home/index.jsf");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean validarPermissaoUsuario() {

        Usuario usuario = (Usuario) Faces.getSessionAttribute("usuario");
        boolean contemPermissao = false;
        SubMenuEnum subMenuEnum = SubMenuEnum.getSubMenu(Faces.getRequestURI());

        if (usuario != null && subMenuEnum != null) {

            contemPermissao = (subMenuEnum.getMenu().getListPermicoes().contains(usuario.getPerfil()) && !subMenuEnum.getListPermecaoNegada().contains(usuario.getPerfil()));
        }

        String page = Faces.getRequestURI();

        if (usuario != null && usuario.getPerfil().equals(PerfilUsuarioEnum.ADMIN))
            return false;

        return (!page.contains("/erro") && !page.contains("/home") && usuario != null && !contemPermissao);

    }

    @Override
    public PhaseId getPhaseId() {

        return PhaseId.RESTORE_VIEW;

    }

}

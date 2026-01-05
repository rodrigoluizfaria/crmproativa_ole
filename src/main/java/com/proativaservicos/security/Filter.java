package com.proativaservicos.security;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.SubMenuEnum;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import java.io.IOException;

// Nota: Registre esta classe no faces-config.xml
public class Filter implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW; // Roda logo no início do ciclo JSF
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // Nada a fazer antes, validamos após restaurar a view ou usamos afterPhase
        // Mas como seu código original usava beforePhase no RESTORE_VIEW, mantemos aqui
        // pois é o momento onde a URL chega.

        try {
            handleSecurityAndCleaning();
        } catch (Exception e) {
            e.printStackTrace(); // Idealmente use um Logger (SLF4J/Log4J)
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        // Não utilizado
    }

    private void handleSecurityAndCleaning() throws IOException, ServletException {
        String page = Faces.getRequestURI();
        HttpSession sessao = Faces.getSession(false);
        boolean isUsrSession = (sessao != null && sessao.getAttribute("usuario") != null);

        // 1. Pular recursos estáticos ou páginas públicas
        if (page.contains("consultaNumero.jsf") || page.contains("envioSms.jsf") || page.contains("javax.faces.resource")) {
            return;
        }

        // =================================================================================
        // 2. LIMPEZA AUTOMÁTICA DE SESSÃO (GARBAGE COLLECTOR)
        // =================================================================================
        // Se a página atual NÃO for a ficha de atendimento, removemos os atributos da sessão.
        // Isso resolve seu problema de "destruir o setAttribute quando sair da página".
        if (!page.contains("/atendimento/fichaAtendimento")) { // Ajuste o caminho conforme seu XHTML
            System.out.println("REMOVENDO ATN,,,,");
         /*   if (sessao != null) {
                sessao.removeAttribute("atendimento_iniciado");
                sessao.removeAttribute("cpf_atn");
                sessao.removeAttribute("protocolo_pai");
                // System.out.println("Limpando dados de atendimento da sessão (Navegação detectada).");
            }*/
        }

        // =================================================================================
        // 3. LÓGICA DE LOGIN E SEGURANÇA
        // =================================================================================
        if (isUsrSession || page.contains("index.jsf")) {

            // Se logado e tentar acessar login, joga para inicial
            if (isUsrSession && (page.endsWith("/index.jsf") || page.endsWith("/crmproativa/"))) {
                Faces.redirect(Faces.getRequestContextPath() + "/pages/home/inicial.jsf");
                return;
            }

            // Verifica Flags de Expiração Forçada
            if (sessao != null) {
                if (sessao.getAttribute("REMOVER_USR_SESSAO") != null) {
                    realizarLogoutForcado(sessao, "Sua sessão expirou, favor logar novamente.");
                    return;
                } else if (sessao.getAttribute("REMOVER_USR_SESSAO_EXPEDIENTE") != null) {
                    realizarLogoutForcado(sessao, "Sua sessão expirou, fora do horário de expediente.");
                    return;
                }
            }

            // Validação de Permissão (ACL)
            if (isUsrSession && validarPermissaoUsuario() && !page.endsWith("/index.jsf")) {
                Faces.redirect(Faces.getRequestContextPath() + "/pages/erro/access.jsf");
            }

        } else {
            // Não logado tentando acessar páginas internas
            if (page.contains("/pages/")) {
                Messages.addFlashGlobalError("Sessão inválida. por favor efetue login.");
                Faces.redirect(Faces.getRequestContextPath() + "/pages/home/index.jsf");
            }
        }
    }

    private void realizarLogoutForcado(HttpSession sessao, String mensagem) throws IOException, ServletException {

        Faces.logout();
        sessao.invalidate(); // Mata a sessão
        Messages.addFlashGlobalError(mensagem);
        Faces.redirect(Faces.getRequestContextPath() + "/pages/home/index.jsf");

    }

    private boolean validarPermissaoUsuario() {
        Usuario usuario = (Usuario) Faces.getSessionAttribute("usuario");
        if (usuario == null) return false;

        // Admin total
        if (PerfilUsuarioEnum.ADMIN.equals(usuario.getPerfil())) return false;

        SubMenuEnum subMenuEnum = SubMenuEnum.getSubMenu(Faces.getRequestURI());

        // Se a página não está mapeada no Enum, talvez deva liberar ou bloquear (depende da regra)
        if (subMenuEnum == null) {
            String page = Faces.getRequestURI();
            // Libera páginas padrão de erro e home
            return !page.contains("/erro") && !page.contains("/home");
        }

        boolean temPermissaoExplicita = subMenuEnum.getMenu().getListPermicoes().contains(usuario.getPerfil());
        boolean acessoNegadoExplicito = subMenuEnum.getListPermecaoNegada().contains(usuario.getPerfil());

        // Retorna TRUE se deve BLOQUEAR (Lógica inversa do seu código original)
        return !(temPermissaoExplicita && !acessoNegadoExplicito);
    }
}
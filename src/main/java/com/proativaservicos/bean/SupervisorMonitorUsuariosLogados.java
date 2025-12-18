package com.proativaservicos.bean;

import com.proativaservicos.listener.ControlUserSession;
import com.proativaservicos.service.UsuarioLogadoService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Named
@ViewScoped
public class SupervisorMonitorUsuariosLogados extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<?> listUsuariosLogados;

    private List<?> listUsuariosLogadosFilter;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private ControlUserSession controlSession;


    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;

    @PostConstruct
    public void init() {

        inicializarEmpresa(retornarEmpresaUsuarioSessao());
        pesquisar();

    }


    public void pesquisar() {

        try {

            if (retornarUsuarioSessao().getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listUsuariosLogados = this.serviceUsuario.pesquisarUsuariosLogados(retornarUsuarioSessao().getId(), getEmpresa().getId());

            } else {

                this.listUsuariosLogados = this.serviceUsuario.pesquisarUsuariosLogados(null, getEmpresa().getId());


            }


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void invalidSession(String login) {

        String sessao = this.serviceUsuarioLogado.pesquisarSessaoUsuario(login);

        if (sessao != null) {

            this.serviceUsuarioLogado.excluirUsuarioLogadoPorLogin(sessao);
            this.controlSession.invalidarSessao(sessao);

        }

        pesquisar();


    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();

        if (StringUtils.isBlank(filterText)) {
            return true;
        }


        Object obj[] = (Object[]) value;

        return String.valueOf(obj[1]).toLowerCase().contains(filterText) || String.valueOf(obj[0]).toLowerCase().contains(filterText);

    }

    public List<?> getListUsuariosLogadosFilter() {
        return listUsuariosLogadosFilter;
    }

    public void setListUsuariosLogadosFilter(List<?> listUsuariosLogadosFilter) {
        this.listUsuariosLogadosFilter = listUsuariosLogadosFilter;
    }

    public List<?> getListUsuariosLogados() {
        return listUsuariosLogados;
    }

    public void setListUsuariosLogados(List<?> listUsuariosLogados) {
        this.listUsuariosLogados = listUsuariosLogados;
    }

}
	


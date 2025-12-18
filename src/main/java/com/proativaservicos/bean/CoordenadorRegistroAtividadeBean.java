package com.proativaservicos.bean;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Date;
import java.util.List;


@Named
@ViewScoped
public class CoordenadorRegistroAtividadeBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService serviceUsuario;

    private List<Usuario> listUsuario;

    private List<Long> listIdUsuario;

    private Date data;

    private Long idUsuario;

    private List<?> listResultados;

    @PostConstruct
    public void init() {

        inicializarEmpresa();
        trocarEmpresa();

    }


    public void trocarEmpresa() {

        if (getEmpresa() == null) {
            this.listUsuario = null;
        } else {
            this.listUsuario = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
        }

    }


    public void pesquisar() {

        this.listResultados = this.serviceUsuario.pesquisarMonitorAtividadeListAtividades(this.idUsuario, this.data);

    }


    public List<Usuario> getListUsuario() {
        return listUsuario;
    }


    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }


    public List<Long> getListIdUsuario() {
        return listIdUsuario;
    }


    public void setListIdUsuario(List<Long> listIdUsuario) {
        this.listIdUsuario = listIdUsuario;
    }


    public Date getData() {
        return data;
    }


    public void setData(Date data) {
        this.data = data;
    }


    public List<?> getListResultados() {
        return listResultados;
    }


    public void setListResultados(List<?> listResultados) {
        this.listResultados = listResultados;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


}

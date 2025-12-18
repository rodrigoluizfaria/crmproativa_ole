package com.proativaservicos.bean;

import com.proativaservicos.model.Equipe;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.Date;
import java.util.List;


@Named
@ViewScoped
public class CoordenadorUltimaAcaoBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;


    private Date dataInicio;
    private Date dataFim;

    private Long idEquipe;

    private List<Long> listIdEquipes;
    private List<Equipe> listEquipes;

    private List<?> listAcessos;

    @PostConstruct
    private void init() {

        inicializarEmpresa();
        trocarEmpresa();
    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;

        } else {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }

    }

    public void pesquisar() {

        try {

            listAcessos = this.serviceUsuario.pesquisarRelatoriosUltimaAcao(getEmpresa().getId(), this.listIdEquipes, this.dataInicio, this.dataFim);

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }


    public List<Long> getListIdEquipes() {
        return listIdEquipes;
    }

    public void setListIdEquipes(List<Long> listIdEquipes) {
        this.listIdEquipes = listIdEquipes;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<?> getListAcessos() {
        return listAcessos;
    }

    public void setListAcessos(List<?> listAcessos) {
        this.listAcessos = listAcessos;
    }


}

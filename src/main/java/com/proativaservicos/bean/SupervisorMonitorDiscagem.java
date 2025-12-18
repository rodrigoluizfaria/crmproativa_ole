package com.proativaservicos.bean;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
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
public class SupervisorMonitorDiscagem extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    private List<Campanha> listCampanha;

    private List<Equipe> listEquipe;

    private List<StatusCampanha> listStatusCampanha;

    private List<Object[]> listQuatidades;

    private Usuario usuario;

    private Long campanhaLong;

    private Long equipeLong;

    private Long statusCampanhaLong;

    private Date dataInicio;

    private Date dataFim;

    @PostConstruct
    public void init() {
        try {

            System.out.println("INIT:::");
            this.usuario = retornarUsuarioSessao();
            inicializarEmpresa(retornarEmpresaUsuarioSessao());
            trocarEmpresa();

            this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(getEmpresa().getId(),
                    TipoAcessoEnum.ATIVO);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void pesquisar() {
        try {

            if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {


            } else {

                this.listQuatidades = this.serviceAtendimento.pesquisarQuantidadeMonitorDiscagem(this.campanhaLong, this.equipeLong, this.statusCampanhaLong, null, getEmpresa().getId(), this.dataInicio, this.dataFim);

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void changeEquipe() {


        try {
            this.equipeLong = null;

            if (this.campanhaLong == null) {

                if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipe = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());
                } else {

                    this.listEquipe = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                }

                this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipe = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanhaLong, this.usuario.getId());

            } else {

                this.listEquipe = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanhaLong);
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public void changeCampanha() {

        try {
            if (getEmpresa() != null) {

                this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanha = null;
            this.listEquipe = null;

        } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listEquipe = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listCampanha = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuario.getId(), getEmpresa().getId());

        } else {
            this.listEquipe = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCampanhaLong() {
        return campanhaLong;
    }

    public void setCampanhaLong(Long campanhaLong) {
        this.campanhaLong = campanhaLong;
    }

    public Long getEquipeLong() {
        return equipeLong;
    }

    public void setEquipeLong(Long equipeLong) {
        this.equipeLong = equipeLong;
    }

    public Long getStatusCampanhaLong() {
        return statusCampanhaLong;
    }

    public void setStatusCampanhaLong(Long statusCampanhaLong) {
        this.statusCampanhaLong = statusCampanhaLong;
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

    public List<Campanha> getListCampanha() {
        return listCampanha;
    }

    public void setListCampanha(List<Campanha> listCampanha) {
        this.listCampanha = listCampanha;
    }

    public List<Equipe> getListEquipe() {
        return listEquipe;
    }

    public void setListEquipe(List<Equipe> listEquipe) {
        this.listEquipe = listEquipe;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public List<Object[]> getListQuatidades() {
        return listQuatidades;
    }

    public void setListQuatidades(List<Object[]> listQuatidades) {
        this.listQuatidades = listQuatidades;
    }

}

package com.proativaservicos.bean;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CoordenacaoRegistroAcaoUsuarioBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService serviceUsuario;

    private Long idUsuario;

    private Date dataInicial;
    private Date dataFinal;

    private String eventoInicio;

    private String eventoFinal;

    private List<Usuario> listUsuarios;

    private List<Object[]> listResultados;


    @PostConstruct
    public void init() {

        try {

            inicializarEmpresa();
            trocarEmpresa();

            this.dataInicial = DateUtil.builder().retornarDataPrimeiroDiaMes().getData();
            this.dataFinal = DateUtil.builder().retornarDataUltimoDiaMes().getData();


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addFlashGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listUsuarios = null;

        } else {

            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
        }


    }

    public void pesquisar() {

        try {

            this.listResultados = this.serviceUsuario.pesquisarRelatorioAcoesUsuarios(this.idUsuario, this.dataInicial, this.dataFinal, this.eventoInicio, this.eventoFinal, getEmpresa().getId());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addFlashGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }


    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getEventoInicio() {
        return eventoInicio;
    }

    public void setEventoInicio(String eventoInicio) {
        this.eventoInicio = eventoInicio;
    }

    public String getEventoFinal() {
        return eventoFinal;
    }

    public void setEventoFinal(String eventoFinal) {
        this.eventoFinal = eventoFinal;
    }


    public List<Object[]> getListResultados() {
        return listResultados;
    }

    public void setListResultados(List<Object[]> listResultados) {
        this.listResultados = listResultados;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }


}

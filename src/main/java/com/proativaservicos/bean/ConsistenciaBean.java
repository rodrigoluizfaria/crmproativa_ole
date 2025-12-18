package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Consistencia;
import com.proativaservicos.service.ConsistenciaService;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ConsistenciaBean extends GenericBean {

    private static final long serialVersionUID = -7161367718690076467L;

    @Inject
    private ConsistenciaService serviceConsistencia;

    private Consistencia consistencia;

    private TipoPaginaEnum tipoPagina;

    private List<Consistencia> listaConsistencia;


    @PostConstruct
    public void init() {

        inicializarEmpresa(retornarEmpresaMatrizUsuarioSessao());
        this.consistencia = new Consistencia();
        this.tipoPagina = TipoPaginaEnum.PESQUISA;
        pesquisar();

    }

    public void pesquisar() {

        if (getEmpresa() != null)
            this.listaConsistencia = this.serviceConsistencia.pesquisarConsistenciaPorEmpresa(getEmpresa().getId(), this.consistencia);

    }

    public void salvar() {

        try {

            if (this.consistencia.getId() != null) {

                this.consistencia.setEmpresa(getEmpresa());
                alterar((Serializable) this.consistencia);

            } else {

                this.consistencia.setEmpresa(getEmpresa());
                inserir((Serializable) this.consistencia);
                this.consistencia = new Consistencia();


            }

            Messages.addGlobalInfo("Salvo com sucesso!", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            e.printStackTrace();

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }

    }

    public void novo() {

        this.consistencia = new Consistencia();
        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(Consistencia consistencia) {
        this.consistencia = consistencia;
        this.tipoPagina = TipoPaginaEnum.CADASTRO;
    }

    public void voltar() {

        this.consistencia = new Consistencia();
        pesquisar();
        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void deletar() {

        try {

            if (this.consistencia != null && this.consistencia.getId() != null) {

                excluir(Consistencia.class, this.getConsistencia().getId());
            }

            Messages.addGlobalInfo("Removido com sucesso!", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            e.printStackTrace();

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }

    }

    public void trocarEmpresa() {


        pesquisar();


    }

    /**
     * @return the consistencia
     */
    public Consistencia getConsistencia() {
        return consistencia;
    }

    /**
     * @param consistencia the consistencia to set
     */
    public void setConsistencia(Consistencia consistencia) {
        this.consistencia = consistencia;
    }

    /**
     * @return the tipoPagina
     */
    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    /**
     * @param tipoPagina the tipoPagina to set
     */
    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    /**
     * @return the listaConsistencia
     */
    public List<Consistencia> getListaConsistencia() {
        return listaConsistencia;
    }

    /**
     * @param listaConsistencia the listaConsistencia to set
     */
    public void setListaConsistencia(List<Consistencia> listaConsistencia) {
        this.listaConsistencia = listaConsistencia;
    }

    public InstituicaoFinanceiraEnum[] getInstituicaoFinanceiraEnum() {
        return InstituicaoFinanceiraEnum.values();
    }

}

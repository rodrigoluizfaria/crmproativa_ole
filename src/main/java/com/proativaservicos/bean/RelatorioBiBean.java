package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.RelatorioBi;
import com.proativaservicos.service.RelatorioBiService;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named
public class RelatorioBiBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private RelatorioBiService serviceRelatorioBi;

    private RelatorioBi relatorioBi;

    private List<RelatorioBi> listRelatorioBi;


    @PostConstruct
    public void init() {
        pesquisar();
    }

    public void pesquisar() {


        try {

            this.listRelatorioBi = this.serviceRelatorioBi.pesquisarRelatoriosBiPorEmpresa(retornarEmpresaUsuarioSessao().getId());


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void salvar() {

        try {
            if (this.relatorioBi == null || StringUtils.isBlank(this.relatorioBi.getUrl()) || (!StringUtils.startsWithIgnoreCase(this.relatorioBi.getUrl(), "https://") && !StringUtils.startsWithIgnoreCase(this.relatorioBi.getUrl(), "http://")))
                throw new ProativaException("Url inválida.");


            if (this.relatorioBi.getId() != null) {

                alterar((Serializable) this.relatorioBi);

            } else {

                this.relatorioBi.setData(new Date());
                this.relatorioBi.setEmpresa(retornarEmpresaUsuarioSessao());
                inserir((Serializable) this.relatorioBi);

            }

            Messages.addGlobalInfo("Inserido com sucesso.", new Object[0]);
            this.relatorioBi = new RelatorioBi();

            pesquisar();
            PrimeFaces.current().executeScript("PF('dlgRelatorioBi').hide();");

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void excluir(RelatorioBi relatorio) {

        try {

            excluir(RelatorioBi.class, (Serializable) relatorio.getId());
            pesquisar();

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void novo() {

        this.relatorioBi = new RelatorioBi();

        PrimeFaces.current().executeScript("PF('dlgRelatorioBi').show()");
    }

    public void editar(RelatorioBi relatorio) {

        if (relatorio != null) {

            this.relatorioBi = relatorio;
            System.out.println(this.relatorioBi.getUrl());
            PrimeFaces.current().executeScript("PF('dlgRelatorioBi').show()");

        } else {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public RelatorioBi getRelatorioBi() {
        return relatorioBi;
    }


    public void setRelatorioBi(RelatorioBi relatorioBi) {
        this.relatorioBi = relatorioBi;
    }


    public List<RelatorioBi> getListRelatorioBi() {
        return listRelatorioBi;
    }


    public void setListRelatorioBi(List<RelatorioBi> listRelatorioBi) {
        this.listRelatorioBi = listRelatorioBi;
    }


}

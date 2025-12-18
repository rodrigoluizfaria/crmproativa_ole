package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Pabx;
import com.proativaservicos.service.PabxService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoPabxEnum;
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
public class PabxIpBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private Pabx pabx;

    private List<Pabx> listPabx;

    private TipoPabxEnum tipoPabx;

    @Inject
    private PabxService servicePabx;

    private TipoPaginaEnum tipoPagina;

    @PostConstruct
    private void init() {

        this.pabx = new Pabx();

        this.pabx.setEmpresa(retornarEmpresaUsuarioSessao());

        listPabx = servicePabx.pesquisarServidoresVoipPorEmpresa(retornarEmpresaUsuarioSessao().getId(), true, null);

        tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    @SuppressWarnings("static-access")
    public TipoPabxEnum[] getTipoPabx() {
        return tipoPabx.values();
    }

    public void setTipoPabx(TipoPabxEnum tipoPabx) {
        this.tipoPabx = tipoPabx;
    }

    public Pabx getPabx() {
        return pabx;
    }

    public void setPabx(Pabx pabx) {
        this.pabx = pabx;
    }

    public List<Pabx> getListPabx() {
        return listPabx;
    }

    public void setListPabx(List<Pabx> listPabx) {
        this.listPabx = listPabx;
    }

    public void salvar() {

        try {

            String msg = this.pabx.getId() == null ? "PabxIP Salvo com sucesso!" : "PabxIP atualizado com sucesso!";


            if (this.pabx != null && this.pabx.getId() != null) {

                System.out.println(this.pabx.getEmpresa().getNome());

                alterar((Serializable) this.pabx, false);
                Messages.addGlobalInfo(msg, new Object[0]);

            } else {

                inserir((Serializable) this.pabx, false);
                Messages.addGlobalInfo(msg, new Object[0]);

            }

            pabx = new Pabx();

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void editar(Pabx pabx) {

        this.pabx = pabx;
        tipoPagina = TipoPaginaEnum.CADASTRO;
    }

    public void voltar() {

        init();

    }


    public void pesquisar() {

        listPabx = servicePabx.pesquisarServidores(this.pabx.getEmpresa().getId(), this.pabx);

    }

    public void novo() {

        this.pabx = new Pabx();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

}

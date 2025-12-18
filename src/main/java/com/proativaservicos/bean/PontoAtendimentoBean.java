package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.Pabx;
import com.proativaservicos.model.PontoAtendimento;
import com.proativaservicos.model.trescplus.ResponseAuthenticate;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.PabxService;
import com.proativaservicos.service.PontoAtendimentoService;
import com.proativaservicos.util.TresCPlusServiceUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PontoAtendimentoBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PontoAtendimento pontoAtendimento;

    @Inject
    private PabxService servicePabx;

    @Inject
    private PontoAtendimentoService servicePontoatendimento;

    private List<PontoAtendimento> listPontoAtendimento;

    private List<Pabx> listPabx;

    private TipoPaginaEnum tipoPagina;

    @Inject
    private IntegracaoService integracaoService;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @PostConstruct
    public void init() {

        this.pontoAtendimento = new PontoAtendimento();
        this.pontoAtendimento.setEmpresa(retornarEmpresaUsuarioSessao());

        pesquisar();

        inicializarVariaveis();

        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    private void inicializarVariaveis() {

        // TODO Auto-generated method stub

        if (retornarUsuarioSessao().getEmpresa().isPossuiFiliais()) {

            trocarEmpresa();

        } else {

            this.listPabx = this.servicePabx.pesquisarServidoresVoipPorEmpresa(retornarEmpresaUsuarioSessao().getId(),false, TipoAcessoEnum.ATIVO);
        }

    }

    public void trocarEmpresa() {
        // TODO Auto-generated method stub

        if (this.pontoAtendimento.getEmpresa() == null) {

            this.listPabx = null;

        } else {

            this.listPabx = this.servicePabx.pesquisarServidoresVoipPorEmpresa(
                    this.pontoAtendimento.getEmpresa().getId(), false, TipoAcessoEnum.ATIVO);

        }

    }

    public void onBuscarToken3c() {

        try {

            if (this.pontoAtendimento.getPabx() == null || !this.pontoAtendimento.getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS))
                throw new ProativaException("Favor informar o tipo de PABX 3c+");

            IntegracaoWs integracaoWs = new IntegracaoWs();
            integracaoWs.setUrl(this.pontoAtendimento.getPabx().getUrl());
            integracaoWs.setToken(this.pontoAtendimento.getPabx().getApiToken());

            if (StringUtils.isBlank(integracaoWs.getToken()))
                throw new ProativaException("Nehum integração 3C+ encontrada.");


            ResponseAuthenticate responseAuthenticate = this.tresCPlusServiceUtil.autenticacao(integracaoWs, pontoAtendimento.getRamal(), this.pontoAtendimento.getSenha());

            this.pontoAtendimento.setApiToken(responseAuthenticate.getAgent().getApiToken());


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }

    public List<PontoAtendimento> getListPontoAtendimento() {
        return listPontoAtendimento;
    }

    public void setListPontoAtendimento(List<PontoAtendimento> listPontoAtendimento) {
        this.listPontoAtendimento = listPontoAtendimento;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public List<Pabx> getListPabx() {
        return listPabx;
    }

    public void setListPabx(List<Pabx> listPabx) {
        this.listPabx = listPabx;
    }

    public void voltar() {

        this.pontoAtendimento = new PontoAtendimento();
        this.pontoAtendimento.setEmpresa(retornarEmpresaUsuarioSessao());

        pesquisar();

        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void novo() {

        this.pontoAtendimento = new PontoAtendimento();
        inicializarVariaveis();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(PontoAtendimento ponto) {

        pontoAtendimento = ponto;
        inicializarVariaveis();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void pesquisar() {

        try {

            this.listPontoAtendimento = this.servicePontoatendimento.pesquisarPontosAtendimentos(this.pontoAtendimento);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void salvar() {

        try {

            String msg = "";

            if (this.pontoAtendimento != null && this.pontoAtendimento.getId() != null) {

                alterar((Serializable) pontoAtendimento, false);
                msg = "Ponto Atendimento alterado com sucesso!";

            } else {

                inserir((Serializable) pontoAtendimento, false);
                msg = "Ponto Atendimento salvo com sucesso!";
                this.pontoAtendimento = new PontoAtendimento();
            }

            Messages.addGlobalInfo(msg, new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

}

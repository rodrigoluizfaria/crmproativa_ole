package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.asynchronous.WebSocket3c;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
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
public class IntegracaoBean extends GenericBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private IntegracaoWs integracao;

    private TipoPaginaEnum tipoPagina;

    @Inject
    private IntegracaoService serviceIntegracao;

    private List<IntegracaoWs> listIntegracao;

    private List<Empresa> listEmpresa;

    private Usuario usuario;

    private boolean socketAlive3c;

    @Inject
    private WebSocket3c webSocket3c;

    @PostConstruct
    public void init() {

        this.integracao = new IntegracaoWs();

        this.usuario = retornarUsuarioSessao();

        this.integracao = new IntegracaoWs();

        this.integracao.setEmpresa(this.usuario.getEmpresa());

        pesquisar();

        this.tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public TipoIntegracaoEnum[] getTipoIntegracao() {
        return TipoIntegracaoEnum.values();
    }

    public IntegracaoWs getIntegracao() {
        return integracao;
    }

    public void setIntegracao(IntegracaoWs integracao) {
        this.integracao = integracao;
    }


    public List<IntegracaoWs> getListIntegracao() {
        return listIntegracao;
    }

    public void setListIntegracao(List<IntegracaoWs> listIntegracao) {
        this.listIntegracao = listIntegracao;
    }

    public List<Empresa> getListEmpresa() {

        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public void voltar() {

        init();

    }

    public void novo() {

        this.integracao = new IntegracaoWs();

        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(IntegracaoWs integracao) {

        this.integracao = integracao;

        if (this.integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.TRES_CPLUS)) {

            this.socketAlive3c = webSocket3c.isIntegracaoIsActive(integracao.getId());
            if(this.socketAlive3c)
                this.integracao.setStatus(this.webSocket3c.retornarStatus());
        }

        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void salvar() {

        try {

            String msg = this.integracao.getId() == null ? "Serviço de Integração e Salvo com sucesso!" : "Serviço de Integração atualizado com sucesso!";

            if (this.integracao.getId() != null) {

                alterar((Serializable) this.integracao, false);
                Messages.addGlobalInfo(msg, new Object[0]);

            } else {

                inserir((Serializable) this.integracao, false);
                this.integracao = new IntegracaoWs();
                this.integracao.setEmpresa(usuario.getEmpresa());
                Messages.addGlobalInfo(msg, new Object[0]);
                integracao = new IntegracaoWs();

            }

            init();


        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisar() {

        try {

            this.listIntegracao = this.serviceIntegracao.pesquisarServicosIntegracoes(this.integracao);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalFatal(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void pararWebsocket() {

        this.webSocket3c.stop();
        tempoEspera(5000L);
        this.socketAlive3c = webSocket3c.emExecucao();
        onRetornarStatus();

    }

    public void iniciarWebsocket() {

        try {

            if (this.integracao != null && this.integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.TRES_CPLUS)) {

                if (!webSocket3c.isIntegracaoIsActive(this.integracao.getId())) {

                    if (webSocket3c.emExecucao())
                        throw new ProativaException("Já existe um socket ativo: " + webSocket3c.getIntegracaoWs().getDescricao());

                    integracao.setHabilitarWebsocket(true);
                    webSocket3c.setIntegracaoWs(integracao);
                    webSocket3c.start(integracao);
                    tempoEspera(5000L);
                    this.socketAlive3c = webSocket3c.emExecucao();
                    this.onRetornarStatus();
                }


            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }
    }

    public void onRetornarStatus() {

        if (this.integracao != null && this.integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.TRES_CPLUS)) {

            this.integracao.setStatus(this.webSocket3c.retornarStatus());

        }
    }

    private void tempoEspera(Long milisegundos) {

        try {

            Thread.sleep(milisegundos);

        } catch (InterruptedException ignored) {

        }
    }


    public boolean isSocketAlive3c() {
        return socketAlive3c;
    }

    public void setSocketAlive3c(boolean socketAlive3c) {
        this.socketAlive3c = socketAlive3c;
    }
}

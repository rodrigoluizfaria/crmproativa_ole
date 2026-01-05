package com.proativaservicos.bean;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Cliente;
import com.proativaservicos.model.Departamento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.*;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ConsultaBackofficeBean extends GenericBean {

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private HistoricoAtividadeService serviceHistoricoAtividade;

    @Inject
    private HistoricoAtendimentoService serviceHistoricoAtendimento;

    @Inject
    private DepartamentoService serviceDepartamento;
    @Inject
    private ClienteService serviceCliente;

    @Inject
    private AtendimentoAudiosService serviceAtendimentoAudios;


    private Atendimento atendimentoView;

    private Cliente clienteView;

    private List<Atendimento> listAtendimento;

    private List<Departamento> listDepartamento;

    private String tipoFiltro = "CPF";

    private String filtro;
    private String filtroCpf;
    private String filtroProtocolo;

    private String textoCompleto;
    private Usuario usuario;


    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();

        if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR_BACKOFFICE)) {
            this.listDepartamento = this.serviceDepartamento.pesquisarDepartamentosAssociadosPorUsuario(this.usuario.getId());
        }

    }


    public void pesquisar() {

        System.out.println("Filtro: " + tipoFiltro);
        switch (this.tipoFiltro) {
            case "CPF":
                this.filtroProtocolo = null;
                this.filtroCpf = filtro;
                pesquisarFiltro();
                break;
            case "PROTOCOLO":
                this.filtroProtocolo = filtro;
                this.filtroCpf = null;
                pesquisarFiltro();
                break;
        }

    }

    private void pesquisarFiltro() {


        if (!this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR_BACKOFFICE)) {

            this.listAtendimento = this.serviceAtendimento.pesquisarAtendimentosSacFiltros(filtroProtocolo, filtroCpf, null);

        } else if (CollectionUtils.isNotEmpty(listDepartamento)) {

            List<Long> ids = this.listAtendimento.stream().filter(Objects::nonNull).map(Atendimento::getId).collect(Collectors.toList());
            this.listAtendimento = this.serviceAtendimento.pesquisarAtendimentosSacFiltros(filtroProtocolo, filtroCpf, ids);
        }

        if (CollectionUtils.isNotEmpty(listAtendimento)) {
            clienteView = this.serviceCliente.pesquisarClienteAtendimentoSacPorId(listAtendimento.get(0).getCliente().getId());
        }

    }

    public void onOpenAtendimento(Long atendimentoId) {

        this.atendimentoView = this.serviceAtendimento.pesquisarAtendimentoSacPorCodigo(atendimentoId, true);

        if (this.atendimentoView != null && this.atendimentoView.getAtendimentoPai())
            this.atendimentoView.setListaAudios(this.serviceAtendimentoAudios.pesquisarAtendimentoAudios(this.atendimentoView.getId()));
        else if (this.atendimentoView != null && StringUtils.isNotBlank(this.atendimentoView.getProtocoloPai()))
            this.atendimentoView.setListaAudios(this.serviceAtendimentoAudios.pesquisarAtendimentoAudiosPorProtocolo(this.atendimentoView.getProtocoloPai()));


        PrimeFaces.current().executeScript("PF('dlgVisualizarCompleto').show();");
        PrimeFaces.current().ajax().update("dlgVisualizarCompleto");

    }

    public String onCriarNovoAtendimento() {

        if (this.clienteView != null && StringUtils.isNotBlank(this.clienteView.getCpf())) {

            // Limpar a o CPF da sessão
            Faces.getSession().removeAttribute("atendimento_iniciado");

            //  Seta o CPF alvo
            Faces.getSession().setAttribute("cpf_atn", clienteView.getCpf());

            // 3. Redireciona
            return "/pages/atendimento/fichaAtendimentoSac?faces-redirect=true";

        }
        Messages.addGlobalError("Cliente não encontrado.");

        return null;
    }

    public void exibirTextoCompleto(String textoCompleto) {

        this.textoCompleto = textoCompleto;
    }

    public Atendimento getAtendimentoView() {
        return atendimentoView;
    }

    public void setAtendimentoView(Atendimento atendimentoView) {
        this.atendimentoView = atendimentoView;
    }

    public List<Atendimento> getListAtendimento() {
        return listAtendimento;
    }

    public void setListAtendimento(List<Atendimento> listAtendimento) {
        this.listAtendimento = listAtendimento;
    }

    public String getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(String tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Cliente getClienteView() {
        return clienteView;
    }

    public void setClienteView(Cliente clienteView) {
        this.clienteView = clienteView;
    }

    public String getTextoCompleto() {
        return textoCompleto;
    }
}

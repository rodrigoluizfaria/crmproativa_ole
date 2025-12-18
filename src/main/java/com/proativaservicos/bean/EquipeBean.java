package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.service.asynchronous.WebSocket3c;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class EquipeBean extends GenericBean {

    private static final long serialVersionUID = 1L;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    private EquipeService equipeService;
    @Inject
    private LojaService lojaService;
    @Inject
    private IntegracaoService integracaoService;
    private Equipe equipe;
    private List<Equipe> listEquipes;
    private List<Empresa> listEmpresa;
    private List<Usuario> listSupervisores;
    private List<Usuario> listUsuarios;
    private List<Loja> listLojas;
    private List<Long> listLojasIdsAssociados;
    private IntegracaoWs integracao;
    private DualListModel<Usuario> dualListSupervisores;
    private List<Usuario> listSupervisoresAssociados;
    private DualListModel<Usuario> dualListUsuarios;
    private List<Usuario> listUsuariosAssociados;
    private List<Bot> listBots;
    private TipoPaginaEnum tipoPagina;
    @Inject
    private AtendimentoBackofficeSingletonService serviceBko;
    @Inject
    private BotService serviceBot;

    @PostConstruct
    public void init() {

        this.equipe = new Equipe();
        equipe.setEmpresa(retornarEmpresaUsuarioSessao());
        pesquisar();
        setTipoPagina(TipoPaginaEnum.PESQUISA);

    }

    public void pesquisarIntegracao() {

        this.integracao = this.integracaoService.pesquisarIntegracoes(TipoIntegracaoEnum.AMBEC, retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

    }

    public void pesquisar() {

        if (equipe.getEmpresa() != null) {

            listEquipes = equipeService.pesquisarEquipes(equipe.getEmpresa().getId(), equipe);

        } else {

            listEquipes = equipeService.pesquisarEquipes(retornarEmpresaUsuarioSessao().getId(), equipe);

        }

        if (listEquipes.isEmpty()) {

            Messages.addInfo(null, "Nenhum registro Encontrado", "RT");
        }

    }


    public void editar(Equipe equipe) {

        inicializar();

        this.equipe = this.equipeService.pesquisarEquipe(equipe.getId());

        this.listBots = this.serviceBot.pesquisarBots();


        for (Usuario usuario : this.equipe.getListSupervisores()) {

            this.listSupervisoresAssociados.add(new Usuario(usuario.getId(), usuario.getNome()));

        }

        for (Usuario usuario : this.equipe.getListUsuarios()) {

            this.listUsuariosAssociados.add(new Usuario(usuario.getId(), usuario.getNome()));

        }

        for (Loja loja : this.equipe.getListLojas()) {

            this.listLojasIdsAssociados.add(loja.getId());

        }

        this.listUsuarios.addAll(this.listUsuariosAssociados);

        if (retornarUsuarioSessao().getEmpresa().isPossuiFiliais()) {

            preencherPickList();

        } else {

            preencherListas(retornarEmpresaUsuarioSessao());
        }

        this.tipoPagina = TipoPaginaEnum.CADASTRO;


    }

    public void novo() {

        inicializar();

        this.equipe = new Equipe();

        if (retornarUsuarioSessao().getEmpresa().isPossuiFiliais()) {

            preencherPickList();

        } else {


            preencherListas(retornarEmpresaUsuarioSessao());

        }

        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    private void inicializar() {

        this.listSupervisoresAssociados = new ArrayList<>();
        this.listUsuariosAssociados = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listLojas = new ArrayList<Loja>();
        this.listLojasIdsAssociados = new ArrayList<Long>();
        pesquisarIntegracao();

    }

    public void salvar() {

        try {

            List<Long> listaSupervisores = new ArrayList<>();
            List<Long> listaUsuariosAssociados = new ArrayList<>();
            List<Long> listaUsuariosNaoAssociados = new ArrayList<>();

            for (Usuario usuario : this.dualListSupervisores.getTarget()) {

                listaSupervisores.add(usuario.getId());

            }

            for (Usuario usuario : this.dualListUsuarios.getTarget()) {

                listaUsuariosAssociados.add(usuario.getId());

            }

            for (Usuario usuario : this.dualListUsuarios.getSource()) {

                listaUsuariosNaoAssociados.add(usuario.getId());

            }

            this.equipe.setListSupervisores(new ArrayList<Usuario>());

            if (!listaSupervisores.isEmpty()) {

                this.equipe.setListSupervisores(this.usuarioService.pesquisarUsuarios(listaSupervisores));

            }

            this.equipe.setListUsuarios(new ArrayList<Usuario>());

            if (!listaUsuariosAssociados.isEmpty()) {

                for (Usuario usuario : this.usuarioService.pesquisarUsuarios(listaUsuariosAssociados)) {

                    this.equipe.addUsuario(usuario);
                }

                this.serviceBko.inicializarListEquipeAtendimentos(null);
            }

            this.equipe.setListLojas(new ArrayList<Loja>());

            if (CollectionUtils.isNotEmpty(this.listLojasIdsAssociados)) {

                this.equipe.setListLojas(this.lojaService.pesquisarLojasPorIds(this.listLojasIdsAssociados));
            }


            this.equipe.setMetaMensal(Double.valueOf((this.equipe.getMetaMensal() == null) ? 0.0D : this.equipe.getMetaMensal().doubleValue()));

            this.equipe.setMetaSemanal(Double.valueOf((this.equipe.getMetaSemanal() == null) ? 0.0D : this.equipe.getMetaSemanal().doubleValue()));

            this.equipe.setMetaDiaria(Double.valueOf((this.equipe.getMetaDiaria() == null) ? 0.0D : this.equipe.getMetaDiaria().doubleValue()));

            this.equipe.setMetaMensalConcluida(Double.valueOf((this.equipe.getMetaMensalConcluida() == null) ? 0.0D : this.equipe.getMetaMensalConcluida().doubleValue()));

            this.equipe.setMetaSemanalConcluida(Double.valueOf((this.equipe.getMetaSemanalConcluida() == null) ? 0.0D : this.equipe.getMetaSemanalConcluida().doubleValue()));

            this.equipe.setMetaDiariaConcluida(Double.valueOf((this.equipe.getMetaDiariaConcluida() == null) ? 0.0D : this.equipe.getMetaDiariaConcluida().doubleValue()));

            if (this.equipe.getId() == null) {

                this.equipe.setUsuarioCadastro((Usuario) Faces.getSessionAttribute("usuario"));
                this.equipe.setDataCadastro(new Date(System.currentTimeMillis()));

                alterar((Serializable) this.equipe, false);

                this.equipe = new Equipe();
                this.listSupervisores = new ArrayList<>();
                this.listUsuarios = new ArrayList<>();

                this.dualListSupervisores = new DualListModel<Usuario>((List<Usuario>) CollectionUtils.subtract(this.listSupervisores, new ArrayList<Usuario>()), new ArrayList<Usuario>());

                this.dualListUsuarios = new DualListModel<Usuario>((List<Usuario>) CollectionUtils.subtract(this.listUsuarios, new ArrayList<Usuario>()), new ArrayList<Usuario>());

            } else {

                alterar((Serializable) this.equipe, false);

            }

            if (!listaUsuariosNaoAssociados.isEmpty()) {

                this.usuarioService.atualizarUsuariosEquipe(listaUsuariosNaoAssociados);

            }

            Messages.addGlobalInfo("Equipe salva com sucesso!", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void voltar() {

        this.equipe = new Equipe();
        this.equipe.setEmpresa(retornarEmpresaUsuarioSessao());
        pesquisar();

        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void trocarEmpresa() {

        this.listSupervisoresAssociados = new ArrayList<>();
        this.listUsuariosAssociados = new ArrayList<>();
        this.listSupervisores = new ArrayList<>();

        this.listLojas = new ArrayList<Loja>();
        this.listLojasIdsAssociados = new ArrayList<Long>();

        this.listUsuarios = new ArrayList<>();
        this.equipe.setListUsuarios(new ArrayList<>());
        this.equipe.setListSupervisores(new ArrayList<>());

        if (this.equipe.getId() != null) {

            this.usuarioService.atualizarUsuariosEquipe(this.equipe.getId());
        }

        preencherPickList();
    }


    private void preencherPickList() {

        if (this.equipe.getEmpresa() == null) {

            this.listSupervisores = usuarioService.pesquisarUsuariosPorNiveis(Arrays.asList(new PerfilUsuarioEnum[]{PerfilUsuarioEnum.SUPERVISOR}), retornarEmpresaMatrizUsuarioSessao().getId());

            this.listUsuarios = usuarioService.pesquisarUsuariosSemEquipe(retornarEmpresaMatrizUsuarioSessao().getId(), false);

            this.dualListSupervisores = new DualListModel<Usuario>((List<Usuario>) CollectionUtils.subtract(this.listSupervisores, this.listSupervisoresAssociados), this.listSupervisoresAssociados);

            this.dualListUsuarios = new DualListModel<Usuario>((List<Usuario>) CollectionUtils.subtract(this.listUsuarios, this.listUsuariosAssociados), this.listUsuariosAssociados);

            this.listLojas = this.lojaService.pesquisarLojas(retornarEmpresaMatrizUsuarioSessao().getId(), null);


        } else {

            preencherListas(this.equipe.getEmpresa());
        }


    }


    private void preencherListas(Empresa empresa) {


        this.listSupervisores = this.usuarioService.pesquisarUsuariosPorNiveis(Arrays.asList(new PerfilUsuarioEnum[]{PerfilUsuarioEnum.SUPERVISOR}), empresa.getId());

        this.listUsuarios.addAll(this.usuarioService.pesquisarUsuariosSemEquipe(empresa.getId(), false));

        this.dualListSupervisores = new DualListModel<Usuario>((List<Usuario>) CollectionUtils.subtract(this.listSupervisores, this.listSupervisoresAssociados), this.listSupervisoresAssociados);

        this.dualListUsuarios = new DualListModel<Usuario>((List<Usuario>) CollectionUtils.subtract(this.listUsuarios, this.listUsuariosAssociados), this.listUsuariosAssociados);

        this.listLojas = this.lojaService.pesquisarLojas(empresa.getId(), null);


    }


    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public DualListModel<Usuario> getDualListUsuarios() {
        return dualListUsuarios;
    }

    public void setDualListUsuarios(DualListModel<Usuario> dualListUsuarios) {
        this.dualListUsuarios = dualListUsuarios;
    }

    public DualListModel<Usuario> getDualListSupervisores() {
        return dualListSupervisores;
    }

    public void setDualListSupervisores(DualListModel<Usuario> dualListSupervisores) {
        this.dualListSupervisores = dualListSupervisores;
    }

    public List<Usuario> getListSupervisores() {
        return listSupervisores;
    }

    public void setListSupervisores(List<Usuario> listSupervisores) {
        this.listSupervisores = listSupervisores;
    }

    public List<Usuario> getListSupervisoresAssociados() {
        return listSupervisoresAssociados;
    }

    public void setListSupervisoresAssociados(List<Usuario> listSupervisoresAssociados) {
        this.listSupervisoresAssociados = listSupervisoresAssociados;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Usuario> getListUsuariosAssociados() {
        return listUsuariosAssociados;
    }

    public void setListUsuariosAssociados(List<Usuario> listUsuariosAssociados) {
        this.listUsuariosAssociados = listUsuariosAssociados;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }


    /**
     * @return the listLojas
     */
    public List<Loja> getListLojas() {
        return listLojas;
    }

    /**
     * @param listLojas the listLojas to set
     */
    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;
    }


    public List<Long> getListLojasIdsAssociados() {

        return listLojasIdsAssociados;
    }

    public void setListLojasIdsAssociados(List<Long> listLojasIdsAssociados) {
        this.listLojasIdsAssociados = listLojasIdsAssociados;
    }

    public List<Bot> getListBots() {
        return listBots;
    }

    public void setListBots(List<Bot> listBots) {
        this.listBots = listBots;
    }

    public IntegracaoWs getIntegracao() {
        return integracao;
    }

}

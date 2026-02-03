package com.proativaservicos.bean;

import com.proativaservicos.model.*;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.DepartamentoService;
import com.proativaservicos.service.asynchronous.produtoseguros.TipoPagamento;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class BackofficeFilaBean extends GenericBean implements Serializable {

    @Inject
    private AtendimentoService atendimentoService;

    @Inject
    private DepartamentoService departamentoService;

    private long idAtendimento;

    private long qtdAndamento;

    private long qtdConcluidos;

    private long qtdPendentes;
    private long qtdPrazoEstourado;

    private List<Atendimento> listaAtendimentos;

    private Atendimento atendimento;

    private List<Departamento> listDepartamentosAssociados;


    private Usuario usuario;

    private TipoPaginaEnum tipoPaginaEnum;

    private Atendimento atendimentoSelecionado;

    private String filtroProtocolo;

    private String filtroCpf;

    private String filtroStatus;

    private Long idDepartamento;

    private boolean exibirConcluidos;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        this.filtroStatus = "PENDENTE";

        if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR_BACKOFFICE)) {

            this.listDepartamentosAssociados = this.departamentoService.pesquisarDepartamentosAssociadosPorUsuario(this.usuario.getId());

        } else {

            this.listDepartamentosAssociados = this.departamentoService.listarDepartamentosAtivos(null);
        }

        //  carregarMocadaFila();
        pesquisar();
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;

    }

    public void pesquisar() {

        Boolean encerrado = null;

        if (StringUtils.isNotBlank(filtroStatus)) {
            encerrado = filtroStatus.equalsIgnoreCase("CONCLUIDO");
        }

        if (!this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR_BACKOFFICE)) {

            this.listaAtendimentos = atendimentoService.pesquisarAtendimentosSacFiltros(
                    filtroProtocolo,
                    filtroCpf,
                    encerrado,
                    idDepartamento
            );

        } else if (CollectionUtils.isNotEmpty(listaAtendimentos)) {

            List<Long> ids = listDepartamentosAssociados.stream().filter(Objects::nonNull).map(Departamento::getId).collect(Collectors.toList());
            this.listaAtendimentos = atendimentoService.pesquisarAtendimentosSacFiltrosDepartamento(
                    filtroProtocolo,
                    filtroCpf,
                    encerrado, ids, idDepartamento
            );

        }

        calcularKpis();
    }


    public long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public long getQtdAndamento() {
        return qtdAndamento;
    }

    public void setQtdAndamento(long qtdAndamento) {
        this.qtdAndamento = qtdAndamento;
    }

    public long getQtdConcluidos() {
        return qtdConcluidos;
    }

    public void setQtdConcluidos(long qtdConcluidos) {
        this.qtdConcluidos = qtdConcluidos;
    }

    public long getQtdPendentes() {
        return qtdPendentes;
    }

    public void setQtdPendentes(long qtdPendentes) {
        this.qtdPendentes = qtdPendentes;
    }

    public List<Atendimento> getListaAtendimentos() {
        return listaAtendimentos;
    }

    public void setListaAtendimentos(List<Atendimento> listaAtendimentos) {
        this.listaAtendimentos = listaAtendimentos;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void carregarMocadaFila() {

        listaAtendimentos = this.atendimentoService.pesquisarAtendimentosDerivados();

        if (CollectionUtils.isNotEmpty(listaAtendimentos)) {
            calcularKpis();

            for (Atendimento atendimento : listaAtendimentos) {
                System.out.println("Carregando atendimento: " + atendimento.getProtocolo());
            }

        } else {
            listaAtendimentos = new ArrayList<>();

            // --- MOCK DE DADOS PARA VISUALIZAR A TELA ---
            Atendimento a1 = new Atendimento(); // Sua classe concreta
            a1.setId(1L);
            a1.setProtocolo("SAC27981789");
            a1.setDataInicioAtendimento(new Date()); // Hoje
            a1.setNome("Maria Oliveira Costa");
            a1.setCpf("987.654.321-00");
            a1.setObservacao("Teste 123 - Cliente solicita aumento, mas sistema trava.");

            // Mocks de objetos aninhados (essencial para não dar NullPointer no XHTML)
            Motivo m = new Motivo();
            m.setDescricao("Solicitação de Aumento de Limite");
            a1.setMotivo(m);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            a1.setPrazoPrazoDemanda(calendar.getTime()); // +2 dias

            // Adiciona à lista
            listaAtendimentos.add(a1);

            // Item 2
            Atendimento a2 = new Atendimento();
            a2.setId(2L);
            a2.setProtocolo("SAC27981790");
            a2.setDataInicioAtendimento(new Date(System.currentTimeMillis() - 3600000)); // 1 hora atrás
            a2.setNome("João da Silva");
            a2.setCpf("123.456.789-99");
            a2.setObservacao("Aumento limite - Reclamação de demora na análise.");
            Motivo m2 = new Motivo();
            m2.setDescricao("Solicitação de Aumento de Limite");
            a2.setMotivo(m2);
            a2.setPrazoPrazoDemanda(new Date(System.currentTimeMillis() + 86400000)); // +1 dia
            listaAtendimentos.add(a2);
            // --------------------------------------------
        }
    }

    private void calcularKpis() {

        if (listaAtendimentos == null || listaAtendimentos.isEmpty()) {
            this.qtdPendentes = 0;
            this.qtdConcluidos = 0;
            this.qtdAndamento = 0;
            this.qtdPrazoEstourado = 0;
            return;
        }

        // 1. CONCLUÍDOS: Tudo que estiver marcado como encerrado = TRUE
        this.qtdConcluidos = (int) listaAtendimentos.stream()
                .filter(a -> Boolean.TRUE.equals(a.getDemandaEncerrada()))
                .count();

        // 2. EM ANDAMENTO: Não encerrado E com ação 'EM_ANALISE'
        this.qtdAndamento = (int) listaAtendimentos.stream()
                .filter(a -> !Boolean.TRUE.equals(a.getDemandaEncerrada())) // Garante que está aberto
                .filter(a -> a.getStatus() != null
                        && a.getStatus().getAcao() == AcaoStatusAtendimentoEnum.EM_ANALISE)
                .count();


        // Basicamente: Tudo que sobrou da fila
        this.qtdPendentes = (int) listaAtendimentos.stream()
                .filter(a -> !Boolean.TRUE.equals(a.getDemandaEncerrada())) // Garante que está aberto
                .filter(a -> a.getStatus() == null
                        || a.getStatus().getAcao() != AcaoStatusAtendimentoEnum.EM_ANALISE)
                .count();

        this.qtdPrazoEstourado = (int) listaAtendimentos.stream()
                .filter(a -> Boolean.TRUE.equals(a.isPrazoEstourado()))
                .count();


    }

    public void limparFiltros() {
        this.filtroProtocolo = null;
        this.filtroCpf = null;
        this.filtroStatus = null;
        pesquisar(); // Recarrega a lista completa
    }

    public void selecionarAtendimento(Long idAtendimento) {

        this.atendimentoSelecionado = this.atendimentoService.pesquisarAtendimentoSacPorCodigo(idAtendimento);
    }

    public TipoPaginaEnum getTipoPaginaEnum() {
        return tipoPaginaEnum;
    }

    public void setTipoPaginaEnum(TipoPaginaEnum tipoPaginaEnum) {
        this.tipoPaginaEnum = tipoPaginaEnum;
    }

    public Atendimento getAtendimentoSelecionado() {
        return atendimentoSelecionado;
    }

    public void setAtendimentoSelecionado(Atendimento atendimentoSelecionado) {
        this.atendimentoSelecionado = atendimentoSelecionado;
    }

    public String getFiltroProtocolo() {
        return filtroProtocolo;
    }

    public void setFiltroProtocolo(String filtroProtocolo) {
        this.filtroProtocolo = filtroProtocolo;
    }

    public String getFiltroCpf() {
        return filtroCpf;
    }

    public void setFiltroCpf(String filtroCpf) {
        this.filtroCpf = filtroCpf;
    }

    public String getFiltroStatus() {
        return filtroStatus;
    }

    public void setFiltroStatus(String filtroStatus) {
        this.filtroStatus = filtroStatus;
    }

    public List<Departamento> getListDepartamentosAssociados() {
        return listDepartamentosAssociados;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public long getQtdPrazoEstourado() {
        return qtdPrazoEstourado;
    }
}

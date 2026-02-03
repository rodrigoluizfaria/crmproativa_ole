package com.proativaservicos.bean;

import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Loja;
import com.proativaservicos.model.Produto;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.dto.ProdutividadeSacDto;
import com.proativaservicos.service.*;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoVisualizacaoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ProdutividadeConsistenciaBean extends GenericBean {


    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;


    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private AtendimentoBackofficeService serviceAtendimentoBackoffice;

    private List<ProdutividadeSacDto> listAtendimentoProdutividade;

    private Usuario usuarioLogado;

    private TipoVisualizacaoEnum tipoVisualizacao;

    private List<Equipe> listEquipes;

    private List<Usuario> listUsuarios;

    private List<Produto> listProdutos;

    private List<Loja> listLojas;

    private Long[] listEquipesSelecionadas;
    private Long[] listUsuariosSelecionadas;


    private Date dataInicio;
    private Date dataFim;

    private Long totalCpf;
    private Long totalAtendimento;

    private Long totalResolvidoN1;
    private Long totalResolvidoN2;

    private Long totalDerivado;
    private Long totalConcluida;
    private Long totalNoPrazo;
    private Long totalPrazoExcedido;
    private Long totalAberto;

    private Long usuarioLong;
    private Long equipeLong;
    private Long produtoLong, lojaLong;
    private Double totalPercentualFcr;
    private Long totalFcr;
    private Double totalPercentualNoPrazo;
    private Double totalPercentualPrazoExcedido;

    @PostConstruct
    public void init() {

        this.usuarioLogado = retornarUsuarioSessao();
        this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);
        inicializarEmpresa(retornarEmpresaMatrizUsuarioSessao());
        trocarEmpresa();

    }

    public void pesquisar() {

        this.listAtendimentoProdutividade = this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimentoSac(this.listEquipesSelecionadas, this.listUsuariosSelecionadas, this.produtoLong, this.lojaLong, this.dataInicio, this.dataFim, this.tipoVisualizacao);
        somarTotaisFooter(listAtendimentoProdutividade);
    }

    private void somarTotaisFooter(List<ProdutividadeSacDto> list) {

        if (CollectionUtils.isNotEmpty(list)) {

            // 1. Calcula as somas absolutas (Quantidades)
            this.totalAtendimento = list.stream().mapToLong(obj -> safeLong(obj.getQtdeAtendimento())).sum();
            this.totalCpf = list.stream().mapToLong(obj -> safeLong(obj.getQtdeCpf())).sum();
            this.totalResolvidoN2 = list.stream().mapToLong(obj -> safeLong(obj.getQtdeResolvidoN2())).sum();
            this.totalResolvidoN1 = list.stream().mapToLong(obj -> safeLong(obj.getQtdadeResolvidoN1())).sum();
            this.totalDerivado = list.stream().mapToLong(obj -> safeLong(obj.getQtdeDerivado())).sum();
            this.totalNoPrazo = list.stream().mapToLong(obj -> safeLong(obj.getQtdadeDemandaNoPrazo())).sum();
            this.totalPrazoExcedido = list.stream().mapToLong(obj -> safeLong(obj.getQtdadeDemandaPrazoEstourado())).sum();
            this.totalAberto = list.stream().mapToLong(obj -> safeLong(obj.getQtidadeEmAberto())).sum();

            // Totais de FCR
            this.totalFcr = list.stream().mapToLong(obj -> safeLong(obj.getQtdeFcr())).sum();

            this.totalConcluida = this.totalResolvidoN1 + this.totalResolvidoN2;

            // ---------------------------------------------------------------------
            // 2. CÁLCULO DOS PERCENTUAIS GERAIS (MÉDIAS PONDERADAS)
            // ---------------------------------------------------------------------

            // Cálculo do % Total de FCR
            if (this.totalAtendimento > 0) {
                // Convertemos para double para ter precisão decimal
                this.totalPercentualFcr = (double) this.totalFcr / this.totalAtendimento;
            } else {
                this.totalPercentualFcr = 0.0;
            }

            // DICA: Você provavelmente vai querer calcular os totais dos outros percentuais também:

            // Cálculo do % Total No Prazo
            if (this.totalAtendimento > 0) {
                this.totalPercentualNoPrazo = (double) this.totalNoPrazo / this.totalAtendimento;
            } else {
                this.totalPercentualNoPrazo = 0.0;
            }

            // Cálculo do % Total Excedido
            if (this.totalAtendimento > 0) {
                this.totalPercentualPrazoExcedido = (double) this.totalPrazoExcedido / this.totalAtendimento;
            } else {
                this.totalPercentualPrazoExcedido = 0.0;
            }
        } else {

            this.totalPercentualFcr = 0.0;
            this.totalAtendimento = 0L;
            this.totalCpf = 0L;
            this.totalResolvidoN2 = 0L;
            this.totalResolvidoN1 = 0L;
            this.totalDerivado = 0L;
            this.totalNoPrazo = 0L;
            this.totalPrazoExcedido = 0L;
            this.totalAberto = 0L;
            this.totalFcr = 0L;
            this.totalConcluida = 0L;
            this.totalPercentualPrazoExcedido = 0.0;
            this.totalPercentualNoPrazo = 0.0;
            this.totalPercentualFcr = 0.0;
        }
    }

    public Long safeLong(Long cont) {

        if (cont == null)
            return 0L;

        return cont;

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;
            this.listUsuarios = null;

        } else {

            if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

                if (CollectionUtils.isNotEmpty(listEquipes)) {

                    this.listLojas = this.serviceLoja.pesquisarLojasPorEquipes(this.listEquipes.stream().map(e -> e.getId()).collect(Collectors.toList()));
                } else {
                    this.listLojas = null;
                }

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

                this.listLojas = this.serviceLoja.pesquisarLojas(getEmpresa().getId(), TipoAcessoEnum.ATIVO);


            }

        }

    }


    public void changeEquipe() {

        try {

            this.listUsuariosSelecionadas = null;

            if (this.listEquipesSelecionadas == null) {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
                    this.listLojas = this.serviceLoja.pesquisarLojasPorEquipe(this.usuarioLogado.getId());

                } else {

                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
                    this.listLojas = this.serviceLoja.pesquisarLojas(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                }


            } else {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(Arrays.asList(this.listEquipesSelecionadas));
                this.listLojas = this.serviceLoja.pesquisarLojasPorEquipes(Arrays.asList(this.listEquipesSelecionadas));
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void changeSelectOneMenuEquipePorCampanha() {

        try {

            this.equipeLong = null;


            if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(),
                        TipoAcessoEnum.ATIVO);

            }


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public AtendimentoBackofficeService getServiceAtendimentoBackoffice() {
        return serviceAtendimentoBackoffice;
    }

    public void setServiceAtendimentoBackoffice(AtendimentoBackofficeService serviceAtendimentoBackoffice) {
        this.serviceAtendimentoBackoffice = serviceAtendimentoBackoffice;
    }

    public List<ProdutividadeSacDto> getListAtendimentoProdutividade() {
        return listAtendimentoProdutividade;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public TipoVisualizacaoEnum getTipoVisualizacao() {
        return this.tipoVisualizacao;
    }

    public void setTipoVisualizacao(TipoVisualizacaoEnum tipoVisualizacao) {
        this.tipoVisualizacao = tipoVisualizacao;
    }

    public TipoVisualizacaoEnum[] getTipoVisualizacoes() {
        return TipoVisualizacaoEnum.getVisualizacaoBackoffice();
    }


    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public List<Loja> getListLojas() {
        return listLojas;
    }

    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;
    }

    public Long[] getListEquipesSelecionadas() {
        return listEquipesSelecionadas;
    }

    public void setListEquipesSelecionadas(Long[] listEquipesSelecionadas) {
        this.listEquipesSelecionadas = listEquipesSelecionadas;
    }

    public Long[] getListUsuariosSelecionadas() {
        return listUsuariosSelecionadas;
    }

    public void setListUsuariosSelecionadas(Long[] listUsuariosSelecionadas) {
        this.listUsuariosSelecionadas = listUsuariosSelecionadas;
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


    public Long getUsuarioLong() {
        return usuarioLong;
    }

    public void setUsuarioLong(Long usuarioLong) {
        this.usuarioLong = usuarioLong;
    }

    public Long getEquipeLong() {
        return equipeLong;
    }

    public void setEquipeLong(Long equipeLong) {
        this.equipeLong = equipeLong;
    }

    public Long getLojaLong() {
        return lojaLong;
    }

    public void setLojaLong(Long lojaLong) {
        this.lojaLong = lojaLong;
    }

    public Long getProdutoLong() {
        return produtoLong;
    }

    public void setProdutoLong(Long produtoLong) {
        this.produtoLong = produtoLong;
    }

    public void setListAtendimentoProdutividade(List<ProdutividadeSacDto> listAtendimentoProdutividade) {
        this.listAtendimentoProdutividade = listAtendimentoProdutividade;
    }

    public Long getTotalCpf() {
        return totalCpf;
    }

    public void setTotalCpf(Long totalCpf) {
        this.totalCpf = totalCpf;
    }

    public Long getTotalAtendimento() {
        return totalAtendimento;
    }

    public void setTotalAtendimento(Long totalAtendimento) {
        this.totalAtendimento = totalAtendimento;
    }

    public Long getTotalResolvidoN1() {
        return totalResolvidoN1;
    }

    public void setTotalResolvidoN1(Long totalResolvidoN1) {
        this.totalResolvidoN1 = totalResolvidoN1;
    }

    public Long getTotalResolvidoN2() {
        return totalResolvidoN2;
    }

    public void setTotalResolvidoN2(Long totalResolvidoN2) {
        this.totalResolvidoN2 = totalResolvidoN2;
    }

    public Long getTotalDerivado() {
        return totalDerivado;
    }

    public void setTotalDerivado(Long totalDerivado) {
        this.totalDerivado = totalDerivado;
    }

    public Long getTotalConcluida() {
        return totalConcluida;
    }

    public void setTotalConcluida(Long totalConcluida) {
        this.totalConcluida = totalConcluida;
    }

    public Long getTotalNoPrazo() {
        return totalNoPrazo;
    }

    public void setTotalNoPrazo(Long totalNoPrazo) {
        this.totalNoPrazo = totalNoPrazo;
    }

    public Long getTotalPrazoExcedido() {
        return totalPrazoExcedido;
    }

    public void setTotalPrazoExcedido(Long totalPrazoExcedido) {
        this.totalPrazoExcedido = totalPrazoExcedido;
    }

    public Long getTotalAberto() {
        return totalAberto;
    }

    public void setTotalAberto(Long totalAberto) {
        this.totalAberto = totalAberto;
    }

    public Double getTotalPercentualFcr() {
        return totalPercentualFcr;
    }

    public void setTotalPercentualFcr(Double totalPercentualFcr) {
        this.totalPercentualFcr = totalPercentualFcr;
    }

    public Long getTotalFcr() {
        return totalFcr;
    }

    public void setTotalFcr(Long totalFcr) {
        this.totalFcr = totalFcr;
    }

    public Double getTotalPercentualNoPrazo() {
        return totalPercentualNoPrazo;
    }

    public void setTotalPercentualNoPrazo(Double totalPercentualNoPrazo) {
        this.totalPercentualNoPrazo = totalPercentualNoPrazo;
    }

    public Double getTotalPercentualPrazoExcedido() {


        return totalPercentualPrazoExcedido;
    }

    public String getTotalPercentualNoPrazoFormatado() {
        if (this.totalPercentualNoPrazo == null) {
            return "0%";
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);

        return nf.format(this.totalPercentualNoPrazo);
    }

    public String getTotalPercentualPrazoExcedidoFormatado() {
        if (this.totalPercentualPrazoExcedido == null) {
            return "0%";
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);

        return nf.format(this.totalPercentualPrazoExcedido);
    }


    public void setTotalPercentualPrazoExcedido(Double totalPercentualPrazoExcedido) {
        this.totalPercentualPrazoExcedido = totalPercentualPrazoExcedido;
    }
}

package com.proativaservicos.bean;

import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Loja;
import com.proativaservicos.model.Produto;
import com.proativaservicos.model.Usuario;
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

import java.math.BigDecimal;
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

    private List<?> listAtendimentoProdutividade;

    private Usuario usuarioLogado;

    private TipoVisualizacaoEnum tipoVisualizacao;

    private List<Equipe> listEquipes;

    private List<Usuario> listUsuarios;

    private List<Produto> listProdutos;

    private List<Loja> listLojas;

    private Long[] listEquipesSelecionadas;
    private Long[] listUsuariosSelecionadas;

    private Double totalCpf;
    private Date dataInicio;
    private Date dataFim;

    private Double totalAtendimento;
    private Double totalPropostas;

    private Double totalPago;
    private Double totalVendido;
    private Double totalContratos;

    private Long usuarioLong;
    private Long equipeLong;
    private Long produtoLong, lojaLong;

    @PostConstruct
    public void init() {

        this.usuarioLogado = retornarUsuarioSessao();
        this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);
        inicializarEmpresa(retornarEmpresaMatrizUsuarioSessao());
        trocarEmpresa();

    }

    public void pesquisar() {

        this.listAtendimentoProdutividade = this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimento(this.listEquipesSelecionadas, this.listUsuariosSelecionadas, this.produtoLong, this.lojaLong, retornarUsuarioSessao(), this.dataInicio, this.dataFim, this.tipoVisualizacao);
        somarTotaisFooter(listAtendimentoProdutividade);
    }

    private void somarTotaisFooter(List<?> list) {

        this.totalAtendimento = Double.valueOf(0.0D);
        this.totalCpf = Double.valueOf(0.0D);
        this.totalContratos = Double.valueOf(0.0D);
        this.totalPago = Double.valueOf(0.0D);
        this.totalPropostas = Double.valueOf(0.0D);
        this.totalVendido = Double.valueOf(0.0D);

        if (list != null && !list.isEmpty()) {

            for (Object obj : list) {

                this.totalAtendimento = Double.valueOf(this.totalAtendimento.doubleValue() + ((BigDecimal) ((Object[]) obj)[2]).doubleValue());
                this.totalCpf = Double.valueOf(this.totalCpf.doubleValue() + ((BigDecimal) ((Object[]) obj)[1]).doubleValue());
                this.totalPropostas = Double.valueOf(this.totalPropostas.doubleValue() + ((BigDecimal) ((Object[]) obj)[3]).doubleValue());
                this.totalContratos = Double.valueOf(this.totalContratos.doubleValue() + ((BigDecimal) ((Object[]) obj)[4]).doubleValue());
                this.totalVendido = Double.valueOf(this.totalVendido.doubleValue() + ((BigDecimal) ((Object[]) obj)[5]).doubleValue());
                this.totalPago = Double.valueOf(this.totalPago.doubleValue() + ((BigDecimal) ((Object[]) obj)[6]).doubleValue());

            }

        }


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

    public List<?> getListAtendimentoProdutividade() {
        return listAtendimentoProdutividade;
    }

    public void setListAtendimentoProdutividade(List<?> listAtendimentoProdutividade) {
        this.listAtendimentoProdutividade = listAtendimentoProdutividade;
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

    public Double getTotalCpf() {
        return totalCpf;
    }

    public void setTotalCpf(Double totalCpf) {
        this.totalCpf = totalCpf;
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

    public Double getTotalAtendimento() {
        return totalAtendimento;
    }

    public void setTotalAtendimento(Double totalAtendimento) {
        this.totalAtendimento = totalAtendimento;
    }

    public Double getTotalPropostas() {
        return totalPropostas;
    }

    public void setTotalPropostas(Double totalPropostas) {
        this.totalPropostas = totalPropostas;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public Double getTotalContratos() {
        return totalContratos;
    }

    public void setTotalContratos(Double totalContratos) {
        this.totalContratos = totalContratos;
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
}

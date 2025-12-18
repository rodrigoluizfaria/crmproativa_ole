package com.proativaservicos.bean;

import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class SupervisorProdutividadeAtendimentoBean extends GenericBean {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private AtendimentoService serviceAtendimento;

    private Long statusCampanhaLong;
    private Long campanhaLong;
    private Long usuarioLong;
    private Long equipeLong;

    private Long[] listCampanhasSelecionadas;
    private Long[] listEquipesSelecionadas;
    private Long[] listUsuariosSelecionadas;

    private Long produtoLong;


    private Date dataInicio;
    private Date dataFim;

    private Double totalAtendimento;
    private Double totalPropostas;

    private Double totalPago;
    private Double totalVendido;
    private Double totalContratos;

    private Double totalCpf;

    private TipoCampanhaEnum tipoCampanha;

    private List<StatusCampanha> listStatusCampanhas;


    private List<Campanha> listCampanhas;

    private List<Equipe> listEquipes;

    private List<Usuario> listUsuarios;

    private List<Produto> listProdutos;

    private List<StatusAtendimento> listStatusAtendimentos;

    private List<?> listAtendimentoProdutividade;

    private Usuario usuarioLogado;

    private TipoVisualizacaoEnum tipoVisualizacao;

    @PostConstruct
    public void init() {

        try {

            this.usuarioLogado = retornarUsuarioSessao();
            inicializarEmpresa();
            trocarEmpresa();

            this.listStatusCampanhas = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);
            this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void pesquisar() {

        this.listAtendimentoProdutividade = this.serviceAtendimento.pesquisarProdutividadeAtendimento(this.listCampanhasSelecionadas, this.statusCampanhaLong, this.tipoCampanha, this.listEquipesSelecionadas, this.listUsuariosSelecionadas, getEmpresa().getId(), this.produtoLong,
                retornarUsuarioSessao(), this.dataInicio, this.dataFim, this.tipoVisualizacao);

        somarTotaisFooter(this.listAtendimentoProdutividade);

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

    public void changeSelectOneMenuUsuarioEquipePorCampanha() {

        try {

            this.equipeLong = null;
            this.usuarioLong = null;

            if (this.campanhaLong == null) {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

                } else {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

                }

            } else {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanhaLong, this.usuarioLogado.getId());
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanhaSupervisor(this.campanhaLong, this.usuarioLogado.getId());

                } else {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanhaLong);
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanha(this.campanhaLong);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void changeSelectOneMenuEquipePorCampanha() {

        try {

            this.equipeLong = null;

            if (this.campanhaLong == null) {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(),
                            getEmpresa().getId());

                } else {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(),
                            TipoAcessoEnum.ATIVO);

                }

            } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanhaLong,
                        this.usuarioLogado.getId());

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanhaLong);

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void changeSelectOneMenuUsuarioEquipeStatusAtendimentoPorCampanha() {

        try {

            this.usuarioLong = null;
            this.equipeLong = null;


            if (this.campanhaLong == null) {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

                } else {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

                }
                this.listStatusAtendimentos = this.serviceStatusAtendimento
                        .pesquisarStatusAtendimentosPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            } else {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanhaLong, this.usuarioLogado.getId());
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanhaSupervisor(this.campanhaLong, this.usuarioLogado.getId());

                } else {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanhaLong);
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanha(this.campanhaLong);

                }

                this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorCampanha(this.campanhaLong);

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void changeUsuariosPorEquipe() {

        try {

            this.usuarioLong = null;

            if (this.equipeLong == null) {

                if (this.campanhaLong == null) {

                    if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                        this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

                    } else {

                        this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

                    }

                } else {

                    if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                        this.listUsuarios = this.serviceUsuario
                                .pesquisarUsuariosPorCampanhaSupervisor(this.campanhaLong, this.usuarioLogado.getId());

                    } else {

                        this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanha(this.campanhaLong);

                    }

                }

            } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipesSupervisor(this.equipeLong,
                        this.usuarioLogado.getId());

            } else {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(this.equipeLong);
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void changeProdutoPorCampanha() {

        try {

            this.produtoLong = null;

            if (this.campanhaLong == null) {

                this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            } else {

                this.listProdutos = this.serviceProduto.pesquisarProdutoPorCampanha(this.campanhaLong);
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void changeSelectOneMenuCampanha() {

        try {
            if (getEmpresa() != null && getEmpresa().getId() != null) {

                this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(),
                        this.statusCampanhaLong, this.tipoCampanha);
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanhas = null;
            this.listEquipes = null;
            this.listUsuarios = null;

        } else {

            if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(),
                        getEmpresa().getId());

                this.listCampanhas = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuarioLogado.getId(),
                        getEmpresa().getId());

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(usuarioLogado.getId(),
                        getEmpresa().getId());

                this.listStatusCampanhas = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(),
                        TipoAcessoEnum.ATIVO);

                this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
                this.listStatusCampanhas = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            }

        }

    }

    public void changeEquipe() {

        try {

            this.listUsuariosSelecionadas = null;

            if (this.listEquipesSelecionadas == null) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

            } else {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(Arrays.asList(this.listEquipesSelecionadas));

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void changeCampanha(boolean todas) {

        try {


            this.equipeLong = null;
            this.usuarioLong = null;
            this.listEquipesSelecionadas = null;
            this.listUsuariosSelecionadas = null;


            if (todas) {

                if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(usuarioLogado.getId(), getEmpresa().getId());

                } else {

                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                }

                this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                if (this.listCampanhasSelecionadas == null) {

                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

                    this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

                } else {

                    this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanhaSupervisor(Arrays.asList(this.listCampanhasSelecionadas), this.usuarioLogado.getId());

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(listCampanhasSelecionadas, this.usuarioLogado.getId());

                    this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorCampanha(Arrays.asList(this.listCampanhasSelecionadas));

                }

            } else if (this.listCampanhasSelecionadas == null || this.listCampanhasSelecionadas.length == 0) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(),
                        TipoAcessoEnum.ATIVO);
                this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(
                        retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            } else {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCampanha(Arrays.asList(this.listCampanhasSelecionadas));
                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(Arrays.asList(listCampanhasSelecionadas));
                this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorCampanha(Arrays.asList(this.listCampanhasSelecionadas));
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public Long getStatusCampanhaLong() {
        return statusCampanhaLong;
    }

    public void setStatusCampanhaLong(Long statusCampanhaLong) {
        this.statusCampanhaLong = statusCampanhaLong;
    }

    public Long getCampanhaLong() {
        return campanhaLong;
    }

    public void setCampanhaLong(Long campanhaLong) {
        this.campanhaLong = campanhaLong;
    }

    public Long getUsuarioLong() {
        return usuarioLong;
    }

    public void setUsuarioLong(Long usuarioLong) {
        this.usuarioLong = usuarioLong;
    }

    public Long getProdutoLong() {
        return produtoLong;
    }

    public void setProdutoLong(Long produtoLong) {
        this.produtoLong = produtoLong;
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

    public TipoCampanhaEnum getTipoCampanha() {
        return tipoCampanha;
    }

    public void setTipoCampanha(TipoCampanhaEnum tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public List<StatusCampanha> getListStatusCampanhas() {
        return listStatusCampanhas;
    }

    public void setListStatusCampanhas(List<StatusCampanha> listStatusCampanhas) {
        this.listStatusCampanhas = listStatusCampanhas;
    }

    public TipoCampanhaEnum[] getListTiposCampanhas() {
        return TipoCampanhaEnum.values();
    }


    public List<Campanha> getListCampanhas() {
        return listCampanhas;
    }

    public void setListCampanhas(List<Campanha> listCampanhas) {
        this.listCampanhas = listCampanhas;
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

    public void setEquipeLong(Long equipeLong) {
        this.equipeLong = equipeLong;
    }

    public Long getEquipeLong() {
        return equipeLong;
    }

    public TipoVisualizacaoEnum getTipoVisualizacao() {
        return tipoVisualizacao;
    }


    public void setTipoVisualizacao(TipoVisualizacaoEnum tipoVisualizacao) {
        this.tipoVisualizacao = tipoVisualizacao;
    }

    public TipoVisualizacaoEnum[] getTipoVisualizacoes() {
        return TipoVisualizacaoEnum.getVisualizacao();
    }

    public List<StatusAtendimento> getListStatusAtendimentos() {
        return listStatusAtendimentos;
    }

    public void setListStatusAtendimentos(List<StatusAtendimento> listStatusAtendimentos) {
        this.listStatusAtendimentos = listStatusAtendimentos;
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

    public Long[] getListCampanhasSelecionadas() {
        return listCampanhasSelecionadas;
    }

    public void setListCampanhasSelecionadas(Long[] listCampanhasSelecionadas) {
        this.listCampanhasSelecionadas = listCampanhasSelecionadas;
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

    public Double getTotalCpf() {
        return totalCpf;
    }

    public void setTotalCpf(Double totalCpf) {
        this.totalCpf = totalCpf;
    }


}

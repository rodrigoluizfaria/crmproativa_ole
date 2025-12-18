package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.ArrayUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
@ViewScoped
public class AcompanhamentoPropostasBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private GenericHistoricoAtendimento historicoAtendimento;
    private GenericAtendimento atendimento;
    private GenericAtendimento atendimentoVisualizar;

    private TipoCampanhaEnum tipoCampanha;

    private Long campanha;
    private Long campanhaPesquisa;
    private Long operador;
    private Long operadorAgendamento;
    private Long operadorPendencia;
    private Long operadorBloqueado;
    private Long produto;
    private Long equipe;
    private Long equipeCampanha;
    private Long equipeAgendamento;
    private Long equipePendencia;
    private Long equipePausa;
    private Long statusAtendimento;
    private Long statusAtendimentoAgendamento;
    private Long statusCampanha;
    private Long controlePausa;
    private Long controleUsuarioBloqueado;

    private String cpf;
    private String nome;
    private String adesao;
    private String protocolo;
    private String dialogModal;
    private String justificativa;

    private Date dataInicio;
    private Date dataFim;
    private Date dataInicioAgendamento;
    private Date dataFimAgendamento;
    private Date dataAgendamento;

    private List<StatusAtendimento> listStatusAtendimento;
    private List<StatusAtendimento> listStatusAtendimentoAgendados;
    private List<Equipe> listEquipes;
    private List<Usuario> listOperador;

    private List<Campanha> listCampanha;
    private List<Produto> listProdutos;
    private List<FormaPagamento> listFormaPagamento;
    private List<StatusTelefone> listStatusTelefone;
    private List<StatusCampanha> listStatusCampanha;
    private List<Usuario> listOperadoresEditar;
    private List<Loja> listLojas;

    private List<AtendimentoAudios> listAudiosAtendimentos;

    private List<?> listHistoricosAtendimentos;
    private List<?> listHistoricosAgendamentos;
    private List<?> listPendencias;
    private List<?> listPausas;
    private List<?> listUsuariosBloqueados;

    private List<Object[]> listPendenciasSelecionadas;
    private List<Object[]> listAtendimentos;
    private List<Object[]> listAgendamentosSelecionados;
    private List<Object[]> listCampanhaEquipes;

    Map<Long, StatusAtendimento> mapStatusAtendimentos;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private StatusContratoService serviceStatusContrato;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private TelefoneService serviceTelefone;


    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private FormaPagamentoService serviceformaPagamento;

    @Inject
    private LojaService serviceLoja;


    @Inject
    private FichaAtendimentoBean fichaAtendimentoBean;

    @Inject
    private AtendimentoAudiosService serviceAtendimentoAudio;

    @Inject
    private ControleUsuarioBloqueadoService serviceControleUsuarioBloqueado;


    @PostConstruct
    public void init() {

        try {

            inicializarEmpresa();

            this.usuario = retornarUsuarioSessao();

            setEmpresa(this.usuario.getEmpresa());

            trocarEmpresa();

            this.listStatusAtendimento = serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listStatusTelefone = this.serviceStatusTelefone.pesquisarStatusTelefonesPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.dataInicio = new Date(System.currentTimeMillis());
            this.dataFim = new Date(System.currentTimeMillis());

            this.dataInicioAgendamento = new Date(System.currentTimeMillis());
            this.dataFimAgendamento = new Date(System.currentTimeMillis());

            this.mapStatusAtendimentos = new HashedMap<Long, StatusAtendimento>();
            this.listStatusAtendimentoAgendados = new ArrayList<StatusAtendimento>();

            this.listCampanhaEquipes = new ArrayList<Object[]>();
            this.listPendenciasSelecionadas = new ArrayList<Object[]>();

            for (StatusAtendimento status : this.listStatusAtendimento) {

                this.mapStatusAtendimentos.put(status.getId(), status);

                if (Arrays.<AcaoStatusAtendimentoEnum>asList(new AcaoStatusAtendimentoEnum[]{
                        AcaoStatusAtendimentoEnum.AGENDAR, AcaoStatusAtendimentoEnum.AGENDAR_DUAS_HORAS,
                        AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL, AcaoStatusAtendimentoEnum.AGENDAR_QUATRO_HORAS,
                        AcaoStatusAtendimentoEnum.AGENDAR_SEIS_HORAS,
                        AcaoStatusAtendimentoEnum.AGENDAR_VINTE_QUATRO_HORAS}).contains(status.getAcao())) {

                    this.listStatusAtendimentoAgendados.add(status);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onConciliar(Long idAtendimento) {

        try {

            if (idAtendimento != null) {

                this.listAudiosAtendimentos = this.serviceAtendimentoAudio.pesquisarAtendimentoAudios(idAtendimento);

                PrimeFaces.current().executeScript("PF('dlgAudio').show();");
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }


    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanha = null;
            this.listEquipes = null;
            this.listOperador = null;
            this.listProdutos = null;

        } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listCampanha = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

        } else {

            this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
            this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

        }

        this.listHistoricosAgendamentos = null;

    }

    public void pesquisarAtendimentos() {

        try {

            this.listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorNomeCpfProduto(this.cpf, this.nome, this.adesao, this.protocolo, this.campanhaPesquisa, this.equipe, this.operador,
                    this.statusAtendimento, null, this.dataInicio, this.dataFim, this.usuario, produto, getEmpresa().getId());


        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public void pesquisarMeusAtendimentos() {

        try {

            this.listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorNomeCpfProduto(this.cpf, this.nome, this.adesao, this.protocolo, this.campanhaPesquisa, this.equipe, this.usuario.getId(),
                    this.statusAtendimento, null, this.dataInicio, this.dataFim, this.usuario, produto, getEmpresa().getId());


        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisarMeusAgendamentos() {

        try {

            if (getEmpresa() == null)
                return;

            this.listHistoricosAgendamentos = this.serviceHistorico.pesquisarAgendamentos(this.usuario.getId(), this.equipeAgendamento, this.statusAtendimentoAgendamento, this.cpf, this.dataInicioAgendamento, this.dataFimAgendamento, this.usuario, getEmpresa().getId(), true);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }


    }

    public void pesquisarAgendamentos() {

        try {

            if (getEmpresa() == null)
                return;

            this.listHistoricosAgendamentos = this.serviceHistorico.pesquisarAgendamentos(this.operadorAgendamento, this.equipeAgendamento, this.statusAtendimentoAgendamento, this.cpf, this.dataInicioAgendamento, this.dataFimAgendamento, this.usuario, getEmpresa().getId(), true);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onChangeEquipe(Long idEquipe) {

        try {

            if (idEquipe != null) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEquipes(idEquipe);

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(idEquipe, getEmpresa().getId());

            } else {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void visualizarAtendimento(Long id) {
        try {

            this.listHistoricosAtendimentos = new ArrayList<>();
            this.atendimentoVisualizar = this.serviceAtendimento.pesquisarAtendimentoContrato(id);

            if (CollectionUtils.isNotEmpty(this.atendimentoVisualizar.getListaHistoricos())) {

                this.historicoAtendimento = this.atendimentoVisualizar.getListaHistoricos().get(0);
                this.historicoAtendimento.setStatusAtendimento(this.mapStatusAtendimentos.get(this.historicoAtendimento.getStatusAtendimento().getId()));

            } else {

                this.historicoAtendimento = new HistoricoAtendimento();
                this.historicoAtendimento.setDataCadastro(new Date(System.currentTimeMillis()));

            }

            this.historicoAtendimento.setUsuario(this.atendimentoVisualizar.getUsuarioAlteracao());

            for (GenericTelefone telefone : this.atendimentoVisualizar.getListaTelefones()) {

                StatusTelefone statusTelefone = telefone.getStatusTelefone();

                if (statusTelefone != null) {

                    telefone.setStatusTelefone(new StatusTelefone(statusTelefone.getId(), statusTelefone.getDescricao(), statusTelefone.getParametro()));
                }

            }

            iniciarAtendimento();

            if (this.atendimentoVisualizar.getContrato() == null) {

                this.atendimentoVisualizar.setContrato(new Contrato());

                this.atendimentoVisualizar.getContrato().setDataCadastro(new Date(System.currentTimeMillis()));

                this.atendimentoVisualizar.getContrato().setUsuarioCadastro(retornarUsuarioSessao());

                StatusContrato statusHistorico = this.serviceStatusContrato.pesquisarStatusContratoPorAcao(
                        AcaoStatusContratoEnum.INICIADA, TipoStatusContratoEnum.HISTORICO, null,
                        retornarEmpresaMatrizUsuarioSessao().getId());

                if (statusHistorico == null || statusHistorico.getId() == null) {

                    statusHistorico = new StatusContrato();
                    statusHistorico.setAcao(AcaoStatusContratoEnum.INICIADA);
                    statusHistorico.setDescricao("Iniciada Histórico");
                    statusHistorico.setTipoStatus(TipoStatusContratoEnum.HISTORICO);
                    statusHistorico.setDataAlteracao(new Date());
                    statusHistorico.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                    statusHistorico.setUsuarioCadastro(retornarUsuarioSessao());
                    statusHistorico.setUsuarioAlteracao(retornarUsuarioSessao());
                    statusHistorico.setAtivo(TipoAcessoEnum.ATIVO);
                    inserir((Serializable) statusHistorico);

                }

                this.atendimentoVisualizar.getContrato().setStatusContrato(statusHistorico);

                StatusContrato statusPendencia = this.serviceStatusContrato.pesquisarStatusContratoPorAcao(
                        AcaoStatusContratoEnum.INICIADA, TipoStatusContratoEnum.PENDENCIA, null,
                        retornarEmpresaMatrizUsuarioSessao().getId());

                if (statusPendencia == null || statusPendencia.getId() == null) {

                    statusPendencia = new StatusContrato();
                    statusPendencia.setAcao(AcaoStatusContratoEnum.INICIADA);
                    statusPendencia.setDescricao("Iniciada Pendência");
                    statusPendencia.setTipoStatus(TipoStatusContratoEnum.PENDENCIA);
                    statusPendencia.setDataAlteracao(new Date());
                    statusPendencia.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                    statusPendencia.setUsuarioCadastro(retornarUsuarioSessao());
                    statusPendencia.setUsuarioAlteracao(retornarUsuarioSessao());
                    statusPendencia.setAtivo(TipoAcessoEnum.ATIVO);
                    inserir((Serializable) statusPendencia);
                }
                this.atendimentoVisualizar.getContrato().setStatusPendencia(statusPendencia);

                // QUESTIONARIO

            }

            this.listProdutos = this.serviceProduto.pesquisarProdutoPorCampanha(this.atendimentoVisualizar.getCampanha().getId());

            this.listFormaPagamento = this.serviceformaPagamento
                    .pesquisarFormaPagamentosPorCampanha(this.atendimentoVisualizar.getCampanha().getId());

            this.listOperadoresEditar = this.serviceUsuario
                    .pesquisarUsuariosPorCampanhaDto(this.atendimentoVisualizar.getCampanha().getId());

            if (this.atendimentoVisualizar.getCampanha() != null
                    && this.atendimentoVisualizar.getCampanha().getInstituicaoFinanceira() != null) {

                this.listLojas = this.serviceLoja.pesquisarLojas(this.atendimentoVisualizar.getEmpresa().getId(),
                        this.atendimentoVisualizar.getCampanha().getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);

            } else if (this.atendimentoVisualizar.getInstituicaoFinanceira() != null) {

                this.listLojas = this.serviceLoja.pesquisarLojas(this.atendimentoVisualizar.getEmpresa().getId(),
                        this.atendimentoVisualizar.getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void iniciarAtendimento() {

        if (this.atendimentoVisualizar.getProduto() == null)
            this.atendimentoVisualizar.setProduto(new Produto());
        if (this.atendimentoVisualizar.getFormaPagamento() == null)
            this.atendimentoVisualizar.setFormaPagamento(new FormaPagamento());

    }

    public void buscarHistoricosAtendimentos() {

        try {

            validarCpf();

            this.listHistoricosAtendimentos = this.serviceHistorico.pesquisarHIstoricoPorCpf(
                    this.atendimentoVisualizar.getCpf().trim(), retornarEmpresaUsuarioSessao().getId());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void validarCpf() throws ProativaException {

        if (this.atendimentoVisualizar.getCpf() == null || this.atendimentoVisualizar.getCpf().trim().isEmpty()) {

            throw new ProativaException("CPF Deve ser informado");
        }
    }

    public void gerarNovaProposta() {

        try {

            if (this.atendimentoVisualizar != null) {

                this.fichaAtendimentoBean.criarNovaProposta(this.atendimentoVisualizar);

                Faces.redirect(Faces.getRequestContextPath() + "/pages/atendimento/fichaAtendimento2.jsf");

            }
        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void abrirDlgTrocaAgendamento() {

        if (this.listAgendamentosSelecionados.size() > 0) {

            PrimeFaces.current().executeScript("PF('dlgTrocaAgendamento').show();");

        } else {

            Messages.addGlobalWarn("Nenhum Agendamento foi selecionado.", new Object[0]);
        }

    }

    public void trocarOperadorAgendamento() {
        try {

            if (this.listAgendamentosSelecionados.size() > 0) {

                Usuario operadorNovo = this.serviceUsuario.pesquisar(Usuario.class, operadorAgendamento);

                for (Object agendamentos : listAgendamentosSelecionados) {

                    Object[] arrayAgendamento = (Object[]) agendamentos;

                    GenericAtendimento atendimento = this.serviceAtendimento.pesquisarAtendimentosComHistorico(
                            Long.valueOf(((BigInteger) arrayAgendamento[1]).longValue()));

                    for (GenericHistoricoAtendimento historico : atendimento.getListaHistoricos()) {

                        if (historico.getDataVisualizado() == null && historico.getAgendamento() != null)
                            historico.setDataVisualizado(new Date());

                    }
                    // ESQUEMA BKP
                    HistoricoAtendimento historico = new HistoricoAtendimento();
                    historico.setAgendamento(dataAgendamento);
                    historico.setObservacao("Agendamento transferidor por::: " + this.usuario.getNome());
                    historico.setUsuario(operadorNovo);
                    historico.setDataCadastro(new Date(System.currentTimeMillis()));
                    historico.setStatusAtendimento(this.serviceStatusAtendimento.pesquisarStatusAtendimentoPorAcao(
                            AcaoStatusAtendimentoEnum.AGENDAR, retornarEmpresaMatrizUsuarioSessao().getId()));
                    atendimento.adicionarHistorico((GenericHistoricoAtendimento) historico);
                    alterar((Serializable) atendimento);

                }

                this.operadorAgendamento = null;
                pesquisarAgendamentos();
                PrimeFaces.current().executeScript("PF('dlgTrocaAgendamento').hide();");
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void pesquisarCampanhasEquipe() {

        this.listCampanhaEquipes = this.serviceCampanha.pesquisarCampanhasStatusTipoEquipe(getEmpresa().getId(),
                this.campanha, this.tipoCampanha, this.statusCampanha, this.equipeCampanha,
                this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR) ? this.usuario.getId() : null);

    }

    public void exportarCampanhaCsv() {

        try {
            exportarArquivoCsv(Arrays.asList(new String[]{"Campanha", "Tipo Campanha", "Descrição", "Equipe"}),
                    this.listCampanhaEquipes, "relatorioCampanhas.csv");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void updateSelectEquipeUsuarioCampanha() {

        try {

            if (this.campanha == null) {

                if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(),
                            getEmpresa().getId());

                } else {
                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(),
                            TipoAcessoEnum.ATIVO);
                }

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanha,
                        this.usuario.getId());

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanha);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void updateSelectCampanha() {

        try {

            if (getEmpresa() != null) {

                this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(),
                        this.statusCampanha, this.tipoCampanha);
            }

        } catch (Exception e) {
            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisarPendencias() {

        try {

            this.listPendencias = this.serviceAtendimento.pesquisarPendencias(this.equipePendencia,
                    this.operadorPendencia, this.usuario, getEmpresa().getId());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void trocarEquipe(Long idTrocaEquipe) {

        try {
            if (idTrocaEquipe != null) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEquipes(idTrocaEquipe);

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(),
                        getEmpresa().getId());

            } else {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void reativarPendencia() {
        try {

            if (this.listPendenciasSelecionadas.size() > 0) {

                List<Long> listOperadoresAtendimentosPendentes = new ArrayList<Long>();

                for (Object obj : this.listPendenciasSelecionadas) {

                    Object[] dados = (Object[]) obj;

                    listOperadoresAtendimentosPendentes.add(Long.valueOf((Integer) dados[0]).longValue());
                }

                StatusAtendimento statusAtendimentoReativar = this.serviceStatusAtendimento
                        .pesquisarAtendimentoReativador(retornarEmpresaMatrizUsuarioSessao().getId());

                if (statusAtendimentoReativar == null) {

                    statusAtendimentoReativar = new StatusAtendimento();
                    statusAtendimentoReativar.setAcao(AcaoStatusAtendimentoEnum.FIM_FILA);
                    statusAtendimentoReativar.setDescricao("REATIVADO::");
                    statusAtendimentoReativar.setAtivo(TipoAcessoEnum.ATIVO);
                    inserir((Serializable) statusAtendimentoReativar);
                }

                List<Atendimento> listAtendimentosPendentes = this.serviceAtendimento
                        .pesquisarPendenciasPorOperadores(listOperadoresAtendimentosPendentes);

                for (Atendimento atendimento : listAtendimentosPendentes) {

                    HistoricoAtendimento historico = new HistoricoAtendimento();
                    historico.setAtendimento((GenericAtendimento) atendimento);
                    historico.setStatusAtendimento(statusAtendimentoReativar);
                    historico.setObservacao("Atendimento foi Reativado");
                    historico.setDataCadastro(new Date());
                    historico.setUsuario(this.usuario);
                    inserir((Serializable) historico);
                    atendimento.setUsuarioOcupado(null);
                    atendimento.setStatus(null);
                    alterar((Serializable) atendimento);
                }

                pesquisarPendencias();
                Messages.addGlobalInfo("Pendência Reativada com sucesso!!", new Object[0]);

            } else {

                Messages.addGlobalWarn("Nenhuma Pendência foi selecionada", new Object[0]);
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisarPausas() {

        try {

            this.listPausas = this.serviceControlePausa.pesquisarControlePausa(this.equipePausa,
                    new Date(System.currentTimeMillis()), this.usuario, getEmpresa().getId());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisarUsuariosBloqueados() {

        try {

            if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listUsuariosBloqueados = this.serviceControleUsuarioBloqueado.pesquisarUsuariosBloqueados(
                        this.operadorBloqueado, this.usuario.getId(), getEmpresa().getId());

            } else {

                this.listUsuariosBloqueados = this.serviceControleUsuarioBloqueado
                        .pesquisarUsuariosBloqueados(this.operadorBloqueado, getEmpresa().getId());
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void desbloquearPausa() {
        try {

            ControlePausa controle = this.serviceControlePausa.pesquisarControlePausa(this.controlePausa);

            if (controle != null) {

                controle.setDataLiberacao(new Date());
                controle.setJustificativa(justificativa);
                controle.setDataRetorno(new Date());
                controle.setUsuarioLiberacao(this.usuario);
                this.serviceControlePausa.alterar(controle);
                // RETORNAR PAUSA DISCADORA
            }

            pesquisarPausas();
            this.justificativa = null;
            PrimeFaces.current().executeScript("PF('dlgDesbloqueioUsuario').hide();");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void desbloquearUsuario() {

        try {

            ControleUsuarioBloqueado usuarioBloqueado = (ControleUsuarioBloqueado) this.serviceControleUsuarioBloqueado
                    .pesquisar(ControleUsuarioBloqueado.class, this.controleUsuarioBloqueado);

            if (usuarioBloqueado != null) {

                usuarioBloqueado.setDataLiberacao(new Date());
                usuarioBloqueado.setUsuarioLiberacao(this.usuario);
                usuarioBloqueado.setJustificativa(this.justificativa);
                this.serviceControleUsuarioBloqueado.alterar(usuarioBloqueado);

            }

            pesquisarUsuariosBloqueados();
            this.justificativa = null;
            PrimeFaces.current().executeScript("PF('dlgDesbloqueioUsuario').hide();");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public String converterTempoCorrido(Date dataCorrida) {

        return String.valueOf(DateUtil.builder(dataCorrida, new Date()).calcularDiferencaDatas(DataEnum.MINUTO)
                .getDataNumerico().intValue());

    }

    public void salvarAtendimento() {

        try {

            if (this.atendimentoVisualizar != null) {

                salvarAtendimento(atendimentoVisualizar, this.historicoAtendimento, this.usuario, false);
                pesquisarAtendimentos();
                iniciarAtendimento();
                PrimeFaces.current().executeScript("PF('dlgVisualizarEditar').hide();");

            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onExportarCsv() {

        exportarAtendimentos(true);
    }

    private void exportarAtendimentos(boolean exportarCompleto) {

        try {

            int qtidadeMaxTel = 0;


            Map<Long, List<Object>> mapaTelefones = criarListTelefones();

            List<Object[]> listAtendimentosGerados = new ArrayList<>();

            for (Object[] atendimento : this.listAtendimentos) {

                List<Object> listTelefones = mapaTelefones.get(Long.valueOf((((BigInteger) atendimento[0]).longValue())));

                if (!exportarCompleto) {

                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);

                }

                if (CollectionUtils.isNotEmpty(listTelefones)) {

                    if (listTelefones.size() / 3 > qtidadeMaxTel)
                        qtidadeMaxTel = listTelefones.size() / 3;

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, listTelefones.toArray(new Object[listTelefones.size()])));
                    continue;
                }

                listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, new Object[0]));

            }

            ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[]{"ID Atendimento", "Campanha", "Nome", "CPF", "Status Atendimento", "Último Contato", "Operador", "Adesão", "Quantidade Parcela", "Valor Parcela", "Valor Liberado", "Protocolo", "Status do Contrato"}));

            if (exportarCompleto) {

                listCabecalho.add("Valor Emprestimo");
                listCabecalho.add("Entidade Principal");
                listCabecalho.add("Entidade Secundária");
                listCabecalho.add("Orgão Principal");
                listCabecalho.add("Orgão Secudário");
                listCabecalho.add("Outras Informações");

            }

            for (int i = 0; i < qtidadeMaxTel; i++) {

                listCabecalho.add("DDD");
                listCabecalho.add("telefone");
                listCabecalho.add("Status Telefone");
            }

            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmm");

            exportarArquivoCsv(listCabecalho, listAtendimentosGerados, "relatorio_backoffice_" + formato.format(new Date()) + ".csv");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    private Map<Long, List<Object>> criarListTelefones() {

        List<Long> idsAtendimentos = new ArrayList<>();

        List<Object[]> listTelefones = new ArrayList<Object[]>();

        for (Object[] atendimento : this.listAtendimentos) {

            idsAtendimentos.add(Long.valueOf(((BigInteger) atendimento[0]).longValue()));

            if (idsAtendimentos.size() == 1000) {

                listTelefones.addAll(this.serviceTelefone.pesquisarTelefonesPorAtendimentos(idsAtendimentos));

                idsAtendimentos = new ArrayList<>();
            }

        }

        if (idsAtendimentos.size() > 0) {

            listTelefones.addAll(this.serviceTelefone.pesquisarTelefonesPorAtendimentos(idsAtendimentos));
            idsAtendimentos = new ArrayList<Long>();
        }

        Map<Long, List<Object>> mapTelefones = new HashMap<Long, List<Object>>();

        if (CollectionUtils.isNotEmpty(listTelefones)) {

            for (Object[] telefone : listTelefones) {

                ((List<Object>) mapTelefones.computeIfAbsent(Long.valueOf(((BigInteger) telefone[0]).longValue()),
                        t -> new ArrayList<Object>())).add((telefone[1] == null) ? "" : telefone[1]);

                ((List<Object>) mapTelefones.get(Long.valueOf(((BigInteger) telefone[0]).longValue())))
                        .add((telefone[2] == null) ? "" : telefone[2]);

                ((List<Object>) mapTelefones.get(Long.valueOf(((BigInteger) telefone[0]).longValue())))
                        .add((telefone[3] == null) ? "" : telefone[3]);
            }

        }

        return mapTelefones;

    }

    public Long getCampanha() {
        return campanha;
    }

    public void setCampanha(Long campanha) {
        this.campanha = campanha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getEquipe() {
        return equipe;
    }

    public void setEquipe(Long equipe) {
        this.equipe = equipe;
    }

    public Long getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(Long statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAdesao() {
        return adesao;
    }

    public void setAdesao(String adesao) {
        this.adesao = adesao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
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

    public List<StatusAtendimento> getListStatusAtendimento() {
        return listStatusAtendimento;
    }

    public void setListStatusAtendimento(List<StatusAtendimento> listStatusAtendimento) {
        this.listStatusAtendimento = listStatusAtendimento;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Usuario> getListOperador() {
        return listOperador;
    }

    public void setListOperador(List<Usuario> listOperador) {
        this.listOperador = listOperador;
    }

    public List<Campanha> getListCampanha() {
        return listCampanha;
    }

    public void setListCampanha(List<Campanha> listCampanha) {
        this.listCampanha = listCampanha;
    }

    public List<Object[]> getListAtendimentos() {
        return listAtendimentos;
    }

    public void setListAtendimentos(List<Object[]> listAtendimentos) {
        this.listAtendimentos = listAtendimentos;
    }

    public Long getOperador() {
        return operador;
    }

    public void setOperador(Long operador) {
        this.operador = operador;
    }

    public List<?> getListHistoricosAtendimentos() {
        return listHistoricosAtendimentos;
    }

    public void setListHistoricosAtendimentos(List<?> listHistoricosAtendimentos) {
        this.listHistoricosAtendimentos = listHistoricosAtendimentos;
    }

    public List<?> getListHistoricosAgendamentos() {
        return listHistoricosAgendamentos;
    }

    public void setListHistoricosAgendamentos(List<?> listHistoricosAgendamentos) {
        this.listHistoricosAgendamentos = listHistoricosAgendamentos;
    }

    public GenericAtendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(GenericAtendimento atendimento) {
        this.atendimento = atendimento;
    }

    public GenericHistoricoAtendimento getHistoricoAtendimento() {
        return historicoAtendimento;
    }

    public void setHistoricoAtendimento(GenericHistoricoAtendimento historico) {
        this.historicoAtendimento = historico;
    }

    public List<StatusAtendimento> getListStatusAtendimentoAgendados() {
        return listStatusAtendimentoAgendados;
    }

    public void setListStatusAtendimentoAgendados(List<StatusAtendimento> listStatusAtendimentoAgendados) {
        this.listStatusAtendimentoAgendados = listStatusAtendimentoAgendados;
    }

    public Long getCampanhaPesquisa() {
        return campanhaPesquisa;
    }

    public void setCampanhaPesquisa(Long campanhaAgendamento) {
        this.campanhaPesquisa = campanhaAgendamento;
    }

    public Long getOperadorAgendamento() {
        return operadorAgendamento;
    }

    public void setOperadorAgendamento(Long operadorAgendamento) {
        this.operadorAgendamento = operadorAgendamento;
    }

    public Long getEquipeAgendamento() {
        return equipeAgendamento;
    }

    public void setEquipeAgendamento(Long equipeAgendamento) {
        this.equipeAgendamento = equipeAgendamento;
    }

    public Long getStatusAtendimentoAgendamento() {
        return statusAtendimentoAgendamento;
    }

    public void setStatusAtendimentoAgendamento(Long statusAtendimentoAgendamento) {
        this.statusAtendimentoAgendamento = statusAtendimentoAgendamento;
    }

    public Date getDataInicioAgendamento() {
        return dataInicioAgendamento;
    }

    public void setDataInicioAgendamento(Date dataInicioAgendamento) {
        this.dataInicioAgendamento = dataInicioAgendamento;
    }

    public Date getDataFimAgendamento() {
        return dataFimAgendamento;
    }

    public void setDataFimAgendamento(Date dataFimAgendamento) {
        this.dataFimAgendamento = dataFimAgendamento;
    }

    public GenericAtendimento getAtendimentoVisualizar() {
        return atendimentoVisualizar;
    }

    public void setAtendimentoVisualizar(GenericAtendimento atendimentoVisualizar) {
        this.atendimentoVisualizar = atendimentoVisualizar;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public List<FormaPagamento> getListFormaPagamento() {
        return listFormaPagamento;
    }

    public void setListFormaPagamento(List<FormaPagamento> listFormaPagamento) {
        this.listFormaPagamento = listFormaPagamento;
    }

    public List<Usuario> getListOperadoresEditar() {
        return listOperadoresEditar;
    }

    public void setListOperadoresEditar(List<Usuario> listOperadoresEditar) {
        this.listOperadoresEditar = listOperadoresEditar;
    }

    public Map<Long, StatusAtendimento> getMapStatusAtendimentos() {
        return mapStatusAtendimentos;
    }

    public void setMapStatusAtendimentos(Map<Long, StatusAtendimento> mapStatusAtendimentos) {
        this.mapStatusAtendimentos = mapStatusAtendimentos;
    }

    public List<Loja> getListLojas() {
        return listLojas;
    }

    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;

    }

    public List<Object[]> getListAgendamentosSelecionados() {
        return listAgendamentosSelecionados;
    }

    public void setListAgendamentosSelecionados(List<Object[]> listAgendamentosSelecionados) {
        this.listAgendamentosSelecionados = listAgendamentosSelecionados;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Long getStatusCampanha() {
        return statusCampanha;
    }

    public void setStatusCampanha(Long statusCampanha) {
        this.statusCampanha = statusCampanha;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public Long getEquipeCampanha() {
        return equipeCampanha;
    }

    public void setEquipeCampanha(Long equipeCampanha) {
        this.equipeCampanha = equipeCampanha;
    }

    public TipoCampanhaEnum[] getTipoCampanhas() {
        return TipoCampanhaEnum.values();
    }

    public void setTipoCampanha(TipoCampanhaEnum tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public TipoCampanhaEnum getTipoCampanha() {
        return tipoCampanha;
    }

    public List<Object[]> getListCampanhaEquipes() {
        return listCampanhaEquipes;
    }

    public void setListCampanhaEquipes(List<Object[]> listCampanhaEquipes) {
        this.listCampanhaEquipes = listCampanhaEquipes;
    }

    public Long getOperadorPendencia() {
        return operadorPendencia;
    }

    public void setOperadorPendencia(Long operadorPendencia) {
        this.operadorPendencia = operadorPendencia;
    }

    public Long getEquipePendencia() {
        return equipePendencia;
    }

    public void setEquipePendencia(Long equipePendencia) {
        this.equipePendencia = equipePendencia;
    }

    public List<?> getListPendencias() {
        return listPendencias;
    }

    public void setListPendencias(List<?> listPendencias) {
        this.listPendencias = listPendencias;
    }

    public List<Object[]> getListPendenciasSelecionadas() {
        return listPendenciasSelecionadas;
    }

    public void setListPendenciasSelecionadas(List<Object[]> listPendenciasSelecionadas) {
        this.listPendenciasSelecionadas = listPendenciasSelecionadas;
    }

    public Long getEquipePausa() {
        return equipePausa;
    }

    public void setEquipePausa(Long equipePausa) {
        this.equipePausa = equipePausa;
    }

    public List<?> getListPausas() {
        return listPausas;
    }

    public void setListPausas(List<?> listPausas) {
        this.listPausas = listPausas;
    }

    public Long getControlePausa() {
        return controlePausa;
    }

    public String getDialogModal() {
        return dialogModal;
    }

    public void setDialogModal(String dialogModal) {
        this.dialogModal = dialogModal;
    }

    public void setControlePausa(Long controlePausa) {
        this.controlePausa = controlePausa;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Long getOperadorBloqueado() {
        return operadorBloqueado;
    }

    public void setOperadorBloqueado(Long operadorBloqueado) {
        this.operadorBloqueado = operadorBloqueado;
    }

    public List<?> getListUsuariosBloqueados() {
        return listUsuariosBloqueados;
    }

    public void setListUsuariosBloqueados(List<?> listUsuariosBloqueados) {
        this.listUsuariosBloqueados = listUsuariosBloqueados;
    }

    public Long getControleUsuarioBloqueado() {
        return controleUsuarioBloqueado;
    }

    public void setControleUsuarioBloqueado(Long controleUsuarioBloqueado) {
        this.controleUsuarioBloqueado = controleUsuarioBloqueado;
    }

    public List<StatusTelefone> getListStatusTelefone() {
        return listStatusTelefone;
    }

    public void setListStatusTelefone(List<StatusTelefone> listStatusTelefone) {
        this.listStatusTelefone = listStatusTelefone;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public Integer getIdade() {

        if (this.atendimentoVisualizar != null && this.atendimentoVisualizar.getDataNascimento() != null) {

            return Integer.valueOf(DateUtil.builder(this.atendimentoVisualizar.getDataNascimento()).calcularIdade());

        }
        return null;
    }

    public List<AtendimentoAudios> getListAudiosAtendimentos() {
        return listAudiosAtendimentos;
    }

    public void setListAudiosAtendimentos(List<AtendimentoAudios> listAudiosAtendimentos) {
        this.listAudiosAtendimentos = listAudiosAtendimentos;
    }

}

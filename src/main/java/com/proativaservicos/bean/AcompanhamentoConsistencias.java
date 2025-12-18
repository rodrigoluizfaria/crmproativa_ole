package com.proativaservicos.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.ArquivoUtil;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AcompanhamentoConsistencias extends GenericBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private GenericHistoricoAtendimento historicoAtendimento;
	private HistoricoAtendimentoBackoffice historicoAtendimentoBko;
	private GenericAtendimento atendimento;
	private AtendimentoBackoffice atendimentoVisualizar;

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

	private Long lojaNaoTrabalhada;
	private Long consistenciaNaoTrabalhada;
	private String cpfNaoTrabalhado;
	private String adesaoNaoTrabalhada;

	private String cpf;
	private String nome;
	private String adesao;
	
	private String cpfPendente;
	private String nomePendente;
	private String adesaoPendente;
	
	private String protocolo;
	private String dialogModal;
	private String justificativa;

	private Date dataInicio;
	private Date dataFim;
	private Date dataInicioAgendamento;
	private Date dataFimAgendamento;
	private Date dataAgendamento;
	
	private Date dataInicioPanel;
	private Date dataFimPanel;

	private List<StatusAtendimento> listStatusAtendimento;
	private List<StatusAtendimento> listStatusAtendimentoAgendados;

	private List<Motivo> listMotivos;
	private List<SubMotivo> listSubMotivos;

	private List<Equipe> listEquipes;
	private List<Usuario> listOperador;

	private List<Campanha> listCampanha;
	private List<Produto> listProdutos;
	private List<FormaPagamento> listFormaPagamento;
	private List<StatusTelefone> listStatusTelefone;

	private List<Usuario> listOperadoresEditar;
	private List<Loja> listLojas;

	private List<AtendimentoAudios> listAudiosAtendimentos;

	private List<?> listHistoricosAtendimentos;
	private List<?> listHistoricosAgendamentos;
	private List<?> listHistoricosMeusAgendamentos;
	private List<?> listPendencias;

	private List<?> listMinhasPendencias;
	private List<?> listNaoTrabalhados;
	private List<?> listPausas;
	private List<?> listUsuariosBloqueados;

	private List<Object[]> listAtendimentos;

	Map<Long, StatusAtendimento> mapStatusAtendimentos;

	@Inject
	private StatusAtendimentoService serviceStatusAtendimento;

	@Inject
	private MotivoService serviceMotivo;

	@Inject
	private SubMotivoService serviceSubMotivo;

	@Inject
	private EquipeService serviceEquipe;

	@Inject
	private UsuarioService serviceUsuario;

	@Inject
	private CampanhaService serviceCampanha;

	@Inject
	private HistoricoAtendimentoService serviceHistorico;

	@Inject
	private HistoricoAtendimentoBackofficeService serviceHistoricoBko;

	@Inject
	private ProdutoService serviceProduto;

	@Inject
	private AtendimentoBackofficeService serviceAtendimentoBackoffice;

	@Inject
	private ConsistenciaService serviceConsistencias;

	@Inject
	private LojaService serviceLoja;

	private BigDecimal total;

	@Inject
	private FichaAtendimentoBackofficeBean fichaAtendimentoBean;

	@Inject
	private AtendimentoAudiosService serviceAtendimentoAudio;

	@Inject
	private UsuarioLogadoService serviceUsuarioLogado;
	
	private List<Consistencia> listConsistencias;

	private Long consistencia;

	private Long loja;

	private Boolean tratada;

	private StreamedContent fileRar;

	private Long idAtendimento;

	private StatusAtendimento status;

	private Motivo motivo;

	private SubMotivo subMotivo;

	private StreamedContent file;

	private DonutChartModel donutModelStatusAtendimento;
	private DonutChartModel donutModelMotivo;
	private DonutChartModel donutSubmodelMotivo;
	private List<?> listStatusPainel;
	private List<?> listMotioPainel;
	private List<?> listSubmotioPainel;
	
	private List<?> listQuantidadeConsistencia;

	private BarChartModel stackedBarModelConsistencia;
	
	private String filtro;

	@PostConstruct
	public void init() {

		try {

			inicializarEmpresa();

			this.usuario = retornarUsuarioSessao();

			setEmpresa(this.usuario.getEmpresa());

			trocarEmpresa();
			this.filtro = "DIA";
			this.dataInicioPanel = new Date();
			this.dataFimPanel  = new Date();
			
			inicializarStatus();
			inicializarLojas();
	
			this.listConsistencias = this.serviceConsistencias.pesquisarConsistenciaPorEmpresa(getEmpresa().getId());

			this.dataInicio = new Date(System.currentTimeMillis());
			this.dataFim = new Date(System.currentTimeMillis());
			this.dataInicioAgendamento = new Date(System.currentTimeMillis());
			this.dataFimAgendamento = new Date(System.currentTimeMillis());

			this.mapStatusAtendimentos = new HashedMap<Long, StatusAtendimento>();
			this.listStatusAtendimentoAgendados = new ArrayList<StatusAtendimento>();
			this.listHistoricosAtendimentos = new ArrayList<>();

			for (StatusAtendimento status : this.listStatusAtendimento) {

				this.mapStatusAtendimentos.put(status.getId(), status);

				if (Arrays.<AcaoStatusAtendimentoEnum>asList(new AcaoStatusAtendimentoEnum[] {
						AcaoStatusAtendimentoEnum.AGENDAR, AcaoStatusAtendimentoEnum.AGENDAR_DUAS_HORAS,
						AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL, AcaoStatusAtendimentoEnum.AGENDAR_QUATRO_HORAS,
						AcaoStatusAtendimentoEnum.AGENDAR_SEIS_HORAS,
						AcaoStatusAtendimentoEnum.AGENDAR_VINTE_QUATRO_HORAS }).contains(status.getAcao())) {

					this.listStatusAtendimentoAgendados.add(status);

				}

			}

			

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	private void inicializarLojas() {

		if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR) || this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR_BACKOFFICE)) {

			this.listLojas = this.serviceLoja.pesquisarLojasPorEquipe(this.serviceUsuario.pesquisarEquipeUsuario(this.usuario.getId()));

		} else {

			this.listLojas = this.serviceLoja.pesquisarLojas(getEmpresa().getId());
		}
	}

	private void inicializarStatus() {

		this.listStatusAtendimento = serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

		this.listMotivos = serviceMotivo.pesquisarMotivosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

		this.listSubMotivos = this.serviceSubMotivo.pesquisarSubMotivoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());
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

			this.listCampanha = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuario.getId(),getEmpresa().getId());

			this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(),getEmpresa().getId());

			this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(),getEmpresa().getId());

			this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(),TipoAcessoEnum.ATIVO);

		} else {

			this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
			this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(),	TipoAcessoEnum.ATIVO);
			this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
			this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(),TipoAcessoEnum.ATIVO);

		}

		this.listHistoricosAgendamentos = null;

	}

	public void gerarFooterTotal() {

		if (CollectionUtils.isNotEmpty(this.listAtendimentos)) {

			this.total = BigDecimal.ZERO;

			for (Object[] objects : this.listAtendimentos) {

				if (objects[7] != null) {

					this.total = this.total.add((BigDecimal) objects[7]);

				}

			}

		}
	}

	public void gerarFooterTotalMinhaLista() {

		if (CollectionUtils.isNotEmpty(this.listMinhasPendencias)) {

			this.total = BigDecimal.ZERO;

			for (Object object : this.listMinhasPendencias) {

				Object[] objects = (Object[]) object;

				if (objects[7] != null) {

					this.total = this.total.add((BigDecimal) objects[7]);

				}

			}

		}
	}

	public void pesquisarAtendimentosBackoffice() {

		try {

			this.total = null;

			this.listAtendimentos = this.serviceAtendimentoBackoffice.pesquisarAtendimentos(this.cpf, this.nome,
					this.adesao, this.protocolo, this.equipe, this.operador, this.consistencia,
					this.status != null ? this.status.getId() : null, this.dataInicio, this.dataFim, this.usuario,
					this.produto, this.tratada, this.loja, this.motivo != null ? this.motivo.getId() : null,
					this.subMotivo != null ? this.subMotivo.getId() : null);

			gerarFooterTotal();

		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}
	}

	public void pesquisarAgendamentos() {

		try {

			if (getEmpresa() == null)
				return;

			this.listHistoricosAgendamentos = this.serviceHistoricoBko.pesquisarAgendamentos(this.operadorAgendamento,
					this.equipeAgendamento, this.statusAtendimentoAgendamento, this.cpf, this.dataInicioAgendamento,
					this.dataFimAgendamento, this.usuario, getEmpresa().getId(), true);

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}

	}

	public void pesquisarMeusAgendamentos() {

		try {

			if (getEmpresa() == null)
				return;

			this.listHistoricosMeusAgendamentos = this.serviceHistoricoBko.pesquisarAgendamentos(this.usuario.getId(),
					this.equipeAgendamento, this.statusAtendimentoAgendamento, this.cpf, this.dataInicioAgendamento,
					this.dataFimAgendamento, this.usuario, getEmpresa().getId(), true);

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}

	}

	public void onChangeTab(@SuppressWarnings("rawtypes") TabChangeEvent event) {

		if (event.getTab().getId().equals("tabAgendamento")) {

			this.dataAgendamento = new Date();
			this.dataFimAgendamento = new Date();
			pesquisarMeusAgendamentos();

		} else if (event.getTab().getId().equals("tabPendencia")) {

			try {

				this.listPendencias = this.serviceAtendimentoBackoffice.pesquisarPendencias(null, this.usuario.getId(),	this.usuario,retornarIdsLojas(), getEmpresa().getId(),null,null,null, false);

			} catch (Exception e) {

				e.printStackTrace();
				Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
			}

		}
	}

	public void pesquisarPendencias() {

		try {

			
			this.listPendencias = this.serviceAtendimentoBackoffice.pesquisarPendencias(this.equipePendencia,this.operadorPendencia, this.usuario,retornarIdsLojas(), getEmpresa().getId(),this.adesaoPendente,this.cpfPendente,this.nomePendente, false);

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}
	}
	
	public List<Long> retornarIdsLojas(){
		
		if(this.usuario.getPerfil().equals(PerfilUsuarioEnum.COORDENADOR) || this.usuario.getPerfil().equals(PerfilUsuarioEnum.ADMIN) ) {
			return null;
		
		}if(CollectionUtils.isNotEmpty(this.listLojas)) {
			return this.listLojas.stream().map(l -> l.getId()).collect(Collectors.toList());
		}
		
		return null;
	}

	public void pesquisarMinhaLista() {

		/*
		 * try {
		 * 
		 * this.listMinhasPendencias =
		 * this.serviceAtendimentoBackoffice.pesquisarMinhaListaAtendimento(
		 * this.usuario.getId(), this.cpf, this.adesao, this.nome, this.dataInicio,
		 * this.dataFim);
		 * 
		 * gerarFooterTotalMinhaLista();
		 * 
		 * } catch (Exception e) {
		 * 
		 * e.printStackTrace();
		 * Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new
		 * Object[0]); }
		 */
	}

	public void pesquisarAtendimentosNaoTrabalhados() {

		try {

			if(this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR) && CollectionUtils.isEmpty(this.listLojas)) {
				
				this.listNaoTrabalhados = null;
		
			}else {
			
			this.listNaoTrabalhados = this.serviceAtendimentoBackoffice.pesquisarNaoTrabalhados(
					this.consistenciaNaoTrabalhada, this.lojaNaoTrabalhada, retornarIdsLojas(), this.cpfNaoTrabalhado,
					this.adesaoNaoTrabalhada, this.usuario, this.getEmpresa().getId());
			}
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

	public void onSelectMotivo() {

		if (this.motivo != null && this.motivo.getId() != null) {

			this.motivo.setListSubMotivos(new ArrayList<>());

			this.listSubMotivos = this.serviceSubMotivo.pesquisarSubMotivosPorMotivo(this.motivo.getId(),null);

		} else {

			this.listSubMotivos = this.serviceSubMotivo
					.pesquisarSubMotivoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

		}
	}

	public void onChangeStatus() {

		this.motivo = null;
		this.subMotivo = null;

		if (this.status != null) {

			this.status = this.serviceStatusAtendimento.pesquisarStatusAtendimentosListaMotivos(this.status.getId(),null);

			if (this.status != null) {

				this.listMotivos = this.status.getListMotivos();
				this.listSubMotivos = new ArrayList<>();

			}

		} else {

			inicializarStatus();

		}

	}

	public void visualizarAtendimento(Long id) {

		try {

			this.listHistoricosAtendimentos = new ArrayList<>();

			this.atendimentoVisualizar = this.serviceAtendimentoBackoffice.pesquisarAtendimentoPorId(id);

			if (CollectionUtils.isNotEmpty(this.atendimentoVisualizar.getListHistoricos())) {

				this.historicoAtendimentoBko = this.atendimentoVisualizar.getListHistoricos().get(0);
				this.historicoAtendimentoBko
						.setStatus(this.mapStatusAtendimentos.get(this.historicoAtendimentoBko.getStatus().getId()));

			} else {

				this.historicoAtendimentoBko = new HistoricoAtendimentoBackoffice();
				this.historicoAtendimentoBko.setDataCadastro(new Date(System.currentTimeMillis()));

			}

			if (this.atendimentoVisualizar.getAtendimento() != null)
				this.atendimentoVisualizar.getAtendimento().setListaContratosEfetivados(null);

			if (this.atendimentoVisualizar.getAtendimento() != null
					&& CollectionUtils.isNotEmpty(this.atendimentoVisualizar.getAtendimento().getListHistoricos())) {

				this.historicoAtendimento = this.atendimentoVisualizar.getAtendimento().getListHistoricos().get(0);
				this.historicoAtendimento.setStatusAtendimento((this.historicoAtendimentoBko.getStatus() != null
						&& this.historicoAtendimentoBko.getStatus().getId() != null)
								? this.mapStatusAtendimentos.get(this.historicoAtendimentoBko.getStatus().getId())
								: null);

			} else {
				this.historicoAtendimento = new HistoricoAtendimento();
				this.historicoAtendimento.setDataCadastro(new Date(System.currentTimeMillis()));
			}

			this.historicoAtendimento.setUsuario(this.atendimentoVisualizar.getUsuarioAlteracao());

			for (GenericTelefone telefone : this.atendimentoVisualizar.getListTelefones()) {

				StatusTelefone statusTelefone = telefone.getStatusTelefone();

				if (statusTelefone != null) {

					telefone.setStatusTelefone(new StatusTelefone(statusTelefone.getId(), statusTelefone.getDescricao(),
							statusTelefone.getParametro()));
				}

			}

			iniciarAtendimento();

			if (CollectionUtils.isEmpty(this.atendimentoVisualizar.getListConsistencias())) {

			}

			this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(this.usuario.getId(),
					TipoAcessoEnum.ATIVO);

			// this.listOperadoresEditar =
			// this.serviceUsuario.pesquisarUsuarios(this.atendimentoVisualizar.getCampanha().getId());

			if (this.atendimentoVisualizar.getInstituicaoFinanceira() != null) {

				this.listLojas = this.serviceLoja.pesquisarLojas(this.usuario.getId(),
						this.atendimentoVisualizar.getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);
			}

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}

	}

	private void iniciarAtendimento() {

		if (this.atendimentoVisualizar.getProduto() == null)
			this.atendimentoVisualizar.setProduto(null);

		if (CollectionUtils.isEmpty(this.atendimentoVisualizar.getListHistoricos()))
			this.atendimentoVisualizar.setListHistoricos(new ArrayList<HistoricoAtendimentoBackoffice>());

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

				this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanha,	this.usuario.getId());

			} else {

				this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanha);

			}

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

				this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(),getEmpresa().getId());

			} else {

				this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
			}

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}

	}

	public String converterTempoCorrido(Date dataCorrida) {

		return String.valueOf(DateUtil.builder(dataCorrida, new Date()).calcularDiferencaDatas(DataEnum.MINUTO)
				.getDataNumerico().intValue());

	}

	public void onExportarCsv() {

		exportarAtendimentos(true);
	}

	@SuppressWarnings("unchecked")
	private void exportarAtendimentos(boolean exportarCompleto) {

		try {

			List<Object[]> listAtendimentosGerados = new ArrayList<>();
			int totalConsistencia = 0;

			for (Object[] atendimento : this.listAtendimentos) {

				Object[] objectAtend = new Object[17];

				StringBuilder consistencias = new StringBuilder();

				for (int i = 0; i < atendimento.length; i++) {

					if (i < 16)
						objectAtend[i] = atendimento[i];

					else {

						List<Object[]> listConsistencia = (List<Object[]>) atendimento[16];

						if (CollectionUtils.isNotEmpty(listConsistencia) && listConsistencia.size() > totalConsistencia)
							totalConsistencia = listConsistencia.size();

						for (Object[] objConsis : listConsistencia) {

							if (objConsis != null) {

								for (Object obj : objConsis) {

									if (obj != null) {

										if (obj instanceof Boolean)

											consistencias.append(
													(Boolean.valueOf(obj.toString()).equals(Boolean.TRUE) ? "SIM"
															: "NÃO"));

										else
											consistencias.append(String.valueOf(obj.toString()));
									} else {

										consistencias.append("-");

									}
									consistencias.append(";");

								}
							}
						}
					}
				}

				objectAtend[16] = consistencias.toString();
				listAtendimentosGerados.add(objectAtend);

			}

			ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[] { "id_atendimento","atendimento_integrado", "nome", "cpf", "adesão", "data_alteração", "serviço", "valor",	"resp_corban ", "cod_loja", "usuário", "status", "motivo", "submotivo", "produto", "arquivo" }));

			for (int i = 0; i < totalConsistencia; i++) {

				listCabecalho.add("id_consistência");
				listCabecalho.add("descrição_consistência");
				listCabecalho.add("instiuição");
				listCabecalho.add("código_consistência");
				listCabecalho.add("significado");
				listCabecalho.add("convênio");
				listCabecalho.add("prazo");
				listCabecalho.add("resp");
				listCabecalho.add("ação");
				listCabecalho.add("tratada");
				listCabecalho.add("arquivo");
			}

			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmm");

			exportarArquivoCsv(listCabecalho, listAtendimentosGerados,"relatorio_consistrencia_" + formato.format(new Date()) + ".csv");

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	@SuppressWarnings("unchecked")
	private String exportarAtendimentosString(boolean exportarCompleto) {

		try {

			List<Object[]> listAtendimentosGerados = new ArrayList<>();
			int totalConsistencia = 0;

			for (Object[] atendimento : this.listAtendimentos) {

				Object[] objectAtend = new Object[17];

				StringBuilder consistencias = new StringBuilder();

				for (int i = 0; i < atendimento.length; i++) {

					if (i < 16)
						objectAtend[i] = atendimento[i];

					else {

						List<Object[]> listConsistencia = (List<Object[]>) atendimento[16];

						if (CollectionUtils.isNotEmpty(listConsistencia) && listConsistencia.size() > totalConsistencia)
							totalConsistencia = listConsistencia.size();

						for (Object[] objConsis : listConsistencia) {

							if (objConsis != null) {

								for (Object obj : objConsis) {

									if (obj != null) {

										if (obj instanceof Boolean)

											consistencias.append(
													(Boolean.valueOf(obj.toString()).equals(Boolean.TRUE) ? "SIM"
															: "NÃO"));

										else
											consistencias.append(String.valueOf(obj.toString()));
									} else {

										consistencias.append("-");

									}
									consistencias.append(";");

								}
							}
						}
					}
				}

				objectAtend[16] = consistencias.toString();
				listAtendimentosGerados.add(objectAtend);

			}

			ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[] { "id_atendimento","atendimento_integrado", "nome", "cpf", "adesão", "data_alteração", "serviço", "valor",	"resp_corban ", "cod_loja", "usuário", "status", "motivo", "submotivo", "produto", "arquivo" }));

			for (int i = 0; i < totalConsistencia; i++) {

				listCabecalho.add("id_consistência");
				listCabecalho.add("descrição_consistência");
				listCabecalho.add("instiuição");
				listCabecalho.add("código_consistência");
				listCabecalho.add("significado");
				listCabecalho.add("convênio");
				listCabecalho.add("prazo");
				listCabecalho.add("resp");
				listCabecalho.add("ação");
				listCabecalho.add("tratada");
				listCabecalho.add("arquivo");
			}

			return ArquivoUtil.gerarArquivoCSVString(listCabecalho, listAtendimentosGerados);
			// exportarArquivoCsv(listCabecalho,
			// listAtendimentosGerados,"relatorio_consistrencia_" + formato.format(new
			// Date()) + ".csv");

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

		return null;

	}

	public void onBaixarAnexo(String adesao, String srtArquivo) {

		try {

			if (StringUtils.isNotBlank(adesao) && StringUtils.isNotBlank(srtArquivo)) {

				Type listType = new TypeToken<ArrayList<String>>() {
				}.getType();

				ArrayList<String> arquivos = new Gson().fromJson(srtArquivo, listType);

				List<File> listArquivos = new ArrayList<File>();

				for (String s : arquivos) {

					File arquivo = new File(System.getProperty("user.home") + File.separator + "proativa"
							+ File.separator + "consistencias" + File.separator + StringUtils.deleteWhitespace(adesao)
							+ File.separator + s.trim());

					if (arquivo.exists()) {
						listArquivos.add(arquivo);
					}
				}

				if (CollectionUtils.isNotEmpty(listArquivos)) {

					File rar = Utils.compactarRar(listArquivos, criarDiretorios("arquivos_baixados"), "anexados");

					if (rar == null || (rar != null && !rar.exists()))
						throw new ProativaException("Não foi possivel compactar os arquivos.");

					byte[] array = Files.readAllBytes(Paths.get(rar.getAbsolutePath()));

					this.fileRar = DefaultStreamedContent.builder().name("anexados.rar").contentType("application/x-rar-compressed").stream(() -> new ByteArrayInputStream(array)).build();

					rar.delete();

				}

			}
		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	public String adiantarFichaAtendimentoNaoTrabalado() {
			
		try {
		
			AtendimentoBackoffice atendimento = null;

		if (this.idAtendimento != null) {
			
			 atendimento = this.serviceAtendimentoBackoffice.pesquisarAtendimentoOcupadoPorId(idAtendimento);
						
			if(validarUsuarioAtendimento(atendimento)) {
				
				return this.fichaAtendimentoBean.adiantarAtendimento(this.idAtendimento);
			
			}else {
				
				pesquisarAtendimentosNaoTrabalhados();
				throw new ProativaException("Atendimento pendente ou em atendimento para: "+(atendimento.getUsuarioEmAtendimento()==null?"":atendimento.getUsuarioEmAtendimento().getNome()));
			}
		}
		}catch (ProativaException e) {
			
			Messages.addGlobalWarn(e.getMessage(), new Object());
		}

		return null;
	}

	
	public boolean validarUsuarioAtendimento(AtendimentoBackoffice atendimento) {
		
		if(atendimento == null) {
			
			return true;
		
		}else if( atendimento.getUsuarioEmAtendimento()==null) {
			
			return true;
			
		}else if (atendimento.getUsuarioEmAtendimento().getId() == retornarUsuarioSessao().getId() ) {
			
			return true;
		
		}else if(!this.serviceUsuarioLogado.checkUsuarioLogado(atendimento.getUsuarioEmAtendimento().getLogin())){
		
			return true;
		}
						
		return false;
	}
	
	public void criarDash() {

		this.listStatusPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioStatusAtendimentoPorUsuario(retornarUsuarioSessao().getId(), this.dataInicioPanel, this.dataFimPanel, null);
		this.listMotioPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioMotivoPorUsuario(retornarUsuarioSessao().getId(),this.dataInicioPanel, this.dataFimPanel, null);
		this.listSubmotioPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioSubmotivoPorUsuario(retornarUsuarioSessao().getId(), this.dataInicioPanel, this.dataFimPanel, null);

		criarCharts(listStatusPainel, "Status Atendimento");
		criarChatsMotivo();
		criarChatsSubmotivo();

	}

	private void criarCharts(List<?> list, String title) {

		this.donutModelStatusAtendimento = new DonutChartModel();

		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();

		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		for (Object linhaOb : list) {

			Object[] row = (Object[]) linhaOb;

			if (row[0] != null)
				values.add((BigDecimal) row[0]);
			else
				values.add(0);

			labels.add((String) row[2]);
			String cor = ColorUtil.getColorDinamic();
			bgColors.add(cor);
			row[3] = cor;
		}

		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		Legend legend = new Legend();
		legend.setDisplay(false);

		Title tile = new Title();
		tile.setText(title);

		DonutChartOptions op = new DonutChartOptions();
		op.setLegend(legend);
		op.setTitle(tile);

		this.donutModelStatusAtendimento.setOptions(op);

		this.donutModelStatusAtendimento.setData(data);

	}

	public Long retornarTotalFooter(List<?> list) {

		if (CollectionUtils.isNotEmpty(list)) {

			BigDecimal decimal = BigDecimal.ZERO;

			for (Object linhaOb : list) {

				Object[] row = (Object[]) linhaOb;

				if (row[0] != null)
					decimal = decimal.add((BigDecimal) row[0]);

			}
			
			
			return decimal.longValue();

		}

		return null;
	}

	public Integer retornarQuantidadeTotalFooter(List<?> list) {

		if (CollectionUtils.isNotEmpty(list)) {

			BigInteger total = BigInteger.ZERO;

			for (Object linhaOb : list) {

				Object[] row = (Object[]) linhaOb;

				if (row[1] != null)
					total = total.add((BigInteger) row[1]);

			}
			
			
			return total.intValue();
		}

		return null;
	}

	private void criarChatsMotivo() {

		ChartData data = criarDataSet(this.listMotioPainel);

		DonutChartOptions op = new DonutChartOptions();
		Legend legend = new Legend();
		legend.setDisplay(false);

		op.setLegend(legend);

		this.donutModelMotivo = new DonutChartModel();
		this.donutModelMotivo.setOptions(op);
		this.donutModelMotivo.setData(data);

	}

	private void criarChatsSubmotivo() {

		ChartData data = criarDataSet(this.listSubmotioPainel);

		DonutChartOptions op = new DonutChartOptions();
		Legend legend = new Legend();
		legend.setDisplay(false);

		op.setLegend(legend);

		this.donutSubmodelMotivo = new DonutChartModel();
		this.donutSubmodelMotivo.setOptions(op);
		this.donutSubmodelMotivo.setData(data);

	}

	private ChartData criarDataSet(List<?> list) {

		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();

		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		for (Object linhaOb : list) {

			Object[] row = (Object[]) linhaOb;

			if (row[0] != null)
				values.add((BigDecimal) row[0]);
			else
				values.add(0);

			labels.add((String) row[2]);
			String cor = ColorUtil.getColorDinamic();
			bgColors.add(cor);
			row[3] = cor;
		}

		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		return data;

	}

	public void onAtualizarPanel() {

		
		switch (filtro) {

		case "DIA":

			this.dataInicioPanel = new Date();
			this.dataFimPanel = new Date();
			break;

		case "MES":

			this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().retornarDataComHoraInicial().getData();
			this.dataFimPanel = DateUtil.builder(new Date()).retornarDataUltimoDiaMes().retornarDataComHoraFinal().getData();
			break;

		case "SEMANA":

			this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataPrimeiroDiaSemana().retornarDataComHoraInicial().getData();
			this.dataFimPanel = DateUtil.builder(new Date()).retornarDataUltimoDiaSemana().retornarDataComHoraFinal().getData();

			break;

		default:
			
			this.dataInicioPanel = new Date();
			this.dataFimPanel = new Date();
			break;
		}
		
		criarDash();
		pesquisarQuantidadeConsistencia();

	}

	private void gerarStreamedArquivo() {

		this.file = null;

		String arquivoString = exportarAtendimentosString(true);

		if (arquivoString != null)
			this.file = DefaultStreamedContent.builder().name("consistencias_backoffice.csv")
					.contentType("text/comma-separated-values")
					.stream(() -> new ByteArrayInputStream(arquivoString.getBytes(StandardCharsets.ISO_8859_1)))
					.build();

	}
	
	private void pesquisarQuantidadeConsistencia() {
		
		this.listQuantidadeConsistencia = this.serviceAtendimentoBackoffice.pesquisarQuantidadeConsistencia(retornarEmpresaUsuarioSessao().getId(),this.dataInicioPanel,this.dataFimPanel);
		criarDashConsistencia();

	}
	
	private void criarDashConsistencia() {
		
		this.stackedBarModelConsistencia = new BarChartModel();
		ChartData data = new ChartData();
		
	    List<String> labels = new ArrayList<>();
		
	 
	    BarChartDataSet barDataSetTratada= new BarChartDataSet();
	    
	    BarChartDataSet barDataSetNaoTratada= new BarChartDataSet();
	    
	    barDataSetTratada.setLabel("Tratada");
	    barDataSetTratada.setBackgroundColor("rgb(75, 192, 192)");
	    
	    barDataSetNaoTratada.setLabel("Não tratadas");
	    barDataSetNaoTratada.setBackgroundColor("rgb(255, 99, 132)");
	    List<Number> dataSetTratada = new ArrayList<>();
	    List<Number> dataSetNaoTratada = new ArrayList<>();
	    
	    for (Object objects : this.listQuantidadeConsistencia) {
			
	    	
	    	Object [] row = (Object[]) objects;
			
			labels.add(String.valueOf(row[0].toString()+" - "+StringUtils.abbreviate(row[1].toString(), 20) ));
				
	        dataSetTratada.add((Integer) row[4]);
	       
	        dataSetNaoTratada.add((Integer) row[5]);
	       
		}
	    
	    barDataSetNaoTratada.setData(dataSetNaoTratada);
	    
	    barDataSetTratada.setData(dataSetTratada);
	    data.addChartDataSet(barDataSetTratada);
	    data.addChartDataSet(barDataSetNaoTratada);
	    BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Consistência ");
        options.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        options.setTooltip(tooltip);
		data.setLabels(labels);
		stackedBarModelConsistencia.setOptions(options);
		stackedBarModelConsistencia.setData(data);
		
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

	public HistoricoAtendimentoBackoffice getHistoricoAtendimentoBko() {
		return historicoAtendimentoBko;
	}

	public void setHistoricoAtendimentoBko(HistoricoAtendimentoBackoffice historicoAtendimentoBko) {
		this.historicoAtendimentoBko = historicoAtendimentoBko;
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

	public AtendimentoBackoffice getAtendimentoVisualizar() {
		return atendimentoVisualizar;
	}

	public void setAtendimentoVisualizar(AtendimentoBackoffice atendimentoVisualizar) {
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

	public List<AtendimentoAudios> getListAudiosAtendimentos() {
		return listAudiosAtendimentos;
	}

	public void setListAudiosAtendimentos(List<AtendimentoAudios> listAudiosAtendimentos) {
		this.listAudiosAtendimentos = listAudiosAtendimentos;
	}

	public List<Consistencia> getListConsistencias() {
		return listConsistencias;
	}

	public Long getConsistencia() {
		return consistencia;
	}

	public void setConsistencia(Long consistencia) {
		this.consistencia = consistencia;
	}

	public Long getLoja() {
		return loja;
	}

	public void setLoja(Long loja) {
		this.loja = loja;
	}

	public Boolean getTratada() {
		return tratada;
	}

	public void setTratada(Boolean tratada) {
		this.tratada = tratada;
	}

	public StreamedContent getFileRar() {
		return fileRar;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public void setFileRar(StreamedContent fileRar) {
		this.fileRar = fileRar;
	}

	public List<?> getListHistoricosMeusAgendamentos() {
		return listHistoricosMeusAgendamentos;
	}

	public void setListHistoricosMeusAgendamentos(List<?> listHistoricosMeusAgendamentos) {
		this.listHistoricosMeusAgendamentos = listHistoricosMeusAgendamentos;
	}

	/**
	 * @return the lojaNaoTrabalhada
	 */
	public Long getLojaNaoTrabalhada() {
		return lojaNaoTrabalhada;
	}

	/**
	 * @param lojaNaoTrabalhada the lojaNaoTrabalhada to set
	 */
	public void setLojaNaoTrabalhada(Long lojaNaoTrabalhada) {
		this.lojaNaoTrabalhada = lojaNaoTrabalhada;
	}

	/**
	 * @return the cpfNaoTrabalhado
	 */
	public String getCpfNaoTrabalhado() {
		return cpfNaoTrabalhado;
	}

	/**
	 * @param cpfNaoTrabalhado the cpfNaoTrabalhado to set
	 */
	public void setCpfNaoTrabalhado(String cpfNaoTrabalhado) {
		this.cpfNaoTrabalhado = cpfNaoTrabalhado;
	}

	/**
	 * @return the adesaoNaoTrabalhada
	 */
	public String getAdesaoNaoTrabalhada() {
		return adesaoNaoTrabalhada;
	}

	/**
	 * @param adesaoNaoTrabalhada the adesaoNaoTrabalhada to set
	 */
	public void setAdesaoNaoTrabalhada(String adesaoNaoTrabalhada) {
		this.adesaoNaoTrabalhada = adesaoNaoTrabalhada;
	}

	/**
	 * @param listConsistencias the listConsistencias to set
	 */
	public void setListConsistencias(List<Consistencia> listConsistencias) {
		this.listConsistencias = listConsistencias;
	}

	public Long getConsistenciaNaoTrabalhada() {
		return consistenciaNaoTrabalhada;
	}

	public void setConsistenciaNaoTrabalhada(Long consistenciaNaoTrabalhada) {
		this.consistenciaNaoTrabalhada = consistenciaNaoTrabalhada;
	}

	public List<?> getListNaoTrabalhados() {
		return listNaoTrabalhados;
	}

	public List<?> getListMinhasPendencias() {
		return listMinhasPendencias;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public List<Motivo> getListMotivos() {
		return listMotivos;
	}

	public void setListMotivos(List<Motivo> listMotivos) {
		this.listMotivos = listMotivos;
	}

	public List<SubMotivo> getListSubMotivos() {
		return listSubMotivos;
	}

	public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
		this.listSubMotivos = listSubMotivos;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public SubMotivo getSubMotivo() {
		return subMotivo;
	}

	public void setSubMotivo(SubMotivo subMotivo) {
		this.subMotivo = subMotivo;
	}

	public StreamedContent getFile() {
		gerarStreamedArquivo();
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public DonutChartModel getDonutModelStatusAtendimento() {
		return donutModelStatusAtendimento;
	}

	public List<?> getListStatusPainel() {
		return listStatusPainel;
	}

	public DonutChartModel getDonutModelMotivo() {
		return donutModelMotivo;
	}

	public DonutChartModel getDonutSubmodelMotivo() {
		return donutSubmodelMotivo;
	}

	public List<?> getListMotioPainel() {
		return listMotioPainel;
	}

	public List<?> getListSubmotioPainel() {
		return listSubmotioPainel;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public BarChartModel getStackedBarModelConsistencia() {
		return stackedBarModelConsistencia;
	}
	
	public List<?> getListQuantidadeConsistencia() {
		return listQuantidadeConsistencia;
	}

	public String getCpfPendente() {
		return cpfPendente;
	}

	public void setCpfPendente(String cpfPendente) {
		this.cpfPendente = cpfPendente;
	}

	public String getNomePendente() {
		return nomePendente;
	}

	public void setNomePendente(String nomePendente) {
		this.nomePendente = nomePendente;
	}

	public String getAdesaoPendente() {
		return adesaoPendente;
	}

	public void setAdesaoPendente(String adesaoPendente) {
		this.adesaoPendente = adesaoPendente;
	}
	

}

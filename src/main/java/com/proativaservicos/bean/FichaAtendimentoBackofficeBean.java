package com.proativaservicos.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.CorreiosUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.constantes.*;
import com.thoughtworks.xstream.XStream;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Named
@ViewScoped
public class FichaAtendimentoBackofficeBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtendimentoBackofficeAtivoService serviceAtendimentoAtivo;

	@Inject
	private StatusAtendimentoService serviceStatusAtendimento;

	@Inject
	private SubMotivoService serviceSubMotivo;

	@Inject
	private AtendimentoBackofficeService serviceAtendimentoBackOffice;

	@Inject
	private HistoricoAtendimentoService serviceHistorico;

	@Inject
	private TelefoneService serviceTelefone;

	@Inject
	private DadosBancariosService serviceDadosBancarios;

	@Inject
	private PabxService servicePabx;

	@Inject
	private LojaService serviceLoja;

	@Inject
	private ProdutoService serviceProduto;

	@Inject
	private EnderecoService serviceEndereco;
	
	@Inject
	private CorreiosUtil correios;

	@Inject
	private AtendimentoBackofficeConsistenciasService serviceAtendimentoConsistencia;

	@Inject
	private RegistroSistemaUtil registro;

	@Inject
	private StatusTelefoneService serviceStatusTelefone;

	@Inject
	private BlackListTelefoneService serviceBlackListTelefone;

	@Inject
	private ConsistenciaService serviceConsistencias;
	
	@Inject
	private UsuarioLogadoService serviceUsuarioLogado;
	

	private Atendimento atendimento;

	private AtendimentoBackoffice atendimentoBackoffice;

	private HistoricoAtendimentoBackoffice historicoAtendimentoBackoffice;

	private List<StatusAtendimento> listStatusAtendimentos;

	private List<Motivo> listMotivos;

	private List<SubMotivo> listSubMotivos;

	private List<AtendimentoBackoffice> listAtendimentosAdesao;

	private List<Loja> listLojas;

	private List<Produto> listProdutos;

	private List<StatusTelefone> listStatusTelefones;

	private List<Consistencia> listConsistencia;

	private Usuario usuario;

	private Consistencia consistenciaSelecionada;

	private AtendimentoBackoffice atendimentoSelecionado;

	private Pabx pabx;

	private List<Object[]> listHistoricoAtendimento;

	private Integer fistTel;

	private Telefone telefone;

	private GenericDadosBancarios dadosBancarios;

	private List<Object[]> listAgendados;

	private List<Object[]> listPendentes;

	private String acao;

	private Boolean valorTipoPesquisaProspect;

	private String valorPesquisaProspect;

	private String cpf;

	private String adesao;

	private List<Long> idConsistencias;

	private Long idLoja;

	private GenericEndereco endereco;
	
	
	private boolean carregarVonixJS;
	private boolean carregarPstJS;

	private List<Object[]> listPendentesIntegrados;
	
	
	@PostConstruct
	public void init() {

		String page = Faces.getRequestURI();
		this.valorPesquisaProspect = "ADESAO";

		this.usuario = retornarUsuarioSessao();
		
		this.usuario.setEquipe(retornarEquipeUsuario(this.usuario.getId()));
		
		if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getId() != null) {

			this.usuario.getPontoAtendimento().setPabx(this.servicePabx.pesquisarPorPontoAtendimento(this.usuario.getPontoAtendimento().getId()));

			this.pabx = this.usuario.getPontoAtendimento().getPabx();

		}
		
		this.carregarVonixJS = carregarJavaScriptVonix();
		
		this.carregarPstJS = carregarJavaScriptPst();

		if (page.contains(PagesEnum.RECEPTIVO_BACKOFFICE.constante)) {

			if (this.atendimentoBackoffice == null) {

				this.listConsistencia = this.serviceConsistencias.pesquisarConsistenciaPorEmpresaDiferenteDeResponsabilidade(retornarEmpresaUsuarioSessao().getId(),"BMG");
				
				inicializarLojas();

				PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').show();");
			}

		} else {

			Long idAtendimento = (Long) Faces.getFlashAttribute("idAtendimento");

			if (idAtendimento != null) {

				iniciarAtendimento(idAtendimento);
				// iniciarEntidadesAtendimento();

			} else {

				iniciarAtendimento();
			}
			
		
			inicializarEntidades();
		}
	}
	
	private void inicializarLojas() {
		
		if(this.usuario.getEquipe()!=null && this.usuario.getEquipe().getId()!=null) {
			
			this.listLojas = this.serviceLoja.pesquisarLojasPorEquipe(this.usuario.getEquipe().getId());
		
		}else {
			this.listLojas = new ArrayList<>();
		}
		
	}

	private void inicializarEntidades() {

		this.historicoAtendimentoBackoffice = new HistoricoAtendimentoBackoffice();
		this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosBackoffice(this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
		//this.listLojas = this.serviceLoja.pesquisarLojas(this.usuario.getEmpresa().getId());
		inicializarLojas();
		this.listProdutos = this.serviceProduto.pesquisarProdutosPorEmpresa(this.usuario.getEmpresa().getId());
		this.listStatusTelefones = this.serviceStatusTelefone.pesquisarStatusTelefonesPorEmpresa(this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
		this.listConsistencia = this.serviceConsistencias.pesquisarConsistenciaPorEmpresaDiferenteDeResponsabilidade(retornarEmpresaUsuarioSessao().getId(),"BMG");

	
		// inicializarPendencia();
	}
	
	public boolean carregarJavaScriptVonix() {

		if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null) {

			return TipoPabxEnum.VONIX.equals(this.usuario.getPontoAtendimento().getPabx().getTipo());

		} else {

			return false;
		}
	}
	
	public boolean carregarJavaScriptPst() {

		if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null) {

			return TipoPabxEnum.PST_PHONE.equals(this.usuario.getPontoAtendimento().getPabx().getTipo());

		} else {

			return false;
		}
	}

	private void iniciarVariaveis() {

		this.atendimentoSelecionado = new AtendimentoBackoffice();
		this.atendimentoSelecionado.setStatus(new StatusAtendimento());
		this.atendimentoSelecionado.setMotivo(new Motivo());
		this.atendimentoSelecionado.setSubmotivo(new SubMotivo());
		this.fistTel = Integer.valueOf(0);
		this.telefone = new Telefone();

		if (this.atendimentoBackoffice.getStatus() == null)
			this.atendimentoBackoffice.setStatus(new StatusAtendimento());

		if (this.atendimentoBackoffice.getProduto() == null)
			this.atendimentoBackoffice.setProduto(new Produto());

		if (this.usuario.getEquipe() != null && this.usuario.getEquipe().getId() != null)
			this.atendimentoBackoffice.setEquipe(this.usuario.getEquipe());

		for (GenericTelefone tel : this.atendimentoBackoffice.getListTelefones()) {

			if (tel.getStatusTelefone() == null) {
				tel.setStatusTelefone(new StatusTelefone());

			}

		}

	}

	private void iniciarEntidadesAtendimento() {

		try {

			inicializarPendencia();

			this.listHistoricoAtendimento = new ArrayList<Object[]>();

			if (this.atendimentoBackoffice != null) {

				this.historicoAtendimentoBackoffice = new HistoricoAtendimentoBackoffice();
				this.atendimento = new Atendimento();
				this.telefone = new Telefone();
				this.dadosBancarios = new DadosBancarios();
				this.acao = "";
				this.fistTel = null;

				if (this.atendimentoBackoffice.getProtocolo() == null) {

					this.atendimentoBackoffice.setProtocolo(DateUtil.builder().formatarDataParaString("yyyyMMddHHmm").getDataTexto()+ String.valueOf(this.getAtendimentoBackoffice().getId()));

				}

				validarTeledonesAtendimentos();

				if (this.atendimentoBackoffice.getSubmotivo() != null	&& this.atendimentoBackoffice.getSubmotivo().getAcao() != null	&& this.atendimentoBackoffice.getSubmotivo().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

					for (HistoricoAtendimentoBackoffice historico : this.atendimentoBackoffice.getListHistoricos()) {

						if (historico.getSubmotivo().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

							this.historicoAtendimentoBackoffice.setObservacao(historico.getObservacao());

							historico.setDataVisualizado(new Date());

							alterar((Serializable) historico);

							if (historico.getAgendamento() != null) {

								String msg = "";

								if (historico.getSubmotivo()!=null && historico.getSubmotivo().getAcao().equals(AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL)) {

									msg = "Atendimento enviado pelo Agendamento Global. Data realizada: "	+ DateUtil.builder(historico.getAgendamento()).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();

								} else {

									msg = "Atendimento enviado pelo Agendamento. Data realizada: "+ DateUtil.builder(historico.getAgendamento()).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();

								}

								Messages.addGlobalInfo(msg, new Object[0]);
								break;

							}

						}

					}

				}

				iniciarVariaveis();
			}

		} catch (ProativaException e) {

			Messages.addGlobalError(e.getMessage(), new Object[0]);

		}

	}

	private void inicializarPendencia() {

		this.listAgendados = this.serviceAtendimentoBackOffice.pesquisarAtendimentosAgendados(this.usuario.getId());
		this.listPendentes = this.serviceAtendimentoBackOffice.pesquisarAtendimentosPendentes(this.usuario.getId());
		
		this.listPendentesIntegrados = this.serviceAtendimentoBackOffice.pesquisarAtendimentosPendentesStatusExtrator(this.usuario.getId(), Arrays.asList("INTEGRADO","INTEGRADA","CANCELADA"));
	
		removerList(this.listPendentes, 0);
		removerList(this.listPendentesIntegrados, 0);
				
	}
	
	
	private void removerList(List<Object[]> lista,int valorArray) {
		
		if (CollectionUtils.isNotEmpty(lista) && this.atendimentoBackoffice !=null && this.atendimentoBackoffice.getId() !=null ) {

			int i = 0;
			boolean encontrou = false;

			for (Object[] objects : lista) {
				
				BigInteger id = (BigInteger) objects[valorArray];
			
				if (id != null && id.longValue() == this.atendimentoBackoffice.getId().longValue()) {
					encontrou = true;
					break;
				}
				i++;
			}

			if (encontrou)
				lista.remove(i);
		}
		
	}

	public void iniciarAtendimento() {

		iniciarAtendimento(null);
		iniciarEntidadesAtendimento();

	}

	public void iniciarAtendimento(Long idAtendimento) {

		try {

			if (this.usuario.getEquipe() == null)
				throw new ProativaException("É preciso estar associado a alguma equipe para iniciar o atendimento...");

			if (this.atendimentoBackoffice != null) {

				if (this.atendimentoBackoffice.getUsuarioEmAtendimento() == null
						&& this.atendimentoBackoffice.getStatus() != null
						&& this.atendimentoBackoffice.getStatus().getAcao() != null
								& this.atendimentoBackoffice.getStatus().getAcao().toString()
										.startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

					this.serviceAtendimentoBackOffice.atualizarAtendimentoOcupado(this.atendimentoBackoffice.getId(),this.usuario.getId());

				}

			} else if (idAtendimento != null) {

				this.atendimentoBackoffice = this.serviceAtendimentoBackOffice.pesquisarAtendimentoPorId(idAtendimento);

				if (this.atendimentoBackoffice != null && this.atendimentoBackoffice.getUsuarioEmAtendimento() == null && this.atendimentoBackoffice.getStatus() != null && this.atendimentoBackoffice.getStatus().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

					this.serviceAtendimentoBackOffice.atualizarAtendimentoOcupado(this.atendimentoBackoffice.getId(),	this.usuario.getId());

				} else if (this.atendimentoBackoffice != null	&& this.atendimentoBackoffice.getUsuarioEmAtendimento() == null	) {
					//&& this.atendimentoBackoffice.getStatus() == null

					this.serviceAtendimentoBackOffice.atualizarAtendimentoOcupado(this.atendimentoBackoffice.getId(),this.usuario.getId());
				}
				
				this.serviceAtendimentoBackOffice.atualizarAtendimentoOcupado(this.atendimentoBackoffice.getId(),this.usuario.getId());
				
			} else {

				Long idUsuarioAtendimento = this.serviceAtendimentoAtivo.pesquisarProximoAtendimento(this.usuario.getId(), this.usuario.getEquipe().getId());

				if (idUsuarioAtendimento != null) {

					this.atendimentoBackoffice = serviceAtendimentoBackOffice.pesquisarAtendimentoPorId(idUsuarioAtendimento);

				}

			}

			if (this.atendimentoBackoffice == null) {

				Long idUsuarioAtendimento = this.serviceAtendimentoAtivo.pesquisarProximoAtendimento(this.usuario.getId(), this.usuario.getEquipe().getId());

				if (idUsuarioAtendimento != null) {

					this.atendimentoBackoffice = this.serviceAtendimentoBackOffice.pesquisarAtendimentoPorId(idUsuarioAtendimento);
				}

				if (this.atendimentoBackoffice == null)
					throw new ProativaException(MessagesEnum.NAO_EXISTE_ATENDIMENTO.constante);
			}

			if (idAtendimento != null) {
			
				iniciarEntidadesAtendimento();
				
			}
						
			StringBuilder acao = new StringBuilder();
			acao.append("INICIOU ATENDIMENTO BACKOFFICE" + " | ");
			acao.append("ADESÃO: " + this.atendimentoBackoffice.getAdesao() + " | CLIENTE: ");
			acao.append( StringUtils.isBlank(this.atendimentoBackoffice.getNome()) ? " NOVO" : this.atendimentoBackoffice.getNome());
			
			if(this.atendimento!=null && this.atendimento.getId()!=null)
				this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.INICIOU_ATENDIMENTO_BKO,	acao.toString());
			else
				this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.INICIOU_ATENDIMENTO_BKO,	acao.toString());

			
			
		} catch (ProativaException e) {

			if (e.getMessage().equalsIgnoreCase("É preciso estar associado a alguma equipe para iniciar o atendimento..."))
				Messages.addGlobalWarn(e.getMessage(), new Object[0]);
			else
				Messages.addGlobalError(e.getMessage(), new Object[0]);

		} catch (Exception e) {

			e.printStackTrace();

			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	private void validarTeledonesAtendimentos() {

		if (this.atendimentoBackoffice != null && CollectionUtils.isEmpty(this.atendimentoBackoffice.getListTelefones()) && this.atendimentoBackoffice.getAtendimento() != null 	&& CollectionUtils.isEmpty(this.atendimentoBackoffice.getAtendimento().getListTelefones())) {
			this.atendimentoBackoffice.integrarTelefonesAtendimento();
		}

	}

	public void buscarProximo() {

		try {

			
			inserirConsistencias();
			
			validarHistorico();

			this.atendimentoBackoffice.setUsuarioEmAtendimento(null);
			this.atendimentoBackoffice.setDataAlteracao(new Date());
			
			String cpfAux = this.atendimentoBackoffice.getCpf().replaceAll("\\D", "");
			String adeAux = this.atendimentoBackoffice.getAdesao().replaceAll("\\D", "");
			this.atendimentoBackoffice.setAdesao(adeAux);
			this.atendimentoBackoffice.setCpf(StringUtils.leftPad(cpfAux,11, "0"));

			alterarAtendimento();

			Long idAtendimento = validarAtendimentoRelacionado();
			
			StringBuilder builder = new StringBuilder();
			builder.append("ATENDIMENTO BACKOFFICE FINALIZADO | CLIENTE: " + this.atendimentoBackoffice.getNome());
			builder.append(" | ADESÃO: " + this.atendimentoBackoffice.getAdesao());
			
			if(this.atendimentoBackoffice.getAtendimento()!=null && this.atendimentoBackoffice.getAtendimento().getId()!=null)
				this.registro.registrarLog(atendimentoBackoffice.getAtendimento(), usuario, TipoEventoEnum.ENCERROU_ATENDIMENTO_BKO, builder.toString());
			else
				this.registro.registrarLog(this.usuario.getId(),  TipoEventoEnum.ENCERROU_ATENDIMENTO_BKO, builder.toString());
				
			this.atendimentoBackoffice = null;
			this.historicoAtendimentoBackoffice = null;
			
			String page = Faces.getRequestURI();
					
			
			if(page.contains(PagesEnum.RECEPTIVO_BACKOFFICE.constante)) {
				
				Faces.redirect("/crmproativa/pages/backoffice/meusAtendimentosConsistencias.jsf");
				
			}else{
			

				if (idAtendimento == null) {
					iniciarAtendimento();
	
				} else {
					iniciarAtendimento(idAtendimento);
					inicializarEntidades();
				}
			}
			// inicializarPendencia();

		} catch (ProativaException e) {

			e.printStackTrace();
			Messages.addGlobalError(e.getMessage(), new Object[0]);

		} catch (Exception e) {

			e.printStackTrace();

			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	private Long validarAtendimentoRelacionado() {

		if (this.atendimentoBackoffice != null
				&& CollectionUtils.isNotEmpty(this.atendimentoBackoffice.getListAtendimentosIntegrados())) {

			Optional<AtendimentoBackoffice> opAtendimento = this.atendimentoBackoffice.getListAtendimentosIntegrados().stream().filter(a -> a.getStatus() == null).findFirst();

			if (!opAtendimento.isPresent()) return null;
			return opAtendimento.get().getId();

		}

		return null;

	}

	private void alterarAtendimento() throws ProativaException {

		this.atendimentoBackoffice.setUsuarioAlteracao(this.usuario);
		this.atendimentoBackoffice.setEnviado(Boolean.valueOf(false));

		if (this.atendimentoBackoffice.getAtendimento() == null
				|| this.atendimentoBackoffice.getAtendimento().getId() == null)
			this.atendimentoBackoffice.setAtendimento(null);

		if (this.usuario.getEquipe() != null && this.usuario.getEquipe().getId() != null)
			this.atendimentoBackoffice.setEquipe(this.usuario.getEquipe());

		if (this.historicoAtendimentoBackoffice.getMotivo() == null
				|| this.historicoAtendimentoBackoffice.getMotivo().getId() == null)
			this.historicoAtendimentoBackoffice.setMotivo(null);

		if (this.historicoAtendimentoBackoffice.getSubmotivo() == null
				|| this.historicoAtendimentoBackoffice.getSubmotivo().getId() == null)
			this.historicoAtendimentoBackoffice.setSubmotivo(null);

		if (this.atendimentoBackoffice.getMotivo() == null || this.atendimentoBackoffice.getMotivo().getId() == null)
			this.atendimentoBackoffice.setMotivo(null);

		if (this.atendimentoBackoffice.getSubmotivo() == null
				|| this.atendimentoBackoffice.getSubmotivo().getId() == null)
			this.atendimentoBackoffice.setSubmotivo(null);

		this.atendimentoBackoffice.setAdesao(StringUtils.deleteWhitespace(this.atendimentoBackoffice.getAdesao()));
		
		alterarGenerico((Serializable) this.atendimentoBackoffice);

	}

	private void validarHistorico() throws ProativaException {

		
		if(StringUtils.isBlank(this.atendimentoBackoffice.getAdesao()))
			throw new ProativaException("Informe o numero da adesão.");
		
		if(StringUtils.isBlank(this.atendimentoBackoffice.getCpf()))
			throw new ProativaException("Informe o CPF.");
		
		if(StringUtils.isBlank(this.atendimentoBackoffice.getAdesao()))
			throw new ProativaException("O campo adesão deve ser informado.");
		
		if(this.serviceAtendimentoBackOffice.existeAtendimentoPorAdesao(this.atendimentoBackoffice.getAdesao(),this.atendimentoBackoffice.getId(), this.usuario.getEmpresa().getId()))
			throw new ProativaException("Adesão informada está sendo trabalhada.");
		
		if (this.historicoAtendimentoBackoffice.getStatus() == null)
			throw new ProativaException("Informe o status do atendimento.");

		if(CollectionUtils.isNotEmpty(this.historicoAtendimentoBackoffice.getStatus().getListMotivos()) 
				&& (this.historicoAtendimentoBackoffice.getMotivo() == null 
				|| this.historicoAtendimentoBackoffice.getMotivo().getId()==null)) {
			
			throw new ProativaException("Informe o motivo.");
		}
		
		if(CollectionUtils.isNotEmpty(this.historicoAtendimentoBackoffice.getMotivo().getListSubMotivos()) && ( this.historicoAtendimentoBackoffice.getSubmotivo() == null  || this.historicoAtendimentoBackoffice.getMotivo().getId()==null))
			throw new ProativaException("Informe o Submotivo.");
		
		if (historicoAtendimentoBackoffice.getSubmotivo() != null
				&& (AcaoStatusAtendimentoEnum.AGENDAR.equals(historicoAtendimentoBackoffice.getSubmotivo().getAcao())	|| AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL	.equals(historicoAtendimentoBackoffice.getSubmotivo().getAcao()))) {

			if (historicoAtendimentoBackoffice.getAgendamento() == null) {

				throw new ProativaException(MessagesEnum.DATA_AGENDAMENTO_REQUERIDO.constante);
			}

			if (historicoAtendimentoBackoffice.getAgendamento().before(new Date(System.currentTimeMillis()))) {

				throw new ProativaException(MessagesEnum.DATA_AGENDAMENTO_DEVE_SER_MAIOR_QUE_DATA_ATUAL.constante);
			}

		} else {

			this.historicoAtendimentoBackoffice.setDataVisualizado(new Date(System.currentTimeMillis()));

			if (this.atendimentoBackoffice.getListHistoricos() == null)
				this.atendimentoBackoffice.setListHistoricos(new ArrayList<HistoricoAtendimentoBackoffice>());

			for (HistoricoAtendimentoBackoffice historicGeneric : this.atendimentoBackoffice.getListHistoricos()) {
				historicGeneric.setDataVisualizado(new Date(System.currentTimeMillis()));
			}

		}

		this.historicoAtendimentoBackoffice.setDataCadastro(new Date());
		this.historicoAtendimentoBackoffice.setUsuario(this.usuario);
		this.historicoAtendimentoBackoffice.setAtendimentoBackoffice(this.atendimentoBackoffice);
		this.historicoAtendimentoBackoffice.setConsistenciaBanco(this.atendimentoBackoffice.getConsistenciaBanco());

		this.historicoAtendimentoBackoffice.setConsistenciaCoban(this.atendimentoBackoffice.getConsistenciaCoban());

		if (StringUtils.isNotBlank(this.atendimentoBackoffice.getProtocolo())) {
			this.historicoAtendimentoBackoffice.setProtocolo(this.atendimentoBackoffice.getProtocolo());
		}

		this.atendimentoBackoffice.setUsuarioEmAtendimento(null);
		this.atendimentoBackoffice.setStatus(this.historicoAtendimentoBackoffice.getStatus());
		this.atendimentoBackoffice.setMotivo(this.historicoAtendimentoBackoffice.getMotivo());
		this.atendimentoBackoffice.setSubmotivo(this.historicoAtendimentoBackoffice.getSubmotivo());

		if (this.historicoAtendimentoBackoffice.getId() == null)
			this.atendimentoBackoffice.adicionarHistorico(this.historicoAtendimentoBackoffice);

	}

	public void inserirConsistencias() throws ProativaException {

		if (CollectionUtils.isNotEmpty(this.atendimentoBackoffice.getListConsistencias())) {

			for (Consistencia consistencia : this.atendimentoBackoffice.getListConsistencias()) {

				AtendimentoBackofficeConsistencia atnCon = new AtendimentoBackofficeConsistencia(this.atendimentoBackoffice, consistencia);

				atnCon.setTratada(consistencia.getTratada());

				if (consistencia.getMapArquivos() != null) {

					for (String key : consistencia.getMapArquivos().keySet()) {

						salvarArquivo(consistencia.getMapArquivos().get(key), key,"consistencias" + File.separator + StringUtils.deleteWhitespace(this.atendimentoBackoffice.getAdesao()), false);
					}

				}
				if(CollectionUtils.isNotEmpty(consistencia.getArquivos())) {
					String json = new Gson().toJson(consistencia.getArquivos());
					atnCon.setArquivo(json);
				}
				this.serviceAtendimentoConsistencia.alterar(atnCon);

			}

		}

	}

	public void onAtualizarConsistencia(Consistencia consistencia) {

		if (consistencia != null) {

			for (Consistencia consistenciaAux : atendimentoBackoffice.getListConsistencias()) {

				if (consistencia.getId().equals(consistenciaAux.getId())) {
					consistenciaAux.setTratada(consistencia.getTratada());
					break;
				}

			}

		}

	}

	public void onSelectMotivo() {

		if (this.historicoAtendimentoBackoffice != null && this.historicoAtendimentoBackoffice.getMotivo() != null) {

			this.historicoAtendimentoBackoffice.getMotivo().setListSubMotivos(new ArrayList<SubMotivo>());
			this.historicoAtendimentoBackoffice.getMotivo().setListSubMotivos(this.serviceSubMotivo.pesquisarSubMotivosPorMotivo(this.historicoAtendimentoBackoffice.getMotivo().getId(),TipoAcessoEnum.ATIVO));
			
			if(CollectionUtils.isNotEmpty(this.historicoAtendimentoBackoffice.getMotivo().getListSubMotivos())) 
				this.historicoAtendimentoBackoffice.setSubmotivo(this.historicoAtendimentoBackoffice.getMotivo().getListSubMotivos().get(0));
			else
				this.historicoAtendimentoBackoffice.setSubmotivo(new SubMotivo());
	
		}else {
			this.historicoAtendimentoBackoffice.setSubmotivo(new SubMotivo());
		}
		
	}

	public void onChangeStatus() {

		this.historicoAtendimentoBackoffice.setMotivo(new Motivo());
		this.historicoAtendimentoBackoffice.setSubmotivo(new SubMotivo());
	}

	public void buscarHistoricosAtendimentos() {

		try {

			validarCpf();
			this.listHistoricoAtendimento = this.serviceHistorico.pesquisarHIstoricoPorCpf(
					this.atendimentoBackoffice.getCpf().trim(), this.usuario.getEmpresa().getId());

		} catch (ProativaException e) {

			Messages.addGlobalError(e.getMessage(), new Object[0]);

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}
	}

	public void validarCpf() throws ProativaException {

		if (this.atendimentoBackoffice.getCpf() == null || this.atendimentoBackoffice.getCpf().trim().isEmpty()) {

			throw new ProativaException("CPF Deve ser informado");
		}
	}

	public void enviarArquivo(FileUploadEvent event) {

		try {
			
			if(StringUtils.isBlank(this.atendimentoBackoffice.getAdesao()))
				throw new ProativaException("Por favor informe a adesão.");
			
			if (this.consistenciaSelecionada != null) {

				String nomeArquivo = StringUtils.deleteWhitespace(this.consistenciaSelecionada.getCodigoConcistencia()+"_"+ this.atendimentoBackoffice.getAdesao().trim()) + "_"+ StringUtils.deleteWhitespace(deAccent(event.getFile().getFileName().replaceAll(" ", "")));

				if (this.consistenciaSelecionada.getMapArquivos() == null) {

					this.consistenciaSelecionada.setMapArquivos(new HashMap<String, InputStream>());
				}

				this.consistenciaSelecionada.getMapArquivos().remove(nomeArquivo);

				if (CollectionUtils.isEmpty(this.consistenciaSelecionada.getArquivos())) {

					this.consistenciaSelecionada.setArquivos(new ArrayList<String>());

				}

				this.consistenciaSelecionada.getArquivos().remove(nomeArquivo);
				this.consistenciaSelecionada.getArquivos().add(nomeArquivo);
				this.consistenciaSelecionada.getMapArquivos().put(nomeArquivo, event.getFile().getInputStream());
				Messages.addGlobalInfo("Arquivo enviado com sucesso.", new Object[0]);
			}

		} catch (ProativaException e) {

			e.printStackTrace();
			Messages.addGlobalWarn(e.getMessage(), new Object[0]);

		}catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public void pesquisarTelefones() {

		try {

			if (this.atendimentoBackoffice != null && StringUtils.isBlank(this.atendimentoBackoffice.getCpf()))
				throw new ProativaException("Cpf precisa ser informado.");

			List<Object[]> list = this.serviceTelefone.pesquisarTelefonesPorCpf(this.atendimentoBackoffice.getCpf(),this.fistTel, Integer.valueOf(3));

			if (list == null || list.size() < 3) {

				this.fistTel = null;

				if (list == null)
					Messages.addGlobalWarn("Nenhum telefone encontrado", new Object[0]);

			} else {
				this.fistTel = this.fistTel + 2;
			}

			if (this.atendimentoBackoffice.getAtendimento() == null) {
				this.atendimentoBackoffice.setAtendimento(new Atendimento());
			}

			if (CollectionUtils.isEmpty(this.atendimentoBackoffice.getAtendimento().getListTelefones())) {
				this.atendimentoBackoffice.getAtendimento().setListaTelefones(new ArrayList<GenericTelefone>());
			}

			for (Object[] objects : list) {

				this.atendimentoBackoffice.getAtendimento().adicionarTelefone(
						((GenericTelefone) new Telefone(Short.valueOf(objects[0].toString()), objects[1].toString())));
			}

		} catch (ProativaException e) {

			e.printStackTrace();
			Messages.addGlobalError(e.getMessage(), new Object[0]);

		}

	}

	@SuppressWarnings("unchecked")
	public void onPesquisarEndereco() {
		
		try {

			if (this.atendimentoBackoffice != null && StringUtils.isBlank(this.atendimentoBackoffice.getCpf()))
				throw new ProativaException("Cpf precisa ser informado.");
		
			
		
		List<Endereco> listEnderecos =	(List<Endereco>) this.serviceEndereco.pesquisarEnderecoPorCpf(this.atendimentoBackoffice.getCpf());
		
		if(CollectionUtils.isNotEmpty(listEnderecos)) {
		
			if(this.atendimentoBackoffice.getAtendimento() ==null)
				this.atendimentoBackoffice.setAtendimento(new Atendimento());
			
			this.atendimentoBackoffice.getAtendimento().setListaEnderecos(listEnderecos);
		
			
		}else {
			Messages.addGlobalWarn("Nenhum endereço encontrado.", new Object[0]);
		}
			
			
		} catch (ProativaException e) {

			e.printStackTrace();
			Messages.addGlobalError(e.getMessage(), new Object[0]);

		}

	}
	
	public void onPesquisarDadosBancarios() {
		
		try {
			
			if (this.atendimentoBackoffice != null && StringUtils.isBlank(this.atendimentoBackoffice.getCpf()))
				throw new ProativaException("Cpf precisa ser informado.");
									
			List<DadosBancarios> listDadosBancarios =	(List<DadosBancarios>) this.serviceDadosBancarios.pesquisarDadosBancariosPorCpf(this.atendimentoBackoffice.getCpf());
			
			if(CollectionUtils.isNotEmpty(listDadosBancarios)) {
				
				if(this.atendimentoBackoffice.getAtendimento() ==null)
					this.atendimentoBackoffice.setAtendimento(new Atendimento());
				
				this.atendimentoBackoffice.getAtendimento().setListaDadosBancarios(listDadosBancarios);
				
				
			}else {
				Messages.addGlobalWarn("Nenhum dados bancários encontrado.", new Object[0]);
			}
			
			
		} catch (ProativaException e) {
			
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage(), new Object[0]);
			
		}
		
	}
	
	
	public void pesquisarDadosBancarios() {

		try {

			if (this.atendimentoBackoffice != null && StringUtils.isBlank(this.atendimentoBackoffice.getCpf()))
				throw new ProativaException("Cpf precisa ser informado.");

		} catch (ProativaException e) {

			e.printStackTrace();
			Messages.addGlobalError(e.getMessage(), new Object[0]);

		}
	}

	public void removerAnexo(String arquivo) {

		if (StringUtils.isNotBlank(arquivo) && this.consistenciaSelecionada.getMapArquivos() != null) {

			this.consistenciaSelecionada.getMapArquivos().remove(arquivo);
			this.consistenciaSelecionada.getArquivos().remove(arquivo);

		}

		if (this.consistenciaSelecionada.getMapArquivos() == null)
			Messages.addGlobalWarn("Não é possivel remover arquivo já cadastrado", new Object[0]);

	}

	public void iniciarOutraTratativa(AtendimentoBackoffice atendimento) {

		if (atendimento != null && atendimento.getId() != null) {

			try {

				inserirConsistencias();

				validarHistorico();

				this.atendimentoBackoffice.setUsuarioEmAtendimento(null);
				this.atendimentoBackoffice.setDataAlteracao(new Date());

				alterarAtendimento();

				this.atendimentoBackoffice = null;
				this.historicoAtendimentoBackoffice = null;

				iniciarAtendimento(atendimento.getId());
				inicializarEntidades();
				// inicializarPendencia();

			} catch (ProativaException e) {

				e.printStackTrace();
				Messages.addGlobalError(e.getMessage(), new Object[0]);

			} catch (Exception e) {

				e.printStackTrace();

				Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

			}

		}

	}

	public void iniciarAtendimentoPendente(Long id) {

		try {

			if (id != null) {

				inserirConsistencias();

				validarHistorico();

				this.atendimentoBackoffice.setUsuarioEmAtendimento(null);
				this.atendimentoBackoffice.setDataAlteracao(new Date());

				alterarAtendimento();

				this.atendimentoBackoffice = null;
				this.historicoAtendimentoBackoffice = null;

				iniciarAtendimento(id);

			}
		} catch (ProativaException e) {

			Messages.addGlobalError(e.getMessage(), new Object[0]);

		} catch (Exception e) {

			e.printStackTrace();

			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}

	public void criarEntidade(String tipo) {

		try {

			if (tipo.equalsIgnoreCase("ENDERECO")) {

				
				this.endereco = new Endereco();
				PrimeFaces.current().ajax().update("dlgEndereco");

				
			} else if (tipo.equalsIgnoreCase("TELEFONE")) {

				this.telefone = new Telefone();

			} else if (tipo.equalsIgnoreCase("DADOS_BANCARIOS")) {

				this.dadosBancarios = new DadosBancarios();
				PrimeFaces.current().ajax().update("dlgDadosBancarios");

			} else if (tipo.equalsIgnoreCase("EMAIL")) {

			}

			this.acao = "S";

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}
	}

	public void atualizarCepPorEndereco() {

		Endereco endAux = correios.consultarEnderecoPorCep(this.endereco.getCep(), this.endereco.getId());

		if (endAux != null)
			this.endereco = (GenericEndereco) endAux;
	}

	
	public void updadeEntidade(String tipo) {

		try {

			StringBuilder registroLog = new StringBuilder(MessagesEnum.ALTEROU + " ");

			if (tipo.equalsIgnoreCase("ENDERECO")) {

				
				registroLog.append("ENDEREÇO - DE: ");

				registroLog.append(retornarXML(DadosBancarios.class,pesquisar(DadosBancarios.class, this.endereco.getId())));

				registroLog.append(" | PARA: " + retornarXML(DadosBancarios.class, this.endereco));

				this.registro.registrarLog(usuario.getId(), TipoEventoEnum.ALTERACAO_DADOS, registroLog.toString());

				alterar((Serializable) this.endereco);

				this.endereco = null;
			}

			if (tipo.equalsIgnoreCase("EMAIL")) {

			}
			if (tipo.equalsIgnoreCase("DADOS_BANCARIOS")) {

				registroLog.append("DADOS BANCARIOS - DE: ");

				registroLog.append(retornarXML(DadosBancarios.class,pesquisar(DadosBancarios.class, this.dadosBancarios.getId())));

				registroLog.append(" | PARA: " + retornarXML(DadosBancarios.class, this.dadosBancarios));

				this.registro.registrarLog(this.usuario.getId(),TipoEventoEnum.ALTERACAO_DADOS, registroLog.toString());

				alterar((Serializable) this.dadosBancarios);

				this.dadosBancarios = null;

			}

		} catch (ProativaException e) {

			Messages.addGlobalError(e.getMessage(), new Object[0]);
			e.printStackTrace();

		} catch (Exception e) {

			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}

	}

	public void salvarEntidade(String tipo) {

		try {

			StringBuilder registroLog = new StringBuilder(MessagesEnum.SALVOU + " ");

			if (this.atendimentoBackoffice.getAtendimento() == null)
				this.atendimentoBackoffice.setAtendimento(new Atendimento());

			if (tipo.equalsIgnoreCase("ENDERECO")) {
				
				this.atendimentoBackoffice.getAtendimento().adicionarEndereco(this.endereco);

				inserir((Serializable) this.endereco);

				if (this.endereco.getId() == null) {

					this.atendimento.getListaEnderecos().remove(this.endereco);
					throw new ProativaException(MessagesEnum.ERRO_INSERIR_ENDERECO.constante);

				}

				registroLog.append("ENDEREÇO: ");
				registroLog.append(retornarXML(DadosBancarios.class, this.endereco));
				this.registro.registrarLog( this.usuario.getId(),TipoEventoEnum.SALVAR_DADOS, registroLog.toString());
			
				this.endereco = null;

			} else if (tipo.equalsIgnoreCase("DADOS_BANCARIOS")) {

				this.atendimentoBackoffice.getAtendimento().adicionarDadosBancarios(this.dadosBancarios);

				inserir((Serializable) this.dadosBancarios);

				if (this.dadosBancarios.getId() == null) {

					this.atendimento.getListaDadosBancarios().remove(this.dadosBancarios);
					throw new ProativaException(MessagesEnum.ERRO_INSERIR_DADOS_BANCARIOS.constante);

				}

				registroLog.append("DADOS BANCARIOS: ");
				registroLog.append(retornarXML(DadosBancarios.class, this.dadosBancarios));
				this.registro.registrarLog( this.usuario.getId(),	TipoEventoEnum.SALVAR_DADOS, registroLog.toString());
				this.dadosBancarios = null;

			} else if (tipo.equalsIgnoreCase("EMAIL")) {

			} else if (tipo.equalsIgnoreCase("TELEFONE")) {

				if (this.atendimentoBackoffice.getAtendimento().getListaTelefones() == null)
					this.getAtendimentoBackoffice().getAtendimento().setListaTelefones(new ArrayList<Telefone>());

				this.telefone.setStatusTelefone(null);
				this.telefone.setNumero(this.telefone.getNumero().replaceAll("[-]", "").replaceAll("[(]", "").replaceAll("[)]", "").replaceAll(" ", "").trim());

				String numeroTelefoneDDD = String.valueOf(this.telefone.getDdd()) + this.telefone.getNumero();

				if (this.serviceBlackListTelefone.pesquisarTelefonesBlackListManual(numeroTelefoneDDD,
						this.usuario.getEmpresa().getId()) != null) {
					throw new ProativaException("Telefone está incluido na black list, não pode ser inserido. Informe seu superviso.");

				}

				boolean naoTemTelefone = this.atendimentoBackoffice.getAtendimento().adicionarTelefone(this.telefone);

				if (naoTemTelefone) {

					this.telefone.setUsuarioCadastro(this.usuario);

					inserir((Serializable) this.telefone);

					if (this.telefone.getId() == null) {

						this.atendimentoBackoffice.getListTelefones().remove(this.telefone);
						throw new ProativaException(MessagesEnum.ERRO_INSERIR_TELEFONE.constante);

					}

					this.telefone.setCondicaoNovo(true);
					this.telefone.setStatusTelefone(new StatusTelefone());
					registroLog.append("TELEFONE: ");

					registroLog.append(retornarXML(TelefoneBackoffice.class, this.telefone));

					this.registro.registrarLog( this.usuario.getId(),TipoEventoEnum.SALVAR_DADOS, registroLog.toString());
					atualizarTelefone();

				} else {

					throw new ProativaException(MessagesEnum.TELEFONE_JA_EXISTE.constante);
				}

			}

		} catch (ProativaException e) {

			Messages.addGlobalError(e.getMessage(), new Object[0]);

		} catch (Exception e) {

			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}

	}

	public void onPesquisarConsistencias() {

		try {

			if (this.atendimentoBackoffice == null	|| StringUtils.isBlank(this.atendimentoBackoffice.getConsistenciaCoban()))
				throw new ProativaException("Nenhuma consistência encontrada.");

			List<Integer> listaConsistencias = new ArrayList<Integer>();

			if (StringUtils.contains(this.atendimentoBackoffice.getConsistenciaCoban(), ",")) {

				String[] arrayConsistencias = this.atendimentoBackoffice.getConsistenciaCoban().split("[,]");

				for (String concistencia : arrayConsistencias) {

					String aux = StringUtils.deleteWhitespace(concistencia);

					if (StringUtils.isNumeric(aux.trim())) {

						listaConsistencias.add(Integer.valueOf(aux.trim()));
					}
				}

			} else if (StringUtils.isNumeric(StringUtils.deleteWhitespace(this.atendimentoBackoffice.getConsistenciaCoban()))) {

				listaConsistencias.add(Integer.valueOf(StringUtils.deleteWhitespace(this.atendimentoBackoffice.getConsistenciaCoban())));

			}

			if (CollectionUtils.isEmpty(listaConsistencias))
				throw new ProativaException("Nenhuma consistência cadastrada.");

			this.atendimentoBackoffice.setListConsistencias(validarConcistencias(listaConsistencias));

		} catch (ProativaException e) {

			Messages.addGlobalWarn(e.getMessage(), new Object[0]);

		}

	}
	
	public String buscarAtendimentoProspect() {
				
		try {

			AtendimentoBackoffice atendimento = null;
			
			if (this.valorPesquisaProspect.equals("ADESAO")) {
								
				atendimento =	this.serviceAtendimentoBackOffice.pesquisarAtendimentoPorAdesao(this.adesao, retornarEmpresaUsuarioSessao().getId());
								
				if(atendimento != null && atendimento.getId()!=null) {
					
					if(atendimento.getUsuarioEmAtendimento()==null || atendimento.getUsuarioEmAtendimento().getId() == this.usuario.getId() || !this.serviceUsuarioLogado.checkUsuarioLogado(atendimento.getUsuarioEmAtendimento().getLogin() )) {
					
						atendimento.setListConsistencias(this.serviceConsistencias.pesquisarConsistenciasPorAtendimento(atendimento.getId()));
						atendimento = serviceAtendimentoBackOffice.pesquisarAtendimentoPorId(atendimento.getId());
					
					}else {
						
						throw new ProativaException("Atendimento pendente ou em atendimento para: "+(atendimento.getUsuarioEmAtendimento()==null?"" :atendimento.getUsuarioEmAtendimento().getNome()));
					}
					
				}else {
														
					atendimento = new AtendimentoBackoffice();
					atendimento.setAdesao(this.adesao);
					atendimento.setProspect(Boolean.TRUE);
					criarAtendimentoIntegrado(atendimento);
					inserir((Serializable)atendimento);					
					
				}
								
			}else {
				
				atendimento = new AtendimentoBackoffice();
				atendimento.setProspect(Boolean.TRUE);
				atendimento.setCpf(StringUtils.leftPad(this.cpf.replaceAll("\\D+", "").trim(),11, "0") );
				criarAtendimentoIntegrado(atendimento);
				inserir((Serializable)atendimento);		
				
			}
			
			if(idLoja!=null) {
				
				atendimento.setLoja(this.listLojas.stream().filter(l -> l.getId().equals(idLoja)).findFirst().get());
				atendimento.setCodLoja(atendimento.getLoja().getCodigoLoja());
			}
			
			validarConsistencias(atendimento);
			
			this.atendimentoBackoffice = atendimento;
			this.atendimentoBackoffice.setUsuarioEmAtendimento(this.usuario);
			this.serviceAtendimentoBackOffice.atualizarAtendimentoOcupado(this.atendimentoBackoffice.getId(), this.usuario.getId());
			
			StringBuilder builder = new StringBuilder("INICIOU ATENDIMENTO PROSPECT ");
			
			builder.append(" | Backoffice | ADESÃO: "+(StringUtils.isEmpty(this.adesao) ? " - " :this.adesao));
		
			builder.append(	" | CPF: " + (StringUtils.isEmpty(this.cpf) ? " - " : this.cpf));

			this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.INICIOU_ATENDIMENTO_BKO,	builder.toString());
			
			PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').hide();");
			
			inicializarEntidades();
			
			iniciarEntidadesAtendimento();
		
			return "";
			
		}catch(ProativaException e) {
			
			e.printStackTrace();
			Messages.addGlobalWarn(e.getMessage(), new Object[0]);
			
		}catch (Exception e) {
			
			e.printStackTrace();
			Messages.addGlobalWarn(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
		}
		
		return "";
		
	}
	
	private void criarAtendimentoIntegrado(AtendimentoBackoffice atendimentoBko) {
		
		
		try {

				Atendimento propostas = new Atendimento();
				propostas.setAdesao(this.adesao);
				propostas.setCpf(this.cpf);
				propostas.setUsuarioCadastro(usuario);
				propostas.setLoja(idLoja ==null? null: this.listLojas.stream().filter(l -> l.getId().equals(idLoja)).findFirst().get());
				propostas.setObservacao("NOVO ATENDIMENTO BACKOFFICE:");
				
				propostas.setDataCadastro(new Date());
				propostas.setBko(Boolean.TRUE);
				inserir((Serializable) propostas, true);
				atendimentoBko.setAtendimento(propostas);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onInserirConsistencias() {

		try {

			if (CollectionUtils.isEmpty(this.idConsistencias))
				throw new ProativaException("Por favor informe pelo menos uma consistência.");
			
			validarConsistencias(this.atendimentoBackoffice);
			
		PrimeFaces.current().executeScript("PF('dlgConsistencias').hide();");

		} catch (ProativaException e) {
			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

		}

	}
	
	private void validarConsistencias(AtendimentoBackoffice atendimento) {
		
		for (Long id : this.idConsistencias) {
			
			for(Consistencia consistencia: this.listConsistencia) {
				
				if(id.equals(consistencia.getId()) && atendimento.getListConsistencias().stream().noneMatch( c -> c.getId().equals(id))) {
				
					this.serviceAtendimentoConsistencia.inserirAtendimentoConsistencia(atendimento.getId(), id);
					
					atendimento.adicionarConsistencias(consistencia);
					
				}
				
			}
			
		}
		
		
	}

	private List<Consistencia> validarConcistencias(List<Integer> list) {

		this.listConsistencia = this.serviceConsistencias.pesquisarConsistenciaPorEmpresa(retornarEmpresaUsuarioSessao().getId());

		List<Consistencia> listRetornoConsistencias = new ArrayList<Consistencia>();

		for (Integer codigo : list) {

			for (Consistencia consistencia : listConsistencia) {

				if (codigo.equals(consistencia.getCodigoConcistencia())) {

					this.serviceAtendimentoConsistencia.inserirAtendimentoConsistencia(this.atendimentoBackoffice.getId(), consistencia.getId());

					listRetornoConsistencias.add(consistencia);
				}

			}
		}

		if (CollectionUtils.isNotEmpty(listRetornoConsistencias))
			return this.serviceConsistencias.pesquisarConsistenciasPorAtendimento(this.atendimentoBackoffice.getId());

		return listRetornoConsistencias;

	}

	private String retornarXML(Class<?> classe, Object entidade) {

		String retorno = criarJson(entidade);
		
		if(StringUtils.isBlank(retorno)) {
			
			XStream xstream = new XStream( );
			xstream.processAnnotations(classe);
				
			return xstream.toXML(entidade);
		}
		
		return retorno;
	}

	
	private String criarJson(Object entiti) {
		  
		ObjectMapper mapper = new ObjectMapper();  
		
		  try {
			  
			return mapper.writeValueAsString(entiti);
			
		} catch (JsonProcessingException e1) {
			
		}
		  return null;
	}
	public void atualizarTelefone() {

		PrimeFaces.current().executeScript("PF('dlgTelefone').hide()");
		PrimeFaces.current().ajax().update("groupTel");
	}

	public String adiantarAtendimento(Long idAtendimento) {

		try {

			StringBuilder builder = new StringBuilder(MessagesEnum.ADIANTAR_FICHA_ATENDIMENTO_BACKOFFICE.constante);
			
			builder.append(" | ID: " + idAtendimento);
			
			this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.ADIANTAR_FICHA_ATENDIMENTO_BACKOFFICE,	builder.toString());

			Faces.setFlashAttribute("idAtendimento", idAtendimento);
			System.out.println("Removendo atendimento.");
			this.serviceAtendimentoAtivo.removerAtendimentoMap(idAtendimento);
			return PagesEnum.ATIVO_BACKOFFICE.constante + PagesEnum.REDIRECT.constante;

		} catch (Exception e) {

			e.printStackTrace();

			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

			return null;

		}

	}
	

	/**
	 * @return the atendimento
	 */
	public Atendimento getAtendimento() {
		return atendimento;
	}

	/**
	 * @param atendimento the atendimento to set
	 */
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	/**
	 * @return the atendimentoBackoffice
	 */
	public AtendimentoBackoffice getAtendimentoBackoffice() {
		return atendimentoBackoffice;
	}

	/**
	 * @param atendimentoBackoffice the atendimentoBackoffice to set
	 */
	public void setAtendimentoBackoffice(AtendimentoBackoffice atendimentoBackoffice) {
		this.atendimentoBackoffice = atendimentoBackoffice;
	}

	/**
	 * @return the historicoAtendimentoBackoffice
	 */
	public HistoricoAtendimentoBackoffice getHistoricoAtendimentoBackoffice() {
		return historicoAtendimentoBackoffice;
	}

	/**
	 * @param historicoAtendimentoBackoffice the historicoAtendimentoBackoffice to
	 *                                       set
	 */
	public void setHistoricoAtendimentoBackoffice(HistoricoAtendimentoBackoffice historicoAtendimentoBackoffice) {
		this.historicoAtendimentoBackoffice = historicoAtendimentoBackoffice;
	}

	/**
	 * @return the listStatusAtendimentos
	 */
	public List<StatusAtendimento> getListStatusAtendimentos() {
		return listStatusAtendimentos;
	}

	/**
	 * @param listStatusAtendimentos the listStatusAtendimentos to set
	 */
	public void setListStatusAtendimentos(List<StatusAtendimento> listStatusAtendimentos) {
		this.listStatusAtendimentos = listStatusAtendimentos;
	}

	/**
	 * @return the listMotivos
	 */
	public List<Motivo> getListMotivos() {
		return listMotivos;
	}

	/**
	 * @param listMotivos the listMotivos to set
	 */
	public void setListMotivos(List<Motivo> listMotivos) {
		this.listMotivos = listMotivos;
	}

	/**
	 * @return the listSubMotivos
	 */
	public List<SubMotivo> getListSubMotivos() {
		return listSubMotivos;
	}

	/**
	 * @param listSubMotivos the listSubMotivos to set
	 */
	public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
		this.listSubMotivos = listSubMotivos;
	}

	/**
	 * @return the listAtendimentosAdesao
	 */
	public List<AtendimentoBackoffice> getListAtendimentosAdesao() {
		return listAtendimentosAdesao;
	}

	/**
	 * @param listAtendimentosAdesao the listAtendimentosAdesao to set
	 */
	public void setListAtendimentosAdesao(List<AtendimentoBackoffice> listAtendimentosAdesao) {
		this.listAtendimentosAdesao = listAtendimentosAdesao;
	}

	/**
	 * @return the pabx
	 */
	public Pabx getPabx() {
		return pabx;
	}

	/**
	 * @param pabx the pabx to set
	 */
	public void setPabx(Pabx pabx) {
		this.pabx = pabx;
	}

	public List<Loja> getListLojas() {
		return listLojas;
	}

	public void setListLojas(List<Loja> listLojas) {
		this.listLojas = listLojas;
	}

	public List<Object[]> getListHistoricoAtendimento() {
		return listHistoricoAtendimento;
	}

	public void setListHistoricoAtendimento(List<Object[]> listHistoricoAtendimento) {
		this.listHistoricoAtendimento = listHistoricoAtendimento;
	}

	public Consistencia getConsistenciaSelecionada() {
		return consistenciaSelecionada;
	}

	public void setConsistenciaSelecionada(Consistencia consistenciaSelecionada) {
		this.consistenciaSelecionada = consistenciaSelecionada;
	}

	public AtendimentoBackoffice getAtendimentoSelecionado() {
		return atendimentoSelecionado;
	}

	public void setAtendimentoSelecionado(AtendimentoBackoffice atendimentoSelecionado) {
		this.atendimentoSelecionado = atendimentoSelecionado;
	}

	public Integer getFistTel() {
		return fistTel;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<StatusTelefone> getListStatusTelefones() {
		return listStatusTelefones;
	}

	public void setListStatusTelefones(List<StatusTelefone> listStatusTelefones) {
		this.listStatusTelefones = listStatusTelefones;
	}

	public GenericDadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(GenericDadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	public TipoContaEnum[] getTiposConta() {
		return TipoContaEnum.values();
	}

	/**
	 * @return the listAgendados
	 */
	public List<Object[]> getListAgendados() {
		return listAgendados;
	}

	/**
	 * @param listAgendados the listAgendados to set
	 */
	public void setListAgendados(List<Object[]> listAgendados) {
		this.listAgendados = listAgendados;
	}

	/**
	 * @return the listPendentes
	 */
	public List<Object[]> getListPendentes() {
		return listPendentes;
	}

	/**
	 * @param listPendentes the listPendentes to set
	 */
	public void setListPendentes(List<Object[]> listPendentes) {
		this.listPendentes = listPendentes;
	}

	/**
	 * @return the listConsistencia
	 */
	public List<Consistencia> getListConsistencia() {
		return listConsistencia;
	}

	/**
	 * @param listConsistencia the listConsistencia to set
	 */
	public void setListConsistencia(List<Consistencia> listConsistencia) {
		this.listConsistencia = listConsistencia;
	}

	/**
	 * @return the valorTipoPesquisaProspect
	 */
	public Boolean getValorTipoPesquisaProspect() {
		return valorTipoPesquisaProspect;
	}

	/**
	 * @param valorTipoPesquisaProspect the valorTipoPesquisaProspect to set
	 */
	public void setValorTipoPesquisaProspect(Boolean valorTipoPesquisaProspect) {
		this.valorTipoPesquisaProspect = valorTipoPesquisaProspect;
	}

	/**
	 * @return the valorPesquisaProspect
	 */
	public String getValorPesquisaProspect() {
		return valorPesquisaProspect;
	}

	/**
	 * @param valorPesquisaProspect the valorPesquisaProspect to set
	 */
	public void setValorPesquisaProspect(String valorPesquisaProspect) {
		this.valorPesquisaProspect = valorPesquisaProspect;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the adesao
	 */
	public String getAdesao() {
		return adesao;
	}

	/**
	 * @param adesao the adesao to set
	 */
	public void setAdesao(String adesao) {
		this.adesao = adesao;
	}

	/**
	 * @return the idConsistencias
	 */
	public List<Long> getIdConsistencias() {
		return idConsistencias;
	}

	/**
	 * @param idConsistencias the idConsistencias to set
	 */
	public void setIdConsistencias(List<Long> idConsistencias) {
		this.idConsistencias = idConsistencias;
	}

	public Long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Long idLoja) {
		this.idLoja = idLoja;
	}
	
	public GenericEndereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(GenericEndereco endereco) {
		this.endereco = endereco;
	}
	
	public boolean isCarregarVonixJS() {
		return carregarVonixJS;
	}
	
	public boolean isCarregarPstJS() {
		return carregarPstJS;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Object[]> getListPendentesIntegrados() {
		return listPendentesIntegrados;
	}

}

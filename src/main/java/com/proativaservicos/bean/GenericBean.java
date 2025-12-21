package com.proativaservicos.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.*;
import java.util.stream.Collectors;


import com.proativaservicos.util.constantes.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.DadosBancarios;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Endereco;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.FormaPagamento;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.GenericControle;
import com.proativaservicos.model.GenericDadosBancarios;
import com.proativaservicos.model.GenericEndereco;
import com.proativaservicos.model.GenericHistoricoAtendimento;
import com.proativaservicos.model.GenericTelefone;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.Telefone;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.service.TelefoneService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.ArquivoUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.Utils;

public class GenericBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Empresa empresa;

	@Inject
	private ServiceAbstract serviceAbstract;

	@Inject
	private AtendimentoService serviceAtendimento;

	@Inject
	private TelefoneService serviceTelefone;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private EquipeService serviceEquipe;

	public void inserir(Serializable entidade) throws ProativaException {

		inserir(entidade, true);

	}

	public void inserir(Serializable entidade, boolean empresa) throws ProativaException {

		if (empresa) {

			if (retornarEmpresaUsuarioSessao().getMatriz() == null) {

				atribuirEmpresaEntidade(entidade);

			} else {

				atribuirEmpresaMatrizEntidade(entidade);

			}

		} else if (!retornarEmpresaUsuarioSessao().isPossuiFiliais()) {

			atribuirEmpresaEntidade(entidade);

		}

		atribuirUsuarioDataEntidade(entidade, true);

		this.serviceAbstract.inserir(entidade);

	}

	public void inserirGenerico(Serializable entidade) throws ProativaException {
		this.serviceAbstract.inserir(entidade);
	}

	public void alterar(Serializable entidade) throws ProativaException {

		alterar(entidade, true);
	}

	public void alterar(Serializable entidade, boolean empresa) throws ProativaException {

		if (empresa) {

			if (retornarEmpresaUsuarioSessao().getMatriz() == null) {

				atribuirEmpresaEntidade(entidade);

			} else {

				atribuirEmpresaMatrizEntidade(entidade);
			}

		} else if (!retornarEmpresaUsuarioSessao().isPossuiFiliais()) {

			atribuirEmpresaEntidade(entidade);
		}

		atribuirUsuarioDataEntidade(entidade, false);

		this.serviceAbstract.alterar(entidade);
	}

	public void alterarGenerico(Serializable entidade) throws ProativaException {
		this.serviceAbstract.alterar(entidade);
	}

	public void excluir(Class<?> classe, Serializable pk) throws ProativaException {
		this.serviceAbstract.excluir(classe, pk);
	}
	public void excluirClean(Class<?> classe, Serializable pk) throws ProativaException {
		this.serviceAbstract.excluirClean(classe, pk);
	}

	public List<?> pesquisarTodos(Class<?> classe, String orderBy) {
		return this.serviceAbstract.pesquisarTodos(classe, orderBy);
	}

	public List<?> pesquisarTodos(Class<?> classe) {
		return this.serviceAbstract.pesquisarTodos(classe);
	}

	@SuppressWarnings("unchecked")
	public <T> T pesquisar(Class<?> classe, Serializable pk) {
		return (T) this.serviceAbstract.pesquisar(classe, pk);
	}

	private void atribuirEmpresaEntidade(Serializable entidade) {

		try {

			Method method = entidade.getClass().getDeclaredMethod("setEmpresa", new Class[] { Empresa.class });
			method.invoke(entidade, new Object[] { ((Usuario) Faces.getSessionAttribute("usuario")).getEmpresa() });

		} catch (Exception exception) {

		}
	}

	private void atribuirEmpresaMatrizEntidade(Serializable entidade) {

		try {

			Method method = entidade.getClass().getDeclaredMethod("setEmpresa", new Class[] { Empresa.class });
			method.invoke(entidade,
					new Object[] { ((Usuario) Faces.getSessionAttribute("usuario")).getEmpresa().getMatriz() });

		} catch (Exception exception) {

		}

	}

	protected void atribuirUsuarioDataEntidade(Serializable entidade, boolean inserirDataCadastro) {

		if (entidade instanceof GenericControle) {

			GenericControle genericContole = (GenericControle) entidade;
			Usuario usuario = (Usuario) Faces.getSessionAttribute("usuario");

			genericContole.setDataAlteracao(new Date());
			genericContole.setUsuarioAlteracao(usuario);

			if (inserirDataCadastro) {

				genericContole.setUsuarioCadastro(usuario);
				genericContole.setDataCadastro(genericContole.getDataAlteracao());
			}
		}
	}

	public Empresa retornarEmpresaUsuarioSessao() {

		Usuario usuario = (Usuario) Faces.getSessionAttribute("usuario");

		return (usuario == null) ? null : usuario.getEmpresa();
	}

	public Empresa retornarEmpresaMatrizUsuarioSessao() {

		Empresa empresa = retornarEmpresaUsuarioSessao();

		if (empresa != null) {

			return (empresa.getMatriz() == null) ? empresa : empresa.getMatriz();
		}

		return null;
	}

	public void inicializarEmpresa() {

		inicializarEmpresa(null);
	}

	public void inicializarEmpresa(Empresa empresa) {

		if (empresa != null) {

			this.empresa = empresa;

		} else {

			Usuario usuario = retornarUsuarioSessao();

			if (usuario != null && usuario.getEmpresa() != null && !usuario.getEmpresa().isPossuiFiliais()) {

				this.empresa = retornarEmpresaUsuarioSessao();

			} else {

				this.empresa = null;

			}
		}
	}

	public List<InstituicaoFinanceiraEnum> getBancosEnum() {

		return (List<InstituicaoFinanceiraEnum>) Arrays.asList(InstituicaoFinanceiraEnum.values()).stream()
				.filter(b -> StringUtils.isNotEmpty(b.getNumeroBanco())).collect(Collectors.toList());

	}

	public InputStream retornarFoto(String foto, String sexo) {

		try {

			if (foto == null || foto.trim().isEmpty()) {

				if (sexo == null || sexo.equals(SexoEnum.MASCULINO.name())) {

					return Faces.getResourceAsStream("/resources/img/masculino.png");

				}

				return Faces.getResourceAsStream("/resources/img/feminino.png");

			}
			

			return new FileInputStream(System.getProperty("user.home") + File.separator + "proativa" + File.separator+ "fotos" + File.separator + foto);

		} catch (FileNotFoundException e) {


			return Faces.getResourceAsStream("/resources/img/feminino.png");

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public String retornarFotoString(String foto, String sexo) {

		try {
			if (foto == null || foto.trim().isEmpty()) {

				if (sexo == null || sexo.equals(SexoEnum.MASCULINO.name())) {

					return "/crmproativa/jakarta.faces.resource/img/masculino.png.jsf";

				}

				return "/crmproativa/jakarta.faces.resource/img/feminino.png.jsf";

			}

			File stream = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator+ "fotos" + File.separator + foto);
		
			byte[] fileContent = FileUtils.readFileToByteArray(stream);
		
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			
			return "data:image/jpeg;Base64,"+encodedString;

		


		} catch (Exception e) {
			
			return "/resources/img/feminino.png";

			
		}
	}

	public String salvarArquivo(InputStream audio, String nome, String strDiretorio, boolean convertarMp3) {

		try {

			if (audio != null && StringUtils.isNotBlank(nome) && StringUtils.isNotBlank(strDiretorio)) {

				File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

				if (!diretorio.exists()) {

					Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
				}

				File arquivo = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator	+ strDiretorio + File.separator + nome);

				Files.deleteIfExists(arquivo.toPath());

				Files.copy(audio, arquivo.toPath(), new java.nio.file.CopyOption[0]);

				audio.close();

				if (convertarMp3) {

					File mp3 = Utils.converterAudioMp3Format16(arquivo, new File(arquivo.getAbsolutePath()));

					if (mp3.isFile() && mp3.exists()) {

						Files.deleteIfExists(arquivo.toPath());

					}

				}

				return diretorio.getAbsolutePath();

			} else {

				System.err.println("Não foi possivel salvar audio Diretorio e ou nome do arquivo não iformado.....");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return strDiretorio;
	}

	public String salvarArquivoMailing(InputStream mailing, String nome, String strDiretorio) {

		try {

			if (mailing != null && StringUtils.isNotBlank(nome) && StringUtils.isNotBlank(strDiretorio)) {

				File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

				if (!diretorio.exists()) {

					Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
				}

				File arquivo = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio + File.separator + nome);

				Files.deleteIfExists(arquivo.toPath());

				Files.copy(mailing, arquivo.toPath(), new java.nio.file.CopyOption[0]);

				mailing.close();

				return diretorio.getAbsolutePath();

			} else {

				System.err.println("Não foi possivel salvar audio Diretorio e ou nome do arquivo não iformado.....");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return strDiretorio;
	}

	public File salvarArquivoAudio(InputStream audio, String nome, String strDiretorio, boolean convertarMp3) {

		try {

			if ( audio != null && StringUtils.isNotBlank(nome) && StringUtils.isNotBlank(strDiretorio)) {

				File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

				if (!diretorio.exists()) {

					Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
				}

				File arquivo = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator	+ strDiretorio + File.separator + nome);

				Files.deleteIfExists(arquivo.toPath());

				Files.copy(audio, arquivo.toPath(), new java.nio.file.CopyOption[0]);

				audio.close();

				if (convertarMp3 && !Objects.requireNonNull(ArquivoUtil.obterExtensao(arquivo)).equalsIgnoreCase("mp3") ) {
					System.out.println("SALVANDO MP3");
					File mp3 = Utils.converterAudioMp3Format16(arquivo,	new File(arquivo.getAbsolutePath().replaceAll("(?i)\\.wav$", ".mp3")));

					if (mp3.isFile() && mp3.exists()) {

						Files.deleteIfExists(arquivo.toPath());
						return mp3;

					}

				}

				return arquivo;

			} else {

				System.err.println("Não foi possivel salvar audio Diretorio e ou nome do arquivo não iformado.....");

			}

		} catch (IOException e) {
			System.out.println("Arquivo já anexado. "+e.getMessage());
		//	e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;
	}
	
	public File criarDiretorios(String strDiretorio) throws IOException {
		
		File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

		if (!diretorio.exists()) {

			Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
		}
		return diretorio;
		
	}

	/**
	 * Metodo Validar Historico e se possui Agendamentos
	 * 
	 * @param historico           GenericHistoricoAtendimento
	 * @param atendimento         GenericAtendimento
	 * @param integracao          IntegracaoWs - necessário para a carga do discador
	 * @param alterarDataCadastro boolean
	 * @throws ProativaException
	 */
	public void validarHistoricoAtendimento(GenericHistoricoAtendimento historico, GenericAtendimento atendimento,	IntegracaoWs integracao, boolean alterarDataCadastro) throws ProativaException {

		if (historico.getStatusAtendimento() != null && (AcaoStatusAtendimentoEnum.AGENDAR.equals(historico.getStatusAtendimento().getAcao())	|| AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL.equals(historico.getStatusAtendimento().getAcao()))) {

			if (historico.getAgendamento() == null) {

				throw new ProativaException(MessagesEnum.DATA_AGENDAMENTO_REQUERIDO.constante);
			}

			if (historico.getAgendamento().before(new Date(System.currentTimeMillis()))) {

				throw new ProativaException(MessagesEnum.DATA_AGENDAMENTO_DEVE_SER_MAIOR_QUE_DATA_ATUAL.constante);
			}

		} else {

			historico.setDataVisualizado(new Date(System.currentTimeMillis()));

			if (atendimento.getListaHistoricos() == null)
				atendimento.setListHistoricos(new ArrayList<GenericHistoricoAtendimento>());

			for (GenericHistoricoAtendimento historicGeneric : atendimento.getListaHistoricos()) {
				historicGeneric.setDataVisualizado(new Date(System.currentTimeMillis()));
			}

		}

		// historico.setDataVisualizado(null);

		Usuario usuario = retornarUsuarioSessao();

		if (usuario.getPontoAtendimento() != null && usuario.getPontoAtendimento().getId() != null) {

			historico.setPontoAtendimento(usuario.getPontoAtendimento());

		} else {

			historico.setPontoAtendimento(null);
		}

		// VALIDAR REDISCAGEN CAMPANHA.....
		// ADICIONAR PESO DE DISCAGEM.... ENVIAR PARA DISCADOR
		if (historico.getStatusAtendimento() != null	&& AcaoStatusAtendimentoEnum.FIM_FILA.equals(historico.getStatusAtendimento().getAcao())) {

			atendimento.setPesoCarteira(Integer.valueOf((atendimento.getPesoCarteira() == null ? 1 : (atendimento.getPesoCarteira() + 1))));

			atendimento.setPesoDiscagem(Integer.valueOf((atendimento.getPesoDiscagem() == null ? 1 : (atendimento.getPesoDiscagem() + 1))));

			atendimento.setQuantidadeDiscagem(Integer.valueOf((atendimento.getQuantidadeDiscagem() == null ? 1 : (atendimento.getQuantidadeDiscagem() + 1))));

			if (((atendimento.getCampanha().getQuantdadeRediscagem() == null	&& atendimento.getQuantidadeDiscagem().intValue() <= 3)	|| (atendimento.getCampanha().getQuantdadeRediscagem() != null	&& atendimento.getQuantidadeDiscagem().intValue() < atendimento.getCampanha().getQuantdadeRediscagem().intValue()))
					&& TipoCampanhaEnum.PREDITIVA.equals(atendimento.getCampanha().getTipoCampanha())	&& integracao != null) {

				if (integracao.getTipoIntegracao().equals(TipoIntegracaoEnum.VONIX)) {

					// this.serviceVonix.alimentarDiscagemFimFila(integracao, atendimento);

				}

			}

		}

		if (historico.getUsuario() == null) {
			historico.setUsuario(usuario);
		}

		liberarAtendimento(historico, atendimento, alterarDataCadastro);
	}

	public void validarPropostaEfetivada(GenericHistoricoAtendimento historico, GenericAtendimento atendimento,	boolean editar) throws ProativaException {

		// POSVENDAS
		
		atendimento.setComissaoPagar(null);

		if (historico.getPausa() != null && historico.getStatusAtendimento() == null)
			throw new ProativaException(MessagesEnum.INFORME_STATUS_ATENDIMENTO_PAUSA.constante);
		
		if (atendimento.getEmpresa()!=null && atendimento.getEmpresa().getParametroAdesaoCampoObrigatorio()!=null && atendimento.getEmpresa().getParametroAdesaoCampoObrigatorio().equals(Boolean.FALSE)) {
			//return; 
			
		}

		if (atendimento.getCampanha().getSegmento().equals(SegmentoEnum.EMPRESTIMO)	&& historico.getStatusAtendimento().getAcao().equals(AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA)) {

		
			if (atendimento.getFormaPagamento() == null || atendimento.getFormaPagamento().getId() == null	|| atendimento.getProduto() == null || atendimento.getProduto().getId() == null) {
				throw new ProativaException(MessagesEnum.FORMA_PAGAMENTO_E_PRODUTO_SAO_OBRIGATORIOS.constante);
			}
			
						
			if (atendimento.getAdesao() != null) {

				atendimento.setAdesao(StringUtils.deleteWhitespace(atendimento.getAdesao().trim()));
			}

			InstituicaoFinanceiraEnum instituicaoFinanceiraEnum = (atendimento.getInstituicaoFinanceira() == null)	? atendimento.getCampanha().getInstituicaoFinanceira()	: atendimento.getInstituicaoFinanceira();

			if(StringUtils.isBlank(atendimento.getAdesao()) && instituicaoFinanceiraEnum.equals(InstituicaoFinanceiraEnum.BANCO_MASTER)) {

				atendimento.setAdesao(String.valueOf(atendimento.getId()));

			}else if (!instituicaoFinanceiraEnum.equals(InstituicaoFinanceiraEnum.BANCO_MASTER) && !this.serviceAtendimento.validarProposta(editar ? atendimento.getId() : null,	atendimento.getAdesao().trim(), instituicaoFinanceiraEnum, retornarEmpresaUsuarioSessao().getId())) {

				throw new ProativaException(MessagesEnum.PROPOSTA_JA_EXISTE.constante);

			}
			
			
			if (!atendimento.getCampanha().getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.BANCO_MASTER) && StringUtils.isBlank(atendimento.getEntidadePrincipal())) {
				throw new ProativaException("Entidade principal é obrigatório.");
			}

		
			if ((atendimento.getProduto().getInserirDadosBancarios()==null || atendimento.getProduto().getInserirDadosBancarios().equals(Boolean.FALSE) ) && atendimento.getFormaPagamento() != null && atendimento.getFormaPagamento().getId() != null) {

				FormaPagamento formaPagamento = pesquisar(FormaPagamento.class,	atendimento.getFormaPagamento().getId());

				if (!formaPagamento.getParametro().equals(ParamentroFormaPagamentoEnum.CHEQUE)	&& (atendimento.getListaDadosBancarios() == null	|| atendimento.getListaDadosBancarios().isEmpty())) {

					throw new ProativaException(MessagesEnum.FAVOR_CADASTRAR_DADOS_BANCARIOS.constante);

				}

				if (!formaPagamento.getParametro().equals(ParamentroFormaPagamentoEnum.CHEQUE)) {

					for (GenericDadosBancarios dadosBancarios : atendimento.getListaDadosBancarios()) {

						if (dadosBancarios.getBanco() == null || StringUtils.isBlank(dadosBancarios.getAgencia()) || StringUtils.isBlank(dadosBancarios.getConta())) {

							throw new ProativaException(MessagesEnum.DADOS_BANCARIOS_INCOMPLETOS.constante);
						}

					}

				}


			}

		} // VALIDAR OUTROS SEGMENTOS

	}

	public void validarStatusTelefone(GenericHistoricoAtendimento historicoAtendimento, GenericAtendimento atendimento,	boolean addRamal) throws ProativaException {

		Empresa empresa = retornarEmpresaUsuarioSessao();

		if (empresa.getParametroTabulacaoTelefone() != null && empresa.getParametroTabulacaoTelefone().booleanValue()	&& atendimento.getListaTelefones() != null && !atendimento.getListaTelefones().isEmpty()) {

			for (GenericTelefone telefone : atendimento.getListaTelefones()) {

				if (addRamal && retornarUsuarioSessao().getPontoAtendimento() != null) {

					String ramal = retornarUsuarioSessao().getPontoAtendimento().getRamal();
					telefone.setRamal((ramal == null) ? null : ramal.replaceAll("\\D+", ""));
				}

				if ((telefone.getStatusTelefone() == null	|| (telefone.getStatusTelefone() != null && telefone.getStatusTelefone().getId() == null))	&& (telefone.getExibe() == null || telefone.getExibe().equals(SimNaoEnum.SIM))) {

					throw new ProativaException(MessagesEnum.INFORME_STATUS_TELEFONE.constante + ": ("	+ telefone.getDdd() + ") " + telefone.getNumero());
				}

			}

		} else if (atendimento != null && atendimento.getListaTelefones() != null && !atendimento.getListaTelefones().isEmpty()) {

			for (GenericTelefone telefone : atendimento.getListaTelefones()) {

				if (addRamal && retornarUsuarioSessao().getPontoAtendimento() != null) {

					String ramal = retornarUsuarioSessao().getPontoAtendimento().getRamal();
					telefone.setRamal((ramal == null) ? null : ramal.replaceAll("\\D+", ""));
				}

				if (telefone.getStatusTelefone() != null && telefone.getStatusTelefone().getId() == null) {
					telefone.setStatusTelefone(null);
				}

			}

		}

		if (historicoAtendimento != null && historicoAtendimento.getId() != null && historicoAtendimento.getStatusAtendimento().getAcao().equals(AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA)) {

			if (atendimento.getListaTelefones() == null || atendimento.getListaTelefones().isEmpty()) {

				throw new ProativaException(MessagesEnum.DEVE_HAVER_PELO_MENOS_UM_TELEFONE_COM_STATUS_SUCESSO.constante);
			}

			int qtdadeSucesso = 0;

			for (GenericTelefone telefone : atendimento.getListaTelefones()) {

				if (telefone.getStatusTelefone() != null && telefone.getStatusTelefone().getParametro().equals(AcaoStatusTelefoneEnum.CONTATO_CLIENTE))
					qtdadeSucesso++;

			}

			if (qtdadeSucesso == 0) {
				throw new ProativaException(MessagesEnum.DEVE_HAVER_PELO_MENOS_UM_TELEFONE_COM_STATUS_SUCESSO.constante);
			}

		}
		
		if(atendimento!=null && atendimento.getCampanha()!=null && atendimento.getCampanha().getTipoCampanha().equals(TipoCampanhaEnum.RECEPTIVA) && CollectionUtils.isEmpty(atendimento.getListaTelefones())) {
			
			throw new ProativaException(MessagesEnum.DEVE_HAVER_PELO_MENOS_UM_TELEFONE_CADASTRADO.constante);
		}

	}

	/**
	 * Metodo Gerar o Historico de Atendimento.
	 * 
	 * @param historico           GenericHistoricoAtendimento
	 * @param atendimento         GenericAtendimento
	 * @param alterarDataCadastro boolean
	 */
	private void liberarAtendimento(GenericHistoricoAtendimento historico, GenericAtendimento atendimento,	boolean alterarDataCadastro) {

		if (historico.getPausa() == null || historico.getPausa().getId() == null) {

			historico.setPausa(null);

		}

		if (historico.getId() == null) {
			atendimento.adicionarHistorico(historico);
		}

		if (alterarDataCadastro) {
			historico.setDataCadastro(new Date(System.currentTimeMillis()));
		}

		if (atendimento.getProtocolo() != null) {
			historico.setProtocolo(atendimento.getProtocolo());
		}

		atendimento.setUsuarioOcupado(null);
		atendimento.setStatus(historico.getStatusAtendimento());

		// FINALIZAR ATENDIMENTO DE ACORDO COM PESO DE DISCAGEM.
		/*
		 * if (historico.getStatusAtendimento() != null &&
		 * historico.getStatusAtendimento().getAcao() != null &&
		 * historico.getStatusAtendimento().getAcao().equals(AcaoStatusAtendimentoEnum.
		 * FIM_FILA) && atendimento.getPesoDiscagem() != null &&
		 * atendimento.getPesoDiscagem().intValue() >=
		 * atendimento.getCampanha().getQuantdadeRediscagem()) {
		 * 
		 * 
		 * // PESQUISAR STATUS FINALIZADOS....
		 * 
		 * }
		 */

	}

	public void inserirEntidade(String tipo, GenericAtendimento atendimento) {

		try {

			if (tipo.equals("ENDERECO")) {

				((GenericAtendimento) atendimento).adicionarEndereco((GenericEndereco) new Endereco());

			} else if (tipo.equals("DADOS_BANCARIOS")) {

				((GenericAtendimento) atendimento)
						.adicionarDadosBancarios((GenericDadosBancarios) new DadosBancarios());

			} else if (tipo.equals("TELEFONE")) {

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void onStatusTelefone(Telefone tel, Long idStatus) {

		if (tel != null & tel.getId() != null && idStatus != null) {

			this.serviceTelefone.alterarTelefone(tel, idStatus, retornarUsuarioSessao().getId());

		}

	}
	
	
	public Equipe retornarEquipeUsuario(Long idUsuario) {
		
		Long equipe = this.usuarioService.pesquisarEquipeUsuario(idUsuario);	
		
		if(equipe==null)
			return null;
		
		return this.serviceEquipe.pesquisarEquipe(equipe,false);
		
	}

	public Date pesquisarDataCorrenteServidor() {

		return this.serviceAbstract.pesquisarDataCorrenteServidor();
	}

	public Usuario retornarUsuarioSessao() {
		return (Usuario) Faces.getSessionAttribute("usuario");
	}

	public void exportarArquivoCsv(List<String> listCabecalho, List<Object[]> listDados, String nomeArquivo) throws IOException {

		HttpServletResponse response = Faces.getResponse();
		response.reset();
		response.setContentType("text/comma-separated-values;charset=iso-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

		ServletOutputStream output = response.getOutputStream();

		output.write(ArquivoUtil.gerarArquivoCSV(listCabecalho, listDados));

		output.flush();
		output.close();

		Faces.responseComplete();

	}

	public void salvarAtendimento(GenericAtendimento atendimento, GenericHistoricoAtendimento historico,
			Usuario usuario, boolean addRamal) throws ProativaException {

		salvarAtendimento(atendimento, historico, usuario, addRamal, null);

	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private void salvarAtendimento(GenericAtendimento atendimento, GenericHistoricoAtendimento historico,	Usuario usuario, boolean addRamal, String msgAdicional) throws ProativaException {

		validarStatusTelefone(historico, atendimento, addRamal);
		validarPropostaEfetivada(historico, atendimento, true);
		validarHistoricoAtendimento(historico, atendimento, null, false);

		StringBuilder obs = new StringBuilder("ATENDIMENTO EDITADO POR:: " + usuario.getNome() + "\n");

		obs.append("DATA: " + DateUtil.builder().formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto() + "\n");

		obs.append(!StringUtils.isBlank(msgAdicional) ? msgAdicional + "\n" : "");

		obs.append(historico.getObservacao());

		historico.setObservacao(obs.toString());
		historico.setUsuario(atendimento.getUsuarioAlteracao());

		if (atendimento.getProduto() == null || atendimento.getProduto().getId() == null)
			atendimento.setProduto(null);

		if (atendimento.getFormaPagamento() == null || atendimento.getFormaPagamento().getId() == null)
			atendimento.setFormaPagamento(null);

		if (historico != null && historico.getStatusAtendimento() != null	&& !AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA.equals(historico.getStatusAtendimento().getAcao())) {

			atendimento.setContrato(null);
			atendimento.setAdesao(null);
			atendimento.setValorParcela(null);
			atendimento.setQuantidadeParcela(null);
			atendimento.setValorLiberado(null);

		}

		if (atendimento.getListInformacoesComplementares() != null
				&& !atendimento.getListInformacoesComplementares().isEmpty()) {

			Gson gson = new Gson();
			Type gsonType = new TypeToken<HashMap>() {
			}.getType();
			String gsonStr = gson.toJson(atendimento.getListInformacoesComplementares(), gsonType);
			atendimento.setInformacoesComplementares(gsonStr);
		}

		this.serviceAbstract.alterar(atendimento);
//MOVER BKP		
	}

	public TipoFormaEnvioEnum[] getFormasEnviosSaque() {
		return new TipoFormaEnvioEnum[] { TipoFormaEnvioEnum.GRAVACAO, TipoFormaEnvioEnum.BALCAO };
	}

	public TipoFormaEnvioEnum[] getFormasEnviosSaque(TipoFormaEnvioEnum formaEnvio) {

		if (TipoFormaEnvioEnum.GRAVACAO.equals(formaEnvio) || TipoFormaEnvioEnum.DIGITAL_GRAVADO.equals(formaEnvio))
			return new TipoFormaEnvioEnum[] { TipoFormaEnvioEnum.GRAVACAO, TipoFormaEnvioEnum.DIGITAL_GRAVADO };

		return new TipoFormaEnvioEnum[] { TipoFormaEnvioEnum.BALCAO, TipoFormaEnvioEnum.DIGITAL_BALCAO };
	}

	public boolean filtrarContem(Object value, Object filter, Locale locale) {

		String filterText = (filter == null) ? null : filter.toString().toLowerCase().trim();
		String valor = String.valueOf(value).toLowerCase().trim();

		if (filterText == null || filterText.equals(""))
			return true;

		if (value == null)
			return false;

		return valor.contains(filterText);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public SexoEnum[] getListSexoEnum() {
		return SexoEnum.values();
	}

	public EstadoCivilEnum[] getEstadoCivil() {
		return EstadoCivilEnum.values();
	}

	public TipoFormaEnvioEnum[] getlistFormaEnvio() {
		return TipoFormaEnvioEnum.values();
	}

	public static void main(String[] args) {

	}

	public TipoAcessoEnum[] getAcessoEnum() {
		return TipoAcessoEnum.values();
	}

	public EstadosEnum[] getEstadosEnum() {
		return EstadosEnum.values();
	}

	public InstituicaoFinanceiraEnum[] getInstituicoesFinanceiras() {
		return InstituicaoFinanceiraEnum.values();
	}

	public TipoEventoEnum[] getTipoEventos() {

		return TipoEventoEnum.values();

	}

	public EstadosEnum[] getUfEnums() {

		return EstadosEnum.values();

	}

	public TipoCampanhaEnum[] getTipoCampanhas() {

		return TipoCampanhaEnum.values();

	}
	
	public MediaEnum [] getMediasEnum() {
		return MediaEnum.values();
	}
 
}

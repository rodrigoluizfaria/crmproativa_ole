package com.proativaservicos.service.asynchronous;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bmg.CartaoProdutoSeguroBMG;
import com.proativaservicos.model.bmg.ClienteProdutoSeguroBMG;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.WsdlBmgUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ConsultaAssincronaRefin implements Serializable {

	@Resource
	private UserTransaction transaction;

	@EJB
	private ServiceAbstract abstractService;

	@Inject
	private IntegracaoService serviceIntegra;

	@Inject
	private AtendimentoService serviceAtendimento;

	@Inject
	private WsdlBmgUtil consultaBmgUtil;

	private String urlWsBMG;
	private String usuarioWsBMG;
	private String senhaWsBMG;
	
	@Inject
	private DiscadorUtil discadorUtil;

	private Importacao importacao;

	@Asynchronous
	public void consultarProdutoSeguro(Importacao importacao, Campanha campanha, Usuario usuario, Empresa empresa) {

		try {
			
			System.out.println("INICIANDO CONSULTA SEGURO....");
			
			this.importacao = importacao;
			
			inicializarTransacao();
			
			this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SEGURO);
			
			this.abstractService.alterar(this.importacao);

			this.usuarioWsBMG = campanha.getIntegrarWs().getUsr();
			this.senhaWsBMG = campanha.getIntegrarWs().getPsw();
			this.urlWsBMG = campanha.getIntegrarWs().getUrl();
			//verificarFormaEnvioIntegracaoBMG(campanha.getFormaDeEnvio(), empresa.getId());

			this.transaction.commit();
			
			List<Atendimento> listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(),	this.importacao.getId(), false);

			System.out.println("TOTAL ATENDIMENTOS [ "+campanha.getDescricao()+" ] [ "+importacao.getNomeArquivo()+" ] :" +listAtendimentos.size());
			consultaSeguroRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, false);
			
			listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(),this.importacao.getId(), true);
		
			//RECONSULTA...
			System.out.println("INICIANDO RECONSULTA SEGURO 2....");
			
			if( listAtendimentos!=null && !listAtendimentos.isEmpty() ) {
				
				inicializarTransacao();
				this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SEGURO_RECONSULTANDO);
			
				this.abstractService.alterar(this.importacao);
				this.transaction.commit();
			
				consultaSeguroRecursivo(listAtendimentos, campanha, empresa, usuario, importacao, true);
				
			}
			
			
			if( !StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao()) ) {
						
				//IMPORTAR PARA DISCADORA
				if(campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {
					
					List<Atendimento> listAtendimentosDiscadora = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha);
				
					//IMPORTAR	
					this.discadorUtil.subirCargaDiscador(empresa, campanha, listAtendimentosDiscadora);
														
				}
				
				inicializarTransacao();
				
				this.importacao.setDataFim(new Date(System.currentTimeMillis()));
				this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
				this.importacao.setQtidadeImportado(serviceAtendimento.pesquisarQuantidadeImportadosSaque(this.importacao.getId()));
				this.importacao.setLog("Importada com sucesso...");
				abstractService.alterar(this.importacao);
				
				this.transaction.commit();
				
				
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				
				if(transaction.getStatus()==0) {
					transaction.rollback();
				}
				
				if(this.importacao!=null && this.importacao.getId()!=null) {
					
					inicializarTransacao();
					this.importacao.setStatusImportacao(StatusImportacaoEnum.NAO_IMPORTADA);
					this.importacao.setQtidadeImportado(Integer.valueOf(0));
					this.importacao.setDataFim(new Date(System.currentTimeMillis()));
					this.importacao.setLog("Erro: "+e.getMessage());
					this.abstractService.alterar(this.importacao);
					this.transaction.commit();
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}finally {
			
			System.out.println("Importacao Finalizada....");
		}

	}

	private void consultaSeguroRecursivo(List<Atendimento> listAtendimentos, Campanha campanha, Empresa empresa,
			Usuario usuario, Importacao importacao, boolean possuiErros) {
		// TODO Auto-generated method stub
		
	

		List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(80);

		for (Atendimento atendimento : listAtendimentos) {

			listResultadosFuture.add(executor.submit(new ConsultaSeguro(atendimento, campanha, usuario, empresa)));

		}

		int qtidadeCancelados = 1;
		int qtidadeRepetidosCompletos = 0;
		
		for (Future<Long> future : listResultadosFuture) {
			
			try {
				
				long qtidadeCompletadosAnterior = executor.getCompletedTaskCount();
				
				Thread.sleep(29999L);
				
				Long inicioExecucao = future.get(30L,TimeUnit.SECONDS);
				
				imprimirMonitorConsultaSaqueComplementar(executor, campanha.getDescricao());
				
			
			
				if(System.currentTimeMillis() - inicioExecucao.intValue()> 60000L) {
					
					future.cancel(true);
				}
				
			
				
				if(executor.getCompletedTaskCount() == executor.getTaskCount()&& !executor.isTerminated()) {
					executor.shutdown();
				break;
				}
				
							
				if(qtidadeCompletadosAnterior == executor.getCompletedTaskCount())
					qtidadeRepetidosCompletos++;
				
				
				if(qtidadeRepetidosCompletos==5) {
					executor.shutdown();
					break;
				}
				
				
			}catch (Exception e) {
				// TODO: handle exception
				
				System.out.println(e.getMessage());
				
				System.out.println("[ "+campanha.getDescricao()+" ] - Quantidade Consultas Canceladas ==> "+qtidadeCancelados++);
			}
			
		}
		
		executor.shutdown();
		
		listAtendimentos = null;
	
		
		listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(empresa.getId(), campanha.getId(), possuiErros);
	
		
		if(listAtendimentos!=null&& !listAtendimentos.isEmpty()) {
			consultaSeguroRecursivo(listAtendimentos, campanha, empresa, usuario, importacao, possuiErros);
		}
		
		
	}
	
	
	  private void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {
		    System.out.println(
		        String.format("[Consulta Produto Seguro : " + campanha + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s", new Object[] {
		            Integer.valueOf(executor.getPoolSize()), 
		            Integer.valueOf(executor.getCorePoolSize()), 
		            Integer.valueOf(executor.getActiveCount()), 
		            Long.valueOf(executor.getCompletedTaskCount()), 
		            Long.valueOf(executor.getTaskCount()), 
		            Boolean.valueOf(executor.isShutdown()), 
		            Boolean.valueOf(executor.isTerminated()) }));
		    
		  }

	private void verificarFormaEnvioIntegracaoBMG(TipoFormaEnvioEnum formaDeEnvio, Long idEmpresa) throws ProativaException {
		// TODO Auto-generated method stub

		if (formaDeEnvio.equals(TipoFormaEnvioEnum.GRAVACAO) || formaDeEnvio.equals(TipoFormaEnvioEnum.DIGITAL_GRAVADO)) {

			IntegracaoWs integra = serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_GRAVACAO, idEmpresa,	TipoAcessoEnum.ATIVO);

			if (integra == null) {

				throw new ProativaException("Nenhuma Integração cadastrada. para o Tipo de Envio Gravação");
			}

			this.urlWsBMG = integra.getUrl();
			this.usuarioWsBMG = integra.getUsr();
			this.senhaWsBMG = integra.getPsw();

		} else if (formaDeEnvio.equals(TipoFormaEnvioEnum.BALCAO) || formaDeEnvio.equals(TipoFormaEnvioEnum.DIGITAL_BALCAO)) {

			IntegracaoWs integra = null;
					//serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_FISICO, idEmpresa,TipoAcessoEnum.ATIVO);

			if (integra == null) {

				throw new ProativaException("Nenhuma Integração cadastrada. para o Tipo de Envio Fisico.");
			}
			
		
		} else {
			// SEM FORMA DE ENVIO
			throw new ProativaException("Nenhuma Forma de Envio cadastrada..");
		}

	}

	private synchronized void atualizarAtendimento(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
		
		
		if(!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {
			
			inicializarTransacao();
			
			//HIGIENIZAR>>>>>
		
			this.serviceAtendimento.atualizarAtendimentoSeguroPapCard(atendimento.getId(),atendimento.getValorMaxOperacao(),atendimento.getMargem(),atendimento.getLimite(),atendimento.getSeguro(),atendimento.getBeneficioPrincipal(),atendimento.getDataNascimento(),atendimento.getRating(),atendimento.getObservacao());
			
			try {
				
				this.transaction.commit();
				
			} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
					 | HeuristicRollbackException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

	private String tratarErro(String erro) {
		return (erro == null) ? null
				: erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "").replaceAll("SqlMapClient operation", "javax.resource.ResourceException:")
						.replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
						.replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
						.trim();
	}

	class ConsultaSeguro implements Callable<Long> {

		private Long inicio;
		private Usuario usuario;
		private Atendimento atendimento;
		private Empresa empresa;
		private Campanha campanha;

		public ConsultaSeguro(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
			// TODO Auto-generated constructor stub

			this.atendimento = atendimento;
			this.campanha = campanha;
			this.usuario = usuario;
			this.empresa = empresa;
			this.inicio = System.currentTimeMillis();
		}

		
		/*
		 * Consulta Seguro Pap Card
		 */
		
		
		@Override
		public Long call() throws Exception {
			// TODO Auto-generated method stub
			try {
				
				if (ConsultaAssincronaRefin.this.usuarioWsBMG != null	&& ConsultaAssincronaRefin.this.senhaWsBMG != null) {

										
					ClienteProdutoSeguroBMG clienteSeguro = ConsultaAssincronaRefin.this.consultaBmgUtil.retornarCartoesDisponiveisSeguroBmg(ConsultaAssincronaRefin.this.urlWsBMG,	ConsultaAssincronaRefin.this.usuarioWsBMG,
									ConsultaAssincronaRefin.this.senhaWsBMG, atendimento, campanha.getId(), usuario,campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().getCodigo() ,false);

					if (clienteSeguro != null && clienteSeguro.getListaCartaoPlanos() !=null && !clienteSeguro.getListaCartaoPlanos().isEmpty()) {

						double valorMaximo = 0.0D;
						double margem = 0.0D;
						double limite = 0.0D;
						String entidade = null;
						Short orgao = null;
						Integer codCliente = null;
						String matricula = null;
											
						Double seguro = null;
						Integer rating = null;
						boolean primeiroCartao = true;
						boolean cpfMotivoEletifivade = false;
						boolean consultaSucesso = false;
						Date dataNascimento = null;

						for (CartaoProdutoSeguroBMG cartaoProdutoSeguro : clienteSeguro.getListaCartaoPlanos()) {

							if (cartaoProdutoSeguro != null && cartaoProdutoSeguro.getListaPlanosSeguroReturn() != null  && cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()!=null &&  StringUtils.isBlank(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getMotivoElegibilidade())) {
								
								//INFORMA PRIMEIRO CARTAO
								if (primeiroCartao) {
																		
									orgao = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getSequencialOrgao();
									
									entidade = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getCodigoEntidade();
									
									codCliente =	cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getCodigoCliente();
								
									limite = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getLimiteCartao().doubleValue();
																		
									// VALOR DO SEGURO....
									
									seguro = cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()[cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos().length-1].getValorPremio().doubleValue();

									rating = cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()[cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos().length-1].getRating();

									dataNascimento = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getDataNascimento().getTime();		
							
									primeiroCartao = false;

									// VALIDA SE O CARTAO TEM VALOR MAIOR
								} else if (seguro < cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()[cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos().length-1].getValorPremio().doubleValue()) {

									orgao = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getSequencialOrgao();
									
									entidade = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getCodigoEntidade();
																											
									codCliente =	cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getCodigoCliente();
																								
									limite = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getLimiteCartao().doubleValue();
									
									seguro = cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()[cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos().length-1].getValorPremio().doubleValue();

									rating = cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()[cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos().length-1].getRating();

									dataNascimento = cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getDataNascimento().getTime();
								}
								
								cpfMotivoEletifivade = false;
							}

							// CARTAO IMPEDIDO....
							if (StringUtils.isNotBlank(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getMotivoElegibilidade())) {
							
								this.atendimento.setObservacao(cartaoProdutoSeguro.getCartaoClienteAtivoVendaSeguro().getMotivoElegibilidade());
								cpfMotivoEletifivade = true;
						
							}else if(StringUtils.isNoneBlank(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro())) {
								
								this.atendimento.setObservacao(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro());
								cpfMotivoEletifivade = true;
								
							}else if(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getPlanos()==null) {
							
								this.atendimento.setObservacao(StringUtils.isNotBlank(cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro())?cartaoProdutoSeguro.getListaPlanosSeguroReturn().getMensagemDeErro():"Nenhum plano disponivel.");
								cpfMotivoEletifivade = true;
							
							}else if(cartaoProdutoSeguro.getListaPlanosSeguroReturn()==null) {
								
								this.atendimento.setObservacao("Nenhum plano disponivel.");
								cpfMotivoEletifivade = true;
							
							}else {
								
								consultaSucesso = true;
							}
							
							
							
						}

						this.atendimento.setLimite(BigDecimal.valueOf(limite));
						this.atendimento.setMargem(BigDecimal.valueOf(margem));
						this.atendimento.setValorMaxOperacao(BigDecimal.valueOf(valorMaximo));
						this.atendimento.setBeneficioPrincipal(matricula);
						
						this.atendimento.setOrgaoPrincipal(orgao==null?null:String.valueOf(orgao));
						
						this.atendimento.setSeguro(seguro==null?null:BigDecimal.valueOf(seguro));
						this.atendimento.setRating(rating);
						this.atendimento.setEntidadePrincipal(entidade);
						
						if(dataNascimento!=null)
							this.atendimento.setDataNascimento(dataNascimento);
						
						
						
						if(this.atendimento.getSeguro()!=null)
							this.atendimento.setObservacao("Consulta Seguro realizada com sucesso.");

				
					
					} else {
						// SEM LIMITE...ERRO VINDO WENSERVICE
						this.atendimento.setLimite(null);
						this.atendimento.setMargem(null);
						this.atendimento.setValorMaxOperacao(null);
						this.atendimento.setSeguro(null);
						
						if(StringUtils.isNotBlank(clienteSeguro.getMensagemDeErro()))
							this.atendimento.setObservacao(clienteSeguro.getMensagemDeErro());
						else
							this.atendimento.setObservacao("sem cartao");
						
					}

				} else {

					this.atendimento.setLimite(null);
					this.atendimento.setMargem(null);
					this.atendimento.setValorMaxOperacao(null);
					this.atendimento.setSeguro(null);
					this.atendimento.setObservacao("Consulta não foi realizada");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
				this.atendimento.setLimite(null);
				this.atendimento.setMargem(null);
				this.atendimento.setValorMaxOperacao(null);
				this.atendimento.setSeguro(null);
				this.atendimento.setObservacao(ConsultaAssincronaRefin.this.tratarErro(e.getMessage()));
				

			} finally {

				if (this.atendimento.getObservacao() == null || this.atendimento.getObservacao().trim().isEmpty()) {
			
					this.atendimento.setLimite(null);
					this.atendimento.setMargem(null);
					this.atendimento.setValorMaxOperacao(null);
					this.atendimento.setSeguro(null);
					this.atendimento.setObservacao("Consulta não foi realizada");
				}

			}
			
			ConsultaAssincronaRefin.this.atualizarAtendimento(this.atendimento,this.campanha,this.usuario,this.empresa);
			return this.inicio;
		}

	}

	private void inicializarTransacao() {

		try {
			if (this.transaction.getStatus() == 1) {
				this.transaction.rollback();
			}

			if (this.transaction.getStatus() != 0) {
				this.transaction.begin();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		ConsultaAssincronaRefin consult = new ConsultaAssincronaRefin();
		
		
		
	}

}

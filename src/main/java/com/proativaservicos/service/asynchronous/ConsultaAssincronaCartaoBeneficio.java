package com.proativaservicos.service.asynchronous;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.WsdlCartaoBeneficioUtil;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ConsultaAssincronaCartaoBeneficio implements Serializable {
	
	
	
	@Resource
	private UserTransaction transaction;

	@EJB
	private ServiceAbstract abstractService;

	@Inject
	private AtendimentoService serviceAtendimento;
	
	@Inject
	private WsdlCartaoBeneficioUtil consultaCartaoBeneficioUtil;
	
	@Inject
	private DiscadorUtil discadorUtil;

	private String urlWsBMG;
	private String usuarioWsBMG;
	private String senhaWsBMG;
	
	private Importacao importacao;
	
	@Asynchronous
	public void consultarRetornoSeTemCartao(Importacao importacao, Campanha campanha, Usuario usuario, Empresa empresa) {
		
		
		try {
		System.out.println("INICIANDO CONSULTA VALIDAR CARTÃO BENEFICIO....");
				
		this.importacao = importacao;
			
		
		this.usuarioWsBMG = campanha.getIntegrarWs().getUsr();
		this.senhaWsBMG = campanha.getIntegrarWs().getPsw();
		this.urlWsBMG = campanha.getIntegrarWs().getUrl();
		
		List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(),	this.importacao.getId(), false);

		System.out.println("TOTAL ATENDIMENTOS [ "+campanha.getDescricao()+" ] [ "+importacao.getNomeArquivo()+" ] :" +listAtendimentos.size());
		consultaCartaoBeneficionRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, true);
		reconsultar(campanha, empresa, usuario);
		reconsultar(campanha, empresa, usuario);
		reconsultar(campanha, empresa, usuario);
		reconsultar(campanha, empresa, usuario);
		
		if( !StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao()) ) {
			
			//IMPORTAR PARA DISCADORA
			inicializarTransacao();
			
			this.importacao.setDataFim(new Date(System.currentTimeMillis()));
			this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
			this.importacao.setQtidadeImportado(serviceAtendimento.pesquisarQuantidadeImportadosSaque(this.importacao.getId()));
			this.importacao.setLog("Importada com sucesso...");
			this.abstractService.alterar(this.importacao);
			
			this.transaction.commit();
			
			if(campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {
				
				List<Atendimento> listAtendimentosDiscadora = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha);
			
				//IMPORTAR	
				this.discadorUtil.subirCargaDiscador(empresa, campanha, listAtendimentosDiscadora);
													
			}
			
			
			
		}
					
		
	} catch (Exception e) {
		
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
			
			e1.printStackTrace();
		}
		
	}finally {
		
		System.out.println("Importacao Finalizada [ CONSULTA CARTÃO BENEFICIO]....");
	}
		
		
	}
	
	
	private void consultaCartaoBeneficionRecursivo(List<Atendimento> listAtendimentos, Campanha campanha, Empresa empresa,	Usuario usuario, Importacao importacao, boolean novasConsultas) {
		
		List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

		for (Atendimento atendimento : listAtendimentos) {

			listResultadosFuture.add(executor.submit(new ConsultaCartaoBeneficio(atendimento, campanha, usuario, empresa)));

		}

		int qtidadeCancelados = 1;
		int qtidadeRepetidosCompletos = 0;
		
		for (Future<Long> future : listResultadosFuture) {
			
			try {
				
				long qtidadeCompletadosAnterior = executor.getCompletedTaskCount();
				
				Thread.sleep(10000L);
				
				Long inicioExecucao = future.get(10L,TimeUnit.SECONDS);
				
				imprimirMonitorConsultaCartaoBeneficio(executor, campanha.getDescricao());
			
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
		
	//	listAtendimentos = null;
	
		if(novasConsultas) {
			
			List<Atendimento> listAtendimentosNovos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), importacao.getId(), false);
			
			if(CollectionUtils.isNotEmpty(listAtendimentosNovos)) {

				consultaCartaoBeneficionRecursivo(listAtendimentosNovos, campanha, empresa, usuario, importacao, true);
			}
		}
		
	}
	
	
	private void reconsultar(Campanha campanha,Empresa empresa,Usuario usuario) throws ProativaException, Exception {
		
		List<Atendimento> listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(),this.importacao.getId(), true);
		
		//RECONSULTA...
		
		if( listAtendimentos!=null && !listAtendimentos.isEmpty() ) {
			
			System.out.println("INICIANDO RECONSULTA BENEFICIO 2... TOTAL: "+listAtendimentos.size());
			
			inicializarTransacao();
			
			this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_BENEFICIO);
		
			this.abstractService.alterar(this.importacao);
			this.transaction.commit();
		
			consultaCartaoBeneficionRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, false);
			
		}
		
		
	}
	
	  private void imprimirMonitorConsultaCartaoBeneficio(ThreadPoolExecutor executor, String campanha) {
		    System.out.println(
		        String.format("[Consulta Refin : " + campanha + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s", new Object[] {
		            Integer.valueOf(executor.getPoolSize()), 
		            Integer.valueOf(executor.getCorePoolSize()), 
		            Integer.valueOf(executor.getActiveCount()), 
		            Long.valueOf(executor.getCompletedTaskCount()), 
		            Long.valueOf(executor.getTaskCount()), 
		            Boolean.valueOf(executor.isShutdown()), 
		            Boolean.valueOf(executor.isTerminated()) }));
		    
		  }

	
	
	class ConsultaCartaoBeneficio implements Callable<Long> {

		private Long inicio;
		private Usuario usuario;
		private Atendimento atendimento;
		private Empresa empresa;
		private Campanha campanha;

		public ConsultaCartaoBeneficio(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
			// TODO Auto-generated constructor stub

			this.atendimento = atendimento;
			this.campanha = campanha;
			this.usuario = usuario;
			this.empresa = empresa;
			this.inicio = System.currentTimeMillis();
		}

		
		/*
		 * Consulta REFIN
		 */
		
		
		@Override
		public Long call() throws Exception {
			// TODO Auto-generated method stub
			try {
				
				if (ConsultaAssincronaCartaoBeneficio.this.usuarioWsBMG != null	&& ConsultaAssincronaCartaoBeneficio.this.senhaWsBMG != null) {

									
					String retorno = ConsultaAssincronaCartaoBeneficio.this.consultaCartaoBeneficioUtil.validarSePossuiCartaoBeneficio(ConsultaAssincronaCartaoBeneficio.this.urlWsBMG,	ConsultaAssincronaCartaoBeneficio.this.usuarioWsBMG,ConsultaAssincronaCartaoBeneficio.this.senhaWsBMG, atendimento, campanha.getId(), usuario, true, false);
					
					
					if(StringUtils.isNotBlank(retorno)) {
						
						this.atendimento.setObservacao(retorno);
					}
						
										
				}

				
			} catch (Exception e) {
			
				
				if( !(e instanceof ProativaException) )
					e.printStackTrace();
				
				this.atendimento.setObservacao(ConsultaAssincronaCartaoBeneficio.this.tratarErro(e.getMessage()));
				

			} finally {

				if (this.atendimento.getObservacao() == null || this.atendimento.getObservacao().trim().isEmpty()) {
								
					this.atendimento.setObservacao("Consulta não foi realizada");
				}

			}
			
			ConsultaAssincronaCartaoBeneficio.this.atualizarAtendimento(this.atendimento,this.campanha,this.usuario,this.empresa);
			
			return this.inicio;
		}

	}
	
	private String tratarErro(String erro) {
		
		return (erro == null) ? null
				: erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "").replaceAll("SqlMapClient operation", "javax.resource.ResourceException:")
						.replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
						.replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
						.trim();
	}
	
	
	private synchronized void atualizarAtendimento(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
		
		
		if(!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {
			
			inicializarTransacao();
						
		
			this.serviceAtendimento.atualizarValidaCartaoBeneficio(atendimento.getId(),atendimento.getObservacao());
			
			try {
				
				this.transaction.commit();
				
			} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException |
					 HeuristicRollbackException |
					 SystemException e) {
			
				e.printStackTrace();
			}
			
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
	

}

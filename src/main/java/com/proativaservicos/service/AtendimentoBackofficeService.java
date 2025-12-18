package com.proativaservicos.service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.dao.implemets.DaoAtendimentoBackoffice;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.AtendimentoBackoffice;
import com.proativaservicos.model.Consistencia;
import com.proativaservicos.model.HistoricoAtendimentoBackoffice;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.TipoVisualizacaoEnum;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import jakarta.ejb.Stateless;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SuppressWarnings("unchecked")
@Stateless
public class AtendimentoBackofficeService extends GenericProService<AtendimentoBackoffice> {

	@Inject
	private DaoAtendimentoBackoffice dao;

	@Inject
	private AtendimentoBackofficeConsistenciasService serviceAtendimentoConsistencias;
	
	@Inject
	private HistoricoAtendimentoBackofficeService serviceAtendimentoHistorico;
	
	@Inject
	private AtendimentoService servicePropostas;
	
	@Inject
	private TelefoneService serviceTelefone;

	@Inject
	private EnderecoService serviceEndereco;

	@Inject
	private DadosBancariosService serviceDadosBancarios;
	
	@Inject
	private ConsistenciaService serviceConsistencias;
	
	@Override
	public GenericDao<AtendimentoBackoffice> getDAO() {

		return (GenericDao<AtendimentoBackoffice>) this.dao;
	}

	public void inserirAtendimentoConsistencias(AtendimentoBackoffice atendimento, Long idEmpresa,	List<Consistencia> listConsistencias,Long idUsuario) throws ProativaException {
	
	if (atendimento.getId() != null) {
		
			this.dao.alterar(atendimento);
			
		//	if(idUsuario!=null)
			//	this.dao.atualizarAtendimentoOcupado(atendimento.getId(), idUsuario);
		
	}else {
	
		this.dao.salvar(atendimento);
	}
			
		if (CollectionUtils.isNotEmpty(listConsistencias)) {
			
			for (Consistencia consistencia : listConsistencias) {
				
				//AtendimentoBackofficeConsistencia aBackofficeConsistencia = new AtendimentoBackofficeConsistencia(atendimento, consistencia);
				//atendimento.getAtendimentosConcistencias().add(aBackofficeConsistencia);
					if(!this.serviceAtendimentoConsistencias.isExisteConsistenciasAtendimento(atendimento.getId(), consistencia.getId())) {
					
						this.serviceAtendimentoConsistencias.inserirAtendimentoConsistencia(atendimento.getId(),consistencia.getId());
					
					}

			}

		}

	}
	
	public AtendimentoBackoffice pesquisarAtendimentoPorAdesao(String adesao, Long idEmpresa) {

		return this.dao.pesquisarAtendimentoPorAdesao(adesao, idEmpresa);
		
	}
	
	public boolean existeAtendimentoPorAdesao(String adesao,Long idAtendimento,  Long idEmpresa) {

		AtendimentoBackoffice atn = this.dao.pesquisarAtendimentoPorAdesao(adesao, idEmpresa);
				
		return   (atn != null && atn.getId()!=null ) && (idAtendimento!=null && idAtendimento.longValue() != atn.getId().longValue()) ;
		
	}
	
	public AtendimentoBackoffice pesquisarAtendimentoPorAdesao(String adesao, Long idEmpresa,boolean pesquisarConsistencia) {

		AtendimentoBackoffice atendimento = this.dao.pesquisarAtendimentoPorAdesao(adesao, idEmpresa);
		
		if(  atendimento!=null && atendimento.getId() !=null ) {
					
			if(pesquisarConsistencia)
				atendimento.setListConsistencias(this.serviceConsistencias.pesquisarConsistenciasPorAtendimento(atendimento.getId()));
		}
		
		return atendimento;
			
	}
	
	public List<Long> pesquisarAtendimentosPorEquipe(Long idEquipe, boolean limiteConsulta) {

		List<Long> listAtendimentos = this.dao.pesquisarAtendimentosPorEquipe(idEquipe, limiteConsulta);
	
		this.dao.atualizarAtendimentosEnviado(listAtendimentos);
		
		return  listAtendimentos;
	}

	public void atualizarAtendimentoOcupado(Long idAtendimento, Long idUsr) {
				
		this.dao.atualizarAtendimentoOcupado(idAtendimento,idUsr);
		
	}
	
	public AtendimentoBackoffice pesquisarAtendimentoPorId(Long idAtendimento) {

		AtendimentoBackoffice atendimento = this.dao.pesquisarAtendimentoPorId(idAtendimento);

		return (AtendimentoBackoffice) detalharAtendimento(atendimento, true,true);

	}
	
	public AtendimentoBackoffice pesquisarAtendimentoOcupadoPorId(Long idAtendimento) {
			return dao.pesquisarAtendimentosOcupadoPorId(idAtendimento);
		
		}
	
	@SuppressWarnings("serial")
	private AtendimentoBackoffice detalharAtendimento(AtendimentoBackoffice atendimentoBackoffice, boolean historico,boolean pesquisarAtendimentoIntegrado) {
		
		if(atendimentoBackoffice!=null) {
			
			if(atendimentoBackoffice.getAtendimento() != null && atendimentoBackoffice.getAtendimento().getId() != null) {
			
				atendimentoBackoffice.setAtendimento(this.servicePropostas.pesquisarAtendimentoPorBkoId(atendimentoBackoffice.getAtendimento().getId()));
				
				if(atendimentoBackoffice.getAtendimento()!=null) {
				
					atendimentoBackoffice.getAtendimento().setListaTelefones(this.serviceTelefone.pesquisarTelefonesPorAtendimento(atendimentoBackoffice.getAtendimento().getId()));
					atendimentoBackoffice.getAtendimento().setListaEnderecos(this.serviceEndereco.pesquisarEnderecoPorAtendimento(atendimentoBackoffice.getAtendimento().getId()));
					atendimentoBackoffice.getAtendimento().setListaDadosBancarios(this.serviceDadosBancarios.pesquisarDadosBancariosPorAtendimento(atendimentoBackoffice.getAtendimento().getId()));
					
				}
			}
			
			if(historico) {
				
				atendimentoBackoffice.setListHistoricos(new ArrayList<HistoricoAtendimentoBackoffice>());
				
				atendimentoBackoffice.setListHistoricos(this.serviceAtendimentoHistorico.pesquisarHistoricoPorAtendimento(atendimentoBackoffice.getId()));
			}
			
			atendimentoBackoffice.setListConsistencias(this.serviceConsistencias.pesquisarConsistenciasPorAtendimento(atendimentoBackoffice.getId()));
			
			
			
			if(CollectionUtils.isNotEmpty(atendimentoBackoffice.getListConsistencias())) {
				
				for (Consistencia consistenci : atendimentoBackoffice.getListConsistencias()) {
										          
					Type listType = new TypeToken<ArrayList<String>>() {}.getType();
					
					if(StringUtils.isNotBlank(consistenci.getArquivo())) {
						
						consistenci.setArquivos(new Gson().fromJson(consistenci.getArquivo(), listType));
												
					}
					
				}
			}
			
			if(pesquisarAtendimentoIntegrado) {
				
				atendimentoBackoffice.setListAtendimentosIntegrados(this.dao.pesquisarHqlCpfPorId(atendimentoBackoffice.getCpf()));
				atendimentoBackoffice.getListAtendimentosIntegrados().remove(atendimentoBackoffice);
			}
			
			return atendimentoBackoffice;
		}
		
		return null;
	}

	public List<Object[]> pesquisarAtendimentos(String cpf, String nome, String adesao, String protocolo, Long equipe,	Long operador, Long consistencia, Long statusAtendimento, Date dataInicio, Date dataFim, Usuario usuario,Long produto, Boolean tratada, Long loja, Long motivo, Long subMotivo) {

		List<Object[]> list = this.dao.pesquisarAtendimentos(cpf, nome, adesao, protocolo, equipe, operador,consistencia, statusAtendimento, dataInicio, dataFim, usuario, produto, tratada, loja, motivo,	subMotivo);

		if (tratada != null && CollectionUtils.isNotEmpty(list)) {

			List<Object[]> listTradata = new ArrayList<>();

			for (Object[] objects : list) {

				if (objects[16] != null) {

					List<Object[]> listConsistencias = (List<Object[]>) objects[16];

					if (CollectionUtils.isNotEmpty(listConsistencias)) {
						
						boolean tratar = false;
						
						for (Object[] concis : listConsistencias) {

							if (concis !=null && concis[9] != null && (((Boolean) concis[9]))) {

								tratar = true;
								break;

							}

						}

						if (tratar == tratada.booleanValue()) {

							listTradata.add(objects);

						}
						
					}
					
				}

			}
			
			return listTradata;
		}

		return list;

	}

	public List<Object[]>  pesquisarAtendimentosAgendados(Long idUsuario) {
		
		return this.dao.pesquisarAtendimentosAgendados(idUsuario);
	}
	public List<Object[]>  pesquisarAtendimentosPendentes(Long idUsuario) {
		
		return this.dao.pesquisarAtendimentosPendentes(idUsuario);
	}
	
	public List<Object[]>  pesquisarAtendimentosPendentesStatusExtrator(Long idUsuario,String statusExtrator) {
		
		return this.dao.pesquisarAtendimentosPendentesPorStatusExtrator(idUsuario,statusExtrator);
	}
	
	public List<Object[]>  pesquisarAtendimentosPendentesStatusExtrator(Long idUsuario,List<String> statusExtrator) {
		
		return this.dao.pesquisarAtendimentosPendentesPorStatusExtrator(idUsuario,statusExtrator);
	}


	public List<?> pesquisarPendencias(Long equipePendencia, Long operadorPendencia, Usuario usuario,List<Long> lojas, Long idEmpresa,String adesao,String cpf,String nome,boolean statusVazio) {
				
		return this.dao.pesquisarAtendimentosPendentes(equipePendencia,operadorPendencia,usuario, lojas,idEmpresa,adesao,cpf,nome,statusVazio);
	}
	
	public List<?> pesquisarMinhaListaAtendimento(Long operadorPendencia,String cpf,String adesao,String nome,Long idconsistencia,Long status,Long produto,Long loja,Long motivo,Long sub, Date dataInicio,Date dataFim) {
		
		return pesquisarAtendimentos(cpf, nome, adesao, null, null, operadorPendencia, idconsistencia, status, dataInicio, dataFim, null, produto, null, loja,motivo,sub);
		
		//return this.dao.pesquisarMinhaListaAtendimento(operadorPendencia,cpf,adesao,nome,dataInicio,dataFim);
	}


	public List<?> pesquisarNaoTrabalhados(Long consistenciaNaoTrabalhada, Long lojaNaoTrabalhada,List<Long> lojas, String cpfNaoTrabalhado,	String adesaoNaoTrabalhada, Usuario usuario, Long idEmpresa) {
	
		return this.dao.pesquisarNaoTrabalhados(consistenciaNaoTrabalhada,lojaNaoTrabalhada,lojas,cpfNaoTrabalhado,adesaoNaoTrabalhada,usuario,idEmpresa);
	
	}
	
	public boolean isAtendimentoOcupado(Long idAtendimento,Long idUsuario) {
		return dao.isAtendimentoOcupado(idAtendimento, idUsuario);
		
	}

	public void resetarAtendimentosEnviados() {
		this.dao.resetarAtendimentosEnviados();
		
	}

	public List<?> pesquisarRelatorioPorUsuario(Long idUsuario,Date dataInicio,Date dataFim) {

		return this.dao.pesquisarRelatoriosPorUsuario(idUsuario,dataInicio,dataFim);
		
	}
	
	public List<?> pesquisarRelatorioStatusAtendimentoPorUsuario(Long idUsuario,Date dataInicio,Date dataFim,Long idStatus) {

		return this.dao.pesquisarRelatoriosStatusAtendimentoPorUsuario(idUsuario,dataInicio,dataFim,idStatus);
		
	}
	
	public List<?> pesquisarRelatorioStatusAtendimentoPorSupervisor(Long supervisor,Date dataInicio,Date dataFim,Long idStatus) {

		return this.dao.pesquisarRelatoriosStatusAtendimentoPorSupervisor(supervisor,dataInicio,dataFim,idStatus);
		
	}
	
	public List<?> pesquisarRelatorioMotivoPorUsuario(Long idUsuario,Date dataInicio,Date dataFim,Long idmotivo) {

		return this.dao.pesquisarRelatoriosMotivoPorUsuario(idUsuario,dataInicio,dataFim,idmotivo);
		
	}
	
	public List<?> pesquisarRelatorioMotivoPorSupervisor(Long supervisor,Date dataInicio,Date dataFim,Long idmotivo) {

		return this.dao.pesquisarRelatoriosMotivoPorSupervisor(supervisor,dataInicio,dataFim,idmotivo);
		
	}
	
	public List<?> pesquisarRelatorioSubmotivoPorUsuario(Long idUsuario,Date dataInicio,Date dataFim,Long subMotivo) {

		return this.dao.pesquisarRelatoriosSubmotivoPorUsuario(idUsuario,dataInicio,dataFim,subMotivo);
		
	}
	
	public List<?> pesquisarRelatorioSubmotivoPorSupervisor(Long idUsuario,Date dataInicio,Date dataFim,Long subMotivo) {

		return this.dao.pesquisarRelatoriosSubmotivoPorSupervisor(idUsuario,dataInicio,dataFim,subMotivo);
		
	}

	public List<?> pesquisarQuantidadeConsistencia(Long idUsuario, Date dataInicioPanel, Date dataFimPanel) {
		
		return this.dao.pesquisarQuantidadeConsistencia(idUsuario,dataInicioPanel,dataFimPanel);
	}
	
	public List<?> pesquisarQuantidadeConsistenciaSupervisor(Long idSupervisor, Date dataInicioPanel, Date dataFimPanel) {
		
		return this.dao.pesquisarQuantidadeConsistenciaSupervisor(idSupervisor,dataInicioPanel,dataFimPanel);
	}

	public List<?> pesquisarProdutividadeAtendimento(Long[] equipeLong, Long[] usuarioLong,	Long produtoLong,Long loja, Usuario usurioLogado, Date dataInicio, Date dataFim,	TipoVisualizacaoEnum tipoVisualizacao) {
		
		return this.dao.pesquisarProdutividadeAtendimento(getLists(equipeLong),getLists(usuarioLong),produtoLong,loja, usurioLogado,dataInicio,dataFim,tipoVisualizacao);
	}
	
  public List<Long> getLists(Long... valores) {
		    return (valores == null) ? null : Arrays.<Long>asList(valores);
  }
  
  
  public List<?> pesquisarQuantidadeMonitorMailing(Long[] idsImportacao,Long[] lojas,Long[] equipes){
	  
	  return dao.pesquisarQuantidadeMonitorMailing( getLists(idsImportacao),getLists(lojas),getLists(equipes));
  }
  
 public List<?> pesquisarQuantidadeMonitorMailingStatusExtrator(Long[] idsImportacao,Long[] lojas,Long[] equipes){
	  
	  return dao.pesquisarQuantidadeMonitorMailingStatusExtrator( getLists(idsImportacao),getLists(lojas),getLists(equipes));
  }
 
  public Integer pesquisarQuantidadeTelefonePorExtrator(Long[] ids,Long[] lojas){
	  
	  return dao.pesquisarQuantidadeTelefonePorExtrator(getLists(ids),getLists(lojas));
  }

	public void resetarAtendimentosEnviadosPorEquipe(Long idEquipe) {
		this.dao.resetarAtendimentosEnviadosPorEquipe(idEquipe);
		
	}

	
	
}

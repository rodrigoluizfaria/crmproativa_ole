package com.proativaservicos.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;


import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.dao.implemets.DaoStatusAtendimentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Motivo;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Stateless
public class StatusAtendimentoService extends GenericProService<StatusAtendimento> {

	@Inject
	private DaoStatusAtendimentoImp dao;

	@Inject
	private SubMotivoService serivceSubMotivo;
		

	@Override
	public GenericDao<StatusAtendimento> getDAO() {
		return (GenericDao<StatusAtendimento>) this.dao;
	}


	public List<StatusAtendimento> pesquisaCriteria(StatusAtendimento statusAtendimento) {
	
			return dao.pesquisarCriteria(statusAtendimento);
	}


	public List<StatusAtendimento> pesquisarStatusAtendimentos(Long id, StatusAtendimento statusAtendimento) {
		
		return this.dao.pesquisarStatusAtendimentos(id, statusAtendimento); 
	}
	
	public List<StatusAtendimento> pesquisarStatusAtendimentos(Long id, StatusAtendimento statusAtendimento,boolean carregarMotivos) {
		
		List<StatusAtendimento> list= this.dao.pesquisarStatusAtendimentosMotivos(id, statusAtendimento,carregarMotivos); 
	
		if(carregarMotivos)
			return trabalharMapStatusAtendimento(list);
		
		return list;
	
	}
	
	public List<StatusAtendimento> pesquisarStatusAtendimentosMotivoSubMotivo(Long id, StatusAtendimento statusAtendimento,boolean carregarMotivos) {
		
		List<StatusAtendimento> list = null;
		
		if (statusAtendimento != null && ( (  statusAtendimento.getMotivo() != null	&& StringUtils.isNotBlank(statusAtendimento.getMotivo().getDescricao())) || (statusAtendimento.getSubmotivo() != null	&& StringUtils.isNotBlank(statusAtendimento.getSubmotivo().getDescricao())  ))) {
				
				List<Long> listIds = this.dao.pesquisarStatusAtendimentoMotivoSubmotivo(statusAtendimento, id);
						
				
				if(CollectionUtils.isNotEmpty(listIds))
					list = this.dao.pesquisarStatusAtendimentoMotivoPorId(listIds);
			
		}else {
		
			list = this.dao.pesquisarStatusAtendimentosMotivos(id, statusAtendimento,carregarMotivos); 
	
		
		}
		
		if(carregarMotivos && CollectionUtils.isNotEmpty(list))
			return trabalharMapStatusAtendimento(list);
		
		return list;
	
	}
	
	
	public StatusAtendimento pesquisarStatusAtendimentosListaMotivos(Long idStatus,TipoAcessoEnum acessoMotivo) {
	
		StatusAtendimento status = this.dao.pesquisarStatusAtendimentosListaMotivos(idStatus);
	
		if(status!=null && CollectionUtils.isNotEmpty(status.getListMotivos())) {
			
			for (Motivo	 motivo	 : status.getListMotivos()) {
				
				motivo.setListSubMotivos(this.serivceSubMotivo.pesquisarSubMotivosPorMotivo(motivo.getId(),acessoMotivo));
				
			}
		}
				
		return  status;
	}

	

	private List<StatusAtendimento> trabalharMapStatusAtendimento(List<StatusAtendimento> listStatus){
		
		
		if(CollectionUtils.isNotEmpty(listStatus)) {
			
			Map<Long , StatusAtendimento> mapStatus = new HashMap<Long, StatusAtendimento>();
			
			for(StatusAtendimento status: listStatus) {
					
					if(mapStatus.isEmpty() || !mapStatus.containsKey(status.getId()))
						mapStatus.put(status.getId(), status);
					
				}
				
			if(!mapStatus.isEmpty())
				return mapStatus.entrySet().stream().map(k -> k.getValue()).collect(Collectors.toList());
			}
			
			
			return listStatus;
	}
	
	public List<StatusAtendimento> pesquisarStatusAtendimentos(Long id, TipoAcessoEnum ativo) {
	
		return dao.pesquisarStatusAtendimentos(id,ativo);
	}


	public List<StatusAtendimento> pesquisarStatusAtendimentosPorCampanha(Long idCampanha) {
		return dao.pesquisarStatusAtendimentoPorCampanha(idCampanha);
	}
	
	public List<StatusAtendimento> pesquisarStatusAtendimentosPorCampanha(List<Long> listCampanhas) {
		return dao.pesquisarStatusAtendimentoPorCampanha(listCampanhas,null);
	}


	public List<StatusAtendimento> pesquisarStatusAtendimentosPorEmpresa(Long id, TipoAcessoEnum ativo) {
		return dao.pesquisarStatusAtendimentosPorEmpresa(id,ativo);
	}


	public StatusAtendimento pesquisarStatusAtendimentoPorAcao(AcaoStatusAtendimentoEnum acao, Long idEmpresa) {
		return this.dao.pesquisarStatusAtendimentoPorAcao(acao,idEmpresa);
	}


	public StatusAtendimento pesquisarAtendimentoReativador(Long idEmpresa) {
		return dao.pesquisarAtendimentoReativador(idEmpresa);
	}


	public StatusAtendimento pesquisarStatusAtendimentoPorDescricao(String descricao) {
		return this.dao.pesquisarStatusAtendimentoPorDescricao(descricao);
	}


	public List<StatusAtendimento> pesquisarStatusAtendimentosFimFilaPorCampanhaEmAtendimentos(Long idCampanhaRetrabalhar) {
		return this.dao.pesquisarStatusAtendimentosFimFilaPorCampanhaEmAtendimentos(idCampanhaRetrabalhar);
	}


	public List<StatusAtendimento> pesquisarStatusAtendimentosAgendamentosPorEmpresa(Long idEmpresa) {
		return this.dao.pesquisarStatusAtendimentosAgendamentosPorEmpresa(idEmpresa);
	}


	public StatusAtendimento pesquisarStatusAtendimentoTimeOut(Long idEmpresa) {
		return this.dao.pesquisarStatusAtendimentoTimeOut(idEmpresa);
	}
	
	

	public List<StatusAtendimento> pesquisarStatusAtendimentosBackoffice(Long idEmpresa, TipoAcessoEnum ativo) {
	
		
		List<StatusAtendimento> listStatus = this.dao.daopesquisarStatusAtendimentosBackoffice(idEmpresa, ativo);
		
		for (StatusAtendimento statusAtendimento : listStatus) {
			if (ativo != null) {
				if (CollectionUtils.isNotEmpty(statusAtendimento.getListMotivos())) {
					statusAtendimento.getListMotivos().removeIf(m->!m.getAcesso().equals(ativo));
				}
			}
		}
		
		return trabalharMapStatusAtendimento(listStatus);
		
		
	}
	
	
	public List<StatusAtendimento> pesquisarStatusAtendimentosBackoffice(Long idEmpresa,TipoAcessoEnum acesso,  boolean pesquisarMotivos) {
		
		List<StatusAtendimento> listStatus = this.dao.daopesquisarStatusAtendimentosBackoffice(idEmpresa);
		
		if(pesquisarMotivos) {
			
			for (StatusAtendimento status : listStatus) {
				
				if(status!=null && CollectionUtils.isNotEmpty(status.getListMotivos())) {
					
					if(acesso!=null)
						status.getListMotivos().removeIf(s -> !s.getAcesso().equals(acesso));
					
					for (Motivo	 motivo	 : status.getListMotivos()) {
						
						motivo.setListSubMotivos(this.serivceSubMotivo.pesquisarSubMotivosPorMotivo(motivo.getId(),TipoAcessoEnum.ATIVO));
						
					}
				}
			}
		}
		
		return trabalharMapStatusAtendimento(listStatus);
		
		
	}
	public StatusAtendimento pesquisarStatusAtendimentosBackoffice(Long idEmpresa, String desc) {
		
		return this.dao.daopesquisarStatusAtendimentosBackoffice(idEmpresa, desc);
	}


	public void inserirStatusAtendimentoMotivo(Long idStatus, Long idMotivo) {
		this.dao.inserirStatusAtendimentoMotivo(idStatus,idMotivo);
		
	}
	public List<StatusAtendimento> pesquisarStatusAtendimentoPorAcao(List<AcaoStatusAtendimentoEnum> acao, Long idEmpresa) {
		return this.dao.pesquisarStatusAtendimentoPorAcao(acao,idEmpresa);
	}


}

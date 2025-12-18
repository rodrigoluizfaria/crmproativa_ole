package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.map.HashedMap;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.PropostasEfetivadas;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoPropostasEfetivadasImp extends GenericDao<PropostasEfetivadas> {




	public List<Object[]> pesquisarPropostasPorCpf(String cpf, Long idEmpresa) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(cpf);
		Objects.requireNonNull(idEmpresa);
		
	    StringBuilder query = new StringBuilder();
	    
	    query.append("select c.descricao as campanha, ");
	    query.append("\t   \t p.descricao as produto, ");
	    query.append("       a.data_alteracao as data_atendimento, ");
	    query.append("\t   \t a.adesao, ");
	    query.append("\t   \t a.valor_liberado, ");
	    query.append("\t   \t a.quantidade_parcelas, ");
	    query.append("\t   \t a.valor_parcela, ");
	    query.append("\t   \t sc.descricao as status_contrato, ");
	    query.append("\t   \t c.data_cadastro as data_contrato, ");
	    query.append("\t\t (select h.observacao from historico_contrato h where h.contrato = ct.id order by h.data_cadastro desc limit 1) as observacao ");
	    
	    query.append("from propostas_realizadas a ");
	    query.append("\t  join campanha c on a.campanha = c.id ");
	    query.append("\t  join produto p on a.produto = p.id ");
	    query.append("    join status_atendimento s on a.status = s.id ");
	    query.append("    join contrato ct on ct.id = a.contrato ");
	    query.append("    join status_contrato sc on ct.status_contrato = sc.id ");
	    
	    query.append("where a.cpf = :cpf ");
	    query.append("\tand s.acao = 'PROPOSTA_EFETIVADA' ");
	    query.append("\tand a.empresa = :empresa ");
	    

	    Map<String, Object> parametros = new HashedMap<String, Object>();
	    
	    parametros.put("cpf", cpf.replaceAll("\\D+", "").replaceAll(" ", ""));
	    parametros.put("empresa", idEmpresa);
		
		return searchEntidades(DaoEnum.NATIVE_OBJECT,query.toString(),parametros);
	}

	public PropostasEfetivadas pesquisarPropostasPorAdesao(String adesao) {
		
		    StringBuilder query = new StringBuilder();
		    query.append("select p ");
		    query.append(" from PropostasEfetivadas p  ");
		    query.append(" where p.adesao = :adesao ");
		    
		    Map<String, Object> parametros = new HashedMap<String, Object>();
		    
		    parametros.put("adesao", adesao.replaceAll("\\D+", "").replaceAll(" ", ""));
			
			return (PropostasEfetivadas) searchEntidade(DaoEnum.HQL_QUERRY,query.toString(),parametros);
	}

	public PropostasEfetivadas pesquisarPropostasPorId(Long idAtendimento) {

		StringBuilder query = new StringBuilder();

		query.append("select a ");
		query.append("from PropostasEfetivadas a ");
		query.append("\tjoin fetch a.campanha c ");
		query.append("\tleft join fetch c.fila ");
		query.append("\tleft join fetch a.status sa ");
		query.append("\tleft join fetch a.usuarioOcupado o ");
		query.append("\tleft join fetch a.formaPagamento tp ");
		query.append("\tleft join fetch a.produto pe ");
		query.append("\tleft join fetch a.contrato ");
		query.append("\tleft join fetch a.loja ");
		query.append("\tleft join fetch a.equipe ");
		query.append("where a.id = :id ");

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idAtendimento);

		return (PropostasEfetivadas) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
		
	}
	
}

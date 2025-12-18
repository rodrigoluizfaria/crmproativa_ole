package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.Consistencia;
import com.proativaservicos.util.ConverterUtilList;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoConsistenciaImp extends GenericDao<Consistencia> {

	public List<Consistencia> pesquisarConsistenciaPorEmpresa(Long idEmpresa) {

		StringBuilder builder = new StringBuilder();
		builder.append("select c from Consistencia c ");
		builder.append("\t where c.empresa.id = :empresa");
		builder.append("\t order by c.codigoConcistencia");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresa", idEmpresa);

		return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);
	}
	
	public List<Consistencia> pesquisarConsistenciaPorEmpresaDiferenteDeResponsabilidade(Long idEmpresa,String responsabilidade) {

		StringBuilder builder = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		builder.append("select c from Consistencia c ");
		builder.append("\t where c.empresa.id = :empresa");
	
		if(StringUtils.isNotBlank(responsabilidade)) {
			builder.append("\t and upper(c.responsabilidade) <> :resp ");
			parametros.put("resp", responsabilidade.toUpperCase().trim());
		}
		builder.append("\t order by c.codigoConcistencia");
		
		parametros.put("empresa", idEmpresa);
		
		return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);
	}

	public List<Consistencia> pesquisarConsistenciaPorEmpresa(Long idEmpresa, Consistencia consistencia) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder builder = new StringBuilder();
		builder.append("select c from Consistencia c ");
		builder.append("\t where c.empresa.id = :empresa ");

		if (consistencia.getCodigoConcistencia() != null) {

			builder.append("\t and c.codigoConcistencia = :cod ");
			parametros.put("cod", consistencia.getCodigoConcistencia());
		}

		if (StringUtils.isNotEmpty(consistencia.getDescricao())) {

			builder.append("\t and upper(c.descricao) = :desc ");
			parametros.put("desc", "%" + consistencia.getDescricao().toUpperCase() + "%");
		}

		if (StringUtils.isNotEmpty(consistencia.getConvenio())) {

			builder.append("\t and upper(c.convenio) = :conv ");
			parametros.put("conv", "%" + consistencia.getConvenio().toUpperCase() + "%");
		}

		if (consistencia.getInstituicaoFinanceira() != null) {

			builder.append("\t and c.instituicaoFinanceira = :banco ");

			parametros.put("banco", consistencia.getInstituicaoFinanceira());

		}
		builder.append("\t order by c.codigoConcistencia");
		parametros.put("empresa", idEmpresa);

		return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);
	}

	public List<Consistencia> pesquisarConsistencia(List<Integer> listaConsistencias, Long idEmpresa) {

		Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder();

		builder.append("select c from Consistencia c ");

		builder.append("\t where c.empresa.id = :empresa ");

		builder.append("\t and c.codigoConcistencia in :Ids ");

		parametros.put("empresa", idEmpresa);
		parametros.put("Ids", listaConsistencias);
		builder.append("\t order by c.codigoConcistencia");
		return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);

	}

	public List<Consistencia> pesquisarConsistenciasNaoExistentes(List<Long> listIds) {

		Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder();

		builder.append("select c from Consistencia c ");

		builder.append("\t where  ");

		builder.append("\t  c.id not in :Ids ");

		parametros.put("Ids", listIds);
		builder.append("\t order by c.codigoConcistencia");
		return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);

	}

	public List<Consistencia> pesquisarConsistenciasNaoExistentes(List<Long> ids, Long idAtendimento) {

		Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder();

		builder.append(" SELECT  c.id , c.codigo_consistencia ,c.descricao,c.responsabilidade,c.instituicao_financeira,c.convenio,c.significado,c.acao,c.prazo, abc.arquivo,abc.tratada   ");
		builder.append(" from atendimento_backoffice_consistencia abc  ");

		builder.append("\t join atendimento_backoffice ab on ab.id = abc.atendimento_id  ");

		builder.append("\t join consistencia c on c.id = abc.consistencia_id  ");
		builder.append("\t WHERE ab.id = :atendimento ");
		builder.append("\t and c.codigo_consistencia  not in  " + sqlFormatedList(ids));
		builder.append("\t order by c.codigo_consistencia");
		parametros.put("atendimento", idAtendimento);
				

		return ConverterUtilList.converterConcistencias(searchEntidades(DaoEnum.NATIVE_OBJECT, builder.toString(), parametros));
	}

	public List<Consistencia> pesquisarConsistenciasPorAtendimento(Long idAtendimento) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder();

		builder.append(" SELECT  c.id , c.codigo_consistencia ,c.descricao,c.responsabilidade,c.instituicao_financeira,c.convenio,c.significado,c.acao,c.prazo, abc.arquivo,abc.tratada   ");
		
		builder.append(" from atendimento_backoffice_consistencia abc  ");

		builder.append("\t join atendimento_backoffice ab on ab.id = abc.atendimento_id  ");

		builder.append("\t join consistencia c on c.id = abc.consistencia_id  ");
		
		builder.append("\t WHERE ab.id = :atendimento ");
		builder.append("\t order by c.codigo_consistencia");
		parametros.put("atendimento", idAtendimento);

		return ConverterUtilList.converterConcistencias(searchEntidades(DaoEnum.NATIVE_OBJECT, builder.toString(), parametros));
		
	}

}

package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.Upload;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;

@SuppressWarnings("unchecked")
public class DaoUpload extends GenericDao<Upload> {

	public List<Upload> pesquisar(Upload arquivo, Usuario usuario, Date dataInicio, Date dataFim) {
		
		StringBuilder sql = new StringBuilder();
		
		Map<String, Object> parametros = new HashMap<>();

		sql.append(" select distinct d ");
		sql.append("from Upload d ");
		sql.append("\tinner join fetch d.usuarioCadastro u ");
		sql.append("\tinner join fetch d.empresa e ");
		
	
		sql.append("where  1=1 ");
		
		if( usuario.getPerfil()!= PerfilUsuarioEnum.ADMIN && usuario.getEmpresa() != null) {
			
			sql.append("\tand e.id = :empresa ");
			parametros.put("empresa", usuario.getEmpresa().getId());
		}
	
		
		if(usuario.getPerfil()!= PerfilUsuarioEnum.ADMIN ) {
			
			sql.append("\tand u.id = :usuario ");
			parametros.put("usuario", usuario.getId());
		}
		
		if(StringUtils.isNotEmpty(arquivo.getDescricao()) ) {
								
			sql.append("\tand upper(d.descricao) like :descricao ");
			parametros.put("descricao", "%" + arquivo.getDescricao().toUpperCase() + "%");
		}
		
		if(StringUtils.isNotEmpty(arquivo.getNomeArquivo()) ) {
			
			sql.append("\tand upper(d.nomeArquivo) like :nomeArquivo ");
			parametros.put("nomeArquivo", "%" + arquivo.getNomeArquivo().toUpperCase() + "%");
		}
		

		if (dataInicio != null && dataFim != null) {

			sql.append("and date(d.dataCriacao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
			parametros.put("periodoInicio", dataInicio);
			parametros.put("periodoFim", dataFim);

		} else if (dataInicio != null) {

			sql.append("and date(d.dataCriacao) >= date(:periodoInicio) ");
			parametros.put("periodoInicio", dataInicio);

		} else if (dataFim != null) {

			sql.append("and date(d.dataCriacao) <= date(:periodoFim) ");
			parametros.put("periodoFim", dataFim);
		}
		
	
		sql.append("\t order by d.descricao ");
	
		return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);
	}


	
	
	
}

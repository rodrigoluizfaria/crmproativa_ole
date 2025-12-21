package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import org.apache.commons.lang3.StringUtils;


@SuppressWarnings("unchecked")
public class DaoSubMotivo extends GenericDao<SubMotivo> {

    public List<SubMotivo> pesquisarSubMotivoPorEmpresa(Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select m ");
        query.append("from SubMotivo m ");

        query.append("where m.empresa.id = :empresa ");

        parametros.put("empresa", idEmpresa);


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<SubMotivo> pesquisarSubMotivosPorMotivo(Long idMotivo, TipoAcessoEnum acesso) {
        // TODO Auto-generated method stub

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select m ");
        query.append("from SubMotivo m ");


        query.append("where m.motivo.id = :idMotivo ");

        if (acesso != null) {
            query.append(" and m.acesso = :acesso ");
            parametros.put("acesso", acesso);

        }

        parametros.put("idMotivo", idMotivo);


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);


    }


    public List<SubMotivo> pesquisarSubMotivos(String subMotivoDescricao, Long idMotivo, TipoAcessoEnum acesso) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append(" select m ");
        query.append(" from SubMotivo m ");
        query.append("\t join fetch m.motivo mo ");
        query.append(" where 1=1 ");


        if (idMotivo != null) {

            query.append(" and m.motivo.id = :idMotivo ");
            parametros.put("idMotivo", idMotivo);
        }

        if (acesso != null) {

            query.append(" and m.acesso = :acesso ");
            parametros.put("acesso", acesso);

        }

        if (StringUtils.isNotBlank(subMotivoDescricao)) {
            query.append(" and m.descricao = :descricao ");
            parametros.put("descricao", subMotivoDescricao);

        }
        query.append(" order by m.descricao ");


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public SubMotivo pesquisarSubMotivosPorId(Long id) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append(" select m ");
        query.append(" from SubMotivo m ");
        query.append("\t join fetch m.motivo mo ");
        query.append(" where m.id = :id ");
        parametros.put("id", id);

        return (SubMotivo) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);


    }
}


	
	
	


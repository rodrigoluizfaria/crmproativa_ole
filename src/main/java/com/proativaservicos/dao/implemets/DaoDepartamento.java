package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Departamento;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named
public class DaoDepartamento extends GenericDao<Departamento> implements Serializable {

    private static final long serialVersionUID = 1L;


    public Departamento pesquisarDepartamentoPorId(Long idDepartamento) {

        if (idDepartamento == null) {
            return null;
        }

        String jpql = "SELECT DISTINCT d FROM Departamento d " +
                "LEFT JOIN FETCH d.listUsuariosDepartamento " +
                "WHERE d.id = :idDepartamento";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idDepartamento", idDepartamento);

        return (Departamento) searchEntidade(DaoEnum.HQL_QUERRY, jpql, parametros);


    }

    public List<Departamento> listarDepartamentos(TipoAcessoEnum acessoEnum) {
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        jpql.append("SELECT DISTINCT d FROM Departamento d ");
        jpql.append(" WHERE 1=1 ");

        if (acessoEnum != null) {
            jpql.append(" and d.ativo = :ativo");
            parametros.put("ativo", acessoEnum);
        }

        return searchEntidades(DaoEnum.HQL_QUERRY, jpql.toString(), parametros);
    }


    public List<Departamento> pesquisarDepartamentos(String descricao, TipoAcessoEnum acessoEnum) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append(" select distinct d ");
        query.append(" from Departamento d ");
        query.append("    join d.empresa em ");
        query.append("    LEFT JOIN FETCH d.listUsuariosDepartamento ud");
        query.append(" where 1=1 ");


        if (StringUtils.isNotBlank(descricao)) {
            query.append(" AND upper(d.descricao) like :descricao ");
            parametros.put("descricao", "%" + descricao.toUpperCase() + "%");
        }

        if (acessoEnum != null) {
            query.append("\tand d.ativo = :acesso ");
            parametros.put("acesso", acessoEnum);
        }

        query.append("order by d.descricao ");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<Departamento> pesquisarDepartamentosAssociadosPorUsuario(Long idUsuario) {

        Map<String, Object> parametros = new HashMap<>();
        String jpql = "SELECT DISTINCT d " +
                "FROM Departamento d " +
                "JOIN d.empresa em " +
                "JOIN d.listUsuariosDepartamento ud " +
                "WHERE ud.id = :idUsuario";

        parametros.put("idUsuario", idUsuario);

        return searchEntidades(DaoEnum.HQL_QUERRY, jpql, parametros);
    }
}

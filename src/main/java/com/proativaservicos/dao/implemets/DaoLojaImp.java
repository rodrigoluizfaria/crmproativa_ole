package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Loja;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoLojaImp extends GenericDao<Loja> {

    public List<Loja> pesquisarLojas(Long empresa, Loja loja, TipoAcessoEnum acesso) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT l.* ");
        query.append("from loja l ");
        query.append("where l.empresa = :empresa ");

        parametros.put("empresa", empresa);

        if (acesso != null) {

            query.append("and l.ativo = :acesso");
            parametros.put("acesso", acesso.name());

        }

        if (loja != null) {

            if (loja.getCodigoLoja() != null) {
                query.append(" and l.cod_loja = :codigoLoja ");
                parametros.put("codigoLoja", loja.getCodigoLoja());
            }

            if (loja.getInstituicaoFinanceira() != null) {
                query.append(" and l.instituicao_financeira = :instituicaoFinanceira ");
                parametros.put("instituicaoFinanceira", loja.getInstituicaoFinanceira().name());
            }

            if (loja.getAtivo() != null) {
                query.append(" and l.ativo = :ativo");
                parametros.put("ativo", loja.getAtivo());
            }

            query.append(" order by l.cod_loja ");
        }

        return (List<Loja>) searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

    }

    public List<String> pesquisarCodigosLojas(Long empresa, InstituicaoFinanceiraEnum instituicaoFinanceira) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT l.cod_loja ");
        sql.append(" from loja l ");
        sql.append(" where l.empresa = :empresa");
        sql.append(" AND l.instituicao_financeira = :instituicaoFinanceira");
        sql.append(" AND l.ativo = :ativo ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", empresa);
        parametros.put("instituicaoFinanceira", instituicaoFinanceira.toString());
        parametros.put("ativo", TipoAcessoEnum.ATIVO);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

    }

    public List<Loja> pesquisarLojasPorEquipe(Long idEquipe) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();
        query.append("select distinct e.* ");
        query.append("from loja e ");
        query.append("  join equipe_loja el on e.id = el.loja ");
        query.append("where el.equipe = :equipe ");
        parametros.put("equipe", idEquipe);

        query.append("order by e.cod_loja ");
        return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }


    public List<Loja> pesquisarLojasPorEquipes(List<Long> listEquipes) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct e.* ");
        query.append("from loja e ");
        query.append("  join equipe_loja el on e.id = el.loja ");
        query.append("where 1=1 ");

        if (CollectionUtils.isNotEmpty(listEquipes)) {
            query.append("and el.equipe in  " + sqlFormatedList(listEquipes));
        }

        query.append(" and e.ativo = 'ATIVO' ");
        query.append("order by e.cod_loja ");
        return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), null);
    }

    public List<Long> pesquisarLojasPorEquipe(Long idEquipe, TipoAcessoEnum acesso) {

        StringBuilder query = new StringBuilder();

        Map<String, Object> parametros = new HashMap<>();

        query.append("select  e.id ");

        query.append("from loja e ");

        query.append("  join equipe_loja el on e.id = el.loja ");

        query.append("where el.equipe = :equipe ");

        if (acesso != null) {

            //query.append(" AND e.ativo = :ativo ");
            //parametros.put("ativo", acesso.name());
        }

        parametros.put("equipe", idEquipe.longValue());

        query.append("order by e.cod_loja ");

        List<Long> listIds = Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
        return listIds;
    }


    public List<Loja> pesquisarLojasPorIds(List<Long> listIds) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT l.* ");
        query.append("from loja l ");
        query.append("where l.id in (:ids) ");
        parametros.put("ids", listIds);

        return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

    }

    public Loja pesquisarLojasPorCodigo(String loja, InstituicaoFinanceiraEnum instituicao, Long idEmpresa) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append(" SELECT l.* ");
        query.append(" from loja l ");
        query.append(" where l.empresa = :empresa ");
        query.append(" and l.cod_loja = :codigoLoja ");

        parametros.put("empresa", idEmpresa);
        parametros.put("codigoLoja", loja);

        if (instituicao != null) {
            query.append(" and l.instituicao_financeira = :instituicaoFinanceira ");
            parametros.put("instituicaoFinanceira", instituicao.name());
        }

        query.append(" order by l.cod_loja ");

        return (Loja) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }

    @Transactional
    public void inserirLojaExtrator(Loja l) {


        StringBuilder query = new StringBuilder("");

        query.append("INSERT into loja (cod_loja,instituicao_financeira,ativo,empresa) ");

        query.append(" VALUES (:cod_loja, :instituicao_financeira,:ativo,:empresa) ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cod_loja", l.getCodigoLoja());
        parametros.put("instituicao_financeira", l.getInstituicaoFinanceira().name());
        parametros.put("ativo", l.getAtivo().name());
        parametros.put("empresa", l.getIdEmpresa());

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);


    }


}

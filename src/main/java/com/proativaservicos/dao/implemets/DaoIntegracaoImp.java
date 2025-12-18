package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.Loja;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("unchecked")
public class DaoIntegracaoImp extends GenericDao<IntegracaoWs> {

    public List<IntegracaoWs> pesquisarCriteria(IntegracaoWs integracao) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<IntegracaoWs> criteria = builder.createQuery(IntegracaoWs.class);

        Root<IntegracaoWs> p = criteria.from(IntegracaoWs.class);
        Order orderBy = builder.asc(p.get("descricao"));
        criteria.orderBy(orderBy);
        p.fetch("empresa");
        List<Predicate> listPredicates = new ArrayList<>();

        if (integracao.getDescricao() != null) {

            ParameterExpression<String> descricaoIntegracaoWs = builder.parameter(String.class,
                    "descricaoIntegracaoWs");
            listPredicates
                    .add(builder.like(builder.upper(p.<String>get("descricao")), builder.upper(descricaoIntegracaoWs)));
        }

        if (integracao.getUrl() != null) {

            ParameterExpression<String> urlIntegracaoWs = builder.parameter(String.class, "urlIntegracaoWs");
            listPredicates.add(builder.like(builder.upper(p.<String>get("url")), builder.upper(urlIntegracaoWs)));
        }

        if (integracao.getEmpresa() != null) {

            ParameterExpression<Empresa> empresaIntegracaoWs = builder.parameter(Empresa.class, "empresaIntegracaoWs");

            listPredicates.add(builder.equal(p.<Empresa>get("empresa"), empresaIntegracaoWs));

        }

        if (integracao.getTipoIntegracao() != null) {

            ParameterExpression<TipoIntegracaoEnum> tipoIntegracaoWs = builder.parameter(TipoIntegracaoEnum.class,
                    "tipoIntegracaoWs");
            listPredicates.add(builder.equal(p.<TipoIntegracaoEnum>get("tipoIntegracao"), tipoIntegracaoWs));
        }

        if (integracao.getAtivo() != null) {

            ParameterExpression<TipoAcessoEnum> acessoIntegracaoWs = builder.parameter(TipoAcessoEnum.class,
                    "acessoIntegracaoWs");
            listPredicates.add(builder.equal(p.<TipoAcessoEnum>get("ativo"), acessoIntegracaoWs));
        }

        criteria.where(listPredicates.toArray(new Predicate[0]));

        TypedQuery<IntegracaoWs> querry = getEntityManager().createQuery(criteria);

        if (integracao.getDescricao() != null)
            querry.setParameter("descricaoIntegracaoWs", "%" + integracao.getDescricao() + "%");

        if (integracao.getUrl() != null)
            querry.setParameter("urlIntegracaoWs", "%" + integracao.getUrl() + "%");

        if (integracao.getEmpresa() != null)
            querry.setParameter("empresaIntegracaoWs", integracao.getEmpresa());

        if (integracao.getAtivo() != null)
            querry.setParameter("acessoIntegracaoWs", integracao.getAtivo());

        if (integracao.getTipoIntegracao() != null)
            querry.setParameter("tipoIntegracaoWs", integracao.getTipoIntegracao());

        return querry.getResultList();

    }

    public List<IntegracaoWs> pesquisarServicosIntegracoes(IntegracaoWs integracao) {
        // TODO Auto-generated method stub

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select s ");
        query.append("from IntegracaoWs s");
        query.append("\tjoin fetch s.empresa e ");
        query.append("where (e.id = :empresa or e.matriz.id = :empresa) ");

        parametros.put("empresa", integracao.getEmpresa().getId());

        if (integracao != null) {

            if (integracao.getTipoIntegracao() != null) {
                query.append(" and s.tipoIntegracao = :tipo");
                parametros.put("tipo", integracao.getTipoIntegracao());
            }

            if (integracao.getAtivo() != null) {
                query.append("  and s.ativo = :acesso");
                parametros.put("acesso", integracao.getAtivo());
            }
        }

        query.append(" order by s.tipoIntegracao ");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<IntegracaoWs> pesquisarIntegracoes(List<TipoIntegracaoEnum> integracaoPossiveis, Long id,
                                                   TipoAcessoEnum ativo) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select new com.proativaservicos.model.IntegracaoWs ");
        query.append("\t(s.id, s.url, s.usr, s.psw, s.nomeEmpresa, s.tipoIntegracao, s.limiteConsulta) ");

        query.append("from IntegracaoWs s ");

        query.append("where s.url is not null ");

        query.append("\tand s.url <> '' ");
        query.append("\tand s.usr is not null ");
        query.append("\tand s.usr <> '' ");
        query.append("\tand s.psw is not null ");
        query.append("\tand s.psw <> '' ");

        if (!integracaoPossiveis.isEmpty()) {

            query.append("\tand s.tipoIntegracao in (:tipoIntegracoes) ");
            parametros.put("tipoIntegracoes", integracaoPossiveis);

        }

        if (id != null) {
            query.append("\tand s.empresa.id = :empresa ");
            parametros.put("empresa", id);
        }

        if (ativo != null) {
            query.append("\tand s.ativo = :acesso ");
            parametros.put("acesso", ativo);
        }
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public IntegracaoWs pesquisarIntegracoes(TipoIntegracaoEnum tipoIntegra, Long idEmpresa, TipoAcessoEnum ativo) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select new com.proativaservicos.model.IntegracaoWs ");
        query.append("\t(s.id, s.url, s.usr, s.psw, s.nomeEmpresa, s.tipoIntegracao, s.limiteConsulta,s.token,s.validadeToken,s.habilitarWebsocket) ");
        query.append("from IntegracaoWs s ");
        query.append("where s.url is not null ");
        query.append("\tand s.tipoIntegracao = :tipoIntegracao ");
        query.append("\tand s.url <> '' ");
        query.append("\tand s.usr is not null ");
        query.append("\tand s.usr <> '' ");
        query.append("\tand s.psw is not null ");
        query.append("\tand s.psw <> '' ");

        if (idEmpresa != null) {
            query.append("\tand s.empresa.id = :empresa ");
            parametros.put("empresa", idEmpresa);
        }

        if (ativo != null) {
            query.append("\tand s.ativo = :acesso ");
            parametros.put("acesso", ativo);
        }

        parametros.put("tipoIntegracao", tipoIntegra);

        return (IntegracaoWs) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
    }

    public IntegracaoWs pesquisarIntegracoes(Long id) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select new com.proativaservicos.model.IntegracaoWs ");
        query.append("\t(s.id, s.url, s.usr, s.psw, s.nomeEmpresa, s.tipoIntegracao, s.limiteConsulta,s.token,s.validadeToken,s.habilitarWebsocket) ");
        query.append("from IntegracaoWs s ");
        query.append("where s.url is not null ");
       query.append("\tand s.url <> '' ");
        query.append("\tand s.usr is not null ");
        query.append("\tand s.usr <> '' ");
        query.append("\tand s.psw is not null ");
        query.append("\tand s.psw <> '' ");

        query.append("\tand s.id = :id ");

        parametros.put("id", id);

        return (IntegracaoWs) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, 0, 1);
    }


    public IntegracaoWs pesquisarIntegracoesVonixResults(TipoIntegracaoEnum tipoIntegra, TipoAcessoEnum ativo) {
        // TODO Auto-generated method stub
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select s  ");

        query.append("\tfrom IntegracaoWs s ");
        query.append("\tjoin fetch s.empresa e  ");
        query.append("where s.url is not null ");
        query.append("\tand s.tipoIntegracao = :tipoIntegracao ");
        query.append("\tand s.url <> '' ");
        query.append("\tand s.usr is not null ");
        query.append("\tand s.usr <> '' ");
        query.append("\tand s.psw is not null ");
        query.append("\tand s.psw <> '' ");


        if (ativo != null) {
            query.append("\tand s.ativo = :acesso ");
            parametros.put("acesso", ativo);
        }

        parametros.put("tipoIntegracao", tipoIntegra);

        return (IntegracaoWs) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0),
                Integer.valueOf(1));
    }

    public IntegracaoWs pesquisaIngracaoDiscador(Long idEmpresa) {
        // TODO Auto-generated method stub
        StringBuilder query = new StringBuilder();

        query.append("select i.* ");
        query.append("from integracao_ws i ");
        query.append("where i.tipo_integracao in (:tiposIntegracoes) ");
        query.append("\tand i.empresa = :empresa ");
        query.append("\tand i.ativo = :acesso ");
        query.append("order by  ");
        query.append("case tipo_integracao ");
        query.append("\twhen '" + TipoIntegracaoEnum.VONIX.name() + "' then 1 ");
        query.append("\twhen '" + TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.name() + "' then 2 ");
        query.append("end ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);
        parametros.put("acesso", TipoAcessoEnum.ATIVO.name());
        parametros.put("tiposIntegracoes", Arrays.asList(new String[]{TipoIntegracaoEnum.TRES_CPLUS.name(), TipoIntegracaoEnum.VONIX.name(), TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.name(), TipoIntegracaoEnum.VIRTUAL_POWER_DIALER.name()}));


        return (IntegracaoWs) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
    }

    public List<IntegracaoWs> pesquisarIntegracao(Long id, TipoAcessoEnum ativo) {

        return null;

    }

    public List<IntegracaoWs> pesquisarIntegracoes(TipoIntegracaoEnum tipoIntegracao, TipoAcessoEnum ativo) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select new com.proativaservicos.model.IntegracaoWs ");
        query.append("\t(s.id,s.descricao, s.url, s.usr, s.psw, s.nomeEmpresa, s.tipoIntegracao) ");
        query.append("from IntegracaoWs s ");
        query.append("where s.url is not null ");
        query.append("\tand s.url <> '' ");
        query.append("\tand s.usr is not null ");
        query.append("\tand s.usr <> '' ");
        query.append("\tand s.psw is not null ");
        query.append("\tand s.psw <> '' ");

        if (tipoIntegracao != null) {

            query.append("\tand s.tipoIntegracao = :tipoIntegracao ");
            parametros.put("tipoIntegracao", tipoIntegracao);

        }

        if (ativo != null) {
            query.append("\tand s.ativo = :acesso ");
            parametros.put("acesso", ativo);
        }

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<IntegracaoWs> pesquisarIntegracoes(TipoIntegracaoEnum tipo, Long idEmpresa, TipoAcessoEnum ativo,
                                                   InstituicaoFinanceiraEnum instituicao) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select ");
        query.append("\t distinct i.id, ");
        query.append("\t i.url, ");
        query.append("\t i.usr, ");
        query.append("\t i.psw, ");
        query.append("\t i.empresa, ");
        query.append("\t i.tipo_integracao, ");
        query.append("\t l.id as loja, ");
        query.append("\t i.limite_consulta,  ");
        query.append("\t l.cod_loja as loja_empresa ");
        query.append(" from integracao_ws i  ");
        query.append("\t  inner join empresa e on i.empresa = e.id   ");
        query.append("\t  inner join loja l on l.empresa = e.id ");
        query.append(" where i.tipo_integracao = :tipoIntegracao  ");
        query.append("\t and i.url is not null ");
        query.append("\t and i.url <> ''  ");
        query.append("\t and i.usr is not null ");
        query.append("\t and i.usr <> ''  ");
        query.append("\t and i.psw is not null ");
        query.append("\t and i.psw <> ''  ");
        query.append("\t and i.cod_loja = l.cod_loja  ");

        if (idEmpresa != null) {
            query.append("\tand e.id = :empresa ");
            parametros.put("empresa", idEmpresa);
        }

        if (ativo != null) {
            query.append("\tand i.ativo = :acesso ");
            query.append("\tand l.ativo = :acesso ");
            parametros.put("acesso", ativo.name());
        }

        if (instituicao != null) {
            query.append("  AND l.instituicao_financeira = :instituicaoFinanceira ");
            parametros.put("instituicaoFinanceira", instituicao.name());
        }

        parametros.put("tipoIntegracao", tipo.name());

        List<Object[]> resultados = searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        List<IntegracaoWs> servicos = new ArrayList<>();

        for (Object[] resultado : resultados) {

            IntegracaoWs servico = new IntegracaoWs();

            BigInteger id = (BigInteger) resultado[0];

            servico.setId(id.longValue());

            servico.setUrl(resultado[1].toString());
            servico.setUsr(resultado[2].toString());
            servico.setPsw(resultado[3].toString());
            servico.setNomeEmpresa(resultado[4].toString());


            servico.setTipoIntegracao(TipoIntegracaoEnum.getTipoIntegracao(resultado[5].toString()));

            servico.setLoja(new Loja(Long.valueOf(Integer.valueOf((Integer) resultado[6]))));

            servico.setLimiteConsulta((Integer) resultado[7]);

            servico.setCodLojaEmpresa(resultado[8].toString());
            servicos.add(servico);
        }

        return servicos;
    }

    public IntegracaoWs pesquisarIntegracao(TipoIntegracaoEnum tipoIntegracao, Long idEmpresa, TipoAcessoEnum ativo) {


        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select new com.proativaservicos.model.IntegracaoWs ");
        query.append("\t\t(s.id, s.url, s.usr, s.psw, s.nomeEmpresa, s.tipoIntegracao, s.limiteConsulta) ");
        query.append("from IntegracaoWs s ");
        query.append("where s.tipoIntegracao = :tipoIntegracao ");
        query.append("\tand s.url is not null ");
        query.append("\tand s.url <> '' ");
        query.append("\tand s.usr is not null ");
        query.append("\tand s.usr <> '' ");
        query.append("\tand s.psw is not null ");
        query.append("\tand s.psw <> '' ");

        if (idEmpresa != null) {
            query.append("\tand s.empresa.id = :empresa ");
            parametros.put("empresa", idEmpresa);

        }

        if (ativo != null) {

            query.append("\tand s.ativo = :ativo ");
            parametros.put("ativo", ativo);

        }

        parametros.put("tipoIntegracao", tipoIntegracao);

        return (IntegracaoWs) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));

    }

    public IntegracaoWs pesquisarIntegracaoDiscadora(Long idEmpresa) {

        StringBuilder query = new StringBuilder();
        query.append("select s.* ");
        query.append("from integracao_ws s ");
        query.append("where s.tipo_integracao in (:tiposIntegracoes) ");
        query.append("\tand s.empresa = :empresa ");
        query.append("\tand s.ativo = :ativo ");
        query.append("order by  ");
        query.append("case tipo_integracao ");
        query.append("\twhen '" + TipoIntegracaoEnum.VONIX.name() + "' then 1 ");
        query.append("\twhen '" + TipoIntegracaoEnum.VIRTUAL_POWER_DIALER.name() + "' then 2 ");
        query.append("\twhen '" + TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.name() + "' then 3 ");

        query.append("end ");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);
        parametros.put("ativo", TipoAcessoEnum.ATIVO.name());

        parametros.put("tiposIntegracoes", Arrays.asList(new String[]{TipoIntegracaoEnum.VONIX.name(), TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.name(), TipoIntegracaoEnum.TRES_CPLUS.name(), TipoIntegracaoEnum.VIRTUAL_POWER_DIALER.name(),}));

        return (IntegracaoWs) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
    }


    @Transactional
    public void atualizarDataValidadeIntegracao(Long id, Date validadeToken, String token) {

        String query = " update integracao_ws set validade_token = :validade_token , token = :token where id = :id ";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        parametros.put("validade_token", validadeToken);
        parametros.put("token", token);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);


    }
}

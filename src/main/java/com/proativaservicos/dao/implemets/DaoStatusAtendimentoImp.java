package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.util.CollectionUtils;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class DaoStatusAtendimentoImp extends GenericDao<StatusAtendimento> {

    public List<StatusAtendimento> pesquisarCriteria(StatusAtendimento statusAtendimento) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StatusAtendimento> criteria = builder.createQuery(StatusAtendimento.class);

        Root<StatusAtendimento> s = criteria.from(StatusAtendimento.class);
        Order orderBy = builder.asc(s.get("descricao"));
        criteria.orderBy(orderBy);

        List<Predicate> listPredicates = new ArrayList<>();

        ParameterExpression<String> descricaoStatus = builder.parameter(String.class, "descricaoStatus");
        listPredicates.add(builder.like(builder.upper(s.<String>get("descricao")), descricaoStatus));

        if (statusAtendimento.getEmpresa() != null) {

            ParameterExpression<Empresa> empresaStatus = builder.parameter(Empresa.class, "empresaStatus");
            listPredicates.add(builder.equal(s.<Empresa>get("empresa"), empresaStatus));

        }

        if (statusAtendimento.getAtivo() != null) {

            ParameterExpression<TipoAcessoEnum> acessoStatus = builder.parameter(TipoAcessoEnum.class, "acessoStatus");
            listPredicates.add(builder.equal(s.<TipoAcessoEnum>get("ativo"), acessoStatus));

        }

        if (statusAtendimento.getAcao() != null) {

            ParameterExpression<AcaoStatusAtendimentoEnum> acaoStatus = builder
                    .parameter(AcaoStatusAtendimentoEnum.class, "acaoStatus");
            listPredicates.add(builder.equal(s.<AcaoStatusAtendimentoEnum>get("acao"), acaoStatus));

        }

        criteria.where(listPredicates.toArray(new Predicate[0]));
        TypedQuery<StatusAtendimento> querry = getEntityManager().createQuery(criteria);

        querry.setParameter("descricaoStatus", "%" + statusAtendimento.getDescricao() + "%");
        if (statusAtendimento.getEmpresa() != null)
            querry.setParameter("empresaStatus", statusAtendimento.getEmpresa());

        if (statusAtendimento.getAcao() != null)
            querry.setParameter("acaoStatus", statusAtendimento.getAcao());

        if (statusAtendimento.getAtivo() != null)
            querry.setParameter("acessoStatus", statusAtendimento.getAtivo());

        return querry.getResultList();
    }

    public List<StatusAtendimento> pesquisarStatusAtendimentos(Long id, StatusAtendimento statusAtendimento) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");
        query.append("where sa.empresa.id = :empresa ");

        parametros.put("empresa", id);

        if (statusAtendimento != null) {

            if (statusAtendimento.getDescricao() != null && !statusAtendimento.getDescricao().isEmpty()) {
                query.append("and upper(sa.descricao) like :descricao ");
                parametros.put("descricao", "%" + statusAtendimento.getDescricao().toUpperCase() + "%");
            }

            if (statusAtendimento.getAcao() != null) {
                query.append("and sa.acao = :acao ");
                parametros.put("acao", statusAtendimento.getAcao());
            }

            if (statusAtendimento.getAtivo() != null) {
                query.append("and sa.ativo = :acesso ");
                parametros.put("acesso", statusAtendimento.getAtivo());
            }

            if (statusAtendimento.isTarget()) {

                query.append("and sa.target = :target ");
                parametros.put("target", statusAtendimento.isTarget());
            }

            if (statusAtendimento.getStatusAtendimentoMatriz() != null) {

                query.append("and sam.id = :statusAtendimentoMatriz ");
                parametros.put("statusAtendimentoMatriz", statusAtendimento.getStatusAtendimentoMatriz().getId());
            }

            query.append("order by sa.descricao");
        }

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<StatusAtendimento> pesquisarStatusAtendimentosMotivos(Long id, StatusAtendimento statusAtendimento, boolean pesquisarMotivos) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");

        if (pesquisarMotivos)
            query.append("\t left join fetch sa.listMotivos mo ");

        query.append("where sa.empresa.id = :empresa ");

        parametros.put("empresa", id);

        if (statusAtendimento != null) {

            if (statusAtendimento.getDescricao() != null && !statusAtendimento.getDescricao().isEmpty()) {
                query.append("and upper(sa.descricao) like :descricao ");
                parametros.put("descricao", "%" + statusAtendimento.getDescricao().toUpperCase() + "%");
            }

            if (statusAtendimento.getAcao() != null) {
                query.append("and sa.acao = :acao ");
                parametros.put("acao", statusAtendimento.getAcao());
            }

            if (statusAtendimento.getAtivo() != null) {
                query.append("and sa.ativo = :acesso ");
                parametros.put("acesso", statusAtendimento.getAtivo());
            }

            if (statusAtendimento.isTarget()) {

                query.append("and sa.target = :target ");
                parametros.put("target", statusAtendimento.isTarget());
            }

            if (statusAtendimento.getStatusAtendimentoMatriz() != null) {

                query.append("and sam.id = :statusAtendimentoMatriz ");
                parametros.put("statusAtendimentoMatriz", statusAtendimento.getStatusAtendimentoMatriz().getId());
            }

            if (statusAtendimento.getMotivo() != null && StringUtils.isNotBlank(statusAtendimento.getMotivo().getDescricao())) {

            }

            if (statusAtendimento.getSubmotivo() != null && StringUtils.isNotBlank(statusAtendimento.getSubmotivo().getDescricao())) {

            }

            query.append("order by sa.descricao");
        }

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public List<StatusAtendimento> pesquisarStatusAtendimentoMotivoPorId(List<Long> listIds) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");
        query.append("\t left join fetch sa.listMotivos mo ");
        query.append("where sa.id in  " + sqlFormatedList(listIds));

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public List<Long> pesquisarStatusAtendimentoMotivoSubmotivo(StatusAtendimento statusAtendimento, Long empresa) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("	SELECT DISTINCT sa.id FROM status_atendimento sa  ");
        query.append("\tLEFT JOIN motivo_status_atendimento ms on ms.status_atendimento = sa.id  ");
        query.append("\tLEFT JOIN motivo m on m.id = ms.motivo  ");
        query.append("\tLEFT JOIN submotivo sb on sb.motivo = m.id  ");

        query.append(" where sa.empresa = :empresa ");
        parametros.put("empresa", empresa);

        if (statusAtendimento != null) {

            if (statusAtendimento.getDescricao() != null && !statusAtendimento.getDescricao().isEmpty()) {
                query.append("and upper(sa.descricao) like :descricao ");
                parametros.put("descricao", "%" + statusAtendimento.getDescricao().toUpperCase() + "%");
            }

            if (statusAtendimento.getAcao() != null) {
                query.append("and sa.acao = :acao ");
                parametros.put("acao", statusAtendimento.getAcao());
            }

            if (statusAtendimento.getAtivo() != null) {
                query.append("and sa.ativo = :acesso ");
                parametros.put("acesso", statusAtendimento.getAtivo());
            }

            query.append("and sa.target = :target ");
            parametros.put("target", statusAtendimento.isTarget());

            if (statusAtendimento.getMotivo() != null && StringUtils.isNotBlank(statusAtendimento.getMotivo().getDescricao())) {

                query.append("and upper(m.descricao) like :motivo ");
                parametros.put("motivo", "%" + statusAtendimento.getMotivo().getDescricao().toUpperCase() + "%");

            }

            if (statusAtendimento.getSubmotivo() != null && StringUtils.isNotBlank(statusAtendimento.getSubmotivo().getDescricao())) {

                query.append("and upper(sb.descricao) like :submotivo ");
                parametros.put("submotivo", "%" + statusAtendimento.getSubmotivo().getDescricao().toUpperCase() + "%");
            }


        }

        List<Integer> list = searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        if (org.apache.commons.collections4.CollectionUtils.isEmpty(list))
            return null;


        return list.stream().map(Integer::longValue).collect(Collectors.toList());

    }

    public StatusAtendimento pesquisarStatusAtendimentosListaMotivos(Long idStatus) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");

        query.append("\tleft join fetch sa.listMotivos mo ");


        query.append("where sa.id = :id ");


        parametros.put("id", idStatus);

        query.append("order by sa.descricao");

        return (StatusAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    /**
     * Retorna Lista de status atendimento por ID da empresa.
     *
     * @param long           id
     * @param TipoAcessoEnum ativo
     * @return List<StatusAtendimento>
     */
    public List<StatusAtendimento> pesquisarStatusAtendimentos(Long id, TipoAcessoEnum ativo) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");
        query.append("where sa.empresa.id = :empresa and sa.ativo = :ativo");

        parametros.put("empresa", id);
        parametros.put("ativo", ativo);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<StatusAtendimento> pesquisarStatusAtendimentoPorCampanha(Long idCampanha) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct s.* ");
        query.append("from status_atendimento s ");
        query.append("  join campanha_status_atendimento cs on s.id = cs.status_atendimento ");
        query.append("where cs.campanha = :campanha ");
        query.append("\tand s.ativo = 'ATIVO' ");
        query.append("order by s.descricao ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", idCampanha);

        return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }

    public List<StatusAtendimento> pesquisarStatusAtendimentosPorEmpresa(Long idEmpresa, TipoAcessoEnum ativo) {
        // TODO Auto-generated method stub
        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select new com.proativaservicos.model.StatusAtendimento(t.id, t.descricao, t.acao, t.ativo) ");
        query.append("from StatusAtendimento t ");
        query.append("where t.empresa.id = :empresa ");

        if (ativo != null) {

            query.append("\tand t.ativo = :acesso ");
            parametros.put("acesso", ativo);

        }
        query.append("order by t.descricao ");

        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public StatusAtendimento pesquisarStatusAtendimentoPorAcao(AcaoStatusAtendimentoEnum acao, Long idEmpresa) {
        // TODO Auto-generated method stub
        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select t ");
        query.append("from StatusAtendimento t ");
        query.append("where t.acao = :acao ");
        query.append("\tand t.empresa.id = :empresa ");
        query.append("\tand t.ativo = 'ATIVO' ");
        parametros.put("acao", acao);
        parametros.put("empresa", idEmpresa);
        List<StatusAtendimento> resultados = searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros,
                Integer.valueOf(0), Integer.valueOf(1));

        return (resultados == null || resultados.isEmpty()) ? null : resultados.get(0);

    }

    public StatusAtendimento pesquisarAtendimentoReativador(Long idEmpresa) {
        // TODO Auto-generated method stub
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        query.append("select t ");
        query.append("from StatusAtendimento t ");
        query.append("where t.acao = 'FIM_FILA' ");
        query.append("\tand upper(t.descricao) = 'REATIVADO::' ");
        query.append("\tand t.empresa.id = :empresa ");
        parametros.put("empresa", idEmpresa);
        return (StatusAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<StatusAtendimento> pesquisarStatusAtendimentoPorCampanha(List<Long> listCampanhas, Boolean matriz) {
        // TODO Auto-generated method stub

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        query.append("select sa.id, sa.descricao, sa.acao ");
        query.append("from status_atendimento sa ");
        query.append("\tjoin campanha_status_atendimento csa on (sa.id = csa.status_atendimento)");
        query.append("where csa.campanha in (:campanhas) ");
        query.append("\tand sa.ativo = 'ATIVO' ");

        if (matriz != null)
            if (matriz.booleanValue()) {
                query.append("\tand sa.status_atendimento_matriz is null ");
            } else {
                query.append("\tand sa.status_atendimento_matriz is not null ");
            }

        query.append("order by sa.descricao ");
        parametros.put("campanhas", listCampanhas);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public StatusAtendimento pesquisarStatusAtendimentoPorDescricao(String descricao) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        query.append("select t ");
        query.append("from StatusAtendimento t ");
        query.append("where upper(t.descricao) = :descricao  ");

        parametros.put("descricao", descricao);
        return (StatusAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<StatusAtendimento> pesquisarStatusAtendimentosPorCampanhaEmAtendimentos(Long idCampanhaRetrabalhar) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct * from ( ");
        query.append("\tselect sa.id, sa.descricao, sa.acao ");
        query.append("\tfrom status_atendimento sa ");
        query.append("\t\tjoin public.atendimento a on (sa.id = a.status)");
        query.append("\twhere a.campanha = :campanha ");

        query.append(") s order by s.descricao ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", idCampanhaRetrabalhar);


        return CollectionUtils.converterStatusAtendimento(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    public List<StatusAtendimento> pesquisarStatusAtendimentosFimFilaPorCampanhaEmAtendimentos(Long idCampanhaRetrabalhar) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct * from ( ");
        query.append("\tselect sa.id, sa.descricao, sa.acao ");
        query.append("\tfrom status_atendimento sa ");
        query.append("\t\tjoin public.atendimento a on (sa.id = a.status)");
        query.append("\twhere a.campanha = :campanha ");
        query.append("\tand sa.acao in ('NONE', 'FIM_FILA') ");

        query.append(") s order by s.descricao ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", idCampanhaRetrabalhar);

        return CollectionUtils.converterStatusAtendimento(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    public List<StatusAtendimento> pesquisarStatusAtendimentosAgendamentosPorEmpresa(Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select new com.proativaservicos.model.StatusAtendimento(t.id,t.descricao) ");
        query.append("from StatusAtendimento t ");
        query.append("where t.empresa.id = :empresa ");
        query.append("\tand t.acao like 'AGENDAR%' ");
        query.append("\tand t.ativo = 'ATIVO' ");
        query.append("order by t.descricao ");
        parametros.put("empresa", idEmpresa);
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public StatusAtendimento pesquisarStatusAtendimentoTimeOut(Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select t ");

        query.append("from StatusAtendimento t ");

        query.append("where t.acao = 'FIM_FILA' ");

        query.append("\tand upper(t.descricao) = 'ENCERRADO POR TIMEOUT' ");

        query.append("\tand t.empresa.id = :empresa ");

        parametros.put("empresa", idEmpresa);

        return (StatusAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<StatusAtendimento> daopesquisarStatusAtendimentosBackoffice(Long idEmpresa, TipoAcessoEnum ativo) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");
        query.append("\tleft join fetch sa.listMotivos mo ");

        query.append("where sa.empresa.id = :empresa ");
        query.append(" and  sa.backoffice = true ");

        if (ativo != null) {

            query.append(" and sa.ativo = :ativo  ");
            parametros.put("ativo", ativo);
        }

        parametros.put("empresa", idEmpresa);
        query.append(" order by sa.descricao");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<StatusAtendimento> daopesquisarStatusAtendimentosBackoffice(Long idEmpresa) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");
        query.append("\tleft join fetch sa.listMotivos mo ");

        query.append("where sa.empresa.id = :empresa ");
        query.append(" and  sa.backoffice = true ");

        parametros.put("empresa", idEmpresa);
        query.append(" order by sa.descricao");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public StatusAtendimento daopesquisarStatusAtendimentosBackoffice(Long idEmpresa, String descricao) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("from StatusAtendimento sa ");
        query.append("\tleft join fetch sa.statusAtendimentoMatriz sam ");
        query.append("\tleft join fetch sa.listMotivos mo ");

        query.append("where sa.empresa.id = :empresa ");
        query.append(" and  sa.backoffice = true ");


        query.append(" and sa.descricao = :desc  ");
        parametros.put("desc", descricao);


        parametros.put("empresa", idEmpresa);
        query.append(" order by sa.descricao");

        return (StatusAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    @Transactional
    public void inserirStatusAtendimentoMotivo(Long idStatus, Long idMotivo) {

        StringBuilder query = new StringBuilder("");
        query.append("INSERT into motivo_status_atendimento (motivo,status_atendimento) ");
        query.append(" VALUES (:motivo, :status_atendimento) ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("status_atendimento", idStatus);
        parametros.put("motivo", idMotivo);


        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

    }


    public List<StatusAtendimento> pesquisarStatusAtendimentoPorAcao(List<AcaoStatusAtendimentoEnum> acoes, Long idEmpresa) {
        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select t ");
        query.append("from StatusAtendimento t ");
        query.append("where t.acao in (:acao) ");
        query.append("and t.empresa.id = :empresa ");
        query.append("and t.ativo = 'ATIVO' ");

        parametros.put("acao", acoes);
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(),parametros );


    }


}

package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Pabx;
import com.proativaservicos.model.PontoAtendimento;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class DaoPontoAtendimentoImp extends GenericDao<PontoAtendimento> {

    public List<PontoAtendimento> pesquisarCriteria(PontoAtendimento e) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PontoAtendimento> criteria = builder.createQuery(PontoAtendimento.class);
        Root<PontoAtendimento> s = criteria.from(PontoAtendimento.class);
        Order orderBy = builder.asc(s.get("descricao"));
        criteria.orderBy(orderBy);
        s.fetch("empresa");

        List<Predicate> predicates = new ArrayList<>();

        if (e.getDescricao() != null) {

            ParameterExpression<String> descricaoPontoAtendimento = builder.parameter(String.class,
                    "descricaoPontoAtendimento");
            predicates.add(
                    builder.like(builder.upper(s.<String>get("descricao")), builder.upper(descricaoPontoAtendimento)));
        }

        if (e.getAtivo() != null) {

            ParameterExpression<String> ativoPontoAtendimento = builder.parameter(String.class,
                    "ativoPontoAtendimento");
            predicates.add(builder.equal(s.<String>get("ativo"), ativoPontoAtendimento));
        }

        if (e.getRamal() != null) {

            ParameterExpression<String> ramalPontoAtendimento = builder.parameter(String.class,
                    "ramalPontoAtendimento");
            predicates.add(builder.like(builder.upper(s.<String>get("ramal")), builder.upper(ramalPontoAtendimento)));
        }

        if (e.getEmpresa() != null) {

            ParameterExpression<Empresa> idEmpresa = builder.parameter(Empresa.class, "idEmpresa");
            predicates.add(builder.equal(s.<Empresa>get("empresa"), idEmpresa));
        }

        if (e.getPabx() != null) {
            ParameterExpression<Pabx> pabxPontoAtendimento = builder.parameter(Pabx.class, "pabxPontoAtendimento");
            predicates.add(builder.equal(s.<Empresa>get("pabx"), pabxPontoAtendimento));

        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<PontoAtendimento> query = getEntityManager().createQuery(criteria);

        if (e.getDescricao() != null) {

            query.setParameter("descricaoPontoAtendimento", "%" + e.getDescricao() + "%");
        }

        if (e.getRamal() != null) {

            query.setParameter("ramalPontoAtendimento", "%" + e.getDescricao() + "%");
        }

        if (e.getAtivo() != null) {

            query.setParameter("ativoPontoAtendimento", e.getAtivo());
        }

        if (e.getPabx() != null) {

            query.setParameter("pabxPontoAtendimento", e.getPabx());
        }

        if (e.getEmpresa() != null) {

            query.setParameter("idEmpresa", e.getEmpresa());
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        return query.getResultList();
    }

    public List<PontoAtendimento> pesquisarPontosAtendimentos(PontoAtendimento pontoAtendimento) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select p ");
        query.append("from PontoAtendimento p  ");
        query.append("\tjoin fetch p.empresa e ");
        query.append("\tjoin fetch p.pabx s ");
        query.append("where (e.id = :empresa or e.matriz.id = :empresa) ");

        parametros.put("empresa", pontoAtendimento.getEmpresa().getId());

        if (pontoAtendimento.getPabx() != null && pontoAtendimento.getPabx().getId() != null) {
            query.append(" and s.id = :servidorVoip ");
            parametros.put("servidorVoip", pontoAtendimento.getPabx().getId());
        }

        if (StringUtils.isNotBlank(pontoAtendimento.getRamal())) {
            query.append(" and p.ramal = :ramal ");
            parametros.put("ramal", pontoAtendimento.getRamal());
        }

        if (StringUtils.isNotBlank(pontoAtendimento.getDescricao())) {
            query.append(" and p.descricao = :descricao ");
            parametros.put("descricao", pontoAtendimento.getDescricao());
        }

        if (pontoAtendimento.getAtivo() != null) {
            query.append("\tand p.ativo = :acesso ");
            parametros.put("acesso", pontoAtendimento.getAtivo());
        }

        query.append(" order by p.ramal ");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public PontoAtendimento pesquisarPontoAtendimentosPorUsuario(Long id) {
        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select p ");
        query.append("from PontoAtendimento p  ");
        query.append("\tjoin fetch p.empresa e ");
        query.append("\tjoin fetch p.pabx s ");
        query.append("where p.id = :id and p.ativo = 'ATIVO' ");


        parametros.put("id", id);


        return (PontoAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<PontoAtendimento> pesquisarPontoAtendimentosPorEmpresa(Long idEmpresa) {
        // TODO Auto-generated method stub
        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select p ");
        query.append("from PontoAtendimento p  ");
        query.append("\tjoin fetch p.empresa e ");
        query.append("\tjoin fetch p.pabx s ");
        query.append("where p.empresa.id = :empresa and p.ativo = 'ATIVO' ");

        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


}

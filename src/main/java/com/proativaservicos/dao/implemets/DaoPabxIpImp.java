package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Pabx;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPabxEnum;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
@Named
public class DaoPabxIpImp extends GenericDao<Pabx> {

    public List<Pabx> pesquisarCriteria(Pabx pabx) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pabx> criteria = builder.createQuery(Pabx.class);

        Root<Pabx> p = criteria.from(Pabx.class);
        Order orderBy = builder.asc(p.get("descricao"));
        criteria.orderBy(orderBy);

        List<Predicate> listPredicates = new ArrayList<>();

        if (pabx.getDescricao() != null) {

            ParameterExpression<String> descricaoPabx = builder.parameter(String.class, "descricaoPabx");
            listPredicates.add(builder.like(builder.upper(p.<String>get("descricao")), builder.upper(descricaoPabx)));
        }

        if (pabx.getEmpresa() != null) {

            ParameterExpression<Pabx> empresaPabx = builder.parameter(Pabx.class, "empresaPabx");
            listPredicates.add(builder.equal(p.<Pabx>get("empresa"), empresaPabx));
        }

        if (pabx.getTipo() != null) {

            ParameterExpression<TipoPabxEnum> tipoPabx = builder.parameter(TipoPabxEnum.class, "tipoPabx");
            listPredicates.add(builder.equal(p.<TipoPabxEnum>get("tipo"), tipoPabx));
        }

        if (pabx.getAtivo() != null) {

            ParameterExpression<TipoAcessoEnum> acessoPabx = builder.parameter(TipoAcessoEnum.class, "acessoPabx");
            listPredicates.add(builder.equal(p.<TipoAcessoEnum>get("ativo"), acessoPabx));
        }

        criteria.where(listPredicates.toArray(new Predicate[0]));

        TypedQuery<Pabx> querry = getEntityManager().createQuery(criteria);

        if (pabx.getDescricao() != null)
            querry.setParameter("descricaoPabx", "%" + pabx.getDescricao() + "%");

        if (pabx.getEmpresa() != null)
            querry.setParameter("empresaPabx", pabx.getEmpresa());

        if (pabx.getAtivo() != null)
            querry.setParameter("acessoPabx", pabx.getAtivo());

        if (pabx.getTipo() != null)
            querry.setParameter("tipoPabx", pabx.getTipo());

        return querry.getResultList();
    }

    public List<Pabx> pesquisarServidoresVoipPorEmpresa(Long id, boolean matriz, TipoAcessoEnum acesso) {
        // TODO Auto-generated method stub
        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select s ");
        query.append("from Pabx s ");
        query.append(" \tinner join fetch s.empresa e ");
        query.append("where e.id = :empresa ");

        if (matriz) {
            query.append("\tor e.matriz.id = :empresa ");
        }

        if (acesso != null) {
            query.append("\tand s.ativo = :acesso ");
            parametros.put("acesso", acesso);
        }
        query.append("order by e.nome, s.descricao");

        parametros.put("empresa", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public List<Pabx> pesquisarServidoresVoip(Long id, Pabx pabx) {
        // TODO Auto-generated method stub
        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select s ");
        query.append("from Pabx s ");
        query.append(" join fetch s.empresa e ");
        query.append("where e.id = :codigo ");

        parametros.put("codigo", id);

        if (pabx != null) {

            if (pabx.getTipo() != null) {

                query.append("\tand s.tipo = :tipoServidor ");
                parametros.put("tipoServidor", pabx.getTipo());
            }

            if (!pabx.getDescricao().isEmpty()) {

                query.append("  and s.descricao = :descricao ");
                parametros.put("descricao", pabx.getDescricao());
            }

            if (pabx.getAtivo() != null) {

                query.append("  and s.acesso = :acesso ");
                parametros.put("acesso", pabx.getAtivo());
            }
        }
        query.append(" order by s.tipo ");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public Pabx pesquisarPorPontoAtendimento(Long idPontoAtendimento) {

        StringBuilder query = new StringBuilder();
        query.append("select pa.* ");
        query.append("from pabx pa ");
        query.append("\tjoin ponto_atendimento p on (p.pabx = pa.id) ");
        query.append("where p.id = :pontoAtendimento ");
        query.append("\tand pa.ativo = 'ATIVO'");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("pontoAtendimento", idPontoAtendimento);

        return (Pabx) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }

}

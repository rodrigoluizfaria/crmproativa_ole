package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Produto;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.*;


@Named
public class DaoProdutoImp extends GenericDao<Produto> {

    public List<Produto> pesquisarCriteria(Produto produto) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);

        Root<Produto> p = criteria.from(Produto.class);
        Order orderBy = builder.asc(p.get("descricao"));
        criteria.orderBy(orderBy);

        List<Predicate> listPredicates = new ArrayList<>();

        if (produto.getDescricao() != null) {

            ParameterExpression<String> descricaoProduto = builder.parameter(String.class, "descricaoProduto");
            listPredicates
                    .add(builder.like(builder.upper(p.<String>get("descricao")), builder.upper(descricaoProduto)));
        }

        if (produto.getEmpresa() != null) {

            ParameterExpression<Empresa> empresaProduto = builder.parameter(Empresa.class, "empresaProduto");
            listPredicates.add(builder.equal(p.<Empresa>get("empresa"), empresaProduto));
        }

        if (produto.getTipoProduto() != null) {

            ParameterExpression<TipoProdutoEnum> tipoProduto = builder.parameter(TipoProdutoEnum.class, "tipoProduto");
            listPredicates.add(builder.equal(p.<TipoProdutoEnum>get("tipoProduto"), tipoProduto));
        }

        if (produto.getAtivo() != null) {
            ParameterExpression<TipoAcessoEnum> acessoProduto = builder.parameter(TipoAcessoEnum.class,
                    "acessoProduto");
            listPredicates.add(builder.equal(p.<TipoAcessoEnum>get("ativo"), acessoProduto));
        }

        criteria.where(listPredicates.toArray(new Predicate[0]));

        TypedQuery<Produto> querry = getEntityManager().createQuery(criteria);

        if (produto.getDescricao() != null)
            querry.setParameter("descricaoProduto", "%" + produto.getDescricao() + "%");

        if (produto.getEmpresa() != null)
            querry.setParameter("empresaProduto", produto.getEmpresa());

        if (produto.getAtivo() != null)
            querry.setParameter("acessoProduto", produto.getAtivo());

        if (produto.getTipoProduto() != null)
            querry.setParameter("tipoProduto", produto.getTipoProduto());

        return querry.getResultList();
    }

    public List<Produto> pesquisarProdutos(Long id, Produto produto) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("from Produto p ");
        query.append("where p.empresa.id = :empresa ");

        if (produto != null) {

            if (produto.getDescricao() != null && !produto.getDescricao().isEmpty()) {
                query.append("and upper(p.descricao) like :descricao ");
                parametros.put("descricao", "%" + produto.getDescricao().toUpperCase() + "%");
            }

            if (produto.getTipoProduto() != null) {
                query.append("\tand p.tipoProduto = :tipoProduto ");
                parametros.put("tipoProduto", produto.getTipoProduto());
            }

            if (produto.getAtivo() != null) {
                query.append("\tand p.ativo = :acesso ");
                parametros.put("acesso", produto.getAtivo());
            }

            query.append("order by p.descricao");
        }

        parametros.put("empresa", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Produto> pesquisarProdutoPorEmpresa(Long id, TipoAcessoEnum acesso) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select p ");
        query.append("from Produto p ");
        query.append("where p.empresa.id = :empresa");

        if (acesso != null) {

            query.append("\tand p.ativo = :acesso ");
            parametros.put("acesso", acesso);

        }

        parametros.put("empresa", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Produto> pesquisarProdutosPorEmpresa(Long idEmpresa) {
        StringBuilder query = new StringBuilder();
        query.append("select new com.proativaservicos.model.Produto(p.id, p.descricao) ");
        query.append("from Produto p ");
        query.append("where p.empresa.id = :empresa ");
        query.append("\tand p.ativo = 'ATIVO' ");
        query.append("order by p.descricao ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Produto> pesquisarProdutosCampanha(Long idCampanha) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct p.* ");
        query.append("from produto p ");
        query.append("  join campanha_produto cp on p.id = cp.produto ");
        query.append("where cp.campanha = :campanha ");
        query.append("\tand p.ativo = 'ATIVO' ");
        query.append("order by p.descricao ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", idCampanha);

        return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

    }

    public List<Object[]> pesquisarProjecaoPorProduto(Usuario usuario, SituacaoEnum situacaoEnum,
                                                      PeriodoEnum periodoEnum, Date dataInicial, Date dataFinal, TipoDadosEnum tipoDadosEnum) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        String tipoMeta = "";
        String atributo = null;
        String projecaoMeta = null;

        query.append("select p.descricao,  ");
        query.append("\t\t sum((EXTRACT(EPOCH FROM a.data_fim_atendimento - a.data_inicio_atendimento))/60) as total_trabalhado, ");
        query.append("\t\t count(distinct a.usuario_alteracao) as total_operador, ");

        if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {

            if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                query.append(
                        " \t     coalesce(sum(case when sc.acao = 'CONCLUIDA' then a.valor_liberado else 0 end), 0) as total_vendido, ");
            } else {

                query.append(
                        " \t     coalesce(count(case when sc.acao = 'CONCLUIDA' then a.id else 0 end), 0) as total_vendido, ");
            }

            tipoMeta = "_concluida";
            atributo = "c.data_cadastro";

        } else {

            if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                query.append(
                        " \t     coalesce(sum(case when s.acao = 'PROPOSTA_EFETIVADA' then a.valor_liberado else 0 end), 0) as total_vendido, ");

            } else {
                query.append(
                        " \t     coalesce(count(case when s.acao = 'PROPOSTA_EFETIVADA' then a.id else 0 end), 0) as total_vendido, ");
            }
            atributo = "a.data_alteracao";
        }
        if (PeriodoEnum.DIARIO.equals(periodoEnum)) {

            if (tipoMeta.isEmpty()) {

                if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                    projecaoMeta = "coalesce(p.meta_diaria, m.produto_diaria_realizada_valor) ";

                } else {

                    projecaoMeta = "coalesce(p.meta_diaria_quantidade, m.produto_diaria_realizada_quantidade) ";

                }

            } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                projecaoMeta = "coalesce(p.meta_diaria_concluida, m.produto_diaria_concluida_valor) ";

            } else {

                projecaoMeta = "coalesce(p.meta_diaria_concluida_quantidade, m.produto_diaria_concluida_quantidade) ";
            }

        } else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {

            if (tipoMeta.isEmpty()) {

                if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                    projecaoMeta = "coalesce(p.meta_semanal, m.produto_semanal_realizada_valor) ";

                } else {

                    projecaoMeta = "coalesce(p.meta_semanal_quantidade, m.produto_semanal_realizada_quantidade) ";
                }

            } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                projecaoMeta = "coalesce(p.meta_semanal_concluida, m.produto_semanal_concluida_valor) ";

            } else {

                projecaoMeta = "coalesce(p.meta_semanal_concluida_quantidade, m.produto_semanal_concluida_quantidade) ";
            }

        } else if (tipoMeta.isEmpty()) {

            if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

                projecaoMeta = "coalesce(p.meta_mensal, m.produto_mensal_realizada_valor) ";

            } else {

                projecaoMeta = "coalesce(p.meta_mensal_quantidade, m.produto_mensal_realizada_quantidade) ";

            }

        } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

            projecaoMeta = "coalesce(p.meta_mensal_concluida, m.produto_mensal_concluida_valor) ";

        } else {

            projecaoMeta = "coalesce(p.meta_mensal_concluida_quantidade, m.produto_mensal_concluida_quantidade) ";

        }

        query.append(projecaoMeta + ",");
        query.append("\t\tsum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as quantidade_atendimento  ");
        query.append("from propostas_realizadas a ");

        if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {

            query.append("    join contrato c on (a.contrato = c.id) ");
            query.append("    join status_contrato sc on (c.status_contrato = sc.id) ");

        }

        query.append("\t  join status_atendimento s on a.status = s.id ");
        query.append("    join produto p on a.produto = p.id ");
        query.append("\t  join empresa e on a.empresa = e.id ");
        query.append("\t  join meta m on m.empresa = e.id ");
        query.append("\twhere a.usuario_alteracao in (select distinct u.id ");
        query.append("\t\t\t\t\t\t\t\t  from usuario u ");
        query.append("\t\t\t\t\t\t\t\t  \t  join empresa e on u.empresa = e.id ");
        query.append("\t\t\t\t\t\t\t\t  where (e.id = :empresa or e.matriz = :empresa)) ");
        parametros.put("empresa", usuario.getEmpresa().getId());

        if (PeriodoEnum.DIARIO.equals(periodoEnum)) {

            query.append("    and date(" + atributo + ") = date(now()) ");

        } else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {

            query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
            parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaSemana().getData());
            parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaSemana().getData());

        } else if (PeriodoEnum.DATA.equals(periodoEnum)) {

            query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
            parametros.put("dataInicio", dataInicial);
            parametros.put("dataFim", dataFinal);

        } else {

            query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
            parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaMes().getData());
            parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaMes().getData());

        }

        query.append(" group by p.descricao, " + projecaoMeta);
        query.append(" order by total_vendido desc, quantidade_atendimento desc, p.descricao ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Produto> pesquisarProdutos(List<Long> listIds) {

        String query = "select t from Produto t where t.id in (:ids)";

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("ids", listIds);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

}

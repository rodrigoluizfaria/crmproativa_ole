package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.DadosBancarios;
import com.proativaservicos.model.GenericDadosBancarios;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Dependent
public class DaoDadosBancariosImp extends GenericDao<DadosBancarios> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<? extends GenericDadosBancarios> pesquisarDadosBancariosPorAtendimento(Long id) {
        // TODO Auto-generated method stub

        StringBuilder builder = new StringBuilder();

        builder.append("select d from DadosBancarios d");
        builder.append("\t where d.atendimento.id = :atendimento");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("atendimento", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);


    }

    public Integer pesquisarQuantidadeDadosBancariosPorCampanha(Long campanha) {


        StringBuilder query = new StringBuilder();
        query.append("select  ");
        query.append("\tmax(a.total) ");
        query.append("from ( ");
        query.append("\t\tselect ");
        query.append("\t\t\th.atendimento, count(h.id) as total ");
        query.append("\t\tfrom public.dados_bancarios h ");
        query.append("\t\t\tjoin public.atendimento a on h.atendimento = a.id ");
        query.append("\t\twhere a.campanha = :campanha ");
        query.append("\t\tgroup by h.atendimento ) a");


        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        Number result = (Number) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        if (result == null)
            result = Integer.valueOf(0);

        return Integer.valueOf(result.intValue());

    }

    public List<Object[]> pesquisarDadosBancariosPorCampanha(Long campanha) {
        StringBuilder query = new StringBuilder();
        query.append("select * ");
        query.append("from ( ");
        query.append("SELECT d.atendimento,  ");
        query.append("       COALESCE(d.banco,'') as banco, ");
        query.append("       COALESCE(d.agencia, '') as agencia, ");
        query.append("       COALESCE(d.digito_agencia, '') as dv_agencia, ");
        query.append("       COALESCE(d.conta, '') as conta, ");
        query.append("       COALESCE(d.digito_conta, '') as dv_conta, ");
        query.append("       COALESCE(d.tipo_conta, '') as tipo_conta, ");
        query.append("       COALESCE(d.uf, '') as estado ");
        query.append("FROM public.dados_bancarios d  ");
        query.append("\tjoin public.atendimento a on d.atendimento = a.id ");
        query.append("where a.campanha = :campanha ) d ");

        query.append("  order by d.atendimento ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<DadosBancarios> pesquisarDadosBancariosPorCpf(String cpf) {

        StringBuilder builder = new StringBuilder();

        builder.append("select d from DadosBancarios d");
        builder.append("\t where d.atendimento.cpf = :cpf");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("cpf", cpf);

        return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros, Integer.valueOf(0), Integer.valueOf(5));


    }


    public void atualizarDadosBancarios(Long atendimento, InstituicaoFinanceiraEnum banco, String agencia, String conta) {

        String query = "";
        query = "update dados_bancarios set banco = :banco , agencia = :agencia , conta = :conta where atendimento = :atendimento";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("banco", banco.name());
        parametros.put("agencia", agencia);
        parametros.put("conta", conta);
        parametros.put("atendimento", atendimento);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);
    }

    public List<DadosBancarios> pesquisarDadosBancariosPorCliente(Long idCliente) {

        if (idCliente == null) {
            return Collections.emptyList();
        }

        String jpql = "select d from DadosBancarios d where d.cliente.id = :idCliente";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idCliente", idCliente);

        return searchEntidades(DaoEnum.HQL_QUERRY, jpql, parametros, 0, 5);
    }
}

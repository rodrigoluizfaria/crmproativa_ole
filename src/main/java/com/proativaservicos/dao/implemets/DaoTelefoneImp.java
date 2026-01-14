package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.GenericTelefone;
import com.proativaservicos.model.Telefone;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named
public class DaoTelefoneImp extends GenericDao<Telefone> implements Serializable {


    private static final long serialVersionUID = 1L;

    public List<? extends GenericTelefone> pesquisarTelefonesPorAtendimento(Long id) {

        StringBuilder query = new StringBuilder();

        query.append("select t from Telefone t ");
        query.append("\tleft join fetch t.statusTelefone ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand (t.exibe is null or t.exibe = 'SIM') ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public Telefone pesquisarTelefoneSemStatus(Long atendimento, Long telefone) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from Telefone t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand t.id = :telefone ");
        query.append("\tand s.id is null ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", atendimento);
        parametros.put("telefone", telefone);

        return (Telefone) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public Telefone pesquisarTelefone(Long atendimento, Long telefone) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from Telefone t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand t.id = :telefone ");


        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", atendimento);
        parametros.put("telefone", telefone);

        return (Telefone) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public Telefone pesquisarTelefonePorNumeroDdd(Long atendimento, String ddd, String numero) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from Telefone t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand cast(t.ddd AS string) = :ddd and t.numero = :numero ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", atendimento);
        parametros.put("ddd", ddd.trim());
        parametros.put("numero", numero.trim());


        return (Telefone) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public void alterarTelefone(Telefone tel, Long idStatus, Long idUsr) {
        // TODO Auto-generated method stub

        String query = "update telefone set usuario_cadastro = :usuario, status_telefone = :status where id = :id ";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsr);
        parametros.put("status", idStatus);
        parametros.put("id", tel.getId());

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void alterarStatusTelefone(Long idAtendimento, String numeroTelefone, Long idStatus) {

        String query = "update telefone set status_telefone = :status where atendimento = :atendimento and cast(ddd as varchar)||numero = :numeroTelefone ";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("status", idStatus);
        parametros.put("atendimento", idAtendimento);
        parametros.put("numeroTelefone", numeroTelefone.startsWith("0") ? numeroTelefone.substring(1) : numeroTelefone);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);


    }

    public Integer pesquisarQuantidadeTelefonesPorCampanha(Long campanha) {

        StringBuilder query = new StringBuilder();

        query.append("select  ");
        query.append("\tmax(a.total) ");
        query.append("from ( ");
        query.append("\t\tselect h.atendimento, count(h.id) as total ");
        query.append("\t\tfrom telefone h ");
        query.append("\t\twhere h.atendimento in (select id from atendimento where campanha = :campanha) ");
        query.append("\t\tgroup by h.atendimento ) a");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        Number result = (Number) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        if (result == null)
            result = Integer.valueOf(0);

        return Integer.valueOf(result.intValue());

    }

    public List<Object[]> pesquisarTelefonesPorCampanha(Long campanha) {


        StringBuilder query = new StringBuilder();

        query.append("select * ");
        query.append("from (");
        query.append("    SELECT t.atendimento, t.ddd, t.numero, coalesce(s.descricao, '') as status ");
        query.append("    FROM telefone t ");
        query.append("        join public.atendimento a on t.atendimento = a.id ");
        query.append("        left join status_telefone s on t.status_telefone = s.id ");
        query.append("    where a.campanha = :campanha ) t ");

        query.append("order by t.atendimento, t.ddd, t.numero ");

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("campanha", campanha);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }

    public Telefone pesquisarTelefonePorId(Long idTelfone) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from Telefone t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.id = :telefone ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("telefone", idTelfone);


        return (Telefone) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Object[]> pesquisarTelefonesPorAtendimento(List<Long> idsAtendimentos) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct a.id, t.ddd, t.numero, st.descricao ");
        query.append("from public.telefone t ");
        query.append("\tjoin public.atendimento a on t.atendimento = a.id ");
        query.append("\tleft join status_telefone st on st.id = t.status_telefone ");
        query.append("where t.atendimento in (:listaAtendimentos) ");

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("listaAtendimentos", idsAtendimentos);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<Object[]> pesquisarTelefonesPorCpf(String cpf, Integer fist, Integer max) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct t.ddd, t.numero ");
        query.append(" from public.telefone t  ");
        query.append(" join public.atendimento a on t.atendimento = a.id   ");
        query.append(" where a.cpf  = :cpf GROUP BY t.ddd, t.numero order by t.ddd, t.numero   ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cpf", cpf);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, fist, max);
    }

    public void inserirTelefoneCliente(Long idCliente, Short ddd, String numero, Long idUsuario) {

        String sql = "INSERT INTO telefone " +
                "(ddd, numero, cliente, usuario_cadastro, usuario_alteracao, data_cadastro, data_alteracao) " +
                "VALUES (:ddd, :numero, :cliente, :usuarioCadastro, :usuarioAlteracao, :dataCadastro, :dataAlteracao)";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ddd", ddd); // mantém como Short
        parametros.put("numero", numero.replaceAll("\\D", ""));
        parametros.put("cliente", idCliente);
        parametros.put("usuarioCadastro", idUsuario);
        parametros.put("usuarioAlteracao", idUsuario);
        parametros.put("dataCadastro", new java.sql.Timestamp(System.currentTimeMillis()));
        parametros.put("dataAlteracao", new java.sql.Timestamp(System.currentTimeMillis()));

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, sql, parametros);
    }
}

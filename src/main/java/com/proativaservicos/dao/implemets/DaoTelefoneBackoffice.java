package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.TelefoneBackoffice;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class DaoTelefoneBackoffice extends GenericDao<TelefoneBackoffice> implements Serializable {


    private static final long serialVersionUID = 1L;

    public List<TelefoneBackoffice> pesquisarTelefonesPorAtendimento(Long id) {

        StringBuilder query = new StringBuilder();

        query.append("select t from TelefoneBackoffice t ");
        query.append("\tleft join fetch t.statusTelefone ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand (t.exibe is null or t.exibe = 'SIM') ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public TelefoneBackoffice pesquisarTelefoneSemStatus(Long atendimento, Long telefone) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from TelefoneBackoffice t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand t.id = :telefone ");
        query.append("\tand s.id is null ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", atendimento);
        parametros.put("telefone", telefone);

        return (TelefoneBackoffice) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public TelefoneBackoffice pesquisarTelefone(Long atendimento, Long telefone) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from TelefoneBackoffice t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.atendimento.id = :atendimento ");
        query.append("\tand t.id = :telefone ");


        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", atendimento);
        parametros.put("telefone", telefone);

        return (TelefoneBackoffice) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public void alterarTelefone(TelefoneBackoffice tel, Long idStatus, Long idUsr) {
        // TODO Auto-generated method stub

        String query = "update telefone_backoffice set usuario_alteracao = :usuario, status_telefone = :status , data_alteracao = now() where id = :id ";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsr);
        parametros.put("status", idStatus);
        parametros.put("id", tel.getId());

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void alterarStatusTelefone(Long idAtendimento, String numeroTelefone, Long idStatus) {

        String query = "update telefone_backoffice set status_telefone = :status where atendimento = :atendimento and cast(ddd as varchar)||numero = :numeroTelefone ";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("status", idStatus);
        parametros.put("atendimento", idAtendimento);
        parametros.put("numeroTelefone", numeroTelefone.startsWith("0") ? numeroTelefone.substring(1) : numeroTelefone);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);


    }

    public TelefoneBackoffice pesquisarTelefonePorId(Long idTelfone) {

        StringBuilder query = new StringBuilder();

        query.append("select t ");
        query.append("from TelefoneBackoffice t ");
        query.append("\tleft join fetch t.statusTelefone s ");
        query.append("where t.id = :telefone ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("telefone", idTelfone);


        return (TelefoneBackoffice) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Object[]> pesquisarTelefonesPorAtendimento(List<Long> idsAtendimentos) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct a.id, t.ddd, t.numero, st.descricao ");
        query.append("from public.telefone_backoffice t ");
        query.append("\tjoin public.atendimento_backoffice a on t.atendimento = a.id ");
        query.append("\tleft join status_telefone st on st.id = t.status_telefone ");
        query.append("where t.atendimento in (:listaAtendimentos) ");

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("listaAtendimentos", idsAtendimentos);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<Object[]> pesquisarTelefonesPorCpf(String cpf, Integer fist, Integer max) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct t.ddd, t.numero ");
        query.append(" from public.telefone_backoffice t  ");
        query.append(" join public.atendimento_backoffice a on t.atendimento = a.id   ");
        query.append(" where a.cpf  = :cpf GROUP BY t.ddd, t.numero order by t.ddd, t.numero   ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cpf", cpf);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, fist, max);
    }

}

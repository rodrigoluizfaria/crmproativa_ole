package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Cliente;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class DaoCliente extends GenericDao<Cliente> {


    public Cliente pesquisarClienteComAtendimentosSacPorCpf(String cpf) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct c ");
        query.append("from Cliente c ");
        query.append(" left join fetch c.listTelefones t ");
        query.append(" where c.cpf = :cpf ");

        parametros.put("cpf", StringUtils.leftPad(cpf.trim(), 11, "0"));

        return (Cliente) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public Cliente pesquisarClienteComAtendimentosSacPorId(Long idCliente) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct c ");
        query.append("from Cliente c ");
        query.append(" left join fetch c.listTelefones t ");
        query.append(" where c.id = :id ");

        parametros.put("id", idCliente);

        return (Cliente) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    @Transactional
    public void atualizarNomeCliente(String nome, String nomeMae, String nomePai, Date dataNascimento, Long idCliente) {

        StringBuilder query = new StringBuilder("update cliente set ");
        Map<String, Object> parametros = new HashMap<>();

        query.append("nome = :nome, nome_mae = :nomeMae, nome_pai = :nomePai");
        parametros.put("nome", nome);
        parametros.put("nomeMae", nomeMae);
        parametros.put("nomePai", nomePai);

        if (dataNascimento != null) {
            query.append(", data_nascimento = :dataNascimento");
            parametros.put("dataNascimento", dataNascimento);
        }

        query.append(" where id = :idCliente");
        parametros.put("idCliente", idCliente);

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }

}

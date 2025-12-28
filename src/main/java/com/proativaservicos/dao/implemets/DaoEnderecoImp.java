package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Endereco;
import com.proativaservicos.model.GenericEndereco;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoEnderecoImp extends GenericDao<Endereco> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<? extends GenericEndereco> pesquisarEnderecoPorAtendimento(Long id) {

        StringBuilder builder = new StringBuilder();

        builder.append("select e from Endereco e");

        builder.append("\t where e.atendimento.id = :atendimento");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("atendimento", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);
    }

    public Integer pesquisarQuantidadeEnderecosPorCampanha(Long campanha) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<? extends GenericEndereco> pesquisarEnderecoPorCpf(String cpf) {

        StringBuilder builder = new StringBuilder();

        builder.append("select e from Endereco e");

        builder.append("\t where e.atendimento.cpf = :cpf");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("cpf", cpf);

        return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros, Integer.valueOf(0), Integer.valueOf(5));
    }

    public List<Endereco> pesquisarEnderecoPorCliente(Long idCliente) {

        StringBuilder builder = new StringBuilder();

        builder.append("select e from Endereco e");

        builder.append("\t where e.cliente.id = :idCliente");

        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("idCliente", idCliente);

        return searchEntidades(DaoEnum.HQL_QUERRY, builder.toString(), parametros);
    }
}

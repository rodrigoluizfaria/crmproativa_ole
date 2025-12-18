package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.ExtratorImportacao;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.StatusImportacaoExtratorEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class DaoExtracaoImportacaoImp extends GenericDao<ExtratorImportacao> implements Serializable {


    private static final long serialVersionUID = 1L;

    public List<ExtratorImportacao> pesquisarPorEmpresa(Long id, StatusImportacaoExtratorEnum status) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select e ");
        query.append("from ExtratorImportacao e ");
        query.append("\tjoin fetch e.empresa em ");
        query.append("where 1=1 ");

        if (status != null) {

            query.append(" and e.statusImportacao = :status");
            parametros.put("status", status);

        }

        query.append("\t order by e.dataInicio DESC ");
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<ExtratorImportacao> pesquisarPorEmpresaIncompleta(long id) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select e ");
        query.append("from ExtratorImportacao e ");
        query.append("\tjoin fetch e.empresa em ");
        query.append("where (em.id = :empresa or em.matriz.id = :empresa) ");
        query.append(" and e.statusImportacao <> :status");

        parametros.put("status", StatusImportacaoExtratorEnum.FINALIZADA);
        parametros.put("empresa", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public void resetarExtrator() {

        StringBuilder query = new StringBuilder();

        query.append("update extrator_importacao set status_importacao = 'NAO_FINALIZADA' WHERE status_importacao  not like'FINA%' and status_importacao <> 'NAO_FINALIZADA'");


        executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), null);

    }


}

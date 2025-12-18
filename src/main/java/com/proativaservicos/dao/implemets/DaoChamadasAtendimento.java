package com.proativaservicos.dao.implemets;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.ChamadasAtendimento;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;


public class DaoChamadasAtendimento extends GenericDao<ChamadasAtendimento> {

    public List<?> pesquisarQuantidadePorStatusDiscador(Long campanha, TipoIntegracaoEnum tipoIntegracaoEnum) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("SELECT COUNT(ca.id ) AS total_ca,  ");

        if (tipoIntegracaoEnum.name().equals(TipoIntegracaoEnum.TRES_CPLUS.name()))
            query.append(" ca.cod_status_telefone ");
        else
            query.append(" ca.status_telefone_vonix ");

        query.append(" FROM chamadas_atendimento AS ca ");
        query.append(" JOIN atendimento AS a ON a.id = ca.atendimento ");
        query.append(" JOIN campanha AS c ON c.id = A.campanha  ");
        query.append("\twhere c.id = :idCampanha ");

        if (tipoIntegracaoEnum.name().equals(TipoIntegracaoEnum.TRES_CPLUS.name()))
            query.append("\tGROUP BY c.id ,ca.cod_status_telefone ");
        else
            query.append("\tGROUP BY c.id ,ca.status_telefone_vonix ");

        parametros.put("idCampanha", campanha);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarQuantidadePorStatus(Long campanha, String status, String condicao, TipoCampanhaEnum tipoCampanhaEnum) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("	SELECT COUNT	( ca.id ) AS total_chamadas,  ");
        query.append(" to_char( data_discagem, 'HH24' ) AS hora ");
        query.append(" FROM chamadas_atendimento AS ca ");
        query.append(" JOIN atendimento AS a ON a.id = ca.atendimento ");
        query.append(" JOIN campanha AS c ON c.id = A.campanha  ");
        query.append("\twhere c.id = :idCampanha ");

        if(tipoCampanhaEnum!=null && tipoCampanhaEnum.equals(TipoCampanhaEnum.PREDITIVA_3C)) {

            query.append("\tand ca.cod_status_telefone ").append(condicao).append(" :status ");
            parametros.put("status", Integer.parseInt(status));

        }else{

            query.append("\tand ca.status_telefone_vonix ").append(condicao).append(" :status ");
            query.append("\tand  ca.status_telefone_vonix <> '_0' ");
            parametros.put("status", status);

        }

        query.append("\tGROUP BY to_char( data_discagem, 'HH24')  ");
        parametros.put("idCampanha", campanha);


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }

    public Object[] pesquisarSpin(Long campanha) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("	SELECT ca.cam, ");
        query.append("	cast( cast( ca.total_ca as Numeric(10,2 ) )/cast(C.total  as Numeric(10,2)) as Numeric(10,2)) as spin  ");
        query.append("	,ca.total_ca, c.total,c.valor_liberado,c.total_ad,c.valor_minimo,c.valor_maximo,c.tiket ");

        query.append(" FROM ");

        query.append(" ( SELECT COUNT(*) AS total, campanha, SUM(valor_liberado) as valor_liberado,COUNT(adesao) as total_ad   ");

        query.append("	,MIN ( valor_liberado ) AS valor_minimo ");
        query.append("	,MAX ( valor_liberado ) AS valor_maximo ");
        query.append("	, CAST( ( SUM ( valor_liberado ) / COUNT ( valor_liberado ) ) AS NUMERIC ( 7, 2 ) ) AS tiket ");
        query.append("	FROM atendimento WHERE campanha = :campanha GROUP BY campanha ) ");

        query.append(" C JOIN ( ");
        query.append(" SELECT COUNT	(ca.id) as total_ca,c.descricao AS cam,c.id AS id_cam  ");
        query.append(" FROM  ");
        query.append(" chamadas_atendimento as ca JOIN atendimento as a on a.id = ca.atendimento JOIN campanha as c ON c.id = a.campanha  ");
        query.append(" WHERE ");
        query.append(" c.id = :campanha  ");
        query.append("\tGROUP BY c.descricao,c.id  ");
        query.append("\t ) ca ON ( c.campanha = ca.id_cam ) ");
        parametros.put("campanha", campanha);


        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Object[] pesquisarCpcPorCampanha(Long campanha) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("	SELECT coalesce(cast( (ca.tota_contato_cliente*100)/ NULLIF(ca.sem_contato_cliente, 0) AS NUMERIC ( 7, 2 )) , 0) as CPC,   ");
        query.append(" coalesce(cast( (ca.duracao)/ NULLIF(ca.sem_contato_cliente,0)  as NUMERIC ( 7, 2 )) , 0) as tma	   ");
        query.append(" FROM ( ");
        query.append(" select  cast( sum(case when st.parametro = 'CONTATO_CLIENTE' then 1 else 0 end) as NUMERIC  )as tota_contato_cliente, ");
        query.append(" cast(sum(case when ca.status_telefone_vonix = '_16' then 1 else 0 end) as NUMERIC  ) as sem_contato_cliente,  ");
        query.append(" SUM(ca.duracao) as duracao  ");
        query.append(" from chamadas_atendimento ca   ");
        query.append("\tJOIN atendimento a on a.id = ca.atendimento ");
        query.append("\tJOIN status_atendimento sa on sa.id = a.status ");
        query.append("\tjoin telefone t on t.atendimento = a.id  ");
        query.append("\tjoin status_telefone st on t.status_telefone = st.id WHERE a.campanha = :idCampanha ) ca  ");
        parametros.put("idCampanha", campanha);


        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public Object[] pesquisarCpcPorCampanha3c(Long campanha) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("	SELECT coalesce(cast( (ca.tota_contato_cliente*100)/ NULLIF(ca.sem_contato_cliente, 0) AS NUMERIC ( 7, 2 )) , 0) as CPC,   ");
        query.append(" coalesce(cast( (ca.duracao)/ NULLIF(ca.sem_contato_cliente,0)  as NUMERIC ( 7, 2 )) , 0) as tma	   ");
        query.append(" FROM ( ");
        query.append(" select  cast( sum(case when st.parametro = 'CONTATO_CLIENTE' then 1 else 0 end) as NUMERIC  )as tota_contato_cliente, ");
        query.append(" cast(sum(case when ca.cod_status_telefone = '7' or ca.cod_status_telefone = '2' or ca.cod_status_telefone = '3' then 1 else 0 end) as NUMERIC  ) as sem_contato_cliente,  ");
        query.append(" SUM(ca.duracao) as duracao  ");
        query.append(" from chamadas_atendimento ca   ");
        query.append("\tJOIN atendimento a on a.id = ca.atendimento ");
        query.append("\tJOIN status_atendimento sa on sa.id = a.status ");
        query.append("\tjoin telefone t on t.atendimento = a.id  ");
        query.append("\tjoin status_telefone st on t.status_telefone = st.id WHERE a.campanha = :idCampanha ) ca  ");
        parametros.put("idCampanha", campanha);


        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

}

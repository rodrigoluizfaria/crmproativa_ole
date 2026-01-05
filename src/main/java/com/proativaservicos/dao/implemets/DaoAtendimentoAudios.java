package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.AtendimentoAudios;
import com.proativaservicos.model.Pabx;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;


@Named
public class DaoAtendimentoAudios extends GenericDao<AtendimentoAudios> implements Serializable {


    private static final long serialVersionUID = 1L;

    @Transactional
    public void salvarAtendimentoAudio(String callId, Long idAtendimento, Pabx pabx, String ramal, Date date, String fila, String destino) {


        StringBuilder query = new StringBuilder("");
        query.append("INSERT into atendimento_audios (descricao,atendimento,tipo,ramal,data,fila,destino,pabx) ");
        query.append(" VALUES (:descricao, :atendimento,:tipo,:ramal,:data,:fila,:destino,:pabx) ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("descricao", callId);
        parametros.put("atendimento", idAtendimento);
        parametros.put("tipo", pabx.getTipo().name());
        parametros.put("pabx", pabx.getId());
        parametros.put("ramal", ramal);
        parametros.put("data", date);
        parametros.put("fila", fila);
        parametros.put("destino", destino);

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);


    }

    @Transactional
    public void salvarAtendimentoAudio(String callId, String audioGravacao, Long idAtendimento, Pabx pabx, String ramal, Date date, String fila, String destino) {


        String query = "INSERT into atendimento_audios (descricao,atendimento,tipo,ramal,data,fila,destino,pabx,call_id) " +
                " VALUES (:descricao, :atendimento,:tipo,:ramal,:data,:fila,:destino,:pabx,:call_id) ";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("descricao", audioGravacao);
        parametros.put("atendimento", idAtendimento);
        parametros.put("tipo", pabx.getTipo().name());
        parametros.put("pabx", pabx.getId());
        parametros.put("ramal", ramal);
        parametros.put("data", date);
        parametros.put("fila", fila);
        parametros.put("destino", destino);
        parametros.put("call_id", callId);

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query, parametros);


    }


    @SuppressWarnings("unchecked")
    public List<AtendimentoAudios> pesquisarAtendimentoAudios(Long idAtendimento) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select a from AtendimentoAudios a ");

        query.append("JOIN FETCH a.pabx p ");

        query.append("where a.atendimento.id = :atendimento ");

        parametros.put("atendimento", idAtendimento);

        query.append("order by a.data desc");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);


    }

    public List<AtendimentoAudios> pesquisarAtendimentoAudiosPorProtocolo(String protocolo) {

        if (StringUtils.isBlank(protocolo)) {
            return Collections.emptyList();
        }

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select a from AtendimentoAudios a ");
        query.append("JOIN FETCH a.pabx pabx ");
        query.append("JOIN a.atendimento atend ");
        query.append("where atend.protocolo = :protocolo ");
        query.append("order by a.data desc");

        parametros.put("protocolo", protocolo);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


}

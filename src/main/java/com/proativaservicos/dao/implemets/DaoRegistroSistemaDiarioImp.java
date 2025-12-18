package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.RegistroSistemaDiario;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class DaoRegistroSistemaDiarioImp extends GenericDao<RegistroSistemaDiario> implements Serializable {

    private static final long serialVersionUID = 1L;

    public String deletarReristrosDiarios() {

        StringBuilder query = new StringBuilder("INSERT into registro_sistema (data_cadastro,evento,ip_requisicao,tipo,tipo_pausa,usuario) SELECT data_cadastro,evento,ip_requisicao,tipo,tipo_pausa,usuario FROM registro_sistema_diario;");

        query.append("\tDELETE from registro_sistema_diario;");
        return query.toString();
    }

    public List<?> pesquisarEventosOperacional(Long usuario, Long idEquipe, List<TipoEventoEnum> tipos) {

        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder query = new StringBuilder("SELECT DISTINCT u.id , u.nome, rs.tipo,rs.data_cadastro,rs.evento,	e.nome as equipe,p.ramal_aux,u.sexo,u.foto,u.login ");
        query.append(" \tFROM ");
        query.append(" \tregistro_sistema_diario rs ");
        query.append(" \tJOIN usuario u ON u.ID = rs.usuario ");
        query.append(" \tLEFT join equipe_supervisor es on es.equipe  = u.equipe ");
        query.append(" \tjoin equipe e on e.id = u.equipe ");
        query.append(" \tJOIN usuario_logado ul on ul.usuario = rs.usuario ");
        query.append(" \tLEFT JOIN ponto_atendimento p on p.id = u.ponto_atendimento ");
        query.append(" \tWHERE ");
        query.append(" \trs.tipo IN    " + sqlFormatedListObect(tipos));
        query.append(" \t and u.perfil = 'OPERADOR' ");

        if (usuario != null) {

            query.append("\t and es.supervisor = :supervisor ");
            parametros.put("supervisor", usuario);
        }

        if (idEquipe != null) {

            query.append(" \tand es.equipe = :equipe ");
            parametros.put("equipe", idEquipe);

        }


        query.append(" ORDER BY ");
        query.append(" rs.data_cadastro DESC ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Object pesquisarUltimoEvento(Long idUsuario, TipoEventoEnum evento) {

        Map<String, Object> parametros = new HashMap<String, Object>();

        StringBuilder query = new StringBuilder("SELECT tipo from registro_sistema_diario WHERE usuario = :idUsuario ");

        if (evento != null) {

            query.append("\tand tipo = :tipo ");
            parametros.put("tipo", evento.name());
        }

        query.append("\t ORDER BY data_cadastro desc LIMIT 1 ");
        parametros.put("idUsuario", idUsuario);

        return searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

}

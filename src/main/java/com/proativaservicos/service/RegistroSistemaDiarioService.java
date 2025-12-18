package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoRegistroSistemaDiarioImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.RegistroSistemaDiario;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class RegistroSistemaDiarioService extends GenericProService<RegistroSistemaDiario> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoRegistroSistemaDiarioImp dao;

    @Override
    public GenericDao<RegistroSistemaDiario> getDAO() {

        return (GenericDao<RegistroSistemaDiario>) this.dao;
    }

    public Object pesquisarUltimoEvento(Long idUsuario, TipoEventoEnum evento) {
        return this.dao.pesquisarUltimoEvento(idUsuario, evento);
    }

    public List<?> pesquisarEventosOperacional(Long usuario, Long idEquipe, List<TipoEventoEnum> tiposEventos) {

        return this.dao.pesquisarEventosOperacional(usuario, idEquipe, tiposEventos);
    }


}

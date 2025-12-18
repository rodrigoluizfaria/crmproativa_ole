package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoControleUsuarioBloqueadoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ControleUsuarioBloqueado;
import com.proativaservicos.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ControleUsuarioBloqueadoService extends GenericProService<ControleUsuarioBloqueado> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoControleUsuarioBloqueadoImp dao;

    public ControleUsuarioBloqueado findUsuarioBloqueado(Usuario id) {

        return dao.findUsuarioBloqueado(id);

    }

    @Override
    public GenericDao<ControleUsuarioBloqueado> getDAO() {

        return (GenericDao<ControleUsuarioBloqueado>) this.dao;
    }

    public List<?> pesquisarUsuariosBloqueados(Long idUsuario, Long idSupervisor, Long idEmpresa) {
        return this.dao.pesquisarUsuariosBloqueados(idUsuario, idSupervisor, idEmpresa);
    }

    public List<?> pesquisarUsuariosBloqueados(Long idUsuario, Long idEmpresa) {
        return pesquisarUsuariosBloqueados(idUsuario, null, idEmpresa);
    }

}

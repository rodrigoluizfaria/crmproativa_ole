package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoControlePausaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ControlePausa;
import com.proativaservicos.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Stateless
public class ControlePausaService extends GenericProService<ControlePausa> {

    @Inject
    private DaoControlePausaImp dao;

    @Override
    public GenericDao<ControlePausa> getDAO() {

        return (GenericDao<ControlePausa>) this.dao;
    }

    public ControlePausa pesquisarControlePausaPorUsuario(Usuario usuario, Date date) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarControlePausaPorUsuario(usuario, date);
    }

    public Integer pesquisarQuantidadeUsuarioEmPausa(Long pausa, Long empresa) {
        // TODO Auto-generated method stub
        return dao.pesquisarQuantidadeUsuarioEmPausa(pausa, empresa);
    }

    public List<?> pesquisarControlePausa(Long idEquipe, Date dataCadastro, Usuario usuario, Long idEmpresa) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarControlePausa(idEquipe, dataCadastro, usuario, idEmpresa);
    }

    public ControlePausa pesquisarControlePausa(Long idControlePausa) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarControlePausa(idControlePausa);
    }

    public List<?> pesquisarMonitorPausa(Long idUsuario, Long idEquipe) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarMonitorPausa(idUsuario, idEquipe);
    }


}

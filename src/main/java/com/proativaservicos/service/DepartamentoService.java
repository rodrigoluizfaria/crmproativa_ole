package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoDepartamento;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Departamento;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;


@Stateless
public class DepartamentoService extends GenericProService<Departamento> {

    @Inject
    private DaoDepartamento dao;

    @Override
    public GenericDao<Departamento> getDAO() {

        return this.dao;
    }


    public Departamento pesquisarDepartamentoPorId(Long idDepartamento) {
        return this.dao.pesquisarDepartamentoPorId(idDepartamento);
    }

    public List<Departamento> pesquisarDepartamentos(String descricao, TipoAcessoEnum acessoEnum) {
        return this.dao.pesquisarDepartamentos(descricao, acessoEnum);
    }

    public List<Departamento> listarDepartamentosAtivos(TipoAcessoEnum acessoEnum) {

        return this.dao.listarDepartamentos(acessoEnum);
    }

    public List<Departamento> pesquisarDepartamentosAssociadosPorUsuario(Long idUsuario) {
        return this.dao.pesquisarDepartamentosAssociadosPorUsuario(idUsuario);
    }
}

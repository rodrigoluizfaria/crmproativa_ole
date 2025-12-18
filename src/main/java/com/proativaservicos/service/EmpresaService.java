package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoEmpresaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.PeriodoEnum;
import com.proativaservicos.util.constantes.SituacaoEnum;
import com.proativaservicos.util.constantes.TipoDadosEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class EmpresaService extends GenericProService<Empresa> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoEmpresaImp dao;

    public List<Empresa> pesquisarCriteria() throws ProativaException {

        return dao.pesquisarCriteria(new Empresa());

    }


    public List<Empresa> persquisarCriteria(Empresa empresa) {

        try {

            return dao.pesquisarCriteria(empresa);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }


    public Empresa pesquisarEmpresa(String cnpj) {
        return this.dao.pesquisarEmpresa(cnpj);
    }


    public List<Empresa> pesquisarEmpresasFiliais(Long empresa) {
        return this.dao.pesquisarEmpresasFiliais(empresa);
    }

    public List<Empresa> pesquisarEmpresas(Long empresa) {
        return this.dao.pesquisarEmpresas(empresa);
    }


    public Empresa pesquisarEmpresa(Long codigoEmpresa) {
        return this.dao.pesquisarEmpresa(codigoEmpresa);
    }


    public Empresa pesquisarMatriz(Long empresa) {
        return this.dao.pesquisarMatriz(empresa);
    }


    public Empresa pesquisarMatriz(String cnpj) {
        return this.dao.pesquisarMatriz(cnpj);
    }


    public List<Empresa> pesquisarEmpresas(Empresa empresa) {
        return this.dao.pesquisarEmpresas(empresa);
    }


    @Override
    public GenericDao<Empresa> getDAO() {

        return (GenericDao<Empresa>) this.dao;
    }


    public Empresa pesquisaNomeEmpresa(String nome) {

        return dao.pesquisaNome(nome);


    }


    public List<Object[]> pesquisarProjecaoPorEmpresa(Usuario usuario, SituacaoEnum situacaoEnum, PeriodoEnum periodoEnum,
                                                      TipoDadosEnum tipoDadosEnum, Date inicio, Date fim) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarProjecaoPorEmpresa(usuario, situacaoEnum, periodoEnum, tipoDadosEnum, inicio, fim);
    }


}

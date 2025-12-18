package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoExpedienteImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Expediente;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;


@Stateless
public class ExpedienteService extends GenericProService<Expediente> {

    @Inject
    private DaoExpedienteImp dao;

    public List<Expediente> pesquisarExpedientes(Long empresa, Expediente expediente) {


        return this.dao.pesquisarExpedientes(empresa, expediente);
    }


    public List<Expediente> pesquisarExpedientesPorEmpresa(Long empresa, TipoAcessoEnum acesso) {
        return this.dao.pesquisarExpedientesPorEmpresa(empresa, acesso);
    }

    public List<Expediente> pesquisarExpedientes(Long empresa, TipoAcessoEnum acesso) {
        return this.dao.pesquisarExpedientes(empresa, acesso);
    }

    public Expediente pesquisarExpediente(Long codigo) {

        Expediente pesquisarExpediente = this.dao.pesquisarExpediente(codigo);
        pesquisarExpediente.getListDiasDaSemana().size();
        return pesquisarExpediente;
    }

    public GenericDao<Expediente> getDAO() {
        return (GenericDao<Expediente>) this.dao;
    }


}

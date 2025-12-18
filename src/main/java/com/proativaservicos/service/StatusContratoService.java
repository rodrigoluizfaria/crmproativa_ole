package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoStatusContratoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.StatusContrato;
import com.proativaservicos.util.constantes.AcaoStatusContratoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoStatusContratoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;


@Stateless
public class StatusContratoService extends GenericProService<StatusContrato> {

    @Inject
    private DaoStatusContratoImp dao;


    @Override
    public GenericDao<StatusContrato> getDAO() {

        return (GenericDao<StatusContrato>) this.dao;
    }


    public List<StatusContrato> pesquisarStatusContratoPorEmpresa(Long idEmpresa, TipoStatusContratoEnum statusContrato,
                                                                  AcaoStatusContratoEnum acao, TipoAcessoEnum acesso) {

        return this.dao.pesquisarStatusContratoPorEmpresa(idEmpresa, statusContrato, acao, acesso);
    }


    public StatusContrato pesquisarStatusContratoPorAcao(AcaoStatusContratoEnum acao, TipoStatusContratoEnum tipo,
                                                         TipoAcessoEnum acesso, Long idEmpresa) {

        return this.dao.pesquisarStatusContratoPorAcao(acao, tipo, acesso, idEmpresa);
    }


    public StatusContrato pesquisarStatusContratoPorAcaoIciar(TipoStatusContratoEnum historicoStatus, Long idEmpresa) {

        return this.dao.pesquisarStatusContratoPorAcaoIniciar(historicoStatus, idEmpresa);


    }


}

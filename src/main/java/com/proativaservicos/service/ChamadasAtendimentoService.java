package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoChamadasAtendimento;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ChamadasAtendimento;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import com.proativaservicos.util.constantes.TipoDiscagemEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ChamadasAtendimentoService extends GenericProService<ChamadasAtendimento> {

    @Inject
    private DaoChamadasAtendimento dao;

    @Override
    public GenericDao<ChamadasAtendimento> getDAO() {

        return (GenericDao<ChamadasAtendimento>) this.dao;
    }

    public List<?> pesquisarQuantidadePorStatusDiscador(Long campanha, TipoIntegracaoEnum discador) {

        return this.dao.pesquisarQuantidadePorStatusDiscador(campanha, discador);
    }

    public List<?> pesquisarQuantidadePorStatusEqual(Long campanha, String status, TipoCampanhaEnum discador) {

        return this.dao.pesquisarQuantidadePorStatus(campanha, status, " = ",discador);
    }

    public List<?> pesquisarQuantidadePorStatusNotEqual(Long campanha, String status, TipoCampanhaEnum discador) {

        return this.dao.pesquisarQuantidadePorStatus(campanha, status, " <> ",discador);
    }

    public Object[] pesquisarSpin(Long campanha) {

        return this.dao.pesquisarSpin(campanha);
    }

    public Object[] pesquisarCpcPorCampanha(Long campanha) {

        return this.dao.pesquisarCpcPorCampanha(campanha);
    }

    public Object[] pesquisarCpcPorCampanha3c(Long campanha) {

        return this.dao.pesquisarCpcPorCampanha3c(campanha);
    }


}

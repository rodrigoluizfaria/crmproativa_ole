package com.proativaservicos.service;

import java.io.Serializable;
import java.util.List;


import com.proativaservicos.dao.implemets.DaoMotivo;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Motivo;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

@Model
public class MotivoService extends GenericProService<Motivo> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoMotivo dao;

    @Inject
    private SubMotivoService subMotivoService;


    @Override
    public GenericDao<Motivo> getDAO() {

        return (GenericDao<Motivo>) this.dao;
    }

    public List<Motivo> pesquisarMotivosPorEmpresa(Long idEmpresa) {

        return this.dao.pesquisarMotivosPorEmpresa(idEmpresa);
    }

    public Motivo pesquisarMovivoPorDescricaoStatus(Long idEmpresa, String desc, Long idStatus) {

        return this.dao.pesquisarMovivoPorDescricaoStatus(idEmpresa, desc, idStatus);
    }


    public List<Motivo> pesquisarMotivos(String nomeMotivo, TipoAcessoEnum tipoAcessoEnum) {
        return this.dao.pesquisarMotivos(nomeMotivo, tipoAcessoEnum);
    }

    public Motivo pesquisarMotivoPorId(Long idMotivo) {
        return this.dao.pesquisarMotivoPorId(idMotivo);
    }

    public List<Motivo> pesquisarMotivosPorCamapanha(Long idCampanha) {

        return pesquisarMotivosPorCamapanha(idCampanha, false);

    }

    public List<Motivo> pesquisarMotivosPorCamapanha(Long idCampanha, boolean carregarSubMotivos) {

        List<Motivo> motivos = this.dao.pesquisarMotivosPorCamapanha(idCampanha);

        if (CollectionUtils.isNotEmpty(motivos) && carregarSubMotivos) {

            for (Motivo motivo : motivos) {
                motivo.setListSubMotivos(this.subMotivoService.pesquisarSubMotivosPorMotivo(motivo.getId(), TipoAcessoEnum.ATIVO));
            }

        }

        return motivos;

    }
}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoGrupoFaqPergunta;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.GrupoFaqPergunta;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class GrupoFaqPerguntaService extends GenericProService<GrupoFaqPergunta> {

    @Inject
    private DaoGrupoFaqPergunta dao;

    @Inject
    private FaqPerguntaService servicePergunta;

    @Override
    public GenericDao<GrupoFaqPergunta> getDAO() {

        return (GenericDao<GrupoFaqPergunta>) this.dao;
    }


    private List<GrupoFaqPergunta> retornarListaComPergunta(List<GrupoFaqPergunta> list) {

        List<GrupoFaqPergunta> listFinal = new ArrayList<GrupoFaqPergunta>();

        if (CollectionUtils.isNotEmpty(list)) {

            Long id = null;

            for (GrupoFaqPergunta grupoFaqPergunta : list) {

                if (id == null || !grupoFaqPergunta.getId().equals(id)) {

                    grupoFaqPergunta.setListFaqPergunta(this.servicePergunta.pesquisarPerguntasPorGrupo(grupoFaqPergunta.getId()));
                    listFinal.add(grupoFaqPergunta);
                    id = grupoFaqPergunta.getId();


                }

            }


        }
        if (CollectionUtils.isNotEmpty(listFinal)) {

            return listFinal;
        }

        return list;
    }

    public GrupoFaqPergunta pesquisarGrupo(Long id, boolean pesquisarEquipe) {

        GrupoFaqPergunta grupo = this.dao.pesquisarGrupo(id);

        if (grupo != null && grupo.getId() != null) {

            grupo.setListFaqPergunta(this.servicePergunta.pesquisarPerguntasPorGrupo(grupo.getId()));

        }

        return grupo;
    }


    public List<?> pesquisarGruposPerguntasPorEquipes(Long equipe) {
        return this.dao.pesquisarGruposPerguntasPorEquipes(equipe);

    }

    public List<GrupoFaqPergunta> pesquisarGrupos(String grupoDescricao, Long idGrupo, Long idEmpresa, TipoAcessoEnum ativo, boolean retornarComPerguntas) {

        if (retornarComPerguntas)
            return retornarListaComPergunta(this.dao.pesquisarGrupos(grupoDescricao, idGrupo, idEmpresa, ativo));
        else
            return this.dao.pesquisarGrupos(grupoDescricao, idGrupo, idEmpresa, ativo);

    }


}

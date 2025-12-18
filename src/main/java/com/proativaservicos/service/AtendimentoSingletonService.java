package com.proativaservicos.service;

import com.proativaservicos.model.Campanha;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import org.apache.commons.collections4.map.HashedMap;

import java.io.Serializable;
import java.util.*;

@Singleton
@Startup
public class AtendimentoSingletonService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private CampanhaService serviceCampanha;

    Map<Long, List<Long>> mapCampanhaListAtendimento;

    public Long pesquisarProximoAtendimento(Campanha campanhaPesquisa) {
        // TODO Auto-generated method stub

        if (campanhaPesquisa == null) {
            return null;
        }


        if (this.mapCampanhaListAtendimento == null) {

            this.mapCampanhaListAtendimento = new HashMap<>();

        }


        List<Long> listIdAtendimentos = this.mapCampanhaListAtendimento.get(campanhaPesquisa.getId());

        //MAP VAZIA INSERINDO NOVA LISTAS
        if (listIdAtendimentos == null || listIdAtendimentos.isEmpty()) {

            inicializarListCampanhaAtendimentos(campanhaPesquisa);

            listIdAtendimentos = this.mapCampanhaListAtendimento.get(campanhaPesquisa.getId());

            if (listIdAtendimentos == null || listIdAtendimentos.isEmpty()) {
                return null;
            }
        }

        Long atendimento = listIdAtendimentos.remove(0);

        return (atendimento == null) ? null : Long.valueOf(atendimento.longValue());


    }

    public void inicializarListCampanhaAtendimentos(Campanha campanha) {

        if (this.mapCampanhaListAtendimento == null)
            this.mapCampanhaListAtendimento = new HashedMap<Long, List<Long>>();

        if (campanha != null) {

            List<Long> listAtendimentos = this.mapCampanhaListAtendimento.get(campanha.getId());

            if (listAtendimentos == null || listAtendimentos.isEmpty()) {

                this.mapCampanhaListAtendimento.put(campanha.getId(), serviceAtendimento.pesquisarProximoAtendimentoLotePorCampanha(campanha));

            } else {

                Set<Long> listNaoRepetidos = new LinkedHashSet<Long>(listAtendimentos);
                listNaoRepetidos.addAll(this.serviceAtendimento.pesquisarProximoAtendimentoLotePorCampanha(campanha));
                this.mapCampanhaListAtendimento.put(campanha.getId(), new ArrayList<Long>(listNaoRepetidos));

            }

        } else {

            this.serviceAtendimento.resetarAtendimentosEnviados();

            List<Campanha> listCampanhas = this.serviceCampanha.pesquisarCampanhasAtivas();

            for (Campanha campanhaAtivo : listCampanhas) {

                this.mapCampanhaListAtendimento.put(campanhaAtivo.getId(), this.serviceAtendimento.pesquisarProximoAtendimentoLotePorCampanha(campanhaAtivo));
            }

        }

    }

    public void removerCampanha(Long campanha) {

        if (this.mapCampanhaListAtendimento != null && this.mapCampanhaListAtendimento.containsKey(campanha)) {
            this.mapCampanhaListAtendimento.remove(campanha);
            this.serviceAtendimento.resetarAtendimentosEnviados(campanha);
        }
    }

    @Asynchronous
    public void verificarQuantidadeAtendimento(Campanha campanha) {

        List<Long> listAtendimentos = this.mapCampanhaListAtendimento.get(campanha.getId());

        if (listAtendimentos != null && listAtendimentos.size() == 10)
            inicializarListCampanhaAtendimentos(campanha);
    }

    public Long buscarProximoAtendimento(Campanha campanha) {

        if (campanha == null) {
            return null;
        }

        if (this.mapCampanhaListAtendimento == null || this.mapCampanhaListAtendimento.isEmpty()) {
            inicializarListCampanhaAtendimentos();
        }

        List<Long> listaAtendimentos = this.mapCampanhaListAtendimento.get(campanha.getId());

        if (listaAtendimentos == null || listaAtendimentos.isEmpty()) {

            inicializarListCampanhaAtendimentos(campanha);

            listaAtendimentos = this.mapCampanhaListAtendimento.get(campanha.getId());

            if (listaAtendimentos == null || listaAtendimentos.isEmpty()) {

                return null;
            }
        }

        Long atendimento = listaAtendimentos.remove(0);

        return (atendimento == null) ? null : Long.valueOf(atendimento.longValue());
    }

    private void inicializarListCampanhaAtendimentos() {

        inicializarListCampanhaAtendimentos(null);

    }

}

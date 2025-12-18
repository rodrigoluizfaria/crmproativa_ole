package com.proativaservicos.service;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import org.apache.commons.collections4.map.HashedMap;

import java.io.Serializable;
import java.util.*;

@Singleton
@Startup
public class AtendimentoBackofficeSingletonService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoBackofficeService serviceAtendimento;


    private Map<Long, List<Long>> mapEquipeListAtendimento;

    public Long pesquisarProximoAtendimento(Long equipe) {

        if (equipe == null) {
            return null;
        }

        if (this.mapEquipeListAtendimento == null) {

            this.mapEquipeListAtendimento = new HashMap<>();

        }

        List<Long> listIdAtendimentos = this.mapEquipeListAtendimento.get(equipe);

        //MAP VAZIA INSERINDO NOVA LISTAS
        if (listIdAtendimentos == null || listIdAtendimentos.isEmpty()) {

            inicializarListEquipeAtendimentos(equipe);

            listIdAtendimentos = this.mapEquipeListAtendimento.get(equipe);

            if (listIdAtendimentos == null || listIdAtendimentos.isEmpty()) {

                return null;
            }

        }

        Long atendimento = listIdAtendimentos.remove(0);

        return (atendimento == null) ? null : Long.valueOf(atendimento.longValue());

    }

    public void inicializarListEquipeAtendimentos(Long equipe) {


        if (this.mapEquipeListAtendimento == null)
            this.mapEquipeListAtendimento = new HashedMap<Long, List<Long>>();

        if (equipe != null) {

            List<Long> listAtendimentos = this.mapEquipeListAtendimento.get(equipe);

            if (listAtendimentos == null || listAtendimentos.isEmpty()) {

                this.mapEquipeListAtendimento.put(equipe, serviceAtendimento.pesquisarAtendimentosPorEquipe(equipe, true));


            } else {

                Set<Long> listNaoRepetidos = new LinkedHashSet<Long>(listAtendimentos);

                listNaoRepetidos.addAll(this.serviceAtendimento.pesquisarAtendimentosPorEquipe(equipe, true));

                this.mapEquipeListAtendimento.put(equipe, new ArrayList<Long>(listNaoRepetidos));

            }


        } else {

            if (this.mapEquipeListAtendimento == null || this.mapEquipeListAtendimento.isEmpty())
                System.out.println("vazio");
            else
                System.out.println("Contem..... limpando cache: " + this.mapEquipeListAtendimento.size());

            this.serviceAtendimento.resetarAtendimentosEnviados();
            this.mapEquipeListAtendimento = null;

            //this.serviceAtendimento.resetarAtendimentosEnviados();

            /*
             * List<Campanha> listCampanhas =
             * this.serviceCampanha.pesquisarCampanhasAtivas();
             *
             * for (Campanha campanhaAtivo : listCampanhas) {
             *
             * this.mapCampanhaListAtendimento.put(campanhaAtivo.getId(),this.
             * serviceAtendimento.pesquisarProximoAtendimentoLotePorCampanha(campanhaAtivo))
             * ; }
             */

        }


    }

    @Asynchronous
    public void verificarQuantidadeAtendimento(Long equipe) {

        List<Long> listAtendimentos = this.mapEquipeListAtendimento.get(equipe);

        if (listAtendimentos != null && listAtendimentos.size() == 10)
            inicializarListEquipeAtendimentos(equipe);
    }

    public void verificarAtendimentos(List<Long> listIds) {

        if (this.mapEquipeListAtendimento != null) {

            this.mapEquipeListAtendimento.keySet().removeAll(listIds);

        }


    }

    @Asynchronous
    public void removerAtendimento(Long id) {

        if (this.mapEquipeListAtendimento != null) {

            for (Long key : this.mapEquipeListAtendimento.keySet()) {

                if (this.mapEquipeListAtendimento.get(key).removeIf(l -> l.longValue() == id.longValue())) {
                    System.out.println("Atendimento removido: " + id);
                    break;
                }

            }
        }


    }

    public void resetarAtendimentosEnviadosPorEquipe(Long idEquipe) {
        this.serviceAtendimento.resetarAtendimentosEnviadosPorEquipe(idEquipe);

    }


}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoAtendimentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Campanha;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Serviço de Atendimento Ativo
 *
 * @author rodrigo
 */

@Stateless
public class AtendimentoAtivoService extends GenericProService<Atendimento> implements Serializable {


    private static final long serialVersionUID = 1L;

    @Inject
    private DaoAtendimentoImp dao;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private AtendimentoSingletonService serviceAtendimentoSingleton;


    public Long pesquisarProximoAtendimento(Long idUsuario, Long idCampanha) {


        Long idAtendimento = null;

        if ((idAtendimento = this.dao.pesquisarProximoAtendimentoAgendamento(idUsuario)) != null) {
            this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);
            return idAtendimento;
        }

        if ((idAtendimento = this.dao.pesquisarProximoAtendimentoOcupado(idUsuario)) != null)
            return idAtendimento;

        Campanha campanhaPesquisa = null;

        if (idCampanha == null) {

            campanhaPesquisa = this.serviceCampanha.pesquisarCampanhaAtivoPorUsuario(idUsuario);

        } else {

            campanhaPesquisa = this.serviceCampanha.pesquisar(Campanha.class, idCampanha);

        }

        idAtendimento = this.serviceAtendimentoSingleton.pesquisarProximoAtendimento(campanhaPesquisa);

        if (idAtendimento == null) {
            return null;
        }

        if (this.dao.verificarJaFoiAtendido(idAtendimento)) {
            return pesquisarProximoAtendimento(idUsuario, idCampanha);

        }

        this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);
        this.serviceAtendimentoSingleton.verificarQuantidadeAtendimento(campanhaPesquisa);
        return idAtendimento;
    }

    public Long pesquisarAtendimentoAgendado(Long idUsuario) {

       Long idAtendimento = null;

        if ((idAtendimento = this.dao.pesquisarProximoAtendimentoAgendamento(idUsuario)) != null) {

            this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);
            return idAtendimento;
        }
        return idAtendimento;
    }

    @Override
    public GenericDao<Atendimento> getDAO() {

        return (GenericDao<Atendimento>) this.dao;
    }

    public void retrabalharAtendimentosAtivo(Long idCampanha, List<Long> listIds, boolean retrabalharFimFila) {


        this.dao.resetarAtendimentosEnviados(idCampanha);

        this.dao.retrabalharAtendimentosAtivo(idCampanha, listIds, retrabalharFimFila);


    }


}

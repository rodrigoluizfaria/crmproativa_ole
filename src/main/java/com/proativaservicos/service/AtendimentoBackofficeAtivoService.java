package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoAtendimentoBackoffice;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.AtendimentoBackoffice;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AtendimentoBackofficeAtivoService extends GenericProService<AtendimentoBackoffice> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoAtendimentoBackoffice dao;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private AtendimentoBackofficeSingletonService serviceSingleton;

    public Long pesquisarProximoAtendimento(Long idUsuario, Long idEquipe) {

        Long idAtendimento = null;

        if ((idAtendimento = this.dao.pesquisarProximoAtendimentoAgendamento(idUsuario)) != null) {
            this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);
            return idAtendimento;
        }

        if ((idAtendimento = this.dao.pesquisarProximoAtendimentoOcupado(idUsuario)) != null)
            return idAtendimento;


        Long equipe = this.serviceUsuario.pesquisarEquipeUsuario(idUsuario);

        idAtendimento = this.serviceSingleton.pesquisarProximoAtendimento(equipe);

        if (idAtendimento == null && equipe != null) {

            this.serviceSingleton.resetarAtendimentosEnviadosPorEquipe(equipe);
            idAtendimento = this.serviceSingleton.pesquisarProximoAtendimento(equipe);
        }


        if (idAtendimento == null) {

            return null;
        }

        if (this.dao.verificarJaFoiAtendido(idAtendimento)) {
            return pesquisarProximoAtendimento(idUsuario, idEquipe);

        }

        //this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);

        atualizarUsuarioEmAtendimento(idAtendimento, idUsuario);

        this.serviceSingleton.verificarQuantidadeAtendimento(equipe);

        return idAtendimento;
    }


    private void atualizarUsuarioEmAtendimento(Long idAtendimento, Long idUsuario) {

        String cpf = this.dao.pesquisarCpfPorId(idAtendimento);

        List<Long> listIds = new ArrayList<Long>();

        if (StringUtils.isNotBlank(cpf)) {

            listIds = this.dao.pesquisarIdsPorCpf(cpf);

        }

        if (CollectionUtils.isNotEmpty(listIds)) {

            this.dao.atualizarAtendimentoOcupado(listIds, idUsuario);
            listIds.remove(idAtendimento);
            this.serviceSingleton.verificarAtendimentos(listIds);

        } else {

            this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);

        }
    }

    private void validarUsuarioAtendimento(Long idAtendimento, Long idUsuario) {

        String cpf = this.dao.pesquisarCpfPorId(idAtendimento);

        List<Long> listIds = new ArrayList<Long>();
        List<AtendimentoBackoffice> listAtendimentos = new ArrayList<AtendimentoBackoffice>();

        if (StringUtils.isNotBlank(cpf)) {

            listAtendimentos = this.dao.pesquisarHqlCpfPorId(cpf);

        }

        if (CollectionUtils.isNotEmpty(listAtendimentos)) {

            listIds = listAtendimentos.stream().map(AtendimentoBackoffice::getId).collect(Collectors.toList());

            this.dao.atualizarAtendimentoOcupado(listIds, idUsuario);
            listIds.remove(idAtendimento);
            this.serviceSingleton.verificarAtendimentos(listIds);

        } else {

            this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsuario);

        }
    }


    @Override
    public GenericDao<AtendimentoBackoffice> getDAO() {

        return (GenericDao<AtendimentoBackoffice>) this.dao;

    }

    public void resetarAtendimentos() {
        this.serviceSingleton.inicializarListEquipeAtendimentos(null);
    }

    public void removerAtendimentoMap(Long idAtendimento) {
        this.serviceSingleton.removerAtendimento(idAtendimento);
    }


}

package com.proativaservicos.service.asynchronous;

import com.proativaservicos.model.*;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.AtendimentoTransactionalService;
import com.proativaservicos.util.ApiBancoMasterUtil;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

@Stateless
public class ConsultaWorkerMaster {

    @EJB
    private AtendimentoService serviceAtendimento;

    @EJB
    private AtendimentoTransactionalService atendimentoTransactionalService;

    @EJB
    private ApiBancoMasterUtil consultaApiMasterSaque;


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void executarConsulta(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa, Importacao importacao) {
        try {

            // Execução da consulta simulada (pode ser substituída pela chamada real à API)
            ConsultaAssincronaMasterSaqueExecutor.ConsultaMasterSaque tarefa = new ConsultaAssincronaMasterSaqueExecutor().new ConsultaMasterSaque(atendimento, campanha, usuario, empresa);

            tarefa.call();

            // Atualiza banco em transação independente
            atendimentoTransactionalService.atualizarAtendimento(atendimento, null);

        } catch (Exception e) {
            atendimento.setObservacao("Ocorreu um erro: " + e.getMessage());
            atendimentoTransactionalService.atualizarAtendimento(atendimento, null);
        }
    }
}

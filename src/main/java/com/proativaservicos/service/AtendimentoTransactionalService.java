package com.proativaservicos.service;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.DadosBancarios;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

@Stateless
public class AtendimentoTransactionalService {

    @EJB
    private AtendimentoService atendimentoService;

    @EJB
    private DadosBancariosService dadosBancariosService;


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void atualizarAtendimento(Atendimento atendimento, DadosBancarios dadosBancarios) {

        atendimentoService.atualizarAtendimentoSaqueMaster(
                atendimento.getId(),
                atendimento.getValorMaxOperacao(),
                atendimento.getMargem(),
                atendimento.getLimite(),
                atendimento.getBeneficioPrincipal(),
                atendimento.getInformacoesComplementares(),
                atendimento.getObservacao()
        );

        if (dadosBancarios != null && dadosBancarios.getBanco() != null) {
            dadosBancariosService.atualizarDadosBancarios(
                    atendimento.getId(),
                    dadosBancarios.getBanco(),
                    dadosBancarios.getAgencia(),
                    dadosBancarios.getConta()
            );
        }
    }


}

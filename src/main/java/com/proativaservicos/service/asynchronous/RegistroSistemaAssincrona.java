package com.proativaservicos.service.asynchronous;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.ImportacaoService;
import com.proativaservicos.service.LogAtendimentoService;
import com.proativaservicos.service.RegistroSistemaDiarioService;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Date;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Named
public class RegistroSistemaAssincrona {

    @Inject
    private LogAtendimentoService serviceLogAtendimento;

    @Inject
    private RegistroSistemaDiarioService serviceRegistroSistema;

    @Inject
    private ImportacaoService serviceImportacao;

    @Asynchronous
    public void gerarLog(Long idUsuario, Long idAtendimento, Long idCampanha, Long pontoAtendimento, Long pausa, String numeroDestivo, String dadoEnviado, String dadoRetorno, String evento, String ipRequisicao, Date data,
                         TipoEventoEnum tipoEvento) {

        try {

            if (idUsuario != null && idUsuario > 0L) {

                if (idAtendimento != null || idCampanha != null) {

                    LogAtendimento logAtendiment = new LogAtendimento();
                    logAtendiment.setUsuario(new Usuario(idUsuario));
                    logAtendiment.setData(new Date(System.currentTimeMillis()));

                    if (idAtendimento != null)
                        logAtendiment.setAtendimento(new Atendimento(idAtendimento));

                    if (pontoAtendimento != null)
                        logAtendiment.setPontoAtendimento(new PontoAtendimento(pontoAtendimento));

                    if (idCampanha != null)
                        logAtendiment.setCampanha(new Campanha(idCampanha));

                    if (dadoEnviado != null)
                        logAtendiment.setDadoEnviado(dadoEnviado);

                    if (dadoRetorno != null)
                        logAtendiment.setDadoRetorno(dadoRetorno);

                    if (evento != null)
                        logAtendiment.setEvento(evento);

                    this.serviceLogAtendimento.inserir(logAtendiment);

                }

                RegistroSistemaDiario registroSistemaDiario = new RegistroSistemaDiario();
                registroSistemaDiario.setUsuario(new Usuario(idUsuario));

                if (data != null) {

                    registroSistemaDiario.setDataCadastro(data);

                } else {

                    registroSistemaDiario.setDataCadastro(new Date(System.currentTimeMillis()));
                }

                if (pausa != null)
                    registroSistemaDiario.setTipoPausa(new Pausa(pausa));

                if (evento != null)
                    registroSistemaDiario.setEvento(evento);

                if (tipoEvento != null)
                    registroSistemaDiario.setTipo(tipoEvento);

                if (ipRequisicao != null)
                    registroSistemaDiario.setIpRequisicao(ipRequisicao);

                this.serviceRegistroSistema.inserir(registroSistemaDiario);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public void inserirImportacao(Importacao importacao) {

        try {


            this.serviceImportacao.alterar(importacao);

        } catch (ProativaException e) {

            e.printStackTrace();
        }
    }
}

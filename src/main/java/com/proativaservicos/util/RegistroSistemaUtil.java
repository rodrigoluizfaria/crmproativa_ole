package com.proativaservicos.util;

import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.Importacao;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.asynchronous.RegistroSistemaAssincrona;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.ejb.EJB;
import jakarta.inject.Named;


import java.io.Serializable;
import java.util.Date;

@Named
public class RegistroSistemaUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private RegistroSistemaAssincrona registro;

    public void registrarLog(Long idUsuario, TipoEventoEnum tipoEvento, String evento, String ip) {
        registrarLog(idUsuario, tipoEvento, evento, null, ip);

    }

    public void registrarLog(Long usuario, TipoEventoEnum tipo, String acao, Date data) {
        registrarLog(usuario, tipo, acao, data, null);

    }


    public void registrarLog(Long usuario, String ipRequisicao, TipoEventoEnum tipo, String acao) {

        registrarLog(usuario, tipo, acao, null, ipRequisicao);

    }

    public void registrarLog(Long usuario, TipoEventoEnum tipo, String evento, Date data, String ipRequisicao) {

        try {

            this.registro.gerarLog(usuario, null, null, null, null, null, null, null, evento, ipRequisicao, data, tipo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarLog(Long atendimento, Long campanha, Usuario usuario, TipoEventoEnum tipo, String evento, String dadoEnvio, String dadoRetorno) {

        try {

            this.registro.gerarLog(usuario.getId(), atendimento, campanha, null, null, null, dadoEnvio, dadoRetorno, evento, null, null, tipo);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void registraLog(Long codigoAtendimento, Long campanha, Usuario usuario, TipoEventoEnum tipo, String acao,
                            String dadoEnviado, String dadoRetornado) {
        this.registro.gerarLog(usuario.getId(), codigoAtendimento, campanha, null, null, null, dadoEnviado,
                dadoRetornado, acao, null, null, tipo);

    }

    public void registrarLog(GenericAtendimento atendimento, Usuario usuario, TipoEventoEnum tipoEvento, String evento) {
        this.registro.gerarLog(usuario.getId(), atendimento.getId(), null, null, null, null, null, null, evento, usuario.getIp(), null, tipoEvento);
    }

    public void registrarLog(Long idAtendimento, Usuario usuario, TipoEventoEnum tipo, String acao) {

        this.registro.gerarLog(usuario.getId(), idAtendimento, null, usuario.getPontoAtendimento() == null ? null : usuario.getPontoAtendimento().getId(), null, null, null, null, acao, usuario.getIp(), null, tipo);

    }

    public void registrarLog(Long idUsuario, TipoEventoEnum tipo, String evento) {

        this.registrarLog(idUsuario, tipo, evento, null, null);


    }

    public void alterarImportacao(Importacao importacao) {
        this.registro.inserirImportacao(importacao);
    }


}

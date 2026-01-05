package com.proativaservicos.model;

import com.proativaservicos.util.constantes.TipoPabxEnum;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Entity
@Table(name = "atendimento_audios")
public class AtendimentoAudios extends Generic {


    private static final long serialVersionUID = 1L;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPabxEnum tipoIntegracao;

    @Column(name = "ramal", length = 10)
    private String ramal;

    @Column(name = "fila")
    private String fila;

    @Column(name = "destino")
    private String destino;

    @Column(name = "call_id")
    private String callId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento")
    private Atendimento atendimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pabx")
    private Pabx pabx;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private Date data;

    @Transient
    private String urlAudio;


    public AtendimentoAudios() {

    }

    public AtendimentoAudios(String descricao, TipoPabxEnum tipo, String ramal, GenericAtendimento atendimento) {

        this.descricao = descricao;
        this.tipoIntegracao = tipo;
        this.ramal = ramal;
        this.atendimento = (Atendimento) atendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoPabxEnum getTipoIntegracao() {
        return tipoIntegracao;
    }

    public void setTipoIntegracao(TipoPabxEnum tipoIntegracao) {
        this.tipoIntegracao = tipoIntegracao;
    }

    public String getRamal() {
        return this.ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Atendimento getAtendimento() {
        return this.atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }


    public Pabx getPabx() {
        return pabx;
    }

    public void setPabx(Pabx pabx) {
        this.pabx = pabx;
    }


    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public String getUrlPublicaRange() {

        if (this.pabx != null
                && StringUtils.isNotBlank(this.pabx.getUrl())
                && this.pabx.getTipo().equals(TipoPabxEnum.PST_PHONE)) {

            String host = normalizarHost(this.pabx.getUrl());

            String path = "/profone/audio?uniqueid="
                    + this.callId.trim().replaceAll("(?i)\\.wav", "")
                    + "&modo=range";

            return host + path;

        }
        return null;
    }

    public String getUrlPublicaDownload() {

        if (this.pabx != null
                && StringUtils.isNotBlank(this.pabx.getUrl())
                && this.pabx.getTipo().equals(TipoPabxEnum.PST_PHONE)) {

            String host = normalizarHost(this.pabx.getUrl());

            String path = "/profone/audio?uniqueid="
                    + this.callId.trim().replaceAll("(?i)\\.wav", "")
                    + "&modo=download";

            return host + path;
        }
        return null;
    }

    private String normalizarHost(String host) {
        if (StringUtils.isBlank(host)) {
            return null;
        }
        // Se já começa com http:// ou https://, retorna como está
        if (host.startsWith("http://") || host.startsWith("https://")) {
            return host;
        }
        // Caso contrário, adiciona http://
        return "http://" + host;
    }

}

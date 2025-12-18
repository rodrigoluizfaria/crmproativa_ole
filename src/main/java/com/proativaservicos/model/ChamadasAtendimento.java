package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.proativaservicos.util.constantes.StatusTelefoneVonixEnum;
import com.proativaservicos.util.constantes.TipoPabxEnum;
import org.apache.commons.lang3.StringUtils;


@Entity
@Table(name = "chamadas_atendimento")
public class ChamadasAtendimento extends Generic {


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento")
    private Atendimento atendimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telefone")
    private Telefone telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pabx")
    private IntegracaoWs pabx;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_discagem")
    private Date dataDiscagem;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hangup")
    private Date dataHangup;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atendida")
    private Date dataAtendida;

    @Column(name = "duracao")
    private Long duracao;

    @Column(name = "status_telefone_vonix")
    @Enumerated(EnumType.STRING)
    private StatusTelefoneVonixEnum statusTelefoneVonix;

    @Column(name = "cod_status_telefone")
    private Integer codStatusTelefone;

    @Column(name = "irv_digit", length = 10)
    private String irvDigit;


    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the tipoIntegracao
     */
    public TipoPabxEnum getTipoIntegracao() {
        return tipoIntegracao;
    }

    /**
     * @param tipoIntegracao the tipoIntegracao to set
     */
    public void setTipoIntegracao(TipoPabxEnum tipoIntegracao) {
        this.tipoIntegracao = tipoIntegracao;
    }

    /**
     * @return the ramal
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * @param ramal the ramal to set
     */
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    /**
     * @return the fila
     */
    public String getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(String fila) {
        this.fila = fila;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the atendimento
     */
    public Atendimento getAtendimento() {
        return atendimento;
    }

    /**
     * @param atendimento the atendimento to set
     */
    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    /**
     * @return the telefone
     */
    public Telefone getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the pabx
     */
    public IntegracaoWs getPabx() {
        return pabx;
    }

    /**
     * @param pabx the pabx to set
     */
    public void setPabx(IntegracaoWs pabx) {
        this.pabx = pabx;
    }

    /**
     * @return the dataDiscagem
     */
    public Date getDataDiscagem() {
        return dataDiscagem;
    }

    /**
     * @param dataDiscagem the dataDiscagem to set
     */
    public void setDataDiscagem(Date dataDiscagem) {
        this.dataDiscagem = dataDiscagem;
    }

    /**
     * @return the dataHangup
     */
    public Date getDataHangup() {
        return dataHangup;
    }

    /**
     * @param dataHangup the dataHangup to set
     */
    public void setDataHangup(Date dataHangup) {
        this.dataHangup = dataHangup;
    }

    /**
     * @return the dataAtendida
     */
    public Date getDataAtendida() {
        return dataAtendida;
    }

    /**
     * @param dataAtendida the dataAtendida to set
     */
    public void setDataAtendida(Date dataAtendida) {
        this.dataAtendida = dataAtendida;
    }

    /**
     * @return the statusTelefoneVonix
     */
    public StatusTelefoneVonixEnum getStatusTelefoneVonix() {
        return statusTelefoneVonix;
    }

    /**
     * @param statusTelefoneVonix the statusTelefoneVonix to set
     */
    public void setStatusTelefoneVonix(StatusTelefoneVonixEnum statusTelefoneVonix) {
        this.statusTelefoneVonix = statusTelefoneVonix;
    }

    public String getIrvDigit() {
        return irvDigit;
    }

    public void setIrvDigit(String irvDigit) {
        this.irvDigit = irvDigit;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public Integer getCodStatusTelefone() {
        return codStatusTelefone;
    }

    public void setCodStatusTelefone(Integer codStatusTelefone) {
        this.codStatusTelefone = codStatusTelefone;
    }

    public String retornarStatusTelefoneDiscador3c() {

        if (this.codStatusTelefone != null) {
            switch (this.codStatusTelefone) {
                case 1:
                    return "Discando";
                case 2:
                    return "Atendida";
                case 3:
                    return "Conectada";
                case 4:
                    return "Encerrada";
                case 5:
                    return "Não atendida";
                case 6:
                    return "Abandonada";
                case 7:
                    return "Finalizada";
                case 8:
                    return "Falha";
                case 9:
                    return "Caixa postal pós atendimento";
                case 15:
                    return "Caixa postal pré atendimento";

                default:
                    return null;


            }
        }
        return null;

    }

    public static void main(String[] args) {
        ChamadasAtendimento chamadasAtendimento = new ChamadasAtendimento();
chamadasAtendimento.setCodStatusTelefone(16);
        System.out.println(chamadasAtendimento.retornarStatusTelefoneDiscador3c());
    }
}

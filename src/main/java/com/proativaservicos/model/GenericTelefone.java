package com.proativaservicos.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.proativaservicos.model.pwd.Numero;
import com.proativaservicos.util.NumeroUtil;
import com.proativaservicos.util.Util;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ComparisonChain;
import com.proativaservicos.util.constantes.SimNaoEnum;
import com.proativaservicos.util.constantes.TipoContatoEnum;

@MappedSuperclass
public abstract class GenericTelefone extends Generic implements Serializable, Comparable<GenericTelefone> {

    private static final long serialVersionUID = 1L;

    @Column(name = "ddd")
    private Short ddd;

    @Column(name = "numero", length = 15, nullable = false)
    private String numero;

    @Column(name = "ramal", length = 4)
    private String ramal;

    @Column(name = "tipo", length = 30)
    private String tipo;

    @Column(name = "atende")
    private String atende;

    @ManyToOne
    @JoinColumn(name = "status_telefone", foreignKey = @ForeignKey(name = "status_tel_telefone_fk"))
    private StatusTelefone statusTelefone;

    @Column(name = "whatsapp")
    private Boolean whatsapp;

    @Enumerated(EnumType.STRING)
    private SimNaoEnum exibe;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato")
    private TipoContatoEnum tipoContato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cadastro")
    private Usuario usuarioCadastro;

    @Column(name = "percentual_atendido")
    private BigDecimal percentualAtendido;

    @Column(name = "quantidade_discado")
    private Integer quantidadeDiscado;

    @Column(name = "quantidade_atendido")
    private Integer quantidadeAtendido;

    @JoinColumn(name = "cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    @XStreamOmitField
    private Cliente cliente;

    @Transient
    private boolean condicaoNovo;

    @Transient
    private boolean validaWhatsapp;
    @Transient
    private String observacao;

    public GenericTelefone() {

        this.exibe = SimNaoEnum.SIM;
    }

    public GenericTelefone(Short ddd, String numero) {
        this.exibe = SimNaoEnum.SIM;
        this.ddd = Short.valueOf(ddd);
        this.numero = numero;
    }

    public GenericTelefone(short ddd, String numero, String statusTelefone) {

        this.exibe = SimNaoEnum.SIM;
        this.ddd = Short.valueOf(ddd);
        this.numero = numero;
        this.statusTelefone = StringUtils.isBlank(statusTelefone) ? null : new StatusTelefone(statusTelefone);

    }

    public GenericTelefone(Long id, short ddd, String numero) {
        setId(id);
        this.ddd = Short.valueOf(ddd);
        this.numero = numero;
    }

    public Short getDdd() {
        return ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtende() {
        return atende;
    }

    public void setAtende(String atende) {
        this.atende = atende;
    }

    public StatusTelefone getStatusTelefone() {
        return statusTelefone;
    }

    public void setStatusTelefone(StatusTelefone statusTelefone) {
        this.statusTelefone = statusTelefone;
    }

    public Boolean getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public SimNaoEnum getExibe() {
        return exibe;
    }

    public void setExibe(SimNaoEnum exibe) {
        this.exibe = exibe;
    }

    public TipoContatoEnum getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContatoEnum tipoContato) {
        this.tipoContato = tipoContato;
    }

    public boolean isCondicaoNovo() {
        return condicaoNovo;
    }

    public void setCondicaoNovo(boolean condicaoNovo) {
        this.condicaoNovo = condicaoNovo;
    }

    public BigDecimal getPercentualAtendido() {
        return percentualAtendido;
    }

    public void setPercentualAtendido(BigDecimal percentualAtendido) {
        this.percentualAtendido = percentualAtendido;
    }

    public Integer getQuantidadeDiscado() {
        return quantidadeDiscado;
    }

    public void setQuantidadeDiscado(Integer quantidadeDiscado) {
        this.quantidadeDiscado = quantidadeDiscado;
    }

    public Integer getQuantidadeAtendido() {
        return quantidadeAtendido;
    }

    public void setQuantidadeAtendido(Integer quantidadeAtendido) {
        this.quantidadeAtendido = quantidadeAtendido;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @Override
    public int compareTo(GenericTelefone o) {
        return ComparisonChain.start().compare(this.ddd, o.getDdd()).compare(this.numero, o.getNumero()).result();
    }

    public boolean isValidaWhatsapp() {
        return validaWhatsapp;
    }

    public void setValidaWhatsapp(boolean validaWhatsapp) {
        this.validaWhatsapp = validaWhatsapp;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDDDTelefone() {

        String dddTelefone = "";

        if (this.numero != null && this.ddd != null)
            dddTelefone = this.ddd.toString();

        if (this.numero != null)
            dddTelefone = dddTelefone + this.numero;


        return dddTelefone;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDDDTelefoneFormatado() {

        String dddTelefone = "";

        if (this.ddd != null && this.numero != null) {

            dddTelefone = this.ddd + this.numero;

            if (this.numero.length() < 9)
                return Util.formatString(dddTelefone, "(##) ####-####");
            else
                return Util.formatString(dddTelefone, "(##) #####-####");

        }


        return dddTelefone;
    }

    public abstract GenericAtendimento getAtendimento();

    public abstract void setAtendimento(GenericAtendimento parametroGenericAtendimento);





}

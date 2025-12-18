package com.proativaservicos.model;

import jakarta.persistence.*;

import java.io.InputStream;
import java.util.Date;


@Entity
@Table(name = "conciliar_audio_anexo")
public class ConciliarAudioAnexo extends Generic {


    private static final long serialVersionUID = 1L;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "nome_arquivo_original")
    private String nomeArquivoOriginal;

    @Column(name = "arquivo_completo")
    private String arquivoCompleto;

    @Column(name = "data_conciliacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConciliacao;

    @Column(name = "data_anexo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAnexo;


    @Column(name = "transcricao")
    private String transcricao;


    @Column(name = "conciliado")
    private Boolean conciliado;

    @JoinColumn(name = "atendimento")
    @ManyToOne
    private Atendimento atendimento;

    @Column(name = "tamanho_arquivo")
    private Long tamanhoArquivo;

    @Column(name = "codigo_externo")
    private Integer codigoExterno;


    @Transient
    private InputStream inpuStream;

    @Transient
    private byte[] fileByte;

    @Transient
    private String arquivoBase64;

    @Transient
    private boolean inserido;

    public ConciliarAudioAnexo() {
    }

    public ConciliarAudioAnexo(String nomeArquivo, String arquivoCompleto, GenericAtendimento atendimento) {

        this.nomeArquivo = nomeArquivo;
        this.arquivoCompleto = arquivoCompleto;
        this.atendimento = (Atendimento) atendimento;
    }

    public ConciliarAudioAnexo(Long id, String nomeArquivo, String arquivoCompleto, Date dataConciliacao, Boolean conciliado, GenericAtendimento atendimento) {
        setId(id);
        this.nomeArquivo = nomeArquivo;
        this.arquivoCompleto = arquivoCompleto;
        this.dataConciliacao = dataConciliacao;
        this.conciliado = conciliado;
        this.atendimento = (Atendimento) atendimento;
    }


    /**
     * @return the nomeArquivo
     */
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    /**
     * @param nomeArquivo the nomeArquivo to set
     */
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    /**
     * @return the arquivoCompleto
     */
    public String getArquivoCompleto() {
        return arquivoCompleto;
    }

    /**
     * @param arquivoCompleto the arquivoCompleto to set
     */
    public void setArquivoCompleto(String arquivoCompleto) {
        this.arquivoCompleto = arquivoCompleto;
    }

    /**
     * @return the dataConciliacao
     */
    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    /**
     * @param dataConciliacao the dataConciliacao to set
     */
    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    /**
     * @return the conciliado
     */
    public Boolean getConciliado() {
        return conciliado;
    }

    /**
     * @param conciliado the conciliado to set
     */
    public void setConciliado(Boolean conciliado) {
        this.conciliado = conciliado;
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
     * @return the tamanhoArquivo
     */
    public Long getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    /**
     * @param tamanhoArquivo the tamanhoArquivo to set
     */
    public void setTamanhoArquivo(Long tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    /**
     * @return the inpuStream
     */
    public InputStream getInpuStream() {
        return inpuStream;
    }

    /**
     * @param inpuStream the inpuStream to set
     */
    public void setInpuStream(InputStream inpuStream) {
        this.inpuStream = inpuStream;
    }

    /**
     * @return the fileByte
     */
    public byte[] getFileByte() {
        return fileByte;
    }

    /**
     * @param fileByte the fileByte to set
     */
    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public boolean isInserido() {
        return inserido;
    }

    public void setInserido(boolean inserido) {
        this.inserido = inserido;
    }

    public String getArquivoBase64() {
        return arquivoBase64;
    }

    public void setArquivoBase64(String arquivoBase64) {
        this.arquivoBase64 = arquivoBase64;
    }


    public Date getDataAnexo() {
        return dataAnexo;
    }

    public void setDataAnexo(Date dataAnexo) {
        this.dataAnexo = dataAnexo;
    }

    public String getNomeArquivoOriginal() {
        return nomeArquivoOriginal;
    }

	public String getTranscricao() {
		return transcricao;
	}

	public void setTranscricao(String transcricao) {
		this.transcricao = transcricao;
	}

	public void setNomeArquivoOriginal(String nomeArquivoOriginal) {
        this.nomeArquivoOriginal = nomeArquivoOriginal;
    }

    public Integer getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(Integer codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
}

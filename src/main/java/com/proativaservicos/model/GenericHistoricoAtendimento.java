package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class GenericHistoricoAtendimento extends Generic {

    private static final long serialVersionUID = 1L;

    @Column(name = "protocolo")
    private String protocolo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date agendamento;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_visualizado")
    private Date dataVisualizado;

    @Column(name = "numero_destino")
    private String numeroDestino;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "observacao", columnDefinition = "text")
    private String observacao;

    @Column(name = "observacao_adicional", columnDefinition = "text")
    private String observacaoAdicional;

    @Column(name = "observacao_n2", columnDefinition = "text")
    private String observacaoN2;

    @Column(name = "anotacao", columnDefinition = "text")
    private String anotacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pausa")
    private Pausa pausa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    private Usuario usuario;


    @Column(name = "tempo_pos_atendimento")
    private Long tempoPosAtendimento;

    private byte[] anexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponto_atendimento", foreignKey = @ForeignKey(name = "historico_ponto_fk"))
    private PontoAtendimento pontoAtendimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_atendimento", foreignKey = @ForeignKey(name = "historico_status_fk"))
    private StatusAtendimento statusAtendimento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_out_operador")
    private Usuario usuarioTimeOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motivo")
    private Motivo motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submotivo")
    private SubMotivo subMotivomotivo;

    @Column(name = "enviar_n2")
    private Boolean enviarN2;

    @Column(name = "atendimento_finalizado")
    private Boolean atendimentoFinalizado;


    @Column(name = "posicao_fila")
    private Integer posicaoFila;

    @Column(name = "abertura_demanda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAberturaDemanda;

    @Column(name = "prazo_demanda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prazoPrazoDemanda;

    @Column(name = "fechamento_demanda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamentoDemanda;

    @Column(name = "demanda_encerrada")
    private Boolean demandaEncerrada;

    @Column(name = "data_inicio_atendimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicioAtendimento;

    @Column(name = "data_fim_atendimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFimAtendimento;

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Date agendamento) {
        this.agendamento = agendamento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataVisualizado() {
        return dataVisualizado;
    }

    public void setDataVisualizado(Date dataVisualizado) {
        this.dataVisualizado = dataVisualizado;
    }

    public String getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pausa getPausa() {
        return pausa;
    }

    public void setPausa(Pausa pausa) {
        this.pausa = pausa;
    }

    public byte[] getAnexo() {
        return anexo;
    }

    public void setAnexo(byte[] anexo) {
        this.anexo = anexo;
    }

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }

    public StatusAtendimento getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public abstract GenericAtendimento getAtendimento();

    public abstract void setAtendimento(GenericAtendimento atn);

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getTempoPosAtendimento() {
        return tempoPosAtendimento;
    }

    public void setTempoPosAtendimento(Long tempoPosAtendimento) {
        this.tempoPosAtendimento = tempoPosAtendimento;
    }

    public Usuario getUsuarioTimeOut() {
        return usuarioTimeOut;
    }

    public void setUsuarioTimeOut(Usuario usuarioTimeOut) {
        this.usuarioTimeOut = usuarioTimeOut;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public SubMotivo getSubMotivomotivo() {
        return subMotivomotivo;
    }

    public void setSubMotivomotivo(SubMotivo subMotivomotivo) {
        this.subMotivomotivo = subMotivomotivo;
    }

    public Boolean getEnviarN2() {
        return enviarN2;
    }

    public void setEnviarN2(Boolean enviarN2) {
        this.enviarN2 = enviarN2;
    }

    public Integer getPosicaoFila() {
        return posicaoFila;
    }

    public void setPosicaoFila(Integer posicaoFila) {
        this.posicaoFila = posicaoFila;
    }

    public Date getDataAberturaDemanda() {
        return dataAberturaDemanda;
    }

    public void setDataAberturaDemanda(Date dataAberturaDemanda) {
        this.dataAberturaDemanda = dataAberturaDemanda;
    }

    public Date getPrazoPrazoDemanda() {
        return prazoPrazoDemanda;
    }

    public void setPrazoPrazoDemanda(Date prazoPrazoDemanda) {
        this.prazoPrazoDemanda = prazoPrazoDemanda;
    }

    public Date getDataFechamentoDemanda() {
        return dataFechamentoDemanda;
    }

    public void setDataFechamentoDemanda(Date dataFechamentoDemanda) {
        this.dataFechamentoDemanda = dataFechamentoDemanda;
    }

    public Boolean getDemandaEncerrada() {
        return demandaEncerrada;
    }

    public void setDemandaEncerrada(Boolean demandaEncerrada) {
        this.demandaEncerrada = demandaEncerrada;
    }

    public Date getDataInicioAtendimento() {
        return dataInicioAtendimento;
    }

    public void setDataInicioAtendimento(Date dataInicioAtendimento) {
        this.dataInicioAtendimento = dataInicioAtendimento;
    }

    public Date getDataFimAtendimento() {
        return dataFimAtendimento;
    }

    public void setDataFimAtendimento(Date dataFimAtendimento) {
        this.dataFimAtendimento = dataFimAtendimento;
    }

    public String getObservacaoAdicional() {
        return observacaoAdicional;
    }

    public void setObservacaoAdicional(String observacaoAdicional) {
        this.observacaoAdicional = observacaoAdicional;
    }

    public String getObservacaoN2() {
        return observacaoN2;
    }

    public void setObservacaoN2(String observacaoN2) {
        this.observacaoN2 = observacaoN2;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public Boolean getAtendimentoFinalizado() {
        return atendimentoFinalizado;
    }

    public void setAtendimentoFinalizado(Boolean atendimentoFinalizado) {
        this.atendimentoFinalizado = atendimentoFinalizado;
    }
}

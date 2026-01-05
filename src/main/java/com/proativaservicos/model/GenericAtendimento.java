package com.proativaservicos.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.*;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

/*
 * Classe Generica de atendimento
 *
 */

@MappedSuperclass
public abstract class GenericAtendimento extends GenericControle {

    public GenericAtendimento() {
        this.fazerSeguro = true;
        this.abrirContaPagamento = false;
        this.valorComissaoPagar = Double.valueOf(0.0D);
        this.quantidadeProposta = Integer.valueOf(1);
    }

    public GenericAtendimento(Long id) {
        setId(id);
    }

    public GenericAtendimento(String nome) {
        this.nome = nome;
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "adesao", length = 30)
    private String adesao;

    @Column(name = "protocolo", length = 50)
    private String protocolo;

    // CLIENTE
    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "cpf", length = 15)
    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column(name = "nome_mae", length = 150)
    private String nomeMae;

    @Column(name = "nome_pai", length = 150)
    private String nomePai;

    @Column(name = "nome_conjuge", length = 150)
    private String nomeConjuge;

    @Column(name = "uf", length = 30)
    private String uf;

    @Column(name = "nacionalidade", length = 60)
    private String nacionalidade;

    @Column(name = "uf_nascimento")
    private String ufNascimento;

    @Column(name = "cidade_nascimento", length = 30)
    private String cidadeNascimento;

    @Column(name = "estado_civil", length = 20)
    @Enumerated(EnumType.STRING)
    private EstadoCivilEnum estadoCivil;

    @Column(name = "uf_documento", length = 30)
    private String ufDocumento;

    @Column(name = "data_emissao_documento")
    private Date dataEmissaoDocumento;

    @Column(name = "numero_documento", length = 30)
    private String numeroDocumento;

    @Column(name = "signo")
    private String signo;

    @Column(name = "orgao_doc", length = 30)
    private String orgaoDocumento;

    // ATN
    @Column(name = "prioridade", length = 30)
    @Enumerated(EnumType.STRING)
    private PrioridadeEnum prioridade;

    @Column(name = "enviado")
    private Boolean enviado;

    @Enumerated(EnumType.STRING)
    private SimNaoEnum atender;

    @Column(name = "conciliado")
    private Boolean conciliado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_conciliacao")
    private Date dataConciliacao;

    @Column(name = "discou")
    private Boolean discou;

    @Column(name = "publico")
    private Boolean publico;

    // DESCOBRIR
    @Column(name = "tipo_beneficio")
    private Integer tipoBeneficio;

    @Column(name = "tempo_pos_atendimento")
    private Long tempoPosAtendimento;

    @Column(name = "situacao_servidor")
    private Integer situacaoServidor;

    // IDENTIFICAR
    @Column(name = "peso_carteira")
    private Integer pesoCarteira;

    @Column(name = "quantidade_discagem")
    private Integer quantidadeDiscagem;

    // DISCADOR
    @Column(name = "peso_discagem")
    private Integer pesoDiscagem;

    @Column(name = "abertura_conta")
    private Boolean aberturaConta;

    // IDENTIFICAR
    @Column(name = "fluxo", length = 30)
    private String fluxo;

    // OBS
    @Column(name = "outras_informacoes", columnDefinition = "text")
    private String outrasInformacoes;

    //
    @Column(name = "margem_resultado", length = 30)
    private String margemResultado;

    @Column(name = "manifesto", columnDefinition = "text")
    private String manifesto;

    @Column(name = "motivo_retencao")
    private MotivoRetencaoEnum motivoRetencao;

    @Column(name = "observacao", columnDefinition = "text")
    private String observacao;

    @Column(name = "prospect")
    private boolean prospect;

    @Column(name = "vinculo_matricula", length = 30)
    private String vinculoMatricula;

    @Column(name = "codigo_averbacao", length = 30)
    private String codigoAverbacao;

    @Column(name = "tipo_convenio", length = 30)
    private String tipoConvenio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio_atendimento")
    private Date dataInicioAtendimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_fim_atendimento")
    private Date dataFimAtendimento;

    @Column(name = "comissao_pagar", length = 30)
    private String comissaoPagar;

    @Column(name = "valor_liberado", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorLiberado;

    @Column(name = "valor_parcela", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorParcela;

    @Column(name = "valor_max_operacao", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorMaxOperacao;

    @Column(name = "margem", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal margem;

    @Column(name = "margem_cinco", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal margemCinco;

    @Column(name = "margem_secundaria", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal margemSecundaria;

    @Column(name = "margem_secundaria_cinco", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal margemSecundariaCinco;

    @Column(name = "limite", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal limite;

    @Column(name = "limite_disponivel", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal limiteDisponivel;

    @Column(name = "seguro", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal seguro;

    @Column(name = "valor_seguro_liberado", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorSeguroLiberado;

    @Column(name = "seguro_prestamista", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal seguroPrestamista;

    @Column(name = "valor_contrato", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorContrato;

    @Column(name = "saldo_devedor", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal saldoDevedor;

    @Column(name = "valor_total", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorTotal;

    @Column(name = "risco", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal risco;

    @Column(name = "valor_liberado_refin", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorLiberadoRefinanciamento;

    @Column(name = "valor_liberado_emp", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorLiberadoEmprestimo;

    @Column(name = "salario_cliente", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal salarioCliente;

    @Column(name = "valor_carta_credito", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal valorCartaCredito;

    @Column(name = "taxa", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal taxa;

    @Column(name = "beneficio_principal")
    private String beneficioPrincipal;

    @Column(name = "beneficio_secundario", length = 30)
    private String beneficioSecundario;

    @Column(name = "desconto_compulsorio", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal descontoCompulsorio;

    @Column(name = "desconto_facultativo", columnDefinition = "NUMERIC(19,2)")
    private BigDecimal descontoFacultativo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_vencimento")
    private Date dataVencimento;

    @Column(name = "quantidade_contratos")
    private int quantidadeContratos;

    @Column(name = "quantidade_parcelas")
    private Integer quantidadeParcela;

    @Column(name = "instituicao_financeira", length = 30)
    @Enumerated(EnumType.STRING)
    private InstituicaoFinanceiraEnum instituicaoFinanceira;

    @Column(name = "informacoes_complementares", columnDefinition = "text")
    private String informacoesComplementares;

    @Column(name = "cod_loja", length = 30)
    private String codigoLoja;

    @Column(name = "entidade_principal", length = 50)
    private String entidadePrincipal;

    @Column(name = "entidade_secundaria", length = 50)
    private String entidadeSecundaria;

    @Column(name = "orgao_principal", length = 50)
    private String orgaoPrincipal;

    @Column(name = "orgao_secundario", length = 50)
    private String orgaoSecundario;

    @Column(name = "mailingid")
    private String mailingId;

    @Column(name = "cod_tabela_refin")
    private Integer codTabelaRefin;

    @Column(name = "target")
    private boolean target;

    @Column(name = "enviar_n2")
    private Boolean enviarN2;

    @Column(name = "posicao_fila")
    private Integer posicaoFila;

    @Column(name = "abertura_demanda")
    private Date dataAberturaDemanda;

    @Column(name = "prazo_demanda")
    private Date prazoPrazoDemanda;

    @Column(name = "fechamento_demanda")
    private Date dataFechamentoDemanda;

    @Column(name = "demanda_encerrada")
    private Boolean demandaEncerrada;

    @Transient
    private Boolean clienteVip;


    @Column(name = "anotacao")
    private String anotacao;

    @Column(name = "observacao_adicional")
    private String observacaoAdicional;

    @Column(name = "observacao_n2")
    private String observacaoN2;

    @Column(name = "resposta_n2")
    private String respostaN2;

    @Column(name = "tipo_cliente")
    @Enumerated(EnumType.STRING)
    private TipoClienteEnum tipoClienteEnum;

    @Column(name = "atendimento_finalizado")
    private Boolean atendimentoFinalizado;

    @Column(name = "atendimento_pai")
    private Boolean atendimentoPai;

    @Column(name = "protocolo_pai")
    private String protocoloPai;

    @Column(name = "fluxo_esteira")
    @Enumerated(EnumType.STRING)
    private TipoFluxoEsteira fluxoEsteira;

    @JoinColumn(name = "usuario_em_atendimento", foreignKey = @ForeignKey(name = "atendimento_usr_em_atn_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioOcupado;

    @JoinColumn(name = "campanha", foreignKey = @ForeignKey(name = "atendimento_campanha_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Campanha campanha;

    @JoinColumn(name = "status", foreignKey = @ForeignKey(name = "atendimento_status_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private StatusAtendimento status;

    @JoinColumn(name = "motivo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Motivo motivo;

    @JoinColumn(name = "submotivo")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubMotivo subMotivo;

    @JoinColumn(name = "produto", foreignKey = @ForeignKey(name = "atendimento_produto_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @JoinColumn(name = "importacao", foreignKey = @ForeignKey(name = "atendimento_importacao_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Importacao importacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe", foreignKey = @ForeignKey(name = "atendimento_equipe_fk"))
    private Equipe equipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "atendimento_empresa_fk"))
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja", foreignKey = @ForeignKey(name = "atendimento_loja_fk"))
    private Loja loja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor")
    private Usuario supervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador")
    private Usuario coordenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento", foreignKey = @ForeignKey(name = "atendimento_forma_pag_fk"))
    private FormaPagamento formaPagamento;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "contrato")
    private Contrato contrato;

    @Column(name = "prioridade_fila")
    private Integer prioridadeFila;


    @Column(name = "tiket", length = 30)
    private String tiket;

    @Column(name = "bko")
    private Boolean bko;

    @Column(name = "rating")
    private Integer rating;

    @JoinColumn(name = "cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    @XStreamOmitField
    private Cliente cliente;

    @JoinColumn(name = "responsaveln2")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario responsavelN2;

    @JoinColumn(name = "departamento_derivado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamentoDerivado;

    @Transient
    private Map<String, String> listInformacoesComplementares;

    @Transient
    private Double valorSaque;

    @Transient
    private String descricaoSeguro;
    @Transient
    private double capitalSegurado;
    @Transient
    private double valorSeguro;
    @Transient
    private boolean fazerSeguro;
    @Transient
    private boolean abrirContaPagamento;
    @Transient
    private Object situacaoFuncional;

    @Transient
    private Double valorComissaoPagar;
    @Transient
    private Integer quantidadeProposta;
    @Transient
    private List<Equipe> equipes;

    @Transient
    private List<StatusAtendimento> listStatusAtendimentos;

    @Transient
    private List<StatusTelefone> statusTelefones;
    @Transient
    private List<Pausa> pausas;
    @Transient
    private List<Produto> produtos;
    @Transient
    private List<FormaPagamento> formaPagamentos;

    @Transient
    private String especie;


    public List<String> getListInformacoesComplementaresChaves() {
        if (this.listInformacoesComplementares == null) {
            return null;
        }

        return new ArrayList<>(this.listInformacoesComplementares.keySet());
    }

    public Usuario getResponsavelN2() {
        return responsavelN2;
    }

    public void setResponsavelN2(Usuario responsavelN2) {
        this.responsavelN2 = responsavelN2;
    }

    public String getAdesao() {
        return adesao;
    }

    public void setAdesao(String adesao) {
        this.adesao = adesao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getUfNascimento() {
        return ufNascimento;
    }

    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public EstadoCivilEnum getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getUfDocumento() {
        return ufDocumento;
    }

    public void setUfDocumento(String ufDocumento) {
        this.ufDocumento = ufDocumento;
    }

    public Date getDataEmissaoDocumento() {
        return dataEmissaoDocumento;
    }

    public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
        this.dataEmissaoDocumento = dataEmissaoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getEntidadePrincipal() {
        return entidadePrincipal;
    }

    public void setEntidadePrincipal(String entidadePrincipal) {
        this.entidadePrincipal = entidadePrincipal;
    }

    public String getEntidadeSecundaria() {
        return entidadeSecundaria;
    }

    public void setEntidadeSecundaria(String entidadeSecundaria) {
        this.entidadeSecundaria = entidadeSecundaria;
    }

    public String getOrgaoPrincipal() {
        return orgaoPrincipal;
    }

    public void setOrgaoPrincipal(String orgaoPrincipal) {
        this.orgaoPrincipal = orgaoPrincipal;
    }

    public String getOrgaoSecundario() {
        return orgaoSecundario;
    }

    public void setOrgaoSecundario(String orgaoSecundario) {
        this.orgaoSecundario = orgaoSecundario;
    }

    public String getOrgaoDocumento() {
        return orgaoDocumento;
    }

    public void setOrgaoDocumento(String orgaoDocumento) {
        this.orgaoDocumento = orgaoDocumento;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public SimNaoEnum getAtender() {
        return atender;
    }

    public void setAtender(SimNaoEnum atender) {
        this.atender = atender;
    }

    public Boolean getConciliado() {
        return conciliado;
    }

    public void setConciliado(Boolean conciliado) {
        this.conciliado = conciliado;
    }

    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    public Boolean getDiscou() {
        return discou;
    }

    public void setDiscou(Boolean discou) {
        this.discou = discou;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public Integer getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(Integer tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public Integer getSituacaoServidor() {
        return situacaoServidor;
    }

    public void setSituacaoServidor(Integer situacaoServidor) {
        this.situacaoServidor = situacaoServidor;
    }

    public Integer getPesoCarteira() {
        return pesoCarteira;
    }

    public void setPesoCarteira(Integer pesoCarteira) {
        this.pesoCarteira = pesoCarteira;
    }

    public Integer getQuantidadeDiscagem() {
        return quantidadeDiscagem;
    }

    public void setQuantidadeDiscagem(Integer quantidadeDiscagem) {
        this.quantidadeDiscagem = quantidadeDiscagem;
    }

    public Integer getPesoDiscagem() {
        return pesoDiscagem;
    }

    public void setPesoDiscagem(Integer pesoDiscagem) {
        this.pesoDiscagem = pesoDiscagem;
    }

    public Boolean getAberturaConta() {
        return aberturaConta;
    }

    public void setAberturaConta(Boolean aberturaConta) {
        this.aberturaConta = aberturaConta;
    }

    public String getFluxo() {
        return fluxo;
    }

    public void setFluxo(String fluxo) {
        this.fluxo = fluxo;
    }

    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }

    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }

    public String getMargemResultado() {
        return margemResultado;
    }

    public void setMargemResultado(String margemResultado) {
        this.margemResultado = margemResultado;
    }

    public String getManifesto() {
        return manifesto;
    }

    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }

    public MotivoRetencaoEnum getMotivoRetencao() {
        return motivoRetencao;
    }

    public void setMotivoRetencao(MotivoRetencaoEnum motivoRetencao) {
        this.motivoRetencao = motivoRetencao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isProspect() {
        return prospect;
    }

    public void setProspect(boolean prospect) {
        this.prospect = prospect;
    }

    public String getVinculoMatricula() {
        return vinculoMatricula;
    }

    public void setVinculoMatricula(String vinculoMatricula) {
        this.vinculoMatricula = vinculoMatricula;
    }

    public String getCodigoAverbacao() {
        return codigoAverbacao;
    }

    public void setCodigoAverbacao(String codigoAverbacao) {
        this.codigoAverbacao = codigoAverbacao;
    }

    public String getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(String tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
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

    public String getComissaoPagar() {
        return comissaoPagar;
    }

    public void setComissaoPagar(String comissaoPagar) {
        this.comissaoPagar = comissaoPagar;
    }

    public BigDecimal getValorLiberado() {
        return valorLiberado;
    }

    public void setValorLiberado(BigDecimal valorLiberado) {
        this.valorLiberado = valorLiberado;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public BigDecimal getValorMaxOperacao() {
        return valorMaxOperacao;
    }

    public void setValorMaxOperacao(BigDecimal valorMaxOperacao) {
        this.valorMaxOperacao = valorMaxOperacao;
    }

    public BigDecimal getMargem() {
        return margem;
    }

    public void setMargem(BigDecimal margem) {
        this.margem = margem;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getLimitFormatado() {
        if (limite == null) {
            return "R$ 0,00";
        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(limite);
    }

    public String getLimitDisponivelFormatado() {

        if (limiteDisponivel == null) {
            return "R$ 0,00";
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(limiteDisponivel);
    }

    public String getLimiteSaqueFormatado() {

        if (valorLiberado == null) {
            return "R$ 0,00";
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valorLiberado);
    }

    public String getLimiteSaqueDisponivel() {

        if (valorMaxOperacao == null) {
            return "R$ 0,00";
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valorMaxOperacao);
    }


    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getSeguro() {
        return seguro;
    }

    public void setSeguro(BigDecimal seguro) {
        this.seguro = seguro;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public BigDecimal getSaldoDevedor() {
        return saldoDevedor;
    }

    public BigDecimal getSeguroPrestamista() {
        return seguroPrestamista;
    }

    public void setSeguroPrestamista(BigDecimal seguroPrestamista) {
        this.seguroPrestamista = seguroPrestamista;
    }

    public void setSaldoDevedor(BigDecimal saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getRisco() {
        return risco;
    }

    public void setRisco(BigDecimal risco) {
        this.risco = risco;
    }

    public BigDecimal getValorLiberadoRefinanciamento() {
        return valorLiberadoRefinanciamento;
    }

    public void setValorLiberadoRefinanciamento(BigDecimal valorLiberadoRefinanciamento) {
        this.valorLiberadoRefinanciamento = valorLiberadoRefinanciamento;
    }

    public BigDecimal getValorLiberadoEmprestimo() {
        return valorLiberadoEmprestimo;
    }

    public void setValorLiberadoEmprestimo(BigDecimal valorLiberadoEmprestimo) {
        this.valorLiberadoEmprestimo = valorLiberadoEmprestimo;
    }

    public BigDecimal getSalarioCliente() {
        return salarioCliente;
    }

    public void setSalarioCliente(BigDecimal salarioCliente) {
        this.salarioCliente = salarioCliente;
    }

    public BigDecimal getValorCartaCredito() {
        return valorCartaCredito;
    }

    public void setValorCartaCredito(BigDecimal valorCartaCredito) {
        this.valorCartaCredito = valorCartaCredito;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public String getBeneficioPrincipal() {
        return beneficioPrincipal;
    }

    public void setBeneficioPrincipal(String beneficioPrincipal) {
        this.beneficioPrincipal = beneficioPrincipal;
    }

    public String getBeneficioSecundario() {
        return beneficioSecundario;
    }

    public void setBeneficioSecundario(String beneficioSecundario) {
        this.beneficioSecundario = beneficioSecundario;
    }

    public BigDecimal getDescontoCompulsorio() {
        return descontoCompulsorio;
    }

    public void setDescontoCompulsorio(BigDecimal descontoCompulsorio) {
        this.descontoCompulsorio = descontoCompulsorio;
    }

    public BigDecimal getDescontoFacultativo() {
        return descontoFacultativo;
    }

    public void setDescontoFacultativo(BigDecimal descontoFacultativo) {
        this.descontoFacultativo = descontoFacultativo;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getQuantidadeContratos() {
        return quantidadeContratos;
    }

    public void setQuantidadeContratos(int quantidadeContratos) {
        this.quantidadeContratos = quantidadeContratos;
    }

    public Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public InstituicaoFinanceiraEnum getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(InstituicaoFinanceiraEnum instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public String getInformacoesComplementares() {
        return informacoesComplementares;
    }

    public void setInformacoesComplementares(String informacoesComplementares) {
        this.informacoesComplementares = informacoesComplementares;
    }

    public String getCodigoLoja() {
        return codigoLoja;
    }

    public void setCodigoLoja(String codigoLoja) {
        this.codigoLoja = codigoLoja;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public TipoFluxoEsteira getFluxoEsteira() {
        return fluxoEsteira;
    }

    public void setFluxoEsteira(TipoFluxoEsteira fluxoEsteira) {
        this.fluxoEsteira = fluxoEsteira;
    }

    public Usuario getUsuarioOcupado() {
        return usuarioOcupado;
    }

    public void setUsuarioOcupado(Usuario usuarioOcupado) {
        this.usuarioOcupado = usuarioOcupado;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Importacao getImportacao() {
        return importacao;
    }

    public void setImportacao(Importacao importacao) {
        this.importacao = importacao;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public BigDecimal getMargemSecundaria() {
        return margemSecundaria;
    }

    public void setMargemSecundaria(BigDecimal margemSecundaria) {
        this.margemSecundaria = margemSecundaria;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Map<String, String> getListInformacoesComplementares() {
        return listInformacoesComplementares;
    }

    public void setListInformacoesComplementares(Map<String, String> listInformacoesComplementares) {
        this.listInformacoesComplementares = listInformacoesComplementares;
    }

    public Double getValorSaque() {
        return valorSaque;
    }

    public void setValorSaque(Double valorSaque) {
        this.valorSaque = valorSaque;
    }

    public String getDescricaoSeguro() {
        return descricaoSeguro;
    }

    public void setDescricaoSeguro(String descricaoSeguro) {
        this.descricaoSeguro = descricaoSeguro;
    }

    public double getCapitalSegurado() {
        return capitalSegurado;
    }

    public void setCapitalSegurado(double capitalSegurado) {
        this.capitalSegurado = capitalSegurado;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }


    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public boolean isFazerSeguro() {
        return fazerSeguro;
    }

    public void setFazerSeguro(boolean fazerSeguro) {
        this.fazerSeguro = fazerSeguro;
    }

    public boolean isAbrirContaPagamento() {
        return abrirContaPagamento;
    }

    public void setAbrirContaPagamento(boolean abrirContaPagamento) {
        this.abrirContaPagamento = abrirContaPagamento;
    }

    public Object getSituacaoFuncional() {
        return situacaoFuncional;
    }

    public void setSituacaoFuncional(Object situacaoFuncional) {
        this.situacaoFuncional = situacaoFuncional;
    }

    public Double getValorComissaoPagar() {
        return valorComissaoPagar;
    }

    public void setValorComissaoPagar(Double valorComissaoPagar) {
        this.valorComissaoPagar = valorComissaoPagar;
    }

    public Integer getQuantidadeProposta() {
        return quantidadeProposta;
    }

    public void setQuantidadeProposta(Integer quantidadeProposta) {
        this.quantidadeProposta = quantidadeProposta;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public List<StatusAtendimento> getListStatusAtendimentos() {
        return listStatusAtendimentos;
    }

    public void setListStatusAtendimentos(List<StatusAtendimento> statusListAtendimentos) {
        this.listStatusAtendimentos = statusListAtendimentos;
    }

    public List<StatusTelefone> getStatusTelefones() {
        return statusTelefones;
    }

    public void setStatusTelefones(List<StatusTelefone> statusTelefones) {
        this.statusTelefones = statusTelefones;
    }

    public List<Pausa> getPausas() {
        return pausas;
    }

    public void setPausas(List<Pausa> pausas) {
        this.pausas = pausas;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Integer getPrioridadeFila() {
        return prioridadeFila;
    }

    public void setPrioridadeFila(Integer prioridadeFila) {
        this.prioridadeFila = prioridadeFila;
    }


    public Long getTempoPosAtendimento() {
        return tempoPosAtendimento;
    }

    public void setTempoPosAtendimento(Long tempoPosAtendimento) {
        this.tempoPosAtendimento = tempoPosAtendimento;
    }

    public BigDecimal getMargemCinco() {
        return margemCinco;
    }

    public void setMargemCinco(BigDecimal margemCinco) {
        this.margemCinco = margemCinco;
    }

    public BigDecimal getMargemSecundariaCinco() {
        return margemSecundariaCinco;
    }

    public void setMargemSecundariaCinco(BigDecimal margemSecundariaCinco) {
        this.margemSecundariaCinco = margemSecundariaCinco;
    }


    public String getMailingId() {
        return mailingId;
    }

    public void setMailingId(String mailingId) {
        this.mailingId = mailingId;
    }

    public Integer getCodTabelaRefin() {
        return codTabelaRefin;
    }

    public void setCodTabelaRefin(Integer codTabelaRefin) {
        this.codTabelaRefin = codTabelaRefin;
    }

    public Usuario getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Usuario coordenador) {
        this.coordenador = coordenador;
    }

    public Usuario getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Usuario supervisor) {
        this.supervisor = supervisor;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }

    public Boolean getBko() {
        return bko;
    }

    public void setBko(Boolean bko) {
        this.bko = bko;
    }

    public BigDecimal getValorSeguroLiberado() {
        return valorSeguroLiberado;
    }

    public void setValorSeguroLiberado(BigDecimal valorSeguroLiberado) {
        this.valorSeguroLiberado = valorSeguroLiberado;
    }

    public abstract void setListHistoricos(List<? extends GenericHistoricoAtendimento> paramList);

    public abstract void adicionarHistorico(GenericHistoricoAtendimento paramGenericHistorico);

    public abstract List<? extends GenericTelefone> getListaTelefones();

    public abstract List<? extends GenericTelefone> getListaTelefonesOrdenada();

    public abstract void setListaTelefones(List<? extends GenericTelefone> paramList);

    public abstract boolean adicionarTelefone(GenericTelefone paramGenericTelefone);

    public abstract List<? extends GenericDadosBancarios> getListaDadosBancarios();

    public abstract void setListaDadosBancarios(List<? extends Generic> paramList);

    public abstract List<? extends GenericCartaoCredito> getListaCartoesCredito();

    public abstract void setListaCartoesCredito(List<? extends GenericCartaoCredito> paramList);

    public abstract void adicionarDadosBancarios(GenericDadosBancarios paramGenericDadosBancarios);

    public abstract void adicionarCartoesCredito(GenericCartaoCredito paramGenericCartaoCredito);

    public abstract List<? extends GenericEmail> getListaEmails();

    public abstract void setListaEmails(List<? extends GenericEmail> paramList);

    public abstract void adicionarEmail(GenericEmail paramGenericEmail);

    public abstract List<? extends GenericEndereco> getListaEnderecos();

    public abstract void setListaEnderecos(List<? extends GenericEndereco> paramList);

    public abstract void adicionarEndereco(GenericEndereco paramGenericEndereco);

    public abstract List<? extends GenericContratoEfetivado> getListaContratosEfetivados();

    public abstract void setListaContratosEfetivados(List<? extends GenericContratoEfetivado> paramList);

    public abstract void adicionarContratoEfetivado(GenericContratoEfetivado paramGenericContratoEfetivado);

    public abstract void adicionarPortabilidade(Portabilidade portabilidade);

    public abstract List<? extends GenericHistoricoAtendimento> getListaHistoricos();

    @Override
    public String toString() {
        return "GenericAtendimento{" +
                "adesao='" + adesao + '\'' +
                ", protocolo='" + protocolo + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", nomeMae='" + nomeMae + '\'' +
                ", nomePai='" + nomePai + '\'' +
                ", nomeConjuge='" + nomeConjuge + '\'' +
                ", uf='" + uf + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", ufNascimento='" + ufNascimento + '\'' +
                ", cidadeNascimento='" + cidadeNascimento + '\'' +
                ", estadoCivil=" + estadoCivil +
                ", ufDocumento='" + ufDocumento + '\'' +
                ", dataEmissaoDocumento=" + dataEmissaoDocumento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", signo='" + signo + '\'' +
                ", orgaoDocumento='" + orgaoDocumento + '\'' +
                ", prioridade=" + prioridade +
                ", enviado=" + enviado +
                ", atender=" + atender +
                ", conciliado=" + conciliado +
                ", dataConciliacao=" + dataConciliacao +
                ", discou=" + discou +
                ", publico=" + publico +
                ", tipoBeneficio=" + tipoBeneficio +
                ", tempoPosAtendimento=" + tempoPosAtendimento +
                ", situacaoServidor=" + situacaoServidor +
                ", pesoCarteira=" + pesoCarteira +
                ", quantidadeDiscagem=" + quantidadeDiscagem +
                ", pesoDiscagem=" + pesoDiscagem +
                ", aberturaConta=" + aberturaConta +
                ", fluxo='" + fluxo + '\'' +
                ", outrasInformacoes='" + outrasInformacoes + '\'' +
                ", margemResultado='" + margemResultado + '\'' +
                ", manifesto='" + manifesto + '\'' +
                ", motivoRetencao=" + motivoRetencao +
                ", observacao='" + observacao + '\'' +
                ", prospect=" + prospect +
                ", vinculoMatricula='" + vinculoMatricula + '\'' +
                ", codigoAverbacao='" + codigoAverbacao + '\'' +
                ", tipoConvenio='" + tipoConvenio + '\'' +
                ", dataInicioAtendimento=" + dataInicioAtendimento +
                ", dataFimAtendimento=" + dataFimAtendimento +
                ", comissaoPagar='" + comissaoPagar + '\'' +
                ", valorLiberado=" + valorLiberado +
                ", valorParcela=" + valorParcela +
                ", valorMaxOperacao=" + valorMaxOperacao +
                ", margem=" + margem +
                ", margemCinco=" + margemCinco +
                ", margemSecundaria=" + margemSecundaria +
                ", margemSecundariaCinco=" + margemSecundariaCinco +
                ", limite=" + limite +
                ", seguro=" + seguro +
                ", valorSeguroLiberado=" + valorSeguroLiberado +
                ", seguroPrestamista=" + seguroPrestamista +
                ", valorContrato=" + valorContrato +
                ", saldoDevedor=" + saldoDevedor +
                ", valorTotal=" + valorTotal +
                ", risco=" + risco +
                ", valorLiberadoRefinanciamento=" + valorLiberadoRefinanciamento +
                ", valorLiberadoEmprestimo=" + valorLiberadoEmprestimo +
                ", salarioCliente=" + salarioCliente +
                ", valorCartaCredito=" + valorCartaCredito +
                ", taxa=" + taxa +
                ", beneficioPrincipal='" + beneficioPrincipal + '\'' +
                ", beneficioSecundario='" + beneficioSecundario + '\'' +
                ", descontoCompulsorio=" + descontoCompulsorio +
                ", descontoFacultativo=" + descontoFacultativo +
                ", dataVencimento=" + dataVencimento +
                ", quantidadeContratos=" + quantidadeContratos +
                ", quantidadeParcela=" + quantidadeParcela +
                ", instituicaoFinanceira=" + instituicaoFinanceira +
                ", informacoesComplementares='" + informacoesComplementares + '\'' +
                ", codigoLoja='" + codigoLoja + '\'' +
                ", entidadePrincipal='" + entidadePrincipal + '\'' +
                ", entidadeSecundaria='" + entidadeSecundaria + '\'' +
                ", orgaoPrincipal='" + orgaoPrincipal + '\'' +
                ", orgaoSecundario='" + orgaoSecundario + '\'' +
                ", mailingId='" + mailingId + '\'' +
                ", codTabelaRefin=" + codTabelaRefin +
                ", target=" + target +
                ", fluxoEsteira=" + fluxoEsteira +
                ", usuarioOcupado=" + usuarioOcupado +
                ", campanha=" + campanha +
                ", status=" + status +
                ", produto=" + produto +
                ", importacao=" + importacao +
                ", equipe=" + equipe +
                ", empresa=" + empresa +
                ", loja=" + loja +
                ", supervisor=" + supervisor +
                ", coordenador=" + coordenador +
                ", formaPagamento=" + formaPagamento +
                ", contrato=" + contrato +
                ", prioridadeFila=" + prioridadeFila +
                ", tiket='" + tiket + '\'' +
                ", bko=" + bko +
                ", listInformacoesComplementares=" + listInformacoesComplementares +
                ", valorSaque=" + valorSaque +
                ", descricaoSeguro='" + descricaoSeguro + '\'' +
                ", capitalSegurado=" + capitalSegurado +
                ", valorSeguro=" + valorSeguro +
                ", fazerSeguro=" + fazerSeguro +
                ", abrirContaPagamento=" + abrirContaPagamento +
                ", situacaoFuncional=" + situacaoFuncional +
                ", valorComissaoPagar=" + valorComissaoPagar +
                ", quantidadeProposta=" + quantidadeProposta +
                ", equipes=" + equipes +
                ", listStatusAtendimentos=" + listStatusAtendimentos +
                ", statusTelefones=" + statusTelefones +
                ", pausas=" + pausas +
                ", produtos=" + produtos +
                ", formaPagamentos=" + formaPagamentos +
                '}';
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

    public TipoClienteEnum getTipoClienteEnum() {
        return tipoClienteEnum;
    }

    public void setTipoClienteEnum(TipoClienteEnum tipoClienteEnum) {
        this.tipoClienteEnum = tipoClienteEnum;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public SubMotivo getSubMotivo() {
        return subMotivo;
    }

    public void setSubMotivo(SubMotivo subMotivo) {
        this.subMotivo = subMotivo;
    }

    public BigDecimal getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(BigDecimal limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public Boolean getClienteVip() {
        return clienteVip;
    }

    public void setClienteVip(Boolean clienteVip) {
        this.clienteVip = clienteVip;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
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

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public abstract List<Portabilidade> getListPortabilidades();

    public abstract void setListPortabilidades(List<Portabilidade> listPortabilidades);

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEspecie() {

        return especie;
    }

    public Boolean getAtendimentoFinalizado() {
        return atendimentoFinalizado;
    }

    public void setAtendimentoFinalizado(Boolean atendimentoFinalizado) {
        this.atendimentoFinalizado = atendimentoFinalizado;
    }

    public String getRespostaN2() {
        return respostaN2;
    }

    public void setRespostaN2(String respostaN2) {
        this.respostaN2 = respostaN2;
    }

    public String getDataAberturaDemandaFormatada() {

        if (dataAberturaDemanda == null)
            return "";

        return DateUtil.builder(this.dataAberturaDemanda).formatarDataParaString("dd/MM/yyyy").getDataTexto();

    }

    public String getDataPrazoDemandaFormatada() {

        if (prazoPrazoDemanda == null)
            return "";

        return DateUtil.builder(this.prazoPrazoDemanda).formatarDataParaString("dd/MM/yyyy").getDataTexto();

    }

    public String getDataFechamentoDemandaFormatada() {

        if (dataFechamentoDemanda == null)
            return "";

        return DateUtil.builder(this.dataFechamentoDemanda).formatarDataParaString("dd/MM/yyyy").getDataTexto();

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boolean getAtendimentoPai() {
        return atendimentoPai;
    }

    public void setAtendimentoPai(Boolean atendimentoPai) {
        this.atendimentoPai = atendimentoPai;
    }

    public String getProtocoloPai() {
        return protocoloPai;
    }

    public void setProtocoloPai(String protocoloPai) {
        this.protocoloPai = protocoloPai;
    }

    public Departamento getDepartamentoDerivado() {
        return departamentoDerivado;
    }

    public void setDepartamentoDerivado(Departamento departamentoDerivado) {
        this.departamentoDerivado = departamentoDerivado;
    }

    public String getDescricaoCliente() {

        return Optional.ofNullable(cliente)
                .map(c -> String.format("%s | CPF: %s",
                        Optional.ofNullable(c.getNome()).orElse("Nome não informado"),
                        Optional.ofNullable(c.getCpf()).orElse("CPF não informado")))
                .orElse("Não informado");
    }

}

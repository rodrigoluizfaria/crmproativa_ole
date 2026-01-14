package com.proativaservicos.model;

import com.google.common.collect.ComparisonChain;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.EstadoCivilEnum;
import com.proativaservicos.util.constantes.TipoClienteEnum;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "cliente")
public class Cliente extends GenericControle {

    @Column(name = "cpf", nullable = false, length = 15)
    private String cpf;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "cidade_nascimento")
    private String cidadeNascimento;


    @Column(name = "data_emissao_doc")
    private Date dataEmissaoDoc;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "email")
    private String email;


    @Column(name = "estado_civil", length = 20)
    @Enumerated(EnumType.STRING)
    private EstadoCivilEnum estadoCivil;

    @Column(name = "nascionalidade", length = 60)
    private String nascionalidade;


    @Column(name = "nome_conjuge", length = 150)
    private String nomeConjuge;

    @Column(name = "nome_mae", length = 150)
    private String nomeMae;

    @Column(name = "nome_pai", length = 150)
    private String nomePai;

    @Column(name = "numero_doc", length = 30)
    private String numeroDoc;

    @Column(name = "orgao_doc", length = 30)
    private String orgaoDoc;

    @Column(name = "outras_informacoes")
    private String outrasInformacoes;

    @Column(name = "sexo", length = 20)
    private String sexo;

    @Column(name = "uf", length = 30)
    private String uf;

    @Column(name = "uf_documento", length = 30)
    private String ufDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    @Column(name = "salario", precision = 19, scale = 2)
    private BigDecimal salario;

    @Column(name = "tipo_cliente")
    @Enumerated(EnumType.STRING)
    private TipoClienteEnum tipoClienteEnum;


    @Column(name = "ultimo_protocolo", length = 30)
    private String ultimoProtocolo;

    @Transient
    private String protocolo;

    @Transient
    private Boolean clienteVip;


    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "cliente")
    private List<Telefone> listTelefones;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "cliente")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<DadosBancarios> listDadosBancarios;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "cliente")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Endereco> listEnderecos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CartaoCredito> listCartoesCreditos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Email> listEmails;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Atendimento> listAtendimentos;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public Date getDataEmissaoDoc() {
        return dataEmissaoDoc;
    }

    public void setDataEmissaoDoc(Date dataEmissaoDoc) {
        this.dataEmissaoDoc = dataEmissaoDoc;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getDataNascimentoFormatado() {
        if(dataNascimento != null) {
            return DateUtil.builder(this.dataNascimento).formatarDataParaString("ddd/MM/yyyy").getDataTexto();
        }

        return null;
    }


    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoCivilEnum getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNascionalidade() {
        return nascionalidade;
    }

    public void setNascionalidade(String nascionalidade) {
        this.nascionalidade = nascionalidade;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
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

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getOrgaoDoc() {
        return orgaoDoc;
    }

    public void setOrgaoDoc(String orgaoDoc) {
        this.orgaoDoc = orgaoDoc;
    }

    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }

    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUfDocumento() {
        return ufDocumento;
    }

    public void setUfDocumento(String ufDocumento) {
        this.ufDocumento = ufDocumento;
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public List<Telefone> getListTelefones() {
        return listTelefones;
    }

    public void setListTelefones(List<Telefone> listTelefones) {
        this.listTelefones = listTelefones;
    }

    public List<DadosBancarios> getListDadosBancarios() {
        return listDadosBancarios;
    }

    public void setListDadosBancarios(List<DadosBancarios> listDadosBancarios) {
        this.listDadosBancarios = listDadosBancarios;
    }

    public List<Endereco> getListEnderecos() {
        return listEnderecos;
    }

    public void setListEnderecos(List<Endereco> listEnderecos) {
        this.listEnderecos = listEnderecos;
    }

    public List<CartaoCredito> getListCartoesCreditos() {
        return listCartoesCreditos;
    }

    public void setListCartoesCreditos(List<CartaoCredito> listCartoesCreditos) {
        this.listCartoesCreditos = listCartoesCreditos;
    }

    public List<Email> getListEmails() {
        return listEmails;
    }

    public void setListEmails(List<Email> listEmails) {
        this.listEmails = listEmails;
    }

    public List<Atendimento> getListAtendimentos() {
        return listAtendimentos;
    }

    public void setListAtendimentos(List<Atendimento> listAtendimentos) {
        this.listAtendimentos = listAtendimentos;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String retornarTelefonesConcatenados() {

        return retornarTelefonesConcatenados(3L);

    }

    public TipoClienteEnum getTipoClienteEnum() {
        return tipoClienteEnum;
    }

    public void setTipoClienteEnum(TipoClienteEnum tipoClienteEnum) {
        this.tipoClienteEnum = tipoClienteEnum;
    }

    public Boolean getClienteVip() {
        return clienteVip;
    }

    public void setClienteVip(Boolean clienteVip) {
        this.clienteVip = clienteVip;
    }

    public String getUltimoProtocolo() {
        return ultimoProtocolo;
    }

    public void setUltimoProtocolo(String ultimoProtocolo) {
        this.ultimoProtocolo = ultimoProtocolo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }



    public String retornarTelefonesConcatenados(Long quantidade) {

        if (CollectionUtils.isEmpty(listTelefones))
            return "-";

        if (quantidade != null)
            return this.getListaTelefonesOrdenada().stream().limit(quantidade).map(GenericTelefone::getDDDTelefoneFormatado).collect(Collectors.joining(", "));
        else
            return this.getListaTelefonesOrdenada().stream().map(GenericTelefone::getDDDTelefoneFormatado).collect(Collectors.joining(", "));

    }

    public List<? extends GenericTelefone> getListaTelefonesOrdenada() {

        List<Telefone> listaOrdenada = new ArrayList<>(this.listTelefones);

        Collections.sort(listaOrdenada, new Comparator<Telefone>() {

            public int compare(Telefone telefone1, Telefone telefone2) {

                if (telefone1.getId() != null && telefone2.getId() != null) {
                    return ComparisonChain.start().compare(
                                    (telefone1.getStatusTelefone() == null || telefone1.getStatusTelefone().getId() == null)
                                            ? 10
                                            : telefone1.getStatusTelefone().getParametro().getOrdenacao().intValue(),
                                    (telefone2

                                            .getStatusTelefone() == null || telefone2.getStatusTelefone().getId() == null) ? 10
                                            : telefone2.getStatusTelefone().getParametro().getOrdenacao().intValue())
                            .compare(telefone1.getId(), telefone2.getId()).result();
                }

                return ComparisonChain.start().compare(
                                (telefone1.getStatusTelefone() == null || telefone1.getStatusTelefone().getId() == null) ? 10
                                        : telefone1.getStatusTelefone().getParametro().getOrdenacao().intValue(),
                                (telefone2

                                        .getStatusTelefone() == null || telefone2.getStatusTelefone().getId() == null) ? 10
                                        : telefone2.getStatusTelefone().getParametro().getOrdenacao().intValue())
                        .compare(telefone1.getDdd(), telefone2.getDdd())
                        .compare(telefone1.getNumero(), telefone2.getNumero()).result();
            }
        });

        return listaOrdenada;

    }

    public boolean adicionarTelefone(GenericTelefone telefone) {

        if (this.listTelefones == null) {

            this.listTelefones = new ArrayList<>();
        }

        boolean naoExisteTelefone = this.listTelefones.stream()
                .noneMatch(t -> (t.getDdd().shortValue() == telefone.getDdd().shortValue()
                        && t.getNumero().equals(telefone.getNumero())));

        if (naoExisteTelefone) {
            telefone.setCliente(this);
            this.listTelefones.add((Telefone) telefone);
        }

        return naoExisteTelefone;
    }

    public void adicionarEndereco(GenericEndereco endereco) {

        if (CollectionUtils.isEmpty(listEnderecos))
            this.setListEnderecos(new ArrayList<>());

        endereco.setCliente(this);

        this.listEnderecos.add((Endereco) endereco);

    }

    public void addCartao(CartaoCredito cartaoCredito) {

        if (CollectionUtils.isEmpty(listCartoesCreditos))
            this.setListCartoesCreditos(new ArrayList<>());

        cartaoCredito.setCliente(this);
        this.listCartoesCreditos.add(cartaoCredito);
    }

    public String getTelefoneResumido() {

        if (CollectionUtils.isEmpty(listTelefones))
            return null;

        Telefone telefone = listTelefones.get(0);

        return telefone.getDDDTelefoneFormatado();

    }

    public String getPrimeiroTelefone() {

        if (CollectionUtils.isEmpty(listTelefones))
            return null;

        Telefone telefone = listTelefones.get(0);

        return telefone.getDDDTelefone();

    }

    public String getEnderecoResumido() {

        if (CollectionUtils.isEmpty(listEnderecos))
            return null;

        Endereco endereco = listEnderecos.get(0);

        StringBuilder builder = new StringBuilder();
        builder.append(endereco.getLogradouro());

        if (StringUtils.isNotBlank(endereco.getComplemento()))
            builder.append(", ").append(endereco.getComplemento()).append(", ");

        builder.append(endereco.getBairro()).append(" - ").append(endereco.getCidade()).append("/");
        builder.append(endereco.getUf());

        builder.append(endereco.getCep());
        return builder.toString();
    }

    public String getEnderecoCidadeUf() {

        if (CollectionUtils.isEmpty(listEnderecos))
            return null;

        Endereco endereco = listEnderecos.get(0);

        StringBuilder builder = new StringBuilder();


        builder.append(endereco.getCidade()).append(" / ").append(endereco.getUf());


        return builder.toString();
    }

    public CartaoCredito getCartaoCreditoPrincipal() {

        if (CollectionUtils.isEmpty(listCartoesCreditos))
            return null;

        return this.listCartoesCreditos.stream()
                .findFirst()
                .orElseGet(CartaoCredito::new);


    }

    public Integer getIdade() {

        if ( this.dataNascimento != null) {

            return DateUtil.builder(this.dataNascimento).calcularIdade();

        }
        return null;
    }

}

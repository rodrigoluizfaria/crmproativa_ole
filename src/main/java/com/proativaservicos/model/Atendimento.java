package com.proativaservicos.model;

/**
 * Classe Entidade de Atendimento
 *
 * @author rodrigo
 */

import com.google.common.collect.ComparisonChain;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "atendimento")
@SuppressWarnings({"unchecked", "rawtypes"})
public class Atendimento extends GenericAtendimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    private List<Telefone> listTelefones;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<DadosBancarios> listDadosBancarios;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Endereco> listEnderecos;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CartaoCredito> listCartoesCreditos;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Email> listEmails;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<HistoricoAtendimento> listHistoricos;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ContratoEfetivado> listContratosEfetivados;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "atendimento")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Portabilidade> listPortabilidades;

    public Atendimento() {

        setPesoDiscagem(Integer.valueOf(0));

        this.listDadosBancarios = new ArrayList<>();
        this.listTelefones = new ArrayList<Telefone>();

        this.listEnderecos = new ArrayList<>();

        this.listCartoesCreditos = new ArrayList<>();

        this.listEmails = new ArrayList<>();

        this.listHistoricos = new ArrayList<>();
        this.listContratosEfetivados = new ArrayList<>();
    }

    public Atendimento(String nome) {
        super(nome);
    }

    public Atendimento(Long id) {
        super(id);
    }

    public List<HistoricoAtendimento> getListHistoricos() {
        return this.listHistoricos;
    }

    public List<Telefone> getListTelefones() {
        return listTelefones;
    }

    public void adicionarHistorico(GenericHistoricoAtendimento historico) {
        historico.setAtendimento(this);
        this.listHistoricos.add((HistoricoAtendimento) historico);
    }

    @Override
    public void setListHistoricos(List<? extends GenericHistoricoAtendimento> listHistorico) {
        this.listHistoricos = (List<HistoricoAtendimento>) listHistorico;

    }

    @Override
    public boolean adicionarTelefone(GenericTelefone telefone) {

        if (this.listTelefones == null) {

            this.listTelefones = new ArrayList<>();
        }

        boolean naoExisteTelefone = this.listTelefones.stream()
                .noneMatch(t -> (t.getDdd().shortValue() == telefone.getDdd().shortValue()
                        && t.getNumero().equals(telefone.getNumero())));

        if (naoExisteTelefone) {
            telefone.setAtendimento(this);
            this.listTelefones.add((Telefone) telefone);
        }

        return naoExisteTelefone;
    }

    public Telefone retornarTelefone(Long id) {


        if (id == null)
            return null;

        for (Telefone tel : listTelefones) {


            if (tel.getId().equals(id)) {

                return tel;
            }

        }

        return null;
    }


    public String retornarTelefonesConcatenados() {

        return retornarTelefonesConcatenados(3L);

    }

    public String retornarTelefonesConcatenados(Long quantidade) {

        if (CollectionUtils.isEmpty(listTelefones))
            return "-";

        if (quantidade != null)
            return this.getListaTelefonesOrdenada().stream().limit(quantidade).map(GenericTelefone::getDDDTelefoneFormatado).collect(Collectors.joining(", "));
        else
            return this.getListaTelefonesOrdenada().stream().map(GenericTelefone::getDDDTelefoneFormatado).collect(Collectors.joining(", "));

    }


    @Override
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

    @Override
    public List<? extends GenericTelefone> getListaTelefones() {
        // TODO Auto-generated method stub
        return this.listTelefones;
    }

    @Override
    public void setListaTelefones(List<? extends GenericTelefone> listTel) {

        this.listTelefones = (List<Telefone>) listTel;

    }

    @Override
    public List<DadosBancarios> getListaDadosBancarios() {
        // TODO Auto-generated method stub
        return (List<DadosBancarios>) this.listDadosBancarios;
    }

    @Override
    public void setListaDadosBancarios(List<? extends Generic> listDadosBancarios) {

        this.listDadosBancarios = (List<DadosBancarios>) listDadosBancarios;

    }

    @Override
    public void adicionarDadosBancarios(GenericDadosBancarios dadosBancarios) {

        dadosBancarios.setAtendimento(this);
        this.listDadosBancarios.add((DadosBancarios) dadosBancarios);

    }

    @Override
    public List<? extends GenericCartaoCredito> getListaCartoesCredito() {
        // TODO Auto-generated method stub
        return this.listCartoesCreditos;
    }

    @Override
    public void setListaCartoesCredito(List<? extends GenericCartaoCredito> ListCartoes) {

        this.listCartoesCreditos = (List) ListCartoes;

    }

    @Override
    public void adicionarCartoesCredito(GenericCartaoCredito cartaoCredito) {

        cartaoCredito.setAtendimento(this);
        this.listCartoesCreditos.add((CartaoCredito) cartaoCredito);

    }

    @Override
    public List<Email> getListaEmails() {
        // TODO Auto-generated method stub
        return this.listEmails;
    }

    @Override
    public void setListaEmails(List<? extends GenericEmail> listEmails) {
        this.listEmails = (List) listEmails;

    }

    @Override
    public void adicionarEmail(GenericEmail email) {

        email.setAtendimento(this);
        this.listEmails.add((Email) email);

    }

    @Override
    public List<Endereco> getListaEnderecos() {
        // TODO Auto-generated method stub
        return this.listEnderecos;
    }

    @Override
    public void setListaEnderecos(List<? extends GenericEndereco> listEnderecos) {

        this.listEnderecos = (List) listEnderecos;

    }

    @Override
    public void adicionarEndereco(GenericEndereco endereco) {

        endereco.setAtendimento(this);

        this.listEnderecos.add((Endereco) endereco);

    }

    @Override
    public List<? extends GenericContratoEfetivado> getListaContratosEfetivados() {
        // TODO Auto-generated method stub
        return this.listContratosEfetivados;
    }

    @Override
    public void setListaContratosEfetivados(List<? extends GenericContratoEfetivado> listContratos) {
        // TODO Auto-generated method stub
        this.listContratosEfetivados = (List<ContratoEfetivado>) listContratos;

    }

    @Override
    public void adicionarContratoEfetivado(GenericContratoEfetivado contrato) {
        contrato.setAtendimento(this);
        this.listContratosEfetivados.add((ContratoEfetivado) contrato);

    }

    public void addCartao(CartaoCredito cartaoCredito) {
        if (CollectionUtils.isEmpty(listCartoesCreditos))
            this.setListaCartoesCredito(new ArrayList<>());

        cartaoCredito.setAtendimento(this);
        this.listCartoesCreditos.add(cartaoCredito);
    }

    public CartaoCredito getCartaoCreditoPrincipal() {

        if (CollectionUtils.isEmpty(listCartoesCreditos))
            return null;

        return this.listCartoesCreditos.stream()
                .findFirst()
                .orElseGet(CartaoCredito::new);


    }

    public boolean adicionarTelefone(Telefone telefone) {

        if (this.listTelefones == null) {

            this.listTelefones = new ArrayList<>();
        }

        boolean naoExisteTelefone = this.listTelefones.stream()
                .noneMatch(t -> (t.getDdd().equals(telefone.getDdd())) && t.getNumero().equals(telefone.getNumero()));

        if (naoExisteTelefone) {
            telefone.setAtendimento(this);
            this.listTelefones.add(telefone);
        }

        return naoExisteTelefone;

    }

    @Override
    public void adicionarPortabilidade(Portabilidade portabilidade) {

        if (portabilidade == null)
            return;

        portabilidade.setAtendimento(this);

        if (StringUtils.isBlank(portabilidade.getBeneficio()))
            portabilidade.setBeneficio(getBeneficioPrincipal());

        if (CollectionUtils.isEmpty(this.listPortabilidades))
            this.listPortabilidades = new ArrayList<>();

        this.listPortabilidades.add(portabilidade);

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

    @Override
    public List<? extends GenericHistoricoAtendimento> getListaHistoricos() {
        // TODO Auto-generated method stub
        return this.listHistoricos;
    }

    @Override
    public List<Portabilidade> getListPortabilidades() {
        return listPortabilidades;
    }

    @Override
    public void setListPortabilidades(List<Portabilidade> listPortabilidades) {
        this.listPortabilidades = listPortabilidades;
    }
}

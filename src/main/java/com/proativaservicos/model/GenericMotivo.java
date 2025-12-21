package com.proativaservicos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.proativaservicos.util.constantes.TipoMetodosMotivoEnum;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;

import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.validation.constraints.Digits;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class GenericMotivo extends GenericControle {

    private static final long serialVersionUID = 1L;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "acao", length = 30)
    @Enumerated(EnumType.STRING)
    private AcaoStatusAtendimentoEnum acao;


    @Column(name = "ativo", length = 10)
    @Enumerated(EnumType.STRING)
    private TipoAcessoEnum acesso;

    @ManyToOne()
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    @Column(name = "enviar_n2")
    private Boolean envarN2;

    @Column(name = "cor")
    private String cor;


    @Column(name = "tipo_metodos")
    private String tipoMetodosMotivo;

    @Transient
    private List<TipoMetodosMotivoEnum> listTipoMetodosMotivos;

    public GenericMotivo() {

    }

    public GenericMotivo(Long id, String descricao) {
        setId(id);
        this.descricao = descricao;
    }


    public GenericMotivo(String descricao, AcaoStatusAtendimentoEnum acao) {

        this.descricao = descricao;
        this.acao = acao;
    }

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
     * @return the acao
     */
    public AcaoStatusAtendimentoEnum getAcao() {
        return acao;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(AcaoStatusAtendimentoEnum acao) {
        this.acao = acao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoAcessoEnum getAcesso() {
        return acesso;
    }

    public void setAcesso(TipoAcessoEnum acesso) {
        this.acesso = acesso;
    }

    public Boolean getEnvarN2() {
        return envarN2;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setEnvarN2(Boolean envarN2) {
        this.envarN2 = envarN2;
    }

    public String getTipoMetodosMotivo() {
        return tipoMetodosMotivo;
    }

    public void setTipoMetodosMotivo(String tipoMetodosMotivo) {
        this.tipoMetodosMotivo = tipoMetodosMotivo;
    }

    @JsonIgnore
    public void adicionarListMetodo(List<TipoMetodosMotivoEnum> tipoMetodosMotivo) {

        if (CollectionUtils.isEmpty(tipoMetodosMotivo))
            this.tipoMetodosMotivo = null;

        try {

            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            this.tipoMetodosMotivo = gson.toJson(tipoMetodosMotivo);

        }catch (Exception e){

        }

    }

    @JsonIgnore
    public List<TipoMetodosMotivoEnum> getListTipoMetodosMotivos() {

        if (StringUtils.isBlank(tipoMetodosMotivo))
            return null;

        ObjectMapper mapper2 = new ObjectMapper();

        try {

            return mapper2.readValue(tipoMetodosMotivo, new TypeReference<List<TipoMetodosMotivoEnum>>() {
            });

        } catch (Exception e) {
            return null;
        }

    }
}

package com.proativaservicos.model;

import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento extends GenericControle {

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    @Enumerated(EnumType.STRING)
    private TipoAcessoEnum ativo;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "departamento_usuario", joinColumns = { @JoinColumn(name = "departamento") }, inverseJoinColumns = {
            @JoinColumn(name = "usuario") })
    private List<Usuario> listUsuariosDepartamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa")
    private Empresa empresa;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoAcessoEnum getAtivo() {
        return ativo;
    }

    public void setAtivo(TipoAcessoEnum ativo) {
        this.ativo = ativo;
    }

    public List<Usuario> getListUsuariosDepartamento() {
        return listUsuariosDepartamento;
    }

    public void setListUsuariosDepartamento(List<Usuario> listUsuariosDepartamento) {
        this.listUsuariosDepartamento = listUsuariosDepartamento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}

package com.proativaservicos.model;

import com.proativaservicos.util.constantes.TipoStatusAtividadesEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historico_atividade")
public class HistoricoAtividade extends Generic {


    @Column(name = "tipo_status_atividades")
    @Enumerated(EnumType.STRING)
    private TipoStatusAtividadesEnum tipoStatusAtividade;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "detalhes")
    private String detalhes;


    @Column(name = "protocolo")
    private String protocolo;

    @Column(name = "data")
    private Date data;

    @JoinColumn(name = "usuario_cadastro")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JoinColumn(name = "cliente")
    @OneToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Column(name = "icone")
    private String tipoIcone;


    public TipoStatusAtividadesEnum getTipoStatusAtividade() {
        return tipoStatusAtividade;
    }

    public void setTipoStatusAtividade(TipoStatusAtividadesEnum tipoStatusAtividade) {
        this.tipoStatusAtividade = tipoStatusAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoIcone() {
        return tipoIcone;
    }

    public void setTipoIcone(String tipoIcone) {
        this.tipoIcone = tipoIcone;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}

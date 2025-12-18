package com.proativaservicos.model;


import jakarta.persistence.*;

import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;

import java.util.Date;


@Entity
@Table(name = "integracao_ws")
public class IntegracaoWs extends GenericControle {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "integra_empresa_fk"))
    private Empresa empresa;

    @Column(name = "descricao", length = 150)
    private String descricao;

    @Column(name = "url", length = 150)
    private String url;

    @Column(name = "usr", length = 150)
    private String usr;

    @Column(name = "psw", length = 150)
    private String psw;

    @Column(name = "token", length = 150)
    private String token;

    @Column(name = "tipo_integracao", length = 30)
    @Enumerated(EnumType.STRING)
    private TipoIntegracaoEnum tipoIntegracao;

    @Column(name = "ativo")
    @Enumerated(EnumType.STRING)
    private TipoAcessoEnum ativo;

    @Column(name = "limite_consulta")
    private Integer limiteConsulta;

    @Column(name = "cod_loja", length = 150)
    private String codLojaEmpresa;

    @Column(name = "nome_empresa")
    private String nomeEmpresa;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;


    @Column(name = "validade_token")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validadeToken;

    @Column(name = "habilitar_websocket")
    private Boolean habilitarWebsocket;
    @Transient
    private Object servico;

    @Transient
    private String status;

    @Transient
    private Loja loja;

    public IntegracaoWs() {

    }

    public IntegracaoWs(String url,String token,TipoIntegracaoEnum tipoIntegracao) {
        this.url = url;
        this.token = token;
        this.tipoIntegracao = tipoIntegracao;

    }

    public IntegracaoWs(Long id, String url, String usuario, String senha, String nomeEmpresa, TipoIntegracaoEnum tipoIntegracao, Integer limiteConsulta) {

        setId(id);
        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.nomeEmpresa = nomeEmpresa;
        this.tipoIntegracao = tipoIntegracao;
        this.limiteConsulta = limiteConsulta;
    }
    public IntegracaoWs(Long id, String url, String usuario, String senha, String nomeEmpresa, TipoIntegracaoEnum tipoIntegracao, Integer limiteConsulta,String token, Date validadeToken) {

        setId(id);
        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.nomeEmpresa = nomeEmpresa;
        this.tipoIntegracao = tipoIntegracao;
        this.limiteConsulta = limiteConsulta;
        this.token = token;
        this.validadeToken = validadeToken;

    }
    public IntegracaoWs(Long id, String url, String usuario, String senha, String nomeEmpresa, TipoIntegracaoEnum tipoIntegracao, Integer limiteConsulta,String token, Date validadeToken,Boolean habilitarWebsocket) {

        setId(id);
        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.nomeEmpresa = nomeEmpresa;
        this.tipoIntegracao = tipoIntegracao;
        this.limiteConsulta = limiteConsulta;
        this.token = token;
        this.validadeToken = validadeToken;
        this.habilitarWebsocket = habilitarWebsocket;

    }

    public IntegracaoWs(Long id, String descricao, String url, String usuario, String senha, String nomeEmpresa, TipoIntegracaoEnum tipoIntegracao) {

        setId(id);
        this.descricao = descricao;
        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.nomeEmpresa = nomeEmpresa;
        this.tipoIntegracao = tipoIntegracao;

    }

    public IntegracaoWs(Long codigo, String url, String usuario, String senha, String nomeEmpresa, TipoIntegracaoEnum tipoIntegracao, Long codigoLoja, Integer limiteConsulta) {

        setId(codigo);
        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.nomeEmpresa = nomeEmpresa;
        this.tipoIntegracao = tipoIntegracao;
        this.loja = new Loja(codigoLoja);
        this.limiteConsulta = limiteConsulta;
    }

    public IntegracaoWs(Long id, String url, String usuario, String senha, TipoIntegracaoEnum tipoIntegracao, Long codigoEmpresa, String empresaNome) {

        setId(id);
        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.tipoIntegracao = tipoIntegracao;
        this.empresa = new Empresa();
        this.empresa.setId(codigoEmpresa);
        this.empresa.setNome(empresaNome);
    }


    public IntegracaoWs(String url, String usuario, String senha, TipoIntegracaoEnum tipoIntegracao) {


        this.url = url;
        this.usr = usuario;
        this.psw = senha;
        this.tipoIntegracao = tipoIntegracao;

    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public TipoIntegracaoEnum getTipoIntegracao() {
        return tipoIntegracao;
    }

    public void setTipoIntegracao(TipoIntegracaoEnum tipoIntegracao) {
        this.tipoIntegracao = tipoIntegracao;
    }

    public TipoAcessoEnum getAtivo() {
        return ativo;
    }

    public void setAtivo(TipoAcessoEnum ativo) {
        this.ativo = ativo;
    }


    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getLimiteConsulta() {
        return limiteConsulta;
    }

    public void setLimiteConsulta(Integer limiteConsulta) {
        this.limiteConsulta = limiteConsulta;
    }

    public String getCodLojaEmpresa() {
        return codLojaEmpresa;
    }

    public void setCodLojaEmpresa(String codLojaEmpresa) {
        this.codLojaEmpresa = codLojaEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }


    public String getClientId() {
        return clientId;
    }


    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public String getClientSecret() {
        return clientSecret;
    }


    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }


    public Object getServico() {
        return servico;
    }


    public void setServico(Object servico) {
        this.servico = servico;
    }


    public Loja getLoja() {
        return loja;
    }

    public Date getValidadeToken() {
        return validadeToken;
    }

    public void setValidadeToken(Date validadeToken) {
        this.validadeToken = validadeToken;
    }

    public Boolean getHabilitarWebsocket() {
        return habilitarWebsocket;
    }

    public void setHabilitarWebsocket(Boolean habilitarWebsocket) {
        this.habilitarWebsocket = habilitarWebsocket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

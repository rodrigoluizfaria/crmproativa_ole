package com.proativaservicos.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.AcaoCampanhaEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;


@Entity
@Table(name = "status_campanha")
public class StatusCampanha extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column (name = "descricao",length = 150,nullable = false)
	private String descricao;
	
	@Column(name = "acao",length = 30)
	@Enumerated(EnumType.STRING)
	private AcaoCampanhaEnum acao;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum acesso;
	
	@JoinColumn(name = "usuario_cadastro",foreignKey = @ForeignKey (name ="status_campanha_usr_cad_fk"),nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioCadastro;
	
	@JoinColumn(name = "usuario_alteracao",foreignKey = @ForeignKey (name ="status_campanha_usr_alt_fk"),nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioAlteracao;
	
	@Column(name = "data_cadastro",nullable = false)
	private Date dataCadastro;
	
	@Column(name = "data_alteracao")
	private Date dataAlteracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa",foreignKey = @ForeignKey (name ="status_campanha_usr_empresa_fk"),nullable = false)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "status")
	private List<Campanha> listCampanha;
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AcaoCampanhaEnum getAcao() {
		return acao;
	}

	public void setAcao(AcaoCampanhaEnum acao) {
		this.acao = acao;
	}



	public TipoAcessoEnum getAcesso() {
		return acesso;
	}

	public void setAcesso(TipoAcessoEnum acesso) {
		this.acesso = acesso;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	
	public List<Campanha> getListCampanha() {
		return listCampanha;
	}
	
	public void setListCampanha(List<Campanha> listCampanha) {
		this.listCampanha = listCampanha;
	}
	
	@Override
	public String toString() {
		return "StatusCampanha [descricao=" + descricao + ", acao=" + acao + ", ativo=" + acesso + ", empresa=" + empresa
				+ ", getUsuarioCadastro()=" + getUsuarioCadastro() + ", getUsuarioAlteracao()=" + getUsuarioAlteracao()
				+ ", getDataCadastro()=" + getDataCadastro() + ", getDataAlteracao()=" + getDataAlteracao()
				+ ", getId()=" + getId() + "]";
	}

}

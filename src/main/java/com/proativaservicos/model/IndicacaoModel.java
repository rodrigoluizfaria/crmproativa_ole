package com.proativaservicos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.util.constantes.MediaEnum;

@Entity
@Table(name = "indicacao_cliente")
public class IndicacaoModel extends Generic {


	private static final long serialVersionUID = 1L;

	@Column(name = "nome_indicador")
	private String nomeIndicador;
	
	@Column(name = "nome_indicado")
	private String nomeIndicado;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "outro")
	private String outro;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "media")
	private MediaEnum media;
		
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	@JoinColumn(name = "usuario_cadastro")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioCadastro;
		
	@JoinColumn(name = "atendimento")
	@ManyToOne(fetch = FetchType.LAZY)
	private Atendimento atendimento;
	
	@Column(name = "indicado_por_cliente")
	private Boolean indicadoPorCliente;
	
	
	@OneToMany(mappedBy = "indicacao",cascade = { CascadeType.ALL })
	private List<TelefoneIndicacao> listTelefones;


	/**
	 * @return the nomeIndicador
	 */
	public String getNomeIndicador() {
		return nomeIndicador;
	}


	/**
	 * @param nomeIndicador the nomeIndicador to set
	 */
	public void setNomeIndicador(String nomeIndicador) {
		this.nomeIndicador = nomeIndicador;
	}


	/**
	 * @return the nomeIndicado
	 */
	public String getNomeIndicado() {
		return nomeIndicado;
	}


	/**
	 * @param nomeIndicado the nomeIndicado to set
	 */
	public void setNomeIndicado(String nomeIndicado) {
		this.nomeIndicado = nomeIndicado;
	}


	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}


	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	/**
	 * @return the media
	 */
	public MediaEnum getMedia() {
		return media;
	}


	/**
	 * @param media the media to set
	 */
	public void setMedia(MediaEnum media) {
		this.media = media;
	}


	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}


	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	/**
	 * @return the usuarioCadastro
	 */
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}


	/**
	 * @param usuarioCadastro the usuarioCadastro to set
	 */
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
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
	 * @return the indicadoPorCliente
	 */
	public Boolean getIndicadoPorCliente() {
		return indicadoPorCliente;
	}


	/**
	 * @param indicadoPorCliente the indicadoPorCliente to set
	 */
	public void setIndicadoPorCliente(Boolean indicadoPorCliente) {
		this.indicadoPorCliente = indicadoPorCliente;
	}


	/**
	 * @return the listTelefones
	 */
	public List<TelefoneIndicacao> getListTelefones() {
		return listTelefones;
	}

	public String getOutro() {
		return outro;
	}
	
	public void setOutro(String outro) {
		this.outro = outro;
	}

	/**
	 * @param listTelefones the listTelefones to set
	 */
	public void setListTelefones(List<TelefoneIndicacao> listTelefones) {
		this.listTelefones = listTelefones;
	}


	public void addTelefones(TelefoneIndicacao telefoneIndicacao) {
		
		if(telefoneIndicacao==null || telefoneIndicacao.getDdd() == null || StringUtils.isEmpty(telefoneIndicacao.getNumero()))
			return;
		
	//	telefoneIndicacao.setIndicacao(this);
		
		if (CollectionUtils.isEmpty(this.listTelefones)) {
			
			this.listTelefones = new ArrayList<TelefoneIndicacao>();
			this.listTelefones.add(telefoneIndicacao);

		} else {

			boolean contem = this.listTelefones.stream().anyMatch(t -> (t.getDdd().equals(telefoneIndicacao.getDdd())	&& t.getNumero().equals(telefoneIndicacao.getNumero())));
			
			if (!contem)
				this.listTelefones.add(telefoneIndicacao);
		}

	}
	
	
	
	
}

package com.proativaservicos.service.api;
import java.util.Date;
import java.util.List;

import com.google.common.base.MoreObjects;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import com.proativaservicos.util.DateUtil;

public class DocImportacaoDiscador {

	private ObjectId id;
	 
	 @BsonProperty(value = "campanha")
	private String campanha;
	
	 @BsonProperty(value = "id_campanha")
	private Integer idCampanha;

	@BsonProperty(value = "id_importacao")
	private Integer idImportacao;
	
	 @BsonProperty(value = "arquivo")
	private String arquivo;
	
	 @BsonProperty(value = "quantidade_total_lote")
	private Integer quantidadeTotalLote;
	
	 @BsonProperty(value = "lote_atual")
	private Integer loteAtual;
	
	 @BsonProperty(value = "total_atendimento_lote_atual")
	private Integer totalAtendimentoLoteAtual;

	@BsonProperty(value = "total_atendimento_consultado")
	private Integer totalAtendimentoConsultado;

	@BsonProperty(value = "lista_atendimentos_erro")
	private List<Integer> listaAtendimentosErro;
	
	 @BsonProperty(value = "total_atendimento")
	private Integer totalAtendimento;
	
	 @BsonProperty(value = "status_importacao")
	private String statusImportacao;


	 @BsonProperty(value = "erro_envio_lote")
	private Boolean erro;

	 @BsonProperty(value = "atendimentos_erro")
	private List<String> atendimentosErro;
	 
	 @BsonProperty(value = "date")
	 private Date data;
	 
	 @BsonProperty(value = "observacao")
	 private String observacao;
	 
	 @BsonProperty(value = "enviado")
	 private Integer enviado;
	 
	 @BsonProperty(value = "nao_enviado")
	 private Integer naoEnviado;
	 
	 @BsonIgnore
	 private String tempo;
	 
	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the campanha
	 */
	public String getCampanha() {
		return campanha;
	}

	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	/**
	 * @return the idCampanha
	 */
	public Integer getIdCampanha() {
		return idCampanha;
	}

	/**
	 * @param idCampanha the idCampanha to set
	 */
	public void setIdCampanha(Integer idCampanha) {
		this.idCampanha = idCampanha;
	}

	/**
	 * @return the arquivo
	 */
	public String getArquivo() {
		return arquivo;
	}

	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * @return the quantidadeTotalLote
	 */
	public Integer getQuantidadeTotalLote() {
		return quantidadeTotalLote;
	}

	/**
	 * @param quantidadeTotalLote the quantidadeTotalLote to set
	 */
	public void setQuantidadeTotalLote(Integer quantidadeTotalLote) {
		this.quantidadeTotalLote = quantidadeTotalLote;
	}

	/**
	 * @return the loteAtual
	 */
	public Integer getLoteAtual() {
		return loteAtual;
	}

	/**
	 * @param loteAtual the loteAtual to set
	 */
	public void setLoteAtual(Integer loteAtual) {
		this.loteAtual = loteAtual;
	}

	/**
	 * @return the totalAtendimentoLoteAtual
	 */
	public Integer getTotalAtendimentoLoteAtual() {
		return totalAtendimentoLoteAtual;
	}

	/**
	 * @param totalAtendimentoLoteAtual the totalAtendimentoLoteAtual to set
	 */
	public void setTotalAtendimentoLoteAtual(Integer totalAtendimentoLoteAtual) {
		this.totalAtendimentoLoteAtual = totalAtendimentoLoteAtual;
	}

	/**
	 * @return the totalAtendimento
	 */
	public Integer getTotalAtendimento() {
		return totalAtendimento;
	}

	/**
	 * @param totalAtendimento the totalAtendimento to set
	 */
	public void setTotalAtendimento(Integer totalAtendimento) {
		this.totalAtendimento = totalAtendimento;
	}

	/**
	 * @return the statusImportacao
	 */
	public String getStatusImportacao() {
		return statusImportacao;
	}

	/**
	 * @param statusImportacao the statusImportacao to set
	 */
	public void setStatusImportacao(String statusImportacao) {
		this.statusImportacao = statusImportacao;
	}

	/**
	 * @return the erro
	 */
	public Boolean getErro() {
		return erro;
	}

	/**
	 * @param erro the erro to set
	 */
	public void setErro(Boolean erro) {
		this.erro = erro;
	}

	/**
	 * @return the atendimentosErro
	 */
	public List<String> getAtendimentosErro() {
		return atendimentosErro;
	}

	/**
	 * @param atendimentosErro the atendimentosErro to set
	 */
	public void setAtendimentosErro(List<String> atendimentosErro) {
		this.atendimentosErro = atendimentosErro;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	


	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getTempo() {
		
		if(this.data!=null)
			return DateUtil.builder(this.data, new Date()).retornarDiferencaEntreDatas().getDataTexto();
		
		return tempo;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the enviado
	 */
	public Integer getEnviado() {
		return enviado;
	}

	/**
	 * @param enviado the enviado to set
	 */
	public void setEnviado(Integer enviado) {
		this.enviado = enviado;
	}

	/**
	 * @return the naoEnviado
	 */
	public Integer getNaoEnviado() {
		return naoEnviado;
	}

	/**
	 * @param naoEnviado the naoEnviado to set
	 */
	public void setNaoEnviado(Integer naoEnviado) {
		this.naoEnviado = naoEnviado;
	}

	public Integer getIdImportacao() {
		return idImportacao;
	}

	public void setIdImportacao(Integer idImportacao) {
		this.idImportacao = idImportacao;
	}

	public Integer getTotalAtendimentoConsultado() {
		return totalAtendimentoConsultado;
	}

	public void setTotalAtendimentoConsultado(Integer totalAtendimentoConsultado) {
		this.totalAtendimentoConsultado = totalAtendimentoConsultado;
	}

	public List<Integer> getListaAtendimentosErro() {
		return listaAtendimentosErro;
	}

	public void setListaAtendimentosErro(List<Integer> listaAtendimentosErro) {
		this.listaAtendimentosErro = listaAtendimentosErro;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("campanha", campanha)
				.add("idCampanha", idCampanha)
				.add("idImportacao", idImportacao)
				.add("arquivo", arquivo)
				.add("quantidadeTotalLote", quantidadeTotalLote)
				.add("loteAtual", loteAtual)
				.add("totalAtendimentoLoteAtual", totalAtendimentoLoteAtual)
				.add("totalAtendimentoConsultado", totalAtendimentoConsultado)
				.add("listaAtendimentosErro", listaAtendimentosErro)
				.add("totalAtendimento", totalAtendimento)
				.add("statusImportacao", statusImportacao)
				.add("erro", erro)
				.add("atendimentosErro", atendimentosErro)
				.add("data", data)
				.add("observacao", observacao)
				.add("enviado", enviado)
				.add("naoEnviado", naoEnviado)
				.add("tempo", tempo)
				.toString();
	}
}

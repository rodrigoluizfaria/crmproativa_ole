package com.proativaservicos.service.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DocImportacao {


    private ObjectId id;

    @BsonProperty(value = "campanha")
    private String campanha;

    @BsonProperty(value = "id_campanha")
    private Integer idCampanha;

    @BsonProperty(value = "id_importacao")
    private Integer idImportacao;

    @BsonProperty(value = "arquivo")
    private String arquivo;

    @BsonProperty(value = "total_linhas")
    private Integer totalLinhas;

    @BsonProperty(value = "status")
    private String status;

    @BsonProperty(value = "total_atendimentos")
    private Integer totalAtendimentos;

    @BsonProperty(value = "clientes_blacklist")
    private List<String> clientesBlackList;


    @BsonProperty(value = "observacao")
    private String observacao;

    @BsonProperty(value = "data_inicio")
    private Date dataInicio;


    @BsonProperty(value = "data_fim")
    private Date dataFim;

    @BsonProperty(value = "finalizou")
    private Boolean finalizou;

    @BsonProperty(value = "tipo_consulta")
    private String tipoConsulta;

    @BsonProperty(value = "erro")
    private String erro;

    @Deprecated
    @BsonProperty(value = "total_atendimentos_consultados")
    private Integer totalAtendimentosConsultados;

    @BsonProperty(value = "processando")
    private Integer processando;

    @BsonProperty(value = "quantidade_consultado")
    private Integer quantidadeConsultado;
    @BsonProperty(value = "quantidade_reconsulta")
    private Integer quantidadeReconsulta;


    @BsonProperty(value = "atendimentos_erro")
    private List<Integer> atendimentosErro;

    @BsonProperty(value = "lote")
    private Integer lote;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCampanha() {
        return campanha;
    }

    public void setCampanha(String campanha) {
        this.campanha = campanha;
    }

    public Integer getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Integer idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Integer getIdImportacao() {
        return idImportacao;
    }

    public void setIdImportacao(Integer idImportacao) {
        this.idImportacao = idImportacao;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getTotalLinhas() {
        return totalLinhas;
    }

    public void setTotalLinhas(Integer totalLinhas) {
        this.totalLinhas = totalLinhas;
    }

    public String getStatus() {
        return status;
    }

    public boolean isImportacaoConsulta() {

       if(StringUtils.isBlank(status))
           return false;



        return status.equalsIgnoreCase("IMPORTANDO_INTEGRACAO");
    }

    public boolean isImportacaoDisco() {

        if(StringUtils.isBlank(status))
            return false;



        return status.equalsIgnoreCase("IMPORTANDO");
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalAtendimentos() {
        return totalAtendimentos;
    }

    public void setTotalAtendimentos(Integer totalAtendimentos) {
        this.totalAtendimentos = totalAtendimentos;
    }

    public List<String> getClientesBlackList() {
        return clientesBlackList;
    }

    public void setClientesBlackList(List<String> clientesBlackList) {
        this.clientesBlackList = clientesBlackList;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean getFinalizou() {
        return finalizou;
    }

    public void setFinalizou(Boolean finalizou) {
        this.finalizou = finalizou;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Integer getTotalAtendimentosConsultados() {
        return totalAtendimentosConsultados;
    }

    public void setTotalAtendimentosConsultados(Integer totalAtendimentosConsultados) {
        this.totalAtendimentosConsultados = totalAtendimentosConsultados;
    }

    public Integer getProcessando() {
        return processando;
    }

    public void setProcessando(Integer processando) {
        this.processando = processando;
    }

    public Integer getQuantidadeConsultado() {
        return quantidadeConsultado;
    }

    public void setQuantidadeConsultado(Integer quantidadeConsultado) {
        this.quantidadeConsultado = quantidadeConsultado;
    }

    public List<Integer> getAtendimentosErro() {
        return atendimentosErro;
    }

    public void setAtendimentosErro(List<Integer> atendimentosErro) {
        this.atendimentosErro = atendimentosErro;
    }

    public String getDataInicioFormatada() {

        return dataFormatada(this.dataInicio);

    }

    public String getDataFimFormatada() {

        return dataFormatada(this.dataFim);

    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Integer getQuantidadeReconsulta() {
        return quantidadeReconsulta;
    }

    public void setQuantidadeReconsulta(Integer quantidadeReconsulta) {
        this.quantidadeReconsulta = quantidadeReconsulta;
    }

    private String dataFormatada(Date data) {
        if (data == null)
            return null;

        Instant instant = data.toInstant();
        ZoneId zonaBrasil = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime dataConvertida = instant.atZone(zonaBrasil);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(dataConvertida);


    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("campanha", campanha)
                .append("idCampanha", idCampanha)
                .append("idImportacao", idImportacao)
                .append("arquivo", arquivo)
                .append("totalLinhas", totalLinhas)
                .append("status", status)
                .append("totalAtendimentos", totalAtendimentos)
                .append("clientesBlackList", clientesBlackList)
                .append("observacao", observacao)
                .append("dataInicio", dataInicio)
                .append("dataFim", dataFim)
                .append("finalizou", finalizou)
                .append("tipoConsulta", tipoConsulta)
                .append("totalAtendimentosConsultados", totalAtendimentosConsultados)
                .append("processando", processando)
                .append("quantidadeConsultado", quantidadeConsultado)
                .append("atendimentosErro", atendimentosErro)
                .toString();
    }



}

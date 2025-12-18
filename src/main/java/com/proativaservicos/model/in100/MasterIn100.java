package com.proativaservicos.model.in100;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proativaservicos.model.pan.DateDeserializer;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.HttpPostUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class MasterIn100 {

    private String cpf;
    private String numeroMatricula;
    private Date dataNascimento;
    private String nomeBeneficiario;
    private Integer situacaoBeneficio;

    private Integer especieBeneficio;

    private Boolean concessaoJudicial;
    private Date dataCessacaoBeneficio;
    private String ufPagamento;
    private String tipoCredito;
    private Integer cbcIfPagadora;
    private Integer agenciaPagadora;
    private String contaCorrente;
    private Integer pensaoAlimenticia;
    private Boolean possuiRepresentanteLegal;
    private Boolean possuiProcurador;
    private Boolean possuiEntidadeRepresentacao;

    private Boolean bloqueadoParaEmprestimo;

    private Date dataUltimaPericia;

    private Date dataDespachoBeneficio;

    private Double margemDisponivel;

    private Double margemDisponivelCartao;
    private Double valorLimiteCartao;

    private Integer qtdEmprestimosAtivosSuspensos;

    private Date dataConsulta;

    private String cpfRepresentanteLegal;

    private String nomeRepresentanteLegal;

    private Date dataFimRepresentanteLegal;

    private Boolean elegivelEmprestimo;

    private String observacao;

    private Double margemDisponivelRcc;

    private Double valorLimiteRcc;

    private String status;

    private String message;

    public MasterIn100(String cpf, String numeroMatricula) {
        this.cpf = cpf;
        this.numeroMatricula = numeroMatricula;
    }

    public MasterIn100() {

    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {


        this.dataNascimento = dataNascimento;
    }


    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public Integer getSituacaoBeneficio() {
        return situacaoBeneficio;
    }

    public void setSituacaoBeneficio(Integer situacaoBeneficio) {
        this.situacaoBeneficio = situacaoBeneficio;
    }

    public Integer getEspecieBeneficio() {
        return especieBeneficio;
    }

    public void setEspecieBeneficio(Integer especieBeneficio) {
        this.especieBeneficio = especieBeneficio;
    }

    public Boolean getConcessaoJudicial() {
        return concessaoJudicial;
    }

    public void setConcessaoJudicial(Boolean concessaoJudicial) {
        this.concessaoJudicial = concessaoJudicial;
    }

    public Date getDataCessacaoBeneficio() {
        return dataCessacaoBeneficio;
    }

    public void setDataCessacaoBeneficio(Date dataCessacaoBeneficio) {
        this.dataCessacaoBeneficio = dataCessacaoBeneficio;
    }

    public String getUfPagamento() {
        return ufPagamento;
    }

    public void setUfPagamento(String ufPagamento) {
        this.ufPagamento = ufPagamento;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public Integer getCbcIfPagadora() {
        return cbcIfPagadora;
    }

    public void setCbcIfPagadora(Integer cbcIfPagadora) {
        this.cbcIfPagadora = cbcIfPagadora;
    }

    public Integer getAgenciaPagadora() {
        return agenciaPagadora;
    }

    public void setAgenciaPagadora(Integer agenciaPagadora) {
        this.agenciaPagadora = agenciaPagadora;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public Integer getPensaoAlimenticia() {
        return pensaoAlimenticia;
    }

    public void setPensaoAlimenticia(Integer pensaoAlimenticia) {
        this.pensaoAlimenticia = pensaoAlimenticia;
    }

    public Boolean getPossuiRepresentanteLegal() {
        return possuiRepresentanteLegal;
    }

    public void setPossuiRepresentanteLegal(Boolean possuiRepresentanteLegal) {
        this.possuiRepresentanteLegal = possuiRepresentanteLegal;
    }

    public Boolean getPossuiProcurador() {
        return possuiProcurador;
    }

    public void setPossuiProcurador(Boolean possuiProcurador) {
        this.possuiProcurador = possuiProcurador;
    }

    public Boolean getPossuiEntidadeRepresentacao() {
        return possuiEntidadeRepresentacao;
    }

    public void setPossuiEntidadeRepresentacao(Boolean possuiEntidadeRepresentacao) {
        this.possuiEntidadeRepresentacao = possuiEntidadeRepresentacao;
    }

    public Boolean getBloqueadoParaEmprestimo() {
        return bloqueadoParaEmprestimo;
    }

    public void setBloqueadoParaEmprestimo(Boolean bloqueadoParaEmprestimo) {
        this.bloqueadoParaEmprestimo = bloqueadoParaEmprestimo;
    }

    public Date getDataUltimaPericia() {
        return dataUltimaPericia;
    }

    public void setDataUltimaPericia(Date dataUltimaPericia) {
        this.dataUltimaPericia = dataUltimaPericia;
    }

    public Date getDataDespachoBeneficio() {
        return dataDespachoBeneficio;
    }

    public void setDataDespachoBeneficio(Date dataDespachoBeneficio) {
        this.dataDespachoBeneficio = dataDespachoBeneficio;
    }

    public Double getMargemDisponivel() {
        return margemDisponivel;
    }

    public void setMargemDisponivel(Double margemDisponivel) {
        this.margemDisponivel = margemDisponivel;
    }

    public Double getMargemDisponivelCartao() {
        return margemDisponivelCartao;
    }

    public void setMargemDisponivelCartao(Double margemDisponivelCartao) {
        this.margemDisponivelCartao = margemDisponivelCartao;
    }

    public Double getValorLimiteCartao() {
        return valorLimiteCartao;
    }

    public void setValorLimiteCartao(Double valorLimiteCartao) {
        this.valorLimiteCartao = valorLimiteCartao;
    }

    public Integer getQtdEmprestimosAtivosSuspensos() {
        return qtdEmprestimosAtivosSuspensos;
    }

    public void setQtdEmprestimosAtivosSuspensos(Integer qtdEmprestimosAtivosSuspensos) {
        this.qtdEmprestimosAtivosSuspensos = qtdEmprestimosAtivosSuspensos;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getCpfRepresentanteLegal() {
        return cpfRepresentanteLegal;
    }

    public void setCpfRepresentanteLegal(String cpfRepresentanteLegal) {
        this.cpfRepresentanteLegal = cpfRepresentanteLegal;
    }

    public String getNomeRepresentanteLegal() {
        return nomeRepresentanteLegal;
    }

    public void setNomeRepresentanteLegal(String nomeRepresentanteLegal) {
        this.nomeRepresentanteLegal = nomeRepresentanteLegal;
    }

    public Date getDataFimRepresentanteLegal() {
        return dataFimRepresentanteLegal;
    }

    public void setDataFimRepresentanteLegal(Date dataFimRepresentanteLegal) {
        this.dataFimRepresentanteLegal = dataFimRepresentanteLegal;
    }

    public Boolean getElegivelEmprestimo() {
        return elegivelEmprestimo;
    }

    public void setElegivelEmprestimo(Boolean elegivelEmprestimo) {
        this.elegivelEmprestimo = elegivelEmprestimo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getMargemDisponivelRcc() {
        return margemDisponivelRcc;
    }

    public void setMargemDisponivelRcc(Double margemDisponivelRcc) {
        this.margemDisponivelRcc = margemDisponivelRcc;
    }

    public Double getValorLimiteRcc() {
        return valorLimiteRcc;
    }

    public void setValorLimiteRcc(Double valorLimiteRcc) {
        this.valorLimiteRcc = valorLimiteRcc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MasterIn100{" +
                "cpf='" + cpf + '\'' +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nomeBeneficiario='" + nomeBeneficiario + '\'' +
                ", situacaoBeneficio=" + situacaoBeneficio +
                ", especieBeneficio=" + especieBeneficio +
                ", concessaoJudicial=" + concessaoJudicial +
                ", dataCessacaoBeneficio=" + dataCessacaoBeneficio +
                ", ufPagamento='" + ufPagamento + '\'' +
                ", tipoCredito='" + tipoCredito + '\'' +
                ", cbcIfPagadora=" + cbcIfPagadora +
                ", agenciaPagadora=" + agenciaPagadora +
                ", contaCorrente='" + contaCorrente + '\'' +
                ", pensaoAlimenticia=" + pensaoAlimenticia +
                ", possuiRepresentanteLegal=" + possuiRepresentanteLegal +
                ", possuiProcurador=" + possuiProcurador +
                ", possuiEntidadeRepresentacao=" + possuiEntidadeRepresentacao +
                ", bloqueadoParaEmprestimo=" + bloqueadoParaEmprestimo +
                ", dataUltimaPericia=" + dataUltimaPericia +
                ", dataDespachoBeneficio=" + dataDespachoBeneficio +
                ", margemDisponivel=" + margemDisponivel +
                ", margemDisponivelCartao=" + margemDisponivelCartao +
                ", valorLimiteCartao=" + valorLimiteCartao +
                ", qtdEmprestimosAtivosSuspensos=" + qtdEmprestimosAtivosSuspensos +
                ", dataConsulta=" + dataConsulta +
                ", cpfRepresentanteLegal='" + cpfRepresentanteLegal + '\'' +
                ", nomeRepresentanteLegal='" + nomeRepresentanteLegal + '\'' +
                ", dataFimRepresentanteLegal='" + dataFimRepresentanteLegal + '\'' +
                ", elegivelEmprestimo=" + elegivelEmprestimo +
                ", observacao='" + observacao + '\'' +
                ", margemDisponivelRcc=" + margemDisponivelRcc +
                ", valorLimiteRcc=" + valorLimiteRcc +
                '}';
    }

    public String toJason() {

        return new Gson().toJson(this);

    }

    public String getHeader(){

      return "cpf;nomeBeneficiario;" +
              "dataNascimento;" +
              "valorLimiteRcc;" +
              "margemDisponivelRcc;" +
              "margemDisponivel;" +
              "qtdEmprestimosAtivosSuspensos;" +
              "agenciaPagadora;" +
              "ufPagamento;" +
              "tipoCredito;" +
              "bloqueadoParaEmprestimo;" +
              "qtdEmprestimosAtivosSuspensos;" +
              "cbcIfPagadora;" +
              "concessaoJudicial;" +
              "contaCorrente;" +
              "cpfRepresentanteLegal;" +
              "elegivelEmprestimo;" +
              "especieBeneficio;" +
              "margemDisponivelCartao;" +
              "pensaoAlimenticia;" +
              "possuiEntidadeRepresentacao;" +
              "possuiProcurador;" +
              "possuiRepresentanteLegal;" +
              "observacao;" +
              "valorLimiteCartao;" +
              "situacaoBeneficio;" +
              "dataCessacaoBeneficio;" +
              "dataFimRepresentanteLegal;" +
              "dataUltimaPericia;" +
              "dataConsulta;" +
              "dataDespachoBeneficio;";
    }

    public String validandoValoresBoolean(Boolean val){
        if(val==null)
            return "";
        return val?"SIM" : "NÃO";

    }


    public String toCsvStr() {

        StringBuilder builder = new StringBuilder();
        builder.append(this.cpf + ";");
        builder.append(this.nomeBeneficiario + ";");
        builder.append(DateUtil.builder(this.dataNascimento).formatarDataParaString("dd/MM/yyyy").getDataTexto() + ";");
        builder.append(this.valorLimiteRcc + ";");
        builder.append(this.margemDisponivelRcc + ";");
        builder.append(this.margemDisponivel + ";");
        builder.append(this.qtdEmprestimosAtivosSuspensos + ";");
        builder.append(this.agenciaPagadora + ";");
        builder.append(this.ufPagamento + ";");
        builder.append(this.tipoCredito + ";");
        builder.append(validandoValoresBoolean(this.bloqueadoParaEmprestimo  )+ ";");
        builder.append(this.qtdEmprestimosAtivosSuspensos + ";");
        builder.append(this.cbcIfPagadora + ";");
        builder.append(validandoValoresBoolean(this.concessaoJudicial  )+ ";");
        builder.append(this.contaCorrente + ";");
        builder.append(this.cpfRepresentanteLegal + ";");
        builder.append(validandoValoresBoolean(this.elegivelEmprestimo )+ ";");
        builder.append(this.especieBeneficio + ";");
        builder.append(this.margemDisponivelCartao + ";");
        builder.append(this.pensaoAlimenticia + ";");
        builder.append(validandoValoresBoolean(this.possuiEntidadeRepresentacao)+ ";");
        builder.append(validandoValoresBoolean(this.possuiProcurador  )+ ";");
        builder.append(validandoValoresBoolean(this.possuiRepresentanteLegal ) + ";");
        builder.append(this.observacao + ";");
        builder.append(this.valorLimiteCartao + ";");
        builder.append(this.situacaoBeneficio + ";");
        builder.append(DateUtil.builder(this.dataCessacaoBeneficio).formatarDataParaString("dd/MM/yyyy").getDataTexto() + ";");
        builder.append(DateUtil.builder(this.dataFimRepresentanteLegal).formatarDataParaString("dd/MM/yyyy").getDataTexto() + ";");
        builder.append(DateUtil.builder(this.dataUltimaPericia).formatarDataParaString("dd/MM/yyyy").getDataTexto() + ";");
        builder.append(DateUtil.builder(this.dataConsulta).formatarDataParaString("dd/MM/yyyy").getDataTexto() + ";");
        builder.append(DateUtil.builder(this.dataDespachoBeneficio).formatarDataParaString("dd/MM/yyyy").getDataTexto() + ";");

        return builder.toString();

    }

    public static void main(String[] args) {

        MasterIn100 ma = new MasterIn100();
        ma.setCpf("06726506657");
        ma.setAgenciaPagadora(12);
        ma.setCbcIfPagadora(2);
        ma.setValorLimiteCartao(0.54D);
        ma.setContaCorrente("55441");
        ma.setDataCessacaoBeneficio(new Date());
        ma.setPossuiProcurador(true);
        System.out.println(ma.toJason());

        String str = "{\n" +
                "    \"cpf\": \"13025082534\",\n" +
                "    \"numeroMatricula\": \"1371679212\",\n" +
                "    \"dataNascimento\": \"1958-12-17T00:00:00\",\n" +
                "    \"nomeBeneficiario\": \"FLORACY BATISTA TEIXEIRA\",\n" +
                "    \"situacaoBeneficio\": 0,\n" +
                "    \"especieBeneficio\": 21,\n" +
                "    \"concessaoJudicial\": false,\n" +
                "    \"dataCessacaoBeneficio\": \"0001-01-01T00:00:00\",\n" +
                "    \"ufPagamento\": \"GO\",\n" +
                "    \"tipoCredito\": \"2\",\n" +
                "    \"cbcIfPagadora\": 104,\n" +
                "    \"agenciaPagadora\": 2234,\n" +
                "    \"contaCorrente\": \"7785365432\",\n" +
                "    \"pensaoAlimenticia\": 0,\n" +
                "    \"possuiRepresentanteLegal\": false,\n" +
                "    \"possuiProcurador\": false,\n" +
                "    \"possuiEntidadeRepresentacao\": \"Não\",\n" +
                "    \"bloqueadoParaEmprestimo\": false,\n" +
                "    \"dataUltimaPericia\": \"0001-01-01T00:00:00\",\n" +
                "    \"dataDespachoBeneficio\": \"2007-04-30T00:00:00\",\n" +
                "    \"margemDisponivel\": 57.28,\n" +
                "    \"margemDisponivelCartao\": 0,\n" +
                "    \"valorLimiteCartao\": 10017.77,\n" +
                "    \"qtdEmprestimosAtivosSuspensos\": 7,\n" +
                "    \"dataConsulta\": \"2023-08-21T00:00:00\",\n" +
                "    \"cpfRepresentanteLegal\": \"\",\n" +
                "    \"nomeRepresentanteLegal\": \"\",\n" +
                "    \"dataFimRepresentanteLegal\": \"0001-01-01T00:00:00\",\n" +
                "    \"elegivelEmprestimo\": true,\n" +
                "    \"observacao\": \"\",\n" +
                "    \"margemDisponivelRcc\": 0,\n" +
                "    \"valorLimiteRcc\": 10017.78\n" +
                "}";

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());

        MasterIn100 ma2 = gsonBuilder.create().fromJson(str, MasterIn100.class);
        System.out.println(ma2.toCsvStr());
        JSONObject jso = new JSONObject();
        jso.put("username", "lev_in100_2");
        jso.put("password", "3X455?0>4ZZHVj[T<r|m");
        jso.put("cpf", "5472493811");
        jso.put("matricula", "7015913500");
        jso.put("api_key", "3a104afb999ac0cf2b3a559aad575556");


    }

}

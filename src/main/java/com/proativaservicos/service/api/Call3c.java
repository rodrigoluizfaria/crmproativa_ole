package com.proativaservicos.service.api;

import com.proativaservicos.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Objects;

public class Call3c {


    private ObjectId id;

    @BsonProperty(value = "identifier")
    private String identifier;

    @BsonProperty(value = "call_id")
    private String callId;

    @BsonProperty(value = "number")
    private String number;

    @BsonProperty(value = "call_date")
    private String callDate;

    @BsonProperty(value = "status")
    private Integer status;

    @BsonProperty(value = "fila")
    private Integer fila;

    @BsonProperty(value = "data_discagem")
    private String dataDiscagem;

    @BsonProperty(value = "sip")
    private String sip;

    @BsonProperty(value = "call_mode")
    private String callMode;

    @BsonProperty(value = "processado")
    private Boolean processado;

    @BsonProperty(value = "speaking_time")
    private Integer speakingTime;


    @BsonProperty(value = "date_create")
    private Date dateCreate;
    @BsonProperty(value = "list_id")
    private Integer listId;

    @BsonProperty(value = "hangup_cause")
    private Integer hangupCause;
    @BsonProperty(value = "hangup_text")
    private String hangupText;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public String getDataDiscagem() {
        return dataDiscagem;
    }

    public void setDataDiscagem(String dataDiscagem) {
        this.dataDiscagem = dataDiscagem;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getCallMode() {
        return callMode;
    }

    public void setCallMode(String callMode) {
        this.callMode = callMode;
    }

    public Boolean getProcessado() {
        return processado;
    }

    public void setProcessado(Boolean processado) {
        this.processado = processado;
    }

    public Date getCalldateIso() {

        if (StringUtils.isNotBlank(this.callDate)) {
            return DateUtil.builder(this.callDate).formatarDataParaISO8601().getData();
        }
        return null;
    }

    public Integer getSpeakingTime() {

        return speakingTime;
    }
    public String getSpeakingTimeString(){

        if(speakingTime==null)
            return null;

        return String.valueOf(this.speakingTime);

    }

    public void setSpeakingTime(Integer speakingTime) {
        this.speakingTime = speakingTime;
    }


    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(Integer hangupCause) {
        this.hangupCause = hangupCause;
    }

    public String getHangupText() {
        return hangupText;
    }

    public void setHangupText(String hangupText) {
        this.hangupText = hangupText;
    }

    public String getNumeroFormatado(){

        if(StringUtils.isNotBlank(this.number)){
            return this.number.substring(2);
        }


        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call3c call3c = (Call3c) o;
        return Objects.equals(id, call3c.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("identifier", identifier)
                .append("callId", callId)
                .append("number", number)
                .append("callDate", callDate)
                .append("status", status)
                .append("fila", fila)
                .append("dataDiscagem", dataDiscagem)
                .append("sip", sip)
                .append("callMode", callMode)
                .append("processado", processado)
                .append("speakingTime", speakingTime)
                .append("dateCreate", dateCreate)
                .append("listId", listId)
                .append("hangupCause", hangupCause)
                .append("hangupText", hangupText)
                .toString();
    }
}

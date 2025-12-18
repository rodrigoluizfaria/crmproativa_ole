package com.proativaservicos.model.trescplus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCallSocket {

    private Date bootTime;
    private  Call callHistory;


    public Date getBootTime() {
        return bootTime;
    }

    private HangupCause hangupCause;

    public HangupCause getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(HangupCause hangupCause) {
        this.hangupCause = hangupCause;
    }

    public void setBootTime(Date bootTime) {
        this.bootTime = bootTime;
    }

    public Call getCallHistory() {
        return callHistory;
    }

    public void setCallHistory(Call callHistory) {
        this.callHistory = callHistory;
    }

    public class HangupCause{

       private String color;
        private String text;
        private int id;

        private int sip;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSip() {
            return sip;
        }

        public void setSip(int sip) {
            this.sip = sip;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("color", color)
                    .append("text", text)
                    .append("id", id)
                    .append("sip", sip)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bootTime", bootTime)
                .append("callHistory", callHistory).append(","+super.toString())
                .toString();
    }
}

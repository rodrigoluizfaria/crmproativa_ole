package com.proativaservicos.model.trescplus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.RawJsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Call {


    public String id;

    public String list;

    public String number;
    public String call_date;
    public Date call_date_rfc3339;
    public int campaign_id;
    public String campaign;
    public String queue_name;
    public String ivr_name;
    public Object receptive_name;
    public Object receptive_phone;
    public Object receptive_did;
    public boolean has_agent;

    public String agent;
    public String acw_time;
    public String speaking_time;
    public String ivr_time;
    public String amd_time;
    public String waiting_time;
    public String speaking_with_agent_time;

    public Route route;
    public String billed_time;
    public String billed_value;
    public String qualification;
    public String behavior;
    public String readable_behavior_text;
    public String phone_type;
    public String recording;
    public Object recording_amd;
    public int status_id;

    public int status;
    public String readable_status_text;
    public String readable_amd_status_text;
    public String mode;
    public int hangup_cause;
    public String sip_cause;
    public String readable_hangup_cause_text;

    public Object feedback;
    public boolean recorded;
    public boolean ended_by_agent;
    public String qualification_note;
    public String sid;
    public boolean is_dmc;
    public boolean is_unknown;
    public boolean is_transferred;
    public boolean is_consult;
    public boolean is_transfer;
    public ArrayList<Object> consults;
    public Object consult_ids;
    public Object transfer;
    public Object parent;
    public Object transfer_id;
    public Object parent_id;
    public Object consult;
    public boolean is_conversion;
    public Object consult_id;
    public Object recording_transfer;
    public Object recording_consult;
    public int qualification_id;
    public boolean consult_cancelled;
    public String recording_after_consult_cancel;
    public String ivr_digit_pressed;
    public String record_name;
    public ArrayList<Object> ivr_after_call;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public MailingData mailing_data;

    public class MailingData {
        public String _id;
        public String identifier;
        public int campaign_id;
        public int company_id;
        public int list_id;

        public Object uf;

        public String phone;
        public int dialed_phone;
        public int dialed_identifier;
        public int on_calling;
        public int column_position;
        public int row_position;

        public String cpf;

        @JsonDeserialize(using = RawJsonDeserializer.class)
        public String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {

            this.data = data;
        }

        public Map<String, Object> getDataToMap() {

            if (StringUtils.isNotBlank(this.data)) {

                Gson gson = new Gson();
                Type empMapType = new TypeToken<Map<String, Object>>() {
                }.getType();
                return gson.fromJson(this.data, empMapType);

            }
            return null;

        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public int getCampaign_id() {
            return campaign_id;
        }

        public void setCampaign_id(int campaign_id) {
            this.campaign_id = campaign_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public int getList_id() {
            return list_id;
        }

        public void setList_id(int list_id) {
            this.list_id = list_id;
        }

        public Object getUf() {
            return uf;
        }

        public void setUf(Object uf) {
            this.uf = uf;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getDialed_phone() {
            return dialed_phone;
        }

        public void setDialed_phone(int dialed_phone) {
            this.dialed_phone = dialed_phone;
        }

        public int getDialed_identifier() {
            return dialed_identifier;
        }

        public void setDialed_identifier(int dialed_identifier) {
            this.dialed_identifier = dialed_identifier;
        }

        public int getOn_calling() {
            return on_calling;
        }

        public void setOn_calling(int on_calling) {
            this.on_calling = on_calling;
        }

        public int getColumn_position() {
            return column_position;
        }

        public void setColumn_position(int column_position) {
            this.column_position = column_position;
        }

        public int getRow_position() {
            return row_position;
        }

        public void setRow_position(int row_position) {
            this.row_position = row_position;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        @Override
        public String toString() {
            return "MailingData{" +
                    "_id='" + _id + '\'' +
                    ", identifier='" + identifier + '\'' +
                    ", campaign_id=" + campaign_id +
                    ", company_id=" + company_id +
                    ", list_id=" + list_id +
                    ", uf=" + uf +
                    ", phone='" + phone + '\'' +
                    ", dialed_phone=" + dialed_phone +
                    ", dialed_identifier=" + dialed_identifier +
                    ", on_calling=" + on_calling +
                    ", column_position=" + column_position +
                    ", row_position=" + row_position +
                    ", cpf='" + cpf + '\'' +
                    '}';
        }
    }

    public class Route {

        public int id;
        public String name;
        public String host;
        public String route;
        public String endpoint;
        public Object caller_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public Object getCaller_id() {
            return caller_id;
        }

        public void setCaller_id(Object caller_id) {
            this.caller_id = caller_id;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", host='" + host + '\'' +
                    ", route='" + route + '\'' +
                    ", endpoint='" + endpoint + '\'' +
                    ", caller_id=" + caller_id +
                    '}';
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getNumber() {

        return number;
    }

    public String getNumberPtBr() {

        if (StringUtils.isNotBlank(number) && number.startsWith("55")) {
            return number.substring(2);

        }

        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getCall_date() {

        if (StringUtils.isNotBlank(call_date)) {
            return DateUtil.builder(call_date).formatarStringParaData("yyyy-MM-dd HH:mm:ss").formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();

        }


        return call_date;
    }

    public void setCall_date(String call_date) {
        this.call_date = call_date;
    }

    public Date getCall_date_rfc3339() {
        return call_date_rfc3339;
    }

    public void setCall_date_rfc3339(Date call_date_rfc3339) {
        this.call_date_rfc3339 = call_date_rfc3339;
    }

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getQueue_name() {
        return queue_name;
    }

    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }

    public String getIvr_name() {
        return ivr_name;
    }

    public void setIvr_name(String ivr_name) {
        this.ivr_name = ivr_name;
    }

    public Object getReceptive_name() {
        return receptive_name;
    }

    public void setReceptive_name(Object receptive_name) {
        this.receptive_name = receptive_name;
    }

    public Object getReceptive_phone() {
        return receptive_phone;
    }

    public void setReceptive_phone(Object receptive_phone) {
        this.receptive_phone = receptive_phone;
    }

    public Object getReceptive_did() {
        return receptive_did;
    }

    public void setReceptive_did(Object receptive_did) {
        this.receptive_did = receptive_did;
    }

    public boolean isHas_agent() {
        return has_agent;
    }

    public void setHas_agent(boolean has_agent) {
        this.has_agent = has_agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAcw_time() {
        return acw_time;
    }

    public void setAcw_time(String acw_time) {
        this.acw_time = acw_time;
    }

    public String getSpeaking_time() {
        return speaking_time;
    }

    public void setSpeaking_time(String speaking_time) {
        this.speaking_time = speaking_time;
    }

    public String getIvr_time() {
        return ivr_time;
    }

    public void setIvr_time(String ivr_time) {
        this.ivr_time = ivr_time;
    }

    public String getAmd_time() {
        return amd_time;
    }

    public void setAmd_time(String amd_time) {
        this.amd_time = amd_time;
    }

    public String getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(String waiting_time) {
        this.waiting_time = waiting_time;
    }

    public String getSpeaking_with_agent_time() {
        return speaking_with_agent_time;
    }

    public void setSpeaking_with_agent_time(String speaking_with_agent_time) {
        this.speaking_with_agent_time = speaking_with_agent_time;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getBilled_time() {
        return billed_time;
    }

    public void setBilled_time(String billed_time) {
        this.billed_time = billed_time;
    }

    public String getBilled_value() {
        return billed_value;
    }

    public void setBilled_value(String billed_value) {
        this.billed_value = billed_value;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getReadable_behavior_text() {
        return readable_behavior_text;
    }

    public void setReadable_behavior_text(String readable_behavior_text) {
        this.readable_behavior_text = readable_behavior_text;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public Object getRecording_amd() {
        return recording_amd;
    }

    public void setRecording_amd(Object recording_amd) {
        this.recording_amd = recording_amd;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getReadable_status_text() {
        return readable_status_text;
    }

    public void setReadable_status_text(String readable_status_text) {
        this.readable_status_text = readable_status_text;
    }

    public String getReadable_amd_status_text() {
        return readable_amd_status_text;
    }

    public void setReadable_amd_status_text(String readable_amd_status_text) {
        this.readable_amd_status_text = readable_amd_status_text;
    }

    public String getMode() {

        if (StringUtils.isNotBlank(this.mode)) {

            switch (this.mode) {
                case "dialer":
                    return "Discador";
                case "manual":
                    return "Manual";
                default:
                    return this.mode;
            }


        }

        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getHangup_cause() {
        return hangup_cause;
    }

    public void setHangup_cause(int hangup_cause) {
        this.hangup_cause = hangup_cause;
    }

    public String getSip_cause() {
        return sip_cause;
    }

    public void setSip_cause(String sip_cause) {
        this.sip_cause = sip_cause;
    }

    public String getReadable_hangup_cause_text() {
        return readable_hangup_cause_text;
    }

    public void setReadable_hangup_cause_text(String readable_hangup_cause_text) {
        this.readable_hangup_cause_text = readable_hangup_cause_text;
    }

    public Object getFeedback() {
        return feedback;
    }

    public void setFeedback(Object feedback) {
        this.feedback = feedback;
    }

    public boolean isRecorded() {
        return recorded;
    }

    public void setRecorded(boolean recorded) {
        this.recorded = recorded;
    }

    public boolean isEnded_by_agent() {
        return ended_by_agent;
    }

    public void setEnded_by_agent(boolean ended_by_agent) {
        this.ended_by_agent = ended_by_agent;
    }

    public String getQualification_note() {
        return qualification_note;
    }

    public void setQualification_note(String qualification_note) {
        this.qualification_note = qualification_note;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public boolean isIs_dmc() {
        return is_dmc;
    }

    public void setIs_dmc(boolean is_dmc) {
        this.is_dmc = is_dmc;
    }

    public boolean isIs_unknown() {
        return is_unknown;
    }

    public void setIs_unknown(boolean is_unknown) {
        this.is_unknown = is_unknown;
    }

    public boolean isIs_transferred() {
        return is_transferred;
    }

    public void setIs_transferred(boolean is_transferred) {
        this.is_transferred = is_transferred;
    }

    public boolean isIs_consult() {
        return is_consult;
    }

    public void setIs_consult(boolean is_consult) {
        this.is_consult = is_consult;
    }

    public boolean isIs_transfer() {
        return is_transfer;
    }

    public void setIs_transfer(boolean is_transfer) {
        this.is_transfer = is_transfer;
    }

    public ArrayList<Object> getConsults() {
        return consults;
    }

    public void setConsults(ArrayList<Object> consults) {
        this.consults = consults;
    }

    public Object getConsult_ids() {
        return consult_ids;
    }

    public void setConsult_ids(Object consult_ids) {
        this.consult_ids = consult_ids;
    }

    public Object getTransfer() {
        return transfer;
    }

    public void setTransfer(Object transfer) {
        this.transfer = transfer;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public Object getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Object transfer_id) {
        this.transfer_id = transfer_id;
    }

    public Object getParent_id() {
        return parent_id;
    }

    public void setParent_id(Object parent_id) {
        this.parent_id = parent_id;
    }

    public Object getConsult() {
        return consult;
    }

    public void setConsult(Object consult) {
        this.consult = consult;
    }

    public boolean isIs_conversion() {
        return is_conversion;
    }

    public void setIs_conversion(boolean is_conversion) {
        this.is_conversion = is_conversion;
    }

    public Object getConsult_id() {
        return consult_id;
    }

    public void setConsult_id(Object consult_id) {
        this.consult_id = consult_id;
    }

    public Object getRecording_transfer() {
        return recording_transfer;
    }

    public void setRecording_transfer(Object recording_transfer) {
        this.recording_transfer = recording_transfer;
    }

    public Object getRecording_consult() {
        return recording_consult;
    }

    public void setRecording_consult(Object recording_consult) {
        this.recording_consult = recording_consult;
    }

    public int getQualification_id() {
        return qualification_id;
    }

    public void setQualification_id(int qualification_id) {
        this.qualification_id = qualification_id;
    }

    public boolean isConsult_cancelled() {
        return consult_cancelled;
    }

    public void setConsult_cancelled(boolean consult_cancelled) {
        this.consult_cancelled = consult_cancelled;
    }

    public String getRecording_after_consult_cancel() {
        return recording_after_consult_cancel;
    }

    public void setRecording_after_consult_cancel(String recording_after_consult_cancel) {
        this.recording_after_consult_cancel = recording_after_consult_cancel;
    }

    public String getIvr_digit_pressed() {
        return ivr_digit_pressed;
    }

    public void setIvr_digit_pressed(String ivr_digit_pressed) {
        this.ivr_digit_pressed = ivr_digit_pressed;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public ArrayList<Object> getIvr_after_call() {
        return ivr_after_call;
    }

    public void setIvr_after_call(ArrayList<Object> ivr_after_call) {
        this.ivr_after_call = ivr_after_call;
    }

    public MailingData getMailing_data() {
        return mailing_data;
    }

    public void setMailing_data(MailingData mailing_data) {
        this.mailing_data = mailing_data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("list", list)
                .append("number", number)
                .append("call_date", call_date)
                .append("call_date_rfc3339", call_date_rfc3339)
                .append("campaign_id", campaign_id)
                .append("campaign", campaign)
                .append("queue_name", queue_name)
                .append("ivr_name", ivr_name)
                .append("receptive_name", receptive_name)
                .append("receptive_phone", receptive_phone)
                .append("receptive_did", receptive_did)
                .append("has_agent", has_agent)
                .append("agent", agent)
                .append("acw_time", acw_time)
                .append("speaking_time", speaking_time)
                .append("ivr_time", ivr_time)
                .append("amd_time", amd_time)
                .append("waiting_time", waiting_time)
                .append("speaking_with_agent_time", speaking_with_agent_time)
                .append("route", route)
                .append("billed_time", billed_time)
                .append("billed_value", billed_value)
                .append("qualification", qualification)
                .append("behavior", behavior)
                .append("readable_behavior_text", readable_behavior_text)
                .append("phone_type", phone_type)
                .append("recording", recording)
                .append("recording_amd", recording_amd)
                .append("status_id", status_id)
                .append("status", status)
                .append("readable_status_text", readable_status_text)
                .append("readable_amd_status_text", readable_amd_status_text)
                .append("mode", mode)
                .append("hangup_cause", hangup_cause)
                .append("sip_cause", sip_cause)
                .append("readable_hangup_cause_text", readable_hangup_cause_text)
                .append("feedback", feedback)
                .append("recorded", recorded)
                .append("ended_by_agent", ended_by_agent)
                .append("qualification_note", qualification_note)
                .append("sid", sid)
                .append("is_dmc", is_dmc)
                .append("is_unknown", is_unknown)
                .append("is_transferred", is_transferred)
                .append("is_consult", is_consult)
                .append("is_transfer", is_transfer)
                .append("consults", consults)
                .append("consult_ids", consult_ids)
                .append("transfer", transfer)
                .append("parent", parent)
                .append("transfer_id", transfer_id)
                .append("parent_id", parent_id)
                .append("consult", consult)
                .append("is_conversion", is_conversion)
                .append("consult_id", consult_id)
                .append("recording_transfer", recording_transfer)
                .append("recording_consult", recording_consult)
                .append("qualification_id", qualification_id)
                .append("consult_cancelled", consult_cancelled)
                .append("recording_after_consult_cancel", recording_after_consult_cancel)
                .append("ivr_digit_pressed", ivr_digit_pressed)
                .append("record_name", record_name)
                .append("ivr_after_call", ivr_after_call)
                .append("mailing_data", mailing_data)
                .toString();
    }
}

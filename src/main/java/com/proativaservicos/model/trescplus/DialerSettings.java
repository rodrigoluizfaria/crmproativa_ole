package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

public class DialerSettings {

    @SerializedName("url")
    private String url;
    @SerializedName("qualification_list_id")
    private int qualification_list_id;

    @SerializedName("wait_time")
    private int wait_time;

    @SerializedName("call_time")
    private int call_time;

    @SerializedName("recalls")
    private int recalls;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQualification_list_id() {
        return qualification_list_id;
    }

    public void setQualification_list_id(int qualification_list_id) {
        this.qualification_list_id = qualification_list_id;
    }

    public int getWait_time() {
        return wait_time;
    }

    public void setWait_time(int wait_time) {
        this.wait_time = wait_time;
    }

    public int getCall_time() {
        return call_time;
    }

    public void setCall_time(int call_time) {
        this.call_time = call_time;
    }

    public int getRecalls() {
        return recalls;
    }

    public void setRecalls(int recalls) {
        this.recalls = recalls;
    }
}

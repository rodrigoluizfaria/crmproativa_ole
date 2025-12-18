package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Qualification {


    @SerializedName("url")
    private String url;
    @SerializedName("qualifications")
    private List<Qualifications> qualifications;

    public List<Qualifications> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualifications> qualifications) {
        this.qualifications = qualifications;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "url='" + url + '\'' +
                ", qualifications=" + qualifications +
                '}';
    }
}

package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("id")
    public int id;
    @SerializedName("timezone")
    public String timezone;
    @SerializedName("language")
    public String language;
    @SerializedName("date_format")
    public String dateFormat;
    @SerializedName("hour_format")
    public String hourFormat;
    @SerializedName("sector")
    public String sector;

    @SerializedName("access")
    public Object access;
    @SerializedName("web_extension")
    public boolean webExtension;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getHourFormat() {
        return hourFormat;
    }

    public void setHourFormat(String hourFormat) {
        this.hourFormat = hourFormat;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Object getAccess() {
        return access;
    }

    public void setAccess(Object access) {
        this.access = access;
    }

    public boolean isWebExtension() {
        return webExtension;
    }

    public void setWebExtension(boolean webExtension) {
        this.webExtension = webExtension;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", timezone='" + timezone + '\'' +
                ", language='" + language + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", hourFormat='" + hourFormat + '\'' +
                ", sector='" + sector + '\'' +
                ", access=" + access +
                ", webExtension=" + webExtension +
                '}';
    }
}

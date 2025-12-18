package com.proativaservicos.model.calculadoraConsignado;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class LoginResponse {

    private String ok;
    private String data;

    private Date valid;

    private String token;

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getIsOk() {

        if (StringUtils.isBlank(this.ok))
            return false;

        return Boolean.valueOf(ok);

    }

    public Date getValid() {
        return valid;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String toke) {
        this.token = toke;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ok", ok)
                .append("data", data)
                .append("valid", valid)
                .append("token", token)
                .toString();
    }
}

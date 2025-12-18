package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

public class ResponseAuthenticate extends ResponseGeneric {

    @SerializedName("data")
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "ResponseAuthenticate{" +
                "agent=" + agent +
                '}';
    }
}

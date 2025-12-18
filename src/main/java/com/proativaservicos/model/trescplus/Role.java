package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

public class Role {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("readable_name")
    public String readableName;

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

    public String getReadableName() {
        return readableName;
    }

    public void setReadableName(String readableName) {
        this.readableName = readableName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", readableName='" + readableName + '\'' +
                '}';
    }
}

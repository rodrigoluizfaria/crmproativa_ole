package com.proativaservicos.service.api;

import org.bson.types.ObjectId;

import java.util.Objects;

public abstract class DocumentGeneric {

    private ObjectId id;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentGeneric that = (DocumentGeneric) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

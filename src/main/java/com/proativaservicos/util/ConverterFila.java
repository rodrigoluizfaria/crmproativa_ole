package com.proativaservicos.util;

import com.proativaservicos.model.Fila;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.io.Serializable;

;

@FacesConverter(value = "converterFila")
public class ConverterFila implements Converter, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.isEmpty()) {

            return (Fila) component.getAttributes().get(value);

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // TODO Auto-generated method stub

        if (value instanceof Fila) {

            Fila entity = (Fila) value;

            if (entity instanceof Fila && entity.getId() != null) {

                component.getAttributes().put(entity.getId().toString(), entity);

                return entity.getId().toString();
            }
        }
        return "";
    }

}

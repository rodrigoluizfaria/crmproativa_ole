package com.proativaservicos.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.io.Serializable;


@FacesConverter(value = "converterEstado")
public class ConverterEstado implements Converter, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // TODO Auto-generated method stub

        if (value != null && !value.isEmpty()) {

            return component.getAttributes().get(value).equals("ATIVO") ? true : false;

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // TODO Auto-generated method stub

        if (value instanceof Boolean) {

            Boolean entity = (boolean) value;

            if (entity instanceof Boolean && entity != null) {

                component.getAttributes().put(entity.toString(), entity);

                return entity.toString();
            }
        }


        return null;
    }

}

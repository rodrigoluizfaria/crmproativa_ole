package com.proativaservicos.util;

import com.proativaservicos.model.Equipe;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.io.Serializable;

@FacesConverter(value = "converterEquipe")
public class ConverterEquipe implements Converter, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.isEmpty()) {

            return (Equipe) component.getAttributes().get(value);

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value instanceof Equipe) {

            Equipe entity = (Equipe) value;

            if (entity instanceof Equipe && entity.getId() != null) {

                component.getAttributes().put(entity.getId().toString(), entity);

                return entity.getId().toString();
            }
        }
        return "";
    }

}

package com.proativaservicos.util;

import com.proativaservicos.model.StatusCampanha;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.io.Serializable;

@FacesConverter(value = "converterStatusCamanha")
public class ConverterStatusCampanha implements Converter, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.isEmpty()) {

            return (StatusCampanha) component.getAttributes().get(value);

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // TODO Auto-generated method stub

        if (value instanceof StatusCampanha) {

            StatusCampanha entity = (StatusCampanha) value;

            if (entity instanceof StatusCampanha && entity.getId() != null) {

                component.getAttributes().put(entity.getId().toString(), entity);

                return entity.getId().toString();
            }
        }
        return "";
    }

}

package com.proativaservicos.util;

import java.io.Serializable;


import com.proativaservicos.model.Empresa;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "converterEmpresa")
public class ConverterEmpresa implements Converter,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null && !value.isEmpty()) {

			return (Empresa) component.getAttributes().get(value);

		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub

		if (value instanceof Empresa) {

			Empresa entity = (Empresa) value;

			if (entity instanceof Empresa && entity.getId() != null) {

				component.getAttributes().put(entity.getId().toString(), entity);

				return entity.getId().toString();
			}
		}
		return "";
	}

}

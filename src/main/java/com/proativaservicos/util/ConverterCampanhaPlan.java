package com.proativaservicos.util;

import com.proativaservicos.util.constantes.CampanhaAtendimentoEnum;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.io.Serializable;

@FacesConverter(value = "converterCampanhaPlan")
public class ConverterCampanhaPlan implements Converter,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null && !value.isEmpty()) {

			return (CampanhaAtendimentoEnum) component.getAttributes().get(value);

		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub

		if (value instanceof CampanhaAtendimentoEnum) {

			CampanhaAtendimentoEnum entity = (CampanhaAtendimentoEnum) value;

			if (entity instanceof CampanhaAtendimentoEnum && entity != null) {

				component.getAttributes().put(entity.toString(), entity);

				return entity.toString();
			}
		}
		return "";
	}

}

package com.proativaservicos.util;

import java.io.Serializable;
import java.util.List;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UISelectItem;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.primefaces.component.orderlist.OrderList;


@FacesConverter(value = "orderListConverter")
public class ConverterOrderList implements Converter,Serializable{

	
  
	private static final long serialVersionUID = 1L;

	public ConverterOrderList() {
        super();
    }

    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
        if (value == null) {
            return null;
        }
        return fromSelect(component, value);
    }

    /**
     * @param currentcomponent
     * @param objectString
     * @return the Object
     */
    private Object fromSelect(final UIComponent currentcomponent, final String objectString) {

        if (currentcomponent.getClass() == UISelectItem.class) {
            final UISelectItem item = (UISelectItem) currentcomponent;
            final Object value = item.getValue();
            if (objectString.equals(serialize(value))) {
                return value;
            }
        }

        if (currentcomponent.getClass() == UISelectItems.class) {
            final UISelectItems items = (UISelectItems) currentcomponent;
            final List<Object> elements = (List<Object>) items.getValue();
            for (final Object element : elements) {
                if (objectString.equals(serialize(element))) {
                    return element;
                }
            }
        }

        if (!currentcomponent.getChildren().isEmpty()) {
            for (final UIComponent component : currentcomponent.getChildren()) {
                final Object result = fromSelect(component, objectString);
                if (result != null) {
                    return result;
                }
            }
        }

        if (currentcomponent instanceof OrderList) {
            Object items = ((OrderList) currentcomponent).getValue();
            List<Object> elements = (List<Object>) items;
            for (final Object element : elements) {
                if (objectString.equals(serialize(element))) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * @param object
     * @return the String
     */
    private String serialize(final Object object) {
        if (object == null) {
            return null;
        }
        return object.getClass() + "@" + object.hashCode();
    }

	@Override
	public String getAsString(final FacesContext context,final UIComponent component,final Object value) {
		
		 return serialize(value);
	}

}

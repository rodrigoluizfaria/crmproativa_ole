package com.proativaservicos.util;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

public class FacesUtil {

	
	
	public static boolean isPostBack() {
		
		return Faces.isPostback();
	}
	
	public static void removendoAtributos(String ... strName ) {
		
		for (String atributo : strName) {
			
			if(StringUtils.isNotBlank(atributo))
				Faces.getSession().removeAttribute(atributo);
			
		}
		
	}
}

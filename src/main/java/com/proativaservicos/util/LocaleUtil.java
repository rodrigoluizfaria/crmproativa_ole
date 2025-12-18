package com.proativaservicos.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtil {

	private Locale locale;

	private static LocaleUtil instance = new LocaleUtil();

	public static LocaleUtil getInstance() {
		return instance;
	}

	public String getMessage(String message) {

		this.locale = new Locale("pt", "BR");
		ResourceBundle bundle = ResourceBundle.getBundle("menssagens",	this.locale, Thread.currentThread().getContextClassLoader());
	
		if (bundle != null)
			return bundle.getString(message);
		
		return "";
	}

}

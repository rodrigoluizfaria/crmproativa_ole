package com.proativaservicos.model.pan;

import com.google.gson.Gson;

public abstract class OpenApiPan<E> {
	
	
	public String toJson(E e) {
		
		return new Gson().toJson(e,e.getClass());
		
	}

}

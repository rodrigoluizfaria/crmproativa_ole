package com.proativaservicos.exception;


import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory {
	
	
	private ExceptionHandlerFactory parent;

	@SuppressWarnings("deprecation")
	public JsfExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		// TODO Auto-generated constructor stub
		this.parent = parent;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		
		return new JsfExceptionHandler(parent.getExceptionHandler());
	}
	
	
	

}

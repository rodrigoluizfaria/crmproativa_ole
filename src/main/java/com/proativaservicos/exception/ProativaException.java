package com.proativaservicos.exception;

public class ProativaException extends Exception {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProativaException(Throwable e) {
		super(e);
	}

	public ProativaException(String msg) {
		super(msg);
	}

}

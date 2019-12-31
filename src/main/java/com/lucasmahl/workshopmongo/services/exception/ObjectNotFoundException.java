package com.lucasmahl.workshopmongo.services.exception;

//RuntimeException é uma exceção não é exigida q seja tratada
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}

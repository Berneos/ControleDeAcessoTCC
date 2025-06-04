package com.brenomartins.cursospring.services.exceptions;

import java.io.Serializable;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		
		super(msg);
		
	}

}

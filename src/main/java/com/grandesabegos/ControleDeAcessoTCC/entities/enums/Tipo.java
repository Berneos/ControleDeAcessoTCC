package com.grandesabegos.ControleDeAcessoTCC.entities.enums;

public enum Tipo {

	Escola(1),
	Empresa(2),
	Academia(3),
	EdificioComercial(4);
	
	private int code;
	
	private Tipo(int code) {
		
		this.code = code;
		
	}
	
	public int getCode() {
		
		return code;
		
	}
	
	public static Tipo valueOf(int code) {
		
		for(Tipo tipo : Tipo.values()) {
			
			if(tipo.getCode() == code) {
				
				return tipo;
				
			}
			
			
		}
		
		throw new IllegalArgumentException("Código inválido de tipo de instituição");
		
	}
	
}

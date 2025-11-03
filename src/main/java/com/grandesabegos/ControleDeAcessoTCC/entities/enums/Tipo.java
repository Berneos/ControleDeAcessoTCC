package com.grandesabegos.ControleDeAcessoTCC.entities.enums;

public enum Tipo {

	Escola(1),
	Empresa(2),
	Academia(3),
	EdificioComercial(4);
	
	private Integer code;
	
	private Tipo(Integer code) {
		
		this.code = code;
		
	}
	
	public Integer getCode() {
		
		return code;
		
	}
	
	public static Tipo valueOf(Integer code) {
		
		for(Tipo tipo : Tipo.values()) {
			
			if(tipo.getCode() == code) {
				
				return tipo;
				
			}
			
			
		}
		
		throw new IllegalArgumentException("Código inválido de tipo de instituição");
		
	}
	
}

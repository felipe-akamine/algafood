package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");
	
	private String title;
	private String path;
	
	ProblemType(String path, String title){
		this.path = "https://algafood.com.br" + path;
		this.title = title;
	}
	
}

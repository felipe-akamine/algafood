package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private LocalDateTime timeStamp;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private List<Object> objects;
	
	@Getter
	@Setter
	@Builder
	public static class Object {
		
		private String name;
		private String userMessage;
		
	}
	
}

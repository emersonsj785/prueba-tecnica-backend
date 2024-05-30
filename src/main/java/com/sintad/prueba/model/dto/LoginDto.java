package com.sintad.prueba.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto
{
	@JsonInclude(value = Include.NON_NULL)
	private String username;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contrasenia;
	
	private String token;
}

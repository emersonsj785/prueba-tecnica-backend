package com.sintad.prueba.security.service;

import org.springframework.stereotype.Service;

import com.sintad.prueba.model.dto.LoginDto;
import com.sintad.prueba.security.exception.ExcepcionesAutenticacion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements ILoginService
{
	private static final String USUARIO = "ADMIN";
	private static final String CONTRASENIA = "ADMIN";
	
	private JwtServiceImpl jwtService;
	
	@Override
	public LoginDto login(LoginDto login)
	{
		if(login.getUsername().equals(USUARIO) && login.getContrasenia().equals(CONTRASENIA))
		{
			return LoginDto.builder().token(this.jwtService.generarToken(login.getUsername())).build();
		}
			
		throw new ExcepcionesAutenticacion();
	}
	
}

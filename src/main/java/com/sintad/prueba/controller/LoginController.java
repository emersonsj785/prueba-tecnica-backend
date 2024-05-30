package com.sintad.prueba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sintad.prueba.model.dto.LoginDto;
import com.sintad.prueba.security.service.ILoginService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController
{
	private ILoginService loginService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto dto)
	{
		return new ResponseEntity<>(this.loginService.login(dto), HttpStatus.OK);
	}
}

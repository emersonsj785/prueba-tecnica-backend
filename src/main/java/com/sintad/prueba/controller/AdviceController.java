package com.sintad.prueba.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sintad.prueba.security.exception.ExcepcionesAutenticacion;
@RestControllerAdvice
public class AdviceController 
{
	/**
     * Maneja la excepción de autenticación.
     * @param ex la excepción de autenticación
     * @return una respuesta HTTP con el mensaje de error y el estado UNAUTHORIZED
     */
	@ExceptionHandler(ExcepcionesAutenticacion.class)
	public ResponseEntity<?> manejarExcepcionAutenticacion(ExcepcionesAutenticacion ex)
	{
		ex.printStackTrace();
		Map<String, Object> response = new HashMap<>();
		response.put("mensajeError", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	/**
     * Maneja la excepción de violación de integridad de datos.
     * @param ex la excepción de violación de integridad de datos
     * @return una respuesta HTTP con el mensaje de error y el estado INTERNAL_SERVER_ERROR (500)
     */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> manejarExcepcionDataIntegrityViolation(DataIntegrityViolationException ex)
	{
		ex.printStackTrace();
		Map<String, Object> response = new HashMap<>();
		response.put("mensajeError", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
     * Maneja cualquier otra excepción no especificada.
     * @param ex la excepción
     * @return una respuesta HTTP con el mensaje de error y el estado INTERNAL_SERVER_ERROR (500)
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> manejarExcepcion(Exception ex)
	{
		ex.printStackTrace();
		Map<String, Object> response = new HashMap<>();
		response.put("mensajeError", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
}

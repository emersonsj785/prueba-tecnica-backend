package com.sintad.prueba.security.exception;

public class ExcepcionesAutenticacion extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	public final static String mensaje = "Autenticación inválida";
	
	public ExcepcionesAutenticacion()
	{
		super(mensaje);
	}
	
}

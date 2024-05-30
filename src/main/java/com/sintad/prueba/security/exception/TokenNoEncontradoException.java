package com.sintad.prueba.security.exception;

public class TokenNoEncontradoException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	public final static String mensaje = "Token No Encontrado";
	
	public TokenNoEncontradoException(String mensaje)
	{
        super(mensaje);
    }
	
}

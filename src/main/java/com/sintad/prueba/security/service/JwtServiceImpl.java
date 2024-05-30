package com.sintad.prueba.security.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl
{
	public static final String CODIGO_SECRETO= "U0lOVEFEIC0gUFJVRUJBIC0gVEVDTklDQSAtIEZJUk1B";
	
	/**
     * Genera un token JWT para el usuario.
     * @param usuario el nombre de usuario
     * @return el token JWT
     */
	public String generarToken(String user) 
	{
		return Jwts.builder()
				.setSubject(user)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 40))
				.signWith(this.obtenerLlave(), SignatureAlgorithm.HS256).compact();
	}
	
	/**
     * Obtiene la llave secreta para firmar el token.
     * @return la llave secreta
     */
	private Key obtenerLlave() 
	{
		byte[] llaveBytes = Decoders.BASE64.decode(CODIGO_SECRETO);
		return Keys.hmacShaKeyFor(llaveBytes);
	}
	
	/**
     * Extrae una reclamación (claim) del token JWT.
     * @param token el token JWT
     * @param claimsResolver la función para resolver la reclamación
     * @return la reclamación resuelta
     */
	public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver)
	{ 
        final Claims claims = Jwts 
                .parserBuilder() 
                .setSigningKey(this.obtenerLlave()) 		//la llave del token
                .build() 
                .parseClaimsJws(token)  					//extrae componentes de token
                .getBody();             					//lo que contiene token
        return claimsResolver.apply(claims); 				//ejecuta la funcion
    } 
	
	/*
	 	Validaciones de Token
	 */
	private Date obtenerExpiracion(String token)
	{
		return this.extraerClaim(token, Claims::getExpiration);
	}
	
	private Boolean esTokenExpirado(String token)
	{
		return this.obtenerExpiracion(token).before(new Date());
	}
	
	public String extraerUsuario(String token) 
	{ 
        return extraerClaim(token, Claims::getSubject); 
    } 
	
	/**
     * Valida el token JWT con los detalles del usuario.
     * @param token el token JWT
     * @param detallesUsuario los detalles del usuario
     * @return verdadero si el token es válido, falso en caso contrario
     */
	public Boolean validarToken(String token, UserDetails detallesUsuario)
	{
		String usuario = this.extraerUsuario(token);
		return (usuario.equals(detallesUsuario.getUsername()) && !this.esTokenExpirado(token));
	}
}

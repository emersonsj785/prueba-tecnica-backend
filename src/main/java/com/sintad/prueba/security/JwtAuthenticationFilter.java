package com.sintad.prueba.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sintad.prueba.model.dto.ApiDto;
import com.sintad.prueba.security.service.JwtServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	
	private JwtServiceImpl jwtService;
	
	private ObjectMapper objectMapper;
	
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	
	/**
     * Filtra las solicitudes para autenticar al usuario usando JWT.
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @param filterChain la cadena de filtros
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String usuario = null;
		try 
		{
			if(authHeader != null && authHeader.startsWith("Bearer "))
			{
				token = authHeader.substring(7);
				usuario = this.jwtService.extraerUsuario(token);
			}
			
			if(usuario != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(usuario);
				
					if(this.jwtService.validarToken(token, userDetails)) 
					{
						UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
			                    userDetails,
			                    null,
			                    userDetails.getAuthorities());
						authToken.setDetails(userDetails);
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} 
				
		}
		catch (Exception e)
		{
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			if (e instanceof ExpiredJwtException)
			{	
				response.setStatus(HttpStatus.FORBIDDEN.value());
				writer.print(objectMapper.writeValueAsString(this.respuestaApi(String.valueOf(HttpStatus.FORBIDDEN.value()), "Token invalido", null)));
			}
			else 
			{
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				writer.print(objectMapper.writeValueAsString(this.respuestaApi(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "Error desconocido", null)));
			}
			writer.flush();
			return;
		}
		
		filterChain.doFilter(request, response);
	}
	
	/**
     * Crea una respuesta API en formato JSON.
     * @param codeStatus el código de estado HTTP
     * @param mensaje el mensaje de respuesta
     * @param data datos adicionales
     */
	private ApiDto respuestaApi(String codeStatus, String mensaje, String data)
	{
		return ApiDto.
				builder().
				codeStatus(codeStatus).
				mensaje(mensaje).
				data(data).
				build();
	}
}

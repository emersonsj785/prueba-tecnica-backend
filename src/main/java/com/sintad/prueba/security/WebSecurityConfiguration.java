package com.sintad.prueba.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfiguration 
{
	private JwtAuthenticationFilter filtroAutenticacionJWT;
	
	private final UserDetailsService detalleUsuarioService;
	
	/**
     * Configura la cadena de filtros de seguridad.
     * @param http el objeto HttpSecurity
     * @return la cadena de filtros configurada
     */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
	
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) ->
						authorize
						.requestMatchers("/api").hasAnyRole("ADMIN")
						.requestMatchers("/login").permitAll()
						.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(this.authenticationProvider())
				.addFilterBefore(filtroAutenticacionJWT, UsernamePasswordAuthenticationFilter.class)
				.cors(Customizer.withDefaults())
				.build();
	}
	
	/**
     * Proveedor de autenticación que usa los detalles del usuario.
     * @return el proveedor de autenticación
     */
	@Bean
    public AuthenticationProvider authenticationProvider() 
	{ 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(detalleUsuarioService); 
        return authenticationProvider; 
    } 
	
	/**
     * Configura el gestor de autenticación.
     * @param configuracionAutenticacion la configuración de autenticación
     * @return el gestor de autenticación configurado
     */
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception 
	{
        return authenticationConfiguration.getAuthenticationManager();
    }
				
}
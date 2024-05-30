package com.sintad.prueba.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetaleServiceImpl implements UserDetailsService
{
	
	/**
     * Carga los detalles del usuario por nombre de usuario.
     * @param username el nombre de usuario
     * @return los detalles del usuario
     * @throws UsernameNotFoundException si el usuario no existe
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		//Se debe agregar logica de consulta a BD
		String usuario = "ADMIN";
		String contrasenia = "ADMIN";
		if(usuario.equals(username))
		{
			return User.builder()
					.username(usuario)
					.password(contrasenia)
					.roles("ADMIN")
					.build();
		}
		throw new UsernameNotFoundException("Usuario no existente");
	}

}

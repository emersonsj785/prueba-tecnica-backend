package com.sintad.prueba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionMvc implements WebMvcConfigurer
{
	
	/*
	 * Esta configuración hace que pueda funcionar 
	 * en el Front Angular.
	 */
	@Override
	public void addCorsMappings(CorsRegistry registro)
	{
		registro.addMapping("/**")
        	.allowedOrigins("http://localhost:4200")
        	.allowedMethods("GET", "POST", "PUT", "DELETE");
	}
}

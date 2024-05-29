package com.sintad.prueba.model.dto;

import lombok.Data;

@Data
public class TipoDocumentoDto
{
	private Long id;	
	private String codigo;
	private String nombre;
	private String descripcion;
	private Integer estado;
}

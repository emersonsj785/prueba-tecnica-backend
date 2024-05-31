package com.sintad.prueba.model.dto;

import lombok.Data;

@Data
public class EntidadDto
{
	private Long id; 
	
	private Long idTipoDocumento;
	
	private String nroDocumento;
	
	private String razonSocial;
	
	private String nombreComercial;
	
	private Long idTipoContribuyente;
	
	private String direccion;
	
	private String telefono;
	
	private Integer estado;
	
	private TipoDocumentoDto enTipoDocumento;
	
	private TipoContribuyenteDto enTipoContribuyente;
}

package com.sintad.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_entidad")
@Data
public class Entidad 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entidad")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_documento")
	private TipoDocumento enTipoDocumento;
	
	@Column(name = "nroDocumento")
	private String nroDocumento;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	@Column(name = "nombreComercial")
	private String nombreComercial;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_contribuyente")
	private TipoContribuyente enTipoContribuyente;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "estado")
	private Integer estado;
}
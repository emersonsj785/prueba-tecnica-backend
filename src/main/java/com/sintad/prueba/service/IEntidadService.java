package com.sintad.prueba.service;

import java.util.List;
import java.util.Optional;

import com.sintad.prueba.model.dto.EntidadDto;

public interface IEntidadService
{
	public List<EntidadDto> listarTodos();
    public Optional<EntidadDto> buscarPorId(Long id);
    public EntidadDto registrarEntidad(EntidadDto entidadDto);
    public EntidadDto actualizarEntidad(Long id, EntidadDto entidadDto);
    public void eliminarEntidad(Long id);
}

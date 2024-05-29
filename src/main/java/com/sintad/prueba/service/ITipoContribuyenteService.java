package com.sintad.prueba.service;

import java.util.List;
import java.util.Optional;

import com.sintad.prueba.model.dto.TipoContribuyenteDto;

public interface ITipoContribuyenteService
{	
	public List<TipoContribuyenteDto> listarTodos();
	public Optional<TipoContribuyenteDto> buscarPorId(Long id);
    public TipoContribuyenteDto registrarTipoContribuyente(TipoContribuyenteDto tipoContribuyenteDto);
    public TipoContribuyenteDto actualizarTipoContribuyente(Long id, TipoContribuyenteDto tipoContribuyenteDto);
    public void eliminarTipoContribuyente(Long id);
}

package com.sintad.prueba.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.sintad.prueba.model.TipoContribuyente;
import com.sintad.prueba.model.dto.TipoContribuyenteDto;
import com.sintad.prueba.repository.ITipoContribuyenteRepository;
import com.sintad.prueba.service.ITipoContribuyenteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoContribuyenteServiceImpl implements ITipoContribuyenteService
{

    private final ITipoContribuyenteRepository tipoContribuyenteRepository;

    @Override
    public List<TipoContribuyenteDto> listarTodos()
    {
        List<TipoContribuyente> tipoContribuyentes = tipoContribuyenteRepository.findAll();
        return mapToDtoList(tipoContribuyentes);
    }

    @Override
    public Optional<TipoContribuyenteDto> buscarPorId(Long id) {
        Optional<TipoContribuyente> tipoContribuyente = tipoContribuyenteRepository.findById(id);
        return tipoContribuyente.map(this::mapToDto);
    }

    @Override
    public TipoContribuyenteDto registrarTipoContribuyente(TipoContribuyenteDto tipoContribuyenteDto)
    {
        TipoContribuyente tipoContribuyente = mapToEntity(tipoContribuyenteDto);
        TipoContribuyente guardarTipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);
        return mapToDto(guardarTipoContribuyente);
    }

    @Override
    public TipoContribuyenteDto actualizarTipoContribuyente(Long id, TipoContribuyenteDto tipoContribuyenteDto)
    {
        TipoContribuyente tipoContribuyente = mapToEntity(tipoContribuyenteDto);
        tipoContribuyente.setId(id);
        TipoContribuyente actualizarTipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);
        return mapToDto(actualizarTipoContribuyente);
    }

    @Override
    public void eliminarTipoContribuyente(Long id)
    {
        tipoContribuyenteRepository.deleteById(id);
    }

    // Métodos para mapear entre entidades y DTOs
    private TipoContribuyenteDto mapToDto(TipoContribuyente tipoContribuyente)
    {
        TipoContribuyenteDto dto = new TipoContribuyenteDto();
        dto.setId(tipoContribuyente.getId());
        dto.setNombre(tipoContribuyente.getNombre());
        dto.setEstado(tipoContribuyente.getEstado());
        return dto;
    }

    private List<TipoContribuyenteDto> mapToDtoList(List<TipoContribuyente> tipoContribuyentes)
    {
        return tipoContribuyentes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TipoContribuyente mapToEntity(TipoContribuyenteDto dto)
    {
        TipoContribuyente tipoContribuyente = new TipoContribuyente();
        tipoContribuyente.setNombre(dto.getNombre());
        tipoContribuyente.setEstado(dto.getEstado());
        return tipoContribuyente;
    }
}

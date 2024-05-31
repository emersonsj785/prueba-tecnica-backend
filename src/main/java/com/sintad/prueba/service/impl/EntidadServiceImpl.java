package com.sintad.prueba.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sintad.prueba.model.Entidad;
import com.sintad.prueba.model.TipoContribuyente;
import com.sintad.prueba.model.TipoDocumento;
import com.sintad.prueba.model.dto.EntidadDto;
import com.sintad.prueba.model.dto.TipoContribuyenteDto;
import com.sintad.prueba.model.dto.TipoDocumentoDto;
import com.sintad.prueba.repository.IEntidadRepository;
import com.sintad.prueba.repository.ITipoContribuyenteRepository;
import com.sintad.prueba.repository.ITipoDocumentoRepository;
import com.sintad.prueba.service.IEntidadService;
import com.sintad.prueba.util.ClaseUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntidadServiceImpl implements IEntidadService
{
	
    private IEntidadRepository entidadRepository;

    private ITipoDocumentoRepository tipoDocumentoRepository;

    private ITipoContribuyenteRepository tipoContribuyenteRepository;
    
    @Override
    public List<EntidadDto> listarTodos()
    {
        List<Entidad> entidades = entidadRepository.findAll();
        return entidades.stream()
                       .map(this::mapeoEntidadADto)
                       .collect(Collectors.toList());
    }

    private EntidadDto mapeoEntidadADto(Entidad entidad)
    {
        EntidadDto entidadDto = ClaseUtil.aDto(entidad, EntidadDto.class);
        if (entidad.getEnTipoDocumento() != null)
        {
            entidadDto.setIdTipoDocumento(entidad.getEnTipoDocumento().getId());
            entidadDto.setEnTipoDocumento(ClaseUtil.aDto(entidad.getEnTipoDocumento(), TipoDocumentoDto.class));
        }
        if (entidad.getEnTipoContribuyente() != null)
        {
            entidadDto.setIdTipoContribuyente(entidad.getEnTipoContribuyente().getId());
            entidadDto.setEnTipoContribuyente(ClaseUtil.aDto(entidad.getEnTipoContribuyente(), TipoContribuyenteDto.class));
        }
        
        
        return entidadDto;
    }

    @Override
    public Optional<EntidadDto> buscarPorId(Long id)
    {
        Optional<Entidad> entidadBuscada = entidadRepository.findById(id);
        return entidadBuscada.map(this::mapeoEntidadADto);
    }
    
    @Override
    public EntidadDto registrarEntidad(EntidadDto entidadDto)
    {
        Entidad entidad = ClaseUtil.aEntidad(entidadDto, Entidad.class);
        
        // Si se proporciona un ID de tipo de documento, establecerlo en la entidad
        if (entidadDto.getIdTipoDocumento() != null)
        {
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(entidadDto.getIdTipoDocumento())
                                                                 .orElse(null);
            entidad.setEnTipoDocumento(tipoDocumento);
        }
        
        // Si se proporciona un ID de tipo de contribuyente, establecerlo en la entidad
        if (entidadDto.getIdTipoContribuyente() != null)
        {
            TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(entidadDto.getIdTipoContribuyente())
                                                                             .orElse(null);
            entidad.setEnTipoContribuyente(tipoContribuyente);
        }
        
        entidad = entidadRepository.save(entidad);
        //return ClaseUtil.aDto(entidad, EntidadDto.class);
        
        // Después de guardar la entidad, obtén el DTO actualizado
        EntidadDto entidadRegistradaDto = ClaseUtil.aDto(entidad, EntidadDto.class);
        
        // Si se proporcionó un ID de tipo de documento, establecerlo en el DTO
        if (entidad.getEnTipoDocumento() != null)
        {
            entidadRegistradaDto.setIdTipoDocumento(entidad.getEnTipoDocumento().getId());
            entidadRegistradaDto.setEnTipoDocumento(ClaseUtil.aDto(entidad.getEnTipoDocumento(), TipoDocumentoDto.class));
        }
        
        // Si se proporcionó un ID de tipo de contribuyente, establecerlo en el DTO
        if (entidad.getEnTipoContribuyente() != null)
        {
            entidadRegistradaDto.setIdTipoContribuyente(entidad.getEnTipoContribuyente().getId());
            entidadRegistradaDto.setEnTipoContribuyente(ClaseUtil.aDto(entidad.getEnTipoContribuyente(), TipoContribuyenteDto.class));
        }
        
        return entidadRegistradaDto;
    }

    @Override
    public EntidadDto actualizarEntidad(Long id, EntidadDto entidadDto)
    {
        // Buscar la entidad existente por su ID
        Optional<Entidad> entidadOptional = entidadRepository.findById(id);
        if (!entidadOptional.isPresent())
        {
            throw new EntityNotFoundException("Entidad no encontrada con el ID: " + id);
        }

        Entidad entidadExistente = entidadOptional.get();

        // Actualizar los campos de la entidad con los valores del DTO
        if (entidadDto.getIdTipoDocumento() != null)
        {
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(entidadDto.getIdTipoDocumento())
                                                                 .orElse(null);
            entidadExistente.setEnTipoDocumento(tipoDocumento);
        }

        if (entidadDto.getIdTipoContribuyente() != null)
        {
            TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(entidadDto.getIdTipoContribuyente())
                                                                             .orElse(null);
            entidadExistente.setEnTipoContribuyente(tipoContribuyente);
        }

        entidadExistente.setNroDocumento(entidadDto.getNroDocumento());
        entidadExistente.setRazonSocial(entidadDto.getRazonSocial());
        entidadExistente.setNombreComercial(entidadDto.getNombreComercial());
        entidadExistente.setDireccion(entidadDto.getDireccion());
        entidadExistente.setTelefono(entidadDto.getTelefono());
        entidadExistente.setEstado(entidadDto.getEstado());

        // Guardar la entidad actualizada en la base de datos
        Entidad entidadActualizada = entidadRepository.save(entidadExistente);

        // Convertir la entidad actualizada a DTO
        EntidadDto entidadActualizadaDto = ClaseUtil.aDto(entidadActualizada, EntidadDto.class);

        // Establecer los IDs de tipo de documento y tipo de contribuyente en el DTO
        if (entidadActualizada.getEnTipoDocumento() != null)
        {
            entidadActualizadaDto.setIdTipoDocumento(entidadActualizada.getEnTipoDocumento().getId());
            entidadActualizadaDto.setEnTipoDocumento(ClaseUtil.aDto(entidadActualizada.getEnTipoDocumento(), TipoDocumentoDto.class));
        }

        if (entidadActualizada.getEnTipoContribuyente() != null)
        {
            entidadActualizadaDto.setIdTipoContribuyente(entidadActualizada.getEnTipoContribuyente().getId());
            entidadActualizadaDto.setEnTipoContribuyente(ClaseUtil.aDto(entidadActualizada.getEnTipoContribuyente(), TipoContribuyenteDto.class));
        }

        return entidadActualizadaDto;
    }


    @Override
    public void eliminarEntidad(Long id)
    {
        entidadRepository.deleteById(id);
    }
	
}

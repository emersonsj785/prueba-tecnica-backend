package com.sintad.prueba.util;

import java.util.ArrayList;
import java.util.List;

import com.sintad.prueba.model.dto.ApiDto;

public class ClaseUtil
{
	public static <T, D> List<D> aDtoLista(List<T> entidades, Class<D> dtoClase)
	{
        List<D> dtos = new ArrayList<>();
        for (T entidad : entidades)
        {
            dtos.add(aDto(entidad, dtoClase));
        }
        return dtos;
    }

    public static <T, D> D aDto(T entidad, Class<D> dtoClase)
    {
        try
        {
            D dto = dtoClase.getDeclaredConstructor().newInstance();
            org.springframework.beans.BeanUtils.copyProperties(entidad, dto);
            return dto;
        } catch (Exception ex)
        {
            throw new RuntimeException("Error al mapear entidad a dto: " + ex.getMessage());
        }
    }

    public static <T, D> T aEntidad(D dto, Class<T> entidadClase)
    {
        try
        {
            T entidad = entidadClase.getDeclaredConstructor().newInstance();
            org.springframework.beans.BeanUtils.copyProperties(dto, entidad);
            return entidad;
        } catch (Exception ex)
        {
            throw new RuntimeException("Error al mapear dto a entidad: " + ex.getMessage());
        }
    }
    
    /**
     * Crea una respuesta API en formato JSON.
     * @param codeStatus el código de estado HTTP
     * @param mensaje el mensaje de respuesta
     * @param data datos adicionales
     */
	public static ApiDto respuestaApi(String codeStatus, String mensaje, String data)
	{
		return ApiDto.
				builder().
				codeStatus(codeStatus).
				mensaje(mensaje).
				data(data).
				build();
	}
}

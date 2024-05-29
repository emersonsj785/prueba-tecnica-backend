package com.sintad.prueba.generic;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sintad.prueba.util.ClaseUtil;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public abstract class GenericServiceImpl<T, D> implements IGenericService<T, D>
{

    private final JpaRepository<T, Long> repositorio;
    private final Class<T> entidadClase;
    private final Class<D> dtoClase;

    @SuppressWarnings("unchecked")
    public GenericServiceImpl(JpaRepository<T, Long> repositorio, Class<T> entidadClase, Class<D> dtoClase)
    {
        this.repositorio = repositorio;
        this.entidadClase = entidadClase;
        this.dtoClase = dtoClase;
    }

    @Override
    public D guardar(D dto)
    {
        T entidad = ClaseUtil.aEntidad(dto, entidadClase);
        return ClaseUtil.aDto(repositorio.save(entidad), dtoClase);
    }

    @Override
    public List<D> buscarTodos()
    {
        return repositorio.findAll().stream()
                .map(entidad -> ClaseUtil.aDto(entidad, dtoClase))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<D> buscarPorId(Long id)
    {
        return repositorio.findById(id)
                .map(entidad -> ClaseUtil.aDto(entidad, dtoClase));
    }

    @Override
    public void eliminarPorId(Long id)
    {
        repositorio.deleteById(id);
    }

    @Override
    public D actualizar(Long id, D dto)
    {
        T entidad = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));
        BeanUtils.copyProperties(ClaseUtil.aEntidad(dto, entidadClase), entidad, "id");
        return ClaseUtil.aDto(repositorio.save(entidad), dtoClase);
    }
}
package com.sintad.prueba.generic;

import java.util.List;
import java.util.Optional;

public interface IGenericService <T, D>
{
    public D guardar(D dto);
    public List<D> buscarTodos();
    public Optional<D> buscarPorId(Long id);
    public void eliminarPorId(Long id);
    public D actualizar(Long id, D dto);
}

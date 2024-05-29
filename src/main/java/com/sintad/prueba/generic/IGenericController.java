package com.sintad.prueba.generic;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IGenericController <D>
{
    ResponseEntity<D> guardar(@RequestBody D dto);
    ResponseEntity<List<D>> buscarTodos();
    ResponseEntity<D> buscarPorId(@PathVariable Long id);
    ResponseEntity<Void> eliminarPorId(@PathVariable Long id);
    ResponseEntity<D> actualizar(@PathVariable Long id, @RequestBody D dto);
}

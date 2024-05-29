package com.sintad.prueba.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class GenericController<T, D> implements IGenericController<D>
{

    private final IGenericService<T, D> servicio;

    @Autowired
    public GenericController(IGenericService<T, D> servicio)
    {
        this.servicio = servicio;
    }

    @PostMapping
    @Override
    public ResponseEntity<D> guardar(@RequestBody D dto)
    {
        return ResponseEntity.ok(servicio.guardar(dto));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<D>> buscarTodos()
    {
        return ResponseEntity.ok(servicio.buscarTodos());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<D> buscarPorId(@PathVariable Long id)
    {
        Optional<D> dto = servicio.buscarPorId(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id)
    {
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<D> actualizar(@PathVariable Long id, @RequestBody D dto)
    {
        return ResponseEntity.ok(servicio.actualizar(id, dto));
    }
}
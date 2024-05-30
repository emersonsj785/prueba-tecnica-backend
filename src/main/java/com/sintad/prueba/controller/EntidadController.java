package com.sintad.prueba.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sintad.prueba.model.dto.EntidadDto;
import com.sintad.prueba.service.IEntidadService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/entidades")
@AllArgsConstructor
public class EntidadController
{

    private IEntidadService entidadService;

    @GetMapping
    public ResponseEntity<List<EntidadDto>> listarTodos()
    {
        List<EntidadDto> entidades = this.entidadService.listarTodos();
        return new ResponseEntity<>(entidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadDto> buscarPorId(@PathVariable Long id)
    {
        Optional<EntidadDto> entidadDto = this.entidadService.buscarPorId(id);
        return entidadDto.map(entidad -> new ResponseEntity<>(entidad, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntidadDto> registrarEntidad(@RequestBody EntidadDto entidadDto)
    {
        EntidadDto entidadRegistrada = this.entidadService.registrarEntidad(entidadDto);
        return new ResponseEntity<>(entidadRegistrada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadDto> actualizarEntidad(@PathVariable Long id, @RequestBody EntidadDto entidadDto)
    {
        EntidadDto entidadActualizada = this.entidadService.actualizarEntidad(id, entidadDto);
        if (entidadActualizada != null)
        {
            return new ResponseEntity<>(entidadActualizada, HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEntidad(@PathVariable Long id)
    {
        this.entidadService.eliminarEntidad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

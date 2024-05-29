package com.sintad.prueba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sintad.prueba.model.dto.TipoContribuyenteDto;
import com.sintad.prueba.service.ITipoContribuyenteService;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-contribuyentes")
public class TipoContribuyenteController
{

    private final ITipoContribuyenteService tipoContribuyenteService;

    public TipoContribuyenteController(ITipoContribuyenteService tipoContribuyenteService)
    {
        this.tipoContribuyenteService = tipoContribuyenteService;
    }

    @GetMapping
    public ResponseEntity<List<TipoContribuyenteDto>> listarTodos()
    {
        List<TipoContribuyenteDto> tipoContribuyentes = tipoContribuyenteService.listarTodos();
        return ResponseEntity.ok(tipoContribuyentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContribuyenteDto> buscarPorId(@PathVariable Long id)
    {
        return tipoContribuyenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoContribuyenteDto> registrarTipoContribuyente(@RequestBody TipoContribuyenteDto tipoContribuyenteDto)
    {
        TipoContribuyenteDto savedTipoContribuyente = tipoContribuyenteService.registrarTipoContribuyente(tipoContribuyenteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoContribuyente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoContribuyenteDto> actualizarTipoContribuyente(@PathVariable Long id, @RequestBody TipoContribuyenteDto tipoContribuyenteDto)
    {
        TipoContribuyenteDto updatedTipoContribuyente = tipoContribuyenteService.actualizarTipoContribuyente(id, tipoContribuyenteDto);
        return ResponseEntity.ok(updatedTipoContribuyente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTipoContribuyente(@PathVariable Long id)
    {
        tipoContribuyenteService.eliminarTipoContribuyente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


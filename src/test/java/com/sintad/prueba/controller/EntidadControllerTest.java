package com.sintad.prueba.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.sintad.prueba.service.IEntidadService;
import com.sintad.prueba.model.dto.EntidadDto;

@WebMvcTest(controllers = EntidadController.class)
@ExtendWith(MockitoExtension.class)
public class EntidadControllerTest {
	
    @MockBean
    private IEntidadService service;
    private List<EntidadDto> listado;
    private EntidadDto l;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

        l = new EntidadDto();
        l.setId(9L);
        l.setIdTipoDocumento(2L);
        l.setNroDocumento("78941263");
        l.setRazonSocial("Mock Prueba");
        l.setNombreComercial("Mock Prueba Comercial");
        l.setIdTipoContribuyente(8L);
        l.setDireccion("Mock Direccion");
        l.setTelefono("78451296");
        l.setEstado(1);

        listado = new ArrayList<>();
        listado.add(l);
    }

    @Test
    public void itShouldReturnTheServiceValueWith200StatusCode() throws Exception {
        Mockito.when(service.listarTodos()).thenReturn(listado);
        ResultActions response = this.mockMvc.perform(get("/api/entidades").contentType(MediaType.APPLICATION_JSON));
        
        response.andExpect(status().isOk());
        
    }
}

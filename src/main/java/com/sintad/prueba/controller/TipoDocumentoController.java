package com.sintad.prueba.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sintad.prueba.model.dto.TipoDocumentoDto;
import com.sintad.prueba.generic.GenericController;
import com.sintad.prueba.model.TipoDocumento;
import com.sintad.prueba.service.impl.TipoDocumentoServiceImpl;

@RestController
@RequestMapping("/api/tipo-documentos")
public class TipoDocumentoController extends GenericController<TipoDocumento, TipoDocumentoDto>
{
    @Autowired
    public TipoDocumentoController(TipoDocumentoServiceImpl servicio)
    {
        super(servicio);
    }
}
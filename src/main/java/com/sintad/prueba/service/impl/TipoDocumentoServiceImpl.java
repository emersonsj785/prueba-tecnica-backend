package com.sintad.prueba.service.impl;

import org.springframework.stereotype.Service;

import com.sintad.prueba.generic.GenericServiceImpl;
import com.sintad.prueba.model.TipoDocumento;
import com.sintad.prueba.model.dto.TipoDocumentoDto;
import com.sintad.prueba.repository.ITipoDocumentoRepository;

@Service
public class TipoDocumentoServiceImpl extends GenericServiceImpl<TipoDocumento, TipoDocumentoDto>
{
    public TipoDocumentoServiceImpl(ITipoDocumentoRepository repositorio)
    {
        super(repositorio, TipoDocumento.class, TipoDocumentoDto.class);
    }
}
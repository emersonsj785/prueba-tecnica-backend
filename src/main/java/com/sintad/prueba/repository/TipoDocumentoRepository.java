package com.sintad.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sintad.prueba.model.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> 
{

}


package com.sintad.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sintad.prueba.model.Entidad;

public interface IEntidadRepository extends JpaRepository<Entidad, Long> 
{

}


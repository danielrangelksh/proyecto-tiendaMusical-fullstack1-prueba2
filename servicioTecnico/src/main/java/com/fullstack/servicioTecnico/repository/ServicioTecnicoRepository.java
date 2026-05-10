package com.fullstack.servicioTecnico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.servicioTecnico.model.ServicioTecnico;

public interface ServicioTecnicoRepository extends JpaRepository<ServicioTecnico, Integer> {
}

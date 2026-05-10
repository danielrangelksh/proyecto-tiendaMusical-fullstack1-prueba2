package com.fullstack.reclamos.repository;

import com.fullstack.reclamos.model.Reclamos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamosRepository extends JpaRepository<Reclamos, Integer> {
}

package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    List<Cuota> findByEstudiante(Estudiante estudiante);
}


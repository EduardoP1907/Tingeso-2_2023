package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    List<Cuota> findByEstudiante(Estudiante estudiante);
}


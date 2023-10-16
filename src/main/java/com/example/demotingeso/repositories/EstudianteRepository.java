package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {


    List<Estudiante> findByPagadoFalse();

    Estudiante findByRut(String rutEstudiante);
}



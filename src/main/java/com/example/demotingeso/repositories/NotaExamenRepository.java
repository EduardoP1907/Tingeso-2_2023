package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaExamenRepository extends JpaRepository<NotaExamen, Long> {
    List<NotaExamen> findByEstudiante(Estudiante estudiante);
    // Puedes agregar consultas personalizadas aquí si es necesario
}


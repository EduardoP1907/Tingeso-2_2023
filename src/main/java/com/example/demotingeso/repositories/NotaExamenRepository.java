package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.NotaExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaExamenRepository extends JpaRepository<NotaExamen, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}


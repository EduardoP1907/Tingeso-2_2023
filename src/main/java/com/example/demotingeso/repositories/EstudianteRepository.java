package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Métodos personalizados de repositorio, si es necesario
}



package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
import com.example.demotingeso.entities.PlanillaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanillaPagoRepository extends JpaRepository<PlanillaPago, Long> {
    PlanillaPago findByEstudiante(Estudiante estudiante);

}

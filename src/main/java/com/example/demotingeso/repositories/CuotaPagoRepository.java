package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.CuotaPago;
import com.example.demotingeso.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuotaPagoRepository extends JpaRepository<CuotaPago, Long> {
    List<CuotaPago> findByEstudiante(Estudiante estudiante);
}


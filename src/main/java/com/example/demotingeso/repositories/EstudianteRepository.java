package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    @Query("SELECT e FROM Estudiante e JOIN FETCH e.estudiantePagos ep " +
            "WHERE YEAR(ep.fechaPago) = :ano AND MONTH(ep.fechaPago) = :mes")
    List<Estudiante> findEstudiantesByMesYAno(@Param("mes") int mes, @Param("ano") int ano);

    List<Estudiante> findByPagadoFalse();
}



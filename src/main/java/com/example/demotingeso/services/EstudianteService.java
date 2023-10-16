package com.example.demotingeso.services;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
import com.example.demotingeso.entities.ReporteEstudiante;
import com.example.demotingeso.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.*;


@Service
public class EstudianteService {


    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService() {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {

        return estudianteRepository.save(estudiante);
    }

    public Estudiante obtenerestudianteporrut(String rut) {

        try {
            String jpql = "SELECT e FROM Estudiante e WHERE e.rut = :rut";
            TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);
            query.setParameter("rut", rut);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {

        return estudianteRepository.findAll();
    }

    public Estudiante obtenerEstudiantePorId(Long estudianteId) {

        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);

        if (estudianteOptional.isPresent()) {
            return estudianteOptional.get();
        } else {

            throw new EstudianteNotFoundException("Estudiante no encontrado con ID: " + estudianteId);
        }

    }



    public int anosDesdeEgreso(Estudiante estudiante) {

        int anoEgreso = estudiante.getAnoEgresoColegio();
        LocalDate fechaActual = LocalDate.now();
        int anoActual = fechaActual.getYear();
        int anosDesdeEgreso = anoActual - anoEgreso;

        return anosDesdeEgreso;
    }


}
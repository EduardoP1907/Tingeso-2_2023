package com.example.demotingeso.services;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
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
    public double calcularPromedioNotasPorMes(List<NotaExamen> notas) {
        double promedio = 0.0;
        int totalNotas = 0;
        LocalDate fechaActual = LocalDate.now();

        for (NotaExamen nota : notas) {
            if (nota.getFechaExamen().getMonth() == fechaActual.getMonth()) {
                promedio += nota.getPuntajeObtenido();
                totalNotas++;
            }
        }

        if (totalNotas > 0) {
            promedio /= totalNotas;
        }

        return promedio;
    }
    public double aplicarDescuentoArancelPorMes(double promedio) {
        if (promedio >= 950) {
            return 0.90;
        } else if (promedio >= 900) {
            return 0.95;
        } else if (promedio >= 850) {
            return 0.98;
        } else {
            return 1.0;
        }
    }

    public void calcularArancelPorMes(List<Estudiante> estudiantes, List<NotaExamen> notas) {
        for (Estudiante estudiante : estudiantes) {
            double promedio = calcularPromedioNotasPorMes(notas);
            double descuento = aplicarDescuentoArancelPorMes(promedio);

            // Aplicar el descuento al arancel del estudiante
            double arancelMensual = estudiante.getArancelMensual();
            double arancelConDescuento = arancelMensual * descuento;
            estudiante.setArancelMensual(arancelConDescuento);
        }
    }


}
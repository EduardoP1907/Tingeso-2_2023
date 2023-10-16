package com.example.demotingeso.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class ReporteEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rutEstudiante;
    private String nombreEstudiante;
    private int numeroExamenesRendidos;
    private double promedioPuntajeExamenes;
    private double montoArancelPagar;
    private String tipoPago;
    private int numeroCuotasPactadas;
    private int numeroCuotasPagadas;
    private double montoTotalPagado;
    private LocalDate fechaUltimoPago;
    private double saldoPorPagar;
    private int numeroCuotasRetraso;

    public void setNombre(String nombres) {
    }

    public void setRutEstudiante(String rut) {
    }

    // Getters y setters
}

package com.example.demotingeso.services;

import java.math.BigDecimal;

public class GenerarCuotasRequest {
    private Long estudianteId; // Identificador del estudiante
    private BigDecimal montoTotal; // Monto total a dividir en cuotas
    private int numeroCuotas; // Número de cuotas a generar

    // Getters y setters
    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }
}


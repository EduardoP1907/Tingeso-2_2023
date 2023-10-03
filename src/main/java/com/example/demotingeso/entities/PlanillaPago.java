package com.example.demotingeso.entities;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
@Table(name = "planillapago")
public class PlanillaPago {
    private int mes;
    private int ano;
    private List<Estudiante> estudiantesPagos;
    private BigDecimal montoTotal; // Declare montoTotal property
    private List<EstudiantePago> estudiantes; // Declare estudiantes property

    private Double descuentoPorPromedio;

    @ManyToOne
    private Estudiante estudiante;

    public PlanillaPago() {
        this.mes = mes;
        this.ano = ano;
        this.estudiantesPagos = estudiantesPagos;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Estudiante> getEstudiantesPagos() {
        return estudiantesPagos;
    }

    public void setEstudiantesPagos(List<Estudiante> estudiantesPagos) {
        this.estudiantesPagos = estudiantesPagos;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<EstudiantePago> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantesPagos = estudiantes;
    }


}

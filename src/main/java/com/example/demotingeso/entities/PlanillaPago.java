package com.example.demotingeso.entities;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "planillapago")
public class PlanillaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mes;
    private int ano;
    private Double montoTotal;
    private String rutEstudiante;

    @ManyToOne // Relación muchos-a-uno con Estudiante
    private Estudiante estudiante;

    @OneToMany(mappedBy = "planillaPago")
    private List<Cuota> cuotas;

    public PlanillaPago(int mes, int ano, Double montoTotal, Estudiante estudiante, List<Cuota> cuotas) {
        this.mes = mes;
        this.ano = ano;
        this.montoTotal = montoTotal;
        this.estudiante = estudiante;
        this.rutEstudiante = rutEstudiante;
        this.cuotas = cuotas;
    }
}

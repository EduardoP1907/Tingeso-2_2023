package com.example.demotingeso.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cuotapago")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private Double numeroCuota;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    private boolean pagada;
    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "planilla_pago_id") // Nombre de la columna que hace referencia a la planilla de pago
    private PlanillaPago planillaPago;

}



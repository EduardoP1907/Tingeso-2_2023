package com.example.demotingeso.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cuota_pago")
public class CuotaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    public void setEstudiante(Long estudiante) {
    }

    public void setMonto(BigDecimal montoCuota) {
    }

    public void setFechaVencimiento(Date date) {
    }

    // Getters y setters
}


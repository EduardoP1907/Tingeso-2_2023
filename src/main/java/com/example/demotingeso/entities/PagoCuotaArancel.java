package com.example.demotingeso.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class PagoCuotaArancel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CuotaPago cuotaPago;

    private BigDecimal montoPagado;
    private LocalDate fechaPago;

    public void setCuotaPago(CuotaPago cuotaPago) {
    }

    public void setMontoPagado(BigDecimal montoPagado) {
    }

    public void setFechaPago(LocalDate fechaPago) {
    }

    // Getters y setters
}


package com.example.demotingeso.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int montoTotal;

    public PlanillaPago(Long id, int mes, int ano, int montoTotal) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.montoTotal = montoTotal;
    }

}

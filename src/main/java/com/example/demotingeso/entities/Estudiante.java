package com.example.demotingeso.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Estudiante", unique = true, nullable = false, columnDefinition = "serial")
    private Long id;

    private String nombres;

    private String fechaNacimiento;

    private String apellidos;

    private String rut;


    private String tipoColegioDeProcedencia;

    private int anoEgresoColegio;
    private String nombreColegio;
    @Column(nullable = false)
    private boolean pagado;
    @Column(nullable = false)
    private double arancelMensual;
    @Column(nullable = false)
    private int numeroCuotas;


// ...


    public String getTipoColegioProcedencia() {
        return tipoColegioDeProcedencia;
    }

    public void setTipoColegioProcedencia(String tipoColegioProcedencia) {
        this.tipoColegioDeProcedencia = tipoColegioProcedencia;
    }

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Cuota> cuotasPagos;

    public int getAnoEgresoColegio() {
        return anoEgresoColegio;
    }

    public double getArancelMensual() {
        return arancelMensual;
    }

    public void setArancelMensual(double arancelMensual) {
        this.arancelMensual = arancelMensual;
    }


}



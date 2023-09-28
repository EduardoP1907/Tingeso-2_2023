package com.example.demotingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table (name = "estudiante")
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



// ...
    Estudiante estudiante = getEstudiante();
     // Reemplaza 1L con el ID del estudiante que deseas obtener

    int anoEgreso = estudiante.getAnoEgreso();

    // Obtiene la fecha actual
    LocalDate fechaActual = LocalDate.now();

    // Obtiene el año actual
    int anoActual = fechaActual.getYear();

    // Calcula los años transcurridos desde que egresó
    int anosDesdeEgreso = anoActual - anoEgreso;

    public String getTipoColegioProcedencia() {
        return tipoColegioDeProcedencia;
    }
    public void setTipoColegioProcedencia(String tipoColegioProcedencia) {
        this.tipoColegioDeProcedencia = tipoColegioProcedencia;
    }

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<CuotaPago> cuotasPagos;

}

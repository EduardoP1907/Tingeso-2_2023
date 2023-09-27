package com.example.demotingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @Column(name = "Nombres", unique = true, nullable = false)
    private String nombres;
    @Column(name = "Fecha de Nacimiento", unique = true, nullable = false)
    private String fechaNacimiento;
    @Column(name = "Apellidos", unique = true, nullable = false)
    private String apellidos;
    @Column(name = "Rut Estudiante", unique = true, nullable = false)
    private String rut;
    @Column(name = "Tipo Colegio Procedencia", unique = true, nullable = false)
    private String tipoColegioProcedencia;
    @Column(name = "Año de Egreso", unique = true, nullable = false)
    private int anoEgresoColegio;
    @Column(name = "Nombre de Colegio", unique = true, nullable = false)
    private String nombreColegio;

}

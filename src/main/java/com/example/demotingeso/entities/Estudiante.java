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
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<NotaExamen> notas;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Cuota> cuotasPagos;



    public String getTipoColegioProcedencia() {
        return tipoColegioDeProcedencia;
    }

    public void setTipoColegioProcedencia(String tipoColegioProcedencia) {
        this.tipoColegioDeProcedencia = tipoColegioProcedencia;
    }

    public int getAnoEgresoColegio() {
        return anoEgresoColegio;
    }

    public double getArancelMensual() {
        return arancelMensual;
    }

    public void setArancelMensual(double arancelMensual) {
        this.arancelMensual = arancelMensual;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombre(String nombres) {
        this.nombres = nombres;
    }
    public double promedioNotas() {
        if (notas == null || notas.isEmpty()) {
            return 0.0; // Si no hay notas, el promedio es cero.
        }

        double totalPuntajes = 0.0;
        for (NotaExamen nota : notas) {
            totalPuntajes += nota.getPuntajeObtenido();
        }

        return totalPuntajes / notas.size();
    }

    public void setPromedioNotas(double promedio) {
    }
    public List<Cuota> getCuotasPagos() {
        return cuotasPagos;
    }
}



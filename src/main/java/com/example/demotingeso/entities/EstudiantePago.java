package com.example.demotingeso.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "estudiante_pago")
public class EstudiantePago {
    private String nombreEstudiante;
    private double montoAPagar;
    private boolean pagado;
    @Id
    private Long id;

    public EstudiantePago(String nombreEstudiante, double montoAPagar, boolean pagado) {
        this.nombreEstudiante = nombreEstudiante;
        this.montoAPagar = montoAPagar;
        this.pagado = pagado;
    }

    public EstudiantePago() {

    }
    @Temporal(TemporalType.DATE) // Opcional: Si deseas mapear solo la fecha sin la hora
    @Column(name = "fecha_pago")
    private Date fechaPago;

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public double getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(double montoAPagar) {
        this.montoAPagar = montoAPagar;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "estudiante_id") // Nombre de la columna que hace referencia a Estudiante
    private Estudiante estudiante;
}

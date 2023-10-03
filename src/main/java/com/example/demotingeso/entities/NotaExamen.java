package com.example.demotingeso.entities;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class NotaExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    private LocalDate fechaExamen;
    private double puntajeObtenido;
    @Column (nullable = false)
    public void setEstudiante(Estudiante estudiante) {
    }
    @Column (nullable = false)
    public void setFechaExamen(LocalDate fechaExamen) {
    }
    @Column (nullable = false)
    public void setPuntajeObtenido(double puntajeObtenido) {
    }

    // Getters y setters

    // Constructores
}


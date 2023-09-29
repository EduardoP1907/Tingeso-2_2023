package com.example.demotingeso.services;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EstudiantePagoService {
    private final EstudianteRepository estudianteRepository;
    private EstudianteService estudianteService;

    @Autowired
    public EstudiantePagoService(EstudianteRepository estudianteRepository, EstudianteService estudianteService) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteService = estudianteService;
    }

    // Método para calcular el pago de un estudiante
    public BigDecimal calcularPagoEstudiante(Estudiante estudiante) {
        String tipoColegioProcedencia = estudiante.getTipoColegioProcedencia();


        // Obtiene los años desde que egresó del colegio
        int anosDesdeEgreso = estudianteService.anosDesdeEgreso(estudiante);

        // Define el monto de la matrícula y el arancel de estudio

        BigDecimal montoArancel = BigDecimal.valueOf(1500000);

        // Aplica el descuento de acuerdo al tipo de colegio de procedencia
        BigDecimal descuentoTipoColegio = BigDecimal.ZERO;
        if ("Municipal".equals(tipoColegioProcedencia)) {
            descuentoTipoColegio = montoArancel.multiply(BigDecimal.valueOf(0.20)); // 20% de descuento
        } else if ("Subvencionado".equals(tipoColegioProcedencia)) {
            descuentoTipoColegio = montoArancel.multiply(BigDecimal.valueOf(0.10)); // 10% de descuento
        }

        // Aplica el descuento de acuerdo a los años desde que egresó del colegio
        BigDecimal descuentoAnosEgreso = BigDecimal.ZERO;
        if (anosDesdeEgreso < 1) {
            descuentoAnosEgreso = montoArancel.multiply(BigDecimal.valueOf(0.15)); // 15% de descuento
        } else if (anosDesdeEgreso >= 1 && anosDesdeEgreso <= 2) {
            descuentoAnosEgreso = montoArancel.multiply(BigDecimal.valueOf(0.08)); // 8% de descuento
        } else if (anosDesdeEgreso >= 3 && anosDesdeEgreso <= 4) {
            descuentoAnosEgreso = montoArancel.multiply(BigDecimal.valueOf(0.04)); // 4% de descuento
        }

        // Calcula el monto total con descuentos
        BigDecimal montoTotal = montoArancel.subtract(descuentoTipoColegio);

        return montoTotal;
    }
}



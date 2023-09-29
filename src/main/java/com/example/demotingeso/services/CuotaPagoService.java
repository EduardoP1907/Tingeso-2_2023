package com.example.demotingeso.services;

import com.example.demotingeso.entities.CuotaPago;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.repositories.CuotaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CuotaPagoService {
    private final EstudianteService estudianteService;
    private final CuotaPagoRepository cuotaPagoRepository;

    @Autowired
    public CuotaPagoService(EstudianteService estudianteService, CuotaPagoRepository cuotaPagoRepository) {
        this.estudianteService =  estudianteService;
        this.cuotaPagoRepository = cuotaPagoRepository;
    }

    public BigDecimal generarCuotasDePago(Long estudianteId) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        // Obtiene el tipo de colegio de procedencia del estudiante como una cadena de texto
        String tipoColegioProcedencia = estudiante.getTipoColegioProcedencia();


        // Obtiene los años desde que egresó del colegio
        int anosDesdeEgreso = estudianteService.anosDesdeEgreso(estudiante);

        // Define el monto de la matrícula y el arancel de estudio
        BigDecimal montoMatricula = BigDecimal.valueOf(70000);
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
        BigDecimal montoTotalConDescuentos = montoMatricula.add(montoArancel)
                .subtract(descuentoTipoColegio)
                .subtract(descuentoAnosEgreso);

        // Aquí ya tienes el monto total con descuentos
        // Ahora puedes calcular el número de cuotas y el monto de cada cuota según tus reglas

        // Por ejemplo, generamos 12 cuotas mensuales
        int numeroCuotas = 10;
        BigDecimal montoCuota = montoTotalConDescuentos.divide(BigDecimal.valueOf(numeroCuotas), 2, RoundingMode.HALF_UP);
        LocalDate fechaVencimiento = LocalDate.now().plusMonths(1); // Primera cuota vence en un mes

        for (int i = 1; i <= numeroCuotas; i++) {
            CuotaPago cuota = new CuotaPago();
            cuota.setMonto(montoCuota);
            cuota.setNumeroCuota(i);
            cuota.setFechaVencimiento(fechaVencimiento);
            cuota.setPagada(false); // Inicialmente no pagada
            cuota.setEstudiante(estudiante);

            cuotaPagoRepository.save(cuota);

            fechaVencimiento = fechaVencimiento.plusMonths(1); // Siguiente cuota en el próximo mes
        }
        return montoMatricula;
    }



}


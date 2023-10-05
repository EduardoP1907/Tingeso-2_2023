package com.example.demotingeso.services;

import com.example.demotingeso.Excepciones.CuotaPagoAlreadyPaidException;
import com.example.demotingeso.Excepciones.CuotaPagoNotFoundException;
import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.repositories.CuotaRepository;
import com.example.demotingeso.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class CuotaService {
    private final EstudianteService estudianteService;
    private final CuotaRepository cuotaRepository;
    private EstudianteRepository estudianteRepository;

    @Autowired
    public CuotaService(EstudianteService estudianteService, CuotaRepository cuotaRepository) {
        this.estudianteService = estudianteService;
        this.cuotaRepository = cuotaRepository;
        this.estudianteRepository = estudianteRepository;

    }

    public List<Cuota> listarCuotasDeEstudiante(Long estudianteId) {
        // Aquí implementamos la lógica para listar las cuotas de un estudiante
        // Primero, debemos obtener el estudiante por su ID
        // Luego, buscamos todas las cuotas asociadas a ese estudiante
        // Supongamos que tienes una relación entre Estudiante y CuotaPago en tus entidades

        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId); // Implementa este método según tus necesidades
        if (estudiante == null) {
            throw new EstudianteNotFoundException("No se encontró un estudiante con el ID proporcionado.");
        }

        List<Cuota> cuotasDelEstudiante = cuotaRepository.findByEstudiante(estudiante);

        return cuotasDelEstudiante;
    }

    public int generarCuotasDePago(Long estudianteId) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        // Obtiene el tipo de colegio de procedencia del estudiante como una cadena de texto
        String tipoColegioProcedencia = estudiante.getTipoColegioProcedencia();


        // Obtiene los años desde que egresó del colegio
        int anosDesdeEgreso = estudianteService.anosDesdeEgreso(estudiante);

        // Define el monto de la matrícula y el arancel de estudio
        int montoMatricula = 70000;
        int montoArancel = 1500000;

        // Aplica el descuento de acuerdo al tipo de colegio de procedencia
        double descuentoTipoColegio = 0;
        if ("Municipal".equals(tipoColegioProcedencia)) {
            descuentoTipoColegio = (montoArancel * 0.2); // 20% de descuento
        } else if ("Subvencionado".equals(tipoColegioProcedencia)) {
            descuentoTipoColegio = montoArancel * 0.1; // 10% de descuento
        }

        // Aplica el descuento de acuerdo a los años desde que egresó del colegio
        Double descuentoAnosEgreso = 0.0;
        if (anosDesdeEgreso < 1) {
            descuentoAnosEgreso = montoArancel*0.15; // 15% de descuento
        } else if (anosDesdeEgreso >= 1 && anosDesdeEgreso <= 2) {
            descuentoAnosEgreso = montoArancel*0.08; // 8% de descuento
        } else if (anosDesdeEgreso >= 3 && anosDesdeEgreso <= 4) {
            descuentoAnosEgreso = montoArancel*0.04; // 4% de descuento
        }

        // Calcula el monto total con descuentos
        double montoTotalConDescuentos = montoMatricula+montoArancel
                -descuentoTipoColegio
                -descuentoAnosEgreso;

        // Aquí ya tienes el monto total con descuentos
        // Ahora puedes calcular el número de cuotas y el monto de cada cuota según tus reglas

        // Por ejemplo, generamos 10 cuotas mensuales
        Double numeroCuotas = 10.0;
        double montoCuota = montoTotalConDescuentos/numeroCuotas;
        LocalDate fechaVencimiento = LocalDate.now().plusMonths(1); // Primera cuota vence en un mes

        for (int i = 1; i <= numeroCuotas; i++) {
            Cuota cuota = new Cuota();
            cuota.setMonto(montoCuota);
            cuota.setNumeroCuota(numeroCuotas);
            cuota.setFechaVencimiento(fechaVencimiento);
            cuota.setPagada(false); // Inicialmente no pagada
            cuota.setEstudiante(estudiante);
            cuota.setFechaPago(LocalDate.now());

            cuotaRepository.save(cuota);

            fechaVencimiento = fechaVencimiento.plusMonths(1); // Siguiente cuota en el próximo mes
        }
        return montoMatricula;
    }
    public void registrarPagoCuota(Long cuotaPagoId) {
        // Obtener la cuota de arancel por su ID
        Cuota cuota = cuotaRepository.findById(cuotaPagoId)
                .orElseThrow(() -> new CuotaPagoNotFoundException("No se encontró la cuota de arancel con el ID proporcionado."));

        // Validar que la cuota no esté pagada previamente
        if (cuota.isPagada()) {
            throw new CuotaPagoAlreadyPaidException("La cuota de arancel ya ha sido pagada.");
        }

        // Marcar la cuota como pagada
        cuota.setPagada(true);
        cuotaRepository.save(cuota);
    }
}




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
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
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


        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        if (estudiante == null) {
            throw new EstudianteNotFoundException("No se encontró un estudiante con el ID proporcionado.");
        }

        List<Cuota> cuotasDelEstudiante = cuotaRepository.findByEstudiante(estudiante);

        return cuotasDelEstudiante;
    }

    public int generarCuotasDePago(Long estudianteId, int numeroCuotas) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        String tipoColegioProcedencia = estudiante.getTipoColegioProcedencia();

        int anosDesdeEgreso = estudianteService.anosDesdeEgreso(estudiante);
        int montoMatricula = 70000;
        int montoArancel = 1500000;

        double descuentoTipoColegio = 0;
        if ("Municipal".equals(tipoColegioProcedencia)) {
            descuentoTipoColegio = (montoArancel * 0.2);
        } else if ("Subvencionado".equals(tipoColegioProcedencia)) {
            descuentoTipoColegio = montoArancel * 0.1;
        }

        double descuentoAnosEgreso = 0.0;
        if (anosDesdeEgreso < 1) {
            descuentoAnosEgreso = montoArancel * 0.15; // 15% de descuento
        } else if (anosDesdeEgreso >= 1 && anosDesdeEgreso <= 2) {
            descuentoAnosEgreso = montoArancel * 0.08; // 8% de descuento
        } else if (anosDesdeEgreso >= 3 && anosDesdeEgreso <= 4) {
            descuentoAnosEgreso = montoArancel * 0.04; // 4% de descuento
        }

        double montoTotalConDescuentos = montoMatricula + montoArancel - descuentoTipoColegio - descuentoAnosEgreso;

        double montoCuota = montoTotalConDescuentos / numeroCuotas;
        LocalDate fechaVencimiento = LocalDate.now().plusMonths(1);

        for (int i = 1; i <= numeroCuotas; i++) {
            Cuota cuota = new Cuota();
            cuota.setMonto(montoCuota);
            cuota.setNumeroCuota((double) i);
            cuota.setFechaVencimiento(fechaVencimiento);
            cuota.setPagada(false);
            cuota.setEstudiante(estudiante);
            cuota.setFechaPago(LocalDate.now());

            cuotaRepository.save(cuota);

            fechaVencimiento = fechaVencimiento.plusMonths(1);
        }
        return montoMatricula;
    }
    public void registrarPagoCuota(Long cuotaPagoId) {

        Cuota cuota = cuotaRepository.findById(cuotaPagoId)
                .orElseThrow(() -> new CuotaPagoNotFoundException("No se encontró la cuota de arancel con el ID proporcionado."));


        if (cuota.isPagada()) {
            throw new CuotaPagoAlreadyPaidException("La cuota de arancel ya ha sido pagada.");
        }

        // Marcar la cuota como pagada
        cuota.setPagada(true);
        cuotaRepository.save(cuota);
    }
    public void pagarCuota(Long cuotaId) {
        Cuota cuota = cuotaRepository.findById(cuotaId)
                .orElseThrow(() -> new CuotaPagoNotFoundException("No se encontró la cuota de arancel con el ID proporcionado."));

        if (cuota.isPagada()) {
            throw new CuotaPagoAlreadyPaidException("La cuota de arancel ya ha sido pagada.");
        }

        cuota.setPagada(true);
        cuota.setFechaPago(LocalDate.now());
        cuotaRepository.save(cuota);
    }
    public List<Cuota> obtenerCuotasPorEstudiante(Estudiante estudiante) {
        return cuotaRepository.findByEstudiante(estudiante);
    }

    public Cuota obtenerCuotaPorId(Long cuotaId) {
        return cuotaRepository.findById(cuotaId).orElse(null);
    }

    public void guardarCuota(Cuota cuota) {
        cuotaRepository.save(cuota);
    }
    public List<Cuota> obtenerTodasLasCuotasDelEstudiante(Estudiante estudiante) {
        return cuotaRepository.findByEstudiante(estudiante);
    }

    public List<Cuota> obtenerCuotasPendientesPorEstudiante(Estudiante estudiante) {
        List<Cuota> cuotasPendientes = new ArrayList<>();


        List<Cuota> todasLasCuotasDelEstudiante = obtenerTodasLasCuotasDelEstudiante(estudiante);

        for (Cuota cuota : todasLasCuotasDelEstudiante) {
            if (!cuota.isPagada()) {
                cuotasPendientes.add(cuota);
            }
        }

        return cuotasPendientes;
    }
    public double calcularMontoArancel(Estudiante estudiante) {
        double arancelBase = 1500000;
        double montoArancel = 0.0;


        if (estudiante.getTipoColegioProcedencia().equals("Municipal")) {
            montoArancel = arancelBase - (arancelBase * 0.20);
        } else if (estudiante.getTipoColegioProcedencia().equals("Subvencionado")) {
            montoArancel = arancelBase - (arancelBase * 0.10);
        } else {
            montoArancel = arancelBase;
        }

        int anosEgreso = estudiante.getAnoEgresoColegio();
        if (anosEgreso < 1) {
            montoArancel -= arancelBase * 0.15;
        } else if (anosEgreso >= 1 && anosEgreso <= 2) {
            montoArancel -= arancelBase * 0.08;
        } else if (anosEgreso >= 3 && anosEgreso <= 4) {
            montoArancel -= arancelBase * 0.04;
        }


        double promedioNotas = estudiante.promedioNotas();
        if (promedioNotas >= 950) {
            montoArancel *= 0.90;
        } else if (promedioNotas >= 900) {
            montoArancel *= 0.95;
        } else if (promedioNotas >= 850) {
            montoArancel *= 0.98;
        }


        List<Cuota> cuota = estudiante.getCuotasPagos();
        int mesesAtraso = calcularMesesAtraso(cuota);
        if (mesesAtraso > 3) {
            montoArancel *= 1.15;
        }

        return montoArancel;
    }
    public int calcularMesesAtraso(List<Cuota> fechaVencimiento) {
        LocalDate fechaActual = LocalDate.now();
        YearMonth ymVencimiento = YearMonth.from((TemporalAccessor) fechaVencimiento);
        YearMonth ymActual = YearMonth.from(fechaActual);

        long mesesAtraso = ChronoUnit.MONTHS.between(ymVencimiento, ymActual);

        return (int) Math.max(mesesAtraso, 0);
    }

}





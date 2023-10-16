package com.example.demotingeso.services;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
import com.example.demotingeso.entities.PlanillaPago;
import com.example.demotingeso.repositories.CuotaRepository;
import com.example.demotingeso.repositories.EstudianteRepository;
import com.example.demotingeso.repositories.PlanillaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

@Service
public class PlanillaPagoService {

    private final PlanillaPagoRepository planillaPagoRepository;
    private final EstudianteService estudianteService;
    private final CuotaService cuotaService;
    private EstudianteRepository estudianteRepository;
    private CuotaRepository cuotaRepository;

    @Autowired
    public PlanillaPagoService(PlanillaPagoRepository planillaPagoRepository, EstudianteService estudianteService, CuotaService cuotaService, EstudianteRepository estudianteRepository, CuotaRepository cuotaRepository) {
        this.planillaPagoRepository = planillaPagoRepository;
        this.estudianteService = estudianteService;
        this.cuotaService = cuotaService;
        this.estudianteRepository = estudianteRepository;
        this.cuotaRepository = cuotaRepository;
    }

    public PlanillaPago generarPlanillaDePago(Long estudianteId, int mes, int ano) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElse(null);

        if (estudiante == null) {
            throw new EstudianteNotFoundException("Estudiante no encontrado");
        }

        Double montoArancel = calcularMontoArancel(estudiante);
        PlanillaPago planillaPago = new PlanillaPago();
        planillaPago.setMes(mes);
        planillaPago.setAno(ano);
        planillaPago.setMontoTotal(montoArancel);
        planillaPago.setEstudiante(estudiante);

        planillaPago = planillaPagoRepository.save(planillaPago);

        return planillaPago;
    }
    public double calcularPromedioNotasPorMes(List<NotaExamen> notas) {
        double promedio = 0.0;
        int totalNotas = 0;
        LocalDate fechaActual = LocalDate.now();

        for (NotaExamen nota : notas) {
            if (nota.getFechaExamen().getMonth() == fechaActual.getMonth()) {
                promedio += nota.getPuntajeObtenido();
                totalNotas++;
            }
        }

        if (totalNotas > 0) {
            promedio /= totalNotas;
        }

        return promedio;
    }
    public double aplicarDescuentoArancelPorMes(double promedio) {
        if (promedio >= 950) {
            return 0.90;
        } else if (promedio >= 900) {
            return 0.95;
        } else if (promedio >= 850) {
            return 0.98;
        } else {
            return 1.0;
        }
    }

    public void calcularArancelPorMes(List<Estudiante> estudiantes, List<NotaExamen> notas) {
        for (Estudiante estudiante : estudiantes) {
            double promedio = calcularPromedioNotasPorMes(notas);
            double descuento = aplicarDescuentoArancelPorMes(promedio);

            double arancelMensual = estudiante.getArancelMensual();
            double arancelConDescuento = arancelMensual * descuento;
            estudiante.setArancelMensual(arancelConDescuento);
        }
    }

    public PlanillaPago obtenerPlanillaPorEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        return planillaPagoRepository.findByEstudiante(estudiante);
    }

    public void guardarPlanillaPago(PlanillaPago planillaPago) {
        planillaPagoRepository.save(planillaPago);
    }
    public PlanillaPago getPlanillaPago(Long id) {
        Optional<PlanillaPago> planillaPagoOptional = planillaPagoRepository.findById(id);

        if (planillaPagoOptional.isPresent()) {
            return planillaPagoOptional.get();
        } else {
            return null;
        }
    }
    public int calcularMesesAtraso(List<Cuota> fechaVencimiento) {
        LocalDate fechaActual = LocalDate.now();
        YearMonth ymVencimiento = YearMonth.from((TemporalAccessor) fechaVencimiento);
        YearMonth ymActual = YearMonth.from(fechaActual);

        long mesesAtraso = ChronoUnit.MONTHS.between(ymVencimiento, ymActual);

        return (int) Math.max(mesesAtraso, 0);
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
}



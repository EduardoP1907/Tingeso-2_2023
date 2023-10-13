package com.example.demotingeso.services;

import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.PlanillaPago;
import com.example.demotingeso.repositories.PlanillaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanillaPagoService {

    private final PlanillaPagoRepository planillaPagoRepository;
    private final EstudianteService estudianteService;
    private final CuotaService cuotaService;

    @Autowired
    public PlanillaPagoService(PlanillaPagoRepository planillaPagoRepository, EstudianteService estudianteService, CuotaService cuotaService) {
        this.planillaPagoRepository = planillaPagoRepository;
        this.estudianteService = estudianteService;
        this.cuotaService = cuotaService;
    }

    public PlanillaPago generarPlanillaDePago(Long estudianteId, int mes, int ano) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        List<Cuota> cuotas = cuotaService.obtenerCuotasPorEstudiante(estudiante);
        Double montoTotal = cuotas.stream().mapToDouble(Cuota::getMonto).sum();

        PlanillaPago planillaPago = new PlanillaPago(mes, ano, montoTotal, estudiante, cuotas);
        planillaPagoRepository.save(planillaPago);

        return planillaPago;
    }

    public PlanillaPago obtenerPlanillaPorEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        return planillaPagoRepository.findByEstudiante(estudiante);
    }

    public void pagarCuota(Long cuotaId) {
        Cuota cuota = cuotaService.obtenerCuotaPorId(cuotaId);
        cuota.setPagada(true);
        cuota.setFechaPago(LocalDate.now());
        cuotaService.guardarCuota(cuota);
    }
    public void guardarPlanillaPago(PlanillaPago planillaPago) {
        planillaPagoRepository.save(planillaPago);
    }
}


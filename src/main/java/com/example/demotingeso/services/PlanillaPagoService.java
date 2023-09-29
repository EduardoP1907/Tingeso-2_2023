package com.example.demotingeso.services;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.PlanillaPago;
import com.example.demotingeso.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlanillaPagoService {
    private final EstudianteRepository estudianteRepository;
    private final EstudiantePagoService estudiantePagoService;
    private  final CuotaPagoService cuotaPagoService;
    @Autowired
    public PlanillaPagoService(EstudianteRepository estudianteRepository, EstudiantePagoService estudiantePagoService, CuotaPagoService cuotaPagoService) {
        this.estudianteRepository = estudianteRepository;
        this.estudiantePagoService = estudiantePagoService;
        this.cuotaPagoService = cuotaPagoService;
    }


    public PlanillaPago calcularPlanilla(@RequestParam int mes, @RequestParam int ano) {

        // Obtener la lista de estudiantes para el mes y año especificados
        List<Estudiante> estudiantes = estudianteRepository.findEstudiantesByMesYAno(mes, ano);

        // Calcular los pagos para cada estudiante y acumular el monto total
        BigDecimal montoTotal = BigDecimal.ZERO;
        for (Estudiante estudiante : estudiantes) {
            BigDecimal pagoEstudiante = cuotaPagoService.generarCuotasDePago(estudiante.getId());
            montoTotal = montoTotal.add(pagoEstudiante);
        }

        // Crear una instancia de PlanillaPago con los resultados
        PlanillaPago planillaPago = new PlanillaPago();
        planillaPago.setMes(mes);
        planillaPago.setAno(ano);
        planillaPago.setMontoTotal(montoTotal);
        planillaPago.setEstudiantes(estudiantes);

        return planillaPago;
    }


}

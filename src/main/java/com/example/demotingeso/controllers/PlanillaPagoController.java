package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.PlanillaPago;
import com.example.demotingeso.services.CuotaService;
import com.example.demotingeso.services.EstudianteService;
import com.example.demotingeso.services.PlanillaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/planillapago")
public class PlanillaPagoController {

    private final EstudianteService estudianteService;
    private final CuotaService cuotaService;
    private final PlanillaPagoService planillaPagoService;

    @Autowired
    public PlanillaPagoController(EstudianteService estudianteService, CuotaService cuotaService, PlanillaPagoService planillaPagoService) {
        this.estudianteService = estudianteService;
        this.cuotaService = cuotaService;
        this.planillaPagoService = planillaPagoService;
    }

    @GetMapping("/buscar-estudiante")
    public String mostrarBusquedaEstudiante(Model model) {
        return "buscarestudianteporrut2";
    }

    @GetMapping("/buscar-estudiante2")
    public String buscarEstudiantePorRut(@RequestParam String rut, Model model) {
        Estudiante estudiante = estudianteService.obtenerestudianteporrut(rut);

        if (estudiante != null) {
            List<Cuota> cuotas = cuotaService.obtenerCuotasPorEstudiante(estudiante);
            PlanillaPago planilla = new PlanillaPago();
            planilla.setEstudiante(estudiante);
            planilla.setRutEstudiante(estudiante.getRut());
            planilla.setCuotas(cuotas);

            Double montoTotal = cuotas.stream().mapToDouble(Cuota::getMonto).sum();
            planilla.setMontoTotal(montoTotal);

            planillaPagoService.guardarPlanillaPago(planilla);

            model.addAttribute("planilla", planilla);
        }

        return "pagarcuotas";
    }
}

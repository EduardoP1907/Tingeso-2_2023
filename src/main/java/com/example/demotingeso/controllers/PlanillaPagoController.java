package com.example.demotingeso.controllers;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.PlanillaPago;
import com.example.demotingeso.services.CuotaService;
import com.example.demotingeso.services.EstudianteService;
import com.example.demotingeso.services.PlanillaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<PlanillaPago> getPlanillaPago(@PathVariable Long id) {
        PlanillaPago planillaPago = planillaPagoService.getPlanillaPago(id);
        if (planillaPago == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(planillaPago, HttpStatus.OK);
    }

    @PostMapping("/generar-planilla")
    public ResponseEntity<PlanillaPago> generarPlanillaPago(@RequestParam Long estudianteId, @RequestParam int mes, @RequestParam int ano) {
        try {
            PlanillaPago planillaPago = planillaPagoService.generarPlanillaDePago(estudianteId, mes, ano);
            return new ResponseEntity<>(planillaPago, HttpStatus.CREATED);
        } catch (EstudianteNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar-estudiante")
    public String mostrarBusquedaEstudiante(Model model) {
        return "buscar-estudiante";
    }
    @GetMapping("/generar-planilla")
    public String mostrarGenerarPlanilla(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "planillapagos";
    }
}

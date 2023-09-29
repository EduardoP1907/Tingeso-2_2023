package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.repositories.EstudianteRepository;
import com.example.demotingeso.services.EstudianteService;
import com.example.demotingeso.services.PlanillaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/planilla-pago")
public class PlanillaPagoController {

    private final PlanillaPagoService planillaPagoService;

    @Autowired
    public PlanillaPagoController(PlanillaPagoService planillaPagoService) {
        this.planillaPagoService = planillaPagoService;
    }

        @Autowired
        private EstudianteRepository estudianteRepository;

        @Autowired
        private EstudianteService estudiantePagoService;

        @GetMapping("/calcularplanilla")
        public String calcularPlanilla(@RequestParam int mes, @RequestParam int ano) {

            List<Estudiante> estudiantes = estudianteRepository.findEstudiantesByMesYAno(mes, ano);

            return "/redirect:calcularplanilla";
        }
    }





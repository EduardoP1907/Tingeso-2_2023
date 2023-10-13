package com.example.demotingeso.controllers;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.PlanillaPago;
import com.example.demotingeso.services.CuotaService;
import com.example.demotingeso.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;
    private CuotaService cuotaService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService, CuotaService cuotaService) {
        this.estudianteService = estudianteService;
        this.cuotaService = cuotaService;
    }


    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute Estudiante estudiante, @RequestParam int numeroCuotas) {
        System.out.println(estudiante);
        estudianteService.registrarEstudiante(estudiante);
        cuotaService.generarCuotasDePago(estudiante.getId(), numeroCuotas);
        return "index";
    }

    @GetMapping("/lista")
    public String mostrarListaEstudiantes(org.springframework.ui.Model model) {

        List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        System.out.printf(estudiantes.get(2).toString());
        return "listaEstudiante";
    }

    @GetMapping("/obtenerestudiante/{id}")

    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id, Model model) {


        try {
            Estudiante estudiante = estudianteService.obtenerEstudiantePorId(id);

            return ResponseEntity.ok(estudiante);

        } catch (EstudianteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("registro")
    public String registro(Model model){
        return "registro";
    }

    @GetMapping("/buscar-estudiante")
    public String mostrarBusquedaEstudiante(Model model) {

        return "buscarestudianteporrut";
    }

    @GetMapping("/buscar-estudiante2")
    public String mostrarBusquedaEstudiante2(Model model) {

        return "buscarestudianteporrut2";
    }

    @PostMapping("/buscar-estudiante2")
    public String buscarEstudiantePorRut(@RequestParam String rut, Model model) {

        Estudiante estudiante = estudianteService.obtenerestudianteporrut(rut);

        if (estudiante != null) {

            PlanillaPago planilla = new PlanillaPago();
            planilla.setEstudiante(estudiante);
            planilla.setRutEstudiante(estudiante.getRut());
            planilla.setCuotas(estudiante.getCuotasPagos());

        }


        return "redirect:/estudiantes/pagar-cuota";
    }
    @GetMapping("pagar-cuota")
    public String pagarcuota(Model model){
        return "pagarcuotas";
    }


}



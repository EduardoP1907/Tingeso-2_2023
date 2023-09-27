package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;


   /* @GetMapping("/registro")
    public String mostrarFormularioRegistro(org.springframework.ui.Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "registro";
    }*/


    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute Estudiante estudiante) {
        System.out.println(estudiante);
        estudianteService.registrarEstudiante(estudiante);
        return "index";
    }

    @GetMapping("/lista")
    public String mostrarListaEstudiantes(org.springframework.ui.Model model) {

        List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        System.out.printf(estudiantes.get(2).toString());
        return "listaEstudiante";
    }
}



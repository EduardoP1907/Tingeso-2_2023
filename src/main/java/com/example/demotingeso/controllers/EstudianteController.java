package com.example.demotingeso.controllers;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Estudiante;
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
    private  CuotaService cuotaService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }


    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute Estudiante estudiante) {
        System.out.println(estudiante);
        estudianteService.registrarEstudiante(estudiante);
        cuotaService.generarCuotasDePago(estudiante.getId());
        return "registro";
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
        // Utiliza el servicio para obtener un estudiante por su ID

        try {
            Estudiante estudiante = estudianteService.obtenerEstudiantePorId(id);

            return ResponseEntity.ok(estudiante);

        } catch (EstudianteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/buscar-estudiante")
    public String buscarEstudiantePorRut(@RequestParam("rut") String rut, Model model) {
        // Realiza la búsqueda del estudiante por su RUT
        Estudiante estudiante = estudianteService.obtenerestudianteporrut(rut);

        if (estudiante != null) {
            // Si se encuentra el estudiante, lo agregamos al modelo y lo mostramos en una vista
            model.addAttribute("estudianteEncontrado", estudiante);
            return "vista-estudiante-encontrado"; // Reemplaza con el nombre de tu vista
        } else {
            // Si no se encuentra el estudiante, puedes mostrar un mensaje de error o redirigir a otra vista
            return "vista-estudiante-no-encontrado"; // Reemplaza con el nombre de tu vista
        }
    }


}



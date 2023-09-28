package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.repositories.EstudianteRepository;
import com.example.demotingeso.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


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



    @GetMapping("/obtener-estudiante/{id}") // Cambia "/obtener-estudiante/{id}" a la ruta que necesites
    public String obtenerEstudiante(@PathVariable Long id, Model model) {
        // Utiliza el servicio para obtener un estudiante por su ID
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(id);

        if (estudiante != null) {
            // Aquí puedes realizar operaciones con el estudiante obtenido
            int anoEgreso = estudiante.getAnoEgreso();
            // Resto del código...

            // Luego, puedes agregar el estudiante o sus datos al modelo para usarlos en la vista
            model.addAttribute("estudiante", estudiante);
        } else {
            // Manejo de error si el estudiante no se encuentra
        }

        return "vista"; // Reemplaza "vista" con el nombre de tu plantilla HTML
    }

    EstudianteService estudianteService = new EstudianteService();
    Estudiante estudiante = estudianteService.obtenerEstudiantePorId(1L); // Reemplaza 1L con el ID del estudiante que deseas obtener
}



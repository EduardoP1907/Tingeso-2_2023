package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes/")
    public class EstudianteController {
        private final EstudianteService estudianteService;


        @Autowired
        public EstudianteController(EstudianteService estudianteService) {
            this.estudianteService = estudianteService;
        }

        @PostMapping("/registrar")
        public Estudiante registrarEstudiante(@RequestBody Estudiante estudiante) {
            return estudianteService.registrarEstudiante(estudiante);
        }

        @GetMapping("/{id}")
        public Estudiante obtenerEstudiantePorId(@PathVariable Long id) {
            return estudianteService.obtenerEstudiantePorId(id);
        }

        @GetMapping("/todos")
        public List<Estudiante> obtenerTodosLosEstudiantes() {
            return estudianteService.obtenerTodosLosEstudiantes();
        }
    }


package com.example.demotingeso.services;

import com.example.demotingeso.Excepciones.EstudianteNotFoundException;
import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService() {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {

         return estudianteRepository.save(estudiante);
    }
    public ArrayList<Estudiante> obtenerestudianteporut(){
        return (ArrayList<Estudiante>) estudianteRepository.findAll();
    }
    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }


    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerEstudiante(Long estudianteId) {
        // Implementa la lógica para obtener un estudiante por su ID
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);

        if (estudianteOptional.isPresent()) {
            return estudianteOptional.get();
        } else {
            // Puedes lanzar una excepción si el estudiante no se encuentra
            throw new EstudianteNotFoundException("Estudiante no encontrado con ID: " + estudianteId);
        }

    }

    public boolean eliminarUsuario(Long id) {
        try{
            estudianteRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }



}
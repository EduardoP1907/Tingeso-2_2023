package com.example.demotingeso.controllers;

import com.example.demotingeso.services.NotaExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/notas-examen")
public class NotaExamenController {
    private final NotaExamenService notaExamenService;

    @Autowired
    public NotaExamenController(NotaExamenService notaExamenService) {
        this.notaExamenService = notaExamenService;
    }

    @PostMapping("/importar")
    public ResponseEntity<String> importarNotas(@RequestParam("archivo") MultipartFile archivo) {
        try {
            notaExamenService.importarNotasDesdeExcel(archivo);
            return ResponseEntity.ok("Notas importadas con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al importar notas.");
        }
    }
}

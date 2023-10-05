package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cuotas-pago")
public class CuotaController {

    private final CuotaService cuotaService;


    @Autowired
    public CuotaController(CuotaService cuotaService) {
        this.cuotaService = cuotaService;
    }





    @PostMapping("/generar-cuotas/{estudianteId}")
    public String generarCuotasDePago(@PathVariable Long estudianteId) {
        cuotaService.generarCuotasDePago(estudianteId);
        return "redirect:/estudiantes/lista";
    }

    @GetMapping("/estudiante/{estudianteId}")
    public List<Cuota> listarCuotasDeEstudiante(@PathVariable Long estudianteId) {
        return cuotaService.listarCuotasDeEstudiante(estudianteId);
    }

}


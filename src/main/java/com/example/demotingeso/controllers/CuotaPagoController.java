package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.CuotaPago;
import com.example.demotingeso.services.CuotaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cuotas-pago")
public class CuotaPagoController {
    private final CuotaPagoService cuotaPagoService;


    @Autowired
    public CuotaPagoController(CuotaPagoService cuotaPagoService) {
        this.cuotaPagoService = cuotaPagoService;
    }


    // Otros métodos relacionados con cuotas de pago



    @PostMapping("/generar-cuotas/{estudianteId}")
    public String generarCuotasDePago(@PathVariable Long estudianteId) {
        cuotaPagoService.generarCuotasDePago(estudianteId);
        return "redirect:/estudiantes/lista";
    }

    @GetMapping("/estudiante/{estudianteId}")
    public List<CuotaPago> listarCuotasDeEstudiante(@PathVariable Long estudianteId) {
        return cuotaPagoService.listarCuotasDeEstudiante(estudianteId);
    }

}


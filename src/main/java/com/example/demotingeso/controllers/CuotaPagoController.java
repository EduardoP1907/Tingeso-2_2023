package com.example.demotingeso.controllers;

import com.example.demotingeso.services.CuotaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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





}


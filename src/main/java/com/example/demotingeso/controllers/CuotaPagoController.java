package com.example.demotingeso.controllers;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.services.CuotaPagoService;
import com.example.demotingeso.services.GenerarCuotasRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;


@RestController
@RequestMapping("/cuotas-pago")
public class CuotaPagoController {
    private final CuotaPagoService cuotaPagoService;

    @Autowired
    public CuotaPagoController(CuotaPagoService cuotaPagoService) {
        this.cuotaPagoService = cuotaPagoService;
    }


    // Otros métodos relacionados con cuotas de pago

    @PostMapping("/generar")
    public ResponseEntity<String> generarCuotas(@RequestBody GenerarCuotasRequest request) {
        // Implementa la lógica para obtener el estudiante y otros datos necesarios
        Long estudiante = request.getEstudianteId();
        BigDecimal montoTotal = request.getMontoTotal();
        int numeroCuotas = request.getNumeroCuotas();

        cuotaPagoService.generarCuotas(estudiante, montoTotal, numeroCuotas);

        return ResponseEntity.ok("Cuotas generadas correctamente.");
    }



}


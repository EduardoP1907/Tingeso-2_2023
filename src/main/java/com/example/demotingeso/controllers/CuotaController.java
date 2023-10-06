package com.example.demotingeso.controllers;

import com.example.demotingeso.Excepciones.CuotaPagoAlreadyPaidException;
import com.example.demotingeso.Excepciones.CuotaPagoNotFoundException;
import com.example.demotingeso.entities.Cuota;
import com.example.demotingeso.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cuotas")
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
    @PostMapping("/pagar")
    public ResponseEntity<String> pagarCuota(@RequestParam Long cuotaId) {
        try {
            cuotaService.pagarCuota(cuotaId);
            return ResponseEntity.ok("Cuota pagada con éxito.");
        } catch (CuotaPagoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cuota.");
        } catch (CuotaPagoAlreadyPaidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuota ya ha sido pagada.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el pago de la cuota.");
        }
    }

}


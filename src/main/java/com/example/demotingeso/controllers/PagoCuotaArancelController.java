package com.example.demotingeso.controllers;

import com.example.demotingeso.services.PagoCuotaArancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/pagos-cuotas-arancel")
public class PagoCuotaArancelController {
    private final PagoCuotaArancelService pagoCuotaArancelService;

    @Autowired
    public PagoCuotaArancelController(PagoCuotaArancelService pagoCuotaArancelService) {
        this.pagoCuotaArancelService = pagoCuotaArancelService;
    }

    @PostMapping("/registrarpagos")
    public ResponseEntity<String> registrarPagoCuotaArancel(
            @RequestParam Long cuotaPagoId,
            @RequestParam BigDecimal montoPagado,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaPago) {
        pagoCuotaArancelService.registrarPagoCuotaArancel(cuotaPagoId, montoPagado, fechaPago);
        return ResponseEntity.ok("Pago registrado exitosamente.");
    }
}

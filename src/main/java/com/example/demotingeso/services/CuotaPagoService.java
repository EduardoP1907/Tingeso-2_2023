package com.example.demotingeso.services;

import com.example.demotingeso.entities.CuotaPago;
import com.example.demotingeso.repositories.CuotaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class CuotaPagoService {
    private final CuotaPagoRepository cuotaPagoRepository;

    @Autowired
    public CuotaPagoService(CuotaPagoRepository cuotaPagoRepository) {
        this.cuotaPagoRepository = cuotaPagoRepository;
    }

    public void generarCuotas(Long estudiante, BigDecimal montoTotal, int numeroCuotas) {
        BigDecimal montoCuota = montoTotal.divide(BigDecimal.valueOf(numeroCuotas), 2, BigDecimal.ROUND_HALF_UP);

        for (int i = 0; i < numeroCuotas; i++) {
            CuotaPago cuota = new CuotaPago();
            cuota.setEstudiante(estudiante);
            cuota.setMonto(montoCuota);


            cuota.setFechaVencimiento(new Date(System.currentTimeMillis() + (i + 1) * 30L * 24 * 60 * 60 * 1000));

            cuotaPagoRepository.save(cuota);
        }
    }
}

package com.example.demotingeso.services;

import com.example.demotingeso.Excepciones.CuotaPagoAlreadyPaidException;
import com.example.demotingeso.Excepciones.CuotaPagoNotFoundException;
import com.example.demotingeso.entities.CuotaPago;
import com.example.demotingeso.entities.PagoCuotaArancel;
import com.example.demotingeso.repositories.CuotaPagoRepository;
import com.example.demotingeso.repositories.PagoCuotaArancelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PagoCuotaArancelService {
    private final CuotaPagoRepository cuotaPagoRepository;
    private final PagoCuotaArancelRepository pagoCuotaArancelRepository;

    @Autowired
    public PagoCuotaArancelService(CuotaPagoRepository cuotaPagoRepository, PagoCuotaArancelRepository pagoCuotaArancelRepository) {
        this.cuotaPagoRepository = cuotaPagoRepository;
        this.pagoCuotaArancelRepository = pagoCuotaArancelRepository;
    }

    public void registrarPagoCuotaArancel(Long cuotaPagoId, BigDecimal montoPagado, LocalDate fechaPago) {
        // Obtener la cuota de arancel por su ID
        CuotaPago cuotaPago = cuotaPagoRepository.findById(cuotaPagoId)
                .orElseThrow(() -> new CuotaPagoNotFoundException("No se encontró la cuota de arancel con el ID proporcionado."));

        // Validar que la cuota no esté pagada previamente
        if (cuotaPago.isPagada()) {
            throw new CuotaPagoAlreadyPaidException("La cuota de arancel ya ha sido pagada.");
        }

        // Realizar el registro del pago
        PagoCuotaArancel pago = new PagoCuotaArancel();
        pago.setCuotaPago(cuotaPago);
        pago.setMontoPagado(montoPagado);
        pago.setFechaPago(fechaPago);

        pagoCuotaArancelRepository.save(pago);

        // Marcar la cuota como pagada
        cuotaPago.setPagada(true);
        cuotaPagoRepository.save(cuotaPago);
    }
}


package com.example.demotingeso.repositories;

import com.example.demotingeso.entities.PagoCuotaArancel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoCuotaArancelRepository extends JpaRepository<PagoCuotaArancel, Long> {
    // Puedes agregar métodos personalizados si es necesario
}

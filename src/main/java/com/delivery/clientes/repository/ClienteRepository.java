package com.delivery.clientes.repository;

import com.delivery.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}

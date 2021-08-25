package com.delivery.clientes.services;

import com.delivery.clientes.dto.ClienteRequestDTO;
import com.delivery.clientes.dto.ClienteResponse;
import com.delivery.clientes.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ClienteService {

    List<ClienteResponse> findAll();

    Page<Cliente> findAll(Pageable pageable);

    ClienteResponse findById(Integer id);

    Cliente findByIdEntity(Integer id);

    ClienteResponse save(ClienteRequestDTO cliente);

    void delete (Integer id);

    ClienteResponse update(Integer id, ClienteRequestDTO clienteRequest);

}

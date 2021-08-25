package com.delivery.clientes.repository;

import com.delivery.clientes.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

    Usuario findByEmail(String email);

}

package com.delivery.clientes.services;

import com.delivery.clientes.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);


}

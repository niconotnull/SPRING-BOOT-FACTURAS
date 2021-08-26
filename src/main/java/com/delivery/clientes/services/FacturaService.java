package com.delivery.clientes.services;

import com.delivery.clientes.entity.Factura;
import com.delivery.clientes.entity.Producto;

import java.util.List;

public interface FacturaService {

    Factura save(Factura factura);

    void delete(Integer  id);

    Factura update(Factura factura);

    Factura findById(Integer id);

    List<Producto> findByNombreProducto(String nombre);

}

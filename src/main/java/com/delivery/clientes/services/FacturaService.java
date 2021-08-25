package com.delivery.clientes.services;

import com.delivery.clientes.entity.Factura;

public interface FacturaService {

    Factura save(Factura factura);

    void delete(Integer  id);

    Factura update(Factura factura);

    Factura findById(Integer id);

}

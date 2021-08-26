package com.delivery.clientes.services;

import com.delivery.clientes.entity.Factura;
import com.delivery.clientes.entity.Producto;
import com.delivery.clientes.exception.BDException;
import com.delivery.clientes.repository.FacturaRepository;
import com.delivery.clientes.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Factura save(Factura factura) {
        try {
            return facturaRepository.save(factura);
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            facturaRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public Factura update(Factura factura) {
        try {
            Factura facturaRes = findById(factura.getId());
            facturaRes.setDescripcion(facturaRes.getDescripcion());
            facturaRes.setObservacion(factura.getObservacion());
            return facturaRepository.save(facturaRes);
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public Factura findById(Integer id) {
        try {
            return facturaRepository.findById(id).orElse(null);
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public List<Producto> findByNombreProducto(String nombre) {
        try {
            return productoRepository.findByNombreContainingIgnoreCase(nombre);
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }
}

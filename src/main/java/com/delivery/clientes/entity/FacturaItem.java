package com.delivery.clientes.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FACTURA_ITEM")
@Data
public class FacturaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;    // si no se define, se asigna de forma automática

    public Double getImporte() {
        return cantidad.doubleValue() * producto.getPrecio();
    }
}

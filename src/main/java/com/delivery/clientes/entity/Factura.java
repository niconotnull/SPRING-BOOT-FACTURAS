package com.delivery.clientes.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FACTURA")
@Data
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;

    private String observacion;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties({"factura","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id") // si no se define, se asigna de forma automática
    private Cliente cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")    // Este si es necesario
    private List<FacturaItem> items;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Double getTotal() {
        Double total = 0.00;
        for (FacturaItem item : this.items) {
            total += item.getImporte();
        }
        return total;
    }

    public Factura() {
        this.items = new ArrayList<>();
    }
}

package com.delivery.clientes.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCTO")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Double precio;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}

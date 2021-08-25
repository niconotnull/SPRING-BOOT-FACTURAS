package com.delivery.clientes.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20)
    private String nombre;

}

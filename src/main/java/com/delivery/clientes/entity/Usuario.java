package com.delivery.clientes.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)
    private String password;

    private boolean enabled;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Permite definir los nombres del tabla para no tomar los nombres por defecto
    // El  user_id role_id se definen como unicos a través del UniqueConstraint, es decir no se podrá definir varias veces un rol para un usuario y viceversa
    // Un usuario no podrá tener repetido un rol
    @JoinTable(name="user_authorities", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})})
    private List<Role> roles;

}

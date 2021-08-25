package com.delivery.clientes.dto;

import com.delivery.clientes.entity.Factura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private Date createAt;
    private String urlFoto;
    private String urlFoto2;
    private List<Factura> facturas;

}

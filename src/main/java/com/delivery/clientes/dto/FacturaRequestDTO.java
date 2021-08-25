package com.delivery.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonPropertyOrder({"idcliente"})
public class FacturaRequestDTO {


    @NotEmpty
    @JsonProperty("idcliente")
    private Integer idCliente;

}

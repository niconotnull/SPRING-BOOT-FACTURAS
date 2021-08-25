package com.delivery.clientes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Cliente")
@Data
@JsonPropertyOrder({"nombre","apellido","email","createAt","urlFoto","urlFoto2"})
public class ClienteRequestDTO implements Serializable {

	private static final long serialVersionUID = 3510798629761270195L;

    @NotEmpty
	@ApiModelProperty(name="nombre", value="Nombre del Cliente", dataType = "String", example = "Nicolas", position = 1)
    @JsonProperty("nombre")
    private String nombre;

    @NotEmpty
    @ApiModelProperty(name="apellido", value="Apellido del Cliente", dataType = "String", example = "Jimenez" ,position = 2)
    @JsonProperty("apellido")
    private String apellido;

    @ApiModelProperty(name="email", value="Email del Cliente", dataType = "String", example = "prueba@gmail.com", position = 3)
    @JsonProperty("email")
    @NotBlank
    private String email;

    @ApiModelProperty(name="createAt", value="Fecha de creacion", dataType = "String", example = "17/08/1987", position = 4)
    @JsonProperty("createAt")
    private Date createAt;

    @ApiModelProperty(name="urlFoto", value="Url de la foto", dataType = "String", example = "url", position = 5)
    @JsonProperty("urlFoto")
    private String urlFoto;

    @ApiModelProperty(name="urlFoto2", value="Url de la foto2", dataType = "String", example = "url", position = 6)
    @JsonProperty("urlFoto2")
    private String urlFoto2;

    @Override
    public String toString() {
         return new Gson().toJson(this, ClienteRequestDTO.class);
    }
}

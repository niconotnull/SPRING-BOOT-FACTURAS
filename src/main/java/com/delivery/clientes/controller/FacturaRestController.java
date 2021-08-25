package com.delivery.clientes.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import com.delivery.clientes.entity.Factura;
import com.delivery.clientes.exception.BDException;
import com.delivery.clientes.services.FacturaService;
import com.delivery.clientes.util.BindingErrorsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
@Api(tags = "Facturas", value = "Servicios para procesar las facturas")
public class FacturaRestController {

    @Autowired
    private BindingErrorsResult errors;

    @Autowired
    private FacturaService facturaService;

    @ApiOperation(value = "Método para guardar una factura.")
    @PostMapping(value = "/factura", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFactura(@Valid @RequestBody Factura factura, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<Map<String, Object>>(errors.responseErrors(result), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<Factura>(facturaService.save(factura), HttpStatus.OK);
        } catch (BDException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @ApiOperation(value = "Método que permite eliminar una factura")
    @DeleteMapping(value = "/factura/{idFactura}")
    public ResponseEntity<?> deleteFactura(@PathVariable Integer idFactura) {
        try {
            facturaService.delete(idFactura);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BDException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}

package com.delivery.clientes.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.delivery.clientes.dto.ClienteRequestDTO;
import com.delivery.clientes.dto.ClienteResponse;
import com.delivery.clientes.exception.BDException;
import com.delivery.clientes.services.ClienteService;
import com.delivery.clientes.entity.Cliente;
import com.delivery.clientes.util.BindingErrorsResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
@Api(tags="Clientes", value="Microservice de Clientes")
public class ClienteRestController {

   @Autowired
   private ClienteService clienteService;

   @Autowired
   private BindingErrorsResult errors;

    @ApiOperation(value = "Método que permite obtener la lista de clientes.")
    @GetMapping(value="/clients", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteResponse>> findAllClients(){
        try {
            return  new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
        }catch (BDException e){
            e.printStackTrace();
            throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "Método que permite obtener la lista de cliente de forma paginado")
    @GetMapping(value = "clients/page/{page}")
    public ResponseEntity<?> findAllClientsPegeable(@PathVariable Integer page) {
        try {

//            Pageable pageable = PageRequest.of(page, 4);
            Pageable pageable = PageRequest.of(page,4, Sort.by("id").descending());
            return new ResponseEntity<Page<Cliente>>(clienteService.findAll(pageable), HttpStatus.OK);
        } catch (BDException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "Método que permite buscar un cliente por id.")
    @Secured({"ROLE_ADMIN","ROL_USER"})
    @GetMapping(value ="/client/{clienteId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> findByCliente(@PathVariable Integer clienteId) {
        try {
            return   new ResponseEntity<>(clienteService.findByIdEntity(clienteId), HttpStatus.OK);
        } catch (BDException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage() );
        }
    }

    @ApiOperation(value="Método que permite guardar un cliente.")
    @Secured("ROLE_ADMIN")
    @PostMapping(value="/client", produces =  APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> saveClient (@Valid @RequestBody ClienteRequestDTO clienteRequest, BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<Map<String, Object>>(errors.responseErrors(result), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<ClienteResponse>(clienteService.save(clienteRequest), HttpStatus.OK)  ;
        }catch (BDException e){
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation(value="Método que permite eliminar un cliente")
    @Secured("ROLE_ADMIN")
    @DeleteMapping(value="/client/{clienteId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clienteId){
        try{
            clienteService.delete(clienteId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (BDException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @ApiOperation(value="Método que permite actualizar un cliente.")
    @Secured("ROLE_ADMIN")
    @PutMapping(value="/client/{clienteId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCliente(@Valid @RequestBody ClienteRequestDTO clienteRequest, BindingResult result, @PathVariable Integer clienteId  ){
        if (result.hasErrors()) {
            return new ResponseEntity<Map<String, Object>>(errors.responseErrors(result), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<ClienteResponse>(clienteService.update(clienteId, clienteRequest), HttpStatus.OK);
        }catch (BDException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}

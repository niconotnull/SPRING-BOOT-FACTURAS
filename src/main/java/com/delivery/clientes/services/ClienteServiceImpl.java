package com.delivery.clientes.services;

import com.delivery.clientes.dto.ClienteRequestDTO;
import com.delivery.clientes.dto.ClienteResponse;
import com.delivery.clientes.entity.Cliente;
import com.delivery.clientes.exception.BDException;
import com.delivery.clientes.repository.ClienteRepository;
import com.delivery.clientes.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements   ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UploadUtil uploadUitl;

    @Override
    public List<ClienteResponse> findAll(){
        try {
            List<Cliente> listClients =  clienteRepository.findAll();

            return listClients.stream().map(c->{
                ClienteResponse client = new ClienteResponse();
                client.setId(c.getId());
                client.setNombre(c.getNombre());
                client.setApellido(c.getApellido());
                client.setEmail(c.getEmail());
                client.setCreateAt(c.getCreateAt());
                client.setUrlFoto(c.getUrlFoto());
                client.setUrlFoto2(c.getUrlFoto2());
               return client;
            }).collect(Collectors.toList());

        }catch (DataAccessException e){
          throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        try {
            return clienteRepository.findAll(pageable);
        }catch (DataAccessException e){
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public ClienteResponse findById(Integer id) {
        try {
            Cliente cliente = clienteRepository.findById(id).orElse(null);
            if(cliente== null){
                throw new BDException("No existe el clienteId "+id);
            }else{
                System.out.println(cliente.getFacturas().toString());
            }
            return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getCreateAt(), cliente.getUrlFoto(), cliente.getUrlFoto2(), cliente.getFacturas());
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public Cliente findByIdEntity(Integer id) {
        try {
            Cliente cliente = clienteRepository.findById(id).orElse(null);
            if(cliente== null){
                throw new BDException("No existe el clienteId "+id);
            }
            return cliente;
        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public ClienteResponse save(ClienteRequestDTO clienteRequest) {
          try {
              Cliente cliente = new Cliente();
              cliente.setNombre(clienteRequest.getNombre());
              cliente.setApellido(clienteRequest.getApellido());
              cliente.setEmail(clienteRequest.getEmail());
              cliente.setCreateAt(new Date());
              cliente.setUrlFoto(clienteRequest.getUrlFoto());
              cliente.setUrlFoto2(clienteRequest.getUrlFoto2());
              clienteRepository.save(cliente);
             return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getCreateAt(), cliente.getUrlFoto(), cliente.getUrlFoto2(),null);
          }catch (DataAccessException e ){
              throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
          }
    }

    @Override
    public void delete(Integer id) {
        try{
            Cliente cliente =  clienteRepository.findById(id).orElse(null);
            uploadUitl.deleteFotoAnterior(cliente.getUrlFoto2());
            clienteRepository.deleteById(id);
        }catch (DataAccessException e){
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    public ClienteResponse update(Integer id, ClienteRequestDTO clienteRequest) {
        try {
            ClienteResponse cliente = findById(id);
            if (cliente != null) {
                cliente.setNombre(clienteRequest.getNombre());
                cliente.setApellido(clienteRequest.getApellido());
                cliente.setEmail(clienteRequest.getEmail());
                cliente.setUrlFoto(clienteRequest.getUrlFoto());
                cliente.setUrlFoto2(clienteRequest.getUrlFoto2());

            Cliente newClient = new Cliente();
                newClient.setId(cliente.getId());
                newClient.setNombre(cliente.getNombre());
                newClient.setApellido(cliente.getApellido());
                newClient.setEmail(cliente.getEmail());
                newClient.setCreateAt(cliente.getCreateAt());
                newClient.setUrlFoto(cliente.getUrlFoto());
                newClient.setUrlFoto2(cliente.getUrlFoto2());

                clienteRepository.save(newClient);
                return new ClienteResponse(newClient.getId(), newClient.getNombre(), newClient.getApellido(), newClient.getEmail(), newClient.getCreateAt(), newClient.getUrlFoto(),newClient.getUrlFoto2(), null);
            } else {
                throw new BDException("El cliente no se puede actualizar no existe : " + id);
            }

        } catch (DataAccessException e) {
            throw new BDException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }

    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}

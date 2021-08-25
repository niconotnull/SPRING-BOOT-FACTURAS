package com.delivery.clientes.services;

import com.delivery.clientes.entity.Cliente;
import com.delivery.clientes.exception.UploadException;
import com.delivery.clientes.repository.ClienteRepository;
import com.delivery.clientes.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService{

    private final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

@Autowired
private ClienteRepository clienteRepository;

@Autowired
private UploadUtil uploadUitl;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file, Integer id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente != null) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
            try {
                Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
                log.info(rutaArchivo.toString());

                Files.copy(file.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                throw new UploadException("Error al subir la imagen " + e.getMessage());
            }
            uploadUitl.deleteFotoAnterior(cliente.getUrlFoto2());

            cliente.setUrlFoto2(nombreArchivo);
            clienteRepository.save(cliente);
            response.put("cliente", cliente);
            response.put("message", "Has subido correctamente  la imagen " + nombreArchivo);
        }else{
            throw new UploadException("No existe el cliente con el id: "+id );
        }
        return response;
    }

    @Override
    public Resource getResourceImage(String namePicture) {
        Path rutaArchivo = Paths.get("uploads").resolve(namePicture).toAbsolutePath();
        Resource resource = null;
        log.info(rutaArchivo.toString());
        try{
            resource = new UrlResource(rutaArchivo.toUri());
            if(!resource.exists() && !resource.isReadable()){
                throw new UploadException("No se pudo cargar la imagen");
            }else{
                return resource;
            }
        }catch (MalformedURLException e){
            throw new UploadException(e.getMessage());
        }
    }
}

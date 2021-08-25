package com.delivery.clientes.controller;

import com.delivery.clientes.exception.BDException;
import com.delivery.clientes.exception.UploadException;
import com.delivery.clientes.services.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
@Api(tags="Upload", value = "Servicio que permite la carga  de  archivos")
public class UploadRestController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "Servicio que permite la carga  de  archivos")
    @Secured({"ROLE_ADMIN","ROL_USER"})
    @PostMapping("/client/upload/image")
    public ResponseEntity<?> upload(@RequestParam(value = "file", required = true) MultipartFile file,
                                    @RequestParam(value = "id", required = true) Integer id) {
        System.out.println("Estoy aqui");
        if (!file.isEmpty()) {
            try {
                return new ResponseEntity<Map<String, Object>>(uploadService.uploadFile(file, id), HttpStatus.CREATED);

            } catch (UploadException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Método que permite obtener la imagen")
    @GetMapping("/client/upload/image/{namePicture:.+}")
    public ResponseEntity<?> getImage(@PathVariable String namePicture) {

        try {
            HttpHeaders headers = new HttpHeaders();
            Resource resource = uploadService.getResourceImage(namePicture);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
            return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
        } catch (UploadException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}

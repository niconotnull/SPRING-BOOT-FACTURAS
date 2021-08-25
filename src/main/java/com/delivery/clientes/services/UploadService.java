package com.delivery.clientes.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {


    Map<String, Object> uploadFile(MultipartFile file, Integer id);

    Resource getResourceImage(String namePicture);

}

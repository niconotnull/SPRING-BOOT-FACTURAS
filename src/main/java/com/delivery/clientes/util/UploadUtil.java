package com.delivery.clientes.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadUtil {

    public void deleteFotoAnterior(String nombreFotoAnterior) {
        if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
            Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
            File archivoAnterior = rutaFotoAnterior.toFile();
            if (archivoAnterior.exists() && archivoAnterior.canRead()) {
                archivoAnterior.delete();
            }
        }

    }

}

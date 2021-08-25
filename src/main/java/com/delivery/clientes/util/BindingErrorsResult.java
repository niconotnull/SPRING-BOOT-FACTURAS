package com.delivery.clientes.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BindingErrorsResult {

    public Map<String, Object> responseErrors(BindingResult result){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            errors= result.getFieldErrors().stream().map(err-> "El campo '"+err.getField()+"' "+err.getDefaultMessage()
            ).collect(Collectors.toList());
           response.put("errors",errors);
        }
        return response;
    }

}

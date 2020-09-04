package com.dxctraining.customermgt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFound(CustomerNotFoundException e){
        System.out.println("inside handleCustomerNotFound");
        String msg=e.getMessage();
        return msg;
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidArgument(InvalidArgumentException e){
        System.out.println("inside handleInvalidArgument");
        return e.getMessage();
    }
}

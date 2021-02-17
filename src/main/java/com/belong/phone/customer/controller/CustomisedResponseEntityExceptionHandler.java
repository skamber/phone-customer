package com.belong.phone.customer.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.belong.phone.customer.exception.NoDataFoundException;
import com.belong.phone.customer.model.CustomerPhoneResponse;



@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // this is for any throwable exception type
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomerPhoneResponse> handleAllExceptions(Exception ex, WebRequest request) {
        CustomerPhoneResponse exceptionResponse = new CustomerPhoneResponse(ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //this is the response object if no phones found
    @ExceptionHandler(NoDataFoundException.class)
    public final ResponseEntity<CustomerPhoneResponse> handleUserNotFoundException(NoDataFoundException ex,
            WebRequest request) {
        CustomerPhoneResponse exceptionResponse = new CustomerPhoneResponse(ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}

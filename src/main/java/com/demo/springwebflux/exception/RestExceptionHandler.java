package com.demo.springwebflux.exception;

import com.demo.springwebflux.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<Response> productNotFound(ProductNotFoundException ex) {
        log.debug("handling exception::" + ex);
        Response response = new Response();
        response.setErrorMessage(ex.getMessage());
        response.setIsSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenralException.class)
    ResponseEntity<Response> GeneralException(GenralException ex) {
        log.debug("handling exception::" + ex);
//        return notFound().build();
        Response response = new Response();
        response.setErrorMessage(ex.getMessage());
        response.setIsSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
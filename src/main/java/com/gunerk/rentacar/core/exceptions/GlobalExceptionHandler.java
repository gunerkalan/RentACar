package com.gunerk.rentacar.core.exceptions;

import com.gunerk.rentacar.core.utilities.Results.ErrorDataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Doğrulama Hataları");
        if(errors!=null)
            log.error(errors.getMessage()+" "+errors.getData().toString());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(EmptyResultDataAccessException ex) {

        Map<String,Object> validationErrors = new HashMap<String, Object>();
        validationErrors.put("Hata",ex.getLocalizedMessage());


        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Kayıt Bulunamadı Hataları");
        if(errors!=null)
            log.error(errors.getMessage()+" "+errors.getData().toString());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(IllegalArgumentException ex) {

        Map<String,Object> validationErrors = new HashMap<String, Object>();
        validationErrors.put("Hata",ex.getLocalizedMessage());


        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors,"Kayıt Bulunamadı Hataları");
        if(errors!=null)
            log.error(errors.getMessage()+" "+errors.getData().toString());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(DataIntegrityViolationException ex)
    {
        Map<String,Object> error = new HashMap<String,Object>();
        error.put("Hata",ex.getLocalizedMessage());

        ErrorDataResult<Object> errors = new ErrorDataResult<>(error,"Sql Hataları");

        if(errors!=null)
            log.error(errors.getMessage()+" "+errors.getData().toString());

        return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }


}

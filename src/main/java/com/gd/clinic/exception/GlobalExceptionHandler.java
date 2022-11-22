package com.gd.clinic.exception;

import com.gd.clinic.model.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import org.springframework.web.server.ResponseStatusException;



import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> generateException(ResponseStatusException ex)
    {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setStatus( String.valueOf( ex.getStatus().value()));
        dto.setMessage(ex.getMessage());
        log.error("Exception Occurred : ", ex);

        return new ResponseEntity<ErrorResponseDto>(dto,ex.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> generateException(RuntimeException ex)
    {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setStatus("500");
        dto.setMessage(ex.getMessage());
        log.error("Exception Occurred : ",ex);

        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

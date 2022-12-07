package com.gd.clinic.exception;

import com.gd.clinic.model.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> userNameAlreadyExistResponse(Exception ex, WebRequest request) {
        log.error("Exception Occurred : ", ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> userNameNotFoundResponse(Exception ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> generateResponseStatusException(ResponseStatusException ex) {
        log.error("Exception Occurred : ", ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getStatus().toString(), ex.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, ex.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> generateRuntimeException(RuntimeException ex) {
        log.error("Exception Occurred : ", ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("500", ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> generateException(RuntimeException ex) {
        log.error("Exception Occurred : ", ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("500", ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package edu.sena.petcare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                "ITEM DUPLICADO");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                "SOLICITUD ERRONEA");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(TokenExpired.class)
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpired exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                "TOKEN EXPIRADO");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                "RECURSO NO ENCONTRADO");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        // Log the full stack trace for debugging
        exception.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage() != null ? exception.getMessage() : "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "ERROR INTERNO");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

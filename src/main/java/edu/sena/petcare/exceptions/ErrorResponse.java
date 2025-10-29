package edu.sena.petcare.exceptions;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String mensaje;
    private Integer statusCode;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String errorDetails;

        public ErrorResponse(String mensaje, Integer statusCode, String errorDetails){
        this.mensaje = mensaje;
        this.statusCode = statusCode;
        this.errorDetails = errorDetails;
    }
}

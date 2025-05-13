package com.babel.test.exception;
/**
 * Clase para el manejo de posibles excepciones
 *
 * @author RGR
 */

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarEmpleadoNoEncontrado(
    		EmpleadoNotFoundException ex,
            HttpServletRequest solicitud) {
        
        ErrorResponse respuesta = ErrorResponse.builder()
                .fechaHora(LocalDateTime.now())
                .codigoEstado(HttpStatus.NOT_FOUND.value())
                .estado("No encontrado")
                .mensaje(ex.getMessage())
                .ruta(solicitud.getRequestURI())
                .detalle("El empleado solicitado no existe en el sistema")
                .build();
        
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarExcepcionGeneral(
            Exception ex,
            HttpServletRequest solicitud) {
        
        ErrorResponse respuesta = ErrorResponse.builder()
                .fechaHora(LocalDateTime.now())
                .codigoEstado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .estado("Error interno")
                .mensaje("Ocurrió un error inesperado")
                .ruta(solicitud.getRequestURI())
                .detalle(ex.getMessage())
                .build();
        
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

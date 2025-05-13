package com.babel.test.exception;
/**
 * Clase para mapeo de errores
 *
 * @author RGR
 */

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime fechaHora;
    private int codigoEstado;
    private String estado;
    private String mensaje;
    private String ruta;
    private Map<String, String> errores;
    private String detalle;
}

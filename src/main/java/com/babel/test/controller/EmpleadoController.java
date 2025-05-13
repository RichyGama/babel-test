package com.babel.test.controller;
/**
 * Controlador REST para la gestión de empleados.
 *
 * @author RGR
 */

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babel.test.dto.EmpleadoRequest;
import com.babel.test.dto.EmpleadoResponse;
import com.babel.test.service.EmpleadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/babel/empleados")
public class EmpleadoController {
	
	private final EmpleadoService service;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodosEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerEmpleadoPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crearEmpleado(
            @Validated @RequestBody EmpleadoRequest empleadoRequest) {
        EmpleadoResponse nuevoEmpleado = service.crearEmpleado(empleadoRequest);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @PostMapping("/varios")
    public ResponseEntity<List<EmpleadoResponse>> crearMultiplesEmpleados(
            @Validated @RequestBody List<EmpleadoRequest> empleadosRequest) {
        List<EmpleadoResponse> empleadosCreados = service.crearMultiplesEmpleados(empleadosRequest);
        return new ResponseEntity<>(empleadosCreados, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> actualizarEmpleado(
            @PathVariable Long id,
            @Validated @RequestBody EmpleadoRequest empleadoRequest) {
        return ResponseEntity.ok(service.actualizarEmpleado(id, empleadoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        service.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

}

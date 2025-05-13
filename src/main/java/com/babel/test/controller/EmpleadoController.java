package com.babel.test.controller;

/**
 * Controlador REST para la gestión de empleados.
 * contiene documentacion mediante openApi
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/babel/empleados")
@Tag(name = "Empleados", description = "API para la gestión de empleados")
public class EmpleadoController {

	private final EmpleadoService service;

	@Operation(summary = "Obtener todos los empleados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente") })
	@GetMapping
	public ResponseEntity<List<EmpleadoResponse>> listarTodos() {
		return ResponseEntity.ok(service.obtenerTodosEmpleados());
	}

	@Operation(summary = "Obtener empleado por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
			@ApiResponse(responseCode = "404", description = "Empleado no encontrado") })
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoResponse> obtenerPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.obtenerEmpleadoPorId(id));
	}

	@Operation(summary = "Crear nuevo empleado")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Empleado creado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	@PostMapping
	public ResponseEntity<EmpleadoResponse> crearEmpleado(@Validated @RequestBody EmpleadoRequest empleadoRequest) {
		EmpleadoResponse nuevoEmpleado = service.crearEmpleado(empleadoRequest);
		return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
	}

	@Operation(summary = "Crear múltiples empleados")
	@PostMapping("/varios")
	public ResponseEntity<List<EmpleadoResponse>> crearMultiplesEmpleados(
			@Validated @RequestBody List<EmpleadoRequest> empleadosRequest) {
		List<EmpleadoResponse> empleadosCreados = service.crearMultiplesEmpleados(empleadosRequest);
		return new ResponseEntity<>(empleadosCreados, HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar empleado")
	@PutMapping("/{id}")
	public ResponseEntity<EmpleadoResponse> actualizarEmpleado(@PathVariable Long id,
			@Validated @RequestBody EmpleadoRequest empleadoRequest) {
		return ResponseEntity.ok(service.actualizarEmpleado(id, empleadoRequest));
	}

	@Operation(summary = "Eliminar empleado")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
		service.eliminarEmpleado(id);
		return ResponseEntity.noContent().build();
	}

}

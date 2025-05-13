package com.babel.test.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.babel.test.controller.EmpleadoController;
import com.babel.test.dto.EmpleadoRequest;
import com.babel.test.dto.EmpleadoResponse;
import com.babel.test.service.EmpleadoService;

@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTest {

	@Mock
	private EmpleadoService empleadoService;

	@InjectMocks
	private EmpleadoController empleadoController;

	@Test
	void crearEmpleado_DevuelveResponseCorrecto() {
		EmpleadoResponse mockResponse = new EmpleadoResponse();
		mockResponse.setId(1L);

		when(empleadoService.crearEmpleado(any(EmpleadoRequest.class))).thenReturn(mockResponse);

		ResponseEntity<EmpleadoResponse> respuesta = empleadoController.crearEmpleado(new EmpleadoRequest());

		assertEquals(201, respuesta.getStatusCodeValue());
		assertNotNull(respuesta.getBody());
		assertEquals(1L, respuesta.getBody().getId());
	}

	@Test
	void obtenerEmpleado_Existente_DevuelveEmpleado() {
		EmpleadoResponse mockResponse = new EmpleadoResponse();
		mockResponse.setId(1L);

		when(empleadoService.obtenerEmpleadoPorId(1L)).thenReturn(mockResponse);

		ResponseEntity<EmpleadoResponse> respuesta = empleadoController.obtenerPorId(1L);

		assertEquals(200, respuesta.getStatusCodeValue());
		assertEquals(1L, respuesta.getBody().getId());
	}

	@Test
	void eliminarEmpleado_Devuelve204() {
		doNothing().when(empleadoService).eliminarEmpleado(1L);

		ResponseEntity<Void> respuesta = empleadoController.eliminarEmpleado(1L);

		assertEquals(204, respuesta.getStatusCodeValue());
		verify(empleadoService, times(1)).eliminarEmpleado(1L);
	}
}

package com.babel.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.babel.test.dto.EmpleadoRequest;
import com.babel.test.dto.EmpleadoResponse;
import com.babel.test.exception.EmpleadoNotFoundException;
import com.babel.test.model.Empleado;
import com.babel.test.repository.EmpleadoRepository;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

	@Mock
	private EmpleadoRepository empleadoRepository;

	@InjectMocks
	private EmpleadoService empleadoService;

	@Test
	void crearEmpleado_DevuelveEmpleadoConIdGenerado() {
		EmpleadoRequest request = new EmpleadoRequest();
		request.setPrimerNombre("Ricardo");
		request.setApellidoPaterno("Gama");
		request.setFechaNacimiento(LocalDate.of(1988, 10, 30));
		request.setPuesto("Desarrollador");

		Empleado empleadoGuardado = Empleado.builder().id(1L).primerNombre("Ricardo").apellidoPaterno("Gama")
				.fechaNacimiento(LocalDate.of(1988, 10, 30)).puesto("Desarrollador").build();

		when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoGuardado);

		EmpleadoResponse resultado = empleadoService.crearEmpleado(request);

		assertEquals(1L, resultado.getId());
		assertEquals("Ricardo", resultado.getPrimerNombre());
		verify(empleadoRepository)
				.save(argThat(e -> e.getPrimerNombre().equals("Ricardo") && e.getApellidoPaterno().equals("Gama")));
	}

	@Test
	void obtenerEmpleadoPorId_Existente_DevuelveEmpleado() {
		Empleado empleado = Empleado.builder().id(1L).primerNombre("Paty").apellidoPaterno("Juncos").build();

		when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

		EmpleadoResponse resultado = empleadoService.obtenerEmpleadoPorId(1L);

		assertEquals("Paty", resultado.getPrimerNombre());
	}

	@Test
	void obtenerEmpleadoPorId_NoExistente_LanzaExcepcion() {
		when(empleadoRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(EmpleadoNotFoundException.class, () -> {
			empleadoService.obtenerEmpleadoPorId(99L);
		});
	}

	@Test
	void actualizarEmpleado_ActualizaSoloCamposNoNulos() {
		Empleado empleadoExistente = Empleado.builder().id(1L).primerNombre("Carlos").apellidoPaterno("Gomez")
				.puesto("Analista").build();

		EmpleadoRequest updateRequest = new EmpleadoRequest();
		updateRequest.setPuesto("Senior Analyst");

		when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleadoExistente));
		when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoExistente);

		empleadoService.actualizarEmpleado(1L, updateRequest);

		verify(empleadoRepository).save(argThat(e -> e.getPrimerNombre().equals("Carlos") && // Mantiene valor original
				e.getPuesto().equals("Senior Analyst")
		));
	}
}

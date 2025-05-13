package com.babel.test.exception;
/**
 * Excepcion lanzada cuando no se encuentra el empleado por su id
 *
 * @author RGR
 */

import lombok.Getter;

public class EmpleadoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private final Long id;

	public EmpleadoNotFoundException(Long id) {
		super("El empleado con el id: " + id+" nofue encontrado");
		this.id = id;
	}

	public EmpleadoNotFoundException(String message) {
		super(message);
		this.id = null;
	}

}

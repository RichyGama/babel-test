package com.babel.test.repository;
/**
 * Interfaz para la gestión de persistencia (CRUD) de entidades Empleado.
 *
 * @author RGR
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.babel.test.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}

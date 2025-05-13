package com.babel.test.service;
/**
 * Capa de servicio para la gestión de empleados.
 *
 * @author RGR
 */

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babel.test.dto.EmpleadoRequest;
import com.babel.test.dto.EmpleadoResponse;
import com.babel.test.exception.EmpleadoNotFoundException;
import com.babel.test.mapper.EmpleadoMapper;
import com.babel.test.model.Empleado;
import com.babel.test.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
	
    private final EmpleadoRepository empleadoRepositorio;
    private final EmpleadoMapper empleadoMapper;

    @Transactional(readOnly = true)
    public List<EmpleadoResponse> obtenerTodosEmpleados() {
        return empleadoMapper.aListaDto(empleadoRepositorio.findAll());
    }

    @Transactional(readOnly = true)
    public EmpleadoResponse obtenerEmpleadoPorId(Long id) {
        return empleadoMapper.aDto(empleadoRepositorio.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id)));
    }

    @Transactional
    public EmpleadoResponse crearEmpleado(EmpleadoRequest empleadoRequest) {
        Empleado empleado = empleadoMapper.aEntidad(empleadoRequest);
        empleado = empleadoRepositorio.save(empleado);
        return empleadoMapper.aDto(empleado);
    }

    @Transactional
    public List<EmpleadoResponse> crearMultiplesEmpleados(List<EmpleadoRequest> empleadosRequest) {
        List<Empleado> empleados = empleadosRequest.stream()
                .map(empleadoMapper::aEntidad)
                .toList();

        empleados = empleadoRepositorio.saveAll(empleados);
        return empleadoMapper.aListaDto(empleados);
    }

    @Transactional
    public EmpleadoResponse actualizarEmpleado(Long id, EmpleadoRequest empleadoRequest) {
        Empleado empleadoExistente = empleadoRepositorio.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
        
        empleadoMapper.actualizarPorReq(empleadoRequest, empleadoExistente);
        empleadoExistente.setId(id);
        
        Empleado empleadoActualizado = empleadoRepositorio.save(empleadoExistente);
        return empleadoMapper.aDto(empleadoActualizado);
    }

    @Transactional
    public void eliminarEmpleado(Long id) {
        if (!empleadoRepositorio.existsById(id)) {
            throw new EmpleadoNotFoundException(id);
        }
        empleadoRepositorio.deleteById(id);
    }

}

package com.babel.test.mapper;
/**
 * Mapper para desacoplar la capa de persistencia
 *
 * @author RGR
 */

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.babel.test.dto.EmpleadoRequest;
import com.babel.test.dto.EmpleadoResponse;
import com.babel.test.model.Empleado;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmpleadoMapper {

    @Mapping(target = "id", ignore = true)
    Empleado aEntidad(EmpleadoRequest request);

    EmpleadoResponse aDto(Empleado empleado);

    List<EmpleadoResponse> aListaDto(List<Empleado> empleados);

    @Mapping(target = "id", ignore = true)
    void actualizarPorReq(EmpleadoRequest request, @MappingTarget Empleado empleado);

}

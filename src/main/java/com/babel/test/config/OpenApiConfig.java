package com.babel.test.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Clase para configurar documentacion swagger con OpenApi
 *
 * @author RGR
 */

public class OpenApiConfig {
    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("BABEL- Prueba tecnica - API de Gestión de Empleados").version("1.0")
				.description("Documentación para la API de gestión de empleados como prueba tecnica de Babel"));
	}

}

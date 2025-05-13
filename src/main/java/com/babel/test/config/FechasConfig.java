package com.babel.test.config;

/**
 * Clase para configurar formato de fecha
 *
 * @author RGR
 */

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class FechasConfig {

	@Bean
	FormattingConversionService conversionService() {
		FormattingConversionService servicio = new FormattingConversionService();

		DateTimeFormatterRegistrar dateformmatter = new DateTimeFormatterRegistrar();
		dateformmatter.setDateFormatter(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		dateformmatter.registerFormatters(servicio);

		return servicio;
	}

}

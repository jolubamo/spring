package com.prueba.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExperienciaLaboral {
	private Integer codigo;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Integer perCodigo;
	private Integer titCodigo;
}

package com.prueba.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Persona {
	private String nombre;
	private String apellido;
	private Integer codigo;
	private LocalDate fechaNacimiento;
	private String identificacion;

}

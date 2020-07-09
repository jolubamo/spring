package com.prueba.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonaCalculo {
	
	private String nombre;
	private Long edad;
	private String documento;
	private long totalDias;
	private float totalMeses;
	private float totalYears;
	private List<TotalExperiencia> lstTotalExperiencia;
	
	public PersonaCalculo(String nombre, Long edad, String documento, long totalDias, float totalMeses,
			float totalYears, List<TotalExperiencia> lstTotalExperiencia) {
		this.nombre = nombre;
		this.edad = edad;
		this.documento = documento;
		this.totalDias = totalDias;
		this.totalMeses = totalMeses;
		this.totalYears = totalYears;
		this.lstTotalExperiencia = lstTotalExperiencia;
	}
	
	
}

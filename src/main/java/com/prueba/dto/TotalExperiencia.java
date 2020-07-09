package com.prueba.dto;

import lombok.Data;

@Data
public class TotalExperiencia {
	
	private long dias;
	private float meses;
	private float years;
	private String nombreTrabajo;

	public TotalExperiencia(long dias, float meses, float years, String nombreTrabajo) {
		super();
		this.dias = dias;
		this.meses = meses;
		this.years = years;
		this.nombreTrabajo = nombreTrabajo;
	}

}

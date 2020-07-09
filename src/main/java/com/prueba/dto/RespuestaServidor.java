package com.prueba.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RespuestaServidor {
	private String respuesta;
	private int codigo;

	public RespuestaServidor(String respuesta, int codigo) {
		this.respuesta = respuesta;
		this.codigo = codigo;
	}
}

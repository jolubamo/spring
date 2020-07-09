package com.prueba.service;

import java.util.List;

import com.prueba.dto.RespuestaServidor;
import com.prueba.model.TipoTrabajo;

public interface ITipoTrabajoService {
	void crear(TipoTrabajo tt);

	List<TipoTrabajo> listar();

	TipoTrabajo listarPorCodigo(Integer codigo);

	RespuestaServidor update(TipoTrabajo tt);
	
	RespuestaServidor eliminar(Integer codigo);
}

package com.prueba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.RespuestaServidor;
import com.prueba.model.TipoTrabajo;
import com.prueba.repository.TipoTrabajoRepository;
import com.prueba.service.ITipoTrabajoService;

@Service
public class TipoTrabajoServiceImpl implements ITipoTrabajoService {
	@Autowired
	private TipoTrabajoRepository titRepo;

	@Override
	public void crear(TipoTrabajo tt) {
	
		titRepo.crear(tt);

	}

	@Override
	public List<TipoTrabajo> listar() {

		return titRepo.listar();
	}

	@Override
	public TipoTrabajo listarPorCodigo(Integer codigo) {

		return titRepo.listarPorCodigo(codigo);
	}

	@Override
	public RespuestaServidor update(TipoTrabajo tt) {
		if(!titRepo.update(tt)) {
			
			return new RespuestaServidor("modificacion Exitosa",1);
			
			
		}else {
			return new RespuestaServidor("modificacion Fallida",2);
		}
			

	}@Override
	public RespuestaServidor eliminar(Integer codigo ) {
		if(!titRepo.eliminar(codigo)) {
			
			return new RespuestaServidor("Eliminacion Exitosa",1);
			
			
		}else {
			return new RespuestaServidor("Eliminacion Fallida",2);
		}
			

	}

	
}

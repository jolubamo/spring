package com.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dto.RespuestaServidor;
import com.prueba.model.TipoTrabajo;
import com.prueba.service.ITipoTrabajoService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/tipo-trabajo")
public class TipoTrabajoController {
	@Autowired ITipoTrabajoService service;
	@PostMapping(path = "crear")
	public void crear(@RequestBody TipoTrabajo tt) {
		 service.crear(tt);
		
	}

	@GetMapping(path = "listar")
	public List<TipoTrabajo> listar() {
		return service.listar();
	}

	@GetMapping(path = "listar-por-codigo/{codigo}")
	public TipoTrabajo listar(@PathVariable Integer codigo) {
		return service.listarPorCodigo(codigo);
	}

	@PutMapping(path = "update")
	public RespuestaServidor update(@RequestBody TipoTrabajo tt) {
		return service.update(tt);
	}
	@DeleteMapping(path = "eliminar/{codigo}")
	public RespuestaServidor eliminar(@PathVariable Integer codigo) {
		return service.eliminar(codigo);
	}

}

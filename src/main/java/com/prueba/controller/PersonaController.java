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
import com.prueba.model.Persona;
import com.prueba.service.IPersonaService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/persona")
public class PersonaController {

	@Autowired
	private IPersonaService service;

	@PostMapping(path = "crear")
	public RespuestaServidor crear(@RequestBody Persona persona) {
		return service.crear(persona);
		
	}

	@GetMapping(path = "listar")
	public List<Persona> listar() {
		return service.listar();
	}

	@GetMapping(path = "listar-por-codigo/{codigo}")
	public Persona listar(@PathVariable Integer codigo) {
		return service.listarPorCodigo(codigo);
	}

	@PutMapping(path = "update")
	public RespuestaServidor update(@RequestBody Persona persona) {
		return service.update(persona);
	}
	@DeleteMapping(path = "eliminar/{codigo}")
	public RespuestaServidor eliminar(@PathVariable Integer codigo) {
		return service.eliminar(codigo);
	}

}

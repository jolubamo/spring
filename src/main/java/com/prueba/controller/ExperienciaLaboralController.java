package com.prueba.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.prueba.dto.PersonaCalculo;
import com.prueba.dto.RespuestaServidor;
import com.prueba.model.ExperienciaLaboral;
import com.prueba.service.IExperienciaLaboralService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/experiencia-laboral")
public class ExperienciaLaboralController {
	@Autowired
	private IExperienciaLaboralService service;

	@PostMapping(path = "crear")
	public void crear(@RequestBody ExperienciaLaboral exl) {
		service.crear(exl);

	}

	@GetMapping(path = "listar")
	public List<ExperienciaLaboral> listar() {
		return service.listar();
	}

	@GetMapping(path = "listar-por-codigo/{codigo}")
	public ExperienciaLaboral listar(@PathVariable Integer codigo) {
		return service.listarPorCodigo(codigo);
	}

	@PutMapping(path = "update")
	public RespuestaServidor update(@RequestBody ExperienciaLaboral exl) {
		return service.update(exl);
	}

	@GetMapping(path = "listar-por-per-codigo/{codigo}")
	public List<ExperienciaLaboral> listarPorPerCodigo(@PathVariable Integer codigo) {
		return service.listarPorPerCodigo(codigo);
	}

	@GetMapping(path = "listar-por-tit-codigo/{codigo}")
	public List<ExperienciaLaboral> listarPorTitCodigo(@PathVariable Integer codigo) {
		return service.listarPorTitCodigo(codigo);
	}
/*
	@GetMapping(path = "experiencia/{codigo}")
	public TotalExperiencia tiepoexp(@PathVariable Integer codigo) {
		return service.tiempoexp(codigo);
	}*/

	@GetMapping(path = "experiencia-individual")
	public List<PersonaCalculo> tiempoIndividual() {
		return service.tiempoIndividual();
	}

	@DeleteMapping(path = "eliminar/{codigo}")
	public RespuestaServidor eliminar(@PathVariable Integer codigo) {
		return service.eliminar(codigo);
	}

	@GetMapping(path = "experiencia-pdf")
	public void experienciaPdf(HttpServletResponse response) {
		service.experienciaPdf(response); 
	}

	@GetMapping(path = "experiencia-excel")
	public void experienciaExcel(HttpServletResponse response) {
		service.experienciaExcel(response); 
	}

}

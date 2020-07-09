package com.prueba.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.prueba.dto.PersonaCalculo;
import com.prueba.dto.RespuestaServidor;
import com.prueba.model.ExperienciaLaboral;

public interface IExperienciaLaboralService {
	void crear(ExperienciaLaboral exl);

	List<ExperienciaLaboral> listar();

	List<ExperienciaLaboral> listarPorPerCodigo(Integer codigo);

	ExperienciaLaboral listarPorCodigo(Integer codigo);

	List<ExperienciaLaboral> listarPorTitCodigo(Integer codigo);

	RespuestaServidor update(ExperienciaLaboral exl);

	//TotalExperiencia tiempoexp(Integer codigo);

	List<PersonaCalculo> tiempoIndividual();

	RespuestaServidor eliminar(Integer codigo);

	void experienciaPdf(HttpServletResponse response);

	void experienciaExcel(HttpServletResponse response);
}

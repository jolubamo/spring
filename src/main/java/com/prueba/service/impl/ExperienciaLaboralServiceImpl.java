package com.prueba.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.JasperData;
import com.prueba.dto.PersonaCalculo;
import com.prueba.dto.RespuestaServidor;
import com.prueba.dto.TotalExperiencia;
import com.prueba.model.ExperienciaLaboral;
import com.prueba.model.Persona;
import com.prueba.repository.ExperienciaLaboralRepository;
import com.prueba.repository.PersonaRepository;
import com.prueba.repository.TipoTrabajoRepository;
import com.prueba.service.IExperienciaLaboralService;
import com.prueba.util.JasperReportComponent;

@Service
public class ExperienciaLaboralServiceImpl implements IExperienciaLaboralService {
	@Autowired
	private ExperienciaLaboralRepository exlRepo;
	@Autowired
	private JasperReportComponent jasperComponent;
	@Autowired
	private PersonaRepository personaRepo;
	@Autowired
	private TipoTrabajoRepository tipoTrabajoRepo;

	@Override
	public void crear(ExperienciaLaboral exl) {
		exlRepo.crear(exl);
	}

	@Override
	public List<ExperienciaLaboral> listar() {

		return exlRepo.listar();
	}

	@Override
	public List<ExperienciaLaboral> listarPorPerCodigo(Integer codigo) {

		return exlRepo.listarPorPerCodigo(codigo);
	}

	@Override
	public ExperienciaLaboral listarPorCodigo(Integer codigo) {

		return exlRepo.listarPorCodigo(codigo);
	}

	@Override
	public List<ExperienciaLaboral> listarPorTitCodigo(Integer codigo) {

		return exlRepo.listarPorTitCodigo(codigo);
	}

	@Override
	public RespuestaServidor update(ExperienciaLaboral exl) {
		if (!exlRepo.update(exl)) {
			return new RespuestaServidor("Actualizacion correcta", 1);
		} else {
			return new RespuestaServidor("Actualizacion Fallida", 2);
		}

	}
	/*
	 * @Override public TotalExperiencia tiempoexp(Integer codigo) { long days = 0;
	 * float year = 0, mes = 0; for (ExperienciaLaboral exp :
	 * exlRepo.listarPorPerCodigo(codigo)) { days =
	 * ChronoUnit.DAYS.between(exp.getFechaInicio(), exp.getFechaFin()) + days;
	 * 
	 * } mes = days / 30; year = mes / 12; return new TotalExperiencia(days, mes,
	 * year, codigo); }
	 */

	@Override
	public List<PersonaCalculo> tiempoIndividual() {

		List<PersonaCalculo> lstPersonaC = new ArrayList<PersonaCalculo>();

		
		TotalExperiencia txp = null;
		PersonaCalculo personaC = null;
		String trabajo;
		LocalDate today = LocalDate.now();
		long edad;

		
		for (Persona p : personaRepo.listar()) {
			List<TotalExperiencia> lstTotalexp = new ArrayList<TotalExperiencia>();
			long dias = 0;
			long diast = 0;
			float meses = 0;
			
			float years = 0;
			float mesest = 0;
			float yearst = 0;
			
			
			for (ExperienciaLaboral exp : exlRepo.listarPorPerCodigo(p.getCodigo())) {
				dias = ChronoUnit.DAYS.between(exp.getFechaInicio(), exp.getFechaFin()) + dias;
				diast = dias + diast;
				meses = (float)(dias / 30.0 + meses);
				years = (float)(dias / 365.0 + years);
				mesest = (float)(dias / 30.0);
				yearst = (float)(dias/365.0);
				mesest =  Math.round(mesest * 100) / 100f;
				yearst =  Math.round(yearst * 100) / 100f;
				
				trabajo = tipoTrabajoRepo.listarPorCodigo(exp.getTitCodigo()).getNombre();
				txp = new TotalExperiencia(dias, mesest, yearst, trabajo);
				lstTotalexp.add(txp);
			}

			edad = ChronoUnit.YEARS.between(p.getFechaNacimiento(), today);
			String nombre=p.getNombre()+" "+p.getApellido();
			personaC = new PersonaCalculo(nombre, edad, p.getIdentificacion(), diast, meses, years, lstTotalexp);
			lstPersonaC.add(personaC);
		}
		return lstPersonaC;
	}

	public RespuestaServidor eliminar(Integer codigo) {
		if (!exlRepo.eliminar(codigo)) {

			return new RespuestaServidor("Eliminacion Exitosa", 1);

		} else {

			return new RespuestaServidor("Eliminacion Fallida", 2);

		}

	}

	@Override
	public void experienciaPdf(HttpServletResponse response) {

		// List<TotalExperiencia> lstTotalExp = tiempoIndividual();
		List<PersonaCalculo> lstPersona = tiempoIndividual();
		
		float diastt = 0;
		float mesestt = 0;
		float yearstt = 0;
		
		for(PersonaCalculo psc:lstPersona) {
			diastt = diastt + psc.getTotalDias();
			mesestt = mesestt + psc.getTotalMeses();
			yearstt = yearstt + psc.getTotalYears();
		}
		
		JasperData jasper = new JasperData();

		Map<String, Object> dataSource = new HashMap<>();
		
		dataSource.put("totald", diastt);
		dataSource.put("totalm", mesestt);
		dataSource.put("totaly", yearstt);
		
		dataSource.put("experiencia", lstPersona);

		jasper.setPathJrxml("/static/reporte/pdf/experiencia_laboral.jrxml");
		jasper.setResponse(response);
		jasper.setDataSource(dataSource);

		try {
			jasperComponent.exportToPdf(jasper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void experienciaExcel(HttpServletResponse response) {
		List<PersonaCalculo> lstPersona = tiempoIndividual();

		JasperData jasper = new JasperData();

		Map<String, Object> dataSource = new HashMap<>();

		dataSource.put("experiencia", lstPersona);

		jasper.setPathJrxml("/static/reporte/pdf/experiencia_laboral.jrxml");
		jasper.setResponse(response);
		jasper.setDataSource(dataSource);

		try {
			jasperComponent.exportToExcel(jasper);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}

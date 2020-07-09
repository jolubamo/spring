package com.prueba.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prueba.model.ExperienciaLaboral;
@Repository
public class ExperienciaLaboralRepository {
	@Autowired
	private DataSource datasource;

	public void crear(ExperienciaLaboral exl) {
		String sql = "insert into experiencia_laboral(exl_fecha_inicio,exl_fecha_fin,per_codigo,tit_codigo) values (?,?,?,?)";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setObject(1, exl.getFechaInicio());
			ps.setObject(2, exl.getFechaFin());
			ps.setInt(3, exl.getPerCodigo());
			ps.setInt(4, exl.getTitCodigo());
			ps.execute();
			ps.close();
			conexion.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<ExperienciaLaboral> listar() {
		String sql = "select * from experiencia_laboral";
		List<ExperienciaLaboral> exls = new ArrayList<>();
		ExperienciaLaboral exl = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				exl = new ExperienciaLaboral();
				exl.setCodigo(rs.getInt("exl_codigo"));
				exl.setFechaInicio(rs.getObject("exl_fecha_inicio",LocalDate.class));
				exl.setFechaFin(rs.getObject("exl_fecha_fin",LocalDate.class));
				exl.setPerCodigo(rs.getInt("per_codigo"));
				exl.setTitCodigo(rs.getInt("tit_codigo"));
				exls.add(exl);
			}
			return exls;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
	public List<ExperienciaLaboral> listarPorPerCodigo(Integer codigo) {
		String sql = "select * from experiencia_laboral where per_codigo=?";
		List<ExperienciaLaboral> exls = new ArrayList<>();
		ExperienciaLaboral exl = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				exl = new ExperienciaLaboral();
				exl.setCodigo(rs.getInt("exl_codigo"));
				exl.setFechaInicio(rs.getObject("exl_fecha_inicio",LocalDate.class));
				exl.setFechaFin(rs.getObject("exl_fecha_fin",LocalDate.class));
				exl.setPerCodigo(rs.getInt("per_codigo"));
				exl.setTitCodigo(rs.getInt("tit_codigo"));
				exls.add(exl);
			}
			return exls;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	public ExperienciaLaboral listarPorCodigo(Integer codigo) {
		String sql = "select * from experiencia_laboral where exl_codigo=?";
		ExperienciaLaboral exl = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				exl = new ExperienciaLaboral();
				exl.setCodigo(rs.getInt("exl_codigo"));
				exl.setFechaInicio(rs.getObject("exl_fecha_inicio",LocalDate.class));
				exl.setFechaFin(rs.getObject("exl_fecha_fin",LocalDate.class));
				exl.setPerCodigo(rs.getInt("per_codigo"));
				exl.setTitCodigo(rs.getInt("tit_codigo"));
				
			}
			return exl;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
	
	public List<ExperienciaLaboral> listarPorTitCodigo(Integer codigo) {
		String sql = "select * from experiencia_laboral where tit_codigo=?";
		List<ExperienciaLaboral> exls = new ArrayList<>();
		ExperienciaLaboral exl = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				exl = new ExperienciaLaboral();
				exl.setCodigo(rs.getInt("exl_codigo"));
				exl.setFechaInicio(rs.getObject("exl_fecha_inicio",LocalDate.class));
				exl.setFechaFin(rs.getObject("exl_fecha_fin",LocalDate.class));
				exl.setPerCodigo(rs.getInt("per_codigo"));
				exl.setTitCodigo(rs.getInt("tit_codigo"));
				exls.add(exl);
			}
			return exls;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
	public Boolean update(ExperienciaLaboral exl) {
		boolean flag;
		String sql = "update experiencia_laboral set exl_fecha_inicio=?,exl_fecha_fin=?,per_codigo=?,tit_codigo=? where exl_codigo=?";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setObject(1, exl.getFechaInicio());
			ps.setObject(2, exl.getFechaFin());
			ps.setInt(3, exl.getPerCodigo());
			ps.setInt(4, exl.getTitCodigo());
			ps.setInt(5, exl.getCodigo());
			flag=ps.execute();
			ps.close();
			conexion.close();
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Boolean eliminar(Integer codigo) {
		boolean flag;
		String sql="delete from experiencia_laboral where exl_codigo=?";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			flag=ps.execute();
			ps.close();
			conexion.close();
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

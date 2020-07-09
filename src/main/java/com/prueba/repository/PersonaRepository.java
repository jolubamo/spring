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

import com.prueba.model.Persona;

@Repository
public class PersonaRepository {

	@Autowired
	private DataSource datasource;

	public void crear(Persona persona) {
		String sql = "insert into persona(per_nombre,per_apellido,per_fecha_nacimiento,per_identificacion) values (?,?,?,?)";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getApellido());
			ps.setObject(3, persona.getFechaNacimiento());
			ps.setString(4, persona.getIdentificacion());
			ps.execute();
			ps.close();
			conexion.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Persona> listar() {
		String sql = "select * from persona";
		List<Persona> personas = new ArrayList<>();
		Persona persona = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				persona = new Persona();
				persona.setIdentificacion(rs.getString("per_identificacion"));
				persona.setApellido(rs.getString("per_apellido"));
				persona.setNombre(rs.getString("per_nombre"));
				persona.setFechaNacimiento(rs.getObject("per_fecha_nacimiento", LocalDate.class));
				persona.setCodigo(rs.getInt("per_codigo"));
				personas.add(persona);
			}
			return personas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public Persona listarPorCodigo(Integer codigo) {
		String sql = "select * from persona where per_codigo=?";

		Persona persona = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				persona = new Persona();
				persona.setIdentificacion("per_identificacion");
				persona.setApellido(rs.getString("per_apellido"));
				persona.setNombre(rs.getString("per_nombre"));
				persona.setFechaNacimiento(rs.getObject("per_fecha_nacimiento", LocalDate.class));
				persona.setCodigo(rs.getInt("per_codigo"));
			}
			return persona;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}public Persona listarPorIdentificacion(String identificacion) {
		String sql = "select * from persona where per_identificacion=?";

		Persona persona = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1,identificacion);;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				persona = new Persona();
				persona.setCodigo(rs.getInt("per_codigo"));
			}
			return persona;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public Boolean update(Persona persona) {
		boolean flag=false;
		String sql = "update persona  set per_nombre=?,per_apellido=?,per_fecha_nacimiento=?,per_identificacion=? where per_codigo=?";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getApellido());
			ps.setObject(3, persona.getFechaNacimiento());
			ps.setString(4, persona.getIdentificacion());
			ps.setInt(5, persona.getCodigo());
			flag=ps.execute();
			ps.close();
			conexion.close();
			return flag;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Boolean eliminar(Integer codigo) {
		String sql="delete from persona where per_codigo=?";
		boolean flag;
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


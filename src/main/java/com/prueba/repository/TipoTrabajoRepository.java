package com.prueba.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prueba.model.TipoTrabajo;
@Repository
public class TipoTrabajoRepository {
	@Autowired
	private DataSource datasource;

	public void crear(TipoTrabajo tt) {
		String sql = "insert into tipo_trabajo(tit_nombre) values (?)";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, tt.getNombre());
			ps.execute();
			ps.close();
			conexion.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<TipoTrabajo> listar() {
		String sql = "select * from tipo_trabajo";
		List<TipoTrabajo> tts = new ArrayList<>();
		TipoTrabajo tt = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tt = new TipoTrabajo();
				tt.setNombre(rs.getString("tit_nombre"));
				tt.setCodigo(rs.getInt("tit_codigo"));
				tts.add(tt);
			}
			return tts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public TipoTrabajo listarPorCodigo(Integer codigo) {
		String sql = "select * from tipo_trabajo where tit_codigo=?";

		TipoTrabajo tt = null;
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tt = new TipoTrabajo();
				tt.setNombre(rs.getString("tit_nombre"));
				tt.setCodigo(rs.getInt("tit_codigo"));
			}
			return tt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	public Boolean update(TipoTrabajo tt) {
		boolean flag;
		String sql = "update tipo_trabajo  set tit_nombre=? where tit_codigo=?";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, tt.getNombre());
			ps.setInt(2, tt.getCodigo());
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
		String sql="delete from tipo_trabajo where tit_codigo=?";
		try {
			Connection conexion = datasource.getConnection();

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, codigo);
			flag=ps.execute();
			ps.close();
			conexion.close();
			return flag;
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}

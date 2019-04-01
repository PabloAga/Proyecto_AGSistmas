package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;
import app.Context;
import modelo.Alarma;
import modelo.Casa;


public class AlarmaDao {

	Connection com;

	public AlarmaDao(Connection com) {
		this.com = com;
	}

	public boolean registrarAlarma(Alarma a, Logger logs) {
		PreparedStatement state = null;
		ResultSet resultado = null;

		String insert = "INSERT INTO alarma VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			state = (PreparedStatement) com.prepareStatement(insert);
			state.setString(1, a.getN_Serie());
			state.setString(2, a.getMarca_Modelo());
			state.setInt(3, a.getCant_Zonas());
			state.setString(4, a.getMarca_Modelo_backup());
			state.setString(5, a.getEmpresa_Monitoreo());
			state.setDate(6, a.getFecha_Bateria());
			state.setDate(7, a.getFecha_Preventivo());
			state.setDate(8, a.getFecha_Instalaciona());
			state.setString(9, a.getNota());
			state.setString(10, a.getCasa_Direccion());
			state.execute();
		}

		catch (SQLException e) {
			logs.log(Level.INFO, "No se a podido insertar la alarma que tiene el numero de serie" + a.getN_Serie());
			return false;
		}

		return true;
	}

	public boolean modificarAlarma(Alarma a, Logger logs) {
		String instrucccion;
		PreparedStatement pstm = null;

		instrucccion = "UPDATE ag_sistemas.alarma SET marca_Modelo = '" + a.getMarca_Modelo() + "',cant_Zonas = "
				+ a.getCant_Zonas() + ",marca_Modelo_backup = '" + a.getMarca_Modelo_backup() + "', empresa_Monitoreo='"
				+ a.getEmpresa_Monitoreo() + "',fecha_Bateria = '" + a.getFecha_Bateria() + "',fecha_Preventivo = '"
				+ a.getFecha_Preventivo() + "',fecha_Instalacion = '" + a.getFecha_Instalaciona() + "',nota = '"
				+ a.getNota() + "' WHERE (n_Serie = '" + a.getN_Serie() + "')";
		try {
			pstm = (PreparedStatement) com.prepareStatement(instrucccion);
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
		}

		return false;
	}

	public boolean exisAlarma(Alarma a, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();
			resultado = state.executeQuery("select n_Serie from alarma");
																		
			while (resultado.next())
				if (a.getN_Serie().equals(resultado.getString(1)))
					return false;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de acceder a la base de dato");
			e.printStackTrace();
		}
		return true;
	}

	public int cantidad_filas_casa(Casa a, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();

			resultado = state
					.executeQuery("select count(*) from  alarma WHERE casa_Direccion= '" + a.getDireccion() + "'");
			while (resultado.next())
				return resultado.getInt(1);
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}

		return 0;
	}

	public Alarma[] buscar_Alarma_por_Dirccion(Context c, Casa casa, Logger logs) throws Exception {
		int cant = cantidad_filas_casa(casa, logs);
		Alarma alarma = null;
		if (cant == 0) {
			alarma = new Alarma(null, null, 0, null, null, null, null, null, null, null);
			Alarma vector_publ[] = new Alarma[1];
			vector_publ[0] = alarma;
			logs.log(Level.INFO, "No hay datos existentes");
			return vector_publ;
		}
		Alarma vector_publ[] = new Alarma[cant];
		int conta = 0;
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("select * from  alarma WHERE casa_Direccion= '" + casa.getDireccion() + "'");

			while (rs.next()) {

				alarma = new Alarma();
				alarma.setN_Serie(rs.getString("n_Serie"));
				alarma.setMarca_Modelo(rs.getString("marca_Modelo"));
				alarma.setCant_Zonas(Integer.parseInt(rs.getString("cant_Zonas")));
				alarma.setMarca_Modelo_backup(rs.getString("marca_Modelo_backup"));
				alarma.setEmpresa_Monitoreo(rs.getString("empresa_Monitoreo"));
				alarma.setFecha_Bateria(c.getpBO().fecha_sql(rs.getString("fecha_Bateria")));
				alarma.setFecha_Preventivo(c.getpBO().fecha_sql(rs.getString("fecha_Preventivo")));
				alarma.setFecha_Instalaciona(c.getpBO().fecha_sql(rs.getString("fecha_Instalacion")));
				alarma.setNota(rs.getString("nota"));
				vector_publ[conta] = alarma;
				conta++;

			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return vector_publ;
	}

	public Alarma buscar_Alarma(Context c, String n_serie, Logger logs) throws Exception {
		Alarma alarma = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("select * from  alarma WHERE n_Serie= '" + n_serie + "'");

			while (rs.next()) {

				alarma = new Alarma();
				alarma.setN_Serie(rs.getString("n_Serie"));
				alarma.setMarca_Modelo(rs.getString("marca_Modelo"));
				alarma.setCant_Zonas(Integer.parseInt(rs.getString("cant_Zonas")));
				alarma.setMarca_Modelo_backup(rs.getString("marca_Modelo_backup"));
				alarma.setEmpresa_Monitoreo(rs.getString("empresa_Monitoreo"));
				alarma.setFecha_Bateria(c.getpBO().fecha_sql(rs.getString("fecha_Bateria")));
				alarma.setFecha_Preventivo(c.getpBO().fecha_sql(rs.getString("fecha_Preventivo")));
				alarma.setFecha_Instalaciona(c.getpBO().fecha_sql(rs.getString("fecha_Instalacion")));
				alarma.setNota(rs.getString("nota"));
			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return alarma;
	}

	public int cantidad_filas_alarma(java.sql.Date dateInicio, java.sql.Date dateFinal, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();
			resultado = state.executeQuery("select count(*)  n_Serie from alarma WHERE fecha_Bateria >='" + dateInicio
					+ "' and fecha_Bateria < '" + dateFinal + "'");

			while (resultado.next())
				return resultado.getInt(1);

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}

		return 0;
	}

	public Alarma[] fechas_Vencimiento(Context c, java.sql.Date dateInicio, java.sql.Date dateFinal, Logger logs)
			throws Exception {
		int cant = cantidad_filas_alarma(dateInicio, dateFinal, logs);
		Alarma vector_publ[] = new Alarma[cant];
		Alarma alarma;
		int conta = 0;
		if (cant == 0)
			throw new Exception("No hay datos existentes ");
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("select cl.n_Cuenta , casa_Direccion , n_Serie,fecha_Bateria  "
					+ "from cliente cl inner join casa cs on cs.n_Cuenta= cl.n_Cuenta "
					+ "inner join alarma a on a.casa_Direccion= cs.direccion " + " WHERE fecha_Bateria >= '"
					+ dateInicio + "' and fecha_Bateria < '" + dateFinal + "'");

			while (rs.next()) {
				alarma = new Alarma();
				alarma.setN_Serie(rs.getString("n_Serie"));
				alarma.setMarca_Modelo_backup(rs.getString("n_Cuenta"));
				alarma.setCasa_Direccion(rs.getString("casa_Direccion"));
				alarma.setFecha_Bateria(c.getpBO().fecha_sql(rs.getString("fecha_Bateria")));
				vector_publ[conta] = alarma;
				conta++;

			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return vector_publ;
	}

}
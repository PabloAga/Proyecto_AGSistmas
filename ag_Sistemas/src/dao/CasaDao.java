package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;
import app.Context;
import modelo.Casa;
import modelo.Cliente;

public class CasaDao {

	Connection com;

	public CasaDao(Connection com) {
		this.com = com;
	}

	public boolean registrarCasa(Casa c, Logger logs) {
		PreparedStatement state = null;
		ResultSet resultado = null;

		String insert = "insert into casa values(?,?,?,?,?,?,?)";
		try {
			state = (PreparedStatement) com.prepareStatement(insert);
			state.setString(1, c.getDireccion());
			state.setInt(2, c.getNumero());
			state.setString(3, c.getBarrio());
			state.setString(4, c.getLocalidad());
			state.setString(5, c.getProvincia());
			state.setString(6, c.getNota());
			state.setString(7, c.getN_Cuenta());

			state.execute();
		}

		catch (SQLException e) {
			logs.log(Level.INFO, "No se a podido modificar a la casa que tiene la direccion" + c.getDireccion());
			return false;
		}

		return true;
	}

	public boolean modificar_Casa(Casa cs, Logger logs) {
		String instrucccion;
		PreparedStatement pstm = null;

		instrucccion = "UPDATE ag_sistemas.casa SET numero = " + cs.getNumero() + ",barrio = '" + cs.getBarrio()
				+ "',localidad='" + cs.getLocalidad() + "',provincia = '" + cs.getProvincia() + "',nota = '"
				+ cs.getNota() + "' WHERE (direccion = '" + cs.getDireccion() + "')";
		try {
			pstm = (PreparedStatement) com.prepareStatement(instrucccion);
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
		}

		return false;
	}

	public boolean exisCasa(Casa cs, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();
			resultado = state.executeQuery("select direccion from casa");																		
			while (resultado.next())
				if (cs.getDireccion().equals(resultado.getString(1)))
					return false;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de acceder a la base de dato");
			e.printStackTrace();
		}
		return true;
	}

	public int cantidad_filas_consulta(Cliente cl, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();
			resultado = state.executeQuery("select count(*) from  casa WHERE n_Cuenta= '" + cl.getN_Cuenta() + "'");
			while (resultado.next())
				return resultado.getInt(1);
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return 0;
	}

	public Casa[] buscar_Casa_por_N_cuenta(Context c, Cliente cl, Logger logs) throws Exception {
		int cant = cantidad_filas_consulta(cl, logs);
		Casa casa;
		if (cant == 0) {
			casa = new Casa(null, 0, null, null, null, null, null);
			Casa vector_publ[] = new Casa[1];
			vector_publ[0] = casa;
			logs.log(Level.INFO, "No hay datos existentes");
			return vector_publ;
		}
		Casa vector_publ[] = new Casa[cant];
		int conta = 0;
		if (cant == 0)
			throw new Exception("No hay datos existentes ");
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("select * from  casa WHERE n_Cuenta= '" + cl.getN_Cuenta() + "'");

			while (rs.next()) {

				casa = new Casa();
				casa.setDireccion(rs.getString("direccion"));
				casa.setNumero(Integer.parseInt(rs.getString("numero")));
				casa.setBarrio(rs.getString("barrio"));
				casa.setLocalidad(rs.getString("localidad"));
				casa.setProvincia(rs.getString("provincia"));
				casa.setNota(rs.getString("nota"));
				vector_publ[conta] = casa;
				conta++;

			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return vector_publ;
	}

	public Casa buscar_Casa(Context c, String Direccion, Logger logs) throws Exception {

		Casa casa = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("select * from  casa WHERE Direccion= '" + Direccion + "'");
			while (rs.next()) {
				casa = new Casa();
				casa.setDireccion(rs.getString("direccion"));
				casa.setNumero(Integer.parseInt(rs.getString("numero")));
				casa.setBarrio(rs.getString("barrio"));
				casa.setLocalidad(rs.getString("localidad"));
				casa.setProvincia(rs.getString("provincia"));
				casa.setNota(rs.getString("nota"));
			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return casa;
	}

	public ResultSet validarUname(int c) {
		Statement state = null;
		ResultSet resultado = null;
		try {

			state = com.createStatement();

			resultado = state.executeQuery("select * from casa where id_casa='" + c + "'");

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

}

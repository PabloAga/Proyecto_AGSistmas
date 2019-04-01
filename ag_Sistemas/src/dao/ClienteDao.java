package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;
import app.Context;
import modelo.Cliente;
import modelo.Persona;

public class ClienteDao {

	Connection com;

	public ClienteDao(Connection com) {
		this.com = com;
	}

	public boolean registrarCliente(Cliente c, Logger logs) {
		PreparedStatement state = null;
		ResultSet resultado = null;

		String insert = "insert into cliente values(?,?,?,?,?,?,?,?,?)";
		try {
			state = (PreparedStatement) com.prepareStatement(insert);
			state.setString(1, c.getN_Cuenta());
			state.setString(2, c.getNombre());
			state.setString(3, c.getApellido());
			state.setString(4, c.getEmail());
			state.setInt(5, c.getCaracteristica_Celular());
			state.setInt(6, c.getCaracteristica_Fijo());
			state.setInt(7, c.getNumero_Celular());
			state.setInt(8, c.getNumero_Fijo());
			state.setString(9, c.getPersona_Usuario());
			state.execute();
		}

		catch (SQLException e) {
			logs.log(Level.INFO, "No se a podido modificar al cliente que tiene el numero de cuenta" + c.getN_Cuenta());
			return false;
		}

		return true;
	}

	public boolean modificar(Cliente c, Logger logs) {
		File file;
		FileInputStream fis;
		String instrucccion;
		PreparedStatement pstm = null;
		instrucccion = "UPDATE ag_sistemas.cliente SET nombre = '" + c.getNombre() + "',apellido = '" + c.getApellido()
				+ "',email = '" + c.getEmail() + "',caracteristica_Celular=" + c.getCaracteristica_Celular()
				+ ",numero_Celular=" + c.getNumero_Celular() + " ',caracteristica_Fijo=" + c.getCaracteristica_Fijo()
				+ ",numero_Fijo=" + c.getNumero_Fijo() + " WHERE (n_Cuenta = '" + c.getN_Cuenta() + "')";
		try {
			pstm = (PreparedStatement) com.prepareStatement(instrucccion);
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
		}

		return false;
	}

	public int cantidad_filas_consulta(Persona p, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();
			resultado = state
					.executeQuery("select count(*) from  cliente WHERE persona_Usuario= '" + p.getUsuario() + "'");
			while (resultado.next())
				return resultado.getInt(1);
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return 0;
	}

	public Cliente[] buscar_Cliente_persona(Context c, Persona p, Logger logs) throws Exception {
		int cant = cantidad_filas_consulta(p, logs);
		Cliente cliente = new Cliente();
		if (cant == 0) {
			cliente = new Cliente(null, null, null, null, 0, 0, 0, 0, null);
			Cliente vector_publ[] = new Cliente[1];
			vector_publ[0] = cliente;
			logs.log(Level.INFO, "No hay datos existentes");
			return vector_publ;
		}
		Cliente vector_publ[] = new Cliente[cant];

		int conta = 0;
		if (cant == 0) {
			throw new Exception("No hay datos existentes ");

		}
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("SELECT * FROM cliente WHERE persona_Usuario= '" + p.getUsuario() + "'");

			while (rs.next()) {

				cliente = new Cliente();
				cliente.setN_Cuenta(rs.getString("n_Cuenta"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setEmail(rs.getString("email"));
				cliente.setCaracteristica_Celular(Integer.parseInt(rs.getString("caracteristica_Celular")));
				cliente.setNumero_Celular(Integer.parseInt(rs.getString("numero_Celular")));
				cliente.setCaracteristica_Fijo(Integer.parseInt(rs.getString("caracteristica_Fijo")));
				cliente.setNumero_Fijo(Integer.parseInt(rs.getString("numero_Fijo")));
				vector_publ[conta] = cliente;
				conta++;

			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return vector_publ;
	}

	public Cliente buscar_Cliente(Context c, String n_Cuenta, Logger logs) throws Exception {

		Cliente cliente = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("SELECT * FROM cliente WHERE n_Cuenta= '" + n_Cuenta + "'");
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setN_Cuenta(rs.getString("n_Cuenta"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setEmail(rs.getString("email"));
				cliente.setCaracteristica_Celular(Integer.parseInt(rs.getString("caracteristica_Celular")));
				cliente.setNumero_Celular(Integer.parseInt(rs.getString("numero_Celular")));
				cliente.setCaracteristica_Fijo(Integer.parseInt(rs.getString("caracteristica_Fijo")));
				cliente.setNumero_Fijo(Integer.parseInt(rs.getString("numero_Fijo")));

			}

		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de extraer informacion de la base de dato");
			e.printStackTrace();
		}
		return cliente;
	}

	public boolean exisCliente(Cliente c, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) com.createStatement();
			resultado = state.executeQuery("select n_Cuenta from cliente");// la interfaz statemet el metodo //
																			// executeQuery que retorna un obj de tipo
																			// RESULT SET
			while (resultado.next())
				if (c.getN_Cuenta().equals(resultado.getString(1)))
					return false;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de acceder a la base de dato");
			e.printStackTrace();
		}
		return true;
	}

	public Cliente obtener_Cliente(Cliente c, Logger logs) {
		Statement state = null;
		ResultSet rs = null;
		try {
			state = (Statement) com.createStatement();
			rs = state.executeQuery("select * from cliente where apellido = '" + c.getApellido() + "'");

			while (rs.next()) {

				c.setN_Cuenta(rs.getString("n_Cuenta"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setEmail(rs.getString("email"));
				c.setCaracteristica_Celular(Integer.parseInt(rs.getString("caracteristica_Celular")));
				c.setNumero_Celular(Integer.parseInt(rs.getString("numero_Celular")));
				c.setCaracteristica_Fijo(Integer.parseInt(rs.getString("caracteristica_Fijo")));
				c.setNumero_Fijo(Integer.parseInt(rs.getString("numero_Fijo")));
				return c;
			}
		} catch (SQLException e) {
			logs.log(Level.INFO, "error el querer obtener los datos del cliente con el apellido :" + c.getApellido());
			e.printStackTrace();
		}
		return c;
	}

}

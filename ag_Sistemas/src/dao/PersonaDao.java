package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import modelo.Persona;

public class PersonaDao {
	Connection conn;

	public PersonaDao() {
	}

	public PersonaDao(Connection conn) {
		this.conn = conn;
	}

	public boolean exisUsuario(Persona p, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) conn.createStatement();
			resultado = state.executeQuery("select Usuario from persona");// la interfaz statemet el metodo executeQuery
																			// que retorna un obj de tipo RESULT SET
			while (resultado.next())
				if (p.getUsuario().equals(resultado.getString(1)))
					return false;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al tratar de acceder a la base de dato");
			e.printStackTrace();
		}
		return true;
	}

	public boolean coincidircontra(Persona p) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) conn.createStatement();
			resultado = state.executeQuery("select Contraseña from persona where Usuario = '" + p.getUsuario() + "'");

			while (resultado.next()) {
				if (p.getContra().equals(resultado.getString(1))) {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Persona obtner_Persona(Persona p, Logger logs) {
		Statement state = null;
		ResultSet resultado = null;
		try {
			state = (Statement) conn.createStatement();
			resultado = state.executeQuery(
					"select Mail,Nombre,Apellido,Contraseña from persona where Usuario = '" + p.getUsuario() + "'");

			while (resultado.next()) {
				p.setMail(resultado.getString(1));
				p.setNombre(resultado.getString(2));
				p.setApellido(resultado.getString(3));
				p.setContra(resultado.getString(4));

				return p;
			}
		} catch (SQLException e) {
			logs.log(Level.INFO, "error el querer obtener los datos de la persona :" + p.getMail());
			e.printStackTrace();
		}
		return p;
	}

	public boolean insertar_persona(Persona p, Logger logs) {
		PreparedStatement state = null;
		String insert = "insert into persona(Usuario,Mail,Nombre,Apellido,Contraseña)" + " values(?,?,?,?,?)";

		try {
			state = (PreparedStatement) conn.prepareStatement(insert);
			state.setString(1, p.getUsuario());
			state.setString(2, p.getMail());
			state.setString(3, p.getNombre());
			state.setString(4, p.getApellido());
			state.setString(5, p.getContra());
			state.execute();

		} catch (SQLException e) {
			logs.log(Level.INFO, "No se a podido modificar a la persona que tiene el mail" + p.getMail());
			return true;
		}

		return false;

	}

	public boolean modificar(Persona p, Logger logs) {

		String instrucccion;
		PreparedStatement pstm = null;

		instrucccion = "UPDATE ag_sistemas.persona SET Mail = '" + p.getMail() + "' ,Nombre = '" + p.getNombre()
				+ "' ,Apellido = '" + p.getApellido() + "' ,Contraseña = '" + p.getContra() + "' WHERE (Usuario = '"
				+ p.getUsuario() + "' )";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(instrucccion);
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			logs.log(Level.INFO, "error al traar de xtraer informacion de la base de dato");
		}
		return false;

	}

}

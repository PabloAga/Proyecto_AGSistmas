package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import presentacion.MarcoLogin;

public class App {
	public static void main(String[] args) {
		Logger logs=Logger.getLogger(App.class.getName());
		logs.log(Level.FINE,"inicio de la aplicacion ");
		Context conexion = new Context();
		MarcoLogin ml=new MarcoLogin(conexion,logs);
	
	}

}

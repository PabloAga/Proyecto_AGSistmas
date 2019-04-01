package negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import app.Context;
import dao.AlarmaDao;
import modelo.Alarma;


public class AlarmaBO {

	private AlarmaDao aDao;
	private PersonaBO pBO;

	public AlarmaBO() {
	}

	public AlarmaBO(AlarmaDao aDao) {
		this.aDao = aDao;
	}

	public boolean verificacionAlarmar(Alarma a, String h, Boolean casa, JFrame marco, Context c, Logger logs) {

		pBO = new PersonaBO();
		if (pBO.letras_Correctas(a.getN_Serie()) || pBO.letras_Correctas(a.getMarca_Modelo())
				|| pBO.letras_Correctas(a.getMarca_Modelo_backup()) || pBO.letras_Correctas(a.getEmpresa_Monitoreo())
				|| pBO.letras_Correctas(a.getNota())) {
			JOptionPane.showMessageDialog(null,
					"Error, hay datos invalidos para registrar alarma .Ingrese los datos correctamente");
			return false;
		}
		if (h.contentEquals("nuevo"))
			if (!aDao.exisAlarma(a, logs)) {
				JOptionPane.showMessageDialog(null, "El numero de serie ya esta registrado en la base de datos ");
				return false;
			}
		if (a.getN_Serie().equals("")) {
			JOptionPane.showMessageDialog(null, "Error, hay datos vacios .Ingrese los datos correctamente");
			return false;
		} else {

			if (casa)
				return true;
			else
				return false;
		}

	}

	public boolean registrarAlarma(Alarma a, Boolean csa, Logger logs) {
		if (aDao.registrarAlarma(a, logs) && csa) {
			return true;
		} else {
			if (!csa)
				return false;
			JOptionPane.showMessageDialog(null, "El numero de serie ya ah sido registrado");
		}
		return false;
	}

	public boolean modificarAlarma(Alarma a, Logger logs) {
		return aDao.modificarAlarma(a, logs);
	}

	public Alarma buscarAlarma(Context c, String n_serie, Logger logs) {
		try {
			return aDao.buscar_Alarma(c, n_serie, logs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo buscar a la alarma");
			e.printStackTrace();
		}
		return null;

	}

	public boolean verificarFecha(String fecha, Boolean f_fecha) {
		boolean res = true;
		res = validarFecha(fecha);

		if (res == true && f_fecha) {
			return true;
		} else
			return false;
	}

	public static boolean validarFecha(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public java.sql.Date fechaSQL(String fecha) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date fecha_SQL = null;
		Date fecha_date;
		try {
			fecha_date = format.parse(fecha);
			fecha_SQL = new java.sql.Date(fecha_date.getTime());
			return fecha_SQL;
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "El formato de fecha es incorrecto");

		}
		return fecha_SQL;

	}

}

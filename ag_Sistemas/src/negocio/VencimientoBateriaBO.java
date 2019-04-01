package negocio;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import app.Context;
import dao.AlarmaDao;
import modelo.Alarma;

public class VencimientoBateriaBO extends DefaultTableCellRenderer {
	private AlarmaDao aDao;
	private Alarma[] array_a;

	public VencimientoBateriaBO() {
	}

	public VencimientoBateriaBO(AlarmaDao aDao) {
		super();
		this.aDao = aDao;
	}

	public Alarma[] Tabla_Vencimiento_Bateria(java.sql.Date fecha, Context c, Logger logs) {

		try {

			Date fechaI = operarDiasFecha(fecha, -7);
			java.sql.Date fechaInicio = new java.sql.Date(fechaI.getTime());
			array_a = aDao.fechas_Vencimiento(c, fechaInicio, fecha, logs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array_a;

	}

	public static java.sql.Date sumarMesesAFecha(java.sql.Date fecha, int meses) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date fecha_SQL = null;
		Date fecha_date = null;
		Calendar calendar = null;
		String dia_bisiesto;
		int year;
		boolean bisiesto;
		if (meses == 0)
			return fecha;

		fecha_date = (Date) fecha;
		calendar = Calendar.getInstance();
		calendar.setTime(fecha_date);
		calendar.add(Calendar.MONTH, meses);
		fecha_date = calendar.getTime();
		fecha_SQL = new java.sql.Date(fecha_date.getTime());
		year = fecha_date.getYear() + 1900;
		bisiesto = ((GregorianCalendar) calendar).isLeapYear(year);
		dia_bisiesto = String.valueOf(year) + "-02-28"; // si la fecha de esta en un año bisiesto y es despues del 28 de
														// febrero se suma un dia
		if (bisiesto) {
			try {
				if (fecha_date.after(format.parse(dia_bisiesto))) { // verifica si la fecha es posterior al 28 de
																	// febrero
					fecha_date = operarDiasFecha(fecha_date, 1);
					fecha_SQL = new java.sql.Date(fecha_date.getTime());
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}
			return fecha_SQL;

	}

	public static Date operarDiasFecha(Date fecha, int dias) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (dias == 0)
			return fecha;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		String fecha_formato = format.format(calendar.getTime());

		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) // condicion si el dia es domingo
			fecha = operarDiasFecha(calendar.getTime(), 1);
		try {
			fecha = format.parse(fecha_formato);
			return fecha;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}

	public String fecha_Formato_normal(java.sql.Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fecha_formato = format.format(date);
		return fecha_formato;

	}

	public java.sql.Date fechaActual() {
		Date fecha = new Date(); // Sistema actual La fecha y la hora se asignan a objDate
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_formato = format.format(fecha);
		java.sql.Date fecha_SQL = null;
		Date fecha_date;
		try {
			fecha_date = format.parse(fecha_formato);
			fecha_SQL = new java.sql.Date(fecha_date.getTime());
			return fecha_SQL;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha_SQL;

	}

	public String fechaActualstring() {
		Date fecha = new Date(); // Sistema actual La fecha y la hora se asignan a objDate
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_formato = format.format(fecha);
		return fecha_formato;

	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (value instanceof JButton) {
			JButton btn = (JButton) value;
			if (isSelected) {
				btn.setForeground(table.getSelectionForeground());
				btn.setBackground(table.getSelectionBackground());
			} else {
				btn.setForeground(table.getForeground());
				btn.setBackground(UIManager.getColor("Button.background"));
			}
			return btn;
		}

		if (value instanceof JCheckBox) {
			JCheckBox ch = (JCheckBox) value;

			return ch;
		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}

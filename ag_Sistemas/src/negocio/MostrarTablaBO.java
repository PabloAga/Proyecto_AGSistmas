package negocio;

import java.awt.Component;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import app.Context;
import dao.AlarmaDao;
import dao.CasaDao;
import dao.ClienteDao;
import modelo.Alarma;
import modelo.Cliente;
import modelo.Persona;
import modelo.Casa;

public class MostrarTablaBO {

	private AlarmaDao aDao;

	private CasaDao csDao;
	private ClienteDao cDao;
	private Cliente[] array_c;
	private Alarma[] array_a;
	private Casa[] array_cs;

	public MostrarTablaBO(AlarmaDao aDao, CasaDao csDao, ClienteDao cDao) {
		super();
		this.aDao = aDao;
		this.csDao = csDao;
		this.cDao = cDao;
	}

	public Cliente[] propiedades_Tabla_cliente(Persona p, Context c, Logger logs) {

		try {
			array_c = cDao.buscar_Cliente_persona(c, p, logs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array_c;

	}

	public Alarma[] propiedades_Tabla_alarma(Casa casa, Context c, Logger logs) {

		try {
			array_a = aDao.buscar_Alarma_por_Dirccion(c, casa, logs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array_a;

	}

	public Casa[] propiedades_Tabla_casa(Cliente cl, Context c, Logger logs) {

		try {
			array_cs = csDao.buscar_Casa_por_N_cuenta(c, cl, logs);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return array_cs;
	}

	public class formateando_Tabla extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof JLabel) {
				JLabel label = (JLabel) value;
				return label;
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

	public void cambiar_panel(JPanel lp, JFrame marco) {
		lp.setBorder(new EmptyBorder(5, 5, 5, 5));
		marco.getContentPane().removeAll();
		marco.getContentPane().repaint();
		marco.getContentPane().add(lp);
		marco.setSize(lp.getSize());
	}

}

package presentacion;

import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import app.Context;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import negocio.VencimientoBateriaBO;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Vencimiento_Bateria extends JPanel {

	private JPanel contentPane;
	private JTable table;
	private Alarma alarma;
	private Casa casa;
	private Cliente cliente;
	private VencimientoBateriaBO vbBO;
	private boolean[] editable = { false, false, false, false, false, false, false, false, true, true };
	JScrollPane mibarra1;

	public Vencimiento_Bateria(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(852, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		vbBO = new VencimientoBateriaBO();
		JLabel lblClientesConBaterias = new JLabel("Clientes con Baterias a vencer");
		lblClientesConBaterias.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblClientesConBaterias.setBounds(247, 26, 353, 33);
		add(lblClientesConBaterias);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 171, 731, 232);
		add(scrollPane);

		table = new JTable();
		alarma = new Alarma();
		casa = new Casa();
		cliente = new Cliente();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Nombre", "Apellido", "email", "Direccion", "Numero", "Placa-Modelo", "Nota",
						"Vencimiento_Bateria", "enviar", "confirmar" }));
		table.getColumnModel().getColumn(3).setPreferredWidth(600);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.setRowHeight(23);
		scrollPane.setViewportView(table);

		Alarma[] array_a = c.getVbBO().Tabla_Vencimiento_Bateria(vbBO.fechaActual(), c, logs);
		String titulos[] = { "Nombre", "Apellido", "email", "Direccion", "Numero", "Placa-Modelo", "Nota",
				"Vencimiento_Bateria", "enviar", "confirmar" };
		try {
			table.setDefaultRenderer(Object.class, new negocio.Render());
			DefaultTableModel tm = new DefaultTableModel(titulos, 0) {

				Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
						java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
						java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class };

				public Class getColumnClass(int columnIndex) {
					return types[columnIndex];
				}

				public boolean isCellEditable(int row, int column) {
					return editable[column];
				}

			};

			for (int i = 0; i < array_a.length; i++) { /// para pasar blob a imagnen
				alarma = c.getaBO().buscarAlarma(c, array_a[i].getN_Serie(), logs);
				casa = c.getcsBO().buscarCasa(c, array_a[i].getCasa_Direccion(), logs);
				cliente = c.getclBO().buscarCliente(c, array_a[i].getMarca_Modelo_backup(), logs);
				// agrego datos a la fila de la tabla
				tm.addRow(new Object[] { cliente.getNombre(), cliente.getApellido(), cliente.getEmail(),
						casa.getDireccion(), casa.getNumero(), alarma.getMarca_Modelo(), alarma.getNota(),
						alarma.getFecha_Bateria() });
				table.setModel(tm);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			DefaultTableModel tm = new DefaultTableModel(null, titulos);
			table.setModel(tm);
		}
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.getEmBO().enviar(table, array_a, p, c, logs);

			}
		});
		btnNewButton.setBounds(523, 462, 97, 25);
		add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(692, 462, 97, 25);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(m, marco);
			}
		});

		add(btnCancelar);

		JButton btnConfirmacion = new JButton("Confirmacion");
		btnConfirmacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.getEmBO().confirmar(table, array_a, p, c, logs);
				Vencimiento_Bateria vb = new Vencimiento_Bateria(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(vb, marco);

			}
		});
		btnConfirmacion.setBounds(682, 413, 121, 25);
		add(btnConfirmacion);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClientes.setBounds(21, 138, 97, 25);
		add(lblClientes);

	}
}

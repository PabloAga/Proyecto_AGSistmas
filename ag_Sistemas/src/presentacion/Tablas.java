package presentacion;

import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Date;
import app.Context;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import negocio.AlarmaBO;
import negocio.MostrarTablaBO.formateando_Tabla;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Tablas extends JPanel {
	private JPanel contentPane;
	private JTable table_casa;
	private JTable table_alarma;
	private Casa casa;
	private Alarma alarma;

	private Alarma[] array_a;
	private Casa[] array_cs;
	JScrollPane mibarra1;

	/**
	 * Create the panel.
	 */
	public Tablas(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(852, 724);
		JLabel lblNcuenta = new JLabel("N_cuenta");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(43, 74, 141, 16);
		add(lblNcuenta);

		JLabel lblEditarCliente = new JLabel("Editar Cliente");
		lblEditarCliente.setBounds(337, 13, 129, 27);
		lblEditarCliente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lblEditarCliente);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(43, 103, 81, 16);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(43, 132, 78, 16);
		add(lblApellido);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(448, 74, 56, 16);
		add(lblEmail);

		JLabel lblNuemeroCelular = new JLabel("Nuemero celular");
		lblNuemeroCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNuemeroCelular.setBounds(448, 103, 145, 16);
		add(lblNuemeroCelular);

		JLabel lblNumeroDeTelefono = new JLabel("Numero de Telefono Fijo");
		lblNumeroDeTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroDeTelefono.setBounds(448, 132, 186, 16);
		add(lblNumeroDeTelefono);

		JLabel n_cuenta = new JLabel(ct.getN_Cuenta());
		n_cuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		n_cuenta.setBounds(157, 76, 92, 14);
		add(n_cuenta);

		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(678, 103, 56, 16);
		add(label);

		JLabel nombre = new JLabel(ct.getNombre());
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre.setBounds(157, 100, 116, 22);
		add(nombre);

		JLabel apellido = new JLabel(ct.getApellido());
		apellido.setBounds(157, 129, 116, 22);
		add(apellido);
		apellido.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel email = new JLabel(ct.getEmail());
		email.setBounds(624, 71, 216, 22);
		add(email);
		email.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel numero_celular = new JLabel(String.valueOf(ct.getNumero_Celular()));
		numero_celular.setBounds(699, 100, 116, 22);
		add(numero_celular);
		numero_celular.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel = new JLabel(String.valueOf(ct.getCaracteristica_Fijo()));
		lblNewLabel.setBounds(634, 132, 68, 16);
		add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel numero_fijo = new JLabel(String.valueOf(ct.getNumero_Fijo()));
		numero_fijo.setBounds(699, 129, 116, 22);
		add(numero_fijo);
		numero_fijo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel c_celular = new JLabel(String.valueOf(ct.getCaracteristica_Celular()));
		c_celular.setBounds(634, 100, 68, 22);
		add(c_celular);
		c_celular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JScrollPane scrollPane_casa = new JScrollPane();
		scrollPane_casa.setBounds(43, 223, 437, 81);
		add(scrollPane_casa);

		table_casa = new JTable();
		table_casa.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Direccion", "Numero", "Barrio", "Localidad", "Provincia", "nota" }));

		scrollPane_casa.setViewportView(table_casa);

		array_cs = c.getmtBO().propiedades_Tabla_casa(ct, c, logs);

		String titulos[] = { " Direccion", "Numero", "Barrio", "Localidad", "Provincia", "nota" };
		try {

			DefaultTableModel tm = new DefaultTableModel(null, titulos);

			for (int i = 0; i < array_cs.length; i++) {
				// agrego datos a la fila de la tabla
				tm.addRow(new Object[] { array_cs[i].getDireccion(), array_cs[i].getNumero(), array_cs[i].getBarrio(),
						array_cs[i].getLocalidad(), array_cs[i].getProvincia(), array_cs[i].getNota(), });
				table_casa.setModel(tm);
			}
			// averiguom la fila en que me hace click
			table_casa.addMouseListener(new java.awt.event.MouseAdapter() {

				public void mouseClicked(java.awt.event.MouseEvent e) {
					int fila = table_casa.rowAtPoint(e.getPoint());
					int columna = table_casa.columnAtPoint(e.getPoint());
					StringBuilder sb = new StringBuilder();

					casa = new Casa();
					casa.setDireccion((String) table_casa.getModel().getValueAt(fila, 0));
					casa.setNumero((Integer) table_casa.getModel().getValueAt(fila, 1));
					casa.setBarrio((String) table_casa.getModel().getValueAt(fila, 2));
					casa.setLocalidad((String) table_casa.getModel().getValueAt(fila, 3));
					casa.setProvincia((String) table_casa.getModel().getValueAt(fila, 4));
					casa.setNota((String) table_casa.getModel().getValueAt(fila, 5));
					marco.getJMenuBar();
					Tabla_Alarma(c, p, a, casa, ct, marco, logs);

				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DefaultTableModel tm = new DefaultTableModel(null, titulos);
			table_casa.setModel(tm);
		}

		JLabel lblClientes = new JLabel("Casa/Sucursal_Cliente");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClientes.setBounds(43, 181, 190, 16);
		add(lblClientes);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(529, 571, 97, 25);
		add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(m, marco);
			}
		});

		add(btnAtras);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(689, 571, 97, 25);
		add(btnGuardar);

		JButton btnModificarCliente = new JButton("Modificar Cliente");
		btnModificarCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModificarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Modificar_Cliente mCl = new Modificar_Cliente(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(mCl, marco);

			}
		});
		btnModificarCliente.setBounds(624, 179, 152, 25);
		add(btnModificarCliente);

		JButton btnA = new JButton("A\u00F1adir Casa");
		btnA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Nueva_Casa nC = new Nueva_Casa(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(nC, marco);

			}
		});
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnA.setBounds(234, 178, 142, 25);
		add(btnA);

		JLabel label_1 = new JLabel("-");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(678, 132, 56, 16);
		add(label_1);

	}

	public void Tabla_Alarma(Context c, Persona p, Alarma a, Casa casa, Cliente ct, JFrame marco, Logger logs) {
		updateUI();
		JButton btnModificarCasa = new JButton("Modificar Casa");
		btnModificarCasa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Modificar_Casa mCs = new Modificar_Casa(c, p, a, casa, ct, marco, logs);
				c.getpBO().cambiar_panel(mCs, marco);

			}
		});
		btnModificarCasa.setBounds(624, 278, 129, 25);
		add(btnModificarCasa);

		JButton btnAadirAlarma = new JButton("A\u00F1adir Alarma");
		btnAadirAlarma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Nueva_Alarma nA = new Nueva_Alarma(c, p, a, casa, ct, marco, logs);
				c.getmtBO().cambiar_panel(nA, marco);

			}
		});
		btnAadirAlarma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAadirAlarma.setBounds(129, 317, 137, 25);
		add(btnAadirAlarma);

		JLabel lblAlarmas_1 = new JLabel("Alarmas");
		lblAlarmas_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlarmas_1.setBounds(43, 324, 89, 16);
		add(lblAlarmas_1);

		JLabel lblAlarmas = new JLabel("Alarmas");
		lblAlarmas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlarmas.setBounds(46, 273, 93, 16);
		add(lblAlarmas);

		JScrollPane scrollPane_alarma = new JScrollPane();
		scrollPane_alarma.setBounds(43, 353, 731, 132);
		add(scrollPane_alarma);

		table_alarma = new JTable();
		table_alarma.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null }, },
						new String[] { "N-Serie", "Marca-Modelo", "Cant_Zonas", "Marca-Modelo-Backup",
								"Empresa-Monitoreo", "Fecha-Bateria", "Fecha-Ultimo-Preventivo", "Fecha-Instalacion",
								"Nota" }));
		scrollPane_alarma.setViewportView(table_alarma);
		array_a = c.getmtBO().propiedades_Tabla_alarma(casa, c, logs);
		String titulos2[] = { "N-Serie", "Marca-Modelo", "Cant_Zonas", "Marca-Modelo-Backup", "Empresa-Monitoreo",
				"Fecha-Bateria", "Fecha-Ultimo-Preventivo", "Fecha-Instalacion", "Nota" };
		try {

			DefaultTableModel tmp = new DefaultTableModel(null, titulos2);

			for (int i = 0; i < array_a.length; i++) {
				// agrego datos a la fila de la tabla
				tmp.addRow(new Object[] { array_a[i].getN_Serie(), array_a[i].getMarca_Modelo(),
						array_a[i].getCant_Zonas(), array_a[i].getMarca_Modelo_backup(),
						array_a[i].getEmpresa_Monitoreo(), array_a[i].getFecha_Bateria(),
						array_a[i].getFecha_Preventivo(), array_a[i].getFecha_Instalaciona(), array_a[i].getNota() });
				table_alarma.setModel(tmp);
			}
			// averiguom la fila en que me hace click
			table_alarma.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int fila = table_alarma.rowAtPoint(e.getPoint());
					int columna = table_alarma.columnAtPoint(e.getPoint());
					StringBuilder sb = new StringBuilder();

					alarma = new Alarma();
					Cliente clte = new Cliente();
					alarma.setN_Serie((String) table_alarma.getModel().getValueAt(fila, 0));
					alarma.setMarca_Modelo((String) table_alarma.getModel().getValueAt(fila, 1));
					alarma.setCant_Zonas((Integer) table_alarma.getModel().getValueAt(fila, 2));
					alarma.setMarca_Modelo_backup((String) table_alarma.getModel().getValueAt(fila, 3));
					alarma.setEmpresa_Monitoreo((String) table_alarma.getModel().getValueAt(fila, 4));
					alarma.setFecha_Bateria((Date) table_alarma.getModel().getValueAt(fila, 5));
					alarma.setFecha_Preventivo((Date) table_alarma.getModel().getValueAt(fila, 6));
					alarma.setFecha_Instalaciona((Date) table_alarma.getModel().getValueAt(fila, 7));
					alarma.setNota((String) table_alarma.getModel().getValueAt(fila, 8));
					marco.getJMenuBar();
					mAlarma(c, p, alarma, casa, ct, marco, logs);

				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DefaultTableModel tmp = new DefaultTableModel(null, titulos2);
			table_casa.setModel(tmp);
		}
	}

	public void mAlarma(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		updateUI();
		JButton btnModificarAlarma = new JButton("Modificar Alarma");
		btnModificarAlarma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Modificar_Alarma mAL = new Modificar_Alarma(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(mAL, marco);

			}
		});
		btnModificarAlarma.setBounds(657, 517, 152, 25);
		add(btnModificarAlarma);
	}
}

package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import app.Context;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class Menu extends JPanel {

	private JPanel contentPane;
	private JTable table;
	private JMenuBar barraMenu;
	private JMenu menuArchivo, menuEdicion;
	private JMenuItem menuItemNuevo_Cliente, menuItemSalir, mntmEditarPerfil, menuItemVencimientto_B;
	private Registro_Cliente reg;
	private MiPerfil perfil;
	private Tablas tabla;
	JScrollPane mibarra1;

	public Menu(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(852, 624);
		barraMenu = new JMenuBar();
		menuEdicion = new JMenu("Edicion");
		menuArchivo = new JMenu("Archivo");
		menuItemNuevo_Cliente = new JMenuItem("Nuevo Cliente");

		menuItemVencimientto_B = new JMenuItem("Vencimiento Baterias");
		menuItemSalir = new JMenuItem("Salir");
		mntmEditarPerfil = new JMenuItem("Editar Perfil");
		JLabel lblAg = new JLabel("AG");
		lblAg.setFont(new Font("Arial Black", Font.BOLD, 35));
		lblAg.setForeground(Color.BLUE);
		lblAg.setBounds(23, 13, 60, 65);
		add(lblAg);

		JLabel lblNewLabel = new JLabel("Sistemas");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(87, 44, 129, 16);
		add(lblNewLabel);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClientes.setBounds(23, 135, 107, 16);
		add(lblClientes);

		menuArchivo.add(menuItemNuevo_Cliente);
		menuArchivo.add(menuItemSalir);
		menuArchivo.add(menuItemVencimientto_B);

		barraMenu.add(menuArchivo);
		barraMenu.add(menuEdicion);
		marco.setJMenuBar(barraMenu);
		menuEdicion.add(mntmEditarPerfil);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 171, 731, 287);
		add(scrollPane);

		JButton a_clientes = new JButton("A\u00F1adir Cliente");
		a_clientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				marco.getJMenuBar();
				reg = new Registro_Cliente(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(reg, marco);

			}
		});
		a_clientes.setBounds(632, 133, 120, 25);
		add(a_clientes);

		JButton btnVencimientoBateria = new JButton("Vencimiento Bateria");
		btnVencimientoBateria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vencimiento_Bateria vb = new Vencimiento_Bateria(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(vb, marco);

			}
		});
		btnVencimientoBateria.setBounds(139, 133, 154, 25);
		add(btnVencimientoBateria);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "N_cuenta", "Nombre", "Apellido", "Email", "Carac_Celular", "Numero de Celular",
						"Carac_Fijo", "Numero de telefono fijo" }));
		table.getColumnModel().getColumn(4).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.setGridColor(new java.awt.Color(0, 0, 0));
		scrollPane.setViewportView(table);

		Cliente[] array_c = c.getmtBO().propiedades_Tabla_cliente(p, c, logs);

		String titulos[] = { "N_cuenta", "Nombre", "Apellido", "Email", "Carac_Celular", "Numero de Celular",
				"Carac_Fijo", "Numero de telefono fijo" };
		try {
			
			DefaultTableModel tm = new DefaultTableModel(null, titulos);

			for (int i = 0; i < array_c.length; i++) { /// para pasar blob a imagnen
				tm.addRow(new Object[] { array_c[i].getN_Cuenta(), array_c[i].getNombre(), array_c[i].getApellido(),
						array_c[i].getEmail(), array_c[i].getCaracteristica_Celular(), array_c[i].getNumero_Celular(),
						array_c[i].getCaracteristica_Fijo(), array_c[i].getNumero_Fijo() });
				table.setModel(tm);

			}
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int fila = table.rowAtPoint(e.getPoint());
					int columna = table.columnAtPoint(e.getPoint());
					StringBuilder sb = new StringBuilder();

					Cliente clte = new Cliente();
					clte.setN_Cuenta((String) table.getModel().getValueAt(fila, 0));
					clte.setNombre((String) table.getModel().getValueAt(fila, 1));
					clte.setApellido((String) table.getModel().getValueAt(fila, 2));
					clte.setEmail((String) table.getModel().getValueAt(fila, 3));
					clte.setCaracteristica_Celular((Integer) table.getModel().getValueAt(fila, 4));
					clte.setNumero_Celular((Integer) table.getModel().getValueAt(fila, 5));
					clte.setCaracteristica_Fijo((Integer) table.getModel().getValueAt(fila, 6));
					clte.setNumero_Fijo((Integer) table.getModel().getValueAt(fila, 7));
					marco.getJMenuBar();
					tabla = new Tablas(c, p, a, cs, clte, marco, logs);
					c.getmtBO().cambiar_panel(tabla, marco);

				}

			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
			DefaultTableModel tm = new DefaultTableModel(null, titulos);
			table.setModel(tm);
		}

		menuItemNuevo_Cliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				marco.getJMenuBar();
				reg = new Registro_Cliente(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(reg, marco);

			}
		});

		menuItemSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		menuItemVencimientto_B.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vencimiento_Bateria vB = new Vencimiento_Bateria(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(vB, marco);

			}
		});

		mntmEditarPerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				perfil = new MiPerfil(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(perfil, marco);

			}
		});

	}

}

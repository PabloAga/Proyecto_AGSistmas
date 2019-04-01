package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import app.Context;
import modelo.Cliente;
import modelo.Persona;
import modelo.Casa;
import modelo.Alarma;
import negocio.ClienteBO;
import negocio.PersonaBO;
import negocio.VencimientoBateriaBO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class Registro_Cliente extends JPanel {

	private JTextField nombre_a;
	private JTextField c_celular;
	private JTextField celular;
	private JTextField direccion;
	private JTextField ciudad;
	private JTextField n_cuenta;
	private JTextField monitoreo;
	private JTextField email;
	private JTextField nombre_placa;
	private JTextField c_zonas;
	private JTextField Backup_celular;
	private JTextField n_Serie;
	private JTextField nota_alarma;
	private JTextField provincia;
	private JTextField txtApellido;
	private JTextField telfijo;
	private JTextField numeo_Direccion;
	private JTextField barrio;
	private JTextField nota_casa;
	private Cliente cliente;
	private Alarma alarma;
	private Casa casa;
	private ClienteBO clBO;
	private VencimientoBateriaBO vbBO;
	private boolean verificacion_cl = false;
	private boolean verificacion_cs = false;
	private boolean verificacion_a = false;
	private JTextField meses;
	private JTextField c_fijo;

	public Registro_Cliente(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(827, 800);
		JLabel lblRegistrarCliente = new JLabel("Registro");
		lblRegistrarCliente.setBounds(342, 36, 161, 27);
		lblRegistrarCliente.setFont(new Font("Arial", Font.PLAIN, 21));
		add(lblRegistrarCliente);

		JLabel lblNombreYApellido = new JLabel("Nombre ");
		lblNombreYApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreYApellido.setBounds(22, 97, 70, 25);
		add(lblNombreYApellido);

		nombre_a = new JTextField();
		nombre_a.setBounds(156, 98, 208, 22);
		add(nombre_a);
		nombre_a.setColumns(10);

		JLabel lblCelular = new JLabel("Caracteristica-Celular");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCelular.setBounds(409, 102, 167, 16);
		add(lblCelular);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(55, 197, 13, -1);
		add(lblNewLabel);

		c_celular = new JTextField();
		c_celular.setBounds(572, 99, 60, 22);
		add(c_celular);
		c_celular.setColumns(10);
		celular = new JTextField();
		celular.setBounds(667, 99, 116, 22);
		add(celular);
		celular.setColumns(10);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDireccion.setBounds(22, 284, 104, 16);
		add(lblDireccion);

		direccion = new JTextField();
		direccion.setBounds(156, 284, 208, 22);
		add(direccion);
		direccion.setColumns(10);

		JLabel lblCiudad = new JLabel("Localidad");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCiudad.setBounds(409, 335, 70, 16);
		add(lblCiudad);

		ciudad = new JTextField();
		ciudad.setBounds(572, 333, 208, 22);
		add(ciudad);
		ciudad.setColumns(10);

		JLabel lblNcuenta = new JLabel("N_cuenta");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(409, 180, 104, 16);
		add(lblNcuenta);

		n_cuenta = new JTextField();
		n_cuenta.setBounds(572, 174, 116, 22);
		add(n_cuenta);
		n_cuenta.setColumns(10);

		JLabel lblMonitoreo = new JLabel("Empresa de monitoreo");
		lblMonitoreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMonitoreo.setBounds(394, 555, 167, 16);
		add(lblMonitoreo);

		JLabel lblAlarma = new JLabel("Alarma");
		lblAlarma.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAlarma.setBounds(22, 451, 123, 32);
		add(lblAlarma);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(22, 180, 82, 16);
		add(lblEmail);

		monitoreo = new JTextField();
		monitoreo.setBounds(572, 553, 116, 22);
		add(monitoreo);
		monitoreo.setColumns(10);

		email = new JTextField();
		email.setBounds(156, 178, 228, 22);
		add(email);
		email.setColumns(10);

		JLabel lblNombrePlaca = new JLabel("Marca-Modelo ");
		lblNombrePlaca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombrePlaca.setBounds(22, 511, 116, 16);
		add(lblNombrePlaca);

		nombre_placa = new JTextField();
		nombre_placa.setBounds(156, 509, 128, 22);
		add(nombre_placa);
		nombre_placa.setColumns(10);

		JLabel lblCantidadDeZonas = new JLabel("Cantidad de Zonas");
		lblCantidadDeZonas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidadDeZonas.setBounds(394, 511, 148, 16);
		add(lblCantidadDeZonas);

		c_zonas = new JTextField();
		c_zonas.setBounds(572, 509, 82, 22);
		add(c_zonas);
		c_zonas.setColumns(10);

		JLabel lblBackupCelular = new JLabel("Backup celular ");
		lblBackupCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBackupCelular.setBounds(22, 603, 143, 16);
		add(lblBackupCelular);

		Backup_celular = new JTextField();
		Backup_celular.setBounds(156, 601, 116, 22);
		add(Backup_celular);
		Backup_celular.setColumns(10);

		n_Serie = new JTextField();
		n_Serie.setBounds(156, 553, 116, 22);
		add(n_Serie);
		n_Serie.setColumns(10);

		JLabel lblNserie = new JLabel("N_serie");
		lblNserie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNserie.setBounds(22, 555, 56, 16);
		add(lblNserie);
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(22, 143, 70, 16);
		add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(156, 141, 116, 22);
		add(txtApellido);
		txtApellido.setColumns(10);

		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(644, 101, 56, 16);
		add(label);

		JLabel lblTelefonoFijo = new JLabel("Telefono Fijo");
		lblTelefonoFijo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefonoFijo.setBounds(409, 144, 114, 16);
		add(lblTelefonoFijo);

		telfijo = new JTextField();
		telfijo.setBounds(667, 139, 116, 22);
		add(telfijo);
		telfijo.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCliente.setBounds(22, 68, 82, 16);
		add(lblCliente);

		JLabel lblCasalocal = new JLabel("Casa/Local");
		lblCasalocal.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCasalocal.setBounds(22, 228, 123, 27);
		add(lblCasalocal);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumero.setBounds(409, 284, 78, 16);
		add(lblNumero);

		numeo_Direccion = new JTextField();
		numeo_Direccion.setBounds(572, 282, 116, 22);
		add(numeo_Direccion);
		numeo_Direccion.setColumns(10);

		JLabel lblBarrio = new JLabel("Barrio");
		lblBarrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBarrio.setBounds(22, 323, 56, 16);
		add(lblBarrio);

		barrio = new JTextField();
		barrio.setBounds(156, 319, 116, 22);
		add(barrio);
		barrio.setColumns(10);

		JLabel lblNota_1 = new JLabel("Nota");
		lblNota_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota_1.setBounds(22, 421, 56, 16);
		add(lblNota_1);

		nota_casa = new JTextField();
		nota_casa.setBounds(156, 419, 523, 22);
		add(nota_casa);
		nota_casa.setColumns(10);
		nota_alarma = new JTextField();
		nota_alarma.setBounds(156, 646, 523, 27);
		add(nota_alarma);
		nota_alarma.setColumns(10);
		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota.setBounds(22, 657, 56, 16);
		add(lblNota);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			add(buttonPane, BorderLayout.SOUTH);
			{

			}
		}

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(503, 699, 97, 25);
		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				vbBO = new VencimientoBateriaBO();
				clBO = new ClienteBO();
				boolean confirmacion = false;
				boolean confirmacionA = false;
				cliente = new Cliente(n_cuenta.getText(), nombre_a.getText(), txtApellido.getText(), email.getText(),
						clBO.numeros_Correctos(c_celular.getText()), clBO.numeros_Correctos(celular.getText()),
						clBO.numeros_Correctos(c_fijo.getText()), clBO.numeros_Correctos(telfijo.getText()),
						p.getUsuario());

				casa = new Casa(direccion.getText(), clBO.numeros_Correctos(numeo_Direccion.getText()),
						barrio.getText(), ciudad.getText(), provincia.getText(), nota_casa.getText(),
						n_cuenta.getText());

				alarma = new Alarma(n_Serie.getText(), nombre_placa.getText(),
						clBO.numeros_Correctos(c_zonas.getText()), Backup_celular.getText(), monitoreo.getText(),
						vbBO.sumarMesesAFecha(vbBO.fechaActual(), clBO.numeros_Correctos(meses.getText())),
						vbBO.fechaActual(), vbBO.fechaActual(), nota_alarma.getText(), direccion.getText());

				verificacion_cl = c.getclBO().verificacionCliente(cliente, "nuevo", marco, c, logs);
				verificacion_cs = c.getcsBO().verificacionCasa(casa, "nuevo", verificacion_cl, marco, c, logs);
				verificacion_a = c.getaBO().verificacionAlarmar(alarma, "nuevo", verificacion_cs, marco, c, logs);

				if (verificacion_a) {
					confirmacion = c.getclBO().registrarCliente(cliente, logs);
					confirmacion = c.getcsBO().registrarACasa(casa, confirmacion, logs);
					confirmacionA = c.getaBO().registrarAlarma(alarma, confirmacion, logs);
					if (confirmacionA) {
						JOptionPane.showMessageDialog(null, "Se ha registrado de manera exitosa");
						Menu m = new Menu(c, p, a, cs, ct, marco, logs);
						c.getpBO().cambiar_panel(m, marco);
					}
				} else
					JOptionPane.showMessageDialog(null, "Error, Verifique  los errores informados");
			}
		});
		add(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(686, 699, 97, 25);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu(c, p, a, cs, ct, marco, logs);
				c.getpBO().cambiar_panel(m, marco);
			}
		});

		add(btnCancelar);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProvincia.setBounds(22, 356, 104, 16);
		add(lblProvincia);

		provincia = new JTextField();
		provincia.setBounds(156, 354, 116, 22);
		add(provincia);
		provincia.setColumns(10);

		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(284, 144, 56, 16);
		add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(700, 181, 56, 16);
		add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(368, 287, 56, 16);
		add(label_3);

		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(284, 555, 56, 16);
		add(lblNewLabel_1);

		JLabel label_4 = new JLabel("*");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(394, 180, 56, 16);
		add(label_4);

		JLabel lblLosCamposQue = new JLabel("Los campos que tienen \" * \" al final son obligatorios.");
		lblLosCamposQue.setForeground(Color.RED);
		lblLosCamposQue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLosCamposQue.setBounds(22, 686, 362, 16);
		add(lblLosCamposQue);

		JLabel lblCantidadDeMeses = new JLabel("Cantidad de meses para cambio de bateria ");
		lblCantidadDeMeses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidadDeMeses.setBounds(394, 603, 306, 16);
		add(lblCantidadDeMeses);

		meses = new JTextField("18");
		meses.setBounds(718, 601, 38, 22);
		add(meses);
		meses.setColumns(10);

		c_fijo = new JTextField();
		c_fijo.setColumns(10);
		c_fijo.setBounds(572, 139, 60, 22);
		add(c_fijo);

		JLabel label_5 = new JLabel("-");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(644, 145, 56, 16);
		add(label_5);

	}
}

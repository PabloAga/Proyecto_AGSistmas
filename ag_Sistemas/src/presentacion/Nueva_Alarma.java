package presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Context;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import negocio.AlarmaBO;
import negocio.ClienteBO;
import negocio.VencimientoBateriaBO;

import java.awt.Color;
import javax.swing.JRadioButton;

public class Nueva_Alarma extends JPanel {

	private JTextField marca_m;
	private JTextField zonas;
	private JTextField f_bateria;
	private JTextField f_preventivo;
	private JTextField monitoreo;
	private JTextField marca_backup;
	private JTextField nota;
	private JTextField n_serie;
	private JTextField f_instala;
	private JTextField meses;

	private AlarmaBO aBO;
	private ClienteBO clBO;
	private VencimientoBateriaBO vbBO;
	private Alarma alarma;

	public Nueva_Alarma(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(962, 550);
		vbBO = new VencimientoBateriaBO();

		JLabel lblNcuenta = new JLabel("N_Serie");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(43, 74, 141, 16);
		add(lblNcuenta);

		JLabel lbnuevaCliente = new JLabel("Agrego Alarma");
		lbnuevaCliente.setBounds(401, 13, 159, 27);
		lbnuevaCliente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lbnuevaCliente);

		JLabel lblNombre = new JLabel("Marca Modelo");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(40, 125, 104, 16);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Cant zonas");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(43, 185, 78, 16);
		add(lblApellido);

		JLabel lblEmail = new JLabel("Fecha de Bateria");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(447, 240, 129, 16);
		add(lblEmail);

		JLabel lblNuemeroCelular = new JLabel("Fecha de Preventivo");
		lblNuemeroCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNuemeroCelular.setBounds(447, 185, 145, 16);
		add(lblNuemeroCelular);

		JLabel lblNumeroDeTelefono = new JLabel("Fecha de Instalacion");
		lblNumeroDeTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroDeTelefono.setBounds(447, 125, 186, 16);
		add(lblNumeroDeTelefono);

		marca_m = new JTextField();
		marca_m.setColumns(10);
		marca_m.setBounds(214, 123, 116, 22);
		add(marca_m);

		zonas = new JTextField();
		zonas.setBounds(214, 183, 116, 22);
		add(zonas);
		zonas.setColumns(10);

		f_bateria = new JTextField();
		f_bateria.setBounds(642, 238, 116, 22);
		add(f_bateria);
		f_bateria.setColumns(10);

		f_preventivo = new JTextField();
		f_preventivo.setBounds(642, 183, 116, 22);
		add(f_preventivo);
		f_preventivo.setColumns(10);

		JLabel lblEmpresaDeMonitoreo = new JLabel("Empresa de Monitoreo");
		lblEmpresaDeMonitoreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmpresaDeMonitoreo.setBounds(47, 240, 165, 16);
		add(lblEmpresaDeMonitoreo);

		monitoreo = new JTextField();
		monitoreo.setBounds(214, 238, 116, 22);
		add(monitoreo);
		monitoreo.setColumns(10);

		JLabel lblMarcamodeloBackup = new JLabel("Marca-Modelo Backup");
		lblMarcamodeloBackup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMarcamodeloBackup.setBounds(447, 74, 159, 16);
		add(lblMarcamodeloBackup);

		marca_backup = new JTextField();
		marca_backup.setBounds(642, 72, 116, 22);
		add(marca_backup);
		marca_backup.setColumns(10);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota.setBounds(43, 318, 56, 16);
		add(lblNota);

		nota = new JTextField();
		nota.setBounds(134, 316, 624, 22);
		add(nota);
		nota.setColumns(10);

		JLabel f_inst = new JLabel();
		f_inst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		f_inst.setBounds(646, 127, 112, 14);
		add(f_inst);

		JRadioButton radioButton_f_inst = new JRadioButton("");
		radioButton_f_inst.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f_instala.setText(vbBO.fechaActualstring());
			}
		});
		radioButton_f_inst.setBounds(833, 120, 127, 25);
		add(radioButton_f_inst);

		JRadioButton radioButton_f_prev = new JRadioButton("");
		radioButton_f_prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f_preventivo.setText(vbBO.fechaActualstring());

			}
		});
		radioButton_f_prev.setBounds(833, 176, 127, 25);
		add(radioButton_f_prev);

		JRadioButton radioButtonf_Bat = new JRadioButton("");
		radioButtonf_Bat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f_bateria.setText(vbBO.fechaActualstring());

			}
		});
		radioButtonf_Bat.setBounds(833, 231, 127, 25);
		add(radioButtonf_Bat);

		JButton btnHguardar = new JButton("Guardar");
		btnHguardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean verifica = false, verifica_fecha = false, modifica = false;
				aBO = new AlarmaBO();
				clBO = new ClienteBO();
				verifica_fecha = aBO.verificarFecha(f_bateria.getText(), true);
				verifica_fecha = aBO.verificarFecha(f_preventivo.getText(), verifica_fecha);
				if (verifica_fecha) {

					alarma = new Alarma(n_serie.getText(), marca_m.getText(), clBO.numeros_Correctos(zonas.getText()),
							marca_backup.getText(), monitoreo.getText(),
							vbBO.sumarMesesAFecha(aBO.fechaSQL(f_bateria.getText()),
									clBO.numeros_Correctos(meses.getText())),
							aBO.fechaSQL(f_preventivo.getText()), aBO.fechaSQL(f_instala.getText()), nota.getText(),
							cs.getDireccion());

					verifica = c.getaBO().verificacionAlarmar(alarma, "modificar", true, marco, c, logs);
					if (verifica) {
						modifica = c.getaBO().registrarAlarma(alarma, true, logs);
						if (modifica) {
							JOptionPane.showMessageDialog(null, "Se ha añadido la alarma correctamente");
							Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
							c.getmtBO().cambiar_panel(tab, marco);
						}
					} else
						JOptionPane.showMessageDialog(null, "Error, Verifique  los errores informados");
				} else
					JOptionPane.showMessageDialog(null, "El formato de fecha es incorrecto (AAAA-MM-DD)");
			}

		});
		btnHguardar.setBounds(567, 376, 97, 25);
		add(btnHguardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
				c.getmtBO().cambiar_panel(tab, marco);

			}
		});
		btnCancelar.setBounds(676, 376, 97, 25);
		add(btnCancelar);

		n_serie = new JTextField();
		n_serie.setBounds(214, 72, 116, 22);
		add(n_serie);
		n_serie.setColumns(10);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(342, 75, 56, 16);
		add(label);

		JLabel lblLosCampos = new JLabel("* Los campos que tienen \" * \" al final son obligatorios.");
		lblLosCampos.setForeground(Color.RED);
		lblLosCampos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLosCampos.setBounds(43, 347, 390, 20);
		add(lblLosCampos);

		f_instala = new JTextField();
		f_instala.setBounds(645, 123, 116, 22);
		add(f_instala);
		f_instala.setColumns(10);

		JLabel lblCantidadDeMeses = new JLabel("Cantidad de meses para proximo cambio de bateria");
		lblCantidadDeMeses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidadDeMeses.setBounds(342, 283, 380, 16);
		add(lblCantidadDeMeses);

		meses = new JTextField("18");
		meses.setBounds(719, 281, 35, 22);
		add(meses);
		meses.setColumns(10);

		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(770, 185, 56, 16);
		add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(769, 126, 56, 16);
		add(label_3);

		JLabel lblUtilizarFechaActual = new JLabel("Utilizar fecha actual");
		lblUtilizarFechaActual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUtilizarFechaActual.setBounds(787, 40, 163, 16);
		add(lblUtilizarFechaActual);

		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.BLUE);
		label_4.setBackground(Color.BLACK);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(770, 241, 56, 16);
		add(label_4);

		JLabel lblElFormato = new JLabel("* El formato de fecha es a\u00F1o-mes-dia . Ej 2000-01-01. ");
		lblElFormato.setForeground(Color.BLUE);
		lblElFormato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblElFormato.setBounds(40, 380, 393, 21);
		add(lblElFormato);

	}
}

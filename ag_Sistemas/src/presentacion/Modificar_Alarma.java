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
import javax.swing.JRadioButton;

public class Modificar_Alarma extends JPanel {

	private JTextField marca_m;
	private JTextField zonas;
	private JTextField f_bateria;
	private JTextField f_preventivo;
	private JTextField monitoreo;
	private JTextField marca_backup;
	private JTextField nota;
	private AlarmaBO aBO;
	private ClienteBO clBO;
	private VencimientoBateriaBO vbBO;
	private JTextField meses;

	public Modificar_Alarma(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(962, 550);

		vbBO = new VencimientoBateriaBO();

		JLabel lblNcuenta = new JLabel("N_Serie");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(43, 74, 141, 16);
		add(lblNcuenta);

		JLabel lblEditarCliente = new JLabel("Editar Alarma");
		lblEditarCliente.setBounds(401, 13, 159, 27);
		lblEditarCliente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lblEditarCliente);

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
		lblEmail.setBounds(448, 240, 129, 16);
		add(lblEmail);

		JLabel lblNuemeroCelular = new JLabel("Fecha de Preventivo");
		lblNuemeroCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNuemeroCelular.setBounds(448, 125, 145, 16);
		add(lblNuemeroCelular);

		JLabel lblNumeroDeTelefono = new JLabel("Fecha de Instalacion");
		lblNumeroDeTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroDeTelefono.setBounds(448, 185, 186, 16);
		add(lblNumeroDeTelefono);

		JLabel n_serie = new JLabel(a.getN_Serie());
		n_serie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		n_serie.setBounds(213, 75, 92, 14);
		add(n_serie);

		marca_m = new JTextField(a.getMarca_Modelo());
		marca_m.setColumns(10);
		marca_m.setBounds(214, 123, 116, 22);
		add(marca_m);

		zonas = new JTextField(String.valueOf(a.getCant_Zonas()));
		zonas.setBounds(214, 183, 116, 22);
		add(zonas);
		zonas.setColumns(10);

		f_bateria = new JTextField(String.valueOf(a.getFecha_Bateria()));
		f_bateria.setBounds(642, 238, 116, 22);
		add(f_bateria);
		f_bateria.setColumns(10);

		f_preventivo = new JTextField(String.valueOf(a.getFecha_Preventivo()));
		f_preventivo.setBounds(642, 123, 116, 22);
		add(f_preventivo);
		f_preventivo.setColumns(10);

		JLabel lblEmpresaDeMonitoreo = new JLabel("Empresa de Monitoreo");
		lblEmpresaDeMonitoreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmpresaDeMonitoreo.setBounds(47, 240, 165, 16);
		add(lblEmpresaDeMonitoreo);

		monitoreo = new JTextField(a.getEmpresa_Monitoreo());
		monitoreo.setBounds(214, 238, 116, 22);
		add(monitoreo);
		monitoreo.setColumns(10);

		JLabel lblMarcamodeloBackup = new JLabel("Marca-Modelo Backup");
		lblMarcamodeloBackup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMarcamodeloBackup.setBounds(448, 74, 159, 16);
		add(lblMarcamodeloBackup);

		marca_backup = new JTextField(a.getMarca_Modelo_backup());
		marca_backup.setBounds(642, 72, 116, 22);
		add(marca_backup);
		marca_backup.setColumns(10);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota.setBounds(43, 317, 56, 16);
		add(lblNota);

		nota = new JTextField(a.getNota());
		nota.setBounds(146, 315, 624, 22);
		add(nota);
		nota.setColumns(10);

		JLabel f_inst = new JLabel(String.valueOf(a.getFecha_Instalaciona()));
		f_inst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		f_inst.setBounds(646, 185, 112, 14);
		add(f_inst);

		JButton btnHguardar = new JButton("Guardar");
		btnHguardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean verifica = false, verifica_fecha = false, modifica = false;
				aBO = new AlarmaBO();
				clBO = new ClienteBO();
				verifica_fecha = aBO.verificarFecha(f_bateria.getText(), true);
				verifica_fecha = aBO.verificarFecha(f_preventivo.getText(), verifica_fecha);
				if (verifica_fecha) {

					a.setMarca_Modelo(marca_m.getText());
					a.setCant_Zonas(clBO.numeros_Correctos(zonas.getText()));
					a.setMarca_Modelo_backup(marca_backup.getText());
					a.setEmpresa_Monitoreo(monitoreo.getText());
					a.setFecha_Bateria(vbBO.sumarMesesAFecha(aBO.fechaSQL(f_bateria.getText()),
							clBO.numeros_Correctos(meses.getText())));
					a.setFecha_Preventivo(aBO.fechaSQL(f_preventivo.getText()));
					a.setNota(nota.getText());

					verifica = c.getaBO().verificacionAlarmar(a, "modificar", true, marco, c, logs);
					if (verifica) {
						modifica = c.getaBO().modificarAlarma(a, logs);
						if (modifica)
							JOptionPane.showMessageDialog(null, "Se ha modificado de manera exitosa");
						Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
						c.getmtBO().cambiar_panel(tab, marco);
					} else
						JOptionPane.showMessageDialog(null, "Error, Verifique  los errores informados");
				} else
					JOptionPane.showMessageDialog(null, "El formato de fecha es incorrecto (AAAA-MM-DD)");
			}

		});
		btnHguardar.setBounds(566, 344, 97, 25);
		add(btnHguardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
				c.getmtBO().cambiar_panel(tab, marco);

			}
		});
		btnCancelar.setBounds(684, 344, 97, 25);
		add(btnCancelar);

		JLabel label = new JLabel("Cantidad de meses para proximo cambio de bateria");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(344, 286, 380, 16);
		add(label);

		meses = new JTextField("18");
		meses.setBounds(721, 284, 37, 22);
		add(meses);
		meses.setColumns(10);

		JRadioButton radioButtonf_prev = new JRadioButton("");
		radioButtonf_prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f_preventivo.setText(vbBO.fechaActualstring());

			}
		});
		radioButtonf_prev.setBounds(809, 122, 127, 25);
		add(radioButtonf_prev);

		JLabel label_1 = new JLabel("Utilizar fecha actual");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(773, 39, 163, 16);
		add(label_1);

		JRadioButton radioButtonf_bateria = new JRadioButton("");
		radioButtonf_bateria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f_bateria.setText(vbBO.fechaActualstring());

			}
		});
		radioButtonf_bateria.setBounds(809, 237, 127, 25);
		add(radioButtonf_bateria);

	}
}

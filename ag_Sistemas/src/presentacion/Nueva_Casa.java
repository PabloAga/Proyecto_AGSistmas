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
import negocio.ClienteBO;
import java.awt.Color;

public class Nueva_Casa extends JPanel {

	private JTextField numero;
	private JTextField barrio;
	private JTextField localidad;
	private JTextField Provincia;
	private JTextField nota;
	private JTextField direccion;
	private Casa casa;
	private ClienteBO clBO;

	public Nueva_Casa(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(962, 451);

		JLabel lblNcuenta = new JLabel("Direccion");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(43, 74, 141, 16);
		add(lblNcuenta);

		JLabel lblEditarCliente = new JLabel("Añadir Casa");
		lblEditarCliente.setBounds(401, 13, 129, 27);
		lblEditarCliente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lblEditarCliente);

		JLabel lblNombre = new JLabel("Numero");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(40, 125, 81, 16);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Barrio");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(43, 185, 78, 16);
		add(lblApellido);

		JLabel localida = new JLabel("Localidad");
		localida.setFont(new Font("Tahoma", Font.PLAIN, 16));
		localida.setBounds(448, 99, 145, 16);
		add(localida);

		JLabel lblNumeroDeTelefono = new JLabel("Provincia");
		lblNumeroDeTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroDeTelefono.setBounds(448, 157, 186, 16);
		add(lblNumeroDeTelefono);

		direccion = new JTextField();
		direccion.setBounds(157, 68, 115, 22);
		add(direccion);
		direccion.setColumns(10);

		numero = new JTextField();
		numero.setColumns(10);
		numero.setBounds(156, 123, 116, 22);
		add(numero);

		barrio = new JTextField();
		barrio.setBounds(156, 183, 116, 22);
		add(barrio);
		barrio.setColumns(10);

		localidad = new JTextField();
		localidad.setBounds(630, 97, 116, 22);
		add(localidad);
		localidad.setColumns(10);

		Provincia = new JTextField();
		Provincia.setBounds(630, 155, 116, 22);
		add(Provincia);
		Provincia.setColumns(10);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota.setBounds(43, 249, 56, 16);
		add(lblNota);

		nota = new JTextField();
		nota.setBounds(157, 247, 735, 22);
		add(nota);
		nota.setColumns(10);

		JButton btnHguardar = new JButton("Guardar");
		btnHguardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean verifica = false, nuevo = false;
				clBO = new ClienteBO();
				casa = new Casa(direccion.getText(), clBO.numeros_Correctos(numero.getText()), barrio.getText(),
						localidad.getText(), Provincia.getText(), nota.getText(), ct.getN_Cuenta());

				verifica = c.getcsBO().verificacionCasa(casa, "modificar", true, marco, c, logs);
				if (verifica) {
					nuevo = c.getcsBO().registrarACasa(casa, true, logs);
					if (nuevo) {
						JOptionPane.showMessageDialog(null, "Se ha añadido la casa correctamente");
						Tablas tab = new Tablas(c, p, a, casa, ct, marco, logs);
						c.getmtBO().cambiar_panel(tab, marco);
					}
				} else
					JOptionPane.showMessageDialog(null, "Error, Verifique  los errores informados");
			}
		});
		btnHguardar.setBounds(630, 292, 97, 25);
		add(btnHguardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Tablas tab = new Tablas(c, p, a, casa, ct, marco, logs);
				c.getmtBO().cambiar_panel(tab, marco);

			}

		});
		btnCancelar.setBounds(795, 292, 97, 25);
		add(btnCancelar);

		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(284, 74, 56, 16);
		add(label);

		JLabel label_1 = new JLabel("Los campos que tienen \" * \" al final son obligatorios.");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(43, 296, 362, 16);
		add(label_1);

	}
}

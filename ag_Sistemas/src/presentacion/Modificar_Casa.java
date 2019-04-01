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

public class Modificar_Casa extends JPanel {

	private JTextField numero;
	private JTextField barrio;
	private JTextField localidad;
	private JTextField Provincia;
	private JTextField nota;
	private ClienteBO clBO;

	public Modificar_Casa(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(962, 451);

		JLabel lblNcuenta = new JLabel("Direccion");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(43, 74, 141, 16);
		add(lblNcuenta);

		JLabel lblEditarCliente = new JLabel("Editar Casa");
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

		JLabel direccion = new JLabel(cs.getDireccion());
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		direccion.setBounds(157, 76, 92, 14);
		add(direccion);

		numero = new JTextField(String.valueOf(cs.getNumero()));
		numero.setColumns(10);
		numero.setBounds(156, 123, 116, 22);
		add(numero);

		barrio = new JTextField(cs.getBarrio());
		barrio.setBounds(156, 183, 116, 22);
		add(barrio);
		barrio.setColumns(10);

		localidad = new JTextField(cs.getLocalidad());
		localidad.setBounds(630, 97, 116, 22);
		add(localidad);
		localidad.setColumns(10);

		Provincia = new JTextField(cs.getProvincia());
		Provincia.setBounds(630, 155, 116, 22);
		add(Provincia);
		Provincia.setColumns(10);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNota.setBounds(43, 249, 56, 16);
		add(lblNota);

		nota = new JTextField(cs.getNota());
		nota.setBounds(157, 247, 735, 22);
		add(nota);
		nota.setColumns(10);

		JButton btnHguardar = new JButton("Guardar");
		btnHguardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clBO = new ClienteBO();
				boolean verifica = false, modifica = false;
				cs.setNumero(clBO.numeros_Correctos(numero.getText()));
				cs.setBarrio(barrio.getText());
				cs.setLocalidad(localidad.getText());
				cs.setProvincia(Provincia.getText());
				cs.setNota(nota.getText());

				verifica = c.getcsBO().verificacionCasa(cs, "modificar", true, marco, c, logs);
				if (verifica) {
					modifica = c.getcsBO().modificarCasa(cs, logs);
					if (modifica)
						JOptionPane.showMessageDialog(null, "Se ha modificado de manera exitosa");
					Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
					c.getmtBO().cambiar_panel(tab, marco);
				} else
					JOptionPane.showMessageDialog(null, "Error, Verifique  los errores informados");
			}
		});
		btnHguardar.setBounds(630, 292, 97, 25);
		add(btnHguardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
				c.getmtBO().cambiar_panel(tab, marco);

			}

		});
		btnCancelar.setBounds(795, 292, 97, 25);
		add(btnCancelar);

	}
}

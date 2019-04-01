package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JTextField;
import app.Context;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import negocio.ClienteBO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Modificar_Cliente extends JPanel {
	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JTextField numero_celular;
	private JTextField numero_fijo;
	private JTextField c_celular;
	private ClienteBO clBO;
	private JTextField caract_fijo;

	public Modificar_Cliente(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(962, 450);

		JLabel lblNcuenta = new JLabel("N_cuenta");
		lblNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNcuenta.setBounds(43, 74, 141, 16);
		add(lblNcuenta);

		JLabel lblEditarCliente = new JLabel("Editar Cliente");
		lblEditarCliente.setBounds(401, 13, 129, 27);
		lblEditarCliente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lblEditarCliente);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(40, 125, 81, 16);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(43, 185, 78, 16);
		add(lblApellido);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(448, 74, 56, 16);
		add(lblEmail);

		JLabel lblNuemeroCelular = new JLabel("Nuemero celular");
		lblNuemeroCelular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNuemeroCelular.setBounds(444, 125, 145, 16);
		add(lblNuemeroCelular);

		JLabel lblNumeroDeTelefono = new JLabel("Numero de Telefono Fijo");
		lblNumeroDeTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroDeTelefono.setBounds(448, 185, 186, 16);
		add(lblNumeroDeTelefono);

		JLabel n_cuenta = new JLabel(ct.getN_Cuenta());
		n_cuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		n_cuenta.setBounds(157, 76, 92, 14);
		add(n_cuenta);

		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(725, 125, 56, 16);
		add(label);

		nombre = new JTextField(ct.getNombre());
		nombre.setColumns(10);
		nombre.setBounds(156, 123, 116, 22);
		add(nombre);

		apellido = new JTextField(ct.getApellido());
		apellido.setBounds(156, 183, 116, 22);
		add(apellido);
		apellido.setColumns(10);

		email = new JTextField(ct.getEmail());
		email.setBounds(642, 72, 216, 22);
		add(email);
		email.setColumns(10);

		numero_celular = new JTextField(String.valueOf(ct.getNumero_Celular()));
		numero_celular.setBounds(742, 123, 116, 22);
		add(numero_celular);
		numero_celular.setColumns(10);

		numero_fijo = new JTextField(String.valueOf(ct.getNumero_Fijo()));
		numero_fijo.setBounds(742, 183, 116, 22);
		add(numero_fijo);
		numero_fijo.setColumns(10);

		c_celular = new JTextField(String.valueOf(ct.getCaracteristica_Celular()));
		c_celular.setBounds(646, 123, 68, 22);
		add(c_celular);
		c_celular.setColumns(10);
		caract_fijo = new JTextField(String.valueOf(ct.getCaracteristica_Fijo()));
		caract_fijo.setColumns(10);
		caract_fijo.setBounds(646, 183, 68, 22);
		add(caract_fijo);

		JButton btnHguardar = new JButton("Guardar");
		btnHguardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clBO = new ClienteBO();
				boolean verifica = false, modifica = false;
				ct.setNombre(nombre.getText());
				ct.setApellido(apellido.getText());
				ct.setEmail(email.getText());
				ct.setCaracteristica_Celular(clBO.numeros_Correctos(c_celular.getText()));
				ct.setNumero_Celular(clBO.numeros_Correctos(numero_celular.getText()));
				ct.setCaracteristica_Fijo(clBO.numeros_Correctos(caract_fijo.getText()));
				ct.setNumero_Fijo(clBO.numeros_Correctos(numero_fijo.getText()));
				verifica = c.getclBO().verificacionCliente(ct, "modificar", marco, c, logs);
				if (verifica) {
					modifica = c.getclBO().modificarCliente(ct, logs);
					if (modifica)
						JOptionPane.showMessageDialog(null, "Se ha modificado de manera exitosa");
					Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
					c.getmtBO().cambiar_panel(tab, marco);
				} else
					JOptionPane.showMessageDialog(null, "Error, Verifique  los errores informados");
			}
		});
		btnHguardar.setBounds(630, 269, 97, 25);
		add(btnHguardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Tablas tab = new Tablas(c, p, a, cs, ct, marco, logs);
				c.getmtBO().cambiar_panel(tab, marco);

			}
		});
		btnCancelar.setBounds(798, 269, 97, 25);
		add(btnCancelar);

		JLabel label_1 = new JLabel("-");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(725, 186, 56, 16);
		add(label_1);

	}
}

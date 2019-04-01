package presentacion;

import javax.swing.JPanel;
import app.Context;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class MiPerfil extends JPanel {
	private JTextField nomtxt;
	private JTextField apetxt;
	private JTextField contxt;
	private JTextField mailtxt;

	public MiPerfil(Context c, Persona p, Alarma a, Casa cs, Cliente ct, JFrame marco, Logger logs) {
		setLayout(null);
		setSize(642, 350);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 70, 46, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(24, 126, 46, 14);
		add(lblApellido);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(231, 70, 46, 14);
		add(lblMail);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(231, 126, 86, 14);
		add(lblContrasea);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(24, 181, 56, 16);
		add(lblUsuario);

		mailtxt = new JTextField(p.getMail());
		mailtxt.setBounds(317, 66, 213, 22);
		add(mailtxt);
		mailtxt.setColumns(10);

		nomtxt = new JTextField(p.getNombre());
		nomtxt.setBounds(98, 67, 86, 20);
		add(nomtxt);
		nomtxt.setColumns(10);

		apetxt = new JTextField(p.getApellido());
		apetxt.setColumns(10);
		apetxt.setBounds(98, 123, 86, 20);
		add(apetxt);

		contxt = new JTextField(p.getContra());
		contxt.setColumns(10);
		contxt.setBounds(321, 123, 116, 20);
		add(contxt);

		JButton btnCancelar = new JButton("Volver");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu(c, p, a, cs, ct, marco, logs);
				c.getmtBO().cambiar_panel(menu, marco);
			}
		});
		btnCancelar.setBounds(500, 220, 89, 23);
		add(btnCancelar);

		JButton btnEditar = new JButton("Guardar Cambios");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setNombre(nomtxt.getText());
				p.setApellido(apetxt.getText());
				p.setContra(contxt.getText());
				p.setMail(mailtxt.getText());
				JOptionPane.showMessageDialog(null, c.getpBO().modificar(p, logs));
				Menu menu = new Menu(c, p, a, cs, ct, marco, logs);
				c.getmtBO().cambiar_panel(menu, marco);

			}
		});
		btnEditar.setBounds(299, 220, 138, 23);
		add(btnEditar);

		JLabel insusua = new JLabel(p.getUsuario());
		insusua.setBounds(92, 182, 92, 14);
		add(insusua);

		JLabel lblEditarPerfil = new JLabel("Editar Perfil");
		lblEditarPerfil.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEditarPerfil.setBounds(198, 13, 119, 20);
		add(lblEditarPerfil);

	}
}

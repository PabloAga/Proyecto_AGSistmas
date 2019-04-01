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

import com.jgoodies.forms.factories.DefaultComponentFactory;

import app.Context;
import modelo.Persona;

public class Registro_Usuario extends JPanel {
	private JTextField nombretxt;
	private JTextField apetxt;
	private JTextField mailtxt;
	private JTextField contxt;
	private JTextField usuariotxt;

	public Registro_Usuario(Context c, Persona p, JFrame marco, Logger logs) {
		setSize(485, 350);
		marco.setSize(getSize());
		setLayout(null);

		JLabel lblRgistrarme = DefaultComponentFactory.getInstance().createTitle("Registro Usuario");
		lblRgistrarme.setFont(new Font("Tarzan", Font.PLAIN, 22));
		lblRgistrarme.setBounds(155, 26, 177, 23);
		add(lblRgistrarme);

		JLabel label2 = new JLabel("Nombre");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label2.setBounds(30, 97, 68, 14);
		add(label2);

		nombretxt = new JTextField();
		nombretxt.setBounds(110, 94, 104, 20);
		add(nombretxt);
		nombretxt.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApellido.setBounds(30, 133, 68, 23);
		add(lblApellido);

		apetxt = new JTextField();
		apetxt.setColumns(10);
		apetxt.setBounds(110, 136, 104, 20);
		add(apetxt);

		JLabel lblNewLabel = new JLabel("Mail");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 180, 46, 14);
		add(lblNewLabel);

		mailtxt = new JTextField();
		mailtxt.setColumns(10);
		mailtxt.setBounds(110, 179, 240, 20);
		add(mailtxt);

		contxt = new JTextField();
		contxt.setBounds(344, 136, 101, 20);
		add(contxt);
		contxt.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(238, 98, 80, 16);
		add(lblUsuario);

		usuariotxt = new JTextField();
		usuariotxt.setBounds(344, 95, 101, 22);
		add(usuariotxt);
		usuariotxt.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContrasea.setBounds(239, 135, 93, 18);
		add(lblContrasea);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setNombre(nombretxt.getText());
				p.setApellido(apetxt.getText());
				p.setUsuario(usuariotxt.getText());
				p.setMail(mailtxt.getText());
				p.setContra(contxt.getText());
				JOptionPane.showMessageDialog(null, c.getpBO().insertar_persona(p, marco, c, logs));

			}
		});
		btnGuardar.setBounds(238, 247, 89, 23);
		add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginPanel lp = new LoginPanel(c, marco, logs);
				c.getpBO().cambiar_panel(lp, marco);
			}
		});
		btnCancelar.setBounds(356, 247, 89, 23);
		add(btnCancelar);

		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(450, 100, 56, 16);
		add(label);

		JLabel lblLosCampor = new JLabel("Los campor que tienen * son obligatorios");
		lblLosCampor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLosCampor.setBounds(30, 207, 367, 29);
		add(lblLosCampor);

		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(450, 138, 56, 16);
		add(label_1);

	}
}
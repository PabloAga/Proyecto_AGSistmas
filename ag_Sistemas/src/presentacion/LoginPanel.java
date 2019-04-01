package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import app.Context;
import modelo.Persona;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	private JTextField UsuariotextField;
	private JPasswordField passwordField;
	private Persona p;
	private Alarma a;

	private Casa cs;
	private Cliente cl;

	public LoginPanel(Context c, JFrame marco, Logger logs) {
		setSize(471, 272);
		marco.setSize(getSize());
		setLayout(null);

		logs.log(Level.INFO, "se creo de manera exitosa el panel del login");

		JLabel lblTiendaOnline = DefaultComponentFactory.getInstance().createTitle("Ingresar");
		lblTiendaOnline.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTiendaOnline.setBounds(157, 23, 109, 14);
		add(lblTiendaOnline);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(103, 51, 46, 14);
		add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(82, 96, 67, 14);
		add(lblContrasea);

		UsuariotextField = new JTextField("admin");
		UsuariotextField.setBounds(159, 48, 107, 20);
		add(UsuariotextField);
		UsuariotextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(159, 93, 107, 20);
		add(passwordField);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dialogo;
				logs.log(Level.INFO, "Apreto el boton Ingresar de la clase LoginPanel");
				p = new Persona(UsuariotextField.getText(), null, null, null, passwordField.getText());
				dialogo = c.getpBO().validarlogin(p, a, cs, cl, marco, c, logs);
				if (!dialogo.equals("Se ingreso correctamente"))
					JOptionPane.showMessageDialog(null, dialogo);
			}
		});
		btnIngresar.setBounds(39, 148, 89, 23);
		add(btnIngresar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(157, 148, 89, 23);
		add(btnSalir);

		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = new Persona(null, null, null, null, null);
				Registro_Usuario r = new Registro_Usuario(c, p, marco, logs);
				r.setBorder(new EmptyBorder(5, 5, 5, 5));
				r.setBounds(getBounds());
				marco.setContentPane(r);
			}
		});
		btnRegistrarme.setBounds(278, 148, 107, 23);
		add(btnRegistrarme);
	}
}

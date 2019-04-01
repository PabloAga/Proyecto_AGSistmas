package presentacion;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import app.Context;
import modelo.Casa;
import modelo.Alarma;
import modelo.Cliente;
import modelo.Persona;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarcoCte {
	private JFrame marco = new JFrame("AG_Sistemas");
	private JPanel contentPane;
	private Menu m;

	public MarcoCte(Context c, Persona p, Alarma a, Casa cs, Cliente ct, Logger logs) {

		inicializarMarco();
		centrar();
		logs.log(Level.INFO, "se creo de manera exitosa el marco definitivo");
		m = new Menu(c, p, a, cs, ct, marco, logs);
		m.setBorder(new EmptyBorder(5, 5, 5, 5));
		marco.setSize(m.getSize());
		marco.setContentPane(m);

	}

	public void inicializarMarco() {
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		marco.setContentPane(contentPane);
		contentPane.setLayout(null);
		marco.setVisible(true);
		marco.pack();
	}

	public void centrar() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		marco.setSize(width / 2, height / 2);
		marco.setLocationRelativeTo(null);
	}
}

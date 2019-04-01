package presentacion;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Context;
import modelo.Persona;


public class MarcoLogin {
	private JFrame marco = new JFrame("Login");
	private JPanel contentPane;
	private LoginPanel lp;
	
	
	public MarcoLogin(Context c,Logger logs) {
	
		inicializarMarco();
		centrar();
		logs.log(Level.INFO,"se creo el marco del login de forma exitosa");
		lp=new LoginPanel(c, marco,logs);
		lp.setBorder(new EmptyBorder(5, 5, 5, 5));
		marco.setSize(lp.getSize());
		marco.setContentPane(lp);
		
	}
	public void inicializarMarco() {
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		marco.setContentPane(contentPane);
		contentPane.setLayout(null);
		marco.setVisible(true);
	}
	public void centrar() {
		   Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		      int height = pantalla.height;
		      int width = pantalla.width;
		      marco.setSize(width/4, height/4);	
		      marco.setLocationRelativeTo(null);
	}
}

package negocio;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import app.Context;
import dao.AlarmaDao;
import dao.CasaDao;
import dao.ClienteDao;
import dao.PersonaDao;
import modelo.Alarma;
import modelo.Persona;

public class EnviarMailBO {
	private AlarmaDao aDao;
	private CasaDao csDao;
	private ClienteDao cDao;
	private PersonaDao pDao;
	private Alarma alarma;
	private static String remitente;

	public EnviarMailBO() {

	}

	public EnviarMailBO(AlarmaDao aDao, CasaDao csDao, ClienteDao cDao, PersonaDao pDao) {
		super();
		this.aDao = aDao;
		this.csDao = csDao;
		this.cDao = cDao;
		this.pDao = pDao;
	}

	public void confirmar(JTable table, Alarma[] array_a,Persona p, Context c, Logger logs) {
	alarma= new Alarma();
	boolean confirmacion=false;
		for (int i = 0; i < array_a.length; i++) {
			if ((Boolean) table.getValueAt(i, 9) != null) {
				alarma= c.getaBO().buscarAlarma(c, array_a[i].getN_Serie(), logs);
				alarma.setFecha_Bateria(c.getVbBO().sumarMesesAFecha(c.getVbBO().fechaActual(), 18));
				confirmacion=c.getaBO().modificarAlarma(alarma, logs);

			}
		}
	}
	public void enviar(JTable table, Alarma[] array_a,Persona p, Context c, Logger logs) {
		String cuerpo;
		String asunto="AG Sistemas-Recordatorio de vencimiento de bateria del sistema de alarma";
		for (int i = 0; i < array_a.length; i++) {
			if ((Boolean) table.getValueAt(i, 8) != null) {
				cuerpo = "Este mensaje es generdo automaticamente  para recordarle que la bateria de su sistema de alarma esta proxima a vencer ."
						+ "\nEspecificaciones de su sistema de alarma:" + "\n-Direccion: " + table.getValueAt(i, 3) + " "
						+ table.getValueAt(i, 4) + "\n-Marca y modelo de la alarma : " + table.getValueAt(i, 5)
						+ "\n-Bateria vence :"
						+ c.getVbBO().fecha_Formato_normal((java.sql.Date) table.getValueAt(i, 7))
							+"\nEste es un mensaje automatico, no admite respuesta.\n Por cualquier consulta comunicarse al numero telefonico: 3515208478";


				remitente = p.getMail();
				Properties props = System.getProperties();
				props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
				props.put("mail.smtp.user", remitente);
				props.put("mail.smtp.clave", "Ariel5599"); // La clave de la cuenta
				props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
				props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
				props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

				Session session = Session.getDefaultInstance(props);
				MimeMessage message = new MimeMessage(session);

				try {
					message.setFrom(new InternetAddress(remitente));
					message.addRecipients(Message.RecipientType.TO,(String)table.getValueAt(i, 2) ); // Se podrían
																										// añadir varios
																										// de la misma
																										// manera
					message.setSubject(asunto);
					message.setText(cuerpo);
					Transport transport = session.getTransport("smtp");
					transport.connect("smtp.gmail.com", remitente, "Ariel5599");
					transport.sendMessage(message, message.getAllRecipients());
					JOptionPane.showMessageDialog(null, "Se ha enviado el mail correctamente");
					transport.close();

				} catch (MessagingException me) {
					JOptionPane.showMessageDialog(null, "No se pudo nviar el mail");
					me.printStackTrace(); // Si se produce un error
				}

			}

		}
	}

}

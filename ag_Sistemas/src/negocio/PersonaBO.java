package negocio;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import app.Context;
import dao.PersonaDao;
import modelo.Alarma;
import modelo.Casa;
import modelo.Cliente;
import modelo.Persona;
import presentacion.LoginPanel;
import presentacion.MarcoCte;


public class PersonaBO {
	private PersonaDao pbd;

	public PersonaBO() {
	}

	public PersonaBO(PersonaDao pbd) {
		this.pbd = pbd;
	}

	public String validarlogin(Persona p, Alarma a, Casa cs, Cliente cl, JFrame marco, Context c, Logger logs) {
		String informe;
		if (pbd.exisUsuario(p, logs)) {
			informe = "Usuario no existente,ingrese correctamente el Usuario";
			logs.log(Level.INFO, informe);
			return informe;
		} else {
			if (pbd.coincidircontra(p)) {
				informe = "No coincide la contraseña para el usuario que ingreso";
				logs.log(Level.INFO, informe);
				return informe;
			} else {
				informe = "Se ingreso correctamente";
				logs.log(Level.INFO, informe);
				p = cargarPersona(p, logs);
				MarcoCte menu = new MarcoCte(c, p, a, cs, cl, logs); 																
				marco.dispose();
				return informe;
			}
		}
	}

	public String insertar_persona(Persona p, JFrame marco, Context c, Logger logs) {
		if (contiene_aroba(p))
			return "El campo del email no es una dirreccion de email, ya que falta el @";
		else {
			if (letras_Correctas(p.getContra()) && letras_Correctas(p.getUsuario()))
				return "Por favor, ingrese los campos correctamente";
			if (!pbd.exisUsuario(p, logs))
				return "Un usuario ya se registro con ese nombre,por favor ingrese otro usuario";
			else {
				if (!pbd.insertar_persona(p, logs)) {
					LoginPanel lp = new LoginPanel(c, marco, logs);
					cambiar_panel(lp, marco);
					return "se registro correctamente";
				} else
					return "no se pudo registrar correctamente";
			}

		}
	}

	public boolean contiene_aroba(Persona p) {
		for (int i = 0; i < p.getMail().length(); i++)
			if (p.getMail().charAt(i) == '@')
				return false;
		return true;
	}

	public boolean letras_Correctas(String palabra) {
		int cont = 0;
		if (!palabra.equals("")) {
			for (int i = 0; i < palabra.length(); i++)
				if (palabra.codePointAt(i) < 91 && palabra.codePointAt(i) > 63
						|| palabra.codePointAt(i) < 123 && palabra.codePointAt(i) > 94
						|| palabra.codePointAt(i) < 58 && palabra.codePointAt(i) > 44||palabra.codePointAt(i)==32)
					cont++;
				else
					return true;
			if (cont == palabra.length() )
				return false;
				else
					return true;
		}
		return false;

	}
	public boolean letras_Correctas_solo_Letra(String palabra) {
		int cont = 0;
		if (!palabra.equals("")) {
			for (int i = 0; i < palabra.length(); i++)
				if (palabra.codePointAt(i) < 91 && palabra.codePointAt(i) > 64
						|| palabra.codePointAt(i) < 123 && palabra.codePointAt(i) > 96||palabra.codePointAt(i)==32)
					cont++;
				else
					return true;
			if (cont == palabra.length() )
				return false;
				else
					return true;
		}
		return false;

	}

	public java.sql.Date fecha_sql(String f) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		java.sql.Date sqlStartDate = null;
		try {
			date = sdf1.parse(f);
			sqlStartDate = new java.sql.Date(date.getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlStartDate;

	}


	public Persona cargarPersona(Persona p, Logger logs) {
		return p = pbd.obtner_Persona(p, logs);
	}

	public String modificar(Persona p, Logger logs) {
		if (letras_Correctas(p.getContra()) || letras_Correctas(p.getUsuario())
				|| letras_Correctas(p.getNombre()) || letras_Correctas(p.getApellido())
				|| letras_Correctas(p.getMail()))
			return "Por favor, ingrese los campos correctamente";
		if (contiene_aroba(p))
			return "El campo del email no es una dirreccion de email, ya que falta el @";
		else {
			if (pbd.modificar(p, logs))
				return "se ha podido modificar correctamente ";
			else
				return "No se ha podido modificar correctamente ";

		}
	}

	public void cambiar_panel(JPanel lp, JFrame marco) {
		lp.setBorder(new EmptyBorder(5, 5, 5, 5));
		marco.getContentPane().removeAll();
		marco.getContentPane().repaint();
		marco.getContentPane().add(lp);
		marco.setSize(lp.getSize());
	}

}

package negocio;

import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import app.Context;
import dao.ClienteDao;
import modelo.Cliente;

public class ClienteBO {
	private PersonaBO pBO;
	private ClienteDao cDao;

	public ClienteBO() {

	}

	public ClienteBO(ClienteDao cDao) {
		this.cDao = cDao;
	}

	public Boolean verificacionCliente(Cliente cl, String h, JFrame marco, Context c, Logger logs) {
		pBO = new PersonaBO();
		if (pBO.letras_Correctas_solo_Letra(cl.getNombre()) || pBO.letras_Correctas_solo_Letra(cl.getApellido())
				|| pBO.letras_Correctas(cl.getN_Cuenta()) || pBO.letras_Correctas(cl.getEmail())) {
			JOptionPane.showMessageDialog(null,
					"Error, los datos de registrar cliente son invalidos .Ingrese los datos correctamente");
			return false;
		}
		if (h.contentEquals("nuevo"))
			if (!cDao.exisCliente(cl, logs)) {
				JOptionPane.showMessageDialog(null, "El numero de cuenta ya esta registrado ");
				return false;
			}

		if (contiene_aroba(cl)) {
			JOptionPane.showMessageDialog(null, "Error, el email ingresado es invalido");
			return false;
		}
		if (cl.getApellido().equals("") || cl.getN_Cuenta().equals("") || cl.getEmail().equals("")) {
			JOptionPane.showMessageDialog(null, "Error, hay datos vacios .Ingrese los datos correctamente");
			return false;
		} else
			return true;

	}

	public boolean registrarCliente(Cliente cl, Logger logs) {
		if (cDao.registrarCliente(cl, logs))
			return true;
		else {
			System.out.println();
			JOptionPane.showMessageDialog(null, "El numero de cuenta ya ah sido registrado");
		}
		return false;

	}

	public boolean modificarCliente(Cliente cl, Logger logs) {
		return cDao.modificar(cl, logs);
	}

	public boolean contiene_aroba(Cliente cl) {
		for (int i = 0; i < cl.getEmail().length(); i++)
			if (cl.getEmail().charAt(i) == '@')
				return false;
		return true;
	}

	public Cliente buscarCliente(Context c, String n_Cuenta, Logger logs) {
		try {
			return cDao.buscar_Cliente(c, n_Cuenta, logs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo buscar el cliente");
			e.printStackTrace();
		}
		return null;

	}

	public Integer numeros_Correctos(String entero) {
		int cont = 0;
		Integer numero;
		if (!entero.equals("")) {
			for (int i = 0; i < entero.length(); i++)
				if (entero.codePointAt(i) < 58 && entero.codePointAt(i) > 47)
					cont++;
				else {
					JOptionPane.showMessageDialog(null,
							"Error, Se ingreso un digito no correspondiente en los campos de numero");
					return 0;
				}

			if (cont == entero.length()) {
				try {
					numero = Integer.parseInt(entero);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Error, Se ingreso un digito no correspondiente en los campos de numero");
					return 0;
				}

				return numero;
			}
		}
		return 0;

	}

}

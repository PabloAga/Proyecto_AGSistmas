package negocio;

import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import app.Context;
import dao.CasaDao;
import modelo.Casa;

public class CasaBO {
	private PersonaBO pBO;
	private CasaDao cDao;

	public CasaBO() {
	}

	public CasaBO(CasaDao cDao) {
		this.cDao = cDao;
	}

	public boolean verificacionCasa(Casa cs, String h, Boolean cliente, JFrame marco, Context c, Logger logs) {

		pBO = new PersonaBO();
		if (pBO.letras_Correctas(cs.getDireccion()) || pBO.letras_Correctas(cs.getBarrio())
				|| pBO.letras_Correctas(cs.getLocalidad()) || pBO.letras_Correctas_solo_Letra(cs.getProvincia())
				|| pBO.letras_Correctas(cs.getNota())) {
			JOptionPane.showMessageDialog(null,
					"Error, hay datos invalidos para registrar casa .Ingrese los datos correctamente");
			return false;
		}
		if (h.contentEquals("nuevo"))
			if (!cDao.exisCasa(cs, logs)) {
				JOptionPane.showMessageDialog(null, "En esta direccion ya se registraron clientes");
				return false;
			}
		if (cs.getDireccion().equals("")) {
			JOptionPane.showMessageDialog(null, "Error, hay datos vacios .Ingrese los datos correctamente");
			return false;
		} else {
			if (cliente)
				return true;
			else
				return false;
		}

	}

	public boolean registrarACasa(Casa cs, Boolean cle, Logger logs) {
		if (cDao.registrarCasa(cs, logs) && cle)
			return true;
		else {
			if (!cle)
				return false;
			JOptionPane.showMessageDialog(null, "La direccion ya ah sido registrada");
		}
		return false;
	}

	public boolean modificarCasa(Casa cs, Logger logs) {
		return cDao.modificar_Casa(cs, logs);
	}

	public Casa buscarCasa(Context c, String Direccion, Logger logs) {
		try {
			return cDao.buscar_Casa(c, Direccion, logs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo buscar a la casa");
			e.printStackTrace();
		}
		return null;

	}
}

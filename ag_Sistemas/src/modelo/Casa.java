package modelo;

public class Casa {

	private String direccion;
	private int numero;
	private String barrio;
	private String localidad;
	private String provincia;
	private String nota;
	private String n_Cuenta;

	public Casa() {

	}

	public Casa(String direccion, int numero, String barrio, String localidad, String provincia, String nota,
			String n_Cuenta) {
		super();
		this.direccion = direccion;
		this.numero = numero;
		this.barrio = barrio;
		this.localidad = localidad;
		this.provincia = provincia;
		this.nota = nota;
		this.n_Cuenta = n_Cuenta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getN_Cuenta() {
		return n_Cuenta;
	}

	public void setN_Cuenta(String n_Cuenta) {
		this.n_Cuenta = n_Cuenta;
	}

}

package modelo;



public class Cliente {

	private String n_Cuenta;
	private String nombre;
	private String apellido;
	private String email;
	private int caracteristica_Celular;
	private int caracteristica_Fijo;
	private int numero_Celular;
	private int numero_Fijo;
	private String persona_Usuario;

	public Cliente() {
	}

	public Cliente(String n_Cuenta, String nombre, String apellido, String email, int caracteristica_Celular,
			int caracteristica_Fijo, int numero_Celular, int numero_Fijo, String persona_Usuario) {
		super();
		this.n_Cuenta = n_Cuenta;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.caracteristica_Celular = caracteristica_Celular;
		this.caracteristica_Fijo = caracteristica_Fijo;
		this.numero_Celular = numero_Celular;
		this.numero_Fijo = numero_Fijo;
		this.persona_Usuario = persona_Usuario;
	}

	public String getN_Cuenta() {
		return n_Cuenta;
	}

	public void setN_Cuenta(String n_Cuenta) {
		this.n_Cuenta = n_Cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCaracteristica_Celular() {
		return caracteristica_Celular;
	}

	public void setCaracteristica_Celular(int caracteristica_Celular) {
		this.caracteristica_Celular = caracteristica_Celular;
	}

	public int getCaracteristica_Fijo() {
		return caracteristica_Fijo;
	}

	public void setCaracteristica_Fijo(int caracteristica_Fijo) {
		this.caracteristica_Fijo = caracteristica_Fijo;
	}

	public int getNumero_Celular() {
		return numero_Celular;
	}

	public void setNumero_Celular(int numero_Celular) {
		this.numero_Celular = numero_Celular;
	}

	public int getNumero_Fijo() {
		return numero_Fijo;
	}

	public void setNumero_Fijo(int numero_Fijo) {
		this.numero_Fijo = numero_Fijo;
	}

	public String getPersona_Usuario() {
		return persona_Usuario;
	}

	public void setPersona_Usuario(String persona_Usuario) {
		this.persona_Usuario = persona_Usuario;
	}

}
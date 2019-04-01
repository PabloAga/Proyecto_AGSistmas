package modelo;


public class Persona {

	private String usuario;
	private String mail;
	private String nombre;
	private String apellido;
	private String contra;

	public Persona(String usuario, String mail, String nombre, String apellido, String contra) {
		super();
		this.usuario = usuario;
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contra = contra;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

}
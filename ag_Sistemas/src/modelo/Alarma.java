package modelo;

import java.sql.Date;

public class Alarma {

	private String n_Serie;
	private String marca_Modelo;
	private int cant_Zonas;
	private String marca_Modelo_backup;
	private String empresa_Monitoreo;
	private Date fecha_Bateria;
	private Date fecha_Preventivo;
	private Date fecha_Instalaciona;
	private String nota;
	private String casa_Direccion;

	public Alarma() {

	}

	public Alarma(String n_Serie, String marca_Modelo, int cant_Zonas, String marca_Modelo_backup,
			String empresa_Monitoreo, Date fecha_Bateria, Date fecha_Preventivo, Date fecha_Instalaciona, String nota,
			String casa_Direccion) {
		super();
		this.n_Serie = n_Serie;
		this.marca_Modelo = marca_Modelo;
		this.cant_Zonas = cant_Zonas;
		this.marca_Modelo_backup = marca_Modelo_backup;
		this.empresa_Monitoreo = empresa_Monitoreo;
		this.fecha_Bateria = fecha_Bateria;
		this.fecha_Preventivo = fecha_Preventivo;
		this.fecha_Instalaciona = fecha_Instalaciona;
		this.nota = nota;
		this.casa_Direccion = casa_Direccion;
	}

	public String getN_Serie() {
		return n_Serie;
	}

	public void setN_Serie(String n_Serie) {
		this.n_Serie = n_Serie;
	}

	public String getMarca_Modelo() {
		return marca_Modelo;
	}

	public void setMarca_Modelo(String marca_Modelo) {
		this.marca_Modelo = marca_Modelo;
	}

	public int getCant_Zonas() {
		return cant_Zonas;
	}

	public void setCant_Zonas(int cant_Zonas) {
		this.cant_Zonas = cant_Zonas;
	}

	public String getMarca_Modelo_backup() {
		return marca_Modelo_backup;
	}

	public void setMarca_Modelo_backup(String marca_Modelo_backup) {
		this.marca_Modelo_backup = marca_Modelo_backup;
	}

	public String getEmpresa_Monitoreo() {
		return empresa_Monitoreo;
	}

	public void setEmpresa_Monitoreo(String empresa_Monitoreo) {
		this.empresa_Monitoreo = empresa_Monitoreo;
	}

	public Date getFecha_Bateria() {
		return fecha_Bateria;
	}

	public void setFecha_Bateria(Date fecha_Bateria) {
		this.fecha_Bateria = fecha_Bateria;
	}

	public Date getFecha_Preventivo() {
		return fecha_Preventivo;
	}

	public void setFecha_Preventivo(Date fecha_Preventivo) {
		this.fecha_Preventivo = fecha_Preventivo;
	}

	public Date getFecha_Instalaciona() {
		return fecha_Instalaciona;
	}

	public void setFecha_Instalaciona(Date fecha_Instalaciona) {
		this.fecha_Instalaciona = fecha_Instalaciona;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getCasa_Direccion() {
		return casa_Direccion;
	}

	public void setCasa_Direccion(String casa_Direccion) {
		this.casa_Direccion = casa_Direccion;
	}

}

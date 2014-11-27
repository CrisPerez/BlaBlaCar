package beans;

import java.util.Date;

import model.BlaBlaCar;

public class BeanRegistrar {
	private String nombre;
	private String apellidos;
	private String profesion;
	private String correo;
	private String usuario;
	private String clave;
	private String clave2;
	private Date fechaNacimiento;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave2() {
		return clave2;
	}

	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String validacion() {
		java.sql.Date fecha = new java.sql.Date(fechaNacimiento.getTime());
		if (clave.equals(clave2)) {
			if (BlaBlaCar.getInstance().registroUsuario(usuario, clave,
					profesion, correo, nombre, apellidos, fecha))
				return "si";
		}
		return "no";
	}
}
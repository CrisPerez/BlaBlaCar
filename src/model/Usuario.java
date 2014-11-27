package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String usuario;
	private String clave;
	private String email;
	private String profesion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	private String nombre;
	private String apellidos;
	@OneToOne(mappedBy = "propietario")
	private Coche coche;
	@OneToMany(cascade = { CascadeType.REMOVE },mappedBy = "usuario")
	private List<Reserva> reservas;
	@OneToMany(cascade = { CascadeType.REMOVE },mappedBy = "emisor")
	private List<Valoracion> valoracionesEmitidas;
	@OneToMany(cascade = { CascadeType.REMOVE },mappedBy = "receptor")
	private List<Valoracion> valoracionesRecibidas;

	public Usuario(){
		super();
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Valoracion> getValoracionesEmitidas() {
		return valoracionesEmitidas;
	}

	public void setValoracionesEmitidas(
			List<Valoracion> valoracionesEmitidas) {
		this.valoracionesEmitidas = valoracionesEmitidas;
	}

	public List<Valoracion> getValoracionesRecibidas() {
		return valoracionesRecibidas;
	}

	public void setValoracionesRecibidas(
			List<Valoracion> valoracionesRecibidas) {
		this.valoracionesRecibidas = valoracionesRecibidas;
	}

}
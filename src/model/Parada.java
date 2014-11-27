package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Parada implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String ciudad;
	@Embedded
	private Direccion direccion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	public Parada(){
		super();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}

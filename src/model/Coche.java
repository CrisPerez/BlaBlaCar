package model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Coche {

	@Id
	private String matricula;
	private String modelo;
	private int ano;
	private int confort;
	@OneToOne
	private Usuario propietario;
	@OneToMany (cascade = { CascadeType.REMOVE }, mappedBy = "coche")
	private List<Viaje> viajes;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getConfort() {
		return confort;
	}

	public void setConfort(int confort) {
		this.confort = confort;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
}

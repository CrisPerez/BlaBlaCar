package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name="findViajeById", query ="SELECT v FROM Viaje v WHERE v.id = :viaje")
public class Viaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int plazas;
	private double precio;
	@ManyToOne
	private Coche coche;
	@CollectionTable(name = "NotasViaje")
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> notas;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Parada paradaOrigen;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Parada paradaDestino;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "viaje")
	@OrderBy("estado ASC")
	private List<Reserva> reservas;

	public Viaje() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public List<String> getNotas() {
		return notas;
	}

	public void setNotas(List<String> notas) {
		this.notas = notas;
	}

	public Parada getParadaOrigen() {
		return paradaOrigen;
	}

	public void setParadaOrigen(Parada paradaOrigen) {
		this.paradaOrigen = paradaOrigen;
	}

	public Parada getParadaDestino() {
		return paradaDestino;
	}

	public void setParadaDestino(Parada paradaDestino) {
		this.paradaDestino = paradaDestino;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}

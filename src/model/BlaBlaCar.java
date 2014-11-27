package model;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;

import dao.*;

public class BlaBlaCar {

	private DAOFactoria factoria;
	private static BlaBlaCar controlador;
	private int tecnologiaDAO;

	private BlaBlaCar() {
		try {
			// Configurar la instancia de DAOFactoria

			// JDBC
			// factoria = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC);

			// JPA
			tecnologiaDAO = DAOFactoria.JPA;
			factoria = DAOFactoria.getDAOFactoria(tecnologiaDAO);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static BlaBlaCar getInstance() {
		if (controlador == null)
			controlador = new BlaBlaCar();
		return controlador;
	}

	public DAOFactoria getFactoria() {
		return factoria;
	}

	public void setTecnologiaDAO(int tecnologiaDAO) {
		this.tecnologiaDAO = tecnologiaDAO;
	}

	public boolean login(String usuario, String clave) {
		try {
			UsuarioDAO usuDAO = factoria.getUsuarioDAO();
			Usuario usu = usuDAO.findUsuarioByUsuario(usuario);
			return (usu.getClave().equals(clave));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}

	////////// CREATES //////////
	
	public boolean registroUsuario(String usuario, String clave,
			String profesion, String mail, String nombre, String apellidos,
			Date fechaNacimiento) {
		try {
			UsuarioDAO usuDAO = factoria.getUsuarioDAO();
			usuDAO.createUsuario(usuario, clave, profesion, mail, nombre,
					apellidos, fechaNacimiento);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean registrarCoche(String usuario, String matricula,
			String modelo, int ano, int confort) {
		try {
			CocheDAO cocheDAO = factoria.getCocheDAO();
			cocheDAO.createCoche(matricula, modelo, ano, confort, usuario);
			return true;

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int registrarViaje(int plazas, double precio, String matricula) {
		try {
			ViajeDAO viajeDAO = factoria.getViajeDAO();
			Viaje viaje = viajeDAO.createViaje(plazas, precio, matricula);

			return viaje.getId();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int registrarParadaOrigen(int viaje, String ciudad, String calle,
			int CP, Date fecha) {
		try {
			ParadaDAO paradaDAO = factoria.getParadaDAO();
			Parada parada = paradaDAO.createParadaOrigen(viaje, ciudad, calle,
					CP, fecha);

			return parada.getId();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int registrarParadaDestino(int viaje, String ciudad, String calle,
			int CP, Date fecha) {
		try {
			ParadaDAO paradaDAO = factoria.getParadaDAO();
			Parada parada = paradaDAO.createParadaDestino(viaje, ciudad, calle,
					CP, fecha);

			return parada.getId();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int reservarViaje(String usuario, int viaje, String comentario) {
		try {
			ReservaDAO reservaDAO = factoria.getReservaDAO();
			Reserva reserva = reservaDAO.createReserva(usuario, viaje,
					comentario);

			return reserva.getId();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int hacerValoracion(String emisor, String receptor, int idReserva, int puntuacion, String comentario){
		try{
			ValoracionDAO valoracionDAO = factoria.getValoracionDAO();
			Valoracion v = valoracionDAO.createValoracion(comentario, puntuacion, idReserva, emisor, receptor);
			
			return v.getId();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	////////// BUSQUEDAS //////////
	
	public Usuario buscarUsuario(String usu) {
		try {
			UsuarioDAO usuDAO = factoria.getUsuarioDAO();
			Usuario usuario = usuDAO.findUsuarioByUsuario(usu);
			return usuario;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Coche buscarCoche(String matricula) {
		try {
			CocheDAO cDAO = factoria.getCocheDAO();
			Coche coche = cDAO.findCocheByMatricula(matricula);
			return coche;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Viaje buscarViaje(int idViaje) {
		try {
			ViajeDAO vDAO = factoria.getViajeDAO();
			Viaje viaje = vDAO.findViajeById(idViaje);
			return viaje;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Valoracion buscarValoracion (int idValoracion){
		try {
			ValoracionDAO vDAO = factoria.getValoracionDAO();
			Valoracion valoracion = vDAO.findValoracionById(idValoracion);
			return valoracion;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Reserva buscarReserva (int idReserva){
		try{
			ReservaDAO reservaDAO = factoria.getReservaDAO();
			Reserva reserva = reservaDAO.findReservaById(idReserva);
			return reserva;
		}catch (DAOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	////////// UPDATES //////////
	
	public void aceptarReserva(int idReserva){
		try{
			ReservaDAO reservaDAO = factoria.getReservaDAO();
			Reserva reserva = reservaDAO.findReservaById(idReserva);
			reserva.setEstado(EstadoReserva.ACEPTADA);
			reservaDAO.update(reserva);
		}catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void rechazarReserva(int idReserva){
		try{
			ReservaDAO reservaDAO = factoria.getReservaDAO();
			Reserva reserva = reservaDAO.findReservaById(idReserva);
			reserva.setEstado(EstadoReserva.RECHAZADA);
			reservaDAO.update(reserva);
		}catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	
	////////// LISTADOS //////////

	public Collection<Usuario> listarUsuarios() {
		try {
			UsuarioDAO usuDAO = factoria.getUsuarioDAO();
			Collection<Usuario> usuarios = usuDAO.findAll();
			return usuarios;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<Viaje> listarViajes() {
		try {
			ViajeDAO viaDAO = factoria.getViajeDAO();
			Collection<Viaje> viajes = viaDAO.findAll();
			return viajes;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<Reserva> listarReservaByViaje(int idViaje){
		try{
			ViajeDAO viajeDAO = factoria.getViajeDAO();
			Viaje v = viajeDAO.findViajeById(idViaje);
			Collection <Reserva> reservas = v.getReservas();
			
			return reservas;
		} catch(DAOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Reserva> listarReservaByUsuario(String usuario){
		try{
			UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
			Usuario u = usuarioDAO.findUsuarioByUsuario(usuario);
			Collection <Reserva> reservas = u.getReservas();
			
			return reservas;
		} catch(DAOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	///////// OTROS //////////
	
	public Collection<String> listarCiudades (){
		Collection<String> ciudades = new LinkedList<String>();
		
		for(Ciudades c: Ciudades.values()){
			ciudades.add(c.name());
		}
		
		return ciudades;
	}
	
}

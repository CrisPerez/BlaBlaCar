package dao;

import java.util.Collection;

import model.Viaje;

public interface ViajeDAO {

	public Viaje createViaje(int plazas, double precio, String matricula) throws DAOException;
	
	public Viaje findViajeById(int id) throws DAOException;
	
	public Collection<Viaje> findAll() throws DAOException;
}

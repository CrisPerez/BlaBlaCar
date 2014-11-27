package dao;

import java.sql.Date;

import model.Parada;

public interface ParadaDAO {
	
	public Parada createParadaOrigen(int viaje, String ciudad, String calle, int CP, Date fecha) throws DAOException;
	
	public Parada createParadaDestino(int viaje, String ciudad, String calle, int CP, Date fecha) throws DAOException;
	
	public Parada findParadaById (int id) throws DAOException;
	
}

package dao;

import model.Reserva;

public interface ReservaDAO {

	public Reserva createReserva(String usuario, int viaje, String comentario) throws DAOException;
	
	public Reserva findReservaById(int idReserva) throws DAOException;
	
	public void update(Reserva r) throws DAOException;
}

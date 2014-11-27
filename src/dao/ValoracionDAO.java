package dao;

import model.Valoracion;

public interface ValoracionDAO {
	public Valoracion createValoracion(String comentario, int puntuacion, int idReserva, String emisor, String receptor) throws DAOException;

	public Valoracion findValoracionById(int idValoracion) throws DAOException;

}

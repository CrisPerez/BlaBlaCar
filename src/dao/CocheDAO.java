package dao;

import model.Coche;

//Define los metodos de acceso a los datos para un cliente
public interface CocheDAO {
	public Coche createCoche(String matricula, String modelo, int ano,
			int confort, String propietario) throws DAOException;

	// Busca un Usuario por "usuario" (clave primaria). Si no lo encuentra
	// devuelve "null"
	public Coche findCocheByMatricula(String matricula) throws DAOException;

	// Actualiza un usuario
	public void update(Coche c) throws DAOException;
}
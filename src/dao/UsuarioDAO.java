package dao;

import java.sql.Date;

import model.Usuario;

//Define los metodos de acceso a los datos para un cliente
public interface UsuarioDAO {
	public Usuario createUsuario(String usuario, String clave,
			String profesion, String mail, String nombre, String apellidos,
			Date fechaNacimiento) throws DAOException;

	// Busca un Usuario por "usuario" (clave primaria). Si no lo encuentra
	// devuelve "null"
	public Usuario findUsuarioByUsuario(String usuario) throws DAOException;

	// Obtiene todos los usuarios
	public java.util.Collection<Usuario> findAll() throws DAOException;

	// Actualiza un usuario
	public void update(Usuario c) throws DAOException;
}
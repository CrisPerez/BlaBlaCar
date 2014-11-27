package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.Usuario;
import model.Coche;

public class JDBCUsuarioDAO implements UsuarioDAO {

	private DataSource ds;

	public JDBCUsuarioDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Usuario createUsuario(String usuario, String clave,
			String profesion, String mail, String nombre, String apellidos,
			Date fechaNacimiento) throws DAOException {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT into USUARIO(usuario,clave,fechanacimiento,profesion,nombre,apellidos,email) "
							+ "values (?,?,?,?,?,?,?)");
			stmt.setString(1, usuario);
			stmt.setString(2, clave);
			stmt.setDate(3, fechaNacimiento);
			stmt.setString(4, profesion);
			stmt.setString(5, nombre);
			stmt.setString(6, apellidos);
			stmt.setString(7, mail);

			stmt.executeUpdate();

			stmt.close();
			con.close();

			Usuario c = new Usuario();
			c.setUsuario(usuario);
			c.setClave(clave);
			c.setFechaNacimiento(fechaNacimiento);
			c.setProfesion(profesion);
			c.setNombre(nombre);
			c.setApellidos(apellidos);
			c.setCoche(null);
			return c;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Usuario findUsuarioByUsuario(String usuario) throws DAOException {
		Connection con = null;
		try {
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * from Usuario WHERE usuario = '"
							+ usuario + "'");
			if (rs.next()) {
				Usuario c = new Usuario();
				c.setUsuario(rs.getString("usuario"));
				c.setClave(rs.getString("clave"));
				c.setProfesion(rs.getString("profesion"));
				c.setEmail(rs.getString("email"));
				c.setNombre(rs.getString("nombre"));
				c.setApellidos(rs.getString("apellidos"));
				c.setFechaNacimiento(rs.getDate("fechanacimiento"));
				String matricula = rs.getString("coche");
				
				if (matricula != null && matricula.equals("")) {
					Statement stmCo = con.createStatement();
					ResultSet rsCo = stmCo
							.executeQuery("SELECT * from Coche WHERE matricula = '"
									+ matricula + "'");
					if (rsCo.next()) {
						Coche co = new Coche();
						co.setMatricula(matricula);
						co.setModelo(rsCo.getString("modelo"));
						co.setAno(rsCo.getInt("ano"));
						co.setConfort(rsCo.getInt("confort"));

						co.setPropietario(c);
						c.setCoche(co);
					}
					rsCo.close();
					stmCo.close();
				}
				rs.close();
				stmt.close();
				con.close();
				return c;
			} else {
				rs.close();
				stmt.close();
				con.close();
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Collection<Usuario> findAll() throws DAOException {
		Connection con = null;
		Collection<Usuario> usuarios = new LinkedList<Usuario>();
		try {
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * from Usuario ORDER BY usuario");
			while (rs.next()) {
				Usuario c = new Usuario();
				c.setUsuario(rs.getString("usuario"));
				c.setClave(rs.getString("clave"));
				c.setProfesion(rs.getString("profesion"));
				c.setEmail(rs.getString("email"));
				c.setNombre(rs.getString("nombre"));
				c.setApellidos(rs.getString("apellidos"));
				c.setFechaNacimiento(rs.getDate("fechanacimiento"));
				String matricula = rs.getString("coche");
				
				if (matricula != null && matricula.equals("")) {
					Statement stmCo = con.createStatement();
					ResultSet rsCo = stmCo
							.executeQuery("SELECT * from Coche WHERE matricula = '"
									+ matricula + "'");
					if (rsCo.next()) {
						Coche co = new Coche();
						co.setMatricula(matricula);
						co.setModelo(rsCo.getString("modelo"));
						co.setAno(rsCo.getInt("ano"));
						co.setConfort(rsCo.getInt("confort"));

						co.setPropietario(c);
						c.setCoche(co);
					}
					rsCo.close();
					stmCo.close();
				}
				usuarios.add(c);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return usuarios;
	}

	@Override
	public void update(Usuario c) throws DAOException {
		// TODO Auto-generated method stub

	}
}

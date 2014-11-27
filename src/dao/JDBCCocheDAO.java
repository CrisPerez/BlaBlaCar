package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import model.Coche;
import model.Direccion;
import model.Parada;
import model.Usuario;

public class JDBCCocheDAO implements CocheDAO {

	private DataSource ds;

	public JDBCCocheDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Coche createCoche(String matricula, String modelo, int ano,
			int confort, String propietario) throws DAOException {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT into COCHE (matricula,modelo,ano,confort,propietario) "
							+ "values (?,?,?,?,?)");
			stmt.setString(1, matricula);
			stmt.setString(2, modelo);
			stmt.setInt(3, ano);
			stmt.setInt(4, confort);
			stmt.setString(5, propietario);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			
			Coche c = new Coche();
			c.setMatricula(matricula);
			c.setModelo(modelo);
			c.setAno(ano);
			c.setConfort(confort);
			if (propietario != null && !propietario.equals("")) {
				Statement stm = con.createStatement();
				stm.executeUpdate("UPDATE USUARIO SET coche='" + matricula
						+ "' WHERE usuario='" + propietario + "'");
				stm.close();
			}
			UsuarioDAO usuDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC)
					.getUsuarioDAO();
			Usuario usuario = usuDAO.findUsuarioByUsuario(propietario);
			c.setPropietario(usuario);
			
			return c;
		} catch (SQLException e) {

			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Coche findCocheByMatricula(String matricula) throws DAOException {
		Connection con = null;
		try {
			con = ds.getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM COCHE WHERE id = '" + matricula
							+ "'");
			if (rs.next()) {
				Coche c = new Coche();
				c.setMatricula(matricula);
				c.setModelo(rs.getString("modelo"));
				c.setAno(rs.getInt("ano"));
				c.setConfort(rs.getInt("confort"));
				UsuarioDAO usuDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC)
						.getUsuarioDAO();
				Usuario usuario = usuDAO.findUsuarioByUsuario(rs.getString("propietario"));
				c.setPropietario(usuario);
					
				return c;
			}

			rs.close();
			stmt.close();
			con.close();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Coche c) throws DAOException {
		// TODO Auto-generated method stub

	}

}

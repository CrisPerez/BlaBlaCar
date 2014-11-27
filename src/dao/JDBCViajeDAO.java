package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.sql.DataSource;

import model.*;

public class JDBCViajeDAO implements ViajeDAO{
	
	private DataSource ds;
	
	public JDBCViajeDAO (DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Viaje createViaje(int plazas, double precio, String matricula) throws DAOException {
		// TODO comprobar matricula
		Connection con = null;
		try {
			Viaje v = new Viaje();
			
			con = ds.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT into VIAJE (id,plaza,precio) "
							+ "values (?,?,?)");
			stmt.setInt(1, v.getId());
			stmt.setInt(2, plazas);
			stmt.setDouble(3, precio);

			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
			v.setPlazas(plazas);
			v.setPrecio(precio);

			return v;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Viaje findViajeById(int id) throws DAOException {
		Connection con = null;
		try {
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * from VIAJE WHERE id = '"
							+ id + "'");
			if (rs.next()) {
				Viaje v = new Viaje();
				v.setId(rs.getInt("id"));
				v.setPlazas(rs.getInt("plazas"));
				v.setPrecio(rs.getDouble("precio"));
				
				int paradaorigen = rs.getInt("paradaorigen");
				int paradadestino = rs.getInt("paradadestino");
				
				ParadaDAO paradaDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC).getParadaDAO();
				Parada p = paradaDAO.findParadaById(paradaorigen);
				v.setParadaOrigen(p);
				
				p = paradaDAO.findParadaById(paradadestino);
				v.setParadaDestino(p);

				rs.close();
				stmt.close();
				con.close();
				return v;
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
	public Collection<Viaje> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}

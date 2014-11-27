package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.sql.DataSource;

import model.Direccion;
import model.Parada;

public class JDBCParadaDAO implements ParadaDAO {

	private DataSource ds;

	public JDBCParadaDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Parada createParadaOrigen(int viaje, String ciudad,
			String calle, int CP, Date fecha) throws DAOException {
		Connection con = null;
		try {
			Parada p = new Parada();

			con = ds.getConnection();

			PreparedStatement stmt = con
					.prepareStatement("INSERT into PARADA (id,ciudad,calle,CP,fecha) "
							+ "values (?,?,?,?,?)");
			stmt.setInt(1, p.getId());
			stmt.setString(2, ciudad);
			stmt.setString(3, calle);
			stmt.setInt(4, CP);
			stmt.setDate(5, fecha);

			stmt.executeUpdate();

			stmt.close();
			con.close();

			p.setCiudad(ciudad);
			Direccion dir = new Direccion();
			dir.setCalle(calle);
			dir.setCP(CP);
			p.setDireccion(dir);
			p.setFecha(fecha);

			Statement stm = con.createStatement();
			stm.executeUpdate("UPDATE VIAJE SET paradaorigen='" + p.getId()
					+ "' WHERE id='" + viaje + "'");

			stm.close();

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Parada createParadaDestino(int viaje, String ciudad,
			String calle, int CP, Date fecha) throws DAOException {
		Connection con = null;
		try {
			Parada p = new Parada();

			con = ds.getConnection();

			PreparedStatement stmt = con
					.prepareStatement("INSERT into PARADA (id,ciudad,calle,CP,fecha) "
							+ "values (?,?,?,?,?)");
			stmt.setInt(1, p.getId());
			stmt.setString(2, ciudad);
			stmt.setString(3, calle);
			stmt.setInt(4, CP);
			stmt.setDate(5, fecha);

			stmt.executeUpdate();

			stmt.close();
			con.close();

			p.setCiudad(ciudad);
			Direccion dir = new Direccion();
			dir.setCalle(calle);
			dir.setCP(CP);
			p.setDireccion(dir);
			p.setFecha(fecha);

			Statement stm = con.createStatement();
			stm.executeUpdate("UPDATE VIAJE SET paradadestino='" + p.getId()
					+ "' WHERE id='" + viaje + "'");
			stm.close();

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Parada findParadaById(int id) throws DAOException {
		Connection con = null;
		try {
			con = ds.getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM PARADA WHERE id = '" + id
							+ "'");
			if (rs.next()) {
				Parada p = new Parada();
				p.setId(rs.getInt("id"));
				p.setCiudad(rs.getString("ciudad"));
				
				Direccion d = new Direccion();
				d.setCalle(rs.getString("calle"));
				d.setCP(rs.getInt("CP"));
		
				p.setDireccion(d);
				p.setFecha(rs.getDate("fecha"));

				rs.close();
				stmt.close();
				con.close();
				return p;

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
}

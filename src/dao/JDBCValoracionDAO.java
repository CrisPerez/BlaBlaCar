package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Reserva;
import model.Usuario;
import model.Valoracion;
import model.Viaje;

public class JDBCValoracionDAO implements ValoracionDAO {
	
	DataSource ds;

	public JDBCValoracionDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Valoracion createValoracion(String comentario, int puntuacion,
			int idReserva, String emisor, String receptor) throws DAOException {
		Connection con = null;
		try {
			Valoracion v = new Valoracion();
			
			con = ds.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT into VALORACION (id,comentario,puntuacion,reserva,emisor,receptor) "
							+ "values (?,?,?,?,?,?)");
			
			stmt.setInt(1, v.getId());
			stmt.setString(2, comentario);
			stmt.setInt(3, puntuacion);
			stmt.setInt(4, idReserva);
			stmt.setString(5, emisor);
			stmt.setString(5, receptor);
			
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
			UsuarioDAO usuDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC).getUsuarioDAO();
			Usuario uEmisor = usuDAO.findUsuarioByUsuario(emisor);

			Usuario uReceptor = usuDAO.findUsuarioByUsuario(emisor);
			
			ReservaDAO reserva = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC).getReservaDAO();
			Reserva r = reserva.findReservaById(idReserva);
			
			v.setComentario(comentario);
			v.setEmisor(uEmisor);
			v.setReceptor(uReceptor);
			v.setPuntuacion(puntuacion);
			v.setReserva(r);
			
			return v;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Valoracion findValoracionById(int idValoracion) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}

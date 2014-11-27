package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.*;

public class JDBCReservaDAO implements ReservaDAO {
	
	private DataSource ds; 
	
	public JDBCReservaDAO (DataSource ds){
		this.ds = ds;
	}
	
	public Reserva createReserva(String usuario, int viaje, String comentario) throws DAOException{
		Connection con = null;
		try {
			Reserva r = new Reserva();
			
			con = ds.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT into RESERVA (id,comentario,usuario,viaje,estado) "
							+ "values (?,?,?,?,?)");
			
			stmt.setInt(1, r.getId());
			stmt.setString(2, comentario);
			stmt.setString(3, usuario);
			stmt.setInt(4, viaje);
			stmt.setInt(5, r.getEstado().ordinal());
			
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
			UsuarioDAO usuDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC).getUsuarioDAO();
			Usuario u = usuDAO.findUsuarioByUsuario(usuario);
			
			ViajeDAO viajeDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JDBC).getViajeDAO();
			Viaje v = viajeDAO.findViajeById(viaje);
			
			r.setComentario(comentario);
			r.setUsuario(u);
			r.setViaje(v);
			
			return r;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Reserva findReservaById(int idReserva) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Reserva r) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}

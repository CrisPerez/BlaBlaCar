package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.*;

public class JPAReservaDAO implements ReservaDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.emName);
	private EntityManager em = emf.createEntityManager();

	@Override
	public Reserva createReserva(String usuario, int viaje, String comentario)
			throws DAOException {
		em.getTransaction().begin();
		
		Reserva reserva = new Reserva();
		reserva.setComentario(comentario);
		
		Usuario u = em.find(Usuario.class, usuario);
		u.getReservas().add(reserva);
		reserva.setUsuario(u);
		
		Viaje v = em.find(Viaje.class, viaje);
		v.getReservas().add(reserva);
		reserva.setViaje(v);
		
		reserva.setValoraciones(new ArrayList<Valoracion>());
		
		em.persist(reserva);
		em.getTransaction().commit();
		em.close();

		return reserva;		
	}

	@Override
	public Reserva findReservaById(int idReserva) throws DAOException {
		em.getTransaction().begin();
		
		Reserva reserva = em.find(Reserva.class, idReserva);

		em.close();
		
		return reserva;
	}

	@Override
	public void update(Reserva r) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}

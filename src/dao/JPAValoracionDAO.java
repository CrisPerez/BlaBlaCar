package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.*;

public class JPAValoracionDAO implements ValoracionDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.emName);
	private EntityManager em = emf.createEntityManager();

	@Override
	public Valoracion createValoracion(String comentario, int puntuacion,
			int idReserva, String emisor, String receptor) throws DAOException {
		em.getTransaction().begin();
		
		Valoracion valoracion = new Valoracion();
		valoracion.setComentario(comentario);
		valoracion.setPuntuacion(puntuacion);
		
		Usuario uEmisor = em.find(Usuario.class, emisor);
		uEmisor.getValoracionesEmitidas().add(valoracion);
		valoracion.setEmisor(uEmisor);
		
		Usuario uReceptor = em.find(Usuario.class, receptor);
		uReceptor.getValoracionesRecibidas().add(valoracion);
		valoracion.setReceptor(uReceptor);
		
		Reserva reserva = new Reserva();
		reserva.getValoraciones().add(valoracion);
		valoracion.setReserva(reserva);
		
		em.persist(valoracion);
		em.getTransaction().commit();
		em.close();

		return valoracion;
	}

	@Override
	public Valoracion findValoracionById(int idValoracion) throws DAOException {
		em.getTransaction().begin();
		Valoracion v = em.find(Valoracion.class, idValoracion);
		
		em.close();
		return v;
	}

}

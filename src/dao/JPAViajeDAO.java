package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class JPAViajeDAO implements ViajeDAO {
	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(DAOFactoria.emName);
	private EntityManager em = emf.createEntityManager();

	@Override
	public Viaje createViaje(int plazas, double precio, String matricula)
			throws DAOException {
		em.getTransaction().begin();

		Viaje viaje = new Viaje();
		viaje.setPlazas(plazas);
		viaje.setPrecio(precio);

		viaje.setNotas(new ArrayList<String>());
		viaje.setReservas(new ArrayList<Reserva>());

		Coche coche = em.find(Coche.class, matricula);
		coche.getViajes().add(viaje);
		viaje.setCoche(coche);

		em.persist(viaje);
		em.getTransaction().commit();
		em.close();

		return viaje;
	}

	@Override
	public Viaje findViajeById(int id) throws DAOException {
		em.getTransaction().begin();

		Viaje viaje = em.find(Viaje.class, id);
		
		em.close();

		return viaje;
	}

	public Collection<Viaje> findAll() throws DAOException {
		Query query = em.createQuery("SELECT v FROM Viaje v");
		List<Viaje> viajes = query.getResultList();

		em.close();
		return viajes;
	}

}

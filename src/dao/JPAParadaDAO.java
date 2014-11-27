package dao;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.*;

public class JPAParadaDAO implements ParadaDAO{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.emName);
	private EntityManager em = emf.createEntityManager();

	@Override
	public Parada createParadaOrigen(int viaje, String ciudad, String calle, int CP,
			Date fecha) throws DAOException {
		em.getTransaction().begin();
		
		Parada parada = new Parada();
		parada.setCiudad(ciudad);
		parada.setFecha(fecha);
		
		Direccion dir = new Direccion();
		dir.setCalle(calle);
		dir.setCP(CP);
		
		parada.setDireccion(dir);
		Viaje v = em.find(Viaje.class, viaje);
		v.setParadaOrigen(parada);
		
		em.persist(parada);
		em.getTransaction().commit();
		em.close();

		return parada;
	}

	@Override
	public Parada createParadaDestino(int viaje, String ciudad, String calle, int CP,
			Date fecha) throws DAOException {
		em.getTransaction().begin();
		
		Parada parada = new Parada();
		parada.setCiudad(ciudad);
		parada.setFecha(fecha);
		
		Direccion dir = new Direccion();
		dir.setCalle(calle);
		dir.setCP(CP);
		
		parada.setDireccion(dir);
		Viaje v = em.find(Viaje.class, viaje);
		v.setParadaDestino(parada);
		
		em.persist(parada);
		em.getTransaction().commit();
		em.close();

		return parada;
	}
	
	@Override
	public Parada findParadaById(int id) throws DAOException {
		em.getTransaction().begin();
		
		Parada parada = em.find(Parada.class, id);
		
		em.close();
		
		return parada;
	}

}

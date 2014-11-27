package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.*;

public class JPACocheDAO implements CocheDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.emName);
	private EntityManager em = emf.createEntityManager();

	@Override
	public Coche createCoche(String matricula, String modelo, int ano,
			int confort, String propietario) throws DAOException {
		em.getTransaction().begin();
		
		Coche coche = new Coche();
		coche.setAno(ano);
		coche.setConfort(confort);
		coche.setMatricula(matricula);
		coche.setModelo(modelo);
		coche.setViajes(new ArrayList<Viaje>());
		
		Usuario usuario = em.find(Usuario.class, propietario);
		usuario.setCoche(coche);
		coche.setPropietario(usuario);
		
		em.persist(coche);
		em.getTransaction().commit();
		em.close();
		
		return coche;
	}

	@Override
	public Coche findCocheByMatricula(String matricula) throws DAOException {
		em.getTransaction().begin();
		
		Coche coche = em.find(Coche.class, matricula);

		em.close();
		
		return coche;
	}

	@Override
	public void update(Coche c) throws DAOException {
		em.getTransaction().begin();
		
		Coche coche = new Coche();
		coche.setAno(c.getAno());
		coche.setConfort(c.getConfort());
		coche.setMatricula(c.getMatricula());
		coche.setModelo(c.getModelo());
		coche.setViajes(c.getViajes());
		
		Usuario usuario = em.find(Usuario.class, c.getPropietario().getUsuario());
		coche.setPropietario(usuario);
		
		em.persist(coche);
		em.getTransaction().commit();
		em.close();
		
	}

}

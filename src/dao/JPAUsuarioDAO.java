package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class JPAUsuarioDAO implements UsuarioDAO {
	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory(DAOFactoria.emName);
	private EntityManager em = emf.createEntityManager();

	@Override
	public Usuario createUsuario(String usuario, String clave,
			String profesion, String email, String nombre, String apellidos,
			Date fechaNacimiento) throws DAOException {

		em.getTransaction().begin();

		Usuario usu = new Usuario();
		usu.setUsuario(usuario);
		usu.setClave(clave);
		usu.setProfesion(profesion);
		usu.setEmail(email);
		usu.setNombre(nombre);
		usu.setApellidos(apellidos);
		usu.setFechaNacimiento(fechaNacimiento);
		usu.setReservas(new ArrayList<Reserva>());
		usu.setValoracionesEmitidas(new ArrayList<Valoracion>());
		usu.setValoracionesRecibidas(new ArrayList<Valoracion>());

		em.persist(usu);
		em.getTransaction().commit();
		em.close();

		Usuario u = new Usuario();
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setProfesion(profesion);
		u.setNombre(nombre);
		u.setEmail(email);
		u.setApellidos(apellidos);
		u.setFechaNacimiento(fechaNacimiento);
		u.setReservas(new ArrayList<Reserva>());
		u.setValoracionesEmitidas(new ArrayList<Valoracion>());
		u.setValoracionesRecibidas(new ArrayList<Valoracion>());

		return u;
	}

	@Override
	public Usuario findUsuarioByUsuario(String usuario) throws DAOException {
		em.getTransaction().begin();

		Usuario usu = em.find(Usuario.class, usuario);

		if (usu.getCoche() != null) {
			Coche coche = em.find(Coche.class, usu.getCoche().getMatricula());

			usu.setCoche(coche);
		}

		em.close();

		return usu;
	}

	@Override
	public Collection findAll() throws DAOException {
		Query query = em.createQuery("SELECT u FROM Usuario u");
		
		List<Usuario> resultado = new LinkedList<Usuario>();
		List<Usuario> usuarios = query.getResultList();
		
		for (Usuario usuJPA : usuarios) {
			Usuario usu = new Usuario();
			usu.setUsuario(usuJPA.getUsuario());
			usu.setClave(usuJPA.getClave());
			usu.setNombre(usuJPA.getNombre());
			usu.setApellidos(usuJPA.getApellidos());
			usu.setEmail(usuJPA.getEmail());
			usu.setProfesion(usuJPA.getProfesion());
			usu.setFechaNacimiento(usuJPA.getFechaNacimiento());
			resultado.add(usu);
		}
		em.close();
		return resultado;
	}

	@Override
	public void update(Usuario c) throws DAOException {
		em.getTransaction().begin();

		Usuario u = em.find(Usuario.class, c.getUsuario());
		u.setClave(c.getClave());
		u.setFechaNacimiento(c.getFechaNacimiento());
		u.setProfesion(u.getProfesion());
		u.setEmail(c.getEmail());
		u.setNombre(c.getNombre());
		u.setApellidos(c.getApellidos());
		u.setReservas(c.getReservas());
		u.setValoracionesEmitidas(c.getValoracionesEmitidas());
		u.setValoracionesRecibidas(c.getValoracionesRecibidas());

		em.persist(u);
		em.getTransaction().commit();
		em.close();
	}
}

package pruebas;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.BlaBlaCar;
import model.*;

import org.junit.Test;

public class TestJPA {

	@Test
	public void testRegistroViaje() {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha = null;
		
		try{
			fecha = formatoDelTexto.parse("01/01/2014");
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
		int idViaje = BlaBlaCar.getInstance().registrarViaje(3, 125.0,"2323 AAA");
		BlaBlaCar.getInstance().registrarParadaOrigen(idViaje, "Murcia", "C/ Mayor, 25", 30001, sqlDate);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BlaBlaCar");
		EntityManager em = emf.createEntityManager();
		Viaje viajeJPA = em.find(Viaje.class, idViaje);
		
		assertNotNull(viajeJPA);
	}

}

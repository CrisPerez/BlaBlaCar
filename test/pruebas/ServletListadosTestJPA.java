package pruebas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class ServletListadosTest
 */
public class ServletListadosTestJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListadosTestJPA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AADD-MySQL");
		EntityManager em = emf.createEntityManager();
		
		out.println("<head></head><body>");
		
		Query q = em.createQuery("SELECT v FROM Viaje v");
		List<Viaje> viajes = q.getResultList();
		out.println("Viajes<br>");
		
		for (Viaje viaje: viajes){
			out.println("-> " + viaje.getId() + " : " + viaje.getPlazas() + " : " + viaje.getPrecio() + "<br>");
			Parada parada = viaje.getParadaOrigen();
			out.println("\t--> "+ parada.getId() + " : " + parada.getCiudad() + "<br>");			
		}
		out.println("<br>");
		
		int ultimoIdviaje = -1;
		out.println("Viajes con consulta nativa: <br>");
		Query q1 = em.createNamedQuery("SELECT * FROM ViajeCoche", Viaje.class);
		viajes = q1.getResultList();
		
		for(Viaje viaje: viajes){
			out.println("-> " + viaje.getId() + " : "+ viaje.getPlazas() + " : " + viaje.getPrecio()+"<br>");
		}
		out.println("<br>");
		
		out.println("Viajes con consulta nombrada: <br>");
		Query q2 = em.createNamedQuery("findViajeById").setParameter("viaje", ultimoIdviaje);
		viajes = q2.getResultList();
		
		for (Viaje viaje: viajes){
			out.println("-> "+ viaje.getId() + " : "+ viaje.getPlazas() + " : " + viaje.getPrecio() + "<br>");
		}
		out.println("<br>");
		out.println("</body>");
	}

}

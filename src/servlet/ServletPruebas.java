package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactoria;
import model.BlaBlaCar;
import model.Usuario;
import model.Util;
import model.Viaje;

/**
 * Servlet implementation class ServletPruebas
 */
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		BlaBlaCar b = BlaBlaCar.getInstance();

		out.println("<head></head><body>");
		//################## JPA ##################//
		// Registro de usuarios
		out.println("<h3>Registro de usuarios</h3>");
		if (b.registroUsuario("usuario1", "usuario1", "prof1", "1@1.1", "Nombre1", "Apellidos1", Util.strToDate("01/01/2001"))){
			out.println("Se ha registrado: usuario1<br>");
		}
		if (b.registroUsuario("usuario2", "usuario2", "prof2", "2@2.2", "Nombre2", "Apellidos2", Util.strToDate("02/02/2002"))){
			out.println("Se ha registrado: usuario2<br>");
		}
		if (b.registroUsuario("usuario3", "usuario3", "prof3", "3@3.3", "Nombre3", "Apellidos3", Util.strToDate("03/03/2003"))){
			out.println("Se ha registrado: usuario3<br");
		}
		if (b.registroUsuario("usuario4", "usuario4", "prof4", "4@4.4", "Nombre4", "Apellidos4", Util.strToDate("04/04/2004"))){
			out.println("Se ha registrado: usuario4<br>");
		}
		else out.println("Usuario4 no registrado<br>");
		
		// Registrar coche
		out.println("<br><h3>Registrar Coche:</h3>");
		if (b.registrarCoche("usuario1", "1111aaa", "model1", 2001, 1)){
			out.println("Añadido coche1<br>");
		}
		if (b.registrarCoche("usuario2", "2222bbb", "model2", 2002, 2)){
			out.println("Añadido coche2");
		}
		
		// Login usuario1
		out.println("<br><h3>Login usuario1</h3>");
		if(b.login("usuario1", "usuario1")){
			out.println("Usuario1 se ha logueado:");
			Usuario u = b.buscarUsuario("usuario1");
			out.println("<br>   Usuario: " + u.getUsuario());
			out.println("<br>   Nombre: " + u.getNombre());
			out.println("<br>   Apellidos: " + u.getApellidos());
			out.println("<br>   Fecha nac: " + Util.dateToStr(u.getFechaNacimiento()));	
			out.println("<br>   Profesion: " + u.getProfesion());
			out.println("<br>   Email: " + u.getEmail());
			out.println("<br>   Coche: " + u.getCoche().getMatricula());
		}
		
		//Registrar viaje
		out.println("<br><h3>Registrar Viaje:</h3>");
		int idViaje = b.registrarViaje(5,18.5,"1111aaa");
		Viaje viaje = b.buscarViaje(idViaje);
		int idViaje2 = b.registrarViaje(4,8.5,"2222bbb");
		Viaje viaje2 = b.buscarViaje(idViaje2);
		out.println("Se ha creado un viaje con id: " + idViaje + " y matricula "+ viaje.getCoche().getMatricula() +"<br>");
		out.println("Se ha creado un viaje con id: " + idViaje2 + " y matricula " + viaje2.getCoche().getMatricula());

		//Registrar paradas
		out.println("<br><h3>Registrar Paradas:</h3>");
		out.println("Se ha creado la parada de origen con id: " + b.registrarParadaOrigen(idViaje, "Murcia", "C/Gran via, 2", 30001, Util.strToDate("31/12/2014")) + " en el viaje: " + idViaje+"<br>");
		out.println("Se ha creado la parada de destino con id: " + b.registrarParadaDestino(idViaje, "Madrid", "C/Gran via, 20", 28003, Util.strToDate("31/12/2014")) + " en el viaje: " + idViaje+"<br>");
		
		
		//################## JDBC ##################//
		b.setTecnologiaDAO(DAOFactoria.JDBC);
		//Crear otra base de datos con otro nombre para las pruebas con JDBC (aaddJDBC)
//		// Registro de usuarios
//				out.println("<h3>Registro de usuarios</h3>");
//				if (b.registroUsuario("usuario1", "usuario1", "prof1", "1@1.1", "Nombre1", "Apellidos1", Util.strToDate("01/01/2001"))){
//					out.println("Se ha registrado: usuario1<br>");
//				}
//				if (b.registroUsuario("usuario2", "usuario2", "prof2", "2@2.2", "Nombre2", "Apellidos2", Util.strToDate("02/02/2002"))){
//					out.println("Se ha registrado: usuario2<br>");
//				}
//				if (b.registroUsuario("usuario3", "usuario3", "prof3", "3@3.3", "Nombre3", "Apellidos3", Util.strToDate("03/03/2003"))){
//					out.println("Se ha registrado: usuario3<br");
//				}
//				if (b.registroUsuario("usuario4", "usuario4", "prof4", "4@4.4", "Nombre4", "Apellidos4", Util.strToDate("04/04/2004"))){
//					out.println("Se ha registrado: usuario4<br>");
//				}
//				else out.println("Usuario4 no registrado<br>");
//				
//				// Registrar coche
//				out.println("<br><h3>Registrar Coche:</h3>");
//				if (b.registrarCoche("usuario1", "1111aaa", "model1", 2001, 1)){
//					out.println("Añadido coche1<br>");
//				}
//				if (b.registrarCoche("usuario2", "2222bbb", "model2", 2002, 2)){
//					out.println("Añadido coche2");
//				}
//				
//				// Login usuario1
//				out.println("<br><h3>Login usuario1</h3>");
//				if(b.login("usuario1", "usuario1")){
//					out.println("Usuario1 se ha logueado:");
//					Usuario u = b.buscarUsuario("usuario1");
//					out.println("<br>   Usuario: " + u.getUsuario());
//					out.println("<br>   Nombre: " + u.getNombre());
//					out.println("<br>   Apellidos: " + u.getApellidos());
//					out.println("<br>   Fecha nac: " + Util.dateToStr(u.getFechaNacimiento()));	
//					out.println("<br>   Profesion: " + u.getProfesion());
//					out.println("<br>   Email: " + u.getEmail());
//					out.println("<br>   Coche: " + u.getCoche().getMatricula());
//				}
//				
//				//Registrar viaje
//				out.println("<br><h3>Registrar Viaje:</h3>");
//				int idViaje3 = b.registrarViaje(5,18.5,"1111aaa");
//				Viaje viaje3 = b.buscarViaje(idViaje);
//				int idViaje4 = b.registrarViaje(4,8.5,"2222bbb");
//				Viaje viaje4 = b.buscarViaje(idViaje2);
//				out.println("Se ha creado un viaje con id: " + idViaje3 + " y matricula "+ viaje3.getCoche().getMatricula() +"<br>");
//				out.println("Se ha creado un viaje con id: " + idViaje4 + " y matricula " + viaje4.getCoche().getMatricula());
//
//				//Registrar paradas
//				out.println("<br><h3>Registrar Paradas:</h3>");
//				out.println("Se ha creado la parada de origen con id: " + b.registrarParadaOrigen(idViaje, "Murcia", "C/Gran via, 2", 30001, Util.strToDate("31/12/2014")) + " en el viaje: " + idViaje+"<br>");
//				out.println("Se ha creado la parada de destino con id: " + b.registrarParadaDestino(idViaje, "Madrid", "C/Gran via, 20", 28003, Util.strToDate("31/12/2014")) + " en el viaje: " + idViaje+"<br>");
//				
				
		out.println("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}

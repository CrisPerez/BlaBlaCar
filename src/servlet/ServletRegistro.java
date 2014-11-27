package servlet;

import model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRegistro
 */
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRegistro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String profesion = request.getParameter("profesion");
		String email = request.getParameter("email");
		String fechaNacimiento = request.getParameter("fechanacimiento");
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha = null;
		
		try {
			fecha = formatoDelTexto.parse(fechaNacimiento);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		java.sql.Date sql = new java.sql.Date(fecha.getTime());
		
		
		String matricula = request.getParameter("matricula");
		String modelo = request.getParameter("modelo");
		
		int ano = Integer.parseInt(request.getParameter("ano"));
		int confort = Integer.parseInt(request.getParameter("confort"));
		boolean exito = BlaBlaCar.getInstance().registroUsuario(usuario, clave, profesion,
				email, nombre, apellidos, sql);
		
		if (exito) {
			exito = BlaBlaCar.getInstance().registrarCoche(usuario, matricula, modelo, ano,
					confort);
		}
		if (exito)
			response.sendRedirect("index.html");
		else
			response.sendRedirect("error.html");
	}

}

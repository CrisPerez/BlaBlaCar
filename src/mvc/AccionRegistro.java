package mvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BlaBlaCar;

public class AccionRegistro implements Accion {
	@Override
	public String ejecutar(HttpServletRequest peticion,
			HttpServletResponse respuesta, ServletContext aplicacion) {
		String usuario = peticion.getParameter("usuario");
		String clave = peticion.getParameter("clave");
		String nombre = peticion.getParameter("nombre");
		String apellidos = peticion.getParameter("apellidos");
		String profesion = peticion.getParameter("profesion");
		String email = peticion.getParameter("email");
		String fechaNacimiento = peticion.getParameter("fechanacimiento");
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = formatoDelTexto.parse(fechaNacimiento);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		java.sql.Date sql = new java.sql.Date(fecha.getTime());
		String matricula = peticion.getParameter("matricula");
		String modelo = peticion.getParameter("modelo");
		int ano = Integer.parseInt(peticion.getParameter("ano"));
		int confort = Integer.parseInt(peticion.getParameter("confort"));
		boolean exito = BlaBlaCar.getInstance().registroUsuario(usuario, clave,
				profesion, email, nombre, apellidos, sql);
		if (exito) {
			exito = BlaBlaCar.getInstance().registrarCoche(usuario, matricula,
					modelo, ano, confort);
		}
		if (exito)
			return "index.html";
		else
			return "error.html";
	}
}
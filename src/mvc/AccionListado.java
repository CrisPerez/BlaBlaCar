package mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BlaBlaCar;
import model.Usuario;

public class AccionListado implements Accion {
	@Override
	public String ejecutar(HttpServletRequest peticion,
			HttpServletResponse respuesta, ServletContext aplicacion) {
		HttpSession sesion = peticion.getSession();
		sesion.setAttribute("usuarios", BlaBlaCar.getInstance()
				.listarUsuarios());
		return "listadoUsuarios.jsp";
	}
}
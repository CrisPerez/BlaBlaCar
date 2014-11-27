package mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BlaBlaCar;

public class AccionLogin implements Accion {
	@Override
	public String ejecutar(HttpServletRequest peticion,
			HttpServletResponse respuesta, ServletContext aplicacion) {
		String usu = peticion.getParameter("usuario");
		String clave = peticion.getParameter("clave");
		if (BlaBlaCar.getInstance().login(usu, clave)) {
			HttpSession sesion = peticion.getSession();
			sesion.setAttribute("usuario", BlaBlaCar.getInstance()
					.buscarUsuario(usu));
			return "index.html";
		}
		return "error.html";
	}
}
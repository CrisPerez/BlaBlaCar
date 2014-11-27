package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.BlaBlaCar;

@ManagedBean(name = "beanLogin2")
@SessionScoped
public class BeanLogin2 {
	private String usuario;
	private String clave;

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}

	// Tenemos que devolver el nombre del fichero facelet
	// La vista que se debe visualizar
	public String validacion() {
		try {
			if (BlaBlaCar.getInstance().login(usuario, clave)) {
				return "faceletsWelcome";
			} else {
				setClave(new String());
				return "faceletsLogin";
			}
		} catch (Exception e) {
			setClave(new String());
			return "faceletsLogin";
		}
	}
}
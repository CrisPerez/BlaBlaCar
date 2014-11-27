package beans;

import model.BlaBlaCar;

public class BeanLogin {
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

	public String validacion() {
		if (BlaBlaCar.getInstance().login(usuario, clave)) {
			return "si";
		} else {
			setUsuario(new String());
			setClave(new String());
			return "no";
		}
	}
}
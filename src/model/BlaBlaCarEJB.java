package model;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BlaBlaCarEJB implements BlaBlaCarRemote {

	private BlaBlaCarRemote blablacar;
	private static BlaBlaCarEJB controlador;

	private BlaBlaCarEJB() {
		try {
			blablacar = (BlaBlaCarRemote) new InitialContext()
					.lookup("java:global/BlaBlaCarEJB3/BlaBlaCarRemoto");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static BlaBlaCarEJB getInstancia(){
		if (controlador == null) controlador = new BlaBlaCarEJB();
		return controlador;
	}

	public String login(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
}

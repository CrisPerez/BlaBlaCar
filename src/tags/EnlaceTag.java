package tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.*;

public class EnlaceTag extends TagSupport {
	// Atributos de la etiqueta
	private String url;
	private String texto;

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public int doEndTag() throws JspTagException {
		try {
			HttpServletResponse respuesta = (HttpServletResponse) pageContext
					.getResponse();
			pageContext.getOut().write(
					"<a href=\"" + respuesta.encodeURL(url) + "\">" + texto
							+ "</a>");
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return EVAL_PAGE;
	}
}
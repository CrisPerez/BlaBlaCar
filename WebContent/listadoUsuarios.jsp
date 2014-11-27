<%@ taglib uri="/ad" prefix="ad" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="section">
		<table border="1">
			<tr>
				<td width="20%">
					<p align="center">
						<b>Usuario</b>
				</td>
				<td width="50%">
					<p align="center">
						<b>Nombre</b>
				</td>
				<td width="30%">
					<p align="center">
						<b>Correo</b>
				</td>
			</tr>
			<ad:listaUsuarios usuarios="${usuarios}">
				<tr>
					<td with="20%" align="center"><%=usuario%></td>
					<td with="50%" align="center"><%=nombre%></td>
					<td with="30%" align="center"><%=email%></td>
				</tr>
			</ad:listaUsuarios>
		</table>
		
		<BR>
		El identificador de session generado es:
		JSSIONID: ${cookie['JSESSIONID']['value']}
		<BR>
		HOST: ${header['host']}
		<BR>
		
		<ad:enlace texto="Inicio" URL="index.html"></ad:enlace>
		
		<b> Numero de usuarios
		${fn:length(sessionScope['usuarios'])} </b>
		<ad:listaUsuarios usuarios="${usuarios}%>"/>
		
		<BR>
		HOST: ${header['host']}
		<BR>
		MIME: ${fn:substring(header['Content-Type'],
		0,fn:indexOf(header['Content-Type'],"/"))}
		<BR>
		Contiene separador?: ${fn:contains(header['Content-Type'],"/")}
		
	</div>
	

	<div>
		<ad:enlace texto="Inicio" URL="index.html"></ad:enlace>
			<!-- Se cambia request por servletContext -->
		<ad:enlace texto="Inicio" URL="${ad:concat(pageContext.servletContext.contextPath,'/index.html')}"></ad:enlace>
	</div>
</body>
</html>
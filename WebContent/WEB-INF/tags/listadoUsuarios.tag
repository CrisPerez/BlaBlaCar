<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag body-content="empty"%>
<%@ attribute name="usuarios" required="true"
	type="java.util.Collection"%>
<table border="1">
	<tr>
		<td width="20%">
			<p align="center">
				<b>Usuario</b>
			</p>
		</td>
		<td width="50%">
			<p align="center">
				<b>Nombre</b>
			</p>
		</td>
		<td width="30%">
			<p align="center">
				<b>Mail</b>
			</p>
		</td>
	</tr>
	<c:forEach items="${usuarios}" var="usuario">
		<tr>
			<td with="20%" align="center">${usuario.usuario}</td>
			<td with="50%" align="center">${usuario.nombre}</td>
			<td with="30%" align="center">${usuario.email}</td>
		</tr>
	</c:forEach>
</table>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<f:view>
	<f:loadBundle basename="resources.messages" var="msg" />
	<title><h:outputText value="#{msg.login}" /></title>
</f:view>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="header">
		<%@ include file="divHeader.jsp"%>
	</div>
	<div id="nav">
		<%@ include file="divNav.jsp"%>
	</div>
	<div id="section">
		<html locale="true">
<center>
	<h3>
		<h:outputText value="#{msg.registrar}" />
	</h3>
</center>
<hr>
<f:view>
	<h:form id="RegistroForm">
		<br>
		<h:outputText value="Usuario: " />
		<h:inputText id="usuario" value="#{beanRegistrar.usuario}"
			required="true" />
		<h:message for="usuario" />
		<br>
		<br>
		<h:outputText value="Clave: " />
		<h:inputSecret id="clave" value="#{beanRegistrar.clave}"
			required="true" />
		<h:message for="clave" />
		<br>
		<br>
		<h:outputText value="Clave Repetida: " />
		<h:inputSecret id="clave2" value="#{beanRegistrar.clave2}"
			required="true" />
		<h:message for="clave2" />
		<br>
		<br>
		<h:outputText value="Nombre: " />
		<h:inputText id="nombre" value="#{beanRegistrar.nombre}"
			required="true" />
		<h:message for="nombre" />
		<br>
		<br>
		<h:outputText value="Apellidos: " />
		<h:inputText id="apellidos" value="#{beanRegistrar.apellidos}"
			required="true" />
		<h:message for="nombre" />
		<br>
		<br>
		<h:outputText value="Correo: " />
		<h:inputText id="correo" value="#{beanRegistrar.correo}"
			required="true" />
		<h:message for="correo" />
		<br>
		<br>
		<h:outputText value="Profesion: " />
		<h:inputText id="profesion" value="#{beanRegistrar.profesion}"
			required="true" />
		<h:message for="profesion" />
		<br>
		<br>
		<h:outputText value="Fecha (dd/MM/yyyy): " />
		<h:inputText id="fecha" value="#{beanRegistrar.fechaNacimiento}"
			required="true">
			<f:convertDateTime pattern="dd/MM/yyyy" />
		</h:inputText>
		<h:message for="fecha" />
		<br>
		<br>
		<h:commandButton id="submit" action="#{beanRegistrar.validacion}"
			value="Guardar" />
	</h:form>
</f:view>
<hr>
	</div>
	<div id="footer">
		<%@ include file="divFooter.jsp"%>
	</div>
</body>
</html>
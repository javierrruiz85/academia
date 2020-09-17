<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>

<%
	// segun carga la pagina principal, redireccionamos a un controlador
	response.sendRedirect( request.getContextPath() + "/cursos");
%>

<%@ include file="includes/pie.jsp" %>
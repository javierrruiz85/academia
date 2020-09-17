<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>

<h1>Zona de profesores</h1>


<hr>
${mensajeOk}
${mensajeError}
${crearOk}
${crearError}
<hr>

<h1>Dar de alta un nuevo curso</h1>

<form action="curso_crear" method="post">
	<div class="form-group">
		<label for="nombre">Nombre del curso:</label>
		<input type="text" name="nombre" id="nombre"  class="form-control" placeholder="Nombre del curso">
	</div>
	<div class="form-group">
		<label for="identificador">Identificador:</label>
		<input type="text" name="identificador" id="identificador"  class="form-control" placeholder="Ej. I007, A001, etc">
	</div>
	<div class="form-group">
		<label for="horas">Horas:</label>
		<input type="number" name="horas" id="horas"  class="form-control" placeholder="Duracion del curso en horas">
	</div>
	
	<input type="submit" value="Crear" class="btn boton btn-primary btn-block">
</form>

<hr>
<h1>Cursos en los que esta inscrito</h1>
<hr>
<table class="tabla table table-striped table-bordered">
	<thead>
		<tr>
			<th>Id</th>
			<th>Curso</th>
			<th>Identificador</th>	
			<th>Horas</th>
			<th>Id profesor</th>	
			<th>Nombre</th>
			<th>Apellidos</th>						
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cursos}" var="c">
			<tr>
				<td>${c.id}</td> 
				<td>${c.nombre}</td>
				<td>${c.identificador}</td>
				<td>${c.horas}</td>
				<td>${c.profesor.id}</td>
				<td>${c.profesor.nombre}</td>
				<td>${c.profesor.apellidos}</td>
				<td>
					<!-- En la linea siguiente, rara vez el href va a una .jsp, es mejor ir directo al controlador (crear-pelicula) -->
					
					<a href="curso_eliminar?id=${c.id}"><i class="far icono fa-trash-alt fa-2x" title="Eliminar pelicula"></i> Eliminar </a>
					
				</td>		
			</tr>
		</c:forEach>
	</tbody>
</table>



<%@ include file="../includes/pie.jsp" %>



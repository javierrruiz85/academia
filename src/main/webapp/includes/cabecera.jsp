
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!doctype html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Todas las rutas relativas comienzan por el href indicado justo abajo -->
    <!-- ${pageContext.request.contextPath} == http://localhost:8080/peliculas.javi -->
    <base href="${pageContext.request.contextPath}/" />

    <!-- fontawesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/estilos.css">
    
    <!-- datatables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">



    <title> ${param.title} | Ejercicios</title>
  </head>
  <body>
    <nav class="navbar navbar-default navbar-expand-md navbar-light fixed-top">
        <!-- logo -->
        <a class="navbar-brand">
            <i class="fab fa-fort-awesome-alt"></i>
        </a>

        <!-- icono para desplegar menu en moviles -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <!-- lista enlaces -->
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        
			
        
        </div>
      </nav>
      
      <main role="main" class="container p-5 bg-white">
      
      	<%@ include file="alerta.jsp" %>
      
      <!-- 
      CUIDADO porque esto lo incluye en funcion de la url en la que estemos
      	<jsp:include page="alerta.jsp"></jsp:include>
       -->
      	
      
      
      
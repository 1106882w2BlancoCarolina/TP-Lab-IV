<%-- 
    Document   : Publicacion
    Created on : Oct 31, 2020, 10:37:36 PM
    Author     : Carolina
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Admin - Inscripciones</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
    </head>

    <body>
        <c:choose>
            <c:when test="${ not empty user}">
                <!-- Page Wrapper -->
                <div id="wrapper">
                    <%@ include file="../adminSidebar.jsp" %>

                    <!-- Content Wrapper -->
                    <div id="content-wrapper" class="d-flex flex-column">

                        <!-- Main Content -->
                        <div id="content">

                            <div class="container-fluid" style="margin-top: 2%">

                                <!-- Page Heading -->
                                <h1 class="h3 mb-2 text-gray-800">Inscripciones</h1>

                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Socio</h6>
                                    </div>
                                    <div class="card-body">
                                        <p><span class="small font-weight-bold">Nº de socio:</span> ${modeloSocio.id}</p>
                                        <p><span class="small font-weight-bold">Nombre y Apellido:</span> ${modeloSocio.getNombreCompleto()}</p>
                                        <p><span class="small font-weight-bold">DNI:</span> ${modeloSocio.dni}</p>
                                    </div>
                                </div>
                                    
                                <c:if test="${mensaje != null}">
                                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                                        ${mensaje}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:if>
                                    
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Actividades inscriptas</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                        <c:forEach items="${ listaInscripciones }" var="item">
                                            <!-- Tiny Card Example -->
                                            <div class="col-xl-3 col-md-6 mb-4">
                                                <div class="card border-left-primary shadow h-100 py-2">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col mr-2">
                                                                <div class="text font-weight-bold text-primary text-uppercase mb-1">${item.getActividad().getNombre()}</div>
                                                                <div>${item.getActividad().getDescripcion()}</div>
                                                                <div class="h3 mb-0 font-weight-bold text-gray-800">$ ${item.getActividad().getCostoMensual()}</div>
                                                                <br>
                                                                <a onclick="window.location = 'Inscripciones?modo=eliminacion&id=${item.getId()}'" 
                                                                   class="btn btn-danger btn-user btn-block text-white">Desinscribir</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Registrar nueva Actividad</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                        <c:forEach items="${ listaActividades }" var="item">
                                            <!-- Tiny Card Example -->
                                            <div class="col-xl-3 col-md-6 mb-4">
                                                <div class="card border-left-primary shadow h-100 py-2">
                                                    <div class="card-body">
                                                        <div class="row no-gutters align-items-center">
                                                            <div class="col mr-2">
                                                                <div class="text font-weight-bold text-primary text-uppercase mb-1">${item.nombre}</div>
                                                                <div>${item.descripcion}</div>
                                                                <div class="h3 mb-0 font-weight-bold text-gray-800">$ ${item.costoMensual}</div>
                                                                <br>
                                                                <form action="Inscripciones" method="post">
                                                                    <input name="idSocio" type="hidden" value="${modeloSocio.id}">
                                                                    <input name="idActividad" type="hidden" value="${item.id}">
                                                                    <input class="btn btn-primary btn-user btn-block" type="submit" value="Inscribir" >
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <!-- Bootstrap core JavaScript-->
                                <script src="vendor/jquery/jquery.min.js"></script>
                                <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                                <!-- Core plugin JavaScript-->
                                <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

                                <!-- Custom scripts for all pages-->
                                <script src="js/sb-admin-2.min.js"></script>

                            </div>
                        </div>

                    </div>

                </c:when>
                <c:otherwise>
                    <%@include file="../errorUsuario.jsp" %>
                </c:otherwise>
            </c:choose>
    </body>
</html>


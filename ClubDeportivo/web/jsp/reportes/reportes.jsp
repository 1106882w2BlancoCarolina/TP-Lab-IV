<%-- 
    Document   : listadoPublicaciones
    Created on : Oct 31, 2020, 7:43:05 PM
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

        <title>Admin - Reportes</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body>
        <c:choose>
            <c:when test="${ not empty user }">
                <!-- Page Wrapper -->
                <div id="wrapper">
                    <%@ include file="../adminSidebar.jsp" %>

                    <!-- Content Wrapper -->
                    <div id="content-wrapper" class="d-flex flex-column">

                        <!-- Main Content -->
                        <div id="content">

                            <div class="container-fluid" style="margin-top: 2%">

                                <!-- Page Heading -->
                                <h1 class="h3 mb-2 text-gray-800">Reportes</h1>

                                <div class="row">
                                    <!-- Earnings (Monthly) Card Example -->
                                    <div class="col-xl-3 col-md-6 mb-4">
                                        <div class="card border-left-success shadow h-100 py-2">
                                            <div class="card-body">
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col mr-2">
                                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                            Total de ganancias </div>
                                                        <div class="h5 mb-0 font-weight-bold text-gray-800">$ ${totalGanancias}</div>
                                                    </div>
                                                    <div class="col-auto">
                                                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Project Card Example -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Actividades con mayor ganancia total</h6>
                                    </div>

                                    <c:forEach items="${ actividadesGanancias }" var="item">
                                        <div class="card-body">
                                            <h4 class="small font-weight-bold">${item.nombre}<span
                                                    class="float-right">$ ${item.ganancia}</span></h4>
                                            <div class="progress mb-4">
                                                <div class="progress-bar bg-success" role="progressbar" 
                                                     style="width: ${ (item.ganancia / mayorGanancia) * 100}%"></div>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </div>

                                <!-- DataTables -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Listado de socios inscriptos por actividad</h6>
                                    </div>
                                    <div class="card-body">
                                        <form action="Reportes" method="post" class="user">
                                            <div class="row">
                                                <div class="col-md-6 col-lg-6">
                                                    <div class="form-group">
                                                        <select name=cmbActividad class="form-control" id="cmbActividad" required>

                                                            <c:forEach items="${ listaActividades }" var="item">
                                                                <option value="${ item.id }" <c:if test="${ item.id == idActividad}">selected</c:if>>${ item.nombre }</option>
                                                            </c:forEach>
                                                        </select>


                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-lg-6">
                                                    <button type="submit" class="btn btn-primary btn-icon-split">
                                                        <span class="icon text-white">
                                                            <i class="fas fa-search"></i>
                                                        </span>
                                                        <span class="text text-white">Buscar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Nº Socio</th>
                                                        <th>Nombre</th>
                                                        <th>Apellido</th>
                                                        <th>DNI</th>
                                                        <th>Genero</th>
                                                        <th>Fecha Nacimiento</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${ socios }" var="item">
                                                        <tr>
                                                            <td>${ item.id }</td>
                                                            <td>${ item.nombre }</td>
                                                            <td>${ item.apellido }</td>
                                                            <td>${ item.dni}</td>
                                                            <td>${ item.genero}</td>
                                                            <td>${ item.fechaNacimientoString}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Listado de cuotas pagas</h6>

                                    </div>
                                    <div class="card-body">
                                        <p>Puede acceder al listado de cuotas pagas de un determinado socio a traves de la seccion Pagos</p>
                                        <a onclick="window.location = 'Pagos'" class="btn btn-success btn-icon-split">
                                            <span class="icon text-white">
                                                <i class="fas fa-dollar-sign"></i>
                                            </span>
                                            <span class="text text-white">Ir a Pagos</span>
                                        </a>
                                    </div>
                                </div>


                                <!-- Bootstrap core JavaScript-->
                                <script src="vendor/jquery/jquery.min.js"></script>
                                <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                                <!-- Core plugin JavaScript-->
                                <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

                                <!-- Custom scripts for all pages-->
                                <script src="js/sb-admin-2.min.js"></script>

                                <!-- Page level plugins -->
                                <script src="vendor/datatables/jquery.dataTables.min.js"></script>
                                <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

                                <!-- Page level custom scripts -->
                                <script src="js/demo/datatables-demo.js"></script>
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

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

        <title>Admin - Publicaciones</title>

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
                                <h1 class="h3 mb-2 text-gray-800">Listado de Publicaciones</h1>
                                <p class="mb-4">Puede editar y eliminar publicaciones</p>
                                <c:if test="${mensaje != null}">
                                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                                        ${mensaje}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:if>


                                <!-- DataTables -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Publicaciones</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Activa</th>
                                                        <th>Titulo</th>
                                                        <th>Contenido</th>
                                                        <th>Actividad</th>
                                                        <th>Fecha</th>
                                                        <th></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${ publicaciones }" var="item">
                                                        <tr>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${item.activa == true}">
                                                                        Si     <i class="fas fa-check"></i>
                                                                    </c:when>    
                                                                    <c:otherwise>
                                                                        No     <i class="fas fa-times"></i>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${ item.titulo }</td>
                                                            <td style="max-width: 200px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                                                                ${ item.contenido }</td>
                                                            <td>${ item.getActividad().getNombre() }</td>
                                                            <td>${ item.fechaString }</td>
                                                            <td>
                                                                <a onclick="window.location = 'Publicaciones?modo=edicion&id=${ item.id }'" class="btn btn-primary btn-circle">
                                                                    <i class="fas fa-pencil-alt" style="color: white"></i>
                                                                </a>
                                                            </td>
                                                            <td>
                                                                <a onclick="window.location = 'Publicaciones?modo=eliminacion&id=${ item.id }'" class="btn btn-danger btn-circle">
                                                                    <i class="fas fa-trash" style="color: white"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                        </div>
                                        <a onclick="window.location = 'Publicaciones?modo=creacion'" class="btn btn-success btn-icon-split" style="margin-top:2%">
                                            <span class="icon text-white">
                                                <i class="fas fa-plus"></i>
                                            </span>
                                            <span class="text text-white">Crear Nueva Publicacion</span>
                                        </a>
                                    </div>
                                </div>

                            </div>
                            <!-- /.container-fluid -->

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

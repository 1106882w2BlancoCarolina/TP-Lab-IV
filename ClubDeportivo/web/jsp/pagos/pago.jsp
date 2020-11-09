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

        <title>Admin - Pagos</title>

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
                                <h1 class="h3 mb-2 text-gray-800">Pago</h1>

                                <div class="col-lg-8 offset-lg-2" style="margin-top: 2%">

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

                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary">Detalle de Actividades</h6>
                                        </div>
                                        <div class="card-body">
                                            <c:forEach items="${ actividadesDelSocio }" var="item">
                                                <p><span class="small font-weight-bold">${item.nombre}:</span><span class="float-right">$ ${item.costoMensual}</span></p>
                                            </c:forEach>
                                            <p><span class="font-weight-bold">Total:</span> <span class="float-right font-weight-bold">$ ${ modeloPago.monto }</span></p>
                                        </div>
                                    </div>


                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary">Registrar nuevo Pago</h6>
                                        </div>
                                        <div class="card-body">
                                        
                                            <form action="Pagos" method="post" class="user needs-validation" novalidate>
                                                <input type="hidden" name="idSocio" value="<jsp:getProperty name="modeloPago" property="idSocio"></jsp:getProperty>"/>
                                                <input name="txtMonto" type="hidden" value="<jsp:getProperty name="modeloPago" property="monto"></jsp:getProperty>"/>
                                                <p><span class="font-weight-bold">Monto a pagar:</span> <span class="float-right font-weight-bold">$ ${ modeloPago.monto }</span></p>
                                                <div class="form-group">
                                                    <input name="txtReferencia" type="text" class="form-control form-control-user" placeholder="Referencia" 
                                                           value="<jsp:getProperty name="modeloPago" property="referencia"></jsp:getProperty>"/>
                                                </div>
                                                <div class="form-group">
                                                    <input name="txtFecha" type="date" class="form-control form-control-user" placeholder="Fecha" required
                                                           value="<jsp:getProperty name="modeloPago" property="fechaString"></jsp:getProperty>"/>
                                                    <div class="invalid-feedback">Debe ingresar una fecha de pago</div>
                                                </div>

                                                <div class="row" style="margin-top: 6%">        
                                                    <div class="col-lg-6">
                                                        <a onclick="window.location = 'Pagos?modo=listaPagos&id=${modeloSocio.id}'" class="btn btn-light btn-user btn-block">Cancelar</a>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <input class="btn btn-primary btn-user btn-block" type="submit" value="Registrar" >
                                                    </div>
                                                </div>
                                            </form>
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
                    </div>

                </c:when>
                <c:otherwise>
                    <%@include file="../errorUsuario.jsp" %>
                </c:otherwise>
            </c:choose>
    </body>
</html>


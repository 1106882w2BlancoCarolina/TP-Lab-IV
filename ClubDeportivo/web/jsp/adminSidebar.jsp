<%-- 
    Document   : adminSidenav
    Created on : Oct 31, 2020, 9:05:34 PM
    Author     : Carolina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="">
        <div class="sidebar-brand-text mx-3">Admin Apolo</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item">
        <a class="nav-link" href= "Publicaciones">
            <i class="fas fa-fw fa-newspaper"></i>
            <span>Publicaciones</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href= "Socios">
            <i class="fas fa-fw fa-address-book"></i>
            <span>Socios</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href= "Actividades">
            <i class="fas fa-fw fa-trophy"></i>
            <span>Actividades</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">
    
    <li class="nav-item">
        <a class="nav-link" href= "Pagos">
            <i class="fas fa-fw fa-credit-card"></i>
            <span>Pago de cuotas</span></a>
    </li>
    
    <li class="nav-item">
        <a class="nav-link" href= "Inscripciones">
            <i class="fas fa-fw fa-star"></i>
            <span>Inscripciones</span></a>
    </li>
    
    <!-- Divider -->
    <hr class="sidebar-divider my-0">
    
    <li class="nav-item">
        <a class="nav-link" href= "Reportes">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Reportes</span></a>
    </li>
    
    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">
    
    <li class="nav-item">
        <a class="nav-link" href= "index.html">
            <i class="fas fa-fw fa-arrow-left"></i>
            <span>Salir</span></a>
    </li>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>

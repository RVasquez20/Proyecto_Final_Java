<%-- 
    Document   : NuevoAdmin
    Created on : 15/10/2020, 12:50:52 AM
    Author     : rodri
--%>
<%@page import="java.util.HashMap"%>
<%
    HttpSession actual = request.getSession(true);
    String usuario = (String) actual.getAttribute("Logueado");
    String nombres = (String) actual.getAttribute("nom");
    String email = (String) actual.getAttribute("em");
    String profile = (String) actual.getAttribute("Ft");
    HashMap<String, String> Menu = (HashMap) actual.getAttribute("Men");
    session.setMaxInactiveInterval(900);
    if ((actual.getAttribute("Logueado") != null)) {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/main.css" />
        <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="CSS/comun.css">
        <script src="JS/AllInOne.js"></script>
        <title>Agregar Admin</title>
    </head>
    <body class="is-preload">
        <!-- Wrapper -->
        <div id="wrapper">
            <!-- Header -->
            <header id="header">
                <div class="dropdown">
                    <button id="btn_sesion" type="button" class="btn btn-outline-light dropdown-toggle" style="border:none;" data-toggle="dropdown">
                        <img src="sources/<%=profile%>" style="width: 60px; height: 60px; border-radius: 2em;"/>   
                        <span id="nombre_sesion" class="navbarr-brand"><%=nombres%></span>
                    </button>
                    <div id="dropdown_menu" class="dropdown-menu text-center" style="font-size: 22px;">

                        <span class="dropdown-item"><%=usuario%></span>
                        <span class="dropdown-item"><%=email%></span>
                        <form action="sr_login" method="post">
                            <h6 class="text-muted"><input type="submit" value="Cerrar Sesion" class="btn btn-dark" id="cerrarsesion" name="cerrarsesion"/></h6>
                        </form>
                    </div>


                </div>
                <div class="logo">
                    <span class="icon fa-gem"></span>
                </div>
                <div class="content">
                    <div class="inner">
                        <h2>Formulario</h2><h3>Nuevo Administrador</h3>
                        <h5 id="Titulo" class="text-center">NUEVO REGISTRO</h5>

                        <form action="sr_Admin" method="post" class="form-group" enctype="multipart/form-data" name="formulario" class="form-horizontal" role="form">

                            <label >Nombre usuario</label>                                    <br>
                            <input type="text" id="text_usuario" class="form-control" name="txt_usuarionuevo" required="">
                            <br>
                            <label>Nombres</label>                                    <br>
                            <input type="text" id="text_nombre" class="form-control" name="txt_nombre" required="" onkeypress="return text(event);">
                            <br>
                            <label>Apellidos</label>                                    <br>
                            <input type="text" id="text_apellidos" class="form-control" name="txt_apellidos" required="" onkeypress="return text(event);">
                            <br>
                            <label>Correo</label>                                    <br>
                            <input type="email" id="text_correo" class="form-control" name="txt_correo" required="">
                            <br>
                            <label>Password</label>                                    <br>
                            <input type="password" id="text_pass" class="form-control" name="txt_passnueva" required="">
                            <br>
                            <label>Foto De Perfil</label>  
                            <input type="file" id="imagen" name="archivo" onchange="cargarFotodeperfil(this)">
                            <br>
                            <label>Codigo de validacion</label>                                    <br>
                            <input type="text" id="text_codigo" class="form-control" name="txt_codigo" required="" maxlength="7">
                            <br>

                            <input type="hidden" name="nombre" id="file">
                            <input id ="Registrar" name="Registrar" value="Registrar" class="btn btn-success" type ="submit" onclick="return ValidarCodigo();">

                        </form>
                    </div>
                </div>
                <nav>
                    <ul>
                        <%

                            for (String i : Menu.keySet()) {
                                out.println("<li><a href='" + Menu.get(i) + "'>" + i + "</a></li><br>");
                            }


                        %>
                        <!--<li><a href="#elements">Elements</a></li>-->
                    </ul>
                </nav>
            </header>
        </div>
        <!-- BG -->
        <div id="bg"></div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/browser.min.js"></script>
        <script src="assets/js/breakpoints.min.js"></script>
        <script src="assets/js/util.js"></script>
        <script src="assets/js/main.js"></script>
    </body>
</html>
<%    } else {
        response.sendRedirect("index.jsp");

    }
%>
<%-- 
    Document   : NuevoAdmin
    Created on : 15/10/2020, 12:50:52 AM
    Author     : rodri
--%>
<%@page import="java.util.HashMap"%>
<%
      HttpSession actual =request.getSession(true);
      String usuario = (String) actual.getAttribute("Logueado");
      String nombres=(String) actual.getAttribute("nom");
      String email=(String) actual.getAttribute("em");
      String profile=(String) actual.getAttribute("Ft");
      HashMap<String,String> Menu=(HashMap)actual.getAttribute("Men");
      session.setMaxInactiveInterval(900);
      if((actual.getAttribute("Logueado")!=null)){
        %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="JS/AllInOne.js"></script>
        <title>Agregar Admin</title>
    </head>
    <body>
           <div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
        <h4 class="text-white">Herramientas</h4>
        <!--<form action="sr_login" method="post">
            <h6 class="text-muted"><input type="submit" value="Cerrar Sesion" class="btn btn-dark" id="cerrarsesion" name="cerrarsesion"/></h6>
        </form>-->
    </div>

  </div>
  <nav class="navbar navbar-dark baner" style="background-color: #2A2A1E">
      <div class="dropdown">
          <button type="button" class="btn btn-outline-light dropdown-toggle" style="border:none;" data-toggle="dropdown">
    <img src="sources/<%=profile%>" style="width: 60px; height: 60px; border-radius: 2em;"/>   <span class="navbarr-brand"><%=nombres%></span>
  </button>
  <div class="dropdown-menu text-center" style="font-size: 22px;">

    <span class="dropdown-item"><%=usuario%></span>
    <span class="dropdown-item"><%=email%></span>
    <form action="sr_login" method="post">
            <h6 class="text-muted"><input type="submit" value="Cerrar Sesion" class="btn btn-dark" id="cerrarsesion" name="cerrarsesion"/></h6>
        </form>
  </div>
</div>
   <!-- <button class="navbar-toggler btn btn-secondary" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
<img src="sources/<%=profile%>" style="width: 60px; height: 60px; border-radius: 2em;"/>
      <span class="navbarr-brand"><%=nombres%></span>
<span class="navbarr-brand"><%=usuario%></span>
-->
  </button>
  <% 

                         for (String i:Menu.keySet()){
                             out.println("<a href='" + Menu.get(i) + "'>" + i + "</a><br>");
                         }
                         
                    
                    %>
  </nav>
</div>
  <div class="container" >
        <h1>Formulario Nuevo Administrador</h1>
         <h5 style="color: #000000;" id="Titulo" class="text-center">NUEVO REGISTRO</h5>



    <form action="sr_Admin" method="post" class="form-group" enctype="multipart/form-data" name="formulario" class="form-horizontal" role="form">
                     
                                     <label style="color: #000">Nombre usuario</label>                                    <br>
                                    <input type="text" id="text_usuario" class="form-control" name="txt_usuarionuevo" required="">
                                    <br>
                                    <label style="color: #000">Nombres</label>                                    <br>
                                    <input type="text" id="text_nombre" class="form-control" name="txt_nombre" required="" onkeypress="return text(event);">
                                    <br>
                                    <label style="color: #000">Apellidos</label>                                    <br>
                                    <input type="text" id="text_apellidos" class="form-control" name="txt_apellidos" required="" onkeypress="return text(event);">
                                     <br>
                                     <label style="color: #000">Correo</label>                                    <br>
                                    <input type="email" id="text_correo" class="form-control" name="txt_correo" required="">
                                    <br>
                                    <label style="color: #000">Password</label>                                    <br>
                                    <input type="password" id="text_pass" class="form-control" name="txt_passnueva" required="">
                                    <br>
                                       <label style="color: #000">Foto De Perfil</label>  
                                     <input type="file" id="imagen" name="archivo" class="col-md-8 btn" onchange="cargarFotodeperfil(this)">
                                <br>
                                    <label style="color: #000">Codigo de validacion</label>                                    <br>
                                    <input type="text" id="text_codigo" class="form-control" name="txt_codigo" required="" maxlength="7">
                                    <br>
                              
                                     <input type="hidden" name="nombre" id="file">
                                     <input id ="Registrar" name="Registrar" value="Registrar" class="btn btn-success" type ="submit" onclick="return ValidarCodigo();">
                     
                        </form>
      </div>


          


   

                                     </body>
</html>
<%
   }
else{
response.sendRedirect("index.jsp");

}
%>
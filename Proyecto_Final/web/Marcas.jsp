<%-- 
    Document   : Marcas
    Created on : 1/10/2020, 04:55:32 PM
    Author     : rodri
--%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.util.HashMap"%>
<%
    HttpSession actual = request.getSession(true);
    String usuario = (String) actual.getAttribute("Logueado");
    String nombres = (String) actual.getAttribute("nom");
    String email = (String) actual.getAttribute("em");
    String profile = (String) actual.getAttribute("Ft");
    HashMap<String, String> Menu = (HashMap) actual.getAttribute("Men");
    String tipo = (String) actual.getAttribute("T");
    session.setMaxInactiveInterval(900);
    if ((actual.getAttribute("Logueado") != null) && (tipo.equals("ADMIN"))) {
%>
<%@page import="Modelo.marcas"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
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
        <title>JSP Page</title>
    </head>
    <body class="is-preload">
        <!-- Wrapper -->
        <div id="wrapper">
            <!-- Header -->
            <header id="header">
                <div class="dropdown">
                    <button id="btn_sesion" type="button" class="btn btn-outline-light" dropdown-toggle="dropdown" style="border:none;" data-toggle="dropdown">
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

                        <h1>Marcas</h1>
                     <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
  Open modal
</button>
                        <form action="sr_Marcas" method="POST" class="form-group">
                            <label>ID:</label>
                            <input type="text" name="txt_id_Marcas" id="txt_id_Marcas" class="form-control" value="0" readonly="">
                            <label for="lbl_Marca" ><b>Marca</b></label>
                            <input type="text" name="txt_Marca" id="txt_Marca" class="form-control" placeholder="Ejemplo: Pepsi" onkeypress="return text(event);" required>

                            <br>
                            <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                            <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                            <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if (!confirm('¿Desea Eliminar?'))
                                        return false" >Eliminar</button>
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
     

                      
        <br>

       

        <br>
 
        <!-- BG -->

  	</div>

		
        <div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
 
      </div>

      <!-- Modal body -->
      <div class="modal-body">

         <table class="table table-striped" style="text-align: center;">
                <thead>
                    <tr>
                        <th>Marca</th>

                    </tr>
                </thead>
                <tbody id="tbl_Marcas" style="color:white;">
                    <%            marcas marca = new marcas();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = marca.ListaDeMarcas();
                        for (int t = 0; t < tabla.getRowCount(); t++) {
                            out.println("<tr data-idm=" + tabla.getValueAt(t, 0) + ">");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("</tr>");

                        }

                    %>

                </tbody>
            </table>
  </div>

 

    </div>
  </div>
</div>
                    <!-- BG -->
			<div id="bg"></div>
    
                    
		<!-- Scripts -->
			
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

        <script type="text/javascript">
                                $('#tbl_Marcas').on('click', 'tr td', function (evt) {
                                    var target, id, Marcas;
                                    target = $(event.target);
                                    id = target.parent().data("idm");
                                    Marcas = target.parent("tr").find("td").eq(0).html();


                                    $("#txt_id_Marcas").val(id);
                                    $("#txt_Marca").val(Marcas);
   $('#myModal').modal('hide')
                                });
        </script>
 
      

    </body>
</html>
<%    } else {

        response.sendRedirect("Error.jsp");

    }
%>

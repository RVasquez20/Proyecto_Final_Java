<%-- 
    Document   : clientes
    Created on : 29/09/2020, 10:32:32 PM
    Author     : Willy Valle
--%>
<%
      HttpSession actual =request.getSession(true);
      String usuario = (String) actual.getAttribute("Logueado");
      String nombres=(String) actual.getAttribute("nom");
      String email=(String) actual.getAttribute("em");
      String profile=(String) actual.getAttribute("Ft");
      HashMap<String,String> Menu=(HashMap)actual.getAttribute("Men");
      session.setMaxInactiveInterval(900);
      if(actual.getAttribute("Logueado")!=null){
        %>  
<%@page import="Modelo.Clientes" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Ventas" %>
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
        <script>
        $(document).ready(function () {
            
              $("#btn_modificar").hide();
       $("#btn_eliminar").hide();
       $("#btn_agregar").show();
        });
    </script>

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
                    <span ><img src="CSS/Logos/eme.svg" style="max-width: 60px; max-height: 60px;margin-right:5px; "></span>
                </div>
                <div class="content">
                    <div class="inner">
        <h1>Ventana Clientes</h1>
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Listado</button>
        <button type="button" name="btn_nuevoc" id="btn_nuevoc" class="btn btn-info btn-lg"  onclick="LimpiarClientes();">Nuevo</button>
        

    
            <form action="sr_cliente" method="post" class="form-group">
                <label for="lbl_id">ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value ="0"  readonly>
                <label for="lbl_nombres">Nombres:</label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Juan Francisco" onkeypress="return text(event);" required>
                <label for="lbl_apellidos">Apellidos:</label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Perez Ramirez" onkeypress="return text(event);" required>
                <label for="lbl_nit">Nit:</label>
                <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="12345678" maxlength="11" onkeypress="return entero(event);" required>
                <label for="lbl_telefono">Telefono:</label>
                <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="12345678" maxlength="8"  onkeypress="return entero(event);" required>
                <label for="lbl_correo">Correo:</label>
                <input type="email" name="txt_correo" id="txt_correo" class="form-control" required>
                <label id="lbl_fecha">Fecha:</label>
                <input type="datetime" name="txt_fecha" id="txt_fecha" class="form-control" value="0" required>
                <br>
                <label for="lbl_genero" style="font-size: 18px;">Genero: &ensp;</label>
                <div class="form-check-inline">
                  <label class="form-check-label" for="Masculino">
                      <input type="radio" class="form-check-input" id="drop_genero" name="drop_genero" value="1" style="width: 1.2em;height: 1.2em;">Masculino
                  </label>
                </div>
                <div class="form-check-inline">
                  <label class="form-check-label" for="Femenino">
                    <input type="radio" class="form-check-input" id="drop_genero2" name="drop_genero" value="2" style="width: 1.2em;height: 1.2em;">Femenino
                  </label>
                </div>
<br>
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger btn-danger" onclick="javasript:if (!confirm('Desea Eliminar?'))
                            return false">Eliminar</button>

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
   <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header text-center">
        <h4 class="modal-title text-center">Lasta de las Marcas </h4>
 
      </div>

      <!-- Modal body -->
      <div class="modal-body text-center">

         <table class="table table-dark table-hover text-center">
                <thead>
                    <tr>
                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>Nit</th>
                        <th>Telefono</th>
                        <th>Correo</th>
                        <th>Fecha Ingreso</th>
                        <th>Genero</th>
                    </tr>
                </thead>
                <tbody id="tbl_clientes" style="color:white;">
                    <%                        Clientes clientes = new Clientes();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = clientes.leer();
                        for (int t = 0; t < tabla.getRowCount(); t++) {
                            out.println("<tr data-id=" + tabla.getValueAt(t, 0) + ">");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                            if(tabla.getValueAt(t, 7).equals("1")){
                                out.println("<td>Masculino</td>");
                            }else{
                                out.println("<td>Femenino</td>");
                            }
                            
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
            $(document).ready(function () {
            $("#lbl_fecha").hide();
                        $("#txt_fecha").hide();
                        $("#btn_agregar").show();
                        $("#btn_modificar").hide();
                        $("#btn_eliminar").hide();
                        
    });
            
            </script>
        <script type="text/javascript">
                    $('#tbl_clientes').on('click', 'tr td', function (evt) {
                        $("#lbl_fecha").show();
                        $("#txt_fecha").show();
                        $("#btn_agregar").hide();
                        $("#btn_modificar").show();
                        $("#btn_eliminar").show();
                        var target, id, nombres, apellidos, nit, telefono, correo, fechaingreso, genero;
                        target = $(evt.target);
                        id = target.parent().data('id');
                        nombres = target.parent("tr").find("td").eq(0).html();
                        apellidos = target.parent("tr").find("td").eq(1).html();
                        nit = target.parent("tr").find("td").eq(2).html();
                        telefono = target.parent("tr").find("td").eq(3).html();
                        correo = target.parent("tr").find("td").eq(4).html();
                        fechaingreso = target.parent("tr").find("td").eq(5).html();
                        genero = target.parent("tr").find("td").eq(6).html();
                        if(genero==="Masculino"){
                            $("#drop_genero").prop('checked',true);
                        }
                        if(genero==="Femenino"){
                            $("#drop_genero2").prop('checked',true);
                        }
                        $("#txt_id").val(id);
                        $("#txt_nombres").val(nombres);
                        $("#txt_apellidos").val(apellidos);
                        $("#txt_nit").val(nit);
                        $("#txt_telefono").val(telefono);
                        $("#txt_correo").val(correo);
                        $("#txt_fecha").val(fechaingreso);
                          $('#myModal').modal('hide');

                    }
                    );

        </script>
 <script type="text/javascript">
                    $('#btn_nuevoc').click( function (evt) {
                        $("#lbl_fecha").hide();
                        $("#txt_fecha").hide();
                        $("#btn_agregar").show();
                        $("#btn_modificar").hide();
                        $("#btn_eliminar").hide();
                             $("#drop_genero").prop('checked',false);
      $("#drop_genero2").prop('checked',false);
                    }
                    );

        </script>

    </body>
</html>
<%
   }
else{
response.sendRedirect("index.jsp");

}
%>
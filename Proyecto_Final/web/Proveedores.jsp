<%-- 
    Document   : Proveedores
    Created on : 29/09/2020, 11:49:52 PM
    Author     : josef
--%>
<%@page import="java.util.HashMap"%>
<%
    HttpSession actual = request.getSession(true);
    String usuario = (String) actual.getAttribute("Logueado");
    String nombres = (String) actual.getAttribute("nom");
    String email = (String) actual.getAttribute("em");
    String profile = (String) actual.getAttribute("Ft");
    String tipo = (String) actual.getAttribute("T");
    HashMap<String, String> Menu = (HashMap) actual.getAttribute("Men");
    session.setMaxInactiveInterval(900);
    if ((actual.getAttribute("Logueado") != null) && (tipo.equals("ADMIN"))) {
%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.Proveedores"%>
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
                    <button id="btn_sesion" type="button" class="btn btn-outline-light dropdown-toggle"  data-toggle="dropdown" style="border:none;">
                        <img src="sources/<%=profile%>" style="width: 60px; height: 60px; border-radius: 2em;"/>   
                        <span id="nombre_sesion" class="navbarr-brand"><%=nombres%></span>
                    </button>
                    <div id="dropdown_menu" class="dropdown-menu text-center" style="font-size: 22px; z-index: 1500;">

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

                        <h1>Proveedores</h1>
                        <button type="button" class="btn btn-info btn-lg" id="nuevo" onclick="LimpiarProveedores()">Nuevo</button><button type="button" class="btn btn-primary" id="Mostrar" >  <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-card-list" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                            <path fill-rule="evenodd" d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5z"/>
                            <circle cx="3.5" cy="5.5" r=".5"/>
                            <circle cx="3.5" cy="8" r=".5"/>
                            <circle cx="3.5" cy="10.5" r=".5"/>
                            </svg>
                            &ensp;Lista
                        </button>
                        <div class="container">
                            <form action="sr_Proveedores" method="POST" class="form-group">
                                <label>ID:</label>
                                <input type="text" name="txt_id_Proveedores" id="txt_id_Proveedores" class="form-control" value="0"  readonly>
                                <label for="lbl_Proveedor" ><b>Proveedor</b></label>
                                <input type="text" name="txt_Proveedor" id="txt_Proveedor" class="form-control" placeholder="Ejemplo: Pepsi" onkeypress="return text(event);" required>
                                <label for="lbl_NIT" ><b>NIT</b></label>
                                <input type="text" name="txt_NIT" id="txt_NIT" class="form-control" placeholder="Ejemplo: 12345678"  onkeypress="return entero(event);" required>
                                <label for="lbl_Direccion" ><b>Direccion</b></label>
                                <input type="text" name="txt_Direccion" id="txt_Direccion" class="form-control" placeholder="Ejemplo: Antigua calle 2" required>
                                <label for="lbl_Telefono" ><b>Telefono</b></label>
                                <input type="text"  name="txt_Telefono" id="txt_Telefono" class="form-control" placeholder="Ejemplo: 78211212" maxlength="8" onkeypress="return entero(event);" required>
                                <br>
                                <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if (!confirm('¿Desea Eliminar?'))
                                            return false" >Eliminar</button>
                            </form>
                        </div>
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
        <br>

        <br>
        <br>
        <!-- BG -->
        <div id="bg"></div>

        
      <!-- The Modal -->
  <div class="modal" id="myModal2">
    <div class="modal-dialog modal-xl modal-dialog-scrollable">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h1 class="modal-title">Modal Heading</h1>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         <table class="table table-dark table-hover">
                <thead>
                    <tr>
                        <th>Proveedor</th>
                        <th>NIT</th>
                        <th>Direccion</th>
                        <th>Telefono</th>
                    </tr>
                </thead>
                <tbody id="tbl_Poveedores">
                    <%            Proveedores proveedor = new Proveedores();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = proveedor.ListaDeProveedores();
                        for (int t = 0; t < tabla.getRowCount(); t++) {
                            out.println("<tr data-id=" + tabla.getValueAt(t, 0) + ">");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                            out.println("</tr>");

                        }
                    %>

                </tbody>
            </table>
        </div>
       
     
        
      </div>
    </div>
  </div>
  

        
                   <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/browser.min.js"></script>
        <script src="assets/js/breakpoints.min.js"></script>
        <script src="assets/js/util.js"></script>
        <script src="assets/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

     
        <script type="text/javascript">
            $('#tbl_Poveedores').on('click', 'tr td', function (evt) {
                var target, id, Proveedores, NIT, direccion, telefono;
                target = $(event.target);
                   $("#btn_modificar").show();
       $("#btn_eliminar").show();
       $("#btn_agregar").hide();
                id = target.parent().data('id');
                Proveedores = target.parent("tr").find("td").eq(0).html();
                NIT = target.parent("tr").find("td").eq(1).html();
                direccion = target.parent("tr").find("td").eq(2).html();
                telefono = target.parent("tr").find("td").eq(3).html();

                $("#txt_id_Proveedores").val(id);
                $("#txt_Proveedor").val(Proveedores);
                $("#txt_NIT").val(NIT);
                $("#txt_Direccion").val(direccion);
                $("#txt_Telefono").val(telefono);
            $('#myModal2').modal('hide')
            

            });
        </script>
         <script type="text/javascript">

        $("#Mostrar").click(function () {
            $("#myModal2").modal('show');
        });


    </script>
    <script type="text/javascript">
$(document).ready(function () {
             $("#btn_modificar").hide();
       $("#btn_eliminar").hide();
       $("#btn_agregar").show();
});
    </script>
        <script type="text/javascript">
        $("#nuevo").click(function () {
                $("#btn_modificar").hide();
       $("#btn_eliminar").hide();
       $("#btn_agregar").show();
        });
    </script>


    </body>
</html>
<%
    } else {
        response.sendRedirect("Error.jsp");

    }
%>
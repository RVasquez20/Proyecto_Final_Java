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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <script src="JS/Clientes.js"></script>

        <title>JSP Page</title>

    </head>
    <body>
        <div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
        <div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
        <h4 class="text-white">Herramientas</h4>
        <form action="sr_login" method="post">
            <h6 class="text-muted"><input type="submit" value="Cerrar Sesion" class="btn btn-dark" id="cerrarsesion" name="cerrarsesion"/></h6>
        </form>
    </div>

  </div>
  <nav class="navbar navbar-dark baner" style="background-color: #2A2A1E;">
    <button class="navbar-toggler btn btn-secondary" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
     <img src="sources/<%=profile%>" style="width: 40px; height: 40px"/>
      <span class="navbarr-brand"><%=nombres%></span>
<span class="navbarr-brand"><%=usuario%></span>
<span class="navbarr-brand"><%=email%></span>
  </button>

  </nav>
</div>
        <h1>Ventana Clientes</h1>

        <button type="button" class="btn btn-info btn-lg"  onclick="LimpiarClientes()">Nuevo</button>
        <a href="index.jsp">Regresar</a>

        <div class="container-fluid">
            <form action="sr_cliente" method="post" class="form-group">
                <label for="lbl_id">ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value ="0" readonly>
                <label for="lbl_nombres">Nombres:</label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Juan Francisco" required>
                <label for="lbl_apellidos">Apellidos:</label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Perez Ramirez" required>
                <label for="lbl_nit">Nit:</label>
                <input type="number" name="txt_nit" id="txt_nit" class="form-control" placeholder="12345678" required>
                <label for="lbl_telefono">Telefono:</label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="12345678" required>
                <label for="lbl_correo">Correo:</label>
                <input type="email" name="txt_correo" id="txt_correo" class="form-control" required>
                <label for="lbl_fecha">Fecha:</label>
                <input type="datetime" name="txt_fecha" id="txt_fecha" class="form-control" required>
                <label for="lbl_genero">Genero:</label>
                <select name="drop_genero" id="drop_genero" class="form-control">
                    
                        
                        <option value='0'>Seleccione</option>
                        <option value='1'>Masculino</option>
                        <option value='2'>Femenino</option>
                      


                    
                </select>

                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger btn-danger" onclick="javasript:if (!confirm('Desea Eliminar?'))
                            return false">Eliminar</button>

            </form>
            <table class="table table-striped">
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
                <tbody id="tbl_clientes">
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
                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                            out.println("</tr>");

                        }
                    %>
                </tbody>
            </table>

        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        
        <script type="text/javascript">
                    $('#tbl_clientes').on('click', 'tr td', function (evt) {
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

                        $("#txt_id").val(id);
                        $("#txt_nombres").val(nombres);
                        $("#txt_apellidos").val(apellidos);
                        $("#txt_nit").val(nit);
                        $("#txt_telefono").val(telefono);
                        $("#txt_correo").val(correo);
                        $("#txt_fecha").val(fechaingreso);
                        $("#drop_genero").val(genero);

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
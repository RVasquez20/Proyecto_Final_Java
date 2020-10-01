<%-- 
    Document   : ventas
    Created on : 29/09/2020, 10:32:47 PM
    Author     : Willy Valle
--%>

<%@page import="Modelo.Ventas" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    </head>
    <body>
        <h1>Ventana Ventas</h1>
        <a href="index.jsp">Regresar</a>

        <div class="container-fluid">
            <form action="sr_venta" method="post" class="form-group">
                <label for="lbl_id">ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value ="0" readonly>
                <label for="lbl_nofactura">No.Factura:</label>
                <input type="number" name="txt_nofactura" id="txt_nofactura" class="form-control" required>
                <label for="lbl_serie">Serie:</label>
                <input type="text" name="txt_serie" id="txt_serie" class="form-control" required>
                <label for="lbl_fechafactura">Fecha Factura:</label>
                <input type="date" name="txt_fechafactura" id="txt_fechafactura" class="form-control" required>
                <label for="lbl_idcliente">ID Cliente:</label>
                <input type="number" name="txt_idcliente" id="txt_idcliente" class="form-control" required>
                <label for="lbl_idempleado">ID Empleado:</label>
                <input type="number" name="txt_idempleado" id="txt_idempleado" class="form-control" required>
                <label for="lbl_fechaingreso">Fecha Ingreso:</label>
                <input type="date" name="txt_fechaingreso" id="txt_fechaingreso" class="form-control" required>


                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger">Eliminar</button>

            </form>
             <table class="table table-striped">
                <thead>
                    <tr>
                        <th>No. Factura</th>
                        <th>Serie</th>
                        <th>Fecha Factura</th>
                        <th>Id Cliente</th>
                        <th>Id Empleado</th>
                        <th>Fecha Ingreso</th>
                    </tr>
                </thead>
                <tbody id="tbl_ventas">
                    <%
                    Ventas ventas = new Ventas();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = ventas.leer();
                    for (int t=0;t<tabla.getRowCount();t++){
                        out.println("<tr data-id="+ tabla.getValueAt(t, 0) +">");
                        out.println("<td>"+ tabla.getValueAt(t, 1) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 3) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 4) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6) +"</td>");
                        out.println("</tr>");
                        
                        
                    }
                    %>
                </tbody>
            </table>

        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    </body>
</html>

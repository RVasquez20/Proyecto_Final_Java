<%-- 
    Document   : ventas
    Created on : 29/09/2020, 10:32:47 PM
    Author     : Willy Valle
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.lang.String"%>
<%@page import="Modelo.Ventas" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="JS/Ventas.js"></script>
    </head>
    <body>
        <h1>Ventana Ventas</h1>
          <button type="button" class="btn btn-info btn-lg"  onclick="LimpiarVentas()">Nuevo</button>
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

                <select name="txt_idcliente" id="txt_idcliente" class="form-control">
                    <% 
                        Ventas cliente = new Ventas();
                        HashMap<String,String> drop = cliente.ListaC();
                         Ventas cliente2 = new Ventas();
                        HashMap<String,String> drop2 = cliente2.ListaC2();
                        out.println("<option value='0'>Seleccione</option>");
                         for (String i:drop.keySet()){
                             out.println("<option value='" + i + "'>Nombre: " + drop.get(i) + "  NIT:  "+ drop2.get(i) +"</option>");
                         }
                         
                    
                    %>
                </select>
                <label for="lbl_idempleado">ID Empleado:</label>
                <input type="number" name="txt_idempleado" id="txt_idempleado" class="form-control" required>
                <label for="lbl_fechaingreso">Fecha Ingreso:</label>
                <input type="datetime" name="txt_fechaingreso" id="txt_fechaingreso" class="form-control" required>


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
                        <th>Nombre</th>
                        <th>NIT</th>
                        <th>Empleado</th>
                        <th>Fecha Ingreso</th>
                    </tr>
                </thead>
                <tbody id="tbl_ventas">
                    <%
                    Ventas ventas = new Ventas();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = ventas.leer();
                    for (int t=0;t<tabla.getRowCount();t++){
                        out.println("<tr data-id="+ tabla.getValueAt(t, 0) +" data-idc="+ tabla.getValueAt(t, 4) +" data-ide="+ tabla.getValueAt(t, 8) +">");
                        out.println("<td>"+ tabla.getValueAt(t, 1) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 3) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 7) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 9) +"</td>");
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
                    $('#tbl_ventas').on('click', 'tr td', function (evt) {
                        var target, id, nofactura, serie, fechafactura, idcliente, idempleado, fechaingreso;
                        target = $(evt.target);
                        id = target.parent().data('id');
                        idcliente = target.parent().data('idc');
                        nofactura = target.parent("tr").find("td").eq(0).html();
                        serie = target.parent("tr").find("td").eq(1).html();
                        fechafactura = target.parent("tr").find("td").eq(2).html();
                        
                        idempleado = target.parent("tr").find("td").eq(5).html();
                        fechaingreso = target.parent("tr").find("td").eq(6).html();

                        $("#txt_id").val(id);
                        $("#txt_nofactura").val(nofactura);
                        $("#txt_serie").val(serie);
                        $("#txt_fechafactura").val(fechafactura);
                        $("#txt_idcliente").val(idcliente);
                        $("#txt_idempleado").val(idempleado);
                        $("#txt_fechaingreso").val(fechaingreso);

                    }
                    );

        </script>
        
    </body>
</html>

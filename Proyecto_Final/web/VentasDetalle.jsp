<%-- 
    Document   : VentasDetalle
    Created on : 4/10/2020, 01:04:24 PM
    Author     : rodri
--%>

<%@page import="Modelo.productos"%>
<%@page import="Modelo.Empleado"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Ventas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <title>Ventas Detalle</title>
    </head>
    <body>
        <div class="container">
   <form action="sr_VentasDetalle" method="post" class="form-group">
       <label for="lbl_id_Ventas">ID:</label>
                <input type="text" name="txt_id_Ventas" id="txt_id_Ventas" class="form-control" value ="0" readonly>
                <label for="lbl_Producto" ><b>Producto</b></label>
                <select name="drop_Producto" id="drop_Producto" class="form-control">
                    <% 
                        productos producto = new productos();
                        HashMap<String,String> lista = producto.ListaProductos();
                        out.println("<option value='0'>Seleccione</option>");
                         for (String i:lista.keySet()){
                             out.println("<option value='" + i + "'>" + lista.get(i) + "</option>");
                         }
                         
                    
                    %>
                </select>
                <label for="lbl_Cantidad" ><b>Cantidad</b></label>
                <input type="number"  name="txt_Cantidad" id="txt_Cantidad" class="form-control" required>
                 <label for="lbl_PrecioUnitario" ><b>Precio Unitario</b></label>
                <input type="money"  name="txt_PrecioUnitario" id="txt_PrecioUnitario" class="form-control" required>
                <br>
               
                <!--ventas-->
       
                <label for="lbl_idventas">ID:</label>
                <input type="text" name="txt_idventas" id="txt_idventas" class="form-control" value ="0" readonly>
                <label for="lbl_nofactura">No.Factura:</label>
                <input type="number" name="txt_nofactura" id="txt_nofactura" class="form-control" required>
                <label for="lbl_serie">Serie:</label>
                <input type="text" name="txt_serie" id="txt_serie" class="form-control" required>
                <label for="lbl_fechafactura">Fecha Factura:</label>
                <input type="date" name="txt_fechafactura" id="txt_fechafactura" class="form-control" required>
                <label for="lbl_idcliente">Cliente:</label>

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
                <label for="lbl_idempleado">Empleado:</label>
                 <select name="txt_idempleado" id="txt_idempleado" class="form-control">
                <%
                Empleado empleado=new Empleado();
               HashMap<String,String> dropempleados = empleado.ListaEmpleados();
                 out.println("<option value='0'>Seleccione</option>");
                         for (String i:dropempleados.keySet()){
                             out.println("<option value='" + i + "'>" + dropempleados.get(i) +"</option>");
                         }
                %>
                 </select>
                <label for="lbl_fechaingreso">Fecha Ingreso:</label>
                <input type="datetime" name="txt_fechaingreso" id="txt_fechaingreso" class="form-control" required>


                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger">Eliminar</button>

            </form>
                
                
                
                
              <!--Ventas-->
                 <table class="table table-striped">
                <thead>
                    <tr>
                        <th>No. Factura</th>
                        <th>Serie</th>
                        <th>Fecha Factura</th>
                        <th>Nombre</th>
                        <th>NIT</th>
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
                        out.println("<tr data-id="+ tabla.getValueAt(t, 0) +" data-idc="+ tabla.getValueAt(t, 4) +">");
                        out.println("<td>"+ tabla.getValueAt(t, 1) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 3) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 7) +"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 8) +"</td>");
                        out.println("</tr>");
                        
                        
                    }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>

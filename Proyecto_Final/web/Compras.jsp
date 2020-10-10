<%-- 
    Document   : Compras
    Created on : 29/09/2020, 11:50:11 PM
    Author     : josef
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
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Proveedores"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.Compras"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="JS/Compras.js"></script>
        <title>JSP Page</title>
    </head> 
    <body>    
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
        <h1>Compras</h1>
        <button type="button" class="btn btn-info btn-lg"  onclick="LimpiarCompras()">Nuevo</button>
        <div class="container">
         <form action="sr_Compras" method="POST" class="form-group">
           <label>ID:</label>
           <input type="text" name="txt_id_Compra" id="txt_id_Compra" class="form-control" value="0"  readonly>
           <label for="lbl_No_Orden" ><b>Numero de Orden</b></label>
           <input type="text" name="txt_No_Orden" id="txt_No_Orden" class="form-control" placeholder="Ejemplo: 120" required>
               <label for="lbl_Proveedor" ><b>Proveedor</b></label>
                <select name="ListaProveedores" id="ListaProveedores" class="form-control">
                    <% 
                        Proveedores proveedor = new Proveedores();
                        HashMap<String,String> drop = proveedor.ListaProveedor();
                        out.println("<option value='0'>Seleccione</option>");
                         for (String i:drop.keySet()){
                             out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                         }
                         
                    
                    %>
                </select>
                <br>
           <label for="lbl_Fecha_Orden" ><b>Fecha_Orden</b></label>
           <input type="Date" name="txt_Fecha_Orden" id="txt_Fecha_Orden" class="form-control" placeholder="Ejemplo:12/12/2020" required>
           <label for="lbl_Fecha_Ingreso" ><b>Fecha De Ingreso</b></label>
           <input type="DateTime" name="txt_Fecha_Ingreso" id="txt_Fecha_Ingreso" class="form-control" placeholder="Ejemplo: 12/12/2000 2:2:2" " required>
           
           <br>
 <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
  </form>
    </div>
        <br>
        
                <br> <a href="Compras.jsp">Compras</a>
                <a href="index.jsp">Regresar</a>
                <br>
                <br>
                <div class="container">
                           
           
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Numero De Orden</th>
        <th>Proveedor</th>
        <th>Fecha de Orden</th>
        <th>Fecha De Ingreso</th>
      </tr>
    </thead>
    <tbody id="tbl_Compras">
        <% 
       Compras Compra=new Compras();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = Compra.ListaDeCompras();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t,0)+" data-idp="+tabla.getValueAt(t,2)+ ">");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("</tr>");
        
        }
        %>
      
    </tbody>
  </table>
                    
                </div>
        <script type="text/javascript">
         $('#tbl_Compras').on('click','tr td',function(evt){
       var target,id,NumeroDeOrden,FechaDeOrden,FechaDeIngreso,idProveedor; 
       target = $(event.target);
       id = target.parent().data('id'); 
        idProveedor = target.parent().data('idp'); 
        NumeroDeOrden = target.parent("tr").find("td").eq(0).html();
       FechaDeOrden = target.parent("tr").find("td").eq(2).html();
       FechaDeIngreso= target.parent("tr").find("td").eq(3).html();
       

       $("#txt_id_Compra").val(id);
       $("#ListaProveedores").val(idProveedor);
       $("#txt_No_Orden").val(NumeroDeOrden);
       $("#txt_Fecha_Orden").val(FechaDeOrden);
       $("#txt_Fecha_Ingreso").val(FechaDeIngreso);
 
    });
    </script>
    </body>
</html>

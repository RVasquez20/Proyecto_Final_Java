<%-- 
    Document   : VentasDetalle
    Created on : 4/10/2020, 01:04:24 PM
    Author     : rodri
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
<%@page import="Modelo.VentasDetalle"%>
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
<script src="JS/AllInOne.js"></script>
        <title>Ventas Detalle</title>
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
                <input type="number"  name="txt_Cantidad" id="txt_Cantidad" class="form-control"  onkeypress="return entero(event);" required>
                 <label for="lbl_PrecioUnitario" ><b>Precio Unitario</b></label>
                <input type="money"  name="txt_PrecioUnitario" id="txt_PrecioUnitario" class="form-control" onkeypress="return decimal(event);" required>
                <br>
               
                <!--ventas-->
       
                <label for="lbl_idventas">ID:</label>
                <input type="text" name="txt_idventas" id="txt_idventas" class="form-control" value ="0" readonly>
                <label for="lbl_nofactura">No.Factura:</label>
                <input type="number" name="txt_nofactura" id="txt_nofactura" class="form-control"  onkeypress="return entero(event);" required>
                <label for="lbl_serie">Serie:</label>
                <input type="text" name="txt_serie" id="txt_serie" class="form-control" maxlength="1" required>
                <label id="lbl_fechafactura">Fecha Factura:</label>
                <input type="date" name="txt_fechafactura" id="txt_fechafactura" value="0" class="form-control" required>
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
                <label id="lbl_fechaingreso">Fecha Ingreso:</label>
                <input type="datetime" name="txt_fechaingreso" id="txt_fechaingreso" value="0" class="form-control" required>


                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>

            </form>
                
                
                <!--Ventas Detalle-->
                 <table class="table table-striped">
                <thead>
                    <tr>
                        <th>No. Factura</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                    </tr>
                </thead>
                <tbody id="tbl_ventasDetalle">
                    <%
                    VentasDetalle vd = new VentasDetalle();
                    DefaultTableModel tabladv = new DefaultTableModel();
                    tabladv = vd.ListaDeVentasDetalle();
                    for (int t=0;t<tabladv.getRowCount();t++){
                        out.println("<tr data-idvd="+ tabladv.getValueAt(t, 0) +" data-idv="+ tabladv.getValueAt(t, 1) +" data-idpr="+ tabladv.getValueAt(t, 3) +">");
                        out.println("<td>"+ tabladv.getValueAt(t, 2) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 4) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 5) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 6) +"</td>");
                        out.println("</tr>");
                        
                        
                    }
                    %>
                </tbody>
            </table>
              <!--Ventas-->
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
                 <script type="text/javascript">
            $(document).ready(function () {
            $("#lbl_fechafactura").hide();
                        $("#txt_fechafactura").hide();
                                 $("#lbl_fechaingreso").hide();
                        $("#txt_fechaingreso").hide();
                        $("#btn_agregar").show();
                        $("#btn_modificar").hide();
                        $("#btn_eliminar").hide();
    });
            
            </script>
                    <script type="text/javascript">
    $('#tbl_ventasDetalle').on('click','tr td',function(evt){
       var target,idvd,idv,idpr,noFac,producto,precio,cantidad; 
       $("#lbl_fechafactura").show();
                        $("#txt_fechafactura").show();
                                 $("#lbl_fechaingreso").show();
                        $("#txt_fechaingreso").show();
                        $("#btn_agregar").hide();
                        $("#btn_modificar").showv();
                        $("#btn_eliminar").show();
       target = $(event.target);
       idvd = target.parent().data('idvd');
       idv = target.parent().data('idv'); 
       idpr = target.parent().data('idpr'); 
       noFac= target.parent("tr").find("td").eq(0).html();
       producto = target.parent("tr").find("td").eq(1).html();
       cantidad=target.parent("tr").find("td").eq(2).html();
       precio = target.parent("tr").find("td").eq(3).html();
       
       $("#txt_id_Ventas").val(idvd);
        $("#txt_idventas").val(idv);
         $("#drop_Producto").val(idpr);
       $("#txt_PrecioUnitario").val(precio);
       $("#txt_Cantidad").val(cantidad);
       
       
    });
    
</script>
  <script type="text/javascript">
         $('#tbl_ventas').on('click', 'tr td', function (evt) {
                        var target, id, nofactura, serie, fechafactura, idcliente, idempleado, fechaingreso;
                        target = $(evt.target);
                        id = target.parent().data('id');
                        idcliente = target.parent().data('idc');
                        idempleado = target.parent().data('ide');
                        nofactura = target.parent("tr").find("td").eq(0).html();
                        serie = target.parent("tr").find("td").eq(1).html();
                        fechafactura = target.parent("tr").find("td").eq(2).html();
                        
                        
                        fechaingreso = target.parent("tr").find("td").eq(6).html();

                        $("#txt_idventas").val(id);
                        $("#txt_nofactura").val(nofactura);
                        $("#txt_serie").val(serie);
                        $("#txt_fechafactura").val(fechafactura);
                        $("#txt_idcliente").val(idcliente);
                        $("#txt_idempleado").val(idempleado);
                        $("#txt_fechaingreso").val(fechaingreso);

 
    });
    </script>
        
    </body>
</html>
<%
   }
else{
response.sendRedirect("index.jsp");

}
%>
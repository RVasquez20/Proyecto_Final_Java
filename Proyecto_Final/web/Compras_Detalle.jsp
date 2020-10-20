<%-- 
    Document   : Compras_Detalle
    Created on : 2/10/2020, 03:56:40 PM
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
<%@page import="Modelo.Proveedores"%>
<%@page import="Modelo.ComprasDetalle"%>
<%@page import="javax.swing.table.DefaultTableModel"%>  
<%@page import="Modelo.productos"%>
<%@page import="Modelo.Compras"%>
<%@page import="java.util.HashMap"%>
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
        <script>
        $(document).ready(function () {
            
              $("#btn_modificar").hide();
       $("#btn_eliminar").hide();
       $("#btn_agregar").show();
        });
    </script>
        <title>Compras Detalle</title>
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
            <h1>Compras Detalle</h1>
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Listado</button>
       <form action="sr_ComprasDetalle" method="POST" class="form-group">
               <label for="lbl_id" ><b>ID</b></label>
               <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly><br>
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
                <h1>Compras:</h1>
           <label>ID:</label>
           <input type="text" name="txt_id_Compra" id="txt_id_Compra" class="form-control" value="0"  readonly>
           <label for="lbl_No_Orden" ><b>Numero de Orden</b></label>
           <input type="text" name="txt_No_Orden" id="txt_No_Orden" class="form-control" placeholder="Ejemplo: 120"  onkeypress="return entero(event);" required>
               <label for="lbl_Proveedor" ><b>Proveedor</b></label>
                <select name="ListaProveedores" id="ListaProveedores" class="form-control">
                    <% 
                        Proveedores proveedor = new Proveedores();
                        HashMap<String,String> drop2 = proveedor.ListaProveedor();
                        out.println("<option value='0'>Seleccione</option>");
                         for (String i:drop2.keySet()){
                             out.println("<option value='" + i + "'>" + drop2.get(i) + "</option>");
                         }
                         
                    
                    %>
                </select>
                <br>
           <label for="lbl_Fecha_Orden" ><b>Fecha_Orden</b></label>
           <input type="Date" name="txt_Fecha_Orden" id="txt_Fecha_Orden" class="form-control" placeholder="Ejemplo:12/12/2020" required>
           <label id="lbl_Fecha_Ingreso" ><b>Fecha De Ingreso</b></label>
           <input type="DateTime" name="txt_Fecha_Ingreso" id="txt_Fecha_Ingreso" class="form-control" placeholder="Ejemplo: 12/12/2000 2:2:2" value="0" required>
           
           <br>
 <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
  
    
                
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
        <h4 class="modal-title text-center">Lasta de las Compras y sus Detalles </h4>
 <form class="mr-sm-2">
                            <input class="form-control" id="myInput" type="text" placeholder="Buscar">
                            <br><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo"><svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-info-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                <path d="M8.93 6.588l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588z"/>
                                <circle cx="8" cy="4.5" r="1"/>
                                </svg>&ensp;Ayuda</button>
                            <div id="demo" class="collapse">
                                <br><b>Esta busqueda esta basada en cada tipo de columna de la tabla
                                Si desea regresar a la lista completa de empleados solo debe borrar lo
                                    buscado :D.</b>

                            </div>
                        </form>
      </div>

      <!-- Modal body -->
      <div class="modal-body text-center">

         <table class="table table-dark table-hover text-center">
    <thead>
      <tr>
        <th>No. Orden</th>
        <th>Productos</th>
        <th>Cantidad</th>
        <th>Precio Costo Unitario</th>
        <th>Proveedor</th>
        <th>Fecha De Orden</th>
        <th>Fecha De Ingreso</th>
      </tr>
      </thead>
      <tbody id="tbl_CompraDetalle">
        <% 
        ComprasDetalle Detalle = new ComprasDetalle();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = Detalle.ListaDeComprasDetalle();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t,0) + " data-idc=" + tabla.getValueAt(t,1) +" data-idp=" + tabla.getValueAt(t,3) +" data-idx="+ tabla.getValueAt(t,7) + ">");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,8) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,9) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,10) + "</td>");
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
            $("#lbl_Fecha_Ingreso").hide();
                        $("#txt_Fecha_Ingreso").hide();
                        $("#btn_agregar").show();
                        $("#btn_modificar").hide();
                        $("#btn_eliminar").hide();
    });
            
            </script>
         <script type="text/javascript">
    $('#tbl_CompraDetalle').on('click','tr td',function(evt){
       var target,id,idc,idp,precio,cantidad,idc,NumeroDeOrden,FechaDeOrden,FechaDeIngreso,idProveedor; ; 
       target = $(event.target);
       $("#lbl_Fecha_Ingreso").show();
                        $("#txt_Fecha_Ingreso").show();
                        $("#btn_agregar").hide();
                        $("#btn_modificar").show();
                        $("#btn_eliminar").show();
       id = target.parent().data('id');
       idc = target.parent().data('idc'); 
       idp = target.parent().data('idp'); 
        idProveedor = target.parent().data('idx'); 
        NumeroDeOrden = target.parent("tr").find("td").eq(0).html();
       FechaDeOrden = target.parent("tr").find("td").eq(5).html();
       FechaDeIngreso= target.parent("tr").find("td").eq(6).html();
       cantidad=target.parent("tr").find("td").eq(2).html();
       precio = target.parent("tr").find("td").eq(3).html();
       
       $("#txt_id").val(id);

         $("#drop_Producto").val(idp);
       $("#txt_PrecioUnitario").val(precio);
       $("#txt_Cantidad").val(cantidad);
       
       $("#txt_id_Compra").val(idc);
       $("#ListaProveedores").val(idProveedor);
       $("#txt_No_Orden").val(NumeroDeOrden);
       $("#txt_Fecha_Orden").val(FechaDeOrden);
       $("#txt_Fecha_Ingreso").val(FechaDeIngreso);
           $('#myModal').modal('hide');
       
    });
    
</script>
  
        <script>
        $(document).ready(function () {
            
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#tbl_CompraDetalle tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
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
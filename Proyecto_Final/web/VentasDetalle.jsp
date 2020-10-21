<%-- 
    Document   : VentasDetalle
    Created on : 4/10/2020, 01:04:24 PM
    Author     : rodri
--%>
<%
    HttpSession actual = request.getSession(true);
    String usuario = (String) actual.getAttribute("Logueado");
    String nombres = (String) actual.getAttribute("nom");
    String email = (String) actual.getAttribute("emai");
    String profile = (String) actual.getAttribute("Ft");
    String Marca = (String) actual.getAttribute("mar");
    String Produ = (String) actual.getAttribute("pr");
    String client = (String) actual.getAttribute("cl");
    String compdet = (String) actual.getAttribute("cd");
    String Empl = (String) actual.getAttribute("em");
    String tipo = (String) actual.getAttribute("T");
    String Prove = (String) actual.getAttribute("pro");
    String Puest = (String) actual.getAttribute("pues");
    String Vendde = (String) actual.getAttribute("vende");
    String nuevo = (String) actual.getAttribute("nu");
    session.setMaxInactiveInterval(900);
    if ((actual.getAttribute("Logueado") != null) && ((tipo.equals("ADMIN")||Vendde!=null))) {
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
        <title>Ventas Detalle</title>
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
                        <h1>Formulario de Ventas</h1>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Listado</button>
                        <button type="button" name="btn_nuevoc" id="btn_nuevoc" class="btn btn-info btn-lg"  onclick="LimpiarVentasDetalle();">Nuevo</button>
                        
   <form action="sr_VentasDetalle" method="post" class="form-group">
       <label for="lbl_id_Ventas">ID:</label>
                <input type="text" name="txt_id_Ventas" id="txt_id_Ventas" class="form-control" value ="0" readonly>
                <label for="lbl_Producto" ><b>Producto</b></label>
                <select name="drop_Producto" id="drop_Producto" class="form-control" required="">
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
                <input type="text" name="txt_fechafactura" id="txt_fechafactura" value="0" class="form-control" required>
                <label for="lbl_idcliente">Cliente:</label>

                <select name="txt_idcliente" id="txt_idcliente" class="form-control">
                    <% 
                        Ventas cliente = new Ventas();
                        HashMap<String,String> drop = cliente.ListaC();
                         Ventas cliente2 = new Ventas();
                        HashMap<String,String> drop2 = cliente2.ListaC2();
                        out.println("<option value='0'>Seleccione</option>");
                         for (String i:drop.keySet()){
                             out.println("<option value='" + i + "'>Nombre: " + drop.get(i) + "         NIT:  "+ drop2.get(i) +"</option>");
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
                <input type="text" name="txt_fechaingreso" id="txt_fechaingreso" value="0" class="form-control" required>


                <br>
                
      
                 <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-success btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                
            </form>
                
               </div>
                </div>
                <nav>
                    <ul>
                        <%

                           if(Marca!=null){
                                out.println("<li><a href='Marcas.jsp'>Marcas</a></li><br>");
                           }
                           if(Produ!=null){
                                out.println("<li><a href='Productos.jsp'>Productos</a></li><br>");
                           }
                           if(client!=null){
                                out.println("<li><a href='clientes.jsp'>Clientes</a></li><br>");
                           }
                           if(compdet!=null){
                                out.println("<li><a href='Compras_Detalle.jsp'>Compras Detalle</a></li><br>");
                           }
                           if(Empl!=null){
                                out.println("<li><a href='Empleados.jsp'>Empleados</a></li><br>");
                           }
                           if(Prove!=null){
                                out.println("<li><a href='Proveedores.jsp'>Proveedores</a></li><br>");
                           }
                           if(Puest!=null){
                                out.println("<li><a href='Puestos.jsp'>Puestos</a></li><br>");
                           }
                           if(Vendde!=null){
                                out.println("<li><a href='VentasDetalle.jsp'>Ventas Detalle</a></li><br>");
                           }
 if(nuevo!=null){
                                out.println("<li><a href='NuevoAdmin.jsp'>Nuevo Admin</a></li><br>");
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
        <h4 class="modal-title text-center">Lasta de las Ventas y sus detalles </h4>
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
                        <th>No. Factura</th>
                        <th>Serie</th>
                        <th>Fecha Factura</th>
                        <th>Nombre Cliente</th>
                        <th>Nit</th>
                        <th>Nombre Empleado</th>
                        <th>Fecha Ingreso</th>
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
                        out.println("<tr data-idvd="+ tabladv.getValueAt(t, 0) +" data-idv="+ tabladv.getValueAt(t, 1) +" data-idcl="+ tabladv.getValueAt(t, 5) +" data-ide="+ tabladv.getValueAt(t, 9) +" data-idp="+ tabladv.getValueAt(t, 11) +">");
                        out.println("<td>"+ tabladv.getValueAt(t, 2) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 3) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 4) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 6) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 7) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 8) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 10) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 12) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 13) +"</td>");
                        out.println("<td>"+ tabladv.getValueAt(t, 14) +"</td>");

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
       var target,idvd,idv,idc,idpr,idem,precio,cantidad; 
       
       $("#lbl_fechafactura").show();
                        $("#txt_fechafactura").show();
                                 $("#lbl_fechaingreso").show();
                        $("#txt_fechaingreso").show();
                        $("#btn_agregar").hide();
                        $("#btn_modificar").show();
                        $("#btn_eliminar").show();
       target = $(event.target);
       idvd = target.parent().data('idvd');
       idv = target.parent().data('idv'); 
       idpr = target.parent().data('idp'); 
       idc = target.parent().data('idcl');
       idem = target.parent().data('ide');
       nofactura = target.parent("tr").find("td").eq(0).html();
       serie = target.parent("tr").find("td").eq(1).html();
       fechafactura = target.parent("tr").find("td").eq(2).html();
       fechaingreso = target.parent("tr").find("td").eq(6).html();
       cantidad=target.parent("tr").find("td").eq(8).html();
       precio = target.parent("tr").find("td").eq(9).html();
       $("#txt_id_Ventas").val(idvd);
       $("#txt_idventas").val(idv);
       $("#drop_Producto").val(idpr);
       $("#txt_PrecioUnitario").val(precio);
       $("#txt_Cantidad").val(cantidad);
       $("#txt_nofactura").val(nofactura);
       $("#txt_serie").val(serie);
       $("#txt_fechafactura").val(fechafactura);
       $("#txt_idcliente").val(idc);
       $("#txt_idempleado").val(idem);
       $("#txt_fechaingreso").val(fechaingreso);
         $('#myModal').modal('hide');
    });
    
</script>
 
        <script>
        $(document).ready(function () {
            
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#tbl_ventasDetalle tr").filter(function () {
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
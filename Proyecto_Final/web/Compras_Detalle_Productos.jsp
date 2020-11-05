<%-- 
    Document   : Compras_Detalle_Productos
    Created on : 31/10/2020, 10:54:27 PM
    Author     : rodri
--%>
<%@page import="Modelo.productos"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Proveedores"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.ComprasDetalle"%>
<%
    HttpSession actual = request.getSession(true);
    String usuario = (String) actual.getAttribute("Logueado");
    String nombres = (String) actual.getAttribute("nom");
    String email = (String) actual.getAttribute("emai");
    String profile = (String) actual.getAttribute("Ft");
    String Marca = (String) actual.getAttribute("mar");
         String tipo = (String) actual.getAttribute("T");
    String Produ = (String) actual.getAttribute("pr");
    String client = (String) actual.getAttribute("cl");
    String compdet = (String) actual.getAttribute("cd");
    String Empl = (String) actual.getAttribute("em");
    String Prove = (String) actual.getAttribute("pro");
    String Puest = (String) actual.getAttribute("pues");
    String Vendde = (String) actual.getAttribute("vende");
     String nuevo = (String) actual.getAttribute("nu");
         Integer as=(Integer)request.getAttribute("q");
    session.setMaxInactiveInterval(900);
     if ((actual.getAttribute("Logueado") != null) && ((tipo.equals("ADMIN")||compdet!=null))&&(as!=null)) {
%>
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
            
              $("#btn_modificarp").hide();
       $("#btn_eliminarp").hide();
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
          <button type="button" name="btn_nuevoc" id="btn_nuevoc" class="btn btn-info btn-lg"  onclick="LimpiarComprasDetalle();">Nuevo</button>
          
       <form action="sr_ComprasDetalle" method="POST" class="form-group">
           <input type="hidden" name="txt_id_Compra" id="txt_id_Compra" class="form-control" value =<%=as%> readonly>   
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
                
           <br>
   <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-success btn-lg">Agregar Producto</button>
                 <button name="btn_fin" id="btn_fin"  value="fin" class="btn btn-success btn-lg" onclick="finaliza();">Finalizar Compra</button>
                <button name="btn_modificarp" id="btn_modificarp"  value="modificarp" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminarp" id="btn_eliminarp"  value="eliminarp" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                
                
            </form>
       </div>
                             <table>
                         <thead>
                             <tr>
                                 <th>No.</th>
                                 <th>Producto</th>
                                 <th>Cantidad</th>
                                 <th>Precio unitario</th>
                                 <th>Subtotal</th>
                             </tr>
                         </thead>
                         <tbody id="tbl_comprasp" style="color:white;">
                        <%
                        ComprasDetalle lp = new ComprasDetalle();
                        DefaultTableModel tablalp = new DefaultTableModel();
                        tablalp = lp.ListaDeProductos(as);
                        for (int t=0;t<tablalp.getRowCount();t++){
                            out.println("<tr data-idp="+ tablalp.getValueAt(t, 0) +" data-idcd="+ tablalp.getValueAt(t, 1) +">");
                            out.println("<td>"+(t+1)+"</td>");
                            out.println("<td>"+ tablalp.getValueAt(t, 2) +"</td>");
                            out.println("<td>"+ tablalp.getValueAt(t, 3) +"</td>");
                            out.println("<td>"+ tablalp.getValueAt(t, 4) +"</td>");
                            out.println("<td>"+ tablalp.getValueAt(t, 5) +"</td>");
                            out.println("</tr>");
                        
                        
                    }
                    %>
                         </tbody>
                     </table>
               
                        
                        <%
                        ComprasDetalle t = new ComprasDetalle();
                        Double tot=t.Total(as);
                        out.println("<input type='decimal'  name='total' id='total' class='form-control' value='"+tot+"'>");
                    %>
                     
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

	
                    <!-- BG -->
			<div id="bg"></div>
    
                    
		<!-- Scripts -->
			
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
                            <script type="text/javascript">
            function finaliza() {
         document.getElementById("txt_Cantidad").required = false;
         document.getElementById("txt_PrecioUnitario").required = false;
         document.getElementById("drop_Producto").required = false;
    };
            
            </script>
         <script type="text/javascript">
            $(document).ready(function () {

                        $("#btn_agregarp").show();
                        $("#btn_modificarp").hide();
                        $("#btn_eliminarp").hide();
    });
            
            </script>
         <script type="text/javascript">
    $('#tbl_comprasp').on('click','tr td',function(evt){
       var target,idc,idp,precio,cantidad,idc; 
       target = $(event.target);

                        $("#btn_agregar").hide();
                        $("#btn_modificarp").show();
                        $("#btn_eliminarp").show();
       idc = target.parent().data('idcd'); 
       idp = target.parent().data('idp'); 
       cantidad=target.parent("tr").find("td").eq(2).html();
       precio = target.parent("tr").find("td").eq(3).html();
       

$("#txt_id").val(idc);
         $("#drop_Producto").val(idp);
       $("#txt_PrecioUnitario").val(precio);
       $("#txt_Cantidad").val(cantidad);

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
response.sendRedirect("ErrorP.jsp");

}
%>
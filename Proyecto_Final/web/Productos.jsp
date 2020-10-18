<%-- 
    Document   : index
    Created on : 18/09/2020, 10:43:41 PM
    Author     : cindy
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
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.marcas"%>
<%@page import="Modelo.productos"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- <-% 
String variable = (String)request.getAttribute("txt_locale");

%-> -->
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
 <script Language="JavaScript">

<!-- Helpers for JSI page...

// Navigation - Start

function goback(){

alert("Good Bye!");

history.go(-1);

}

function gettheDate() {

Todays = new Date();

TheDate = "" + (Todays.getMonth()+ 1) +" / "+ Todays.getDate() + " / " + (Todays.getYear() + 1900) 

document.clock.thedate.value = TheDate;

}

// Navigation - Stop

// Netscapes Clock - Start

// this code was taken from Netscapes JavaScript documentation at

// www.netscape.com on Jan.25.96



var timerID = null;

var timerRunning = false;

function stopclock (){

	if(timerRunning)

		clearTimeout(timerID);

	timerRunning = false;

}



function startclock () {

	// Make sure the clock is stopped

	stopclock();

	gettheDate()

	showtime();

}



function showtime () {

	var now = new Date();

	var hours = now.getHours();

	var minutes = now.getMinutes();

	var seconds = now.getSeconds()

	var timeValue = "" + ((hours >12) ? hours -12 :hours)

	timeValue += ((minutes < 10) ? ":0" : ":") + minutes

	timeValue += ((seconds < 10) ? ":0" : ":") + seconds

	timeValue += (hours >= 12) ? " P.M." : " A.M."

	document.clock.face.value = timeValue;

	// you could replace the above with this

	// and have a clock on the status bar:

	// window.status = timeValue;

	timerID = setTimeout("showtime()",1000);

	timerRunning = true;

}

// Netscapes Clock - Stop



// end Helpers -->

</script>

        <title>Productos</title>
    </head>
    
    <body class="is-preload" onload="startclock()">
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
                    <span ><img src="CSS/Logos/eme.svg" style="max-width: 60px; max-height: 60px;margin-right:5px; "></span>
                </div>
                <div class="content">
                    <div class="inner">
        
            <br><h1>Listado De Productos</h1>
       
         





 <form name="clock" onSubmit="0">






   Hoy es: <input type="text" name="thedate" size=12

           value="" readonly>

   La hora es: <input type="text" name="face" size=12

           value="" readonly>
</form>

  <br>

 
         <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Listado</button>
            <form action="sr_productos" method="POST" class="form-group" enctype="multipart/form-data" class="form-horizontal" role="form" name="formulario">
                  
                <label><b>id</b></label>
                <input type="text" name="txt_id" class="form-control" id="txt_id" placeholder="id" value="0" readonly style="max-width: 250px;"><br>
                <label><b>Producto</b></label>
                <input type="text" name="txt_producto" class="form-control" id="txt_producto" placeholder="Ejemplo:Pan" onkeypress="return text(event);" required><br>
                <label><b>Marca</b></label>
                <select name="box_marcas" id="box_marcas" class="form-control">
                    <%
                    marcas marca=new marcas();
                    HashMap<String,String> combo=marca.box_marcas();
                    out.println("<option value='0'>Seleccione</option>");
                    for (String i: combo.keySet()) {
                            out.println("<option value='"+i+"'>"+combo.get(i)+"</option>");
                        }
                    %> 
                </select><br>
                <label><b>descripcion</b></label>
                <input type="text" name="txt_descripcion" class="form-control" id="txt_descripcion" placeholder="Ejemplo:Pan blanco" 
 onkeypress="return text(event);" required><br>
                
                <label><b>Imagen</b></label>
                 <input type="file" id="imagen" name="archivo" class="col-md-8 btn" onchange="cargarArchivo(this)">
                 <input type="hidden" id="imagenes" name="imagenes"/>
                                <div class="clear"></div>
                 <label><b>Precio Costo</b></label>
                <input type="money" name="txt_preciocosto" class="form-control" id="txt_preciocosto" placeholder="Ejemplo:12.20"  onkeypress="return decimal(event);" required><br>
                 <label><b>Precio Venta</b></label>
                <input type="money" name="txt_precioventa" class="form-control" id="txt_precioventa" placeholder="Ejemplo:12.20"  onkeypress="return decimal(event);" required><br>
                  <label><b>Existencias</b></label>
                <input type="number" name="txt_exitencias" class="form-control" id="txt_exitencias" placeholder="Ejemplo:20" onkeypress="return entero(event);" required><br>
                <label id="lbl_fecha"><b>Fecha de Ingreso</b></label>
                <input type="datetime" name="txt_FechaIngreso" class="form-control" id="txt_FechaIngreso" value="0" placeholder="Ejemplo:20" required><br>
               
            <button type="submit" class="btn btn-primary" name="btn_agregar" id="btn_agregar" value="agregar" data-text-loading="Loading..." >Agregar</button>
            <button type="submit" class="btn btn-primary" name="btn_actualizar" id="btn_actualizar" value="modificarguardimagen">Modificar Con misma imagen</button>
            <button type="submit" class="btn btn-primary" name="btn_modificar" id="btn_modificar" value="modificar">Modificar Con distinta imagen</button>
            <button type="submit" class="btn btn-primary" name="btn_eliminar" id="btn_eliminar" value="eliminar" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
       <input type="hidden" name="nombre" id="file">
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
                        <!--<li><a href="#elements">Elements</a></li>-->
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
        <h4 class="modal-title text-center">Lasta de las Marcas </h4>
 
      </div>

      <!-- Modal body -->
      <div class="modal-body text-center">

         <table class="table table-dark table-hover text-center">
    <thead>
      <tr>
        <th>Producto</th>
        <th>Marca</th>
        <th>Descripcion</th>
        <th>Imagen</th>
        <th>Precio Costo</th>
        <th>Precio Venta</th>
        <th>Existencias</th>
        <th>Fecha De Ingreso</th>
      </tr>
    </thead>
    <tbody id="tbl_productos">

        <%
        DefaultTableModel tblTabla=new DefaultTableModel();
        productos producto=new productos();
        tblTabla=producto.Lista();
        for (int i = 0; i < tblTabla.getRowCount(); i++) {
                out.println("<tr data-id_productos="+tblTabla.getValueAt(i, 0)+" data-id_marcas="+tblTabla.getValueAt(i, 9)+" data-idimagen="+ tblTabla.getValueAt(i, 4) +">");
                out.println("<td>"+tblTabla.getValueAt(i, 1)+"</td>");
                out.println("<td>"+tblTabla.getValueAt(i, 2)+"</td>");
                out.println("<td>"+tblTabla.getValueAt(i, 3)+"</td>");
                out.println("<td><img src='upload/"+tblTabla.getValueAt(i, 4)+"' style='width:100px; height:100px; cursor:pointer' value="+tblTabla.getValueAt(i, 4)+" title="+tblTabla.getValueAt(i, 4)+"></td>");
                out.println("<td>"+tblTabla.getValueAt(i, 5)+"</td>");
                out.println("<td>"+tblTabla.getValueAt(i, 6)+"</td>");
                out.println("<td>"+tblTabla.getValueAt(i, 7)+"</td>");
                out.println("<td>"+tblTabla.getValueAt(i, 8)+"</td>");
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
            $("#lbl_fecha").hide();
                        $("#txt_FechaIngreso").hide();
                        $("#btn_agregar").show();
                        $("#btn_modificar").hide();
                        $("#btn_actualizar").hide();
                        $("#btn_eliminar").hide();
    });
            
            </script>
         <script  type="text/javascript">   
$('#tbl_productos').on('click','tr td', function(evt){
 
   var target,idproducto,idmarcas,descripcion,precio_costo,precio_venta,existencias,producto,fechaingreso,imagen;
   target = $(event.target);
    $("#lbl_fecha").show();
                        $("#txt_FechaIngreso").show();
                        $("#btn_agregar").hide();
                        $("#btn_modificar").show();
                        $("#btn_eliminar").show();
                         $("#btn_actualizar").show();
   idproducto = target.parent().data('id_productos');
  idmarcas = target.parent().data('id_marcas');
   producto=target.parents("tr").find("td").eq(0).html();
   descripcion=target.parents("tr").find("td").eq(2).html();
   precio_venta=target.parents("tr").find("td").eq(5).html();
  precio_costo= target.parents("tr").find("td").eq(4).html();
  existencias= target.parents("tr").find("td").eq(6).html();
   fechaingreso=target.parents("tr").find("td").eq(7).html();
   imagen= target.parents().data('idimagen');
   $("#txt_id").val(idproducto);
    $("#box_marcas").val(idmarcas);
   $("#txt_producto").val(producto);
   $("#txt_descripcion").val(descripcion);
   $("#txt_preciocosto").val(precio_costo);
   $("#txt_precioventa").val(precio_venta);
   $("#txt_exitencias").val(existencias);
   $("#txt_FechaIngreso").val(fechaingreso);
   $("#imagenes").val(imagen);
   
          $('#myModal').modal('hide');

  
  
   

   






   
});
</script>
<script type="text/javascript">
      $('#nuevo').click (function(evt){
    $("#txt_id").val('');
    $("#box_marcas").val('');
   $("#txt_producto").val('');
   $("#txt_descripcion").val('');
   $("#txt_preciocosto").val('');
   $("#txt_precioventa").val('');
   $("#txt_exitencias").val('');
   $("#txt_FechaIngreso").val('');
   $("#txt_Imagen").val('');
   

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
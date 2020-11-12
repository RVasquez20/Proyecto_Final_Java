<%-- 
    Document   : NuevoAdmin
    Created on : 15/10/2020, 12:50:52 AM
    Author     : rodri
--%>
<%@page import="Modelo.Usuarios"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.HashMap"%>
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
    if ((actual.getAttribute("Logueado") != null) && ((tipo.equals("ADMIN") || nuevo != null))) {
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
        <link rel="stylesheet" type="text/css" href="CSS/NuevoAdmin.css">

        <script src="JS/AllInOne.js"></script>
        <script>
            $(document).ready(function () {
                $("#Modificar").hide();
                $("#Eliminar").hide();
                $("#txt_id").hide();
                $("#lbl_id").hide();
                 $("#demo").hide();
                  $("#demo1").hide();
                $("#t1").show();
                $("#t2").show();
                $("#Titulo").show();
                $("#Titulo2").hide();
                $("#btn_agregar").show();
         
            });
        </script>
        <title>Agregar Admin</title>
    </head>
    <body class="is-preload">
        <!-- Wrapper -->
        <div id="wrapper">
            <script src="//code.tidio.co/d8ipvm98jajzeh4xydviesl213g21mia.js" async></script>
            <!-- Header -->
            <header id="header">
                <div class="dropdown">
                    <button id="btn_sesion" type="button" class="btn btn-outline-light dropdown-toggle" style="border:none;" data-toggle="dropdown">
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
                    <span ><img src="CSS/Logos/PE.svg" style="max-width: 40px; max-height: 40px;"></span>
                </div>
                <div class="content">
                    <div class="inner">
                        <h2 id="t1">Formulario</h2><h3 id="t2">Nuevo Administrador</h3>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Listado</button>
                        <h5 id="Titulo" class="text-center">NUEVO REGISTRO</h5>
                        <h5 id="Titulo2" class="text-center">Modificacion Usuario</h5>
                        <form action="sr_Admin" method="post" class="form-group" enctype="multipart/form-data" name="formulario"  class="form-horizontal" role="form">
                            <label id="lbl_id">ID</label>                                    <br>
                            <input type="text" id="txt_id" class="form-control" name="txt_id" value="0" readonly>
                            <br>
                            <label >Usuario</label>                                    <br>
                            <input type="text" id="text_usuario" class="form-control" name="txt_usuarionuevo" required="">
                            <br>
                            <label>Nombres</label>                                    <br>
                            <input type="text" id="text_nombre" class="form-control" name="txt_nombre" required="" onkeypress="return text(event);">
                            <br>
                            <label>Apellidos</label>                                    <br>
                            <input type="text" id="text_apellidos" class="form-control" name="txt_apellidos" required="" onkeypress="return text(event);">
                            <br>
                            <label>Correo</label>                                    <br>
                            <input type="email" id="text_correo" class="form-control" name="txt_correo" required="">
                            <br>
                            <label>Password</label>                                    <br>
                            <input type="password" id="text_pass" class="form-control" name="txt_passnueva" required="">
                            <br>
                            <label>Foto De Perfil</label>  
                            <input type="file" id="imagen" name="archivo" onchange="cargarFotodeperfil(this)">
                            <input type="hidden" id="imagenes" name="imagenes"/>
                            <br>
                            <label>Codigo de validacion</label>                                    <br>
                            <input type="text" id="text_codigo" class="form-control" name="txt_codigo" required="" maxlength="7">
                            <br>
                            
                            <input type="hidden" name="nombre" id="file">
                             <br><button type="button" class="btn btn-info" id="demo1" data-toggle="collapse" data-target="#demo"><svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-info-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                <path d="M8.93 6.588l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588z"/>
                                <circle cx="8" cy="4.5" r="1"/>
                                </svg>&ensp;Permisos Para menus</button>
                           
                            <div id="demo" class="collapse" >
                                 <table class="table table-dark table-hover text-center">
                            <thead>
                                <tr>  
                                    <th>Clientes</th>
                                    <th>Compras Detalle</th>
                                    <th>Empleado</th>
                                    <th>Marcas</th>
                                    <th>Productos</th>
                                    <th>Proveedores</th>
                                    <th>Puestos</th>
                                    <th>Ventas Detalle</th>
                                    <th>Nuevo Admin</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                             <td><select name="drop_clientes" id="drop_clientes" class="form-control" >  
                                    <option value="on">✔    </option>
                                    <option value="off">✖</option>    
                                </select>
                             </td>
                            
                                <td><select name="drop_compras_detalle" id="drop_compras_detalle" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                              </td>
                               <td> <select name="drop_empleados" id="drop_empleados" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                              </td>
                               <td> <select name="drop_marcas" id="drop_marcas" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                               </td>
                                <td><select name="drop_productos" id="drop_productos" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                                </td>
                               <td> <select name="drop_proveedores" id="drop_proveedores" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                               </select>
                                </td>
                               <td> <select name="drop_puestos" id="drop_puestos" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                               </td>
                                <td><select name="drop_ventas_detalle" id="drop_ventas_detalle" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                               </td>
                               <td> <select name="drop_NuevoAdmin" id="drop_NuevoAdmin" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                                </td>
                                <td> <select name="drop_Status" id="drop_Status" class="form-control">  
                                    <option value="on">✔</option>
                                    <option value="off">✖</option>    
                                </select>
                                </td>
                                    </tbody>

                        </table>
                    </div>
                            <input id ="Registrar" name="Registrar" value="Registrar" class="btn btn-success" type ="submit" onclick="return ValidarCodigo();">
                             <input id ="Modificar" name="Modificar" value="Modificar" class="btn btn-success" type ="submit">
                            <input type="submit" class="btn btn-primary" name="Eliminar" id="Eliminar" value="Eliminar" onclick="javascript:if (!confirm('¿Desea Eliminar?'))
                        return false" >
                        </form>
                         
                        


                    </div>
                </div>
                <nav>
                    <ul>
                        <%

                            if (Marca != null) {
                                out.println("<li><a href='Marcas.jsp'>Marcas</a></li><br>");
                            }
                            if (Produ != null) {
                                out.println("<li><a href='Productos.jsp'>Productos</a></li><br>");
                            }
                            if (client != null) {
                                out.println("<li><a href='clientes.jsp'>Clientes</a></li><br>");
                            }
                            if (compdet != null) {
                                out.println("<li><a href='Compras_Detalle.jsp'>Compras Detalle</a></li><br>");
                            }
                            if (Empl != null) {
                                out.println("<li><a href='Empleados.jsp'>Empleados</a></li><br>");
                            }
                            if (Prove != null) {
                                out.println("<li><a href='Proveedores.jsp'>Proveedores</a></li><br>");
                            }
                            if (Puest != null) {
                                out.println("<li><a href='Puestos.jsp'>Puestos</a></li><br>");
                            }
                            if (Vendde != null) {
                                out.println("<li><a href='VentasDetalle.jsp'>Ventas Detalle</a></li><br>");
                            }
                            if (nuevo != null) {
                                out.println("<li><a href='NuevoAdmin.jsp'>Nuevo Admin</a></li><br>");
                            }


                        %>
                        <!--<li><a href="#elements">Elements</a></li>-->
                    </ul>
                </nav>
            </header>
        </div>
        <div class="modal" id="myModal">
            <div class="modal-dialog modal-xl modal-dialog-scrollable">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header text-center">
                        <h4 class="modal-title text-center">Lista de Usuarios </h4>
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
                                    <th>Usuario</th>
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>Correo</th>
                                    <th>Foto</th>
                                    <th>Codigo</th>
                                    <th>Tipo</th>
                                    <th>clientes</th>
                                    <th>compras_detalle</th>
                                    <th>empleados</th>
                                    <th>marcas</th>
                                    <th>productos</th>
                                    <th>proveedores</th>
                                    <th>puestos</th>
                                    <th>ventas_detalle</th>
                                    <th>NuevoAdmin</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody id="tbl_usuarios">
                                <%            Usuarios usuari = new Usuarios();
                                    DefaultTableModel tabla = new DefaultTableModel();
                                    tabla = usuari.ListaUsuarios();
                                    for (int t = 0; t < tabla.getRowCount(); t++) {
                                        out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-idimagen=" + tabla.getValueAt(t, 5) + ">");
                                        out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                                        out.println("<td><img src='sources/" + tabla.getValueAt(t, 5) + "' style='width:100px; height:100px; cursor:pointer' value=" + tabla.getValueAt(t, 5) + " title=" + tabla.getValueAt(t, 5) + " </td>");
                                        out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                                        if (tabla.getValueAt(t, 8) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 9) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 10) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 11) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 12) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 13) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 14) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 15) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                        if (tabla.getValueAt(t, 16) != null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
                                         if (tabla.getValueAt(t, 17)!= null) {
                                            out.println("<td>ON</td>");
                                        } else {
                                            out.println("<td>OFF</td>");
                                        }
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
   
        <script src="assets/js/browser.min.js"></script>
        <script src="assets/js/breakpoints.min.js"></script>
        <script src="assets/js/util.js"></script>
        <script src="assets/js/main.js"></script>
        <script  type="text/javascript">
               $('#tbl_usuarios').on('click', 'tr td', function (evt) {

                   var target, idUsuario, Usuario, Pass, Nombres, Apellidos, Correo, Codigo, Foto, clientes, compras_detalle, empleados, marcas,
                           productos, proveedores, puestos, ventas_detalle, NuevoAdmin,Status1;
                   ;
                   target = $(event.target);
                   $("#txt_id").show();
                   $("#lbl_id").show(); 
                   $("#Modificar").show();
                   $("#Eliminar").show();
                   $("#Registrar").hide();
                   $(".form-contol").hide();
                   $("#t1").hide();
                   $("#t2").hide();
                   $("#Titulo").hide();
                   $("#Titulo2").show();
                    $("#demo").show();
                  $("#demo1").show();
                   idUsuario = target.parent().data('id');
                   Foto = target.parents().data('idimagen');
                   Usuario = target.parents("tr").find("td").eq(0).html();
                   Nombres = target.parents("tr").find("td").eq(1).html();
                   Apellidos = target.parents("tr").find("td").eq(2).html();
                   Correo = target.parents("tr").find("td").eq(3).html();
                   Pass = target.parents("tr").find("td").eq(4).html();
                   Codigo = target.parents("tr").find("td").eq(6).html();
                   Tipo = target.parents("tr").find("td").eq(7).html();
                   clientes = target.parents("tr").find("td").eq(8).html();
                   compras_detalle = target.parents("tr").find("td").eq(9).html();
                   empleados = target.parents("tr").find("td").eq(10).html();
                   marcas = target.parents("tr").find("td").eq(11).html();
                   productos = target.parents("tr").find("td").eq(12).html();
                   proveedores = target.parents("tr").find("td").eq(13).html();
                   puestos = target.parents("tr").find("td").eq(14).html();
                   ventas_detalle = target.parents("tr").find("td").eq(15).html();
                   NuevoAdmin = target.parents("tr").find("td").eq(16).html();
                   Status1 = target.parents("tr").find("td").eq(17).html();
                   if(clientes==="ON"){
                       $("#drop_clientes").val("on");
                   }else{
                       $("#drop_clientes").val("off");
                   }
    
                   if(empleados==="ON"){
                       $("#drop_empleados").val("on");
                   }else{
                       $("#drop_empleados").val("off");
                   }
                   if(compras_detalle==="ON"){
                       $("#drop_compras_detalle").val("on");
                   }else{
                       $("#drop_compras_detalle").val("off");
                   }
                   if(marcas==="ON"){
                       $("#drop_marcas").val("on");
                   }else{
                       $("#drop_marcas").val("off");
                   }
                   if(productos==="ON"){
                       $("#drop_productos").val("on");
                   }else{
                       $("#drop_productos").val("off");
                   }
                   if(proveedores==="ON"){
                       $("#drop_proveedores").val("on");
                   }else{
                       $("#drop_proveedores").val("off");
                   }
                   if(puestos==="ON"){
                       $("#drop_puestos").val("on");
                   }else{
                       $("#drop_puestos").val("off");
                   }
                   if(ventas_detalle==="ON"){
                       $("#drop_ventas_detalle").val("on");
                   }else{
                       $("#drop_ventas_detalle").val("off");
                   }
                   if(NuevoAdmin==="ON"){
                       $("#drop_proveedores").val("on");
                   }else{
                       $("#drop_NuevoAdmin").val("off");
                   }
                     
                   if(Status1==="ON"){
                       $("#drop_Status").val("on");
                    
                   }else{
                       $("#drop_Status").val("off");
                   }
    
                   $("#txt_id").val(idUsuario);
                   $("#txt_id").attr('readonly',true);
                   $("#imagenes").val(Foto);
                   $("#imagenes").attr('readonly',true);
                   $("#text_usuario").val(Usuario);
                   $("#text_usuario").attr('readonly',true);
                   $("#text_nombre").val(Nombres);
                   $("#text_nombre").attr('readonly',true);
                   $("#text_apellidos").val(Apellidos);
                   $("#text_apellidos").attr('readonly',true);
                   $("#text_correo").val(Correo);
                   $("#text_correo").attr('readonly',true);
                   $("#text_pass").val(Pass);
                   $("#text_pass").attr('readonly',true);
                   $("#text_codigo").val(Codigo);
                   $("#text_codigo").attr('readonly',true);
   $('#myModal').modal('hide');

               });
        </script>
    </body>
</html>
<%    } else {
        response.sendRedirect("Error.jsp");

    }
%>


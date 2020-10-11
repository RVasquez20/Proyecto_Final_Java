<%-- 
    Document   : Marcas
    Created on : 1/10/2020, 04:55:32 PM
    Author     : rodri
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
<%@page import="Modelo.marcas"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
                <div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
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
        <h1>Marcas</h1>
       
        <div class="container">
         <form action="sr_Marcas" method="POST" class="form-group">
           <label>ID:</label>
           <input type="text" name="txt_id_Marcas" id="txt_id_Marcas" class="form-control" value="0">
           <label for="lbl_Marca" ><b>Marca</b></label>
           <input type="text" name="txt_Marca" id="txt_Marca" class="form-control" placeholder="Ejemplo: Pepsi" required>
           
           <br>
 <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
  </form>
    </div>
        <br>
        
                <br> <a href="Productos.jsp">Regresar</a>
             
                <br>
                <br>
                <div class="container">
                           
           
    <table class="table table-striped">
    <thead>
      <tr>
          <th>Id</th>
        <th>Marca</th>

      </tr>
    </thead>
    <tbody id="tbl_Marcas">
        <% 
        marcas marca=new marcas();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = marca.ListaDeMarcas();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr>");
             out.println("<td>" + tabla.getValueAt(t,0) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("</tr>");
        
        }

        %>
      
    </tbody>
  </table>
                    
                </div>
        <script type="text/javascript">
         $('#tbl_Marcas').on('click','tr td',function(evt){
       var target,id,Marcas; 
       target = $(event.target);
       id = target.parent("tr").find("td").eq(0).html();
       Marcas = target.parent("tr").find("td").eq(1).html();
       

       $("#txt_id_Marcas").val(id);
       $("#txt_Marca").val(Marcas);
 
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
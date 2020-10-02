<%-- 
    Document   : Compras_Detalle
    Created on : 2/10/2020, 03:56:40 PM
    Author     : rodri
--%>

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
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <title>Compras Detalle</title>
    </head>
    <body>
        <div class="container">
            <h1>Compras Detalle</h1>
            <a href="index.jsp">Regresar</a>
       <form action="sr_ComprasDetalle" method="post" class="form-group">
               <label for="lbl_id" ><b>ID</b></label>
               <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly><br>
               <label for="lbl_noOrden" ><b>Numero de Orden</b></label>
                <select name="ListaOrdenes" id="ListaOrdenes" class="form-control">
                    <% 
                        Compras compra = new Compras();
                        HashMap<String,String> drop = compra.Lista_Odenes();
                        out.println("<option value='0'>Seleccione</option>");
                         for (String i:drop.keySet()){
                             out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                         }
                         
                    
                    %>
                </select>
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
                <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                
            </form>
        
          
  <table class="table table-hover">
    <thead>
      <tr>
        <th>No. Orden</th>
        <th>Productos</th>
        <th>Cantidad</th>
        <th>Precio Costo Unitario</th>
      </tr>
      </thead>
      <tbody id="tbl_CompraDetalle">
        <% 
        ComprasDetalle Detalle = new ComprasDetalle();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = Detalle.ListaDeComprasDetalle();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t,0) + " data-idc=" + tabla.getValueAt(t,1) +" data-idp=" + tabla.getValueAt(t,3) + ">");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
            out.println("</tr>");
        
        }
        %>  
    </tbody>
  </table>
        
        
        </div>
         <script type="text/javascript">
    $('#tbl_CompraDetalle').on('click','tr td',function(evt){
       var target,id,idc,idp,orden,producto,precio,cantidad; 
       target = $(event.target);
       id = target.parent().data('id');
       idc = target.parent().data('idc'); 
       idp = target.parent().data('idp'); 
       orden= target.parent("tr").find("td").eq(0).html();
       producto = target.parent("tr").find("td").eq(1).html();
       cantidad=target.parent("tr").find("td").eq(2).html();
       precio = target.parent("tr").find("td").eq(3).html();
       
       $("#txt_id").val(id);
        $("#ListaOrdenes").val(idc);
         $("#drop_Producto").val(idp);
       $("#txt_PrecioUnitario").val(precio);
       $("#txt_Cantidad").val(cantidad);
       
       
    });
    
</script>
        
    </body>
</html>

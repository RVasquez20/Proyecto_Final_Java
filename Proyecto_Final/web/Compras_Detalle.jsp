<%-- 
    Document   : Compras_Detalle
    Created on : 2/10/2020, 03:56:40 PM
    Author     : rodri
--%>

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
                <input type="number"  name="txt_Cantidad" id="txt_Cantidad" class="form-control" required>
                 <label for="lbl_PrecioUnitario" ><b>Precio Unitario</b></label>
                <input type="money"  name="txt_PrecioUnitario" id="txt_PrecioUnitario" class="form-control" required>
                <br>
                <h1>Compras:</h1>
           <label>ID:</label>
           <input type="text" name="txt_id_Compra" id="txt_id_Compra" class="form-control" value="0"  readonly>
           <label for="lbl_No_Orden" ><b>Numero de Orden</b></label>
           <input type="text" name="txt_No_Orden" id="txt_No_Orden" class="form-control" placeholder="Ejemplo: 120" required>
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
           <label for="lbl_Fecha_Ingreso" ><b>Fecha De Ingreso</b></label>
           <input type="DateTime" name="txt_Fecha_Ingreso" id="txt_Fecha_Ingreso" class="form-control" placeholder="Ejemplo: 12/12/2000 2:2:2" " required>
           
           <br>
 <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
  
    
                
            </form>
        </div>
          
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
        <br>
             
          
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
        DefaultTableModel tabla2 = new DefaultTableModel();
        tabla2 = Compra.ListaDeCompras();
        for (int t=0;t<tabla2.getRowCount();t++){
            out.println("<tr data-id=" + tabla2.getValueAt(t,0)+" data-idp="+tabla2.getValueAt(t,2)+ ">");
            out.println("<td>" + tabla2.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla2.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla2.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla2.getValueAt(t,5) + "</td>");
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

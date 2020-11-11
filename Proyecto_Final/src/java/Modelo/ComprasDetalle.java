    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rodri
 */
public class ComprasDetalle {
    private int id,idCompra,idProducto,cantidad;
    private double PrecioUnitario;
    private Conexion cn;

    public ComprasDetalle() {
    }

    public ComprasDetalle(int id, int idCompra, int idProducto, int cantidad, double PrecioUnitario) {
        this.id = id;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.PrecioUnitario = PrecioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }
    public DefaultTableModel ListaDeCompras(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select c.idcompra as id,c.no_orden_compra,pr.idproveedor,pr.proveedor,c.fecha_orden,c.fechaingreso from compras as c inner join proveedores as pr on c.idproveedor=pr.idproveedor order by id;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","idCompras","no Orden","idProveedor","Proveedor","Fecha De Orden","Fecha de Ingreso"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[6];
      while (consulta.next()){
          datos[0] = consulta.getString("ID");
          datos[1] = consulta.getString("no_orden_compra");
          datos[2] = consulta.getString("idproveedor");
          datos[3] = consulta.getString("proveedor");
          datos[4] = consulta.getString("fecha_orden");
          datos[5] = consulta.getString("fechaingreso");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }
    /*****************************************************/
     
    public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into compras_detalle(idcompra,idproducto,cantidad,precio_costo_unitario) values(?,?,?,?)";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getIdCompra());
            parametro.setInt(2,getIdProducto());
            parametro.setInt(3,getCantidad());
            parametro.setDouble(4,getPrecioUnitario());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
      
     public int Modificar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE compras_detalle set idcompra=?,idproducto=?,cantidad=?,precio_costo_unitario=? where idcompra_detalle=?";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getIdCompra());
            parametro.setInt(2,getIdProducto());
            parametro.setInt(3,getCantidad());
            parametro.setDouble(4,getPrecioUnitario());
            parametro.setInt(5,getId());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
      
       public int Eliminar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from compras_detalle where idcompra_detalle=?";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getId());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
       public int cantidadantigua(int id2){
          int retorno=0;
          int cant=0;
     
        try {
            cn=new Conexion();
       
            String query2="select cantidad from compras_detalle where idcompra_detalle="+id2+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta2=cn.conexionbd.createStatement().executeQuery(query2);
             while (consulta2.next()) {
                cant=consulta2.getInt("cantidad");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return cant;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
    }
    public int ActualizarExistencias(){
         int retorno=0;
        try {
            cn=new Conexion();
            productos p1=new productos();
            PreparedStatement parametro;
            String query="UPDATE  productos SET existencia=?+? WHERE idProducto=?;";
            cn.abrirconexion();
            parametro=cn.conexionbd.prepareStatement(query);
            parametro.setInt(1, p1.ex(getIdProducto()));
            
                parametro.setInt(2, getCantidad());
            
            parametro.setInt(3,getIdProducto());
           
                    retorno=parametro.executeUpdate();
            cn.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        } 
    }
    public int ActualizarPrecioCosto(){
         int retorno=0;
        try {
            cn=new Conexion();
            
            PreparedStatement parametro;
            String query="UPDATE  productos SET precio_costo=? WHERE idProducto=?;";
            cn.abrirconexion();
            parametro=cn.conexionbd.prepareStatement(query);
            parametro.setDouble(1, getPrecioUnitario());
            parametro.setInt(2,getIdProducto());
           
                    retorno=parametro.executeUpdate();
            cn.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        } 
    }
     public int ActualizarPrecioventa(){
         int retorno=0;
        try {
            cn=new Conexion();
            
            PreparedStatement parametro;
            String query="UPDATE  productos SET precio_venta=?+(?*0.25) WHERE idProducto=?;";
            cn.abrirconexion();
            parametro=cn.conexionbd.prepareStatement(query);
            parametro.setDouble(1, getPrecioUnitario());
            parametro.setDouble(2, getPrecioUnitario());
            parametro.setInt(3,getIdProducto());
           
                    retorno=parametro.executeUpdate();
            cn.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        } 
    }
     
          public int lastid() throws SQLException{
          int retorno=0;
          int exi=0;
     
        try {
            cn=new Conexion();
       
            String query="SELECT max(idcompra) FROM compras;";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("max(idcompra)");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
 }
 public int noorden() throws SQLException{
          int retorno=0;
          int exi=0;
     
        try {
            cn=new Conexion();
       
            String query="SELECT max(no_orden_compra) FROM compras;";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("max(no_orden_compra)");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
 }
     public Double Total(int id) throws SQLException{
          Double retorno=0.0;
          Double exi=0.0;
     
        try {
            cn=new Conexion();
       
            String query="select sum((precio_costo_unitario*cantidad)) from compras_detalle where idcompra="+id+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getDouble("sum((precio_costo_unitario*cantidad))");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
 }
     
     
         public DefaultTableModel ListaDeProductos(int idCompra){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select cd.idProducto as id,cd.idcompra_detalle,p.Producto,cd.cantidad,cd.precio_costo_unitario,(cd.precio_costo_unitario*cd.cantidad)as subtotal from compras_detalle as cd inner join productos as p on cd.idproducto=p.idProducto  where cd.idcompra='"+idCompra+"' order by id;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"id","idcompradetalle","Producto","Cantidad","Precio_Unitario","Subtotal"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[6];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("idcompra_detalle");
          datos[2] = consulta.getString("Producto");
          datos[3] = consulta.getString("cantidad");
          datos[4] = consulta.getString("precio_costo_unitario");
          datos[5] = consulta.getString("subtotal");
          
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }
         
         
       public DefaultTableModel datosProveedor(int idcompra){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select v.idcompra as id,c.proveedor,c.nit,c.direccion,c.telefono from compras as v inner join proveedores as c on c.idproveedor=v.idproveedor where idcompra='"+idcompra+"';";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"id","proveedor","nit","direccion","telefono"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[5];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("proveedor");
          datos[2] = consulta.getString("nit");
          datos[3] = consulta.getString("direccion");
          datos[4] = consulta.getString("telefono");
          
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }   
           
}

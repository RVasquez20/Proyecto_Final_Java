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
    public DefaultTableModel ListaDeComprasDetalle(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select dc.idcompra_detalle as ID,c.idcompra,c.no_orden_compra,p.idproducto,p.producto,dc.cantidad,dc.precio_costo_unitario,c.idproveedor,pr.proveedor,c.fecha_orden,c.fechaingreso from compras_detalle as dc inner join compras as c inner join productos as p inner join proveedores as pr where dc.idcompra=c.idcompra and dc.idproducto=p.idProducto and pr.idproveedor=c.idproveedor order by ID;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","idCompras","no Orden","idProducto","Productos","Cantidad","Precio unitario","idProveedor","Proveedor","Fecha De Orden","Fecha de Ingreso"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[11];
      while (consulta.next()){
          datos[0] = consulta.getString("ID");
          datos[1] = consulta.getString("idcompra");
          datos[2] = consulta.getString("no_orden_compra");
          datos[3] = consulta.getString("idproducto");
          datos[4] = consulta.getString("producto");
          datos[5] = consulta.getString("cantidad");
          datos[6] = consulta.getString("precio_costo_unitario");
          datos[7] = consulta.getString("idproveedor");
          datos[8] = consulta.getString("proveedor");
          datos[9] = consulta.getString("fecha_orden");
          datos[10] = consulta.getString("fechaingreso");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }
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
         int antigua=0;
        try {
            cn=new Conexion();
            productos p1=new productos();
            ComprasDetalle c1=new ComprasDetalle();
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
}

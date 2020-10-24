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
public class VentasDetalle {
      private int id,idVenta,idProducto,cantidad;
    private double PrecioUnitario;
    private Conexion cn;

    public VentasDetalle() {
    }

    public VentasDetalle(int id, int idVenta, int idProducto, int cantidad, double PrecioUnitario) {
        this.id = id;
        this.idVenta = idVenta;
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

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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
       public DefaultTableModel ListaDeVentasDetalle(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select dv.idVentas_detalle as ID,v.idVentas,v.nofactura,v.serie,v.fechafactura,c.idClientes,c.nombres,"
              + "c.nit,e.nombres,e.idempleado,v.fechaingreso,p.idProducto,p.producto,dv.cantidad,dv.precio_unitario  from ventas_detalle as dv inner join ventas as v inner join productos as p inner join clientes as c inner join empleados as e on dv.idProducto=p.idProducto and dv.idventa=v.idVentas and v.idcliente=c.idClientes and v.idempleado=e.idEmpleado order by ID;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","idVentas","no Factura","Serie","Fecha de Factura","ID Clientes", "Nombre Cliente", "NIT","Nombre empleado", "ID Empleado", "fechaingreso","idProducto","Productos","Cantidad","Precio unitario"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[15];
      while (consulta.next()){
          datos[0] = consulta.getString("ID");
          datos[1] = consulta.getString("idVentas");
          datos[2] = consulta.getString("nofactura");
          datos[3] = consulta.getString("serie");
          datos[4] = consulta.getString("fechafactura");
          datos[5] = consulta.getString("idClientes");
          datos[6] = consulta.getString("nombres");
          datos[7] = consulta.getString("nit");
          datos[8] = consulta.getString("e.nombres");
          datos[9] = consulta.getString("idempleado");
          datos[10] = consulta.getString("fechaingreso");
          datos[11] = consulta.getString("idproducto");
          datos[12] = consulta.getString("producto");
          datos[13] = consulta.getString("cantidad");
          datos[14] = consulta.getString("precio_unitario");
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
            String query = "INSERT INTO ventas_detalle(idventa,idProducto,cantidad,precio_unitario)VALUES(?,?,?,?);";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getIdVenta());
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
            String query = "UPDATE ventas_detalle SET idventa = ?,idProducto =?,cantidad = ?,precio_unitario = ? WHERE idVentas_detalle = ?;";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getIdVenta());
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
            String query = "delete from ventas_detalle where idVentas_detalle=?";
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
       
            String query2="select cantidad from ventas_detalle where idVentas_detalle="+id2+";";
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
            String query="UPDATE productos SET existencia=?-? WHERE idProducto=?;";
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
    public int idforventasdetalle(int nofactura) throws SQLException{
          int retorno=0;
          int exi=0;
     
        try {
            cn=new Conexion();
       
            String query="select idVentas from ventas where nofactura="+nofactura+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("idVentas");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
 }

}

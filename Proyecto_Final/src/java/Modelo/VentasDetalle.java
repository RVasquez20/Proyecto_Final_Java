/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
       public DefaultTableModel ListaDeVentas(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select v.idVentas as ID,v.nofactura,v.serie,v.fechafactura,c.idClientes,c.nombres,c.nit,e.nombres,e.idempleado,v.fechaingreso  from  ventas as v  inner join clientes as c inner join empleados as e on v.idcliente=c.idClientes and v.idempleado=e.idEmpleado order by ID;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","no Factura","Serie","Fecha de Factura","ID Clientes", "Nombre Cliente", "NIT","Nombre empleado", "ID Empleado", "fechaingreso"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[10];
      while (consulta.next()){
          datos[0] = consulta.getString("ID");
          datos[1] = consulta.getString("nofactura");
          datos[2] = consulta.getString("serie");
          datos[3] = consulta.getString("fechafactura");
          datos[4] = consulta.getString("idClientes");
          datos[5] = consulta.getString("nombres");
          datos[6] = consulta.getString("nit");
          datos[7] = consulta.getString("e.nombres");
          datos[8] = consulta.getString("idempleado");
          datos[9] = consulta.getString("fechaingreso");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }
       
 public DefaultTableModel ListaDeVentasDetalle(int id){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select dv.idVentas_detalle as ID,v.idVentas,v.nofactura,v.serie,v.fechafactura,c.idClientes,c.nombres,c.nit,e.nombres,"
              + "e.idempleado,v.fechaingreso,p.idProducto,p.producto,dv.cantidad,dv.precio_unitario  from ventas_detalle as dv inner join ventas as v inner join productos as p inner join clientes as c inner join empleados as e on dv.idProducto=p.idProducto and dv.idventa=v.idVentas and v.idcliente=c.idClientes and v.idempleado=e.idEmpleado where idVentas="+id+" order by ID;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","idVenta","no Factura","serie","Fecha Factura","ID Clientes", "Nombre Cliente", "NIT","Nombre empleado", "ID Empleado", "fechaingreso","id Producto","Producto","cantidad","Precio Unitario"};
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
          datos[11] = consulta.getString("idProducto");
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
    public int nofact(int id) throws SQLException{
          int retorno=0;
          int exi=0;
     
        try {
            cn=new Conexion();
       
            String query="select nofactura from ventas where idVentas="+id+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("nofactura");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
 }
     public int nofactnew() throws SQLException{
          int retorno=0;
          int exi=0;
     
        try {
            cn=new Conexion();
       
            String query="SELECT max(nofactura)as id FROM dbempresa.ventas;";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("id");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
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
       
            String query="SELECT max(idVentas) FROM ventas;";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("max(idVentas)");
               
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
       
            String query="select sum((precio_unitario*cantidad)) from ventas_detalle where idventa="+id+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getDouble("sum((precio_unitario*cantidad))");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
 }
     
     
         public DefaultTableModel ListaDeProductos(int idventa){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select vd.idProducto as id,vd.idVentas_detalle,p.Producto,vd.cantidad,vd.precio_unitario,(vd.precio_unitario*vd.cantidad)as subtotal from ventas_detalle as vd inner join productos as p on vd.idProducto=p.idProducto  where vd.idventa="+idventa+" order by id;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"id","idventadetalle","Producto","Cantidad","Precio_Unitario","Subtotal"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[6];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("idVentas_detalle");
          datos[2] = consulta.getString("Producto");
          datos[3] = consulta.getString("cantidad");
          datos[4] = consulta.getString("precio_unitario");
          datos[5] = consulta.getString("subtotal");
          
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }
       public DefaultTableModel datoscliente(int idventa){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select v.idcliente as id,c.nombres,c.apellidos,c.NIT,c.telefono,c.correo_electronico from ventas as v inner join clientes as c on c.idClientes=v.idcliente where idVentas='"+idventa+"';";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"id","nombre","apellido","nit","telefono","correo electronico"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[6];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("nombres");
          datos[2] = consulta.getString("apellidos");
          datos[3] = consulta.getString("NIT");
          datos[4] = consulta.getString("telefono");
          datos[5] = consulta.getString("correo_electronico");
          
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
  return tabla;
      
    }   
            
  public String correocliente(int id){
          String retorno=null;
          String cant=null;
     
        try {
            cn=new Conexion();
       
            String query2="select c.correo_electronico from ventas as v inner join clientes as c on c.idClientes=v.idcliente where idVentas="+id+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta2=cn.conexionbd.createStatement().executeQuery(query2);
             while (consulta2.next()) {
                cant=consulta2.getString("correo_electronico");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return cant;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
    }
}

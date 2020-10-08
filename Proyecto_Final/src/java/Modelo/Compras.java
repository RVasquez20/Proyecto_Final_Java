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
 * @author josef
 */
public class Compras {
    private int idCompras,numeroDeOrden,idProveedor;
    private String FechaOden,FechaIngreso;
    private Conexion cn;

    public Compras() {
    }

    public Compras(int idCompras, int numeroDeOrden, int idProveedor, String FechaOden, String FechaIngreso) {
        this.idCompras = idCompras;
        this.numeroDeOrden = numeroDeOrden;
        this.idProveedor = idProveedor;
        this.FechaOden = FechaOden;
        this.FechaIngreso = FechaIngreso;
    }

    public int getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(int idCompras) {
        this.idCompras = idCompras;
    }

    public int getNumeroDeOrden() {
        return numeroDeOrden;
    }

    public void setNumeroDeOrden(int numeroDeOrden) {
        this.numeroDeOrden = numeroDeOrden;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFechaOden() {
        return FechaOden;
    }

    public void setFechaOden(String FechaOden) {
        this.FechaOden = FechaOden;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

public DefaultTableModel ListaDeCompras(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select c.idcompra as ID,c.no_orden_compra,p.idproveedor,p.proveedor,c.fecha_orden,c.fechaingreso from compras as c inner join proveedores as p where c.idproveedor=p.idproveedor order by ID;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","Numero de Compra","idProveedor","Proveedor","Fecha De Orden","Fecha de Ingreso"};
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
 public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into compras(no_orden_compra,idproveedor,fecha_orden,fechaingreso) values(?,?,?,?);";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getNumeroDeOrden());
            parametro.setInt(2,getIdProveedor());
            parametro.setString(3,getFechaOden());
            parametro.setString(4,getFechaIngreso());
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
            String query = "UPDATE compras set no_orden_compra=?,idproveedor=?,fecha_orden=?,fechaingreso=? where idcompra=?";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getNumeroDeOrden());
            parametro.setInt(2,getIdProveedor());
            parametro.setString(3,getFechaOden());
            parametro.setString(4,getFechaIngreso());
            parametro.setInt(5,getIdCompras());
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
            String query = "delete from compras where idcompra=?";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getIdCompras());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
           public HashMap Lista_Odenes(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="select idcompra as ID,no_orden_compra from compras;";
         cn = new Conexion();
         cn.abrirconexion();
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("ID"),consulta.getString("no_orden_compra") );
            }
         cn.cerrarconexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
             public int idfordetalle(int orden) throws SQLException{
          int retorno=0;
          int exi=0;
     
        try {
            cn=new Conexion();
       
            String query="select idcompra from compras where no_orden_compra="+orden+";";
            cn.abrirconexion();
           
            
           
             ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
             while (consulta.next()) {
                exi=consulta.getInt("idcompra");
               
                }
                   
                    
            cn.cerrarconexion(); 
            return exi;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
    }

}

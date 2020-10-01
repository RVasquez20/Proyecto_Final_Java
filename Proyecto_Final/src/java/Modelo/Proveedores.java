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
public class Proveedores {
    private int idProveedores;
    private String nit,proveedor,direccion,telefono;
    private Conexion cn;

    public Proveedores(int idProveedores, String nit, String proveedor, String direccion, String telefono) {
        this.idProveedores = idProveedores;
        this.nit = nit;
        this.proveedor = proveedor;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Proveedores() {
    }

    public int getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(int idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
     public DefaultTableModel ListaDeProveedores(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "SELECT idproveedor AS ID,proveedor,nit,direccion,telefono from proveedores";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","Proveedor","NIT","Direccion","Telefono"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[5];
      while (consulta.next()){
          datos[0] = consulta.getString("ID");
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
      public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into proveedores(proveedor,nit,direccion,telefono) values(?,?,?,?);";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setString(1,getProveedor());
            parametro.setString(2,getNit());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
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
            String query = "UPDATE proveedores set proveedor=?,nit=?,direccion=?,telefono=? where idproveedor=?";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setString(1,getProveedor());
            parametro.setString(2,getNit());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setInt(5,getIdProveedores());
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
            String query = "delete from proveedores where idproveedor=?";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1,getIdProveedores());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
        public HashMap ListaProveedor(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="select idproveedor as id,proveedor from proveedores;";
         cn = new Conexion();
         cn.abrirconexion();
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("id"),consulta.getString("proveedor") );
            }
         cn.cerrarconexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
}

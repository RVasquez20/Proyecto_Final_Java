/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.HashMap;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author cindy
 */
public class marcas {
    private int idMarca;
    private String Marca;
    private Conexion cn;
    public marcas() {
    }

    public marcas(int idMarca, String Marca) {
        this.idMarca = idMarca;
        this.Marca = Marca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
    public HashMap box_marcas(){
        HashMap<String,String> combo=new HashMap();
        try {
            cn=new Conexion();
            String query="SELECT idmarca as id,marca from marcas;";
            cn.abrirconexion();
            ResultSet consulta=cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()) {
                combo.put(consulta.getString("id"),consulta.getString("marca"));
            }
            cn.cerrarconexion();
        } catch (SQLException e) {
             System.out.println("Error->"+e.getMessage());
        }
        return combo;
    }
    public DefaultTableModel ListaDeMarcas(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "Select idmarca as ID,marca from marcas";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"ID","Marcas"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[2];
      while (consulta.next()){
          datos[0] = consulta.getString("ID");
          datos[1] = consulta.getString("marca");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }
     public int agregar(){
        int retorno=0;
        try {
            cn=new Conexion();
            PreparedStatement parametro;
            String query="INSERT INTO marcas(marca)VALUES(?);";
            cn.abrirconexion();
            parametro=cn.conexionbd.prepareStatement(query);
            parametro.setString(1, getMarca());
           
                    retorno=parametro.executeUpdate();
            cn.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
      
    }
        public int modificar(){
        int retorno=0;
        try {
            cn=new Conexion();
            PreparedStatement parametro;
            String query="UPDATE  marcas SET marca=? WHERE idmarca=?;";
            cn.abrirconexion();
            parametro=cn.conexionbd.prepareStatement(query);
            parametro.setString(1, getMarca());
            parametro.setInt(2, getIdMarca());
            
                    retorno=parametro.executeUpdate();
            cn.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
      
    }
             public int eliminar(){
        int retorno=0;
        try {
            cn=new Conexion();
            PreparedStatement parametro;
            String query="DELETE FROM marcas WHERE idmarca=?;";
            cn.abrirconexion();
            parametro=cn.conexionbd.prepareStatement(query);
            
            parametro.setInt(1,getIdMarca());
            
                    retorno=parametro.executeUpdate();
            cn.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
      
    }
}

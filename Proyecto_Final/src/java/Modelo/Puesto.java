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

public class Puesto {
    private int id_puesto;
    private String puesto;
    Conexion cn;
    public Puesto(){}
    public Puesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public HashMap drop_Puesto(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="SELECT idPuesto as id,puesto FROM puestos;";
         cn = new Conexion();
         cn.abrirconexion();
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("id"),consulta.getString("puesto") );
            }
         cn.cerrarconexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
    public DefaultTableModel leer(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "select idPuesto as id,puesto from puestos;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"id","puestos"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[2];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("puesto");

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
            String query = "insert into puestos(puesto)values(?);";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setString(1,getPuesto());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    

   public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update puestos set puesto= ? where idPuesto=? ;";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setString(1,getPuesto());
            parametro.setInt(2, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }

    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from puestos  where idPuesto = ?;";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author cindy
 */
public class Marcas {
  
    private int idmarca;
    private String marca;
     private Conexion con;

    public Marcas() {
    }
    
    public Marcas(int idmarca, String marca) {
        this.idmarca = idmarca;
        this.marca = marca;
    }
     public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    

    
    
    

  public DefaultTableModel lista(){
      DefaultTableModel tabla=new DefaultTableModel();
      try{
          con= new Conexion();
          con.abrirconexion();
          String Query ="SELECT marcas.idmarca,marcas.marca FROM dbempresa.marcas;";
          ResultSet Consulta=con.conexionbd.createStatement().executeQuery(Query);
          String[] Encabezado={"idmarca","marca"};
          tabla.setColumnIdentifiers(Encabezado);
          String datos[]=new String[2];
          while(Consulta.next()){
              datos[0]=Consulta.getString("idmarca");
              datos[1]=Consulta.getString("marca");
              
              tabla.addRow(datos);
              
          }
          con.cerrarconexion();
           return tabla;
      }catch(SQLException ex){
          System.out.println("Error"+ex.getMessage());     
           return tabla;
      }
     
  }

   
    
    
 
    public int Agregar() {
        int retorno = 0;
        try{
            con= new Conexion();
            PreparedStatement Parametro;
            String Query="INSERT INTO dbempresa.marcas(idmarca,marca)VALUES (?,?);";
            con.abrirconexion();
            Parametro=con.conexionbd.prepareStatement(Query);
            Parametro.setString(1,this.getMarca());
            
            
            retorno=Parametro.executeUpdate();
            
            con.cerrarconexion();
        }catch(SQLException ex){
            System.out.println("Error"+ex.getMessage());
            retorno=0;
        }
            
        
        
        return retorno;
    }

    public int Modificar() {
        int retorno = 0;
        try{
            con= new Conexion();
            PreparedStatement Parametro;
            String Query="UPDATE dbempresa.marcas SET marca =? WHERE idmarca= ?;";
            con.abrirconexion();
            Parametro=con.conexionbd.prepareStatement(Query);
            Parametro.setString(1,this.getMarca());
          
            
            retorno=Parametro.executeUpdate();
            con.cerrarconexion();
        }catch(SQLException ex){
            System.out.println("Error"+ex.getMessage());
            return retorno;
        }
        return retorno;
    }

    public int Eliminar() {
        int retorno = 0;
        try{
            con = new Conexion();
            PreparedStatement Parametro;
            String Query = "DELETE FROM dbempresa.marcas WHERE idmarca = ?;";
            con.abrirconexion();
            Parametro = con.conexionbd.prepareStatement(Query);
            Parametro.setInt(1, this.getIdmarca());
            retorno = Parametro.executeUpdate();
            con.cerrarconexion();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            return retorno;
        }
        return retorno;
    }

   

}

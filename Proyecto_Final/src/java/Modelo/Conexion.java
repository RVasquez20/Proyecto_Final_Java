/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.*;


/**
 *
 * @author rodri
 */



import java.awt.HeadlessException;
import java.sql.*;

/**
 *
 * @author rodri
 */

public class Conexion {
    
    public Connection conexionbd;
    public final String nombre="dbempresa";
    public final String url=String.format("jdbc:mysql://localhost:3306/%s?useTimezone=true&serverTimezone=UTC",nombre);
    public final String usuario="User_Root";
    public final String contraseña="Final@2020";
    public final String jdbc="com.mysql.cj.jdbc.Driver";
    public void abrirconexion(){
        try{
            Class.forName(jdbc);
            conexionbd=DriverManager.getConnection(url,usuario,contraseña);
        }catch(HeadlessException|ClassNotFoundException|SQLException ex){
            System.out.println("Error->"+ex.getMessage());
        }
    }
    public void cerrarconexion(){
        try{
            conexionbd.close();
        }catch(SQLException ex){
                System.out.println("Error->"+ex.getMessage());
        }
    }
 
   
}

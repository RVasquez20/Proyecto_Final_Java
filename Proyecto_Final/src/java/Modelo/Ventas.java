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
 * @author Willy Valle
 */
public class Ventas {

    private int id_venta, nofactura,idcliente,idempleado;
    private String serie, fechafactura, fechaingreso;
    private Conexion cn;

    public Ventas() {
    }

    public Ventas(int id_venta, int nofactura, int idcliente, int idempleado, String serie, String fechafactura, String fechaingreso) {
        this.id_venta = id_venta;
        this.nofactura = nofactura;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.serie = serie;
        this.fechafactura = fechafactura;
        this.fechaingreso = fechaingreso;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getNofactura() {
        return nofactura;
    }

    public void setNofactura(int nofactura) {
        this.nofactura = nofactura;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(String fechafactura) {
        this.fechafactura = fechafactura;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
    
     
    public HashMap ListaC(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="select IdClientes as Id,nombres from clientes;";
         cn = new Conexion();
         cn.abrirconexion();
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("Id"),consulta.getString("nombres") );
            }
         cn.cerrarconexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
       public HashMap ListaC2(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="select IdClientes as Id,Nit from clientes;";
         cn = new Conexion();
         cn.abrirconexion();
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("Id"),consulta.getString("Nit") );
            }
         cn.cerrarconexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
        
       
     //////////////////////////////////////////////////////////////////////
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO ventas(nofactura,serie,fechafactura,idcliente,idempleado,fechaingreso) VALUES (?,?,now(),?,?,now());";
            cn.abrirconexion();
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(query);
            parametro.setInt(1, getNofactura());
            parametro.setString(2, getSerie());
            parametro.setInt(3, getIdcliente());
            parametro.setInt(4, getIdempleado());
    
            

            retorno = parametro.executeUpdate();
          
            cn.cerrarconexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    ////////////////////////////////////////////////////////////////

    public int modificar() {

        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE ventas SET nofactura=?,serie=?,fechafactura=?,idcliente=?,idempleado=?,fechaingreso=? WHERE idVentas=?;";
            cn.abrirconexion();
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(query);
            parametro.setInt(1, getNofactura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFechafactura());
            parametro.setInt(4, getIdcliente());
            parametro.setInt(5, getIdempleado());
            parametro.setString(6, getFechaingreso());
            parametro.setInt(7, getId_venta());

            retorno = parametro.executeUpdate();
        
            cn.cerrarconexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
///////////////////////////////////////////////////////////////////////////////

    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM ventas WHERE idVentas = ?;";
            cn.abrirconexion();
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(query);
            parametro.setInt(1, getId_venta());
            retorno = parametro.executeUpdate();

            cn.cerrarconexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
///////////////////////////////////////////////////////////////////////////
 
    
    
    
    

}

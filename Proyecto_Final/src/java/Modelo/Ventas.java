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
    
     public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrirconexion();
            String query = "SELECT v.idVentas as id, v.nofactura, v.serie, v.fechafactura, c.idClientes, c.nombres,c.nit,e.nombres,e.idempleado,v.fechaingreso FROM ventas as v inner join clientes as c inner join empleados as e on v.idcliente=c.idClientes and v.idempleado=e.idEmpleado order by id;";
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            String encabezado[] = {"ID", "No.Factura", "Serie", "Fecha", "ID Clientes", "Nombre Cliente", "NIT","Nombre empleado", "ID Empleado", "fechaingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[10];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nofactura");
                datos[2] = consulta.getString("serie");
                datos[3] = consulta.getString("fechafactura");
                datos[4] = consulta.getString("idClientes");
                datos[5] = consulta.getString("nombres");
                datos[6] = consulta.getString("nit");
                datos[7] = consulta.getString("nombres");
                datos[8] = consulta.getString("idempleado");
                datos[9] = consulta.getString("fechaingreso");
                tabla.addRow(datos);
                
            }

            cn.cerrarconexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
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
            parametro.setString(3, getFechafactura());
            parametro.setInt(4, getIdcliente());
            parametro.setInt(5, getIdempleado());
            parametro.setString(6, getFechaingreso());
            

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

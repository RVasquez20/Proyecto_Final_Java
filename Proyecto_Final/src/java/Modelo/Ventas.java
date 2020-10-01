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
            cn.abrir_conexion();
            String query = "SELECT e.idVentas as id, e.nofactura, e.serie, e.fechafactura, e.idcliente, e.idempleado, e.fechaingreso FROM dbempresa.ventas as e;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id", "nombres", "apellidos", "nit", "genero", "telefono", "correo_electronico", "fechaingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("NIT");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("correo_electronico");
                datos[6] = consulta.getString("fechaingreso");
                datos[7] = consulta.getString("genero");
                tabla.addRow(datos);
                
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
    }
    
     //////////////////////////////////////////////////////////////////////
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO ventas(nofactura,serie,fechafactura,idcliente,idempleado,fechaingreso) VALUES (?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getNofactura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFechafactura());
            parametro.setInt(4, getIdcliente());
            parametro.setInt(5, getIdempleado());
            parametro.setString(6, getFechaingreso());
            

            retorno = parametro.executeUpdate();
            parametro.executeUpdate();
            cn.cerrar_conexion();
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
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getNofactura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFechafactura());
            parametro.setInt(4, getIdcliente());
            parametro.setInt(5, getIdempleado());
            parametro.setString(6, getFechaingreso());
            parametro.setInt(7, getId_venta());

            retorno = parametro.executeUpdate();
            parametro.executeUpdate();
            cn.cerrar_conexion();
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
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_venta());
            retorno = parametro.executeUpdate();
            parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
///////////////////////////////////////////////////////////////////////////

    
    
    
    

}

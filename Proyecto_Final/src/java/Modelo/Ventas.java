/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Willy Valle
 */
public class Ventas extends Clientes {

    private int id, no_factura;
    private String serie, fecha_factura;
    private Conexion cn;

    public Ventas() {
    }

    public Ventas(int id, int no_factura, String serie, String fecha_factura) {
        this.id = id;
        this.no_factura = no_factura;
        this.serie = serie;
        this.fecha_factura = fecha_factura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    /**
     *
     * @return
     */
    @Override
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO ventas(nofactura,serie,fechafactura,idcliente,idempleado,fechaingreso) VALUES (?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getNo_factura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFecha_factura());
            parametro.setInt(4, getId());

            retorno = parametro.executeUpdate();
            parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    /////////////////////////////////////////////////////////////

    /**
     *
     * @return
     */
    @Override
    public int modificar() {

        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE ventas set nofactura=?,serie=?,fechafactura=?,idcliente=?,idempleado=?,fechaingreso=?) WHERE idVentas = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getNo_factura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFecha_factura());
            parametro.setInt(4, getId());

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

    @Override
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM ventas WHERE idVentas = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

}

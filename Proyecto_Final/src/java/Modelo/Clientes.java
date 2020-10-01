/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.PreparedStatement;

/**
 *
 * @author Willy Valle
 */
public class Clientes {

    private int id;
    private String nombres, apellidos, nit, telefono, correo, fecha_ingreso, genero;
    private Conexion cn;

    public Clientes() {
    }

    public Clientes(int id, String nombres, String apellidos, String nit, String telefono, String correo, String fecha_ingreso, String genero) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha_ingreso = fecha_ingreso;
        this.genero = genero;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    //////////////////////////////////////////////////////////////////////
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO clientes(nombres,apellidos,NIT,telefono,correo_electronico,fechaingreso,genero) VALUES (?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getCorreo());
            parametro.setString(6, getFecha_ingreso());
             parametro.setString(7, getGenero());
            

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
            String query = "UPDATE clientes set nombres=?,apellidos=?,NIT=?,telefono=?,correo_electronico=?,fechaingreso=?,genero=?) WHERE idClientes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getCorreo());
            parametro.setString(6, getFecha_ingreso());
            parametro.setString(7, getGenero());
            parametro.setInt(8, getId());

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
            String query = "DELETE FROM clientes WHERE idClientes = ?;";
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
///////////////////////////////////////////////////////////////////////////

   
}

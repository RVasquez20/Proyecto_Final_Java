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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willy Valle
 */
public class Clientes {

    private int id, genero;
    private String nombres, apellidos, nit, telefono, correo, fecha_ingreso;
    private Conexion cn;

    public Clientes() {
    }

    public Clientes(int id, int genero, String nombres, String apellidos, String nit, String telefono, String correo, String fecha_ingreso) {
        this.id = id;
        this.genero = genero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha_ingreso = fecha_ingreso;
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

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT e.idClientes as id, e.nombres, e.apellidos, e.NIT, e.telefono, e.correo_electronico, e.fechaingreso, e.genero FROM dbempresa.clientes as e;";
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
            String query = "INSERT INTO clientes(nombres,apellidos,NIT,telefono,correo_electronico,fechaingreso,genero) VALUES (?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getCorreo());
            parametro.setString(6, getFecha_ingreso());
            parametro.setInt(7, getGenero());

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
            String query = "UPDATE clientes SET nombres=?,apellidos=?,NIT=?,telefono=?,correo_electronico=?,fechaingreso=?,genero=? WHERE idClientes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getCorreo());
            parametro.setString(6, getFecha_ingreso());
            parametro.setInt(7, getGenero());
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

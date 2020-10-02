/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cindy
 */
public class productos {
    private int idproducto,idmarca,existencia;
    private double precio_costo,precio_venta;
    private String producto,descripcion,imagen,fechaDeIngreso;
    private Conexion con;
    public productos() {
    }

    public productos(int idproducto, int idmarca, int existencia, double precio_costo, double precio_venta, String producto, String descripcion, String imagen, String fechaDeIngreso) {
        this.idproducto = idproducto;
        this.idmarca = idmarca;
        this.existencia = existencia;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaDeIngreso = fechaDeIngreso;
    }

  

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public DefaultTableModel Lista(){
        DefaultTableModel tabla=new DefaultTableModel();
        try {
            con=new Conexion();
            con.abrirconexion();
            String query="SELECT p.idProducto as id,p.producto,m.marca,m.idmarca,p.Descripcion,p.Imagen,p.precio_costo,p.precio_venta,p.existencia,p.fecha_ingreso FROM productos as p inner join marcas as m on p.idmarca=m.idmarca order by id;";
            ResultSet consulta=con.conexionbd.createStatement().executeQuery(query);
            String encabezado[]={"id","Producto","Descripcion","Marca","Imagen","Precio_Costo","Precio_venta","Existencias","Fecha De Ingreso","idMarca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]=new String[10];
            while (consulta.next()) {
                datos[0]=consulta.getString("id");
                datos[1]=consulta.getString("producto");
                datos[2]=consulta.getString("marca");
                datos[3]=consulta.getString("Descripcion");
                datos[4]=consulta.getString("Imagen");
                datos[5]=consulta.getString("precio_costo");
                datos[6]=consulta.getString("precio_venta");
                datos[7]=consulta.getString("existencia");
                datos[8]=consulta.getString("fecha_ingreso");
                datos[9]=consulta.getString("idmarca");
                tabla.addRow(datos);
                }
            
            con.cerrarconexion();
            return tabla;
        } catch (SQLException e) {
                System.out.println("Error->"+e.getMessage());
                return tabla;
        }
        
    }
    public int agregar(){
        int retorno=0;
        try {
            con=new Conexion();
            PreparedStatement parametro;
            String query="INSERT INTO productos(producto,idmarca,Descripcion,Imagen,precio_costo,precio_venta,existencia,fecha_ingreso)VALUES(?,?,?,?,?,?,?,?);";
            con.abrirconexion();
            parametro=con.conexionbd.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getIdmarca());
            parametro.setString(3,getDescripcion());
            parametro.setString(4,getImagen());
            parametro.setDouble(5,getPrecio_costo());
            parametro.setDouble(6,getPrecio_venta());
            parametro.setInt(7, getExistencia());
            parametro.setString(8,getFechaDeIngreso());
                    retorno=parametro.executeUpdate();
            con.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
      
    }
        public int modificar(){
        int retorno=0;
        try {
            con=new Conexion();
            PreparedStatement parametro;
            String query="UPDATE  productos SET producto=? ,idmarca=?,Descripcion=?,Imagen=?,precio_costo=?,precio_venta=?,existencia=?,fecha_ingreso=? WHERE idProducto=?;";
            con.abrirconexion();
            parametro=con.conexionbd.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getIdmarca());
            parametro.setString(3,getDescripcion());
            parametro.setString(4,getImagen());
            parametro.setDouble(5,getPrecio_costo());
            parametro.setDouble(6,getPrecio_venta());
            parametro.setInt(7, getExistencia());
            parametro.setString(8,getFechaDeIngreso());
            parametro.setInt(9,getIdproducto());
            
                    retorno=parametro.executeUpdate();
            con.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
      
    }
             public int eliminar(){
        int retorno=0;
        try {
            con=new Conexion();
            PreparedStatement parametro;
            String query="DELETE FROM productos WHERE idProducto=?;";
            con.abrirconexion();
            parametro=con.conexionbd.prepareStatement(query);
            
            parametro.setInt(1,getIdproducto());
            
                    retorno=parametro.executeUpdate();
            con.cerrarconexion(); 
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error->"+e.getMessage());
              return retorno;
        }
        
      
    }
                 public HashMap ListaProductos(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="select idProducto as ID,producto from productos;";
         con = new Conexion();
         con.abrirconexion();
            ResultSet consulta = con.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("ID"),consulta.getString("producto") );
            }
         con.cerrarconexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(String fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }
        
}

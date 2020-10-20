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
 * @author rodri
 */
public class Usuarios {
    private int idUsuario;
    private String Usuario,Pass,Nombres,Apellidos,Correo,Codigo,Foto,clientes,compras_detalle,empleados,marcas,productos,proveedores,puestos,ventas_detalle,NuevoAdmin;
    private Conexion cn;
    
       public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }
   
    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }
    
    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }
       public int ValidarUS(String pass, String usuario,String codigo){
        int retorno = 0;
        
        try {
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select * from usuarios where Usuario='"+usuario+"' and Pass='"+pass+"' and Codigo='"+codigo+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            if (rs.next()) {
                if (rs.getString(1).equals(pass)||rs.getString(2).equals(usuario)||rs.getString(3).equals(codigo)) {
                    retorno=1;
                }
                else {
                    return retorno=0;
                }
            }
            cn.cerrarconexion();
        }
        catch (Exception ex) {
            retorno = 0;
        }
        return retorno;
    }
     
        public String Name(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select Nombre from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("Nombre");
            }
            cn.cerrarconexion();
        
        return retorno;
    }
         public String Email(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select Correo from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("Correo");
            }
            cn.cerrarconexion();
        
        return retorno;
    }
           public String Foto(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select Foto from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("Foto");
            }
            cn.cerrarconexion();
        
        return retorno;
    }
           
           public String tipe(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select Tipo from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("Tipo");
            }
            cn.cerrarconexion();
        
        return retorno;
    }
           
  public HashMap Menu(String usuario) throws SQLException{
       HashMap<String,String> lista=new HashMap();
        String retorno = null;
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select Tipo from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("Tipo");
            }
             String query ="select nombre,URL from menu where tipousuario='"+retorno+"';";
         cn = new Conexion();
         cn.abrirconexion();
            ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()){
            lista.put(consulta.getString("nombre"),consulta.getString("URL"));
            }
         cn.cerrarconexion();
        return lista;
    }

        public int NuevoUsuario() {
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            String sqlinsert;
            cn = new Conexion();
            cn.abrirconexion();
            sqlinsert = "insert into Usuarios(Usuario,Nombre,Apellidos,Correo,Pass,Foto,Codigo,Tipo) values(?,?,?,?,?,?,?,?)";
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(sqlinsert);
            parametro.setString(1, getUsuario());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getCorreo());
            parametro.setString(5, getPass());
            parametro.setString(6, getFoto());
            parametro.setString(7, getCodigo());
            parametro.setString(8, "USER");
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }
        catch (Exception ex) {
            retorno=0;
        }
        return retorno; 
    }
         public int NuevoAdmin() {
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            String sqlinsert;
            cn = new Conexion();
            cn.abrirconexion();
      
            sqlinsert = "insert into Usuarios(Usuario,Nombre,Apellidos,Correo,Pass,Foto,Codigo,Tipo,clientes,compras_detalle,empleados,marcas,productos,proveedores,puestos,ventas_detalle,NuevoAdmin) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(sqlinsert);
            parametro.setString(1, getUsuario());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getCorreo());
            parametro.setString(5, getPass());
            parametro.setString(6, getFoto());
            parametro.setString(7, getCodigo());
            parametro.setString(8, "ADMIN");
            parametro.setString(9, "1");
            parametro.setString(10, "1");
            parametro.setString(11, "1");
            parametro.setString(12, "1");
            parametro.setString(13, "1");
            parametro.setString(14, "1");
            parametro.setString(15, "1");
            parametro.setString(16, "1");
            parametro.setString(17, "1");
            
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }
        catch (Exception ex) {
            retorno=0;
        }
        return retorno; 
    }
         
         
            public int ModificarUsuario() {
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            String sqlinsert;
            cn = new Conexion();
            cn.abrirconexion();
            sqlinsert = "Update usuarios set clientes=?,compras_detalle=?,empleados=?,marcas=?,productos=?,proveedores=?,puestos=?,ventas_detalle=?,NuevoAdmin=? where idUsuarios=?";
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(sqlinsert);
            
            parametro.setString(1, getClientes());
            parametro.setString(2, getCompras_detalle());
            parametro.setString(3, getEmpleados());
            parametro.setString(4, getMarcas());
            parametro.setString(5, getProductos());
            parametro.setString(6, getProveedores());
            parametro.setString(7, getPuestos());
            parametro.setString(8, getVentas_detalle());
            parametro.setString(9, getNuevoAdmin());
            parametro.setInt(10, getIdUsuario());
            
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }
        catch (Exception ex) {
            System.out.println("error ptm-->"+ex.getMessage());
            retorno=0;
        }
        return retorno; 
    } 
            
                 public int EliminarUsuario() {
        int retorno = 0;
        
        try {
            PreparedStatement parametro;
            String sqlinsert;
            cn = new Conexion();
            cn.abrirconexion();
            sqlinsert = "Update from usuarios where idUsuarios=?";
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(sqlinsert);
            parametro.setInt(1, getIdUsuario());
            
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }
        catch (SQLException ex) {
            retorno=0;
        }
        return retorno; 
    } 
            
         
          public String Marcas(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select marcas from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("marcas");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
          public String Productos(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select productos from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("productos");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
     
           public String Clientes(String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select clientes from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("clientes");
            }
            cn.cerrarconexion();
        
        return retorno;
        
    }   
         
            public String ComprasDetalle (String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select compras_detalle from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("compras_detalle");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
         
           public String Empleado (String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select empleados from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("empleados");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
            public String Proveedores (String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select proveedores from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("proveedores");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
          public String Puestos (String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select puestos from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("puestos");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
          public String VentasDetalles (String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select ventas_detalle from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("ventas_detalle");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
           public String NuevoAd (String usuario) throws SQLException{
        String retorno = null;
        
       
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select NuevoAdmin from usuarios where Usuario='"+usuario+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("NuevoAdmin");
            }
            cn.cerrarconexion();
        
        return retorno;
    }   
           
            public DefaultTableModel ListaUsuarios(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
     String Tipe="USER";
      String query = "SELECT idUsuarios,Usuario,Nombre,Apellidos,Correo,Pass,Foto,Codigo,Tipo,clientes,compras_detalle,empleados,marcas,productos,proveedores,puestos,ventas_detalle,NuevoAdmin FROM usuarios where Tipo='"+Tipe+"';";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"idUsuarios","Usuario","Nombre","Apellidos","Correo","Pass","Foto","Codigo","Tipo","clientes","compras_detalle","empleados","marcas","productos","proveedores","puestos","ventas_detalle","NuevoAdmin"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[18];
      while (consulta.next()){
          datos[0] = consulta.getString("idUsuarios");
          datos[1] = consulta.getString("Usuario");
          datos[2] = consulta.getString("Nombre");
          datos[3] = consulta.getString("Apellidos");
          datos[4] = consulta.getString("Correo");
          datos[5] = consulta.getString("Pass");
          datos[6] = consulta.getString("Foto");
          datos[7] = consulta.getString("Codigo");
          datos[8] = consulta.getString("Tipo");
          datos[9] = consulta.getString("clientes");
          datos[10] = consulta.getString("compras_detalle");
          datos[11] = consulta.getString("empleados");
          datos[12] = consulta.getString("marcas");
          datos[13] = consulta.getString("productos");
          datos[14] = consulta.getString("proveedores");
          datos[15] = consulta.getString("puestos");
          datos[16] = consulta.getString("ventas_detalle");
          datos[17] = consulta.getString("NuevoAdmin");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }

    public String getClientes() {
        return clientes;
    }

    public void setClientes(String clientes) {
        this.clientes = clientes;
    }

    public String getCompras_detalle() {
        return compras_detalle;
    }

    public void setCompras_detalle(String compras_detalle) {
        this.compras_detalle = compras_detalle;
    }

    public String getEmpleados() {
        return empleados;
    }

    public void setEmpleados(String empleados) {
        this.empleados = empleados;
    }

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getProveedores() {
        return proveedores;
    }

    public void setProveedores(String proveedores) {
        this.proveedores = proveedores;
    }

    public String getPuestos() {
        return puestos;
    }

    public void setPuestos(String puestos) {
        this.puestos = puestos;
    }

    public String getVentas_detalle() {
        return ventas_detalle;
    }

    public void setVentas_detalle(String ventas_detalle) {
        this.ventas_detalle = ventas_detalle;
    }

    public String getNuevoAdmin() {
        return NuevoAdmin;
    }

    public void setNuevoAdmin(String NuevoAdmin) {
        this.NuevoAdmin = NuevoAdmin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
                


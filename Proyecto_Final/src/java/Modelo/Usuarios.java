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

/**
 *
 * @author rodri
 */
public class Usuarios {

    private String Usuario,Pass,Nombres,Apellidos,Correo,Codigo,Foto;
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
       public int ValidarUS(String pass, String usuario){
        int retorno = 0;
        
        try {
            cn = new Conexion();
            cn.abrirconexion();
            PreparedStatement parametro;
            String Query = "select * from usuarios where Usuario='"+usuario+"' and Pass='"+pass+"';";
            parametro = cn.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            if (rs.next()) {
                if (rs.getString(1).equals(pass)||rs.getString(2).equals(usuario)) {
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
            sqlinsert = "insert into Usuarios(Usuario,Nombre,Apellidos,Correo,Pass,Foto,Codigo,Tipo) values(?,?,?,?,?,?,?,?)";
            parametro = (PreparedStatement) cn.conexionbd.prepareStatement(sqlinsert);
            parametro.setString(1, getUsuario());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getCorreo());
            parametro.setString(5, getPass());
            parametro.setString(6, getFoto());
            parametro.setString(7, getCodigo());
            parametro.setString(8, "ADMIN");
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }
        catch (Exception ex) {
            retorno=0;
        }
        return retorno; 
    }
}
                


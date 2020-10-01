/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;


public class Empleado{

    private int id_puesto;
        private int id,Genero;
    private String nombres,apellidos,direccion,telefono,fecha_nacimiento,DPI,FechaDeIngreso,FechaInicio;
    private Conexion cn;
    public Empleado() {}

    public Empleado(int id_puesto, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String DPI, String FechaDeIngreso, String FechaInicio, int Genero) {
        this.id_puesto = id_puesto;
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.DPI = DPI;
        this.FechaDeIngreso = FechaDeIngreso;
        this.FechaInicio = FechaInicio;
        this.Genero = Genero;
    }

   

  

  

    


    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
 public DefaultTableModel leer(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrirconexion();
      String query = "SELECT e.idEmpleado as id,e.nombres,e.apellidos,e.direccion,e.telefono,e.DPI,p.idPuesto,p.puesto,e.fecha_nacimiento,e.fecha_inicio_labores,e.fechaingreso,e.genero FROM empleados as e inner join puestos as p where e.idPuesto = p.idPuesto;";
      ResultSet consulta = cn.conexionbd.createStatement().executeQuery(query);
      String encabezado[] = {"id","nombres","apellidos","direccion","telefono","DPI","Fecha De nacimiento","fecha inicio de labores","fecha ingreso","puesto","id_puesto","genero"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[12];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("nombres");
          datos[2] = consulta.getString("apellidos");
          datos[3] = consulta.getString("direccion");
          datos[4] = consulta.getString("telefono");
          datos[5] = consulta.getString("DPI");
          datos[6] = consulta.getString("idPuesto");
          datos[7] = consulta.getString("puesto");
          datos[8] = consulta.getString("fecha_nacimiento");
          datos[9] = consulta.getString("fecha_inicio_labores");
          datos[10] = consulta.getString("fechaingreso");
          datos[11] = consulta.getString("genero");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarconexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }

    public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into empleados(nombres,apellidos,direccion,telefono,DPI,idPuesto,fecha_nacimiento,fecha_inicio_labores,fechaingreso,genero) values(?,?,?,?,?,?,?,?,?,?);";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getDPI());
            parametro.setInt(6,getId_puesto());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setString(8, getFechaInicio());
            parametro.setString(9, getFechaDeIngreso());
            parametro.setInt(10, getGenero());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    

    public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update empleados set nombres= ?,apellidos= ?,direccion= ?,telefono= ?,DPI= ?,idPuesto= ?,fecha_nacimiento= ?,fecha_inicio_labores= ?,fechaingreso= ?,genero= ? where idEmpleado = ?;";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getDPI());
            parametro.setInt(6,getId_puesto());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setString(8, getFechaInicio());
            parametro.setString(9, getFechaDeIngreso());
            parametro.setInt(10, getGenero());
            parametro.setInt(11, getId());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }

    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from empleados  where idEmpleado = ?;";
            cn.abrirconexion();
            parametro = (PreparedStatement)cn.conexionbd.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrarconexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getFechaDeIngreso() {
        return FechaDeIngreso;
    }

    public void setFechaDeIngreso(String FechaDeIngreso) {
        this.FechaDeIngreso = FechaDeIngreso;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int Genero) {
        this.Genero = Genero;
    }


   
}

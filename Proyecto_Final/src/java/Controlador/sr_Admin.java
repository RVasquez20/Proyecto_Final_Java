/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuarios;
import Modelo.productos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author rodri
 */
@MultipartConfig
public class sr_Admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */Usuarios obj = new Usuarios();
     Usuarios obj2 = new Usuarios();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_Admin</title>");            
            out.println("</head>");
            out.println("<body>");
                 String n = request.getParameter("nombre");
                 obj.setFoto(n);
            obj.setUsuario(request.getParameter("txt_usuarionuevo"));
           obj.setNombres(request.getParameter("txt_nombre"));
           obj.setApellidos(request.getParameter("txt_apellidos"));
          obj.setCorreo(request.getParameter("txt_correo"));
            obj.setPass(request.getParameter("txt_passnueva"));
            obj.setCodigo(request.getParameter("txt_codigo"));
            if (request.getParameter("Registrar") != null) {
                Part archivo = request.getPart("archivo");
                    InputStream is = archivo.getInputStream();
                    File f = new File("C:/Users/rodri/Documents/GitHub/Proyecto_Final_Java/Proyecto_Final/web/sources/"+n);
                    FileOutputStream ous = new FileOutputStream(f);
                    
                    int dato = is.read();
                    while (dato != -1) {
                        ous.write(dato);
                        dato = is.read();
                    }
                    ous.close();
                    is.close();
                    if (obj.NuevoAdmin() > 0) {
                        out.print("<script>alert('Agregado Correctamente');</script>");
                        response.sendRedirect("NuevoAdmin.jsp");
                    }
             

         
            }else if (request.getParameter("Modificar") != null) {
             
             
            /*  out.println("<h1> id: "+Integer.parseInt(request.getParameter("txt_id")));
            out.println("<h1> us: "+request.getParameter("txt_usuarionuevo"));
            out.println("<h1> n: "+request.getParameter("txt_nombre"));
            out.println("<h1> ap: "+request.getParameter("txt_apellidos"));
            out.println("<h1> c: "+request.getParameter("txt_correo"));
            out.println("<h1> pass: "+request.getParameter("txt_passnueva"));
            out.println("<h1> img: "+request.getParameter("imagenes"));
            out.println("<h1> cod: "+request.getParameter("txt_codigo"));
                    String clientes=null;
              if(request.getParameter("drop_clientes").equals("on")){
                  clientes="1";
              }
            out.println("<h1> cli: "+clientes);    
                        out.println("<h1>Fin </h1>"); */
            obj2.setIdUsuario(Integer.parseInt(request.getParameter("txt_id")));
             String clientes=null;
              if(request.getParameter("drop_clientes").equals("on")){
                  clientes="1";
              }obj2.setClientes(clientes);
              
              String cd=null;
              if(request.getParameter("drop_compras_detalle").equals("on")){
                  cd="1";
              }
              obj2.setCompras_detalle(cd);
              
              String emp=null;
              if(request.getParameter("drop_empleados").equals("on")){
                  emp="1";
              }
              obj2.setEmpleados(emp);
              
              String marc=null;
              if(request.getParameter("drop_marcas").equals("on")){
                  marc="1";
              }
              obj2.setMarcas(marc);
              
              String prod=null;
              if(request.getParameter("drop_productos").equals("on")){
                  prod="1";
              }
              obj2.setProductos(prod);
              
              String prov=null;
              if(request.getParameter("drop_proveedores").equals("on")){
                  prov="1";
              }
              obj2.setProveedores(prov);
              
              String pues=null;
              if(request.getParameter("drop_puestos").equals("on")){
                  pues="1";
              }
              obj2.setPuestos(pues);
              
              String vendt=null;
              if(request.getParameter("drop_ventas_detalle").equals("on")){
                  vendt="1";
              }
              obj2.setVentas_detalle(vendt);
              
              String NA=null;
              if(request.getParameter("drop_NuevoAdmin").equals("on")){
                  NA="1";
              }
              obj2.setNuevoAdmin(NA);
/*out.println("<h1> cli: "+clientes);    
out.println("<h1> compd: "+cd);    
out.println("<h1> emp: "+emp);    
out.println("<h1> prod: "+prod);    
out.println("<h1> marc: "+marc);    
out.println("<h1> pro: "+prov);    
out.println("<h1> cli :"+pues);    
out.println("<h1> cli: "+vendt);    
out.println("<h1> cli: "+NA);   */ 
              if(obj2.ModificarUsuario()>0){
                    
               response.sendRedirect("NuevoAdmin.jsp");
                     
               }else{
                    out.println("<h1>Error al registrar...</h1>");
               }
           }  else if (request.getParameter("Eliminar") != null) {
                obj2.setIdUsuario(Integer.parseInt(request.getParameter("txt_id")));
              if(obj2.EliminarUsuario()>0){
                   response.sendRedirect("NuevoAdmin.jsp");
          }else{
               out.println("<h1>Error al registrar...</h1>");
          }
        }
            else {
                    out.println("<h1>Error al registrar...</h1>");
                }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

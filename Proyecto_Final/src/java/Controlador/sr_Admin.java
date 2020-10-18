/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuarios;
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
                    File f = new File("C:/Users/jeron/Documents/GitHub/Proyecto_Final_Java/Proyecto_Final/web/sources/"+n);
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
            } else {
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

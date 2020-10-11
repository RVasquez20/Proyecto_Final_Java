/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodri
 */
public class sr_login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Usuarios us;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            us=new Usuarios();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_login</title>");            
            out.println("</head>");
            out.println("<body>");
     Usuarios obj = new Usuarios();
            
           String pass,usuario;
            pass = request.getParameter("txt_pass");
            usuario = request.getParameter("txt_usuario");
            
            obj.setUsuario(request.getParameter("txt_usuario"));
           // obj.setNombres(request.getParameter("txt_nombre"));
            //obj.setApellidos(request.getParameter("txt_apellidos"));
            //obj.setCorreo(request.getParameter("txt_correo"));
            obj.setPass(request.getParameter("txt_pass"));
            //obj.setCODIGO(request.getParameter("txt_codigo"));

          /*  if (request.getParameter("Registrar") != null) {
                if (obj.NuevoUsuario() > 0) {
                    HttpSession actual = request.getSession(true);
                    actual.setAttribute("Logueado", usuario);
                    response.sendRedirect("principal.jsp");
                }
                else {
                    out.println("<h1>Error al registrar...</h1>");
                }
            }
            */
            /*else*/ if (request.getParameter("Ingresar") != null) {
                if(obj.ValidarUS(pass,usuario) > 0) {
                    String nombre=obj.Name(usuario);
                    String email=obj.Email(usuario);
                    String profile=obj.Foto(usuario);
                    HashMap<String,String> Lista=obj.Menu(usuario);
                    HttpSession actual = request.getSession(true);
                    actual.setAttribute("Logueado", usuario);
                    actual.setAttribute("nom", nombre);
                    actual.setAttribute("em", email);
                    actual.setAttribute("Ft", profile);
                    actual.setAttribute("Men", Lista);
                    response.sendRedirect("Principal.jsp");
                }
                else {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>USUARIO NO ENCONTRADO</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>USUARIO NO ENCONTRADO</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            else if(request.getParameter("cerrarsesion") != null) {
                request.getSession().removeAttribute("Logueado");
                response.sendRedirect("index.jsp");
            }
            /*String accion=request.getParameter("accion");
            if(accion.equals("Ingresar")){
                String user=request.getParameter("txt_usuario");
                String pass=request.getParameter("txt_pass");
                if(us.ValidarUS(pass, user) > 0) {
                    HttpSession actual = request.getSession(true);
                    actual.setAttribute("Logueado", user);
                    response.sendRedirect("Principal.jsp");
                }
                else {
                    response.sendRedirect("index.jsp");
                }
          
                
            } 
               else if(request.getParameter("cerrarsesion") != null) {
               /* request.getSession().removeAttribute("Logueado");
                response.sendRedirect("index.jsp");*/
             /*  out.println("<h1>"+request.getParameter("cerrarsesion")+"</h1>");
            }*/
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sr_login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sr_login.class.getName()).log(Level.SEVERE, null, ex);
        }
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

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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author rodri
 */
@MultipartConfig
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_login</title>");            
            out.println("</head>");
            out.println("<body>");
     Usuarios obj = new Usuarios();
            String n = request.getParameter("nombre");
           String pass,usuario,cod;
            pass = request.getParameter("txt_pass");
            usuario = request.getParameter("txt_usuario");
            cod=request.getParameter("txt_cod");
            obj.setFoto(n);
            obj.setUsuario(request.getParameter("txt_usuarionuevo"));
           obj.setNombres(request.getParameter("txt_nombre"));
           obj.setApellidos(request.getParameter("txt_apellidos"));
          obj.setCorreo(request.getParameter("txt_correo"));
            obj.setPass(request.getParameter("txt_passnueva"));
            obj.setCodigo(obj.Generador());

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
                if (obj.NuevoUsuario() > 0) {
          Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        String correoEnvia = "jeronimovillalta1997@gmail.com";
        String contrasena = "jero.1997";
        String destinatario = request.getParameter("txt_correo");
        String asunto = "Creacion de cuenta ";
        String mensaje ="Su cuenta esta siendo procesada su codigo es "+obj.getCodigo();
        
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia,contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
           
            
             response.sendRedirect("Correo.jsp");
            
            
            
        } catch (AddressException ex) {
            System.out.println("Error->"+ex.getMessage());
        } catch (MessagingException ex) {
              System.out.println("Error->"+ex.getMessage());
        }
        
                   
                }
                
                else {
                    out.println("<h1>Error al registrar...</h1>");
                }
            /*out.println("<h2>n:"+request.getParameter("nombre")+"</h2>");
            out.println("<h2> Usuario:"+request.getParameter("txt_usuarionuevo")+"</h2>");
            out.println("<h2>nombre:"+request.getParameter("txt_nombre")+"</h2>");
            out.println("<h2>apellido:"+request.getParameter("txt_apellidos")+"</h2>");
            out.println("<h2>correo:"+request.getParameter("txt_correo")+"</h2>");
            out.println("<h2>codigo:"+request.getParameter("txt_codigo")+"</h2>");
            out.println("<h2>pass:"+request.getParameter("txt_passnueva")+"</h2>");*/
            }
            
            /*else*/ if (request.getParameter("Ingresar") != null) {
                if(obj.ValidarUS(pass,usuario,cod) > 0) {
                    String nombre=obj.Name(usuario);
                    String email=obj.Email(usuario);
                    String profile=obj.Foto(usuario);
                    String tipo=obj.tipe(usuario);
                    HashMap<String,String> Lista=obj.Menu(usuario);
                    HttpSession actual = request.getSession(true);
                    actual.setAttribute("Logueado", usuario);
                    actual.setAttribute("T", tipo);
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

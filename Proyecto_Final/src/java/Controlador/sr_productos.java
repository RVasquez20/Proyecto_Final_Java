/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.productos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rodri
 */
public class sr_productos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    productos producto;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Productos</title>");            
            out.println("</head>");
            out.println("<body>");
            /*out.println("<h2>sources/"+request.getParameter("txt_Imagen")+"</h2>");
            
            out.println("<h2>"+Integer.parseInt(request.getParameter("txt_exitencias"))+"</h2>");
            out.println("<h2>"+Double.parseDouble(request.getParameter("txt_preciocosto"))+"</h2>");
            out.println("<h2>"+Double.parseDouble(request.getParameter("txt_precioventa"))+"</h2>");
            out.println("<h2>"+request.getParameter("txt_producto")+"</h2>");
            out.println("<h2>"+request.getParameter("txt_descripcion")+"</h2>");*/
            producto=new productos(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("box_marcas")),Integer.parseInt(request.getParameter("txt_exitencias")),
            Double.parseDouble(request.getParameter("txt_preciocosto")),Double.parseDouble(request.getParameter("txt_precioventa")),
            request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),request.getParameter("txt_Imagen"),request.getParameter("txt_FechaIngreso"));
           if("agregar".equals(request.getParameter("btn_agregar"))){
               
               if(producto.agregar()>0){
                    response.sendRedirect("index.jsp"); 
               }else{
                   out.println("<script>alert('Error');</scrpt>");
               }
           }
          
              if("modificar".equals(request.getParameter("btn_modificar"))){
              
               if(producto.modificar()>0){
                    response.sendRedirect("index.jsp"); 
               }else{
                   out.println("<script>alert('Error');</scrpt>");
               }
           }
              
                 if("eliminar".equals(request.getParameter("btn_eliminar"))){
              
               if(producto.eliminar()>0){
                    response.sendRedirect("index.jsp"); 
               }else{
                   out.println("<script>alert('Error');</scrpt>");
               }
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

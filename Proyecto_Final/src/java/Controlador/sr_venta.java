/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ventas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Willy Valle
 */
public class sr_venta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Ventas ventas;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_venta</title>");
            out.println("</head>");
            out.println("<body>");
            
            /*out.println("<h1>" + Integer.parseInt(request.getParameter("txt_id")) + "</h1>");
            out.println("<h1>" + Integer.valueOf(request.getParameter("txt_nofactura")) + "</h1>");
            out.println("<h1>" + Integer.parseInt(request.getParameter("txt_idcliente")) + "</h1>");
            out.println("<h1>" + Integer.parseInt(request.getParameter("txt_idempleado")) + "</h1>");
            out.println("<h1>" + request.getParameter("txt_serie") + "</h1>");
            out.println("<h1>" + request.getParameter("txt_fechafactura") + "</h1>");
            out.println("<h1>" + request.getParameter("txt_fechaingreso") + "</h1>");*/

            ventas = new Ventas(Integer.parseInt(request.getParameter("txt_id")), Integer.parseInt(request.getParameter("txt_nofactura")), Integer.parseInt(request.getParameter("txt_idcliente")), Integer.parseInt(request.getParameter("txt_idempleado")), request.getParameter("txt_serie"),request.getParameter("txt_fechafactura"), request.getParameter("txt_fechaingreso"));

            //Boton Agregar
            if ("agregar".equals(request.getParameter("btn_agregar"))) {
                if (ventas.agregar() > 0) {
                    response.sendRedirect("ventas.jsp");
                } else {
                    out.println("<h1>Error..........................</h1>");
                    out.println("<a href ='ventas.jsp'>Regresar<a>");
                }
            }

            //Boton Modificar
            if ("modificar".equals(request.getParameter("btn_modificar"))) {
                if (ventas.modificar() > 0) {
                    response.sendRedirect("ventas.jsp");
                } else {
                    out.println("<h1>Error..........................</h1>");
                    out.println("<a href ='ventas.jsp'>Regresar<a>");
                }
            }

            //Boton Eliminar
            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                if (ventas.eliminar() > 0) {
                    response.sendRedirect("ventas.jsp");
                } else {
                    out.println("<h1>Error..........................</h1>");
                    out.println("<a href ='ventas.jsp'>Regresar<a>");
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

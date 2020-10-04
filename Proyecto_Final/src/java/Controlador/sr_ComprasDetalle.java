/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Compras;
import Modelo.ComprasDetalle;
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
public class sr_ComprasDetalle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ComprasDetalle detalle,detalleantiguo;  
    Compras compra;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_ComprasDetalle</title>");            
            out.println("</head>");
            out.println("<body>");
            /*out.println("<h1>Id CP"+Integer.parseInt(request.getParameter("txt_id"))+"</h1>");
            out.println("<h1>no orden "+Integer.parseInt(request.getParameter("txt_No_Orden"))+"</h1>");
            out.println("<h1>id producto "+Integer.parseInt(request.getParameter("drop_Producto"))+"</h1>");
            out.println("<h1>Cantidad "+Integer.parseInt(request.getParameter("txt_Cantidad"))+"</h1>");
            out.println("<h1>precio "+Double.parseDouble(request.getParameter("txt_PrecioUnitario"))+"</h1>");
            
            out.println("<h1>Id C"+Integer.parseInt(request.getParameter("txt_id_Compra"))+"</h1>");
            out.println("<h1>no orden "+Integer.parseInt(request.getParameter("txt_No_Orden"))+"</h1>");
            out.println("<h1>proveedor "+Integer.parseInt(request.getParameter("ListaProveedores"))+"</h1>");
            out.println("<h1>fecha orden  "+request.getParameter("txt_Fecha_Orden")+"</h1>");
            out.println("<h1>ingreso "+request.getParameter("txt_Fecha_Ingreso")+"</h1>");*/
       
            detalleantiguo=new ComprasDetalle();
            int x=detalleantiguo.cantidadantigua(Integer.parseInt(request.getParameter("txt_id")));
             detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
            compra=new Compras(Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("ListaProveedores")),request.getParameter("txt_Fecha_Orden"),request.getParameter("txt_Fecha_Ingreso"));
            if ("agregar".equals(request.getParameter("btn_agregar"))){
                
             if ((compra.agregar()>0)){
                 if ((detalle.agregar()>0)&&(detalle.ActualizarExistencias()>0)&&(detalle.ActualizarPrecioCosto()>0)&&(detalle.ActualizarPrecioventa()>0)){ 
             response.sendRedirect("Compras_Detalle.jsp");
                 }
             }else{
             out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
             }
             }
           if ("modificar".equals(request.getParameter("btn_modificar"))){
             if (compra.Modificar()>0){
                  if (detalle.Modificar()>0){
                      if(Integer.parseInt(request.getParameter("txt_Cantidad"))>x){
                     detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));    
                      }else if(Integer.parseInt(request.getParameter("txt_Cantidad"))<x){
                     detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));    
                      }else{
                      detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad"))-x,Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
                      }
                      if((detalle.ActualizarExistencias()>0)&&(detalle.ActualizarPrecioCosto()>0)&&(detalle.ActualizarPrecioventa()>0)){ 
             response.sendRedirect("Compras_Detalle.jsp");
                 }
                  }
             
             }else{
             out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
             }
             }
            if ("eliminar".equals(request.getParameter("btn_eliminar"))){
             if (detalle.Eliminar()>0){
                 detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("drop_Producto")),(0-Integer.parseInt(request.getParameter("txt_Cantidad"))),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
                 detalle.ActualizarExistencias();
                  if ((compra.Eliminar()>0)){ 
             response.sendRedirect("Compras_Detalle.jsp");
                 }
            
             
             }else{
             out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
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

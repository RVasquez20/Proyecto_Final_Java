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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ComprasDetalle detalle,detalleantiguo,c;  
    Compras compra;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
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
               Compras obj1=new Compras();
            int idc;
            idc=obj1.idfordetalle(Integer.parseInt(request.getParameter("txt_No_Orden")));
            out.println("<h1>Id C"+Integer.parseInt(request.getParameter("txt_id_Compra"))+"</h1>");
            out.println("<h1>no orden "+Integer.parseInt(request.getParameter("txt_No_Orden"))+"</h1>");
            out.println("<h1>proveedor "+Integer.parseInt(request.getParameter("ListaProveedores"))+"</h1>");
            out.println("<h1>fecha orden  "+request.getParameter("txt_Fecha_Orden")+"</h1>");
            out.println("<h1>ingreso "+request.getParameter("txt_Fecha_Ingreso")+"</h1>");
            out.println("<h1>Id compra "+idc+"</h1>");
       
            */
              if("next".equals(request.getParameter("btn_next"))){
                  compra=new Compras(Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("ListaProveedores")),request.getParameter("txt_Fecha_Orden"),request.getParameter("txt_Fecha_Ingreso"));
                  if(compra.agregar()>0){
                  c=new ComprasDetalle();
               int q=c.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("Compras_Detalle_Productos.jsp").forward(request, response);
                  }else{
                    out.println("<h1> xxxxxxx No se Ingreso venta xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle_Productos.jsp'>Regresar...</a>");
              }
              }else if("modificarp".equals(request.getParameter("btn_modificarp"))){
                  detalleantiguo=new ComprasDetalle();
            int x=detalleantiguo.cantidadantigua(Integer.parseInt(request.getParameter("txt_id")));
                               detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
             if((Integer.parseInt(request.getParameter("txt_Cantidad"))>0)){
                               if(detalle.Modificar()>0){
                          if(Integer.parseInt(request.getParameter("txt_Cantidad"))>x){
                     detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));    
                      }else if(Integer.parseInt(request.getParameter("txt_Cantidad"))<x){
                     detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));    
                      }else{
                      detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad"))-x,Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
                      }
                    if((detalle.ActualizarExistencias()>0)&&(detalle.ActualizarPrecioCosto()>0)&&(detalle.ActualizarPrecioventa()>0)){
                    c=new ComprasDetalle();
               int q=c.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("Compras_Detalle_Productos.jsp").forward(request, response);
                    }
                
                   
              }
              }else{
                    out.println("<h1> xxxxxxx No se modifico xxxxxxxxxxxx </h1>");
              
              }
              }
              else if("eliminarp".equals(request.getParameter("btn_eliminarp"))){
                 detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
              if(detalle.Eliminar()>0){
              detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),(0-Integer.parseInt(request.getParameter("txt_Cantidad"))),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
                  if(detalle.ActualizarExistencias()>0){
               c=new ComprasDetalle();
               int q=c.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("Compras_Detalle_Productos.jsp").forward(request, response);
                    }
                
                   
              }else{
                    out.println("<h1> xxxxxxx No se modifico xxxxxxxxxxxx </h1>");
        c=new ComprasDetalle();
               int q=c.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("Compras_Detalle_Productos.jsp").forward(request, response);
              }
              }
              else
             if("agregar".equals(request.getParameter("btn_agregar"))){
              detalle=new ComprasDetalle(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));   
                 if ((Integer.parseInt(request.getParameter("txt_Cantidad"))>0)) {
                     
                 
           if((detalle.agregar()>0)&&(detalle.ActualizarExistencias()>0)&&(detalle.ActualizarPrecioCosto()>0)&&(detalle.ActualizarPrecioventa()>0)){
                   
                 
                 int q=c.lastid();
              
               request.setAttribute("q", q);
                  request.getRequestDispatcher("Compras_Detalle_Productos.jsp").forward(request, response);
           }
           else{
                    out.println("<h1> xxxxxxx No se Ingreso vd xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle_Productos.jsp'>Regresar...</a>");
              }
           }else{
                    out.println("<h1> xxxxxxx No se Ingreso vd xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle_Productos.jsp'>Regresar...</a>");
              }
              
             } 
             else if("fin".equals(request.getParameter("btn_fin"))){
                 response.sendRedirect("Compras_Detalle.jsp");
             }else if ("modificar".equals(request.getParameter("btn_modificar"))){
                  compra=new Compras(Integer.parseInt(request.getParameter("txt_id_Compra")),Integer.parseInt(request.getParameter("txt_No_Orden")),Integer.parseInt(request.getParameter("ListaProveedores")),request.getParameter("txt_Fecha_Orden"),request.getParameter("txt_Fecha_Ingreso"));
              if(compra.Modificar()>0){
                 
                   response.sendRedirect("Compras_Detalle.jsp");
              }else{
                    out.println("<h1> xxxxxxx No se modifico xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
              }
                 
             }else{
                    out.println("<h1> xxxxxxx No se Ingreso producto xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sr_ComprasDetalle.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(sr_ComprasDetalle.class.getName()).log(Level.SEVERE, null, ex);
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

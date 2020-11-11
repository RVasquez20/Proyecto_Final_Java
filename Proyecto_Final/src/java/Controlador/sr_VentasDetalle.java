/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Ventas;
import Modelo.VentasDetalle;
import Modelo.productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class sr_VentasDetalle extends HttpServlet {

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
    VentasDetalle detalleantiguo,detalle,v;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VentasDetalle</title>");            
            out.println("</head>");
            out.println("<body>");
          
              
              if("next".equals(request.getParameter("btn_next"))){
                 ventas = new Ventas(Integer.parseInt(request.getParameter("txt_idventas")), Integer.parseInt(request.getParameter("txt_nofactura")), Integer.parseInt(request.getParameter("txt_idcliente")), Integer.parseInt(request.getParameter("txt_idempleado")), request.getParameter("txt_serie"),request.getParameter("txt_fechafactura"), request.getParameter("txt_fechaingreso"));
                  if(ventas.agregar()>0){
                  v=new VentasDetalle();
               int q=v.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("VentasDetalleProductos.jsp").forward(request, response);
                  }else{
                    out.println("<h1> xxxxxxx No se Ingreso venta xxxxxxxxxxxx </h1>");
             out.println("<a href='VentasDetalle.jsp'>Regresar...</a>");
              }
              }else if("modificarp".equals(request.getParameter("btn_modificarp"))){
                  detalleantiguo=new VentasDetalle();
                              int x=detalleantiguo.cantidadantigua(Integer.parseInt(request.getParameter("txt_id_Ventas")));
                              detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),
                          Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),
                          Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
                               productos obj1=new productos();
              int ex=obj1.ex(Integer.parseInt(request.getParameter("drop_Producto")));
               
           if((ex>=(Integer.parseInt(request.getParameter("txt_Cantidad")))-x)&&(Integer.parseInt(request.getParameter("txt_Cantidad")))>0){
              if(detalle.Modificar()>0){
                 if(Integer.parseInt(request.getParameter("txt_Cantidad"))>x){
                      
                        detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
            } else if(x>Integer.parseInt(request.getParameter("txt_Cantidad"))){
                        detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
                    }else{
                        if(Integer.parseInt(request.getParameter("txt_Cantidad"))==x){
                        detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),Integer.parseInt(request.getParameter("drop_Producto")),(Integer.parseInt(request.getParameter("txt_Cantidad"))-x),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
                    }
                    }
                    if(detalle.ActualizarExistencias()>0){
                     v=new VentasDetalle();
               int q=v.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("VentasDetalleProductos.jsp").forward(request, response);
                    }
                
                   
              }else{
                    out.println("<h1> xxxxxxx No se modifico xxxxxxxxxxxx </h1>");
             v=new VentasDetalle();
               int q=v.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("VentasDetalleProductos.jsp").forward(request, response);
              }
              }  else{
                    out.println("<h1> xxxxxxx No se Ingreso , Inventario Insuficiente xxxxxxxxxxxx </h1>");
             out.println("<a href='VentasDetalle.jsp'>Regresar...</a>");
              }
              }
              else if("eliminarp".equals(request.getParameter("btn_eliminarp"))){
                 
                              detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),
                          Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),
                          Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
              if(detalle.Eliminar()>0){
                detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),
                          Integer.parseInt(request.getParameter("drop_Producto")),(0-Integer.parseInt(request.getParameter("txt_Cantidad"))),
                          Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
                  if(detalle.ActualizarExistencias()>0){
                     v=new VentasDetalle();
               int q=v.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("VentasDetalleProductos.jsp").forward(request, response);
                    }
                
                   
              }else{
                    out.println("<h1> xxxxxxx No se modifico xxxxxxxxxxxx </h1>");
             v=new VentasDetalle();
               int q=v.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("VentasDetalleProductos.jsp").forward(request, response);
              }
              }
              else
             if("agregar".equals(request.getParameter("btn_agregar"))){
              detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),
                          Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),
                          Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
              productos obj1=new productos();
              int ex=obj1.ex(Integer.parseInt(request.getParameter("drop_Producto")));
               
           if((ex>=(Integer.parseInt(request.getParameter("txt_Cantidad"))))){
               if((detalle.agregar()>0)&&(detalle.ActualizarExistencias()>0)){
                   
                 
                  v=new VentasDetalle();
               int q=v.lastid();
               request.setAttribute("q", q);
                  request.getRequestDispatcher("VentasDetalleProductos.jsp").forward(request, response);
                  }else{
                    out.println("<h1> xxxxxxx No se Ingreso vd xxxxxxxxxxxx </h1>");
             out.println("<a href='VentasDetalleProductos.jsp'>Regresar...</a>");
              }
              
             }  else{
                    out.println("<h1> xxxxxxx No se Ingreso , Inventario Insuficiente xxxxxxxxxxxx </h1>");
             out.println("<a href='VentasDetalle.jsp'>Regresar...</a>");
              }
             }
             else if("fin".equals(request.getParameter("btn_fin"))){
                 response.sendRedirect("VentasDetalle.jsp");
             }else if ("modificar".equals(request.getParameter("btn_modificar"))){
                  ventas = new Ventas(Integer.parseInt(request.getParameter("txt_idventas")), Integer.parseInt(request.getParameter("txt_nofactura")), Integer.parseInt(request.getParameter("txt_idcliente")), Integer.parseInt(request.getParameter("txt_idempleado")), request.getParameter("txt_serie"),request.getParameter("txt_fechafactura"), request.getParameter("txt_fechaingreso"));
              if(ventas.modificar()>0){
                 
                   response.sendRedirect("VentasDetalle.jsp");
              }else{
                    out.println("<h1> xxxxxxx No se modifico xxxxxxxxxxxx </h1>");
             out.println("<a href='VentasDetalle.jsp'>Regresar...</a>");
              }
                 
             }else{
                    out.println("<h1> xxxxxxx No se Ingreso producto xxxxxxxxxxxx </h1>");
             out.println("<a href='VentasDetalle.jsp'>Regresar...</a>");
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
            Logger.getLogger(sr_VentasDetalle.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(sr_VentasDetalle.class.getName()).log(Level.SEVERE, null, ex);
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

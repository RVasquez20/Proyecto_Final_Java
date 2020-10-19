/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Ventas;
import Modelo.VentasDetalle;
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
    VentasDetalle detalleantiguo,detalle;
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
            /*out.println("<h1>Id vd"+Integer.parseInt(request.getParameter("txt_id_Ventas"))+"</h1>");
            out.println("<h1>idProducto "+Integer.parseInt(request.getParameter("drop_Producto"))+"</h1>");
            out.println("<h1>id Cantidad "+Integer.parseInt(request.getParameter("txt_Cantidad"))+"</h1>");
            out.println("<h1>precio "+Double.parseDouble(request.getParameter("txt_PrecioUnitario"))+"</h1>");
            
               Ventas obj1=new Ventas();
            int idv;
            idv=obj1.idforventasdetalle(Integer.parseInt(request.getParameter("txt_nofactura")));
            out.println("<h1>id v"+Integer.parseInt(request.getParameter("txt_idventas"))+"</h1>");
            out.println("<h1>no fac "+Integer.parseInt(request.getParameter("txt_nofactura"))+"</h1>");
            out.println("<h1>serie "+Integer.parseInt(request.getParameter("txt_serie"))+"</h1>");
            out.println("<h1>fecha factura  "+request.getParameter("txt_fechafactura")+"</h1>");
            out.println("<h1>id cliente "+Integer.parseInt(request.getParameter("txt_idcliente"))+"</h1>");
            out.println("<h1>id empleado "+Integer.parseInt(request.getParameter("txt_idempleado"))+"</h1>");
            out.println("<h1>fecha ingreso  "+request.getParameter("txt_fechaingreso")+"</h1>");
            out.println("<h1>Id venta "+idv+"</h1>");*/
              ventas = new Ventas(Integer.parseInt(request.getParameter("txt_idventas")), Integer.parseInt(request.getParameter("txt_nofactura")), Integer.parseInt(request.getParameter("txt_idcliente")), Integer.parseInt(request.getParameter("txt_idempleado")), request.getParameter("txt_serie"),request.getParameter("txt_fechafactura"), request.getParameter("txt_fechaingreso"));
              detalleantiguo=new VentasDetalle();
            int x=detalleantiguo.cantidadantigua(Integer.parseInt(request.getParameter("txt_id_Ventas")));
            
            if ("agregar".equals(request.getParameter("btn_agregar"))){
            if(ventas.agregar()>0){
                VentasDetalle obj1=new VentasDetalle();
                int idv=obj1.idforventasdetalle(Integer.parseInt(request.getParameter("txt_nofactura")));
                detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),idv,Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
                if((detalle.agregar()>0)&&(detalle.ActualizarExistencias()>0)){
                    response.sendRedirect("VentasDetalle.jsp");
                }
            
            }else{
             out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
             }
            }
              if ("modificar".equals(request.getParameter("btn_modificar"))){
              if(ventas.modificar()>0){
              
                detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),Integer.parseInt(request.getParameter("drop_Producto")),Integer.parseInt(request.getParameter("txt_Cantidad")),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));
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
                    response.sendRedirect("VentasDetalle.jsp");
                    }
                }
        
            }else{
             out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
             out.println("<a href='Compras_Detalle.jsp'>Regresar...</a>");
             }
              }
              if ("eliminar".equals(request.getParameter("btn_eliminar"))){
             if (detalle.Eliminar()>0){
             detalle=new VentasDetalle(Integer.parseInt(request.getParameter("txt_id_Ventas")),Integer.parseInt(request.getParameter("txt_idventas")),Integer.parseInt(request.getParameter("drop_Producto")),(0-Integer.parseInt(request.getParameter("txt_Cantidad"))),Double.parseDouble(request.getParameter("txt_PrecioUnitario")));

                 detalle.ActualizarExistencias();
                  if ((ventas.eliminar()>0)){ 
             response.sendRedirect("VentasDetalle.jsp");
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

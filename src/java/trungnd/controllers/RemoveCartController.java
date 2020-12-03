/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.blo.CakeBLO;
import trungnd.blo.OrderDetailsBLO;
import trungnd.cart.CartObj;
import trungnd.entity.Cake;
import trungnd.entity.Ordering;
import trungnd.entity.OrderingDetail;

/**
 *
 * @author HOME
 */
public class RemoveCartController extends HttpServlet {
    private static final String REMOVE = "showdetails.jsp";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = REMOVE;
        try {
            String cakeID_String = request.getParameter("txtCakeID_ID");
            CakeBLO cakeBLO = new CakeBLO();
            Cake cakeID = cakeBLO.findCake(cakeID_String);
            String orderID_String = request.getParameter("txtOrderID_ID");
            Ordering orderID = new Ordering(orderID_String);
            String productID = request.getParameter("txtKeyValue");
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            OrderDetailsBLO blo = new OrderDetailsBLO();
            blo.removeOrderDetail(cakeID, orderID);
            if(cart != null) {
                if(cart.getCakeList() != null) {
                    cart.removeItem(productID);                                      
                }
            }
        } catch (Exception e) {
            log("ERROR at RemoveCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

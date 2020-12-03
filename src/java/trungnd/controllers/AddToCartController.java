/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.blo.CakeBLO;
import trungnd.blo.OrderDetailsBLO;
import trungnd.blo.OrderingBLO;
import trungnd.cart.CartObj;
import trungnd.entity.Cake;
import trungnd.entity.Ordering;
import trungnd.object.RandomID;

/**
 *
 * @author HOME
 */
public class AddToCartController extends HttpServlet {
    private static final String RETURN = "SearchController?txtSearch=&txtSearchID=&PAGEID=1&action=Search&minPrice=&maxPrice=";
    
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
        String url = RETURN;
        try {
            String cakeID = request.getParameter("txtCakeID");
            String cakeName = request.getParameter("txtCakeName");
            String cakeImage = request.getParameter("txtCakeImage");
            double cakePrice = Double.parseDouble(request.getParameter("txtCakePrice"));
            int cakeQuantity = Integer.parseInt(request.getParameter("txtCakeQuantity"));
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            Date createDate = new Date();
            OrderingBLO orderBLO = new OrderingBLO();                      
            RandomID rdID = new RandomID();                 
            String orderID = request.getParameter("txtOrderID");
            if (cart == null) {
                cart = new CartObj();
                orderID = rdID.getRandomString(6);                  
                orderBLO.addUpdateOrder(orderID, 1, createDate, "NONAME", "0123456789", "NOWHERE", "NOPAYMENT", "NOSTATUS", "NOPAYMENTMETHOD");
            }
            
            int quantity = 0;       
            Ordering order = new Ordering(orderID);
            if (cart.getCakeList()!= null) {
                if (cart.getCakeList().containsKey(cakeID)) {
                    quantity = cart.getCakeList().get(cakeID).getQuantity();
                }
            }       
            String orderDetailsID = rdID.getRandomString(6);
            System.out.println(orderDetailsID);
            CakeBLO cakeblo = new CakeBLO();
            Cake cake = cakeblo.findCake(cakeID);
            OrderDetailsBLO orderDetailsBLO = new OrderDetailsBLO();
            if (cakeQuantity - quantity > 0) {
                cart.addItem(cakeID, cakeName, cakePrice, cakeImage); 
                Date lastUpdateDate = new Date();
                orderDetailsBLO.addUpdateOrderDetail(orderDetailsID, cakePrice, quantity + 1, cake, order, lastUpdateDate);
                session.setAttribute("CAKE_ID", cakeID);
                session.setAttribute("ORDER_ID", orderID);
                session.setAttribute("CART", cart);
            } else {
                session.setAttribute("ERROR_ADD", cakeID);
            }
            url = RETURN;
        } catch (Exception e) {
            log("ERROR at AddToCartController: " + e.getMessage());
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

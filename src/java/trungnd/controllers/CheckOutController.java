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
import trungnd.blo.OrderDetailsBLO;
import trungnd.blo.OrderingBLO;
import trungnd.cart.CakeInCart;
import trungnd.entity.Ordering;
import trungnd.object.RandomID;

/**
 *
 * @author HOME
 */
public class CheckOutController extends HttpServlet {

    private static final String DONE = "showdetails.jsp";

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
        request.setCharacterEncoding("UTF-8");
        String url = DONE;
        try {
            String orderID = request.getParameter("txtOrderID_ID");//1
            String name = request.getParameter("txtUserName");//4
            String address = request.getParameter("txtAddress");//6
            String phoneNumber = request.getParameter("txtPhoneNumber");//5
            String paymentStatus = request.getParameter("txtPaymentStatus"); //7
            String orderStatus = request.getParameter("txtOrderStatus");//8
            String paymentMethod = request.getParameter("cbxPayment");
            String totalPrice_String = request.getParameter("totalPrice");
            double totalPrice = Double.parseDouble(totalPrice_String); //2
            Date createDate = new Date();//3
            OrderingBLO orderBLO = new OrderingBLO();
            orderBLO.addUpdateOrder(orderID, totalPrice, createDate, name, phoneNumber, address, paymentStatus, orderStatus, paymentMethod);
        } catch (Exception e) {
            log("ERROR at CheckOutController : " + e.getMessage());
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

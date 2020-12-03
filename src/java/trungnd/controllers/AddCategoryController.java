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
import trungnd.blo.CategoryBLO;
import trungnd.object.ErrorObj;
import trungnd.object.RandomID;

/**
 *
 * @author HOME
 */
public class AddCategoryController extends HttpServlet {

    private static final String ERROR = "admin.jsp";
    private static final String SUCCESS = "AdminController";

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
        String url = ERROR;
        try {
            String categoryName = request.getParameter("txtCategoryName");
            RandomID rdID = new RandomID();
            ErrorObj errorObj = new ErrorObj();
            String categoryID = rdID.getRandomString(6);
            boolean valid = true;
            if (categoryName.length() == 0) {
                valid = false;
                errorObj.setCategoryNameError("*Name can't be blank");
            }

            CategoryBLO blo = new CategoryBLO();
            if (valid) {
                boolean check = blo.addCake(categoryID, categoryName);
                if (check) {
                    request.setAttribute("NOTI2", "Add Category Successful");
                    url = SUCCESS;
                } else {
                    request.setAttribute("NOTI2", "Add Category Unsuccessful");
                    request.setAttribute("INVALID", errorObj);
                }
            } else {
                request.setAttribute("NOTI2", "Add Category Unsuccessful");
                request.setAttribute("INVALID", errorObj);
            }

        } catch (Exception e) {
            log("Error at AddCakeController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
//            response.sendRedirect(url);
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

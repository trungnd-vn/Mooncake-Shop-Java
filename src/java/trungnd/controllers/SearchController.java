/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.blo.CakeBLO;
import trungnd.blo.CategoryBLO;
import trungnd.entity.Category;

/**
 *
 * @author HOME
 */
public class SearchController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_MEMBER_GUEST = "index.jsp";
    private static final String SUCCESS_ADMIN = "index.jsp";

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
            String searchValue = request.getParameter("txtSearch");
            String searchID = request.getParameter("txtSearchID");
//            System.out.println(searchValue + " dìa dia");
//            Category searchID_cate = (Category) request.getAttribute("txtSearchID");
//            String searchCateByCate = request.getParameter("cate");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            if ("".equals(searchValue)) {
                searchValue = "";
            }
            if ("".equals(searchID)) {
                searchID = "";
            }
            if ("".equals(minPrice)) {
                minPrice = "0";
            }
            if ("".equals(maxPrice)) {
                maxPrice = "1000000";
            }
            int minPrice_Check = Integer.parseInt(minPrice);
            int maxPrice_Check = Integer.parseInt(maxPrice);
            if (minPrice_Check > maxPrice_Check || minPrice_Check < 0) {
                minPrice_Check = 0;
            } 
            if (maxPrice_Check < minPrice_Check || maxPrice_Check < 0) {
                maxPrice_Check = 1000000;
            }
//            System.out.println(searchID);
            int currentPage;
            if (request.getAttribute("PAGEID") != null) {
                currentPage = (int) request.getAttribute("PAGEID");
//                currentPage = Integer.parseInt(request.getParameter("PAGEID"));
            }
            if (request.getParameter("PAGEID") != null) {
                currentPage = Integer.parseInt(request.getParameter("PAGEID"));
            } else {
                currentPage = Integer.parseInt(request.getParameter("pageIDPaging")); //từ paging
            }
            int pageMaxSize = 20;

            CakeBLO blo = new CakeBLO();
            CategoryBLO blo_2 = new CategoryBLO();
            List result_Cake = null;
            List result_Category = null;
            Category categoryName_AfterSearch = null;
            if (!"".equals(searchID)) {
                categoryName_AfterSearch = blo_2.findCategory(searchID);
                request.setAttribute("txtSearchID", categoryName_AfterSearch);
//                System.out.println(categoryName_AfterSearch + " 22222");
                result_Cake = blo.searchByLikeInput_Category(categoryName_AfterSearch, currentPage, pageMaxSize);
                result_Category = blo_2.showCategoryLeftSide();
            } else if (minPrice_Check > 0 || maxPrice_Check > 0) {
                result_Cake = blo.searchByLikeInput_Price(minPrice_Check, maxPrice_Check, currentPage, pageMaxSize);
                result_Category = blo_2.showCategoryLeftSide();
            } else {
                result_Cake = blo.searchByLikeInput(searchValue, currentPage, pageMaxSize);
                result_Category = blo_2.showCategoryLeftSide();
            }
//            for (Object object : result_Category) {
//                System.out.println(object);
//            }
            if (result_Cake != null) {
                request.setAttribute("LIST_CAKE", result_Cake);
                request.setAttribute("LIST_CATEGORY", result_Category);
                request.setAttribute("CURRENT_PAGE", currentPage);
                if (!searchID.isEmpty()) {
                    request.setAttribute("CAKE_COUNT", blo.getAmountFindByLikeInput_Category(categoryName_AfterSearch, pageMaxSize));
                    System.out.println(currentPage + " " + blo.getAmountFindByLikeInput(searchValue, pageMaxSize));
                } else if (minPrice_Check > 0 || maxPrice_Check > 0) {
                    request.setAttribute("CAKE_COUNT", blo.getAmountFindByLikeInput_Price(minPrice_Check, maxPrice_Check, pageMaxSize));
                    System.out.println(currentPage + " " + blo.getAmountFindByLikeInput_Price(minPrice_Check, maxPrice_Check, pageMaxSize));
                } else {
                    request.setAttribute("CAKE_COUNT", blo.getAmountFindByLikeInput(searchValue, pageMaxSize));
                    System.out.println(currentPage + " " + blo.getAmountFindByLikeInput(searchValue, pageMaxSize));
                }
                HttpSession session = request.getSession();
                session.setAttribute("LISTCATEGORY", result_Category);
                String roleName = (String) session.getAttribute("ROLE");
                if ("admin".equals(roleName)) {
                    url = SUCCESS_ADMIN;
                } else {
                    url = SUCCESS_MEMBER_GUEST;
                }
            }
        } catch (Exception e) {
            log("ERROR at SearchController: " + e.getMessage());
            e.printStackTrace();
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

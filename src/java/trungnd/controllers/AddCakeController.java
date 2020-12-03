/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import trungnd.blo.AccountBLO;
import trungnd.blo.CakeBLO;
import trungnd.blo.CategoryBLO;
import trungnd.entity.Account;
import trungnd.entity.Category;
import trungnd.object.ErrorObj;
import trungnd.object.RandomID;

/**
 *
 * @author HOME
 */
public class AddCakeController extends HttpServlet {

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
            String realPath_DB = "";
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    log("ERROR FileUploadException at AddCakeController: " + e.getMessage());
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString("UTF-8"));
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path " + fileName);
                            String realPath = getServletContext().getRealPath("/")
                                    + "images\\imageCake\\" + fileName;
                            System.out.println("Real Path " + realPath);
                            realPath_DB = fileName;
                            File savedFile = new File(realPath);
                            if (fileName.length() != 0) {
                                item.write(savedFile);
                            }
                            
                        } catch (Exception e) {
                            log("ERROR ImageUploadException at AddCakeController: " + e.getMessage() + "ko quan tâm");
//                            e.printStackTrace();
                        }
                    }
                }
                boolean valid = true;
                ErrorObj errorObj = new ErrorObj();
                RandomID rdID = new RandomID();
                String cakeID = rdID.getRandomString(6);
                String cakeName = (String) params.get("txtCakeName");
                if (cakeName.length() == 0) {
                    valid = false;
                    errorObj.setCakeNameError("*Name can't be blank");
                }
                String cakeDescription = (String) params.get("txtCakeDescription");
                String cakeImage = realPath_DB;
                if (cakeImage.length() == 0) {
                    cakeImage = null;
                }
                double cakePrice = 0;
                if (params.get("txtCakePrice").toString().length() == 0) {
                    valid = false;
                    errorObj.setPriceCakeError("*Price can't be blank");
                } else {
                    cakePrice = Double.parseDouble((String) params.get("txtCakePrice"));
                }
                int quantity = 0;
                if (params.get("txtCakeQuantity").toString().length() == 0) {
                    valid = false;
                    errorObj.setQuantityCakeError("*Quantity can't be blank");
                } else {
                    quantity = Integer.parseInt((String) params.get("txtCakeQuantity"));
                }

                String createdDate = (String) params.get("txtCreatedDate");
                if (createdDate.length() == 0) {
                    valid = false;
                    errorObj.setCreateDayError("*Create Day can't be blank");
                }
                String expDate = (String) params.get("txtExpDate");
                if (expDate.length() == 0) {
                    valid = false;
                    errorObj.setCreateDayError("*Exp Date can't be blank");
                }
                String categoryID_String = (String) params.get("cbxCategory");
                System.out.println(categoryID_String);
                String[] tmp = categoryID_String.split("-");
                CategoryBLO categoryBLO = new CategoryBLO();
                Category categoryID = categoryBLO.findCategory(tmp[0]);
                Date createdDate_Add = null;
                Date expDate_Add = null;
                if (createdDate.length() != 0 || expDate.length() != 0) {
                    if (Integer.parseInt(createdDate.substring(0, 4)) >= Integer.parseInt(expDate.substring(0, 4))) {
                        //                System.out.println("year _-_");
                        if (Integer.parseInt(createdDate.substring(5, 7)) >= Integer.parseInt(expDate.substring(5, 7))) {
                            //                    System.out.println("month _-_");
                            if (Integer.parseInt(createdDate.substring(8, 10)) >= Integer.parseInt(expDate.substring(8, 10))) {
                                //                        System.out.println("day _-_");
                                if (Integer.parseInt(createdDate.substring(11, 13)) >= Integer.parseInt(expDate.substring(11, 13))) {
                                    //                            System.out.println("hour _-_");
                                    if (Integer.parseInt(createdDate.substring(14, 16)) >= Integer.parseInt(expDate.substring(14, 16))) {
                                        //                                System.out.println("minutes _-_");
                                        valid = false;
                                        errorObj.setCreateExpDateError("*Input Date again");
                                    }
                                }
                            }
                        }
                    }
                    createdDate_Add = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(createdDate);
                    expDate_Add = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(expDate);
                }

//                System.out.println(createdDate);
                
                Date updateDate_Add = new Date();
                String status = (String) params.get("txtCakeStatus");
                AccountBLO accountBLO = new AccountBLO();
                String accAdmin = (String) params.get("txtAccAdminUpdate");
                System.out.println(accAdmin);
                Account accAdminUpdate = accountBLO.findAccountAdmin(accAdmin);
//                System.out.println("1" + cakeID);
//                System.out.println("2" + cakeName);
//                System.out.println("3" + cakePrice);
//                System.out.println("4" + cakeDescription);
//                System.out.println("5" + createdDate_Add);
//                System.out.println("6" + expDate_Add);
//                System.out.println("7" + updateDate_Add);
//                System.out.println("8" + categoryID);
                CakeBLO blo = new CakeBLO();
                if (valid) {
                    if (categoryID != null) {
                        if (cakeName.length() != 0 || cakePrice > 0 || quantity > 0 || createdDate.length() != 0 || expDate.length() != 0) {
                            blo.addCake(cakeID, cakeName, cakeImage, cakeDescription, cakePrice, quantity, createdDate_Add, expDate_Add, updateDate_Add, status, categoryID, accAdminUpdate);
                            request.setAttribute("NOTI", "Add Successful");
                        } else {
                            request.setAttribute("NOTI", "Add UnSuccessful");
                        }  
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("NOTI", "Add UnSuccessful!!!");
                    request.setAttribute("INVALID", errorObj);
                }

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

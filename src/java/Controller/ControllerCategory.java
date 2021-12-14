/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOCategory;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerCategory", urlPatterns = {"/category"})
public class ControllerCategory extends HttpServlet {

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
        DAOCategory dao = new DAOCategory();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCategory</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerCategory at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

            String service = request.getParameter("service");

            if (service == null) {
                service = "listAll";
            }

            if (service.equalsIgnoreCase("listAll")) {
                ResultSet rs = dao.getData("select * from Category");
                String title = "List all Category for Admin";

                request.setAttribute("rs", rs);
                request.setAttribute("title", title);

                request.getRequestDispatcher("/CategoryView.jsp").forward(request, response);
            }

            if (service.equals("insert") || service.equals("update")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String title = "";

                    if (service.equals("insert")) {
                        title = "Insert New Category";
                    }

                    if (service.equals("update")) {
                        String id = request.getParameter("id");

                        ResultSet rs = null;

                        title = "Update Category";
                        if (id != null) {
                            rs = dao.getData("select * from Category where cateID ='" + id + "'");
                        }
                        request.setAttribute("rs", rs);
                    }

                    request.setAttribute("title", title);

                    request.getRequestDispatcher("/CategoryEdit.jsp").forward(request, response);
                } else {
                    String cateID = request.getParameter("cateID");
                    String cateName = request.getParameter("cateName");
                    String status = request.getParameter("status");

                    if (!(dao.searchCategory(cateName) > 0) || cateName != null || !cateName.equals("")) {
                        int sta = Integer.parseInt(status);

                        Category cate = new Category(cateName, sta);
                        if (service.equals("insert")) {
                            dao.insertCategory(cate);
                        }
                        if (service.equals("update")) {
                            dao.updateCategory(cate, Integer.parseInt(cateID));
                        }
                    }
                    response.sendRedirect("category");
                }
            }

            if (service.equals("delete")) {
                String id = request.getParameter("id");
                dao.removeCategory(Integer.parseInt(id));
                response.sendRedirect("category");
            }
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

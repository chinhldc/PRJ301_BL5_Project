/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOAdmin;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerAdmin", urlPatterns = {"/admin"})
public class ControllerAdmin extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerAdmin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerAdmin at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
            String service = request.getParameter("service");

            if (service == null) {
                service = "listAll";
            }

            if (service.equalsIgnoreCase("listAll")) {
                ResultSet rs = dao.getData("select * from admin");

                String title = "List all Administrators";

                request.setAttribute("rs", rs);
                request.setAttribute("title", title);

                request.getRequestDispatcher("/AdminView.jsp").forward(request, response);
            }

            if (service.equalsIgnoreCase("insert")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String title = "Add Admin";

                    request.setAttribute("title", title);

                    request.getRequestDispatcher("/AdminEdit.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    Admin admin = new Admin(username, password);
                    dao.insertAdmin(admin);
                    response.sendRedirect("admin");
                }
            }

            if (service.equalsIgnoreCase("changePswd")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String adminID = request.getParameter("adminID");

                    ResultSet rs = dao.getData("select adminID, username from admin where adminID=" + adminID);

                    String title = "Change Admin Password";

                    request.setAttribute("title", title);
                    request.setAttribute("rs", rs);

                    request.getRequestDispatcher("/AdminEdit.jsp").forward(request, response);
                } else {
                    int id = Integer.parseInt(request.getParameter("adminID"));
                    String oldPW = request.getParameter("oldPW");
                    String newPW = request.getParameter("newPW");
                    String confirm = request.getParameter("confirm");

                    if ((!oldPW.equals(newPW)) && newPW.equals(confirm) && newPW.length() >= 8) {
                        dao.changePassword(id, oldPW, newPW);
                    }
                    response.sendRedirect("admin");
                }
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

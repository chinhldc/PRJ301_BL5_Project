/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOCustomer;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/customer"})
public class ControllerCustomer extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCustomer</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerCustomer at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

            String service = request.getParameter("service");

            if (service == null) {
                service = "listAll";
            }

            if (service.equalsIgnoreCase("listAll")) {
                ResultSet rs = dao.getData("select * from Customer");
                String title = "List all Customers for Admin";

                request.setAttribute("rs", rs);
                request.setAttribute("title", title);

                request.getRequestDispatcher("/CustomerView.jsp").forward(request, response);
            }

            if (service.equalsIgnoreCase("insert") || (service.equalsIgnoreCase("update"))) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String title = "";

                    ResultSet rs = null;

                    String id = request.getParameter("cid");

                    rs = dao.getData("select * from Customer where cid='" + id + "'");

                    request.setAttribute("title", title);
                    request.setAttribute("rs", rs);

                    request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
                } else {
                    String cname = request.getParameter("cname");
                    String cphone = request.getParameter("cphone");
                    String cAddress = request.getParameter("cAddress");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    if (service.equalsIgnoreCase("insert")) {
                        Customer cus = new Customer(cname, cphone, cAddress, username, password);
                        dao.addCustomer(cus);
                        response.sendRedirect("customer");
                    }
                    if (service.equalsIgnoreCase("update")) {

                    }
                }

            }

            if (service.equalsIgnoreCase("changePswd")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String cid = request.getParameter("cid");

                    ResultSet rs = dao.getData("select cid, username from Customer where cid=" + cid);

                    String title = "Change password";

                    request.setAttribute("rs", rs);
                    request.setAttribute("title", title);

                    request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
                } else {
                    int cid = Integer.parseInt(request.getParameter("cid"));
                    String oldPW = request.getParameter("oldPW");
                    String newPW = request.getParameter("newPW");
                    String checkPW = request.getParameter("checkPW");

                    if (!oldPW.equals(newPW) && newPW.equals(checkPW) && newPW.length() >= 8) {
                        dao.updatePassword(cid, oldPW, newPW);
                    }
                    response.sendRedirect("customer");
                }
            }

            if (service.equals("delete")) {
                String id = request.getParameter("cid");
                dao.removeCustomer(Integer.parseInt(id));
                response.sendRedirect("customer");
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

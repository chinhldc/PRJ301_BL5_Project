/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOBill;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerBill", urlPatterns = {"/bill"})
public class ControllerBill extends HttpServlet {

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
        DAOBill dao = new DAOBill();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerBill</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerBill at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String service = request.getParameter("service");
            
            if (service == null) {
                service = "listAll";
            }
            
            if (service.equalsIgnoreCase("listAll")) {
                ResultSet rs = dao.getData("select * from Bill order by dateCreate desc");
                
                String title = "List Bill for Administrator";
                
                request.setAttribute("rs", rs);
                request.setAttribute("title", title);
                
                request.getRequestDispatcher("/BillView.jsp").forward(request, response);
            }
            
            if (service.equalsIgnoreCase("delete")) {
                String oID = request.getParameter("oID");
                dao.deleteBill(oID);
                response.sendRedirect("bill");
            }
            
            if (service.equalsIgnoreCase("updateInfo")) {
                String submit = request.getParameter("submit");
                
                if (submit == null) {
                    String oID = request.getParameter("oID");
                    
                    ResultSet rs = dao.getData("select oID, cname, cphone, cAddress from Bill where oID='" + oID + "'");
                    
                    String title = "Update buyer info";
                    
                    request.setAttribute("rs", rs);
                    request.setAttribute("title", title);
                    
                    request.getRequestDispatcher("/BillEdit.jsp").forward(request, response);
                } else {
                    String oID = request.getParameter("oID");
                    String cname = request.getParameter("cname");
                    String cphone = request.getParameter("cphone");
                    String cAddress = request.getParameter("cAddress");
                    
                    dao.updateInfo(oID, cname, cphone, cAddress);
                    response.sendRedirect("bill");
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

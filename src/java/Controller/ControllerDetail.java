/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.BillDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOBill;
import DAO.DAODetail;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerDetail", urlPatterns = {"/detail"})
public class ControllerDetail extends HttpServlet {

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
        DAODetail dao = new DAODetail();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerDetail</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerDetail at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            String bill = request.getParameter("bill");

            if (bill == null) {
                response.sendRedirect("bill");
            } else {
                String service = request.getParameter("service");
                if (service == null) {
                    service = "";
                }

                if (service.equalsIgnoreCase("")) {
                    
                    ResultSet rs = dao.getData("select * from BillDetail where oID = '" + bill + "'");

                    String title = "Detail bill " + bill;

                    ResultSet rsCID = dao.getData("select Bill.cid, Customer.cname from Bill "
                            + "INNER JOIN Customer ON Bill.cid = Customer.cid "
                            + "where Bill.oID= '" + bill + "'");
                    
                    request.setAttribute("rsCID", rsCID);
                    request.setAttribute("rs", rs);
                    request.setAttribute("title", title);

                    request.getRequestDispatcher("/DetailView.jsp").forward(request, response);
                }

                if (service.equals("update")) {
                    String submit = request.getParameter("submit");

                    if (submit == null) {
                        String pid = request.getParameter("pid");

                        ResultSet rs = dao.getData("select BillDetail.*, Product.price"
                                + " from BillDetail"
                                + " INNER JOIN Product ON BillDetail.pid = Product.pid"
                                + " where oID = '" + bill + "' and BillDetail.pid='" + pid + "'");
                        
                        String title = "Update Bill Detail Order " + bill;
                        
                        request.setAttribute("rs", rs);
                        request.setAttribute("title", title);
                        
                        request.getRequestDispatcher("/BillDetailEdit.jsp").forward(request, response);
                    } else {
                        String pid = request.getParameter("pid");
                        String oID = request.getParameter("oID");
                        int quantity = Integer.parseInt(request.getParameter("quantity"));
                        double price= Double.parseDouble(request.getParameter("price"));
                        
                        BillDetail bd = new BillDetail(pid, oID, quantity, price);
                        dao.updateDetail(bd);
                        
                        DAOBill daoBill = new DAOBill();
                        daoBill.updateTotal(oID);
                        
                        response.sendRedirect("detail?bill=" + bill);
                    }
                }
                
                if (service.equalsIgnoreCase("delete")) {
                    String pid = request.getParameter("pid");
                    dao.deleteDetail(pid, bill);
                    response.sendRedirect("detail?bill=" + bill);
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

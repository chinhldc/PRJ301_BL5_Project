/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOProduct;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/product"})
public class ControllerProduct extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerProduct</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerProduct at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

            String service = request.getParameter("service");
            String sort = request.getParameter("sort");

            if (service == null) {
                service = "listAll";
            }

            if (service.equals("listAll") || service.equals("search")) {
//                Step 1
//                String sql = "select * from Product";
                ResultSet rs = null;
                String sql = "";
                if (service.equalsIgnoreCase("listAll")) {
                    sql = "SELECT Product.*, Category.cateName "
                            + "FROM Product "
                            + "INNER JOIN Category ON Product.cateID = Category.cateID ";
                }
                if (service.equalsIgnoreCase("search")) {
                    String search = request.getParameter("search");
                    sql = "select Product.*, Category.cateName from Product "
                            + "INNER JOIN Category ON Product.cateID = Category.cateID "
                            + "where Product.pid like '%" + search + "%' "
                            + "OR Product.pname like '%" + search + "%' ";
                }
                if (sort != null) {
                    if (sort.equals("0")) {
                        sql += " order by Product.price desc";
                    } else {
                        sql += " order by Product.price asc";
                    }
                }
                rs = dao.getData(sql);

                String title = "List all Product for Admin";

//                Step 2
                request.setAttribute("result", rs);
                request.setAttribute("title", title);

//                Step 3
                RequestDispatcher disp = request.getRequestDispatcher("/ProductView.jsp");

//                Run
                disp.forward(request, response);
            }

            if (service.equals("insert") || service.equals(("update"))) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    String pid = request.getParameter("pid");
                    ResultSet rs = dao.getData("select * from Product where pid='" + pid + "'");
                    request.setAttribute("rs", rs);

                    ResultSet rsCate = dao.getData("select * from Category");
                    request.setAttribute("rsCate", rsCate);

                    String title = "";

                    if (service.equals("insert")) {
                        title = "Insert New Product";

                    } else {
                        title = "Update Product";
                    }
                    request.setAttribute("title", title);

                    request.getRequestDispatcher("/ProductEdit.jsp").forward(request, response);
                } else {
                    String id = request.getParameter("id");
                    String name = request.getParameter("pname");
                    String quantity = request.getParameter("quantity");
                    String price = request.getParameter("price");
                    String image = request.getParameter("image");
                    String des = request.getParameter("des");
                    String status = request.getParameter("status");
                    String cate = request.getParameter("cate");

                    if (id == null || id.equals("")) {
                        out.print("Input ID");
                    } else {
                        int quan = Integer.parseInt(quantity);
                        double pri = Double.parseDouble(price);
                        int sta = 1;
                        if (status != null) {
                            sta = Integer.parseInt(status);
                        }
                        int cateID = Integer.parseInt(cate);

                        

                        if (service.equals("insert")) {
                            Product pro = new Product(id, name, quan, pri, image, des, 1, cateID);
                            dao.addProduct(pro);
                        }
                        if (service.equals("update")) {
                            Product pro = new Product(id, name, quan, pri, image, des, sta, cateID);
                            dao.updateProduct(pro);
                        }
                        response.sendRedirect("product");
                    }
                }
            }

            if (service.equals("delete")) {
                String pid = request.getParameter("pid");
                dao.removeProduct(pid);
                response.sendRedirect("product");

//                String contextPath = request.getContextPath();
//                request.getRequestDispatcher(contextPath + "/product").forward(request, response);
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

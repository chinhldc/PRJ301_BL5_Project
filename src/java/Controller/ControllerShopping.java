/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Bill;
import Entity.BillDetail;
import Entity.Category;
import Entity.Customer;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.DAOBill;
import DAO.DAOCategory;
import DAO.DAOCustomer;
import DAO.DAODetail;
import DAO.DAOProduct;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerShopping", urlPatterns = {"/"})
public class ControllerShopping extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final DAOProduct dao = new DAOProduct();
    private final DAOCategory daoCate = new DAOCategory();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerShopping</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerShopping at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

            HttpSession session = request.getSession();

//            Create cart
            ArrayList<Product> cart = (ArrayList) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }
            
            ArrayList<Category> cate = (ArrayList) session.getAttribute("cate");
            if (cate == null) {
                cate = new ArrayList<>();
                session.setAttribute("cate", cate);
            }

//            Get customer info
            Customer customer = (Customer) session.getAttribute("customer");
            while (customer == null) {
                // Login
//                customer = getCustomerInfo(1);
//                session.setAttribute("customer", customer);
                String submit = request.getParameter("submit");
                int n = -1;
                if (submit == null || submit.equals("")) {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String message = "";
                    if (username.equals("") || password.equals("")) {
                        message = "Username/Password is empty";
                    } else {
//                        n = login(username, password);
                        if (login(username, password) == null) {
                            message = "Login fails. Check username/password";
                        }
                    }
                    if (message.equals("")) {
                        customer = getCustomerInfo(n);
                        session.setAttribute("customer", customer);
                    } else {
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
            }

            String service = request.getParameter("service");
            String sort = request.getParameter("sort");

            if (service == null) {
                service = "listAll";
            }

            if (service.equals("listAll") || service.equals("search")) {
                ResultSet rs = null;
                String sql = "";
                if (service.equalsIgnoreCase("listAll")) {
                    sql = "SELECT Product.*, Category.cateName "
                            + "FROM Product "
                            + "INNER JOIN Category ON Product.cateID = Category.cateID "
                            + "where Product.quantity > 0 and Product.status = 1";
                }
                if (service.equalsIgnoreCase("search")) {
                    String search = request.getParameter("search");
                    sql = "select Product.*, Category.cateName from Product "
                            + "INNER JOIN Category ON Product.cateID = Category.cateID "
                            + "where Product.pid like '%" + search + "%' "
                            + "OR Product.pname like '%" + search + "%' "
                            + "and Product.quantity > 0 and Product.status = 1";
                }
                if (sort != null) {
                    if (sort.equals("0")) {
                        sql += " order by Product.price desc";
                    } else {
                        sql += " order by Product.price asc";
                    }
                }
                rs = dao.getData(sql);

                String title = "Shopping Products";

//                try {
//                    while (rs.next()) {
//                        Product prod = new Product
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(ControllerShopping.class.getName()).log(Level.SEVERE, null, ex);
//                }

//                request.setAttribute("rs", rs);
                request.setAttribute("title", title);
                //request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

            if (service.equalsIgnoreCase("cart")) {
                String title = "Shopping cart";
                request.setAttribute("title", title);
                String id = request.getParameter("remove");
                if (id != null) {
                    if (id.equalsIgnoreCase("removeall")) {
                        cart.removeAll(cart);
                    } else if (getIndex(id, cart) > -1) {
                        cart.remove(getIndex(id, cart));
                    }
                    session.setAttribute("cart", cart);
                    response.sendRedirect("shopping?service=cart");
                } else {
                    request.getRequestDispatcher("/SessionCart.jsp").forward(request, response);
                }
            }

            if (service.equalsIgnoreCase("checkout")) {
                DAOBill bill = new DAOBill();
                DAODetail detail = new DAODetail();

                double billTotal = 0;

                String submit = request.getParameter("submit");

                if (submit == null || submit.equals("")) {
                    request.getRequestDispatcher("/SessionCheckout.jsp").forward(request, response);
                } else {
                    String sname = request.getParameter("sname");
                    String sphone = request.getParameter("sphone");
                    String saddress = request.getParameter("saddress");

//                Create oID
                    String oID = customer.getUsername() + System.currentTimeMillis();

                    Enumeration<Product> enm = Collections.enumeration(cart);

                    Bill newBill = new Bill(oID, sname, sphone, saddress, billTotal, 1, customer.getCid());
                    bill.addBill(newBill);

                    while (enm.hasMoreElements()) {
                        Product item = enm.nextElement();
                        double total = item.getPrice() * item.getQuantity();
                        billTotal = billTotal + total;
                        BillDetail bd = new BillDetail(item.getPid(), oID, item.getQuantity(), item.getPrice(), total);
                        detail.insertDetail(bd);
                        dao.updateQuantity(item.getPid(), -item.getQuantity());
                    }
//                bill.updateTotal(oID, billTotal);
                    bill.updateTotal(oID);

//                session.invalidate();
                    cart.clear();
                    response.sendRedirect("detail?bill=" + oID);
                }
            }

            if (service.equalsIgnoreCase("add")) {
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                Double price = Double.parseDouble(request.getParameter("price"));
                if (request.getParameter(pid).equals("")) {
                    session.setAttribute("noti", "Invalid item");
                } else {
                    int quantity = Integer.parseInt(request.getParameter(pid));
                    int index = getIndex(pid, cart);

                    if (index == -1) {
                        cart.add(new Product(pid, pname, quantity, price));
                    } else {
                        quantity = cart.get(index).getQuantity() + quantity;
                        if (quantity > getMaxQuantity(pid)) {
                            quantity = getMaxQuantity(pid);
                        }
                        cart.get(index).setQuantity(quantity);
                    }
//                    session.setAttribute("cart", cart);

                    session.setAttribute("noti", "Added " + pid + " to cart");
                }
                response.sendRedirect("shopping");
            }

            if (service.equalsIgnoreCase("cartUpdate")) {
                String submit = request.getParameter("submit");

                if (submit == null || submit.equals("")) {
                    request.getRequestDispatcher("/SessionCart.jsp").forward(request, response);
                } else {
                    Enumeration<Product> enm = Collections.enumeration(cart);

                    while (enm.hasMoreElements()) {
                        Product item = enm.nextElement();
                        int quantity = Integer.parseInt(request.getParameter(item.getPid()));

                        if (quantity > getMaxQuantity(item.getPid())) {
                            quantity = getMaxQuantity(item.getPid());
                        }

                        cart.get(getIndex(item.getPid(), cart)).setQuantity(quantity);
                    }
//                    session.setAttribute("cart", cart);
                    response.sendRedirect("shopping?service=cart");
                }
            }
        }
    }

    private int getIndex(String id, ArrayList<Product> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getPid().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxQuantity(String id) {
        ResultSet rs = dao.getData("select quantity from Product where pid = '" + id + "'");
        try {
            if (rs.next()) {
                int maxQuantity = rs.getInt(1);
                return maxQuantity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerShopping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    private Customer getCustomerInfo(int cid) {
        ResultSet rs = dao.getData("select * from Customer where cid = " + cid);
        try {
            if (rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerShopping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (new Customer());
    }

    private Customer login(String username, String password) {
        DAOCustomer dao = new DAOCustomer();
        return dao.userlogin(username, password);
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

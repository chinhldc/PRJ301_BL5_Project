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
import DAO.DAOBill;
import DAO.DAOCategory;
import DAO.DAOCustomer;
import DAO.DAODetail;
import DAO.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chinh
 */
@WebServlet(name = "ControllerShopping", urlPatterns = {"", "/category", "/search", "/cart", "/product", "/checkout", "/bills", "/login", "/signup", "/logout", "/addtocart"})
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
    private final DAOProduct daoProd = new DAOProduct();
    private final DAOCategory daoCate = new DAOCategory();
    private final DAOCustomer daoCust = new DAOCustomer();
    private final DAOBill daoBill = new DAOBill();
    private final DAODetail daoDetail = new DAODetail();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
//            Create cart
            ArrayList<Product> cart = (ArrayList) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }

//            Create category list
            ArrayList<Category> cate = (ArrayList) session.getAttribute("cate");
            if (cate == null) {
                cate = (ArrayList) daoCate.getAll();
                session.setAttribute("cate", cate);
            }

//            Get cart total
            double total = 0;
            if (!cart.isEmpty()) {
                for (int i = 0; i < cart.size(); i++) {
                    int quantity = cart.get(i).getQuantity();
                    double price = cart.get(i).getPrice();
                    total += price * quantity;
                }
            }
            session.setAttribute("total", total);

//            Flag show Categories in menu
            int showCate = 0;
            session.setAttribute("showCate", showCate);

//            Flag privilege login
            int privilege = -1;
            session.setAttribute("privilege", privilege);

//            Customer info
            Customer customer = (Customer) session.getAttribute("customer");
            
            String searchMessage = "";
            session.setAttribute("searchMessage", searchMessage);
            
            String userPath = request.getServletPath();
            
            if (userPath.equals("")) {
                showCate = 1;
                session.setAttribute("showCate", showCate);
                
                ArrayList<Product> shop_list = daoProd.getShopList();
                session.setAttribute("shop_list", shop_list);
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            /*
            * Search for product by name and category
             */
            if (userPath.equals("/search")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
//                    session.setAttribute("showCate", 1);
                    int searchCateID = 0;
                    try {
                        searchCateID = Integer.parseInt(request.getParameter("searchCateID"));
                    } catch (NumberFormatException e) {
                        searchCateID = 0;
                    }
                    String searchProdID = "";
                    
                    if (request.getParameter("searchProdID") != null) {
                        searchProdID = request.getParameter("searchProdID");
                    }
//                    out.print(searchCateID + " " + searchProdID);
                    ArrayList<Product> shop_list = daoProd.searchShopList(searchCateID, searchProdID);
                    session.setAttribute("shop_list", shop_list);
                    String cateName = "All";
                    if (daoCate.getCateName(searchCateID) != null) {
                        cateName = daoCate.getCateName(searchCateID);
                    }
                    searchMessage = "Searching result for category " + cateName + " and products contain " + searchProdID;
                    session.setAttribute("searchMessage", searchMessage);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }

            /*
            * Get list of product by category
             */
            if (userPath.equals("/category")) {
                showCate = 1;
                session.setAttribute("showCate", showCate);
                
                int searchCateID = Integer.parseInt(request.getParameter("cateID"));
                ArrayList<Product> shop_list = daoProd.searchShopList(searchCateID, "");
                session.setAttribute("shop_list", shop_list);
                
                String cateName = "All";
                if (daoCate.getCateName(searchCateID) != null) {
                    cateName = daoCate.getCateName(searchCateID);
                }
                searchMessage = "Found " + shop_list.size() + " item(s) for category " + cateName;
                session.setAttribute("searchMessage", searchMessage);
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            /*
            * Show product info
             */
            if (userPath.equals("/product")) {
                String pid = request.getParameter("pid");
                Product prod = daoProd.getProduct(pid);
                session.setAttribute("prod", prod);
                
                String cateName = daoCate.getCateName(pid);
                session.setAttribute("cateName", cateName);
                
                request.getRequestDispatcher("product.jsp").forward(request, response);
            }

            /*
            * Show current Cart
             */
            if (userPath.equals("/cart")) {
                String update = (String) request.getParameter("update");
                String delete = (String) request.getParameter("delete");
                
                if (update == null && delete == null) {
                    if (customer == null) {
                        response.sendRedirect("login");
                    } else {
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                    }
                } else if (null == update) {
                    if (delete.equalsIgnoreCase("Delete Cart")) {
                        cart.clear();
                    } else {
                        for (int i = 0; i < cart.size(); i++) {
                            if (cart.get(i).getPid().equals(delete)) {
                                cart.remove(i);
                            }
                        }
                    }
                    response.sendRedirect("cart");
                } else {
                    for (int i = 0; i < cart.size(); i++) {
                        String pid = cart.get(i).getPid();
                        int quantity = Integer.parseInt(request.getParameter(pid));
                        if (quantity > daoProd.getMaxQuantity(pid)) {
                            session.setAttribute("noti" + pid, "Over quantity");
                            quantity = daoProd.getMaxQuantity(pid);
                        }
                        cart.get(i).setQuantity(quantity);
                    }
                    response.sendRedirect("cart");
                }
                
            }

            /*
            * Add product to cart
             */
            if (userPath.equals("/addtocart")) {
                if (customer == null) {
                    response.sendRedirect("login");
                } else {
                    String pid = (String) request.getParameter("pid");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int index = getIndex(pid, cart);
                    if (index == -1) {
                        Product item = daoProd.getProduct(pid);
                        item.setQuantity(quantity);
                        cart.add(item);
                    } else {
                        quantity = cart.get(index).getQuantity() + quantity;
                        if (quantity > daoProd.getMaxQuantity(pid)) {
                            quantity = daoProd.getMaxQuantity(pid);
                        }
                        cart.get(index).setQuantity(quantity);
                    }
                    response.sendRedirect("cart");
                }
            }

            /*
            * Checkout
             */
            if (userPath.equals("/checkout")) {
                if (customer == null) {
                    response.sendRedirect("login");
                } else {
                    String submit = request.getParameter("submit");
                    if (submit == null || submit.equals("")) {
                        session.setAttribute("customer", customer);
                        request.getRequestDispatcher("checkout.jsp").forward(request, response);
                    } else {
                        String name = request.getParameter("name");
                        String phone = request.getParameter("phone");
                        String address = request.getParameter("address");
                        
                        double billTotal = 0;
//                        Create oID
                        String oID = customer.getUsername() + System.currentTimeMillis();
                        Enumeration<Product> enm = Collections.enumeration(cart);
                        Bill newBill = new Bill(oID, name, phone, address, billTotal, -1, customer.getCid());
                        daoBill.addBill(newBill);
                        while (enm.hasMoreElements()) {
                            Product item = enm.nextElement();
                            total = item.getPrice() * item.getQuantity();
                            billTotal = billTotal + total;
                            BillDetail bd = new BillDetail(item.getPid(), oID, item.getQuantity(), item.getPrice(), total);
                            daoDetail.insertDetail(bd);
                            daoProd.updateQuantity(item.getPid(), -item.getQuantity());
                        }
                        daoBill.updateTotal(oID);
                        cart.clear();
                        response.sendRedirect(".");
                    }
                }
            }

            /*
            * Show bills
             */
            if (userPath.equals("/bills")) {
                if (customer == null) {
                    response.sendRedirect("login");
                } else {
                    ArrayList<Bill> bills = daoBill.billsByCID(customer.getCid());
                    session.setAttribute("bills", bills);
                    request.getRequestDispatcher("bills.jsp").forward(request, response);
                }
            }

            /*
            * User login
             */
            if (userPath.equals("/login")) {
                if (customer == null) {
                    String submit = request.getParameter("submit");
                    
                    if (submit == null || submit.equals("")) {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        
                        String message = "";
                        
                        if (username.equals("") || password.equals("")) {
                            message = "Username/Password is empty";
                        } else {
                            customer = daoCust.userlogin(username, password);
                            if (customer == null) {
                                message = "Wrong username/password";
                            }
                        }
                        if (message.equals("")) {
                            session.setAttribute("customer", customer);
                            session.setAttribute("privilege", 0);
                            response.sendRedirect(".");
                        } else {
                            request.setAttribute("message", message);
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        }
                    }
                } else {
                    response.sendRedirect("");
                }
            }

            /*
            * User signup
             */
            if (userPath.equals("/signup")) {
                if (customer == null) {
                    String submit = request.getParameter("submit");
                    String message = "";
                    if (submit == null || submit.equals("")) {
                        request.setAttribute("message", message);
                        request.setAttribute("message", "");
                        request.setAttribute("name", "");
                        request.setAttribute("phone", "");
                        request.setAttribute("address", "");
                        request.setAttribute("username", "");
                        request.getRequestDispatcher("signup.jsp").forward(request, response);
                    } else {
                        String name = request.getParameter("name");
                        String phone = request.getParameter("phone");
                        String address = request.getParameter("address");
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String confirmation = request.getParameter("confirmation");
                        
                        if (!password.equals(confirmation)) {
                            message += "Password does not match confirmation.<br>";
                        }
                        if (daoCust.searchUsername(username) > 0) {
                            message += "Username is already used.<br>";
                        }
                        if (password.length() < 8) {
                            message += "Password must contain at least 8 digits.<br>";
                        }
                        if (message.equals("")) {
                            daoCust.addCustomer(new Customer(name, phone, address, username, password));
                            session.setAttribute("customer", daoCust.userlogin(username, password));
                            response.sendRedirect(".");
                        } else {
                            request.setAttribute("message", message);
                            request.setAttribute("name", name);
                            request.setAttribute("phone", phone);
                            request.setAttribute("address", address);
                            request.setAttribute("username", username);
                            request.getRequestDispatcher("signup.jsp").forward(request, response);
                        }
                    }
                } else {
                    response.sendRedirect("");
                }
                
            }

            /*
            * User logout
             */
            if (userPath.equals("/logout")) {
                String submit = request.getParameter("submit");
                if (customer == null) {
                    response.sendRedirect(".");
                } else if (submit == null || submit.equals("")) {
                    request.getRequestDispatcher("logout.jsp").forward(request, response);
                } else {
                    session.invalidate();
//                request.getRequestDispatcher("index.jsp").forward(request, response);
                    response.sendRedirect(".");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Admin;
import Entity.Bill;
import Entity.BillDetail;
import Entity.Category;
import Entity.Customer;
import Entity.Product;
import DAO.DAOAdmin;
import DAO.DAOBill;
import DAO.DAOCategory;
import DAO.DAOCustomer;
import DAO.DAODetail;
import DAO.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chinh
 */
@WebServlet(name = "ControllerAdmin", urlPatterns = {"/admlogin", "/admin", "/MngProd", "/MngCate", "/MngCust", "/MngBill", "/MngDetail"})
public class ControllerAdmin extends HttpServlet {

    private final DAOAdmin daoAdmin = new DAOAdmin();
    private final DAOCategory daoCate = new DAOCategory();
    private final DAOProduct daoProduct = new DAOProduct();
    private final DAOCustomer daoCustomer = new DAOCustomer();
    private final DAOBill daoBill = new DAOBill();
    private final DAODetail daoDetail = new DAODetail();

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
            HttpSession session = request.getSession();

            int showCate = 0;
            session.setAttribute("showCate", showCate);

            int privilege = 1;
            session.setAttribute("privilege", privilege);

            String userPath = request.getServletPath();

            String service = request.getParameter("service");

            Admin current_admin = (Admin) session.getAttribute("admin");

            if (userPath.equals("/admin")) {
                if (current_admin == null) {
                    response.sendRedirect("admlogin");
                } else {
                    if (service == null) {
                        service = "listAll";
                    }

                    if (service.equalsIgnoreCase("listAll")) {
                        session.setAttribute("showCate", 0);

                        ArrayList<Admin> admin_list = daoAdmin.getAll();

                        String title = "List all Administrators";
                        session.setAttribute("title", title);

                        session.setAttribute("username", "hello");

                        session.setAttribute("admin_list", admin_list);

                        request.getRequestDispatcher("/admin/AdminView.jsp").forward(request, response);
                    }

                    if (service.equalsIgnoreCase("insert")) {
                        String submit = request.getParameter("submit");

                        if (submit == null) {
                            String title = "Add Admin";

                            session.setAttribute("title", title);

                            request.getRequestDispatcher("/admin/AdminEdit.jsp").forward(request, response);
                        } else {
                            String username = request.getParameter("username");
                            String password = request.getParameter("password");

                            Admin admin = new Admin(username, password);
                            daoAdmin.insertAdmin(admin);
                            response.sendRedirect("admin");
                        }
                    }

                    if (service.equalsIgnoreCase("changePswd")) {
                        String submit = request.getParameter("submit");

                        if (submit == null) {
                            int adminID = Integer.parseInt(request.getParameter("adminID"));

                            Admin adm = daoAdmin.getAdmin(adminID);
                            session.setAttribute("adm", adm);

                            String title = "Change Admin Password";

                            session.setAttribute("title", title);

                            request.getRequestDispatcher("/admin/AdminEdit.jsp").forward(request, response);
                        } else {
                            int id = Integer.parseInt(request.getParameter("adminID"));
                            String oldPW = request.getParameter("oldPW");
                            String newPW = request.getParameter("newPW");
                            String confirm = request.getParameter("checkPW");

                            if ((!oldPW.equals(newPW)) && newPW.equals(confirm) && newPW.length() >= 8) {
                                daoAdmin.changePassword(id, oldPW, newPW);
                            }
                            response.sendRedirect("admin");
                        }
                    }
                }
            }

//            Admin Login
            if (userPath.equals("/admlogin")) {
                String submit = request.getParameter("submit");
                String message = "";
                if (submit == null || submit.equals("")) {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    if (username.equals("") || password.equals("")) {
                        message += "Username/Password is empty<br>";
                    } else if (!daoAdmin.login(username, password)) {
                        message += "Wrong username/password";
                    }
                    if (message.equals("")) {
                        Admin login = daoAdmin.getAdmin(username, password);
                        session.setAttribute("admin", login);
                        response.sendRedirect("admin");
                    } else {
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
            }

//            Management of Category
            if (userPath.equals("/MngCate")) {
                if (current_admin == null) {
                    response.sendRedirect("admlogin");
                } else {
                    if (service == null) {
                        service = "listAll";
                    }

                    if (service.equalsIgnoreCase("listAll")) {
                        ArrayList<Category> cate_list = daoCate.getAll();
                        
                        session.setAttribute("cate_list", cate_list);

                        String title = "List all Category for Admin";

                        session.setAttribute("title", title);

                        request.getRequestDispatcher("/admin/CategoryView.jsp").forward(request, response);
                    }

                    if (service.equals("insert") || service.equals("update")) {
                        String submit = request.getParameter("submit");

                        if (submit == null) {
                            String title = "";

                            if (service.equals("insert")) {
                                title = "Insert New Category";
                            }

                            if (service.equals("update")) {
                                int id = Integer.parseInt(request.getParameter("id"));

                                Category category = daoCate.getCate(id);

                                title = "Update Category";
                                
                                session.setAttribute("category", category);
                            }

                            session.setAttribute("title", title);

                            request.getRequestDispatcher("/admin/CategoryEdit.jsp").forward(request, response);
                        } else {
                            String cateID = request.getParameter("cateID");
                            String cateName = request.getParameter("cateName");
                            String status = request.getParameter("status");

                            if (!(daoCate.searchCategory(cateName) > 0) || cateName != null || !cateName.equals("")) {
                                int sta = Integer.parseInt(status);

                                Category cate = new Category(cateName, sta);
                                if (service.equals("insert")) {
                                    daoCate.insertCategory(cate);
                                }
                                if (service.equals("update")) {
                                    daoCate.updateCategory(cate, Integer.parseInt(cateID));
                                }
                            }
                            response.sendRedirect("MngCate");
                        }
                    }

                    if (service.equals("delete")) {
                        String id = request.getParameter("id");
                        daoCate.removeCategory(Integer.parseInt(id));
                        response.sendRedirect("MngCate");
                    }
                }
            }

//            Management of Product
            if (userPath.equals("/MngProd")) {
                if (current_admin == null) {
                    response.sendRedirect("admlogin");
                } else {
                    String sort = request.getParameter("sort");

                    if (service == null) {
                        service = "listAll";
                    }

                    if (service.equals("listAll") || service.equals("search")) {
//                Step 1
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
                        rs = daoProduct.getData(sql);

                        String title = "List all Product for Admin";

//                Step 2
                        request.setAttribute("result", rs);
                        request.setAttribute("title", title);

//                Step 3
                        RequestDispatcher disp = request.getRequestDispatcher("/admin/ProductView.jsp");

//                Run
                        disp.forward(request, response);
                    }

                    if (service.equals("insert") || service.equals(("update"))) {
                        String submit = request.getParameter("submit");

                        if (submit == null) {
                            String pid = request.getParameter("pid");
                            ResultSet rs = daoProduct.getData("select * from Product where pid='" + pid + "'");
                            request.setAttribute("rs", rs);

                            ResultSet rsCate = daoProduct.getData("select * from Category");
                            request.setAttribute("rsCate", rsCate);

                            String title = "";

                            if (service.equals("insert")) {
                                title = "Insert New Product";

                            } else {
                                title = "Update Product";
                            }
                            request.setAttribute("title", title);

                            request.getRequestDispatcher("/admin/ProductEdit.jsp").forward(request, response);
                        } else {
                            String id = request.getParameter("pid");
                            String name = request.getParameter("pname");
                            String quantity = request.getParameter("quantity");
                            String price = request.getParameter("price");
                            String image = request.getParameter("image");
                            String des = request.getParameter("des");
                            String status = request.getParameter("status");
                            String cate = request.getParameter("cate");
                            String location = request.getParameter("location");

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
                                    Product pro = new Product(id, name, quan, pri, image, des, 1, cateID, location);
                                    daoProduct.addProduct(pro);
                                }
                                if (service.equals("update")) {
                                    Product pro = new Product(id, name, quan, pri, image, des, sta, cateID, location);
                                    daoProduct.updateProduct(pro);
                                }
                                response.sendRedirect("MngProd");
                            }
                        }
                    }

                    if (service.equals("delete")) {
                        String pid = request.getParameter("pid");
                        daoProduct.removeProduct(pid);
                        response.sendRedirect("MngProd");

//                String contextPath = request.getContextPath();
//                request.getRequestDispatcher(contextPath + "/product").forward(request, response);
                    }
                }
            }

//            Management of Customer
            if (userPath.equals("/MngCust")) {
                if (current_admin == null) {
                    response.sendRedirect("admlogin");
                } else {
                    if (service == null) {
                        service = "listAll";
                    }

                    if (service.equalsIgnoreCase("listAll")) {
                        ArrayList cust_list = daoCustomer.getAll();
                        session.setAttribute("cust_list", cust_list);
                        
                        String title = "List all Customers for Admin";

                        request.setAttribute("title", title);

                        request.getRequestDispatcher("/admin/CustomerView.jsp").forward(request, response);
                    }

                    if (service.equalsIgnoreCase("insert") || (service.equalsIgnoreCase("update"))) {
                        String submit = request.getParameter("submit");

                        if (submit == null) {
                            String title = "";
                            Customer customer = new Customer();
                            try {
                                int id = Integer.parseInt(request.getParameter("cid"));
                                customer = daoCustomer.getCustomerInfo(id);
                            } catch (NumberFormatException ex) {
                                title = "Invalid customer id";
                            }
                            request.setAttribute("title", title);
                            request.setAttribute("customer", customer);
                            request.getRequestDispatcher("/admin/CustomerEdit.jsp").forward(request, response);
                        } else {
                            String cname = request.getParameter("cname");
                            String cphone = request.getParameter("cphone");
                            String cAddress = request.getParameter("cAddress");
                            String username = request.getParameter("username");
                            String password = request.getParameter("password");
                            int cid = Integer.parseInt(request.getParameter("cid"));
                            int status = Integer.parseInt(request.getParameter("status"));

                            if (service.equalsIgnoreCase("insert")) {
                                Customer cus = new Customer(cname, cphone, cAddress, username, password);
                                daoCustomer.addCustomer(cus);
                                response.sendRedirect("MngCust");
                            }
                            if (service.equalsIgnoreCase("update")) {
                                Customer customer = new Customer(cid, cname, cphone, cAddress, username, "", status);
                                daoCustomer.updateCustomer(customer);
                                response.sendRedirect("MngCust");
                            }
                        }

                    }

                    if (service.equalsIgnoreCase("changePswd")) {
                        String submit = request.getParameter("submit");

                        if (submit == null) {
                            int cid = 0;
                            String title = "";
                            Customer customer = new Customer();
                            try {
                                cid = Integer.parseInt(request.getParameter("cid"));
                                customer = daoCustomer.changePswdInfo(cid);
                            } catch (NumberFormatException ex) {
                                title = "Invalid customer id";
                            }
                            title = "Change password";

                            request.setAttribute("customer", customer);
                            request.setAttribute("title", title);

                            request.getRequestDispatcher("/admin/CustomerEdit.jsp").forward(request, response);
                        } else {
                            int cid = Integer.parseInt(request.getParameter("cid"));
                            String oldPW = request.getParameter("oldPW");
                            String newPW = request.getParameter("newPW");
                            String checkPW = request.getParameter("checkPW");

                            if (!oldPW.equals(newPW) && newPW.equals(checkPW) && newPW.length() >= 8) {
                                daoCustomer.updatePassword(cid, oldPW, newPW);
                            }
                            response.sendRedirect("MngCust");
                        }
                    }

                    if (service.equals("delete")) {
                        String id = request.getParameter("cid");
                        daoCustomer.removeCustomer(Integer.parseInt(id));
                        response.sendRedirect("MngCust");
                    }
                }
            }

//            Management of Bill
            if (userPath.equals("/MngBill")) {
                if (current_admin == null) {
                    response.sendRedirect("admlogin");
                } else {
                    if (service == null) {
                        service = "listAll";
                    }

                    if (service.equalsIgnoreCase("listAll")) {
                        ArrayList<Bill> bills = daoBill.getAll();
                        session.setAttribute("bills", bills);

                        request.getRequestDispatcher("/admin/BillView.jsp").forward(request, response);
                    }

                    if (service.equalsIgnoreCase("delete")) {
                        String oID = request.getParameter("oID");
                        daoBill.deleteBill(oID);
                        response.sendRedirect("MngBill");
                    }

                    if (service.equalsIgnoreCase("updateInfo")) {
                        String submit = request.getParameter("submit");
                        String oID = request.getParameter("oID");
                        if (submit == null) {
                            ArrayList<BillDetail> details = daoDetail.getDetailByoID(oID);
                            session.setAttribute("details", details);

                            Bill bill = daoBill.getBill(oID);
                            session.setAttribute("bill", bill);

                            String title = "Update Status";
                            session.setAttribute("title", title);

                            request.getRequestDispatcher("/admin/BillEdit.jsp").forward(request, response);
                        } else {
                            session.setAttribute("oID", oID);
                            int status = Integer.parseInt(request.getParameter("status"));
                            daoBill.changeStatus(oID, status);

                            response.sendRedirect("MngBill");
                        }
                    }
                }
            }

            if (userPath.equals("/MngDetail")) {
                if (current_admin == null) {
                    response.sendRedirect("admlogin");
                } else {
                    String bill = request.getParameter("bill");

                    if (bill == null) {
                        response.sendRedirect("bill");
                    } else {
                        if (service == null) {
                            service = "";
                        }
                        if (service.equalsIgnoreCase("")) {
                            ArrayList<BillDetail> details = daoDetail.getDetailByoID(bill);
                            session.setAttribute("oID", bill);
                            session.setAttribute("details", details);

                            request.getRequestDispatcher("/admin/DetailView.jsp").forward(request, response);
                        }
                    }
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

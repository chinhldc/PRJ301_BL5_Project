/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnect.DBConnect;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chinh
 */
public class DAOProduct extends DBConnect {
//    public int insertProduct(Product pro) {
//        int n = 0;
//        String sql = "insert into Product(pid, pname, quantity, price, image, description, status, cateID) "
//                + "values('" + pro.getPid() + "','" + pro.getPname() + "'," + pro.getQuantity() +
//                "," + pro.getPrice() + ", '" + pro.getImage() + "', '" + pro.getDescription() + "',"
//               + pro.getStatus() + "," + pro.getCateID() + ")";
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return n;
//    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "insert into Product(pid, pname, quantity, price, image, description, status, cateID) "
                + "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<Product> getShopList() {
        ArrayList<Product> arr = new ArrayList<Product>();

        String sql = "select * from Product where quantity > 0 and status = 1";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String pname = rs.getString(2);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);

                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);

                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Product> searchShopList(int cate, String name) {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "";
        String sql1 = "Select * from Product where quantity > 0 and status = 1 ";
        String sql2 = " and ";
        String sql3 = "";
        String sql4 = "";
        
        if (cate > 0) {
            sql3 = "cateID = " + cate;
        }
        if (!name.isEmpty()) {
            sql4 = "(pid like '%" + name + "%' or pname like '%" + name + "%')";
        }
        if (cate > 0 && !name.isEmpty()) {
            sql = sql1 + sql2 + sql3 + sql2 + sql4;
        }
        if (cate > 0 && name.isEmpty()) {
            sql = sql1 + sql2 + sql3;
        }
        if (cate <= 0 && !name.isEmpty()) {
            sql = sql1 + sql2 + sql4;
        }
        if ((cate <= 0 && name.isEmpty())) {
            sql = sql1;
        }
        
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String pname = rs.getString(2);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);

                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);

                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;
    }

    public ArrayList<Product> getCategoryItems(int cate) {
        ArrayList<Product> arr = new ArrayList<Product>();

        String sql = "Select * from Product where quantity > 0 and status = 1 and cateID = " + cate;

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String pname = rs.getString(2);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);

                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);

                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;
    }

    public int seachProduct(String search) {
        int n = 0;
        String sql = "select pid from Product where pid='" + search + "' OR pname = '" + search + "'";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                n = n + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;

        String sql = "update Product set pname=?, quantity=?, price=?, image=?, description=?, "
                + "status=?, cateID=? where pid=?";
        //code here
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setString(5, pro.getDescription());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateID());
            pre.setString(8, pro.getPid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateQuantity(String id, int quanity) {
        int n = 0;
        String sql = "update Product set quantity = quantity + ? where pid=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, quanity);
            pre.setString(2, id);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Updated quantity successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getMaxQuantity(String id) {
        ResultSet rs = getData("select quantity from Product where pid = '" + id + "'");
        try {
            if (rs.next()) {
                int maxQuantity = rs.getInt(1);
                return maxQuantity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void changeStatus(String id, int status) {
        int n = 0;
        String sql = "update Product set status=? where pid=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, status);
            pre.setString(2, id);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Changed status successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProduct(String pid) {
        String sql = "select * from Product where pid='" + pid + "'";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                Product prod = new Product();

                prod.setPid(rs.getString(1));
                prod.setPname(rs.getString(2));
                prod.setQuantity(rs.getInt(3));
                prod.setPrice(rs.getDouble(4));
                prod.setImage(rs.getString(5));
                prod.setDescription(rs.getString(6));
                prod.setStatus(rs.getInt(7));
                prod.setCateID(rs.getInt(8));
                return prod;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void displayAll() {
        String sql = "select * from Product";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String pname = rs.getString(2);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);

                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getPrice(double from, double to) {
        ArrayList<Product> arr = new ArrayList<Product>();

        String sql = "select * from Product where price between " + from + " and " + to;

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String pname = rs.getString(2);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);

                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);

                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Product> getAll() {
        ArrayList<Product> arr = new ArrayList<Product>();

        String sql = "select * from Product";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String pid = rs.getString("pid");
                String pname = rs.getString(2);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);

                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);

                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public int removeProduct(String id) {
        int n = 0;
        String sql1 = "delete from Product where pid=?";

        String sql2 = "select * from BillDetail where pid='" + id + "'";

        // Check foreign key constraint
        // from BillDetail
        ResultSet rs = getData(sql2);
        try {
            if (rs.next()) {
                changeStatus(id, 0);
            } else {
                PreparedStatement pre = conn.prepareStatement(sql1);

                pre.setString(1, id);

                n = pre.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
//        int n = dao.addProduct(new Product("PEP02", "Pepsi Diet", 500, 10, "no image", "Pepsi giam can", 1, 1));
//        n = dao.addProduct(new Product("P04", "Dell Pro", 2, 1500, "no image", "new", 1, 1));
//        n = dao.addProduct(new Product("M01", "Macbook", 3, 2000, "no image", "new", 1, 1));
//        n = dao.addProduct(new Product("M02", "Macbook Air", 10, 1700, "no image", "new", 1, 1));
//        n = dao.addProduct(new Product("P02", "HP G6", 2, 500, "no image", "second hand", 1, 1));
//        if (n > 0) {
//            System.out.println("Inserted!");
//        }
//        n = dao.updateProduct(new Product("P02", "HP G6", 2, 800, "no image", "second hand", 1, 1));
//        if (n > 0) {
//            System.out.println("Updated!");
//        }
//        dao.updateQuantity("M01", 50);
//        dao.changeStatus("P04", 0);
//        n = dao.removeProduct("P02");
//        if (n > 0) {
//            System.out.println("Removed successfully!");
//        }
        dao.displayAll();
        ResultSet rs = dao.getData("SELECT * FROM Category");
        try {
            while (rs.next()) {
                System.out.println("To be continued");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

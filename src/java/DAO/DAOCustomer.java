/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnect.DBConnect;
import Entity.Customer;
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
public class DAOCustomer extends DBConnect {

    public int addCustomer(Customer customer) {
        int n = 0;
        String sql = "insert into Customer(cname, cphone, cAddress, username, password) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getCphone());
            pre.setString(3, customer.getcAddress());
            pre.setString(4, customer.getUsername());
            pre.setString(5, customer.getPassword());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCustomer(Customer customer) {
        int n = 0;
        String sql = "update Customer set cname=?, cphone=?, cAddress=?, username=?, password=?, status=?"
                + " where cid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getCphone());
            pre.setString(3, customer.getcAddress());
            pre.setString(4, customer.getUsername());
            pre.setString(5, customer.getPassword());
            pre.setInt(6, customer.getStatus());
            pre.setInt(7, customer.getCid());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int removeCustomer(int id) {
        int n = 0;
        String sql1 = "delete from Customer where cid=?";
        String sql2 = "select * from Bill where cid=" + id;

        ResultSet rs = getData(sql2);

        try {
            if (rs.next()) {
                changeStatus(id, 0);
            } else {
                PreparedStatement pre = conn.prepareStatement(sql1);

                pre.setInt(1, id);

                n = pre.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void changeStatus(int id, int status) {
        int n = 0;
        String sql = "update Customer set status=? where cid=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, status);
            pre.setInt(2, id);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Changed status successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePassword(int id, String oldPW, String newPW) {
        int n = 0;
        String sql = "update Customer set password=? where cid=? and password=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, newPW);
            pre.setInt(2, id);
            pre.setString(3, oldPW);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Updated password successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int searchUsername(String username) {
        int n = 0;

        String sql = "select username from Customer where username='" + username + "'";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                n = n + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Customer userlogin(String username, String password) {
        Customer customer = new Customer();
        String sql = "select * from Customer where username='" + username + "' and password='" + password + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomerInfo(int cid) {
        ResultSet rs = getData("select * from Customer where cid = " + cid);
        try {
            if (rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Customer> getAll() {
        ArrayList<Customer> arr = new ArrayList<Customer>();

        String sql = "select * from Customer";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int cid = rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);

                arr.add(new Customer(cid, cname, cphone, cAddress, username, password, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
//        int n = dao.addCustomer(new Customer("Chinh Le", "0987123456", "Hanoi", "bzcut123", "Chinh123"));
//        if (n > 0) {
//            System.out.println("Inserted!");
//        }

        int n = dao.addCustomer(new Customer("Le Duc Chinh", "0981858602", "Hanoi", "chjmbjm", "Chinh123"));
        if (n > 0) {
            System.out.println("Inserted!");
        }
//        
//        n = dao.removeCustomer(2);
//        if (n > 0) {
//            System.out.println("Deleted!");
//        }
    }
}

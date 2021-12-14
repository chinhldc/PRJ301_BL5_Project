/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnect.DBConnect;
import Entity.Admin;
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
public class DAOAdmin extends DBConnect {
    public int insertAdmin(Admin admin) {
        int n = 0;
        String sql = "insert into admin(username, password) values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, admin.getUsername());
            pre.setString(2, admin.getPassword());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int removeAdmin(int adminID) {
        int n = 0;
        String sql = "delete from admin where adminID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, adminID);
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void changePassword(int adminID, String oldPW, String newPW) {
        int n = 0;
        String sql = "update admin set password=? where adminID=? and password=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, newPW);
            pre.setInt(2, adminID);
            pre.setString(3, oldPW);
            
            n = pre.executeUpdate();
            
            if (n > 0) {
                System.out.println("Updated password successfully!");
            }
        } catch (SQLException ex) { 
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayAll() {
        
        String sql = "select * from admin";
        
        ResultSet rs = getData(sql);
        
        try {
            while (rs.next()) {
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                
                System.out.println(new Admin(adminID, username, password));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Admin> getAll() {
        ArrayList<Admin> arr = new ArrayList<Admin>();
        
        String sql = "select * from admin";
        
        ResultSet rs = getData(sql);
        
        try {
            while (rs.next()) {
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                
                arr.add(new Admin(adminID, username, password));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public boolean login(String userName, String password) {
        boolean flag = false;
        String sql = "select * from admin where username=? and password=? and status=1";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, userName);
            pre.setString(2, password);
            
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
        
        int n = dao.removeAdmin(1);
        if (n > 0) {
            System.out.println("Removed!");
        }
        
        n = dao.removeAdmin(2);
        if (n > 0) {
            System.out.println("Removed!");
        }
        
        n = dao.insertAdmin(new Admin("zxc123", "Chinh123"));
        if (n > 0) {
            System.out.println("Inserted!");
        }
        
        
        n = dao.insertAdmin(new Admin("asd123", "Chinh123"));
        if (n > 0) {
            System.out.println("Inserted!");
        }
        
//        dao.changePassword(2, "Chinh123///");
    }
}

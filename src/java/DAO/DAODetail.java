/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.BillDetail;
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
public class DAODetail extends DBConnect.DBConnect {
    public int insertDetail(BillDetail bd) {
        int n = 0;
        
        String sql = "insert into BillDetail(pid, oID, quantity, price, total)"
                + "values(?,?,?,?,?)";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, bd.getPid());
            pre.setString(2, bd.getoID());
            pre.setInt(3, bd.getQuantity());
            pre.setDouble(4, bd.getPrice());
            pre.setDouble(5, bd.getTotal());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateDetail(BillDetail bd) {
        int n = 0;
        
        String sql = "update BillDetail set quantity=?, price=?, total=? where pid=? and oID=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, bd.getQuantity());
            pre.setDouble(2, bd.getPrice());
            pre.setDouble(3, bd.getQuantity() * bd.getPrice());
            pre.setString(4, bd.getPid());
            pre.setString(5, bd.getoID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int deleteDetail(String pid, String oID) {
        int n = 0;
        String sql = "delete from BillDetail where pid=? and oID=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, pid);
            pre.setString(2, oID);
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public ArrayList<BillDetail> getDetailByoID(String order) {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        
        String sql = "select * from BillDetail where oID = '" + order + "'";
        
        ResultSet rs = getData(sql);
        
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double money = rs.getDouble(4);
                double total = rs.getDouble(5);
                
                arr.add(new BillDetail(pid, oID, quantity, money, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<BillDetail> getAll() {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        
        String sql = "select * from BillDetail";
        
        ResultSet rs = getData(sql);
        
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double money = rs.getDouble(4);
                double total = rs.getDouble(5);
                
                arr.add(new BillDetail(pid, oID, quantity, money, total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}

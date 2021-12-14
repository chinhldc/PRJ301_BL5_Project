/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Bill;
import java.sql.Date;
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
public class DAOBill extends DBConnect.DBConnect {

    public int addBill(Bill bill) {
        int n = 0;

        String sql = "insert into Bill (oID, cname, cphone, cAddress, total, status, cid)"
                + "values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, bill.getoID());
            pre.setString(2, bill.getCname());
            pre.setString(3, bill.getCphone());
            pre.setString(4, bill.getcAddress());
            pre.setDouble(5, bill.getTotal());
            pre.setInt(6, bill.getStatus());
            pre.setInt(7, bill.getCid());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateTotal(String oID, double total) {
        int n = 0;

        String sql = "update Bill SET total=? where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setDouble(1, total);
            pre.setString(2, oID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateTotal(String oID) {
        int n = 0;
        
        String sql = "update b "
                + "SET b.total = f.valsum "
                + "from Bill b "
                + "INNER JOIN ("
                + "select oID, SUM(total) valsum "
                + "from BillDetail group by oID) f on b.oID = f.oID "
                + "where b.oID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, oID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateInfo(String oID, String cname, String cphone, String cAddress) {
        int n = 0;

        String sql = "update Bill SET cname=?, cphone=?, cAddress=? where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cname);
            pre.setString(2, cphone);
            pre.setString(3, cAddress);
            pre.setString(4, oID);

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateBill(Bill bill) {
        int n = 0;

        String sql = "update Bill SET cname=?, cphone=?, cAddress=?, total=?, status=?"
                + " where oID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, bill.getCname());
            pre.setString(2, bill.getCphone());
            pre.setString(3, bill.getcAddress());
            pre.setDouble(4, bill.getTotal());
            pre.setInt(5, bill.getStatus());
            pre.setString(6, bill.getoID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void changeStatus(String oID, int status) {
        int n = 0;
        String sql = "update Bill set status=? where oID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, status);
            pre.setString(2, oID);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Changed status successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Bill> getAll() {
        ArrayList<Bill> arr = new ArrayList<Bill>();

        String sql = "select * from Bill";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String oID = rs.getString(1);
                Date dateCreate = rs.getDate(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                Double total = rs.getDouble(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);

                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);

                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public int deleteBill(String oID) {
        int n = 0;
        String sql1 = "delete from Bill where oID=?";
        String sql2 = "select * from BillDetail where oID='" + oID + "'";

        ResultSet rs = getData(sql2);
        try {
            if (rs.next()) {
                changeStatus(oID, 0);
            } else {
                PreparedStatement pre = conn.prepareStatement(sql1);

                pre.setString(1, oID);

                n = pre.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {

    }
}

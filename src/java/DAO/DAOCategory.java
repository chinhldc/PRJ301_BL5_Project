/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnect.DBConnect;
import Entity.Category;
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
public class DAOCategory extends DBConnect {

    public int insertCategory(Category cate) {
        int n = 0;
        String sql = "insert into Category(cateName, status) values(?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCategory(Category cate, int i) {
        int n = 0;
        String sql = "update Category set cateName=?, status=? where cateID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());
            pre.setInt(3, i);

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int searchCategory(String cateName) {
        int n = 0;
        String sql = "select cateName from Category where cateName='" + cateName + "'";

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

    public void changePassword(int id, String password) {
        int n = 0;
        String sql = "update Category set password=? where cateID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, password);
            pre.setInt(2, id);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Changed status successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeStatus(int id, int status) {
        int n = 0;
        String sql = "update Category set status=? where cateID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, status);
            pre.setInt(2, id);

            n = pre.executeUpdate();

            if (n > 0) {
                System.out.println("Changed status successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> arr = new ArrayList<Category>();

        String sql = "select * from Category";

        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int cateId = rs.getInt("cateId");
                String cateName = rs.getString(2);
                int status = rs.getInt(3);

                Category cate = new Category(cateId, cateName, status);

                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public int removeCategory(int id) {
        int n = 0;
        String sql1 = "delete from Category where cateID=?";
        String sql2 = "select * from Product where cateID=" + id;

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
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        System.out.println(dao.searchCategory("Food"));
//        int n = dao.insertCategory(new Category("Tools", 1));
//        if (n > 0) {
//            System.out.println("Inserted!");
//        }
//        n = dao.updateCategory(new Category(2, "Drink", 1));
//        if (n > 0) {
//            System.out.println("Updated!");
//        }
//        dao.changeStatus(4, 1);
//        n = dao.removeCategory(3);
//        if (n > 0) {
//            System.out.println("Removed successfully!");
//        }
    }
}

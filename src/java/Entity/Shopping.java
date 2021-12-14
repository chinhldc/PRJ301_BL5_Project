/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author chinh
 */
public class Shopping {
    private String pid;
    private String pname;
    private int quantity;
    private double price;

    public Shopping() {
    }

    public Shopping(String pid, String pname, int quantity, double price) {
        this.pid = pid;
        this.pname = pname;
        this.quantity = quantity;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String name) {
        this.pname = name;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}

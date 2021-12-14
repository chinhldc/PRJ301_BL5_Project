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
public class BillDetail {
    private String pid;
    private String oID;
    private int quantity;
    private Double price;
    private Double total;

    public BillDetail(String pid, String oID, int quantity, Double price, Double total) {
        this.pid = pid;
        this.oID = oID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    
    public BillDetail(String pid, String oID, int quantity, Double price) {
        this.pid = pid;
        this.oID = oID;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}

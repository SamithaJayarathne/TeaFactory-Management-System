/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class OrderItem {
    
    private String OrderId;
    private String CustomerNic;
    private String OrderStatus;
    private String PaymentMethod;
    private String PaymentStatus;
    private String productName;
    private String ProductCategory; 
    private Double UnitPrice; 
    private Double Qty; 
    private String productId;
    private Double total;
    
    
   
    public String getOrderId() {
        return OrderId;
    }

    
    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }
    public String getproductId() {
        return OrderId;
    }

    
    public void setproductId(String OrderId) {
        this.OrderId = OrderId;
    }

   
    public String getCustomerNic() {
        return CustomerNic;
    }

    
    public void setCustomerNic(String CustomerNic) {
        this.CustomerNic = CustomerNic;
    }

    
    public String getOrderStatus() {
        return OrderStatus;
    }

    
    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

   
    public String getPaymentMethod() {
        return PaymentMethod;
    }

    
    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

   
    public String getPaymentStatus() {
        return PaymentStatus;
    }

   
    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    
    public String getProductName() {
        return productName;
    }

   
    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    public String getProductCategory() {
        return ProductCategory;
    }

   
    public void setProductCategory(String ProductCategory) {
        this.ProductCategory = ProductCategory;
    }

    
    public Double getUnitPrice() {
        return UnitPrice;
    }

    
    public void setUnitPrice(Double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

   
    public Double getQty() {
        return Qty;
    }

    
    public void setQty(Double Qty) {
        this.Qty = Qty;
    }
    
    public void clearData(){
    this.OrderId = "";
    this.CustomerNic = "";
    this.OrderStatus = "";
    this.PaymentMethod = "";
    this.PaymentStatus = "";
    this.productName = "";
    this.ProductCategory = "";
    this.UnitPrice = 0.0;
    this.Qty = 0.0;
    this.productId = "";
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }
    
}

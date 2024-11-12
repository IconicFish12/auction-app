package lelang.app.model;

import java.util.Date;

public class Order {
    private long id, penawaranId;
    private Date orderDate, deliveryDate;
    private String status, shippingAddress, shippingStatus, paymentStastus, paymentMethod;
    private int harga_akhir;

    
    public Order(long id, long penawaranId, Date orderDate, Date deliveryDate, String status, String shippingAddress,
            String shippingStatus, String paymentStastus, String paymentMethod, int harga_akhir) {
        this.id = id;
        this.penawaranId = penawaranId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
        this.paymentStastus = paymentStastus;
        this.paymentMethod = paymentMethod;
        this.harga_akhir = harga_akhir;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getPenawaranId() {
        return penawaranId;
    }


    public void setPenawaranId(long penawaranId) {
        this.penawaranId = penawaranId;
    }


    public Date getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public Date getDeliveryDate() {
        return deliveryDate;
    }


    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getShippingAddress() {
        return shippingAddress;
    }


    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    public String getShippingStatus() {
        return shippingStatus;
    }


    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }


    public String getPaymentStastus() {
        return paymentStastus;
    }


    public void setPaymentStastus(String paymentStastus) {
        this.paymentStastus = paymentStastus;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }


    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public int getHarga_akhir() {
        return harga_akhir;
    }


    public void setHarga_akhir(int harga_akhir) {
        this.harga_akhir = harga_akhir;
    }

    
}

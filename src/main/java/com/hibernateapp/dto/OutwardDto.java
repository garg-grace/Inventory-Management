package com.hibernateapp.dto;

import java.time.LocalDate;

public class OutwardDto {
    private int id;
    private String invoice;
    private int quantity;
    private LocalDate dateOfSupply;
    private String productTitle;
    private String customerName;
    private String customerCity;
    private String warehouseLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateOfSupply() {
        return dateOfSupply;
    }

    public void setDateOfSupply(LocalDate dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public String toString() {
        return "OutwardDto{" +
                "id=" + id +
                ", invoice='" + invoice + '\'' +
                ", quantity=" + quantity +
                ", dateOfSupply=" + dateOfSupply +
                ", productTitle='" + productTitle + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                '}';
    }
}

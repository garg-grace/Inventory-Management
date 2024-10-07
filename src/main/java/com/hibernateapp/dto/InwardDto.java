package com.hibernateapp.dto;

import java.time.LocalDate;

public class InwardDto {
    private int id;
    private String invoice;
    private int quantity;
    private LocalDate dateOfSupply;
    private String productTitle;
    private String supplierName;
    private String supplierCity;
    private String warehouseLocation;
    private String productCategory;

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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "InwardDto{" +
                "id=" + id +
                ", invoice='" + invoice + '\'' +
                ", quantity=" + quantity +
                ", dateOfSupply=" + dateOfSupply +
                ", productTitle='" + productTitle + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierCity='" + supplierCity + '\'' +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}

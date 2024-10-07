package com.hibernateapp.controller;
import com.hibernateapp.dto.InwardDto;
import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hibernateapp.service.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class InwardController {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory_management_system_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService(entityManager);
        WarehouseService warehouseService = new WarehouseService(entityManager);
        SupplierService supplierService = new SupplierService(entityManager);
        InwardService inwardService = new InwardService(entityManager);

        while(true){
            entityTransaction.begin();
            System.out.println("1.Make Inward Entry");
            System.out.println("2. View all Inwards");
            System.out.println("3. View Inwards by warehouse");
            System.out.println("4. View product stats for each warehouse");
            System.out.println("5. Delete Inward Entry");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if(input==0){
                System.out.println("Exiting...");
                entityTransaction.commit();
                break;
            }

            switch(input){
                case 1:
                    System.out.println("Enter Invoice no.");
                    String invoice = sc.next();

                    System.out.println("Enter quantity");
                    int quantity = sc.nextInt();

                    System.out.println("Enter product id");
                    int productId = sc.nextInt();
                    // go to db and fetch object using id
                    Product product;
                    try {
                        product = productService.getById(productId);

                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter warehouse id");
                    int warehouseId = sc.nextInt();
                    Warehouse warehouse;
                    try {
                        warehouse = warehouseService.getById(warehouseId);
                    
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    
                    System.out.println("Enter supplier id");
                    int supplierId = sc.nextInt();
                    Supplier supplier;
                    try {
                        supplier = supplierService.getById(supplierId);

                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                   
                    //attach the objects and obher props to inward register
                    InwardRegister inwardRegister = new InwardRegister();
                    inwardRegister.setProduct(product);
                    inwardRegister.setWarehouse(warehouse);
                    inwardRegister.setSupplier(supplier);
                    inwardRegister.setInvoice(invoice);
                    inwardRegister.setQuantity(quantity);
                    inwardRegister.setDateOfSupply(LocalDate.now());


                    //give this inward object to service and persist it in db
                    inwardService.insert(inwardRegister);
                    System.out.println("Entry made in inward register");
                    break;
                case 2:
                    List<InwardRegister> list = inwardService.getAll();
                    List<InwardDto> listDto = new ArrayList<>();
                    list.stream().forEach(ir->{
                        InwardDto dto = new InwardDto();
                        dto.setId(ir.getId());
                        dto.setInvoice(ir.getInvoice());
                        dto.setQuantity(ir.getQuantity());
                        dto.setDateOfSupply(ir.getDateOfSupply());
                        dto.setProductTitle(ir.getProduct().getTitle());
                        dto.setSupplierName(ir.getSupplier().getName());
                        dto.setSupplierCity(ir.getSupplier().getAddress().getCity());
                        dto.setWarehouseLocation(ir.getWarehouse().getLocation());
                        dto.setProductCategory(ir.getProduct().getCategory().getName());
                        listDto.add(dto);
                    });
                    listDto.stream().forEach(System.out::println);
                    break;
                case 4:
                    list = inwardService.getAll();
                    Map<String,Long> map = inwardService.getProductForEachWarehouse(list);
                    System.out.println(map);
                    break;
                case 5:
                    System.out.println("Enter Inward ID");
                    int inwardId = sc.nextInt();

                    try {
                        InwardRegister ir = inwardService.getById(inwardId);
                        inwardService.delete(ir);
                        System.out.println("Entry deleted..");
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
            }
            entityTransaction.commit();
        }

    }
}

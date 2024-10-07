package com.hibernateapp.controller;
import com.hibernateapp.dto.OutwardDto;
import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import com.hibernateapp.service.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
public class OutwardController {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory_management_system_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService(entityManager);
        WarehouseService warehouseService = new WarehouseService(entityManager);
        CustomerService customerService = new CustomerService(entityManager);
        OutwardService outwardService = new OutwardService(entityManager);

        while(true){
            entityTransaction.begin();
            System.out.println("1.Make Outward Entry");
            System.out.println("2. View all Outwards");
            System.out.println("3. View Outwards by warehouse");
            System.out.println("4. View product stats for each warehouse");
            System.out.println("5. Delete Outward Entry");
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
                    
                    System.out.println("Enter customer id");
                    int customerId = sc.nextInt();
                    Customer customer;
                    try {
                        customer = customerService.getById(customerId);

                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                   
                    //attach the objects and obher props to inward register
                    OutwardRegister outwardRegister = new OutwardRegister();
                    outwardRegister.setProduct(product);
                    outwardRegister.setWarehouse(warehouse);
                    outwardRegister.setCustomer(customer);
                    outwardRegister.setInvoiceNo(invoice);
                    outwardRegister.setQuantity(quantity);
                    outwardRegister.setDateOfSupply(LocalDate.now());
                    outwardRegister.setDateOfDelivery(LocalDate.now().plusDays(2));


                    //give this inward object to service and persist it in db
                    outwardService.insert(outwardRegister);
                    System.out.println("Entry made in outward register");
                    break;
                case 2:
                    List<OutwardRegister> list = outwardService.getAll();
                    List<OutwardDto> listDto = new ArrayList<>();
                    list.stream().forEach(or->{
                        OutwardDto dto = new OutwardDto();
                        dto.setId(or.getId());
                        dto.setInvoice(or.getInvoiceNo());
                        dto.setQuantity(or.getQuantity());
                        dto.setDateOfSupply(or.getDateOfSupply());
                        dto.setProductTitle(or.getProduct().getTitle());
                        dto.setCustomerName(or.getCustomer().getName());
                        dto.setCustomerCity(or.getCustomer().getAddress().getCity());
                        dto.setWarehouseLocation(or.getWarehouse().getLocation());
                        listDto.add(dto);
                    });
                    listDto.stream().forEach(System.out::println);
                    break;
                
            }
            entityTransaction.commit();
        }

    }

}

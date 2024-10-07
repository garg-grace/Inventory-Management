package com.hibernateapp.controller;
import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.Category;
import com.hibernateapp.model.Product;
import java.util.List;
import java.util.Scanner;

import com.hibernateapp.service.CategoryService;
import com.hibernateapp.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProductController {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory_management_system_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService(entityManager);
        CategoryService categoryService = new CategoryService();

        while(true){
            entityTransaction.begin();
            System.out.println("1.Insert Product");
            System.out.println("2. View all products");
            System.out.println("3. View product by category");
            System.out.println("4. Delete product");
            System.out.println("5. Update product details");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if(input==0){
                System.out.println("Exiting...");
                entityTransaction.commit();
                break;
            }
            Product product = new Product();
            switch (input) {
                case 1:
                    System.out.println("Enter category Id");
                    int catId = sc.nextInt();
                    try {
                        Category category = categoryService.getById(entityManager,catId);
                        System.out.println("Enter Title");
                        sc.nextLine();
                        product.setTitle(sc.nextLine());
                        System.out.println("Enter Price");
                        product.setPrice(sc.nextDouble());
                        product.setTagline("tag");
                        product.setDescription("descr");

                        product.setCategory(category);
                        productService.insert(entityManager,product);
                        System.out.println("Product added to DB..");
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    List<Product> list = productService.getAllProducts();
                    list.stream().forEach(System.out :: println);
                    break;
                case 3:
                    System.out.println("Enter category Id");
                    catId = sc.nextInt();
                    try {
                        Category category = categoryService.getById(entityManager,catId);
                        list = productService.getProductByCategoryId(catId);
                        list.stream().forEach(System.out :: println);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter Product Id");
                    int productId = sc.nextInt();
                    try {
                        product = productService.getById(productId);
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Enter Title");
                    sc.nextLine();
                    product.setTitle(sc.nextLine());
                    System.out.println("Enter Price");
                    product.setPrice(sc.nextDouble());

                    productService.insert(entityManager,product);
                    System.out.println("Product record updated");
                    break;
                default:
                    System.out.println("Invalid input..");
                    break;
            }
            entityTransaction.commit();
        }
    }

}

package com.hibernateapp.controller;
import com.hibernateapp.model.Category;

import java.util.Scanner;

import com.hibernateapp.service.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CategoryController {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory_management_system_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();       

        EntityTransaction entityTransaction = entityManager.getTransaction();
        
        CategoryService service = new CategoryService();

        Scanner sc = new Scanner(System.in);  
        System.out.println("Enter Category name");
        String name = sc.next();
        System.out.println("Enter Category Priority");
        int priority = sc.nextInt();

        Category cat = new Category();
        cat.setName(name);
        cat.setPriority(priority);

        entityTransaction.begin();
        service.insert(entityManager,cat);
        System.out.println("Insertion success");
        entityTransaction.commit();
        

    }

}

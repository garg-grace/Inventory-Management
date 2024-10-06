package com.hibernateapp.service;

import com.hibernateapp.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

import javax.management.Query;

import com.hibernateapp.model.Product;

import java.util.List;

public class ProductService {

    EntityManager entityManager;

    public ProductService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void insert(EntityManager entityManager,Product product){
        entityManager.persist(product);
    }

    public List<Product> getAllProducts(){
        List<Product> list = entityManager.createQuery("select p from Product p",Product.class).getResultList();
        return list;
    }

    public List<Product> getProductByCategoryId(int catId){
        jakarta.persistence.Query query = entityManager.createQuery("select p from Product p where p.category.id = :catId",Product.class);
        query.setParameter("catId", catId);
        List<Product> list = query.getResultList();
        return list;

    }

    public Product getById(int productId) throws ResourceNotFoundException{
        Product product = entityManager.find(Product.class,productId);
        if(product==null) throw new ResourceNotFoundException("Product Id invalid");
        
        return product;
    }
}

package com.hibernateapp.service;
import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.Category;
import jakarta.persistence.EntityManager;

public class CategoryService {

    public void insert(EntityManager entityManager,Category cat){
        entityManager.persist(cat);      
    }

    public Category getById(EntityManager entityManager,int catId) throws ResourceNotFoundException {
        Category category =entityManager.find(Category.class,catId);
        if(category==null){
            throw new ResourceNotFoundException("Invalid Id Given");
        }
        return category;
    }
}

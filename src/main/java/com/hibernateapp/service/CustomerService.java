package com.hibernateapp.service;

import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.Customer;
import jakarta.persistence.EntityManager;

public class CustomerService {
    EntityManager entityManager;

    public CustomerService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Customer getById(int customerId) throws ResourceNotFoundException {
        Customer customer = entityManager.find(Customer.class,customerId);
        if(customer ==null) throw new ResourceNotFoundException("Invalid customer ID");
        return customer;
    }

}

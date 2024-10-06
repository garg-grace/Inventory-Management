package com.hibernateapp.service;

import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.Supplier;

import jakarta.persistence.EntityManager;

public class SupplierService {
    EntityManager entityManager;

    public SupplierService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Supplier getById(int supplierId) throws ResourceNotFoundException {
        Supplier supplier = entityManager.find(Supplier.class,supplierId);
        if(supplier==null) throw new ResourceNotFoundException("Invalid supplier Id");
        
        return supplier;
    }

}

package com.hibernateapp.service;
import com.hibernateapp.exception.ResourceNotFoundException;
import com.hibernateapp.model.Warehouse;

import jakarta.persistence.EntityManager;

public class WarehouseService {

    EntityManager entityManager;

    public WarehouseService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Warehouse getById(int warehouseId) throws ResourceNotFoundException {
        Warehouse warehouse = entityManager.find(Warehouse.class,warehouseId);
        if(warehouse==null) throw new ResourceNotFoundException("Invalid Warehouse Id");
        
        return warehouse;
    }

}

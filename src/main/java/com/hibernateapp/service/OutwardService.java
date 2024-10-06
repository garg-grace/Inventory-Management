package com.hibernateapp.service;

import com.hibernateapp.model.OutwardRegister;
import jakarta.persistence.EntityManager;

public class OutwardService {
    EntityManager entityManager;

    public OutwardService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void insert(OutwardRegister outwardRegister){
        entityManager.persist(outwardRegister);
    }

}

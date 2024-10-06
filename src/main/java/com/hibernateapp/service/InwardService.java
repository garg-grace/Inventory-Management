package com.hibernateapp.service;

import com.hibernateapp.model.InwardRegister;

import jakarta.persistence.EntityManager;
public class InwardService {
    EntityManager entityManager;

    public InwardService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void insert(InwardRegister inwardRegister){
        entityManager.persist(inwardRegister);
    }

}

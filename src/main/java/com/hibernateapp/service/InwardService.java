package com.hibernateapp.service;

import com.hibernateapp.model.InwardRegister;

import jakarta.persistence.EntityManager;

import java.util.List;

public class InwardService {
    EntityManager entityManager;

    public InwardService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void insert(InwardRegister inwardRegister){
        entityManager.persist(inwardRegister);
    }

    public List<InwardRegister> getAll() {
        List<InwardRegister> list = entityManager
                .createQuery("select i from InwardRegister i",InwardRegister.class)
                .getResultList();
        return list;
    }
}

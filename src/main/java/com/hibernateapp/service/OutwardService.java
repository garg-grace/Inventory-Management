package com.hibernateapp.service;

import com.hibernateapp.model.OutwardRegister;
import jakarta.persistence.EntityManager;

import java.util.List;

public class OutwardService {
    EntityManager entityManager;

    public OutwardService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void insert(OutwardRegister outwardRegister){
        entityManager.persist(outwardRegister);
    }


    public List<OutwardRegister> getAll() {
        List<OutwardRegister> list = entityManager
                .createQuery("select o from OutwardRegister o",OutwardRegister.class)
                .getResultList();
        return list;
    }
}

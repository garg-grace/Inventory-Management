package com.hibernateapp.service;

import com.hibernateapp.model.InwardRegister;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, Long> getProductForEachWarehouse(List<InwardRegister> list) {
        Map<String,Long> map = list.stream()
                .collect(Collectors.groupingBy((InwardRegister ir)->ir.getWarehouse().getLocation(),Collectors.counting()));
        return  map;
    }
}

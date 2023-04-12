package com.example.demo.repository;

import com.example.demo.domain.models.Entity;

import java.util.List;

public interface Repository<E extends Entity> {
    void create(E entity);
    List<E> read();
    E read(int id);
    void update(E oldEntity, E newEntity);
    void delete(E entity);
}
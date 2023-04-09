package com.example.demo.repository;

import com.example.demo.domain.Entity;

import java.util.List;
import java.util.Optional;

public interface Repository<E extends Entity> {
    void create(E entity);
    List<E> read();
    Optional<E> read(int index);
    void update(E oldEntity, E newEntity);
    void delete(E entity);
}
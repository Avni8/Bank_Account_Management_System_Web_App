/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;
import java.util.List;

/**
 *
 * @author avni
 * @param <T>
 * @param <ID>
 */

public interface CrudRepository<T> {
    
    T save(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);   
}

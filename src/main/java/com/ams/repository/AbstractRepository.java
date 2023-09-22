/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import javax.persistence.EntityManager;
import java.util.List;
import com.ams.model.AbstractEntity;

/**
 *
 * @author avni
 * @param <T>
 */
//implements CrudRepository<T, ID>
public abstract class AbstractRepository<T extends AbstractEntity> {

    protected abstract EntityManager entityManager();

    private final Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
//        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
//        this.entityClass = (Class<T>) type.getActualTypeArguments()[0];
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        entityManager().persist(entity);
        return entity;
    }

    public T findById(Long id) {
        return entityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        return (List<T>) entityManager().createQuery("SELECT entity FROM " + entityClass.getSimpleName() + " entity", entityClass)
                .getResultList();
    }

    public void update(T entity) {
        entityManager().merge(entity);
    }

    public void delete(Long id) {
        T entity = entityManager().find(entityClass, id);
        if (entity != null) {

            entityManager().remove(entity);
            entityManager().flush();
        }
    }
}

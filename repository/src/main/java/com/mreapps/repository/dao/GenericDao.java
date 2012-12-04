package com.mreapps.repository.dao;

import com.mreapps.repository.entity.BaseEntity;

public interface GenericDao<E extends BaseEntity>
{
    E get(Long id);

    E persist(E entity);

    E merge(E entity);

    void delete(E entity);
}

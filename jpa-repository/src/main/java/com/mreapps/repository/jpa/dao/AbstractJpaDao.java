package com.mreapps.repository.jpa.dao;

import com.mreapps.repository.dao.GenericDao;
import com.mreapps.repository.entity.BaseEntity;
import org.apache.commons.lang.Validate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractJpaDao<E extends BaseEntity, J extends E> implements GenericDao<E>
{
    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<J> entityClass;

    protected AbstractJpaDao(Class<J> entityClass)
    {
        Validate.notNull(entityClass, "entityClass: null");
        this.entityClass = entityClass;
    }

    @Override
    public E get(Long id)
    {
        Validate.notNull(id, "id: null");
        return this.entityManager.find(this.entityClass, id);
    }

    @Override
    public E persist(E entity)
    {
        Validate.notNull(entity, "entity: null");
        this.entityManager.persist(entity);
        return get(entity.getId());
    }

    @Override
    public E merge(E entity)
    {
        Validate.notNull(entity, "entity: null");
        return this.entityManager.merge(entity);
    }

    @Override
    public void delete(E entity)
    {
        Validate.notNull(entity, "entity: null");
        this.entityManager.remove(entity);
    }

    protected List<E> executeQueryList(CriteriaQuery<E> cq, Integer firstResult, Integer maxResults)
    {
        final TypedQuery<E> query = entityManager.createQuery(cq);

        if (firstResult != null)
        {
            query.setFirstResult(firstResult);
        }

        if (maxResults != null)
        {
            query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    protected E executeQueryOne(CriteriaQuery<E> cq)
    {
        final TypedQuery<E> query = entityManager.createQuery(cq);

        try
        {
            return query.getSingleResult();
        } catch (NoResultException e)
        {
            return null;
        }
    }

}

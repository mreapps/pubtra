package com.mreapps.repository.jpa.dao;

import com.mreapps.repository.dao.TransitRouteDao;
import com.mreapps.repository.entity.TransitRoute;
import com.mreapps.repository.jpa.entity.JpaTransitRoute;
import com.mreapps.repository.jpa.entity.JpaTransitRoute_;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class JpaTransitRouteDao extends AbstractJpaDao<TransitRoute, JpaTransitRoute> implements TransitRouteDao
{
    public JpaTransitRouteDao()
    {
        super(JpaTransitRoute.class);
    }

    @Override
    public TransitRoute findByCode(String code)
    {
        Validate.notNull(code, "code: null");

        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<TransitRoute> cq = cb.createQuery(TransitRoute.class);
        final Root<JpaTransitRoute> root = cq.from(JpaTransitRoute.class);
        cq.select(root);
        cq.where(cb.equal(root.get(JpaTransitRoute_.code), code));

        return executeQueryOne(cq);
    }
}

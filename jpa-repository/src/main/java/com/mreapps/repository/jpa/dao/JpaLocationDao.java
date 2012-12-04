package com.mreapps.repository.jpa.dao;

import com.mreapps.repository.dao.LocationDao;
import com.mreapps.repository.entity.Location;
import com.mreapps.repository.jpa.entity.JpaLocation;
import com.mreapps.repository.jpa.entity.JpaLocation_;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class JpaLocationDao extends AbstractJpaDao<Location, JpaLocation> implements LocationDao
{
    public JpaLocationDao()
    {
        super(JpaLocation.class);
    }

    @Override
    public List<Location> findByPartOfName(String partOfName)
    {
        Validate.notNull(partOfName, "partOfName: null");

        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Location> cq = cb.createQuery(Location.class);
        final Root<JpaLocation> root = cq.from(JpaLocation.class);
        cq.select(root);
        cq.where(
                cb.like(cb.upper(root.get(JpaLocation_.name)), "%" + partOfName.toUpperCase() + "%")
        );
        cq.orderBy(cb.asc(root.get(JpaLocation_.name)));

        return executeQueryList(cq, 0, 10);
    }

    @Override
    public List<Location> findByGpsCoordinates(int latitude, int longitude)
    {
        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Location> cq = cb.createQuery(Location.class);
        final Root<JpaLocation> root = cq.from(JpaLocation.class);
        cq.select(root);
        // TODO nearby matching locations
//        cq.where(cb.equal(root.get(JpaUnit_.code), code));

        return executeQueryList(cq, 0, 10);
    }
}

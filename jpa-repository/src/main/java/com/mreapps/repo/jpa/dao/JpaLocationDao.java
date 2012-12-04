package com.mreapps.repo.jpa.dao;

import com.mreapps.repo.jpa.entity.JpaLocation;
import com.mreapps.repository.dao.LocationDao;
import com.mreapps.repository.entity.Location;
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
        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Location> cq = cb.createQuery(Location.class);
        final Root<JpaLocation> root = cq.from(JpaLocation.class);
        cq.select(root);
        // TODO nearby mathcing locations
//        cq.where(cb.equal(root.get(JpaUnit_.code), code));

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

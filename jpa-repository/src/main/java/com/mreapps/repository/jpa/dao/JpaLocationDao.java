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
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public List<Location> findByGpsCoordinates(double latitude, double longitude, int rangeInMeters)
    {
        final BigDecimal rangeInMillimeters = BigDecimal.valueOf(rangeInMeters).multiply(BigDecimal.valueOf(1000));
        final BigDecimal decimalDegrees = rangeInMillimeters.divide(BigDecimal.valueOf(1.11), 20, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(0.00000001));

        final double minLatitude = BigDecimal.valueOf(latitude).subtract(decimalDegrees).setScale(8, RoundingMode.HALF_UP).doubleValue();
        final double maxLatitude = BigDecimal.valueOf(latitude).add(decimalDegrees).setScale(8, RoundingMode.HALF_UP).doubleValue();
        final double minLongitude = BigDecimal.valueOf(longitude).subtract(decimalDegrees).setScale(8, RoundingMode.HALF_UP).doubleValue();
        final double maxLongitude = BigDecimal.valueOf(longitude).add(decimalDegrees).setScale(8, RoundingMode.HALF_UP).doubleValue();

        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Location> cq = cb.createQuery(Location.class);
        final Root<JpaLocation> root = cq.from(JpaLocation.class);
        cq.select(root);
        cq.where(
                cb.greaterThanOrEqualTo(root.get(JpaLocation_.latitude), minLatitude),
                cb.lessThanOrEqualTo(root.get(JpaLocation_.latitude), maxLatitude),
                cb.greaterThanOrEqualTo(root.get(JpaLocation_.longitude), minLongitude),
                cb.lessThanOrEqualTo(root.get(JpaLocation_.longitude), maxLongitude)
        );

        return executeQueryList(cq, 0, 10);
    }
}

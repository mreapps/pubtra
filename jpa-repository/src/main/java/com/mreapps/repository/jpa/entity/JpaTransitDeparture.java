package com.mreapps.repository.jpa.entity;

import com.mreapps.repository.entity.TransitDeparture;
import com.mreapps.repository.entity.TransitRouteLocation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transot_departure")
public class JpaTransitDeparture extends AbstractJpaBaseEntity implements TransitDeparture
{
    private static final long serialVersionUID = -229129031646684730L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transit_route_location_uid")
    private JpaTransitRouteLocation location;

    @Column(name = "departure_time")
    private Date departureTime;

    @Override
    public TransitRouteLocation getLocation()
    {
        return location;
    }

    @Override
    public void setLocation(TransitRouteLocation location)
    {
        if (location == null || location instanceof JpaTransitRouteLocation)
        {
            this.location = (JpaTransitRouteLocation) location;
        } else
        {
            throw new IllegalArgumentException("location must be instance of " + JpaTransitRouteLocation.class.getSimpleName());
        }
    }

    @Override
    public Date getDepartureTime()
    {
        return departureTime;
    }

    @Override
    public void setDepartureTime(Date departureTime)
    {
        this.departureTime = departureTime;
    }
}

package com.mreapps.repository.jpa.entity;

import com.mreapps.repository.entity.TransitRouteLocation;
import com.mreapps.repository.entity.TransitTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transit_time")
public class JpaTransitTime extends AbstractJpaBaseEntity implements TransitTime
{
    private static final long serialVersionUID = -229129031646684730L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transit_route_location_uid", nullable = false)
    private JpaTransitRouteLocation location;

    @Column(name = "arrival_time", nullable = false)
    private String arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private String departureTime;

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
    public String getArrivalTime()
    {
        return arrivalTime;
    }

    @Override
    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String getDepartureTime()
    {
        return departureTime;
    }

    @Override
    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }
}

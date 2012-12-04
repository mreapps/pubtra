package com.mreapps.repo.jpa.entity;

import com.mreapps.repository.entity.TransportDeparture;
import com.mreapps.repository.entity.TransportRouteLocation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bus_departure_location")
public class JpaTransportDeparture extends AbstractJpaBaseEntity implements TransportDeparture
{
    private static final long serialVersionUID = -229129031646684730L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_routelocation_uid")
    private JpaTransportRouteLocation location;

    @Column(name = "departure_time")
    private Date departureTime;

    @Override
    public TransportRouteLocation getLocation()
    {
        return location;
    }

    @Override
    public void setLocation(TransportRouteLocation location)
    {
        if (location == null || location instanceof JpaTransportRouteLocation)
        {
            this.location = (JpaTransportRouteLocation) location;
        } else
        {
            throw new IllegalArgumentException("location must be instance of " + JpaTransportRouteLocation.class.getSimpleName());
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

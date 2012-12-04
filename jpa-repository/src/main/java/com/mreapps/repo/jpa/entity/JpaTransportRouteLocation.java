package com.mreapps.repo.jpa.entity;

import com.mreapps.repository.entity.Location;
import com.mreapps.repository.entity.TransportRoute;
import com.mreapps.repository.entity.TransportRouteLocation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transport_route_location")
public class JpaTransportRouteLocation extends AbstractJpaBaseEntity implements TransportRouteLocation
{
    private static final long serialVersionUID = 3097072424589822801L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_route_uid")
    private JpaTransportRoute transportRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_uid")
    private JpaLocation location;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Override
    public TransportRoute getTransportRoute()
    {
        return transportRoute;
    }

    @Override
    public void setTransportRoute(TransportRoute transportRoute)
    {
        if (transportRoute == null || transportRoute instanceof JpaTransportRoute)
        {
            this.transportRoute = (JpaTransportRoute) transportRoute;
        } else
        {
            throw new IllegalArgumentException("transportRoute must be instance of " + JpaTransportRoute.class.getSimpleName());
        }
    }

    @Override
    public Location getLocation()
    {
        return location;
    }

    @Override
    public void setLocation(Location location)
    {
        if (location == null || location instanceof JpaLocation)
        {
            this.location = (JpaLocation) location;
        } else
        {
            throw new IllegalArgumentException("location must be instance of " + JpaLocation.class.getSimpleName());
        }
    }

    @Override
    public int getSequence()
    {
        return sequence;
    }

    @Override
    public void setSequence(int sequence)
    {
        this.sequence = sequence;
    }
}

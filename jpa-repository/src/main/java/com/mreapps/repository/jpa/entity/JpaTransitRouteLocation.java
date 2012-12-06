package com.mreapps.repository.jpa.entity;

import com.mreapps.repository.entity.Location;
import com.mreapps.repository.entity.TransitRoute;
import com.mreapps.repository.entity.TransitRouteLocation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transit_route_location")
public class JpaTransitRouteLocation extends AbstractJpaBaseEntity implements TransitRouteLocation
{
    private static final long serialVersionUID = 3097072424589822801L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transit_route_uid")
    private JpaTransitRoute transitRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_uid")
    private JpaLocation location;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Override
    public TransitRoute getTransitRoute()
    {
        return transitRoute;
    }

    @Override
    public void setTransitRoute(TransitRoute transitRoute)
    {
        if (transitRoute == null || transitRoute instanceof JpaTransitRoute)
        {
            this.transitRoute = (JpaTransitRoute) transitRoute;
        } else
        {
            throw new IllegalArgumentException("transitRoute must be instance of " + JpaTransitRoute.class.getSimpleName());
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

package com.mreapps.repo.jpa.entity;

import com.mreapps.repository.entity.TransportRoute;
import com.mreapps.repository.entity.TransportRouteLocation;
import org.apache.commons.lang.Validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bus_route")
public class JpaTransportRoute extends AbstractJpaBaseEntity implements TransportRoute
{
    private static final long serialVersionUID = -8619632034740942690L;

    @Column(name = "code", nullable = false, length = 10, unique = false)
    private String code;

    @Column(name = "name", nullable = false, length = 50, unique = false)
    private String name;

    @Column(name = "description", nullable = false, length = 150, unique = false)
    private String description;

    @Column(name = "valid_from", nullable = false)
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;

    private Set<JpaTransportRouteLocation> locations = new HashSet<JpaTransportRouteLocation>();

    @Override
    public String getCode()
    {
        return code;
    }

    @Override
    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public Date getValidFrom()
    {
        return validFrom;
    }

    @Override
    public void setValidFrom(Date validFrom)
    {
        this.validFrom = validFrom;
    }

    @Override
    public Date getValidTo()
    {
        return validTo;
    }

    @Override
    public void setValidTo(Date validTo)
    {
        this.validTo = validTo;
    }

    @Override
    public List<TransportRouteLocation> getLocations()
    {
        return Collections.unmodifiableList(new ArrayList<TransportRouteLocation>(locations));
    }

    @Override
    public void addLocation(TransportRouteLocation location)
    {
        Validate.notNull(location, "location: null");
        Validate.isTrue(location instanceof JpaTransportRouteLocation, "location must be instance of " + JpaTransportRouteLocation.class.getSimpleName());
        this.locations.add((JpaTransportRouteLocation) location);
    }

    @Override
    public void removeLocation(TransportRouteLocation location)
    {
        Validate.notNull(location, "location: null");
        Validate.isTrue(location instanceof JpaTransportRouteLocation, "location must be instance of " + JpaTransportRouteLocation.class.getSimpleName());
        JpaTransportRouteLocation l = (JpaTransportRouteLocation) location;
        this.locations.remove(l);
    }
}

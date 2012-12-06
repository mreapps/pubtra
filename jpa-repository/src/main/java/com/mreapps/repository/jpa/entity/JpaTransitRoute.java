package com.mreapps.repository.jpa.entity;

import com.mreapps.repository.entity.TransitRoute;
import com.mreapps.repository.entity.TransitRouteLocation;
import org.apache.commons.lang.Validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "transit_route")
public class JpaTransitRoute extends AbstractJpaBaseEntity implements TransitRoute
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

    @OneToMany(mappedBy = "transitRoute")
    private Set<JpaTransitRouteLocation> locations = new HashSet<JpaTransitRouteLocation>();

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
    public List<TransitRouteLocation> getLocations()
    {
        return Collections.unmodifiableList(new ArrayList<TransitRouteLocation>(locations));
    }

    @Override
    public void addLocation(TransitRouteLocation location)
    {
        Validate.notNull(location, "location: null");
        Validate.isTrue(location instanceof JpaTransitRouteLocation, "location must be instance of " + JpaTransitRouteLocation.class.getSimpleName());
        this.locations.add((JpaTransitRouteLocation) location);
    }

    @Override
    public void removeLocation(TransitRouteLocation location)
    {
        Validate.notNull(location, "location: null");
        Validate.isTrue(location instanceof JpaTransitRouteLocation, "location must be instance of " + JpaTransitRouteLocation.class.getSimpleName());
        JpaTransitRouteLocation l = (JpaTransitRouteLocation) location;
        this.locations.remove(l);
    }
}

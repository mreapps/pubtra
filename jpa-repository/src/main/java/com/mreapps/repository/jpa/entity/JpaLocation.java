package com.mreapps.repository.jpa.entity;

import com.mreapps.repository.entity.Location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class JpaLocation extends AbstractJpaBaseEntity implements Location
{
    private static final long serialVersionUID = -3613710485270373806L;

    @Column(name = "name", nullable = false, length = 150, unique = false)
    private String name;

    @Column(name = "latitude", nullable = false, precision = 11, scale = 8)
    private double latitude;

    @Column(name = "longitude", nullable = false, precision = 11, scale = 8)
    private double longitude;

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
    public double getLatitude()
    {
        return latitude;
    }

    @Override
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    @Override
    public double getLongitude()
    {
        return longitude;
    }

    @Override
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
}
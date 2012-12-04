package com.mreapps.repo.jpa.entity;

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

    @Column(name = "latitude", nullable = false)
    private int latitude;

    @Column(name = "longitude", nullable = false)
    private int longitude;

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
    public int getLatitude()
    {
        return latitude;
    }

    @Override
    public void setLatitude(int latitude)
    {
        this.latitude = latitude;
    }

    @Override
    public int getLongitude()
    {
        return longitude;
    }

    @Override
    public void setLongitude(int longitude)
    {
        this.longitude = longitude;
    }
}
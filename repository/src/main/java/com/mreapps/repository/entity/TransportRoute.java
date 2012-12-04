package com.mreapps.repository.entity;

import java.util.Date;
import java.util.List;

public interface TransportRoute extends BaseEntity
{
    String getCode();

    void setCode(String code);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Date getValidFrom();

    void setValidFrom(Date validFrom);

    Date getValidTo();

    void setValidTo(Date validTo);

    List<TransportRouteLocation> getLocations();

    void addLocation(TransportRouteLocation location);

    void removeLocation(TransportRouteLocation location);
}

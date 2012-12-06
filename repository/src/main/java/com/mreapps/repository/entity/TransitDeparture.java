package com.mreapps.repository.entity;

import java.util.Date;

public interface TransitDeparture extends BaseEntity
{
    TransitRouteLocation getLocation();

    void setLocation(TransitRouteLocation location);

    Date getDepartureTime();

    void setDepartureTime(Date departureTime);
}

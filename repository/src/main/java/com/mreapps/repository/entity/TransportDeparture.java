package com.mreapps.repository.entity;

import java.util.Date;

public interface TransportDeparture extends BaseEntity
{
    TransportRouteLocation getLocation();

    void setLocation(TransportRouteLocation location);

    Date getDepartureTime();

    void setDepartureTime(Date departureTime);
}

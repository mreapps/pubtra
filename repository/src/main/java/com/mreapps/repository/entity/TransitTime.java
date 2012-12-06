package com.mreapps.repository.entity;

public interface TransitTime extends BaseEntity
{
    TransitRouteLocation getLocation();

    void setLocation(TransitRouteLocation location);

    String getArrivalTime();

    void setArrivalTime(String arrivalTime);

    String getDepartureTime();

    void setDepartureTime(String departureTime);
}

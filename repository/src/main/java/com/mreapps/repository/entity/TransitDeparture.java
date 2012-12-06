package com.mreapps.repository.entity;

public interface TransitDeparture extends BaseEntity
{
    TransitRouteLocation getLocation();

    void setLocation(TransitRouteLocation location);

    String getArrivalTime();

    void setArrivalTime(String arrivalTime);

    String getDepartureTime();

    void setDepartureTime(String departureTime);
}

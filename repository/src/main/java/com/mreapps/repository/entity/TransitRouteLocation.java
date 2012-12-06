package com.mreapps.repository.entity;

public interface TransitRouteLocation
{
    TransitRoute getTransitRoute();

    void setTransitRoute(TransitRoute transitRoute);

    Location getLocation();

    void setLocation(Location location);

    int getSequence();

    void setSequence(int sequence);
}

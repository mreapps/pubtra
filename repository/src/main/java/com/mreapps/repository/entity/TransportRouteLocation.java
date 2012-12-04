package com.mreapps.repository.entity;

public interface TransportRouteLocation
{
    TransportRoute getTransportRoute();

    void setTransportRoute(TransportRoute transportRoute);

    Location getLocation();

    void setLocation(Location location);

    int getSequence();

    void setSequence(int sequence);
}

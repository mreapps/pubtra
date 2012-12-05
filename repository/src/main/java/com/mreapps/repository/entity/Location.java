package com.mreapps.repository.entity;

public interface Location extends BaseEntity
{
    String getName();

    void setName(String name);

    double getLatitude();

    void setLatitude(double latitude);

    double getLongitude();

    void setLongitude(double longitude);
}

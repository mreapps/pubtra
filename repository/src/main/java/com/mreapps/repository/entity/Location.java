package com.mreapps.repository.entity;

public interface Location extends BaseEntity
{
    String getName();

    void setName(String name);

    int getLatitude();

    void setLatitude(int latitude);

    int getLongitude();

    void setLongitude(int longitude);
}

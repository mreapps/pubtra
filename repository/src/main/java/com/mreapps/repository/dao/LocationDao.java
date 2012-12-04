package com.mreapps.repository.dao;

import com.mreapps.repository.entity.Location;

import java.util.List;

public interface LocationDao extends GenericDao<Location>
{
    List<Location> findByPartOfName(String partOfName);

    List<Location> findByGpsCoordinates(int latitude, int longitude);
}

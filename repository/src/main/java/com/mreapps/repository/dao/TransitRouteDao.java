package com.mreapps.repository.dao;

import com.mreapps.repository.entity.TransitRoute;

public interface TransitRouteDao extends GenericDao<TransitRoute>
{
    TransitRoute findByCode(String code);
}

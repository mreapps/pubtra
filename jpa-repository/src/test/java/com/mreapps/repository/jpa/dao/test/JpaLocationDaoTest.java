package com.mreapps.repository.jpa.dao.test;

import com.mreapps.repository.dao.LocationDao;
import com.mreapps.repository.entity.Location;
import com.mreapps.repository.jpa.entity.JpaLocation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class JpaLocationDaoTest extends AbstractJpaRepositoryTest
{
    @Autowired
    private LocationDao locationDao;

    @Test
    public void findByPartOfName()
    {
        createLocations();

        assertEquals(1, locationDao.findByPartOfName("lille").size());
        assertEquals(1, locationDao.findByPartOfName("rud").size());
        assertEquals(2, locationDao.findByPartOfName("lia").size());
        assertEquals(1, locationDao.findByPartOfName("stad").size());
        assertEquals(2, locationDao.findByPartOfName("gjøvik").size());
        assertEquals(0, locationDao.findByPartOfName("exs").size());
        assertEquals(10, locationDao.findByPartOfName("").size());
    }

    @Test
    public void findByGpsCoordinates()
    {
        createLocations();

        assertEquals(1, locationDao.findByGpsCoordinates(60.7684, 10.7062, 500).size());
        assertEquals(2, locationDao.findByGpsCoordinates(60.7684, 10.7062, 600).size());
        assertEquals(3, locationDao.findByGpsCoordinates(60.7684, 10.7062, 1000).size());
        assertEquals(0, locationDao.findByGpsCoordinates(60.7684, 10.7062, 10).size());
    }

    private void createLocations()
    {
        createLocation("Hjelpemiddelsentralen", 10.704911, 60.771221);
        createLocation("Bondelia", 10.707614, 60.772934);
        createLocation("Vindingstad", 10.703248, 60.775517);
        createLocation("Rambekkmoen", 10.696810, 60.779163);
        createLocation("Viken", 10.697014, 60.780937);
        createLocation("Østre/Vestre Totenveg", 10.696585, 60.790661);
        createLocation("Gjøvik gård", 10.694450, 60.793530);
        createLocation("Gjøvik skysstasjon", 10.694697, 60.797180);
        createLocation("Kaspergården", 10.694203, 60.786015);
        createLocation("Grobøl", 10.683389, 60.783756);
        createLocation("Kallerud", 10.679312, 60.785004);
        createLocation("Brusvehagen", 10.662848, 60.789543);
        createLocation("Lillekollen", 10.651803, 60.789558);
        createLocation("Hunndalen", 10.642952, 60.786621);
        createLocation("Malerstuen", 10.636750, 60.789753);
        createLocation("Granlia", 10.629122, 60.792225);
        createLocation("Øverbymarka", 10.632663, 60.796055);
    }

    private void createLocation(String name, double longitude, double latitude)
    {
        Location location = new JpaLocation();
        location.setName(name);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.persist(location);
    }
}

package com.mreapps.repository.jpa.dao.test;

import com.mreapps.repository.dao.LocationDao;
import com.mreapps.repository.jpa.entity.JpaLocation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("classpath:jpa-repository-test-context.xml")
@TransactionConfiguration(defaultRollback = true)
public class JpaLocationDaoTest extends AbstractTransactionalJUnit4SpringContextTests
{
    @Autowired
    private LocationDao locationDao;

    @Test
    public void findByPartOfName()
    {
        createLocations();

        assertEquals(2, locationDao.findByPartOfName("lia").size());
        assertEquals(1, locationDao.findByPartOfName("lille").size());
        assertEquals(1, locationDao.findByPartOfName("øvik").size());
        assertEquals(2, locationDao.findByPartOfName("ham").size());
        assertEquals(0, locationDao.findByPartOfName("bre").size());
        assertEquals(10, locationDao.findByPartOfName("").size());
    }

    private void createLocations()
    {
        createLocation("Biri");
        createLocation("Lillehammer");
        createLocation("Gjøvik");
        createLocation("Hamar");
        createLocation("Oslo");
        createLocation("Bergen");
        createLocation("Bondelia");
        createLocation("Nerenglia");
        createLocation("Smedmoen");
        createLocation("Raufoss");
        createLocation("Lena");
        createLocation("Kapp");
        createLocation("Redalen");
    }

    private void createLocation(String name)
    {
        JpaLocation location = new JpaLocation();
        location.setName(name);
        location.setLatitude(0);
        location.setLongitude(0);
        locationDao.persist(location);
    }
}

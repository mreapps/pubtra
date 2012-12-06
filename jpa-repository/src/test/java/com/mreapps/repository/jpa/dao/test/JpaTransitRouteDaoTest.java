package com.mreapps.repository.jpa.dao.test;

import com.mreapps.repository.dao.LocationDao;
import com.mreapps.repository.dao.TransitRouteDao;
import com.mreapps.repository.entity.Location;
import com.mreapps.repository.entity.TransitRoute;
import com.mreapps.repository.entity.TransitRouteLocation;
import com.mreapps.repository.jpa.entity.JpaLocation;
import com.mreapps.repository.jpa.entity.JpaTransitRoute;
import com.mreapps.repository.jpa.entity.JpaTransitRouteLocation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JpaTransitRouteDaoTest extends AbstractJpaRepositoryTest
{
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private TransitRouteDao transitRouteDao;

    @Autowired
    private LocationDao locationDao;

    @Test
    public void findByCode()
    {
        createTransitRoutes();

        TransitRoute transitRoute = transitRouteDao.findByCode("36");
        assertNotNull("Transit route with code 36 not found", transitRoute);
        assertEquals("Øverbymarka - Bondelia", transitRoute.getName());
        assertEquals(35, transitRoute.getLocations().size());

        transitRoute = transitRouteDao.findByCode("XX");
        assertNull("Transit route with code XX should not have been found", transitRoute);
    }

    private void createTransitRoutes()
    {
        Location hjelpemiddelsentralen = createLocation("Hjelpemiddelsentralen", 10.704911, 60.771221);
        Location bondelia = createLocation("Bondelia", 10.707614, 60.772934);
        Location vindingstad = createLocation("Vindingstad", 10.703248, 60.775517);
        Location rambekkmoen = createLocation("Rambekkmoen", 10.696810, 60.779163);
        Location viken = createLocation("Viken", 10.697014, 60.780937);
        Location vikskogen = createLocation("Vikskogen", 10.699224, 60.784492);
        Location vindingstadbakken = createLocation("Vindingstadbakken", 10.702250, 60.774803);
        Location ostreVestreTotenveg = createLocation("Østre/Vestre Totenveg", 10.696585, 60.790661);
        Location gjoevikGaard = createLocation("Gjøvik gård", 10.694450, 60.793530);
        Location gjoevikSkysstasjon = createLocation("Gjøvik skysstasjon", 10.694697, 60.797180);
        Location kaspergaarden = createLocation("Kaspergården", 10.694203, 60.786015);
        Location grobol = createLocation("Grobøl", 10.683389, 60.783756);
        Location kallerud = createLocation("Kallerud", 10.679312, 60.785004);
        Location brusvehagen = createLocation("Brusvehagen", 10.662848, 60.789543);
        Location lillekollen = createLocation("Lillekollen", 10.651803, 60.789558);
        Location hunndalen = createLocation("Hunndalen", 10.642952, 60.786621);
        Location malerstuen = createLocation("Malerstuen", 10.636750, 60.789753);
        Location granlia = createLocation("Granlia", 10.629122, 60.792225);
        Location overbymarka = createLocation("Øverbymarka", 10.632663, 60.796055);

        Collection<TransitRouteLocation> locations36 = Arrays.asList(
                createTransitRouteLocation(gjoevikSkysstasjon, 1),
                createTransitRouteLocation(gjoevikGaard, 2),
                createTransitRouteLocation(vikskogen, 3),
                createTransitRouteLocation(viken, 4),
                createTransitRouteLocation(rambekkmoen, 5),
                createTransitRouteLocation(vindingstadbakken, 6),
                createTransitRouteLocation(hjelpemiddelsentralen, 7),
                createTransitRouteLocation(bondelia, 8),
                createTransitRouteLocation(vindingstad, 9),
                createTransitRouteLocation(rambekkmoen, 10),
                createTransitRouteLocation(viken, 11),
                createTransitRouteLocation(ostreVestreTotenveg, 12),
                createTransitRouteLocation(gjoevikGaard, 13),
                createTransitRouteLocation(gjoevikSkysstasjon, 14),
                createTransitRouteLocation(gjoevikGaard, 15),
                createTransitRouteLocation(ostreVestreTotenveg, 16),
                createTransitRouteLocation(kaspergaarden, 17),
                createTransitRouteLocation(grobol, 18),
                createTransitRouteLocation(kallerud, 19),
                createTransitRouteLocation(brusvehagen, 20),
                createTransitRouteLocation(lillekollen, 21),
                createTransitRouteLocation(hunndalen, 22),
                createTransitRouteLocation(malerstuen, 23),
                createTransitRouteLocation(granlia, 24),
                createTransitRouteLocation(overbymarka, 25),
                createTransitRouteLocation(granlia, 26),
                createTransitRouteLocation(malerstuen, 27),
                createTransitRouteLocation(hunndalen, 28),
                createTransitRouteLocation(lillekollen, 29),
                createTransitRouteLocation(brusvehagen, 30),
                createTransitRouteLocation(kallerud, 31),
                createTransitRouteLocation(grobol, 32),
                createTransitRouteLocation(kaspergaarden, 33),
                createTransitRouteLocation(ostreVestreTotenveg, 34),
                createTransitRouteLocation(gjoevikGaard, 35)
        );

        createTransitRoute("36", "Øverbymarka - Bondelia", "", "2012-12-09", "2013-08-18", locations36);
    }

    private void createTransitRoute(String code, String name, String description, String validFromAsString, String validToAsString, Collection<TransitRouteLocation> locations)
    {
        TransitRoute transitRoute = new JpaTransitRoute();
        transitRoute.setCode(code);
        transitRoute.setName(name);
        transitRoute.setDescription(description);
        try
        {
            transitRoute.setValidFrom(DATE_FORMAT.parse(validFromAsString));
        } catch (ParseException e)
        {
            throw new IllegalArgumentException("Invalid date format for string: " + validFromAsString + ". Valid format is yyyy-MM-dd");
        }
        try
        {
            transitRoute.setValidTo(DATE_FORMAT.parse(validToAsString));
        } catch (ParseException e)
        {
            throw new IllegalArgumentException("Invalid date format for string: " + validToAsString + ". Valid format is yyyy-MM-dd");
        }

        if (locations != null)
        {
            for (TransitRouteLocation location : locations)
            {
                location.setTransitRoute(transitRoute);
                transitRoute.addLocation(location);
            }
        }

        transitRouteDao.persist(transitRoute);
    }

    private TransitRouteLocation createTransitRouteLocation(Location location, int sequence)
    {
        TransitRouteLocation transitRouteLocation = new JpaTransitRouteLocation();
        transitRouteLocation.setLocation(location);
        transitRouteLocation.setSequence(sequence);

        return transitRouteLocation;
    }

    private Location createLocation(String name, double longitude, double latitude)
    {
        Location location = new JpaLocation();
        location.setName(name);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return locationDao.persist(location);
    }
}

package example.testtask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ItemsTest {

    private Items items;

    @Before
    public void setUp() {
        items = mock(Items.class);
        Queue<City> cities = null;
        Queue<Road> roads = null;

        cities = new ConcurrentLinkedQueue<City>();
        when(items.searchCities("City")).thenReturn(cities);

        cities = new ConcurrentLinkedQueue<City>();
        cities.add(new City(1, "City-A", new Point(100, 100)));
        when(items.searchCities("City-A")).thenReturn(cities);

        cities = new ConcurrentLinkedQueue<City>();
        cities.add(new City(3, "City-C", new Point(300, 300)));
        when(items.searchCities("City-C")).thenReturn(cities);

        cities = new ConcurrentLinkedQueue<City>();
        when(items.searchCities("City-W")).thenReturn(cities);

        cities = new ConcurrentLinkedQueue<City>();
        cities.add(new City(5, "City-E", new Point(500, 500)));
        cities.add(new City(6, "City-E", new Point(501, 501)));
        cities.add(new City(7, "City-E", new Point(502, 502)));
        cities.add(new City(8, "City-E", new Point(503, 503)));
        when(items.searchCities("City-E")).thenReturn(cities);

        roads = new ConcurrentLinkedQueue<Road>();
        roads.add(new Road(1, "Road-1",  new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500))));
        roads.add(new Road(2, "Road-2",  new City(5, "City-E", new Point(500, 500)), new City(6, "City-E", new Point(501, 501))));
        roads.add(new Road(3, "Road-3",  new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
        when(items.searchRoads(new City(5, "City-E", new Point(500, 500)))).thenReturn(roads);

        roads = new ConcurrentLinkedQueue<Road>();
        roads.add(new Road(0, "Road-0",  new City(1, "City-A", new Point(100, 100)), new City(3, "City-C", new Point(300, 300))));
        roads.add(new Road(1, "Road-1",  new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500))));
        when(items.searchRoads(new City(1, "City-A", new Point(100, 100)))).thenReturn(roads);

        roads = new ConcurrentLinkedQueue<Road>();
        roads.add(new Road(3, "Road-3",  new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
        when(items.searchRoads(new City(8, "City-E", new Point(503, 503)))).thenReturn(roads);

        roads = new ConcurrentLinkedQueue<Road>();
        when(items.searchRoads(new City(9, "City-F", new Point(600, 600)))).thenReturn(roads);

        doThrow(new NullPointerException("City-Name is Null")).when(items).searchCities(null);
        doThrow(new IllegalArgumentException("City-Name is Empty")).when(items).searchCities("");
//        doThrow(new IllegalArgumentException("City is Null")).when(items).searchCities(null);
        doThrow(new NullPointerException(null)).when(items).deleteCities("xxx");
        doThrow(new NullPointerException("City-Name is Null")).when(items).deleteCities(null);
        doThrow(new IllegalArgumentException("City-Name is Empty")).when(items).deleteCities("");
        doThrow(new NullPointerException("Road-Name is Null")).when(items).deleteRoad(null);
        doThrow(new IllegalArgumentException("Road-Name is Empty")).when(items).deleteRoad("");

        when(items.deleteRoad("xxx")).thenReturn(false);
        when(items.deleteRoad("Road-1")).thenReturn(true).thenReturn(false);
        when(items.deleteCities("City-E")).thenReturn(true);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSearch() {
        String expected1 = "[]";
        String expected2 = "[City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}]";
        String expected3 = "[City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}]";
        String expected4 = "[Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected5 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}]";

        assertEquals("searchCities(\"City\")", expected1, items.searchCities("City").toString());
        assertEquals("searchCities(\"City-A\")", expected2, items.searchCities("City-A").toString());
        assertEquals("searchCities(\"City-E\")", expected3, items.searchCities("City-E").toString());
        assertEquals("items.searchRoads(new City(5, \"City-E\", new Point(500, 500)))", expected4, items.searchRoads(new City(5, "City-E", new Point(500, 500))).toString());
        assertEquals("items.searchRoads(new City(1, \"City-A\", new Point(100, 100)))", expected5, items.searchRoads(new City(1, "City-A", new Point(100, 100))).toString());
    }

    @Test
    public void testSearchException1() {
        String expected = "City-Name is Null";

        try {
            items.searchCities(null);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("items.searchCities(null)", expected, npe.getMessage());
        }
    }

    @Test
    public void testSearchException2() {
        String expected = "City-Name is Empty";

        try {
            items.searchCities("");
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("items.searchCities(\"\")", expected, npe.getMessage());
        }
    }

//    @Test
//    public void testSearchException3() {
//        String expected = "City is Null";
//
//        try {
//            items.searchRoads(null);
//            fail();
//        } catch(IllegalArgumentException iae) {
//            assertEquals("items.searchCities(\"\")", expected, iae.getMessage());
//        }
//    }

    @Test
    public void testSearchCity(){
        String expected2 = "[]";
        String expected3 = "[City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}]";
        String expected4 = "[City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}]";

        assertEquals("items.searchCities(\"City\")", expected2, items.searchCities("City").toString());
        assertEquals("items.searchCities(\"City-C\")", expected3, items.searchCities("City-C").toString());
        assertEquals("items.searchCities(\"City-E\")", expected4, items.searchCities("City-E").toString());
        assertEquals("items.searchCities(\"City-W\")", expected2, items.searchCities("City-W").toString());
    }

    @Test
    public void testSearchRoad(){
        String expected2 = "[Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected3 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}]";
        String expected4 = "[Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected5 = "[]";

        assertEquals("items.searchRoads(new City(5, \"City-E\", new Point(500, 500)))", expected2, items.searchRoads(new City(5, "City-E", new Point(500, 500))).toString());
        assertEquals("items.searchRoads(new City(1, \"City-A\", new Point(100, 100)))", expected3, items.searchRoads(new City(1, "City-A", new Point(100, 100))).toString());
        assertEquals("items.searchRoads(new City(8, \"City-E\", new Point(503, 503)))", expected4, items.searchRoads(new City(8, "City-E", new Point(503, 503))).toString());
        assertEquals("items.searchRoads(new City(9, \"City-F\", new Point(600, 600)))", expected5, items.searchRoads(new City(9, "City-F", new Point(600, 600))).toString());
    }

    @Test
    public void testDelCityException1() {
        Object expected = null;

        try {
            items.deleteCities("xxx");
            fail();
        } catch(NullPointerException npe) {
            assertEquals("items.deleteCities(\"xxx\")", expected, npe.getMessage());
        }
    }

//    @Test
//    public void testDelCityException2() {
//        Object expected = null;
//
//        items.deleteCities("City-E");
//        try {
//            items.deleteCities("City-E");
//            fail();
//        } catch(NullPointerException npe) {
//            assertEquals("items.deleteCities(\"City-E\")", expected, npe.getMessage());
//        }
//    }

    @Test
    public void testDelCityException3() {
        String expected = "City-Name is Null";

        try {
            items.deleteCities(null);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("items.deleteCities(null)", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelCityException4() {
        String expected = "City-Name is Empty";

        try {
            items.deleteCities("");
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("items.deleteCities(\"\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelRoadException1() {
        String expected = "Road-Name is Null";

        try {
            items.deleteRoad(null);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("items.deleteRoad(null)", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelRoadException2() {
        String expected = "Road-Name is Empty";

        try {
            items.deleteRoad("");
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("items.deleteRoad(\"\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelRoadTrue(){
        assertTrue("Road-1", items.deleteRoad("Road-1"));
    }

    @Test
    public void testDelRoadFalse(){
        items.deleteRoad("Road-1");
        assertFalse("Road-1", items.deleteRoad("Road-1"));
        assertFalse("xxx", items.deleteRoad("xxx"));
    }

    @Test
    public void testDelCityTrue(){
        assertTrue("items.deleteCities(\"City-E\")", items.deleteCities("City-E"));
    }
}

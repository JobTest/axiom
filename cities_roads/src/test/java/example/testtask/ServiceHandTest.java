package example.testtask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ServiceHandTest {

    private Service actualAdd;
    private ServiceHand actualIs;
    private Service actual;

    @Before
    public void setUp() {
        actualAdd = new ServiceHand();

        actualIs = new ServiceHand();
        actualIs.addCity("City-A", 10, 20);
        actualIs.addCity("City-B", 20, 30);
        actualIs.addCity("City-C", 30, 30);
        actualIs.addCity("City-D", 40, 20);
        actualIs.addCity("City-E", 30, 10);
        actualIs.addCity("City-F", 20, 10);
        actualIs.addCity("City-G", 10, 10);
        actualIs.addRoad("Road-1", new City(1, "City-A", new Point(10, 20)), new City(2, "City-B", new Point(20, 30)));
        actualIs.addRoad("Road-2", new City(2, "City-B", new Point(20, 30)), new City(3, "City-C", new Point(30, 30)));
        actualIs.addRoad("Road-3", new City(2, "City-B", new Point(20, 30)), new City(4, "City-D", new Point(40, 20)));
        actualIs.addRoad("Road-4", new City(2, "City-B", new Point(20, 30)), new City(5, "City-E", new Point(30, 10)));
        actualIs.addRoad("Road-5", new City(2, "City-B", new Point(20, 30)), new City(6, "City-F", new Point(20, 10)));
        actualIs.addRoad("Road-6", new City(3, "City-C", new Point(30, 30)), new City(4, "City-D", new Point(40, 20)));
        actualIs.addRoad("Road-7", new City(3, "City-C", new Point(30, 30)), new City(5, "City-E", new Point(30, 10)));
        actualIs.addRoad("Road-8", new City(4, "City-D", new Point(40, 20)), new City(5, "City-E", new Point(30, 10)));
        actualIs.addRoad("Road-9", new City(4, "City-D", new Point(40, 20)), new City(6, "City-F", new Point(20, 10)));

        actual = new ServiceHand();
        actual.addCity("City-A", 100, 100);
        actual.addCity("City-B", 200, 200);
        actual.addCity("City-C", 300, 300);
        actual.addCity("City-D", 400, 400);
        actual.addCity("City-E", 500, 500);
        actual.addCity("City-E", 501, 501);
        actual.addCity("City-E", 502, 502);
        actual.addCity("City-E", 503, 503);
        actual.addCity("City-F", 600, 600);
        actual.addCity("City-J", 700, 700);
        actual.addCity("City-I", 800, 800);
        actual.addRoad("Road-0", new City(1, "City-A", new Point(100, 100)), new City(3, "City-C", new Point(300, 300)));
        actual.addRoad("Road-1", new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500)));
        actual.addRoad("Road-2", new City(5, "City-E", new Point(500, 500)), new City(6, "City-E", new Point(501, 501)));
        actual.addRoad("Road-3", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503)));
    }

    @After
    public void tearDown() {
        actualAdd = null;
        actualIs = null;
        actual = null;
    }

    @Test
    public void testAddCityTrue() {
        assertTrue("addCity(\"City-A\", 100, 100)", actualAdd.addCity("City-A", 100, 100));
        assertTrue("addCity(\"City-B\", 200, 200)", actualAdd.addCity("City-B", 200, 200));
        assertTrue("addCity(\"City-C\", 300, 300)", actualAdd.addCity("City-C", 300, 300));
        assertTrue("addCity(\"City-D\", 400, 400)", actualAdd.addCity("City-D", 400, 400));
        assertTrue("addCity(\"City-E\", 500, 500)", actualAdd.addCity("City-E", 500, 500));
        assertTrue("addCity(\"City-E\", 501, 501)", actualAdd.addCity("City-E", 501, 501));
        assertTrue("addCity(\"City-E\", 502, 502)", actualAdd.addCity("City-E", 502, 502));
        assertTrue("addCity(\"City-E\", 503, 503)", actualAdd.addCity("City-E", 503, 503));
    }

    @Test
    public void testAddCityFalse() {
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-E", 501, 501);
        assertFalse("addCity(\"City-A\", 100, 100)", actualAdd.addCity("City-A", 100, 100));
        assertFalse("addCity(\"City-E\", 501, 501)", actualAdd.addCity("City-E", 501, 501));
        assertFalse("addCity(\"City-F\", 100, 100)", actualAdd.addCity("City-F", 100, 100));
    }

    @Test
    public void testAddCityException1() {
        String expected = "City-Name is Null";
        try {
            actualAdd.addCity(null, 600, 600);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("addCity(null, 600, 600)", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddCityException2() {
        String expected = "City-Name is Empty";
        try {
            actualAdd.addCity("", 600, 600);
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("addCity(\"\", 600, 600)", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddCity() {
        int expected = 8;
        String expected0 = "City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}";
        String expected1 = "City{name=City-B id=2 coordinate=java.awt.Point[x=200,y=200]}";
        String expected2 = "City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}";
        String expected3 = "City{name=City-D id=4 coordinate=java.awt.Point[x=400,y=400]}";
        String expected4 = "City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}";
        String expected5 = "City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}";
        String expected6 = "City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}";
        String expected7 = "City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        assertEquals("(Cities) size:", expected, actualAdd.getCities().size());
        assertEquals("getCity(1)", expected0, new City(1, "City-A", new Point(100, 100)).toString());
        assertEquals("getCity(2)", expected1, new City(2, "City-B", new Point(200, 200)).toString());
        assertEquals("getCity(3)", expected2, new City(3, "City-C", new Point(300, 300)).toString());
        assertEquals("getCity(4)", expected3, new City(4, "City-D", new Point(400, 400)).toString());
        assertEquals("getCity(5)", expected4, new City(5, "City-E", new Point(500, 500)).toString());
        assertEquals("getCity(6)", expected5, new City(6, "City-E", new Point(501, 501)).toString());
        assertEquals("getCity(7)", expected6, new City(7, "City-E", new Point(502, 502)).toString());
        assertEquals("getCity(8)", expected7, new City(8, "City-E", new Point(503, 503)).toString());
    }

    @Test
    public void testAddRoadTrue() {
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        assertTrue("Road{name=Road-0 id=0 idCity1=1 idCity2=3}", actualAdd.addRoad("Road-0", new City(1, "City-A", new Point(100, 100)), new City(3, "City-C", new Point(300, 300))));
        assertTrue("Road{name=Road-1 id=1 idCity1=1 idCity2=5}", actualAdd.addRoad("Road-1", new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500))));
        assertTrue("Road{name=Road-2 id=2 idCity1=5 idCity2=6}", actualAdd.addRoad("Road-2", new City(5, "City-E", new Point(500, 500)), new City(6, "City-E", new Point(501, 501))));
        assertTrue("Road{name=Road-3 id=3 idCity1=5 idCity2=8}", actualAdd.addRoad("Road-3", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
    }

    @Test
     public void testAddRoadFalse() {
         actualAdd.addCity("City-A", 100, 100);
         actualAdd.addCity("City-B", 200, 200);
         actualAdd.addCity("City-C", 300, 300);
         actualAdd.addCity("City-D", 400, 400);
         actualAdd.addCity("City-E", 500, 500);
         actualAdd.addCity("City-E", 501, 501);
         actualAdd.addCity("City-E", 502, 502);
         actualAdd.addCity("City-E", 503, 503);
         actualAdd.addRoad("Road-0", new City(1, "City-A", new Point(100, 100)), new City(3, "City-C", new Point(300, 300)));
         actualAdd.addRoad("Road-1", new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500)));
         actualAdd.addRoad("Road-2", new City(5, "City-E", new Point(500, 500)), new City(6, "City-E", new Point(501, 501)));
         actualAdd.addRoad("Road-3", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503)));
         assertFalse("Road{name=Road-4 id= idCity1=2 idCity2=2}", actualAdd.addRoad("Road-4", new City(2, "City-B", new Point(200, 200)), new City(2, "City-B", new Point(200, 200))));
         assertFalse("Road{name=Road-3 id= idCity1=6 idCity2=8}", actualAdd.addRoad("Road-3", new City(6, "City-E", new Point(501, 501)), new City(8, "City-E", new Point(503, 503))));
         assertFalse("Road{name=Road-8 id= idCity1=5 idCity2=8}", actualAdd.addRoad("Road-8", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
         assertFalse("Road{name=Road-9 id= idCity1=1 idCity2=5}", actualAdd.addRoad("Road-9", new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500))));
         assertFalse("Road{name=Road-10 id= idCity1=5 idCity2=8}", actualAdd.addRoad("Road-10", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
         assertFalse("Road{name=Road-11 id= idCity1=5 idCity2=8}", actualAdd.addRoad("Road-11", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
         assertFalse("Road{name=Road-12 id= idCity1=5 idCity2=8}", actualAdd.addRoad("Road-12", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503))));
     }


    @Test
    public void testAddRoadException1() {
        String expected = "city1 OR city2 is Null";
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad("Road-5", null, new City(7, "City-E", new Point(503, 503)));
            fail();
        } catch(NullPointerException npe) {
            assertEquals("Road{name=Road-5 id= idCity1=null idCity2=7}", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoadException2() {
        String expected = "city1 OR city2 is Null";
        try {
            actualAdd.addRoad("Road-6", null, null);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("Road{name=Road-6 id= idCity1=null idCity2=null}", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoadException3() {
        String expected = "Road-Name is Null";
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad(null, new City(5, "City-E", new Point(501, 501)), new City(7, "City-E", new Point(503, 503)));
            fail();
        } catch(NullPointerException npe) {
            assertEquals("Road{name=null id= idCity1=5 idCity2=7}", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoadException4() {
        String expected = "Road-Name is Empty";
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad("", new City(5, "City-E", new Point(501, 501)), new City(7, "City-E", new Point(503, 503)));
            fail();
        } catch(IllegalArgumentException iae) {
            assertEquals("Road{name=Empty id= idCity1=5 idCity2=7}", expected, iae.getMessage());
        }
    }

    @Test
    public void testAddRoadException5() {
        String expected = "city1 OR city2 is Null";
        try {
            actualAdd.addRoad("Road-7", null, null);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("actual1.addRoad(\"Road-7\", null, null)", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoad() {
        String expected = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        actualAdd.addRoad("Road-0", new City(1, "City-A", new Point(100, 100)), new City(3, "City-C", new Point(300, 300)));
        actualAdd.addRoad("Road-1", new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500)));
        actualAdd.addRoad("Road-2", new City(5, "City-E", new Point(500, 500)), new City(6, "City-E", new Point(501, 501)));
        actualAdd.addRoad("Road-3", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503)));
        assertEquals("getRoads()", expected, actualAdd.getRoads().toString());
    }

    @Test
    public void testIsRoad() {
        assertFalse("isRoad(new City(4, \"City-D\", new Point(40, 20)), new City(6, \"City-F\", new Point(20, 10)))", actualIs.isRoad(new City(4, "City-D", new Point(40, 20)), new City(6, "City-F", new Point(20, 10))));
        assertFalse("isRoad(new City(4, \"City-D\", new Point(40, 20)), new City(5, \"City-E\", new Point(30, 10)))", actualIs.isRoad(new City(4, "City-D", new Point(40, 20)), new City(5, "City-E", new Point(30, 10))));
        assertFalse("isRoad(new City(4, \"City-D\", new Point(40, 20)), new City(4, \"City-D\", new Point(40, 20)))", actualIs.isRoad(new City(4, "City-D", new Point(40, 20)), new City(4, "City-D", new Point(40, 20))));
        assertTrue("isRoad(new City(4, \"City-D\", new Point(40, 20)), new City(7, \"City-G\", new Point(10, 10)))", actualIs.isRoad(new City(4, "City-D", new Point(40, 20)), new City(7, "City-G", new Point(10, 10))));
        assertTrue("isRoad(new City(1, \"City-A\", new Point(10, 20)), new City(7, \"City-G\", new Point(10, 10)))", actualIs.isRoad(new City(1, "City-A", new Point(10, 20)), new City(7, "City-G", new Point(10, 10))));
        assertFalse("isRoad(new City(3, \"City-C\", new Point(30, 30)), new City(6, \"City-F\", new Point(20, 10)))", actualIs.isRoad(new City(3, "City-C", new Point(30, 30)), new City(6, "City-F", new Point(20, 10))));
    }

    @Test
    public void testChangeGetCities() {
        String expected = "[City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=2 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=4 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}]";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        actualAdd.getCities().add(new City(10, "City-AA", new Point(1000, 1000)));
        actualAdd.getCities().add(new City(20, "City-BB", new Point(2000, 2000)));
        assertEquals("getCities()", expected, actualAdd.getCities().toString());
    }

    @Test
    public void testChangeGetRoads() {
        String expected = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        actualAdd.addRoad("Road-0", new City(1, "City-A", new Point(100, 100)), new City(3, "City-C", new Point(300, 300)));
        actualAdd.addRoad("Road-1", new City(1, "City-A", new Point(100, 100)), new City(5, "City-E", new Point(500, 500)));
        actualAdd.addRoad("Road-2", new City(5, "City-E", new Point(500, 500)), new City(6, "City-E", new Point(501, 501)));
        actualAdd.addRoad("Road-3", new City(5, "City-E", new Point(500, 500)), new City(8, "City-E", new Point(503, 503)));
        actualAdd.getRoads().add(new Road("Road", new City(10, "City-AA", new Point(1000, 1000)), new City(20, "City-BB", new Point(2000, 2000))));
        assertEquals("getRoads()", expected, actualAdd.getRoads().toString());
    }

    @Test
    public void testSearch() {
        String expected1 = "[]";
        String expected2 = "[City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}]";
        String expected3 = "[City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}]";
        String expected4 = "[Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected5 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}]";
        Items items = actual.items();

        assertEquals("searchCities(\"City\")", expected1, items.searchCities("City").toString());
        assertEquals("searchCities(\"City-A\")", expected2, items.searchCities("City-A").toString());
        assertEquals("searchCities(\"City-E\")", expected3, items.searchCities("City-E").toString());
        assertEquals("items.searchRoads(new City(5, \"City-E\", new Point(500, 500)))", expected4, items.searchRoads(new City(5, "City-E", new Point(500, 500))).toString());
        assertEquals("items.searchRoads(new City(1, \"City-A\", new Point(100, 100)))", expected5, items.searchRoads(new City(1, "City-A", new Point(100, 100))).toString());
    }

    @Test
    public void testSearchException1() {
        String expected = "City-Name is Null";
        Items items = actual.items();
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
        Items items = actual.items();
        try {
            items.searchCities("");
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("items.searchCities(\"\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testSearchException3() {
        String expected = "City is Null";
        Items items = actual.items();
        try {
            items.searchRoads(null);
            fail();
        } catch(IllegalArgumentException iae) {
            assertEquals("items.searchCities(\"\")", expected, iae.getMessage());
        }
    }

    @Test
    public void testSearchCity(){
        String expected1 = "[City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=2 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=4 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}, City{name=City-F id=9 coordinate=java.awt.Point[x=600,y=600]}, City{name=City-J id=10 coordinate=java.awt.Point[x=700,y=700]}, City{name=City-I id=11 coordinate=java.awt.Point[x=800,y=800]}]";
        String expected2 = "[]";
        String expected3 = "[City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}]";
        String expected4 = "[City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}]";
        assertEquals(expected1, actual.getCities().toString());
        Items items = actual.items();
        assertEquals("items.searchCities(\"City\")", expected2, items.searchCities("City").toString());
        assertEquals("items.searchCities(\"City-C\")", expected3, items.searchCities("City-C").toString());
        assertEquals("items.searchCities(\"City-E\")", expected4, items.searchCities("City-E").toString());
        assertEquals("items.searchCities(\"City-W\")", expected2, items.searchCities("City-W").toString());
    }

    @Test
    public void testSearchRoad(){
        String expected1 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected2 = "[Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected3 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}]";
        String expected4 = "[Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected5 = "[]";
        assertEquals(expected1, actual.getRoads().toString());
        Items items = actual.items();
        assertEquals("items.searchRoads(new City(5, \"City-E\", new Point(500, 500)))", expected2, items.searchRoads(new City(5, "City-E", new Point(500, 500))).toString());
        assertEquals("items.searchRoads(new City(1, \"City-A\", new Point(100, 100)))", expected3, items.searchRoads(new City(1, "City-A", new Point(100, 100))).toString());
        assertEquals("items.searchRoads(new City(8, \"City-E\", new Point(503, 503)))", expected4, items.searchRoads(new City(8, "City-E", new Point(503, 503))).toString());
        assertEquals("items.searchRoads(new City(9, \"City-F\", new Point(600, 600)))", expected5, items.searchRoads(new City(9, "City-F", new Point(600, 600))).toString());
    }

    @Test
    public void testDelRoadTrue(){
        Items items = actual.items();
        assertTrue("Road-1", items.deleteRoad("Road-1"));
        assertTrue("Road-3", items.deleteRoad("Road-3"));
    }

    @Test
    public void testDelRoadFalse(){
        Items items = actual.items();
        items.deleteRoad("Road-1");
        assertFalse("Road-1", items.deleteRoad("Road-1"));
        assertFalse("xxx", items.deleteRoad("xxx"));
    }

    @Test
    public void testDelRoadException1() {
        String expected = "Road-Name is Null";
        Items items = actual.items();
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
        Items items = actual.items();
        try {
            items.deleteRoad("");
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("items.deleteRoad(\"\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelRoad() {
        String expected1 = "[]";
        String expected2 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}]";
        String expected3 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}]";
        Items items = actual.items();
        items.deleteRoad("Road-1");
        items.deleteRoad("Road-3");
        assertEquals("getRoads()", expected2, actual.getRoads().toString());
        assertEquals("items.searchRoads(new City(1, \"City-A\", new Point(100, 100)))", expected1, items.searchRoads(new City(1, "City-A", new Point(100, 100))).toString());
        assertEquals("items.searchRoads(new City(2, \"City-B\", new Point(200, 200)))", expected1, items.searchRoads(new City(2, "City-B", new Point(200, 200))).toString());
        assertEquals("items.searchRoads(new City(3, \"City-C\", new Point(300, 300)))", expected3, items.searchRoads(new City(3, "City-C", new Point(300, 300))).toString());
        assertEquals("items.searchRoads(new City(8, \"City-E\", new Point(503, 503)))", expected1, items.searchRoads(new City(8, "City-E", new Point(503, 503))).toString());
    }

    @Test
    public void testDelCityTrue(){
        Items items = actual.items();
        assertTrue("items.deleteCities(\"City-E\")", items.deleteCities("City-E"));
    }

    @Test
    public void testDelCityException1() {
        Object expected = null;
        Items items = actual.items();
        try {
            items.deleteCities("xxx");
            fail();
        } catch(NullPointerException npe) {
            assertEquals("items.deleteCities(\"xxx\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelCityException2() {
        Object expected = null;
        Items items = actual.items();
        items.deleteCities("City-E");
        try {
            items.deleteCities("City-E");
            fail();
        } catch(NullPointerException npe) {
            assertEquals("items.deleteCities(\"City-E\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelCityException3() {
        String expected = "City-Name is Null";
        Items items = actual.items();
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
        Items items = actual.items();
        try {
            items.deleteCities("");
            fail();
        } catch(IllegalArgumentException npe) {
            assertEquals("items.deleteCities(\"\")", expected, npe.getMessage());
        }
    }

    @Test
    public void testDelCity(){
        String expected1 = "[City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=2 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=4 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=6 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=7 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=8 coordinate=java.awt.Point[x=503,y=503]}, City{name=City-F id=9 coordinate=java.awt.Point[x=600,y=600]}, City{name=City-J id=10 coordinate=java.awt.Point[x=700,y=700]}, City{name=City-I id=11 coordinate=java.awt.Point[x=800,y=800]}]";
        String expected2 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}, Road{name=Road-1 id=1 idCity1=1 idCity2=5}, Road{name=Road-2 id=2 idCity1=5 idCity2=6}, Road{name=Road-3 id=3 idCity1=5 idCity2=8}]";
        String expected3 = "[City{name=City-A id=1 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=2 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=4 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-E id=5 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-J id=10 coordinate=java.awt.Point[x=700,y=700]}, City{name=City-I id=11 coordinate=java.awt.Point[x=800,y=800]}]";
        String expected4 = "[Road{name=Road-0 id=0 idCity1=1 idCity2=3}]";
        String expected5 = "[City{name=City-C id=3 coordinate=java.awt.Point[x=300,y=300]}]";
        String expected6 = "[]";
        assertEquals("Cities status Pre-Delete", expected1, actual.getCities().toString());
        assertEquals("Roads status Pre-Delete", expected2, actual.getRoads().toString());
        Items items = actual.items();
        items.deleteCities("City-E");
        assertEquals("Cities status Post-Delete", expected3, actual.getCities().toString());
        assertEquals("Roads status Post-Delete", expected4, actual.getRoads().toString());
        assertEquals("items.searchCities(\"City-C\")", expected5, items.searchCities("City-C").toString());
        assertEquals("items.searchCities(\"City-E\")", expected6, items.searchCities("City-E").toString());
        assertEquals("items.searchCities(\"City-W\")", expected6, items.searchCities("City-W").toString());
    }
}

package example.testtask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * {@link http://www.mkyong.com/unittest/junit-4-tutorial-1-basic-usage/}
 *
 * {@link https://github.com/JobTest/LuhnAlgorithm/blob/master/src/test/java/com/algorithm/LuhnTest.java}
 * {@link https://www.ibm.com/developerworks/ru/library/j-5things4/}
 * {@link https://examples.javacodegeeks.com/core-java/util/concurrent/copyonwritearraylist/java-util-concurrent-copyonwritearraylist-example/}
 * {@link http://stackoverflow.com/questions/22623649/junit-testing-for-assertequal-nullpointerexception}
 */
public class ServiceTest {

    private Service actualAdd;
    private Service actualIs;
    private Service actual;

    @Before
    public void setUp() {
        actualAdd = new Service();

        actualIs = new Service();
        actualIs.addCity("City-A", 10, 20);
        actualIs.addCity("City-B", 20, 30);
        actualIs.addCity("City-C", 30, 30);
        actualIs.addCity("City-D", 40, 20);
        actualIs.addCity("City-E", 30, 10);
        actualIs.addCity("City-F", 20, 10);
        actualIs.addCity("City-G", 10, 10);
        actualIs.addRoad("Road-1", actualIs.getCity(0), actualIs.getCity(1));
        actualIs.addRoad("Road-2", actualIs.getCity(1), actualIs.getCity(2));
        actualIs.addRoad("Road-3", actualIs.getCity(1), actualIs.getCity(3));
        actualIs.addRoad("Road-4", actualIs.getCity(1), actualIs.getCity(4));
        actualIs.addRoad("Road-5", actualIs.getCity(1), actualIs.getCity(5));
        actualIs.addRoad("Road-6", actualIs.getCity(2), actualIs.getCity(3));
        actualIs.addRoad("Road-7", actualIs.getCity(2), actualIs.getCity(4));
        actualIs.addRoad("Road-8", actualIs.getCity(3), actualIs.getCity(4));
        actualIs.addRoad("Road-9", actualIs.getCity(3), actualIs.getCity(5));

        actual = new Service();
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
        actual.addRoad("Road-0", actual.getCity(0), actual.getCity(2));
        actual.addRoad("Road-1", actual.getCity(0), actual.getCity(4));
        actual.addRoad("Road-2", actual.getCity(4), actual.getCity(5));
        actual.addRoad("Road-3", actual.getCity(4), actual.getCity(7));
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
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
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
        String expected0 = "City{name=City-A id=0 coordinate=java.awt.Point[x=100,y=100]}";
        String expected1 = "City{name=City-B id=1 coordinate=java.awt.Point[x=200,y=200]}";
        String expected2 = "City{name=City-C id=2 coordinate=java.awt.Point[x=300,y=300]}";
        String expected3 = "City{name=City-D id=3 coordinate=java.awt.Point[x=400,y=400]}";
        String expected4 = "City{name=City-E id=4 coordinate=java.awt.Point[x=500,y=500]}";
        String expected5 = "City{name=City-E id=5 coordinate=java.awt.Point[x=501,y=501]}";
        String expected6 = "City{name=City-E id=6 coordinate=java.awt.Point[x=502,y=502]}";
        String expected7 = "City{name=City-E id=7 coordinate=java.awt.Point[x=503,y=503]}";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        assertEquals("getCity(0)", expected0, actualAdd.getCity(0).toString());
        assertEquals("getCity(1)", expected1, actualAdd.getCity(1).toString());
        assertEquals("getCity(2)", expected2, actualAdd.getCity(2).toString());
        assertEquals("getCity(3)", expected3, actualAdd.getCity(3).toString());
        assertEquals("getCity(4)", expected4, actualAdd.getCity(4).toString());
        assertEquals("getCity(5)", expected5, actualAdd.getCity(5).toString());
        assertEquals("getCity(6)", expected6, actualAdd.getCity(6).toString());
        assertEquals("getCity(7)", expected7, actualAdd.getCity(7).toString());
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
        assertTrue("Road{name=Road-0 id=0 idCity1=2 idCity2=0}", actualAdd.addRoad("Road-0", actualAdd.getCity(0), actualAdd.getCity(2)));
        assertTrue("Road{name=Road-1 id=1 idCity1=4 idCity2=0}", actualAdd.addRoad("Road-1", actualAdd.getCity(0), actualAdd.getCity(4)));
        assertTrue("Road{name=Road-2 id=2 idCity1=4 idCity2=5}", actualAdd.addRoad("Road-2", actualAdd.getCity(4), actualAdd.getCity(5)));
        assertTrue("Road{name=Road-3 id=3 idCity1=4 idCity2=7}", actualAdd.addRoad("Road-3", actualAdd.getCity(4), actualAdd.getCity(7)));
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
        actualAdd.addRoad("Road-0", actualAdd.getCity(0), actualAdd.getCity(2));
        actualAdd.addRoad("Road-1", actualAdd.getCity(0), actualAdd.getCity(4));
        actualAdd.addRoad("Road-2", actualAdd.getCity(4), actualAdd.getCity(5));
        actualAdd.addRoad("Road-3", actualAdd.getCity(4), actualAdd.getCity(7));
        assertFalse("Road{name=Road-4 id= idCity1=1 idCity2=1}", actualAdd.addRoad("Road-4", actualAdd.getCity(1), actualAdd.getCity(1)));
        assertFalse("Road{name=Road-3 id= idCity1=5 idCity2=7}", actualAdd.addRoad("Road-3", actualAdd.getCity(5), actualAdd.getCity(7)));
        assertFalse("Road{name=Road-8 id= idCity1=4 idCity2=7}", actualAdd.addRoad("Road-8", actualAdd.getCity(4), actualAdd.getCity(7)));
        assertFalse("Road{name=Road-9 id= idCity1=0 idCity2=4}", actualAdd.addRoad("Road-9", actualAdd.getCity(0), actualAdd.getCity(4)));
        assertFalse("Road{name=Road-10 id= idCity1=4 idCity2=7}", actualAdd.addRoad("Road-10", actualAdd.getCity(4), actualAdd.getCity(7)));
        assertFalse("Road{name=Road-11 id= idCity1=4 idCity2=7}", actualAdd.addRoad("Road-11", actualAdd.getCity(4), actualAdd.getCity(7)));
        assertFalse("Road{name=Road-12 id= idCity1=4 idCity2=7}", actualAdd.addRoad("Road-12", actualAdd.getCity(4), actualAdd.getCity(7)));
    }

    @Test
    public void testAddRoadException1() {
        String expected = "city1 OR city2 is Null";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad("Road-5", null, actualAdd.getCity(7));
            fail();
        } catch(NullPointerException npe) {
            assertEquals("Road{name=Road-5 id= idCity1=null idCity2=7}", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoadException2() {
        String expected = "city1 OR city2 is Null";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
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
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad(null, actualAdd.getCity(5), actualAdd.getCity(7));
            fail();
        } catch(NullPointerException npe) {
            assertEquals("Road{name=null id= idCity1=5 idCity2=7}", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoadException4() {
        String expected = "Road-Name is Empty";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad("", actualAdd.getCity(5), actualAdd.getCity(7));
            fail();
        } catch(IllegalArgumentException iae) {
            assertEquals("Road{name=Empty id= idCity1=5 idCity2=7}", expected, iae.getMessage());
        }
    }

    @Test
    public void testAddRoadException5() {
        String expected = "city1 OR city2 is Null";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        try {
            actualAdd.addRoad("Road-7", null, null);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("actual1.addRoad(\"Road-7\", null, null)", expected, npe.getMessage());
        }
    }

    @Test
    public void testAddRoad() {
        String expected = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}, Road{name=Road-1 id=1 idCity1=4 idCity2=0}, Road{name=Road-2 id=2 idCity1=4 idCity2=5}, Road{name=Road-3 id=3 idCity1=4 idCity2=7}]";
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
        actualAdd.addCity("City-D", 400, 400);
        actualAdd.addCity("City-E", 500, 500);
        actualAdd.addCity("City-E", 501, 501);
        actualAdd.addCity("City-E", 502, 502);
        actualAdd.addCity("City-E", 503, 503);
        actualAdd.addRoad("Road-0", actualAdd.getCity(0), actualAdd.getCity(2));
        actualAdd.addRoad("Road-1", actualAdd.getCity(0), actualAdd.getCity(4));
        actualAdd.addRoad("Road-2", actualAdd.getCity(4), actualAdd.getCity(5));
        actualAdd.addRoad("Road-3", actualAdd.getCity(4), actualAdd.getCity(7));
        assertEquals(expected, actualAdd.getRoads().toString());
    }

    @Test
    public void testIsRoad() {
        assertFalse(actualIs.isRoad(actualIs.getCity(3).getId(), actualIs.getCity(5).getId()));
        assertFalse(actualIs.isRoad(actualIs.getCity(3).getId(), actualIs.getCity(4).getId()));
        assertFalse(actualIs.isRoad(actualIs.getCity(3).getId(), actualIs.getCity(3).getId()));
        assertTrue(actualIs.isRoad(actualIs.getCity(3).getId(), actualIs.getCity(6).getId()));
        assertTrue(actualIs.isRoad(actualIs.getCity(0).getId(), actualIs.getCity(6).getId()));
        assertFalse(actualIs.isRoad(actualIs.getCity(2).getId(), actualIs.getCity(5).getId()));
    }

    @Test
    public void testSearch() {
        String expected1 = "[]";
        String expected2 = "[City{name=City-A id=0 coordinate=java.awt.Point[x=100,y=100]}]";
        String expected3 = "[City{name=City-E id=4 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=5 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=6 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=7 coordinate=java.awt.Point[x=503,y=503]}]";
        String expected4 = "[Road{name=Road-1 id=1 idCity1=4 idCity2=0}, Road{name=Road-2 id=2 idCity1=4 idCity2=5}, Road{name=Road-3 id=3 idCity1=4 idCity2=7}]";
        String expected5 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}, Road{name=Road-1 id=1 idCity1=4 idCity2=0}]";
        Items items = actual.items();
        assertEquals("searchCities(\"City\")", expected1, items.searchCities("City").toString());
        assertEquals("searchCities(\"City-A\")", expected2, items.searchCities("City-A").toString());
        assertEquals("searchCities(\"City-E\")", expected3, items.searchCities("City-E").toString());
        assertEquals("searchRoads(4)", expected4, items.searchRoads(actual.getCity(4)).toString());
        assertEquals("searchRoads(0)", expected5, items.searchRoads(actual.getCity(0)).toString());
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
        String expected1 = "[City{name=City-A id=0 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=1 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=2 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=3 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-E id=4 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=5 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=6 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=7 coordinate=java.awt.Point[x=503,y=503]}, City{name=City-F id=8 coordinate=java.awt.Point[x=600,y=600]}, City{name=City-J id=9 coordinate=java.awt.Point[x=700,y=700]}, City{name=City-I id=10 coordinate=java.awt.Point[x=800,y=800]}]";
        String expected2 = "[]";
        String expected3 = "[City{name=City-C id=2 coordinate=java.awt.Point[x=300,y=300]}]";
        String expected4 = "[City{name=City-E id=4 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=5 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=6 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=7 coordinate=java.awt.Point[x=503,y=503]}]";
        assertEquals(expected1, actual.getCities().toString());
        Items items = actual.items();
        assertEquals(expected2, items.searchCities("City").toString());
        assertEquals(expected3, items.searchCities("City-C").toString());
        assertEquals(expected4, items.searchCities("City-E").toString());
        assertEquals(expected2, items.searchCities("City-W").toString());
    }

    @Test
    public void testSearchRoad(){
        String expected1 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}, Road{name=Road-1 id=1 idCity1=4 idCity2=0}, Road{name=Road-2 id=2 idCity1=4 idCity2=5}, Road{name=Road-3 id=3 idCity1=4 idCity2=7}]";
        String expected2 = "[Road{name=Road-1 id=1 idCity1=4 idCity2=0}, Road{name=Road-2 id=2 idCity1=4 idCity2=5}, Road{name=Road-3 id=3 idCity1=4 idCity2=7}]";
        String expected3 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}, Road{name=Road-1 id=1 idCity1=4 idCity2=0}]";
        String expected4 = "[Road{name=Road-3 id=3 idCity1=4 idCity2=7}]";
        String expected5 = "[]";
        assertEquals(expected1, actual.getRoads().toString());
        Items items = actual.items();
        assertEquals(expected2, items.searchRoads(actual.getCity(4)).toString());
        assertEquals(expected3, items.searchRoads(actual.getCity(0)).toString());
        assertEquals(expected4, items.searchRoads(actual.getCity(7)).toString());
        assertEquals(expected5, items.searchRoads(actual.getCity(8)).toString());
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
        String expected2 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}, Road{name=Road-2 id=2 idCity1=4 idCity2=5}]";
        String expected3 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}]";
        Items items = actual.items();
        items.deleteRoad("Road-1");
        items.deleteRoad("Road-3");
        assertEquals(expected2, actual.getRoads().toString());
        assertEquals(expected1, items.searchRoads(actual.getCity(0)).toString());
        assertEquals(expected1, items.searchRoads(actual.getCity(1)).toString());
        assertEquals(expected3, items.searchRoads(actual.getCity(2)).toString());
        assertEquals(expected1, items.searchRoads(actual.getCity(7)).toString());
    }

    @Test
    public void testDelCityTrue(){
        Items items = actual.items();
        assertTrue("City-E", items.deleteCities("City-E"));
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
        String expected1 = "[City{name=City-A id=0 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=1 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=2 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=3 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-E id=4 coordinate=java.awt.Point[x=500,y=500]}, City{name=City-E id=5 coordinate=java.awt.Point[x=501,y=501]}, City{name=City-E id=6 coordinate=java.awt.Point[x=502,y=502]}, City{name=City-E id=7 coordinate=java.awt.Point[x=503,y=503]}, City{name=City-F id=8 coordinate=java.awt.Point[x=600,y=600]}, City{name=City-J id=9 coordinate=java.awt.Point[x=700,y=700]}, City{name=City-I id=10 coordinate=java.awt.Point[x=800,y=800]}]";
        String expected2 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}, Road{name=Road-1 id=1 idCity1=4 idCity2=0}, Road{name=Road-2 id=2 idCity1=4 idCity2=5}, Road{name=Road-3 id=3 idCity1=4 idCity2=7}]";
        String expected3 = "[City{name=City-A id=0 coordinate=java.awt.Point[x=100,y=100]}, City{name=City-B id=1 coordinate=java.awt.Point[x=200,y=200]}, City{name=City-C id=2 coordinate=java.awt.Point[x=300,y=300]}, City{name=City-D id=3 coordinate=java.awt.Point[x=400,y=400]}, City{name=City-F id=8 coordinate=java.awt.Point[x=600,y=600]}, City{name=City-J id=9 coordinate=java.awt.Point[x=700,y=700]}, City{name=City-I id=10 coordinate=java.awt.Point[x=800,y=800]}]";
        String expected4 = "[Road{name=Road-0 id=0 idCity1=2 idCity2=0}]";
        String expected5 = "[City{name=City-C id=2 coordinate=java.awt.Point[x=300,y=300]}]";
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

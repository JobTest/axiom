package example.testtask.some;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class KeyCityTest {

    Map<KeyCity, Integer> cities;

    @Before
    public void setUp() {
        cities = new HashMap<KeyCity, Integer>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddCity() {
        KeyCity keyCityA = new KeyCity("City-A");
        cities.put(keyCityA, 1);
        keyCityA = new KeyCity("City-A");
        keyCityA.incId();
        cities.put(keyCityA, 2);
        keyCityA = new KeyCity("City-A");
        keyCityA.incId();
        keyCityA.incId();
        cities.put(keyCityA, 3);

        KeyCity keyCityB = new KeyCity("City-B");
        cities.put(keyCityB, 11);
        keyCityB = new KeyCity("City-B");
        keyCityB.incId();
        cities.put(keyCityB, 22);
        keyCityB = new KeyCity("City-B");
        keyCityB.incId();
        keyCityB.incId();
        cities.put(keyCityB, 33);

        assertEquals("(KeyCity) size:", 6, cities.size());
    }

    @Test
    public void testSearchCity() {
        KeyCity keyCityA = new KeyCity("City-A");
        cities.put(keyCityA, 1);
        keyCityA = new KeyCity("City-A");
        keyCityA.incId();
        cities.put(keyCityA, 2);
        keyCityA = new KeyCity("City-A");
        keyCityA.incId();
        keyCityA.incId();
        cities.put(keyCityA, 3);

        KeyCity keyCityB = new KeyCity("City-B");
        cities.put(keyCityB, 11);
        keyCityB = new KeyCity("City-B");
        keyCityB.incId();
        cities.put(keyCityB, 22);
        keyCityB = new KeyCity("City-B");
        keyCityB.incId();
        keyCityB.incId();
        cities.put(keyCityB, 33);

        keyCityA = new KeyCity("City-A");
        assertEquals("KeyCity(\"City-A\") id=1", 1, cities.get(keyCityA).intValue());
        keyCityA = new KeyCity("City-A");
        keyCityA.incId();
        assertEquals("KeyCity(\"City-A\") id=3", 2, cities.get(keyCityA).intValue());
        keyCityA = new KeyCity("City-A");
        keyCityA.incId();
        keyCityA.incId();
        assertEquals("KeyCity(\"City-A\") id=3", 3, cities.get(keyCityA).intValue());

        keyCityB = new KeyCity("City-B");
        assertEquals("KeyCity(\"City-B\") id=11", 11, cities.get(keyCityB).intValue());
        keyCityB = new KeyCity("City-B");
        keyCityB.incId();
        assertEquals("KeyCity(\"City-B\") id=22", 22, cities.get(keyCityB).intValue());
        keyCityB = new KeyCity("City-B");
        keyCityB.incId();
        keyCityB.incId();
        assertEquals("KeyCity(\"City-B\") id=33", 33, cities.get(keyCityB).intValue());
    }
}

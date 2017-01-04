package example.testtask.some;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class KeyRoadTest {

    Map<KeyRoad, String> roads;

    @Before
    public void setUp() {
        roads = new HashMap<KeyRoad, String>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddRoad() {
        KeyRoad keyRoad1 = new KeyRoad(1);
        roads.put(keyRoad1, "Road-1");
        keyRoad1 = new KeyRoad(11);
        roads.put(keyRoad1, "Road-2");
        keyRoad1 = new KeyRoad(111);
        roads.put(keyRoad1, "Road-3");

        KeyRoad keyRoad2 = new KeyRoad(2);
        roads.put(keyRoad2, "Road-1");
        keyRoad2 = new KeyRoad(22);
        roads.put(keyRoad2, "Road-2");
        keyRoad2 = new KeyRoad(222);
        roads.put(keyRoad2, "Road-3");

        assertEquals("(KeyRoad) size:", 6, roads.size());
    }

    @Test
    public void testSearchRoad() {
        KeyRoad keyRoad1 = new KeyRoad(1);
        roads.put(keyRoad1, "Road-1");
        keyRoad1 = new KeyRoad(11);
        roads.put(keyRoad1, "Road-2");
        keyRoad1 = new KeyRoad(111);
        roads.put(keyRoad1, "Road-3");

        KeyRoad keyRoad2 = new KeyRoad(2);
        roads.put(keyRoad2, "Road-1");
        keyRoad2 = new KeyRoad(22);
        roads.put(keyRoad2, "Road-2");
        keyRoad2 = new KeyRoad(222);
        roads.put(keyRoad2, "Road-3");

        assertEquals("", "Road-1", roads.get(new KeyRoad(1)));
        assertEquals("", roads.get(new KeyRoad(1)), roads.get(new KeyRoad(2)));
        assertEquals("", "Road-2", roads.get(new KeyRoad(11)));
        assertEquals("", roads.get(new KeyRoad(11)), roads.get(new KeyRoad(22)));
        assertEquals("", "Road-3", roads.get(new KeyRoad(111)));
        assertEquals("", roads.get(new KeyRoad(111)), roads.get(new KeyRoad(222)));
    }
}

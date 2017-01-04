package example.testtask.some;

import example.testtask.City;
import example.testtask.Road;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DijkstraTest {

    private Dijkstra figureBase;
    private Dijkstra figure1;
    private Dijkstra figure2;
    private Dijkstra figureX;

    @Before
    public void setUp() {
        Queue<City> citiesBase = new ConcurrentLinkedQueue<City>();
        citiesBase.add(new City(1, "City-A", new Point(10, 10)));
        citiesBase.add(new City(2, "City-B", new Point(10, 10)));
        citiesBase.add(new City(3, "City-C", new Point(10, 10)));
        citiesBase.add(new City(4, "City-D", new Point(10, 10)));
        citiesBase.add(new City(5, "City-E", new Point(10, 10)));
        citiesBase.add(new City(6, "City-F", new Point(10, 10)));
        citiesBase.add(new City(7, "City-G", new Point(10, 10)));
        Queue<Road> roadsBase = new ConcurrentLinkedQueue<Road>();
        roadsBase.add(new Road("Road-1", new City(1, "City-A", new Point(10, 10)), new City(2, "City-B", new Point(10, 10)), 10));
        roadsBase.add(new Road("Road-2", new City(1, "City-A", new Point(10, 10)), new City(3, "City-C", new Point(10, 10)), 70));
        roadsBase.add(new Road("Road-3", new City(2, "City-B", new Point(10, 10)), new City(4, "City-D", new Point(10, 10)), 40));
        roadsBase.add(new Road("Road-4", new City(2, "City-B", new Point(10, 10)), new City(5, "City-E", new Point(10, 10)), 20));
        roadsBase.add(new Road("Road-5", new City(3, "City-C", new Point(10, 10)), new City(2, "City-B", new Point(10, 10)), 40));
        roadsBase.add(new Road("Road-6", new City(3, "City-C", new Point(10, 10)), new City(5, "City-E", new Point(10, 10)), 50));
        roadsBase.add(new Road("Road-7", new City(4, "City-D", new Point(10, 10)), new City(5, "City-E", new Point(10, 10)), 30));
        roadsBase.add(new Road("Road-8", new City(5, "City-E", new Point(10, 10)), new City(3, "City-C", new Point(10, 10)), 30));
        roadsBase.add(new Road("Road-9", new City(5, "City-E", new Point(10, 10)), new City(4, "City-D", new Point(10, 10)), 100));
        roadsBase.add(new Road("Road-10", new City(6, "City-F", new Point(10, 10)), new City(7, "City-G", new Point(10, 10)), 30));
        roadsBase.add(new Road("Road-11", new City(7, "City-G", new Point(10, 10)), new City(6, "City-F", new Point(10, 10)), 40));
        figureBase = Dijkstra.getInstance(roadsBase, citiesBase, 0);

        Queue<City> cities1 = new ConcurrentLinkedQueue<City>();
        cities1.add(new City(1, "City-A", new Point(100, 200)));
        cities1.add(new City(2, "City-B", new Point(200, 300)));
        cities1.add(new City(3, "City-C", new Point(300, 300)));
        cities1.add(new City(4, "City-D", new Point(400, 200)));
        cities1.add(new City(5, "City-E", new Point(300, 100)));
        cities1.add(new City(6, "City-F", new Point(200, 100)));
        Queue<Road> roads1 = new ConcurrentLinkedQueue<Road>();
        roads1.add(new Road("Road-1", new City(1, "City-A", new Point(100, 200)), new City(2, "City-B", new Point(200, 300)), 150));
        roads1.add(new Road("Road-2", new City(2, "City-B", new Point(200, 300)), new City(3, "City-C", new Point(300, 300)), 100));
        roads1.add(new Road("Road-3", new City(2, "City-B", new Point(200, 300)), new City(4, "City-D", new Point(400, 200)), 225));
        roads1.add(new Road("Road-4", new City(2, "City-B", new Point(200, 300)), new City(5, "City-E", new Point(300, 100)), 225));
        roads1.add(new Road("Road-5", new City(2, "City-B", new Point(200, 300)), new City(6, "City-F", new Point(200, 100)), 200));
        roads1.add(new Road("Road-6", new City(3, "City-C", new Point(300, 300)), new City(4, "City-D", new Point(400, 200)), 150));
        roads1.add(new Road("Road-7", new City(3, "City-C", new Point(300, 300)), new City(5, "City-E", new Point(300, 100)), 200));
        roads1.add(new Road("Road-8", new City(4, "City-D", new Point(400, 200)), new City(6, "City-F", new Point(200, 100)), 225));
        figure1 = Dijkstra.getInstance(roads1, cities1, 2);

        Queue<City> cities2 = new ConcurrentLinkedQueue<City>();
        cities2.add(new City(1, "City-A", new Point(100, 200)));
        cities2.add(new City(2, "City-B", new Point(200, 300)));
        cities2.add(new City(3, "City-C", new Point(300, 300)));
        cities2.add(new City(4, "City-D", new Point(400, 200)));
        cities2.add(new City(5, "City-E", new Point(300, 100)));
        cities2.add(new City(6, "City-F", new Point(200, 100)));
        cities2.add(new City(7, "City-G", new Point(100, 100)));
        cities2.add(new City(8, "City-H", new Point(100, 100)));
        cities2.add(new City(9, "City-I", new Point(100, 100)));
        Queue<Road> roads2 = new ConcurrentLinkedQueue<Road>();
        roads2.add(new Road("Road-1", new City(1, "City-A", new Point(100, 200)), new City(2, "City-B", new Point(200, 300)), 150));
        roads2.add(new Road("Road-2", new City(2, "City-B", new Point(200, 300)), new City(3, "City-C", new Point(300, 300)), 100));
        roads2.add(new Road("Road-3", new City(2, "City-B", new Point(200, 300)), new City(4, "City-D", new Point(400, 200)), 225));
        roads2.add(new Road("Road-4", new City(2, "City-B", new Point(200, 300)), new City(5, "City-E", new Point(300, 100)), 225));
        roads2.add(new Road("Road-5", new City(2, "City-B", new Point(200, 300)), new City(6, "City-F", new Point(200, 100)), 200));
        roads2.add(new Road("Road-6", new City(3, "City-C", new Point(300, 300)), new City(4, "City-D", new Point(400, 200)), 150));
        roads2.add(new Road("Road-7", new City(3, "City-C", new Point(300, 300)), new City(5, "City-E", new Point(300, 100)), 200));
        roads2.add(new Road("Road-8", new City(4, "City-D", new Point(400, 200)), new City(6, "City-F", new Point(200, 100)), 225));
        roads2.add(new Road("Road-9", new City(5, "City-E", new Point(400, 200)), new City(8, "City-H", new Point(200, 100)), 100));
        roads2.add(new Road("Road-10", new City(8, "City-H", new Point(400, 200)), new City(9, "City-I", new Point(200, 100)), 150));
        figure2 = Dijkstra.getInstance(roads2, cities2, 8);

        Queue<City> citiesX = new ConcurrentLinkedQueue<City>();
        citiesX.add(new City(1, "City-A", new Point(100, 100)));
        citiesX.add(new City(2, "City-B", new Point(100, 100)));
        citiesX.add(new City(3, "City-C", new Point(100, 100)));
        citiesX.add(new City(4, "City-D", new Point(100, 100)));
        citiesX.add(new City(5, "City-E", new Point(100, 100)));
        citiesX.add(new City(6, "City-F", new Point(100, 100)));
        citiesX.add(new City(7, "City-G", new Point(100, 100)));
        citiesX.add(new City(8, "City-H", new Point(100, 100)));
        citiesX.add(new City(9, "City-I", new Point(100, 100)));
        citiesX.add(new City(10, "City-J", new Point(100, 100)));
        citiesX.add(new City(11, "City-K", new Point(100, 100)));
        citiesX.add(new City(12, "City-L", new Point(100, 100)));
        Queue<Road> roadsX = new ConcurrentLinkedQueue<Road>();
        roadsX.add(new Road("Road-1", new City(1, "City-A", new Point(100, 100)), new City(8, "City-H", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-2", new City(1, "City-A", new Point(100, 100)), new City(2, "City-B", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-3", new City(2, "City-B", new Point(100, 100)), new City(7, "City-G", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-4", new City(2, "City-B", new Point(100, 100)), new City(3, "City-C", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-5", new City(3, "City-C", new Point(100, 100)), new City(6, "City-F", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-6", new City(3, "City-C", new Point(100, 100)), new City(4, "City-D", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-7", new City(4, "City-D", new Point(100, 100)), new City(5, "City-E", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-8", new City(8, "City-H", new Point(100, 100)), new City(9, "City-I", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-9", new City(8, "City-H", new Point(100, 100)), new City(7, "City-G", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-10", new City(7, "City-G", new Point(100, 100)), new City(10, "City-J", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-11", new City(7, "City-G", new Point(100, 100)), new City(6, "City-F", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-12", new City(6, "City-F", new Point(100, 100)), new City(11, "City-K", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-13", new City(6, "City-F", new Point(100, 100)), new City(5, "City-E", new Point(100, 100)), 100));
        roadsX.add(new Road("Road-14", new City(5, "City-E", new Point(100, 100)), new City(12, "City-L", new Point(100, 100)), 100));
        figureX = Dijkstra.getInstance(roadsX, citiesX, 1);
    }

    @After
    public void tearDown() {
        figureBase = null;
        figure1 = null;
        figure2 = null;
        figureX = null;
    }

    @Test
    public void testFigureBase() {
        int iFinish = 3;
        int[]   expected1 = {0,10,60,50,30,-1,-1};
        int[][] expected2 = {{1},{1,2},{1,2,5,3},{1,2,4},{1,2,5}};
        int[][] expected3 = {{1,2,5,3},{60}};

        assertArrayEquals("Массив всех дистанций", expected1, figureBase.getDistancies());
        assertArrayEquals("Массив всех путей", expected2, figureBase.getWays());
        assertArrayEquals("Поиск пути и дистанции...", expected3, figureBase.search(iFinish));
    }

    @Test(expected=NullPointerException.class)
    public void testFigureBaseException() {
        int iFinish = 6;

        assertEquals("Выход за пределы поиска пути и дистанции...", 0, figureBase.search(iFinish)[1][0]);
    }

    @Test
    public void testFigure1() {
        int iFinish = 6;
        int[]   expected1 = {250,100,0,150,200,300};
        int[][] expected2 = {{3,2,1},{3,2},{3},{3,4},{3,5},{3,2,6}};
        int[][] expected3 = {{3,2,6},{300}};

        assertArrayEquals("Массив всех дистанций", expected1, figure1.getDistancies());
        assertArrayEquals("Массив всех путей", expected2, figure1.getWays());
        assertArrayEquals("Поиск пути и дистанции...", expected3, figure1.search(iFinish));
    }

    @Test(expected=NullPointerException.class)
    public void testFigure1Exception1() {
        int iFinish = -1;

        assertEquals("Выход за пределы поиска пути и дистанции...", 0, figure1.search(iFinish)[1][0]);
    }

    @Test(expected=NullPointerException.class)
    public void testFigure1Exception2() {
        int iFinish = 7;

        assertEquals("Выход за пределы поиска пути и дистанции...", 0, figure1.search(iFinish)[1][0]);
    }

    @Test
    public void testFigure2() {
        int iFinish = 3;
        int[]   expected1 = {625,475,450,-1,250,-1,-1,150,0};
        int[][] expected2 = {{9,8,5,2,1},{9,8,5,2},{9,8,5,3},{9,8,5},{9,8},{9}};
        int[][] expected3 = {{9,8,5,3},{450}};

        assertArrayEquals("Массив всех дистанций", expected1, figure2.getDistancies());
        assertArrayEquals("Массив всех путей", expected2, figure2.getWays());
        assertArrayEquals("Поиск пути и дистанции...", expected3, figure2.search(iFinish));
    }

    @Test(expected=NullPointerException.class)
    public void testFigure2Exception() {
        int iFinish = 4;

        assertEquals("Выход за пределы поиска пути и дистанции...", 0, figure2.search(iFinish)[1][0]);
    }

    @Test
    public void testFigureX() {
        int iFinish = 5;
        int[]   expected1 = {100,0,100,200,300,200,100,200,300,200,300,400};
        int[][] expected2 = {{2,1},{2},{2,3},{2,3,4},{2,7,6,5},{2,7,6},{2,7},{2,1,8},{2,1,8,9},{2,7,10},{2,7,6,11},{2,7,6,5,12}};
        int[][] expected3 = {{2,7,6,5},{300}};

        assertArrayEquals("Массив всех дистанций", expected1, figureX.getDistancies());
        assertArrayEquals("Массив всех путей", expected2, figureX.getWays());
        assertArrayEquals("Поиск пути и дистанции...", expected3, figureX.search(iFinish));
    }

    @Test(expected=NullPointerException.class)
    public void testFigureXException() {
        int iFinish = 13;

        assertEquals("Выход за пределы поиска пути и дистанции...", 0, figureX.search(iFinish)[1][0]);
    }

}
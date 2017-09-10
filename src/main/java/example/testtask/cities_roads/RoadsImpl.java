package example.testtask.cities_roads;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by alexandr on 15.01.16.
 * ********************************
 * (Пример класса: точки на плоскости) http://kostin.ws/java/example-class-point.html
 */
public class RoadsImpl implements Roads {
//    private HashMap<Dimension, String> cities;
    private Map<Dimension, String> cities;
    private Map<Road, String>       roads;
//    private TreeSet

    public RoadsImpl(){
        cities = new TreeMap<Dimension, String>();
        roads  = new TreeMap<Road, String>();
    }

    @Override
    public void addCity(String nane, Dimension coordinates) {
        cities.put(coordinates, nane);
    }

    @Override
    public void addCity(String nane, int x, int y) {
        cities.put(new Dimension(x,y), nane);
    }

    @Override
    public void addRoad(String nane) {

    }

    @Override
    public void addRoad(String nane, String city1, String city2) {
        roads.put(new Road(city1, city2), nane);
    }

    @Override
    public void relationsRoad(String nane, String city1, String city2) {

    }

    @Override
    public void getRoads(String city) {

    }

    @Override
    public void deleteRoad(String nane) {
//        for (Map.Entry<Road, String> road : roads.entrySet()) {
//            Road currentRoad = (Road)road.getKey();
//            String currentName = road.getValue();
//        }
        roads.containsKey(nane);
    }

    @Override
    public void deleteCity(String nane) {

    }
}


class Road implements Comparable {
    private String city1;
    private String city2;

    public Road(){}
    public Road(String city1, String city2){
        this.city1 = city1;
        this.city2 = city2;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road)) return false;

        Road road = (Road) o;

        if (!city1.equals(road.city1)) return false;
        if (!city2.equals(road.city2)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = city1.hashCode();
        result = 31 * result + city2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Road{" +
                ", city1='" + city1 + '\'' +
                ", city2='" + city2 + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

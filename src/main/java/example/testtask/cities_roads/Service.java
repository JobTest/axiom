package example.testtask.cities_roads;

import java.awt.*;

public class Service extends ServiceSome {
    /*
     * Сюда вынесены только условия-проверки при добавлении и удалении нового объекта
     */
    @Override
    public boolean addCity(String name, int latitude, int longitude) throws NullPointerException, IllegalArgumentException {
        if (name==null)
            throw new NullPointerException("City-Name is Null");
        else if (name.isEmpty())
            throw new IllegalArgumentException("City-Name is Empty");

        City city = new City(name, new Point(latitude, longitude));
        return (!getCities().contains(city)) ? getCities().add(city) : false;
    }

    @Override
    public boolean addRoad(String name, City city1, City city2) throws NullPointerException, IllegalArgumentException {
        if (name==null)
            throw new NullPointerException("Road-Name is Null");
        else if (name.isEmpty())
            throw new IllegalArgumentException("Road-Name is Empty");
        if (city1==null || city2==null)
            throw new NullPointerException("city1 OR city2 is Null");

        Road road = new Road(name, city1.getId(), city2.getId());
        return (!city1.equals(city2) && !getRoads().contains(road) && isRoad(city1.getId(), city2.getId())) ? getRoads().add(road) : false;
    }

}


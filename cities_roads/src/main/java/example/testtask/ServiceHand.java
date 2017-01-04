package example.testtask;

import example.testtask.some.ServiceSome;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServiceHand extends ServiceSome implements Service {

    private static final Logger logger = Logger.getLogger(ServiceHand.class);

    @Override
    public Queue<City> getCities(){
        return new ConcurrentLinkedQueue<City>(super.getCities());
    }

    @Override
    public Queue<Road> getRoads(){
        return new ConcurrentLinkedQueue<Road>(super.getRoads());
    }

    @Override
    public boolean addCity(String name, int latitude, int longitude) {
        logger.warn("'" + name + "';");
        if (name==null)
            throw new NullPointerException("City-Name is Null");
        else if (name.isEmpty())
            throw new IllegalArgumentException("City-Name is Empty");

        City city = new City(name, new Point(latitude, longitude));
        return (!super.getCities().contains(city)) ? super.getCities().add(city) : false;
    }

    @Override
    public boolean addRoad(String name, City city1, City city2) {
        logger.warn("'" + name + "'; '" + city1 + "'; " + city2 + "';");
        if (name==null)
            throw new NullPointerException("Road-Name is Null");
        else if (name.isEmpty())
            throw new IllegalArgumentException("Road-Name is Empty");
        if (city1==null || city2==null)
            throw new NullPointerException("city1 OR city2 is Null");

        Road road = new Road(name, city1, city2);
        return (!city1.equals(city2) && !super.getRoads().contains(road) && isRoad(city1, city2)) ? super.getRoads().add(road) : false;
    }

}

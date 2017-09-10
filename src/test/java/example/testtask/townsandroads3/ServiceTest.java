package example.testtask.townsandroads3;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Саша on 07.02.2016.
 */
public class ServiceTest extends AService implements IService {

    private static String LOG_PROPERTIES_FILE = "src/test/resources/log4j.properties"; //private static String LOG_PROPERTIES_FILE = "./src/test/resources/log4j.properties";
    private static Logger log = Logger.getLogger(ServiceTest.class);
    private Config conf;

    @Before
    public void setUp() {
        // log4j
        conf = new Config(LOG_PROPERTIES_FILE);
        conf.init();
    }

    @Test
    public void testCity(){
        addCity("City-A", 100.0, 100.0);
        addCity("City-B", 200.0, 200.0);
        addCity("City-C", 300.0, 300.0);
        addCity("City-D", 400.0, 400.0);
        addCity("City-E", 500.0, 500.0);
        addCity("City-E", 501.1, 501.1);
        addCity("City-E", 502.2, 502.2);
        addCity("City-E", 503.3, 503.3);

        try {
            System.out.println( "--------------------[ City ]" );
            log.info("--------------------[ City ]");
            for (Map.Entry<CityName, City> city : getCities("City").entrySet()){
                System.out.println(city.getValue());
                log.warn(city.getValue());
            }
            System.out.println( "--------------------[ City-C ]" );
            log.info("--------------------[ City-C ]");
            for (Map.Entry<CityName, City> city : getCities("City-C").entrySet()){
                System.out.println(city.getValue());
                log.warn(city.getValue());
            }
            System.out.println( "--------------------[ City-E ]" );
            log.info("--------------------[ City-E ]");
            for (Map.Entry<CityName, City> city : getCities("City-E").entrySet()){
                System.out.println(city.getValue());
                log.warn(city.getValue());
            }
        } catch (CloneNotSupportedException e) { e.printStackTrace(); }
    }

    @Test
    public void testRoad(){
        addRoad("Road-1", "City-A", "City-B");
        addRoad("Road-2", "City-B", "City-C");
        addRoad("Road-3", "City-C", "City-D");
        addRoad("Road-4", "City-E", "City-A");
        addRoad("Road-5", "City-E", "City-B");
        addRoad("Road-6", "City-E", "City-C");
        addRoad("Road-7", "City-E", "City-D");

        try {
            System.out.println( "====================[ City ]" );
            for (Map.Entry<RoadName, Road> road : getRoads("City").entrySet())
                System.out.println(road.getValue());

            System.out.println( "====================[ City-C ]" );
            for (Map.Entry<RoadName, Road> road : getRoads("City-C").entrySet())
                System.out.println(road.getValue());

            System.out.println( "====================[ City-E ]" );
            for (Map.Entry<RoadName, Road> road : getRoads("City-E").entrySet())
                System.out.println(road.getValue());
        } catch (CloneNotSupportedException e) { e.printStackTrace(); }
    }

    @Test
    public void testRoad2(){
        addRoad("Road-1", "City-A", "City-B");
        addRoad("Road-2", "City-B", "City-C");
        addRoad("Road-3", "City-C", "City-D");
        addRoad("Road-4", "City-E", "City-A");
        addRoad("Road-5", "City-E", "City-B");
        addRoad("Road-6", "City-E", "City-C");
        addRoad("Road-7", "City-E", "City-D");

        System.out.println( "++++++++++++++++++++[ City-E, City-B ]" );
        System.out.println(getRoad("City-E", "City-B"));
    }

    @Override
    public City addCity(String name, double latitude, double longitude){
        return addCity(new CityName(name), new City(name, latitude, longitude));
    }

    @Override
    public Road addRoad(String name, String nameCity1, String nameCity2){
        return addRoad(new RoadName(nameCity1, nameCity2), new Road(name, nameCity1, nameCity2));
    }

    @Override
    public Map<CityName, City> getCities(String byNameCity) throws CloneNotSupportedException {
        return getCities(new CityName(byNameCity));
    }

    @Override
    public Map<RoadName, Road> getRoads(String byNameCity) throws CloneNotSupportedException {
        return getRoads(new RoadName(byNameCity));
    }

    @Override
    public Road getRoad(String byNameCity1, String byNameCity2){
        return getRoad(new RoadName(byNameCity1, byNameCity2));
    }
}

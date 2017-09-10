package example.testtask.townsandroads;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandr on 29.01.16.
 */
public class Service3Test {

    Map<CityName, City> cities;

    @Before
    public void setUp() {
        cities = new HashMap<CityName, City>();
    }

    @Test
    public void testMy(){
        addCity("City-A", new City(100.0, 100.0));
        addCity("City-B", new City(200.0, 200.0));
        addCity("City-C", new City(300.0, 300.0));
        addCity("City-D", new City(400.0, 400.0));
        addCity("City-E", new City(500.0, 500.0));
        addCity("City-E", new City(501.1, 501.1));
        addCity("City-E", new City(502.2, 502.2));
        addCity("City-E", new City(503.3, 503.3));

        System.out.println( "--------------------" );
        System.out.println( cities.values() );

        System.out.println( "--------------------[ City ]" );
        for (Map.Entry<CityName, City> city : getCities("City").entrySet()){
            System.out.println("["+city.getKey().getName()+"] "+city.getValue());
        }
        System.out.println( "--------------------[ City-C ]" );
        for (Map.Entry<CityName, City> city : getCities("City-C").entrySet()){
            System.out.println("["+city.getKey().getName()+"] "+city.getValue());
        }
        System.out.println( "--------------------[ City-E ]" );
        for (Map.Entry<CityName, City> city : getCities("City-E").entrySet()){
            System.out.println("["+city.getKey().getName()+"] "+city.getValue());
        }
        System.out.println( "====================[ City-C ]" );
        System.out.println("[City-C] "+getFirstCity("City-C"));
        System.out.println( "====================[ City-E ]" );
        System.out.println("[City-E] "+getFirstCity("City-E"));
        System.out.println( "====================[ City-E ]" );
        System.out.println("[City-E (2)] "+getCity("City-E",2));
        System.out.println( "====================[ City-C ]" );
        System.out.println("[City-C (last)] "+getLastCity("City-C"));
        System.out.println( "====================[ City-E ]" );
        System.out.println("[City-E (last)] "+getLastCity("City-E"));
    }

    public City addCity(String name, City city){
        CityName cityName;
        int        idCity = -1;
        while (cities.containsKey(cityName = new CityName(name, ++idCity))) {}
        return cities.put(cityName, city);
    }

    public Map<CityName, City> getCities(String name){
        CityName        cityName;
        int               idCity = -1;
        Map<CityName, City> copy = new HashMap<CityName, City>();
        while (cities.containsKey(cityName = new CityName(name, ++idCity))) {
            copy.put(cityName, cities.get(cityName));
        }
        return copy;
    }

    public City getLastCity(String name){
        for (Map.Entry<CityName, City> city : getCities(name).entrySet()){
            return city.getValue();
        }
        return null;
    }
    public City getFirstCity(String name){
        return cities.get(new CityName(name, 0));
    }

    public City getCity(String name, int id){
        return cities.get(new CityName(name, id));
    }


    class MyRoad {
        private String name;
        private String nameCity1;
        private String nameCity2;

        public MyRoad(String name, String nameCity1, String nameCity2){
            this.name = name;
            this.nameCity1 = nameCity1;
            this.nameCity2 = nameCity2;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyRoad)) return false;

            MyRoad myRoad = (MyRoad) o;

            if (!name.equals(myRoad.name)) return false;
            if (!nameCity1.equals(myRoad.nameCity1)) return false;
            if (!nameCity2.equals(myRoad.nameCity2)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result1 = nameCity1.hashCode();
            int result2 = nameCity2.hashCode();
            return result1+result2;
        }

        @Override
        public String toString() {
            return "MyRoad{" +
                    "name='" + name + '\'' +
                    ", nameCity1='" + nameCity1 + '\'' +
                    ", nameCity2='" + nameCity2 + '\'' +
                    '}';
        }
    }

    class MyRoadName {
        private String name;
        private int idRoad;
    }

    class City {
        private double latitude;
        private double longitude;

        public City(double latitude, double longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
        public double getLongitude() {
            return longitude;
        }
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "City{" +
                    "latitude=" + latitude +
                    " longitude=" + longitude +
                    '}';
        }
    }

    class CityName {
        private String name;
        private int idCity;

        public CityName(String name, int idCity){
            this.name   = name;
            this.idCity = name.hashCode()+idCity;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            CityName that = (CityName) o;
            if (idCity != that.idCity) return false;
            if (!name.equals(that.name)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            return result;
        }
    }
}

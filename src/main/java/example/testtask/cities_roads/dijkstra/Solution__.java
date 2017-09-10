package example.testtask.cities_roads.dijkstra;

import java.util.*;

/**
 ** https://github.com/Home-Java8/Java7/blob/master/src/main/java/com/App2.java
 * https://github.com/JobTest/vitrinaPredmainTask/blob/miratex-master/Task/src/main/java/com/vitrina/service/DBService.java
 */
public class Solution__ {

    private Map<City__, String> cities;
    private Map<Road__, String> roads;

    public Solution__() {
        cities = new HashMap<City__, String>();
        roads = new HashMap<Road__, String>();
//        roads = new TreeSet<Road__>(new Comparator<Road__>(){
//            @Override
//            public int compare(Road__ road1, Road__ road2) {
//                if (road1.getLinkCity1()==road2.getLinkCity1())
//                    return 0;
//                if (road1.getLinkCity1()<road2.getLinkCity1())
//                    return 1;
//                else
//                    return -1;
//            }
//        });
    }

    public static void main(String[] args) {
        Solution__ solution = new Solution__();
        System.out.println(solution.addCity("City-1", new City__(10.0, 10.0)));
        System.out.println(solution.addCity("City-2", new City__(11.0, 9.0)));
        System.out.println(solution.addCity("City-3", new City__(12.0, 8.0)));
        System.out.println(solution.addCity("City-4", new City__(13.0, 7.0)));
        System.out.println(solution.addCity("City-5", new City__(14.0, 6.0)));
        System.out.println(solution.addCity("City-6", new City__(13.0, 7.0)));
        System.out.println(solution.addCity(null, new City__(13.0, 7.0)));
        System.out.println(solution.addCity("City-6", new City__(13.0, 17.0)));
        System.out.println(solution.addCity("City-7", new City__(23.0, 17.0)));
        System.out.println(solution.addCity("City-7", new City__(23.0, 18.0)));

//        System.out.println();
//        City__ city = solution.getCity("City-7");
//        if(city != null){
//            System.out.println("["+city.hashCode()+"] "+city);
//        }
//
//        System.out.println( solution.cities.get(new City__(12.0, 8.0)) );


        System.out.println();
        System.out.println(solution.addRoad("Road-1", new Road__(solution.getCity("City-1").hashCode(), solution.getCity("City-2").hashCode())));
        System.out.println(solution.addRoad("Road-2", new Road__(0, 0)));
        System.out.println(solution.addRoad("Road-3", new Road__(0, 100)));
        System.out.println(solution.addRoad(null, new Road__(100, 200)));
        System.out.println(solution.addRoad("Road-4", new Road__(solution.getCity("City-1").hashCode(), solution.getCity("City-2").hashCode())));
        System.out.println(solution.addRoad("Road-5", new Road__(solution.getCity("City-2").hashCode(), solution.getCity("City-3").hashCode())));
        System.out.println(solution.addRoad("Road-6", new Road__(solution.getCity("City-3").hashCode(), solution.getCity("City-4").hashCode())));
        System.out.println(solution.addRoad("Road-7", new Road__(solution.getCity("City-4").hashCode(), solution.getCity("City-5").hashCode())));
        System.out.println(solution.addRoad("Road-8", new Road__(solution.getCity("City-5").hashCode(), solution.getCity("City-6").hashCode())));
        System.out.println(solution.addRoad("Road-8", new Road__(solution.getCity("City-6").hashCode(), solution.getCity("City-7").hashCode())));
        System.out.println(solution.addRoad("Road-9", new Road__(solution.getCity("City-5").hashCode(), solution.getCity("City-6").hashCode())));
        System.out.println(solution.addRoad("Road-10", new Road__(solution.getCity("City-1").hashCode(), solution.getCity("City-6").hashCode())));
        System.out.println(solution.addRoad("Road-11", new Road__(solution.getCity("City-1").hashCode(), solution.getCity("City-5").hashCode())));
        System.out.println(solution.addRoad("Road-12", new Road__(solution.getCity("City-1").hashCode(), solution.getCity("City-4").hashCode())));

//        for (Road__ road:solution.roads.keySet()){
//            System.out.println(road);
//        }
//
//        System.out.println();
//        Road__ road = solution.getRoad("Road-7");
//        if(road != null){
//            System.out.println("[Road-7] "+road);
//        }





        System.out.println();
        String checkCity = "City-1";
        for (Map.Entry<Road__, String> ro:solution.getRoads(checkCity).entrySet()){
            System.out.println(ro.getValue() + " >> " + checkCity);
        }
    }


    /**
     * город определяется по имени и координатам, может быть два города с одним именем, но разными координатами
     * @param name
     * @param city
     */
    public boolean addCity(String name, City__ city){
        if (name!=null
                && !cities.containsKey(city)) {
            cities.put(city, name);
            return true;
        }
        return false;
    }

    public City__ getCity(String name){
        if(cities.containsValue(name)){
            Collection<City__> city__s = cities.keySet();
            for (City__ city:city__s) {
                Object obj = cities.get(city);
                if (city != null) {
                    if (name.equals(obj)) {
                        return city;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Дорога имеет имя. Дорога всегда при создании имеет два города, между которыми она проведена.
     * Не может быть дорога без городов. Дорога может соединять только два разных города, дорога не может проходить через город и соединять третий город (при таком примере, будет две разных дороги).
     * @param name
     * @param road
     * @return
     */
    public boolean addRoad(String name, Road__ road){
        if (!roads.containsKey(road) && !roads.containsValue(name)
                && name!=null && road.getLinkCity1()!=0 && road.getLinkCity2()!=0) {
            roads.put(road, name);
            return true;
        }
        return false;
    }

    public Road__ getRoad(String name){
        if(roads.containsValue(name)){
            Collection<Road__> road__s = roads.keySet();
            for (Road__ road:road__s) {
                Object obj = roads.get(road);
                if (road != null) {
                    if (name.equals(obj)) {
                        return road;
                    }
                }
            }
        }
        return null;
    }

    public Map<Road__, String> getRoads(final String checkCity){
        final Map<Road__, String> tmpRoads = roads;

        func(tmpRoads.keySet(), new MyPredicate<Road__>() {
            public boolean myEquals(Road__ ro) {
                return (ro.getLinkCity1() == getCity(checkCity).hashCode() || ro.getLinkCity2() == getCity(checkCity).hashCode());
            }
        });

        return tmpRoads;
    }

    public interface MyPredicate<T> {
        public boolean myEquals(T o);
    }
    public <T> void func(Collection collection, MyPredicate<T> predicate){
        if (collection != null && predicate != null){
            for (Iterator<T> iterator = collection.iterator(); iterator.hasNext();)
                if(!predicate.myEquals(iterator.next())){
                    iterator.remove();
                }
        }
    }
}

class City__ {
    private double latitude;
    private double longitude;

    public City__(double latitude, double longitude){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City__)) return false;

        City__ city__ = (City__) o;

        if (Double.compare(city__.latitude, latitude) != 0) return false;
        if (Double.compare(city__.longitude, longitude) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "City__{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
class Road__ {
    private int linkСity1;
    private int linkСity2;

    public Road__(int linkСity1, int linkСity2){
        this.linkСity1 = linkСity1;
        this.linkСity2 = linkСity2;
    }

    public int getLinkCity1() {
        return linkСity1;
    }
    public void setLinkCity1(int linkСity1) {
        this.linkСity1 = linkСity1;
    }
    public int getLinkCity2() {
        return linkСity2;
    }
    public void seLinktCity2(int linkСity2) {
        this.linkСity2 = linkСity2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road__)) return false;

        Road__ road__ = (Road__) o;

        if (linkСity1 != road__.linkСity1) return false;
        if (linkСity2 != road__.linkСity2) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = linkСity1;
        result = 31 * result + linkСity2;
        return result;
    }

    @Override
    public String toString() {
        return "Road__{" +
                ", linkСity1=" + linkСity1 +
                ", linkСity2=" + linkСity2 +
                '}';
    }
}

package example.testtask.some;


import example.testtask.City;
import example.testtask.Items;
import example.testtask.Road;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public abstract class ServiceSome {
    private static final Logger logger = Logger.getLogger(ServiceSome.class);

    private Queue<City> cities;
    private Queue<Road>  roads;
    private ConcurrentMap<KeyCity, City> byNameCities;
    private ConcurrentMap<KeyRoad, Road>  byCityRoads;

    public ServiceSome() {
        cities = new Cities();
        roads  = new Roads();
    }

    public synchronized Items items(){
        indexCities();
        indexRoads();
        return new ItemsHand();
    }

    public synchronized boolean isRoad(City startCity, City finishCity) {
        if (cities.contains(finishCity)) {
            try {
                logger.debug("'" + Dijkstra.getInstance(roads, cities, startCity.getId()-1).search(finishCity.getId())[1][0] + "';");
                return false;
            } catch (NullPointerException npe) {
                return true;
            }
        } else
            return false;
    }

    public abstract boolean addCity(String name, int latitude, int longitude);

    public abstract boolean addRoad(String name, City city1, City city2);

    protected Queue<City> getCities() {
        return cities;
    }

    protected Queue<Road> getRoads() {
        return roads;
    }

    int indexCities() {
        if (byNameCities == null){ byNameCities = new ConcurrentHashMap<KeyCity, City>(cities.size()); }
        for (City city:cities) {
            KeyCity key = new KeyCity(city.getName());
            while (byNameCities.containsKey(key)) { key.incId(); }
            byNameCities.put(key, city);
        }
        return byNameCities.size();
    }

    int indexRoads() {
        if (byCityRoads == null){ byCityRoads = new ConcurrentHashMap<KeyRoad, Road>(roads.size()); }
        for (Road road:roads) {
            KeyRoad key1 = new KeyRoad(road.getCity1().getId());
            while (byCityRoads.containsKey(key1)) { key1.incId(); }
            byCityRoads.put(key1, road);
            KeyRoad key2 = new KeyRoad(road.getCity2().getId());
            while (byCityRoads.containsKey(key2)) { key2.incId(); }
            byCityRoads.put(key2, road);
        }
        return byCityRoads.size();
    }

    private boolean delCities(String name) {
        boolean isDel = false;
        KeyCity key = new KeyCity(name);
        City city = byNameCities.get(key);
        for (Road road : srchRoads(city)) { delRoad(road.getName()); }
        while (byNameCities.containsKey(key)) {
            isDel = delItem(cities, city.getId());
            key.incId();
        }
        return isDel;
    }

    private boolean delRoad(String name) {
        return delItem(roads, new Road(name, null, null));
    }

    private <T> boolean delItem(final Queue<T> items, final T item) {
        if (items==null) return false;
        Iterator<T> iItems = items.iterator();
        while (iItems.hasNext()){
            Road road = (Road) iItems.next();
            if (road.equals(item)) {
                byCityRoads.remove(new KeyRoad(road.getCity1().getId()));
                byCityRoads.remove(new KeyRoad(road.getCity2().getId()));
                iItems.remove();
                return true;
            }
        }
        return false;
    }

    private <T> boolean delItem(final Queue<T> items, final int idItem) {
        if (items==null) return false;
        Iterator<T> iItems = items.iterator();
        for (int id=0; id<items.size(); id++){
            City city = (City) iItems.next();
            if (id==idItem){
                byNameCities.remove(new KeyCity(city.getName()));
                iItems.remove();
                return true;
            }
        }
        return false;
    }

    private Queue<City> srchCities(String name) {
        KeyCity     key = new KeyCity(name);
        Queue<City> copy = new ConcurrentLinkedQueue<City>();
        while (byNameCities.containsKey(key)) {
            copy.add(byNameCities.get(key));
            key.incId();
        }
        return copy;
    }

    private Queue<Road> srchRoads(City city) {
        KeyRoad     key = new KeyRoad(city.getId());
        Queue<Road> copy = new ConcurrentLinkedQueue<Road>();
        while (byCityRoads.containsKey(key)) {
            copy.add(byCityRoads.get(key));
            key.incId();
        }
        return copy;
    }

    private class Cities extends ConcurrentLinkedQueue<City> {
        @Override
        public synchronized boolean add(City city) {
            city.setId(cities.size()+1);
            return super.add(city);
        }
    }

    private class Roads extends ConcurrentLinkedQueue<Road> {
        @Override
        public synchronized boolean add(Road road) {
            road.setId(roads.size());
            road.setWeight(Math.sqrt(Math.abs(road.getCity2().getCoordinate().getX() - road.getCity1().getCoordinate().getX())) + Math.abs(road.getCity2().getCoordinate().getY() - road.getCity1().getCoordinate().getY()));
            return super.add(road);
        }
    }

    private class ItemsHand implements Items {
        @Override
        public Queue<City> searchCities(String name) {
            logger.warn("'" + name + "';");
            if (name==null)
                throw new NullPointerException("City-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("City-Name is Empty");

            return srchCities(name);
        }

        @Override
        public Queue<Road> searchRoads(City city) {
            logger.warn("'" + city + "';");
            if (city==null)
                throw new IllegalArgumentException("City is Null");

            return srchRoads(city);
        }

        @Override
        public boolean deleteCities(String name) {
            logger.warn("'" + name + "';");
            if (name==null)
                throw new NullPointerException("City-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("City-Name is Empty");

            return delCities(name);
        }

        @Override
        public boolean deleteRoad(String name) {
            logger.warn("'" + name + "';");
            if (name==null)
                throw new NullPointerException("Road-Name is Null");
            else if (name.isEmpty())
                throw new IllegalArgumentException("Road-Name is Empty");

            return delRoad(name);
        }
    }

}

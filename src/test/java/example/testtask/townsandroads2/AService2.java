package example.testtask.townsandroads2;

import java.util.HashMap;
import java.util.Map;

public abstract class AService2 {
    private Map<City2Name, Integer> cities;
    private Map<Road2Name, Integer> roads;

    public AService2() {
        cities = new HashMap(1000);
        roads = new HashMap(1000);
//        roads = new TreeMap();
    }

    public Integer addCity(City2Name name, Integer numCity){
        while (cities.containsKey(name)) { name.incId(); }
        return cities.put(name, numCity);
    }

    public Map<City2Name, Integer> getCities(City2Name name) throws CloneNotSupportedException {
        Map<City2Name, Integer> copy = new HashMap();
        while (cities.containsKey(name)) {
            copy.put(name.clone(), cities.get(name));
            name.incId();
        }
        return copy;
    }

    public Integer getFirstCity(City2Name name){
        return cities.get(name);
    }

    public Integer getLastCity(City2Name name) throws CloneNotSupportedException {
        for (Map.Entry<City2Name, Integer> city : getCities(name).entrySet()){
            return city.getValue();
        }
        return null;
    }

    public Integer getCity(City2Name name, byte id){
        name.incId(id);
        return cities.get(name);
    }

    /*
     * (city-1) (number * 1000) + (city-2) number = (road) hashcode
     */
    public Integer addRoad(Road2 road, Integer numRoad){
        return roads.put(new Road2Name(((road.getNumCity1()*1000) + road.getNumCity2())), numRoad);
    }

//    public Integer getRoad(City2Name name){
    public Map<Road2Name, Integer> getRoad(City2Name name){
        return roads;
    }
}

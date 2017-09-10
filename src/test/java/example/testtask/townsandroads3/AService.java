package example.testtask.townsandroads3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Саша on 07.02.2016.
 */
public abstract class AService {
    private Map<CityName, City> cities;
    private Map<RoadName, Road> roads;

    public AService() {
        cities = new HashMap(1000);
        roads  = new HashMap(1000);
    }

    public City addCity(CityName name, City city){
        while (cities.containsKey(name)) { name.incId(); }
        return cities.put(name, city);
    }

    public Map<CityName, City> getCities(CityName name) throws CloneNotSupportedException {
        Map<CityName, City> copy = new HashMap();
        while (cities.containsKey(name)) {
            copy.put(name.clone(), cities.get(name));
            name.incId();
        }
        return copy;
    }

    public Road addRoad(RoadName name, Road road){
        while (roads.containsKey(name)) { name.incId(); }
        return roads.put(name, road);
    }

    public Map<RoadName, Road> getRoads(RoadName name) throws CloneNotSupportedException {
        Map<RoadName, Road> copy = new HashMap();
        while (roads.containsKey(name)) {
            copy.put(name.clone(), roads.get(name));
            name.incId();
        }
        return copy;
    }

    public Road getRoad(RoadName name) {
        return roads.get(name);
    }
}

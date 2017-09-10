package example.testtask.townsandroads;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/20/2016
 * *******************************
 * The implements for 'TownsRoads'
 */
public class Service implements TownsRoads {
    private Map<Town, String> towns;
    private Map<Road, String> roads;

    public Service() {
        towns = new HashMap<Town, String>();
        roads = new HashMap<Road, String>();
    }

    @Override
    public boolean addTown(String name, Town town){
        if (name!=null
                && !towns.containsKey(town)) {
            towns.put(town, name);
            return true;
        }
        return false;
    }

    public Town getTown(String byName){
        if(towns.containsValue(byName)){
            Collection<Town> town__s = towns.keySet();
            for (Town city:town__s) {
                Object obj = towns.get(city);
                if (city != null) {
                    if (byName.equals(obj)) {
                        return city;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean addRoad(String name, Road road){
        if (!roads.containsKey(road) && !roads.containsValue(name)
                && name!=null && road.getHashTown1()!=0 && road.getHashTown2()!=0) {
            roads.put(road, name);
            return true;
        }
        return false;
    }

    @Override
    public boolean addRoad(String name, int hashTown1, int hashTown2) {
        return false;
    }

    @Override
    public boolean addRoad(String name, Town town1, Town town2) {
        return false;
    }

    @Override
    public Map<Road, String> getRoads(int byHashTown) {
        return null;
    }

    @Override
    public Map<Road, String> getRoads(final String byNameTown) {
        filterRoads(roads.keySet(), new Predicate<Road>() {
            @Override
            public boolean filter(Road ro) {
                return (ro.getHashTown1() == getTown(byNameTown).hashCode() || ro.getHashTown2() == getTown(byNameTown).hashCode());
            }
        });
        return roads;
    }

    public interface Predicate<T> {
        public boolean filter(T temp);
    }
    public <T> void filterRoads(final Collection roads, Predicate<T> predicate) {
        if (roads != null && predicate != null) {
            for (Iterator<T> road = roads.iterator(); road.hasNext(); ) {
                if (!predicate.filter(road.next())) {
                    road.remove();
                }
            }
        }
    }
}

package example.testtask.townsandroads;

import java.util.Map;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/20/2016
 */
public interface TownsRoads {
    /**
     * город определяется по имени и координатам, может быть два города с одним именем, но разными координатами
     * @param name
     * @param town
     */
    public boolean addTown(String name, Town town);
//    public Town getTown(String byName);
    /**
     * Дорога имеет имя. Дорога всегда при создании имеет два города, между которыми она проведена.
     * Не может быть дорога без городов. Дорога может соединять только два разных города, дорога не может проходить через город и соединять третий город (при таком примере, будет две разных дороги).
     * @param name
     * @param road
     * @return
     */
    public boolean addRoad(String name, Road road);
    public boolean addRoad(String name, int hashTown1, int hashTown2);
    public boolean addRoad(String name, Town town1, Town town2);
    public Map<Road, String> getRoads(int byHashTown);
    public Map<Road, String> getRoads(String byNameTown);
}

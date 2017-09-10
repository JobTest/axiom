package example.testtask.townsandroads4;

import java.util.List;

public interface IService {
    /**
     * @param name
     * @param latitude
     * @param longitude
     *
     *
     */
    public boolean addCity(String name, int latitude, int longitude);

    /**
     * @param name
     * @param city1
     * @param city2
     */
    public boolean addRoad(String name, City city1, City city2);

    /**
     * @param byName
     * @return
     */
    public List<City> getCities(String byName);

    /**
     * @param byCityId
     * @return
     */
    public List<Road> getRoads(int byCityId);

    /**
     * @param name
     * @return
     */
    public boolean delRoad(String name);

    /**
     * @param byName
     * @return
     */
    public boolean delCity(String byName);
}

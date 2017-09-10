package example.testtask.townsandroads3;

import java.util.Map;

/**
 * Created by Саша on 07.02.2016.
 */
public interface IService {

    /**
     *
     * @param name
     * @param latitude
     * @param longitude
     * @return
     */
    public City addCity(String name, double latitude, double longitude);

    /**
     *
     * @param name
     * @param nameCity1
     * @param nameCity2
     * @return
     */
    public Road addRoad(String name, String nameCity1, String nameCity2);

    /**
     *
     * @param byNameCity
     * @return
     * @throws CloneNotSupportedException
     */
    public Map<CityName, City> getCities(String byNameCity) throws CloneNotSupportedException;

    /**
     *
     * @param byNameCity
     * @return
     * @throws CloneNotSupportedException
     */
    public Map<RoadName, Road> getRoads(String byNameCity) throws CloneNotSupportedException;

    /**
     *
     * @param byNameCity1
     * @param byNameCity2
     * @return
     */
    public Road getRoad(String byNameCity1, String byNameCity2);
}

package example.testtask.cities_roads;

import java.util.List;

public interface Search {

    /**
     * @param name
     * @return
     */
    public List<City> cities(String name);

    /**
     * @param cityId
     * @return
     */
    public List<Road> roads(int cityId);

    /**
     * @param name
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean delCities(String name) throws NullPointerException, IllegalArgumentException;

    /**
     * @param name
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean delRoads(String name) throws NullPointerException, IllegalArgumentException;
}

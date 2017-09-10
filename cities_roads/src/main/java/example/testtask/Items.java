package example.testtask;

import java.util.List;

public interface Items {

    /**
     * @param name
     * @return
     */
    public List<City> searchCities(String name) throws NullPointerException, IllegalArgumentException;

    /**
     * @param city
     * @return
     * @throws IllegalArgumentException
     */
    public List<Road> searchRoads(City city) throws NullPointerException;

    /**
     * @param name
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean deleteCities(String name) throws NullPointerException, IllegalArgumentException;

    /**
     * @param name
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean deleteRoad(String name) throws NullPointerException, IllegalArgumentException;
}

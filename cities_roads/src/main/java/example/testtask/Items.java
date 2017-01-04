package example.testtask;

import java.util.Queue;

public interface Items {

    /**
     * @param name
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Queue<City> searchCities(String name) throws NullPointerException, IllegalArgumentException;

    /**
     * @param city
     * @return
     * @throws IllegalArgumentException
     */
    public Queue<Road> searchRoads(City city) throws IllegalArgumentException;

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

package example.testtask;

import java.util.Queue;

public interface Service {

    /**
     * @return
     */
    public Queue<City> getCities();

    /**
     * @return
     */
    public Queue<Road> getRoads();

    /**
     * @return
     */
    public Items items();

    /**
     * @param name
     * @param latitude
     * @param longitude
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean addCity(String name, int latitude, int longitude) throws NullPointerException, IllegalArgumentException;

    /**
     * @param name
     * @param city1
     * @param city2
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean addRoad(String name, City city1, City city2) throws NullPointerException, IllegalArgumentException;

}

package example.testtask.cities_roads;

import java.awt.*;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/14/2016
 */
public interface Roads {

    /**
     * The city is determined by name and coordinates
     *
     * @param nane
     * @param coordinates
     */
    public void addCity(String nane, Dimension coordinates);

    /**
     * The city is determined by name and coordinates
     *
     * @param nane
     * @param x
     * @param y
     */
    public void addCity(String nane, int x, int y);

    /**
     * The road has a name
     *
     * @param nane
     */
    public void addRoad(String nane);

    /**
     * The road can only connect two different cities
     *
     * @param nane
     * @param city1
     * @param city2
     */
    public void addRoad(String nane, String city1, String city2);

    /**
     * The road is always to create the two cities between which it is carried out
     *
     * @param nane
     * @param city1
     * @param city2
     */
    public void relationsRoad(String nane, String city1, String city2);

    /**
     * Find roads
     *
     * @param city
     */
    public void getRoads(String city);

    /**
     * Delete road
     *
     * @param nane
     */
    public void deleteRoad(String nane);

    /**
     * Delete city
     *
     * @param nane
     */
    public void deleteCity(String nane);

}

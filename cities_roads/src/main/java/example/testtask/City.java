package example.testtask;

import java.awt.*;

public class City {
    private int           id;
    private String      name;
    private Point coordinate;
    private double    weight;

    public City(String name, Point coordinate){
        this.name       = name;
        this.coordinate = coordinate;
    }
    public City(int id, String name, Point coordinate){
        this.id         = id;
        this.name       = name;
        this.coordinate = coordinate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Point getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;
        if (!(that instanceof City))
            return false;
        City city = (City)that;
        if (!coordinate.equals(city.coordinate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "City{" +
                "name=" + name +
                " id=" + id +
                " coordinate=" + coordinate +
                '}';
    }
}

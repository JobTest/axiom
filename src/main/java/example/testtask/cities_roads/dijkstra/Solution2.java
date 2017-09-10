package example.testtask.cities_roads.dijkstra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саша on 26.01.2016.
 */
public class Solution2 {

    List<City> cities = new ArrayList<City>();
    Double roads[][] = new Double[10][3];
    int ROADS = 0;

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.addCity(1.0, 1.0, 1.0);
        solution2.addCity(2.0, 2.0, 2.0);
        solution2.addCity(3.0, 1.0, 4.0);
        solution2.addCity(4.0, 3.0, 9.0);
        solution2.addCity(5.0, 6.0, 1.0);
        solution2.addCity(6.0, 6.0, 7.0);
        solution2.addCity(7.0, 6.0, 14.0);
        solution2.addCity(8.0, 6.0, 5.0);
        solution2.addCity(9.0, 9.0, 2.0);
        solution2.addCity(10.0, 9.0, 4.0);

        solution2.addRoad(solution2.cities.get(0), solution2.cities.get(1));
        solution2.addRoad(solution2.cities.get(1), solution2.cities.get(2));
        solution2.addRoad(solution2.cities.get(2), solution2.cities.get(3));
        solution2.addRoad(solution2.cities.get(3), solution2.cities.get(4));
        solution2.addRoad(solution2.cities.get(4), solution2.cities.get(5));
        solution2.addRoad(solution2.cities.get(5), solution2.cities.get(6));

        solution2.outRoads();
    }

    void addCity(Double num, Double latitude, Double longitude){
        cities.add(new City(num, latitude, longitude));
    }
    void addRoad(City city1, City city2){
//        synchronized (this){
            roads[ROADS][0] = city1.num;
            roads[ROADS][1] = city2.num;
            roads[ROADS][2] = Math.sqrt(Math.abs(city2.latitude-city1.latitude)+Math.abs(city2.longitude-city1.longitude));
            ROADS++;
//        }
    }

    void outRoads(){
        for (int road=0; road<ROADS; road++){
            System.out.println(roads[road][0]+" > "+roads[road][1]+"  ["+roads[road][2]+"]");
        }
    }
}

class City {
    Double num;
    Double latitude;
    Double longitude;

    public City(){}
    public City(Double num, Double latitude, Double longitude){
        this.num = num;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
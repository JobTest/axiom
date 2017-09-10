package example.testtask.cities_roads.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lazarchuk A.
 * *******************
 * Алгоритм Дейкстры
 ** {@link http://cybern.ru/algoritm-dejkstry-realizaciya-na-java.html}
 *  {@link https://javatalks.ru/topics/13454?page=2}
 *
 *  {@link http://rain.ifmo.ru/cat/view.php/theory/graph-spanning-trees/mst-2005}
 * * * * * * * * * * *
 * Когда я добавляю города - город имеет координаты И уже по этим координатам я могу построить вершины гравфа, а также вычислить расстояние между этими вершинами графа (городами)...
 */
public class Solution3 {

    List<City2>                cities   = new ArrayList<City2>(); // количество вершин в графе
    String[][]                  roads   = new String[20][3];
    int                         ROADS   = 0;
    int                    city_start   = 2;                      // стартовая вершина, от которой ищется расстояние до всех других
    int                   city_finish   = 6;
    private static int            INF   = 1000000;
    private boolean              used[];                          // массив для хранения информации о пройденных и не пройденных вершинах
    private int                  pred[];                          // массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int                  dist[];                          // массив для хранения расстояния от стартовой вершины
    private ArrayList<Integer>    adj[];                          // список смежности
    private ArrayList<Double>  weight[];                          // вес ребра в графе


    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();

        solution3.addCity("1", 1.0, 1.0);
        solution3.addCity("2", 2.0, 2.0);
        solution3.addCity("3", 1.0, 4.0);
        solution3.addCity("4", 3.0, 9.0);
        solution3.addCity("5", 6.0, 1.0);
        solution3.addCity("6", 6.0, 7.0);
        solution3.addCity("7", 6.0, 14.0);
        solution3.addCity("8", 6.0, 5.0);
        solution3.addCity("9", 9.0, 2.0);
        solution3.addCity("10", 19.0, 4.0);
        solution3.addCity("11", 16.0, 14.0);
        solution3.addCity("12", 16.0, 5.0);
        solution3.addCity("13", 19.0, 2.0);
        solution3.addCity("14", 19.0, 4.0);
        solution3.addRoad(solution3.cities.get(0), solution3.cities.get(1));
        solution3.addRoad(solution3.cities.get(1), solution3.cities.get(2));
        solution3.addRoad(solution3.cities.get(2), solution3.cities.get(3));
        solution3.addRoad(solution3.cities.get(3), solution3.cities.get(4));
        solution3.addRoad(solution3.cities.get(4), solution3.cities.get(5));
        solution3.addRoad(solution3.cities.get(5), solution3.cities.get(6));

        solution3.dejkstra(solution3.city_start);
        solution3.printData();
        System.out.println();
        solution3.printData(solution3.city_start, solution3.city_finish);
    }


    void addCity(String name, Double latitude, Double longitude){
        cities.add(new City2(name, latitude, longitude));
    }

    void addRoad(City2 city1, City2 city2){
        if(isRoad(cities, city1, city2)){
            roads[ROADS][0] = city1.name;
            roads[ROADS][1] = city2.name;
            roads[ROADS][2] = String.valueOf( Math.sqrt(Math.abs(city2.latitude-city1.latitude)+Math.abs(city2.longitude-city1.longitude)) );
            ROADS++;
            addRoad(roads);
        }
    }
    boolean isRoad(List<City2> cities, City2 city1, City2 city2){
        for (City2 city:cities) {
            double deltaX2 = 0, deltaY2 = 0, deltaX3 = 0, deltaY3 = 0;
            if(city1.latitude.equals(city.latitude) && city2.latitude.equals(city.latitude)
                    && city1.longitude.equals(city.longitude) && city2.longitude.equals(city.longitude)){
    //                System.out.println("YES");
                deltaX2 = city2.latitude-city1.latitude;
                deltaY2 = city2.longitude-city1.longitude;
                deltaX3 = city.latitude-city1.latitude;
                deltaY3 = city.longitude-city1.longitude;
    //                System.out.println("("+deltaX2+"/"+deltaY2+")" + (deltaX2/deltaY2) + " == " + "("+deltaX3+"/"+deltaY3+")" + (deltaX3/deltaY3));
                if(deltaX2==0 && deltaX3==0){
                    return false;
                } else if(deltaY2==0 && deltaY3==0){
                    return false;
                } else if((deltaX2/deltaY2) == (deltaX3/deltaY3)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * процедура считывания входных данных с консоли
     */
    private void addRoad(String[][] roads) {
        used   = new boolean[cities.size()];
        pred   = new int[cities.size()];
        dist   = new int[cities.size()];
        adj    = new ArrayList[cities.size()];
        weight = new ArrayList[cities.size()];

        // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
        for (int i=0; i<=city_start; i++) {
            for (int a=0; a<ROADS; a++) {
                if(roads[a][1].equals(String.valueOf(i))){
                    roads[a][0] = String.valueOf(Integer.valueOf(roads[a][0]) + Integer.valueOf(roads[a][1]));
                    roads[a][1] = String.valueOf(Integer.valueOf(roads[a][0]) - Integer.valueOf(roads[a][1]));
                    roads[a][0] = String.valueOf(Integer.valueOf(roads[a][0]) - Integer.valueOf(roads[a][1]));
                }
            }
        }

        // инициализируем списка смежности графа размерности n
        // инициализация списка, в котором хранятся веса ребер
        for (int i=0; i<cities.size(); ++i) {
            adj[i]    = new ArrayList<Integer>();
            weight[i] = new ArrayList<Double>();
        }

        // считываем граф, заданный списком ребер
        for (int i=0; i<ROADS; ++i) {
            int     u = Integer.valueOf(roads[i][0]);
            int     v = Integer.valueOf(roads[i][1]);
            Double  w = Double.valueOf(roads[i][2]);
            u--;
            v--;
            adj[u].add(v);
            weight[u].add( Double.valueOf(w) );
        }

        Arrays.fill(used, false);
        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
    }


    /**
     * процедура запуска алгоритма Дейкстры из стартовой вершины
     * @param start
     */
    private void dejkstra(int start) {
        int s = start - 1;
        dist[s] = 0; // кратчайшее расстояние до стартовой вершины равно 0
        for (int iter=0; iter<cities.size(); ++iter) {
            int     v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int i=0; i<cities.size(); ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < dist[i]) {
                    continue;
                }
                v     = i;
                distV = dist[i];
            }
            // рассматриваем все дуги, исходящие из найденной вершины
            for (int i=0; i<adj[v].size(); ++i) {
                int u = adj[v].get(i);
                int weightU = weight[v].get(i).intValue();
                // релаксация вершины
                if (dist[v]+weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                    pred[u] = v;
                }
            }
            // помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
    }

    /**
     * процедура восстановления кратчайшего пути по массиву предком
     * @param v
     */
    void printWay(int v) {
        if (v == -1) {
            return;
        }
        printWay(pred[v]);
        System.out.print((v + 1) + " ");
    }

    /**
     * процедура вывода данных в консоль
     */
    private void printData() {
        System.out.print("Дистанция: ");
        for (int v=0; v<cities.size(); ++v) {
            if (dist[v] != INF) {
                System.out.print("(" + city_start + "-" + (v+1) + ")" + dist[v] + " ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int v=0; v<cities.size(); ++v) {
            System.out.print("Вершина " + city_start + "-" + (v+1) + ": ");
            if (dist[v] != INF) {
                printWay(v);
            }
            System.out.println();
        }
    }
    /**
     * процедура вывода данных в консоль
     */
    private void printData(int first, int last) {
        System.out.print("Дистанция: ");
        for (int v=0; v<cities.size(); ++v) {
            if (dist[v] != INF) {
                if (first==city_start && last==(v+1)) {
                    System.out.print("(" + city_start + "-" + (v + 1) + ")" + dist[v] + " ");
                }
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int v=0; v<cities.size(); ++v) {
            if (first==city_start && last==(v+1)) {
                System.out.print("Вершина " + city_start + "-" + (v+1) + ": ");
                if (dist[v] != INF) {
                    printWay(v);
                }
                System.out.println();
            }
        }
    }
}

class City2 {
    String name;
    Double latitude;
    Double longitude;

    public City2(){}
    public City2(String name, Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
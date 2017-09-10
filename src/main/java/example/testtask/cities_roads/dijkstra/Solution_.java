package example.testtask.cities_roads.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Lazarchuk A.
 * *******************
 * Алгоритм Дейкстры
 ** {@link http://cybern.ru/algoritm-dejkstry-realizaciya-na-java.html}
 *  {@link https://javatalks.ru/topics/13454?page=2}
 *
 *  {@link http://rain.ifmo.ru/cat/view.php/theory/graph-spanning-trees/mst-2005}
 *  {@link http://www.cleverstudents.ru/vectors/vector_length.html}
 * * * * * * * * * * *
 * Когда я добавляю города - город имеет координаты И уже по этим координатам я могу построить вершины гравфа, а также вычислить расстояние между этими вершинами графа (городами)...
 */
public class Solution_ {

    private int                  cities;           // количество вершин в графе
    private int              city_start;           // стартовая вершина, от которой ищется расстояние до всех других
    private final int             EMPTY = 1000000;
    private boolean[]              used;           // массив для хранения информации о пройденных и не пройденных вершинах
    private int[]                  pred;           // массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int[]                  dist;           // массив для хранения расстояния от стартовой вершины
    private ArrayList<Integer>[]    adj;           // список смежности
    private ArrayList<Integer>[] weight;           // вес ребра в орграфе


    public static void main(String[] args) {
//        int      cities = 10;
        ArrayList<City_> cities = new ArrayList<City_>();
        cities.add(new City_("1",10.0,10.0));
        cities.add(new City_("2",40.0,100.0));
        cities.add(new City_("3",80.0,50.0));
        cities.add(new City_("4",100.0,100.0));
        cities.add(new City_("5",200.0,200.0));
        cities.add(new City_("6",250.0,130.0));
        cities.add(new City_("7",300.0,250.0));
        cities.add(new City_("8",180.0,120.0));
        cities.add(new City_("9",210.0,90.0));

        int  city_start = 2;
        int city_finish = 6;
//        int[][]   roads = new int[][]{{1,8,100},{1,2,100},{2,7,100},{2,3,100},{3,6,100},{3,4,100},{4,5,100},{8,9,100},{8,7,100},{7,10,100},{7,6,100},{6,11,100},{6,5,100},{5,12,100}};
//        int[][]   roads = new int[][]{{1,2,150},{1,6,150},{1,4,310},{4,5,150},{5,2,200},{2,3,100},{3,4,150},{3,6,200},{5,6,100}};
//        ArrayList<Road> roads = new ArrayList<Road>();
//        roads.add(new Road("1","2",150.0));
//        roads.add(new Road("1","6",150.0));
//        roads.add(new Road("1","4",310.0));
//        roads.add(new Road("4","5",150.0));
//        roads.add(new Road("5","2",200.0));
//        roads.add(new Road("2","3",100.0));
//        roads.add(new Road("3","4",150.0));
//        roads.add(new Road("3","6",200.0));
//        roads.add(new Road("5","6",100.0));
        ArrayList<Road_> roads = new ArrayList<Road_>();
        roads.add(new Road_(cities.get(0),cities.get(1)));
        roads.add(new Road_(cities.get(0),cities.get(5)));
        roads.add(new Road_(cities.get(0),cities.get(3)));
        roads.add(new Road_(cities.get(3),cities.get(4)));
        roads.add(new Road_(cities.get(4),cities.get(1)));
        roads.add(new Road_(cities.get(1),cities.get(2)));
        roads.add(new Road_(cities.get(2),cities.get(3)));
        roads.add(new Road_(cities.get(2),cities.get(5)));
        roads.add(new Road_(cities.get(4),cities.get(5)));

        Solution_ solution = new Solution_();

        solution.addCities(cities.size(), city_start);
        solution.addRoads(roads);
        solution.findWay();

        solution.printData();
        System.out.println();
        solution.printData(city_finish);
    }


    /**
     * добавить города
     * @param cities
     * @param city_start
     */
    public void addCities(int cities, int city_start){
        this.city_start = city_start;
        this.cities     = cities;
        used            = new boolean[this.cities];
        pred            = new int[this.cities];
        dist            = new int[this.cities];
        // инициализируем списка смежности графа размерности n
        // инициализация списка, в котором хранятся веса ребер
        adj             = new ArrayList[this.cities];
        weight          = new ArrayList[this.cities];
        for (int city=0; city<this.cities; city++) {
            adj[city]    = new ArrayList<Integer>();
            weight[city] = new ArrayList<Integer>();
        }
    }

    /**
     * проложить дорогу(и) между городами
     * @param roads
     */
    public void addRoads(final ArrayList<Road_> roads) { //public void addRoads(final ArrayList<Road> roads) { //public void addRoads(final int[][] roads) {
        // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
        // считываем граф, заданный списком ребер
//        for (int road=0; road<roads.length; road++) {
//            if(roads[road][1]<=city_start){
//                roads[road][0] = roads[road][0]+roads[road][1];
//                roads[road][1] = roads[road][0]-roads[road][1];
//                roads[road][0] = roads[road][0]-roads[road][1];
//            }
//            adj[--roads[road][0]].add(--roads[road][1]);
//            weight[roads[road][0]].add(roads[road][2]);
//        }
//
//        for (Road road:roads) {
//            if(Integer.valueOf(road.city2)<=city_start){
//                String city = road.city2;
//                road.city2  = road.city1;
//                road.city1  = city;
//            }
//            adj[Integer.valueOf(road.city1=String.valueOf(Integer.valueOf(road.city1)-1))].add(Integer.valueOf(road.city2=String.valueOf(Integer.valueOf(road.city2)-1)));
//            weight[Integer.valueOf(road.city1)].add(road.distance.intValue());
//        }
        int count = 1;
        for (Road_ road:roads) {
            if(Integer.valueOf(road.city2.name)<=city_start){
                City_ city = road.city2;
                road.city2 = road.city1;
                road.city1 = city;
            }
            System.out.print("[road = " + (count++) + "] city1(" + road.city1.name + ") city2(" + road.city2.name + ")");
            road.city1.name = String.valueOf(Integer.valueOf(road.city1.name)-1);
            road.city2.name = String.valueOf(Integer.valueOf(road.city2.name)-1);
            System.out.println(" >> city1(" + road.city1.name + ") city2(" + road.city2.name + ")");
            adj[Integer.valueOf(road.city1.name)].add(Integer.valueOf(road.city2.name));
            weight[Integer.valueOf(road.city1.name)].add(road.getDistance().intValue());
        }

        Arrays.fill(used, false);
        Arrays.fill(pred, -1);
        Arrays.fill(dist, EMPTY);
    }

    /**
     * процедура запуска алгоритма Дейкстры из стартовой вершины
     */
    private void findWay() {
        dist[city_start-1] = 0; // кратчайшее расстояние до стартовой вершины равно 0
        for (int city_all=cities; 0<city_all--;) {
            int     v = -1;
            int distV = EMPTY;
            // выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int city=0; city<cities; city++) {
                if (used[city]) continue;
                if (distV < dist[city]) continue;
                v     = city;
                distV = dist[city];
            }
            // рассматриваем все дуги, исходящие из найденной вершины
            for (int city=0; city<adj[v].size(); city++) {
                int u = adj[v].get(city);
                int weightU = weight[v].get(city);
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
        if (v==-1) return;
        printWay(pred[v]);
        System.out.print((v + 1) + " ");
    }

    /**
     * процедура вывода данных в консоль
     */
    private void printData() {
        System.out.print("Дистанция: ");
        for (int v=0; v<cities; ++v) {
            if (dist[v] != EMPTY) {
                System.out.print("(" + city_start + "-" + (v+1) + ")" + dist[v] + " ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int v=0; v<cities; ++v) {
            System.out.print("Вершина " + city_start + "-" + (v+1) + ": ");
            if (dist[v] != EMPTY) {
                printWay(v);
            }
            System.out.println();
        }
    }
    /**
     * процедура вывода данных в консоль
     */
    private void printData(int last) {
        System.out.print("Дистанция: ");
        for (int v=0; v<cities; ++v) {
            if (dist[v] != EMPTY) {
                if (last==(v+1)) {
                    System.out.print("(" + city_start + "-" + (v + 1) + ")" + dist[v] + " ");
                }
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int v=0; v<cities; ++v) {
            if (last==(v+1)) {
                System.out.print("Вершина " + city_start + "-" + (v+1) + ": ");
                if (dist[v] != EMPTY) {
                    printWay(v);
                }
                System.out.println();
            }
        }
    }
}

class Road {
    String city1;
    String city2;
    Double distance;
    public Road(String city1, String city2, Double distance){
        this.city1    = city1;
        this.city2    = city2;
        this.distance = distance;
    }
}
class Road_ {
    City_ city1;
    City_ city2;
    public Road_(City_ city1, City_ city2){
        this.city1 = city1;
        this.city2 = city2;
    }
    Double getDistance(){
        return Math.sqrt(Math.pow(city1.latitude * city2.latitude, 2) + Math.pow(city1.longitude * city2.longitude, 2));
    }
}

class City_ {
    String name;
    Double latitude;
    Double longitude;
    public City_(String name, Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
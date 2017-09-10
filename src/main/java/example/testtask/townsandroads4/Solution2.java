package example.testtask.townsandroads4;

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
public class Solution2 {

    List<City2>            cities; // количество вершин в графе
    List<Road2>             roads;
    private int      cityId_start; // стартовая вершина, от которой ищется расстояние до всех других
    private static final int  INF = 1000000;
    private boolean[]        used; // массив для хранения информации о пройденных и не пройденных вершинах
    private int[]            pred; // массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int[]            dist; // массив для хранения расстояния от стартовой вершины
    private List<Integer>[]   adj; // список смежности
    private List<Double>[] weight; // вес ребра в графе

    public Solution2(List<City2> cities, List<Road2> roads, int cityId_start){
        this.cities = cities;
        this.roads = roads;
        init(cityId_start);
    }

    public static void main(String[] args) {
        List<City2> cities = new ArrayList();
        List<Road2> roads = new ArrayList();
        cities.add(new City2(0, "City-1", 1.0, 1.0));
        cities.add(new City2(1, "City-2", 2.0, 2.0));
        cities.add(new City2(2, "City-3", 1.0, 4.0));
        cities.add(new City2(3, "City-4", 3.0, 9.0));
        cities.add(new City2(4, "City-5", 6.0, 1.0));
        cities.add(new City2(5, "City-6", 6.0, 7.0));
        cities.add(new City2(6, "City-7", 6.0, 14.0));
        cities.add(new City2(7, "City-8", 6.0, 5.0));
        cities.add(new City2(8, "City-9", 9.0, 2.0));
        cities.add(new City2(9, "City-10", 19.0, 4.0));
        cities.add(new City2(10, "City-11", 16.0, 14.0));
        cities.add(new City2(11, "City-12", 16.0, 5.0));
        cities.add(new City2(12, "City-13", 19.0, 2.0));
        cities.add(new City2(13, "City-14", 19.0, 4.0));
        roads.add(new Road2("Road-1", cities.get(0), cities.get(1)));
        roads.add(new Road2("Road-2", cities.get(1), cities.get(2)));
        roads.add(new Road2("Road-3", cities.get(2), cities.get(3)));
        roads.add(new Road2("Road-4", cities.get(3), cities.get(4)));
        roads.add(new Road2("Road-5", cities.get(4), cities.get(5)));
        roads.add(new Road2("Road-6", cities.get(5), cities.get(6)));

//        cities.add(new City2(0, "City-1", 1.0, 2.0));
//        cities.add(new City2(1, "City-2", 2.0, 3.0));
//        cities.add(new City2(2, "City-3", 3.0, 3.0));
//        cities.add(new City2(3, "City-4", 4.0, 2.0));
//        cities.add(new City2(4, "City-5", 3.0, 1.0));
//        cities.add(new City2(5, "City-6", 2.0, 1.0));
//        roads.add(new Road2("Road-1", cities.get(0), cities.get(1)));
//        roads.add(new Road2("Road-2", cities.get(1), cities.get(2)));
//        roads.add(new Road2("Road-3", cities.get(1), cities.get(3)));
//        roads.add(new Road2("Road-4", cities.get(1), cities.get(4)));
//        roads.add(new Road2("Road-5", cities.get(1), cities.get(5)));
//        roads.add(new Road2("Road-6", cities.get(2), cities.get(3)));
//        roads.add(new Road2("Road-6", cities.get(2), cities.get(4)));
//        roads.add(new Road2("Road-6", cities.get(3), cities.get(5)));

        Solution2 solution = new Solution2(cities, roads, 3);

        System.out.println("-----------------------[ dejkstra ]");
        solution.indexes();
        System.out.println("-----------------------[ printData ]");
        solution.printData();
        System.out.println();
        System.out.println("-----------------------[ printData(2, 5) ]");
        solution.printData(3, 5);
        solution.printData(3, 4);
        solution.printData(3, 3);
    }

    /**
     * процедура считывания входных данных с консоли
     * @param cityId_start
     */
    private void init(int cityId_start) {
        this.cityId_start = cityId_start;
        used              = new boolean[cities.size()];
        pred              = new int[cities.size()];
        dist              = new int[cities.size()];
        adj               = new ArrayList[cities.size()];
        weight            = new ArrayList[cities.size()];

        // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
        for (int id=0; id<=cityId_start; id++) {
            for (Road2 road:roads) {
                road.weight = Math.sqrt(Math.abs(road.city2.latitude - road.city1.latitude) + Math.abs(road.city2.longitude - road.city1.longitude));
                if(road.city2.id==id){
                    City2 tmp = road.city1;
                    road.city1 = road.city2;
                    road.city2 = tmp;
                }
            }
        }

        // инициализируем списка смежности графа размерности n
        // инициализация списка, в котором хранятся веса ребер
        for (City2 city:cities) {
            adj[cities.indexOf(city)]    = new ArrayList<Integer>();
            weight[cities.indexOf(city)] = new ArrayList<Double>();
        }

        // считываем граф, заданный списком ребер
        for (Road2 road:roads) {
            adj[road.city1.id].add(road.city2.id);
            weight[road.city1.id].add(road.weight);
        }

        Arrays.fill(used, false);
        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
    }


    /**
     * процедура запуска алгоритма Дейкстры из стартовой вершины
     */
    public void indexes() {
        dist[cityId_start] = 0; // кратчайшее расстояние до стартовой вершины равно 0
        for (int iter=0; iter<cities.size(); iter++) {
            int     v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (City2 city:cities) {
                if (used[cities.indexOf(city)])
                    continue;
                if (distV < dist[cities.indexOf(city)])
                    continue;
                v     = cities.indexOf(city);
                distV = dist[cities.indexOf(city)];
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
    private void printWay(int v) {
        if (v==-1)
            return;
        printWay(pred[v]);
        System.out.print((v) + " ");
    }

    /**
     * процедура вывода данных в консоль
     */
    public void printData() {
        for (int v=0; v<cities.size(); v++) {
            System.out.print("Вершина " + cityId_start + "-" + v + ": ");
            if (dist[v] != INF) {
                printWay(v);
            }
            System.out.println();
        }
        System.out.print("Дистанция: ");
        for (int v=0; v<cities.size(); ++v) {
            if (dist[v] != INF) {
                System.out.print("(" + cityId_start + "-" + v + ")" + dist[v] + " ");
            } else {
                System.out.print("- ");
            }
        }
    }
    /**
     * процедура вывода данных в консоль
     */
    public void printData(int first, int last) {
        init(first);
        indexes();
//        for (int v=0; v<cities.size(); v++) {
//            if (first<=v && v<=last){
//                System.out.print("Вершина " + cityId_start + "-" + v + ": ");
//                if (dist[v] != INF) {
//                    printWay(v);
//                }
//                System.out.println();
//            }
//        }
        System.out.print("Дистанция: ( ");
        for (int v=0; v<cities.size(); v++) {
            if (v==last){
                if (dist[v] != INF) {
                    printWay(v);
                }
                System.out.println(") " + wayToCities(v) + "-cities / " + sumWay(v) + "km.");
            }
        }
    }

    private int sumWay(int v) {
        return sumWay(v, 0);
    }
    private int sumWay(int v, int sum) {
        return (v==-1) ? sum : dist[v] + sumWay(pred[v], dist[v]);
    }

    private int wayToCities(int v) {
        return wayToCities(v, 0);
    }
    private int wayToCities(int v, int count) {
        return (v==-1) ? count : wayToCities(pred[v], count+1);
    }





}

class City2 {
    int id;
    String name;
    Double latitude;
    Double longitude;

    public City2(int id, String name, Double latitude, Double longitude){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
class Road2 {
    String name;
    City2 city1;
    City2 city2;
    double weight;

    public Road2(String name, City2 city1, City2 city2){
        this.name = name;
        this.city1 = city1;
        this.city2 = city2;
        this.weight = weight;
    }
    public Road2(String name, City2 city1, City2 city2, double weight){
        this.name = name;
        this.city1 = city1;
        this.city2 = city2;
        this.weight = weight;
    }
}

package example.testtask.some;

import example.testtask.City;
import example.testtask.Road;
import org.apache.log4j.Logger;

import java.util.*;

public class Dijkstra {
    public static final int MAX_DISTANCE = 1000000;
    private static final Logger logger = Logger.getLogger(Dijkstra.class);
    private int          maxCities;
    private int[]       distancies;
    private int[] parentDistancies;

    private Dijkstra(){
    }

    private Dijkstra(Queue<Road> roads, Queue<City> cities, int startCity) {
        maxCities        = cities.size();
        distancies       = new int[maxCities];
        parentDistancies = new int[maxCities];

        logger.warn("'maxCities=" + maxCities + "'; " + "'startCity=" + startCity + "';");
        exec( convert(roads, startCity) );
    }

    public static Dijkstra getInstance(Queue<Road> roads, Queue<City> cities, int startCity) {
        return new Dijkstra(roads, cities, startCity);
    }

    void exec(List<Integer>[][] dataConvert) {
        boolean[] useds = new boolean[maxCities];
        Arrays.fill(useds, false);

        for (int iter=0; iter<maxCities; ++iter) {
            int v     = -1;
            int distV = MAX_DISTANCE;

            for (int i=0; i<maxCities; ++i) {
                if (useds[i])
                    continue;
                if (distV<distancies[i])
                    continue;
                v     = i;
                distV = distancies[i];
            }

            for (int i=0; i<dataConvert[0][v].size(); ++i) {
                int u       = dataConvert[0][v].get(i);
                int weightU = dataConvert[1][v].get(i);
                if ((distancies[v]+weightU) < distancies[u]) {
                    distancies[u]       = distancies[v] + weightU;
                    parentDistancies[u] = v;
                }
            }

            useds[v] = true;
        }
    }

    int[] getDistancies() {
        int[] arr = new int[0];
        for (int v=0; v<maxCities; ++v) {
            int[] _arr = arr;
            arr = new int[_arr.length+1];
            for (int a=0; a<_arr.length; ++a) arr[a] = _arr[a];
            if (distancies[v]!=MAX_DISTANCE)
                arr[_arr.length] = distancies[v];
            else
                arr[_arr.length] = -1;
        }
        return arr;
    }

    int[][] getWays(){
        int[][] arr = new int[0][];
        for (int v=0; v<maxCities; ++v) {
            int[][] _arr = arr;
            if (distancies[v] != MAX_DISTANCE) {
                arr = new int[_arr.length+1][];
                for (int a=0; a<_arr.length; ++a) arr[a] = _arr[a];
                arr[_arr.length] = getWay(v, new int[0]);
            }
        }
        return arr;
    }

    int[][] search(int finish) throws NullPointerException {
        logger.warn("'finish=" + finish + "';");
        for (int v=0; v<maxCities; ++v) {
            if (distancies[v]!=MAX_DISTANCE) {
                if (finish==(v+1)) {
                    int[][] data = new int[2][1];
                    data[0]    = getWay(v, new int[0]);
                    data[1][0] = distancies[v];
                    return data;
                }
            }
        }
        return null;
    }

    private int[] getWay(int val, int[] arr) {
        if (val==-1) return arr;
        int[] newArr = new int[arr.length+1];
        newArr[0] = val + 1;
        for (int a=arr.length; 0<a; --a) newArr[a] = arr[a-1];
        return getWay(parentDistancies[val], newArr);
    }

    private List<Integer>[][] convert(Queue<Road> items, int start) {
        List<Integer>[][] arrDijkstra = new ArrayList[2][];

        arrDijkstra[0] = new ArrayList[maxCities];
        arrDijkstra[1] = new ArrayList[maxCities];
        for (int i=0; i<maxCities; ++i) {
            arrDijkstra[0][i] = new ArrayList<Integer>();
            arrDijkstra[1][i] = new ArrayList<Integer>();
        }

        Arrays.fill(parentDistancies, -1);
        Arrays.fill(distancies, MAX_DISTANCE);
        distancies[start] = 0;

        int[][] arrRoads = new int[items.size()][3];
        for (int i=0; i<=(start+1); i++) {
            Iterator<Road> iRoads = items.iterator();
            for (int r=0; iRoads.hasNext(); r++) {
                Road road = iRoads.next();
                if (road.getCity2().getId()==i) {
                    arrRoads[r][0] = road.getCity2().getId();
                    arrRoads[r][1] = road.getCity1().getId();
                    arrRoads[r][2] = (int)road.getWeight();
                } else if (arrRoads[r][0]!=road.getCity2().getId()) {
                    arrRoads[r][0] = road.getCity1().getId();
                    arrRoads[r][1] = road.getCity2().getId();
                    arrRoads[r][2] = (int)road.getWeight();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] arr : arrRoads) sb.append("[ " + arr[0] + "," + arr[1] + "," + arr[2] + " ]");
        logger.debug(sb);

        for (int i=0; i<arrRoads.length; ++i) {
            int u = arrRoads[i][0];
            int v = arrRoads[i][1];
            int w = arrRoads[i][2];
            u--;
            v--;
            arrDijkstra[0][u].add(v);
            arrDijkstra[1][u].add(w);
        }

        return arrDijkstra;
    }

}

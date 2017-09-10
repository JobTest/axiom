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
 * * * * * * * * * * *
 * Когда я добавляю города - город имеет координаты И уже по этим координатам я могу построить вершины гравфа, а также вычислить расстояние между этими вершинами графа (городами)...
 */
public class Solution {

    private int                     n   = 20;                // количество вершин в графе
//    int[][]                       AAA   = {{1,2,150},{2,3,100},{3,4,150},{4,5,150},{5,6,100},{1,6,150},  {1,3,200},{1,4,310},{1,5,200},  {2,4,200},{2,5,200},{2,6,190},  {3,5,190},{3,6,200},  {4,6, 200}};
//    int[][]                       AAA   = {{1,2,150},{5,6,100},  {2,3,100},{2,4,200},{2,5,200},{2,6,190},  {3,4,150},{3,5,190},  {4,5,150},{4,6,200}};

//    int[][]                       AAA   = {{1,4,310},{4,5,150},{5,2,200},{2,3,100},{3,6,200}};
//    int[][]                       AAA   = {{1,2,150},{1,6,150},{1,4,310},{4,5,150},{5,2,200},{2,3,100},{3,4,150},{3,6,200},{5,6,100}};
//    int[][]                       AAA   = {{1,2,150},{1,4,310},{4,5,150},{5,2,200},{2,3,100},{3,4,150},{3,6,200},{5,6,100}};

    int[][]                       AAA   = {{1,8,100},{1,2,100},{2,7,100},{2,3,100},{3,6,100},{3,4,100},{4,5,100},{8,9,100},{8,7,100},{7,10,100},{7,6,100},{6,11,100},{6,5,100},{5,12,100}};
    private int                     m   = AAA.length;       // количествое дуг в графе
    int                         start   = 2;                // стартовая вершина, от которой ищется расстояние до всех других
    private static int            INF   = 1000000;
    private boolean              used[] = new boolean[n];   // массив для хранения информации о пройденных и не пройденных вершинах
    private int                  pred[] = new int[n];       // массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int                  dist[] = new int[n];       // массив для хранения расстояния от стартовой вершины
    private ArrayList<Integer>    adj[] = new ArrayList[n]; // список смежности
    private ArrayList<Integer> weight[] = new ArrayList[n]; // вес ребра в орграфе

    /**
     * процедура считывания входных данных с консоли
     */
    public Solution() {
        // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
        for (int i=0; i<=start; i++) {
            for (int a=0; a<AAA.length; a++) {
                if(AAA[a][1]==i){
                    AAA[a][0] = AAA[a][0]+AAA[a][1];
                    AAA[a][1] = AAA[a][0]-AAA[a][1];
                    AAA[a][0] = AAA[a][0]-AAA[a][1];
                }
            }
        }

        // инициализируем списка смежности графа размерности n
        // инициализация списка, в котором хранятся веса ребер
        for (int i=0; i<n; ++i) {
            adj[i]    = new ArrayList<Integer>();
            weight[i] = new ArrayList<Integer>();
        }

        // считываем граф, заданный списком ребер
        for (int i=0; i<m; ++i) {
            int     u = AAA[i][0];
            int     v = AAA[i][1];
            int     w = AAA[i][2];
            u--;
            v--;
            adj[u].add(v);
            weight[u].add(w);
        }

        Arrays.fill(used, false);
        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
    }
    /**
     * процедура считывания входных данных с консоли
     */
    public Solution(int[][] aaa) {
        AAA = aaa;
        // поскольку о отрицательную сторону алгоритм дейкстры НЕработает, поэтому переворачиваем местами вершины (внутри массива) в такой способ чтобы проход выполнялся только в прямом порядке
        for (int i=0; i<=start; i++) {
            for (int a=0; a<AAA.length; a++) {
                if(AAA[a][1]==i){
                    AAA[a][0] = AAA[a][0]+AAA[a][1];
                    AAA[a][1] = AAA[a][0]-AAA[a][1];
                    AAA[a][0] = AAA[a][0]-AAA[a][1];
                }
            }
        }

        // инициализируем списка смежности графа размерности n
        // инициализация списка, в котором хранятся веса ребер
        for (int i=0; i<n; ++i) {
            adj[i]    = new ArrayList<Integer>();
            weight[i] = new ArrayList<Integer>();
        }

        // считываем граф, заданный списком ребер
        for (int i=0; i<m; ++i) {
            int     u = AAA[i][0];
            int     v = AAA[i][1];
            int     w = AAA[i][2];
            u--;
            v--;
            adj[u].add(v);
            weight[u].add(w);
        }

        Arrays.fill(used, false);
        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.dejkstra(solution.start);
        solution.printData();
        System.out.println();
        solution.printData(solution.start,6);
    }

    /**
     * процедура запуска алгоритма Дейкстры из стартовой вершины
     * @param start
     */
    private void dejkstra(int start) {
        int s = start - 1;
        dist[s] = 0; // кратчайшее расстояние до стартовой вершины равно 0
        for (int iter=0; iter<n; ++iter) {
            int     v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int i=0; i<n; ++i) {
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
                int weightU = weight[v].get(i);
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
        for (int v=0; v<n; ++v) {
            if (dist[v] != INF) {
                System.out.print("(" + start + "-" + (v+1) + ")" + dist[v] + " ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int v=0; v<n; ++v) {
            System.out.print("Вершина " + start + "-" + (v+1) + ": ");
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
        for (int v=0; v<n; ++v) {
            if (dist[v] != INF) {
                if (first==start && last==(v+1)) {
                    System.out.print("(" + start + "-" + (v + 1) + ")" + dist[v] + " ");
                }
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
        for (int v=0; v<n; ++v) {
            if (first==start && last==(v+1)) {
                System.out.print("Вершина " + start + "-" + (v+1) + ": ");
                if (dist[v] != INF) {
                    printWay(v);
                }
                System.out.println();
            }
        }
    }
}

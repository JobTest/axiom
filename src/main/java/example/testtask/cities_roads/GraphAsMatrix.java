//package example.testtask.cities_roads;
//
//import java.util.HashSet;
//import java.util.Locale;
//import java.util.Scanner;
//
///**
//* Алгоритм Дейкстры
//* *****************
//* {@link http://www.cyberforum.ru/java-j2se/thread1047242.html}
//*/
//public class GraphAsMatrix {
//
//    public GraphAsMatrix(){// конструктор
//        System.out.print("Введите количество вершин графа N = ");
//        Scanner con = new Scanner(System.in);
//        N = con.nextInt();
//        edges = new double[N+1][N+1]; // чтобы нумерация была от 1
//        con.useLocale(Locale.US);
//        for (int i = 1; i <= N; i++){
//            System.out.println("\nВведите веса ребер для связи вершины № " + i + " c остальными:");
//            System.out.print("     ");
//            for(int j = 1; j <= N; j++){
//                System.out.printf("%5d", j);
//            } //for j
//            System.out.println();
//            System.out.print("Вес: ");
//            for (int j = 1; j <= N; j++){
//                edges[i][j] = con.nextDouble();
//            } // for j
//            edges[i][i] = 0;
//        }
//        System.out.print("\nВведите номер стартовой вершины: ");
//        startVertex = con.nextInt();
//
//        minDistances = new double [N+1];
//    } // конструктор
//
//    void d_alg(){
//        HashSet<Integer> second = new HashSet<Integer>();
//        for (int i = 1; i <= N; i++){
//            minDistances[i] = 0;
//            second.add(i);
//        }
//        minDistances[startVertex] = 0;
//
//        while (!second.isEmpty()) {
//            double minVal = 0;
//            int minVertex = 0; // задаем в качестве минимума несуществующую вершину
//            for (int v: second){
//                if (minDistances[v] >= minVal){
//                    minVal = minDistances[v];
//                    minVertex = v;
//                } // если нашли меньшее значение оценки пути
//            } // цикл по всем вершинам из второго множества
//
//            second.remove(minVertex); // удаляем вершину из множества
//            // а теперь обновляем оценки пути
//            for (int v: second){
//                if (edges[minVertex][v] > 0) {
//                    minDistances[v] = Math.max(minDistances[v],//Math.max(minDistances[v],
//                            minDistances[minVertex] +  edges[minVertex][v]);
//                } // если с вершиной есть связь
//            } // цикл по всем вершинам из второго множества
//
//        } // while множество не пусто
//
//    } // d_alg
//
//    void printDistances(){
//        System.out.println("Минимальное расстояние от вершины  " + startVertex + " " );
//        for (int i = 1; i <= N; i++){
//            System.out.println("до вершины  " + i + ": " + minDistances[i]);
//        }
//    }
//}

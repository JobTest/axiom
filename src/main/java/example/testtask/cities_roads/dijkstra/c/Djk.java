package example.testtask.cities_roads.dijkstra.c;

/**
 * {@link http://www.cyberforum.ru/java-j2se/thread1534172.html}
 * ************************
 * Алгоритм Флойда-Уоршелла
 * Нужны две матрицы:
 * 1. "матрица весов" - которой отображаются веса путей между нодами, находящимися в одном шаге друг от друга (пустые места где нет пути занимаем положительным числом - гарантированно большим любого возможного значения)
 * 2. "матрица на пересечении" - содержит значения куда идем (то есть если из двух в три - пишем в пересечении '3')
 * После окончания работы алгоритма: в "матрице весов" на пересечении - кратчайшие веса между всеми нодами и большие числа - там где путей нет. Во второй матрице на пересечении - следующий шаг (номер ноды).
 */
public class Djk {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int                n  = 5;
        int[][] MatrixVeight  = {{100,10,100,100,100}
                                ,{100,100,12,100,8}
                                ,{100,100,100,8,100}
                                ,{100,6,8,100,3}
                                ,{100,100,100,3,100}};
        int[][] MatrixHistory = {{0,2,0,0,0}
                                ,{0,0,3,0,5}
                                ,{0,0,0,4,0}
                                ,{0,2,3,0,5}
                                ,{0,0,0,4,0}};

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%4d", MatrixVeight[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%4d", MatrixHistory[i][j]);
            }
            System.out.println();
        }

        System.out.println("-----------------");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (MatrixVeight[i][j]>-1){
                    for (int w = 0; w < n; w++){
                        if (MatrixVeight[i][w] > MatrixVeight[i][j] + MatrixVeight[j][w]) {
                            MatrixVeight[i][w] = MatrixVeight[i][j] + MatrixVeight[j][w];
                            MatrixHistory[i][w] = MatrixHistory[i][j];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", MatrixVeight[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", MatrixHistory[i][j]);
            }
            System.out.println();
        }


    }
}
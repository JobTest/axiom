package example.testtask.java8.meeting;

/**
 * Сортировка массива
 * ******************
 * {@link http://edunow.su/site/content/algorithms/sortirovka_massiva}
 * {@link http://collabedit.com/n9ndm}
 */
public class Test3 {

    public static void main(String[] args) {
        int[] a  = {6,5,4,2,1,0,3,9,8,7}; //{7,8,9,3,0,1,2,4,5,6};
        int[] a1 = {6,5,4,2,1,0,3,9,8,7}; //{7,8,9,3,0,1,2,4,5,6};
        int[] a2 = {6,5,4,2,1,0,3,9,8,7}; //{7,8,9,3,0,1,2,4,5,6};
        Test3 test3 = new Test3();

        for (int n:a) System.out.print(" "+n);
        System.out.println("\n -------------------\n");
        for (int n:test3.fSort1(a1)) System.out.print(" "+n);
        System.out.println("\n -------------------\n");
        for (int n:test3.fSort2(a2)) System.out.print(" "+n);
        System.out.println("\n -------------------");
    }

    /**
     * (Цыкл вложенный в другой цыкл) в этом случае:
     * - внешний цыкл расширяется от 0 до ...
     * - внутренний цыкл каждый раз проверяет элементы внешнего цыкла И за каждый проход расширяет область...
     * -- при таком раскладке элементы будут сортироваться независимо в каждый проход, то есть, будет дублирование условий проверки
     *
     * @param x
     * @return
     */
    int[] fSort1(int[] x) {
        int cicle=0,cases=0;
        for (int i=0; i<x.length; i++) {
            for (int j=0; j<i; j++) {
                if (x[i]>x[j]) { // 21 | 24
                    x[i] = x[i]+x[j];
                    x[j] = x[i]-x[j];
                    x[i] = x[i]-x[j];
                    cases++; // 45
                }
                cicle++;
            }
        }
        System.out.println("cicle(1) = "+cicle);
        System.out.println("cases(1) = "+cases);
        return x;
    }

    /**
     * (Цыкл вложенный в другой цыкл) в этом случае:
     * - внешний цыкл сужается от ... до 0
     * - внутренний цыкл каждый раз проверяет элементы внешнего цыкла И за каждый проход сужает область...
     * -- при таком раскладе исключается дублирование условий проверки
     *
     * @param x
     * @return
     */
    int[] fSort2(int[] x) {
        int cicle=0,cases=0;
        for (int i=x.length-1; 0<i; i--) {
            for (int j=0; j<i; j++) {
                if (x[i]>x[j]) { // 7 | 18
                    x[i] = x[i]+x[j];
                    x[j] = x[i]-x[j];
                    x[i] = x[i]-x[j];
                    cases++; // 45
                }
                cicle++;
            }
        }
        System.out.println("cicle(2) = "+cicle);
        System.out.println("cases(2) = "+cases);
        return x;
    }

}

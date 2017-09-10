package example.testtask.townsandroads;

import java.util.*;

/**
 * How to sort a treemap based on its values?
 */
public class SortMapByValue2 {

    public static void main(String[] args) {
        SortMapByValue2 sorted = new SortMapByValue2();

        sorted.func0();
        System.out.println("-----------------");
        sorted.func1();
//        sorted.func2();
    }

    /*
     * http://stackoverflow.com/questions/1448369/how-to-sort-a-treemap-based-on-its-values
     * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * дело в том, что:
     * 1. я имею НЕотсортированный список дорог
     * 2. мне нужно каждый раз по какому-то признаку искать дороги (с конкретными городами) - для этого мне нужно делать сортировку
     * -  существуют и самосортирующие структуры (типа Set) - ни предусматривают дефолтную сортировку...но мне этого НЕнужно
     */
    public void func0() {
        final TreeMap<Integer,String> roads0 = new TreeMap<Integer,String>();
        roads0.put(9, "30");
        roads0.put(1, "40");
        roads0.put(2, "10");
        roads0.put(3, "30");
        roads0.put(4, "20");
        roads0.put(8, "20");
        roads0.put(5, "40");
        roads0.put(7, "30");
        roads0.put(6, "10");
        roads0.put(10, "30");

//        int countBySort = 0;
        TreeMap<Integer,String> sortBy = new TreeMap<Integer,String>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
//                return roads0.get(o1).compareTo(roads0.get(o2));
                if (roads0.get(o1).equals("30") || roads0.get(o2).equals("30")) {
//                    countBySort++;
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        sortBy.putAll(roads0);

        for (Map.Entry<Integer, String> e : sortBy.entrySet() ) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }





    /*
     * http://android.okhelp.cz/map-treemap-key-value-pair-sort-by-value-java-android-example/
     * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * дело в том, что:
     * 1. я имею НЕотсортированный список дорог
     * 2. мне нужно каждый раз по какому-то признаку искать дороги (с конкретными городами) - для этого мне нужно делать сортировку
     * -  существуют и самосортирующие структуры (типа Set) - ни предусматривают дефолтную сортировку...но мне этого НЕнужно
     */
    Map<String, String> roads = new TreeMap<String, String>();
    public void func1() {
        roads.put("9", "0");
        roads.put("1", "20");
        roads.put("4", "40");
        roads.put("2", "30");
        roads.put("3", "10");
        roads.put("6", "30");
        roads.put("5", "40");
        roads.put("8", "30");
        roads.put("7", "10");

//        for (Map.Entry<String, String> entry: ROADS.entrySet()) {
        for (Map.Entry<String, String> road : sordedRoads("30")) {
            if (road.getValue().equals("30"))
                System.out.println(road.getKey() + " : " + road.getValue());
            else
                return;
        }
    }

//    public <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> sorded(Map<K, V> roads) {
//        SortedSet<Map.Entry<K, V>> copy = new TreeSet<Map.Entry<K, V>>(
//                new Comparator<Map.Entry<K, V>>() {
//                    @Override
//                    public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
//                        int res = o1.getValue().compareTo(o2.getValue());
//                        return res != 0 ? res : 1;
//                    }
//                });
//        copy.addAll(roads.entrySet());
//        return copy;
//    }

//    int sordedRoads = 0;
    public SortedSet<Map.Entry<String, String>> sordedRoads(final String city) {
        // 1. Условие по которому нужно сортировать (это условие будет каждый раз заново иннициализироваться)
        SortedSet<Map.Entry<String, String>> copy = new TreeSet<Map.Entry<String, String>>(
                new Comparator<Map.Entry<String, String>>() {
                    @Override
                    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
//                        if (o1.getValue().equals(o2.getValue()))
//                            return 0;
//                        if (Integer.valueOf(o1.getValue()) < Integer.valueOf(o2.getValue()))
//                            return -1;
//                        return 1;

//                        if (o1.getValue().equals(city) || o2.getValue().equals(city))
//                            return -1;
//                        return 1;
                        return (o1.getValue().equals(city) || o2.getValue().equals(city)) ? -1 : 1;
                    }
                });
        // 2. Нужно передать объект для сортировки
        copy.addAll(roads.entrySet());
        return copy;
    }






    /*
     * http://www.javamadesoeasy.com/2015/04/sort-map-by-key-in-ascending-and.html
     */
    public void func2() {
        Map<Employee, Integer> map2 = new TreeMap<Employee, Integer>();
        map2.put(new Employee("Brad", "1"), 3);
        map2.put(new Employee("Amy", "2"), 1);
        map2.put(new Employee("Sam", "3"), 2);

//        System.out.println(map2);
        for (Map.Entry<Employee, Integer> entry: map2.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    class Employee implements Comparable<Employee>{
        String name;
        String id;

        public Employee(String name, String id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int compareTo(Employee otherEmployee) {
            return this.name.compareTo(otherEmployee.name);
        }

        @Override
        public String toString() {
            return "Employee{" + "name=" + name + ", id=" + id  + '}';
        }
    }

}

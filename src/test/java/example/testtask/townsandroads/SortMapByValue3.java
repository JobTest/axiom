package example.testtask.townsandroads;

import java.util.*;

public class SortMapByValue3 extends TreeMap {

    Map cities;

    public static void main(String[] args) {
        SortMapByValue3 sortMapByValue3 = new SortMapByValue3();
        sortMapByValue3.setup();
    }

    void setup() {
//        cities = new TreeMap();
//        cities.put("Moldaviya", 1);
//        cities.put("Kiev", 7);
//        cities.put("Vinnica", 2);
//        cities.put("Cherkasi", 1);
//        cities.put("Test", 1);
//
//        System.out.println("----- unsorted");
//        Iterator iCities = cities.keySet().iterator();
//        while (iCities.hasNext()) {
//            String city = (String) iCities.next();
//            System.out.println("["+cities.get(city)+"] " + city);
//        }

        System.out.println("\n----- sorted by value:");
        byValue();
    }

    void byValue() {
        Map    cities2 = new TreeMap();
        SortedMap copy = new TreeMap(new Road2(cities2));

        cities2.put("Moldaviya", 1);
        cities2.put("Kiev", 7);
        cities2.put("Vinnica", 2);
        cities2.put("Cherkasi", 1);
        cities2.put("Test", 1);

        copy.putAll(cities2);

        Iterator sorted = copy.keySet().iterator();
        while (sorted.hasNext()) {
            String city = (String) sorted.next();
            System.out.println("["+cities2.get(city)+"] " + city);
        }
    }

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     * http://www.manticmoo.com/articles/jeff/programming/java/sorting-maps-by-value.php
     * *********************************************************************************
     * Это мой тип-класса, который я собираюсь сортировать
     * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * дело в том, что:
     * 1. я имею НЕотсортированный список дорог
     * 2. мне нужно каждый раз по какому-то признаку искать дороги (с конкретными городами) - для этого мне нужно делать сортировку
     * -  существуют и самосортирующие структуры (типа Set) - ни предусматривают дефолтную сортировку...но мне этого НЕнужно
     */
    class Road2 implements Comparator {
        private Map words;

        public Road2(Map words) {
            this.words = words;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int val1 = ((Integer) (words.get(o1))).intValue();
            int val2 = ((Integer) (words.get(o2))).intValue();

            if (val1 < val2)
                return 1;
            else if (val1 == val2)
                return ((String) o1).compareTo((String) o2);
            return -1;
        }
    }

}

package example.testtask.townsandroads;

import java.util.*;

public class TreeMapDemo {


    public static void main(String[] args) {
        TreeMapDemo treeMapDemo = new TreeMapDemo();
//        treeMapDemo.func1();
        treeMapDemo.func2();
    }


    /*
     * http://www.tutorialspoint.com/java/util/treemap_headmap.htm
     */
    public void func1() {
        TreeMap<Integer, String>       treemap = new TreeMap<Integer, String>();
        SortedMap<Integer, String> treemaphead = new TreeMap<Integer, String>();

        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");
        treemap.put(15, "пятнадцать");
        treemap.put(25, "двадцать-пять");
        treemap.put(9, "девять");
        treemap.put(10, "десять");

        treemaphead = treemap.headMap(7);

        System.out.println(treemaphead);
        System.out.println(treemap.headMap(7));
    }

    public void func2() {
//        TreeMap<String, Integer> cities = new TreeMap<String, Integer>();
        SortedMap<String, Integer> cities = new TreeMap<String, Integer>();
        cities.put("Moldaviya", 1);
        cities.put("Kiev", 7);
        cities.put("Vinnica", 2);
        cities.put("Cherkasi", 1);
        cities.put("Test", 1);

        System.out.println(cities);
        System.out.println(cities.headMap("Moldaviya"));
    }

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


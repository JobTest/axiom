//package example.testtask.townsandroads;
//
//import java.util.*;
//
///**
// * Created by alexandr on 02.02.16.
// */
//public class SortMapByValue3 extends TreeMap {
//
//    public static void main(String[] args) {
//        SortMapByValue3 sortMapByValue3 = new SortMapByValue3();
//        sortMapByValue3.setup();
//    }
//
//
//    Map words;
//
//    void setup() {
//        words = new TreeMap();
//
//        addWord("rick");
//        addWord("rick");
//        addWord("hi");
//        addWord("hi");
//        addWord("hi");
//        addWord("hi");
//        addWord("haus");
//        addWord("hi");
//        addWord("test");
//        addWord("hi");
//        addWord("hi");
//        addWord("stefan");
//
//        System.out.println("--unsorted:");
//        print(words);
//
//        System.out.println("\n--sorted by value:");
//        print(createMapSortedByValue2());
//    }
//
//    void addWord(String value) {
//        Integer n = (Integer) words.get(value);
//        int count = (n != null ? n.intValue() + 1 : 1);
//        words.put(value, new Integer(count));
//    }
//
//    Map createMapSortedByValue2() {
//        //create a Comparator for words
////        SortByValueComparator2 byValueComp = new SortByValueComparator2(words);
////        //create a sorted treemap, sorted with byValueComp
////        SortedMap sortedWords = new TreeMap(byValueComp);
//        SortedMap sortedWords = new TreeMap(new SortByValueComparator2(words));
//        //add all elements from words
//        sortedWords.putAll(words);
//        //println(sortedWords.size());
//        //println(sortedWords.get("auto"));
//        return sortedWords;
//    }
//
//    void print(Map map) {
//        Iterator iter = map.keySet().iterator();
//        while (iter.hasNext()) {
//            String name = (String) iter.next();
//            System.out.println(name + " " + map.get(name));
//        }
//    }
//
//    /**
//     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
//     * http://www.manticmoo.com/articles/jeff/programming/java/sorting-maps-by-value.php
//     * *********************************************************************************
//     * Это мой тип-класса, который я собираюсь сортировать
//     */
//    class SortByValueComparator2 implements Comparator {
//        Map words;
//
//        SortByValueComparator2(Map words) {
//            this.words = words;
//        }
//
//        @Override
//        public int compare(Object arg0, Object arg1) {
//            int val0 = ((Integer) (words.get(arg0))).intValue();
//            int val1 = ((Integer) (words.get(arg1))).intValue();
//
//            if (val0 < val1) {
//                return 1;
//            }
//            else if (val0 == val1) {
//                // same counts so compare names
//                return ((String) arg0).compareTo((String) arg1);
//            }
//            else {
//                return -1;
//            }
//        }
//    }
//
//}

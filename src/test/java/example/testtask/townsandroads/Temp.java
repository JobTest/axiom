package example.testtask.townsandroads;

import java.util.*;

public class Temp {

    private static String CONST = "30";

    public static void main(String ...strings) {
        new Temp().func1();

    }


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

//        System.out.println(sortedRoads("30"));
        System.out.println(roads);


////        for (Map.Entry<String, String> entry: ROADS.entrySet()) {
//        for (Map.Entry<String, String> road : roads.entrySet()) {
//            if (road.getValue().equals("30"))
//                System.out.println(road.getKey() + " : " + road.getValue());
//            else
//                return;
//        }
    }

    /**
     * мы переопределяем put для этого списка " Map<String, String> roads "
     * а внутри вызывается put-суперкласса И
     * каждый раз когда пытаемся добавить в список объект < предварительно выполняется условие (что исключает лишние элементы)
     *
     * если полностью защитится, то нужно переобределить конструктор и putAll
     */
    @SuppressWarnings("serial")
    Map<String, String> roads = new HashMap<String, String>() {
        @Override
        public String put(String key, String value) {
            if (CONST.equals(value))
                return super.put(key, value);
            return null;
        }
    };

//    public SortedSet<Map.Entry<String, String>> sortedRoads(final String city) {
//        SortedSet<Map.Entry<String, String>> copy = new TreeSet<Map.Entry<String, String>>(
//                new Comparator<Map.Entry<String, String>>() {
//                    @Override
//                    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
//                        return (o1.getValue().equals(city) || o2.getValue().equals(city)) ? -1 : 1;
//                    }
//                });
//        copy.addAll(roads.entrySet());
//        return copy;
//    }

//    public Map<String, String> sortedRoads(final String city) {
//        return roads;
//    }
}

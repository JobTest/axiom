package example.testtask.java8;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Саша on 12.06.2015.
 * {@link http://info.javarush.ru/translation/2014/06/14/Как-правильно-делать-сортировку-в-Java.html}
 * **************************************************************************************************
 * Как правильно делать сортировку в Java
 */
public class Main {

    public static void main(String[] args) {
        // Если нужно отсортировать коллекцию, то применяйте метод Collections.sort()
        System.out.println("Если нужно отсортировать коллекцию, то применяйте метод Collections.sort()");
        List<String> list = new ArrayList<String>();

        list.add("Alexander");
        list.add("Roman");
        list.add("Vladimir");
        list.add("Vyacheslav");
        list.add("Vlad");
        list.add("Vitalik");

        mySort(list);

        for (String l : list)
            System.out.println(l);


        // Если требуется отсортировать массив, используйте метод Arrays.sort()
        System.out.println("\nЕсли требуется отсортировать массив, используйте метод Arrays.sort()");
        String[] arr = {"Alexander", "Roman", "Vladimir", "Vyacheslav", "Vlad", "Vitalik"};

        mySort(arr);

        for (String a :arr)
            System.out.println(a);


        // Применение самосортирующихся структур данных
        // Если нужно отсортировать список (List) или множество (Set), используйте структуру TreeSet для сортировки
        System.out.println("\nПрименение самосортирующихся структур данных");
        Set<String> set = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (str1.length() == str2.length())
                    return 0;
                else if (str1.length() < str2.length())
                    return -1;
                else
                    return 1;
            }
        });

//        set.add("Alexander");
//        set.add("Roman");
//        set.add("Vladimir");
//        set.add("Vyacheslav");
//        set.add("Vlad");
//        set.add("Vitalik");
        set.addAll(list);

        for (String s : set)
            System.out.println(s);


        // Если вам требуется отсортировать словарь (Map), используйте структуру TreeMap для сортировки. TreeMap сортируется по ключу (key)
        System.out.println("\nЕсли вам требуется отсортировать словарь (Map), используйте структуру TreeMap для сортировки. TreeMap сортируется по ключу (key)");
        Map<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() {
            @Override
            public int compare(Integer int1, Integer int2) {
                if (int1 == int2)
                    return 0;
                else if (int1 < int2)
                    return -1;
                else
                    return 1;
            }
        });

        map.put(5, "Alexander");
        map.put(2, "Roman");
        map.put(4, "Vladimir");
        map.put(6, "Vyacheslav");
        map.put(1, "Vlad");
        map.put(3, "Vitalik");

        for (Entry entry : map.entrySet())
            System.out.println(entry.getValue());
    }


    public static void mySort(List list){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (str1.length() == str2.length())
                    return 0;
                else if (str1.length() < str2.length())
                    return -1;
                else
                    return 1;
            }
        });
    }

    public static void mySort(String[] arr){
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (str1.length() == str2.length())
                    return 0;
                else if (str1.length() < str2.length())
                    return -1;
                else
                    return 1;
            }
        });
    }

}

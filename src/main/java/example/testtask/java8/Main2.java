package example.testtask.java8;

import java.util.*;

/**
 * Created by alexandr on 16.01.16.
 * ********************************
 * https://github.com/Home-Java8/Java8/blob/master/src/com/collection/Main.java
 */
public class Main2 {

    public static void main(String[] args) {
        // Для выполнения сортировки для интерфеса 'Collection' нужно воспользоваться классом 'Collections.sort()'
        List<String> lNames = new ArrayList<String>();
        lNames.add("Alexandr");
        lNames.add("Roma");
        lNames.add("Vlad");
        lNames.add("Ruslan");
        lNames.add("Vladimir");
        lNames.add("Vladislav");

        Collections.sort(lNames);

        for (String name:lNames) {
            System.out.println(name);
        }

        // Для выполнения сортировки массивов нужно воспользоваться классаом 'Arrays.sort()'
        String[] sNames = {"Alexandr-2","Roma-2","Vlad-2","Ruslan-2","Vladimir-2","Vladislav-2"};

        Arrays.sort(sNames);

        for (String name:sNames) {
            System.out.println(name);
        }

        // Для выполнения сортировки для интерфейса 'Map' нужно воспользоваться классом 'Comparator' или 'Comparable'
        Map<Integer, String> tmNames = new TreeMap<Integer, String>(new Comparator<Integer>(){
            @Override
            public int compare(Integer num1, Integer num2) {
                if (num1==num2)
                    return 0;
                else if (num1<num2)
                    return -1;
                else
                    return 1;
            }
        });
        tmNames.put(1,"Alexandr-3");
        tmNames.put(2,"Roma-3");
        tmNames.put(4,"Vlad-3");
        tmNames.put(3,"Ruslan-3");
        tmNames.put(5,"Vladimir-3");
        tmNames.put(6,"Vladislav-3");

        for (Map.Entry<Integer, String> name:tmNames.entrySet()) {
            System.out.println(name.getValue());
        }

        // ...
        Set<String> tsNames = new TreeSet<String>(new Comparator<String>(){
            @Override
            public int compare(String name1, String name2){
                if (name1.length()==name2.length()) {
                    return 0;
                } else if (name1.length()<name2.length()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        tsNames.add("Alexandr-4");
        tsNames.add("Roma-4");
        tsNames.add("Vlad-4");
        tsNames.add("Ruslan-4");
        tsNames.add("Vladimir-4");
        tsNames.add("Vladislav-4");

        for (String name:tsNames) {
            System.out.println(name);
        }
    }

}

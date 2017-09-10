package example.testtask.java8.meeting3;

import java.util.HashMap;

/**
 * Created by Саша on 20.02.2016.
 */
public class MyTest2 {

    public static void main(String[] args) {
        func1();
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        func2();
    }

    static void func1(){
        MyData2 maps = new MyData2();
        maps.put(new MyKeyData2("A"), 1);
        maps.put(new MyKeyData2("B"), 2);
        maps.put(new MyKeyData2("C"), 3);
        maps.put(new MyKeyData2("D"), 4);
        maps.put(new MyKeyData2("E") ,5);
        maps.put(new MyKeyData2("E"), 5);
        maps.put(new MyKeyData2("E"), 5);

        System.out.println(maps);
        System.out.println(maps.increment);
    }

    static void func2(){
        MyData2 maps = new MyData2();
//        maps.put(new String("A"), 1);
//        maps.put(new String("B"), 2);
//        maps.put(new String("C"), 3);
//        maps.put(new String("D"), 4);
//        maps.put(new String("E") ,5);
//        maps.put(new String("E"), 5);
//        maps.put(new String("E"), 5);
        maps.put("A", 1);
        maps.put("B", 2);
        maps.put("C", 3);
        maps.put("D", 4);
        maps.put("E" ,5);
        maps.put("E", 5);
        maps.put("E", 5);

        System.out.println(maps);
        System.out.println(maps.increment);
    }
}

class MyData2 extends HashMap {
    int increment;

    @Override
    public Object put(Object key, Object value) {
        increment++;
        return super.put(key, value);
    }
}


class MyKeyData2 {
    String name;

    MyKeyData2(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyKeyData2{" +
                "name='" + name + '\'' +
                '}';
    }
}
package example.testtask.java8.meeting3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Саша on 20.02.2016.
 */
public class MyTest1 {

    public static void main(String[] args) {
//        func1();
//        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        func2();
    }

    /**
     * Независимо от того как определен метод-hashCode, в список передаются разные объекты И поэтому по умолчанию метод-equals будет возвращать их как разные объекты относительно местоположения ячейки ОЗУ...
     * То есть, разные объекты будут как новые элементы добавлены в бакет, а одинаковые объекты будут обновлены
     * И если объект передается по ссылке - тогда это одинаковые объекты, если объект создается по значению (как новый-анонимный) - тогда это всегда разные объекты
     *
     * Разница HashMap от HashSet в том, что:
     * - HashMap обновит значение (до последнего) для одинакового объекта
     * - HashSet будет держать только первое значение (все последующие значения для одинаковых объектов обновляться небудут)
     * + Дело в том, что объекты-ключи для HashMap и HashSet ведут себя одинаково НО значения этих списков используется по разному:
     *   = для HashMap обновляется только значение по ключу
     *   = для HashSet сам объект-ключ собственно и является значением
     *
     * Если объект передается по значению (как новый-анонимный) тогда:
     * - независимо от того как определен метод-hashCode, по умолчанию метод-equals будет возвращать элемент как новый = то есть, такой элемент будет НЕнайден при поиске...
     *
     * Если метод-hashCode оставить по умолчанию И переопределить метод-equals, тогда:
     * - (дефолтный метод-hashCode будет всегда возвращать разный номер хеш-кода И поэтому) независимо от того как будет определен метод-equals, при передаче объекта по значению (он будет всегда как новый) элемент будет НЕнайден...
     * - в этом случае и HashMap и HashSet будут вести себя одиаково (потому-что ключем будет являтся всегда разный элемент...)
     * *******************************************************************************
     * В хеш-карте обязательным условием должны быть определены методы: hashCode() и equals()
     * Если в хеш-карте (используются дефолтные) НЕопределены методы hashCode() и/или equals() - тогда в любом случае теряется ссылка на элемент (несмотря на то что через итератор можно вывести список всех элементов)...
     * То есть, если хотя-бы один метод hashCode() или equals() НЕопределен - тогда теряется ссылка на элемент - при этом будет сильно выжираться ресурсы памяти...
     *
     * Если плохо определить метод hashCode() - тогда долго будет выполнять поиск элементов по списку
     * Если метод hashCode() будет всегда возвращать только одно значение - тогда очень долго будет выполнять поиск элементов по списку (эфективносто такого списка приравнивается LinkedList)
     *
     * Если плохо или неправильно определить метод equals() - тогда поиск элементов в списке будет выполняться НЕправльно/ошибочно - при этом будет выжираться ресурсы памяти...
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * HashSet отличается от HashMap тем что в ключ и является собственно самим элементом-значением
     * В остальном, весь внутрений механизм работы со списком устроен идентично как у HashMap
     *
     * При правильном определении методов hashCode() и equals():
     * - 'HashMap' вернет последнее-обновленное значение элемента (для дублирующих элементов)
     * - 'HashSet' вернет самое первое значение элемента (для дублирующих элементов)
     */
    public static void func1(){
        MyData1 myData1a = new MyData1("A",1,"A1");
        MyData1 myData1b = new MyData1("B",2,"B2");
        MyData1 myData1c = new MyData1("C",3,"C3");
        MyData1 myData1d = new MyData1("D",4,"D4");
        MyData1 myData1e = new MyData1("E",5,"E5");

        Map<MyData1, String> hmaps = new HashMap();
        hmaps.put(myData1a,"A");
        hmaps.put(myData1b,"B");
        hmaps.put(myData1c,"C");
        hmaps.put(myData1d,"D");
        hmaps.put(myData1e,"E1");
        hmaps.put(myData1e,"E2");
        hmaps.put(myData1e,"E3");

        System.out.println(hmaps);
        for (Map.Entry<MyData1, String> hmap : hmaps.entrySet()){
            System.out.println(hmap);
        }
        System.out.println(hmaps.get(myData1c));
        System.out.println(hmaps.get(myData1e));

        System.out.println();

        Set<MyData1> smaps = new HashSet();
        smaps.add(myData1a);
        smaps.add(myData1b);
        smaps.add(myData1c);
        smaps.add(myData1d);
        smaps.add(myData1e);
        smaps.add(myData1e);
        smaps.add(myData1e);

        System.out.println(smaps);
        for (MyData1 smap : smaps){
            System.out.println(smap);
        }
    }

    public static void func2(){
        Map<MyData1, String> hmaps = new HashMap();
        hmaps.put(new MyData1("A",1,"A1"),"A");
        hmaps.put(new MyData1("B",2,"B2"),"B");
        hmaps.put(new MyData1("C",3,"C3"),"C");
        hmaps.put(new MyData1("D",4,"D4"),"D");
        hmaps.put(new MyData1("E",5,"E1"),"E1");
        hmaps.put(new MyData1("E",5,"E2"),"E2");
        hmaps.put(new MyData1("E",5,"E3"),"E3");

        System.out.println(hmaps);
        for (Map.Entry<MyData1, String> hmap : hmaps.entrySet()){
            System.out.println(hmap);
        }
        System.out.println(hmaps.get(new MyData1("C",3)) + " == " + hmaps.containsKey(new MyData1("C",3)));


        System.out.println();

        Set<MyData1> smaps = new HashSet();
        smaps.add(new MyData1("A", 1, "A1"));
        smaps.add(new MyData1("B", 2, "B2"));
        smaps.add(new MyData1("C", 3, "C3"));
        smaps.add(new MyData1("D", 4, "D4"));
        smaps.add(new MyData1("E", 5, "E1"));
        smaps.add(new MyData1("E", 5, "E2"));
        smaps.add(new MyData1("E", 5, "E3"));

        System.out.println(smaps);
        for (MyData1 smap : smaps){
            System.out.println(smap);
        }
        System.out.println( smaps.contains(new MyData1("E", 5, "E2")) );
    }
}

class MyData1 {
    String name;
    int ago;
    String id;

    public MyData1(String name, int ago) {
        this.name = name;
        this.ago = ago;
    }
    public MyData1(String name, int ago, String id) {
        this.name = name;
        this.ago = ago;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        MyData1 that = (MyData1) o;

        if (ago==that.ago && name.equals(that.name))
            return true;
        return false;
    }
//    @Override
//    public boolean equals(Object o) {
//        return false;
//    }

    @Override
    public int hashCode(){
        return 10;
    }

    @Override
    public String toString() {
        return "MyData1{" +
                "name='" + name + '\'' +
                ", ago=" + ago +
                ", id=" + id +
                '}';
    }
}
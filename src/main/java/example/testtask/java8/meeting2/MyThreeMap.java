package example.testtask.java8.meeting2;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * {@link http://www.ibm.com/developerworks/ru/library/l-data_structures_08/}
 * *************************************************************************
 * Хеш-карты (например 'TreeMap','TreeSet') которые используют структуру черно-красного дерева имеют встроеную функцию которая сортирует список элементов на основе алгоритма...
 * (то есть, подобно функции 'hash' в HashMap) По умолчанию функция 'hashCode()' уже присутсвует в каждом объекте класса...
 * Но нет по умолчанию такой функции 'compareTo()' для самосортирующих структур, которые на основе черно-красного дерева.
 * Поэтому если класс - элемент списка 'TreeMap','TreeSet' - с НЕимеет реализацию 'Comparable' или 'Comparator', тогда в этом случае такое поведение приведет к ошибке >> ClassCastException...
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Каждый узел является центральным элементом, который должен лежать между листьями, его потомками (согласно порядку сортировки)
 * Обычно:
 * - слева внизу размещается элемент с минимальным значением
 * - справа внизу размещается элемент с максимальным значением
 * - посредине вверху размещается элемент со средним значением
 * Такой список выстраивается слева на право...как полукруг
 *
 * > Узел (сбалансированный) это всегда один элемент, который в случае балансировки возвращает - '0'
 * > В момент добавления младшего элемента в список (листья, в левую часть) получаем НЕсбалансированное дерево, который в случае балансировки возвращает - '-1'
 * > В момент добавления старшего элемента в список (листья, в правую часть) получаем НЕсбалансированное дерево, который в случае балансировки возвращает - '1'
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * В момент добавления элемента в список структуры черно-красного дерева выполняется условие которое определено внутри 'compareTo()', при этом алгоритм пробегает по всему списку и сравнивает значение элемента И тем самым сортирует порядок элементов в списке...
 * > Если определить чтобы функция 'compareTo()' всегда будет возвращать значение '1' - тогда элементы выстраиваются по порядку их добавления в список
 * > Если определить чтобы функция 'compareTo()' всегда будет возвращать значение '-1' - тогда элементы выстраиваются в обратном порядке их добавления в список
 * > Если определить чтобы функция 'compareTo()' всегда будет возвращать значение '0' - тогда получим только первый И один-единственный элемент списка
 *
 * >> Важно понимать что балансировка дерева выполняется только при наличии реального условия для сортировки списка...
 * >> Если-же условия для сортировки списка будет всегда возвращать '1' или '-1' - в этом случае элементы будут просто выстраиваться в линейном порядке без балансировки - эфективность такого списка будет приравниваться к (обычному) линейному спискку типа LinkedList
 * >> Если-же условия для сортировки списка будет всегда возвращать '0' - в этом случае никакой балансировки НЕбудет - в результате будет всегда только один/первый элемент списка
 *
 * >>> Только при наличии реального условия для сортировки списка (интерфейс) 'Set' исключает дублирующие элементы списка (инначе элемент НЕбудет распознан как дублирующий)!
 */
public class MyThreeMap {

    public static void main(String[] args) {
        /////////////////////////////////////////
        Set<Human> setHumans = new TreeSet();

        setHumans.add(new  Human("Hyston",5));
        setHumans.add(new  Human("Pavel",6));
        setHumans.add(new  Human("Roma",7));
        setHumans.add(new  Human("Tim",8));
        setHumans.add(new  Human("Alexandr",1));
        setHumans.add(new  Human("Bill",2));
        setHumans.add(new  Human("Dima",3));
        setHumans.add(new  Human("George",4));
        setHumans.add(new Human("Hyston", 10));
        setHumans.add(new Human("Hyston", 9));

        for (Object human : setHumans) {
            System.out.println( ((Human)human) );
        }

        /////////////////////////////////////////
        Map<Human,Integer> mapHumans = new TreeMap();

        mapHumans.put(new Human("Hyston", 5), 1);
        mapHumans.put(new Human("Pavel", 6), 2);
        mapHumans.put(new Human("Roma", 7), 3);
        mapHumans.put(new Human("Tim", 8), 4);
        mapHumans.put(new Human("Alexandr", 1), 5);
        mapHumans.put(new Human("Bill", 2), 6);
        mapHumans.put(new Human("Dima", 3), 7);
        mapHumans.put(new Human("George", 4), 8);
        mapHumans.put(new Human("Hyston", 10), 9);
        mapHumans.put(new Human("Hyston", 9), 10);

//        for (Human human : mapHumans.keySet()) {
//            System.out.println( ((Human)human) );
//        }
//        for (Map.Entry<Human,Integer> human : mapHumans.entrySet()) {
//            System.out.println( human.getValue() );
//        }
    }

}

//class Human {
//    String name;
//    int ago;
//
//    public Human(String name, int ago){
//        this.name = name;
//        this.ago = ago;
//    }
//
//    @Override
//    public String toString() {
//        return "Human{" +
//                "name='" + name + '\'' +
//                ", ago=" + ago +
//                '}';
//    }
//}

class Human implements Comparable {
    String name;
    int ago;

    public Human(String name, int ago){
        this.name = name;
        this.ago = ago;
    }

    /**
     * Механизм сортировки класса 'TreeSet'
     * метод 'compareTo' возвращает значения которые обоснованы ( -1|0|1 )
     * '-1'
     *
     * @param that
     * @return
     */
    @Override
    public int compareTo(Object that){
        return name.compareTo(((Human)that).name);
//        return 1;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", ago=" + ago +
                '}';
    }
}
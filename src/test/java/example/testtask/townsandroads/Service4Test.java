package example.testtask.townsandroads;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * *******************************
 ** {@link http://javist.ru/kak-realizovat-interfejs-cloneable/}
 *  {@link https://habrahabr.ru/post/246993/}
 *
 * *******************************
 ** {@link http://www.programcreek.com/2009/02/a-simple-treeset-example/}
 *  {@link http://javatalks.ru/topics/16238?page=1#77748}
 *  {@link https://habrahabr.ru/post/162017/}
 ** {@link http://www.javaprobooks.ru/java-программирование/использование-treemap-java-example-keyset.html}
 *
 * *******************************
 ** Почему нельзя использовать byte[] в качестве ключа в HashMap? {@link https://habrahabr.ru/post/162017/}
 * HashMap, LinkedHashMap и TreeMap в Java {@link http://jdevnotes.blogspot.com/2011/08/hashmap-linkedhashmap-treemap-java.html}
 * {@link https://habrahabr.ru/post/114518/} Из данных тестов видно, что производительность vector может быть существенно (в 3.9 раза) выше, чем map, при этом однократное заполнение vector и его сортировка тоже производиться быстрее (в 10 раз) чем в map. Таким образом, сортированные вектора стоит использовать в ситуациях редких изменений данных и частых поисков, например для данных, загружаемых во время старта программы и в дальнейшем редко меняющихся. И как всегда, не нужно забывать, что преждевременная оптимизация – зло.
 * Различие реализации HashMap и TreeMap|Преимущества TreeMap над HashMap|Преимущества HashMap над TreeMap {@link http://vidiki.webcam/w.php?v=85a-sHg5otU}
 ** Производительность основных коллекций java. Сравнение с аналогами .Net {@link http://korzh.net/2010-11-proizvoditelnost-osnovnyx-kollekcij-java-sravnenie-s-analogami-net.html}
 ** Что лучше использовать, HashMap или TreeMap, если необходимо часто добавлять новые события и еще чаще обращаться по ключу? Однозначно- TreeMap. {@link http://javatalks.ru/topics/10212}
 *** Коллекции в Java {@link http://www.iterekhin.ru/2013/07/java.html}
 *                    {@link https://appliedjava.wordpress.com/2010/09/23/java-collections-framework/}
 *                    {@link https://dzone.com/articles/java-collection-performance}
 *                    {@link http://lewisleo.blogspot.jp/2012/08/java-collections-performance.html}
 *                    {@link https://github.com/leolewis/sandbox/tree/master/src}
 *                    {@link http://4.bp.blogspot.com/-eor4DBhjVFU/UEFtIsHli3I/AAAAAAAAAGA/vg6oUjFMjDU/s1600/ListPerf.png}
 *                    {@link http://sourceforge.net/projects/javacollections/}
 * АНАЛИЗЭФФЕКТИВНОСТИ И ЭВОЛЮЦИЯ СТРУКТУР ДАННЫХ {@link http://www.self-organization.ru/articles/d_v_02.pdf}
 * https://appliedjava.wordpress.com/2010/09/23/java-collections-framework/
 *. http://www.sql.ru/forum/287450/hashtable-vs-hashmap
 *
 * https://books.google.com.ua/books?id=iPHtCfZQyqQC&pg=PT333&lpg=PT333&dq=%D1%81%D0%BA%D0%BE%D1%80%D0%BE%D1%81%D1%82%D1%8C+%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B+treemap+%D0%B8+hashmap&source=bl&ots=tdlwBT5imL&sig=x-KgW9UJknlu7fM1fDH9m72AnfE&hl=ru&sa=X&ved=0ahUKEwittpKeudbKAhWJ2ywKHTfzBTo4ChDoAQg4MAU#v=onepage&q=%D1%81%D0%BA%D0%BE%D1%80%D0%BE%D1%81%D1%82%D1%8C%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D1%8B%20treemap%20%D0%B8%20hashmap&f=false
 * http://files2.enaza.ru/bhv/BOOKS_11_10_12/pdf/978-5-9775-0671-7.pdf
 *
 * (treemap values sorted) http://stackoverflow.com/questions/2864840/treemap-sort-by-value
 * (Дублирование ключей в java.util.TreeMap) http://www.ibm.com/developerworks/ru/library/j-5things3/
 **                                          http://www.sql.ru/forum/201156/sortirovka-po-znacheniyam-v-treemap
 **                                          http://www.quizful.net/post/Java-TreeMap
 *
 * {@link http://www.intuit.ru/studies/courses/16/16/lecture/27131?page=2} Порядок хранения элементов в этой коллекции не совпадает с порядком их добавления. Порядок элементов в коллекции также может меняться во времени. HashMap обеспечивает постоянное время доступа для операций get и put.
 * * * * * * * * * * * * * * * * *
 * http://www.javaprobooks.ru/java-программирование/использование-treemap-java-example-keyset.html
 * http://javatalks.ru/topics/42618
 * http://www.sql.ru/forum/1103403/udalenie-elementov-iz-treeset
 * http://www.cyberforum.ru/java/thread1336587.html
 * Миф об уникальности объектов в Set и ключей в Map {@link http://www.quizful.net/post/set-map-non-unique-elements}
 *
 * Загрузка исходного кода приложения Google App Engine {@link http://jdevnotes.blogspot.com/search/label/google%20app%20engine}
 *
*/
public class Service4Test {

    Map<CityName, City> cities;

    @Before
    public void setUp() {
        cities = new HashMap<CityName, City>(1000);
    }

//    @Test
//    public void testMm(){
//        TreeMap <String, Integer>tMap = new TreeMap<String, Integer>();
//        tMap.put("Sunday", 1);
//        tMap.put("Sunday", 12);
//        tMap.put("Sunday", 123);
//        tMap.put("Monday", 2);
//        tMap.put("Tuesday", 3);
//        tMap.put("Wednesday", 4);
//        tMap.put("Thursday", 5);
//        tMap.put("Friday", 6);
//        tMap.put("Saturday", 7);
//        tMap.put("Sat1", 7);
//        tMap.put("Sat2", 8);
//        tMap.put("Sat3", 9);
//        tMap.put("Sat4", 10);
//        tMap.put("Sat5", 11);
//        tMap.put("Sat6", 12);
//
//        System.out.println("-------------------\nПолучаем все ключи > " + tMap.keySet());
//        System.out.println("\n-------------------\nПолучаем все значения > " + tMap.values());
//        System.out.println("\n-------------------\nПолучаем пятое значение > " + tMap.get("Thursday"));
//        System.out.println("\n-------------------\nПолучаем первый ключ и его значение > " + tMap.firstKey() + " : " + tMap.get(tMap.firstKey()));
//        System.out.println("\n-------------------\nПолучаем последние ключ и значение > " + tMap.lastKey() + " : " + tMap.get(tMap.lastKey()));
//        System.out.println("\n-------------------\nУдаляем первый ключ и значение > " + tMap.remove(tMap.firstKey()) + " >> " + tMap.keySet() + " : " + tMap.values());
//        System.out.println("\n-------------------\nУдаляем последний ключ и значение > " + tMap.remove(tMap.lastKey()) +" >> " + tMap.keySet() + " : " + tMap.values());
//        System.out.println("\n===================\n" + tMap.subMap("Sat1","Sat6"));
//    }

    /*
     * HashMap - может иметь одинаковые ключи в бакете...при этом внутри самого бавкета определяется уникальность ключа
     *         - если нужно быстрый поиск элемента в списке + может иметь дублирующие ключи
     *         + Порядок хранения элементов в этой коллекции не совпадает с порядком их добавления. Порядок элементов в коллекции также может меняться во времени. HashMap обеспечивает постоянное время доступа для операций get и put.
     * TreeMap - НЕможет иметь одинаковых ключей НО может сортировать список по разным признакам ключа + может возвращать несколько отсортированных элементов из диапазона списка
     *         - если нужно сортировать список и фильтровать диапазон его элементов по какому-нибудь призкаку + отсутствуют дублирующие ключи
     *         - (это НЕмассив, подобно LinkedList) здесь динамически выделяется размер для списка для ранее неизвестного количества
     */
//    @Test
//    public void testMmm(){
////        Map<My, Integer> map = new HashMap<My, Integer>();
//        Map<My, Integer> map = new TreeMap<My, Integer>(new Comparator<My>() {
//            @Override
//            public int compare(My o1, My o2) {
//                if (o1.hashCode() == o2.hashCode())
//                    return 0;
//                else if (o1.hashCode() < o2.hashCode())
//                    return 1;
//                else
//                    return -1;
//            }
//        });
//
//        map.put(new My("Bruce", "Willis"), 1);
//        map.put(new My("Arnold", "Schwarzenegger"), 2);
//        map.put(new My("Jackie", "Chan"), 3);
//        map.put(new My("Sylvester", "Stallone"), 4);
//        map.put(new My("Chuck", "Norris-5"), 5);
//        map.put(new My("Chuck", "Norris-6"), 6);
//        map.put(new My("Chuck", "Norris-7"), 7);
//        map.put(new My("Chuck", "Norris-8"), 8);
//        map.put(new My("Chuck", "Norris-9"), 9);
//
//        System.out.println(map.values());
//
//        for(Map.Entry e : map.entrySet()){
//            System.out.println("["+e.getValue()+"] "+e.getKey());
//        }
//
//        System.out.println(map.get(new My("Chuck", "Norris-7")));
//        System.out.println(map.get(new My("Chuck", "Norris")));
//    }
//
//    class My {
//        String firstName;
//        String lastName;
//
//        My(String firstName, String lastName){
//            this.firstName = firstName;
//            this.lastName = lastName;
//        }
//
//        public String getFirstName() {
//            return firstName;
//        }
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//        public String getLastName() {
//            return lastName;
//        }
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            My that = (My) o;
//            if (firstName.equals(that.firstName) && lastName.equals(that.lastName))
//                return true;
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            return firstName.hashCode();
//        }
//
//        @Override
//        public String toString() {
//            return "My{" +
//                    "firstName='" + firstName + '\'' +
//                    ", lastName='" + lastName + '\'' +
//                    '}';
//        }
//    }





    @Test
    public void testMy(){
        addCity(new CityName("City-A"), new City(100.0, 100.0));
        addCity(new CityName("City-B"), new City(200.0, 200.0));
        addCity(new CityName("City-C"), new City(300.0, 300.0));
        addCity(new CityName("City-D"), new City(400.0, 400.0));
        addCity(new CityName("City-E"), new City(500.0, 500.0));
        addCity(new CityName("City-E"), new City(501.1, 501.1));
        addCity(new CityName("City-E"), new City(502.2, 502.2));
        addCity(new CityName("City-E"), new City(503.3, 503.3));

        System.out.println( "--------------------" );
        System.out.println( cities.values() );

        try {
            System.out.println( "--------------------[ City ]" );
            for (Map.Entry<CityName, City> city : getCities(new CityName("City")).entrySet()){
                System.out.println("["+city.getKey()+"] "+city.getValue());
            }
            System.out.println( "--------------------[ City-C ]" );
            for (Map.Entry<CityName, City> city : getCities(new CityName("City-C")).entrySet()){
                System.out.println("["+city.getKey()+"] "+city.getValue());
            }
            System.out.println( "--------------------[ City-E ]" );
            for (Map.Entry<CityName, City> city : getCities(new CityName("City-E")).entrySet()){
                System.out.println("["+city.getKey()+"] "+city.getValue());
            }
            System.out.println( "====================[ City-C ]" );
            System.out.println("[City-C (first)] "+getFirstCity(new CityName("City-C")));
            System.out.println( "====================[ City-E ]" );
            System.out.println("[City-E (first)] "+getFirstCity(new CityName("City-E")));
            System.out.println( "====================[ City-C ]" );
            System.out.println("[City-C (last)] "+getLastCity(new CityName("City-C")));
            System.out.println( "====================[ City-E ]" );
            System.out.println("[City-E (last)] "+getLastCity(new CityName("City-E")));
            System.out.println( "====================[ City-E ]" );
            System.out.println("[City-E (2)] "+getCity(new CityName("City-E"), (byte) 2));
        } catch (CloneNotSupportedException e) { System.err.println(e.getMessage()); }
    }

    public City addCity(CityName name, City city){
        while (cities.containsKey(name)) { name.incId(); }
        return cities.put(name, city);
    }

    public Map<CityName, City> getCities(CityName name) throws CloneNotSupportedException {
        Map<CityName, City> copy = new HashMap<CityName, City>();
        while (cities.containsKey(name)) {
            copy.put(name.clone(), cities.get(name));
            name.incId();
        }
        return copy;
    }

    public City getFirstCity(CityName name){
        return cities.get(name);
    }

    public City getLastCity(CityName name) throws CloneNotSupportedException {
        for (Map.Entry<CityName, City> city : getCities(name).entrySet()){
            return city.getValue();
        }
        return null;
    }

    public City getCity(CityName name, byte id){
        name.incId(id);
        return cities.get(name);
    }


    class MyRoad {
        private String name;
        private String nameCity1;
        private String nameCity2;

        public MyRoad(String name, String nameCity1, String nameCity2){
            this.name = name;
            this.nameCity1 = nameCity1;
            this.nameCity2 = nameCity2;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyRoad)) return false;

            MyRoad myRoad = (MyRoad) o;

            if (!name.equals(myRoad.name)) return false;
            if (!nameCity1.equals(myRoad.nameCity1)) return false;
            if (!nameCity2.equals(myRoad.nameCity2)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result1 = nameCity1.hashCode();
            int result2 = nameCity2.hashCode();
            return result1+result2;
        }

        @Override
        public String toString() {
            return "MyRoad{" +
                    "name='" + name + '\'' +
                    ", nameCity1='" + nameCity1 + '\'' +
                    ", nameCity2='" + nameCity2 + '\'' +
                    '}';
        }
    }

    class MyRoadName {
        private String name;
    }

    class City {
        private double latitude;
        private double longitude;

        public City(double latitude, double longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
        public double getLongitude() {
            return longitude;
        }
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "City{" +
                    "latitude=" + latitude +
                    " longitude=" + longitude +
                    '}';
        }
    }

    class CityName implements Cloneable {
        private String name;
        private byte     id;

        public CityName(String name){
            this.name = name;
            this.id   = (byte) name.hashCode();
        }

        public void incId(){
            id = (byte) (id + 1);
        }
        public void incId(byte id){
            this.id = (byte) (this.id + id);
        }

        @Override
        public boolean equals(Object o) {
            CityName that = (CityName) o;
            if (id == that.id && name.equals(that.name))
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public CityName clone() throws CloneNotSupportedException {
            return (CityName)super.clone();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}


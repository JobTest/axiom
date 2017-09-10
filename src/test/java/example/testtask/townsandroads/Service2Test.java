package example.testtask.townsandroads;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandr on 29.01.16.
 */
public class Service2Test {

//    Map<MyTownName, MyTown> towns;
//
//    @Before
//    public void setUp(){
//        towns = new HashMap<MyTownName, MyTown>();
//    }
//
//
//    public void addTown(String name, MyTown town){
//        int id = 0;
//        if (towns.containsKey(new MyTownName(name))) {
//            // Если (ключевое) имя Найдено - тогда нужно узнать его ID
//            System.out.println(towns.containsKey(new MyTownName(name)) +" "+ towns.get(new MyTownName(name)).getId());
//            id = towns.get(new MyTownName(name)).getId()+1;
//            town.setId(id);
//            towns.put(new MyTownName(name), town);
//        } else {
//            // Если (ключевое) имя НЕнайдено - тогда спокойно добавляем новый элемент в список с ID=0
//            System.out.println(towns.containsKey(new MyTownName(name)) +" "+ id);
//            town.setId(id);
//            towns.put(new MyTownName(name), town);
//        }
//    }

//    @Test
//    public void testOne(){
//        addTown("Town-1", new MyTown(100, 100));
//        addTown("Town-2", new MyTown(200, 200));
//        addTown("Town-3", new MyTown(300, 300));
//        addTown("Town-4", new MyTown(400, 400));
//        addTown("Town-5", new MyTown(500, 500));
//        addTown("Town-5", new MyTown(600, 600));
//        addTown("Town-5", new MyTown(700, 700));
//        addTown("Town-5", new MyTown(800, 800));
////        towns.put(new MyTownName("Town-1",0), new MyTown(100, 100, 0));
////        towns.put(new MyTownName("Town-2",0), new MyTown(200, 200, 0));
////        towns.put(new MyTownName("Town-3",0), new MyTown(300, 300, 0));
////        towns.put(new MyTownName("Town-4",0), new MyTown(400, 400, 0));
////        towns.put(new MyTownName("Town-5",0), new MyTown(500, 500, 0));
////        towns.put(new MyTownName("Town-5",0), new MyTown(600, 600, 0));
////        towns.put(new MyTownName("Town-5",0), new MyTown(700, 700, 0));
////        towns.put(new MyTownName("Town-5",0), new MyTown(800, 800, 0));
//
//        System.out.println("=====================");
//        System.out.println(towns.values());
////        System.out.println("---------------------");
////        System.out.println(towns.get(new MyTownName("Town-1")));
////        System.out.println(towns.get(new MyTownName("Town-5")));
////        System.out.println(towns.get(new MyTownName("Town-5")).hashCode());
////        System.out.println("---------------------");
////        System.out.println(towns.containsKey(new MyTownName("Town-1")));
////        System.out.println(towns.containsKey(new MyTownName("Town-2")));
//    }

    @Test
    public void testMy2(){
        System.out.println("=====================");
        addMap("A",0, new Value(100));
        addMap("B",0, new Value(200));
        addMap("C",0, new Value(300));
        addMap("D",0, new Value(400));
        addMap("E",0, new Value(500));
        addMap("E",1, new Value(501));
        addMap("E",2, new Value(502));
        addMap("E",3, new Value(503));

        System.out.println( map.values() );
//        System.out.println("---------------------");
//        System.out.println( map.get(new MyKey("E",1)) );
//        System.out.println( map.containsKey(new MyKey("E",0)) );
    }

    @Test
    public void testMy3(){
        System.out.println("=====================");
        addMap("A", new Value(100));
        addMap("B", new Value(200));
        addMap("C", new Value(300));
        addMap("D", new Value(400));
        addMap("E", new Value(500));
        addMap("E", new Value(501));
        addMap("E", new Value(502));
        addMap("E", new Value(503));

        System.out.println( map.values() );

        System.out.println("---------------------");
        for (Map.Entry<MyKey, Value> m : getMap("E").entrySet()){
            System.out.println("["+m.getKey().getName()+"] "+m.getValue());
        }
    }

    Map<MyKey, Value> map = new HashMap<MyKey, Value>();

    public void addMap(String name, int id, Value value){
        map.put(new MyKey(name,id), value);
    }
    public void addMap(String name, Value value){
        int id = 0;
//        System.out.println( "["+map.containsKey(new MyKey(name, id))+"] "+map.get(new MyKey(name, id)));
        if (map.containsKey(new MyKey(name,id))) {
//            System.out.println( ">> "+map.get(new MyKey(name,id)).getId());
            boolean isId = true;
            while (isId) {
                id = id + 1;
                if (!map.containsKey(new MyKey(name,id))) {
                    isId = false;
                }
            }
//            System.out.println(">> "+id);
            value.setId(id);
            map.put(new MyKey(name,id), value);
        } else {
//            System.out.println( "> 0");
            value.setId(id);
            map.put(new MyKey(name, id), value);
        }
    }

    public Map<MyKey, Value> getMap(String name){
        Map<MyKey, Value> copy = new HashMap<MyKey, Value>();
        for (int id = 0; id<100; id++) {
            if (map.containsKey(new MyKey(name, id))) {
                copy.put(new MyKey(name, id), map.get(new MyKey(name, id)));
            } else {
                return copy;
            }
        }
        return copy;
    }

    class MyKey {
        private String name;
        private int id;

        public MyKey(String name){
            this.name = name;
        }
        public MyKey(String name, int id){
            this.name = name;
            this.id   = name.hashCode()+id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            MyKey myKey = (MyKey) o;
            if (id != myKey.id) return false;
            if (!name.equals(myKey.name)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            return result;
        }
    }

    class Value {
        private int x;
        private int id;

        public Value(int x){
            this.x = x;
        }
        public Value(int x, int id){
            this.x = x;
            this.id = id;
        }

        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "x=" + x +
                    ", id=" + id +
                    '}';
        }
    }


//    class MyTown {
//        private double latitude;
//        private double longitude;
//        private int id;
//
//        public MyTown(double latitude, double longitude){
//            this.latitude = latitude;
//            this.longitude = longitude;
//        }
//        public MyTown(double latitude, double longitude, int id){
//            this.latitude = latitude;
//            this.longitude = longitude;
//            this.id = id;
//        }
//
//        public double getLatitude() {
//            return latitude;
//        }
//        public void setLatitude(double latitude) {
//            this.latitude = latitude;
//        }
//        public double getLongitude() {
//            return longitude;
//        }
//        public void setLongitude(double longitude) {
//            this.longitude = longitude;
//        }
//        public void setId(int id){
//            this.id = id;
//        }
//        public int getId(){
//            return id;
//        }
//
//        @Override
//        public String toString() {
//            return "MyTown{" +
//                    "latitude=" + latitude +
//                    ", longitude=" + longitude +
//                    ", id=" + id +
//                    '}';
//        }
//    }
//
//    class MyTownName {
//        private String name;
//        private int id;
//
//        public MyTownName(String name){
//            this.name = name;
//        }
//        public MyTownName(String name, int id){
//            this.name = name;
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//        public void setName(String name) {
//            this.name = name;
//        }
//        public int getId() {
//            return id;
//        }
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            MyTownName that = (MyTownName) o;
//            System.out.println("name="+name+"; this="+id+"; that="+that.getId());
//            if (name.equals(that.getName())
//                    && id==that.getId())
//                return true;
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = name.hashCode();
//            return result;
//        }
//    }

}




